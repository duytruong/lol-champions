package com.duyrau.lolchampions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by duyrau on 29/11/2015.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;

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
        ImageView imageView;
//        View itemView;
        if (convertView == null) {
//            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            itemView = inflater.inflate(R.layout.grid_item_champion, parent, false);
//            itemView = inflater.inflate(R.layout.grid_item_champion, null);
//            ImageView imgView = (ImageView)itemView.findViewById(R.id.image_champion_avatar);
//            TextView txtName = (TextView)itemView.findViewById(R.id.txt_champion_name);
//            imgView.setImageResource(mThumbIds[position]);
//            txtName.setText("Name here");


            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(120, 120));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
//            itemView = convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.aatrox, R.drawable.ahri,
            R.drawable.blitzcrank, R.drawable.brand,
            R.drawable.cassiopeia, R.drawable.renekton,
            R.drawable.rengar, R.drawable.shyvana
    };
}
