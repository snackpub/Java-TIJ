package com.snackpub.core.reflection.annotations;


public @interface Uniqueness {

    Constraints constraints() default @Constraints;
}
