package edu.cscc;

import java.io.*;
import java.net.Socket;

/**
 * RequestHandler: process request fom browser
 *
 * @author Kate Stifle; Mark Martin
 * @version 1.0
 * @since 2019-11-25
 */
public class RequestHandler {
    /**
     * Create field for connection.
     */
    private Socket connection;

    /**
     * Constructor
     *
     * @param connection instance
     */
    public RequestHandler(Socket connection) {
        this.connection = connection;
    }

    /**
     * This method process the request.
     *
     */
    public void processRequest() {
        try {
            String httpRequest = readRequest();
            if (httpRequest.contains("ico")) {
                TinyWS.log("File not found: " + httpRequest);
            } else {
            HTTPRequest request = new HTTPRequest(httpRequest);
            ResponseHandler responseHandler = new ResponseHandler(request);
            responseHandler.sendResponse(connection);}
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * This method reads the HTTP request from the socket and returns a string containing the request.
     * @return request in String format
     * @throws IOException
     */
    private String readRequest() throws IOException {
        // Set socket timeout to 500 milliseconds
        connection.setSoTimeout(0);
        int recbufsize = connection.getReceiveBufferSize();
        InputStream input = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        return(reader.readLine());
    }
}
