package com.gunyen.calendar.controller;/*
TimePlanner
Created by: John Nguyen
Creation Date: 12/30/2023
Creation Time: 11:39 AM
*/

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.ResourceBundle;

public class CalendarController implements Initializable {
    @FXML
    public ComboBox comboBoxMonth;
    @FXML
    public ComboBox comboBoxYear;
    public ObservableList<String> allMonths = FXCollections.observableArrayList("JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER");
    public ObservableList<Integer> allYears = FXCollections.observableArrayList();
    public YearMonth yearMonth;
    @FXML
    public Label day0_1;
    @FXML
    public Label day0_2;
    @FXML
    public Label day0_3;
    @FXML
    public Label day0_4;
    @FXML
    public Label day0_5;
    @FXML
    public Label day0_6;
    @FXML
    public Label day0_7;
    @FXML
    public Label day1_1;
    @FXML
    public Label day1_2;
    @FXML
    public Label day1_3;
    @FXML
    public Label day1_4;
    @FXML
    public Label day1_5;
    @FXML
    public Label day1_6;
    @FXML
    public Label day1_7;
    @FXML
    public Label day2_1;
    @FXML
    public Label day2_2;
    @FXML
    public Label day2_3;
    @FXML
    public Label day2_4;
    @FXML
    public Label day2_5;
    @FXML
    public Label day2_6;
    @FXML
    public Label day2_7;
    @FXML
    public Label day3_1;
    @FXML
    public Label day3_2;
    @FXML
    public Label day3_3;
    @FXML
    public Label day3_4;
    @FXML
    public Label day3_5;
    @FXML
    public Label day3_6;
    @FXML
    public Label day3_7;
    @FXML
    public Label day4_1;
    @FXML
    public Label day4_2;
    @FXML
    public Label day4_3;
    @FXML
    public Label day4_4;
    @FXML
    public Label day4_5;
    @FXML
    public Label day4_6;
    @FXML
    public Label day4_7;
    @FXML
    public Label day5_1;
    @FXML
    public Label day5_2;
    @FXML
    public Label day5_3;
    @FXML
    public Label day5_4;
    @FXML
    public Label day5_5;
    @FXML
    public Label day5_6;
    @FXML
    public Label day5_7;


