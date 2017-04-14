package mf.testrx.example.rx;

public class Main {


    public static void main(String[] args) {
        TempServer.getFeeds( "Milano", "Roma", "Napoli" ).subscribe( System.out::println );

/*
        TempServer.getFeeds("Milano", "Roma", "Napoli").subscribe(new Observer<TempInfo>() {
            @Override
            public void onComplete() {
                System.out.println("Done!");
            }

            @Override
            public void onSubscribe( Disposable disposable ) {
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(e);
            }

            @Override
            public void onNext(TempInfo tempInfo) {
                System.out.println(tempInfo);
            }
        });
*/
        try {
            Thread.sleep( 10_000L );
        } catch (InterruptedException e) {
            throw new RuntimeException( e );
        }
    }

}
