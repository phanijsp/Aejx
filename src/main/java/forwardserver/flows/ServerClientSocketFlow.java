package forwardserver.flows;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

import static utils.ApplicationUtils.log;

public class ServerClientSocketFlow extends SocketDataFlow {

    public ServerClientSocketFlow(Socket clientSocket, Socket serverSocket) {
        super(clientSocket, serverSocket);
    }

    @Override
    public void begin() {

        try{

            InputStream inputStream = serverSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream();

            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead= inputStream.read(buffer))!=-1) {
                outputStream.write(buffer, 0, bytesRead);
                log(this.getClass().getSimpleName()+"\t"+new String(Arrays.copyOf(buffer, bytesRead)));
            }

        }catch (Exception e){
            log(e.getMessage());
        }

    }
}
