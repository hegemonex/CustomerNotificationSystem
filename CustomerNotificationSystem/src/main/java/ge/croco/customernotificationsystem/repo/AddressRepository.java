package ge.croco.customernotificationsystem.repo;

import ge.croco.customernotificationsystem.entity.Address;
import ge.croco.customernotificationsystem.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
    void deleteAllByCustomer(Customer customer);
}
