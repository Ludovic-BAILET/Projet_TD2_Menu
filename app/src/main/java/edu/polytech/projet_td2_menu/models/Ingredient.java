package edu.polytech.projet_td2_menu.models;

public class Ingredient {

    private final int image;
    private final String name;

    public Ingredient(int image, String name) {
        this.image = image;
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
}
