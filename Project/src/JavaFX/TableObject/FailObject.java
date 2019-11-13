package JavaFX.TableObject;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class FailObject {



    public String getCandidate() {
        return candidate.get();
    }

    public SimpleStringProperty candidateProperty() {
        return candidate;
    }

    public void setCandidate(String candidate) {
        this.candidate.set(candidate);
    }

    public String getMessage() {
        return message.get();
    }

    public SimpleStringProperty messageProperty() {
        return message;
    }

    public void setMessage(String message) {
        this.message.set(message);
    }



    private SimpleStringProperty candidate;
    private SimpleStringProperty message;



    public FailObject(String candidate, String message) {
        this.candidate = new SimpleStringProperty(candidate);
        this.message = new SimpleStringProperty(message);
    }
}
