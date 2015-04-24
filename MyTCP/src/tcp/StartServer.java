package tcp;

public class StartServer {
	public static void main(String args[]){//在主方法中调用createJFrame()方法
		MyServer server = new MyServer("Server");
		server.setSize(400, 400); 
		server.setVisible(true);
		server.getserver();

	}

}
