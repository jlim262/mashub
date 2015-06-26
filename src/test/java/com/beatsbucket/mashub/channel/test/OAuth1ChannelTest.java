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

package com.beatsbucket.mashub.channel.test;

import java.net.URL;

import org.junit.Test;

import com.beatsbucket.mashub.channel.OAuth1Channel;
import com.beatsbucket.mashub.channel.OAuth1Credential;

public class OAuth1ChannelTest {
	
	@Test
	public void testTest() throws Exception {
		OAuth1Credential credential = new OAuth1Credential(
				"187389271-LzS4gYsbp19Vya6UrpNNPgMjOHpOoosV0qMzuzaG",
				"JZmVrmK5YJ9nU7z858pyGw4dRBfCE6cHkPz9jL4OR60WZ"); 
		
		OAuth1Channel channel = new OAuth1Channel(
				"uEZxNR2Ar0IzeK56CL9cWpvqu",
				"nAFc8PYA4KDQdOQYEANJBL9kmtHFd5F1XHB8jLxlQ19YwarT3z",
				credential);
		
		URL url = new URL("https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=twitterapi&count=1");  
		channel.setTestUrl(url);
		
		channel.test();
	}

}
