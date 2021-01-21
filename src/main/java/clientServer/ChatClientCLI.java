package clientServer;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import control.ServerInstruction;


public class ChatClientCLI {
	
	private Client client;
	private Thread requestThread;
	
	public ChatClientCLI(Client client) {
		this.client = client;
	}
	
	
	public void sendInstructionToServer(ServerInstruction sInstruction, CompletableFuture<Object> completableFuture) throws IOException {
		if (sInstruction == null) {
			System.out.print("cannot send request to server - sInstruction is null");
			completableFuture.cancel(true); //send CancelException to waiting client
			return;
		}
		requestThread = new Thread(new Runnable() {

			public void run() {
				
				try{
					boolean isConnected = client.openConnectionWithServer();
					if (!isConnected) {
						System.err.println("An error occured,cannot connect to server.");
						return;
					}
					client.sendToServer(sInstruction);
				} catch (IOException e1) {
					System.err.println("An error occured,cannot send reuqest to server.");
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					System.err.println("An error occured,cannot send reuqest to server.");
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.err.println("An error occured,cannot send reuqest to server.");
					e.printStackTrace();
				}
			}
		});
		requestThread.start();
	}
	
	public void handleMessageFromServer(Object msg,CompletableFuture<Object> completableFuture) {
		try {
			completableFuture.complete(msg); // send result to the waiting client
			client.closeConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
