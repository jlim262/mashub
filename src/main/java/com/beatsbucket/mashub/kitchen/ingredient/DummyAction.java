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

import javax.ws.rs.NotSupportedException;

public class DummyAction implements Action {
    private Type type;
    private String name;
    private Ingred parent;

    public DummyAction(String name) {
        type = Type.READABLE;
        this.name = name;
    }

    @Override
    public Type getType() {
        return type;
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
    }

    @Override
    public Result act(Message msg) {
        if (parent != null) {
            Result result = new Result();
            result.setData(String.valueOf(System.currentTimeMillis()));
            DummyIngred ingred = (DummyIngred) parent;
            result.setTriggered(ingred.checkLight());
            return result;
        } else {
            return null;
        }
    }
}
