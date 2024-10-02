package ge.croco.customernotificationsystem.repo;

import ge.croco.customernotificationsystem.entity.Customer;
import ge.croco.customernotificationsystem.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {
    List<Notification> findByCustomer(Customer customer);
}
