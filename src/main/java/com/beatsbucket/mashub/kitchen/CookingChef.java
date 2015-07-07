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

import com.beatsbucket.mashub.kitchen.ingredient.Result;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class CookingChef extends Thread implements Chef<CookingQueue, Command> {
    private CookingQueue cookingQueue;
    private ExecutorService executorService;
    private boolean stopped;

    public CookingChef(CookingQueue cookingQueue, ExecutorService executorService) {
        this.cookingQueue = cookingQueue;
        this.executorService = executorService;
        stopped = false;
    }

    @Override
    public void run() {
        while (!stopped) {
            try {
                Thread.sleep(3000);
                perform();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public CookingQueue setQueue(CookingQueue queue) {
        return null;
    }

    @Override
    public CookingQueue insertCommand(Command command) {
        return null;
    }

    @Override
    public boolean perform() {
        Cooking cooking = (Cooking) cookingQueue.get();

        if (cooking != null) {
            Future<Result> future = executorService.submit(cooking);

            try {
                Result result = future.get();
                if (result.isTriggered()) {
                    System.out.println("CookingChef.perform " + "tweeted.");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }


        }
        return false;
    }

    @Override
    public void addListener(Listener listener) {

    }
}
