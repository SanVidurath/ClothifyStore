package controller.order;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class OrdersAndReturnsFormController {

    @FXML
    private Button btnReload;

    @FXML
    private Button btnReloadData;

    @FXML
    private Button btnReturn;

    @FXML
    private ComboBox<?> cmbOrderId;

    @FXML
    private ComboBox<?> cmbProductCode;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colEmployeeId;

    @FXML
    private TableColumn<?, ?> colEmployeeName;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private TableColumn<?, ?> colPaymentType;

    @FXML
    private TableColumn<?, ?> colProductId;

    @FXML
    private TableColumn<?, ?> colQuantityPurchased;

    @FXML
    private TableColumn<?, ?> colTotalPaid;

    @FXML
    private TableView<?> tblViewAllOrders;

    @FXML
    private JFXTextField txtOrderDate;

    @FXML
    private JFXTextField txtQuantityPurchased;

    @FXML
    void btnReloadDataOnAction(ActionEvent event) {

    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {

    }

    @FXML
    void btnReturnOnAction(ActionEvent event) {

    }

    @FXML
    void cmbShowOrderIdOnAction(ActionEvent event) {

    }

    @FXML
    void cmbShowProductCodeOnAction(ActionEvent event) {

    }

}
