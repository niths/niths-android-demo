package no.niths.android.controllers;

import java.util.ArrayList;

import no.niths.android.config.ServerURL;
import no.niths.android.controllers.adapters.IndexListAdapter;

public abstract class DomainList<Domain> extends DomainController {
    protected ArrayList<Domain> list;
    protected IndexListAdapter<?> adapter;
    protected Domain[] tempData;

    @Override
    protected void configure() {
        super.configure();
        list = new ArrayList<Domain>();
        adapter = new IndexListAdapter(context, list);

        setListAdapter(adapter);
        lw = getListView();
    }

    /**
     * 
     * @param ServerURL The URL
     * @param The class to be fetched
     * @return String the 
     */
    protected String buildURL(ServerURL serverInfo, Class<?> clazz) {
        return serverInfo + clazz.getSimpleName().toLowerCase() + 's';
    }

    protected void applyDataToList() {
        list.clear();

        for (Domain domain : tempData) {
            list.add(domain);
        }
    }
}