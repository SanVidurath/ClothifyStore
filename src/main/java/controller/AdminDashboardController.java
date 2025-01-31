package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class AdminDashboardController {

    @FXML
    private AnchorPane ancPaneLoadContainer;

    @FXML
    private Button btnEmployeeForm;

    @FXML
    private Button btnViewReports;

    @FXML
    void btnViewEmployeeFormOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/employee_form.fxml");

        assert resource!=null;

        Parent load = FXMLLoader.load(resource);

        ancPaneLoadContainer.getChildren().clear();
        ancPaneLoadContainer.getChildren().add(load);
    }

    @FXML
    void btnViewReportsOnAction(ActionEvent event) {

    }

}
