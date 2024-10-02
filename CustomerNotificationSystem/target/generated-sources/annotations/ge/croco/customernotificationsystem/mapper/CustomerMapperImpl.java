package ge.croco.customernotificationsystem.mapper;

import ge.croco.customernotificationsystem.entity.Address;
import ge.croco.customernotificationsystem.entity.Customer;
import ge.croco.customernotificationsystem.entity.Preference;
import ge.croco.customernotificationsystem.model.AddressDTO;
import ge.croco.customernotificationsystem.model.CustomerDTO;
import ge.croco.customernotificationsystem.model.PreferenceDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-02T20:50:24+0400",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerDTO toDTO(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setId( customer.getId() );
        customerDTO.setFirstName( customer.getFirstName() );
        customerDTO.setLastName( customer.getLastName() );
        customerDTO.setEmail( customer.getEmail() );
        customerDTO.setContactNumber( customer.getContactNumber() );
        customerDTO.setAddresses( addressListToAddressDTOList( customer.getAddresses() ) );
        customerDTO.setPreference( preferenceToPreferenceDTO( customer.getPreference() ) );

        return customerDTO;
    }

    @Override
    public Customer toEntity(CustomerDTO customerDTO) {
        if ( customerDTO == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setId( customerDTO.getId() );
        customer.setFirstName( customerDTO.getFirstName() );
        customer.setLastName( customerDTO.getLastName() );
        customer.setEmail( customerDTO.getEmail() );
        customer.setContactNumber( customerDTO.getContactNumber() );
        customer.setAddresses( addressDTOListToAddressList( customerDTO.getAddresses() ) );
        customer.setPreference( toPreferenceEntity( customerDTO.getPreference() ) );

        return customer;
    }

    @Override
    public Address toAddressEntity(AddressDTO addressDTO) {
        if ( addressDTO == null ) {
            return null;
        }

        Address address = new Address();

        address.setId( addressDTO.getId() );
        address.setType( addressDTO.getType() );
        address.setAddressLine( addressDTO.getAddressLine() );
        address.setActive( addressDTO.getActive() );

        return address;
    }

    @Override
    public Preference toPreferenceEntity(PreferenceDTO preferenceDTO) {
        if ( preferenceDTO == null ) {
            return null;
        }

        Preference preference = new Preference();

        preference.setId( preferenceDTO.getId() );
        preference.setEmailOptIn( preferenceDTO.getEmailOptIn() );
        preference.setSmsOptIn( preferenceDTO.getSmsOptIn() );
        preference.setPostalOptIn( preferenceDTO.getPostalOptIn() );

        return preference;
    }

    @Override
    public void updateEntityFromDTO(CustomerDTO customerDTO, Customer customer) {
        if ( customerDTO == null ) {
            return;
        }

        customer.setFirstName( customerDTO.getFirstName() );
        customer.setLastName( customerDTO.getLastName() );
        customer.setEmail( customerDTO.getEmail() );
        customer.setContactNumber( customerDTO.getContactNumber() );
        if ( customer.getAddresses() != null ) {
            List<Address> list = addressDTOListToAddressList( customerDTO.getAddresses() );
            if ( list != null ) {
                customer.getAddresses().clear();
                customer.getAddresses().addAll( list );
            }
            else {
                customer.setAddresses( null );
            }
        }
        else {
            List<Address> list = addressDTOListToAddressList( customerDTO.getAddresses() );
            if ( list != null ) {
                customer.setAddresses( list );
            }
        }
        if ( customerDTO.getPreference() != null ) {
            if ( customer.getPreference() == null ) {
                customer.setPreference( new Preference() );
            }
            updatePreferenceFromDTO( customerDTO.getPreference(), customer.getPreference() );
        }
        else {
            customer.setPreference( null );
        }
    }

    @Override
    public void updatePreferenceFromDTO(PreferenceDTO preferenceDTO, Preference preference) {
        if ( preferenceDTO == null ) {
            return;
        }

        preference.setEmailOptIn( preferenceDTO.getEmailOptIn() );
        preference.setSmsOptIn( preferenceDTO.getSmsOptIn() );
        preference.setPostalOptIn( preferenceDTO.getPostalOptIn() );
    }

    @Override
    public void updateAddressFromDTO(AddressDTO addressDTO, Address address) {
        if ( addressDTO == null ) {
            return;
        }

        address.setType( addressDTO.getType() );
        address.setAddressLine( addressDTO.getAddressLine() );
        address.setActive( addressDTO.getActive() );
    }

    protected AddressDTO addressToAddressDTO(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressDTO addressDTO = new AddressDTO();

        addressDTO.setId( address.getId() );
        addressDTO.setType( address.getType() );
        addressDTO.setAddressLine( address.getAddressLine() );
        addressDTO.setActive( address.getActive() );

        return addressDTO;
    }

    protected List<AddressDTO> addressListToAddressDTOList(List<Address> list) {
        if ( list == null ) {
            return null;
        }

        List<AddressDTO> list1 = new ArrayList<AddressDTO>( list.size() );
        for ( Address address : list ) {
            list1.add( addressToAddressDTO( address ) );
        }

        return list1;
    }

    protected PreferenceDTO preferenceToPreferenceDTO(Preference preference) {
        if ( preference == null ) {
            return null;
        }

        PreferenceDTO preferenceDTO = new PreferenceDTO();

        preferenceDTO.setId( preference.getId() );
        preferenceDTO.setEmailOptIn( preference.getEmailOptIn() );
        preferenceDTO.setSmsOptIn( preference.getSmsOptIn() );
        preferenceDTO.setPostalOptIn( preference.getPostalOptIn() );

        return preferenceDTO;
    }

    protected List<Address> addressDTOListToAddressList(List<AddressDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Address> list1 = new ArrayList<Address>( list.size() );
        for ( AddressDTO addressDTO : list ) {
            list1.add( toAddressEntity( addressDTO ) );
        }

        return list1;
    }
}
