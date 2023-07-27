/*
 * Created by Jaysen Patil
 * Copyright (c) 2017.  All rights reserved.
 */

package com.example.healthifyapp.SharedPreferences;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import org.json.JSONArray;
import org.json.JSONException;

public class MySharedPreferences {

    public static String APP_SHARED_PREFS = "rigvApp_pref";
    private SharedPreferences objComman = null;
    private Editor prefsEditor;

    public MySharedPreferences(Context context) {
        objComman = context.getSharedPreferences(APP_SHARED_PREFS, Activity.MODE_PRIVATE);
    }


    // Get Version of Application
    public String getServerCurrentVersionName() {
        return objComman.getString("ServerCurrentVersionName", "");
    }

    public void setServerCurrentVersionName(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("ServerCurrentVersionName", text);
        prefsEditor.apply();
    }

    // Get Version of Application
    public String getMobileCurrentVersionCode() {
        return objComman.getString("MobileCurrentVersionCode", "");
    }

    public void setMobileCurrentVersionCode(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("MobileCurrentVersionCode", text);
        prefsEditor.apply();
    }

    // Server Version of Application
    public int getServerCurrentVersionCode() {
        return objComman.getInt("ServerVersionCode", 0);
    }

    public void setServerCurrentVersionCode(int text) {
        prefsEditor = objComman.edit();
        prefsEditor.putInt("ServerVersionCode", text);
        prefsEditor.apply();
    }

    // Is App Version Expire Skip
    public boolean getIsAppVersionExpireSkip() {
        return objComman.getBoolean("IsAppVersionExpireSkip", false);
    }

    public void setIsAppVersionExpireSkip(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("IsAppVersionExpireSkip", text);
        prefsEditor.apply();
    }

    // Last date of update version change
    public long getLastDateVersionCodeChange() {
        return objComman.getLong("LastDateVersionCodeChange", 0);
    }

    public void setLastDateVersionCodeChange(long text) {
        prefsEditor = objComman.edit();
        prefsEditor.putLong("LastDateVersionCodeChange", text);
        prefsEditor.apply();
    }

    // Last time of Licence Check call
    public long getLastTimeLicenceCheckCall() {
        return objComman.getLong("LastTimeLicenceCheckCall", 0);
    }
    public void setLastTimeLicenceCheckCall(long text) {
        prefsEditor = objComman.edit();
        prefsEditor.putLong("LastTimeLicenceCheckCall", text);
        prefsEditor.apply();
    }

    // Last time of Station List Check call
    public long getLastTimeStationCheckCall() {
        return objComman.getLong("LastTimeStationCheckCall", 0);
    }
    public void setLastTimeStationCheckCall(long text) {
        prefsEditor = objComman.edit();
        prefsEditor.putLong("LastTimeStationCheckCall", text);
        prefsEditor.apply();
    }

    // IS Discalamer accept
    public boolean getISAppDisclamerAccepted() {
        return objComman.getBoolean("ISAppDisclamerAccepted", false);
    }

    public void setISAppDisclamerAccepted(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("ISAppDisclamerAccepted", text);
        prefsEditor.apply();
    }

    // is App Running
    public boolean getISAppRunning() {
        return objComman.getBoolean("ISAppRunning", false);
    }

    public void setISAppRunning(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("ISAppRunning", text);
        prefsEditor.apply();
    }

    // is IS App Gyroscope Enable
    public boolean getISAppGyroscopeEnable() {
        return objComman.getBoolean("ISAppGyroscopeEnable", false);
    }

    public void setISAppGyroscopeEnable(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("ISAppGyroscopeEnable", text);
        prefsEditor.apply();
    }

    // is App Auto Start
    public boolean getISAppAutoStart() {
        return objComman.getBoolean("ISAppAutoStart", true);
    }

    public void setISAppAutoStart(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("ISAppAutoStart", text);
        prefsEditor.apply();
    }

    // Last Dashboard Type Open
    public String getLastDashboardTypeOpen() {
        String result = objComman.getString("LastDashboardTypeOpen", "");
        if (result.isEmpty()) {
            //result = "addressPBDashboard";
            result = "deviceDashboard";
        }

        return result;
    }

    public void setLastDashboardTypeOpen(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("LastDashboardTypeOpen", text);
        prefsEditor.apply();
    }

    //User HTTP Response code
    public int getHttpResponseCode() {
        return objComman.getInt("HttpResponseCode", 0);
    }

    public void setHttpResponseCode(int text) {
        prefsEditor = objComman.edit();
        prefsEditor.putInt("HttpResponseCode", text);
        prefsEditor.apply();
    }

    //User HTTP Response Message
    public String getHttpResponseMessage() {
        return objComman.getString("HttpResponseMessage", "");
    }

    public void setHttpResponseMessage(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("HttpResponseMessage", text);
        prefsEditor.apply();
    }

    //is RigV Chat Indicator
    public boolean getIsChatIndicator() {
        return objComman.getBoolean("IsChatIndicator", false);
    }

    public void setIsChatIndicator(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("IsChatIndicator", text);
        prefsEditor.apply();
    }

    //is RigV RouteTracker Indicator
    public boolean getIsTrackerIndicator() {
        return objComman.getBoolean("IsTrackerIndicator", false);
    }
    public void setIsTrackerIndicator(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("IsTrackerIndicator", text);
        prefsEditor.apply();
    }
    // Current Time Zoze
    public String getTimeZone() {
        return objComman.getString("TimeZone", "");
    }
    public void setTimeZone(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("TimeZone", text);
        prefsEditor.apply();
    }
    // Current Time Zone ID
    public String getTimeZoneID() {
        return objComman.getString("TimeZoneID", "");
    }
    public void setTimeZoneID(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("TimeZoneID", text);
        prefsEditor.apply();
    }


    // //////////// Database parameter
    // ISDatabaseWriteMode
    public boolean getISDatabaseWriteMode() {
        return objComman.getBoolean("ISDatabaseWriteMode", false);
    }

    public void setISDatabaseWriteMode(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("ISDatabaseWriteMode", text);
        prefsEditor.apply();
    }

    // previous version of database
    public int getPreviousDBVersion() {
        return objComman.getInt("PreviousDBVersion", 0);
    }

    public void setPreviousDBVersion(int text) {
        prefsEditor = objComman.edit();
        prefsEditor.putInt("PreviousDBVersion", text);
        prefsEditor.apply();
    }

    // previous version of database
    public int getPreviousDBVersionReport() {
        return objComman.getInt("PreviousDBVersionReport", 0);
    }

    public void setPreviousDBVersionReport(int text) {
        prefsEditor = objComman.edit();
        prefsEditor.putInt("PreviousDBVersionReport", text);
        prefsEditor.apply();
    }
    // ////////////End Database parameter


    ///////////// Generral parameter
    // KeyboardHeight
    public int getKeyboardHeight() {
        return objComman.getInt("KeyboardHeight", 0);
    }

    public void setKeyboardHeight(int text) {
        prefsEditor = objComman.edit();
        prefsEditor.putInt("KeyboardHeight", text);
        prefsEditor.apply();
    }

    //XMPP Registration Done
    public boolean getXMPPRegistrationDone() {
        return objComman.getBoolean("XMPPRegistrationDone", false);
    }

    public void setXMPPRegistrationDone(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("XMPPRegistrationDone", text);
        prefsEditor.apply();
    }

