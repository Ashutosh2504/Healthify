/*
 * Created by Jaysen Patil
 * Copyright (c) 2017.  All rights reserved.
 */

package com.example.healthifyapp.SharedPreferences;

public class rigvAppConstants {

    public static boolean isDebugMode = true;
    public static boolean isDebugModeFCM = false; //set false for FCM token updated

    public static int DocWaletTotalSize = 10240; //in kb

    //db Information
    public static final String DATABASE_NAME = "rigvapp_log.db";
    
    public static final int DATABASE_VERSION = 37;  ////// set 36 value for version 5.7.0.3

    public static final String DATABASE_NAME_Report = "rigvapp_report_log.db";
    public static final int DATABASE_VERSION_Report = 7;  ////// set 6 value for version 5.5.1

    public static final String DATABASE_NAME_Report_Temp = "rigvapp_report_temp_log.db";


    //server username and passsword
    public static String userName = "rapi";
    public static String password = "Serving@world@@";

    public static String AESSeedKey = "@.dkvbD2008:nanajvaas161982~";
    public static String SHA1SeedKey = "@.dkvbD2008:nanajvaas161982~";

///

    //Server Information
    public static String serverLink = "http://rigvapp.net/";
//    public static String serverLink = "http://development.dolphindls.net/apiNew/";

//    public static String serverLinkMain = "http://mmitratest.dolphindls.net/api/";
    public static String serverDevLinkMain = "http://serverdev.dolphindls.net/api/";
    public static String serverLinkMain = "http://spine.dolphindls.net/api/";

    public static String serverFileLink = "http://rigvapp.net/";
//    public static String serverFileLink = "http://development.dolphindls.net/api/";

    public static String Serveradd = serverLink + "index.php/Atlantic/api/v17/";
    public static String ServeraddMain = serverLinkMain;

    public static String ServerProfileImageAdd = serverFileLink + "assets/savefiles/profile/";
    public static String ServerAddressBookImageAdd = serverFileLink + "assets/savefiles/addressbook/address/";
    public static String ServerFullDocumentWaletAdd = serverFileLink + "assets/savefiles/wallet/";
    public static String ServerCountryFlagImage = serverFileLink + "assets/savefiles/countryflag/";
    public static String ServerAddrCategoryImage = serverFileLink + "assets/savefiles/addrcategory/";
    public static String ServerSmileyPreviewImage = serverFileLink + "assets/savefiles/smiley/TalkSmileyPreview/";
    public static String ServerSmileyOGG = serverFileLink + "assets/savefiles/smiley/";
    public static String ServerDriverAadharImage = serverFileLink + "assets/savefiles/drivers/driverAadhar/";
    public static String ServerDriverLicImage = serverFileLink + "assets/savefiles/drivers/driverLic/";
    public static String ServerDriverImage = serverFileLink + "assets/savefiles/drivers/drivers/";

    //Address Book
    public static final int MAX_GROUP_LIMIT = 100;

    //xmpp link
    public static String serverXmppName = "im.dolphindls.net";
    public static int serverXmppPort = 5225;
    public static String xmppResourseName = "RigvAppAnd";


    public static final String Msg_Broadcast_ACTION = "com.dolphin.kuth.pushnotifications.MessageBroadcastService";


    //GPS Server Parameters
    public static final String KEY_ADDRESS = "mams.dolphindls.net";
    public static final int KEY_PORT = 5005;
    public static final int KEY_INTERVAL = 10; //10 sec
    public static final String KEY_PROVIDER = "mixed";  // gps, network, mixed
    public static final boolean KEY_EXTENDED = false;

    //Phonebook contact mine Type
    public static String MimeTypeAddrProfile = "vnd.android.cursor.item/com.dolphin.rigvapp.addrprofile";

    //Notification constant
    public static final String NOTI_SETTING_ACTION = "NOTI_SETTING_ACTION";
    //Share and View report constant
    public static final String SHARE_REPORT_ACTION = "SHARE_REPORT_ACTION";
    public static final String VIEW_REPORT_ACTION = "VIEW_REPORT_ACTION";

    public static final int SERVICE_RESTART_TIMER = 600;
    public static final int SERVICE_RESTART_TIMER_DESTROY = 1;
    public static final int SERVICE_POST_EXECUTE_TIMER = 100;

    public static final String PAYTM_MID = "Dolphi16522590498866";

    public static final int LAST_SEEN_DAYS_COUNT = 3;
    public static final int EXPIRY_WARNING_DAYS_COUNT = 35;
    public static final String NOTIFICATION_TYPE_PASSENGERS = "passengers";
    public static final String TYPE_STATION = "STATION";

    public static final long EIGHT_HOURS_IN_MILLIS = 8 * 60 * 60 * 1000;
    public static final long WEEK_IN_MILLIS = 7 * 24 * 60 * 60 * 1000;
    public static final long MONTH_IN_MILLIS = 30L * 24 * 60 * 60 * 1000;
    public static final long YEAR_IN_MILLIS = 365L * 24 * 60 * 60 * 1000;

}
