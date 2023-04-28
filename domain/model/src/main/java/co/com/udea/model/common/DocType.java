package co.com.udea.model.common;

import java.io.Serializable;

public enum DocType implements Serializable {

    DOCTYPE_IDENTITY_CARD("identity_card"),
    DOCTYPE_FOREIGNER_IDENTITY_CARD("foreigner_identity_card"),
    DOCTYPE_PASSPORT("passport");

    private String value;
    DocType(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
}
