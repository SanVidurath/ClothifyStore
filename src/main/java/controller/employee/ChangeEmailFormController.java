package controller.employee;

import com.jfoenix.controls.JFXTextField;
import model.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChangeEmailFormController {

    @FXML
    private Button btnChangeEmail;

    @FXML
    private JFXTextField txtConfirmNewEmail;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtNewEmail;

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


    @FXML
    void btnChangeEmailOnAction(ActionEvent event) {
        String emailText = txtEmail.getText();
        String newEmailText = txtNewEmail.getText();
        String confirmNewEmailText = txtConfirmNewEmail.getText();
        if (emailText.isEmpty() || newEmailText.isEmpty() || confirmNewEmailText.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "all fields must be filled").show();
        } else {
            try {
                Employee employee = new EmployeeController().search(emailText);
                if (employee == null) {
                    new Alert(Alert.AlertType.ERROR, "no current such email found").show();
                } else {
                    if (!validateEmail(newEmailText)) {
                        new Alert(Alert.AlertType.ERROR, "not a valid email address").show();
                    }else if (!newEmailText.equals(confirmNewEmailText)) {
                        new Alert(Alert.AlertType.ERROR, "new email and confirm new email do not match").show();
                    } else {
                        boolean isEmailUpdated = new EmployeeController().update(emailText, newEmailText);
                        if (isEmailUpdated) {
                            new Alert(Alert.AlertType.INFORMATION, "email updated successfully").show();
                        } else {
                            new Alert(Alert.AlertType.ERROR, "email couldn't be updated, try again later").show();
                        }
                    }
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    private boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.matches();
    }

}
