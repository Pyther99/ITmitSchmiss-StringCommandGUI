package StringCommandGUI;

import java.awt.*;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.*;

public class AsciiMultithread {

    public static String table(int row, int max) {

        StringBuilder out = new StringBuilder();

        Future[] futures = new Future[max/row + 1];

        ThreadPoolExecutor es = (ThreadPoolExecutor) Executors.newFixedThreadPool(max/row + 1);
        futures[0] = es.submit(() -> AsciiTabelle.produceHeader(row, max));

        for (int a = 0; a < max / row; a++) {
            AsciiRowRunner runner = new AsciiRowRunner(row , max, a);
            futures[a+1] = es.submit(runner::generateCell);
        }

        es.shutdown();
        try {
            es.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (Future future : futures) {
            try {
                out.append(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        return out.toString();
    }

    public static void measure() {
        // Für 5000, 50000: PT0.2329987S
        Instant start = Instant.now();
        String x = table(100000, 1000000);
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end));
    }

    public static void main(String[] args) {
        System.out.println(table(4, 127));
    }
}
