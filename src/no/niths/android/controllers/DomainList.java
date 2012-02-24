package no.niths.android.controllers;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;

import no.niths.android.config.ServerURL;
import no.niths.android.controllers.adapters.IndexListAdapter;
import no.niths.android.domains.Committee;

public abstract class DomainList<Domain> extends DomainController {
    protected ArrayList<Domain> list;
    protected IndexListAdapter<?> adapter;
    protected ResponseEntity<Domain[]> tempData;
    

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

    /**
     * Populates the list with the data fetched
     */
    protected void applyDataToList() {
        list.clear();

        if (tempData != null) {
            Domain[] domains = tempData.getBody();

            for (Domain domain : domains) {
                list.add(domain);
            }
        }
    }
}