package ge.croco.customernotificationsystem.model.Actiondto;

import ge.croco.customernotificationsystem.model.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationCreateRequestDTO {
    private NotificationType notificationType;
    private Long customerId;
}