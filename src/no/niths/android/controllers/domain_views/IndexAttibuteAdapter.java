package no.niths.android.controllers.domain_views;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import no.niths.android.R;
import no.niths.android.domains.Course;
import no.niths.android.domains.Domain;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class IndexAttibuteAdapter extends BaseAdapter {
    private ArrayList<Field> list;
    private LayoutInflater inflater;
    private Domain domain;
    
    public IndexAttibuteAdapter(Context context,
            ArrayList<Field> applicationList) {
        this.list = applicationList;
        inflater = LayoutInflater.from(context);
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
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
        ViewHolder nameHolder;
        ViewHolder valueHolder;
        
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.attributes_list, null);
            nameHolder = new ViewHolder();
            nameHolder.tevName =
                    (TextView) convertView.findViewById(R.id.tevAttributeName);
            valueHolder = new ViewHolder();
            valueHolder.tevValue =
                    (TextView) convertView.findViewById(R.id.tevAttributeValue);
            convertView.setTag(nameHolder);
            convertView.setTag(valueHolder);
        } else {
            nameHolder = (ViewHolder) convertView.getTag();
            valueHolder = (ViewHolder) convertView.getTag();
        }

        Field field = list.get(index);
        
        nameHolder.tevName.setText(field.getName());
        //Method m = field.getType().getClass().getMethod(
        //        "get" + field.getName(), parameterTypes)
        valueHolder.tevValue.setText(getValue(field));

        return convertView;
    }

    private String getValue(Field field) {
        Object result = null;
        String fieldName = field.getName();
        String name =
                fieldName.substring(0, 1).toUpperCase() +
                fieldName.substring(1, fieldName.length());
        try {
            Method m = domain.getClass().getMethod("get" + name, null);
            result = m.invoke(domain, null);
        } catch (SecurityException e) {
            Log.i("ref err", e.getMessage());
        } catch (NoSuchMethodException e) {
            Log.i("ref err", e.getMessage());
        } catch (IllegalArgumentException e) {
            Log.i("ref err", e.getMessage());
        } catch (IllegalAccessException e) {
            Log.i("ref err", e.getMessage());
        } catch (InvocationTargetException e) {
            Log.i("ref err", e.getMessage());
        }

        return result.toString();
    }

    static class ViewHolder {
        TextView tevName;
        TextView tevValue;
    }
}