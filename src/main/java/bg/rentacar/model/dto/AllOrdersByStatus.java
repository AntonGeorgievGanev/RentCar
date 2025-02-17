package bg.rentacar.model.dto;

import java.util.List;

public class AllOrdersByStatus {
    private List<OrderDTO> pendingOrders;
    private List<OrderDTO> approvedOrders;
    private List<OrderDTO> canceledOrders;
    private List<OrderDTO> finishedOrders;

    public AllOrdersByStatus() {
    }

    public AllOrdersByStatus(List<OrderDTO> pendingOrders, List<OrderDTO> approvedOrders,
                             List<OrderDTO> canceledOrders, List<OrderDTO> finishedOrders) {
        this.pendingOrders = pendingOrders;
        this.approvedOrders = approvedOrders;
        this.canceledOrders = canceledOrders;
        this.finishedOrders = finishedOrders;
    }

    public List<OrderDTO> getPendingOrders() {
        return pendingOrders;
    }

    public void setPendingOrders(List<OrderDTO> pendingOrders) {
        this.pendingOrders = pendingOrders;
    }

    public List<OrderDTO> getApprovedOrders() {
        return approvedOrders;
    }

    public void setApprovedOrders(List<OrderDTO> approvedOrders) {
        this.approvedOrders = approvedOrders;
    }

    public List<OrderDTO> getCanceledOrders() {
        return canceledOrders;
    }

    public void setCanceledOrders(List<OrderDTO> canceledOrders) {
        this.canceledOrders = canceledOrders;
    }

    public List<OrderDTO> getFinishedOrders() {
        return finishedOrders;
    }

    public void setFinishedOrders(List<OrderDTO> finishedOrders) {
        this.finishedOrders = finishedOrders;
    }
}
