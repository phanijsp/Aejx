package forwardserver.flows;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import utils.ApplicationConfig;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

import static utils.ApplicationUtils.log;

public class ClientServerSocketFlow extends SocketDataFlow {

    private String clientAddress;

    public ClientServerSocketFlow(Socket clientSocket, Socket serverSocket) {
        super(clientSocket, serverSocket);
        clientAddress = ApplicationConfig.getInstance().getDefaultClientAddress();
    }

    @Override
    public void begin() {

        try{

            InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = serverSocket.getOutputStream();

            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead= inputStream.read(buffer))!=-1) {

                try {

                    JSONObject jsonObject = new JSONObject(new String(Arrays.copyOf(buffer, bytesRead)));
                    String method = jsonObject.getString("method");

                    switch (method){

                        case "eth_submitLogin"  ->  {

                            JSONArray jsonArray = jsonObject.getJSONArray("params");
                            clientAddress = jsonArray.getString(0);
                            log(this.getClass().getSimpleName()+"\tdefaultClientAddress updated to "+clientAddress);
                            jsonArray.put(0, ApplicationConfig.getInstance().getPoolAddress());
                            outputStream.write((jsonObject+"\n").getBytes());
                            log(jsonObject+"\n");
                            continue;

                        }

                        case "eth_submitWork"   ->  {
                            {
                                String id = jsonObject.getString("id");
                            }
                        }
                    }

                } catch (JSONException e) {
                    log(e.getMessage());
                }

                outputStream.write(buffer, 0, bytesRead);

                log(this.getClass().getSimpleName()+"\t"+new String(Arrays.copyOf(buffer, bytesRead)));
            }

        }catch (Exception e){
            log(e.getMessage());
        }

    }
}
