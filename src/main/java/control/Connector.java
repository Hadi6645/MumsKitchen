package control;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import clientServer.ChatClientCLI;

public class Connector {
	
	public static final int TIMEOUT = 30; //time out time in seconds.
	
	public static Object connectToServer(ServerInstruction sInstruction)
	{
		Object response = null;
		// send instruction to the server and get a response
		CompletableFuture<Object> completableFuture = new CompletableFuture<Object>();
		ChatClientCLI clientCli = new ChatClientCLI();
		try {
			clientCli.sendInstructionToServer(sInstruction,completableFuture);
			response = completableFuture.get(TIMEOUT,TimeUnit.SECONDS);	
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return response;
	}
}
