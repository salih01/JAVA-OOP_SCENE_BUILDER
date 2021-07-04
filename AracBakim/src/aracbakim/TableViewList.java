/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aracbakim;

/**
 *
 * @author alfa
 */

/*
----------------------------- FlyWeight Tasarim kalibi .Bukalıbı sistem belleginin ve nesnelerin 
------------------------------etkin bir biçimde kullanmak amacıyla ortak kullanılan nesnelerin yani
----------------------------- ad soyad marka model .. yönetiminde kullandık .
----------------------------tabloda listeleme yapmtığımız classları ve formları null yaptıgimiz yer  17.satirdan 26.satira kadardir 
*/
public class TableViewList {
        private String Sahip=null;
        private String Plaka=null;
        private String AracTuru=null;
	private String Marka=null;
	private String Model=null;
	private String Date=null;
	private String Km=null;
        
        
public TableViewList(){ }
        

public TableViewList(String Sahip, String Plaka, String AracTuru, String Marka, String Model, String Date, String Km) 
{
    this.Sahip=Sahip;
    this.Plaka=Plaka;
    this.AracTuru=AracTuru;
    this.Marka=Marka;
    this.Model=Model;
    this.Date=Date;
    this.Km=Km;
}

    /**
     * @return the Sahip
     */
    public String getSahip() {
        return Sahip;
    }

    /**
     * @param Sahip the Sahip to set
     */
    public void setSahip(String Sahip) {
        this.Sahip = Sahip;
    }

    /**
     * @return the Plaka
     */
    public String getPlaka() {
        return Plaka;
    }

    /**
     * @param Plaka the Plaka to set
     */
    public void setPlaka(String Plaka) {
        this.Plaka = Plaka;
    }

    /**
     * @return the AracTuru
     */
    public String getAracTuru() {
        return AracTuru;
    }

    /**
     * @param AracTuru the AracTuru to set
     */
    public void setAracTuru(String AracTuru) {
        this.AracTuru = AracTuru;
    }

    /**
     * @return the Marka
     */
    public String getMarka() {
        return Marka;
    }

    /**
     * @param Marka the Marka to set
     */
    public void setMarka(String Marka) {
        this.Marka = Marka;
    }

    /**
     * @return the Model
     */
    public String getModel() {
        return Model;
    }

    /**
     * @param Model the Model to set
     */
    public void setModel(String Model) {
        this.Model = Model;
    }

    /**
     * @return the Date
     */
    public String getDate() {
        return Date;
    }

    /**
     * @param Date the Date to set
     */
    public void setDate(String Date) {
        this.Date = Date;
    }

    /**
     * @return the Km
     */
    public String getKm() {
        return Km;
    }

    /**
     * @param Km the Km to set
     */
    public void setKm(String Km) {
        this.Km = Km;
    }
        
        
        
        
        
        
        
        
}

