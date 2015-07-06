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

package com.beatsbucket.mashub.kitchen;

import com.beatsbucket.mashub.channel.OAuth1Channel;
import com.beatsbucket.mashub.channel.OAuth1Credential;
import com.beatsbucket.mashub.kitchen.ingredient.*;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BasicWorkFlowTest {
    @Test
    public void runWorkFlowTest() throws MalformedURLException {


        ObservationQueue observationQueue = new ObservationQueue();
        CookingQueue cookingQueue = new CookingQueue();
        ExecutorService es = Executors.newScheduledThreadPool(5);

        ObservingChef observingChef = new ObservingChef(observationQueue, cookingQueue, es);
        CookingChef cookingChef = new CookingChef(cookingQueue, es);

        observingChef.start();
        cookingChef.start();

        DummyIngred dummyIngred = new DummyIngred();
        Twitter twitterIngred = new Twitter();

        SimpleRecipe recipe = new SimpleRecipe("dummyRecipe");
        Action dummyAction = new DummyAction("checkLight");
        dummyIngred.addAction(dummyAction);
        recipe.setIf(dummyIngred, dummyAction);

        Action tweetAction = new TweetAction("tweet");
        OAuth1Credential credential = new OAuth1Credential(
                "187389271-LzS4gYsbp19Vya6UrpNNPgMjOHpOoosV0qMzuzaG",
                "JZmVrmK5YJ9nU7z858pyGw4dRBfCE6cHkPz9jL4OR60WZ");

        OAuth1Channel channel = new OAuth1Channel(
                "uEZxNR2Ar0IzeK56CL9cWpvqu",
                "nAFc8PYA4KDQdOQYEANJBL9kmtHFd5F1XHB8jLxlQ19YwarT3z",
                credential);

        URL url = new URL("https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=twitterapi&count=1");
        channel.setTestUrl(url);
        twitterIngred.loadChannel(channel);
        twitterIngred.addAction(tweetAction);
        recipe.setThen(twitterIngred, tweetAction);


        DefaultRecipeBook recipeBook = new DefaultRecipeBook();
        recipeBook.add(recipe);

        DefaultScheduler scheduler = new DefaultScheduler(recipeBook, observationQueue);
        scheduler.start();

        recipe.setState(Recipe.State.SCHEDULED);

        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
