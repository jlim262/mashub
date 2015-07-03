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

import com.beatsbucket.mashub.auth.User;
import com.beatsbucket.mashub.kitchen.ingredient.Action;
import com.beatsbucket.mashub.kitchen.ingredient.Ingred;
import com.beatsbucket.mashub.kitchen.ingredient.IngredInfo;

import java.util.LinkedList;
import java.util.List;

public class SimpleRecipe implements Recipe<Ingred, Action> {
    private String name;
    private String id;
    private Ingred causeIngred, effectIngred;
    private Action causeAction, effectAction;
    private LinkedList<Filter> filterList;
    private State state;

    //todo should have scheduling trigger

    public SimpleRecipe(String name) {
        this.name = name;
        state = State.NOT_STARTED;
    }

    @Override
    public void setOwner(User owner) {

    }

    @Override
    public void setIf(Ingred ingred, Action action) {
        causeIngred = ingred;
        causeAction = action;
    }

    @Override
    public Ingred getIf() {
        return causeIngred;
    }

    @Override
    public void setThen(Ingred ingred, Action action) {
        effectIngred = ingred;
        effectAction = action;
    }

    @Override
    public Ingred getThen() {
        return effectIngred;
    }

    @Override
    public void setFilter(Filter filter) {

    }

    @Override
    public void setFilters(List<Filter> filters) {

    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public Action getIfAction() {
        return causeAction;
    }

    @Override
    public Action getThenAction() {
        return effectAction;
    }

    public void setState(State state) {
        this.state = state;
    }

}
