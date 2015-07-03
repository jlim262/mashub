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

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class CookingQueue implements Queue<Command> {
    private ArrayList<Command> queue;

    public CookingQueue() {
        queue = new ArrayList<Command>();
    }

    @Override
    public void put(Command command) {

    }

    @Override
    public Command get() {
        Command command = null;
        if (queue.size() > 0) {
            command = queue.get(queue.size() - 1);
            queue.remove(queue.size() - 1);
        }
        return command;
    }

    @Override
    public void hide(Command command, int duration, TimeUnit timeUnit) {

    }

    @Override
    public void delete() {

    }

    @Override
    public void addLast(Command command) {
        queue.add(command);
    }

    @Override
    public void addFirst(Command command) {

    }

    @Override
    public void addBefore(Command command, Command target) {

    }

    @Override
    public void addAfter(Command command, Command target) {

    }

    @Override
    public void clear() {

    }
}
