package edu.exercise.birthday;

import java.util.List;

public interface SendService {
    String sendToSingleContact ( Contact to, Contact from );
    String sendSingleBirthdayReminder ( Contact to, Contact from, List<Contact> contactsWhoseBirthdayIsToday );
    String sendBirthdayReminder ( Contact to, Contact from, Contact contactWhoseBirthdayIsToday );
}
