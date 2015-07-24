package com.capgemini.taxi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class ClientHandler implements Runnable {

	private Socket client;
	private SocketBidirectionalServer server;
	private Position userPosition;
	private int howMany;

	public ClientHandler(Socket client, SocketBidirectionalServer server) {
		this.client = client;
		this.server = server;
	}

	public void run() {
		// while(true){
		try {
			System.out.println("Thread started with name:" + Thread.currentThread().getName());
			readResponse();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// }
	}

	private void readResponse() throws IOException, InterruptedException {
		String userInput;
		// Scanner scanner = new Scanner(System.in);
		int x, y;
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(client.getInputStream()));
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		while ((userInput = stdIn.readLine()) != null) {
			String[] request = userInput.split(":");
			if (request[0].equals("TAXI")) {
				writer.write("REQUEST TO SEND TAXI RECEIVED. TAXI LIST SENDING.");
				userPosition = new Position(Integer.parseInt(request[1]), Integer.parseInt(request[2]));
				howMany = Integer.parseInt(request[3]);
				sendTaxis();
				break;
			}
			System.out.println(userInput);
		}
	}

	private void sendTaxis() throws IOException, InterruptedException {
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		ArrayList<Data> list = server.giveTaxis(userPosition, howMany);
		String message = "";
		for (Data data : list) {
			message += data.getId();
			message += "\n";
		}
		writer.write(message);
		writer.flush();
		writer.close();
	}

}
