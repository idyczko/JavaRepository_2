package com.capgemini.taxi;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SocketBidirectionalServer {

	private static final double METERS_RADIUS = 1000;
	private ServerSocket serverSocket;
	private ArrayList<Taxi> taxiList = new ArrayList<Taxi>();
	private int port;
	private Lock serverLock = new ReentrantLock();

	public SocketBidirectionalServer(int port, WorkArea workArea, int numberOfTaxis) {
		this.port = port;
		for (int i = 0; i < numberOfTaxis; i++) {
			Taxi taxi = new Taxi(workArea, i + 1);
			Thread thread = new Thread(taxi);
			thread.start();
			taxiList.add(taxi);
		}
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
			Thread thread = new Thread(new ClientHandler(client, this));
			thread.start();
		}
	}

	public ArrayList<Data> giveTaxis(Position userPosition, int taxis) {
		serverLock.lock();
		try {
			ArrayList<Data> list = new ArrayList<Data>();
			ArrayList<Data> list_2 = new ArrayList<Data>();
			for (Taxi taxi : taxiList) {
				if (taxi.isTaxiAvailable()) {
					list.add(new Data(taxi.getPosition(), taxi.getId()));
				}
			}
			Collections.sort(list, new DataComparator(userPosition));
			for (Data taxi : list) {
				if (userPosition.distance(taxi.getPosition()) >= METERS_RADIUS || list_2.size() == taxis) {
					break;
				}
				list_2.add(taxi);
			}
			return list_2;
		} finally {
			serverLock.unlock();
		}
	}

	public void addTaxi(Taxi taxi) {
		Thread thread = new Thread(taxi);
		thread.start();
		taxiList.add(taxi);
	}

	public int getNumberOfTaxis() {
		return taxiList.size();
	}

	public static void main(String[] args) {
		Position position = new Position(5, 4);
		WorkArea workArea = new WorkArea(new Position(100, 100), position);
		int portNumber = 9991;
		try {
			SocketBidirectionalServer socketServer = new SocketBidirectionalServer(portNumber, workArea, 100);
			socketServer.start();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
