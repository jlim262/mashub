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

package com.beatsbucket.mashub.kitchen.ingredient.twitter;

import com.beatsbucket.mashub.channel.OAuth1Channel;
import com.beatsbucket.mashub.kitchen.ingredient.AbstractAction;
import com.beatsbucket.mashub.kitchen.ingredient.Ingred;
import com.beatsbucket.mashub.kitchen.ingredient.ReadableAction;
import com.beatsbucket.mashub.kitchen.ingredient.Result;

import com.beatsbucket.mashub.util.ObjectUtil;
import org.glassfish.jersey.client.oauth1.AccessToken;
import org.glassfish.jersey.client.oauth1.ConsumerCredentials;
import org.glassfish.jersey.client.oauth1.OAuth1ClientSupport;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Feature;

public class TimelineAction extends AbstractAction implements ReadableAction {
    private OAuth1Channel channel;
    private String name;
    private Ingred parent;

    public TimelineAction(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setIngred(Ingred parent) {
        this.parent = parent;
        this.channel = (OAuth1Channel) parent.getChannel();
    }

    @Override
    public Result observe(String targetJson) {
        ObjectUtil.checkNotNull(targetJson, "message is null");

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

        int count = Integer.valueOf(targetJson);
        String responseMsg = client.target("https://api.twitter.com/1.1/statuses/user_timeline.json?count=" + count).request()
                .property(OAuth1ClientSupport.OAUTH_PROPERTY_ACCESS_TOKEN, storedToken).get(String.class);

        Result result = new Result();
        result.setData(responseMsg);

        return result;
    }
}
