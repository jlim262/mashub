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
import com.beatsbucket.mashub.kitchen.Fridge;

import java.util.List;
import java.util.concurrent.Future;

public interface Ingred {
    <C extends Channel> void loadChannel(C channel);
    Channel getChannel();
    void advertise(Fridge fridge);
    Result cook(Action action, Message msg);
    Result observe(Action action, Message msg);
    List<Action> getActions();
    Action getAction(String name);
    IngredInfo getInfo();
    void addAction(Action action);
}
