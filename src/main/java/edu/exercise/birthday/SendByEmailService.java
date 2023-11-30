package edu.exercise.birthday;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

public class SendByEmailService implements SendService {
    private static final String PROPERTY_FILE_NAME = "application.properties";
    private static final String MESSAGE_SUBJECT = "Happy birthday!";
    private static final String REMINDER_MESSAGE_SUBJECT = "Birthday Reminder";
    private static final String MIME_TYPE = "text/plain";
    private static final String MESSAGE_TO_SEND_BY_DEFAULT = " Happy birthday, dear %s";
    private static final String MESSAGE_TO_SEND_FOR_REMINDER = "Dear %s, \n Today is %s birthday. Don't forget to send them a message !";

    @Override
    public String sendToSingleContact ( Contact to, Contact from ) {
        try {
            Message message = new MimeMessage(this.setSession());
            message.setFrom( new InternetAddress(from.email(), from.firstname()) );
            message.setRecipient( Message.RecipientType.TO, new InternetAddress( to.email() ) );
            message.setSubject( MESSAGE_SUBJECT );
            message.setContent( new MimeMultipart(MIME_TYPE, new MimeBodyPart( new ByteArrayInputStream( MESSAGE_TO_SEND_BY_DEFAULT.formatted( to.firstname() ).getBytes()) )) );

            return "Email sent successfully";
        } catch( MessagingException | UnsupportedEncodingException e ) {
            throw new RuntimeException( e );
        }
    }

    @Override
    public String sendSingleBirthdayReminder ( Contact to, Contact from, List<Contact> contactsWhoseBirthdayIsToday ) {
        try {
            Message message = new MimeMessage( this.setSession() );
            message.setFrom( new InternetAddress( from.email(), from.firstname() ) );
            message.setRecipient( Message.RecipientType.TO, new InternetAddress( to.email() ));
            message.setSubject( REMINDER_MESSAGE_SUBJECT );
            message.setContent( new MimeMultipart( MIME_TYPE, new MimeBodyPart( new ByteArrayInputStream( MESSAGE_TO_SEND_FOR_REMINDER.formatted(to.firstname(), this.parseContactNameListToString( contactsWhoseBirthdayIsToday.stream().map( Contact::getFullName ).toList() )).getBytes() ) ) ) );

            return "Email sent successfully";
        } catch( MessagingException | UnsupportedEncodingException e ) {
            throw new RuntimeException( e );
        }
    }

    @Override
    public String sendBirthdayReminder ( Contact to, Contact from, Contact contactWhoseBirthdayIsToday ) {
        try {
            Message message = new MimeMessage( this.setSession() );
            message.setFrom( new InternetAddress( from.email(), from.firstname() ) );
            message.setRecipient( Message.RecipientType.TO, new InternetAddress( to.email() ) );
            message.setSubject( REMINDER_MESSAGE_SUBJECT );
            message.setContent( new MimeMultipart( MIME_TYPE, new MimeBodyPart( new ByteArrayInputStream( MESSAGE_TO_SEND_FOR_REMINDER.formatted( to.firstname(), contactWhoseBirthdayIsToday.getFullName()).getBytes() ) ) ) );

            return "Email sent successfully";
        } catch( MessagingException | UnsupportedEncodingException e ) {
            throw new RuntimeException( e );
        }
    }

    private String parseContactNameListToString ( List<String> names ) {
        StringBuilder nameListToString = new StringBuilder();
        for(String name : names ){
            nameListToString.append( "%s,".formatted( name ) );
        }

        return nameListToString.toString();
    }

    private Session setSession() {
        Properties properties = new Properties();
        Properties smtpProperties = new Properties();
        try {
            properties.load( this.getClass().getClassLoader().getResourceAsStream( PROPERTY_FILE_NAME ) );
            smtpProperties.put( "mail.smtp.host", properties.get( "smtp.host" ).toString() );
            smtpProperties.put( "mail.smtp.port", properties.get( "smtp.port" ).toString() );
            smtpProperties.put( "mail.smtp.starttls.enable", properties.get( "smtp.starttls.enable" ).toString() );
            smtpProperties.put( "mail.smtp.auth", properties.get( "smtp.auth" ).toString() );
            return Session.getInstance( smtpProperties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication () {
                    return new PasswordAuthentication( properties.get("smtp.username").toString(), properties.get( "smtp.password" ).toString() );
                }
            } );
        } catch( IOException e ) {
            throw new RuntimeException( e );
        }
    }
}
