package bg.rentacar.repository;

import bg.rentacar.model.entity.Order;
import bg.rentacar.model.enums.RentOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByStatus(RentOrderStatus status);
}
