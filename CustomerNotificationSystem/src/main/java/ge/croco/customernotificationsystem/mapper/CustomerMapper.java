package ge.croco.customernotificationsystem.mapper;

import ge.croco.customernotificationsystem.entity.Address;
import ge.croco.customernotificationsystem.entity.Customer;
import ge.croco.customernotificationsystem.entity.Preference;
import ge.croco.customernotificationsystem.model.AddressDTO;
import ge.croco.customernotificationsystem.model.CustomerDTO;
import ge.croco.customernotificationsystem.model.PreferenceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDTO toDTO(Customer customer);

    Customer toEntity(CustomerDTO customerDTO);

    Address toAddressEntity(AddressDTO addressDTO);

    Preference toPreferenceEntity(PreferenceDTO preferenceDTO);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDTO(CustomerDTO customerDTO, @MappingTarget Customer customer);
    @Mapping(target = "id", ignore = true)
    void updatePreferenceFromDTO(PreferenceDTO preferenceDTO, @MappingTarget Preference preference);
    @Mapping(target = "id", ignore = true)
    void updateAddressFromDTO(AddressDTO addressDTO, @MappingTarget Address address);

}