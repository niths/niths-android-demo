package no.niths.android.controllers.domain_views;

import java.lang.reflect.Field;

import no.niths.android.controllers.domain_views.adapters.DomainAttributesList;
import no.niths.android.domains.Committee;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class CommitteeView extends DomainAttributesList {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        super.configure();
        adapter.setDomain((Committee) getIntent().getSerializableExtra("class"));

        lw.setOnItemClickListener(new OnItemClickListener() {

            /**
             * Called when an element in the list is clicked
             */
            public void onItemClick(AdapterView<?> adapter, View view,
                    int index, long id) {
                // TODO Show CRUD dialog
            }
        });

        Field[] fields = Committee.class.getDeclaredFields();
        
        for (Field field : fields) {
            list.add(field);
        }
    }
}