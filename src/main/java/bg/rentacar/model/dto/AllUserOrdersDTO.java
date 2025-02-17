package bg.rentacar.model.dto;

import java.util.List;

public class AllUserOrdersDTO {

    private List<OrderDTO> userOrders;

    public AllUserOrdersDTO() {
    }

    public AllUserOrdersDTO(List<OrderDTO> userOrders) {
        this.userOrders = userOrders;
    }

    public List<OrderDTO> getUserOrders() {
        return userOrders;
    }

    public void setUserOrders(List<OrderDTO> userOrders) {
        this.userOrders = userOrders;
    }
}
