package com.example.itk;

public class Filtrator implements Filter<String> {
    @Override
    public String apply(String o) {
        return o + ": applied.";
    }
}
