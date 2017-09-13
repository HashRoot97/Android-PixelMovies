package com.example.surbh_000.pixelmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;
import org.xml.sax.DTDHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by surbh_000 on 7/6/2016.
 */
public class MyDataAdapter extends ArrayAdapter {
    List list=new ArrayList();
    public MyDataAdapter(Context context, int resource) {
        super(context, resource);
    }
    static class DataHandler{
        TextView title;
        TextView vote_average;
    }
    @Override
    public void add(Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object getItem(int position) {
        return this.list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row=convertView;
        DataHandler handler;
        if (convertView == null) {
            LayoutInflater inflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(R.layout.row_layout,parent,false);
            handler=new DataHandler();
            handler.title=(TextView)row.findViewById(R.id.text1);
            handler.vote_average=(TextView)row.findViewById(R.id.text2);
            row.setTag(handler);
        }
        else{
            handler=(DataHandler)row.getTag();
        }
        MyDataProvider dataProvider;
        dataProvider=(MyDataProvider)this.getItem(position);
        handler.title.setText(dataProvider.getTitle());
        handler.vote_average.setText(dataProvider.getvote_average());

        return row;
    }
}

