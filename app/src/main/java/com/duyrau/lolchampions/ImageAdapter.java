package com.duyrau.lolchampions;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
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
    private List<Champion> mObjects;
    private List<Champion> mOriginals;

    public ImageAdapter(Context c, List<Champion> objects) {
        mContext = c;
        mObjects = mOriginals = objects;
    }

    public int getCount() {
        return mObjects.size();
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
//        imgView.setImageResource(mObjects.get(position).getImageId());
        Bitmap bmp = loadBitmapPiece(position);
        imgView.setImageBitmap(bmp);
        txtName.setText(mObjects.get(position).getName());
        return view;
    }


    private Bitmap loadBitmapPiece(int position) {
        int size = convertDpToPixel(48);
        Bitmap champion0 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.champion0);
        Bitmap result = Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565);

        Canvas canvas = new Canvas(result);
        Rect src = new Rect(size * position, 0, size + size * position, size);
        RectF dst = new RectF(0, 0, size, size);

        canvas.drawBitmap(champion0, src, dst, null);
        champion0.recycle();
        return result;
    }

    private int getDeviceDensity() {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);
        return metrics.densityDpi;
    }

    private int convertDpToPixel(float dp) {
        return (int) (dp * (getDeviceDensity() / 160f));
    }

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
                result.values = mOriginals;
                result.count = mOriginals.size();
            }
            else {
                // We perform filtering operation
                List<Champion> res = new ArrayList<Champion>();

                for (Champion champ : mOriginals) {
                    if (champ.getName().toUpperCase().contains(constraint.toString().toUpperCase()))
                        res.add(champ);
                }

                result.values = res;
                result.count = res.size();

            }
            return result;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mObjects = (List<Champion>) results.values;
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    }
}
