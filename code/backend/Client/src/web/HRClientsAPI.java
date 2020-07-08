package web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "HRClientsAPI", urlPatterns = {"HRClientsAPI/*"})
public class HRClientsAPI extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] url = request.getRequestURI().toString().split("/");
        String target = url[url.length-1], line = "";

        response.setContentType("aplication/json");

        StringBuilder sb = new StringBuilder();

        while((line = request.getReader().readLine())!=null)sb.append(line);

        System.err.println(sb.toString());

        /*HRClientFacade facade = HRClientFacade.getInstance();

        switch (target){
            case "updateUserToken":
                facade.updateUserToken();
        }*/
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
