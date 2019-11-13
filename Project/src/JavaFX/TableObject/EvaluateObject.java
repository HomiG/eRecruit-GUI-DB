package JavaFX.TableObject;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class EvaluateObject {


    public String getCandidate() {
        return candidate.get();
    }

    public SimpleStringProperty candidateProperty() {
        return candidate;
    }

    public void setCandidate(String candidate) {
        this.candidate.set(candidate);
    }

    public String getRecruiter() {
        return recruiter.get();
    }

    public SimpleStringProperty recruiterProperty() {
        return recruiter;
    }

    public void setRecruiter(String recruiter) {
        this.recruiter.set(recruiter);
    }





    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }



    public int getJobId() {
        return jobId.get();
    }

    public SimpleIntegerProperty jobIdProperty() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId.set(jobId);
    }



    private SimpleStringProperty candidate;
    private SimpleStringProperty recruiter;
    private SimpleIntegerProperty jobId;
    private SimpleIntegerProperty personality;
    private SimpleIntegerProperty education;
    private SimpleIntegerProperty experience;
    private SimpleStringProperty date;

    public int getPersonality() {
        return personality.get();
    }

    public SimpleIntegerProperty personalityProperty() {
        return personality;
    }

    public void setPersonality(int personality) {
        this.personality.set(personality);
    }

    public int getEducation() {
        return education.get();
    }

    public SimpleIntegerProperty educationProperty() {
        return education;
    }

    public EvaluateObject() {
    }

    public void setEducation(int education) {
        this.education.set(education);
    }

    public int getExperience() {
        return experience.get();
    }

    public SimpleIntegerProperty experienceProperty() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience.set(experience);
    }



    public EvaluateObject(String candidate, String recruiter, int jobId, String date, int personality, int education, int experience) {
        this.candidate = new SimpleStringProperty(candidate);
        this.personality = new SimpleIntegerProperty(personality);
        this.education = new SimpleIntegerProperty(education);
        this.jobId = new SimpleIntegerProperty(jobId);
        this.experience = new SimpleIntegerProperty(experience);
        this.recruiter = new SimpleStringProperty(recruiter);
        this.date = new SimpleStringProperty(date);


    }



}
