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

import com.beatsbucket.mashub.util.CircularList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Simple implementation of hashed timer wheel
 * @see <a href="http://www.cs.columbia.edu/~nahum/w6998/papers/ton97-timing-wheels.pdf">
 *     http://www.cs.columbia.edu/~nahum/w6998/papers/ton97-timing-wheels.pdf</a>
 */
public class HashedTimerWheel implements TimerWheel {
    private final int millisecPerTick;
    private final int wheelSize;
    private CircularList<LinkedList<HashedTimer>> wheel;

    public HashedTimerWheel(int millisecPerTick, int wheelSize) {
        this.millisecPerTick = millisecPerTick;
        this.wheelSize = wheelSize;
        wheel = new CircularList<LinkedList<HashedTimer>>(new ArrayList(wheelSize));
    }

    @Override
    public void startTimer(int interval, int requestId, Scheduler scheduler) {
        int nCycle = interval / wheelSize;
        int position = interval % wheelSize;
        HashedTimer hashedTimer = new HashedTimer(nCycle, requestId, scheduler);

        LinkedList<HashedTimer> hashedTimers;
        if (wheel.get(position) == null) {
            hashedTimers = new LinkedList<HashedTimer>();
        } else {
            hashedTimers = wheel.get(position);
        }
        hashedTimers.add(hashedTimer);
        //todo sorting
    }

    @Override
    public void stopTimer(int requestId) {
        //todo implement!
    }

    //todo add tick thread and check thread.

    private class HashedTimer {
        private int nCycle;
        private final int timerId;
        private final Scheduler scheduler;

        public HashedTimer(int nCycle, int timerId, Scheduler scheduler) {
            this.nCycle = nCycle;
            this.timerId = timerId;
            this.scheduler = scheduler;
        }

        //todo need expire and action

        public int getnCycle() {
            return nCycle;
        }

        public void setnCycle(int nCycle) {
            this.nCycle = nCycle;
        }

        public int getTimerId() {
            return timerId;
        }
    }
}
