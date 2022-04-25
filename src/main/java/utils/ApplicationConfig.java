package utils;

import java.util.Properties;

import static utils.ApplicationUtils.*;
import static utils.ApplicationUtils.log;

public class ApplicationConfig {

    private Integer listeningPort;
    private String forwardIp;
    private Integer forwardPort;
    private String poolAddress;
    private String defaultClientAddress;
    private boolean init = true;

    public boolean isInit() {
        return init;
    }

    private static ApplicationConfig applicationConfig;

    private ApplicationConfig(){
        Properties props = new Properties();

        try{
            props.load(ClassLoader.getSystemResourceAsStream("application.properties"));
            log("Configured properties...");
            props.keySet()
                    .stream()
                    .map(o -> "\t"+o +":\t"+ props.getProperty(o.toString()))
                    .forEach(ApplicationUtils::log);
        }catch (Exception e) {
            log("Failed to load application.properties "+e);
            init = false;
            return;
        }

        logEnabled = Boolean.parseBoolean(props.getProperty("logEnabled"));

        listeningPort = getInteger(props.getProperty("listeningPort"));
        if(listeningPort==null) {
            log("listeningPort has to be an Integer");
            init = false;
        }

        forwardIp = props.getProperty("forwardIp");
        if(forwardIp==null || forwardIp.length()==0) {
            log("forwardIp requires value");
            init =false;
        }

        forwardPort = getInteger(props.getProperty("forwardPort"));
        if(forwardPort==null) {
            log("forwardPort has to be an Integer");
            init = false;
        }

        poolAddress = props.getProperty("poolAddress");
        if(poolAddress==null || poolAddress.length()==0) {
            log("poolAddress requires value");
            init = false;
        }

        defaultClientAddress = props.getProperty("defaultClientAddress");
        if(defaultClientAddress==null || defaultClientAddress.length()==0){
            log("defaultClientAddress requires value");
            init = false;
        }
    }

    public String getDefaultClientAddress() {
        return defaultClientAddress;
    }

    public static ApplicationConfig getInstance(){
        if(applicationConfig==null) applicationConfig = new ApplicationConfig();
        return applicationConfig;
    }

    public Integer getListeningPort() {
        return listeningPort;
    }

    public String getForwardIp() {
        return forwardIp;
    }

    public Integer getForwardPort() {
        return forwardPort;
    }

    public String getPoolAddress() {
        return poolAddress;
    }


}
