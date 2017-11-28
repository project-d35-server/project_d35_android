package fr.univtln.serverclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import fr.univtln.serverclient.model.Job;
import fr.univtln.serverclient.model.Profile;
import fr.univtln.serverclient.utilities.IProfileAPI;
import fr.univtln.serverclient.utilities.IProfileAPI_;

@EActivity(R.layout.activity_profile)
public class ProfileActivity extends AppCompatActivity {

    @ViewById(R.id.tv_surname)
    TextView tv_surname;

    @ViewById(R.id.tv_name)
    TextView tv_name;

    @ViewById(R.id.tv_age)
    TextView tv_age;

    @ViewById
    LinearLayout ll_jobs2;

    @ViewById
    TextView tv_taxes;


    IProfileAPI iProfileAPI;

    Profile profile;

    @AfterViews
    public void init(){
        iProfileAPI=new IProfileAPI_(getApplicationContext());
        Intent intent=getIntent();
        profile=(Profile)intent.getSerializableExtra("profile");
        Log.d("profile", profile.toString());
        tv_surname.setText(tv_surname.getHint().toString()+profile.getSurname());
        tv_name.setText(tv_name.getHint().toString()+profile.getName());
         tv_age.setText(tv_age.getHint().toString()+String.valueOf(profile.getAge()));
        for(Job job :profile.getJobs()){
            TextView textView=new TextView(this);
            textView.setText(job.getName()+" : "+job.getSalary()+getString(R.string.euros_mois));
            ll_jobs2.addView(textView);
        }

    }

    @Click(R.id.b_calculate_taxes)
    public void calculateTaxes(){
        tv_taxes.setText(getString(R.string.taxes)+String.valueOf(profile.getTaxes())+getString(R.string.euros_mois));
    }

    @Click(R.id.b_remove_profile)
    public void removeProfile(){
        remove();
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(this, MainActivity_.class);
        startActivity(intent);
    }

    @Background
    public void remove(){
        iProfileAPI.remove(profile.getId());
    }


}
