package test;

import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ServerHandle implements Runnable {

	ArrayList<Socket> s1;
	BufferedReader reader;
	String msg;
	Socket soc;

	public ServerHandle(ArrayList<Socket> s1, Socket soc) {
		this.s1 = s1;
		this.soc=soc;
		
	}

	public void run() {

		try {
			reader = new BufferedReader(new InputStreamReader(soc.getInputStream()));

			while ((msg = reader.readLine()) != null) {
				for (Socket test : s1) {
					BufferedWriter write = new BufferedWriter(new OutputStreamWriter(test.getOutputStream()));
					write.write(msg);
					write.newLine();
					// Đẩy dữ liệu đi
					write.flush();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}
}