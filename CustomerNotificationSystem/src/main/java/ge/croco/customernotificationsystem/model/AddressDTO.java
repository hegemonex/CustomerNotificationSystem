package ge.croco.customernotificationsystem.model;

import ge.croco.customernotificationsystem.model.enums.AddressType;
import lombok.Data;

@Data
public class AddressDTO {
    private Long id;
    private AddressType type;
    private String addressLine;
    private Boolean active;
}
