package com.codve.web;

import com.codve.Message;
import com.codve.data.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author admin
 * @date 2019/10/29 19:07
 */
@Controller
@RequestMapping("/messages")
public class MessageController {

    private MessageRepository messageRepository;

    private static final String MAX_LONG_AS_STRING = "9223372036854775807";

    @Autowired
    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @RequestMapping(method = GET)
    public List<Message> messages(
            @RequestParam(value = "max", defaultValue= MAX_LONG_AS_STRING) long max,
            @RequestParam(value = "count", defaultValue = "20") int count) {
        return messageRepository.findMessages(max, count);
    }

/*    @RequestMapping(method = GET)
    public String messages(
            @RequestParam(value = "max", defaultValue = MAX_LONG_AS_STRING) long max,
            @RequestParam(value = "count", defaultValue = "20") int count,
            Model model) {
        List<Message> messageList = messageRepository.findMessages(max, count);
        model.addAttribute("messageList", messageList);
        return "messages";
    }*/

    @RequestMapping(value = "/{messageId}", method = GET)
    public String message(
            @PathVariable("messageId") long messageId,
            Model model) {
        Message message = messageRepository.findOne(messageId);
        if (message == null) {
            throw new MessageNotFoundException();
        }
        model.addAttribute(messageRepository.findOne(messageId));
        return "message";
    }

    @ExceptionHandler(DuplicateMessageException.class)
    public String handleDuplicateMessage() {
        return "error/duplicate";
    }


}
