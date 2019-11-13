package JavaFX.Admin;

import JavaFX.ConnectDB;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class NewRecrAccount {


    @FXML
    private JFXTextField username;

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
    private Label lebel;

    @FXML
    void createAccount(ActionEvent event) throws SQLException {
        ConnectDB connectDB = new ConnectDB();
        ConnectDB connectDB1 = new ConnectDB();
        Connection connection = connectDB.getConnection();
        Connection connection1 = connectDB1.getConnection();

        //call username and password using textFields as values
        String insertUserQuerry = "insert into `user` (username, `password`, `name`, surname, reg_date, email) values" +
                "(\"" + username.getText() + "\", \"" + password.getText() + "\", \"" + name.getText() + "\", \"" + surname.getText() + "\", \""
                + regDate.getValue() + "\", \"" + email.getText() + "\")";

        String insertRecrwQuerry = "insert into recruiter (username, exp_years, firm) values" +
                "(\""+username.getText()+"\","+expYears.getText()+", \""+firm.getText()+"\")";

        //Create Statement
        Statement statement = connection.createStatement();
        Statement statement1 = connection.createStatement();
        statement.executeUpdate(insertUserQuerry);
        statement1.executeUpdate(insertRecrwQuerry);


        lebel.setVisible(true);
        lebel.setTextFill(Color.web("#4BB543"));
        lebel.setText("Account Created Successfully");

    }
}
