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
import com.beatsbucket.mashub.kitchen.ingredient.Ingred;
import com.beatsbucket.mashub.kitchen.ingredient.IngredType;

import java.util.List;

public interface RecipeBook<R extends Recipe> {
    List<R> search(User user);
    List<R> search(User user, Ingred ingred);
    List<R> search(User user, Ingred ingred, IngredType ingredType);
    List<R> search(User user, Ingred ifType, Ingred thenType);
    void add(R recipe);
    void add(List<R> recipes);
    void delete(R recipe);
    void delete(List<R> recipes);
    void update(R recipe);
    void update(List<R> recipes);
    List<R> getRecipes();
}