package barryalan.ediary70;

/**
 * Created by Alexa on 11/27/2017.
 */

public class careedandeducationgoalsDAO {
    private int ceId;
    private String ceName;
    private String ceGoals;


    public careedandeducationgoalsDAO(){}


    public careedandeducationgoalsDAO(String ceGoals) {

        this.ceGoals = ceGoals;
    }

    public int getCeId() {
        return ceId;
    }

    public void setCeId(int ceId) {
        this.ceId = ceId;
    }

    public String getCeName() {
        return ceName;
    }

    public void setCeName(String ceName) {
        this.ceName = ceName;
    }

    public String getCeGoals() {
        return ceGoals;
    }

    public void setCeGoals(String ceGoals) {
        this.ceGoals = ceGoals;
    }
}
