package no.niths.android.controllers;

import no.niths.android.R;
import no.niths.android.common.AppController;
import no.niths.android.config.ServerConfig;
import no.niths.android.config.TokenConfig;
import no.niths.android.controllers.domain_views.CommitteeView;
import no.niths.android.domains.Committee;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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
public class CommitteesList extends DomainList<Committee> {

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

                Intent intent = new Intent(CommitteesList.this, CommitteeView.class);
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
        HttpHeaders headers = new HttpHeaders();
        headers.set(TokenConfig.HEADER_NAME.toString(), AppController.token);
        headers.set("Authorization", "Basic YWRtaW46YWRtaW4=");
        
        try {
            tempData = rest.exchange(buildURL(ServerConfig.LOCAL_URL, Committee.class), HttpMethod.GET, new HttpEntity<Committee>(headers), Committee[].class);
        } catch (RestClientException e) {
            Log.e(getString(R.string.connection_error), e.getMessage());
        }
    }
}