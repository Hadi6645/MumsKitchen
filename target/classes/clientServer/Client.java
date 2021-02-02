package clientServer;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

import server.ocsf.client.AbstractClient;

public class Client extends AbstractClient {
	private static final Logger LOGGER =
			Logger.getLogger(Client.class.getName());
	
	private ChatClientCLI chatClientCLI;
	private CompletableFuture<Object> completableFuture;
	public Client(String host, int port) {
		super(host, port);
		this.chatClientCLI = new ChatClientCLI(this);
	}
	public Client() {
		super(userInterface.App.getHost(), userInterface.App.getPort());
		this.chatClientCLI = new ChatClientCLI(this);
	}
	public void setCompletableFuture(CompletableFuture<Object> completableFuture)
	{
		this.completableFuture=completableFuture;
	}
	@Override
	protected void connectionEstablished() {
		// TODO Auto-generated method stub
		super.connectionEstablished();
		LOGGER.info("Connected to server.");
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		chatClientCLI.handleMessageFromServer(msg,completableFuture);
	}
	
	@Override
	protected void connectionClosed() {
		// TODO Auto-generated method stub
		super.connectionClosed();
		//chatClientCLI.closeConnection(); //not sure we need it ,
		// we want to open connection every time we send something, not keep it open
	}
	
	public boolean openConnectionWithServer() {
		try {
			this.openConnection();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public static void main(String[] args) throws IOException {
	}
}