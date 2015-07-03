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
import com.beatsbucket.mashub.kitchen.ingredient.Ingred;
import com.beatsbucket.mashub.kitchen.ingredient.Result;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ObservingChef extends Thread implements Chef<Queue, Command> {
    private ObservationQueue observationQueue;
    private CookingQueue cookingQueue;
    private ExecutorService executorService;
    private boolean stopped;

    public ObservingChef(ObservationQueue observationQueue, CookingQueue cookingQueue, ExecutorService executorService) {
        this.observationQueue = observationQueue;
        this.cookingQueue = cookingQueue;
        this.executorService = executorService;
        stopped = false;
    }

    @Override
    public void run() {
        while (!stopped) {
            try {
                Thread.sleep(10000);
                perform();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isStopped() {
        return stopped;
    }

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }

    @Override
    public Queue setQueue(Queue queue) {
        return observationQueue = (ObservationQueue) queue;
    }

    @Override
    public Queue insertCommand(Command command) {
        return null;
    }

    @Override
    public boolean perform() {
        Command nextCommand = observationQueue.get();
        if (nextCommand != null) {
            Result result = observe((Observation) nextCommand);

            if (true) {
                final Recipe recipe = nextCommand.getRecipe();

                Cooking cooking = new Cooking() {
                    @Override
                    public <V> Future<V> execute() {
                        return null;
                    }

                    @Override
                    public Recipe<Ingred, Action> getRecipe() {
                        return recipe;
                    }

                    @Override
                    public void setRecipe(Recipe<Ingred, Action> recipe) {

                    }

                    @Override
                    public Object call() throws Exception {

                        Ingred ingred = (Ingred) recipe.getThen();
                        Action action = (Action) recipe.getThenAction();

                        recipe.setState(Recipe.State.NOT_STARTED);

                        Result result = ingred.cook(action);
                        result.setTriggered(true);
                        return result;
                    }
                };

                cookingQueue.addLast(cooking);
                return true;
            }
        }


        return false;
    }

    public Result observe(Observation observation) {

        Future<Result> future = executorService.submit(observation);

        Result result = null;
        try {
            result = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void addListener(Listener listener) {

    }
}
