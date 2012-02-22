package no.niths.android.controllers.domain_views;

import java.util.ArrayList;

import no.niths.android.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class IndexAttibuteAdapter<Field> extends BaseAdapter {

    private ArrayList<java.lang.reflect.Field> list; 
    private LayoutInflater inflater; 
    
    public IndexAttibuteAdapter(Context context,ArrayList<java.lang.reflect.Field> applicationList) {
        this.list = applicationList;
        inflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return list.size();
    }

    public Object getItem(int index) {
        return list.get(index);
    }

    public long getItemId(int index) {
        return index;
    }

    public View getView(int index, View convertView, ViewGroup parent) {
        ViewHolder holder;
        
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.index_list, null);
            holder = new ViewHolder();
            holder.lblName = (TextView) convertView.findViewById(R.id.tevMain);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.lblName.setText(list.get(index).getName());

        return convertView;
    }

    static class ViewHolder {
        TextView lblName;
    }
}