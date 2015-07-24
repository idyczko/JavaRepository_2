package com.capgemini.socketserver;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class SocketServer {
private ServerSocket server;
private int port;

public SocketServer(int port){
	this.port=port;
}

public void start() throws IOException{
	System.out.println("Starting server at port: " + port);
	server=new ServerSocket(port);
	
	System.out.println("Waiting for clients...");
	Socket client = server.accept();
	sendWelcomeMessage(client);
}

private void sendWelcomeMessage(Socket client) throws IOException{
BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
writer.write("Hello, you are connected to my server.");
writer.flush();
writer.close();
}

public static void main(String[] args) {
    int portNumber = 9990;
    
    try {
        SocketServer socketServer = new SocketServer(portNumber);
        socketServer.start();
        
        } catch (IOException e) {
        e.printStackTrace();
    }
}

}
