package tcp;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

import javax.swing.*;
import javax.swing.border.*;

public class MyClient extends JFrame { // ������̳�JFrame��
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PrintWriter writer; // ����PrintWriter�����
	private Socket socket; // ����Socket����
	private JTextArea ta = new JTextArea(); // ����JtextArea����
	private JTextField tf = new JTextField(); // ����JtextField����
	Container cc; // ����Container����
	private BufferedReader reader;
	
	public MyClient(String title) { 
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
				ta.append("Client:"+tf.getText() + '\n');
				ta.setSelectionEnd(ta.getText().length());
				tf.setText(""); // ���ı������
			}
		});
	}
	
	public void connect() { // �����׽��ַ���
		ta.append("Try connect...\n"); // �ı�������ʾ��Ϣ
		try { // ��׽�쳣
			socket = new Socket("127.0.0.1", 8998); // ʵ����Socket����
			writer = new PrintWriter(socket.getOutputStream(), true);
			ta.append("Connect successful!\nYou can input messages in the blank now!\n\n"); // �ı�������ʾ��Ϣ
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream())); // ʵ����BufferedReader����
			getServerMessage(); // ����getClientMessage()����
		} catch (Exception e) {
			e.printStackTrace(); // ����쳣��Ϣ
		}
	}
	
	private void getServerMessage() {
		try {
			while (true) { // ����׽���������״̬
				if (reader.ready()) {
					// ��ÿͻ�����Ϣ
					ta.append("Server:" + reader.readLine()+"\n");
				}
			}
		} catch (Exception e) {
			e.printStackTrace(); // ����쳣��Ϣ
		}
		try {
			if (reader != null) {
				reader.close(); // �ر���
			}
			if (socket != null) {
				socket.close(); // �ر��׽���
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
