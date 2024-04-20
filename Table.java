

// import javafx.beans.property.SimpleStringProperty;
// import javafx.beans.property.StringProperty;

public class Table {



    private String Medicine_name;
    private String Price;
    private String Expire_Date;
    private String Medicine_id;

        public Table(String Medicine_name,String Price, String Medicine_id, String Expire_Date) {
            this.Medicine_name = Medicine_name;
            this.Price = Price;
            this.Medicine_id = Medicine_id;
            this.Expire_Date = Expire_Date;
        }



        public String getMedicine_name() {
            return Medicine_name;
        }

        public void setMedicine_name(String Medicine_name) {
            this.Medicine_name= Medicine_name;
        }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String Price) {
        this.Price = Price;
    }
    public String getMedicine_id() {
        return Medicine_id;
    }

    public void setMedicine_id(String Medicine_id) {
        this.Medicine_id = Medicine_id;
    }

        public String getExpire_Date() {
            return Expire_Date;
        }

        public void setExpire_Date(String Expire_Date) {
            this.Expire_Date = Expire_Date;
        }

        


   
   
    }