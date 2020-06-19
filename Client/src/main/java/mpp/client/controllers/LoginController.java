package mpp.client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import mpp.domain.entities.User;
import mpp.services.interfaces.Services;

public class LoginController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;


    private Services services;

    public void setServices(Services serv){
        this.services = serv;
    }

    @FXML
    void loginOnAction(ActionEvent event) {
        String userName = usernameField.getText();
        String password = passwordField.getText();

        try {
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource("/gameView.fxml"));
//
//            AnchorPane root = loader.load();
//
//            Stage dialogStage = new Stage();
//            dialogStage.initModality(Modality.WINDOW_MODAL);
//
//            Scene scene = new Scene(root);
//            dialogStage.setScene(scene);
//            dialogStage.setWidth(500);
//
//            GameController controller = loader.getController();



            User player = (User) services.login(userName, password/*, controller*/);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Successfully logged in!");
            alert.show();

//            controller.setServices(services, player);
//            dialogStage.show();
//            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (Exception e) {
//            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }

}
