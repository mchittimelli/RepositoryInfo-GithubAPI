package com.example.finalprojectmp1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapt extends BaseAdapter {

    Context c;
    ArrayList<Repository> rep;
    LayoutInflater inflater;

    public ListAdapt(Context c, ArrayList<Repository> rep) {
        this.c = c;
        this.rep = rep;
    }

    @Override
    public int getCount() {
        return rep.size();
    }

    @Override
    public Object getItem(int position) {
        return rep.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
        {
            inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }


        if(convertView == null)
        {
            convertView = inflater.inflate(R.layout.repositorylist,parent,false);
        }
        TextView txtnm = convertView.findViewById(R.id.txt_repository);
        txtnm.setText(rep.get(position).getName());

        return convertView;
    }
}
