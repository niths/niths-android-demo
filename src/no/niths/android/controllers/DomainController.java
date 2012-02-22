package no.niths.android.controllers;

import no.niths.android.controllers.adapters.IndexListAdapter;
import no.niths.android.controllers.domain_views.IndexAttibuteAdapter;

import org.springframework.web.client.RestTemplate;

import android.app.ListActivity;
import android.content.Context;
import android.widget.ListView;

/**
 * 
 * @author NITHs
 *
 */
public class DomainController extends ListActivity {
    protected RestTemplate rest;
    protected Context context;
    protected ListView lw;

    protected void configure() {
        rest = new RestTemplate();
    }
}