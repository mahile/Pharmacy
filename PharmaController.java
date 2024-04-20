// import java.io.IOException;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
// import javafx.fxml.FXMLLoader;
// import javafx.scene.Parent;
// import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
// import javafx.stage.Stage;
import javafx.stage.Stage;

public class PharmaController {

    @FXML
    private Button Loginbtn;

    @FXML
    private PasswordField Password;

    @FXML
    private TextField Username;

    @FXML
    private AnchorPane main_form;

    
    @FXML
    void OnLoginAction(ActionEvent event) 
    {
  String username = Username.getText();
        String password = Password.getText();

        // Here you would perform validation of the username and password.
        // For this example, let's assume we have hardcoded values.
        String validUsername = "admin";
        String validPassword = "admin123";

        if (username.equals(validUsername) && password.equals(validPassword)) {
            // Login successful, navigate to Phrama3.fxml
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Pharma3.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) Loginbtn.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Login failed, show an error message
            System.out.println("Login failed");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid username or password!");
            alert.showAndWait();
        }
}
}