    // method for Xmpp Msg Pending
    public void setIsXmppMsgPending(boolean isMessageOn) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("IsXmppMsgPending", isMessageOn);
        prefsEditor.apply();
    }

    public boolean getIsXmppMsgPending() {
        return objComman.getBoolean("IsXmppMsgPending", false);
    }

    // Last Periodic check condition time
    public void setLastPeriodicCondCheckTime(long time) {
        prefsEditor = objComman.edit();
        prefsEditor.putLong("LastPeriodicCondCheckTime", time);
        prefsEditor.apply();
    }

    public long getLastPeriodicCondCheckTime() {
        return objComman.getLong("LastPeriodicCondCheckTime", 0);
    }

    // Last Periodic Report check condition time
    public void setLastReportPeriodicCondCheckTime(long time) {
        prefsEditor = objComman.edit();
        prefsEditor.putLong("LastReportPeriodicCondCheckTime", time);
        prefsEditor.apply();
    }

    public long getLastReportPeriodicCondCheckTime() {
        return objComman.getLong("LastReportPeriodicCondCheckTime", 0);
    }

    // Last Periodic Notification check condition time
    public void setLastNotificationPeriodicCondCheckTime(long time) {
        prefsEditor = objComman.edit();
        prefsEditor.putLong("LastNotificationPeriodicCondCheckTime", time);
        prefsEditor.apply();
    }
    public long getLastNotificationPeriodicCondCheckTime() {
        return objComman.getLong("LastNotificationPeriodicCondCheckTime", 0);
    }


    // Last Notification Tone Play
    public void setLastNotificationTonePlayTime(long time) {
        prefsEditor = objComman.edit();
        prefsEditor.putLong("LastNotificationTonePlayTime", time);
        prefsEditor.apply();
    }

    public long getLastNotificationTonePlayTime() {
        return objComman.getLong("LastNotificationTonePlayTime", 0);
    }

    //Recent Buzz list
    public void setRecentBuzzList(String str) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("RecentBuzzList", str);
        prefsEditor.apply();
    }

    public String getRecentBuzzList() {
        return objComman.getString("RecentBuzzList", "");
    }
    ///////////////////end general parameter


    // /////////User Information
    //Mobile ID of user
    public int getMobileID() {
        return objComman.getInt("MobileID", 0);
    }

    public void setMobileID(int text) {
        prefsEditor = objComman.edit();
        prefsEditor.putInt("MobileID", text);
        prefsEditor.apply();
    }

    //Device ID of user
    public String getMobileDeviceID(Context mContext) {
        String deviceID = objComman.getString("DeviceID", "");

        if(rigvAppConstants.isDebugMode) deviceID = "000000000000000";

        if (deviceID.isEmpty()) {
            try {
                TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
                deviceID = tm.getDeviceId();
                if (deviceID == null) deviceID = "";

                if (deviceID == null || deviceID.length() == 0)
                    deviceID = Settings.Secure.getString(mContext.getContentResolver(), Settings.Secure.ANDROID_ID);
                if (deviceID == null) deviceID = "";
            } catch (Exception e) {
                deviceID = "";
            }

            if (!deviceID.isEmpty())
                setMobileDeviceID(deviceID);
            else
                setMobileDeviceID(getMobileNo());
        }

        return deviceID;
    }

    public void setMobileDeviceID(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("DeviceID", text);
        prefsEditor.apply();
    }

    public String getTempMobileDeviceID() {
        return objComman.getString("TempPhoneDeviceID", "");
    }
    public void setTempMobileDeviceID(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("TempPhoneDeviceID", text);
        prefsEditor.apply();
    }


    //Server Device ID of user
    public String getServerPhoneDeviceID() {
        return objComman.getString("ServerPhoneDeviceID", "");
    }

    public void setServerPhoneDeviceID(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("ServerPhoneDeviceID", text);
        prefsEditor.apply();
    }

    //is User Login Expire
    public boolean getIsUserLoginExpire() {
        return objComman.getBoolean("IsUserLoginExpire", false);
    }

    public void setIsUserLoginExpire(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("IsUserLoginExpire", text);
        prefsEditor.apply();
    }

    //Mobile number of user
    public String getMobileNo() {
        return objComman.getString("MobileNo", "");
    }

    public void setMobileNo(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("MobileNo", text);
        prefsEditor.apply();
    }

    //FirstName of user
    public String getFirstName() {
        return objComman.getString("FirstName", "");
    }

    public void setFirstName(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("FirstName", text);
        prefsEditor.apply();
    }

    //LastName of user
    public String getLastName() {
        return objComman.getString("LastName", "");
    }

    public void setLastName(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("LastName", text);
        prefsEditor.apply();
    }

    //Email of user
    public String getEmail() {
        return objComman.getString("Email", "");
    }

    public void setEmail(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("Email", text);
        prefsEditor.apply();
    }

    //Gender of user
    public String getGender() {
        return objComman.getString("Gender", "");
    }

    public void setGender(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("Gender", text);
        prefsEditor.apply();
    }

    //App Reg Lat
    public String getAppRegLatitude() {
        return objComman.getString("AppRegLatitude", "");
    }

    public void setAppRegLatitude(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("AppRegLatitude", text);
        prefsEditor.apply();
    }

    //App Reg Longitude
    public String getAppRegLongitude() {
        return objComman.getString("AppRegLongitude", "");
    }

    public void setAppRegLongitude(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("AppRegLongitude", text);
        prefsEditor.apply();
    }

    //App Reg City Name
    public String getAppRegiCityName() {
        return objComman.getString("AppRegiCityName", "");
    }

    public void setAppRegiCityName(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("AppRegiCityName", text);
        prefsEditor.apply();
    }

    //DOB of user
    public long getDOB() {
        return objComman.getLong("DOB", 0);
    }

    public void setDOB(long text) {
        prefsEditor = objComman.edit();
        prefsEditor.putLong("DOB", text);
        prefsEditor.apply();
    }

    //Verification mobile number
    // phone verification code
    public String getVerificationCode() {
        return objComman.getString("VerificationCode", "");
    }

    public void setVerificationCode(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("VerificationCode", text);
        prefsEditor.apply();
    }

    // SMS Come verification code
    public String getVerificationSMSComeCode() {
        return objComman.getString("VerificationSMSComeCode", "");
    }

    public void setVerificationSMSComeCode(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("VerificationSMSComeCode", text);
        prefsEditor.apply();
    }

    //phone verification  Act Open
    public boolean getISPhoneVeriActOpen() {
        return objComman.getBoolean("ISPhoneVeriActOpen", false);
    }

    public void setISPhoneVeriActOpen(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("ISPhoneVeriActOpen", text);
        prefsEditor.apply();
    }

    //Country Code
    public String getMobileCountryCode() {
        return objComman.getString("MobileCountryCode", "");
    }

    public void setMobileCountryCode(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("MobileCountryCode", text);
        prefsEditor.apply();
    }

    //Country Code Integer
    public String getMobileCountryCodeNumber() {
        return objComman.getString("MobileCountryCodeNumber", "");
    }

    public void setMobileCountryCodeNumber(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("MobileCountryCodeNumber", text);
        prefsEditor.apply();
    }
    // //////end User Info

    // ////////////chat options
    // method for Chat user name
    public void setChatUserName(String isMessageOn) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("ChatUserName", isMessageOn);
        prefsEditor.apply();
    }

    public String getChatUserName() {
        return objComman.getString("ChatUserName", "");
    }

    // method for Chat mobileNum
    public void setChatMobileNum(String isMessageOn) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("ChatMobileNum", isMessageOn);
        prefsEditor.apply();
    }

    public String getChatMobileNum() {
        return objComman.getString("ChatMobileNum", "");
    }

    // method for Chat Current mobileNum
    public void setChatCurrentMobileNum(String isMessageOn) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("ChatCurrentMobileNum", isMessageOn);
        prefsEditor.apply();
    }

    public String getChatCurrentMobileNum() {
        return objComman.getString("ChatCurrentMobileNum", "");
    }

    // method for Chat message pending
    public void setKMMoniterDays(int isMessageOn) {
        prefsEditor = objComman.edit();
        prefsEditor.putInt("KMMoniterDays", isMessageOn);
        prefsEditor.apply();
    }

    public int getKMMoniterDays() {
        return objComman.getInt("KMMoniterDays", 0);
    }


