package com.capgemini.taxi;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SocketBidirectionalClient {
	private String hostname;
	private int port;
	Socket socketClient;
	//UserInterface usint;

	public SocketBidirectionalClient(String hostname, int port) {
		this.hostname = hostname;
		this.port = port;
	}

	public void connect() throws UnknownHostException, IOException {
		System.out.println("Attempting to connect to " + hostname + ":" + port);
		socketClient = new Socket(hostname, port);
		System.out.println("Connection Established");
	}

	public void readResponse() throws IOException {
		String userInput;
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));

		System.out.println("RESPONSE FROM SERVER:");
		while ((userInput = stdIn.readLine()) != null) {
			System.out.println(userInput);
		}
	}

	public void askServer() throws IOException {
		int x, y, howMany;
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
		Scanner scanner = new Scanner(System.in);
		System.out.println("What's your x position?");
		x = scanner.nextInt();
		System.out.println("What's your y position? ");
		y = scanner.nextInt();
		System.out.println("How many taxis would you like me to display? ");
		howMany = scanner.nextInt();
		scanner.close();
		writer.write("TAXI:" + x + ":" + y + ":" + howMany);
		writer.newLine();
		writer.flush();
		readResponse();
	}

	public static void main(String arg[]) {
		// Creating a SocketClient object, IP instead of localhost
		SocketBidirectionalClient client = new SocketBidirectionalClient("localhost", 9991);
/*		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInterface window = new UserInterface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});*/
		try {
			// trying to establish connection to the server
			client.connect();
			// asking server for time
			client.askServer();
			// waiting to read response from server
			// client.readResponse();

		} catch (UnknownHostException e) {
			System.err.println("Host unknown. Cannot establish connection");
		} catch (IOException e) {
			System.err.println("Cannot establish connection. Server may not be up." + e.getMessage());
		}
	}
}
