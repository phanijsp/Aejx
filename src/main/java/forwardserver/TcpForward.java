package forwardserver;


import utils.ApplicationConfig;

import java.net.ServerSocket;
import java.net.Socket;

import static utils.ApplicationUtils.log;


public class TcpForward {

    public void begin(){
        ServerSocket serverSocket;
        try {
            log("Initializing server...");
            serverSocket = new ServerSocket(ApplicationConfig.getInstance().getListeningPort());
            log("Server started listening for connections on port "+serverSocket.getLocalPort());
            while(true){
                try{

                    Socket clientSocket = serverSocket.accept();
                    log("New connection from client\t"+clientSocket.getInetAddress().getHostAddress());
                    ClientSocketHandler clientSocketHandler = new ClientSocketHandler(clientSocket);
                    clientSocketHandler.start();

                }catch (Exception e){
                    log("Error occurred while listening for connections...");
                    log(e.getMessage());
                }
            }
        } catch (Exception e) {
            log("Unable to initialize server on listeningPort:\t"+ApplicationConfig.getInstance().getListeningPort());
            log(e.getMessage());
        }

    }

}
