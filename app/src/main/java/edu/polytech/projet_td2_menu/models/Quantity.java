package edu.polytech.projet_td2_menu.models;

public class Quantity {

    private final int number;
    private final String unite;

    public Quantity(int number, String unite) {
        this.number = number;
        this.unite = unite;
    }

    public int getNumber() {
        return number;
    }

    public String getUnite() {
        return unite;
    }
}
