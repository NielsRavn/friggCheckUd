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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(int alarmId) {
        this.alarmId = alarmId;
    }

    public int getEvaNr() {
        return evaNr;
    }

    public void setEvaNr(int evaNr) {
        this.evaNr = evaNr;
    }

    public int getBrandrapportNr() {
        return brandrapportNr;
    }

    public void setBrandrapportNr(int brandrapportNr) {
        this.brandrapportNr = brandrapportNr;
    }

    public String getNavne() {
        return navne;
    }

    public void setNavne(String navne) {
        this.navne = navne;
    }

    public String getAddresser() {
        return addresser;
    }

    public void setAddresser(String addresser) {
        this.addresser = addresser;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getGruppeNr() {
        return gruppeNr;
    }

    public void setGruppeNr(int gruppeNr) {
        this.gruppeNr = gruppeNr;
    }

    public int getDetektorNr() {
        return detektorNr;
    }

    public void setDetektorNr(int detektorNr) {
        this.detektorNr = detektorNr;
    }

    public String getComment() {
        return comment;
    }

    public void setBemærkning(String comment) {
        this.comment = comment;
    }

    @Override
    public String getName() {
        return "id: " + id;
    }
    
    
    
}
