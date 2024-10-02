package ge.croco.customernotificationsystem.controller;

import ge.croco.customernotificationsystem.entity.Notification;
import ge.croco.customernotificationsystem.model.Actiondto.NotificationCreateRequestDTO;
import ge.croco.customernotificationsystem.model.Actiondto.NotificationUpdateRequestDTO;
import ge.croco.customernotificationsystem.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // Endpoint to create a new notification
    @PostMapping
    public ResponseEntity<Notification> createNotification(@RequestBody NotificationCreateRequestDTO notificationCreateDTO) {
        Notification createdNotification = notificationService.addNotification(notificationCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdNotification);
    }

    // Endpoint to update the status of an existing notification
    @PatchMapping("/{id}/status")
    public ResponseEntity<Notification> updateNotificationStatus(@PathVariable Long id,
                                                                 @RequestBody NotificationUpdateRequestDTO notificationUpdateDTO) {
        Notification updatedNotification = notificationService.updateNotificationStatus(id, notificationUpdateDTO);
        return ResponseEntity.ok(updatedNotification);
    }

    // Endpoint to get notifications by customer ID
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Notification>> getNotificationsByCustomer(@PathVariable long customerId) {
        List<Notification> notifications = notificationService.getNotificationsByCustomer(customerId);
        return ResponseEntity.ok(notifications);
    }
}