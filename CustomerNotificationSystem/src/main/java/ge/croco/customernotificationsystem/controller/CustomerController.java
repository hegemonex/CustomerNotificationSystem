package ge.croco.customernotificationsystem.controller;

import ge.croco.customernotificationsystem.model.CustomerDTO;
import ge.croco.customernotificationsystem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public Page<CustomerDTO> searchCustomer(
            @RequestParam(defaultValue = "") String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return customerService.searchCustomer(query, pageable);
    }

    @GetMapping("/{id}")
    public CustomerDTO getCustomerById(@PathVariable long id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping
    public CustomerDTO createCustomer(@RequestBody CustomerDTO customer) {
        return customerService.createCustomer(customer);
    }

    @PutMapping("/{id}")
    public CustomerDTO updateCustomer(@PathVariable long id, @RequestBody CustomerDTO customer) {
        return customerService.updateCustomer(id, customer);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable long id) {
        customerService.deleteCustomer(id);
    }
}
