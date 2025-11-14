package com.example.itk;

public class Implementation<T> {
    public T[] implement(T[] array, Filter<T> filter) {
        T[] result = array.clone();
        for (int i = 0; i < array.length; i++) {
            result[i] = filter.apply(array[i]);
        }

        return result;
    }
}
