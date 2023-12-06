module com.example.wpa_alpha {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires java.sql;
    requires java.naming;
    requires weka.stable;


    opens com.example.wpa_alpha to javafx.fxml;
    exports com.example.wpa_alpha;
    exports com.example.wpa_alpha.Modells.Stream;
    exports com.example.wpa_alpha.MachineLearning;

    opens com.example.wpa_alpha.PersistenceModels;
    exports com.example.wpa_alpha.Modells;
}