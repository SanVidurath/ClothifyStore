package controller.order;

import com.jfoenix.controls.JFXTextField;
import controller.employee.EmployeeController;
import controller.model.Employee;
import controller.model.Product;
import controller.product.ProductController;
import controller.supplier.SupplierController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;

public class OrderFormController implements Initializable {


    @FXML
    private Button btnAddToCart;

    @FXML
    private Button btnPlaceOrder;

    @FXML
    private ComboBox cmbEmployeeIds;

    @FXML
    private ComboBox cmbPaymentType;

    @FXML
    private ComboBox cmbProductCode;

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
    private JFXTextField txtQuantityInStock;

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
        String customerNameText = txtCustomerName.getText();
        String customerEmailText = txtCustomerEmail.getText();
        String customerPhoneNoText = txtCustomerPhoneNo.getText();
        String employeeId = String.valueOf(cmbEmployeeIds.getValue());
        String employeeNameText = txtEmployeeName.getText();
        String paymentType = String.valueOf(cmbPaymentType.getValue());
        String productCode = String.valueOf(cmbProductCode.getValue());
        String descriptionText = txtProductDescription.getText();
        String unitPriceText = txtUnitPrice.getText();
        String quantityInStockText = txtQuantityInStock.getText();
        String quantityPurchasedText = txtQuantityPurchased.getText();

        if(customerNameText.isEmpty()||customerEmailText.isEmpty()||customerPhoneNoText.isEmpty()||cmbEmployeeIds.getSelectionModel().isEmpty()||cmbPaymentType.getSelectionModel().isEmpty()||cmbProductCode.getSelectionModel().isEmpty()||quantityPurchasedText.isEmpty()){
            new Alert(Alert.AlertType.ERROR,"all fields must be filled").show();
        }else{

        }
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTimeAndDate();
        loadEmployeeIds();
        loadProductCodes();
        loadPaymentType();


        cmbProductCode.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                searchProductData(newValue.toString());
            }
        });

        cmbEmployeeIds.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if(newValue!=null){
                searchEmployeeData(newValue.toString());
            }
        });
    }

    private void searchProductData(String prodCode){
        try {
            Product product = new ProductController().search(Integer.parseInt(prodCode));
            txtProductDescription.setText(product.getDescription());
            txtUnitPrice.setText(String.valueOf(product.getUnitPrice()));
            txtQuantityInStock.setText(String.valueOf(product.getQuantityInStock()));
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void searchEmployeeData(String employeeId){
        try {
            Employee employee = new EmployeeController().search(Integer.parseInt(employeeId));
            txtEmployeeName.setText(employee.getName());
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void loadEmployeeIds() {
        try {
            ObservableList<Integer> employeeIds = new EmployeeController().getIds();
            cmbEmployeeIds.setItems(employeeIds);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void loadProductCodes() {
        try {
            ObservableList<Integer> productIds = new ProductController().getProductIds();
            cmbProductCode.setItems(productIds);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void loadPaymentType() {
        ObservableList<String> paymentTypeObservableList = FXCollections.observableArrayList();
        paymentTypeObservableList.addAll(Arrays.asList("cash", "card"));
        cmbPaymentType.setItems(paymentTypeObservableList);
    }

    private void setTimeAndDate() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
        lblDate.setText(format);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, e -> {
                    LocalTime now = LocalTime.now();
                    lblTime.setText(padWithZeros(now.getHour(), now.getMinute(), now.getSecond()));
                }),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private String padWithZeros(int hour, int min, int sec) {
        String newHour = String.valueOf(hour).length() == 1 ? "0" + hour : String.valueOf(hour);
        String newMin = String.valueOf(min).length() == 1 ? "0" + min : String.valueOf(min);
        String newSec = String.valueOf(sec).length() == 1 ? "0" + sec : String.valueOf(sec);
        return newHour + ":" + newMin + ":" + newSec;
    }
}
