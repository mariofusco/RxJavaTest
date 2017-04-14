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

package mf.testrx.example.flow;

import java.util.concurrent.Flow.Publisher;

import mf.testrx.common.TempInfo;


public class Main2 {
    public static void main( String[] args ) {
        getCelsiusTemperatures( "New York" ).subscribe( new TempSubscriber() );
    }

    public static Publisher<TempInfo> getCelsiusTemperatures( String town ) {
        return subscriber -> {
            TempProcessor processor = new TempProcessor();
            processor.subscribe( subscriber );
            processor.onSubscribe( new TempSubscription( processor, town ) );
        };
    }
}
