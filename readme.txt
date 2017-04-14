1.
    public static Observable<TempInfo> getTemp(String town) {
        return Observable.just(TempInfo.fetch(town));
    }

---------------------------

2.
    public static Observable<TempInfo> getTemps(String... towns) {
        return Observable.from(Stream.of(towns).map(town -> TempInfo.fetch(town)).collect(toList()));
    }

---------------------------

3.

    public static Observable<TempInfo> getTemp(String town) {
        return Observable.create(subscriber -> subscriber.onNext(TempInfo.fetch(town)));
    }

---------------------------

4.
    public static Observable<TempInfo> getFeed(String town) {
        return Observable.create(subscriber -> {
            while (true) {
                subscriber.onNext(TempInfo.fetch(town));
                sleep(1000);
            }
        });
    }

---------------------------

5.
    public static Observable<TempInfo> getFeed(String town) {
        return Observable.create(subscriber ->
                                         Observable.interval(1, TimeUnit.SECONDS)
                                                   .subscribe(i -> subscriber.onNext(TempInfo.fetch(town))));
    }

--------------------------

6.

    public static Observable<TempInfo> getFeeds(String... towns) {
        return Observable.merge(Arrays.stream(towns)
                                      .map(TempServer::getFeed)
                                      .collect(toList()));
    }

-------------------------

7.
    public static Observable<TempInfo> getFeed(String town) {
        return Observable.create(subscriber ->
                                         Observable.interval(1, TimeUnit.SECONDS)
                                                   .subscribe(i -> {
                                                       if (i > 5) subscriber.onCompleted();
                                                       try {
                                                           subscriber.onNext(TempInfo.fetch(town));
                                                       } catch (Exception e) {
                                                           subscriber.onError(e);
                                                       }
                                                   }));
    }
