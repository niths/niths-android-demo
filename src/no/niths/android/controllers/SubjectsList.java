package no.niths.android.controllers;

import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestClientException;

import no.niths.android.R;
import no.niths.android.config.ServerURL;
import no.niths.android.controllers.domain_views.SubjectView;
import no.niths.android.domains.Course;
import no.niths.android.domains.Subject;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 
 * @author NITHs
 *
 */
public class SubjectsList extends DomainList<Subject> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        super.configure();

        lw.setOnItemClickListener(new OnItemClickListener() {

            /**
             * Called when an element in the list is clicked
             */
            public void onItemClick(AdapterView<?> adapter, View view,int index,
                    long id) {

                Intent intent = new Intent(SubjectsList.this, SubjectView.class);
                intent.putExtra("class", list.get(index));
                startActivity(intent);
            }
        });

        fetchData();
        applyDataToList();
    }

    /**
     * Fetches the data from the server and marshals the incoming data
     */
    private void fetchData() {
        try {
            tempData = rest.exchange(
                    buildURL(ServerURL.LOCAL_URL, Subject.class),
                    HttpMethod.GET,
                    null,
                    Subject[].class);

        } catch (RestClientException e) {
            Log.e(getString(R.string.connection_error), e.getMessage());
        }
    }
}