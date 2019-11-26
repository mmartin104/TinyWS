package edu.cscc;

import java.io.*;
import java.net.Socket;

public class RequestHandler {
    private Socket connection;

    public RequestHandler(Socket connection) {
        this.connection = connection;
    }

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

    private String readRequest() throws IOException {
        // Set socket timeout to 500 milliseconds
        connection.setSoTimeout(0);
        int recbufsize = connection.getReceiveBufferSize();
        InputStream input = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        return(reader.readLine());
    }
}
