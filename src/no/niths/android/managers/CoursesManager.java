package no.niths.android.managers;

import no.niths.android.R;

import org.springframework.web.client.RestTemplate;

import android.os.Bundle;
import android.util.Log;

public class CoursesManager extends DomainManager {
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("was called", "wad called");
        configure();
        fetchAll();
    }

    private void configure() {
        setContentView(R.layout.courses_manager);
    }

    public void fetchAll() {
        RestTemplate rest = new RestTemplate();
    }
}