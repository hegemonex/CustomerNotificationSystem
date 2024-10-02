package ge.croco.customernotificationsystem.model;

import ge.croco.customernotificationsystem.model.enums.NotificationStatus;
import ge.croco.customernotificationsystem.model.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDTO {
    private CustomerDTO customer;
    private NotificationType notificationType;
    private NotificationStatus status;
    private LocalDateTime timestamp;
}
