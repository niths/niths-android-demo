package no.niths.android.controllers;

import no.niths.android.managers.interfaces.Fetchable;

import org.springframework.web.client.RestTemplate;

import android.app.ListActivity;
import android.widget.ArrayAdapter;

/**
 * 
 * @author NITHs
 *
 */
public abstract class DomainController<T> extends ListActivity
        implements Fetchable<T> {
    protected RestTemplate rest;
    protected ArrayAdapter<T> adapter;

    protected void configure() {
        rest = new RestTemplate();
        adapter = new ArrayAdapter<T>(
                this, android.R.layout.simple_list_item_1);
        setListAdapter(adapter);
    }
}