//    public void setRefID(int RefID) {
//        prefsEditor = objComman.edit();
//        prefsEditor.putInt("refID", RefID);
//        prefsEditor.apply();
//    }
//
//    public int getRefID() {
//        return objComman.getInt("refID", 0);
//    }

    // method for Chat message pending
    public void setTotalChatMsgPending(int isMessageOn) {
        prefsEditor = objComman.edit();
        prefsEditor.putInt("TotalChatMsgPending", isMessageOn);
        prefsEditor.apply();
    }

    public int getTotalChatMsgPending() {
        return objComman.getInt("TotalChatMsgPending", 0);
    }

    // method for Chat Unread Counter
    public void setTotalUnreadChatJson(JSONArray msgUnread) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("TotalUnreadChatJson", msgUnread.toString());
        prefsEditor.apply();
    }

    public JSONArray getTotalUnreadChatJson() {
        try {
            return new JSONArray(objComman.getString("TotalUnreadChatJson", "[]"));
        } catch (JSONException e) {
        }

        return null;
    }

    //last GPS Loc Getting Time
    public long getLastGPSLocGetTime() {
        return objComman.getLong("LastGPSLocGetTime", 0);
    }

    public void setLastGPSLocGetTime(long text) {
        prefsEditor = objComman.edit();
        prefsEditor.putLong("LastGPSLocGetTime", text);
        prefsEditor.apply();
    }


    // method for Chat CurrStatus
    public void setChatCurrStatus(String isMessageOn) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("CurrStatus", isMessageOn);
        prefsEditor.apply();
    }

    public String getChatCurrStatus() {
        return objComman.getString("CurrStatus", "Hey there! Find me on RigvApp.");
    }
    //////////////end chat


    //////////// Phonebook My people list
    // Phonebook Last Counter
    public int getPhonebookLastCounter() {
        return objComman.getInt("PhonebookLastCounter", 0);
    }

    public void setPhonebookLastCounter(int text) {
        prefsEditor = objComman.edit();
        prefsEditor.putInt("PhonebookLastCounter", text);
        prefsEditor.apply();
    }

    // isNew DeviceID //for server to access phonebook
    public String getIsNewDeviceID() {
        return objComman.getString("IsNewDeviceID", "0");
    }

    public void setIsNewDeviceID(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("IsNewDeviceID", text);
        prefsEditor.apply();
    }

    // Phonebook Status code
    public String getPhonebookStatusCode() {
        return objComman.getString("PhonebookStatusCode", "");
    }

    public void setPhonebookStatusCode(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("PhonebookStatusCode", text);
        prefsEditor.apply();
    }

    // Phonebook Total Sync Counter
    public int getPhonebookTotalSyncCounter() {
        return objComman.getInt("PhonebookTotalSyncCounter", 0);
    }

    public void setPhonebookTotalSyncCounter(int text) {
        prefsEditor = objComman.edit();
        prefsEditor.putInt("PhonebookTotalSyncCounter", text);
        prefsEditor.apply();
    }

    // Phonebook Total Avail Counter
    public int getPhonebookTotalAvailCounter() {
        return objComman.getInt("PhonebookTotalAvailCounter", 0);
    }

    public void setPhonebookTotalAvailCounter(int text) {
        prefsEditor = objComman.edit();
        prefsEditor.putInt("PhonebookTotalAvailCounter", text);
        prefsEditor.apply();
    }
    //////////////end phoonbook my people list


    /////---- GPS Info -----
    //Is getting weather activity open
    public Boolean getIsGPSScanning() {
        return objComman.getBoolean("IsGPSScanning", false);
    }

    public void setIsGPSScanning(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("IsGPSScanning", text);
        prefsEditor.apply();
    }

    //Temp Latitude
    public String getTempLatitude() {
        return objComman.getString("TempLatitude", "0");
    }

    public void setTempLatitude(String GcmID) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("TempLatitude", GcmID);
        prefsEditor.apply();
    }

    //Temp Longitude
    public String getTempLongitude() {
        return objComman.getString("TempLongitude", "0");
    }

    public void setTempLongitude(String GcmID) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("TempLongitude", GcmID);
        prefsEditor.apply();
    }

    //Temp GPS Accuracy
    public String getTempAccuracy() {
        return objComman.getString("TempAccuracy", "");
    }

    public void setTempAccuracy(String Accuracy) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("TempAccuracy", Accuracy);
        prefsEditor.apply();
    }
    ////////////////////GPS Info


    /////////////Data Import export
    //For set last back up time
    public String getLastBackupTime() {
        return objComman.getString("LastBackupTime", "");
    }

    public void setLastBackupTime(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("LastBackupTime", text);
        prefsEditor.apply();
    }

    //Is need to Backup previous data
    public boolean getIsNeedBackupPreData() {
        return objComman.getBoolean("IsNeedBackupPreData", false);
    }

    public void setIsNeedBackupPreData(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("IsNeedBackupPreData", text);
        prefsEditor.apply();
    }
    ////////////////////Data Import export

    //////////////////////////Network usage
    //For set last back up time
    public String getLastResetTimeNetworkUsage() {
        return objComman.getString("ResetTimeNetworkUsage", "");
    }

    public void setLastResetTimeNetworkUsage(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("ResetTimeNetworkUsage", text);
        prefsEditor.apply();
    }

    public long getTotalBytesSent() {
        return objComman.getLong("TotalBytesSent", 0);
    }

    public void setTotalBytesSent(long text) {
        prefsEditor = objComman.edit();
        prefsEditor.putLong("TotalBytesSent", text);
        prefsEditor.apply();
    }

    public long getTotalBytesReceived() {
        return objComman.getLong("TotalBytesReceived", 0);
    }

    public void setTotalBytesReceived(long text) {
        prefsEditor = objComman.edit();
        prefsEditor.putLong("TotalBytesReceived", text);
        prefsEditor.apply();
    }
    //////////////////////////////////////////


    ///////////Build Application
    //Build application done or not
    public boolean getIsNeedBuildRigvApp() {
        return objComman.getBoolean("IsNeedBuildRigvApp", false);
    }

    public void setIsNeedBuildRigvApp(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("IsNeedBuildRigvApp", text);
        prefsEditor.apply();
    }
    ///////////////end Build App

    ///////////Feedback and suggestion
    //User feedback Message
    public String getUserFeedbackMsg() {
        return objComman.getString("UserFeedbackMsg", "");
    }

    public void setUserFeedbackMsg(String ID) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("UserFeedbackMsg", ID);
        prefsEditor.apply();
    }
    ////////////////end feedback and suggestion


    ///////////Address book mng
    //Current Address
    public String getCurrentAddress() {
        return objComman.getString("CurrentAddress", "");
    }

    public void setCurrentAddress(String ID) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("CurrentAddress", ID);
        prefsEditor.apply();
    }

    //Network Last Update Time
    public long getNetworkLastUpdateTime() {
        return objComman.getLong("NetworkLastUpdateTime", 0);
    }

    public void setNetworkLastUpdateTime(long ID) {
        prefsEditor = objComman.edit();
        prefsEditor.putLong("NetworkLastUpdateTime", ID);
        prefsEditor.apply();
    }

    //Is RouteTracker Details show, true
    public boolean getIsTrackerDetailsShow() {
        return objComman.getBoolean("IsTrackerDetailsShow", true);
    }

    public void setIsTrackerDetailsShow(boolean ID) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("IsTrackerDetailsShow", ID);
        prefsEditor.apply();
    }

    //Is Sync on cloud
    public boolean getIsAddBkSyncCloud() {
        return objComman.getBoolean("IsAddBkSyncCloud", true);
    }

    public void setIsAddBkSyncCloud(boolean ID) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("IsAddBkSyncCloud", ID);
        prefsEditor.apply();
    }

    //Is Only Verified Address
    public boolean getIsOnlyVerifiedAddress() {
        return objComman.getBoolean("IsOnlyVerifiedAddress", false);
    }

    public void setIsOnlyVerifiedAddress(boolean ID) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("IsOnlyVerifiedAddress", ID);
        prefsEditor.apply();
    }

    //Is Pending Sync on Clound
    public boolean getIsPendingAddBkSyncCloud() {
        return objComman.getBoolean("IsPendingAddBkSyncCloud", true);
    }

    public void setIsPendingAddBkSyncCloud(boolean ID) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("IsPendingAddBkSyncCloud", ID);
        prefsEditor.apply();
    }

    //Is Pending Delete pending ID
    public String getIsPendingAddBkDelete() {
        return objComman.getString("IsPendingAddBkDelete", "");
    }

    public void setIsPendingAddBkDelete(String ID) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("IsPendingAddBkDelete", ID);
        prefsEditor.apply();
    }

    //Is Pending Delete pending ID
    public String getIsPendingAddBkRestoreDelete() {
        return objComman.getString("IsPendingAddBkRestoreDelete", "");
    }

    public void setIsPendingAddBkRestoreDelete(String ID) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("IsPendingAddBkRestoreDelete", ID);
        prefsEditor.apply();
    }

    //Is Pending Delete Group pending ID
    public String getIsPendingAddBkDeleteGroup() {
        return objComman.getString("IsPendingAddBkDeleteGroup", "");
    }

    public void setIsPendingAddBkDeleteGroup(String ID) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("IsPendingAddBkDeleteGroup", ID);
        prefsEditor.apply();
    }

    //Is Pending Delete pending ID
    public String getIsPendingAddBkRestoreDeleteGroup() {
        return objComman.getString("IsPendingAddBkRestoreDeleteGroup", "");
    }

    public void setIsPendingAddBkRestoreDeleteGroup(String ID) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("IsPendingAddBkRestoreDeleteGroup", ID);
        prefsEditor.apply();
    }

    //Is Pending Address process ID
    public String getIsPendingAddBkProcessID() {
        return objComman.getString("IsPendingAddBkProcessID", "");
    }

    public void setIsPendingAddBkProcessID(String ID) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("IsPendingAddBkProcessID", ID);
        prefsEditor.apply();
    }


    //Is Global Avail
    public String getIsGlobalAvail() {
        return objComman.getString("IsGlobalAvail", "");
    }

    public void setIsGlobalAvail(String ID) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("IsGlobalAvail", ID);
        prefsEditor.apply();
    }

    //Is Pending Global pending ID
    public String getIsPendingAddBkGlobal() {
        return objComman.getString("IsPendingAddBkGlobal", "");
    }

    public void setIsPendingAddBkGlobal(String ID) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("IsPendingAddBkGlobal", ID);
        prefsEditor.apply();
    }

    //Is Pending Global pending ID
    public String getGlobalCatIDList() {
        return objComman.getString("GlobalCatIDList", "0");
    }

    public void setGlobalCatIDList(String ID) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("GlobalCatIDList", ID);
        prefsEditor.apply();
    }

    //Pending Category Change ID
    public String getIsPendingAddBkCategoryChange() {
        return objComman.getString("IsPendingAddBkCategoryChange", "");
    }

    public void setIsPendingAddBkCategoryChange(String ID) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("IsPendingAddBkCategoryChange", ID);
        prefsEditor.apply();
    }

    //Make Global address id
    public String getMakeGlobalAddrID() {
        return objComman.getString("MakeGlobalAddrID", "");
    }

    public void setMakeGlobalAddrID(String ID) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("MakeGlobalAddrID", ID);
        prefsEditor.apply();
    }

    //Is Pending Non Global pending ID
    public String getIsPendingAddBkNonGlobal() {
        return objComman.getString("IsPendingAddBkNonGlobal", "");
    }

    public void setIsPendingAddBkNonGlobal(String ID) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("IsPendingAddBkNonGlobal", ID);
        prefsEditor.apply();
    }

    //Is Pending Fevorite pending ID
    public String getIsPendingAddBkFevorite() {
        return objComman.getString("IsPendingAddBkFevorite", "");
    }

    public void setIsPendingAddBkFevorite(String ID) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("IsPendingAddBkFevorite", ID);
        prefsEditor.apply();
    }

    //Is Pending Non Global pending ID
    public String getIsPendingAddBkNonFevorite() {
        return objComman.getString("IsPendingAddBkNonFevorite", "");
    }

    public void setIsPendingAddBkNonFevorite(String ID) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("IsPendingAddBkNonFevorite", ID);
        prefsEditor.apply();
    }

    //Search Request Text
    public String getAddrSearchReqText() {
        return objComman.getString("AddrSearchReqText", "");
    }

    public void setAddrSearchReqText(String ID) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("AddrSearchReqText", ID);
        prefsEditor.apply();
    }

    //Search Request Type
    public String getAddrSearchReqType() {
        return objComman.getString("AddrSearchReqType", "");
    }

    public void setAddrSearchReqType(String ID) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("AddrSearchReqType", ID);
        prefsEditor.apply();
    }

    //Search Request Position
    public int getAddrSearchReqPosition() {
        return objComman.getInt("AddrSearchReqPosition", 0);
    }

    public void setAddrSearchReqPosition(int ID) {
        prefsEditor = objComman.edit();
        prefsEditor.putInt("AddrSearchReqPosition", ID);
        prefsEditor.apply();
    }

    //Is Pending Rename Group name Array
    public JSONArray getIsPendingAddBkRenameGrp() {
        try {
            return new JSONArray(objComman.getString("IsPendingAddBkRenameGrp", "[]"));
        } catch (JSONException e) {
        }

        return new JSONArray();
    }

    public void setIsPendingAddBkRenameGrp(JSONArray ID) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("IsPendingAddBkRenameGrp", ID.toString());
        prefsEditor.apply();
    }
    ////////////////end feedback and suggestion


    ///////GPS SySTEM Control panel
    // Is GPS Driving mode On
    public boolean getIsGPSDrivingModeOn() {
        return objComman.getBoolean("IsGPSDrivingModeOn", false);
    }

    public void setIsGPSDrivingModeOn(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("IsGPSDrivingModeOn", text);
        prefsEditor.apply();
    }

    // Is Manage battery mode On
    public boolean getIsManagebatteryModeOn() {
        return objComman.getBoolean("IsManagebatteryModeOn", true);
    }

    public void setIsManagebatteryModeOn(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("IsManagebatteryModeOn", text);
        prefsEditor.apply();
    }

    // GPS Scanning mode Type
    public int getGPSScanningModeType() {
        return objComman.getInt("GPSScanningModeType", 0);
    }

    public void setGPSScanningModeType(int text) {
        prefsEditor = objComman.edit();
        prefsEditor.putInt("GPSScanningModeType", text);
        prefsEditor.apply();
    }

    // Manage battery Level
    public int getManageBatteryLevel() {
        return objComman.getInt("ManageBatteryLevel", 0);
    }

    public void setManageBatteryLevel(int text) {
        prefsEditor = objComman.edit();
        prefsEditor.putInt("ManageBatteryLevel", text);
        prefsEditor.apply();
    }
    /////ENd GPS SYstem control panelFf

    ////////Device Setting
    // Set fuel alert
    public String getFuelAlertOnOff() {
        return objComman.getString("FuelAlertOnOff", "1");
    }
    public void setFuelAlertOnOff(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("FuelAlertOnOff", text);
        prefsEditor.apply();
    }
    // Set fuel Filter
    public String getFuelFilter() {
        return objComman.getString("FuelFilter", "0,1");
    }
    public void setFuelFilter(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("FuelFilter", text);
        prefsEditor.apply();
    }

    // Get Server Last Time
    public long getServerLastTime() {
        return objComman.getLong("ServerLastTime", 0);
    }

    public void setServerLastTime(long text) {
        prefsEditor = objComman.edit();
        prefsEditor.putLong("ServerLastTime", text);
        prefsEditor.apply();
    }

    // Get DeviceID list
    public String getDeviceIDList() {
        return objComman.getString("DeviceIDList", "");
    }
    public void setDeviceIDList(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("DeviceIDList", text);
        prefsEditor.apply();
    }
    // Get Route DeviceID list
    public String getRouteDeviceIDList() {
        return objComman.getString("RouteDeviceIDList", "");
    }
    public void setRouteDeviceIDList(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("RouteDeviceIDList", text);
        prefsEditor.apply();
    }
    // Get Sync DeviceID list
    public String getSyncDeviceIDList() {
        return objComman.getString("SyncDeviceIDList", "");
    }
    public void setSyncDeviceIDList(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("SyncDeviceIDList", text);
        prefsEditor.apply();
    }

    // Get Device Last DateTime list
    public String getDeviceTypeList() {
        return objComman.getString("DeviceTypeList", "");
    }

    public void setDeviceTypeList(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("DeviceTypeList", text);
        prefsEditor.apply();
    }

    // Get Device Active list
    public String getDeviceActiveList() {
        return objComman.getString("DeviceActiveList", "");
    }

    public void setDeviceActiveList(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("DeviceActiveList", text);
        prefsEditor.apply();
    }

    // Get Device Search Temp list
    public String getDeviceSearchTempList() {
        return objComman.getString("DeviceSearchTemp", "");
    }

    public void setDeviceSearchTemp(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("DeviceSearchTemp", text);
        prefsEditor.apply();
    }


    // method for Device Record From Time
    public void setDeviceIDFromTimeJson(JSONArray msgUnread) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("DeviceIDFromTimeJson", msgUnread.toString());
        prefsEditor.apply();
    }

    public JSONArray getDeviceIDFromTimeJson() {
        try {
            return new JSONArray(objComman.getString("DeviceIDFromTimeJson", "[]"));
        } catch (JSONException e) {
        }

        return null;
    }

    // method for Device Record Process Time
    public void setDeviceIDProcessTimeJson(JSONArray msgUnread) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("DeviceIDProcessTimeJson", msgUnread.toString());
        prefsEditor.apply();
    }

    public JSONArray getDeviceIDProcessTimeJson() {
        try {
            return new JSONArray(objComman.getString("DeviceIDProcessTimeJson", "[]"));
        } catch (JSONException e) {
        }
        return null;
    }

    // Get Report DeviceID
    public String getReportDeviceID() {
        return objComman.getString("ReportDeviceID", "");
    }

    public void setReportDeviceID(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("ReportDeviceID", text);
        prefsEditor.apply();
    }

    // Get AutoReport DeviceID
    public String getAutoReportDeviceID() {
        return objComman.getString("AutoReportDeviceID", "");
    }

    public void setAutoReportDeviceID(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("AutoReportDeviceID", text);
        prefsEditor.apply();
    }

    // Get Report Gen Window Running
    public boolean getReportGenWindowRun() {
        return objComman.getBoolean("ReportGenWindowRun", false);
    }

    public void setReportGenWindowRun(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("ReportGenWindowRun", text);
        prefsEditor.apply();
    }

    // Get SecureMode DeviceID
    public String getSecureModeDeviceID() {
        return objComman.getString("SecureModeDeviceID", "");
    }

    public void setSecureModeDeviceID(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("SecureModeDeviceID", text);
        prefsEditor.apply();
    }

    // Get SecureMode DeviceName
    public String getSecureModeDeviceName() {
        return objComman.getString("SecureModeDeviceName", "");
    }

    public void setSecureModeDeviceName(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("SecureModeDeviceName", text);
        prefsEditor.apply();
    }

    // Get SecureMode GeoDistance
    public String getSecureModeGeoDistance() {
        return objComman.getString("SecureModeGeoDistance", "");
    }

    public void setSecureModeGeoDistance(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("SecureModeGeoDistance", text);
        prefsEditor.apply();
    }

    // Get AntiTheft DeviceID
    public String getAntiTheftModeDeviceID() {
        return objComman.getString("AntiTheftModeDeviceID", "");
    }

    public void setAntiTheftModeDeviceID(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("AntiTheftModeDeviceID", text);
        prefsEditor.apply();
    }

    // Get AntiTheft DeviceName
    public String getAntiTheftModeDeviceName() {
        return objComman.getString("AntiTheftModeDeviceName", "");
    }

    public void setAntiTheftModeDeviceName(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("AntiTheftModeDeviceName", text);
        prefsEditor.apply();
    }

    // Get AntiTheft GeoDistance
    public String getAntiTheftModeGeoDistance() {
        return objComman.getString("AntiTheftModeGeoDistance", "");
    }

    public void setAntiTheftModeGeoDistance(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("AntiTheftModeGeoDistance", text);
        prefsEditor.apply();
    }

    // Get AntiTheft ServerID
    public String getAntiTheftModeServerID() {
        return objComman.getString("AntiTheftModeServerID", "");
    }

    public void setAntiTheftModeServerID(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("AntiTheftModeServerID", text);
        prefsEditor.apply();
    }

    // Get Temp AntiTheft Distance
    public String getTempAntiTheftModeDistance() {
        return objComman.getString("TempAntiTheftModeDistance", "");
    }

    public void setTempAntiTheftModeDistance(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("TempAntiTheftModeDistance", text);
        prefsEditor.apply();
    }

    // Get Temp AntiTheft Lat,Lng
    public String getTempAntiTheftModeLatLng() {
        return objComman.getString("TempAntiTheftModeLatLng", "");
    }

    public void setTempAntiTheftModeLatLng(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("TempAntiTheftModeLatLng", text);
        prefsEditor.apply();
    }


    // Get SecureMode Request Type
    public String getSecureModeRequestType() {
        return objComman.getString("SecureModeRequestType", "");
    }

    public void setSecureModeRequestType(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("SecureModeRequestType", text);
        prefsEditor.apply();
    }

    // Get Report DateTime
    public String getDeviceReportDateTime() {
        return objComman.getString("DeviceReportDateTime", "");
    }

    public void setDeviceReportDateTime(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("DeviceReportDateTime", text);
        prefsEditor.apply();
    }

    public boolean getDeviceReportBackupIsPregress() {
        return objComman.getBoolean("DeviceReportBackupIsPregress", false);
    }

    public void setDeviceReportBackupIsPregress(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("DeviceReportBackupIsPregress", text);
        prefsEditor.apply();
    }

    // Get Report Server From DateTime
    public String getDeviceServerReportFromTime() {
        return objComman.getString("DeviceServerReportFromTime", "");
    }

    public void setDeviceServerReportFromTime(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("DeviceServerReportFromTime", text);
        prefsEditor.apply();
    }

    // Get Report Server to DateTime
    public String getDeviceServerReportToTime() {
        return objComman.getString("DeviceServerReportToTime", "");
    }

    public void setDeviceServerReportToTime(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("DeviceServerReportToTime", text);
        prefsEditor.apply();
    }

    // Get Report Server ProcessDone DateTime
    public String getDeviceServerReportProcessDoneTime() {
        return objComman.getString("DeviceSrvrReportPrssTm", "");
    }

    public void setDeviceServerReportProcessDoneTime(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("DeviceSrvrReportPrssTm", text);
        prefsEditor.apply();
    }
    ///////////// End Device Setting


    ////////// Follow Me
    public long getGPSLocSendStartTime() {
        return objComman.getLong("GPSLocSendStartTime", 0);
    }

    public void setGPSLocSendStartTime(long text) {
        prefsEditor = objComman.edit();
        prefsEditor.putLong("GPSLocSendStartTime", text);
        prefsEditor.apply();
    }

    public long getGPSLocSendStopTime() {
        return objComman.getLong("GPSLocSendStopTime", 0);
    }

    public void setGPSLocSendStopTime(long text) {
        prefsEditor = objComman.edit();
        prefsEditor.putLong("GPSLocSendStopTime", text);
        prefsEditor.apply();
    }

    public String getRequestFollowMeID() {
        return objComman.getString("RequestFollowMeID", "");
    }

    public void setRequestFollowMeID(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("RequestFollowMeID", text);
        prefsEditor.apply();
    }

    public String getRequestFollowMePacketID() {
        return objComman.getString("RequestFollowMePacketID", "");
    }

    public void setRequestFollowMePacketID(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("RequestFollowMePacketID", text);
        prefsEditor.apply();
    }

    public String getRequestFollowMeExtMsg() {
        return objComman.getString("RequestFollowMeExtMsg", "");
    }

    public void setRequestFollowMeExtMsg(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("RequestFollowMeExtMsg", text);
        prefsEditor.apply();
    }
    ///////////// End Follow Me

    ///////////My Path
    public long getMyPathStartTime() {
        return objComman.getLong("MyPathStartTime", 0);
    }

    public void setMyPathStartTime(long text) {
        prefsEditor = objComman.edit();
        prefsEditor.putLong("MyPathStartTime", text);
        prefsEditor.apply();
    }

    public String getMyPathCurrentID() {
        return objComman.getString("MyPathCurrentID", "0");
    }

    public void setMyPathCurrentID(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("MyPathCurrentID", text);
        prefsEditor.apply();
    }

    public int getMyPathNameCount() {
        return objComman.getInt("MyPathNameCount", 0);
    }

    public void setMyPathNameCount(int text) {
        prefsEditor = objComman.edit();
        prefsEditor.putInt("MyPathNameCount", text);
        prefsEditor.apply();
    }

    public float getMyPathOldLatitude() {
        return objComman.getFloat("MyPathOldLatitude", 0);
    }

    public void setMyPathOldLatitude(float text) {
        prefsEditor = objComman.edit();
        prefsEditor.putFloat("MyPathOldLatitude", text);
        prefsEditor.apply();
    }

    public float getMyPathOldLongitude() {
        return objComman.getFloat("MyPathOldLongitude", 0);
    }

    public void setMyPathOldLongitude(float text) {
        prefsEditor = objComman.edit();
        prefsEditor.putFloat("MyPathOldLongitude", text);
        prefsEditor.apply();
    }
    /////////End My Path

    // Set the recent address limit
    public int getRecentAddrLimit() {
        return objComman.getInt("RecentAddrLimit", 200);
    }

    public void setRecentAddrLimit(int text) {
        prefsEditor = objComman.edit();
        prefsEditor.putInt("RecentAddrLimit", text);
        prefsEditor.apply();
    }
    /////////////////////////////////

    // Set the speed limit
    public int getSpeedLimit() {
        return objComman.getInt("SpeedLimit", 0);
    }

    public void setSpeedLimit(int text) {
        prefsEditor = objComman.edit();
        prefsEditor.putInt("SpeedLimit", text);
        prefsEditor.apply();
    }

    public String getSettingDeviceID() {
        return objComman.getString("SettingDeviceID", "");
    }

    public void setSettingDeviceID(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("SettingDeviceID", text);
        prefsEditor.apply();
    }

    public String getSettingReqType() {
        return objComman.getString("SettingReqType", "");
    }
    public void setSettingReqType(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("SettingReqType", text);
        prefsEditor.apply();
    }

    public long getSettingReqLastTime() {
        return objComman.getLong("SettingReqLastTime", 0);
    }
    public void setSettingReqLastTime(long text) {
        prefsEditor = objComman.edit();
        prefsEditor.putLong("SettingReqLastTime", text);
        prefsEditor.apply();
    }


    public boolean getTrackerNickAsDisplay() {
        return objComman.getBoolean("TrackerNickAsDisplay", true);
    }

    public void setTrackerNickAsDisplay(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("TrackerNickAsDisplay", text);
        prefsEditor.apply();
    }

    //Speed alert on or off
    public String getSpeedAlertOnOff() {
        return objComman.getString("SpeedAlertOnOff", "");
    }

    public void setSpeedAlertOnOff(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("SpeedAlertOnOff", text);
        prefsEditor.apply();
    }

    //Stoppage alert on or off
    public String getStoppageAlertOnOff() {
        return objComman.getString("StoppageAlertOnOff", "");
    }

    public void setStoppageAlertOnOff(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("StoppageAlertOnOff", text);
        prefsEditor.apply();
    }

    public int getStoppageLimit() {
        return objComman.getInt("StoppageLimit", 0);
    }

    public void setStoppageLimit(int text) {
        prefsEditor = objComman.edit();
        prefsEditor.putInt("StoppageLimit", text);
        prefsEditor.apply();
    }
    ///////////////////////

    //Ignition alert on or off
    public String getIgnitionAlertOn() {
        return objComman.getString("IgnitionAlertOn", "");
    }

    public void setIgnitionAlertOn(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("IgnitionAlertOn", text);
        prefsEditor.apply();
    }

    public String getIgnitionAlertOff() {
        return objComman.getString("IgnitionAlertOff", "");
    }

    public void setIgnitionAlertOff(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("IgnitionAlertOff", text);
        prefsEditor.apply();
    }
    /////////////////

    //AC alert on or off
    public String getACAlertOn() {
        return objComman.getString("ACAlertOn", "");
    }

    public void setACAlertOn(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("ACAlertOn", text);
        prefsEditor.apply();
    }

    public String getACAlertOff() {
        return objComman.getString("ACAlertOff", "");
    }

    public void setACAlertOff(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("ACAlertOff", text);
        prefsEditor.apply();
    }
    /////////////////

    //document wallet upload data
    public String getDocUploadData() {
        return objComman.getString("DocUploadData", "");
    }

    public void setDocUploadData(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("DocUploadData", text);
        prefsEditor.apply();
    }
    /////////////////////////////

    ///Tyre Age Limit
    public int getTyreLifeLimit() {
        return objComman.getInt("TyreLifeLimit", 0);
    }

    public void setTyreLifeLimit(int text) {
        prefsEditor = objComman.edit();
        prefsEditor.putInt("TyreLifeLimit", text);
        prefsEditor.apply();
    }
    ///////////////////////

    //document wallet upload data
    public String getDocUploadServerID() {
        return objComman.getString("DocUploadServerID", "");
    }

    public void setDocUploadServerID(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("DocUploadServerID", text);
        prefsEditor.apply();
    }


    // method for request getting Share ID
    public String getShareDeleteID() {
        return objComman.getString("ShareDeleteID", "");
    }

    public void setShareDeleteID(String isMessageOn) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("ShareDeleteID", isMessageOn);
        prefsEditor.apply();
    }

    // method for request getting Share Mobile Num
    public String getShareMobileNumFrd() {
        return objComman.getString("ShareMobileNumFrd", "");
    }

    public void setShareMobileNumFrd(String isMessageOn) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("ShareMobileNumFrd", isMessageOn);
        prefsEditor.apply();
    }

    // method for request getting Device Share ID
    public String getShareDeviceDeleteID() {
        return objComman.getString("ShareDeviceDeleteID", "");
    }

    public void setShareDeviceDeleteID(String isMessageOn) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("ShareDeviceDeleteID", isMessageOn);
        prefsEditor.apply();
    }

    // method for notification multiple
    public String getStoreMultipleNoti() {
        return objComman.getString("SetMultipleNoti", "");
    }

    public void setStoreMultipleNoti(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("SetMultipleNoti", text);
        prefsEditor.apply();
    }

    // method for notification Check Flag
    public boolean getDefaultNotiCheck() {
        return objComman.getBoolean("DefaultNotiCheck", true);
    }

    public void setDefaultNotiCheck(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("DefaultNotiCheck", text);
        prefsEditor.apply();
    }

    // method for notification default time
    public String getDefaultNotiTime() {
        return objComman.getString("DefaultNotiTime", "");
    }

    public void setDefaultNotiTime(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("DefaultNotiTime", text);
        prefsEditor.apply();
    }

    // method for notification pending
    public boolean getIsPendingNotiSett() {
        return objComman.getBoolean("IsPendingNotiSett", false);
    }

    public void setIsPendingNotiSett(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("IsPendingNotiSett", text);
        prefsEditor.apply();
    }


    ////////////Trip & notes Data
    // method for Trip delete IDs
    public String getTripDeleteServerID() {
        return objComman.getString("TripDeleteServerID", "");
    }

    public void setTripDeleteServerID(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("TripDeleteServerID", text);
        prefsEditor.apply();
    }

    // method for Notes delete IDs
    public String getNotesDeleteServerID() {
        return objComman.getString("NotesDeleteServerID", "");
    }

    public void setNotesDeleteServerID(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("NotesDeleteServerID", text);
        prefsEditor.apply();
    }
    ////////////

    ///Driver info
    // method for driver delete IDs
    public String getDriverInfoDeleteServerID() {
        return objComman.getString("DriverInfoDeleteServerID", "");
    }

    public void setDriverInfoDeleteServerID(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("DriverInfoDeleteServerID", text);
        prefsEditor.apply();
    }


    //////Add new Device
    //Device licence Verification
    public String getDeviceLicenceVerify() {
        return objComman.getString("DeviceLicenceVerify", "");
    }

    public void setDeviceLicenceVerify(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("DeviceLicenceVerify", text);
        prefsEditor.apply();
    }

    //Device licence Verification Type
    public String getDeviceLicTypeVerify() {
        return objComman.getString("DeviceLicTypeVerify", "");
    }

    public void setDeviceLicTypeVerify(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("DeviceLicTypeVerify", text);
        prefsEditor.apply();
    }

    //Device VTS Sim number
    public String getDeviceVTSSimNumber() {
        return objComman.getString("DeviceVTSSimNumber", "");
    }

    public void setDeviceVTSSimNumber(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("DeviceVTSSimNumber", text);
        prefsEditor.apply();
    }

    //Device IMEI number
    public String getDeviceIMEINumber() {
        return objComman.getString("DeviceIMEINumber", "");
    }

    public void setDeviceIMEINumber(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("DeviceIMEINumber", text);
        prefsEditor.apply();
    }

    //Device Vehicle Number
    public String getDeviceVehicleNumber() {
        return objComman.getString("DeviceVehicleNumber", "");
    }

    public void setDeviceVehicleNumber(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("DeviceVehicleNumber", text);
        prefsEditor.apply();
    }

    //Device Features ID
    public String getDeviceFeaturesID() {
        return objComman.getString("DeviceFeaturesID", "");
    }

    public void setDeviceFeaturesID(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("DeviceFeaturesID", text);
        prefsEditor.apply();
    }

    //Device From KM
    public String getDeviceFromKm() {
        return objComman.getString("DeviceFromKm", "");
    }

    public void setDeviceFromKm(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("DeviceFromKm", text);
        prefsEditor.apply();
    }

    //Device Chassis Number
    public String getDeviceChassisNumber() {
        return objComman.getString("DeviceChassisNumber", "");
    }

    public void setDeviceChassisNumber(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("DeviceChassisNumber", text);
        prefsEditor.apply();
    }

    ////End Add Device

    //Pref for data usage
    //for mobile data usage
    public boolean getISMobDataUsageON() {
        return objComman.getBoolean("ISMobDataUsageON", false);
    }

    public void setISMobDataUsageON(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("ISMobDataUsageON", text);
        prefsEditor.apply();
    }

    //for wifi data usage
    public boolean getISWifiDataUsageON() {
        return objComman.getBoolean("ISWifiDataUsageON", true);
    }

    public void setISWifiDataUsageON(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("ISWifiDataUsageON", text);
        prefsEditor.apply();
    }

    //for roaming data usage
    public boolean getISRoamingDataUsageON() {
        return objComman.getBoolean("ISRoamingDataUsageON", false);
    }

    public void setISRoamingDataUsageON(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("ISRoamingDataUsageON", text);
        prefsEditor.apply();
    }

    //for RVR System Enable
    public boolean getIsRVRSystemEnable() {
        return objComman.getBoolean("IsRVRSystemEnable", false);
    }

    public void setIsRVRSystemEnable(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("IsRVRSystemEnable", text);
        prefsEditor.apply();
    }

    //for RVR System Enable
    public boolean getIsRVRScreenON() {
        return objComman.getBoolean("IsRVRScreenON", false);
    }

    public void setIsRVRScreenON(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("IsRVRScreenON", text);
        prefsEditor.apply();
    }

    //for RVR System Enable
    public boolean getIsRVRSpotAllFullScreen() {
        return objComman.getBoolean("IsRVRSpotAllFullScreen", false);
    }

    public void setIsRVRSpotAllFullScreen(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("IsRVRSpotAllFullScreen", text);
        prefsEditor.apply();
    }

    //////////Security Lock
    public String getPatternString() {
        return objComman.getString("PatternString", "");
    }

    public void setPatternString(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("PatternString", text);
        prefsEditor.apply();
    }

    public String getNumberPINString() {
        return objComman.getString("NumberPINString", "");
    }

    public void setNumberPINString(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("NumberPINString", text);
        prefsEditor.apply();
    }

    //for security lock Enable
    public boolean getIsSecurityLockEnable() {
        return objComman.getBoolean("IsSecurityLockEnable", false);
    }

    public void setIsSecurityLockEnable(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("IsSecurityLockEnable", text);
        prefsEditor.apply();
    }

    //for security lock apply dashboard
    public boolean getIsSecurLockApplyDashboard() {
        return objComman.getBoolean("IsSecurLockApplyDashboard", false);
    }

    public void setIsSecurLockApplyDashboard(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("IsSecurLockApplyDashboard", text);
        prefsEditor.apply();
    }

    //for security lock apply optionMenu
    public boolean getIsSecurLockApplyOptionMenu() {
        return objComman.getBoolean("IsSecurLockApplyOptionMenu", false);
    }

    public void setIsSecurLockApplyOptionMenu(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("IsSecurLockApplyOptionMenu", text);
        prefsEditor.apply();
    }

    //for security lock apply Powercut
    public boolean getIsSecurLockApplyPowerCut() {
        return objComman.getBoolean("IsSecurLockApplyPowerCut", false);
    }

    public void setIsSecurLockApplyPowerCut(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("IsSecurLockApplyPowerCut", text);
        prefsEditor.apply();
    }

    //for security lock apply Track Power
    public boolean getIsSecurLockApplyTrackPower() {
        return objComman.getBoolean("IsSecurLockApplyTrackPower", true);
    }

    public void setIsSecurLockApplyTrackPower(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("IsSecurLockApplyTrackPower", text);
        prefsEditor.apply();
    }

    //for security lock apply Edit Track
    public boolean getIsSecurLockApplyModifyTrack() {
        return objComman.getBoolean("IsSecurLockApplyModifyTrack", true);
    }
    public void setIsSecurLockApplyModifyTrack(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("IsSecurLockApplyModifyTrack", text);
        prefsEditor.apply();
    }
    //for security lock apply Release Track
    public boolean getIsSecurLockApplyReleaseTrack() {
        return objComman.getBoolean("IsSecurLockApplyReleaseTrack", true);
    }
    public void setIsSecurLockApplyReleaseTrack(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("IsSecurLockApplyReleaseTrack", text);
        prefsEditor.apply();
    }

    //for security lock apply remove passenger
    public boolean getIsSecurLockApplyRemovePassenger() {
        return objComman.getBoolean("IsSecurLockApplyRemovePassenger", true);
    }
    public void setIsSecurLockApplyRemovePassenger(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("IsSecurLockApplyRemovePassenger", text);
        prefsEditor.apply();
    }

    //for store scurity answer
    public String getSecurityAnswer() {
        return objComman.getString("SecurityAnswer", "");
    }

    public void setSecurityAnswer(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("SecurityAnswer", text);
        prefsEditor.apply();
    }

    //for store scurity question
    public String getSecurityQuestion() {
        return objComman.getString("SecurityQuestion", "");
    }

    public void setSecurityQuestion(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("SecurityQuestion", text);
        prefsEditor.apply();
    }
    //////////End Security Lock

    ///////////Guide Window
    //guide for main tab layout act
    public boolean getIsMainTabGuide() {
        return objComman.getBoolean("IsMainTabGuide", false);
    }

    public void setIsMainTabGuide(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("IsMainTabGuide", text);
        prefsEditor.apply();
    }

    //guide for device detail
    public boolean getIsDeviceDetailGuide() {
        return objComman.getBoolean("IsDeviceDetail", false);
    }

    public void setIsDeviceDetailGuide(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("IsDeviceDetail", text);
        prefsEditor.apply();
    }

    //guide for save address detail
    public boolean getIsSaveAddrDetailGuide() {
        return objComman.getBoolean("IsSaveAddrDetail", false);
    }

    public void setIsMainDashboardGuide(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("IsMainDashboard", text);
        prefsEditor.apply();
    }

    //guide for click
    public boolean getIsMainDashboardGuide() {
        return objComman.getBoolean("IsMainDashboard", false);
    }

    public void setIsSaveAddrDetailGuide(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("IsSaveAddrDetail", text);
        prefsEditor.apply();
    }

    //guide for edit address detail
    public boolean getIsEditAddrDetailGuide() {
        return objComman.getBoolean("IsEditAddrDetail", false);
    }

    public void setIsEditAddrDetailGuide(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("IsEditAddrDetail", text);
        prefsEditor.apply();
    }

    //guide for add address detail
    public boolean getIsGroupDetailGuide() {
        return objComman.getBoolean("IsGroupDetail", false);
    }

    public void setIsGroupDetailGuide(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("IsGroupDetail", text);
        prefsEditor.apply();
    }

    //guide for restore address detail
    public boolean getIsRestoreAddrDetailGuide() {
        return objComman.getBoolean("IsRestoreAddrDetail", false);
    }

    public void setIsRestoreAddrDetailGuide(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("IsRestoreAddrDetail", text);
        prefsEditor.apply();
    }

    //guide for phonebook detail
    public boolean getIsPhonebookDetailGuide() {
        return objComman.getBoolean("IsPhonebookDetail", false);
    }

    public void setIsPhonebookDetailGuide(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("IsPhonebookDetail", text);
        prefsEditor.apply();
    }

    //guide for geofence types
    public boolean getIsGeofenceTypeGuide() {
        return objComman.getBoolean("IsGeofenceTypeGuide", false);
    }

    public void setIsGeofenceTypeGuide(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("IsGeofenceTypeGuide", text);
        prefsEditor.apply();
    }
    ///////////End Guide Window

    //Device batch num
    public String getDeviceBatchNo() {
        return objComman.getString("DeviceBatchNo", "");
    }

    public void setDeviceBatchNo(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("DeviceBatchNo", text);
        prefsEditor.apply();
    }


    //Filepath to share report
    public String getShareReportFilePath() {
        return objComman.getString("ShareReportFilePath", "");
    }

    public void setShareReportFilePath(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("ShareReportFilePath", text);
        prefsEditor.apply();
    }


    //Owner type of tracker
    public String getTrackerOwnerType() {
        return objComman.getString("TrackerOwnerType", "");
    }

    public void setTrackerOwnerType(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("TrackerOwnerType", text);
        prefsEditor.apply();
    }

    //is MessageLongService On
    public boolean getIsMessageLongServiceON() {
        return objComman.getBoolean("IsMessageLongServiceON", false);
    }

    public void setIsMessageLongServiceON(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("IsMessageLongServiceON", text);
        prefsEditor.apply();
    }

    //is BatteryOptimizations On
    public boolean getIsBatteryOptimizationsON() {
        return objComman.getBoolean("IsBatteryOptimizationsON", false);
    }

    public void setIsBatteryOptimizationsON(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("IsBatteryOptimizationsON", text);
        prefsEditor.apply();
    }

    //is DND On
    public boolean getIsDNDON() {
        return objComman.getBoolean("IsDNDON", false);
    }

    public void setIsDNDON(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("IsDNDON", text);
        prefsEditor.apply();
    }


    //is DND On
    public boolean getIsPassMsgPush() {
        return objComman.getBoolean("IsPassMsgPush", false);
    }

    public void setIsPassMsgPush(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("IsPassMsgPush", text);
        prefsEditor.apply();
    }

    //is internet is on for show status snackbar
    public boolean getIsInternetON() {
        return objComman.getBoolean("IsInternetON", false);
    }

    public void setIsInternetON(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("IsInternetON", text);
        prefsEditor.apply();
    }

    //is passenge act on
    public String getPassengerActONDeviceName() {
        return objComman.getString("PassengerActONDeviceName", "");
    }

    public void setPassengerActONDeviceName(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("PassengerActONDeviceName", text);
        prefsEditor.apply();
    }

    public String getPassengerStationName() {
        return objComman.getString("PassengerStationName", "");
    }

    public void setPassengerStationName(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("PassengerStationName", text);
        prefsEditor.apply();
    }

    public String getPassengerStationStatus() {
        return objComman.getString("PassengerStationStatus", "");
    }

    public void setPassengerStationStatus(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("PassengerStationStatus", text);
        prefsEditor.apply();
    }


    // IS Discalamer accept
    public boolean getISPassengerDisclamerAccepted() {
        return objComman.getBoolean("ISPassengerDisclamerAccepted", false);
    }

    public void setISPassengerDisclamerAccepted(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("ISPassengerDisclamerAccepted", text);
        prefsEditor.apply();
    }

    // Is App Version Expire Skip
    public boolean getIsFCMNotify() {
        return objComman.getBoolean("IsFCMNotify", false);
    }

    public void setIsFCMNotify(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("IsFCMNotify", text);
        prefsEditor.apply();
    }

    public String getFCMToken() {
        return objComman.getString("FCMToken", "");
    }

    public void setFCMToken(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("FCMToken", text);
        prefsEditor.apply();
    }

    // Is dashboard guide window
    public boolean getIsDashboardGuideWndShow() {
        return objComman.getBoolean("IsDashboardGuideWndShow", false);
    }

    public void setIsDashboardGuideWndShow(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("IsDashboardGuideWndShow", text);
        prefsEditor.apply();
    }

    // Is passenger setting guide window
    public boolean getIsPassSetGuideWndShow() {
        return objComman.getBoolean("IsPassSetGuideWndShow", false);
    }

    public void setIsPassSetGuideWndShow(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("IsPassSetGuideWndShow", text);
        prefsEditor.apply();
    }

    public String getPaymentAmount() {
        return objComman.getString("PaymentAmount", "");
    }

    public void setPaymentAmount(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("PaymentAmount", text);
        prefsEditor.apply();
    }

    public String getKmMonitorFromDate() {
        return objComman.getString("KmMonitorFromDate", "");
    }

    public void setKmMonitorFromDate(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("KmMonitorFromDate", text);
        prefsEditor.apply();
    }

    public String getKmMonitorToDate() {
        return objComman.getString("KmMonitorToDate", "");
    }

    public void setKmMonitorToDate(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("KmMonitorToDate", text);
        prefsEditor.apply();
    }

    public String getTodaysDate() {
        return objComman.getString("TodaysDate", "");
    }

    public void setTodaysDate(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("TodaysDate", text);
        prefsEditor.apply();
    }

    // Is dashboard guide window
    public String getShortCutTodaysDate() {
        return objComman.getString("ShortCutTodaysDate", "");
    }

    public void setShortCutTodaysDate(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("ShortCutTodaysDate", text);
        prefsEditor.apply();
    }

    public boolean getIsShortcutPopupShow() {
        return objComman.getBoolean("IsShortcutPopupShow", true);
    }

    public void setIsShortcutPopupShow(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("IsShortcutPopupShow", text);
        prefsEditor.apply();
    }

    // Is dashboard guide window
    public boolean getIsShortcutPopupClick() {
        return objComman.getBoolean("IsShortcutPopupClick", true);
    }

    public void setIsShortcutPopupClick(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("IsShortcutPopupClick", text);
        prefsEditor.apply();
    }

    // Is reset app
    public boolean getIsResetApp() {
        return objComman.getBoolean("IsResetApp", false);
    }

    public void setIsResetApp(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("IsResetApp", text);
        prefsEditor.apply();
    }

    public int getNotiID() {
        return objComman.getInt("NotiID", 2);
    }

    public void setNotiID(int text) {
        prefsEditor = objComman.edit();
        prefsEditor.putInt("NotiID", text);
        prefsEditor.apply();
    }

    // Is reset app
    public boolean getIsNeedToRefreshList() {
        return objComman.getBoolean("IsNeedToRefreshList", false);
    }

    public void setIsNeedToRefreshList(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("IsNeedToRefreshList", text);
        prefsEditor.apply();
    }

    // Is reset app
    public boolean getIsTyreSettingChange() {
        return objComman.getBoolean("IsTyreSettingChange", false);
    }

    public void setIsTyreSettingChange(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("IsTyreSettingChange", text);
        prefsEditor.apply();
    }


    // Expiry deviceID lis
    public String getExpiryDeviceIdList() {
        return objComman.getString("ExpiryDeviceIdList", "0");
    }

    public void setExpiryDeviceIdList(String text) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("ExpiryDeviceIdList", text);
        prefsEditor.apply();
    }


    public String getAuthToken() {
        return objComman.getString("AuthToken", "");
    }

    public void setAuthToken(String authToken) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("AuthToken", authToken);
        prefsEditor.apply();
    }

    public String getAuthTokenType() {
        return objComman.getString("AuthTokenType", "");
    }

    public void setAuthTokenType(String authTokenType) {
        prefsEditor = objComman.edit();
        prefsEditor.putString("AuthTokenType", authTokenType);
        prefsEditor.apply();
    }

    public boolean getIsForceUpdateApp() {
        return objComman.getBoolean("IsForceUpdateApp", false);
    }

    public void setIsForceUpdateApp(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("IsForceUpdateApp", text);
        prefsEditor.apply();
    }

    public boolean getIsSatelliteMap() {
        return objComman.getBoolean("IsSatelliteMap", false);
    }

    public void setIsSatelliteMap(boolean text) {
        prefsEditor = objComman.edit();
        prefsEditor.putBoolean("IsSatelliteMap", text);
        prefsEditor.apply();
    }

}
