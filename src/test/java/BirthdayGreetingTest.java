import edu.exercise.birthday.Contact;
import edu.exercise.birthday.CsvRepository;
import edu.exercise.birthday.SendByEmailService;
import edu.exercise.birthday.WishHappyBirthdayService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class BirthdayGreetingTest {

    @Test
    void shouldReadContactsInCsvFile() {
        var contact1 = new Contact( "Steve", "Barnett", LocalDate.parse( "1998-11-30" ), "ka@localtest.com" );
        var contact2 = new Contact( "Leonard", "Austin", LocalDate.parse( "1961-12-01" ), "put@localtest.com" );
        var contact3 = new Contact( "Carl", "McGuire", LocalDate.parse( "1958-11-30" ), "zuwi@localtest.com" );
        var expectedContacts = Arrays.asList( contact1, contact2, contact3 );

        var contacts = new CsvRepository().getAllContacts();

        assertThat(contacts.size()).isEqualTo( 3 );
        assertThat( contacts ).containsAll( expectedContacts );
    }

    @Test
    void shouldFindContactsWithBirthdayIsToday() {
        var contact = new Contact( "Steve", "Barnett", LocalDate.parse( "1998-11-30" ), "ka@localtest.com" );
        assertThat(contact).matches( Contact::birthdayIsToday );
    }

    @Test
    void shouldWishHappyBirthdayBySendEmail () {
        var recipientContact = new Contact( "Steve", "Barnett", LocalDate.parse( "1998-11-30" ), "ka@localtest.com" );
        var senderContact = new Contact( "Steve", "Barnett", LocalDate.parse( "1998-11-30" ), "ka@localtest.com" );
        var sendService = new SendByEmailService();
        var message = new WishHappyBirthdayService( sendService).sendHappyBirthday( recipientContact, senderContact );
        assertThat( message ).isEqualTo( "Email sent successfully" );
    }
}
