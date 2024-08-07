package bg.rentacar.service;

import bg.rentacar.model.dto.AllOrdersByStatus;
import bg.rentacar.model.dto.AllUserOrdersDTO;
import bg.rentacar.model.dto.OrderDTO;
import bg.rentacar.model.entity.Order;

import java.security.Principal;

public interface OrderService {
    void registerOrder(OrderDTO orderDTO, String username);
    Order getOrderById(Long id);
    AllUserOrdersDTO getAllUserOrders(Principal principal);
    AllOrdersByStatus getAllOrdersByStatus();
    void approveOrder(Long id);
    void cancelOrder(Long id);
    void finishOrder();
    void deleteOrder(Long id);
}
