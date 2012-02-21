package no.niths.android.managers;

import no.niths.android.R;
import no.niths.android.config.ServerInfo;
import no.niths.android.domains.Course;
import android.os.Bundle;
import android.util.Log;

public class CoursesManager extends DomainManager<Course> {
    
    @Override 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configure();
        fetchAll();
    }

    @Override
    protected void configure() {
        super.configure();
        setContentView(R.layout.courses_manager);
    }

    public Course[] fetchAll() {
        Course[] courses = null;

        try {
            courses = rest.getForObject(
                    ServerInfo.LOCAL_URL.toString() + Course.PATH,
                    Course[].class);
            
        } catch (Exception e) {
            Log.e(getString(R.string.connection_error), ""+e.getMessage());
        }

        setData(courses);
        return courses;
    }

    public void setData(Course[] courses) {
        adapter.clear();

        for (Course course : courses) {
            adapter.add(course);
        }
    }
}