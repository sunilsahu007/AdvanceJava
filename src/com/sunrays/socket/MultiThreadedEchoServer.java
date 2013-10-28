package com.sunrays.socket;

import java.io.*;
import java.net.*;

/*
 * copyright (c) sunRays Technologies Indore
 * @author: sunRays Developer
 * @url : www.sunrays.co.in
 *
 */

public class MultiThreadedEchoServer extends Thread {

	public static void main(String[] args) throws IOException {

		ServerSocket serverSocket = null;
		
		MultiThreadedEchoServer multiThreadedEchoServer = null;

		serverSocket = new ServerSocket(4444);

		System.out.println("Echo Server Started");

		Socket clientSocket = null;

		boolean flag = true;

		while (flag) {

			clientSocket = serverSocket.accept();
			
			multiThreadedEchoServer = new MultiThreadedEchoServer(clientSocket);

			multiThreadedEchoServer.start();
			
		}
		System.out.println("Echo Server Stoped");
		serverSocket.close();
	}

	private Socket clientSocket = null;
	

	public MultiThreadedEchoServer(Socket clientSocket) {
		super();
		this.clientSocket = clientSocket;
	}


	public void run() {
		try {
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),
					true);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
			String inputLine, outputLine;
			inputLine = in.readLine();
			while (inputLine != null) {
				System.out.println("Server Recived " + inputLine);
				out.println(inputLine + " .. " + inputLine);
				if (inputLine.equals("Bye."))
					break;
				inputLine = in.readLine();
			}
			out.close();
			in.close();
			clientSocket.close();

		} catch (IOException e) {

		}

	}

}