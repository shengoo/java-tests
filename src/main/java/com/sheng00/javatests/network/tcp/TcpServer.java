package com.sheng00.javatests.network.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class TcpServer implements Runnable {

    static int PORT_NO = 10086;
    private Socket socket;

    public TcpServer(Socket socket) {
        super();
        this.socket = socket;
    }

    public void run() {
        long threadId = Thread.currentThread().getId();
        System.out.println(String.format("Start thread [%s]",threadId));
        // TODO Auto-generated method stub
        try {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            while (true) {
                String strIn = bufferedReader.readLine();
                System.out.println(String.format("[%s]Input : %s",threadId,strIn));
                if ("quit".equalsIgnoreCase(strIn)) {
                    System.out.println(String.format("[%s]Thread will stop.",threadId));
                    break;
                }
                String strOut = String.format("Welcome %s!",strIn);
                System.out.println(String.format("[%s]Output : %s",threadId,strOut));
                dataOutputStream.writeBytes(strOut + System.getProperty("line.separator"));
            }
            bufferedReader.close();
            bufferedReader.close();
            socket.close();
            System.out.println(String.format("End thread [%s]",threadId));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(PORT_NO);
            while (true) {
                Socket s = ss.accept();
                new Thread(new TcpServer(s)).start();
            }
//            ss.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
