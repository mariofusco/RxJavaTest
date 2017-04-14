package mf.testrx.rx;

import io.reactivex.Observable;
import mf.testrx.common.TempInfo;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static java.util.stream.Collectors.toList;

public class TempObservable {

    public static Observable<TempInfo> getTemperature(String town) {
        return Observable.create(subscriber ->
                                         Observable.interval(1, TimeUnit.SECONDS)
                                                   .subscribe(i -> {
                                                       if (!subscriber.isDisposed()) {
                                                           if ( i >= 5 ) {
                                                               subscriber.onComplete();
                                                           } else {
                                                               try {
                                                                   subscriber.onNext( TempInfo.fetch( town ) );
                                                               } catch (Exception e) {
                                                                   subscriber.onError( e );
                                                               }
                                                           }
                                                       }}));
    }

    public static Observable<TempInfo> getCelsiusTemperature(String town) {
        return getTemperature( town ).map( temp -> new TempInfo( temp.getTown(), (temp.getTemp() - 32) * 5 / 9) );
    }

    public static Observable<TempInfo> getNegativeTemperature(String town) {
        return getCelsiusTemperature( town ).filter( temp -> temp.getTemp() < 0 );
    }

    public static Observable<TempInfo> getCelsiusTemperatures(String... towns) {
        return Observable.merge(Arrays.stream(towns)
                                      .map( TempObservable::getCelsiusTemperature )
                                      .collect(toList()));
    }
}