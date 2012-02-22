package no.niths.android.controllers;

import java.util.ArrayList;

import no.niths.android.config.ServerConfig;
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
     * @param ServerConfig The URL
     * @param The class to be fetched
     * @return String the 
     */
    protected String buildURL(ServerConfig serverInfo, Class<?> clazz) {
        return serverInfo + clazz.getSimpleName().toLowerCase() + 's';
    }

    /**
     * Populates the list with the data fetched
     */
    protected void applyDataToList() {
        list.clear();

        if (tempData != null) {
            for (Domain domain : tempData) {
                list.add(domain);
            }
        }
    }
}