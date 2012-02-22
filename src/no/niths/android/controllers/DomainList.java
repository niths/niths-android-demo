package no.niths.android.controllers;

import java.util.ArrayList;

import no.niths.android.R;
import no.niths.android.controllers.adapters.IndexListAdapter;

import org.springframework.web.client.RestTemplate;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.widget.ListView;
import android.widget.TextView;

public abstract class DomainList<Domain> extends ListActivity {
    protected RestTemplate rest;
    protected ArrayList<Domain> list;
    protected IndexListAdapter<?> adapter;
    protected Context context;
    protected ListView lw;
    protected TextView tevInformation;
    protected AlertDialog.Builder builder;

    protected void configure() {
        rest = new RestTemplate();
        list = new ArrayList<Domain>();
        adapter = new IndexListAdapter(context, list);

        setListAdapter(adapter);
        lw = getListView();

        tevInformation = new TextView(context);
        
        builder= new AlertDialog.Builder(context);
        builder.setTitle(getString(R.string.information));

        builder.setView(tevInformation);
    }

    protected void displayInformation(String information) {
        tevInformation.setText(information);
        builder.create();
        builder.show();
    }
}