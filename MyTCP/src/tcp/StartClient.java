package tcp;

public class StartClient {
	public static void main(String args[]){//在主方法中调用createJFrame()方法
		MyClient client = new MyClient("Client"); // 创建本例对象
		client.setSize(400, 400); 
		client.setVisible(true);
		client.connect(); 
	}

}
