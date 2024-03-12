module com.infinity.labn2_sysprog {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.infinity.labn2_sysprog to javafx.fxml;
    exports com.infinity.labn2_sysprog;
}