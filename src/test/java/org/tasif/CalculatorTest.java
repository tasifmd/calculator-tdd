package org.tasif;

import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatorTest {

    @Test
    public void testAdd_emptyString_returnsZero() {
        Calculator calculator = new Calculator();
        assertEquals(0, calculator.add(""));
    }
}