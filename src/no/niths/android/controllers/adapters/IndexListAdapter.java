package no.niths.android.controllers.adapters;

import java.util.ArrayList;

import no.niths.android.R;
import no.niths.android.domains.Domain;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class IndexListAdapter<T extends Domain> extends BaseAdapter {

    private ArrayList<Domain> list; 
    private LayoutInflater inflater; 
    
    public IndexListAdapter(Context context,ArrayList<Domain> applicationList) {
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
            convertView = inflater.inflate(R.layout.domains_list, null);
            holder = new ViewHolder();
            holder.lblName = (TextView) convertView.findViewById(R.id.tevDomainHolder);
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