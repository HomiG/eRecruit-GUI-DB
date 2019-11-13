package JavaFX.Recruiter;

import JavaFX.ConnectDB;
import JavaFX.TableObject.EtairiaObject;
import JavaFX.TableObject.MyJobObject;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.IllegalCharsetNameException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyJobsController implements Initializable {

    private String username;

    private ObservableList<MyJobObject> list;
    @FXML
    private JFXCheckBox checkBox;

    @FXML
    private TableView<MyJobObject> table;

    @FXML
    private TableColumn<MyJobObject, String> id;

    @FXML
    private TableColumn<MyJobObject, String> startDate;

    @FXML
    private TableColumn<MyJobObject, String> salary;

    @FXML
    private TableColumn<MyJobObject, String> position;

    @FXML
    private TableColumn<MyJobObject, String> edra;

    @FXML
    private TableColumn<MyJobObject, String> recruiter;

    @FXML
    private TableColumn<MyJobObject, String> ancDate;

    @FXML
    private TableColumn<MyJobObject, String> subDate;
    @FXML
    private TableColumn<MyJobObject, Integer> count;


    @FXML
    private JFXTextField storedProcedureId;


    @FXML
    private JFXButton evaluateInterviewButton;


    @FXML
    void storedProcedure(ActionEvent event) throws IOException, SQLException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("storedProcedure.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();

        StoredProcedureController editAccountController = fxmlLoader.getController();
        editAccountController.initData(Integer.parseInt(storedProcedureId.getText()));

        Stage stage = new Stage();
        stage.setTitle("Interviews");
        stage.setScene(new Scene(root1));
        stage.show();

    }

    @FXML
    void evaluateInterviews(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("evaluateInterview.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root1));
        stage1.show();

        EvaluateController editAccountController = fxmlLoader.getController();
        editAccountController.initData(username);

    }

    @FXML
    void deleteJob(ActionEvent event) throws SQLException {
        MyJobObject selectedItem = table.getSelectionModel().getSelectedItem();
        table.getItems().remove(selectedItem);
        ConnectDB connectDB = new ConnectDB();
        Connection connection = connectDB.getConnection();

        //call username and password using textFields as values
        String updateQuerry = "DELETE FROM job where id= " + "\"" + selectedItem.getId() + "\"";

        //Create Statement
        Statement statement = connection.createStatement();
        statement.executeUpdate(updateQuerry);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        id.setCellFactory(TextFieldTableCell.forTableColumn());
        startDate.setCellFactory(TextFieldTableCell.forTableColumn());
        salary.setCellFactory(TextFieldTableCell.forTableColumn());
        position.setCellFactory(TextFieldTableCell.forTableColumn());
        edra.setCellFactory(TextFieldTableCell.forTableColumn());
        recruiter.setCellFactory(TextFieldTableCell.forTableColumn());
        ancDate.setCellFactory(TextFieldTableCell.forTableColumn());
        subDate.setCellFactory(TextFieldTableCell.forTableColumn());
        count.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));


        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = new java.util.Date();
        java.sql.Date sqlCurrDate = new java.sql.Date(date.getTime());


        //LISTENER FOR CHECKBOX
        checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                // TODO Auto-generated method stub
                if (newValue) {

                    try {
                        list = FXCollections.observableArrayList();

                        ConnectDB connectDB = new ConnectDB();
                        ConnectDB connectDB1 = new ConnectDB();
                        Connection connection = connectDB.getConnection();
                        Connection connection1 = connectDB1.getConnection();

                        System.out.println(sqlCurrDate);
                        //call username and password using textFields as values
                        String getMyJobsQueery = "select * from job where recruiter = \"" + username + "\"" + "AND submission_date < " + "'" + sqlCurrDate + "'";

                        //Create Statement
                        Statement statement = connection.createStatement();
                        Statement statement1 = connection1.createStatement(); //to get count


                        //Create Result Set'
                        ResultSet rs = statement.executeQuery(getMyJobsQueery);
                        System.out.println("Test");

                        while (rs.next()) {
                            MyJobObject ob = new MyJobObject(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), 0);
                            //String getCountQuerry = "select job_id, count(*) from applies where job_id=" + ob.getId() + " group by job_id";
                            String getCountQuerry = "select applies.job_id, count(*) from applies left join interview on applies.job_id = interview.job_id AND applies.cand_usrname = interview.candidate_username where applies.job_id = " + ob.getId() + " group by applies.job_id";
                            //select applies.job_id, count(*) from applies left join interview on applies.job_id = interview.job_id AND applies.cand_usrname = interview.candidate_username group by applies.job_id
                            ResultSet rs1 = statement1.executeQuery(getCountQuerry);
                            while (rs1.next()) {
                                ob.setCount(Integer.parseInt(rs1.getString(2)));
                            }

                            list.add(ob);

                        }
                    } catch (SQLException ex) {
                        Logger.getLogger((EditEtairiaController.class.getName())).log(Level.SEVERE, null, ex);

                    }


                    id.setCellValueFactory(new PropertyValueFactory<MyJobObject, String>("id"));
                    startDate.setCellValueFactory(new PropertyValueFactory<MyJobObject, String>("startDate"));
                    salary.setCellValueFactory(new PropertyValueFactory<MyJobObject, String>("salary"));
                    position.setCellValueFactory(new PropertyValueFactory<MyJobObject, String>("position"));
                    edra.setCellValueFactory(new PropertyValueFactory<MyJobObject, String>("edra"));
                    recruiter.setCellValueFactory(new PropertyValueFactory<MyJobObject, String>("recruiter"));
                    ancDate.setCellValueFactory(new PropertyValueFactory<MyJobObject, String>("ancDate"));
                    subDate.setCellValueFactory(new PropertyValueFactory<MyJobObject, String>("subDate"));
                    count.setCellValueFactory(new PropertyValueFactory<MyJobObject, Integer>("count"));


                    table.setItems(list);



                } else {

                    try {
                        list = FXCollections.observableArrayList();

                        ConnectDB connectDB = new ConnectDB();
                        ConnectDB connectDB1 = new ConnectDB();
                        Connection connection = connectDB.getConnection();
                        Connection connection1 = connectDB1.getConnection();

                        //call username and password using textFields as values
                        String getMyJobsQueery = "select * from job where recruiter = \"" + username + "\"";

                        //Create Statement
                        Statement statement = connection.createStatement();
                        Statement statement1 = connection1.createStatement(); //to get count


                        //Create Result Set'
                        ResultSet rs = statement.executeQuery(getMyJobsQueery);
                        System.out.println("Test");

                        while (rs.next()) {
                            MyJobObject ob = new MyJobObject(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), 0);
                            //String getCountQuerry = "select job_id, count(*) from applies where job_id=" + ob.getId() + " group by job_id";
                            String getCountQuerry = "select applies.job_id, count(*) from applies left join interview on applies.job_id = interview.job_id AND applies.cand_usrname = interview.candidate_username where applies.job_id = " + ob.getId() + " group by applies.job_id";
                            //select applies.job_id, count(*) from applies left join interview on applies.job_id = interview.job_id AND applies.cand_usrname = interview.candidate_username group by applies.job_id
                            ResultSet rs1 = statement1.executeQuery(getCountQuerry);
                            while (rs1.next()) {
                                ob.setCount(Integer.parseInt(rs1.getString(2)));
                            }

                            list.add(ob);

                        }
                    } catch (SQLException ex) {
                        Logger.getLogger((EditEtairiaController.class.getName())).log(Level.SEVERE, null, ex);

                    }
                    id.setCellValueFactory(new PropertyValueFactory<MyJobObject, String>("id"));
                    startDate.setCellValueFactory(new PropertyValueFactory<MyJobObject, String>("startDate"));
                    salary.setCellValueFactory(new PropertyValueFactory<MyJobObject, String>("salary"));
                    position.setCellValueFactory(new PropertyValueFactory<MyJobObject, String>("position"));
                    edra.setCellValueFactory(new PropertyValueFactory<MyJobObject, String>("edra"));
                    recruiter.setCellValueFactory(new PropertyValueFactory<MyJobObject, String>("recruiter"));
                    ancDate.setCellValueFactory(new PropertyValueFactory<MyJobObject, String>("ancDate"));
                    subDate.setCellValueFactory(new PropertyValueFactory<MyJobObject, String>("subDate"));
                    count.setCellValueFactory(new PropertyValueFactory<MyJobObject, Integer>("count"));


                    table.setItems(list);


                }
            }
        });

    }


    public void initData(String text) throws SQLException {
        username = text;
        try {
            list = FXCollections.observableArrayList();

            ConnectDB connectDB = new ConnectDB();
            ConnectDB connectDB1 = new ConnectDB();
            Connection connection = connectDB.getConnection();
            Connection connection1 = connectDB1.getConnection();

            //call username and password using textFields as values
            String getMyJobsQueery = "select * from job where recruiter = \"" + username + "\"";

            //Create Statement
            Statement statement = connection.createStatement();
            Statement statement1 = connection1.createStatement(); //to get count


            //Create Result Set'
            ResultSet rs = statement.executeQuery(getMyJobsQueery);
            System.out.println("Test");

            while (rs.next()) {
                MyJobObject ob = new MyJobObject(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), 0);
                //String getCountQuerry = "select job_id, count(*) from applies where job_id=" + ob.getId() + " group by job_id";
                String getCountQuerry = "select applies.job_id, count(*) from applies left join interview on applies.job_id = interview.job_id AND applies.cand_usrname = interview.candidate_username where applies.job_id = " + ob.getId() + " group by applies.job_id";
                //select applies.job_id, count(*) from applies left join interview on applies.job_id = interview.job_id AND applies.cand_usrname = interview.candidate_username group by applies.job_id
                ResultSet rs1 = statement1.executeQuery(getCountQuerry);
                while (rs1.next()) {
                    ob.setCount(Integer.parseInt(rs1.getString(2)));
                }

                list.add(ob);

            }
        } catch (SQLException ex) {
            Logger.getLogger((EditEtairiaController.class.getName())).log(Level.SEVERE, null, ex);

        }


        id.setCellValueFactory(new PropertyValueFactory<MyJobObject, String>("id"));
        startDate.setCellValueFactory(new PropertyValueFactory<MyJobObject, String>("startDate"));
        salary.setCellValueFactory(new PropertyValueFactory<MyJobObject, String>("salary"));
        position.setCellValueFactory(new PropertyValueFactory<MyJobObject, String>("position"));
        edra.setCellValueFactory(new PropertyValueFactory<MyJobObject, String>("edra"));
        recruiter.setCellValueFactory(new PropertyValueFactory<MyJobObject, String>("recruiter"));
        ancDate.setCellValueFactory(new PropertyValueFactory<MyJobObject, String>("ancDate"));
        subDate.setCellValueFactory(new PropertyValueFactory<MyJobObject, String>("subDate"));
        count.setCellValueFactory(new PropertyValueFactory<MyJobObject, Integer>("count"));


        table.setItems(list);

    }


    public void startDateEdit(TableColumn.CellEditEvent<MyJobObject, String> myJobObjectStringCellEditEvent) {
        MyJobObject object = table.getSelectionModel().getSelectedItem();
        object.setStartDate(myJobObjectStringCellEditEvent.getNewValue());
        try {
            ConnectDB connectDB = new ConnectDB();
            Connection connection = connectDB.getConnection();

            //call username and password using textFields as values
            String updateQuerry = "UPDATE job set start_date = \"" + object.getStartDate().toString() + "\" where id =\"" + Integer.parseInt(object.getId()) + "\"";

            //Create Statement
            Statement statement = connection.createStatement();
            statement.executeUpdate(updateQuerry)
            ;
        } catch (SQLException ex) {
        }

    }

    public void salaryEdit(TableColumn.CellEditEvent<MyJobObject, String> myJobObjectStringCellEditEvent) {
        MyJobObject object = table.getSelectionModel().getSelectedItem();
        object.setSalary(myJobObjectStringCellEditEvent.getNewValue());
        try {
            ConnectDB connectDB = new ConnectDB();
            Connection connection = connectDB.getConnection();

            //call username and password using textFields as values
            String updateQuerry = "UPDATE job set salary = \"" + object.getSalary().toString() + "\" where id =\"" + Integer.parseInt(object.getId()) + "\"";

            //Create Statement
            Statement statement = connection.createStatement();
            statement.executeUpdate(updateQuerry)
            ;
        } catch (SQLException ex) {
        }
    }

    public void positionEdit(TableColumn.CellEditEvent<MyJobObject, String> myJobObjectStringCellEditEvent) {
        MyJobObject object = table.getSelectionModel().getSelectedItem();
        object.setPosition(myJobObjectStringCellEditEvent.getNewValue());
        try {
            ConnectDB connectDB = new ConnectDB();
            Connection connection = connectDB.getConnection();

            //call username and password using textFields as values
            String updateQuerry = "UPDATE job set position = \"" + object.getPosition().toString() + "\" where id =\"" + Integer.parseInt(object.getId()) + "\"";

            //Create Statement
            Statement statement = connection.createStatement();
            statement.executeUpdate(updateQuerry)
            ;
        } catch (SQLException ex) {
        }

    }

    public void edraEdit(TableColumn.CellEditEvent<MyJobObject, String> myJobObjectStringCellEditEvent) {
        MyJobObject object = table.getSelectionModel().getSelectedItem();
        object.setEdra(myJobObjectStringCellEditEvent.getNewValue());
        try {
            ConnectDB connectDB = new ConnectDB();
            Connection connection = connectDB.getConnection();

            //call username and password using textFields as values
            String updateQuerry = "UPDATE job set edra = \"" + object.getEdra().toString() + "\" where id =\"" + Integer.parseInt(object.getId()) + "\"";

            //Create Statement
            Statement statement = connection.createStatement();
            statement.executeUpdate(updateQuerry)
            ;
        } catch (SQLException ex) {
        }
    }

    public void recruiterEdit(TableColumn.CellEditEvent<MyJobObject, String> myJobObjectStringCellEditEvent) {
        MyJobObject object = table.getSelectionModel().getSelectedItem();
        object.setRecruiter(myJobObjectStringCellEditEvent.getNewValue());
        try {
            ConnectDB connectDB = new ConnectDB();
            Connection connection = connectDB.getConnection();

            //call username and password using textFields as values
            String updateQuerry = "UPDATE job set recruiter = \"" + object.getRecruiter().toString() + "\" where id =\"" + Integer.parseInt(object.getId()) + "\"";

            //Create Statement
            Statement statement = connection.createStatement();
            statement.executeUpdate(updateQuerry)
            ;
        } catch (SQLException ex) {
        }
    }

    public void subDateEdit(TableColumn.CellEditEvent<MyJobObject, String> myJobObjectStringCellEditEvent) {
        MyJobObject object = table.getSelectionModel().getSelectedItem();
        object.setSubDate(myJobObjectStringCellEditEvent.getNewValue());
        try {
            ConnectDB connectDB = new ConnectDB();
            Connection connection = connectDB.getConnection();

            //call username and password using textFields as values
            String updateQuerry = "UPDATE job set submission_date = \"" + object.getSubDate().toString() + "\" where id =\"" + Integer.parseInt(object.getId()) + "\"";

            //Create Statement
            Statement statement = connection.createStatement();
            statement.executeUpdate(updateQuerry)
            ;
        } catch (SQLException ex) {
        }
    }


}
