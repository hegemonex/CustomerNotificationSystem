package ge.croco.customernotificationsystem.model;

import lombok.Data;

import java.util.List;

@Data
public class CustomerDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String contactNumber;
    private List<AddressDTO> addresses;
    private PreferenceDTO preference;
}