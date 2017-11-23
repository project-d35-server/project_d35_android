package fr.univtln.serverclient;

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

    @Click(R.id.b_retrieve_profile)
    void onClick(View view) {
        getProfileResult();
        Toast.makeText(this," Coucou ", Toast.LENGTH_LONG).show();

    }

    @Background
    void getProfileResult() {
        List<Profile> response = iProfileAPI.getProfiles();

        Log.d("test","str : "+response);
    }
}
