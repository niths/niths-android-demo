package no.niths.android.controllers.domain_views;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import no.niths.android.R;
import no.niths.android.domains.Domain;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * 
 * @author NITHs
 *
 */
public class IndexAttibuteAdapter extends ArrayAdapter<Field> {
    private ArrayList<Field> list;
    private LayoutInflater inflater;
    private Domain domain;
    
    public IndexAttibuteAdapter(Context context,
            ArrayList<Field> list) {
        super(context, R.layout.attributes_list, list);
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }
    
    public int getCount() {
        return list.size();
    }

    public Field getItem(int index) {
        return list.get(index);
    }

    public long getItemId(int index) {

        // TODO
        // Find something more appropriate to return
        return list.get(index).hashCode();
    }

    // Each view in the element list is represented by this
    public View getView(int index, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.attributes_list, null);
            
        }

        ViewHolder nameHolder = new ViewHolder();
        nameHolder.tevName =
                (TextView) convertView.findViewById(R.id.tevAttributeName);
        ViewHolder valueHolder = new ViewHolder();
        valueHolder.tevValue =
                (TextView) convertView.findViewById(R.id.tevAttributeValue);
        convertView.setTag(nameHolder);
        convertView.setTag(valueHolder);

        // The current field, e.g. name, id, etc.
        Field field = list.get(index);
        
        nameHolder.tevName.setText(field.getName());
        valueHolder.tevValue.setText(getValue(field));

        return convertView;
    }

    /**
     * 
     * @param Field The Field to get the current value from
     * @return String The value
     */
    private String getValue(Field field) {
        Object result = null;
        String fieldName = field.getName();
        String name =
                fieldName.substring(0, 1).toUpperCase() +
                fieldName.substring(1, fieldName.length());
        try {
            // TODO
            // Remove warnings
            Log.i("method call", ""+name);
            Method m = domain.getClass().getMethod("get" + name, null);
            result = m.invoke(domain, null);
        } catch (SecurityException e) {
            Log.e(String.valueOf(R.string.reflection_error), e.getMessage());
        } catch (NoSuchMethodException e) {
            Log.e(String.valueOf(R.string.reflection_error), e.getMessage());
        } catch (IllegalArgumentException e) {
            Log.e(String.valueOf(R.string.reflection_error), e.getMessage());
        } catch (IllegalAccessException e) {
            Log.e(String.valueOf(R.string.reflection_error), e.getMessage());
        } catch (InvocationTargetException e) {
            Log.e(String.valueOf(R.string.reflection_error), e.getMessage());
        }

        return result == null ? "null" : result.toString();
    }

    static class ViewHolder {
        TextView tevName;
        TextView tevValue;
    }
}