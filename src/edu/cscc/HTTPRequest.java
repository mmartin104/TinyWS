package edu.cscc;


public class HTTPRequest {
    private String request;         // request string
    private String path;            // path to file
    private boolean validRequest;   // is request valid?

    public HTTPRequest(String r) {
        request = r;
        if (!parse(r)) {
            TinyWS.log(r);
            TinyWS.fatalError(r);
        } else {
            validRequest = true;
        }
    }

    public boolean isValidRequest() {

        return (validRequest);
    }


    public String getPath() {

        return (path);
    }

    private boolean parse(String r) {
        String[] httpRequest = r.split("[ \t\n?]+");
        if (httpRequest.length >= 2 && httpRequest[0].equals("GET") && httpRequest[1] != null) {
            path = httpRequest[1];
            return true;
        } else {
            return false;
        }
     }





}
