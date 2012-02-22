package no.niths.android.controllers;

import no.niths.android.config.ServerInfo;
import no.niths.android.controllers.domain_views.CourseView;
import no.niths.android.domains.Course;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
            public void onItemClick(AdapterView<?> adapter, View view,int index,
                    long id) {
                startActivity(new Intent(CourseList.this, CourseView.class));
            }
        });

        fetchData();
    }

    private void fetchData() {
        Course[] courses = null;
        try {courses = rest.getForObject(
                ServerInfo.LOCAL_URL +
                Course.class.getSimpleName().toLowerCase() + 's',
                Course[].class);
        Log.i("URL", ServerInfo.LOCAL_URL +
                Course.class.getSimpleName().toLowerCase() + 's');
        
        } catch (Exception e) {
            Log.e("errr", "is "+e.getMessage());
        }

        list.clear();
        for (Course course : courses) {
            list.add(course);
        }
    }
}