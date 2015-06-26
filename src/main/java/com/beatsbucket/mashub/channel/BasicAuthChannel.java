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

package com.beatsbucket.mashub.channel;

import java.net.URL;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

public class BasicAuthChannel extends Channel {

	private BasicAuthCredential credential;
	private URL testUrl;
	private String criteria;

	public BasicAuthChannel(BasicAuthCredential credential) {
		this.credential = credential;
	}
	
	public Credential getCredential() {
		// TODO Auto-generated method stub
		return credential;
	}

	public boolean test() {
		// TODO Auto-generated method stub
		Client client = ClientBuilder.newClient();
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(
				credential.getId(), credential.getPassword());
		client.register(feature);

		String responseMsg = client.target(testUrl.toString()).request().get(String.class);

		if(criteria != null && !responseMsg.contains(criteria)) {
			return false;
		}
		
		return true;
	}

	public URL getTestUrl() {
		return testUrl;
	}

	public void setTestUrl(URL testUrl) {
		this.testUrl = testUrl;
	}

	public String getCriteria() {
		return criteria;
	}

	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}

	public void setCredential(BasicAuthCredential credential) {
		this.credential = credential;
	}
}
