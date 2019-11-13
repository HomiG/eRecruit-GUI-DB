package JavaFX.TableObject;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class SuccessfulObject {

    public SuccessfulObject(String candidate, int personality, int education, int experience, int totalScore, int totalInterviews) {
        this.candidate = new SimpleStringProperty(candidate);
        this.education = new SimpleIntegerProperty(education);
        this.experience = new SimpleIntegerProperty(experience);
        this.personality = new SimpleIntegerProperty(personality);
        this.totalScore = new SimpleIntegerProperty(totalScore);
        this.totalInterviews = new SimpleIntegerProperty(totalInterviews);
    }

    public String getCandidate() {
        return candidate.get();
    }

    public SimpleStringProperty candidateProperty() {
        return candidate;
    }

    public void setCandidate(String candidate) {
        this.candidate.set(candidate);
    }

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

    public int getTotalScore() {
        return totalScore.get();
    }

    public SimpleIntegerProperty totalScoreProperty() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore.set(totalScore);
    }

    public int getTotalInterviews() {
        return totalInterviews.get();
    }

    public SimpleIntegerProperty totalInterviewsProperty() {
        return totalInterviews;
    }

    public void setTotalInterviews(int totalInterviews) {
        this.totalInterviews.set(totalInterviews);
    }

    private SimpleStringProperty candidate;
    private SimpleIntegerProperty personality;
    private SimpleIntegerProperty education;
    private SimpleIntegerProperty experience;
    private SimpleIntegerProperty totalScore;
    private SimpleIntegerProperty totalInterviews;


}
