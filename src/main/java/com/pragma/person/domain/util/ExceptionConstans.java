package com.pragma.person.domain.util;

public class ExceptionConstans {


    public static final String FIRST_PART_MESSAGE_EXCEPTION = "Message" ;
    public static final String STATUS_MESSAGE_EXCEPTION = "Error";
    public static final String BOOTCAMP_NOT_COUNT_MIN_MAX = "Bootcamp count must be " + ValueConstants.MIN_COUNT_BOOTCAMP + " - " + ValueConstants.MAX_COUNT_BOOTCAMP ;
    public static final String BOOTCAMP_DUPLICATE = "Duplicate Bootcamp , Id: " ;
    public static final String BOOTCAMP_NOT_FOUND = "Bootcamp not found, id: " ;
    public static final String PERSON_NOT_FOUND = "Person not found, id: " ;
    public static final String BOOTCAMP_OVERLAP = "Bootcamps Overlap ,  %d and  %d " ;

    public static final String PERSON_NAME_REQUIRED= "Name must not be empty";
    public static final String PERSON_EMAIL_REQUIRED = "Email not in correct format";
    public static final String PERSON_AGE_REQUIRED= "Age is required";

    public static final String PERSON_ID_REQUIRED= "Name must not be empty";
    public static final String BOOTCAMPS_ID_REQUIRED= "Bootcamps IDS must not be empty";
}
