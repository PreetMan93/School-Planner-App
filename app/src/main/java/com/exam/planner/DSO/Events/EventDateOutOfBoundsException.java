package com.exam.planner.DSO.Events;

public class EventDateOutOfBoundsException extends EventValidationException{
    EventDateOutOfBoundsException(String message){
        super(message);
    }
}
