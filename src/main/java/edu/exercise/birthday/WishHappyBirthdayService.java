package edu.exercise.birthday;

import java.util.List;

public class WishHappyBirthdayService {
    private final SendService sendService;

    public WishHappyBirthdayService ( SendService sendService ) {
        this.sendService = sendService;
    }

    public String sendHappyBirthday( Contact to, Contact from ) {
        return sendService.sendHappyBirthday( to, from );
    }

    public String sendSingleBirthdayReminder( Contact to, Contact from, List<Contact> contactsWhoseBirthdayIsToday ) {
        return sendService.sendSingleBirthdayReminder( to, from, contactsWhoseBirthdayIsToday );
    }

    public String sendBirthdayReminder ( Contact to, Contact from, Contact contactWhoseBirthdayIsToday ) {
        return sendService.sendBirthdayReminder( to, from, contactWhoseBirthdayIsToday );
    }
}
