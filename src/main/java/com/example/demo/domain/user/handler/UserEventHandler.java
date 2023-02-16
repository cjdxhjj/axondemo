package com.example.demo.domain.user.handler;

import com.alibaba.fastjson2.JSON;
import com.example.demo.domain.user.event.UserCreateEvent;
import com.example.demo.domain.user.event.UserUpdateEvent;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserEventHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @EventHandler
    public void on(UserCreateEvent event) {
        logger.info("recv user create event [{}]", JSON.toJSONString(event));
    }

    @EventHandler
    public void on(UserUpdateEvent event) {
        logger.info("recv user update event [{}]", JSON.toJSONString(event));
    }
}
