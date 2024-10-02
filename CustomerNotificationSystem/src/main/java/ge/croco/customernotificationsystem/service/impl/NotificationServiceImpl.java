package ge.croco.customernotificationsystem.service.impl;

import ge.croco.customernotificationsystem.entity.Customer;
import ge.croco.customernotificationsystem.entity.Notification;
import ge.croco.customernotificationsystem.exception.ResourceNotFoundException;
import ge.croco.customernotificationsystem.model.Actiondto.NotificationCreateRequestDTO;
import ge.croco.customernotificationsystem.model.Actiondto.NotificationUpdateRequestDTO;
import ge.croco.customernotificationsystem.model.enums.NotificationStatus;
import ge.croco.customernotificationsystem.repo.CustomerRepository;
import ge.croco.customernotificationsystem.repo.NotificationRepository;
import ge.croco.customernotificationsystem.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public NotificationServiceImpl(NotificationRepository notificationRepository,
                                   CustomerRepository customerRepository) {
        this.notificationRepository = notificationRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Notification addNotification(NotificationCreateRequestDTO notificationDTO) {
        Customer customer = customerRepository.findById(notificationDTO.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        Notification notification = new Notification();
        notification.setNotificationType(notificationDTO.getNotificationType());
        notification.setCustomer(customer);
        notification.setStatus(NotificationStatus.IN_PROGRESS);  // Default status
        notification.setTimestamp(LocalDateTime.now());

        return notificationRepository.save(notification);
    }

    @Override
    public Notification updateNotificationStatus(Long notificationId, NotificationUpdateRequestDTO notificationUpdateDTO) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found"));

        notification.setStatus(notificationUpdateDTO.getStatus());
        return notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getNotificationsByCustomer(long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        return notificationRepository.findByCustomer(customer);
    }
}

