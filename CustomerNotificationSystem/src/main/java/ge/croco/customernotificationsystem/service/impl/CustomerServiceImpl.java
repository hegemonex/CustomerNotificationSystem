package ge.croco.customernotificationsystem.service.impl;

import ge.croco.customernotificationsystem.entity.Address;
import ge.croco.customernotificationsystem.entity.Customer;
import ge.croco.customernotificationsystem.entity.Preference;
import ge.croco.customernotificationsystem.mapper.CustomerMapper;
import ge.croco.customernotificationsystem.model.AddressDTO;
import ge.croco.customernotificationsystem.model.CustomerDTO;
import ge.croco.customernotificationsystem.repo.AddressRepository;
import ge.croco.customernotificationsystem.repo.CustomerRepository;
import ge.croco.customernotificationsystem.repo.PreferenceRepository;
import ge.croco.customernotificationsystem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    private final AddressRepository addressRepository;

    private final PreferenceRepository preferenceRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper, AddressRepository addressRepository, PreferenceRepository preferenceRepository) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.addressRepository = addressRepository;
        this.preferenceRepository = preferenceRepository;
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.toEntity(customerDTO);
        if (customer.getAddresses() != null) {
            for (Address address : customer.getAddresses()) {
                address.setCustomer(customer);
            }
        }


        if (customer.getPreference() != null) {
            customer.getPreference().setCustomer(customer);
        }
        customer = customerRepository.save(customer);
        return customerMapper.toDTO(customer);
    }

    @Override
    public CustomerDTO updateCustomer(long id, CustomerDTO customerDTO) {

        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));


        customerMapper.updateEntityFromDTO(customerDTO, existingCustomer);


        if (customerDTO.getAddresses() != null) {
            List<Address> updatedAddresses = new ArrayList<>();

            for (AddressDTO addressDTO : customerDTO.getAddresses()) {
                if (addressDTO.getId() != null) {

                    Address existingAddress = addressRepository.findById(addressDTO.getId())
                            .orElseThrow(() -> new RuntimeException("Address not found"));


                    customerMapper.updateAddressFromDTO(addressDTO, existingAddress);
                    updatedAddresses.add(existingAddress);
                } else {

                    Address newAddress = customerMapper.toAddressEntity(addressDTO);
                    newAddress.setCustomer(existingCustomer);
                    updatedAddresses.add(newAddress);
                }
            }


            existingCustomer.getAddresses().clear();
            existingCustomer.getAddresses().addAll(updatedAddresses);
        }


        if (customerDTO.getPreference() != null) {
            if (customerDTO.getPreference().getId() != null) {

                Preference existingPreference = preferenceRepository.findById(customerDTO.getPreference().getId())
                        .orElseThrow(() -> new RuntimeException("Preference not found"));

                customerMapper.updatePreferenceFromDTO(customerDTO.getPreference(), existingPreference);
                existingCustomer.setPreference(existingPreference);
            } else {

                Preference newPreference = customerMapper.toPreferenceEntity(customerDTO.getPreference());
                newPreference.setCustomer(existingCustomer);
                existingCustomer.setPreference(newPreference);
            }
        }


        existingCustomer = customerRepository.saveAndFlush(existingCustomer);

        return customerMapper.toDTO(existingCustomer);
    }


    @Override
    public CustomerDTO getCustomerById(long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return customerMapper.toDTO(customer);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCustomer(long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Page<CustomerDTO> searchCustomer(String query, Pageable pageable) {
        Page<Customer> customers = customerRepository.searchCustomer(query, pageable);

        return customers.map(customerMapper::toDTO);
    }

}
