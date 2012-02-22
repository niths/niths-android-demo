package no.niths.android.enterance;

import no.niths.android.R;
import no.niths.android.domains.Course;
import no.niths.android.domains.Event;
import no.niths.android.domains.Student;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Main menu for selecting which module he / she wants information about 
 * @author NITHs
 *
 */
public class MainMenu extends ListActivity {
    private final String MANAGER_PACKAGE = "no.niths.android.controllers.";
    private final String CONTROLLER_POSTFIX = "List";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final String[] choices = {
                getSimpleName(Course.class),
                getSimpleName(Event.class),
                getSimpleName(Student.class) };
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, choices);
        setListAdapter(adapter);
        
        ListView lw = getListView();
        lw.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View arg1,
                    int index, long id) {
                try {
                    startActivity(new Intent(MainMenu.this, Class.forName(
                            MANAGER_PACKAGE + choices[index] + CONTROLLER_POSTFIX)));
                } catch (ClassNotFoundException e) {
                    Log.e(getString(R.string.corrupt_data), e.getMessage());
                }
            }
        });
    }

    private String getSimpleName(Class<?> clazz) {
        return clazz.getSimpleName() + 's';
    }
}