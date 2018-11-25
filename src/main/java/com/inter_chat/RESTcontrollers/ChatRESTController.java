package com.inter_chat.RESTcontrollers;

import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import com.inter_chat.Inter_Chat_Backend.model.Message;
import com.inter_chat.Inter_Chat_Backend.model.MessageOutput;

@RestController
public class ChatRESTController {
	@MessageMapping("/chat")
	@SendTo("/topic/message")
	public MessageOutput sendMessage(Message message) {
		return new MessageOutput(message, new Date());

	}
}