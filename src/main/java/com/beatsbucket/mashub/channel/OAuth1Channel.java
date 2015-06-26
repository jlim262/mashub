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
import javax.ws.rs.core.Feature;

import org.glassfish.jersey.client.oauth1.AccessToken;
import org.glassfish.jersey.client.oauth1.ConsumerCredentials;
import org.glassfish.jersey.client.oauth1.OAuth1ClientSupport;

public class OAuth1Channel extends Channel<Credential> {

	// The consumerKey and consumerSecret are unique id for Mashub in public service.
	// like Twitter Application
	private String consumerKey;		//oauth_consumer_key		
	private String consumerSecret;	//oauth_consumer_secret
	
	private URL testUrl;
	private String criteria;
	
	// User specific credential information.
	private OAuth1Credential credential;

	public OAuth1Channel(String consumerKey, String consumerSecret, OAuth1Credential credential) {
		this.consumerKey = consumerKey;
		this.consumerSecret = consumerSecret;
		this.credential = credential;
	}
	
	public OAuth1Credential getCredential() {
		// TODO Auto-generated method stub
		return credential;
	}

	public boolean test() {
		// TODO Auto-generated method stub
		Client client = ClientBuilder.newClient();
		ConsumerCredentials consumerCredentials = new ConsumerCredentials(consumerKey, consumerSecret);
		AccessToken storedToken = 
				new AccessToken(credential.getAccessToken(), credential.getAccessTokenSecret());
		
		Feature filterFeature = OAuth1ClientSupport.builder(consumerCredentials)
			    .feature()
			    .accessToken(storedToken)
			    .build();
		
		client.register(filterFeature);

		String responseMsg = client.target(testUrl.toString()).request()
				.property(OAuth1ClientSupport.OAUTH_PROPERTY_ACCESS_TOKEN, storedToken).get(String.class);

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

	public void setCredential(OAuth1Credential credential) {
		this.credential = credential;
	}

	public String getConsumerKey() {
		return consumerKey;
	}

	public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
	}

	public String getConsumerSecret() {
		return consumerSecret;
	}

	public void setConsumerSecret(String consumerSecret) {
		this.consumerSecret = consumerSecret;
	}
	
	
}
