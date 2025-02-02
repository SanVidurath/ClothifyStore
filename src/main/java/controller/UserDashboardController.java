package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class UserDashboardController {

    @FXML
    private AnchorPane ancPaneLoadContainer;

    @FXML
    private Button btnAddProducts;

    @FXML
    private Button btnChangePasswordForm;

    @FXML
    private Button btnOrderForm;

    @FXML
    private Button btnOrderReturnsForm;

    @FXML
    private Button btnSupplierForm;

    @FXML
    private Button btnViewAllOrders;

    @FXML
    private Button btnViewProducts;

    @FXML
    void btnAddProductsFormOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/add_products_form.fxml");

        assert resource!=null;

        Parent load = FXMLLoader.load(resource);

        ancPaneLoadContainer.getChildren().clear();
        ancPaneLoadContainer.getChildren().add(load);
    }

    @FXML
    void btnChangePasswordFormOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/change_password_form.fxml");

        assert resource!=null;

        Parent load = FXMLLoader.load(resource);

        ancPaneLoadContainer.getChildren().clear();
        ancPaneLoadContainer.getChildren().add(load);
    }

    @FXML
    void btnOrderFormOnAction(ActionEvent event) {

    }

    @FXML
    void btnOrderReturnsFormOnAction(ActionEvent event) {

    }

    @FXML
    void btnSupplierFormOnAction(ActionEvent event) {

    }

    @FXML
    void btnViewAllOrdersOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/view_all_orders.fxml");

        assert resource!=null;

        Parent load = FXMLLoader.load(resource);

        ancPaneLoadContainer.getChildren().clear();
        ancPaneLoadContainer.getChildren().add(load);
    }

    @FXML
    void btnViewProductsOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/view_products.fxml");

        assert resource!=null;

        Parent load = FXMLLoader.load(resource);

        ancPaneLoadContainer.getChildren().clear();
        ancPaneLoadContainer.getChildren().add(load);
    }

}
