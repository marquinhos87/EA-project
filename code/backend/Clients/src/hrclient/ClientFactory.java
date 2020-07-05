package hrclient;

public class ClientFactory {

	private static ClientFactory clientFactory;

	/**
	 * Return a single instance of this class.
	 * @return Return a single instance of this class.
	 */
	public static ClientFactory getInstance() {
		if(clientFactory == null)
			clientFactory = new ClientFactory();
		return clientFactory;
	}

	/**
	 * Create different types of IClients.
	 * @param type type of client (at the moment, only exists "Client").
	 */
	public IClient createIClient(String type) {
		if(type.equals("Client"))	//	normal client
			return new Client();
		else return null;
	}

}