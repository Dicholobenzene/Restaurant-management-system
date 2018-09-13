/*这是一个想做却还没有做的功能（用手机点菜甚至还能和服务员聊天？） */

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
		System.out.println("已启动");
		start();
	}

	public void run() {
		try {
			socket = server.accept();
			// 连接提示，到时候写到该在的位置
			System.out.println(socket.getInetAddress() + "已连接" + socket.getPort() + " " + socket.getLocalPort());
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedWriter pw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			out = new PrintWriter(pw, true);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		while (true) {
			try {
				String info;
				// !!读取输入信息的位置
				while ((info = in.readLine()) != null) {
					// 回写示例
					if (info.equals("1")) {
						info = in.readLine();
						//回写标识符为1
						out.println("1");
						out.println(info);
					}
					// 直接响应示例
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

		// run用来监听服务器送来的信息
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
