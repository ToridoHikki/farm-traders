package vn.silverbot99.core.base.config;

public interface ConfigSaver {
    String CONFIG_PAGER = "config_pager";

    String CONFIG_SETTING_SAVED_IS_SHOW_DIALOG_WARNING_LOGGED_OTHER_DEVICE = "config_setting_saved_is_show_dialog_warning_logged_other";
    String CONFIG_SETTING_RES_ID = "config_setting_resId_update";
    String CONFIG_SETTING_SAVED_IS_FIRST_LOGIN_APP = "config_setting_saved_is_first_login_app";
    String CONFIG_SETTING_PROFILE = "config_setting_saved_profile";
    String CONFIG_SETTING_PASSPORT = "config_setting_saved_passport";
    String CONFIG_SETTING_KEY_LOGIN = "config_setting_saved_key_login";

    String CONFIG_SETTING_DATE_TIME = "config_setting_saved_date_time";
    String CONFIG_SETTING_ORDER_TYPE = "config_setting_saved_order_type";
    String CONFIG_SETTING_CONTAINER_TYPE = "config_setting_container_type";
    String CONFIG_SETTING_PUSH_TOKEN= "config_setting_saved_push_token";

    String CONFIG_SETTING_USER_UID = "config_setting_passpord_user_uid";



    void save(String key, Object data);

    <T> T get(String key);

    void delete(String key);

    void deleteAll();
}
