package com.example.itk;

import java.util.HashMap;
import java.util.Map;

public class ElementsCounter {
    public Map<Element, Integer> countElements(Element[] elements) {
        Map<Element, Integer> result = new HashMap<>();
        for (Element element : elements) {
            if (result.containsKey(element)) {
                result.computeIfPresent(element, (key, value) -> ++value);
            } else {
                result.put(element, 1);
            }
        }

        return result;
    }
}
