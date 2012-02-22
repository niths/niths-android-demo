package no.niths.android.controllers.domain_views;

import java.lang.reflect.Field;

import no.niths.android.controllers.domain_views.adapters.DomainAttributesList;
import no.niths.android.domains.Subject;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class SubjectView extends DomainAttributesList {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        super.configure();
        adapter.setDomain((Subject) getIntent().getSerializableExtra("class"));

        lw.setOnItemClickListener(new OnItemClickListener() {

            /**
             * Called when an element in the list is clicked
             */
            public void onItemClick(AdapterView<?> adapter, View view,
                    int index, long id) {
                // TODO Show CRUD dialog
            }
        });

        Field[] fields = Subject.class.getDeclaredFields();
        
        for (Field field : fields) {
            list.add(field);
        }
    }
}