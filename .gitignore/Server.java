/*����һ������ȴ��û�����Ĺ��ܣ����ֻ�����������ܺͷ���Ա���죿�� */

package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
	private ServerSocket server;
	private BufferedReader in;
	private PrintWriter out;
	Socket socket = null;

	public Server(int Port) throws IOException {
		server = new ServerSocket(Port, 2);
		System.out.println("������");
		start();
	}

	public void run() {
		try {
			socket = server.accept();
			// ������ʾ����ʱ��д�����ڵ�λ��
			System.out.println(socket.getInetAddress() + "������" + socket.getPort() + " " + socket.getLocalPort());
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedWriter pw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			out = new PrintWriter(pw, true);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		while (true) {
			try {
				String info;
				// !!��ȡ������Ϣ��λ��
				while ((info = in.readLine()) != null) {
					// ��дʾ��
					if (info.equals("1")) {
						info = in.readLine();
						//��д��ʶ��Ϊ1
						out.println("1");
						out.println(info);
					}
					// ֱ����Ӧʾ��
					if (info.equals("2")) {
						info = in.readLine();
						System.out.println(info);
					}
					if (info.equals("quit"))
						break;
					else
						System.out.println("wrong");
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (null != socket) {
					try {
						socket.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public static class Client extends Thread {
		Socket socket;
		BufferedReader in;
		PrintWriter out;

		public Client(String addr, int Port) throws IOException {
			socket = new Socket(addr, Port);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
			start();
		}

		// run����������������������Ϣ
		public void run() {
			while (true) {
				String info;
				while (true) {
					try {
						if ((info = in.readLine()) != null)
							System.out.println("get" + info);
					} catch (IOException e) {
					}
				}
			}
		}

		public void send() {
			int i;
			i = (int) (Math.random() * 3 + 1);
			out.println(i);
			i = (int) (Math.random() * 10);
			out.println(i);
		}
		
		public void send(String info){
			out.println("1");
			out.println(info);
		}
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		new Server(6666);
	}
}
