package com.ipiecoles.java.mdd050.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ipiecoles.java.mdd050.model.Employe;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class EmployeException extends Throwable {
    public static final String ID = "L'identifiant passé ne correspond pas à l'identifiant de l'employé : ";
    
    public EmployeException(String message, Employe employe, Object valeurIncorrecte) {
        super(message + valeurIncorrecte + ", employe : " + employe.toString());
        System.out.println(this.getMessage());
    }

    public EmployeException(String message) {
        super(message);
        System.out.println(this.getMessage());
    }
}
