import com.wei.socket.Server;


public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Server server=Server.getInstance();
		server.openServer();
	}

}
