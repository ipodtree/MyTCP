package tcp;

public class StartServer {
	public static void main(String args[]){//���������е���createJFrame()����
		MyServer server = new MyServer("Server");
		server.setSize(400, 400); 
		server.setVisible(true);
		server.getserver();

	}

}
