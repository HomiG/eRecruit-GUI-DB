package JavaFX.TableObject;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ShowJobObject {


    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public String getStartDate() {
        return startDate.get();
    }

    public SimpleStringProperty startDateProperty() {
        return startDate;
    }

    public String getSalary() {
        return salary.get();
    }

    public SimpleStringProperty salaryProperty() {
        return salary;
    }

    public String getPosition() {
        return position.get();
    }

    public SimpleStringProperty positionProperty() {
        return position;
    }

    public String getEdra() {
        return edra.get();
    }

    public SimpleStringProperty edraProperty() {
        return edra;
    }

    public String getRecruiter() {
        return recruiter.get();
    }

    public SimpleStringProperty recruiterProperty() {
        return recruiter;
    }

    public String getAncDate() {
        return ancDate.get();
    }

    public SimpleStringProperty ancDateProperty() {
        return ancDate;
    }

    public String getSubDate() {
        return subDate.get();
    }

    public SimpleStringProperty subDateProperty() {
        return subDate;
    }






    public ShowJobObject(String id, String startDate, String salary, String position, String edra, String recruiter, String ancDate, String subDate) {
        this.id = new SimpleStringProperty(id);
        this.startDate = new SimpleStringProperty(startDate);
        this.salary = new SimpleStringProperty(salary);
        this.position = new SimpleStringProperty(position);
        this.edra = new SimpleStringProperty(edra);
        this.recruiter = new SimpleStringProperty(recruiter);
        this.ancDate = new SimpleStringProperty(ancDate);
        this.subDate = new SimpleStringProperty(subDate);


    }

    private SimpleStringProperty id;

    public void setId(String id) {
        this.id.set(id);
    }

    public void setStartDate(String startDate) {
        this.startDate.set(startDate);
    }

    public void setSalary(String salary) {
        this.salary.set(salary);
    }

    public void setPosition(String position) {
        this.position.set(position);
    }

    public void setEdra(String edra) {
        this.edra.set(edra);
    }

    public void setRecruiter(String recruiter) {
        this.recruiter.set(recruiter);
    }

    public void setAncDate(String ancDate) {
        this.ancDate.set(ancDate);
    }

    public void setSubDate(String subDate) {
        this.subDate.set(subDate);
    }

    private SimpleStringProperty startDate;
    private SimpleStringProperty salary;
    private SimpleStringProperty position;
    private SimpleStringProperty edra;
    private SimpleStringProperty recruiter;
    private SimpleStringProperty ancDate;
    private SimpleStringProperty subDate;




}
