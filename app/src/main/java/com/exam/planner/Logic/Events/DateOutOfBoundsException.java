package com.exam.planner.Logic.Events;

public class DateOutOfBoundsException extends DateTimeValidationException{
    DateOutOfBoundsException(String message){
        super(message);
    }
}
