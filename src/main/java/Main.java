import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
  public static void main(String[] args){
    // You can use print statements as follows for debugging, they'll be visible when running tests.
    System.out.println("Logs from your program will appear here!");

    //  Uncomment this block to pass the first stage
      ServerSocket serverSocket = null;
      Socket clientSocket = null;
      int port = 6379;
      try {
        serverSocket = new ServerSocket(port);
        serverSocket.setReuseAddress(true);
        // Wait for connection from client.
        clientSocket = serverSocket.accept();
        BufferedReader in  = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
//        writer.write("+PONG\r\n");
//        writer.flush();
        String line;
        while ((line = in.readLine()) != null) {
          if (line.equalsIgnoreCase("ping")) {
            writer.write("+PONG\r\n");
            writer.flush();
          }
        }
        clientSocket.close();
      } catch (IOException e) {
        System.out.println("IOException: " + e.getMessage());
      } finally {
        try {
          if (clientSocket != null) {
            clientSocket.close();
          }
        } catch (IOException e) {
          System.out.println("IOException: " + e.getMessage());
        }
      }
  }
}
