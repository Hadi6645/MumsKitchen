package clientServer;

import java.io.IOException;
import java.util.logging.Logger;

import server.ocsf.client.AbstractClient;

public class Client extends AbstractClient {
	private static final Logger LOGGER =
			Logger.getLogger(Client.class.getName());
	
	private ChatClientCLI chatClientCLI;	
	public Client(String host, int port) {
		super(host, port);
		this.chatClientCLI = new ChatClientCLI(this);
	}
	
	@Override
	protected void connectionEstablished() {
		// TODO Auto-generated method stub
		super.connectionEstablished();
		LOGGER.info("Connected to server.");
		
		try {
			chatClientCLI.loop();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		chatClientCLI.displayMessage(msg);
	}
	
	@Override
	protected void connectionClosed() {
		// TODO Auto-generated method stub
		super.connectionClosed();
		chatClientCLI.closeConnection();
	}
	
	public static void main(String[] args) throws IOException {
		/*if (args.length != 2) {
			System.out.println("Required arguments: <host> <port>");
		} else {
			String host = args[0];
			int port = Integer.parseInt(args[1]);

			SimpleChatClient chatClient = new SimpleChatClient(host, port);
			chatClient.openConnection();
		}*/
		String host = "localhost";
		//int port = Integer.parseInt(args[1]);

		Client chatClient = new Client(host, 3002);
		chatClient.openConnection();
		System.out.println("Fetching Data, Please Wait.");
	}
}
