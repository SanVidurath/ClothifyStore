package controller.product;

import com.jfoenix.controls.JFXTextField;
import model.Product;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class ModifyProductsWindowController implements Initializable {


    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<?> cmbCategory;

    @FXML
    private ComboBox cmbProductCode;

    @FXML
    private ComboBox<?> cmbSize;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtCategory;

    @FXML
    private JFXTextField txtSize;

    @FXML
    private JFXTextField txtQuantity;

    @FXML
    private JFXTextField txtSupplierId;

    @FXML
    private JFXTextField txtUnitPrice;

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        Product product = null;
        try {
            Integer productCode = Integer.parseInt(String.valueOf(cmbProductCode.getValue()));
            product = new ProductController().search(productCode);
            if (product == null) {
                new Alert(Alert.AlertType.ERROR, "check product code and try again").show();
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "do you want to delete this product?");
                Optional<ButtonType> buttonType = alert.showAndWait();
                if (buttonType.isPresent() && buttonType.get().getText().equals("OK")) {
                    boolean isProductDeleted = new ProductController().delete(product.getCode());
                    if (isProductDeleted) {
                        new Alert(Alert.AlertType.INFORMATION, "product has been deleted successfully").show();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "some error has occured, try again later.").show();
                    }

                }
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        if (cmbProductCode.getSelectionModel().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "product code must be give to search for product").show();
        } else {
            try {
                Integer productCode = Integer.parseInt(String.valueOf(cmbProductCode.getValue()));
                Product product = new ProductController().search(productCode);
                if (product == null) {
                    new Alert(Alert.AlertType.ERROR, "product not found. Check product code and try again.").show();
                } else {
                    txtDescription.setText(product.getDescription());
                    txtQuantity.setText(String.valueOf(product.getQuantityInStock()));
                    txtUnitPrice.setText(String.valueOf(product.getUnitPrice()));
                    txtSupplierId.setText(String.valueOf(product.getSupplierId()));
                    txtCategory.setText(product.getCategory());
                    txtSize.setText(product.getSize());
                    btnUpdate.setDisable(false);
                    btnDelete.setDisable(false);
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String descriptionText = txtDescription.getText();
        String categoryText = txtCategory.getText();
        String sizeText = txtSize.getText();
        String unitPriceText = txtUnitPrice.getText();
        String quantityText = txtQuantity.getText();
        int productCode = Integer.parseInt(String.valueOf(cmbProductCode.getValue()));
        int supplierId = Integer.parseInt(txtSupplierId.getText());



        if (descriptionText.isEmpty() || categoryText.isEmpty() || sizeText.isEmpty() || unitPriceText.isEmpty() || quantityText.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "all fields must be filled").show();
        } else if (!categoryText.equals("ladies")&&!categoryText.equals("gents")&&!categoryText.equals("kids")) {
            new Alert(Alert.AlertType.ERROR, "category has to be ladies/gents/kids").show();
        } else if (!sizeText.equals("small")&&!sizeText.equals("medium")&&!sizeText.equals("large")) {
            new Alert(Alert.AlertType.ERROR, "size has to be small/medium/large").show();
        } else {
            try {
                Double unitPriceValue = Double.parseDouble(unitPriceText);
                Integer quantityTextValue = Integer.parseInt(quantityText);
                Product product = new Product(productCode, descriptionText, categoryText, sizeText, unitPriceValue, quantityTextValue, supplierId );
                Product searchedProduct = new ProductController().search(productCode);
                if (searchedProduct == null) {
                    new Alert(Alert.AlertType.ERROR, "check email and try again later.").show();
                } else {
                    boolean isProductUpdated = new ProductController().update(product);
                    if (isProductUpdated) {
                        new Alert(Alert.AlertType.INFORMATION, "product updated successfully").show();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "product not updated. Try again later.").show();
                    }
                }

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (NumberFormatException e){
                new Alert(Alert.AlertType.ERROR,"quantity must be an integer, unit price must be a double").show();
            }


        }
    }

    @FXML
    void cmbShowCategoryOnAction(ActionEvent event) {

    }

    @FXML
    void cmbShowProductCodesOnAction(ActionEvent event) {

    }

    @FXML
    void cmbShowSizeOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadProductIds();
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }

    private void loadProductIds(){
        try {
            ObservableList<Integer> productIds = new ProductController().getProductIds();
            cmbProductCode.setItems(productIds);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
}
