package com.exam.planner.Logic.Events;

public class EventDateOutOfBoundsException extends EventValidationException{
    EventDateOutOfBoundsException(String message){
        super(message);
    }
}
