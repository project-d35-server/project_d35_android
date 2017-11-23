package fr.univtln.serverclient.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by damienlemenager on 23/11/2017.
 */

public class Profile implements Serializable {

    private int id;

    private String name;

    private String surname;

    private List<Job> jobs = new ArrayList<>();

    private int age;

    public Profile() {
    }

    public Profile(ProfileBuilder profileBuilder) {
        this.age = profileBuilder.age;
        this.name = profileBuilder.name;
        this.surname = profileBuilder.surname;
        this.jobs = profileBuilder.jobs;
    }

    public static class ProfileBuilder {
        private String name;
        private String surname;
        private int age;
        private List<Job> jobs = new ArrayList<>();

        public ProfileBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ProfileBuilder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public ProfileBuilder setAge(int age) {
            this.age = age;
            return this;
        }

        public ProfileBuilder setJobs(List<Job> jobs) {
            this.jobs = jobs;
            return this;
        }

        public Profile build() {
            return new Profile(this);
        }
    }

    public int getTaxes() {
        int countTaxes = 0;
        /*for (Job job :
                jobs) {
            countTaxes += job.getSalary()/10;
        }*/
        return countTaxes;
    }



}
