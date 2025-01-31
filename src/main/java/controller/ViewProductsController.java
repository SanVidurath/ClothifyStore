package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ViewProductsController {

    @FXML
    private Button btnGents;

    @FXML
    private Button btnKids;

    @FXML
    private Button btnLadies;

    @FXML
    private Button btnReload;

    @FXML
    private TableColumn<?, ?> colCode;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colQtyInStock;

    @FXML
    private TableColumn<?, ?> colSupplierId;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableView<?> tblProducts;

    @FXML
    void btnLoadGentsOnAction(ActionEvent event) {

    }

    @FXML
    void btnLoadKidsOnAction(ActionEvent event) {

    }

    @FXML
    void btnLoadLadiesOnAction(ActionEvent event) {

    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {

    }

}
