package barryalan.ediary70;

/**
 * Created by Alexa on 11/27/2017.
 */

public class networksandcontactsDAO {

    private int userId;
    private String userName;
    private String ncContactsName;
    private String ncAffiliation;
    private String ncDate;
    private String ncUsages;
    private String ncComments;


    //default constructor
    public networksandcontactsDAO()
    {

    }

    public networksandcontactsDAO(String ncContactsName, String ncAffiliation, String ncDate, String ncUsages, String ncComments )
    {
        this.ncContactsName = ncContactsName;
        this.ncAffiliation = ncAffiliation;
        this.ncDate = ncDate;
        this.ncUsages = ncUsages;
        this.ncComments = ncComments;

    }


    //setters
    public void setUserId(int userId){this.userId = userId;}
    public void setUserName(String userName) {this.userName = userName;}
    public void setNcContactsName(String ncContactsName) {this.ncContactsName = ncContactsName;}
    public void setNcAffiliation(String ncAffiliation) {this.ncAffiliation = ncAffiliation;}
    public void setNcDate(String ncDate) {this.ncDate = ncDate;}
    public void setNcUsages(String ncUsages) {this.ncUsages = ncUsages;}
    public void setNcComments(String ncComments) {this.ncComments = ncComments;}

    //getters
    public int getId() {return userId;}
    public String getUserName() {return userName;}
    public String getNcContactsName() {return ncContactsName;}
    public String getNcAffiliation() {return ncAffiliation;}
    public String getNcDate() {return ncUsages;}
    public String getNcUsages() {return ncUsages;}
    public String getNcComments() {return ncComments;}

}
