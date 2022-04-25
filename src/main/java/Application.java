import forwardserver.TcpForward;
import utils.ApplicationConfig;


import static utils.ApplicationUtils.*;


public class Application {

    public static void main(String[] args){

        log("Initializing...");
        ApplicationConfig applicationConfig = ApplicationConfig.getInstance();

        if(applicationConfig.isInit()){

            log("Application initialization successful!");
            new TcpForward().begin();

        }else log("Application initialization failed!");

    }
}
