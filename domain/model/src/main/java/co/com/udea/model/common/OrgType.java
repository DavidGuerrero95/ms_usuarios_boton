package co.com.udea.model.common;

import java.io.Serializable;

public enum OrgType implements Serializable {

    ORGTYPE_PLATFORM ("platform"),
    ORGTYPE_SECURITY ("security"),
    ORGTYPE_GLOBAL ("global");

    private String value;

    OrgType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
