package edu.cscc;

import org.junit.Assert;
import org.junit.Test;

public class HTTPRequestTest {

    private HTTPRequest requestTest = new HTTPRequest("GET / HTTP/1.1");

    @Test
    public void validRequestMethodWillReturnTrueIfValid() {
        Assert.assertEquals(true, requestTest.isValidRequest());
    }


}
