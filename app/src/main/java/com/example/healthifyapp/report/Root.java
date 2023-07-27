package com.example.healthifyapp.report;

import java.util.ArrayList;
import java.util.List;
public  class Root {
    private String status;
    private String message;
    //private List<Result> result = new ArrayList<Result>();
    private  List<Root.Result> result;
   // private List<Root.Result.DietAnalysisDetails> dietAnalysisDetailsList;


    public Root(String status, String message, List<Result> result/*, List<Result.DietAnalysisDetails> dietAnalysisDetailsList*/) {
        this.status = status;
        this.message = message;
        this.result = result;
        //this.dietAnalysisDetailsList = dietAnalysisDetailsList;
    }

    @Override
    public String toString() {
        return "Root{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
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

    public List<Root.Result> getResult() {
        return result;
    }

    public void setResult(List<Root.Result> result) {
        this.result = result;
    }


    //child
    public class Result {



        private Integer dietAnalysisId;
        private Integer userAccountId;
        private String dietAnalysisType;
        private String dietTime;
        private String dietType;
        private String fromdate;
        private String todate;

        private List<DietAnalysisDetails> dietAnalysisDetails = new ArrayList<DietAnalysisDetails>();


        //  private  DietAnalysisDetails dietAnalysisDetails;

       /* public DietAnalysisDetails getDietAnalysisDetails() {
            return dietAnalysisDetails;
        }
*/
        public List<DietAnalysisDetails> getDietAnalysisDetailsList() {
            return dietAnalysisDetails;
        }

        public void setDietAnalysisDetailsList(List<DietAnalysisDetails> dietAnalysisDetails) {
            this.dietAnalysisDetails = dietAnalysisDetails;
        }

       /* public void setDietAnalysisDetails(DietAnalysisDetails dietAnalysisDetails) {
            this.dietAnalysisDetails = dietAnalysisDetails;
        }*/

        public Result() {
        }

        public Result(Integer dietAnalysisId, Integer userAccountId, String dietAnalysisType, String dietTime, String dietType, String fromdate, String todate, List<DietAnalysisDetails> dietAnalysisDetails) {
            this.dietAnalysisId = dietAnalysisId;
            this.userAccountId = userAccountId;
            this.dietAnalysisType = dietAnalysisType;
            this.dietTime = dietTime;
            this.dietType = dietType;
            this.fromdate = fromdate;
            this.todate = todate;
            this.dietAnalysisDetails = dietAnalysisDetails;
        }

        public String getFromdate() {
            return fromdate;
        }

        public void setFromdate(String fromdate) {
            this.fromdate = fromdate;
        }

        public String getTodate() {
            return todate;
        }

        public void setTodate(String todate) {
            this.todate = todate;
        }
// public Result(Integer dietAnalysisId, Integer userAccountId, String dietAnalysisType,
        //                      String dietTime, String dietType,
        //                      List<DietAnalysisDetails> dietAnalysisDetails) {
        //            this.dietAnalysisId = dietAnalysisId;
        //            this.userAccountId = userAccountId;
        //            this.dietAnalysisType = dietAnalysisType;
        //            this.dietTime = dietTime;
        //            this.dietType = dietType;
        //            this.dietAnalysisDetails = dietAnalysisDetails;
        ////            this.dietAnalysisDetails = dietAnalysisDetails;
        //        }

        public Integer getDietAnalysisId() {
            return dietAnalysisId;
        }

        public void setDietAnalysisId(Integer dietAnalysisId) {
            this.dietAnalysisId = dietAnalysisId;
        }

        public Integer getUserAccountId() {
            return userAccountId;
        }

        public void setUserAccountId(Integer userAccountId) {
            this.userAccountId = userAccountId;
        }

        public String getDietAnalysisType() {
            return dietAnalysisType;
        }

        public void setDietAnalysisType(String dietAnalysisType) {
            this.dietAnalysisType = dietAnalysisType;
        }

        public String getDietTime() {
            return dietTime;
        }

        public void setDietTime(String dietTime) {
            this.dietTime = dietTime;
        }

        public String getDietType() {
            return dietType;
        }

        public void setDietType(String dietType) {
            this.dietType = dietType;
        }


        @Override
        public String toString() {
            return "Result{" +
                    "dietAnalysisId=" + dietAnalysisId +
                    ", userAccountId=" + userAccountId +
                    ", dietAnalysisType='" + dietAnalysisType + '\'' +
                    ", dietTime='" + dietTime + '\'' +
                    ", dietType='" + dietType + '\'' +

                    ", dietAnalysisDetails=" + dietAnalysisDetails +
                    '}';
        }

        //child's child
        public class DietAnalysisDetails {


            private Integer dietAnalysisDetailId;
            private Integer dietAnalysisId;
            private Integer userAccountId;
            private String date;
            private String time;
            private String item;
            private String quantity;
            private String kCal;
            private String moisture;
            private String protein;
            private String ash;
            private String totalFat;
            private String totalfiber;
            private String insoluble;
            private String soluble;
            private String carbohydrate;
            private String energyKJ;
            private String energyKcal;
            public String sodium;
            public String potassium;
            public String phosphorus;
            public String calcium;
            public String magnesium;
            public  int totalConsumedKcal;

            @Override
            public String toString() {
                return "DietAnalysisDetails{" +
                        "dietAnalysisDetailId=" + dietAnalysisDetailId +
                        ", dietAnalysisId=" + dietAnalysisId +
                        ", userAccountId=" + userAccountId +
                        ", date='" + date + '\'' +
                        ", time='" + time + '\'' +
                        ", item='" + item + '\'' +
                        ", quantity='" + quantity + '\'' +
                        ", kCal='" + kCal + '\'' +
                        ", moisture='" + moisture + '\'' +
                        ", protein='" + protein + '\'' +
                        ", ash='" + ash + '\'' +
                        ", totalFat='" + totalFat + '\'' +
                        ", totalfiber='" + totalfiber + '\'' +
                        ", insoluble='" + insoluble + '\'' +
                        ", soluble='" + soluble + '\'' +
                        ", carbohydrate='" + carbohydrate + '\'' +
                        ", energyKJ='" + energyKJ + '\'' +
                        ", energyKcal='" + energyKcal + '\'' +
                        ", sodium='" + sodium + '\'' +
                        ", potassium='" + potassium + '\'' +
                        ", phosphorus='" + phosphorus + '\'' +
                        ", calcium='" + calcium + '\'' +
                        ", magnesium='" + magnesium + '\'' +
                        ", totalConsumedKcal='" + totalConsumedKcal + '\'' +
                        '}';
            }

            public int getTotalConsumedKcal() {
                return totalConsumedKcal;
            }

            public void setTotalConsumedKcal(int totalConsumedKcal) {
                this.totalConsumedKcal = totalConsumedKcal;
            }

            public String getSodium() {
                return sodium;
            }

            public void setSodium(String sodium) {
                this.sodium = sodium;
            }

            public String getPotassium() {
                return potassium;
            }

            public void setPotassium(String potassium) {
                this.potassium = potassium;
            }

            public String getPhosphorus() {
                return phosphorus;
            }

            public void setPhosphorus(String phosphorus) {
                this.phosphorus = phosphorus;
            }

            public String getCalcium() {
                return calcium;
            }

            public void setCalcium(String calcium) {
                this.calcium = calcium;
            }

            public String getMagnesium() {
                return magnesium;
            }

            public void setMagnesium(String magnesium) {
                this.magnesium = magnesium;
            }

            public String getMoisture() {
                return moisture;
            }

            public void setMoisture(String moisture) {
                this.moisture = moisture;
            }

            public String getProtein() {
                return protein;
            }

            public void setProtein(String protein) {
                this.protein = protein;
            }

            public String getAsh() {
                return ash;
            }

            public void setAsh(String ash) {
                this.ash = ash;
            }

            public String getTotalFat() {
                return totalFat;
            }

            public void setTotalFat(String totalFat) {
                this.totalFat = totalFat;
            }

            public String getTotalfiber() {
                return totalfiber;
            }

            public void setTotalfiber(String totalfiber) {
                this.totalfiber = totalfiber;
            }

            public String getInsoluble() {
                return insoluble;
            }

            public void setInsoluble(String insoluble) {
                this.insoluble = insoluble;
            }

            public String getSoluble() {
                return soluble;
            }

            public void setSoluble(String soluble) {
                this.soluble = soluble;
            }

            public String getCarbohydrate() {
                return carbohydrate;
            }

            public void setCarbohydrate(String carbohydrate) {
                this.carbohydrate = carbohydrate;
            }

            public String getEnergyKJ() {
                return energyKJ;
            }

            public void setEnergyKJ(String energyKJ) {
                this.energyKJ = energyKJ;
            }

            public String getEnergyKcal() {
                return energyKcal;
            }

            public void setEnergyKcal(String energyKcal) {
                this.energyKcal = energyKcal;
            }

            public DietAnalysisDetails() {
            }

            public DietAnalysisDetails(Integer dietAnalysisDetailId, Integer dietAnalysisId, Integer userAccountId, String date, String time, String item, String quantity, String kCal) {
                this.dietAnalysisDetailId = dietAnalysisDetailId;
                this.dietAnalysisId = dietAnalysisId;
                this.userAccountId = userAccountId;
                this.date = date;
                this.time = time;
                this.item = item;
                this.quantity = quantity;
                this.kCal = kCal;

            }

            public Integer getDietAnalysisDetailId() {
                return dietAnalysisDetailId;
            }

            public void setDietAnalysisDetailId(Integer dietAnalysisDetailId) {
                this.dietAnalysisDetailId = dietAnalysisDetailId;
            }

            public Integer getDietAnalysisId() {
                return dietAnalysisId;
            }

            public void setDietAnalysisId(Integer dietAnalysisId) {
                this.dietAnalysisId = dietAnalysisId;
            }

            public Integer getUserAccountId() {
                return userAccountId;
            }

            public void setUserAccountId(Integer userAccountId) {
                this.userAccountId = userAccountId;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getItem() {
                return item;
            }

            public void setItem(String item) {
                this.item = item;
            }

            public String getQuantity() {
                return quantity;
            }

            public void setQuantity(String quantity) {
                this.quantity = quantity;
            }

            public String getkCal() {
                return kCal;
            }

            public void setkCal(String kCal) {
                this.kCal = kCal;
            }




        }
    }


}
