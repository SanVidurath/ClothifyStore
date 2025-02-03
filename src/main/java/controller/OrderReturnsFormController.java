package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class OrderReturnsFormController {

    @FXML
    private Button btnReturn;

    @FXML
    private ComboBox<?> cmbOrderId;

    @FXML
    private ComboBox<?> cmbProductCode;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private TableColumn<?, ?> colProdCode;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private TableView<?> tblOrderReturns;

    @FXML
    private JFXTextField txtOrderDate;

    @FXML
    private JFXTextField txtQuantity;

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
