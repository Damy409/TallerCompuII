package org.demo1.controller;

import org.demo1.model.Notification;
import org.demo1.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificationController {

    @Autowired
    private NotificationRepository notificationRepository;

    @PostMapping("/crear")
    public Notification crearNotificacion(@RequestBody Notification notification) {
        notification.setDate(LocalDate.now());
        return notificationRepository.save(notification);
    }

    @GetMapping("/{userId}")
    public List<Notification> obtenerNotificaciones(@PathVariable UUID userId) {
        return notificationRepository.findByUserId(userId);
    }
}
