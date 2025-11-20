package com.example.itk;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ElementsCounterTest {

    @Test
    public void test() {
        ElementsCounter counter = new ElementsCounter();
        Element fire = new Element("Fire");
        Element water = new Element("Water");
        Element ground = new Element("Ground");
        Element air = new Element("Air");

        Element[] elements = new Element[]{fire, water, fire, ground, air, air, fire, water};

        Map<Element, Integer> result = counter.countElements(elements);
        Map<Element, Integer> expected = new HashMap<>();
        expected.put(fire, 3);
        expected.put(water, 2);
        expected.put(ground, 1);
        expected.put(air, 2);

        assertEquals(expected, result);
    }

}