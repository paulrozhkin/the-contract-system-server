package com.itmo.goblinslayersystemserver;

import com.itmo.goblinslayersystemserver.services.RfcToCalendarConverter;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RfcConverterTest {
    @Test
    public void Should_ReturnCalendar_When_StringRfcCorrect() throws ParseException {

        // Given
        String rfcString = "2020-05-19T16:00:00Z";
        long rfcTimestamp = 1589904000;

        // When
        Calendar calendar = RfcToCalendarConverter.convert(rfcString);

        // Then
        assertEquals(rfcTimestamp, calendar.toInstant().getEpochSecond());
    }

    @Test
    public void Should_ReturnCalendar_When_StringRfcWithTimeZoneCorrect() throws ParseException {
        // Given
        String rfcString = "2020-05-19T19:00:00+03:00";
        long rfcTimestamp = 1589904000;

        // When
        Calendar calendar = RfcToCalendarConverter.convert(rfcString);

        // Then
        assertEquals(rfcTimestamp, calendar.toInstant().getEpochSecond());
    }

    @Test
    public void Should_ReturnCalendar_When_StringRfcWithMillisecond() throws ParseException {
        // Given
        String rfcString = "2020-05-28T16:15:16.68+00:00";

        // When
        Exception convertException = null;
        try {
            Calendar calendar = RfcToCalendarConverter.convert(rfcString);
        } catch (Exception e) {
            convertException = e;
        }

        // Then
        assertNull(convertException);
    }
}
