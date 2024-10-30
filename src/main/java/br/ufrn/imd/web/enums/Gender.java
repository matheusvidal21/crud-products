package br.ufrn.imd.web.enums;

import java.util.Arrays;

public enum Gender {

    FEMALE("Female"),
    MALE("Male");

    private String name;

    Gender(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Gender fromString(String name) {
        return Arrays.asList(Gender.values()).stream()
                .filter(gender -> gender.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid Gender name"));
    }

}
