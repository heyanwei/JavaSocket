import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;

import com.wei.socket.Client;


public class Test {

	public static void main(String[] args) {
		System.out.println("Socket connection...");
		Client client=Client.getInstance();
		client.connectServer();
		BufferedReader inputReader=new BufferedReader(new 
				InputStreamReader(System.in));

		while(true){
			try {
				DataOutputStream dos=new DataOutputStream(client.socket.getOutputStream());
				String readLine=inputReader.readLine();
				dos.write(readLine.getBytes("gbk"));
				dos.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}

}
