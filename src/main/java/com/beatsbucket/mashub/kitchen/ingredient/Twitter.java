package com.beatsbucket.mashub.kitchen.ingredient;

import com.beatsbucket.mashub.kitchen.Fridge;
import com.beatsbucket.mashub.kitchen.ingredient.category.AbstractSocialIngred;

public class Twitter extends AbstractSocialIngred {
	@Override
	public void advertise(Fridge fridge) {

	}
	
	/*public String getTimeline(int count) throws UnsupportedEncodingException {
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
		
		String responseMsg = client.target("https://api.twitter.com/1.1/statuses/user_timeline.json?count=" + count).request()
				.property(OAuth1ClientSupport.OAUTH_PROPERTY_ACCESS_TOKEN, storedToken).get(String.class);
		

		return responseMsg;
	}*/
	

}
