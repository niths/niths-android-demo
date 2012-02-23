package no.niths.android.controllers;

import no.niths.android.config.ServerConfig;
import no.niths.android.controllers.domain_views.CourseView;
import no.niths.android.domains.Course;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 
 * @author NITHs
 *
 */
public class CourseList extends DomainList<Course> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        super.configure();

        lw.setOnItemClickListener(new OnItemClickListener() {

            /**
             * Called when an element in the list is clicked
             */
            public void onItemClick(AdapterView<?> adapter, View view,int index,
                    long id) {

                Intent intent = new Intent(CourseList.this, CourseView.class);
                intent.putExtra("class", list.get(index));
                startActivity(intent);
            }
        });

        fetchData();
        applyDataToList();
    }

    /**
     * Fetches the data from the server and marshals the incoming data
     */
    private void fetchData() {
//        tempData = rest.getForObject(
//                buildURL(ServerConfig.LOCAL_URL, Course.class),
//                Course[].class);
    }
}