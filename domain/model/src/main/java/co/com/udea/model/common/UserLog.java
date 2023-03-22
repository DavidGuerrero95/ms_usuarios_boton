package co.com.udea.model.common;

import java.io.Serializable;

public enum UserLog implements Serializable {

    USER_LOG_REGUSER ("log_reguser"),
    USER_LOG_LOGIN ("log_login"),
    USER_LOG_MANAGE_IOT_CONTAINER ("iot_container"),
    USER_LOG_MANAGE_IOT_DEVICE ("iot_device"),
    USER_LOG_MANAGE_BROKER ("broker"),
    USER_LOG_MANAGE_DB ("db"),
    USER_LOG_MANAGE_DATAANALYTICS ("dataanalytics"),
    USER_LOG_MANAGE_MY_ACCOUNT ("my_account"),
    USER_LOG_MANAGE_USER_ACCOUNTS ("user_accounts"),
    USER_LOG_MANAGE_ROLES ("roles"),
    USER_LOG_MANAGE_ORGS ("orgs");

    private String value;

    UserLog(String valor) {
        this.value = valor;
    }

    public String getValue() {return value;}

    public void setValue(String value) {
        this.value = value;
    }
}
