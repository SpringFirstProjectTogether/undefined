package com.lec.spring.repository.user;

import com.lec.spring.domain.chatbot.ChatbotMessage;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ChatbotRepository {

    int save(ChatbotMessage msg);

    List<ChatbotMessage> loadByUserId(Long id);

}
