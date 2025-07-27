package com.danielnaiff.backend.service;

import com.danielnaiff.backend.Exception.NotificationError;
import com.danielnaiff.backend.entity.DTO.NotificationRequestDTO;
import com.danielnaiff.backend.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final RestTemplate restTemplate;

    public void sendNotification(User user, String message) throws Exception {
        String email = user.getEmail();
        NotificationRequestDTO notificationRequestDTO = new NotificationRequestDTO(email, message);

        ResponseEntity<String>  notification = restTemplate.postForEntity("https://util.devi.tools/api/v1/notify", notificationRequestDTO, String.class);

        if(!(notification.getStatusCode() == HttpStatus.OK)){
            throw new NotificationError("Servico de email esta fora do ar");
        }
    }

}
