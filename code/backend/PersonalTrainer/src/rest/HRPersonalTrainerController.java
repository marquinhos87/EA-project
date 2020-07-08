package rest;

import hrpersonaltrainer.*;
import org.orm.PersistentException;
import redis.clients.jedis.exceptions.JedisConnectionException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "HRPersonalTrainerController", urlPatterns = {"/*"})
public class HRPersonalTrainerController extends HttpServlet {

	/**
	 *
	 * @param request
	 * @param response
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/json"); // indicar que o conteúdo é json
		PrintWriter out = response.getWriter();
		response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		out.print(Utils.makeError(405, "GET method is not allowed."));
	}

	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		try {
			response.setContentType("application/json"); // indicar que o conteúdo é json
			String[] url = request.getRequestURI().split("/");
			String target = url[url.length-1];
				switch (target) {
					case "login":
						login(response, getDataFromPost(request));
						break;
					case "register":
						register(response, getDataFromPost(request));
						break;
					default:
						response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
						response.getWriter().print(Utils.makeError(405, "target is not allowed."));
				}
		} catch (PersistentException e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			out.print(Utils.makeError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "hibernate session error."));
		} catch (PersonalTrainerAlreadyExistsException e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_CONFLICT);
			out.print(Utils.makeError(HttpServletResponse.SC_CONFLICT, "personal trainer with specified username already exists - " + e.getMessage() + "."));
		} catch (JsonKeyInFaultException e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			out.print(Utils.makeError(HttpServletResponse.SC_BAD_REQUEST, "json key in fault - " + e.getMessage() + "."));
		} catch (JedisConnectionException e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			out.print(Utils.makeError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "could not connect to redis server."));
		} catch (PersonalTrainerNotExistsException e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			out.print(Utils.makeError(HttpServletResponse.SC_NOT_FOUND, "personal trainer with specified username does not exist -" + e.getMessage() +  "."));
		} catch (InvalidPasswordException e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			out.print(Utils.makeError(HttpServletResponse.SC_UNAUTHORIZED, "invalid password - " + e.getMessage() + "."));
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			out.print(Utils.makeError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "unexpected error occurred"));
		}
	}

	private void register(HttpServletResponse response, String json) throws IOException, JsonKeyInFaultException, PersistentException, PersonalTrainerAlreadyExistsException {
		PrintWriter out = response.getWriter();
		String oldNewToken = HRPersonalTrainerFacade.getInstance().createPersonalTrainer(json);
		response.setStatus(HttpServletResponse.SC_OK);
		out.print(Utils.makeSuccess200(oldNewToken));
	}

	private static void login(HttpServletResponse response, String json) throws IOException, PersonalTrainerNotExistsException, PersistentException, JsonKeyInFaultException, InvalidPasswordException {
		PrintWriter out = response.getWriter();
		String token = HRPersonalTrainerFacade.getInstance().loginPersonalTrainer(json);
		response.setStatus(HttpServletResponse.SC_OK);
		out.println(Utils.makeSuccess200(token));
	}

	private static String getDataFromPost(HttpServletRequest request) throws IOException {
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = request.getReader().readLine()) != null) {
			sb.append(line);
		}
		return sb.toString();
	}

}