package com.example.itk;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class ImplementationTest {
    @Test
    public void test() {
        Implementation<String> impl = new Implementation<>();
        Filtrator filtrator = new Filtrator();
        String[] array = new String[]{"String1", "String2", "String3", "String4", "String5"};
        var result = impl.implement(array, filtrator);
        var expected = new String[]{"String1: applied.", "String2: applied.", "String3: applied.", "String4: applied.", "String5: applied."};
        assertThat(result, equalTo(expected));
    }
}