package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class EmployeeFormController {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReload;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPhoneNo;

    @FXML
    private TableView<?> tblEmployees;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtConfirmPassword;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXTextField txtPhoneNo;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String nameText = txtName.getText();
        String addressText = txtAddress.getText();
        String emailText = txtEmail.getText();
        String phoneNoText = txtPhoneNo.getText();
        String passwordText = txtPassword.getText();
        String confirmPasswordText = txtConfirmPassword.getText();
        if(nameText.isEmpty()||addressText.isEmpty()||emailText.isEmpty()||phoneNoText.isEmpty()||passwordText.isEmpty()||confirmPasswordText.isEmpty()){
            new Alert(Alert.AlertType.ERROR,"all fields must be filled").show();
        }else{

        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

}
