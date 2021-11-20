package com.github.elribeiro.products.message;

public enum Messages {
    ERROR_SAVE_DATABASE("Could not save on database, details: ");

    private String message;

    Messages(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
