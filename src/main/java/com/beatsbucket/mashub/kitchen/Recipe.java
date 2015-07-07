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
import com.beatsbucket.mashub.kitchen.ingredient.FilterAction;

import java.util.List;

public interface Recipe<I, A> {
    //todo recipe should be saved in persistence layer such as database, file and so on. so, need to fix functions.

    void setOwner(User owner);
    void setIf(I ingred, A action);
    I getIf();
    void setThen(I ingred, A action);
    I getThen();
    void addFilter(FilterAction filterAction);
    void addFilters(List<FilterAction> filterActions);
    void setFilters(List<FilterAction> filterActions);
    List<FilterAction> getFilters();
    String getId();

    State getState();
    void setState(State state);
    Action getIfAction();
    Action getThenAction();

    enum State {
        NOT_STARTED,
        SCHEDULED;
    }
}
