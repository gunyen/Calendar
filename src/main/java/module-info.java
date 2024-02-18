module com.gunyen.calendar {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.gunyen.calendar to javafx.fxml;
    exports com.gunyen.calendar;
    exports com.gunyen.calendar.controller;
    opens com.gunyen.calendar.controller to javafx.fxml;
}