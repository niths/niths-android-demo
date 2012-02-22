package no.niths.android.enterance;

import no.niths.android.controllers.CourseList;
import no.niths.android.domains.Course;
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
    private final String CONTROLLER_POSTFIX = "Controller";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final String[] choises = {
                getSimpleName(Course.class),
                getSimpleName(Student.class) };
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1,
                choises);
        setListAdapter(adapter);
        
        ListView lw = getListView();
        lw.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View arg1,
                    int index, long id) {
                startActivity(new Intent(MainMenu.this, CourseList.class));
                /*
                // Finds and launches the intent class dynamically
                Class<?> clazz = Class.forName(
                        MANAGER_PACKAGE + choises[index] +
                        CONTROLLER_POSTFIX);
                startActivity(new Intent(MainMenu.this, clazz));
                */
            }
        });
    }

    private String getSimpleName(Class<?> clazz) {
        return clazz.getSimpleName() + 's';
    }
}