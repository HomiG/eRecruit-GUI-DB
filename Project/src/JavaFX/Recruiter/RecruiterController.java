package JavaFX.Recruiter;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RecruiterController implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private JFXButton logOutButton;
    @FXML
    private JFXButton evaluateInterviewButton;

    private String username;


    @FXML
    void editEtairia(ActionEvent event){
        Parent root = null;
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("editEtairia.fxml"));
            root = (Parent) fxmlLoader.load();

            EditEtairiaController editAccountController = fxmlLoader.getController();
            editAccountController.initData(username);

        } catch (IOException | SQLException ex){
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
    void insertJob(ActionEvent event) {
        Parent root = null;
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addJob.fxml"));
            root = (Parent) fxmlLoader.load();

            AddJobController editAccountController = fxmlLoader.getController();
            editAccountController.initData(username);
        } catch (IOException | SQLException ex){
            Logger.getLogger(RecruiterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        borderPane.setCenter(root);
    }

    @FXML
    void logOut(ActionEvent event) throws IOException {
        Stage leftSideWindow = (Stage) logOutButton.getScene().getWindow();
        leftSideWindow.close();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../login.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    void showJobs(ActionEvent event) throws IOException {
        Parent root = null;
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("showJobs.fxml"));
            root = (Parent) fxmlLoader.load();

            ShowJobsController editAccountController = fxmlLoader.getController();
            editAccountController.initData(username);

        } catch (IOException | SQLException ex){
            Logger.getLogger(RecruiterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        borderPane.setCenter(root);
    }

    @FXML
    void myJobs(ActionEvent event) {
        Parent root = null;
        try{

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("myJobs.fxml"));
            root = (Parent) fxmlLoader.load();

            MyJobsController editAccountController = fxmlLoader.getController();
            editAccountController.initData(username);

        } catch (IOException | SQLException ex){
            Logger.getLogger(RecruiterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        borderPane.setCenter(root);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void initData(String text) {
        username=text;
        System.out.println("Recruiter controller: " + username);

    }

}



