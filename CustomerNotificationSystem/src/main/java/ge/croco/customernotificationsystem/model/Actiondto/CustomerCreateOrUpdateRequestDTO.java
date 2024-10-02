package ge.croco.customernotificationsystem.model.Actiondto;

import ge.croco.customernotificationsystem.model.AddressDTO;
import ge.croco.customernotificationsystem.model.CustomerDTO;
import ge.croco.customernotificationsystem.model.NotificationDTO;
import ge.croco.customernotificationsystem.model.PreferenceDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class CustomerCreateOrUpdateRequestDTO {
    private CustomerDTO customer;
    private List<AddressDTO> addresses;
    private PreferenceDTO preference;
    private List<NotificationDTO> notifications;
}
