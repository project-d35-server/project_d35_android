package fr.univtln.serverclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.rest.spring.annotations.RestService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import fr.univtln.serverclient.model.Profile;
import fr.univtln.serverclient.utilities.IProfileAPI;
import fr.univtln.serverclient.utilities.IProfileAPI_;
//import fr.univtln.serverclient.utilities.IProfileAPI_;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    //@RestService
    IProfileAPI iProfileAPI;

    @AfterViews
    void afterViews(){
        iProfileAPI = new IProfileAPI_(this.getApplicationContext());
        Log.d("test", "ok");

    }

    /*
    @Click(R.id.b_retrieve_profile)
    void retrieveProfiles(View view) {
        getProfileResult();
        Toast.makeText(this," Coucou ", Toast.LENGTH_LONG).show();

    }
     */
    @Click(R.id.b_retrieve_profile)
    void retrieveProfiles(View view) {
        Intent intent=new Intent(this, SearchProfilesActivity_.class);
        startActivity(intent);

    }

    @Click(R.id.b_create_profile)
    void createProfiles(View view) {
       Intent intent=new Intent(this, CreateProfileActivity_.class);
       startActivity(intent);
    }

    @Background
    void getProfileResult() {
        List<Profile> response = iProfileAPI.getProfiles();

        Log.d("test","str : "+response);
    }
}
