module com.example.sklepshop {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires java.persistence;
    requires org.hibernate.orm.core;


    opens com.example.sklepshop to javafx.fxml;
    exports com.example.sklepshop;

    opens com.example.sklepshop.entities;
}