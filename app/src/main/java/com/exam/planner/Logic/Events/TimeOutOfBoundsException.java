package com.exam.planner.Logic.Events;

public class TimeOutOfBoundsException extends DateTimeValidationException{
    TimeOutOfBoundsException(String message){
        super(message);
    }
}
