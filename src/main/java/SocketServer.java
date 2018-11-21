import com.sun.corba.se.impl.activation.ProcessMonitorThread;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class SocketServer {
    private static AtomicLong count = new AtomicLong(0);
    private static ExecutorService executorService = Executors.newFixedThreadPool(10);
    public static void main(String[] args) throws Exception {
        // 监听指定的端口
        int port = 55533;
        ServerSocket server = new ServerSocket(port);

        // server将一直等待连接的到来
        System.out.println("server将一直等待连接的到来");

        while (true) {
            Socket socket = server.accept();
            new ProcessThread(socket,count).run();
            //executorService.execute(new ProcessThread(socket,count));
        }
    }

    static class ProcessThread implements Runnable {
        private final Socket socket;
        private final AtomicLong atomicLong;

        public ProcessThread(Socket socket,AtomicLong atomicLong) {
            this.socket = socket;
            this.atomicLong = atomicLong;
        }

        @Override
        public void run() {
            try {

                try {
                    Thread.sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                InputStream inputStream = socket.getInputStream();
                byte[] bytes = new byte[1024];
                int len;
                StringBuilder sb = new StringBuilder();
                while ((len = inputStream.read(bytes)) != -1) {
                    //注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
                    sb.append(new String(bytes, 0, len, "UTF-8"));
                }
                atomicLong.getAndIncrement();
                System.out.println("get message from client: " + sb+"_"+atomicLong.toString());
                OutputStream outputStream = socket.getOutputStream();
                outputStream.write("I receive the message".getBytes());
//                System.out.println(socket.isClosed());
//                inputStream.close();
//                System.out.println(socket.isClosed());
//                outputStream.close();
//                System.out.println(socket.isClosed());
                //socket.close();
                inputStream.close();
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}