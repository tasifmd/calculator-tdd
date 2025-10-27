package org.tasif;

import java.util.Arrays;
import java.util.stream.Stream;

public class Calculator {

    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        return getNumbersStream(numbers).mapToInt(Integer::parseInt).sum();
    }

    private Stream<String> getNumbersStream(String numbers) {
        if (numbers.startsWith("//")) {
            String[] parts = numbers.split("\n", 2);
            String header = parts[0];
            String numbersPart = parts[1];
            String delimiter = header.substring(2);
            return Arrays.stream(numbersPart.split(delimiter));
        }

        String[] nums = numbers.split(",|\n");
        return Arrays.stream(nums);
    }

}