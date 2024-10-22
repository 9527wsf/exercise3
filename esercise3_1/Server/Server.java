package esercise3_1.Server;
import java.io.*;
import java.net.*;
import java.util.*;

public class Server {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("服务器已启动...");

            // 不断监听客户端连接
            while (true) {
                Socket clientSocket = serverSocket.accept();
                // 每当有客户端连接时，启动一个新的线程处理请求
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
