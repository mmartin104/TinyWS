package edu.cscc;

/**
 * HTTPRequest: This class accepts an HTTP request from a web browser and extracts the relevant path of the file to be returned
 * to the web browser.
 *
 * @author Kate Stifle; Mark Martin
 * @version 1.0
 * @since 2019-11-25
 */
public class HTTPRequest {
    private String request;         // request string
    private String path;            // path to file
    private boolean validRequest;   // is request valid?

    /**
     * This method validates the request
     *
     * @param r requst from web browser
     */
    public HTTPRequest(String r) {
        request = r;
        if (!parse(r)) {
            TinyWS.log(r);
            TinyWS.fatalError(r);
            validRequest = false;
        } else {
            validRequest = true;
        }
    }

    /**
     * This method returns true for validated requests.
     *
     * @return boolean true
     */
    public boolean isValidRequest() {

        return (validRequest);
    }

    /**
     * This method returns the path
     *
     * @return path of the file requested by browser
     */
    public String getPath() {

        return (path);
    }

    /**
     * This method parses the request and verifies format
     * @param r request
     * @return boolean value
     */
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
