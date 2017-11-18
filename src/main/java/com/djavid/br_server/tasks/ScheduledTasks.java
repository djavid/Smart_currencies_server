package com.djavid.br_server.tasks;

import com.djavid.br_server.model.entity.RegistrationToken;
import com.djavid.br_server.model.repository.RegistrationTokenRepository;
import com.djavid.br_server.push.AndroidPushNotificationsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CompletableFuture;


@Component
public class ScheduledTasks {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private RegistrationTokenRepository registrationTokenRepository;
    private AndroidPushNotificationsService androidPushNotificationsService;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");


    public ScheduledTasks(RegistrationTokenRepository registrationTokenRepository,
                          AndroidPushNotificationsService androidPushNotificationsService) {
        this.registrationTokenRepository = registrationTokenRepository;
        this.androidPushNotificationsService = androidPushNotificationsService;
    }

    //@Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        log.info("Sending push notification...");

        RegistrationToken token = registrationTokenRepository.findOne(1L);

        try {
            CompletableFuture<String> pushNotification = androidPushNotificationsService
                    .send(token.token, "Изменение цены Bitcoin", dateFormat.format(new Date()));
            CompletableFuture.allOf(pushNotification).join();
            String firebaseResponse = pushNotification.get();
            log.info(firebaseResponse);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
