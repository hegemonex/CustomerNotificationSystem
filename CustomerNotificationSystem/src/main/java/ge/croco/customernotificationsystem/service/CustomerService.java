package ge.croco.customernotificationsystem.service;

import ge.croco.customernotificationsystem.entity.Customer;
import ge.croco.customernotificationsystem.model.CustomerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    List<CustomerDTO > getAllCustomers();

    CustomerDTO  getCustomerById(long id);

    CustomerDTO createCustomer(CustomerDTO  customer);

    CustomerDTO  updateCustomer(long id, CustomerDTO  updatedCustomer);

    void deleteCustomer(long id);

    Page<CustomerDTO > searchCustomer(String query, Pageable pageable);
}
