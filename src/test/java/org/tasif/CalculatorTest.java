package org.tasif;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatorTest {

    private Calculator calculator;

    @Before
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void testAdd_emptyString_returnsZero() {
        assertEquals(0, calculator.add(""));
    }

    @Test
    public void testAdd_oneNumber_returnsTheNumber() {
        assertEquals(1, calculator.add("1"));
        assertEquals(5, calculator.add("5"));
    }

    @Test
    public void testAdd_twoNumbers_returnsTheirSum() {
        assertEquals(3, calculator.add("1,2"));
        assertEquals(8, calculator.add("3,5"));
    }

    @Test
    public void testAdd_multipleNumbers_returnsTheirSum() {
        assertEquals(15, calculator.add("1,2,3,4,5"));
    }

    @Test
    public void testAdd_withNewLineDelimiter_returnsSum() {
        assertEquals(6, calculator.add("1\n2,3"));
    }

    @Test
    public void testAdd_withCustomDelimiter_returnsSum() {
        assertEquals(3, calculator.add("//;\n1;2"));
    }

    @Test
    public void testAdd_withNegativeNumber_throwsException() {
        try {
            calculator.add("1,-2,3");
            fail("Exception expected.");
        } catch (IllegalArgumentException e) {
            assertEquals("negatives not allowed: -2", e.getMessage());
        }
    }

    @Test
    public void testAdd_withMultipleNegativeNumbers_throwsException() {
        try {
            calculator.add("1,-2,-4,5");
            fail("Exception expected.");
        } catch (IllegalArgumentException e) {
            assertEquals("negatives not allowed: -2, -4", e.getMessage());
        }
    }

    @Test
    public void testAdd_withNumbersBiggerThan1000_areIgnored() {
        assertEquals(2, calculator.add("2,1001"));
        assertEquals(1002, calculator.add("1000,2"));
    }

    @Test
    public void testAdd_withCustomDelimiterOfAnyLength_returnsSum() {
        assertEquals(6, calculator.add("//[***]\n1***2***3"));
    }

    @Test
    public void testAdd_withMultipleCustomDelimiters_returnsSum() {
        assertEquals(6, calculator.add("//[*][%]\n1*2%3"));
    }

    @Test
    public void testAdd_withMultipleCustomDelimitersOfLongerLength_returnsSum() {
        assertEquals(6, calculator.add("//[**][%%]\n1**2%%3"));
    }
}