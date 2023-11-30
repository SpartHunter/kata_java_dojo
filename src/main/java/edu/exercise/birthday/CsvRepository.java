package edu.exercise.birthday;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

public class CsvRepository implements Repository{

    private static final String FILENAME = "contacts_data.csv";
    @Override
    public List<Contact> getAllContacts () {
        try( InputStreamReader fileReader = new InputStreamReader( Objects.requireNonNull( this.getClass().getClassLoader().getResourceAsStream( FILENAME ) ) ) ) {
            return new BufferedReader( fileReader ).lines().map( this::parseToContact ).toList();
        } catch( IOException e) {
           throw new RuntimeException(e.getCause());
        }
    }

    private Contact parseToContact ( String lineData ) {
        String[] lineDataSplit = lineData.split( "," );
        return Contact.parseToContact( lineDataSplit );
    }
}
