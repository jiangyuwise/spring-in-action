package com.codve;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Date;

/**
 * @author admin
 * @date 2019/10/29 18:38
 */
public class Message {

    private final Long id;
    private final  String message;
    private final Date time;

    public Message(String message, Date time) {
        this(null, message, time);
    }

    public Message(Long id, String message, Date time) {
        this.id = id;
        this.message = message;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public Date getTime() {
        return time;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj, "id", "time");
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, "id", "time");
    }
}
