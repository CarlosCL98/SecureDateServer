package edu.eci.DateServer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static spark.Spark.*;

public class DateServerApp {

    public static void main(String[] args) {
        port(getPort());
        secure("deploy/KeyStoreDateServer.jks", "jQYXDate5App", null, null);
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
}
