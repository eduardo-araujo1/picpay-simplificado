package com.eduardo.picpaysimplificado.services;

import com.eduardo.picpaysimplificado.domain.user.User;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void sendNotification(User user, String message){
        String email = user.getEmail();

        System.out.println(email+message);
    }
}