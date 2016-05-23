package com.atto.netty.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			Integer port = Integer.valueOf(8080);
			if(args != null && args.length >0){
				try{
					port = Integer.valueOf(args[0]);
				}catch(NumberFormatException e){
					e.printStackTrace();
				}
			}
			
			ServerSocket server = null;
			try{
					server = new ServerSocket(port.intValue());
					System.out.println("The time server is start in port:" + port.intValue());
					Socket socket = null;
					while(true){
						socket = server.accept();
						new Thread(new TimeServerHandler(socket)).start();
					}
			}catch (IOException e) {
				e.printStackTrace();
			}finally{
				if(server != null){
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
