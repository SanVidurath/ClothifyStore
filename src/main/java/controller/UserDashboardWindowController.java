package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class UserDashboardWindowController {


    @FXML
    private AnchorPane ancPaneLoadContainer;

    @FXML
    private Button btnAddProducts;

    @FXML
    private Button btnViewSupplierProducts;

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
    void btnAddProductsFormOnAction(ActionEvent event){
        URL resource = this.getClass().getResource("/view/add_products_form.fxml");

        assert resource!=null;

        Parent load = null;
        try {
            load = FXMLLoader.load(resource);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

        ancPaneLoadContainer.getChildren().clear();
        ancPaneLoadContainer.getChildren().add(load);
    }

    @FXML
    void btnOrderFormOnAction(ActionEvent event) {
        URL resource = this.getClass().getResource("/view/order_form.fxml");

        assert resource!=null;

        Parent load = null;
        try {
            load = FXMLLoader.load(resource);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

        ancPaneLoadContainer.getChildren().clear();
        ancPaneLoadContainer.getChildren().add(load);
    }

    @FXML
    void btnOrderReturnsFormOnAction(ActionEvent event){
        URL resource = this.getClass().getResource("/view/order_returns_form.fxml");

        assert resource!=null;

        Parent load = null;
        try {
            load = FXMLLoader.load(resource);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

        ancPaneLoadContainer.getChildren().clear();
        ancPaneLoadContainer.getChildren().add(load);
    }

    @FXML
    void btnSupplierFormOnAction(ActionEvent event){
        URL resource = this.getClass().getResource("/view/supplier_form.fxml");

        assert resource!=null;

        Parent load = null;
        try {
            load = FXMLLoader.load(resource);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

        ancPaneLoadContainer.getChildren().clear();
        ancPaneLoadContainer.getChildren().add(load);
    }

    @FXML
    void btnViewAllOrdersWindowOnAction(ActionEvent event){
        URL resource = this.getClass().getResource("/view/view_all_orders_window.fxml");

        assert resource!=null;

        Parent load = null;
        try {
            load = FXMLLoader.load(resource);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

        ancPaneLoadContainer.getChildren().clear();
        ancPaneLoadContainer.getChildren().add(load);
    }

    @FXML
    void btnViewProductsWindowOnAction(ActionEvent event){
        URL resource = this.getClass().getResource("/view/modify_products_window.fxml");

        assert resource!=null;

        Parent load = null;
        try {
            load = FXMLLoader.load(resource);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

        ancPaneLoadContainer.getChildren().clear();
        ancPaneLoadContainer.getChildren().add(load);
    }

    public void btnViewSupplierProductsOnAction(ActionEvent actionEvent) {
        URL resource = this.getClass().getResource("/view/supplier_products_window.fxml");

        assert resource!=null;

        try {
            Parent load = FXMLLoader.load(resource);

            ancPaneLoadContainer.getChildren().clear();
            ancPaneLoadContainer.getChildren().add(load);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
}
