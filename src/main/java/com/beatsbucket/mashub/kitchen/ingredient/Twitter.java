package com.beatsbucket.mashub.kitchen.ingredient;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.Future;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Feature;

import com.beatsbucket.mashub.kitchen.Fridge;
import com.beatsbucket.mashub.kitchen.ingredient.category.AbstractSocialIngred;
import org.glassfish.jersey.client.oauth1.AccessToken;
import org.glassfish.jersey.client.oauth1.ConsumerCredentials;
import org.glassfish.jersey.client.oauth1.OAuth1ClientSupport;

import com.beatsbucket.mashub.channel.Channel;
import com.beatsbucket.mashub.channel.OAuth1Channel;

public class Twitter extends AbstractSocialIngred {
	private OAuth1Channel channel;
	
	public boolean tweet(String msg) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		Client client = ClientBuilder.newClient();
		
		ConsumerCredentials consumerCredentials = new ConsumerCredentials(
                channel.getConsumerKey(), channel.getConsumerSecret());
		AccessToken storedToken = new AccessToken(
				channel.getCredential().getAccessToken(), 
				channel.getCredential().getAccessTokenSecret());
		
		Feature filterFeature = OAuth1ClientSupport.builder(consumerCredentials)
			    .feature()
			    .accessToken(storedToken)
			    .build();
		
		client.register(filterFeature);
		
		String responseMsg = client.target("https://api.twitter.com/1.1/statuses/update.json?status="+URLEncoder.encode(msg,"UTF-8")).request()
				.property(OAuth1ClientSupport.OAUTH_PROPERTY_ACCESS_TOKEN, storedToken).post(null).toString();

		return true;
	}
	
	@Override
	public <C extends Channel> void loadChannel(C channel) {
		// TODO Auto-generated method stub
		this.channel = (OAuth1Channel) channel;
		
	}

	@Override
	public void advertise(Fridge fridge) {

	}

	@Override
	public Future cook(String json) {
		return null;
	}

}
