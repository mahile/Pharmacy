import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
// import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application   {
    public static void main(String[] args) throws Exception {
       
        
        launch(args);
 
    }

    @Override
    public void start(Stage stage) throws RuntimeException, IOException {
        
         Parent root=FXMLLoader.load(getClass().getResource("Pharma1.fxml"));
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Medicine Storing");
        stage.setResizable(false);
        stage.show(); 
    }

}