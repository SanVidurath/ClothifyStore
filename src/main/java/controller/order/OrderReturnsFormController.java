package controller.order;

import com.jfoenix.controls.JFXTextField;
import controller.product.ProductController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Order;
import model.OrderDetail;
import model.Product;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class OrderReturnsFormController implements Initializable {

    public Button btnReload;
    @FXML
    private Button btnReturn;

    @FXML
    private ComboBox cmbOrderId;

    @FXML
    private ComboBox cmbProductCode;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private TableColumn<?, ?> colProdCode;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private TableView tblOrderReturns;

    @FXML
    private JFXTextField txtOrderDate;

    @FXML
    private JFXTextField txtQuantity;

    ObservableList<OrderDetail> orderDetailList = FXCollections.observableArrayList();

    @FXML
    void btnReturnOnAction(ActionEvent event) {
        if(cmbOrderId.getSelectionModel().isEmpty()||cmbProductCode.getSelectionModel().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"all fields must be filled").show();
        }else{
            ObservableList<Object> objects = FXCollections.observableArrayList();
        }
    }

    @FXML
    void cmbShowOrderIdOnAction(ActionEvent event) {

    }

    @FXML
    void cmbShowProductCodeOnAction(ActionEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadOrderIds();
        loadProductCodes();

        cmbOrderId.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) ->{
            if(newValue!=null){
                searchOrderData(newValue.toString());
            }
        } );

        cmbProductCode.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if(newValue!=null){
                
            }
        });
    }

    private void loadProductCodes(){
        try {
            ObservableList<Integer> productIds = new ProductController().getProductIds();
            cmbProductCode.setItems(productIds);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void searchOrderData(String orderId){
        try {
            Order order = new OrderController().search(orderId);
            txtOrderDate.setText(order.getDate());
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void loadOrderIds(){
        try {
            List<Integer> ids = new OrderController().getIds();
            ObservableList<Integer> orderIds = FXCollections.observableArrayList();
            orderIds.addAll(ids);
            cmbOrderId.setItems(orderIds);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void btnReloadOnAction(ActionEvent actionEvent) {
        if(cmbOrderId.getSelectionModel().isEmpty()||cmbProductCode.getSelectionModel().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"all fields must be filled").show();
        }else{
            try {
                OrderDetail orderDetail = new OrderDetailController().getOrderDetail(cmbOrderId.getValue(), cmbProductCode.getValue());
                orderDetailList.add(orderDetail);
                tblOrderReturns.setItems(orderDetailList);

                colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
                colProdCode.setCellValueFactory(new PropertyValueFactory<>("productCode"));
                colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantityPurchased"));
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }

        }
    }
}
