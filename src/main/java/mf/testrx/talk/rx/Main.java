package mf.testrx.talk.rx;

public class Main {


    public static void main(String[] args) {


        try {
            Thread.sleep( 10_000L );
        } catch (InterruptedException e) {
            throw new RuntimeException( e );
        }
    }

}
