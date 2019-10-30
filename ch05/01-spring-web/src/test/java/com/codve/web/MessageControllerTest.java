package com.codve.web;

import com.codve.Message;
import com.codve.data.MessageRepository;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
/**
 * @author admin
 * @date 2019/10/29 18:45
 */
public class MessageControllerTest {

    // 测试最新的数据
    @Test
    public void showRecentMessages() throws Exception {
        List<Message> messages = createMessageList(20);
        MessageRepository mockRepository = mock(MessageRepository.class);
        when(mockRepository.findMessages(Long.MAX_VALUE, 20))
                .thenReturn(messages);

        MessageController controller = new MessageController(mockRepository);
        MockMvc mockMvc = standaloneSetup(controller)
                .setSingleView(new InternalResourceView("/WEB-INF/views/messages.jsp"))
                .build();
        mockMvc.perform(get("/messages"))
                .andExpect(view().name("messages"))
                .andExpect(model().attributeExists("messageList"))
                .andExpect(model().attribute("messageList",
                        hasItems(messages.toArray())));
    }

    // 测试分页
    @Test
    public void showPagedMessages() throws Exception {
        List<Message> messages = createMessageList(50);
        MessageRepository mockRepository = mock(MessageRepository.class);
        when(mockRepository.findMessages(238900, 50))
                .thenReturn(messages);
        MessageController controller = new MessageController(mockRepository);
        MockMvc mockMvc = standaloneSetup(controller)
                .setSingleView(new InternalResourceView("/WEB-INF/views/messages.jsp"))
                .build();
        mockMvc.perform(get("/messages?max=238900&count=50"))
                .andExpect(view().name("messages"))
                .andExpect(model().attributeExists("messageList"))
                .andExpect(model().attribute("messageList", hasItems(messages.toArray())));
    }

    // 测试单条数据
    @Test
    public void showOneMessage() throws Exception {
        Message message = new Message("hello", new Date());
        MessageRepository mockRepository = mock(MessageRepository.class);
        when(mockRepository.findOne(12345)).thenReturn(message);

        MessageController controller = new MessageController(mockRepository);
        MockMvc mockMvc = standaloneSetup(controller)
                .build();

        mockMvc.perform(get("/messages/12345"))
                .andExpect(view().name("message"))
                .andExpect(model().attributeExists("message"))
                .andExpect(model().attribute("message", message));
    }

    private List<Message> createMessageList(int count) {
        List<Message> messages = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            messages.add(new Message("message " + i, new Date()));
        }
        return messages;
    }
}
