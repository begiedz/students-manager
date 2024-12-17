package org.studentsManager.src.exceptions;

public class NoStudentsFoundException extends RuntimeException{
    public NoStudentsFoundException(String message){
        super(message);
    }
}
