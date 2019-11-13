package JavaFX.Recruiter;

import JavaFX.ConnectDB;
import JavaFX.TableObject.EvaluateObject;
import JavaFX.TableObject.MyJobObject;
import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import javax.print.DocFlavor;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.ResourceBundle;

public class EvaluateController implements Initializable {

    private String username;

    @FXML
    private JFXButton evaluateInterviewButton;


    @FXML
    private JFXTextField duration;

    @FXML
    private JFXTextField comments;


    @FXML
    private JFXButton exitButton;

    @FXML
    private TableView<EvaluateObject> table;

    @FXML
    private TableColumn<EvaluateObject, String> candidate;

    @FXML
    private TableColumn<EvaluateObject, String> recruiter;

    @FXML
    private TableColumn<EvaluateObject, Integer> jobId;

    @FXML
    private TableColumn<EvaluateObject, Integer> personality;

    @FXML
    private TableColumn<EvaluateObject, Integer> education;

    @FXML
    private TableColumn<EvaluateObject, Integer> experience;


    @FXML
    private Label label;
    @FXML
    private TableColumn<EvaluateObject, String> date;
    private ObservableList<EvaluateObject> list;


    @FXML
    private JFXButton updateButton;


    public void exit(ActionEvent actionEvent) {
        Stage evaluateWindow = (Stage) exitButton.getScene().getWindow();
        evaluateWindow.close();
    }

    @FXML
    void updateAction(ActionEvent event) throws SQLException {
        EvaluateObject selectedItem = new EvaluateObject();
        selectedItem = table.getSelectionModel().getSelectedItem();
//        System.out.println("selected ITem:" + selectedItem.getEducation() + "exp ITem:" + selectedItem.getExperience() + "pers ITem:" +  selectedItem.getPersonality() + selectedItem.getJobId() + selectedItem.getCandidate() + selectedItem.getRecruiter());
//        System.out.println(duration.getText() + comments.getText());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        java.sql.Date sqlCurrDate = new java.sql.Date(date.getTime());

        LocalTime localTime = LocalTime.now();
        System.out.println(localTime);
        System.out.println("selected ITem:" + selectedItem.getEducation() + "exp ITem:" + selectedItem.getExperience() + "pers ITem:" + selectedItem.getPersonality() + selectedItem.getJobId() + selectedItem.getCandidate() + selectedItem.getRecruiter() + duration.getText() + comments.getText());


        System.out.println(sqlCurrDate);
        ConnectDB connectDB = new ConnectDB();
        Connection connection = connectDB.getConnection();

        if(selectedItem.getPersonality()>5)
            selectedItem.setPersonality(5);

        if (selectedItem.getPersonality()<0)
            selectedItem.setPersonality(0);

        if(selectedItem.getEducation()>5)
            selectedItem.setEducation(5);

        if (selectedItem.getEducation()<0)
            selectedItem.setEducation(0);


        if(selectedItem.getExperience()>5)
            selectedItem.setExperience(5);

        if (selectedItem.getExperience()<0)
            selectedItem.setExperience(0);




        //call username and password using textFields as values
        String insertInterviewQuerry = "insert into interview (recruiter_username, candidate_username, job_id, int_date, start_time, duration, comments, personality, education, experience) values" +
                "(\"" + selectedItem.getRecruiter() + "\", \"" + selectedItem.getCandidate() + "\"," + selectedItem.getJobId() + ", \""
                + sqlCurrDate + "\", \"" + localTime + "\"," + duration.getText() + ", \"" + comments.getText() + "\"," + selectedItem.getPersonality() + ","
                + selectedItem.getEducation() + "," + selectedItem.getExperience() + ")";


        //Create Statement
        Statement statement = connection.createStatement();
        statement.executeUpdate(insertInterviewQuerry);

        label.setVisible(true);
        label.setText("Interview Updated");
        label.setTextFill(Color.web("#1ce057"));


    }


    //select recruiter.username, job.id, applies.cand_usrname  from recruiter
    // inner join job on recruiter.username = job.recruiter
    // inner join applies on applies.job_id = job.id
    // inner join candidate on candidate.username = applies.cand_usrname
    // inner join candidate as troll on troll.username = applies.cand_usrname where job.recruiter = "Giankost";
    public void initData(String text) throws SQLException {
        username = text;
        int pers, edu, exp;
        pers=edu=exp=-1;

        list = FXCollections.observableArrayList();


        ConnectDB connectDB = new ConnectDB();
        Connection connection = connectDB.getConnection();

        //call username and password using textFields as values
        String getApplications = " select cand_usrname, job.recruiter, applies.job_id, job.submission_date, personality, education, experience from applies " +
                "inner join job on applies.job_id = job.id " +
                "left join interview on applies.job_id = interview.job_id AND cand_usrname = candidate_username where job.recruiter = \"" + username + "\";";


        //Create Statement
        Statement statement = connection.createStatement();


        //Create Result Set'
        ResultSet rs = statement.executeQuery(getApplications);


        while (rs.next()) {
            //rs.getInt(5), rs.getInt(6), rs.getInt(7)
            pers=rs.getInt(5);
            edu=rs.getInt(6);
            exp=rs.getInt(7);

            if(rs.getString(5)==null)
              pers = -1;
            if(rs.getString(6)==null)
              edu=-1;
            if(rs.getString(7)==null)
                exp=-1;

            EvaluateObject ob = new EvaluateObject(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4), pers, edu, exp);
            list.add(ob);
        }


