package com.ahwajkafabi.pencatatandatalimbah.Data;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class Cekhlist implements Serializable {

    private String mId;
    private String name;
    private String description;
    private String lokasi;
    private String hari;
    private String dob;
    private String dod;
    private String phinletmsk;
    private String phinletout;
    private String phoutletmsk;
    private String phoutletout;
    private String suhuinletmsk;
    private String suhuinletout;
    private String suhuoutletmsk;
    private String suhuoutletout;
    private String alumunium;
    private String floaculan;
    private String hasilwaste;
    private String sludgemsk;
    private String sludgeout;
    private String debit;
    private String selisih;
    private String baik;
    private String rusak;
    private String key;

    public Cekhlist() {
        //constructor kosong diperlukan
    }
    public Cekhlist(String key, String name, String description, String lokasi, String hari, String dob,
                    String dod, String phinletmsk, String phinletout, String phoutletmsk, String phoutletout,
                    String suhuinletmsk, String suhuinletout, String suhuoutletmsk, String suhuoutletout,
                    String alumunium, String floaculan, String hasilwaste, String sludgemsk, String sludgeout,
                    String debit, String selisih, String baik, String rusak ) {
        if (name.trim().equals("")) {
            name = "Cekhlist NoName";
        }
        this.key=key;
        this.name = name;
        this.description = description;
        this.lokasi = lokasi;
        this.hari = hari;
        this.dob = dob;
        this.dod = dod;
        this.phinletmsk = phinletmsk;
        this.phinletout = phinletout;
        this.phoutletmsk = phoutletmsk;
        this.phoutletout = phoutletout;
        this.suhuinletmsk = suhuinletmsk;
        this.suhuinletout = suhuinletout;
        this.suhuoutletmsk = suhuoutletmsk;
        this.suhuoutletout = suhuoutletout;
        this.alumunium = alumunium;
        this.floaculan = floaculan;
        this.hasilwaste = hasilwaste;
        this.sludgemsk = sludgemsk;
        this.sludgeout = sludgeout;
        this.debit = debit;
        this.selisih = selisih;
        this.baik = baik;
        this.rusak = rusak;
    }

    public String getId() {
        return mId;
    }
    public void setId(String id) {
        mId = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getLokasi() {
        return lokasi;
    }
    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getHari() {
        return hari;
    }
    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getDob() {
        return dob;
    }
    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDod() {
        return dod;
    }
    public void setDod(String dod) {
        this.dod = dod;
    }

    public String getPhinletmsk() {
        return phinletmsk;
    }
    public void setPhinletmsk(String phinletmsk) {
        this.phinletmsk = phinletmsk;
    }

    public String getPhinletout() {
        return phinletout;
    }
    public void setPhinletout(String phinletout) {
        this.phinletout = phinletout;
    }

    public String getPhoutletmsk() {
        return phoutletmsk;
    }
    public void setPhoutletmsk(String phoutletmsk) {
        this.phoutletmsk = phoutletmsk;
    }

    public String getPhoutletout() {
        return phoutletout;
    }
    public void setPhoutletout(String phoutletout) {
        this.phoutletout = phoutletout;
    }

    public String getSuhuinletmsk() {
        return suhuinletmsk;
    }
    public void setSuhuinletmsk(String suhuinletmsk) {
        this.suhuinletmsk = suhuinletmsk;
    }

    public String getSuhuinletout() {
        return suhuinletout;
    }
    public void setSuhuinletout(String suhuinletout) {
        this.suhuinletout = suhuinletout;
    }

    public String getSuhuoutletmsk() {
        return suhuoutletmsk;
    }
    public void setSuhuoutletmsk(String suhuoutletmsk) {
        this.suhuoutletmsk = suhuoutletmsk;
    }

    public String getSuhuoutletout() {
        return suhuoutletout;
    }
    public void setSuhuoutletout(String suhuoutletout) {
        this.suhuoutletout = suhuoutletout;
    }

    public String getAlumunium() {
        return alumunium;
    }
    public void setAlumunium(String alumunium) {
        this.alumunium = alumunium;
    }

    public String getFloaculan() {
        return floaculan;
    }
    public void setFloaculan(String floaculan) {
        this.floaculan = floaculan;
    }

    public String getHasilwaste() {
        return hasilwaste;
    }
    public void setHasilwaste(String hasilwaste) {
        this.hasilwaste = hasilwaste;
    }

    public String getSludgemsk() {
        return sludgemsk;
    }
    public void setSludgemsk(String sludgemsk) {
        this.sludgemsk = sludgemsk;
    }

    public String getSludgeout() {
        return sludgeout;
    }
    public void setSludgeout(String sludgeout) {
        this.sludgeout = sludgeout;
    }

    public String getDebit() {
        return debit;
    }
    public void setDebit(String debit) {
        this.debit = debit;
    }

    public String getSelisih() {
        return selisih;
    }
    public void setSelisih(String selisih) {
        this.selisih = selisih;
    }

    public String getBaik() {
        return baik;
    }
    public void setBaik(String baik) {
        this.baik = baik;
    }

    public String getRusak() {
        return rusak;
    }
    public void setRusak(String rusak) {
        this.rusak = rusak;
    }

    @Override
    public String toString() {
        return getName();
    }
    @Exclude
    public String getKey() {
        return key;
    }
    @Exclude
    public void setKey(String key) {
        this.key = key;
    }
}
//end
