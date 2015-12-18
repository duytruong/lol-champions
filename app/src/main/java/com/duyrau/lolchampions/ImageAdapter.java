package com.duyrau.lolchampions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends BaseAdapter implements Filterable {
    private Context mContext;
    private Filter mFilter;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_item_champion, parent, false);
        } else {
            view = convertView;
        }
        ImageView imgView = (ImageView) view.findViewById(R.id.image_champion_avatar);
        TextView txtName = (TextView) view.findViewById(R.id.txt_champion_name);
        imgView.setImageResource(mThumbIds[position]);
        txtName.setText(mChampions[position]);
        return view;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.aatrox, R.drawable.ahri,
            R.drawable.blitzcrank, R.drawable.brand,
            R.drawable.cassiopeia, R.drawable.renekton,
            R.drawable.rengar, R.drawable.shyvana
    };

    private String[] mChampions = {
            "Aatrox", "Ahri",
            "Blitzcrank", "Brand",
            "Cassiopeia", "Renekton",
            "Rengar", "Shyvana"
    };


    @Override
    public Filter getFilter() {
        if (mFilter == null) {
            mFilter = new SimpleFilter();
        }
        return mFilter;
    }

    private class SimpleFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults result = new FilterResults();
            if (constraint == null || constraint.length() == 0) {
                // No filter implemented we return all the list
                result.values = mChampions;
                result.count = mChampions.length;
            }
            else {
                // We perform filtering operation
                List<String> res = new ArrayList<String>();

                for (int i = 0; i < mChampions.length; i++) {
                    if (mChampions[i].toUpperCase().startsWith(constraint.toString().toUpperCase()))
                        res.add(mChampions[i]);
                }

                result.values = res;
                result.count = res.size();

            }
            return result;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            if (results.count == 0)
                notifyDataSetInvalidated();
            else {
                planetList = (List<Planet>) results.values;
                notifyDataSetChanged();
            }
        }
    }
}
