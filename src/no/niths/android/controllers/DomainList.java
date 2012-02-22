package no.niths.android.controllers;

import java.util.ArrayList;

import no.niths.android.controllers.adapters.IndexListAdapter;

public abstract class DomainList<Domain> extends DomainController {
    protected ArrayList<Domain> list;
    protected IndexListAdapter adapter;

    @Override
    protected void configure() {
        super.configure();
        list = new ArrayList<Domain>();
        adapter = new IndexListAdapter(context, list);

        setListAdapter(adapter);
        lw = getListView();
    }
}