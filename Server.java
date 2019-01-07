# Server

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int portNumber = Integer.parseInt(args[0]);
        try (ServerSocket serverSocket = new ServerSocket(portNumber);
             Socket clientSocket = serverSocket.accept();
             PrintWriter pOut =
                     new PrintWriter(clientSocket.getOutputStream(), true);
             DataOutputStream dOut = new DataOutputStream(clientSocket.getOutputStream());
             DataInputStream dIn = new DataInputStream(clientSocket.getInputStream());
             BufferedReader reader = new BufferedReader(
                     new InputStreamReader(clientSocket.getInputStream()))
        ) {

            String inputLine;
            Protocol protocol = new Protocol();

            while ((inputLine = reader.readLine()) != null) {
                protocol.addOperable(inputLine);
                if (protocol.isReadyForBytesArray()) {
                    int length = Integer.parseInt(reader.readLine());
                    int bytesRead = 0;
                    boolean append = false;
                    int data = 0;
                    while (bytesRead < length) {
                        while (!protocol.checkIfReady()) {
                            byte[] content = new byte[8192];
                            data = dIn.read(content);
                            if (data != -1) {
                                bytesRead += data;
                                if(bytesRead>length){
                                    data -= bytesRead-length;
                                }
                                protocol.addContent(content);
                            }
                        }
                        if (protocol.checkIfReady()) {
                            protocol.act(append, data);
                            append = true;
                            protocol.wipeData();
                        }
                    }
                    protocol = new Protocol();

                }
                if (protocol.checkIfReady()) {
                    byte[] content = protocol.act(false, 0);
                    if (content != null) {
                        pOut.println(content.length);
                        dOut.write(content);
                        dOut.flush();
                    }
                    protocol = new Protocol();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
