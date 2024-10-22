package esercise3_1.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

// 用于处理每个客户端的线程类
class ClientHandler extends Thread {
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }
    // 联系人列表，用于存储所有联系人信息
    private static List<Contact> contacts = new ArrayList<>();

    public void run() {
        try {
            // 读取客户端请求的输入流
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // 向客户端输出的输出流
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            String request;
            // 不断接收来自客户端的请求
            while ((request = in.readLine()) != null) {
                // 分解请求的命令
                String[] parts = request.split(" ");
                String command = parts[0];

                // 根据命令执行相应操作
                switch (command) {
                    case "ADD":
                        addContact(parts[1], parts[2], parts[3]);
                        break;
                    case "DELETE":
                        deleteContact(parts[1]);
                        break;
                    case "VIEW":
                        viewContacts();
                        break;
                    default:
                        out.println("无效命令");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭客户端连接
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 添加联系人
    private void addContact (String name, String address, String phone){
        contacts.add(new Contact(name, address, phone));
        out.println("联系人添加成功！");
    }

    // 删除联系人
    private void deleteContact (String name){
        contacts.removeIf(contact -> contact.getName().equals(name));
        out.println("联系人删除成功！");
    }

    // 查看所有联系人
    private void viewContacts () {
        if (contacts.isEmpty()) {
            out.println("联系人列表为空！");
        } else {
            for (Contact contact : contacts) {
                out.println(contact.toString());
            }
        }
    }
}