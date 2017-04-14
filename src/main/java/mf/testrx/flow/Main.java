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

package mf.testrx.flow;

import mf.testrx.common.TempInfo;

import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.SubmissionPublisher;

public class Main {
    public static void main( String[] args ) {
        execSync();
        //execAsync();
    }

    private static void execAsync() {
        SubmissionPublisher<TempInfo> publisher = new SubmissionPublisher<>();
        publisher.subscribe( new TempSubscriber() );
        publisher.submit( TempInfo.fetch( "Milano" ) );
        try {
            Thread.sleep( 1000L );
        } catch (InterruptedException e) {
            throw new RuntimeException( e );
        }
        publisher.close();
    }

    private static void execSync() {
        getCelsiusTemperatures( "New York" ).subscribe( new TempSubscriber() );
    }

    public static Publisher<TempInfo> getTemperatures( String town ) {
        return subscriber -> subscriber.onSubscribe( new TempSubscription( subscriber, town ) );
    }

    public static Publisher<TempInfo> getCelsiusTemperatures( String town ) {
        return subscriber -> {
            TempProcessor processor = new TempProcessor();
            processor.subscribe( subscriber );
            processor.onSubscribe( new TempSubscription( processor, town ) );
        };
    }
}
