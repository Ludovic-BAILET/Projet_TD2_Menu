package edu.polytech.projet_td2_menu.factory;

import edu.polytech.projet_td2_menu.models.TypesDishes;

public class ConcreteRecipeFactoryDessert extends ConcreteRecipeFactory {

    public ConcreteRecipeFactoryDessert() {
        super(TypesDishes.DESSERT);
    }

}
