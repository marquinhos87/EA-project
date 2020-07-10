package rest;

import hrpersonaltrainer.*;
import org.orm.PersistentException;

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
		out.print(Utils.makeError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "GET method is not allowed."));
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
					case "registerPersonalTrainer":
						registerPersonalTrainer(response, getDataFromPost(request));
						break;
					case "getPersonalTrainerProfileByPersonalTrainer":
						getPersonalTrainerProfileByPersonalTrainer(response, getDataFromPost(request));
						break;
					case "getPersonalTrainerProfileByPersonalByClient":
						getPersonalTrainerProfileByClient(response, getDataFromPost(request));
						break;
					case "editPersonalTrainerProfile":
						editPersonalTrainerProfile(response, getDataFromPost(request));
						break;
					case "getPersonalTrainers":
						getPersonalTrainers(response, getDataFromPost(request));
						break;
					case "submitClassification":
						submitClassification(response, getDataFromPost(request));
						break;
					case "getPersonalTrainerClients":
						getPersonalTrainerClients(response, getDataFromPost(request));
						break;
					case "addClientToPersonalTrainer":
						addClientToPersonalTrainer(response, getDataFromPost(request));
						break;
					case "registerClient":
						registerClient(response, getDataFromPost(request));
						break;
					case "updateClientToken":
						updateClientToken(response, getDataFromPost(request));
						break;
					default:
						response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
						response.getWriter().print(Utils.makeError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "target is not allowed."));
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
		} catch (PersonalTrainerNotExistsException e) {
                    e.printStackTrace();
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    out.print(Utils.makeError(HttpServletResponse.SC_NOT_FOUND, "personal trainer with specified username does not exist - " + e.getMessage() +  "."));
		} catch (InvalidPasswordException e) {
                    e.printStackTrace();
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    out.print(Utils.makeError(HttpServletResponse.SC_UNAUTHORIZED, "invalid password - " + e.getMessage() + "."));
		} catch (ClientNotExistsException e) {
                    e.printStackTrace();
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    out.print(Utils.makeError(HttpServletResponse.SC_NOT_FOUND, "client with specified username does not exist - " + e.getMessage() +  "."));
		} catch (ClientAlreadyExistsException e) {
                    e.printStackTrace();
                    response.setStatus(HttpServletResponse.SC_CONFLICT);
                    out.print(Utils.makeError(HttpServletResponse.SC_CONFLICT, "client with specified username already exists - " + e.getMessage() +  "."));
		} catch (TokenIsInvalidException e) {
                    e.printStackTrace();
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    out.print(Utils.makeError(HttpServletResponse.SC_UNAUTHORIZED, "token is invalid - " + e.getMessage() +  "."));
		} catch (UserNotExistsException e) {
                    e.printStackTrace();
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    out.print(Utils.makeError(HttpServletResponse.SC_NOT_FOUND, "user with specified username does not exist - " + e.getMessage() +  "."));
		} catch (UserAlreadyExistsException e) {
                    e.printStackTrace();
                    response.setStatus(HttpServletResponse.SC_CONFLICT);
                    out.print(Utils.makeError(HttpServletResponse.SC_CONFLICT, "user with specified username already exists - " + e.getMessage() +  "."));
		}  catch (Exception e) {
                    e.printStackTrace();
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    out.print(Utils.makeError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "unexpected error occurred."));
                }
	}

	private void registerClient(HttpServletResponse response, String json) throws IOException, JsonKeyInFaultException, PersistentException, PersonalTrainerAlreadyExistsException, UserAlreadyExistsException {
		PrintWriter out = response.getWriter();
		HRPersonalTrainerFacade.getInstance().createClient(json);
		response.setStatus(HttpServletResponse.SC_OK);
		out.print(Utils.makeSuccess200(null));
	}

	private void updateClientToken(HttpServletResponse response, String json) throws IOException, PersistentException, JsonKeyInFaultException, TokenIsInvalidException, UserNotExistsException {
		PrintWriter out = response.getWriter();
		HRPersonalTrainerFacade.getInstance().updateClientToken(json);
		response.setStatus(HttpServletResponse.SC_OK);
		out.print(Utils.makeSuccess200(null));
	}

	private void addClientToPersonalTrainer(HttpServletResponse response, String json) throws IOException, PersistentException, ClientAlreadyExistsException, PersonalTrainerNotExistsException, JsonKeyInFaultException, TokenIsInvalidException {
		PrintWriter out = response.getWriter();
		HRPersonalTrainerFacade.getInstance().addClientToPersonalTrainer(json);
		response.setStatus(HttpServletResponse.SC_OK);
		out.print(Utils.makeSuccess200(null));
	}

	private void getPersonalTrainerClients(HttpServletResponse response, String json) throws IOException, PersistentException, TokenIsInvalidException, PersonalTrainerNotExistsException, JsonKeyInFaultException {
		PrintWriter out = response.getWriter();
		String personalTrainerClients = HRPersonalTrainerFacade.getInstance().getPersonalTrainerClients(json);
		response.setStatus(HttpServletResponse.SC_OK);
		out.print(Utils.makeSuccess200(personalTrainerClients));
	}

	private void submitClassification(HttpServletResponse response, String json) throws IOException, PersistentException, TokenIsInvalidException, ClientNotExistsException, JsonKeyInFaultException, PersonalTrainerNotExistsException {
		PrintWriter out = response.getWriter();
		HRPersonalTrainerFacade.getInstance().submitClassification(json);
		response.setStatus(HttpServletResponse.SC_OK);
		out.print(Utils.makeSuccess200(null));
	}

	private void getPersonalTrainers(HttpServletResponse response, String json) throws IOException, PersistentException, TokenIsInvalidException, JsonKeyInFaultException, ClientNotExistsException {
		PrintWriter out = response.getWriter();
		String personalTrainers = HRPersonalTrainerFacade.getInstance().getPersonalTrainers(json);
		response.setStatus(HttpServletResponse.SC_OK);
		out.print(Utils.makeSuccess200(personalTrainers));
	}

	private void editPersonalTrainerProfile(HttpServletResponse response, String json) throws IOException, PersistentException, TokenIsInvalidException, PersonalTrainerNotExistsException, JsonKeyInFaultException {
		PrintWriter out = response.getWriter();
		HRPersonalTrainerFacade.getInstance().editPersonalTrainertProfile(json);
		response.setStatus(HttpServletResponse.SC_OK);
		out.print(Utils.makeSuccess200(null));
	}

	private void getPersonalTrainerProfileByClient(HttpServletResponse response, String json) throws IOException, PersistentException, TokenIsInvalidException, PersonalTrainerNotExistsException, JsonKeyInFaultException, ClientNotExistsException {
		PrintWriter out = response.getWriter();
		String profile = HRPersonalTrainerFacade.getInstance().getPersonalTrainerProfileByClient(json);
		response.setStatus(HttpServletResponse.SC_OK);
		out.print(Utils.makeSuccess200(profile));
	}

	private void getPersonalTrainerProfileByPersonalTrainer(HttpServletResponse response, String json) throws IOException, PersistentException, TokenIsInvalidException, PersonalTrainerNotExistsException, JsonKeyInFaultException {
		PrintWriter out = response.getWriter();
		String profile = HRPersonalTrainerFacade.getInstance().getPersonalTrainerProfileByPersonalTrainer(json);
		response.setStatus(HttpServletResponse.SC_OK);
		out.print(Utils.makeSuccess200(profile));
	}

	private void registerPersonalTrainer(HttpServletResponse response, String json) throws IOException, JsonKeyInFaultException, PersistentException, PersonalTrainerAlreadyExistsException, UserAlreadyExistsException {
		PrintWriter out = response.getWriter();
		String oldNewToken = HRPersonalTrainerFacade.getInstance().createPersonalTrainer(json);
		response.setStatus(HttpServletResponse.SC_OK);
		out.print(Utils.makeSuccess200(oldNewToken));
	}

	private static void login(HttpServletResponse response, String json) throws IOException, PersonalTrainerNotExistsException, PersistentException, JsonKeyInFaultException, InvalidPasswordException, UserNotExistsException {
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