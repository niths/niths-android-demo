package no.niths.android.enterance;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
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
    private final String MANAGER_PACKAGE = "no.niths.android.managers";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final String[] choises = { "Courses" };
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1,
                choises);
        setListAdapter(adapter);
        
        ListView lw = getListView();
        lw.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View arg1,
                    int index, long id) {
                try {

                    // Finds and launches the class dynamically
                    Class<?> clazz = Class.forName(
                            MANAGER_PACKAGE + choises[index] + "Manager");
                    startActivity(new Intent(MainMenu.this, clazz));
                } catch (ClassNotFoundException e) {
                    // Do nothing
                }
            }
        });
    }
}