/*
 * Copyright 2005 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package mf.testrx.talk.flow;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

import mf.testrx.common.TempInfo;

public class AsyncTempSubscription implements Subscription {

    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    private final Subscriber<? super TempInfo> subscriber;
    private final String town;

    public AsyncTempSubscription( Subscriber<? super TempInfo> subscriber, String town ) {
        this.subscriber = subscriber;
        this.town = town;
    }

    @Override
    public void request( long n ) {

    }

    @Override
    public void cancel() {

    }
}
