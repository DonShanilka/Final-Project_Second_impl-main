package lk.ijse.semisterfinal.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.semisterfinal.DB.DbConnetion;
import lk.ijse.semisterfinal.Tm.CartTm;
import lk.ijse.semisterfinal.dto.PlaceOrderDto;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MonthlyincomeController  implements Initializable {

    public Label lblTotalCustomer;
    @FXML
    private AreaChart<?, ?> incomeDataChart;

    @FXML
    private Label lblTotalInCome;

    @FXML
    private Label lblTotalOrders;

    @FXML
    private Label lblTotalUnits;

    @FXML
    private BarChart<?, ?> orderDataChart;

    @FXML
    private AnchorPane root1;

    public void dashBordTotalOrders() throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "SELECT COUNT(order_id) FROM orders";

        String totalOrders = null;

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                totalOrders = resultSet.getString("COUNT(order_id)");
            }
            lblTotalOrders.setText(totalOrders);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void dashBordTotalInCome() throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "SELECT SUM(unit_price) FROM order_detail";

        double totalInCome = 0;

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                totalInCome = resultSet.getDouble("SUM(unit_price)");
            }
            lblTotalInCome.setText("Rs. " + String.valueOf(totalInCome));

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void dashBordTotalUnits() throws SQLException {
        Connection connection;

        String sql = "SELECT SUM(qty) FROM order_detail";
        connection = DbConnetion.getInstance().getConnection();

        int totalUnits = 0;

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                totalUnits = resultSet.getInt("SUM(qty)");

            }
            lblTotalUnits.setText(String.valueOf(totalUnits));

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    /*public void incomeChart() throws SQLException {
        incomeDataChart.getData().clear();

        String sql = "SELECT date,SUM(unit_price) FROM order_detail GROUP BY date ORDER BY TIMESTAMP(date) ASC LIMIT 6";
        Connection connection = DbConnetion.getInstance().getConnection();

        try {
            XYChart.Series chart = new XYChart.Series();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                chart.getData().add(new XYChart.Data(resultSet.getString(1), resultSet.getString(2)));
            }
            incomeDataChart.getData().add(chart);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    public void orderChart() throws SQLException {
        orderDataChart.getData().clear();
        String sql = "SELECT date, COUNT(order_id) FROM orders WHERE date >= DATE_SUB(CURDATE(), INTERVAL 7 DAY) GROUP BY date ORDER BY TIMESTAMP(date) ASC LIMIT 7";

        Connection connection = DbConnetion.getInstance().getConnection();

        try {
            XYChart.Series chart = new XYChart.Series();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                chart.getData().add(new XYChart.Data(resultSet.getString(1), resultSet.getInt(2)));
            }

            orderDataChart.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void totalCustomer() throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "SELECT COUNT(customer_id) FROM customer";

        String totalid = null;

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                totalid = resultSet.getString("COUNT(customer_id)");
            }
            lblTotalCustomer.setText(totalid);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            orderChart();
            //incomeChart();
            totalCustomer();
            dashBordTotalInCome();
            dashBordTotalUnits();
            dashBordTotalOrders();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
