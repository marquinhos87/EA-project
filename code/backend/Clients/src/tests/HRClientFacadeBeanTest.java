package tests;

import hrclient.ClientAlreadyExistsException;
import hrclient.HRClientFacadeBean;
import hrclient.HRClientFacadeBeanBean;
import org.orm.PersistentException;

public class HRClientFacadeBeanTest {
    public static void main(String[] args) {
        String json = "{\n" +
                "  \"username\": \"filipe\",\n" +
                "  \"password\": \"password\",\n" +
                "  \"name\": \"Ricardo Pereira\",\n" +
                "  \"email\": \"ricardo@email.com\",\n" +
                "  \"sex\": \"M\",\n" +
                "  \"birthday\": \"2000-06-19\"\n" +
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
