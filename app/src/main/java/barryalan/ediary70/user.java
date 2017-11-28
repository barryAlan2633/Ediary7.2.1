package barryalan.ediary70;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

/**
 * Created by BarryAlan on 11/6/2017.
 */

public class user {

    //CLASS VARIABLES-------------------------------------------------------------------------------
    private int userId;
    private String userName;
    private String userUsername;
    private String userEmail;
    private String userPassword;
    private String userMedication;
    private String userDiet;
    private String userAllergies;
    private String userVitalSigns;
    private String userExcercisePlan;
    private String userGoalNames;
    private String userGoalDescriptions;
    private String userGoalTime;
    private String userGoal4;
    private String userLongGoal;

    public static String currentUserName = "";
    public static int currentUserID;
    public static int currentUserGoalNumber;
    public static int currentNumberOfGoals;

    //Constructors----------------------------------------------------------------------------------
    //Default constructor
    public user() {
    }

    //Parametized constructor
    public user(String userName, String userUsername, String userEmail, String userPassword) {
        this.userName = userName;
        this.userUsername = userUsername;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userMedication = "";
        this.userAllergies = "";
        this.userVitalSigns = "";
        this.userDiet = "";
        this.userExcercisePlan = "";
        this.userGoalNames = "";
        this.userGoalDescriptions = "";
        this.userGoalTime = "";
        this.userGoal4 = "";
        this.userLongGoal = "";
    }

    //Parametized constructor used in login page
    public user(int userId,String userName, String userUsername, String userEmail, String userPassword) {
        this.userId = userId;
        this.userName = userName;
        this.userUsername = userUsername;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userMedication = "";
        this.userAllergies = "";
        this.userVitalSigns = "";
        this.userDiet = "";
        this.userExcercisePlan = "";
        this.userGoalNames = "";
        this.userGoalDescriptions = "";
        this.userGoalTime = "";
        this.userGoal4 = "";
        this.userLongGoal = "";
    }

    //Parametized constructor used in health care page
    public user(int userId,String userName, String userUsername, String userEmail, String userPassword, String userAllergies,
                String userMedication, String userDiet, String userExcercisePlan, String userVitalSigns,
                String userGoal1, String userGoal2, String userGoal3, String userGoal4, String userLongGoal ) {

        this.userId = userId;
        this.userName = userName;
        this.userUsername = userUsername;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userMedication = userMedication;
        this.userAllergies = userAllergies;
        this.userVitalSigns = userVitalSigns;
        this.userDiet = userDiet;
        this.userExcercisePlan = userExcercisePlan;
        this.userGoalNames = "";
        this.userGoalDescriptions = "";
        this.userGoalTime = "";
        this.userGoal4 = userGoal4;
        this.userLongGoal = userLongGoal;
    }

    //Get methods-----------------------------------------------------------------------------------
    public int getId() {return userId;}

    public String getUserName() {return userName;}

    public String getUserUsername() {return userUsername;}

    public String getUserEmail() {return userEmail;}

    public String getuserPassword() {return userPassword;}

    public String getUserAllergies() {return userAllergies;}

    public String getUserMedications() {return userMedication;}

    public String getUserVitalSigns() {return userVitalSigns;}

    public String getUserExcercisePlan() {return userExcercisePlan;}

    public String getUserDiet() {return userDiet;}

    public String getUserGoalNames() {return userGoalNames;}

    public String getUserGoalDescriptions() {return userGoalDescriptions;}

    public String getUserGoalTimes() {return userGoalTime;}

    public String getUserGoal4() {return userGoal4;}

    public String getUserLongGoal() {return userLongGoal;}

    //Setter methods--------------------------------------------------------------------------------
    public void setUserId(int userId){this.userId = userId;}

    public void setUserName(String userName) {this.userName = userName;}

    public void setUserUsername(String userUsername) {this.userUsername = userUsername;}

    public void setUserEmail(String userEmail) {this.userEmail = userEmail;}

    public void setUserPassword(String userPassword){this.userPassword = userPassword;}

    public void setUserAllergies(String userAllergies) {this.userAllergies = userAllergies;}

    public void setUserMedications(String userMedication) {this.userMedication = userMedication;}

    public void setUserVitalSigns(String userVitalSigns) {this.userVitalSigns = userVitalSigns;}

    public void setUserExcercisePlan(String userExcercisePlan) {this.userExcercisePlan = userExcercisePlan;}

    public void setUserDiet(String userDiet) {this.userDiet = userDiet;}

    public void setCurrentUserName(String username){ this.currentUserName = username; }

    public void setCurrentUserID(int ID){ this.currentUserID = ID; }

    public void setUserGoalNames(String userGoalname) {
        if(TextUtils.isEmpty(userGoalname)){
            this.userGoalNames = null;
            return;
        }
            this.userGoalNames = userGoalname;
    }

    public void setUserGoalDescriptions(String userGoalDescription) {
        if(TextUtils.isEmpty(userGoalDescription)){
            this.userGoalDescriptions = null;
            return;
        }
        this.userGoalDescriptions = userGoalDescription;
    }

    public void setUserGoalTimes(String userGoaltime) {
        if(TextUtils.isEmpty(userGoaltime)){
            this.userGoalTime = null;
            return;
        }
        this.userGoalTime = userGoaltime;
    }

    public void setUserGoal4(String userGoal4) { this.userGoal4 = userGoal4;}

    public void setUserLongGoal (String userLongGoal) {this.userLongGoal = userLongGoal;}

}

