package edu.polytech.projet_td2_menu.models;

public class
Ingredient {

    private final int imageInt;
    private final String imageStr;
    private final String name;

    public Ingredient(int image, String name) {
        this.imageInt = image;
        this.name = name;
        this.imageStr = null;
    }

    public Ingredient(String image, String name) {
        this.imageInt = -1;
        this.name = name;
        this.imageStr = image;
    }

    public int getImageInt() {
        return imageInt;
    }

    public String getImageStr() {
        return imageStr;
    }

    public String getName() {
        return name;
    }
}
