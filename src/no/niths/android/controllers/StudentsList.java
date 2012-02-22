package no.niths.android.controllers;

import no.niths.android.R;
import no.niths.android.config.ServerURL;
import no.niths.android.controllers.domain_views.StudentView;
import no.niths.android.domains.Student;

import org.springframework.web.client.RestClientException;

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
public class StudentsList extends DomainList<Student> {

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

                Intent intent = new Intent(StudentsList.this, StudentView.class);
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
            tempData = rest.getForObject(
                    buildURL(ServerURL.LOCAL_URL, Student.class),
                    Student[].class);
        } catch (RestClientException e) {
            Log.e(getString(R.string.connection_error), e.getMessage());
        }
    }
}