    /**
     * @param url
     * @param resourceBundle
     */
    @SuppressWarnings("unchecked")
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxMonth.setItems(allMonths);
        int baseYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = baseYear+5; i>=(baseYear-100); i--) {
            allYears.add(i);
        }
        comboBoxYear.setItems(allYears);

        setCalendar(Calendar.MONTH, Calendar.getInstance().get(Calendar.YEAR));
        comboBoxYear.getSelectionModel().select(5);
        comboBoxMonth.getSelectionModel().select(Calendar.MONTH - 1);
    }

    /**
     * @param month
     * @param year
     */
    public void setCalendar(int month, int year) {
        //organized arrays of all container Labels by week
        Label[] week1 = new Label[]{day0_1, day0_2, day0_3, day0_4, day0_5, day0_6, day0_7};
        Label[] week2 = new Label[]{day1_1, day1_2, day1_3, day1_4, day1_5, day1_6, day1_7};
        Label[] week3 = new Label[]{day2_1, day2_2, day2_3, day2_4, day2_5, day2_6, day2_7};
        Label[] week4 = new Label[]{day3_1, day3_2, day3_3, day3_4, day3_5, day3_6, day3_7};
        Label[] week5 = new Label[]{day4_1, day4_2, day4_3, day4_4, day4_5, day4_6, day4_7};
        Label[] week6 = new Label[]{day5_1, day5_2, day5_3, day5_4, day5_5, day5_6, day5_7};

        //2d array containing all arrays related to weeks
        Label[][] weekInMonth = new Label[][]{week1, week2, week3, week4, week5, week6};

        //Setting the year and month to populate the containers related to selected year and month
        yearMonth = YearMonth.of(year, month);
        System.out.println("The month is: " + yearMonth.getMonth());
        System.out.println("The year is: " + yearMonth.getYear());

        //getting total number of days withing a selected year and month(1-12)
        int totalDays = yearMonth.lengthOfMonth();

        //int to use as a counter and as a value to set label text
        int dayCount = 1;
        int dayCountNext = 1;

        int prevDaysOffset = yearMonth.atDay(dayCount).getDayOfWeek().getValue()-1;
        YearMonth yearPrevMonth;
        if (month == 1) {
            yearPrevMonth = YearMonth.of(year-1,12);
        } else {
            yearPrevMonth = YearMonth.of(year, month-1);
        }
        int dayCountPrev = yearPrevMonth.lengthOfMonth() - prevDaysOffset;

        //iterate through every week in the month
        for (Label[] everyWeek : weekInMonth) {
//            System.out.println("\nCheck");

            //iterate through every day of the week in current week in the month
            for (int i = 0; i < everyWeek.length; i++) {
//                System.out.println("The array day of the week is: " + i);

                //reset unrelated labels to default value
                everyWeek[i].setText("n/a");
                everyWeek[i].setStyle("<-fx-font-weight> regular");

                //conclude populating labels after the last day of the month is reached
                if (dayCount < totalDays+1) {

                    //set days to appropriate labels
                    if (dayCount == 1 && (yearMonth.atDay(dayCount).getDayOfWeek().getValue()) < 7) {
                        if (yearMonth.atDay(dayCount).getDayOfWeek().getValue() == i) {
                            everyWeek[i].setText(String.valueOf(dayCount));
                            everyWeek[i].setStyle("-fx-font-weight: bold");
//                            System.out.println("The day of the month is: " + dayCount);
                            dayCount++;
                        } else {
                            everyWeek[i].setText(String.valueOf(dayCountPrev));
                            dayCountPrev++;
                        }
                    } else if (dayCount == 1 && (yearMonth.atDay(dayCount).getDayOfWeek().getValue()) == 7) {
                        everyWeek[i].setText(String.valueOf(dayCount));
                        everyWeek[i].setStyle("-fx-font-weight: bold");
//                        System.out.println("The day of the month is: " + dayCount);
                        dayCount++;
                    } else if (dayCount > 1) {
                        everyWeek[i].setText(String.valueOf(dayCount));
                        everyWeek[i].setStyle("-fx-font-weight: bold");
//                        System.out.println("The day of the month is: " + dayCount);
                        dayCount++;
                    }
                } else {
                    everyWeek[i].setText(String.valueOf(dayCountNext));
                    everyWeek[i].setStyle("<-fx-font-weight> regular");
                    dayCountNext++;
                }
            }
        }

    }

    /**
     * @param actionEvent
     */
    public void monthSelect(ActionEvent actionEvent) {
//        System.out.println("Selected Index of month is: " + comboBoxMonth.getSelectionModel().getSelectedIndex());
        if(comboBoxYear.getSelectionModel().getSelectedItem() != null) {
            setCalendar((comboBoxMonth.getSelectionModel().getSelectedIndex()) + 1, (int) comboBoxYear.getSelectionModel().getSelectedItem());
        } else {
            setCalendar((comboBoxMonth.getSelectionModel().getSelectedIndex()) + 1, Calendar.getInstance().get(Calendar.YEAR));
        }
    }

    /**
     * @param actionEvent
     */
    public void yearSelect(ActionEvent actionEvent) {
        if(((comboBoxMonth.getSelectionModel().getSelectedIndex()) + 1) == 0) {
            setCalendar((comboBoxMonth.getSelectionModel().getSelectedIndex()) + 2, Integer.parseInt(comboBoxYear.getSelectionModel().getSelectedItem().toString()));

        } else {
            setCalendar((comboBoxMonth.getSelectionModel().getSelectedIndex()) + 1, (int) comboBoxYear.getSelectionModel().getSelectedItem());
        }
    }

    /**
     * @param actionEvent
     */
    public void previousMonth(ActionEvent actionEvent) {
        if((yearMonth.getMonthValue()-1)==0) {
            comboBoxMonth.getSelectionModel().select(11);
            comboBoxYear.getSelectionModel().select(comboBoxYear.getSelectionModel().getSelectedIndex()+1);
        } else {
            comboBoxMonth.getSelectionModel().selectPrevious();
        }

    }

    /**
     * @param actionEvent
     */
    public void followingMonth(ActionEvent actionEvent) {
        if((yearMonth.getMonthValue())==12) {
            comboBoxMonth.getSelectionModel().select(0);
            comboBoxYear.getSelectionModel().select(comboBoxYear.getSelectionModel().getSelectedIndex()-1);
        } else {
            comboBoxMonth.getSelectionModel().selectNext();
        }
    }
}
