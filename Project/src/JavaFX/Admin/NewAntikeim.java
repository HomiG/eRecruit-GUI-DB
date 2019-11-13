package JavaFX.Admin;

import JavaFX.ConnectDB;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class NewAntikeim {


    @FXML
    private JFXTextField sector;

    @FXML
    private JFXTextField etaireiaId;

    @FXML
    private JFXTextField belongsTo;

    @FXML
    private JFXTextArea Descr;

    @FXML
    private Label label;

    @FXML
    private JFXTextField title;

    @FXML
    private JFXTextField belongs;

    @FXML
    private JFXTextArea description;

    @FXML
    void addSector(ActionEvent event) throws SQLException {

        //Create Connection with the DataBase
        ConnectDB connectDB = new ConnectDB();
        Connection connection = connectDB.getConnection();

        String createAntikeimQuerry = "INSERT INTO tomeas(tomeas_title, description, etaireia_id, belongs_to_sub) VALUES" +
                "(" + "'" + sector.getText() +"', " + "'"+ Descr.getText() +"', " + "'"+etaireiaId.getText() + "', " + "'" + belongsTo.getText() + "'" + ")";
        Statement statement = connection.createStatement();
        statement.executeUpdate(createAntikeimQuerry);
    }

    @FXML
    void createAtnikeim(ActionEvent event) throws SQLException {

        //Create Connection with the DataBase
        ConnectDB connectDB = new ConnectDB();
        Connection connection = connectDB.getConnection();
        //call username and password using textFields as values
        String createAntikeimQuerry;
        if (belongs.getText().equals("")) {
            createAntikeimQuerry = "INSERT INTO antikeim VALUES(\"" + title.getText() + "\", \"" + description.getText() + "\", NULL)";
        } else {
            createAntikeimQuerry = "INSERT INTO antikeim VALUES(\"" + title.getText() + "\", \"" + description.getText() + "\", \"" + belongs.getText() + "\")";
        }

        //Create Statement
        Statement statement = connection.createStatement();
        statement.executeUpdate(createAntikeimQuerry);

        label.setVisible(true);
        label.setTextFill(Color.web("#4BB543"));
        label.setText("Antikeim Added Successfully");

    }
}
