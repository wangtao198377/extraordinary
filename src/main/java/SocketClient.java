import java.awt.datatransfer.StringSelection;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketClient {
  public static void main(String args[]) throws Exception {
    // 要连接的服务端IP地址和端口
    while(true) {
     try {
       Thread.sleep(1000);
     } catch (Exception e) {
       e.printStackTrace();
     }
      new Thread(new MultiThread()).start();
    }
  }

  static class MultiThread implements Runnable {
    @Override
    public void run() {
      try {
        try {
          Thread.sleep(5000);
        } catch (Exception e) {
          e.printStackTrace();
        }
        String host = "127.0.0.1";
        int port = 55533;
        // 与服务端建立连接
        Socket socket = new Socket(host, port);
        // 建立连接后获得输出流
        OutputStream outputStream = socket.getOutputStream();

        String message = "你好  yiwangzhibujian";
        socket.getOutputStream().write(message.getBytes("UTF-8"));

        socket.shutdownOutput();
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int len = -1;
        StringBuilder sb = new StringBuilder();
        while((len = inputStream.read(bytes)) != -1) {
          sb.append(new String(bytes,0,len));
        }
        System.out.println(sb.toString());
        inputStream.close();
        outputStream.close();
        //socket.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}