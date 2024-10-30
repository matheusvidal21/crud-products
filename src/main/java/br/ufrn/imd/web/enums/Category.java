package br.ufrn.imd.web.enums;

public enum Category {

    COSMETICS("Cosmetics"),
    FOOD("Food"),
    CLEANING("Cleaning"),
    PERSONAL_HYGIENE("Personal Hygiene");

    private String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
