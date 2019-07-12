package com.exam.planner.Presentation.CalendarPage;

public class CalendarFormatter {
    public static String dateToString(int year, int month, int day) {
        return year + "/" + month + "/" + day;
    }

    public static String timeToString(int hour, int minute) {
        String ret = hour + ":";
        if (minute < 10)
            ret += "0";
        ret += minute;
        return ret;
    }

    public static int[] dateToInt(String date) throws CalendarInvalidFormatException {
        try {
            validateDateFormat(date);
            String[] split = date.split("/");
            int[] ret = new int[3];
            ret[0] = Integer.parseInt(split[0]);
            ret[1] = Integer.parseInt(split[1]);
            ret[2] = Integer.parseInt(split[2]);
            return ret;
        } catch (CalendarInvalidFormatException e) {
            throw(e);
        }
    }

    public static int[] timeToInt(String time) throws CalendarInvalidFormatException {
        try {
            validateTimeFormat(time);
            String[] split = time.split(":");
            int[] ret = new int[2];
            ret[0] = Integer.parseInt(split[0]);
            ret[1] = Integer.parseInt(split[1]);
            return ret;
        } catch (CalendarInvalidFormatException e) {
            throw(e);
        }
    }

    private static void validateDateFormat(String date) throws CalendarInvalidFormatException {
        String[] split = date.split("/");
        if (split.length != 3)
            throw(new CalendarInvalidFormatException("Date must follow the format YYYY/MM/DD"));
        for (int i = 0; i<3; i++){
            if(split[i].length() == 0)
                throw(new CalendarInvalidFormatException("Date must follow the format YYYY/MM/DD"));
        }
    }

    private static void validateTimeFormat(String time) throws CalendarInvalidFormatException {
        String[] split = time.split(":");
        if (split.length != 2)
            throw(new CalendarInvalidFormatException("Time must follow the format HH:MM"));
        for (int i = 0; i<2; i++){
            if(split[i].length() == 0)
                throw(new CalendarInvalidFormatException("Time must follow the format HH:MM"));
        }
    }
}

class CalendarInvalidFormatException extends Exception {
    CalendarInvalidFormatException(String message){
        super(message);
    }
}