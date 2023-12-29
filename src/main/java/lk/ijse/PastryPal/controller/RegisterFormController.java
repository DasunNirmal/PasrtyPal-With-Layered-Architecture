package lk.ijse.PastryPal.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.PastryPal.DAO.custom.RegistrationDAO;
import lk.ijse.PastryPal.DAO.custom.impl.RegistrationDAOImpl;
import lk.ijse.PastryPal.RegExPatterns.RegExPatterns;
import lk.ijse.PastryPal.dto.RegistrationDto;

import java.io.IOException;
import java.sql.SQLException;

public class RegisterFormController {

    @FXML
    private TextField txtPassword;
    @FXML
    private PasswordField txtConfirmPassword;
    @FXML
    private TextField txtUser;
    @FXML
    private AnchorPane RegisterPane;
    RegistrationDAO registrationDAO = new RegistrationDAOImpl();

    private void clearFields() {
        txtUser.setText("");
        txtPassword.setText("");
        txtConfirmPassword.setText("");
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) this.RegisterPane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.setResizable(false);
        stage.centerOnScreen();
    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        String userName = txtUser.getText();
        String pw = txtPassword.getText();
        String ConfirmPW = txtConfirmPassword.getText();

        boolean isUserValid = RegExPatterns.getValidName().matcher(userName).matches();
        boolean isPasswordValid = RegExPatterns.getValidPassword().matcher(pw).matches();

        if (!isUserValid){
            new Alert(Alert.AlertType.ERROR,"Can Not Leave Name Empty").showAndWait();
            return;
        }if (!isPasswordValid){
            new Alert(Alert.AlertType.ERROR,"Password need to contain minimum of four Characters").showAndWait();
            return;
        }if (!ConfirmPW.equals(pw)){
            new Alert(Alert.AlertType.ERROR,"Password Did Not Matched").showAndWait();
        }else {
            var dto = new RegistrationDto(userName, pw);
            try {
                boolean checkDuplicates = registrationDAO.check(userName, pw);
                if (checkDuplicates) {
                    new Alert(Alert.AlertType.ERROR, "Duplicate Entry").showAndWait();
                    return;
                }
                boolean isRegistered = registrationDAO.saveUser(dto);
                if (isRegistered) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Your Account Has been Created").show();
                    clearFields();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void txtRegisterOnAction(ActionEvent event) {
        btnRegisterOnAction(new ActionEvent());
    }

    @FXML
    void txtGoToNewPasswordOnAction(ActionEvent event) {
        txtPassword.requestFocus();
    }

    @FXML
    void txtGoToConfirmPasswordOnAction(ActionEvent event) {
        txtConfirmPassword.requestFocus();
    }

}
