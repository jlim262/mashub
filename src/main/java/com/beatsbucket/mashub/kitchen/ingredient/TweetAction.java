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

package com.beatsbucket.mashub.kitchen.ingredient;

import com.beatsbucket.mashub.channel.Channel;
import com.beatsbucket.mashub.channel.OAuth1Channel;
import org.glassfish.jersey.client.oauth1.AccessToken;
import org.glassfish.jersey.client.oauth1.ConsumerCredentials;
import org.glassfish.jersey.client.oauth1.OAuth1ClientSupport;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Feature;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class TweetAction implements Action {
    private Type type;
    private OAuth1Channel channel;
    private String name;

    public TweetAction(String name) {
        type = Type.WRITABLE;
        this.name = name;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public void setChannel(Channel channel) {
        this.channel = (OAuth1Channel) channel;
    }

    @Override
    public boolean act(Message msg) {
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

        try {
            String responseMsg = client.target("https://api.twitter.com/1.1/statuses/update.json?status="+ URLEncoder.encode("test", "UTF-8")).request()
                    .property(OAuth1ClientSupport.OAUTH_PROPERTY_ACCESS_TOKEN, storedToken).post(null).toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return true;
    }
}
