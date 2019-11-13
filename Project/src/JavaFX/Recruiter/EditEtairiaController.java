package JavaFX.Recruiter;

import JavaFX.ConnectDB;
import JavaFX.TableObject.EtairiaObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class EditEtairiaController implements Initializable {

    private String username;

    //ArrayList<String> attributes = new ArrayList<>(); //keeps the values of each column of tableview

    @FXML
    private TableView<EtairiaObject> table;

    @FXML
    private TableColumn<EtairiaObject, String> name;

    @FXML
    private TableColumn<EtairiaObject, String> afm;

    @FXML
    private TableColumn<EtairiaObject, String> doy;

    @FXML
    private TableColumn<EtairiaObject, String> phone;

    @FXML
    private TableColumn<EtairiaObject, String> street;

    @FXML
    private TableColumn<EtairiaObject, String> num;

    @FXML
    private TableColumn<EtairiaObject, String> city;

    @FXML
    private TableColumn<EtairiaObject, String> country;


    private ObservableList<EtairiaObject> list;


    public EditEtairiaController() throws SQLException {


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table.setEditable(true);
        phone.setCellFactory(TextFieldTableCell.forTableColumn());
        street.setCellFactory(TextFieldTableCell.forTableColumn());
        num.setCellFactory(TextFieldTableCell.forTableColumn());
        city.setCellFactory(TextFieldTableCell.forTableColumn());
        country.setCellFactory(TextFieldTableCell.forTableColumn());

    }

    public void initData(String text) throws SQLException {
        username = text;
        try {
            list = FXCollections.observableArrayList();

            ConnectDB connectDB = new ConnectDB();
            Connection connection = connectDB.getConnection();

            //call username and password using textFields as values
            String getEtairiaValuesQuerry = " select name, afm, doy, tel, street, num, city, country  from etaireia inner join recruiter on recruiter.firm=etaireia.afm where recruiter.username=\"" + username + "\"";

            //Create Statement
            Statement statement = connection.createStatement();

            //Create Result Set'
            ResultSet rs = statement.executeQuery(getEtairiaValuesQuerry);
            System.out.println("Test");

            while (rs.next()) {
                list.add(new EtairiaObject(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));

            }
        } catch (SQLException ex) {
            Logger.getLogger((EditEtairiaController.class.getName())).log(Level.SEVERE, null, ex);

        }


        name.setCellValueFactory(new PropertyValueFactory<EtairiaObject, String>("Name"));
        afm.setCellValueFactory(new PropertyValueFactory<EtairiaObject, String>("afm"));
        doy.setCellValueFactory(new PropertyValueFactory<EtairiaObject, String>("doy"));
        phone.setCellValueFactory(new PropertyValueFactory<EtairiaObject, String>("Phone"));
        street.setCellValueFactory(new PropertyValueFactory<EtairiaObject, String>("Street"));
        num.setCellValueFactory(new PropertyValueFactory<EtairiaObject, String>("Num"));
        city.setCellValueFactory(new PropertyValueFactory<EtairiaObject, String>("City"));
        country.setCellValueFactory(new PropertyValueFactory<EtairiaObject, String>("Country"));

        table.setItems(list);


    }

    public void onEditCity(TableColumn.CellEditEvent<EtairiaObject, String> etairiaObjectStringCellEditEvent) {
        EtairiaObject object = table.getSelectionModel().getSelectedItem();
        object.setCity(etairiaObjectStringCellEditEvent.getNewValue());
        try {
            ConnectDB connectDB = new ConnectDB();
            Connection connection = connectDB.getConnection();

            //call username and password using textFields as values
            String updateQuerry = "UPDATE etaireia inner join recruiter on recruiter.firm=etaireia.afm set city = \"" + object.getCity().toString() + "\" where recruiter.username=\"" + username + "\"";

            //Create Statement
            Statement statement = connection.createStatement();
            statement.executeUpdate(updateQuerry)
            ;
        } catch (SQLException ex) {
        }

    }

    public void onEditPhone(TableColumn.CellEditEvent<EtairiaObject, String> etairiaObjectStringCellEditEvent) {
        EtairiaObject object = table.getSelectionModel().getSelectedItem();
        object.setPhone(etairiaObjectStringCellEditEvent.getNewValue());
        try {
            ConnectDB connectDB = new ConnectDB();
            Connection connection = connectDB.getConnection();

            //call username and password using textFields as values
            String updateQuerry = "UPDATE etaireia inner join recruiter on recruiter.firm=etaireia.afm set tel = \"" + Integer.parseInt(object.getPhone().toString()) + "\" where recruiter.username=\"" + username + "\"";

            //Create Statement
            Statement statement = connection.createStatement();
            statement.executeUpdate(updateQuerry)
            ;
        } catch (SQLException ex) {
        }

    }

    public void onEditStreet(TableColumn.CellEditEvent<EtairiaObject, String> etairiaObjectStringCellEditEvent) {
        EtairiaObject object = table.getSelectionModel().getSelectedItem();
        object.setStreet(etairiaObjectStringCellEditEvent.getNewValue());
        try {
            ConnectDB connectDB = new ConnectDB();
            Connection connection = connectDB.getConnection();

            //call username and password using textFields as values
            String updateQuerry = "UPDATE etaireia inner join recruiter on recruiter.firm=etaireia.afm set street = \"" + object.getStreet().toString() + "\" where recruiter.username=\"" + username + "\"";

            //Create Statement
            Statement statement = connection.createStatement();
            statement.executeUpdate(updateQuerry)
            ;
        } catch (SQLException ex) {
        }

    }

    public void onEditNum(TableColumn.CellEditEvent<EtairiaObject, String> etairiaObjectStringCellEditEvent) {
        EtairiaObject object = table.getSelectionModel().getSelectedItem();
        object.setNum(etairiaObjectStringCellEditEvent.getNewValue());
        try {
            ConnectDB connectDB = new ConnectDB();
            Connection connection = connectDB.getConnection();

            //call username and password using textFields as values
            String updateQuerry = "UPDATE etaireia inner join recruiter on recruiter.firm=etaireia.afm set num = \"" + Integer.parseInt(object.getNum().toString()) + "\" where recruiter.username=\"" + username + "\"";

            //Create Statement
            Statement statement = connection.createStatement();
            statement.executeUpdate(updateQuerry)
            ;
        } catch (SQLException ex) {
        }

    }

    public void onEditCountry(TableColumn.CellEditEvent<EtairiaObject, String> etairiaObjectStringCellEditEvent) {
        EtairiaObject object = table.getSelectionModel().getSelectedItem();
        object.setCountry(etairiaObjectStringCellEditEvent.getNewValue());
        try {
            ConnectDB connectDB = new ConnectDB();
            Connection connection = connectDB.getConnection();

            //call username and password using textFields as values
            String updateQuerry = "UPDATE etaireia inner join recruiter on recruiter.firm=etaireia.afm set country = \"" + object.getCountry().toString() + "\" where recruiter.username=\"" + username + "\"";

            //Create Statement
            Statement statement = connection.createStatement();
            statement.executeUpdate(updateQuerry)
            ;
        } catch (SQLException ex) {
        }

    }


}
