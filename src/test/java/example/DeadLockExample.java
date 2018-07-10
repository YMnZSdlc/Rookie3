package example;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class DeadLockExample {

    @Test
    @Disabled
    void test() throws InterruptedException {
        Object monitor1 = new Object();
        Object monitor2 = new Object();

        Thread thread1 = new Thread(() -> {
            synchronized (monitor1) {
                samePart("Wszedłem w pierwszy poziom - Wątek 1");
                synchronized (monitor2) {
                    System.out.println("Dostałem się - Wątek 1");
                }
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread2 = new Thread(() -> {
            synchronized (monitor2) {
                samePart("Wszedłem w pierwszy poziom - Wątek 2");
                synchronized (monitor1) {
                    System.out.println("Dostałem się - Wątek 2");
                }
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }

    private static void samePart(String s) {
        System.out.println(s);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
