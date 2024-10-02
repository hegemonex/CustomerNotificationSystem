package ge.croco.customernotificationsystem.model.Actiondto;

import ge.croco.customernotificationsystem.model.enums.NotificationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationUpdateRequestDTO {
    private NotificationStatus status;
}