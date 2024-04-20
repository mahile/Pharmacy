import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Pharma3Controller implements Initializable {

    @FXML
    private TableColumn<Table, String> Expire_Date;

    @FXML
    private TableColumn<Table, String> MedicineName;

    @FXML
    private TableColumn<Table, String> Medicine_id;

    @FXML
    private TableColumn<Table, String> Price;

    @FXML
    private TableView<Table> TableView;

    @FXML
    private Button back;

    @FXML
    private AnchorPane anch1;

    @FXML
    private Button delete;

    @FXML
    private TextField exdate;

    @FXML
    private TextField medid;

    @FXML
    private TextField medname;

    @FXML
    private TextField price;

    @FXML
    private Button update;

  

private Connector db=new Connector();
private Connection con=db.getConnection();



    @FXML
    void OnActionBack(ActionEvent event) 
    { 
        try{ 
              FXMLLoader loader = new FXMLLoader(getClass().getResource("Pharma1.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 700, 492);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            ((Stage) (((javafx.scene.Node) event.getSource()).getScene().getWindow())).close();
        } catch (IOException e) {
            e.printStackTrace(); 
        }  

    }

    @FXML
    void OnActionDelete(ActionEvent event) throws SQLException 
    {
        Table selectedItem = TableView.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            String selectedId = selectedItem.getMedicine_name();

           
                 PreparedStatement stmt = con.prepareStatement("DELETE FROM medicine WHERE Medicine_name = ?");
                stmt.setString(1, selectedId);
                int affectedRows = stmt.executeUpdate();

                if (affectedRows > 0) {
                    TableView.getItems().remove(selectedItem);
                    showAlert(Alert.AlertType.INFORMATION, "Delete Successful", "Record deleted successfully");
                    clearFields();
                } else {
                    showAlert(Alert.AlertType.ERROR, "Delete Failed", "Failed to delete record");
                }
         
        } else {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a record to delete");
        }
    }
    @FXML
    void OnActionUpdate(ActionEvent event) 
    {
         Table selectedItem = TableView.getSelectionModel().getSelectedItem();

            if (selectedItem != null) { 
            String selectedId = selectedItem.getMedicine_id();
            String updatedMedicineName = medname.getText(); 
            String updatedPrice = price.getText(); 
            String updatedExpireDate = exdate.getText();
            
             PreparedStatement stmt;
            try {
                stmt = con.prepareStatement("UPDATE medicine SET Medicine_name = ?, Price = ?, Expire_Date = ? WHERE Medicine_id = ?");
                stmt.setString(1, updatedMedicineName); 
                stmt.setString(2, updatedPrice); 
                stmt.setString(3, updatedExpireDate); 
                stmt.setString(4, selectedId); 
                int affectedRows = stmt.executeUpdate();
            
            if (affectedRows > 0) { 
                 selectedItem.setMedicine_name(updatedMedicineName);
                  selectedItem.setPrice(updatedPrice); 
                  selectedItem.setExpire_Date(updatedExpireDate); 
                  TableView.refresh(); 
                  showAlert(Alert.AlertType.INFORMATION, "Update Successful", "Record updated successfully");
                  clearFields();
                 } else
                  { 
                    showAlert(Alert.AlertType.ERROR, "Update Failed", "Failed to update record"); 
                } 
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
        } 
        else
             { showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a record to update"); 
            }
            
         }
    



    @FXML
    void OnActionMedicine(ActionEvent event)
     {


String Medicine_name = medname.getText();
String Price = price.getText();
String Medicine_id = medid.getText();
String Expire_Date = exdate.getText();


Connector.insertData(Medicine_name, Price, Medicine_id, Expire_Date);


clearFields();
showOnTable();

}

private void clearFields() {
medname.clear();
price.clear();
medid.clear();
exdate.clear();
}

private void showAlert(Alert.AlertType alertType, String title, String headerText) {
Alert alert = new Alert(alertType);
alert.setTitle(title);
alert.setHeaderText(headerText);
// alert.setContentText(title);
alert.showAndWait();
showOnTable();
 }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showOnTable();
    }

    ObservableList<Table> list = FXCollections.observableArrayList();
    private void showOnTable() {
        
        list = Connector.fetchData();
        MedicineName.setCellValueFactory(new PropertyValueFactory<Table, String>("Medicine_name"));
        Price.setCellValueFactory(new PropertyValueFactory<Table, String>("Price"));
        Medicine_id.setCellValueFactory(new PropertyValueFactory<Table, String>("Medicine_id"));
        Expire_Date.setCellValueFactory(new PropertyValueFactory<Table, String>("Expire_Date"));
        TableView.setItems(list);
    TableView.getSelectionModel().selectedItemProperty().addListener(
            (ob,oldValue,newValue)->{
    display(newValue);
            }
        );

}

public void display(Table tabels){
    if(tabels!=null){
        medname.setText(tabels.getMedicine_name());
        price.setText(tabels.getPrice());
        medid.setText(tabels.getMedicine_id());
        exdate.setText(tabels.getExpire_Date());

    }
}


}
