package edu.cscc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * TinyWS a simplistic Tiny Web Server
 *
 * @author Kate Stifle; Mark Martin
 * @version 1.0
 * @since 2019-11-25
 */
public class TinyWS {

    private static int port;
    private static String defaultFolder;
    private static String defaultPage;

    /**
     * Main method
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        TinyWS tiny = new TinyWS();
        tiny.listen();
    }

    /**
     * Configures port
     *
     * @throws NumberFormatException when invalid port
     */
    public TinyWS() {
        Config config = new Config();
        String portValue = config.getProperty(Config.PORT);
        try {
            port = Integer.parseInt(portValue.trim());
        }
        catch (NumberFormatException nfe)
        {
            System.out.println("NumberFormatException: " + nfe.getMessage());
        }
        defaultFolder = config.getProperty(Config.DEFAULTFOLDER);
        defaultPage = config.getProperty(Config.DEFAULTPAGE);
        config.dumpProperties();
    }

    /**
     * Listens for request from web browser
     *
     */
    public void listen() {
        try {
            ServerSocket socket = new ServerSocket(port);
            socket.setSoTimeout(0);
                while (true) {
                Socket connection = socket.accept();
                TinyWS.log("Request from: " + connection.getInetAddress().getCanonicalHostName());
                RequestHandler requestHandler = new RequestHandler(connection);
                requestHandler.processRequest();
                connection.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Log web server requests
     *
     * @param s - message to log
     */
    public static void log(String s) {
        System.out.println(s);
    }

    /**
     * Handle fatal error - print info and die
     *
     * @param s log message
     */
    public static void fatalError(String s) {
        handleError(s, null, true);
    }

    /**
     * Handle fatal error - print info and die
     *
     * @param e log message
     */
    public static void fatalError(Exception e) {
        handleError(null, e, true);
    }

    /**
     * Handle fatal / non-fatal errors
     *
     * @param s log message
     * @param e info for log message
     * @param isFatal error condition
     */
    public static void handleError(String s, Exception e, boolean isFatal) {
        if (s != null) {
            System.out.println(s);
        }
        if (e != null) {
            e.printStackTrace();
        }
        if (isFatal) System.exit(-1);
    }




    /**
     * Get port configuration value
     * @return port number
     */

    public static int getPort() {
        return port;
    }

    /**
     * Get default HTML folder
     * @return folder
     */
    public static String getDefaultFolder() {
        return defaultFolder;
    }

    /**
     * Get name of default web page
     * @return default page
     */
    public static String getDefaultPage() {
        return defaultPage;
    }
}
