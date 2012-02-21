package no.niths.android.controllers;

import no.niths.android.R;
import no.niths.android.config.ServerInfo;
import no.niths.android.domains.Student;
import android.os.Bundle;
import android.util.Log;

/**
 * 
 * @author whirlwin
 *
 */
public class StudentsController extends DomainController<Student> {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configure();
        fetchAll();
    }
    
    @Override
    public void configure() {
        super.configure();
        setContentView(R.layout.students_controller);
    }
    
    public void fetchAll() {
        Student[] students = null;

        try {
            students = rest.getForObject(
                    ServerInfo.LOCAL_URL + Student.PATH,
                    Student[].class);
            setData(students);

        } catch (Exception e) {
            Log.e(getString(R.string.connection_error), e.getMessage());
        }
    }

    public void setData(Student[] students) {
        adapter.clear();

        for (Student student : students) {
            adapter.add(student);
        }
    }
}