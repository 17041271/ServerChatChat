package test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

	Socket soc;

	ArrayList<Socket> listSoc = new ArrayList<Socket>();

	ServerSocket s = null;

	public Server() throws IOException {
		try {
			s = new ServerSocket(9999);
			System.out.println("Server on");
			while (true) {
				soc = s.accept();

				ServerHandle handle = new ServerHandle(listSoc, soc);
				listSoc.add(soc);
				Thread t1 = new Thread(handle);
				t1.start();
				System.out.println(t1 + "Ket noi");
			}
		} catch (Exception e) {
			System.out.println(e);
			// should be e.printStackTrace();
		} finally {
			if (s != null) {
				s.close();
			}
		}
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			new Server();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}