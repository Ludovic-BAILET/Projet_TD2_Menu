package edu.polytech.projet_td2_menu.factory;

import edu.polytech.projet_td2_menu.models.TypesDishes;

public class ConcreteRecipeFactoryEntree extends ConcreteRecipeFactory {

    public ConcreteRecipeFactoryEntree() {
        super(TypesDishes.ENTREE);
    }

}
