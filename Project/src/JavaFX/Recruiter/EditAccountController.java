package JavaFX.Recruiter;

import JavaFX.ConnectDB;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class EditAccountController implements Initializable {

    private String usernameImport;
    @FXML
    private Label username;

    @FXML
    private JFXTextField password;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField surname;

    @FXML
    private JFXDatePicker regDate;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField expYears;

    @FXML
    private JFXTextField firm;

    @FXML
    private JFXButton showValuesButton;







    @FXML
    void changeValues(ActionEvent event) throws SQLException {


        if(password.getText().equals(""))
            password.setText(password.getPromptText());
        if(name.getText().equals(""))
            name.setText(name.getPromptText());
        if(surname.getText().equals(""))
            surname.setText(surname.getPromptText());
        if(email.getText().equals(""))
            email.setText(email.getPromptText());
        if(expYears.getText().equals(""))
            expYears.setText(expYears.getPromptText());
        if(firm.getText().equals(""))
            firm.setText(firm.getPromptText());
//        if(regDate.getValue().equals(""))
//            System.out.println("ROTLFLASDFLASDLF");


        //Create Connection with the DataBase
        ConnectDB connectDB = new ConnectDB();
        Connection connection = connectDB.getConnection();
        //call username and password using textFields as values
        String  updateUserQuerry = "update user set password= " + "\"" + password.getText() + "\"" + ", name= " + "\"" + name.getText() + "\""
                + ", surname= " + "\"" + surname.getText() + "\""
                + ", email= " + "\"" + email.getText() + "\"" + "where username= " + "\"" + usernameImport + "\"" ;

        String updateCandidateQuerry = "update recruiter set exp_years= " + "\"" + expYears.getText() + "\""
                + ", firm= " + "\"" + firm.getText() + "\""
                + "where username= " + "\"" + usernameImport + "\"" ;



        //Create Statement
        Statement statement = connection.createStatement();
        statement.executeUpdate(updateUserQuerry);
        statement.executeUpdate(updateCandidateQuerry);

    }

    @FXML
    void showValues(ActionEvent event) throws SQLException {
        //Create Connection with the DataBase
        ConnectDB connectDB = new ConnectDB();
        Connection connection = connectDB.getConnection();
        //call username and password using textFields as values
        String  getValuesQuerry = "select * from user inner join recruiter on user.username = recruiter.username where user.username = \"" + usernameImport + "\"";

        //Create Statement
        Statement statement = connection.createStatement();


        //Create Result Set'
        ResultSet rs = statement.executeQuery(getValuesQuerry);
        while (rs.next()){

            username.setText(rs.getString(1));
            password.setPromptText(rs.getString(2));
            name.setPromptText(rs.getString(3));
            surname.setPromptText(rs.getString(4));
            regDate.setPromptText(rs.getString(5));
            email.setPromptText(rs.getString(6));
            expYears.setPromptText(rs.getString(8));
            firm.setPromptText(rs.getString(9));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {

    }

    public void initData(String text) throws SQLException {
        usernameImport=text;
        username.setText(text);

        //Create Connection with the DataBase
        ConnectDB connectDB = new ConnectDB();
        Connection connection = connectDB.getConnection();
        //call username and password using textFields as values
        String  getValuesQuerry = "select * from user inner join recruiter on user.username = recruiter.username where user.username = \"" + usernameImport + "\"";

        //Create Statement
        Statement statement = connection.createStatement();


        //Create Result Set'
        ResultSet rs = statement.executeQuery(getValuesQuerry);
        while (rs.next()){

            username.setText(rs.getString(1));
            password.setPromptText(rs.getString(2));
            name.setPromptText(rs.getString(3));
            surname.setPromptText(rs.getString(4));
            regDate.setPromptText(rs.getString(5));
            email.setPromptText(rs.getString(6));
            expYears.setPromptText(rs.getString(8));
            firm.setPromptText(rs.getString(9));
        }
    }

}
