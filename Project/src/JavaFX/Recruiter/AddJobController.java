package JavaFX.Recruiter;

import JavaFX.ConnectDB;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddJobController implements Initializable {


    private String username;

    private ArrayList<String> antikeimList = new ArrayList<>();
    @FXML
    private Label id;

    @FXML
    private TextField description;


    @FXML
    private JFXDatePicker startDate;

    @FXML
    private JFXTextField salary;

    @FXML
    private JFXTextField position;

    @FXML
    private JFXTextField edra;

    @FXML
    private JFXTextField recruiter;

    @FXML
    private JFXDatePicker announceDate;

    @FXML
    private JFXDatePicker submissionDate;

    @FXML
    private JFXTextField antikeimeno;

    @FXML
    private Label label;

    @FXML
    private JFXTextField belongsTo;


    @FXML
    void addAntikeimeno(ActionEvent event) throws SQLException {
        //Create Connection with the DataBase
        ConnectDB connectDB = new ConnectDB();
        ConnectDB connectDB1 = new ConnectDB();
        Connection connection = connectDB.getConnection();
        Connection connection1 = connectDB1.getConnection();

        //call username and password using textFields as values
        String searchAntikeim = "SELECT title FROM antikeim where title = " + "\"" + antikeimeno.getText() + "\"";


        String updateCandidateQuerry = "";

        //Create Statement
        Statement statement = connection.createStatement();
        Statement statement1 = connection1.createStatement();

        ResultSet rs = statement.executeQuery(searchAntikeim);
        String addAntikeim;
        if (rs.next() == false) {
            if (belongsTo.getText().equals("")) {
                addAntikeim = "INSERT INTO antikeim VALUES(\"" + antikeimeno.getText() + "\", \"" + description.getText() + "\", NULL)";
            } else {
                addAntikeim = "INSERT INTO antikeim VALUES(\"" + antikeimeno.getText() + "\", \"" + description.getText() + "\", \"" + belongsTo.getText() + "\")";
            }
            statement1.executeUpdate(addAntikeim);
            label.setText("Antikeimeno Added");
            antikeimList.add(antikeimeno.getText());
            label.setVisible(true);

        } else {
            do {
                label.setText("Antikeimeno Exists");
                antikeimList.add(antikeimeno.getText());
                label.setVisible(true);
            } while (rs.next());
        }

    }


    @FXML
    void addJob(ActionEvent event) throws SQLException {
        //Create Connection with the DataBase
        ConnectDB connectDB = new ConnectDB();
        Connection connection = connectDB.getConnection();
        //call username and password using textFields as values
        String addNewJob = "INSERT INTO job (start_date, salary, position, edra, recruiter, announce_date, submission_date) VALUES" +
                "(" + "\'" + startDate.getValue() + "\', \'" + salary.getText() + "\', \'" + position.getText() + "\', \'" + edra.getText() + "\', \'"
                + recruiter.getText() + "\', \'" + announceDate.getValue() + "\', \'" + submissionDate.getValue() + "\')";
        ;

        String findID = " select id from job order by id desc limit 1";

        //Create Statement
        Statement statement = connection.createStatement();

        statement.executeUpdate(addNewJob);

        int id = -1;
        ResultSet rs = statement.executeQuery(findID);
        while (rs.next()) {
            id = rs.getInt(1);
        }
        String addAntikeimToJob = "";

        for (int i = 0; i < antikeimList.size(); i++) {
            System.out.println(antikeimList.get(i));
            statement.executeUpdate("INSERT INTO requires VALUES(" + id + ", \"" + antikeimList.get(i) + "\")");
        }
        label.setTextFill(Color.web("#4BB543"));
        label.setText("Job and Antikeimena Added Successfully");


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void initData(String text) throws SQLException {
        username = text;
        recruiter.setText(text);
        ConnectDB connectDB = new ConnectDB();
        Connection connection = connectDB.getConnection();

        //call username and password using textFields as values
        String findHighestIdQuerry = " select id from job order by id desc limit 1";

        //Create Statement
        Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery(findHighestIdQuerry);

        while (rs.next()) {
            id.setText(String.valueOf(rs.getInt(1) + 1));
        }


    }

}
