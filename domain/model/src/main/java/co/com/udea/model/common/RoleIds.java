package co.com.udea.model.common;

import java.io.Serializable;

public enum RoleIds implements Serializable {

    ROLE_PLATFORM_ADMIN ("admin"),
    ROLE_PLATFORM_DEVELOPER_ADMIN ("developer_admin"),
    ROLE_PLATFORM_DEVELOPER ("developer"),
    ROLE_CITIZEN ("citizen"),
    ROLE_SECURITY_OFFICE_ADMIN ("office_admin"),
    ROLE_PUBLIC_FORCE_MEMBER ("public_force_member"),
    ROLE_PRIVATE_FORCE_MEMBER ("private_force_member");

    private String value;

    RoleIds(String valor) {
        this.value = valor;
    }

    public String getValue() {return value;}

    public void setValue(String value) {
        this.value = value;
    }
}
