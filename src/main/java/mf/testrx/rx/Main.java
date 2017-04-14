package mf.testrx.rx;

import io.reactivex.Observable;
import mf.testrx.common.TempInfo;

public class Main {

    public static void main(String[] args) {

        Observable<TempInfo> observable = TempObservable.getNegativeTemperature( "New York" );
        //Observable<TempInfo> observable = TempObservable.getCelsiusTemperatures( "New York", "Chicago", "San Francisco" );

        observable.subscribe( new TempObserver() );

        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
