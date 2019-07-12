package com.exam.planner.CalendarTesting;

import com.exam.planner.Presentation.CalendarPage.CalendarFormatter;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CalendarFormatterTest {

    @Test
    public void toStringFormatting() {
        assertEquals("1900/1/1", CalendarFormatter.dateToString(1900, 1, 1));
        assertEquals("20000/2000/200", CalendarFormatter.dateToString(20000, 2000, 200));

        assertEquals("12:01", CalendarFormatter.timeToString(12, 1));
        assertEquals("9:10", CalendarFormatter.timeToString(9, 10));
        assertEquals("0:00", CalendarFormatter.timeToString(0, 0));
        assertEquals("9999:9999", CalendarFormatter.timeToString(9999, 9999));
    }

    @Test
    public void toDateFormattingSuccess() {
        try {
            int[] a = CalendarFormatter.dateToInt("1900/1/1");
            assertEquals(a[0], 1900);
            assertEquals(a[1], 1);
            assertEquals(a[2], 1);
        } catch (Exception e) {
            fail();
        }
    }

    @Test (expected = Exception.class)
    public void toDateFormattingFailure() throws Exception {
        CalendarFormatter.dateToInt("/1/1");
    }

    @Test (expected = Exception.class)
    public void toDateFormattingFailure2() throws Exception {
        CalendarFormatter.dateToInt("1//1");
    }

    @Test (expected = Exception.class)
    public void toDateFormattingFailure3() throws Exception {
        CalendarFormatter.dateToInt("1/1/");
    }

    @Test (expected = Exception.class)
    public void toDateFormattingFailure4() throws Exception {
        CalendarFormatter.dateToInt("1/1");
    }

    @Test (expected = Exception.class)
    public void toDateFormattingFailure5() throws Exception {
        CalendarFormatter.dateToInt("1");
    }

    @Test
    public void toTimeFormattingSuccess() {
        try {
            int[] a = CalendarFormatter.timeToInt("10:01");
            assertEquals(a[0], 10);
            assertEquals(a[1], 1);
        } catch (Exception e) {
            fail();
        }
    }

    @Test (expected = Exception.class)
    public void toTimeFormattingFailure() throws Exception {
        CalendarFormatter.timeToInt("1:");
    }

    @Test (expected = Exception.class)
    public void toTimeFormattingFailure2() throws Exception {
        CalendarFormatter.timeToInt(":1");
    }

    @Test (expected = Exception.class)
    public void toTimeFormattingFailure3() throws Exception {
        CalendarFormatter.timeToInt(":");
    }

    @Test (expected = Exception.class)
    public void toTimeFormattingFailure4() throws Exception {
        CalendarFormatter.timeToInt("10");
    }
}