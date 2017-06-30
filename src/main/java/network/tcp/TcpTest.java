package network.tcp;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Qing
 *
 */
public class TcpTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        TcpServer1 server = new TcpServer1();
        server.start();
        TcpClient1 client = new TcpClient1();
        client.start();
    }
}

class TcpServer1 extends Thread {
    public void run() {
        try {
            ServerSocket listen = new ServerSocket(5050);

            Socket server = listen.accept();

            InputStream in = server.getInputStream();
            OutputStream out = server.getOutputStream();

            int radius;
            while ((radius = in.read()) > 0) {
                System.out.println("server receive:" + radius);

                byte[] b = caculate(radius);
                out.write(b);
                System.out.println("server send:" + new String(b));
            }
            out.close();
            in.close();
            server.close();
            listen.close();
        } catch (Exception e) {

        }
    }

    byte[] caculate(int ra) {
        double result = (ra * ra * Math.PI);
        return Double.toString(result).getBytes();
    }
}

class TcpClient1 extends Thread {
    public void run() {
        try {
            Socket client = new Socket("127.0.0.1", 5050);

            InputStream in = client.getInputStream();
            OutputStream out = client.getOutputStream();
            byte[] b = new byte[20];

            out.write(3);
            System.out.println("client send:" + 3);

            in.read(b);
            System.out.println("client receive:" + new String(b));


            out.write(5);
            System.out.println("client send:" + 5);

            in.read(b);
            System.out.println("client receive:" + new String(b));


            out.close();
            in.close();
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
