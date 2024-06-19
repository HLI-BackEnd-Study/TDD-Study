package com.hanwha.domain;

import java.util.logging.Logger;

public class Announcer {

    Logger logger = Logger.getLogger(getClass().getName());

    public void announceMessage(String message) {
        logger.info(message);

    }

}
