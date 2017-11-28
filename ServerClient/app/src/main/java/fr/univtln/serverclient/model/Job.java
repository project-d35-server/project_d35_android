package fr.univtln.serverclient.model;

import java.io.Serializable;

import lombok.Data;

/**
 * Created by damienlemenager on 23/11/2017.
 */
@Data
public class Job implements Serializable {

    private long id;

    private int salary;

    private NAME name;

    public enum NAME {
        POLICEMAN,
        BAKER,
        DEVELOPER
    }


    @Override
    public String toString() {
        return name.toString();
    }

    public Job() {
    }

    public Job(JobBuilder jobBuilder) {
        this.name = jobBuilder.name;
        this.salary = jobBuilder.salary;
    }

    public static class JobBuilder {
        private int salary;
        private NAME name;

        public JobBuilder setSalary(int salary) {
            this.salary = salary;
            return this;
        }

        public JobBuilder setName(NAME name) {
            this.name = name;
            return this;
        }

        public Job build() {
            return new Job(this);
        }
    }
}
