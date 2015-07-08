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

import com.beatsbucket.mashub.kitchen.ingredient.*;
import com.beatsbucket.mashub.kitchen.ingredient.dummy.DummyIngred;

import java.util.List;
import java.util.concurrent.Future;

public class DefaultScheduler extends Thread implements Scheduler {
    private RecipeBook<Recipe<Ingred, Action>> recipeBook;
    private ObservationQueue observationQueue;
    private boolean stopped;

    public DefaultScheduler(RecipeBook<Recipe<Ingred, Action>> recipeBook, ObservationQueue observationQueue) {
        this.recipeBook = recipeBook;
        this.observationQueue = observationQueue;
        stopped = false;
    }

    @Override
    public void run() {
        while (!stopped) {
            try {
                Thread.sleep(1);
                cookScheduledRecipe();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Future cookScheduledRecipe() {
        List<Recipe<Ingred, Action>> recipes = recipeBook.getRecipes();
        for (final Recipe<Ingred, Action> recipe : recipes) {
            if (recipe.getState().equals(Recipe.State.SCHEDULED)) {
                Observation observation = new Observation() {

                    @Override
                    public Object call() throws Exception {
                        //Ingred ingred = IngredFactory.createIngred(recipe.getIf().getInfo().getName());
                        Ingred ingred = recipe.getIf();
                        ReadableAction action = (ReadableAction) recipe.getIfAction();

                        DummyIngred dummyIngred = (DummyIngred) ingred;
                        //todo should be json string
                        String targetJson = "dummy message";
                        Result result = dummyIngred.observe(action, targetJson);

                        if (result.isTriggered()) {
                            System.out.println("light is on");
                        } else {
                            System.out.println("light is still off");
                        }
                        return result;
                    }

                    /*@Override
                    public <V> Future<V> execute() {

                        return null;
                    }*/

                    @Override
                    public Recipe<Ingred, Action> getRecipe() {
                        return recipe;
                    }

                    @Override
                    public void setRecipe(Recipe<Ingred, Action> recipe) {

                    }
                };

                observationQueue.addLast(observation);
                recipe.setState(Recipe.State.NOT_STARTED);
            }
        }
        return null;
    }

    @Override
    public Future cookRecipe(Recipe recipe) {
        return null;
    }
}
