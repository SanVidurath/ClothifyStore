package controller.product;

import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import model.Product;
import controller.supplier.SupplierController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class AddProductsFormController implements Initializable {

    public Button btnCommit;
    @FXML
    private Button btnAdd;

    @FXML
    private Button btnReload;

    @FXML
    private ComboBox cmbCategory;

    @FXML
    private ComboBox cmbSize;

    @FXML
    private ComboBox cmbSupplierId;

    @FXML
    private TableColumn colCategory;

    @FXML
    private TableColumn colCode;

    @FXML
    private TableColumn colDescription;

    @FXML
    private TableColumn colQtyInStock;

    @FXML
    private TableColumn colSize;

    @FXML
    private TableColumn colSupplierId;

    @FXML
    private TableColumn colUnitPrice;

    @FXML
    private TableView tblProducts;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtQuantity;

    @FXML
    private JFXTextField txtUnitPrice;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String descriptionText = txtDescription.getText();
        String quantityText = txtQuantity.getText();
        String unitPriceText = txtUnitPrice.getText();
        String sizeValue = String.valueOf(cmbSize.getValue());
        String categoryValue = String.valueOf(cmbCategory.getValue());
        String supplierIdValue = String.valueOf(cmbSupplierId.getValue());

        if(descriptionText.isEmpty()||quantityText.isEmpty()||unitPriceText.isEmpty()|| cmbSize.getSelectionModel().isEmpty()||cmbCategory.getSelectionModel().isEmpty()||cmbSupplierId.getSelectionModel().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"all fields must be filled and all combo box values must be selected").show();
        }else{
            Product product = new Product(1, descriptionText, categoryValue, sizeValue, Double.parseDouble(unitPriceText), Integer.parseInt(quantityText), Integer.parseInt(supplierIdValue));

            try {
                boolean isProductAdded = new ProductController().add(product);
                if(isProductAdded){
                    new Alert(Alert.AlertType.INFORMATION,"product added successfully").show();
                }else{
                    new Alert(Alert.AlertType.ERROR,"something went wrong, try again later.").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }

        }

    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        loadData();
    }

    private void loadData(){
        ObservableList<Product> products = FXCollections.observableArrayList();
        try {
            List<Product> productList = new ProductController().getAll();
            products.addAll(productList);
            tblProducts.setItems(products);

            colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
            colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
            colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
            colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
            colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
            colQtyInStock.setCellValueFactory(new PropertyValueFactory<>("quantityInStock"));
            colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void cmbShowCategoryOnAction(ActionEvent event) {

    }

    @FXML
    void cmbShowSizeOnAction(ActionEvent event) {

    }

    @FXML
    void cmbShowSupplierIdOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadCategoryValues();
        loadSizeValues();
        loadSupplierIdValues();
    }

    private void loadCategoryValues() {
        ObservableList<String> categoryObservableList = FXCollections.observableArrayList();
        categoryObservableList.addAll(Arrays.asList("ladies", "gents", "kids"));
        cmbCategory.setItems(categoryObservableList);
    }

    private void loadSizeValues() {
        ObservableList<String> categoryObservableList = FXCollections.observableArrayList();
        categoryObservableList.addAll(Arrays.asList("small", "medium", "large"));
        cmbSize.setItems(categoryObservableList);
    }

    private void loadSupplierIdValues(){
        try {
            ObservableList<Integer> supplierIds = new SupplierController().getSupplierIds();
            cmbSupplierId.setItems(supplierIds);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void btnCommitOnAction(ActionEvent actionEvent) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        connection.commit();
    }
}
