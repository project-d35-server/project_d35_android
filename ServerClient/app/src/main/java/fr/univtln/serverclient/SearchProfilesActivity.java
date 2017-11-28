package fr.univtln.serverclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import fr.univtln.serverclient.model.Profile;
import fr.univtln.serverclient.utilities.IProfileAPI;
import fr.univtln.serverclient.utilities.IProfileAPI_;

@EActivity(R.layout.activity_search_profiles)
public class SearchProfilesActivity extends AppCompatActivity {
    @ViewById(R.id.lv_profiles)
    ListView lv_profiles;

    List<Profile> profiles;

    IProfileAPI iProfileAPI;

    @AfterViews
    public void init(){
        iProfileAPI=new IProfileAPI_(getApplicationContext());
        getProfiles();
    }

    @Background
    public void getProfiles(){
        profiles=iProfileAPI.getProfiles();
        updateView();
    }

    @UiThread
    public void updateView() {

        List<String> strings=new ArrayList<>(profiles.size());
        for(Profile profile : profiles){
            strings.add(profile.toString());
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, strings);
        lv_profiles.setAdapter(adapter);
       lv_profiles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               Log.d("test", String.valueOf(i));
               Intent intent=new Intent(getSearchProfilesActivity(), ProfileActivity_.class);
               intent.putExtra("profile", profiles.get(i));
               startActivity(intent);
           }
       });
    }

    public SearchProfilesActivity getSearchProfilesActivity(){
        return this;
    }

}
