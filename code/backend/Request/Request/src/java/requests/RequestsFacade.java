package requests;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.orm.PersistentException;
import org.orm.PersistentSession;

public class RequestsFacade {

    RequestFacadeBeanLocal requestFacadeBean = lookupRequestFacadeBeanLocal();

    private static RequestsFacade requestsFacade;
    private static PersistentSession session;

    public static RequestsFacade getInstance() {
        if(requestsFacade == null)
            requestsFacade = new RequestsFacade();
        return requestsFacade;
    }

    public static PersistentSession getSession() throws PersistentException {
        if(session == null || !session.isConnected())
                session = DiagramasPersistentManager.instance().getSession();
        return session;
}

    public String sayHello(String name){
        return requestFacadeBean.sayHello(name);
    }

    /**
     * 
     * @param usernameAndTokenAsJson
     */
    public void updateUserToken(String usernameAndTokenAsJson) throws JsonKeyInFaultException, UserDoesNotExistException, TokenIsInvalidException, PersistentException {
        requestFacadeBean.updateUserToken(usernameAndTokenAsJson);
    }

    public void createClient(String usernameAndTokenAsJSon) throws JsonKeyInFaultException, PersistentException, UserAlreadyExistsException, ClientAlreadyExistsException{
        requestFacadeBean.createClient(usernameAndTokenAsJSon);
    }

    public void createPersonalTrainer(String usernameAndTokenAsJSon) throws UserAlreadyExistsException, PersonalTrainerDoesNotExistException, PersonalTrainerAlreadyExistsException, JsonKeyInFaultException, PersistentException {
        requestFacadeBean.createPersonalTrainer(usernameAndTokenAsJSon);
    }

    /**
     * 
     * @param requestInfoAsJSON
     */
    public void submitRequest(String requestInfoAsJSON) throws JsonKeyInFaultException, UserDoesNotExistException, PersistentException, ClientDoesNotExistException, PersonalTrainerDoesNotExistException, TokenIsInvalidException {
            requestFacadeBean.submitRequest(requestInfoAsJSON);
    }

    /**
     * 
     * @param requestIdAndResponseAsJSON
     */
    public void replyToRequest(String requestIdAndResponseAsJSON) throws JsonKeyInFaultException, PersistentException, PersonalTrainerDoesNotExistException, RequestDoesNotExistException, UserDoesNotExistException, TokenIsInvalidException {
            requestFacadeBean.replyToRequest(requestIdAndResponseAsJSON);
    }

    /**
     * 
     * @param clientUsernameAndPTUsernameAsJSON
     */
    public String listClientRequestsByPersonalTrainer(String clientUsernameAndPTUsernameAsJSON) throws JsonKeyInFaultException, TokenIsInvalidException, UserDoesNotExistException, PersistentException, PersonalTrainerDoesNotExistException {
            return requestFacadeBean.listClientRequestsByPersonalTrainer(clientUsernameAndPTUsernameAsJSON);
    }

    /**
     * 
     * @param usernameAsJSON
     */
    public String listClientRequestsByClient(String usernameAsJSON) throws TokenIsInvalidException, UserDoesNotExistException, PersistentException, JsonKeyInFaultException, ClientDoesNotExistException {
            return requestFacadeBean.listClientRequestsByClient(usernameAsJSON);
    }

    private RequestFacadeBeanLocal lookupRequestFacadeBeanLocal() {
        try {
            Context c = new InitialContext();
            return (RequestFacadeBeanLocal) c.lookup("java:global/Request/RequestFacadeBean!requests.RequestFacadeBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}