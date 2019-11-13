package JavaFX.Admin;

import JavaFX.Recruiter.ShowJobsController;
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
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminController {


    @FXML
    private BorderPane borderPane;

    @FXML
    private JFXButton logOutButton;

    @FXML
    void actionHistory(ActionEvent event) {
        Parent root = null;
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("actionHistory.fxml"));
            root = (Parent) fxmlLoader.load();

            ActionHistory editAccountController = fxmlLoader.getController();
            editAccountController.initData();
        } catch (IOException | SQLException ex){
            Logger.getLogger(RecruiterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        borderPane.setCenter(root);
    }

    @FXML
    void logOut(ActionEvent event) throws IOException {
        Stage adminWindow = (Stage) logOutButton.getScene().getWindow();
        adminWindow.close();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../login.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root2));
        stage1.show();
    }

    @FXML
    void newAntikeimeno(ActionEvent event) {
        Parent root = null;
        try{
            root = FXMLLoader.load(getClass().getResource("newAntikeim.fxml"));
        } catch (IOException ex){
            Logger.getLogger(RecruiterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        borderPane.setCenter(root);
    }

    @FXML
    void newCandAccount(ActionEvent event) {
        Parent root = null;
        try{
            root = FXMLLoader.load(getClass().getResource("newCandAccount.fxml"));
        } catch (IOException ex){
            Logger.getLogger(RecruiterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        borderPane.setCenter(root);
    }

    @FXML
    void newRecrAccount(ActionEvent event) {
        Parent root = null;
        try{
            root = FXMLLoader.load(getClass().getResource("newRecrAccount.fxml"));
        } catch (IOException ex){
            Logger.getLogger(RecruiterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        borderPane.setCenter(root);
    }

}
