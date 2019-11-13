package JavaFX.TableObject;

import javafx.beans.property.SimpleStringProperty;

public class ActionHistoryObject {

    private SimpleStringProperty user;
    private SimpleStringProperty action;
    private SimpleStringProperty date;
    private SimpleStringProperty time;
    private SimpleStringProperty onTable;
    private SimpleStringProperty status;

    public ActionHistoryObject(String user, String action, String date, String time, String onTable, String status) {
        this.user = new SimpleStringProperty(user);
        this.action = new SimpleStringProperty(action);;
        this.date = new SimpleStringProperty(date);;
        this.time = new SimpleStringProperty(time);;
        this.onTable = new SimpleStringProperty(onTable);;
        this.status = new SimpleStringProperty(status);;
    }

    public String getUser() {
        return user.get();
    }

    public SimpleStringProperty userProperty() {
        return user;
    }

    public void setUser(String user) {
        this.user.set(user);
    }

    public String getAction() {
        return action.get();
    }

    public SimpleStringProperty actionProperty() {
        return action;
    }

    public void setAction(String action) {
        this.action.set(action);
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

    public String getTime() {
        return time.get();
    }

    public SimpleStringProperty timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public String getOnTable() {
        return onTable.get();
    }

    public SimpleStringProperty onTableProperty() {
        return onTable;
    }

    public void setOnTable(String onTable) {
        this.onTable.set(onTable);
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
}
