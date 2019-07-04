package com.exam.planner.Logic.Events;

public class EventDateInvalidFormatException extends EventValidationException{
    EventDateInvalidFormatException(String message){
        super(message);
    }
}
