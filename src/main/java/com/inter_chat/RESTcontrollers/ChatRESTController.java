package com.inter_chat.RESTcontrollers;

import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import com.inter_chat.Inter_Chat_Backend.model.Message;
import com.inter_chat.Inter_Chat_Backend.model.OutputMessage;

@RestController
public class ChatRESTController {
	@MessageMapping("/chat")
	@SendTo("/topic/message")
	public OutputMessage sendMessage(Message message) {
		return new OutputMessage(message, new Date());
	}
}
