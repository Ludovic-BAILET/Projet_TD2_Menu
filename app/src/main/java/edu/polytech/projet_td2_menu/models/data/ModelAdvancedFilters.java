package edu.polytech.projet_td2_menu.models.data;

import java.util.ArrayList;
import java.util.List;

import edu.polytech.projet_td2_menu.R;
import edu.polytech.projet_td2_menu.models.AdvancedFilter;

public class ModelAdvancedFilters {
    private final static List<AdvancedFilter> filtersList = new ArrayList<>();

    static {
        filtersList.add(new AdvancedFilter(R.drawable.sack_dollar, "Economique"));
        filtersList.add(new AdvancedFilter(R.drawable.briefcase_medical, "Sain"));
        filtersList.add(new AdvancedFilter(R.drawable.dumbbell, "Difficulté"));
        filtersList.add(new AdvancedFilter(R.drawable.heart_full, "Favoris"));
        filtersList.add(new AdvancedFilter(R.drawable.house, "Mes Créations"));
    }

    public static AdvancedFilter get(int index) {
        return filtersList.get(index);
    }

    public static int size() {
        return filtersList.size();
    }
}
