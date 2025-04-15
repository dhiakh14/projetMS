package com.example.sprinproject.email;

import lombok.Getter;

@Getter

public enum EmailTemplateName {
<<<<<<< HEAD
    ACTIVATE_ACCOUNT("activate_account") ,
    RESET_PASSWORD("reset_password");

=======
    ACTIVATE_ACCOUNT("activate_account") ;
>>>>>>> origin/lahmer
    private final String name ;

    EmailTemplateName(String name) {
        this.name = name;
    }
}

