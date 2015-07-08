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

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.beatsbucket.mashub.manager.ThingsManager;

@Path("things")
public class Things implements Communicable {
	
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
	 * @param serial
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Path("{serial}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt(@PathParam("serial") String serial) {
        return "Got "+serial+"!";
    }
    
    /**
     * This interface is used to register things.
     * 
     * @param name: System name of the Thing (need not be unique).
     * @param default_display_name: Name of the Thing to be displayed to users. If a default display name is not provided, the system name is displayed.
     * @param proxy: Identification of the Thing client or proxy (many things can share the same proxy).
     * @param uuid: Manufacturer-provided serial number of the Thing.
     * @param manufacturer: Name of the Thing manufacturer.
     * @param model: Thing model.
     * @param protocol_version: Version of the protocol supported by the Thing, e.g. "1.0".
     * @param setup_url: URL with Thing setup instructions.
     * @param support_url: URL that user should be directed to if they are in need of Thing support.
     * @param update_url: URL that user should be directed to if Thing needs a firmware update.
     * @param app_version: Version of the Thing's application.
     * @param local_settings: Current local settings of the Thing (see Local settings).
     * @param semantic_state: Current state of the Thing.
     * @param capabilities: Thing capabilities.
     * @param capsHash: A hash or digest value of the capabilities data. This value is useful, for example, to compare values and check whether the local Thing's capabilities have changed.
     * @param tag: Tags (free-form string values) to add to the Thing. You can attach a set of unique tags to a Thing, which may be useful to store additional metadata about the Thing for later use by your application.
     * @param data: Private data to add to the Thing. Private data values are similar to tags except that they are write-only; they can be added in /register and added or removed in /update, but they are never rendered in responses from the server.
     * @return
     * The JSON response object contains a Boolean success indicator, the XSRF token used, 
     * a listing of the request parameters, and a list of printers consisting of a single entry 
     * which contains the fields of the printer added in the request.
     * 		registration_token: a human readable string the user will need to claim Thing ownership
	 * 		token_duration: the lifetime of the registration_token, in seconds (the whole registration has to finish within this time frame)
	 * 		invite_url: the url that a user will need to visit to claim ownership of the Thing
	 * 		complete_invite_url: same thing of invite_url but already containing the registration_token, so that the user doesn't have to insert it manually
	 * 		polling_url: the url that the Thing will need to poll for the OAuth2 authorization_code
     */
    @POST
    @Path("register")
    @Produces(MediaType.TEXT_PLAIN)
    public String register(@QueryParam("name") String name,
    		@QueryParam("default_display_name") String defaultDisplayName,
    		@QueryParam("proxy") String proxy,
    		@QueryParam("uuid") String uuid,
    		@QueryParam("manufacturer") String manufacturer,
    		@QueryParam("model") String model,
    		@QueryParam("protocol_version") String protocolVersion,
    		@QueryParam("setup_url") String setupUrl,
    		@QueryParam("support_url") String supportUrl,
    		@QueryParam("update_url") String updateUrl,
    		@QueryParam("app_version") String appVersion,
    		@QueryParam("local_settings") String localSettings,
    		@QueryParam("semantic_state") String semanticState,
    		@QueryParam("capabilities") String capabilities,
    		@QueryParam("capsHash") String capsHash,
    		@QueryParam("tag") String tag,
    		@QueryParam("data") String data
    		) {
    	
        return "";
    }
    
    // Obtaining the OAuth2 authorization_code
    public void getAuthCode(){}

	@Override
	public String getPolicy(String json) {
		// TODO Auto-generated method stub
		return null;
	}

    @POST
    @Path("updateStatus")
    @Produces(MediaType.TEXT_PLAIN)
	@Override
	public String updateStatus(String json) {
		ThingsManager t = new ThingsManager();
		// TODO Its Dummy
		t.updateStatus("dummyID", "on");
		return null;
	}

	@Override
	public String getTask(String json) {
		// TODO Auto-generated method stub
		return null;
	}
    
}
