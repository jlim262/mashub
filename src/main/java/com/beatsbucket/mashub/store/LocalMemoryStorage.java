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

package com.beatsbucket.mashub.store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Use local memory as database. It is volatile. 
 *
 */
public class LocalMemoryStorage extends LocalStorage {

	public static HashMap<String,List<String>> cache = new HashMap<String,List<String>>();
	
	public LocalMemoryStorage() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.beatsbucket.mashub.store.Storable#read(java.lang.Object)
	 */
	@Override
	public List<String> read(String k) {
		// TODO Auto-generated method stub
		return cache.get(k);
	}

	/* (non-Javadoc)
	 * @see com.beatsbucket.mashub.store.Storable#write(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void write(String k, String v) {
		// TODO Auto-generated method stub
		if(cache.get(k) == null) {
			ArrayList<String> theList = new ArrayList<String>();
			theList.add(v);
			cache.put(k, theList);
		} else {
			cache.get(k).add(v);
		}
	}	
}
