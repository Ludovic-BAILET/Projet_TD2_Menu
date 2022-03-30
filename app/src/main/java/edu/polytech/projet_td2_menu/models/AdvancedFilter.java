package edu.polytech.projet_td2_menu.models;

public class AdvancedFilter {

    private final int image;
    private final String description;

    public AdvancedFilter(int image, String description) {
        this.image = image;
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }
}
