package network.tcp;

import java.io.*;
import java.net.Socket;


public class TcpClient {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            Socket s = new Socket("127.0.0.1", TcpServer.PORT_NO);
            InputStream ips = s.getInputStream();
            OutputStream ops = s.getOutputStream();


            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));// from console
            DataOutputStream outToServer = new DataOutputStream(ops);
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(ips));
            while (true) {
                String strWord = inFromUser.readLine();
                outToServer.writeBytes(strWord + System.getProperty("line.separator"));
                String responseStr = inFromServer.readLine();
                if (strWord.equalsIgnoreCase("quit")){

                    outToServer.close();
                    inFromServer.close();
                    inFromUser.close();
                    break;
                }
                else
                    System.out.println(responseStr);
            }
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
