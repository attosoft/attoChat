package com.atto.netty.pio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.atto.netty.bio.TimeServerHandler;

public class TimeServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int port = 8080;
		if(args != null && args.length > 0){
			try {
				port = Integer.valueOf(args[0]).intValue();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		ServerSocket server = null;
		try {
			server = new ServerSocket(port);
			System.out.println("The time Server is start in port : " + port);
			Socket socket = null;
			TimeServerHandlerExecutePool singleExecutePool = new TimeServerHandlerExecutePool(50, 10000);
			while (true){
				socket = server.accept();
				singleExecutePool.execute(new TimeServerHandler(socket));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			if (server != null) {
				System.out.println("The time server close");
				try {
					server.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				server = null;
			    }
		}
	}

}
