package com.xadmin.servlet_p.bean;

public class medicine {
    private int id;
    private String medId;
    private String medName;
    private String medCompName;
    private int medQuantity;
    private int medPrice;

    public medicine(String medId, String medName, String medCompName, int medQuantity, int medPrice) {
        this.medId = medId;
        this.medName = medName;
        this.medCompName = medCompName;
        this.medQuantity = medQuantity;
        this.medPrice = medPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMedId() {
        return medId;
    }

    public void setMedId(String medId) {
        this.medId = medId;
    }

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public String getMedCompName() {
        return medCompName;
    }

    public void setMedCompName(String medCompName) {
        this.medCompName = medCompName;
    }

    public int getMedQuantity() {
        return medQuantity;
    }

    public void setMedQuantity(int medQuantity) {
        this.medQuantity = medQuantity;
    }

    public int getMedPrice() {
        return medPrice;
    }

    public void setMedPrice(int medPrice) {
        this.medPrice = medPrice;
    }
}
