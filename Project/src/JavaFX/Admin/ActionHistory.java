package JavaFX.Admin;

import JavaFX.ConnectDB;
import JavaFX.TableObject.ActionHistoryObject;
import JavaFX.TableObject.ShowJobObject;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class ActionHistory implements Initializable {
    @FXML
    private TableView<ActionHistoryObject> table;

    @FXML
    private TableColumn<ActionHistoryObject, String> user;

    @FXML
    private TableColumn<ActionHistoryObject, String> action;

    @FXML
    private TableColumn<ActionHistoryObject, String> date;

    @FXML
    private TableColumn<ActionHistoryObject, String> time;

    @FXML
    private TableColumn<ActionHistoryObject, String> onTable;

    @FXML
    private TableColumn<ActionHistoryObject, String> status;

    private ObservableList<ActionHistoryObject> list;


    @FXML
    private JFXDatePicker from;

    @FXML
    private JFXDatePicker until;

    @FXML
    private JFXTextField tableTextField;
    @FXML
    private JFXTextField userTextField;


    @FXML
    void refresh(ActionEvent event) throws SQLException {

        list = FXCollections.observableArrayList();

        //Create Connection with the DataBase
        ConnectDB connectDB = new ConnectDB();
        Connection connection = connectDB.getConnection();
        //call username and password using textFields as values
        String istorikoQuerry;
        if (tableTextField.getText().equals("")) {
            istorikoQuerry = "SELECT * FROM istoriko WHERE who = " + "\"" + userTextField.getText() + "\"" + "AND ist_date BETWEEN " + "'" + from.getValue() + "'" + " AND " + "'" + until.getValue() + "'";
        } else if (userTextField.getText().equals("")) {
            istorikoQuerry = "SELECT * FROM istoriko WHERE table_nam = " + "\"" + tableTextField.getText()  + "\"" + "AND ist_date BETWEEN " + "'" + from.getValue() + "'" + " AND " + "'" + until.getValue() + "'";
        } else {
            istorikoQuerry = "SELECT * FROM istoriko WHERE who = " + "\"" + userTextField.getText() + "\"" + "AND table_nam = " + "\"" + tableTextField.getText() + "\""  + " AND ist_date BETWEEN " + "'" + from.getValue() + "'" + " AND " + "'" + until.getValue() + "'";

        }

        Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery(istorikoQuerry);
        while (rs.next()) {
            ActionHistoryObject ob = new ActionHistoryObject(rs.getString(2), rs.getString(5), rs.getString(3), rs.getString(4), rs.getString(6), rs.getString(7));
            list.add(ob);
        }


        user.setCellValueFactory(new PropertyValueFactory<ActionHistoryObject, String>("user"));
        action.setCellValueFactory(new PropertyValueFactory<ActionHistoryObject, String>("action"));
        date.setCellValueFactory(new PropertyValueFactory<ActionHistoryObject, String>("date"));
        time.setCellValueFactory(new PropertyValueFactory<ActionHistoryObject, String>("time"));
        onTable.setCellValueFactory(new PropertyValueFactory<ActionHistoryObject, String>("onTable"));
        status.setCellValueFactory(new PropertyValueFactory<ActionHistoryObject, String>("status"));


        table.setItems(list);


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user.setCellFactory(TextFieldTableCell.forTableColumn());
        action.setCellFactory(TextFieldTableCell.forTableColumn());
        date.setCellFactory(TextFieldTableCell.forTableColumn());
        time.setCellFactory(TextFieldTableCell.forTableColumn());
        onTable.setCellFactory(TextFieldTableCell.forTableColumn());
        status.setCellFactory(TextFieldTableCell.forTableColumn());
    }


    public void initData() throws SQLException {


        list = FXCollections.observableArrayList();

        //Create Connection with the DataBase
        ConnectDB connectDB = new ConnectDB();
        Connection connection = connectDB.getConnection();
        //call username and password using textFields as values
        String istorikoQuerry = "SELECT * FROM istoriko";
        Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery(istorikoQuerry);
        while (rs.next()) {
            ActionHistoryObject ob = new ActionHistoryObject(rs.getString(2), rs.getString(5), rs.getString(3), rs.getString(4), rs.getString(6), rs.getString(7));
            list.add(ob);
        }


        user.setCellValueFactory(new PropertyValueFactory<ActionHistoryObject, String>("user"));
        action.setCellValueFactory(new PropertyValueFactory<ActionHistoryObject, String>("action"));
        date.setCellValueFactory(new PropertyValueFactory<ActionHistoryObject, String>("date"));
        time.setCellValueFactory(new PropertyValueFactory<ActionHistoryObject, String>("time"));
        onTable.setCellValueFactory(new PropertyValueFactory<ActionHistoryObject, String>("onTable"));
        status.setCellValueFactory(new PropertyValueFactory<ActionHistoryObject, String>("status"));


        table.setItems(list);


    }


}
