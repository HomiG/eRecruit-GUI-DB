package JavaFX.TableObject;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ApplicationObject {


    public String getJob() {
        return job.get();
    }

    public SimpleStringProperty jobProperty() {
        return job;
    }

    public void setJob(String job) {
        this.job.set(job);
    }

    public String getApplied() {
        return applied.get();
    }

    public SimpleStringProperty appliedProperty() {
        return applied;
    }

    public void setApplied(String applied) {
        this.applied.set(applied);
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }





    public ApplicationObject(String job, String applied, String status, int jobId) {
        this.job = new SimpleStringProperty(job);
        this.applied = new SimpleStringProperty(applied);
        this.status = new SimpleStringProperty(status);
        this.id = new SimpleIntegerProperty(jobId);

    }

    private SimpleStringProperty job;
    private SimpleStringProperty applied;
    private SimpleStringProperty status;


    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    private SimpleIntegerProperty id;


}
