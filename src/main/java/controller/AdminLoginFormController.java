package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AdminLoginFormController {

    @FXML
    private Button btnLogin;

    @FXML
    private Hyperlink hyperClickHere;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        if(email.isEmpty()||password.isEmpty()){
            new Alert(Alert.AlertType.ERROR,"all fields must be filled").show();
        }else{
            if(email.equals("admin@gmail.com")&&password.equals("admin")){
                Stage stage = new Stage();
                try {
                    stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/admin_dashboard_window.fxml")))));
                } catch (IOException e) {
                    new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
                }
                stage.show();
            }else{
                new Alert(Alert.AlertType.ERROR,"incorrect credentials, try again!!!").show();
            }
        }
    }

    @FXML
    void hyperResetPasswordOnAction(ActionEvent event) {

    }

}
