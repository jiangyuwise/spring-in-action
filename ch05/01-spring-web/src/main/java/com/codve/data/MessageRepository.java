package com.codve.data;

import com.codve.Message;

import java.util.List;

/**
 * @author admin
 * @date 2019/10/29 18:37
 */
public interface MessageRepository {

    List<Message> findMessages(long max, int count);

    Message findOne(long messageId);
}
