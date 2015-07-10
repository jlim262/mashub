/*
 * Copyright 2015 The Mashub Project
 *
 * The Mashub Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.beatsbucket.mashub.webhook;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import junit.framework.TestCase;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ThingsTest {
	private HttpServer server;
    private WebTarget target;
 
    @Before
    public void setUp() throws Exception {
        server = Main.startServer();
 
        Client c = ClientBuilder.newClient();
        target = c.target(Main.BASE_URI);
    }
 
    @After
    public void tearDown() throws Exception {
        server.stop();
    }
 
    @Test
    public void testGetIt() {
        String responseMsg = target.path("things/1").request().get(String.class);
        TestCase.assertTrue(responseMsg.contains("sn"));
    }
    
    @Test
    public void testUpdateStatus() {
    	Response response = target.path("things/1/updateStatus").request().post(Entity.entity("on", MediaType.TEXT_PLAIN));
        String responseMsg = response.readEntity(String.class);
        TestCase.assertTrue(responseMsg.contains("success"));
    }
}
