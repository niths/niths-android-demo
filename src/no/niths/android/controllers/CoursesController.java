package no.niths.android.controllers;

import no.niths.android.R;
import no.niths.android.config.ServerInfo;
import no.niths.android.domains.Course;
import android.os.Bundle;
import android.util.Log;

/**
 * 
 * @author NITHs
 *
 */
public class CoursesController extends DomainController<Course> {
    
    @Override 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configure();
        fetchAll();
    }

    @Override
    protected void configure() {
        super.configure();
        setContentView(R.layout.courses_controller);
    }

    public void fetchAll() {
        Course[] courses = null;

        try {
            courses = rest.getForObject(
                    ServerInfo.LOCAL_URL.toString() + Course.PATH,
                    Course[].class);
            setData(courses);

        } catch (Exception e) {
            Log.e(getString(R.string.connection_error), ""+e.getMessage());
        }
    }

    public void setData(Course[] courses) {
        adapter.clear();

        for (Course course : courses) {
            adapter.add(course);
        }
    }
}