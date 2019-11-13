package JavaFX.Candidate;
import JavaFX.ConnectDB;
import JavaFX.TableObject.ApplicationObject;
import JavaFX.TableObject.MyJobObject;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.paint.Color;
import javafx.util.converter.IntegerStringConverter;
import java.net.URL;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;



public class JobApplicationController implements Initializable {

    private String username;
    private int jobId;

    private ObservableList<ApplicationObject> list;




    @FXML
    private TableView<ApplicationObject> table;

    @FXML
    private TableColumn<ApplicationObject, Integer> id;

    @FXML
    private TableColumn<ApplicationObject, String> job;

    @FXML
    private TableColumn<ApplicationObject, String> applied;

    @FXML
    private TableColumn<ApplicationObject, String> status;

    @FXML
    private JFXButton button;

    @FXML
    void buttonAction(ActionEvent event) throws SQLException, ParseException {
        if(button.getText().equals("UNAVAILABLE")){
            return;
        }
        ConnectDB connectDB = new ConnectDB();
        Connection connection = connectDB.getConnection();
        Statement statement = connection.createStatement();
        Statement statement1 = connection.createStatement();

        String deleteQuerry = "DELETE FROM applies WHERE applies.cand_usrname = " + "\"" + username +  "\" " + " AND applies.job_id = " + jobId;
        String applyQuerry = "INSERT INTO applies VALUES (" + "\"" + username + "\", " + jobId + ")"  ;


        if(button.getText().equals("DELETE")){
                statement.executeUpdate(deleteQuerry);
        }

        if(button.getText().equals("APPLY")){
            statement1.executeUpdate(applyQuerry);
        }

        initData(username);
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        job.setCellFactory(TextFieldTableCell.forTableColumn());
        applied.setCellFactory(TextFieldTableCell.forTableColumn());
        status.setCellFactory(TextFieldTableCell.forTableColumn());
        id.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));


// Add a listener to print the selected item to console when selected
        table.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                jobId = newValue.getId();
                if(newValue.getApplied().equals("NO")) {
                    if(newValue.getStatus().equals("applications are closed")){
                        button.setText("UNAVAILABLE");
                        button.setStyle("-fx-background-color: BLACK;");
                    } else {
                        button.setText("APPLY");
                        button.setStyle("-fx-background-color: #145bcc;");
                    }
                }
                if (newValue.getApplied().equals("YES")) {
                    button.setText("DELETE");
                    button.setStyle("-fx-background-color: #E65B65;");

                }
            }
        });

    }

    public void initData(String text) throws SQLException, ParseException {
        username = text;

        String jobString;
        int jobId;
        String appliedString;
        String statusString="---";
        String ac;



        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        java.sql.Date sqlCurrDate = new java.sql.Date(date.getTime());

        list = FXCollections.observableArrayList();

        ConnectDB connectDB = new ConnectDB();
        Connection connection = connectDB.getConnection();


        //call username and password using textFields as values
        //String applicationQuerry = "select candidate.username, job.id, job.position, job.submission_date from candidate inner join applies on candidate.username=applies.cand_usrname inner join job on applies.job_id=job.id where username=\"" + username + "\";";
        String jobsQuerry = "select position, submission_date, id from job";
        //Create Statement
        Statement statement = connection.createStatement();
        Statement statement1 = connection.createStatement();
        Statement statement2 = connection.createStatement();

        //Create Result Set'
        ResultSet rs = statement.executeQuery(jobsQuerry);
        ResultSet rs1;
        ResultSet rs2;

        while (rs.next()) {
            boolean found=false;
            jobId=rs.getInt(3);
            jobString = rs.getString(1); //get job name
            rs1 = statement1.executeQuery(" select * from applies where cand_usrname=\""+username+"\" AND job_id=" + rs.getInt(3));
            if(rs1.next()==false){
                appliedString="NO";
            }else {
                do {
                    appliedString="YES";
                } while (rs1.next());
            }

            rs2 = statement2.executeQuery("select submission_date from job where id=" + rs.getInt(3));
            while (rs2.next()) {

                if(appliedString=="YES"){

                    String callStoredProcedure = "{CALL proc(?)}";
                    CallableStatement statement3 = connection.prepareCall(callStoredProcedure);
                    statement3.setInt(1, rs.getInt(3));

                    ResultSet rsPro = statement3.executeQuery();

                    int position=0;
                    String user;
                    while(rsPro.next()){
                        position++;
                        user=rsPro.getString(1);
                        if(user.equals(username)) {
                            found=true;
                            statusString="Your position is: " + position;

                        }
                    }
                    statusString=statusString+" out of " + position;
                    if(found==false){
                        statusString="Under Assessment";
                    }
                }
                else {
                    java.sql.Date subDate = rs2.getDate(1);
                    if (sqlCurrDate.after(subDate)) {
                        statusString = "applications are closed";
                    } else {
                        statusString = "Open for Submission";

                    }
                }
            }


            ApplicationObject ob = new ApplicationObject(jobString, appliedString, statusString, jobId);
            list.add(ob);

        }


        job.setCellValueFactory(new PropertyValueFactory<ApplicationObject, String>("job"));
        id.setCellValueFactory(new PropertyValueFactory<ApplicationObject, Integer>("id"));
        applied.setCellValueFactory(new PropertyValueFactory<ApplicationObject, String>("applied"));
        status.setCellValueFactory(new PropertyValueFactory<ApplicationObject, String>("status"));


        table.setItems(list);

    }
}
