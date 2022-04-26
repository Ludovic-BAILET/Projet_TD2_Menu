package edu.polytech.projet_td2_menu.models;

public class Quantity {

    private final double number;
    private final String unite;

    public Quantity(double number, String unite) {
        this.number = number;
        this.unite = unite;
    }

    public double getNumber() {
        return number;
    }

    public String getUnite() {
        return unite;
    }
}
