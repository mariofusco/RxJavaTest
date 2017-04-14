package mf.testrx.example.rx;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import io.reactivex.Observable;
import mf.testrx.common.TempInfo;

import static java.util.stream.Collectors.toList;

public class TempServer {

    public static Observable<TempInfo> getFeed(String town) {
        return Observable.create(subscriber -> {
            Observable.interval(1L, TimeUnit.SECONDS).subscribe(i -> {
                if (i > 3) subscriber.onComplete();
                try {
                    subscriber.onNext(TempInfo.fetch(town));
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            });
        });
    }

    public static Observable<TempInfo> getFeeds(String... towns) {
        return Observable.merge(Stream.of(towns).map( TempServer::getFeed ).collect( toList() ) );
    }
}
