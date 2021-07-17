package net.clarenceho.test.castingunnecessary;

import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UnnecessaryCastingApplication implements CommandLineRunner {
    final int LOOP_COUNT = 1000000;

    public static void main(String[] args) {
        // pass in command-line agruments, e.g.: ./gradlew bootRun --args="1"
        SpringApplication.run(UnnecessaryCastingApplication.class, args);
	}

    private void methodWithDoubleAsParam(double value) {
    }

    @Override
    public void run(String... args) throws Exception {
        // warm-up
        IntStream.range(0, LOOP_COUNT).forEach(i -> methodWithDoubleAsParam((double)Integer.parseInt("1")));

        long startTime;

        startTime = System.nanoTime();
        IntStream.range(0, LOOP_COUNT).forEach(i -> methodWithDoubleAsParam((double)Integer.parseInt(args[0])));
        long elaspedDirect = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        IntStream.range(0, LOOP_COUNT).forEach(i -> methodWithDoubleAsParam((float)Integer.parseInt(args[0])));
        long elaspedExtra = System.nanoTime() - startTime;

        System.out.println(String.format("Extra time spent for each extra casting: %.2fns", (elaspedExtra - elaspedDirect) / (1.0 * LOOP_COUNT)));
    }

}
