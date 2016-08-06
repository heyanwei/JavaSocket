import java.util.Scanner;

import com.wei.socket.Client;


public class Test {

	
	public static void main(String[] args) {
		System.out.println("hello...");
		Client client=new Client();
		client.openSocket();
		Scanner scanner=new Scanner(System.in);
		while(true){
			String line=scanner.nextLine();
			client.sendMsg(line.getBytes());
		}
	}

}
