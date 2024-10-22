package esercise3_1.Client;
import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("已连接到服务器");

            String userInput;
            // 从控制台读取用户输入的命令并发送到服务器
            while (true) {
                System.out.print("请输入指令 (ADD/DELETE/VIEW/EXIT): ");
                userInput = console.readLine();  // 读取用户输入

                if ("EXIT".equalsIgnoreCase(userInput)) {
                    System.out.println("客户端已断开连接");
                    break;  // 用户输入EXIT则退出循环，结束客户端
                }

                out.println(userInput);  // 发送请求到服务器

                // 接收服务器的响应并打印出来，只读取一行
                String serverResponse = in.readLine();
                if (serverResponse != null) {
                    System.out.println("服务器: " + serverResponse);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
