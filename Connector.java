

import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;




public class Connector {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/MedicineStoring1";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    Connection connection;
public Connector(){
try {
    connection=DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
} catch (SQLException e) {
    e.printStackTrace();
}
}
public Connection getConnection(){
    return connection;
}
    public static void insertData(String Medicine_name, String Price, String Medicine_id, String Expire_Date)
     {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO medicine (Medicine_name, Price, Medicine_id, Expire_Date) VALUES (?, ?, ?, ?)")
        ) {
            preparedStatement.setString(1, Medicine_name);
            preparedStatement.setString(2, Price);
            preparedStatement.setString(3, Medicine_id);
            preparedStatement.setString(4, Expire_Date);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
public static ObservableList<Table> fetchData() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String selectQuery = "SELECT Medicine_name, Price, Medicine_id, Expire_Date FROM medicine";

            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                ObservableList<Table> list = FXCollections.observableArrayList();
                ResultSet resultSet = preparedStatement.executeQuery();
                try {
                    if (resultSet.next()) {

                        while (resultSet.next()) {
                            list.add(new Table(resultSet.getString("Medicine_name"),
                                    resultSet.getString("Price"), resultSet.getString("Medicine_id"), resultSet.getString("Expire_Date")));
                        }
                        return list;

                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    
        
    }

    
     
