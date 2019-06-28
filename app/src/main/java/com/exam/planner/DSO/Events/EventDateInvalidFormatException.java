package com.exam.planner.DSO.Events;

public class EventDateInvalidFormatException extends EventValidationException{
    EventDateInvalidFormatException(String message){
        super(message);
    }
}
