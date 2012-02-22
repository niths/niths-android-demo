package no.niths.android.controllers.domain_views.adapters;

import java.lang.reflect.Field;
import java.util.ArrayList;

import no.niths.android.controllers.DomainController;
import no.niths.android.controllers.domain_views.IndexAttibuteAdapter;

public abstract class DomainAttributesList extends DomainController {
    protected ArrayList<Field> list;
    protected IndexAttibuteAdapter adapter;
    
    @Override
    protected void configure() {
        super.configure();
        list = new ArrayList<Field>();

        adapter = new IndexAttibuteAdapter(context, list);

        setListAdapter(adapter);
        lw = getListView();
    }
}