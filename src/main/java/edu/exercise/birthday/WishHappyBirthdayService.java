package edu.exercise.birthday;

public class WishHappyBirthdayService {
    private final SendService sendService;

    public WishHappyBirthdayService ( SendService sendService ) {
        this.sendService = sendService;
    }

    public String send ( Contact to, Contact from ) {
        return sendService.sendToSingleContact( to, from );
    }
}
