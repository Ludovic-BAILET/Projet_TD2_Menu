package edu.polytech.projet_td2_menu.models;

public class Quantity {

    private final int number;
    private final TypesUnits unite;

    public Quantity(int number, TypesUnits unite) {
        this.number = number;
        this.unite = unite;
    }

    public int getNumber() {
        return number;
    }

    public TypesUnits getUnite() {
        return unite;
    }
}
