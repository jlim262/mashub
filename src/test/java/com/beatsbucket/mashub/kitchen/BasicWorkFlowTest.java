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

import com.beatsbucket.mashub.kitchen.ingredient.Action;
import com.beatsbucket.mashub.kitchen.ingredient.DummyIngred;
import com.beatsbucket.mashub.kitchen.ingredient.Twitter;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BasicWorkFlowTest {
    @Test
    public void runWorkFlowTest() {


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
        Action dummyAction = new Action() {
            String name;
            @Override
            public String getName() {
                return name;
            }

            @Override
            public void setName(String name) {
                this.name = name;
            }
        };
        dummyAction.setName("checkLight");
        recipe.setIf(dummyIngred, dummyAction);

        Action tweetAction = new Action() {
            String name;
            @Override
            public String getName() {
                return name;
            }

            @Override
            public void setName(String name) {
                this.name = name;
            }
        };
        tweetAction.setName("tweet");

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
