package com.playback;

import java.util.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MyResourceTest {

    private HttpServer server;
    private WebTarget target;
    private DB db;
    private Log log;
    private Validation validation;

    @Before
    public void setUp() throws Exception {
        // start the server
        server = Main.startServer();
        // create the client
        Client c = ClientBuilder.newClient();

        // uncomment the following line if you want to enable
        // support for JSON in the client (you also have to uncomment
        // dependency on jersey-media-json module in pom.xml and Main.startServer())
        // --
        // c.configuration().enable(new org.glassfish.jersey.media.json.JsonJaxbFeature());

        target = c.target(Main.BASE_URI);

        db = DB.getInstance();
        log = Log.getInstance();

        validation = new Validation();

    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testGetIt() {
        String responseMsg = target.path("myresource").request().get(String.class);
        assertEquals("Got it!", responseMsg);
    }

    /**
     * Test to see that the use who meet the criteria is able to playback.
     */
    @Test
    public void testValidCase() {
        Validation validation = new Validation();
        boolean isValid = validation.checkValidation(2, "tv", "walkingdead", db, "", log);
        assertEquals(isValid, true);
    }

    /**
     * Test to see that if the user is inactive, then he can not play back.
     */
    @Test
    public void testInactiveUser() {
        boolean isValid = validation.checkValidation(4, "tv", "starwars", db, "", log);
        assertEquals(isValid, false);
    }

    /**
     * Test to see that user has went over his stream limit.
     */
    @Test
    public void testOverStreamLimit() {
        boolean isValid = validation.checkValidation(2, "tv", "residentevil", db, "", log);
        assertEquals(isValid, false);
    }

    /**
     * Test to see that the request can exclude a rule set.
     */
    @Test
    public void testExcludeInactiveUserRule() {
        boolean isValid = validation.checkValidation(4, "tv", "starwars", db, "userstatus", log);
        assertEquals(isValid, true);
    }
}
