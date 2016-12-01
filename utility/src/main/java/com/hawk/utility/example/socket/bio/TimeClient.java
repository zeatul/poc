package com.hawk.utility.example.socket.bio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TimeClient {

	public static void main(String[] args) {

		int port = 8080;

		if (args != null && args.length > 0) {
			port = Integer.valueOf(args[0]);
		}
		
		Socket socket = null;
		BufferedReader in = null;
		PrintWriter out = null;
		
		try {
			socket = new Socket("localhost",port);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(),true);
			out.println("QUERY TIME ORDER");
			System.out.println("Send order to server succeed.");
			String resp = in.readLine();
			System.out.print("Now is " +resp);
		} catch (Exception e) {
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
			
			if (socket != null){
				try{
					socket.close();
				}catch(Exception e){
					e.printStackTrace();
				}
				socket = null;
			}
		}
	}
}
