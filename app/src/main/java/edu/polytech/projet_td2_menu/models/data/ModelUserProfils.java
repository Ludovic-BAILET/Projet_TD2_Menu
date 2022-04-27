package edu.polytech.projet_td2_menu.models.data;

import java.util.ArrayList;
import java.util.List;

import edu.polytech.projet_td2_menu.R;
import edu.polytech.projet_td2_menu.models.UserProfil;

public class ModelUserProfils {
    private final static List<UserProfil> userList = new ArrayList<>();

    static {
        userList.add(new UserProfil(R.drawable.user, "Frederic"));
    }

    public static UserProfil get(int index) {
        return userList.get(index);
    }

    public static int size() {
        return userList.size();
    }
}
