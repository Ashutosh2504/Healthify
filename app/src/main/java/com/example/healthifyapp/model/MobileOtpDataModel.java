package com.example.healthifyapp.model;

public class MobileOtpDataModel {


    public String mobileno;
    public String otp;
    public String status;
    public String message;
    public UserAccountDataModel result;

   // public MobileOtpDataModel() { comment line rahul
    //    }

    public MobileOtpDataModel(String mobileno, String otp, String status, String message, UserAccountDataModel result) {
        this.mobileno = mobileno;
        this.otp = otp;
        this.status = status;
        this.message = message;
        this.result = result;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserAccountDataModel getResult() {
        return result;
    }

    public void setResult(UserAccountDataModel result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "MobileOtpDataModel{" +
                "mobileno='" + mobileno + '\'' +
                ", otp='" + otp + '\'' +
                ", status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }
}
