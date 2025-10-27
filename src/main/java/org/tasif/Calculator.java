package org.tasif;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.ArrayList;


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

        return Arrays.stream(parsedNumbers).filter(n -> n <= 1000).sum();
    }

    private Stream<String> getNumbersStream(String numbers) {
        if (numbers.startsWith("//")) {
            String[] parts = numbers.split("\n", 2);
            String header = parts[0].substring(2);
            String numbersPart = parts[1];

            List<String> delimiters = new ArrayList<>();
            Matcher matcher = Pattern.compile("\\[(.*?)\\]").matcher(header);
            while (matcher.find()) {
                delimiters.add(matcher.group(1));
            }

            if (delimiters.isEmpty()) {
                delimiters.add(header);
            }

            String delimiterRegex = delimiters.stream().map(Pattern::quote).collect(Collectors.joining("|"));
            return Arrays.stream(numbersPart.split(delimiterRegex));
        }

        return Arrays.stream(numbers.split(",|\n"));
    }

}