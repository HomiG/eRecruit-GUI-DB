package JavaFX.Admin;

import JavaFX.ConnectDB;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class NewCandAccount {


    @FXML
    private Label label;


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
    private JFXTextField sistakes;

    @FXML
    private JFXTextField certificates;

    @FXML
    private TextArea bio;

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


        String insertCandQuerry = "insert into candidate(username, sistatikes, certificates, bio) values" +
                "(\"" + username.getText() + "\"," + "\"" + sistakes.getText() + "\"" + ", \"" + certificates.getText() + "\", " + "\"" + bio.getText() + "\")";


        //Create Statement
        Statement statement = connection.createStatement();
        Statement statement1 = connection.createStatement();
        statement.executeUpdate(insertUserQuerry);
        statement1.executeUpdate(insertCandQuerry);


        label.setVisible(true);
        label.setTextFill(Color.web("#4BB543"));
        label.setText("Account Created Successfully");

    }

}
