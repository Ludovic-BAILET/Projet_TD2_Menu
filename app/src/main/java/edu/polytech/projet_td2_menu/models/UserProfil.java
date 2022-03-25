package edu.polytech.projet_td2_menu.models;

public class UserProfil {

    private final int image;
    private final String name;

    public UserProfil(int image, String name) {
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
