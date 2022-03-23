package edu.polytech.projet_td2_menu.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import edu.polytech.projet_td2_menu.R;
import edu.polytech.projet_td2_menu.models.AdvancedFilter;
import edu.polytech.projet_td2_menu.models.data.ModelAdvancedFilter;

public class ViewAdapterFilterElement extends BaseAdapter {

    private final LayoutInflater inflater;

    public ViewAdapterFilterElement(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return ModelAdvancedFilter.size();
    }

    @Override
    public Object getItem(int i) {
        return ModelAdvancedFilter.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LinearLayout layout = (LinearLayout) (view == null ? inflater.inflate(R.layout.view_adapater_filter_element, viewGroup, false) : view);

        AdvancedFilter filter = (AdvancedFilter) getItem(i);
        ((ImageView) layout.findViewById(R.id.image)).setImageResource(filter.getImage());
        ((TextView) layout.findViewById(R.id.description)).setText(filter.getDescription());

        return layout;
    }
}