package JavaFX.Candidate;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import JavaFX.Recruiter.RecruiterController;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CanditateControler {

    private String username;

    @FXML
    private BorderPane borderPane;

    @FXML
    private JFXButton logOutButton;



    @FXML
    void applyForJob(ActionEvent event) {
        Parent root = null;
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("jobApplication.fxml"));
            root = (Parent) fxmlLoader.load();
            JobApplicationController editAccountController = fxmlLoader.getController();
            editAccountController.initData(username);

        } catch (IOException | SQLException | ParseException ex){
            Logger.getLogger(RecruiterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        borderPane.setCenter(root);
    }

    @FXML
    void editAccount(ActionEvent event) {
        Parent root = null;
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("editAccount.fxml"));
            root = (Parent) fxmlLoader.load();
            EditAccountController editAccountController = fxmlLoader.getController();
            editAccountController.initData(username);

        } catch (IOException | SQLException ex){
            Logger.getLogger(RecruiterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        borderPane.setCenter(root);



    }

    @FXML
    void logOut(ActionEvent event) throws IOException {
        Stage candidateWindow = (Stage) logOutButton.getScene().getWindow();
        candidateWindow.close();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../login.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root2));
        stage1.show();
    }

    public void initData(String text){
        username=text;
    }

}
