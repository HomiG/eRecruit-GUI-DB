package JavaFX;

import JavaFX.Candidate.CanditateControler;
import JavaFX.Recruiter.EditAccountController;
import JavaFX.Recruiter.RecruiterController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {

    @FXML
    private JFXButton logInButton;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXTextField username;

    @FXML
    private Label logInFail;


    @FXML
    void exit(ActionEvent event) {
        Stage logInWindow = (Stage) logInButton.getScene().getWindow();
        logInWindow.close();
    }


    // Click Log in button, to log in and hide the log in window for possibly later use
    public void logInAction(ActionEvent actionEvent) throws IOException, SQLException {


        String usernameText = username.getText();
        String passwordText = password.getText();
        boolean found = false;


        //Create Connection with the DataBase
        ConnectDB connectDB = new ConnectDB();
        Connection connection = connectDB.getConnection();
        //call username and password using textFields as values
        String logInQuerry = "select user.username, user.password from user where username = \"" + usernameText + "\"" + "AND password = \"" + passwordText + "\"";


        //Create Statement
        Statement statement = connection.createStatement();



        statement.executeUpdate("INSERT INTO loginHistory(username) VALUES (" + "\"" +username.getText() + "\"" + ")");


        //Create Result Set'
        ResultSet rs = statement.executeQuery(logInQuerry);
        //Iterate User table to find if user name exists

        //Check if resultSet is empty
        if (!rs.isBeforeFirst()) {
            System.out.println("No data");
            logInFail.setText("Login FAILED!");
            logInFail.setVisible(true);
        } else {
            while (rs.next()) {

                //Recruiter Log In.

                String findAccountTypeQuerry = "select user.username, user.password from user inner join recruiter on user.username = recruiter.username where user.username = \"" + usernameText + "\"";
                rs = statement.executeQuery(findAccountTypeQuerry);

                while (rs.next()) {
                    try {

                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Recruiter/recruiterWindow.fxml"));
                        Parent root1 = (Parent) fxmlLoader.load();

                        RecruiterController editAccountController = fxmlLoader.getController();
                        editAccountController.initData(username.getText());

                        Stage stage = new Stage();
                        stage.setTitle("Recruiter");
                        stage.setScene(new Scene(root1));
                        stage.show();
                        Stage logInWindow = (Stage) logInButton.getScene().getWindow();
                        logInWindow.close();
                        found = true;
                    } catch (Exception ex) {
                        Logger.getLogger(EditAccountController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                if (found)
                    break;

                //CANDIDATE LOG IN
                findAccountTypeQuerry = "select user.username, user.password from user inner join candidate on user.username = candidate.username where user.username = \"" + usernameText + "\"";
                rs = statement.executeQuery(findAccountTypeQuerry);

                while (rs.next()) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Candidate/candidateWindow.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    CanditateControler editAccountController = fxmlLoader.getController();
                    editAccountController.initData(username.getText());
                    Stage stage = new Stage();
                    stage.setTitle("Candidate");
                    stage.setScene(new Scene(root1));
                    stage.show();
                    Stage logInWindow = (Stage) logInButton.getScene().getWindow();
                    logInWindow.close();
                    found = true;
                }
                if (found)
                    break;

                // Admin Log In
                findAccountTypeQuerry = "select user.username, user.password from user inner join admin on user.username = admin.username where user.username = \"" + usernameText + "\"";
                rs = statement.executeQuery(findAccountTypeQuerry);

                while (rs.next()) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Admin/adminWindow.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                    stage.show();
                    Stage logInWindow = (Stage) logInButton.getScene().getWindow();
                    logInWindow.close();
                    found = true;
                }
            }


        }


    }
}
