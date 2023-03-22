package co.com.udea.model.common;

import java.io.Serializable;

public enum GenderType implements Serializable {

    GENDER_MALE ("male"),
    GENDER_FEMALE ("female"),
    GENDER_BISEXUAL ("bisexual"),
    GENDER_OTHER ("other");

    private String value;

    GenderType(String valor) {
        this.value = valor;
    }

    public String getValue() {return value;}

    public void setValue(String value) {
        this.value = value;
    }
}
