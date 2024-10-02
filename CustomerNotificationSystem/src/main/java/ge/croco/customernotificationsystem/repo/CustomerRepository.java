package ge.croco.customernotificationsystem.repo;

import ge.croco.customernotificationsystem.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT s FROM Customer s WHERE " +
            "LOWER(s.firstName) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(s.lastName) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(s.email) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(s.contactNumber) LIKE LOWER(CONCAT('%', :query, '%'))")
    Page<Customer> searchCustomer(String query, Pageable pageable);
}
