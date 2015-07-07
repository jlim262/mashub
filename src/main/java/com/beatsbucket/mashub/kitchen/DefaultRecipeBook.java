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
import com.beatsbucket.mashub.kitchen.ingredient.IngredType;

import java.util.ArrayList;
import java.util.List;

public class DefaultRecipeBook implements RecipeBook<Recipe<Ingred,Action>> {
    private ArrayList<Recipe<Ingred, Action>> recipes;

    public DefaultRecipeBook() {
        recipes = new ArrayList<Recipe<Ingred, Action>>();
    }

    @Override
    public List<Recipe<Ingred, Action>> search(User user) {
        return null;
    }

    @Override
    public List<Recipe<Ingred, Action>> search(User user, Ingred ingred) {
        return null;
    }

    @Override
    public List<Recipe<Ingred, Action>> search(User user, Ingred ingred, IngredType ingredType) {
        return null;
    }

    @Override
    public List<Recipe<Ingred, Action>> search(User user, Ingred ifType, Ingred thenType) {
        return null;
    }

    @Override
    public void add(Recipe<Ingred, Action> recipe) {
        recipes.add(recipe);
    }

    @Override
    public void add(List<Recipe<Ingred, Action>> recipes) {

    }

    @Override
    public void delete(Recipe<Ingred, Action> recipe) {

    }

    @Override
    public void delete(List<Recipe<Ingred, Action>> recipes) {

    }

    @Override
    public void update(Recipe<Ingred, Action> recipe) {

    }

    @Override
    public void update(List<Recipe<Ingred, Action>> recipes) {

    }

    @Override
    public List<Recipe<Ingred, Action>> getRecipes() {
        return recipes;
    }
}
