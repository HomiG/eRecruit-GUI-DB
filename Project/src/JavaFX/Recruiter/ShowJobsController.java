package JavaFX.Recruiter;

import JavaFX.ConnectDB;
import JavaFX.TableObject.MyJobObject;
import JavaFX.TableObject.ShowJobObject;
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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShowJobsController implements Initializable {
    private String username;

    private ObservableList<ShowJobObject> list;

    @FXML
    private TableView<ShowJobObject> table;

    @FXML
    private TableColumn<ShowJobObject, String> id;

    @FXML
    private TableColumn<ShowJobObject, String> startDate;

    @FXML
    private TableColumn<ShowJobObject, String> salary;

    @FXML
    private TableColumn<ShowJobObject, String> position;

    @FXML
    private TableColumn<ShowJobObject, String> edra;

    @FXML
    private TableColumn<ShowJobObject, String> recruiter;

    @FXML
    private TableColumn<ShowJobObject, String> ancDate;

    @FXML
    private TableColumn<ShowJobObject, String> subDate;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table.setEditable(false);

        id.setCellFactory(TextFieldTableCell.forTableColumn());
        startDate.setCellFactory(TextFieldTableCell.forTableColumn());
        salary.setCellFactory(TextFieldTableCell.forTableColumn());
        position.setCellFactory(TextFieldTableCell.forTableColumn());
        edra.setCellFactory(TextFieldTableCell.forTableColumn());
        recruiter.setCellFactory(TextFieldTableCell.forTableColumn());
        ancDate.setCellFactory(TextFieldTableCell.forTableColumn());
        subDate.setCellFactory(TextFieldTableCell.forTableColumn());


    }


    public void initData(String text) throws SQLException {
        username = text;
        list = FXCollections.observableArrayList();
        //Create Connection with the DataBase
        ConnectDB connectDB = new ConnectDB();
        Connection connection = connectDB.getConnection();

        //Create Statement
        Statement statement = connection.createStatement();

        String getEtairiaId = "Select firm from recruiter where username = " + "\"" + username + "\"";
        String etairiaID="";
        ResultSet rs1 = statement.executeQuery(getEtairiaId);
        while (rs1.next()){
            etairiaID = rs1.getString(1);
            System.out.println(etairiaID);
        }

        String  showAllJobsQuerry = "select * from recruiter inner join job on recruiter.username = job.recruiter where firm = " + "\"" + etairiaID +"\"";



        //Create Result Set'
        ResultSet rs = statement.executeQuery(showAllJobsQuerry);
        while (rs.next()){
            list.add(new ShowJobObject(rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11)));

        }


        id.setCellValueFactory(new PropertyValueFactory<ShowJobObject, String>("id"));
        startDate.setCellValueFactory(new PropertyValueFactory<ShowJobObject, String>("startDate"));
        salary.setCellValueFactory(new PropertyValueFactory<ShowJobObject, String>("salary"));
        position.setCellValueFactory(new PropertyValueFactory<ShowJobObject, String>("position"));
        edra.setCellValueFactory(new PropertyValueFactory<ShowJobObject, String>("edra"));
        recruiter.setCellValueFactory(new PropertyValueFactory<ShowJobObject, String>("recruiter"));
        ancDate.setCellValueFactory(new PropertyValueFactory<ShowJobObject, String>("ancDate"));
        subDate.setCellValueFactory(new PropertyValueFactory<ShowJobObject, String>("subDate"));


        table.setItems(list);
    }


}
