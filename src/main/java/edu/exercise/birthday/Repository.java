package edu.exercise.birthday;

import java.io.IOException;
import java.util.List;

public interface Repository {
    List<Contact> getAllContacts () throws IOException;
}
