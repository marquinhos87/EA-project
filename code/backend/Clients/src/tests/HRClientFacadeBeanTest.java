package tests;

import hrclient.ClientAlreadyExistsException;
import hrclient.HRClientFacadeBean;
import hrclient.HRClientFacadeBeanBean;
import org.orm.PersistentException;

public class HRClientFacadeBeanTest {
    public static void main(String[] args) {
        String json = "{\n" +
                "  \"username\": \"josepereira\",\n" +
                "  \"password\": \"password\",\n" +
                "  \"name\": \"José Pereira\",\n" +
                "  \"email\": \"jose@email.com\",\n" +
                "  \"sex\": \"M\",\n" +
                "  \"birthday\": \"1997-06-19\"\n" +
                "}";
        HRClientFacadeBean hrClientFacadeBean = new HRClientFacadeBeanBean();
        try {
            hrClientFacadeBean.createClient(json);
        } catch (ClientAlreadyExistsException e) {
            e.printStackTrace();
        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }
}
