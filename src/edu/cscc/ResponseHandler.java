package edu.cscc;

import java.io.*;
import java.net.*;
import java.nio.file.Files;

public class ResponseHandler {
    private static final String NOT_FOUND_RESPONSE =
            "HTTP/1.0 404 NotFound\n" +
                    "Server: TinyWS\n" +
                    "Content-type: text/plain\n\n" +
                    "Requested file not found.";

    private static final String FORBIDDEN_RESPONSE =
            "HTTP/1.0 403 Forbidden\n" +
                    "Server: TinyWS\n" +
                    "Content-type: text/plain\n\n" +
                    "Requested action is forbidden.  So there!";

    private static final String HTTP_OK_HEADER =
            "HTTP/1.0 200 OK\n" +
                    "Server: TinyWS\n" +
                    "Content-type: ";

    private HTTPRequest request;
    private int responseCode;

    public ResponseHandler(HTTPRequest request) {
        this.request = request;
        responseCode = 404;
    }

    /**
     * Send HTTP Response
     */
    public void sendResponse(Socket connection) throws IOException {
        byte[] response = null;
        int sendBufferSize = connection.getSendBufferSize();
        BufferedOutputStream out = new BufferedOutputStream(
                connection.getOutputStream(), sendBufferSize);
       if (request.isValidRequest() == true) {
           out.write(getFile(request.getPath()));
           out.flush();
           System.out.println("Close connection");
           out.close();
       }
}

    // Find requested file, assume Document Root is in html folder in project directory
    private byte[] getFile(String path) throws IOException {
        if (path==null) {
            responseCode = 404;
            return null;
       }

       if (path.contains("..")) {
           responseCode = 403;
           return null;
       }

       if (path.startsWith("/")) {
           path = "./html" + path;
       } else {
           path = "./html/" + path;
       }

       File file = new File(path);
       if (file.isDirectory()) {
           path = path + TinyWS.getDefaultPage();
           file = new File(path);
       }

        if (!file.isFile() || !file.canRead()) {

           responseCode = 403;
           return null;
       }

       byte[] byteArray = readFile(file);
       if (byteArray == null) {
           responseCode = 404;
           return null;
       } else {
           byte[] header = HTTP_OK_HEADER.getBytes();
           byte[] mimeType = getMimeType(path).getBytes();
           byte[] newLines = "\n\n".getBytes();
           byte[] fileData = readFile(file);
           byte[] finalArray = new byte[header.length + mimeType.length + newLines.length + fileData.length +1];
           System.arraycopy(header, 0, finalArray, 0, header.length);
           System.arraycopy(mimeType, 0, finalArray, header.length, mimeType.length);
           System.arraycopy(newLines, 0, finalArray, header.length+mimeType.length, newLines.length);
           System.arraycopy(fileData, 0, finalArray, header.length + mimeType.length + newLines.length, fileData.length);
           finalArray = fileData;
           responseCode = 200;

           System.out.println("\tGot file: " + path + " (" + file.length() + " bytes)");
           return finalArray;
       }
    }

    // Read file, return byte array (null if error)
    private byte[] readFile(File f) throws IOException{
        FileInputStream fileIn = null;
        int fileLength = Math.toIntExact(f.length());
        byte[] fileContent = new byte[fileLength];
        try {
            fileIn = new FileInputStream(f);
            fileIn.read(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileIn != null)
                fileIn.close();
        }
        return(fileContent);
    }

    // Return mimetype based on file suffix (or null if error)
    private String getMimeType(String path) {
        String mimeType = null;
        if (path.endsWith(".html") || path.endsWith(".htm")) {
            mimeType = "text/html";
        } else if (path.endsWith(".txt")) {
            mimeType = "text/plain";
        } else if (path.endsWith(".gif")) {
            mimeType = "image/gif";
        } else if (path.endsWith(".jpg") || path.endsWith(".jpeg")) {
            mimeType = "image/jpeg";
        }
        return mimeType;
    }
}
