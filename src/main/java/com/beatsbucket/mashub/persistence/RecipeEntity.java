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
package com.beatsbucket.mashub.persistence;

public class RecipeEntity {
    private Long id;
    private UserEntity owner;
    private String name;
    private IngredEntity causeIngred;
    private IngredEntity effectIngred;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IngredEntity getCauseIngred() {
        return causeIngred;
    }

    public void setCauseIngred(IngredEntity causeIngred) {
        this.causeIngred = causeIngred;
    }

    public IngredEntity getEffectIngred() {
        return effectIngred;
    }

    public void setEffectIngred(IngredEntity effectIngred) {
        this.effectIngred = effectIngred;
    }
}
