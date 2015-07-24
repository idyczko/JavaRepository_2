package com.capgemini.socketserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocketBidirectionalServer {

	private ServerSocket serverSocket;
//	private synchronized ArrayList<Taxi> taxiList=new ArrayList<Taxi>;
	private int port;

	public SocketBidirectionalServer(int port) {
		this.port = port;
	}

	public void start() throws IOException {
		System.out.println("Starting the socket server at port:" + port);
		serverSocket = new ServerSocket(port);

		Socket client = null;

		while (true) {
			System.out.println("Waiting for clients...");
			client = serverSocket.accept();
			System.out.println("The following client has connected:" + client.getInetAddress().getCanonicalHostName());
			// A client has connected to this server. Send welcome message
			Thread thread = new Thread(new ClientHandler(client));
			thread.start();
		}
	}

	public static void main(String[] args) {
		int portNumber = 9991;
		try {
			SocketBidirectionalServer socketServer = new SocketBidirectionalServer(portNumber);
			socketServer.start();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
