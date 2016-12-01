package com.hawk.utility.example.socket.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServer {

	public static void main(String[] args) {

		int port = 8080;

		if (args != null && args.length > 0) {
			port = Integer.valueOf(args[0]);
		}

		ServerSocket server = null;

		try {
			server = new ServerSocket(port);
			System.out.println("The time server is start in port : " + port);
			Socket socket = null;
			while (true) {
				socket = server.accept();
				new Thread(new TimeServerHandler(socket)).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (server != null) {
				System.out.println("The time server close");
				try {
					server.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				server = null;
			}
		}
	}

	public static class TimeServerHandler implements Runnable {
		private Socket socket;

		public TimeServerHandler(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			BufferedReader in = null;
			PrintWriter out = null;
			try {
				in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
				out = new PrintWriter(this.socket.getOutputStream(), true);
				String currentTime = null;
				String body = null;
				while (true) {
					body = in.readLine();
					if (body == null)
						break;
					System.out.println("The time server receive order : " + body);
					currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new java.util.Date().toString() : "BAD ORDER";
					out.println(currentTime);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if (in != null){
					try {
						in.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					in = null;
				}
				
				if (out != null){
					try {
						out.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					out = null;
				}
				
				if (this.socket != null){
					try {
						this.socket.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					this.socket = null;
				}
			}
		}

	}
}
