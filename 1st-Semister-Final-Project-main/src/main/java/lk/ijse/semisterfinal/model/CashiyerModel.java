package lk.ijse.semisterfinal.model;

import lk.ijse.semisterfinal.DB.DbConnetion;
import lk.ijse.semisterfinal.dto.PlaceOrderDto;
import java.sql.*;
import java.time.LocalDate;
import java.sql.Connection;

public class CashiyerModel {

    private static OrderModel orderModel = new OrderModel();
    private static ItemModel itemModel = new ItemModel();
    private static OrderDetailModel orderDetailModel = new OrderDetailModel();


    public static boolean placeOrder(PlaceOrderDto placeOrderDto) throws SQLException {
            System.out.println(placeOrderDto);

            String orderId = placeOrderDto.getOrderId();
            String customerId = placeOrderDto.getCustomerId();
            LocalDate date = placeOrderDto.getDate();

            Connection connection = null;
            try {
                connection = DbConnetion.getInstance().getConnection();
                connection.setAutoCommit(false);

                boolean isOrderSaved = orderModel.saveOrder(orderId, customerId, date);
                if (isOrderSaved) {
                        boolean isUpdated = itemModel.updateItem(placeOrderDto.getCartTmList());
                        if (isUpdated) {
                            boolean isOrderDetailSaved = orderDetailModel.saveOrderDetails(placeOrderDto.getOrderId(), placeOrderDto.getCartTmList());
                            if (isOrderDetailSaved)
                                connection.commit();

                        }
                    }
                connection.rollback();
            } finally {
                connection.setAutoCommit(true);
            }
            return true;
        }
}