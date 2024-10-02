package ge.croco.customernotificationsystem.service;

import ge.croco.customernotificationsystem.entity.Notification;
import ge.croco.customernotificationsystem.model.Actiondto.NotificationCreateRequestDTO;
import ge.croco.customernotificationsystem.model.Actiondto.NotificationUpdateRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface NotificationService {
    List<Notification> getNotificationsByCustomer(long customerId);
    Notification addNotification(NotificationCreateRequestDTO notificationDTO);
    Notification updateNotificationStatus(Long notificationId, NotificationUpdateRequestDTO notificationUpdateDTO);
}
