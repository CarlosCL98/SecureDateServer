package edu.eci.DateServer;

import java.util.Date;

import static spark.Spark.*;

/**
 * Esta clase representa al servidor de fecha que permite obtener la fecha actual.
 *
 * @author Carlos Medina
 */
public class DateServerApp {

    public static void main(String[] args) {
        port(getPort());
        secure("deploy/keyStoreDateServer.jks",
                "jQYXDate5App",
                "deploy/truststoreSecureApp.jks",
                "aACRSecure1App");
        get("/", (req, res) -> "Date Server");
        get("/date", (req, res) -> getDate());
    }

    /**
     * Permite obtener la fecha actual.
     *
     * @return String con la fecha.
     */
    private static String getDate() {
        java.util.Date now = new Date();
        return now.toString();
    }

    /**
     * Este método toma el puerto del entorno en el que se esté ejecutando para poder realizar el despliegue respectivo.
     *
     * @return int como el puerto a usar.
     */
    private static int getPort() {
        int port = 4568; //default port if heroku-port isn't set
        if (System.getenv("PORT") != null) {
            port = Integer.parseInt(System.getenv("PORT"));
        }
        return port;
    }
}
