package org.projectComponents;

public enum NameOfOperation {
    ENCODE ("encode"),
    DECODE ("decode"),
    BRUTEFORCE ("bruteforce");

    private String name;

    NameOfOperation(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
