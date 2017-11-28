package barryalan.ediary70;

/**
 * Created by Alexa on 11/27/2017.
 */

public class financialgoalsDAO {

    private int userId;
    private String userName;
    private String fgCash;
    private String fgAssets;
    private String fgLiabilities;
    private String fgCreditCard1Name;
    private String fgCreditCard1Balance;
    private String fgCreditCard2Name;
    private String fgCreditCard2Balance;
    private String fgStocks;
    private String fgSGoalNames;
    private String fgSGoalDescriptions;
    private String fgSGoalTime;
    private String fgLGoalNames;
    private String fgLGoalDescriptions;
    private String fgLGoalTime;

    public financialgoalsDAO(){}

    public financialgoalsDAO(String fgCash,
                             String fgAssets,
                             String fgLiabilities,
                             String fgCreditCard1Name,
                             String fgCreditCard1Balance,
                             String fgCreditCard2Name,
                             String fgCreditCard2Balance,
                             String fgStocks,
                             String fgSGoalNames,
                             String fgSGoalDescriptions,
                             String fgSGoalTime,
                             String fgLGoalNames,
                             String fgLGoalDescriptions,
                             String fgLGoalTime)
    {
        this.fgAssets = fgAssets;
        this.fgLiabilities = fgLiabilities;
     this.fgCreditCard1Name = fgCreditCard1Name;
        this.fgCreditCard1Balance = fgCreditCard1Balance;
        this.fgCreditCard2Name = fgCreditCard2Name;
        this.fgCreditCard2Balance = fgCreditCard2Balance;
        this.fgStocks = fgStocks;
        this.fgSGoalNames = fgSGoalNames;
        this.fgSGoalDescriptions = fgSGoalDescriptions;
        this.fgSGoalTime = fgSGoalTime;
        this.fgLGoalNames = fgLGoalNames;
        this.fgLGoalDescriptions = fgLGoalDescriptions;
        this.fgLGoalTime = fgLGoalTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFgCash() {
        return fgCash;
    }

    public void setFgCash(String fgCash) {
        this.fgCash = fgCash;
    }

    public String getFgAssets() {
        return fgAssets;
    }

    public void setFgAssets(String fgAssets) {
        this.fgAssets = fgAssets;
    }

    public String getFgLiabilities() {
        return fgLiabilities;
    }

    public void setFgLiabilities(String fgLiabilities) {
        this.fgLiabilities = fgLiabilities;
    }

    public String getFgCreditCard1Name() {
        return fgCreditCard1Name;
    }

    public void setFgCreditCard1Name(String fgCreditCard1Name) {
        this.fgCreditCard1Name = fgCreditCard1Name;
    }

    public String getFgCreditCard1Balance() {
        return fgCreditCard1Balance;
    }

    public void setFgCreditCard1Balance(String fgCreditCard1Balance) {
        this.fgCreditCard1Balance = fgCreditCard1Balance;
    }

    public String getFgCreditCard2Name() {
        return fgCreditCard2Name;
    }

    public void setFgCreditCard2Name(String fgCreditCard2Name) {
        this.fgCreditCard2Name = fgCreditCard2Name;
    }

    public String getFgCreditCard2Balance() {
        return fgCreditCard2Balance;
    }

    public void setFgCreditCard2Balance(String fgCreditCard2Balance) {
        this.fgCreditCard2Balance = fgCreditCard2Balance;
    }

    public String getFgStocks() {
        return fgStocks;
    }

    public void setFgStocks(String fgStocks) {
        this.fgStocks = fgStocks;
    }

    public String getFgSGoalNames() {
        return fgSGoalNames;
    }

    public void setFgSGoalNames(String fgSGoalNames) {
        this.fgSGoalNames = fgSGoalNames;
    }

    public String getFgSGoalDescriptions() {
        return fgSGoalDescriptions;
    }

    public void setFgSGoalDescriptions(String fgSGoalDescriptions) {
        this.fgSGoalDescriptions = fgSGoalDescriptions;
    }

    public String getFgSGoalTime() {
        return fgSGoalTime;
    }

    public void setFgSGoalTime(String fgSGoalTime) {
        this.fgSGoalTime = fgSGoalTime;
    }

    public String getFgLGoalNames() {
        return fgLGoalNames;
    }

    public void setFgLGoalNames(String fgLGoalNames) {
        this.fgLGoalNames = fgLGoalNames;
    }

    public String getFgLGoalDescriptions() {
        return fgLGoalDescriptions;
    }

    public void setFgLGoalDescriptions(String fgLGoalDescriptions) {
        this.fgLGoalDescriptions = fgLGoalDescriptions;
    }

    public String getFgLGoalTime() {
        return fgLGoalTime;
    }

    public void setFgLGoalTime(String fgLGoalTime) {
        this.fgLGoalTime = fgLGoalTime;
    }
}
