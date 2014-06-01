/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BE;

/**
 *
 * @author Niels
 */
public class Story implements IViewObjectBE{
    
    
    
    int id, alarmId, evaNr, brandrapportNr;
    String navne, addresser;
    int type, gruppeNr, detektorNr;
    String  comment;
    
    public Story(int alarmId){
        this.alarmId = alarmId;
    }

    public Story(int id, int alarmId, int evaNr, int brandrapportNr, String navne, String addresser, int type, int gruppeNr, int detektorNr, String comment) {
        this.id = id;
        this.alarmId = alarmId;
        this.evaNr = evaNr;
        this.brandrapportNr = brandrapportNr;
        this.navne = navne;
        this.addresser = addresser;
        this.type = type;
        this.gruppeNr = gruppeNr;
        this.detektorNr = detektorNr;
        this.comment = comment;
    }

    public Story(int alarmId, int evaNr, int brandrapportNr, String navne, String addresser, int type, int gruppeNr, int detektorNr, String comment) {
        this.alarmId = alarmId;
        this.evaNr = evaNr;
        this.brandrapportNr = brandrapportNr;
        this.navne = navne;
        this.addresser = addresser;
        this.type = type;
        this.gruppeNr = gruppeNr;
        this.detektorNr = detektorNr;
        this.comment = comment;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets a new id
     * @param id the new id
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * @return the alarmid
     */
    public int getAlarmId() {
        return alarmId;
    }
    /**
     * Sets a new alarm id
     * @param alarmId the new alarm id 
     */
    public void setAlarmId(int alarmId) {
        this.alarmId = alarmId;
    }
    /**
     * @return the eva repport number
     */
    public int getEvaNr() {
        return evaNr;
    }
    /**
     * Sets a new eva repport number
     * @param evaNr the new eva repport number
     */
    public void setEvaNr(int evaNr) {
        this.evaNr = evaNr;
    }
    /**
     * @return the fire repport number
     */
    public int getBrandrapportNr() {
        return brandrapportNr;
    }
    /**
     * Sets a new fire repport number
     * @param brandrapportNr the new fire repport number
     */
    public void setBrandrapportNr(int brandrapportNr) {
        this.brandrapportNr = brandrapportNr;
    }
    /**
     * @return the names
     */
    public String getNavne() {
        return navne;
    }
    /**
     * Sets new names
     * @param navne the new names
     */
    public void setNavne(String navne) {
        this.navne = navne;
    }
    /**
     * @return the addresses
     */
    public String getAddresser() {
        return addresser;
    }
    /**
     * Sets new addresses
     * @param addresser the new addresses
     */
    public void setAddresser(String addresser) {
        this.addresser = addresser;
    }
    /**
     * @return the type
     */
    public int getType() {
        return type;
    }
    /**
     * sets a new type
     * @param type the new type
     */
    public void setType(int type) {
        this.type = type;
    }
    /**
     * @return group number
     */
    public int getGruppeNr() {
        return gruppeNr;
    }
    /**
     * Sets a new group number
     * @param gruppeNr the new group number
     */
    public void setGruppeNr(int gruppeNr) {
        this.gruppeNr = gruppeNr;
    }
    /**
     * @return the detector number
     */
    public int getDetektorNr() {
        return detektorNr;
    }
    /**
     * Sets a new detector number
     * @param detektorNr the new detector number
     */
    public void setDetektorNr(int detektorNr) {
        this.detektorNr = detektorNr;
    }
    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }
    /**
     * Sets a new comment
     * @param comment the new comment
     */
    public void setBemærkning(String comment) {
        this.comment = comment;
    }
    /**
     * @return a string representation of the story
     */
    @Override
    public String getName() {
        return "id: " + id;
    }
    
    
    
}
