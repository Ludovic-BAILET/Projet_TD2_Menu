package edu.polytech.projet_td2_menu.factory;

import edu.polytech.projet_td2_menu.models.TypesDishes;

public class ConcreteRecipeFactoryPlat extends ConcreteRecipeFactory {
    public ConcreteRecipeFactoryPlat() {
        super(TypesDishes.PLAT);
    }
}
