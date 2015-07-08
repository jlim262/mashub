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
/**
 * 
 */
package com.beatsbucket.mashub.kitchen.ingredient.twitter;

import com.beatsbucket.mashub.kitchen.ingredient.Ingred;
import com.beatsbucket.mashub.kitchen.ingredient.ReadableAction;
import com.beatsbucket.mashub.kitchen.ingredient.Result;
import com.beatsbucket.mashub.manager.ThingsManager;

public class ThingsAction implements ReadableAction {

	/* (non-Javadoc)
	 * @see com.beatsbucket.mashub.kitchen.ingredient.Action#getName()
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.beatsbucket.mashub.kitchen.ingredient.Action#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.beatsbucket.mashub.kitchen.ingredient.Action#setIngred(com.beatsbucket.mashub.kitchen.ingredient.Ingred)
	 */
	@Override
	public void setIngred(Ingred parent) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.beatsbucket.mashub.kitchen.ingredient.ReadableAction#observe(java.lang.String)
	 */
	@Override
	public Result observe(String targetJson) {
		ThingsManager tm = new ThingsManager();
		//TODO remove dummy injection. and convert to use the target.
		targetJson = "dummyId";
		tm.getStatus(targetJson);
        Result result = new Result();
        result.setData(tm.getStatus(targetJson));
		return result;
	}

}
