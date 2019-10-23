package vn.minerva.travinh.app.common;

import android.Manifest;

public interface AppConstants {

    String[] PERMISSIONS_IN_APP = {
//            Manifest.permission.INTERNET,
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
    };

    /**
     *
     * Thay gia tri TRUE vao de cho phep thuc hien tac vu o ngay qua khu
     *
     *
     * */
    boolean IS_ALLOW_YESTERDAY_WORK = false;

    int ORDER_TAP_TRUNG = 1;
    int ORDER_DA_PHUONG = 2;
    int DATE_PICKER_DIALOG_ID = 3;
    int REQUEST_CODE_ORDER_TO_SCAN_QRCODE = 4;
    int ORDER_MULTI_COOPMART_DEFAULT = 1;

    int REQUEST_SUPPLIER_ADD_VEHICLE = 10;
    int REQUEST_SUPPLIER_ADD_DRIVER = 11;
    int REQUEST_SUPPLIER_ORDER_PLAN = 12;
    int REQUEST_CODE_SUPPLIER_PRODUCT_DETAIL = 13;
    int REQUEST_CODE_ORDER_PRODUCT_PACKING = 14;
    int REQUEST_CODE_TRANSPORT_ORDER_PRODUCT = 15;
    int REQUEST_CODE_ORDER = 16;
    int REQUEST_CODE_PROFILE = 17;

    int DRIVER_IMAGE_TYPE_CONTRACT = 5; // Chung nhan hop dong xe
    int DRIVER_IMAGE_TYPE_LICENSE = 1; // Mat truoc bang lai
    int DRIVER_IMAGE_TYPE_CMND = 3; // Mat truoc CMND

    int FORCE_LOGIN_USER_NOT_SUPPORTED = 1;
    int FORCE_LOGIN_TOKEN = 2;
    int FORCE_LOGIN_DEFAULT = 0;
    int CONVERSATION_SUPPLIER_SUPERMARKET = 1;
    int CONVERSATION_SUPPLIER_WAREHOUSE = 2;
    int CONVERSATION_SUPERMARKET_SUPPLIER = 3;
    int CONVERSATION_SUPERMARKET_WAREHOUSE = 4;

    int SETTINGS_NHAN_DON_HANG = 1;
    int SETTINGS_NHAN_BAO_GIA = 2;
    int SETTINGS_XE_GAP_SU_CO = 3;
    int SETTINGS_XAC_NHAN_HANG_HOA = 4;

    String RULE_SUPPLIER = "supplier";
    String RULE_WAREHOUSE = "warehouse";
    String RULE_SUPERMARKET = "supermarket";
    String REGEX_LIST_INT_SEPARATED_COMMA = "(\\d+)(,\\s*\\d+)*";

    String CHATTING_RABBIT_URI = "amqp://coopmart:coopmart@118.69.82.125:25672/chatting";
    String CHATTING_EXCHANGE = "messages";

    String RABBIT_HOST = "sgc.minerva.vn";
    int RABBIT_PORT = 25672;
    String RABBIT_VHOST = "chatting";
    String RABBIT_USER = "coopmart";
    String RABBIT_PASSWORD = "coopmart";
    String RABBIT_EXCHANGE = "messages";


}
