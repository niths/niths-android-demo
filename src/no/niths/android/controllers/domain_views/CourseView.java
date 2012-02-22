package no.niths.android.controllers.domain_views;

import java.lang.reflect.Field;

import no.niths.android.controllers.domain_views.adapters.DomainAttributesList;
import no.niths.android.domains.Course;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class CourseView extends DomainAttributesList {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        super.configure();

        lw.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> adapter, View view,
                    int index, long id) {
                Toast.makeText(CourseView.this, "foo", Toast.LENGTH_LONG).show();
                // TODO show
            }
        });

        Field[] fields = Course.class.getDeclaredFields();
        
        for (Field field : fields) {
            list.add(field);
        }
    }
}