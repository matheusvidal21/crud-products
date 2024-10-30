package br.ufrn.imd.web.enums;

import java.util.Arrays;

public enum Category {

    COSMETICS("Cosmetics"),
    FOOD("Food"),
    CLEANING("Cleaning"),
    PERSONAL_CARE("Personal Care");

    private String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Category fromString(String name){
        return Arrays.asList(Category.values()).stream()
                .filter(category -> category.getName().equalsIgnoreCase(name))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Invalid Category name"));
    }

}
