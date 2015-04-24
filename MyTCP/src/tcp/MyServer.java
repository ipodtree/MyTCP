package tcp;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

public class MyServer extends JFrame { // ������MyTcp
	private static final long serialVersionUID = 1L;
	private PrintWriter writer; // ����PrintWriter�����
	private JTextArea ta = new JTextArea(); // ����JtextArea����
	private JTextField tf = new JTextField(); // ����JtextField����
	Container cc; // ����Container����
	private BufferedReader reader; // ����BufferedReader����
	private ServerSocket server; // ����ServerSocket����
	private Socket socketserver; // ����Socket����socket
	
	public MyServer(String title) { 
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cc = this.getContentPane(); // ʵ��������
		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new BevelBorder(BevelBorder.RAISED));
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(ta);
		cc.add(tf, "South"); // ���ı�����ڴ�����²�
		tf.addActionListener(new ActionListener() {
			// ���¼�
			public void actionPerformed(ActionEvent e) {
				// ���ı�������Ϣд����
				writer.println(tf.getText());
				// ���ı�������Ϣ��ʾ���ı�����
				ta.append("Server:"+tf.getText() + '\n');
				ta.setSelectionEnd(ta.getText().length());
				tf.setText(""); // ���ı������
			}
		});
	}
	
	void getserver() {
		try {
			server = new ServerSocket(8998); // ʵ����Socket����
			ta.append("Server socket successfully created!\n"); // �����Ϣ
			while (true) { // ����׽���������״̬
				ta.append("Waiting for the client:\n\n"); // �����Ϣ
				socketserver = server.accept(); // ʵ����Socket����
				writer = new PrintWriter(socketserver.getOutputStream(), true);
				reader = new BufferedReader(new InputStreamReader(socketserver
						.getInputStream())); // ʵ����BufferedReader����
				getClientMessage(); // ����getClientMessage()����
			}
		} catch (Exception e) {
			e.printStackTrace(); // ����쳣��Ϣ
		}
	}
	
	private void getClientMessage() {
		try {
			while (true) { // ����׽���������״̬
				if (reader.ready()) {
					// ��ÿͻ�����Ϣ
					writer = new PrintWriter(socketserver.getOutputStream(), true);
					ta.append("Client:" + reader.readLine()+"\n");
				}
			}
		} catch (Exception e) {
			e.printStackTrace(); // ����쳣��Ϣ
		}
		try {
			if (reader != null) {
				reader.close(); // �ر���
			}
			if (socketserver != null) {
				socketserver.close(); // �ر��׽���
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
