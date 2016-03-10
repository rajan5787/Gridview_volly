package com.example.rajan.gridview_volly;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Rajan on 2/21/2016.
 */
public class CustomAdapter extends BaseAdapter {
    ArrayList<item> mArrayList;
    Activity mActivity;
    LayoutInflater inflater=null;
    CustomAdapter(Activity mActivity,ArrayList<item> mArrayList){
        this.mArrayList=mArrayList;
        this.mActivity=mActivity;
    }

    @Override
    public int getCount() {
        return mArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(inflater==null){
            inflater=(LayoutInflater)mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView==null){
            convertView=inflater.inflate(R.layout.feed_item, null);
            item mItem=new item();
            mItem=mArrayList.get(position);

            TextView name=(TextView)convertView.findViewById(R.id.name);
            TextView address=(TextView)convertView.findViewById(R.id.address);
            TextView cv=(TextView)convertView.findViewById(R.id.vc);
            ImageView image=(ImageView)convertView.findViewById(R.id.image);

            name.setText(mItem.getName());
            address.setText(mItem.getAddress());
            cv.setText(mItem.getVc());
            Picasso.with(mActivity).load(mItem.getImage()).resize(150,150).into(image);

        }
        return convertView;
    }
}
