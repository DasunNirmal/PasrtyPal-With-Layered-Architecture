package lk.ijse.PastryPal.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import lk.ijse.PastryPal.BO.BOFactory;
import lk.ijse.PastryPal.BO.Custom.ComplainBO;
import lk.ijse.PastryPal.BO.Custom.CustomerBO;
import lk.ijse.PastryPal.BO.Custom.OrderBO;
import lk.ijse.PastryPal.DAO.custom.ComplainDAO;
import lk.ijse.PastryPal.DAO.custom.CustomerDAO;
import lk.ijse.PastryPal.DAO.custom.DashBoardDAO;
import lk.ijse.PastryPal.DAO.custom.JoinQueryDAO;
import lk.ijse.PastryPal.DAO.custom.impl.ComplainDAOImpl;
import lk.ijse.PastryPal.DAO.custom.impl.CustomerDAOImpl;
import lk.ijse.PastryPal.DAO.custom.impl.DashBoardDAOImpl;
import lk.ijse.PastryPal.DAO.custom.impl.JoinQueryDAOImpl;
import lk.ijse.PastryPal.DB.DbConnection;
import lk.ijse.PastryPal.dto.DetailsDto;
import lk.ijse.PastryPal.dto.tm.DetailsTm;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DashboardFormController {

    @FXML
    private TableColumn<?, ?> colCustomer;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private TableColumn<?, ?> colProductID;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblTotalOrders;

    @FXML
    private Label lblSales;

    @FXML
    private Label lblTotalComplains;

    @FXML
    private Label lblTotalCustomers;

    @FXML
    private Label lblOrdersByDate;

    @FXML
    private TableView<DetailsTm> tblOrderDetails;

    JoinQueryDAO joinQueryDAO = new JoinQueryDAOImpl();
    ComplainBO complainDAO = (ComplainBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.COMPLAIN);
    CustomerBO customerDAO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);
    OrderBO orderBO = (OrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ORDER);
    DashBoardDAO dashBoardDAO = new DashBoardDAOImpl();

    private ObservableList<DetailsTm> obList = FXCollections.observableArrayList();

    public void initialize() throws SQLException {
        setDateAndTime();
        setCellValueFactory();
        countOrders();
        TotalSales();
        countOrdersByDate();
        totalCustomers();
        totalComplains();
        loadAllDetails();
    }

    private void totalComplains() {
        try {
            String getComplains = complainDAO.getTotalComplains();
            lblTotalComplains.setText(String.valueOf(getComplains));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void totalCustomers() {
        try {
            String totalCustomers = customerDAO.getTotalCustomer();
            lblTotalCustomers.setText(String.valueOf(totalCustomers));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void countOrdersByDate() throws SQLException {
        try {
            String ordersByDate = orderBO.getOrdersByDate();
            lblOrdersByDate.setText(String.valueOf(ordersByDate));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void countOrders() throws SQLException {
        try {
            String totalOrders = orderBO.getTotalOrders();
            lblTotalOrders.setText(String.valueOf(totalOrders));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void TotalSales() throws SQLException {
        try {
            String totalSales = dashBoardDAO.getTotalSales();
            lblSales.setText(String.valueOf(totalSales));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("order_id"));
        colCustomer.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colProductID.setCellValueFactory(new PropertyValueFactory<>("product_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unit_price"));
        tblOrderDetails.setId("my-table");

    }

    private void loadAllDetails() {
        try {
            obList.clear();
            List<DetailsDto> dtoList = joinQueryDAO.getAllDetails();
            for (DetailsDto dto : dtoList) {
                obList.add(
                        new DetailsTm(
                                dto.getOrder_id(),
                                dto.getCustomer_id(),
                                dto.getDate(),
                                dto.getProduct_id(),
                                dto.getDescription(),
                                dto.getQty(),
                                dto.getUnit_price()
                        )
                );
            }
            tblOrderDetails.setItems(obList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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
}
