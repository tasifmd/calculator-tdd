package org.tasif;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Collectors;


public class Calculator {

    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        int[] parsedNumbers = getNumbersStream(numbers).mapToInt(Integer::parseInt).toArray();

        List<Integer> negatives = Arrays.stream(parsedNumbers).filter(n -> n < 0).boxed().collect(Collectors.toList());

        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("negatives not allowed: " + negatives.stream().map(String::valueOf).collect(Collectors.joining(", ")));
        }

        return Arrays.stream(parsedNumbers).sum();
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