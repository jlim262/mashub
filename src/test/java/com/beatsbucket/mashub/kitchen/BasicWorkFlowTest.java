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
import com.beatsbucket.mashub.kitchen.ingredient.dummy.DummyAction;
import com.beatsbucket.mashub.kitchen.ingredient.dummy.DummyIngred;
import com.beatsbucket.mashub.kitchen.ingredient.filter.DummyFilterAction;
import com.beatsbucket.mashub.kitchen.ingredient.twitter.TweetAction;
import com.beatsbucket.mashub.kitchen.ingredient.twitter.Twitter;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BasicWorkFlowTest {
    @Test
    public void runWorkFlowTest() throws MalformedURLException {
        // creation queues
        ObservationQueue observationQueue = new ObservationQueue();
        CookingQueue cookingQueue = new CookingQueue();

        // executorService for chefs
        ExecutorService es = Executors.newScheduledThreadPool(5);

        // creation chefs and let them work
        ObservingChef observingChef = new ObservingChef(observationQueue, cookingQueue, es);
        CookingChef cookingChef = new CookingChef(cookingQueue, es);
        observingChef.start();
        cookingChef.start();

        // creation ingredients and actions
        DummyIngred dummyIngred = new DummyIngred();
        Action dummyAction = new DummyAction("checkLight");
        dummyIngred.addAction(dummyAction);
        Twitter twitterIngred = new Twitter();
        Action tweetAction = new TweetAction("tweet");

        // creation a filter
        DummyFilterAction filterAction = new DummyFilterAction();

        // creation recipe
        SimpleRecipe recipe = new SimpleRecipe("dummyRecipe");

        // write recipe
        recipe.setIf(dummyIngred, dummyAction);
        recipe.addFilter(filterAction);
        OAuth1Channel channel = getOAuth1Channel();
        twitterIngred.loadChannel(channel);
        twitterIngred.addAction(tweetAction);
        recipe.setThen(twitterIngred, tweetAction);

        // add the recipe to the recipeBook
        DefaultRecipeBook recipeBook = new DefaultRecipeBook();
        recipeBook.add(recipe);

        // starting a scheduler which read the recipebook
        DefaultScheduler scheduler = new DefaultScheduler(recipeBook, observationQueue);
        scheduler.start();
        recipe.setState(Recipe.State.SCHEDULED);

        try {
            // waiting for finishing the basic flow
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private OAuth1Channel getOAuth1Channel() throws MalformedURLException {
        OAuth1Credential credential = new OAuth1Credential(
                "3270752988-Cb1OqGPdgcwqFwi2uwYXg6No3HAr10z2DiL4bSY",
                "gtdRIxhAf73eTX2vfwD2joOVCEiEBBQvCVjQ3Df96AjYb");

        OAuth1Channel channel = new OAuth1Channel(
                "uEZxNR2Ar0IzeK56CL9cWpvqu",
                "nAFc8PYA4KDQdOQYEANJBL9kmtHFd5F1XHB8jLxlQ19YwarT3z",
                credential);

        URL url = new URL("https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=twitterapi&count=1");
        channel.setTestUrl(url);
        return channel;
    }
}
