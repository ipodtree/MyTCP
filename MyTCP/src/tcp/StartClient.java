package tcp;

public class StartClient {
	public static void main(String args[]){//���������е���createJFrame()����
		MyClient client = new MyClient("Client"); // ������������
		client.setSize(400, 400); 
		client.setVisible(true);
		client.connect(); 
	}

}
