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

package com.beatsbucket.mashub.kitchen.ingredient.dummy;

import com.beatsbucket.mashub.kitchen.ingredient.AbstractAction;
import com.beatsbucket.mashub.kitchen.ingredient.ReadableAction;
import com.beatsbucket.mashub.kitchen.ingredient.Result;
import com.beatsbucket.mashub.util.ObjectUtil;

public class DummyAction extends AbstractAction implements ReadableAction {

    public DummyAction(String name) {
        this.name = name;
    }

    @Override
    public Result observe(String targetJson) {
        ObjectUtil.checkNotNull(parent, "parent ingred is null");
        ObjectUtil.checkNotNull(targetJson, "json string is null");

        DummyIngred ingred = (DummyIngred) parent;
        Result result = new Result();
        if (ingred.checkLight()) {
            result.setTriggered(true);
            result.setData(String.valueOf(System.currentTimeMillis()));
        } else {
            result.setTriggered(false);
        }
        return result;
    }
}
