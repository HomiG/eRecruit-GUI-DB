package JavaFX.Candidate;

import JavaFX.ConnectDB;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.sql.*;


public class EditAccountController {


    @FXML
    private Label username;
    private String usernameText;

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
    private JFXTextField sistatikes;

    @FXML
    private JFXTextField certificates;

    @FXML
    private TextArea bio;

    public EditAccountController() throws SQLException {
    }

    @FXML
    void changeValues(ActionEvent event) throws SQLException {

        if (password.getText().equals(""))
            password.setText(password.getPromptText());
        if (name.getText().equals(""))
            name.setText(name.getPromptText());
        if (surname.getText().equals(""))
            surname.setText(surname.getPromptText());
        if (email.getText().equals(""))
            email.setText(email.getPromptText());
        if (sistatikes.getText().equals(""))
            sistatikes.setText(sistatikes.getPromptText());
        if (certificates.getText().equals(""))
            certificates.setText(certificates.getPromptText());
        if (bio.getText().equals(""))
            bio.setText(bio.getPromptText());
//        if(regDate.getValue().equals(""))
//            System.out.println("ROTLFLASDFLASDLF");

        //Create Connection with the DataBase
        ConnectDB connectDB = new ConnectDB();
        Connection connection = connectDB.getConnection();
        //call username and password using textFields as values
        String updateUserQuerry = "update user set password= " + "\"" + password.getText() + "\"" + ", name= " + "\"" + name.getText() + "\""
                + ", surname= " + "\"" + surname.getText() + "\""
                + ", email= " + "\"" + email.getText() + "\""
                + " where username= " + "\"" + usernameText + "\"" ;

        String updateCandidateQuerry = "update candidate set sistatikes= " + "\"" + sistatikes.getText() + "\""
                + ", certificates= " + "\"" + certificates.getText() + "\""
                + ", bio= " + "\"" + bio.getText() + "\"" + " where username= " + "\"" + usernameText + "\"";


        //Create Statement
        Statement statement = connection.createStatement();
        statement.executeUpdate(updateUserQuerry);
        statement.executeUpdate(updateCandidateQuerry);


    }

    @FXML
    void showInfo(ActionEvent event) throws SQLException {
        //Create Connection with the DataBase
        ConnectDB connectDB = new ConnectDB();
        Connection connection = connectDB.getConnection();
        //call username and password using textFields as values
        String getValuesQuerry = "select * from user inner join candidate on user.username = candidate.username where user.username = \"" + usernameText + "\"";

        //Create Statement
        Statement statement = connection.createStatement();


        //Create Result Set'
        ResultSet rs = statement.executeQuery(getValuesQuerry);
        while (rs.next()) {

            username.setText(rs.getString(1));
            password.setPromptText(rs.getString(2));
            name.setPromptText(rs.getString(3));
            surname.setPromptText(rs.getString(4));
            regDate.setPromptText(rs.getString(5));
            email.setPromptText(rs.getString(6));
            bio.setPromptText(rs.getString(8));
            sistatikes.setPromptText(rs.getString(9));
            certificates.setPromptText(rs.getString(10));
        }
    }


    public void initData(String text) throws SQLException {
        usernameText = text;
        username.setText((text));

        //Create Connection with the DataBase
        ConnectDB connectDB = new ConnectDB();
        Connection connection = connectDB.getConnection();
        //call username and password using textFields as values
        String getValuesQuerry = "select * from user inner join candidate on user.username = candidate.username where user.username = \"" + usernameText + "\"";

        //Create Statement
        Statement statement = connection.createStatement();


        //Create Result Set'
        ResultSet rs = statement.executeQuery(getValuesQuerry);
        while (rs.next()) {
            username.setText(rs.getString(1));
            password.setPromptText(rs.getString(2));
            name.setPromptText(rs.getString(3));
            surname.setPromptText(rs.getString(4));
            regDate.setPromptText(rs.getString(5));
            email.setPromptText(rs.getString(6));
            bio.setPromptText(rs.getString(8));
            sistatikes.setPromptText(rs.getString(9));
            certificates.setPromptText(rs.getString(10));

        }
    }


}
