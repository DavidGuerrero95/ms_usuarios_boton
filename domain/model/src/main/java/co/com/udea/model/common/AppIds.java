package co.com.udea.model.common;

import java.io.Serializable;

public enum AppIds implements Serializable {

    APP_PANIC_BUTTON("panic_button"),
    APP_CRIME_REPORT("crime_report"),
    APP_ALERTS_RECEPTION("alerts_reception"),
    APP_ALERTS_MANAGEMENT("alerts_management"),
    APP_HOT_ZONES("hot_zones"),
    APP_SUPERAPP_CITY("superapp_city"),
    APP_PLATFORM_MANAGEMENT("platform_management");

    private String value;

    AppIds(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
