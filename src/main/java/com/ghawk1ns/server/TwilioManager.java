package com.ghawk1ns.server;

import com.ghawk1ns.Logger;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.joda.time.DateTime;

public class TwilioManager {

    private static final org.slf4j.Logger logger = Logger.get(TwilioManager.class);
    public static final String ACCOUNT_SID = "AC6ade8aa72462b8cdfa73bda2ac538930";
    public static final String AUTH_TOKEN = "657bfa70fa500eeef0ff45403bf7bad0";

    private static PhoneNumber number;

    public static void init() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        number = new PhoneNumber("+18327424699");
        logger.info("Twilio initialized");
    }

    public static void send(String toNumber, String msg) {
        Message message = Message.creator(new PhoneNumber(toNumber), number, msg).create();
        String sid = message.getSid();
        Message.Status status = message.getStatus();
        DateTime created = message.getDateCreated();
        DateTime sent = message.getDateSent();
        logger.info("sid={} status={}, created={} sent={}", sid, status.toString(), created.toString(), sent.toString());
    }
}
