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
import com.beatsbucket.mashub.kitchen.Fridge;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractIngred implements Ingred {
    protected OAuth1Channel channel;
    protected ArrayList<Action> actions;
    protected IngredInfo ingredInfo;

    public AbstractIngred() {
        actions = new ArrayList<Action>();
    }

    @Override
    public <C extends Channel> void loadChannel(C channel) {
        this.channel = (OAuth1Channel) channel;
    }

    @Override
    public Channel getChannel() {
        return channel;
    }

    @Override
    public void advertise(Fridge fridge) {
        fridge.put(this);
    }

    @Override
    public Result run(WritableAction action, Message message) {
        Result result = action.run(message);
        return result;
    }

    @Override
    public Result observe(ReadableAction action, String targetJson) {
        Result result = action.observe(targetJson);
        return result;
    }

    @Override
    public List<Action> getActions() {
        return actions;
    }

    @Override
    public Action getAction(String name) {
        for (Action action : actions) {
            if (name.equals(action.getName())) {
                return action;
            }
        }
        return null;
    }

    @Override
    public IngredInfo getInfo() {
        return ingredInfo;
    }

    @Override
    public void addAction(Action action) {
        actions.add(action);
        action.setIngred(this);
    }
}