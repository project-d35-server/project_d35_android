package fr.univtln.serverclient;

import android.content.Intent;
import android.os.NetworkOnMainThreadException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.ViewsById;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.ArrayList;
import java.util.List;

import fr.univtln.serverclient.model.Job;
import fr.univtln.serverclient.model.Profile;
import fr.univtln.serverclient.utilities.IProfileAPI;
import fr.univtln.serverclient.utilities.IProfileAPI_;

@EActivity(R.layout.activity_create_profile)
public class CreateProfileActivity extends AppCompatActivity {



    @ViewById(R.id.ll_jobs)
     LinearLayout ll_jobs;

    @ViewById(R.id.ll_salaries)
      LinearLayout ll_salaries;
     List<CheckBox> checkBoxesJobs;

    @ViewById(R.id.e_surname)
     EditText e_surname;

    @ViewById(R.id.e_name)
   EditText e_name;

    @ViewById(R.id.e_age)
     EditText e_age;

   private List<EditText> editTextsSalaries;

    private IProfileAPI iProfileAPI;


    @AfterViews
    public void init(){
        iProfileAPI = new IProfileAPI_(this.getApplicationContext());
        Job.NAME[] names=Job.NAME.values();
        editTextsSalaries=new ArrayList<>();
        checkBoxesJobs=new ArrayList<>();
        for(Job.NAME name:names ){
            final CheckBox checkBox=new CheckBox(this);
            checkBox.setText(name.toString());
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(checkBox.isChecked()){
                        checkBoxesJobs.add(checkBox);
                        LinearLayout linearLayout=new LinearLayout(getCreateProfileActivity());
                        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
                        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                        TextView textView=new TextView(getCreateProfileActivity());
                        textView.setText(checkBox.getText().toString());
                        linearLayout.addView(textView);
                        EditText editText=new EditText(getCreateProfileActivity());
                        LinearLayout.LayoutParams editTextParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
                        editText.setLayoutParams(editTextParams);
                        linearLayout.addView(editText);
                        TextView textView2=new TextView(getCreateProfileActivity());
                        textView2.setText(getString(R.string.euros_mois));
                        linearLayout.addView(textView2);
                        ll_salaries.addView(linearLayout);
                        editTextsSalaries.add(editText);
                    }
                    else{
                        checkBoxesJobs.remove(checkBox);
                        editTextsSalaries=new ArrayList<>();
                        ll_salaries.removeAllViews();
                        for(CheckBox checkBox1: checkBoxesJobs){
                            LinearLayout linearLayout=new LinearLayout(getCreateProfileActivity());
                            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
                            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                            TextView textView=new TextView(getCreateProfileActivity());
                            textView.setText(checkBox1.getText().toString());
                            linearLayout.addView(textView);
                            EditText editText=new EditText(getCreateProfileActivity());
                            LinearLayout.LayoutParams editTextParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
                            editText.setLayoutParams(editTextParams);
                            linearLayout.addView(editText);
                            TextView textView2=new TextView(getCreateProfileActivity());
                            textView2.setText(getString(R.string.euros_mois));
                            linearLayout.addView(textView2);
                            ll_salaries.addView(linearLayout);
                            editTextsSalaries.add(editText);
                        }
                    }
                }
            });
            ll_jobs.addView(checkBox);
        }

    }
    public CreateProfileActivity getCreateProfileActivity(){
        return this;
    }

    @Click(R.id.b_create_profile2)
    public void createProfile(View view){
        List<Job> jobs2=new ArrayList<>();
        Profile.ProfileBuilder profileBuilder=new Profile.ProfileBuilder().setSurname(e_surname.getText().toString());
        profileBuilder.setName(e_name.getText().toString()).setAge(Integer.parseInt(e_age.getText().toString()));
        int i=0;
        for(CheckBox checkBox: checkBoxesJobs){
             Job.JobBuilder jobBuilder=new Job.JobBuilder();
             Job.NAME name=Job.NAME.valueOf(checkBox.getText().toString());
             jobBuilder.setName(name);
             jobBuilder.setSalary(Integer.parseInt(editTextsSalaries.get(i).getText().toString()));
             Job job=jobBuilder.build();
             jobs2.add(job);
             i=i+1;
        }
        profileBuilder.setJobs(jobs2);
        Profile profile=profileBuilder.build();
        persist(profile);

    }

    @Background
    void getProfileResult() {
        List<Profile> response = iProfileAPI.getProfiles();

        Log.d("test","str : "+response);
    }

    @Background
    public void persist(Profile profile){
        Profile profile1= iProfileAPI.persist(profile);
        updateView(profile1);
    }


   public void updateView(Profile profile){
       Intent intent=new Intent(this, ProfileActivity_.class);
       intent.putExtra("profile",profile);
       startActivity(intent);
   }
}
