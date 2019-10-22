package edu.eci.DateServer;

import java.util.Date;

import static spark.Spark.*;

public class DateServerApp {

    public static void main(String[] args) {
        port(getPort());
        secure("deploy/KeyStoreDateServer.jks", "jQYXDate5App", "deploy/truststoreSecureApp.jks", "aACRSecure1App");
        get("/", (req, res) -> "Date Server");
        get("/date", (req, res) -> getDate());
    }

    private static String getDate() {
        java.util.Date now = new Date();
        return now.toString();
    }

    private static int getPort() {
        int port = 4568; //default port if heroku-port isn't set
        if (System.getenv("PORT") != null) {
            port = Integer.parseInt(System.getenv("PORT"));
        }
        return port;
    }

    static {
        //for localhost testing only
        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
                (hostname, sslSession) -> {
                    if (hostname.equals("localhost")) {
                        return true;
                    }
                    return false;
                });
    }
}