        candidate.setCellValueFactory(new PropertyValueFactory<EvaluateObject, String>("candidate"));
        recruiter.setCellValueFactory(new PropertyValueFactory<EvaluateObject, String>("recruiter"));
        jobId.setCellValueFactory(new PropertyValueFactory<EvaluateObject, Integer>("jobId"));
        personality.setCellValueFactory(new PropertyValueFactory<EvaluateObject, Integer>("personality"));
        experience.setCellValueFactory(new PropertyValueFactory<EvaluateObject, Integer>("experience"));
        education.setCellValueFactory(new PropertyValueFactory<EvaluateObject, Integer>("education"));
        date.setCellValueFactory(new PropertyValueFactory<EvaluateObject, String>("date"));


        table.setItems(list);


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table.setEditable(true);
        recruiter.setCellFactory(TextFieldTableCell.forTableColumn());
        candidate.setCellFactory(TextFieldTableCell.forTableColumn());
        personality.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        jobId.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        education.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        experience.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        date.setCellFactory(TextFieldTableCell.forTableColumn());

        label.setVisible(false);

    }

    public void editPersonality(TableColumn.CellEditEvent<EvaluateObject, Integer> evaluateObjectIntegerCellEditEvent) {
        label.setVisible(false);
        EvaluateObject object = table.getSelectionModel().getSelectedItem();
        int status = object.getEducation();
        object.setPersonality(evaluateObjectIntegerCellEditEvent.getNewValue());
        if (status != -1) {
            try {
                ConnectDB connectDB = new ConnectDB();
                Connection connection = connectDB.getConnection();


                //call username and password using textFields as values
                String updateQuerry = "UPDATE interview set personality = \"" + object.getPersonality() + "\" where recruiter_username =\"" + username + "\" AND job_id = " + object.getJobId();

                if (object.getPersonality() > 5 || object.getPersonality() < 0) {
                    label.setTextFill(Color.web("#ff0000"));
                    label.setVisible(true);
                    label.setText("Can't add that Value! Value Not Updated!");
                    return;
                }
                //Create Statement
                Statement statement = connection.createStatement();
                statement.executeUpdate(updateQuerry)
                ;
            } catch (SQLException ex) {
            }
        }
    }

    public void editEducation(TableColumn.CellEditEvent<EvaluateObject, Integer> evaluateObjectIntegerCellEditEvent) {
        label.setVisible(false);
        EvaluateObject object = table.getSelectionModel().getSelectedItem();
        int status = object.getEducation();
        object.setEducation(evaluateObjectIntegerCellEditEvent.getNewValue());
        if (status != -1) {
            try {
                ConnectDB connectDB = new ConnectDB();
                Connection connection = connectDB.getConnection();


                //call username and password using textFields as values
                String updateQuerry = "UPDATE interview set education = \"" + object.getEducation() + "\" where recruiter_username =\"" + username + "\" AND job_id = " + object.getJobId();

                if (object.getEducation() > 5 || object.getEducation() < 0) {
                    label.setTextFill(Color.web("#ff0000"));
                    label.setVisible(true);
                    label.setText("Can't add that Value! Value Not Updated!");
                    return;
                }
                //Create Statement
                Statement statement = connection.createStatement();
                statement.executeUpdate(updateQuerry)
                ;
            } catch (SQLException ex) {
            }
        }
    }

    public void editExperience(TableColumn.CellEditEvent<EvaluateObject, Integer> evaluateObjectIntegerCellEditEvent) {
        label.setVisible(false);
        EvaluateObject object = table.getSelectionModel().getSelectedItem();
        int status = object.getEducation();
        object.setExperience(evaluateObjectIntegerCellEditEvent.getNewValue());
        if (status != -1) {
            try {
                ConnectDB connectDB = new ConnectDB();
                Connection connection = connectDB.getConnection();


                //call username and password using textFields as values
                String updateQuerry = "UPDATE interview set experience = \"" + object.getExperience() + "\" where recruiter_username =\"" + username + "\" AND job_id = " + object.getJobId();

                if (object.getExperience() > 5 || object.getExperience() < 0) {
                    label.setTextFill(Color.web("#ff0000"));
                    label.setVisible(true);
                    label.setText("Can't add that Value! Value Not Updated!");
                    return;
                }
                //Create Statement
                Statement statement = connection.createStatement();
                statement.executeUpdate(updateQuerry)
                ;
            } catch (SQLException ex) {
            }
        }
    }
}
