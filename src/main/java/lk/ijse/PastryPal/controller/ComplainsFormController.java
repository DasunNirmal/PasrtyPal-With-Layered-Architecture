package lk.ijse.PastryPal.controller;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import lk.ijse.PastryPal.DAO.custom.ComplainDAO;
import lk.ijse.PastryPal.DAO.custom.impl.ComplainDAOImpl;
import lk.ijse.PastryPal.RegExPatterns.RegExPatterns;
import lk.ijse.PastryPal.dto.ComplainDto;
import lk.ijse.PastryPal.dto.tm.ComplainTm;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ComplainsFormController {
    @FXML
    private TableColumn<?, ?> colComplainID;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableView<ComplainTm> tblComplains;

    @FXML
    private Label lblComplainID;

    @FXML
    private Label lblCoplainCount;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private TextArea txtComplain;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtSearch;

    @FXML
    private Label lblComplainSaveOrNot;

    ComplainDAO complainDAO = new ComplainDAOImpl();
    private ObservableList<ComplainTm> obList = FXCollections.observableArrayList();

    public void initialize() throws SQLException {
        setCellValueFactory();
        setDateAndTime();
        generateNextComplainID();
        loadAllComplains();
        tableListener();
        totalComplains();
    }

    private void generateNextComplainID() {
        try {
            String previousComplainID = lblComplainID.getText();
            String complainID = complainDAO.generateNextComplainID();
            lblComplainID.setText(complainID);
            clearFields();
            if (btnClearPressed){
                lblComplainID.setText(previousComplainID);
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean btnClearPressed = false;

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
        generateNextComplainID();
    }

    private void clearFields(){
        txtComplain.setText("");
        txtDate.setValue(null);
        txtSearch.setText("");
        lblComplainSaveOrNot.setText("");
    }

    private void setDateAndTime(){
        Platform.runLater(() -> {
            lblDate.setText(String.valueOf(LocalDate.now()));

            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1), event -> {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
                String timeNow = LocalTime.now().format(formatter);
                lblTime.setText(timeNow);
            }));
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        });
    }
    private void totalComplains() throws SQLException {
        try {
            String getComplains = complainDAO.getAllCount();
            lblCoplainCount.setText(String.valueOf(getComplains));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void tableListener() {
        tblComplains.getSelectionModel().selectedItemProperty().addListener((observable, oldValued, newValue) -> {
            setData(newValue);
        });
    }

    private void setData(ComplainTm row) {
        if (row != null) {
            lblComplainID.setText(row.getComplain_id());
            txtComplain.setText(row.getDescription());
            txtDate.setValue(row.getComplain_date());
        }
    }
    private void setCellValueFactory() {
        colComplainID.setCellValueFactory(new PropertyValueFactory<>("complain_id"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("complain_date"));
        tblComplains.setId("my-table");
    }

    private void loadAllComplains() {
        try {
            obList.clear();
            List<ComplainDto> dtoList = complainDAO.getAllComplains();
            for (ComplainDto dto : dtoList){
                obList.add(
                        new ComplainTm(
                                dto.getComplain_id(),
                                dto.getDescription(),
                                dto.getComplain_date()
                        )
                );
            }
            tblComplains.setItems(obList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = lblComplainID.getText();
        String complain = txtComplain.getText();
        LocalDate date = txtDate.getValue();

        boolean isValidDesc = RegExPatterns.getValidDescription().matcher(complain).matches();

        if (!isValidDesc){
            new Alert(Alert.AlertType.ERROR,"Not a valid Complain.Must start with a Uppercase Letter").showAndWait();
            return;
        }if (date == null ){
            new Alert(Alert.AlertType.ERROR,"Can not Save Complain.Date is empty").showAndWait();
        } else {
            var dto = new ComplainDto(id , complain , date);
            try {
                boolean isSaved = complainDAO.saveComplain(dto);
                if (isSaved){
                    obList.clear();
                    totalComplains();
                    generateNextComplainID();
                    loadAllComplains();
                    lblComplainSaveOrNot.setText("Complain is Saved !");
                    PauseTransition pause = new PauseTransition(Duration.seconds(2));
                    pause.setOnFinished(pauseEvent -> {
                        clearFields();
                    });
                    pause.play();
                }else {
                    lblComplainSaveOrNot.setText("Some Thing Happened Complain is Not Saved !");
                    PauseTransition pause = new PauseTransition(Duration.seconds(2));
                    pause.setOnFinished(pauseEvent -> {
                        lblComplainSaveOrNot.setText("");
                    });
                    pause.play();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = lblComplainID.getText();
        String complain = txtComplain.getText();
        LocalDate date = txtDate.getValue();

        boolean isValidDesc = RegExPatterns.getValidDescription().matcher(complain).matches();

        if (!isValidDesc){
            new Alert(Alert.AlertType.ERROR,"Not a valid Complain.Must start with a Uppercase Letter").showAndWait();
            return;
        }if (date == null ){
            new Alert(Alert.AlertType.ERROR,"Can not Update Complain.Date is empty").showAndWait();
        }else {
            var dto = new ComplainDto(id , complain , date);
            try {
                boolean isUpdated = complainDAO.updateComplains(dto);
                if (isUpdated){
                    obList.clear();
                    totalComplains();
                    generateNextComplainID();
                    loadAllComplains();
                    lblComplainSaveOrNot.setText("Complain is Updated !");
                    PauseTransition pause = new PauseTransition(Duration.seconds(2));
                    pause.setOnFinished(pauseEvent -> {
                        clearFields();
                    });
                    pause.play();
                }else {
                    lblComplainSaveOrNot.setText("Some Thing Happened Complain is Not Updated !");
                    PauseTransition pause = new PauseTransition(Duration.seconds(2));
                    pause.setOnFinished(pauseEvent -> {
                        lblComplainSaveOrNot.setText("");
                    });
                    pause.play();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = lblComplainID.getText();
        String complain = txtComplain.getText();
        LocalDate date = txtDate.getValue();

        boolean isValidDesc = RegExPatterns.getValidDescription().matcher(complain).matches();

        if (!isValidDesc){
            new Alert(Alert.AlertType.ERROR,"Can not Delete Complain.Description Can not be empty").showAndWait();
            return;
        }if (date == null ){
            new Alert(Alert.AlertType.ERROR,"Can not Delete Complain.Date is empty").showAndWait();
        } else {
            try {
                boolean isDeleted = complainDAO.deleteComplains(id);
                if (isDeleted){
                    obList.clear();
                    totalComplains();
                    generateNextComplainID();
                    loadAllComplains();
                    lblComplainSaveOrNot.setText("Complain is Deleted !");
                    PauseTransition pause = new PauseTransition(Duration.seconds(2));
                    pause.setOnFinished(pauseEvent -> {
                        clearFields();
                    });
                    pause.play();
                }else {
                    lblComplainSaveOrNot.setText("Some Thing Happened Complain is Not Deleted !");
                    PauseTransition pause = new PauseTransition(Duration.seconds(2));
                    pause.setOnFinished(pauseEvent -> {
                        lblComplainSaveOrNot.setText("");
                    });
                    pause.play();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void txtSearchOnActon(KeyEvent event) {
        searchTableFilter();
    }

    private void searchTableFilter() {
        FilteredList<ComplainTm> filterComplainsTbl = new FilteredList<>(obList, b -> true);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filterComplainsTbl.setPredicate(customerTm -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String search = newValue.toLowerCase();

                if (customerTm.getComplain_id().toLowerCase().contains(search)) {
                    return true;
                } else if (customerTm.getComplain_id().toLowerCase().contains(search)) {
                    return true;
                } else if (customerTm.getDescription().toLowerCase().contains(search)) {
                    return true;
                } else if (customerTm.getDescription().toLowerCase().contains(search)) {
                    return true;
                } else if (customerTm.getComplain_date().toString().contains(search)) {
                    return true;
                } else if (customerTm.getComplain_date().toString().contains(search)) {
                    return true;
                } else
                    return false;
            });
        });
        SortedList<ComplainTm> sortComplainsTbl = new SortedList<>(filterComplainsTbl);
        sortComplainsTbl.comparatorProperty().bind(tblComplains.comparatorProperty());
        tblComplains.setItems(sortComplainsTbl);
    }
}
