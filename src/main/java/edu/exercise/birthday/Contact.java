package edu.exercise.birthday;

import java.time.LocalDate;

public record Contact (String lastname, String firstname, LocalDate birthday, String email){
    static Contact parseToContact(String... contactFields) {
        return new Contact( contactFields[ 0 ], contactFields[ 1 ], LocalDate.parse(contactFields[ 2 ]), contactFields[ 3 ] );
    }

    public String getFullName() {
        return this.firstname + " " + this.lastname;
    }

    public boolean birthdayIsToday () {
        var today = LocalDate.now();
        return this.birthday.getMonthValue() == today.getMonthValue() && this.birthday.getDayOfMonth() == today.getDayOfMonth();
    }

    public String toString() {
        return this.lastname + "-" + this.firstname + "-" + this.birthday + "-" + this.email;
    }
}

