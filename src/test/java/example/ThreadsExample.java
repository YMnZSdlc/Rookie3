package example;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;

public class ThreadsExample {

    //Poczytać o Eager i lazy w kontekście wielowątkowości

    @Test
    void oneByOne() {
        String description = downloadDescription();
        String photos = downloadPhotos();
        BigDecimal price = downloadPrice();
        long additionalInfo = downloadAddInfo();

        String descriptionFinished = transform(descriptionFunction, description);
        String photosFinshed = transform(photosFunction, photos);
        String priceFinished = transform(priceFunction, price);
        String addInfoFinished = transform(addInfoFunction, additionalInfo);
        ProductForTest productForTest = new ProductForTest(descriptionFinished, photosFinshed, priceFinished, addInfoFinished);
        System.out.println(productForTest);
    }

    private class MyRunnable implements Runnable { //to samo co () ->
        @Override
        public void run() {
            transform(descriptionFunction, downloadDescription());
        }
    }

    @Test
    void threads() {
        List<Thread> threads = createThreads();
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    void executors() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        MyRunnable runnableDescription = new MyRunnable();
        Runnable runnablePhotos = () -> transform(photosFunction, downloadPhotos());
        Runnable runnablePrice = () -> transform(priceFunction, downloadPrice());
        Runnable runnableAddInfo = () -> transform(addInfoFunction, downloadAddInfo());

        executorService.execute(runnableDescription);
        executorService.execute(runnablePhotos);
        executorService.execute(runnablePrice);
        executorService.execute(runnableAddInfo);

        executorService.shutdown();
        while (!executorService.isTerminated()) ;
    }

    @Test
    void future() {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Future<String> futureDescription = executorService.submit(() -> downloadDescription());
        Future<String> futurePhotos = executorService.submit(() -> downloadPhotos());
        Future<BigDecimal> futurePrice = executorService.submit(() -> downloadPrice());
        Future<Long> futureAddInfo = executorService.submit(() -> downloadAddInfo());

        executorService.submit(() -> transform(descriptionFunction, futureDescription.get()));
        executorService.submit(() -> transform(photosFunction, futurePhotos.get()));
        executorService.submit(() -> transform(priceFunction, futurePrice.get()));
        executorService.submit(() -> transform(addInfoFunction, futureAddInfo.get()));

        executorService.shutdown();
        while (!executorService.isTerminated()) ;
    }


    //Najważniejszy przykład do stosowania. Wykozystuje rozwinieci biblioteki Future
    @Test
    void completableFuture() {
        CompletableFuture<String> descrCF = CompletableFuture.supplyAsync(() -> downloadDescription());
        CompletableFuture<String> descr2CF = CompletableFuture.supplyAsync(() -> downloadDescription2());
        CompletableFuture<String> photoCF = CompletableFuture.supplyAsync(() -> downloadPhotos());
        CompletableFuture<BigDecimal> priceCF = CompletableFuture.supplyAsync(() -> downloadPrice());
        CompletableFuture<Long> addInfoCF = CompletableFuture.supplyAsync(() -> downloadAddInfo());

        CompletableFuture<String> descrResultCF =
                descrCF.applyToEitherAsync(descr2CF, e -> {
                    System.out.println("Pobrał się " + e);
                    return e;
                }).thenApplyAsync(descriptionFunction);
        CompletableFuture<String> photosResultCF = photoCF.thenApplyAsync(photosFunction);
        CompletableFuture<String> priceResultCF = priceCF.thenApplyAsync(priceFunction);
        CompletableFuture<String> addInfoResultCF = addInfoCF.thenApplyAsync(addInfoFunction);

        descrResultCF.join();
        photosResultCF.join();
        priceResultCF.join();
        addInfoResultCF.join();
    }

    private List<Thread> createThreads() {
        Thread threadDescription = new Thread(new MyRunnable()); //użycie MyRunnable z implementacją interfejsu Runnable
        Thread threadPhotos = new Thread(() -> transform(photosFunction, downloadPhotos())); //krótki zapis z pustym nawiasem, też wywołuje Runnable
        Thread threadPrice = new Thread(() -> transform(priceFunction, downloadPrice()));
        Thread threadAddInfo = new Thread(() -> transform(addInfoFunction, downloadAddInfo()));

        return Lists.newArrayList(threadDescription, threadPhotos, threadPrice, threadAddInfo);
    }


    //Przykład niżej programowania funkcyjnego
    //Wyciąganie reużywanego kodu do funkcji lub metod
    private Function<String, String> descriptionFunction = description -> description + "!";
    private Function<String, String> photosFunction = photos -> photos + "?";
    private Function<BigDecimal, String> priceFunction = price -> price.toEngineeringString();
    private Function<Long, String> addInfoFunction = addInfo -> Long.toString(addInfo);

    private <T> String transform(Function<T, String> function, T value) {
        simulateDelay(4500);
        System.out.println(Thread.currentThread().getName() + " Transformacja ukończona");
        return function.apply(value);
    }

    private String downloadDescription() {
        System.out.println(Thread.currentThread().getName() + " Description downloading...");
        simulateDelay(4000);
        System.out.println(Thread.currentThread().getName() + " Description downloaded!!!");
        return "OPIS";
    }

    private String downloadDescription2() {
        System.out.println(Thread.currentThread().getName() + " Description2 downloading...");
        simulateDelay(RandomUtils.nextInt(2500, 5500));
        System.out.println(Thread.currentThread().getName() + " Description2 downloaded!!!");
        return "OPIS2";
    }

    private String downloadPhotos() {
        System.out.println(Thread.currentThread().getName() + " Photos downloading...");
        simulateDelay(3000);
        System.out.println(Thread.currentThread().getName() + " Photos downloaded!!!");
        return "Zdjęcia";
    }

    private BigDecimal downloadPrice() {
        System.out.println(Thread.currentThread().getName() + " Price downloading...");
        simulateDelay(2500);
        System.out.println(Thread.currentThread().getName() + " Price downloaded!!!");
        return BigDecimal.valueOf(2.3);
    }

    private long downloadAddInfo() {
        System.out.println(Thread.currentThread().getName() + " Add Info downloading...");
        simulateDelay(3500);
        System.out.println(Thread.currentThread().getName() + " Add Info downloaded!!!");
        return 123L;
    }

    private void simulateDelay(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @ToString
    @AllArgsConstructor
    private class ProductForTest {
        private String description;
        private String photos;
        private String price;
        private String additionalInfo;
    }
}
