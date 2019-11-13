package JavaFX.Recruiter;

import JavaFX.ConnectDB;
import JavaFX.TableObject.EvaluateObject;
import JavaFX.TableObject.FailObject;
import JavaFX.TableObject.MyJobObject;
import JavaFX.TableObject.SuccessfulObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.security.MessageDigest;
import java.sql.*;
import java.util.ResourceBundle;

public class StoredProcedureController implements Initializable {

    private int jobIdForStoredProcedure;

    private ObservableList<SuccessfulObject> listSuccess;
    private ObservableList<FailObject> listFail;


    @FXML
    private TableView<SuccessfulObject> table;

    @FXML
    private TableColumn<SuccessfulObject, String> candidate;

    @FXML
    private TableColumn<SuccessfulObject, Integer> personality;

    @FXML
    private TableColumn<SuccessfulObject, Integer> education;

    @FXML
    private TableColumn<SuccessfulObject, Integer> experience;

    @FXML
    private TableColumn<SuccessfulObject, Integer> totalScore;

    @FXML
    private TableColumn<SuccessfulObject, Integer> interviews;


    @FXML
    private TableView<FailObject> tableF;

    @FXML
    private TableColumn<FailObject, String> candidateF;

    @FXML
    private TableColumn<FailObject, String> messageF;















    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        candidate.setCellFactory(TextFieldTableCell.forTableColumn());
        personality.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        education.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        experience.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        totalScore.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        interviews.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));


        candidateF.setCellFactory(TextFieldTableCell.forTableColumn());
        messageF.setCellFactory(TextFieldTableCell.forTableColumn());


    }


    void initData(int id) throws SQLException {

        listSuccess = FXCollections.observableArrayList();
        listFail = FXCollections.observableArrayList();


        jobIdForStoredProcedure=id;

        ConnectDB connectDB = new ConnectDB();
        Connection connection = connectDB.getConnection();

        //call username and password using textFields as values
        String callStoredProcedure = "{CALL proc(?)}";

        //Create Statement
        CallableStatement statement = connection.prepareCall(callStoredProcedure);

        statement.setInt(1,jobIdForStoredProcedure);

        ResultSet rs = statement.executeQuery();



        while (rs.next()){

            SuccessfulObject ob = new SuccessfulObject(rs.getString(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(6),rs.getInt(5));
            listSuccess.add(ob);
            //System.out.println(rs.getString(1) + " " + rs.getInt(2) + " " + rs.getInt(3)+ " " + rs.getInt(4)+ " " + rs.getInt(5)+ " " + rs.getInt(6));
        }

        statement.getMoreResults();
        ResultSet rs1 = statement.getResultSet();
        while (rs1.next()){

            FailObject ob = new FailObject(rs1.getString(1),rs1.getString(2));
            listFail.add(ob);
            //   System.out.println(rs1.getString(1) + " " + rs1.getString(2));
        }

        statement.getMoreResults();
        ResultSet rs2 = statement.getResultSet();
        while (rs2.next()){

            FailObject ob = new FailObject(rs2.getString(1), "Under Assessment");
            listFail.add(ob);
            //   System.out.println(rs1.getString(1) + " " + rs1.getString(2));
        }



        candidate.setCellValueFactory(new PropertyValueFactory<SuccessfulObject, String>("candidate"));
        personality.setCellValueFactory(new PropertyValueFactory<SuccessfulObject, Integer>("personality"));
        education.setCellValueFactory(new PropertyValueFactory<SuccessfulObject, Integer>("education"));
        experience.setCellValueFactory(new PropertyValueFactory<SuccessfulObject, Integer>("experience"));
        totalScore.setCellValueFactory(new PropertyValueFactory<SuccessfulObject, Integer>("totalScore"));
        interviews.setCellValueFactory(new PropertyValueFactory<SuccessfulObject, Integer>("totalInterviews"));
        table.setItems(listSuccess);


        candidateF.setCellValueFactory(new PropertyValueFactory<FailObject, String>("candidate"));
        messageF.setCellValueFactory(new PropertyValueFactory<FailObject, String>("message"));

        tableF.setItems(listFail);


    }
}
