package controller.order;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class OrderFormController {

    @FXML
    private Button btnAddToCart;

    @FXML
    private Button btnPlaceOrder;

    @FXML
    private ComboBox<?> cmbEmployeeIds;

    @FXML
    private ComboBox<?> cmbPaymentType;

    @FXML
    private ComboBox<?> cmbProductCode;

    @FXML
    private TableColumn<?, ?> colProductCode;

    @FXML
    private TableColumn<?, ?> colProductDescription;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblNetTotal;

    @FXML
    private Label lblTime;

    @FXML
    private TableView<?> tblOrders;

    @FXML
    private JFXTextField txtCustomerEmail;

    @FXML
    private JFXTextField txtCustomerName;

    @FXML
    private JFXTextField txtCustomerPhoneNo;

    @FXML
    private JFXTextField txtEmployeeName;

    @FXML
    private JFXTextField txtProductDescription;

    @FXML
    private JFXTextField txtQuantityPurchased;

    @FXML
    private JFXTextField txtUnitPrice;

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {

    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {

    }

    @FXML
    void cmbShowEmployeeIdsOnAction(ActionEvent event) {

    }

    @FXML
    void cmbShowPaymentTypeOnAction(ActionEvent event) {

    }

    @FXML
    void cmbShowProductCodes(ActionEvent event) {

    }

}
