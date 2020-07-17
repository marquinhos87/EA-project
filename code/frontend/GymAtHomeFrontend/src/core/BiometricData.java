package core;

import java.util.Date;

public class BiometricData {

    public int height;
    public float weight;
    public int wrist;
    public int chest;
    public int tricep;
    public int waist;
    public int quadricep;
    public int twin;
    public Date date;
    public float BMI;

    public void setHeight(int value) {
        this.height = value;
    }

    public int getHeight() {
        return height;
    }

    public void setWeight(float value) {
        this.weight = value;
    }

    public float getWeight() {
        return weight;
    }

    public void setWrist(int value) {
        this.wrist = value;
    }

    public int getWrist() {
        return wrist;
    }

    public void setChest(int value) {
        this.chest = value;
    }

    public int getChest() {
        return chest;
    }

    public void setTricep(int value) {
        this.tricep = value;
    }

    public int getTricep() {
        return tricep;
    }

    public void setWaist(int value) {
        this.waist = value;
    }

    public int getWaist() {
        return waist;
    }

    public void setQuadricep(int value) {
        this.quadricep = value;
    }

    public int getQuadricep() {
        return quadricep;
    }

    public void setTwin(int value) {
        this.twin = value;
    }

    public int getTwin() {
        return twin;
    }

    public void setDate(Date value) {
        this.date = value;
    }

    public Date getDate() {
        return date;
    }

    public void setBMI(float value) {
        this.BMI = value;
    }

    public float getBMI() {
        return BMI;
    }

    @Override
    public String toString() {
        return "BiometricData{" +
                ", height=" + height +
                ", weight=" + weight +
                ", wrist=" + wrist +
                ", chest=" + chest +
                ", tricep=" + tricep +
                ", waist=" + waist +
                ", quadricep=" + quadricep +
                ", twin=" + twin +
                ", date=" + date +
                ", BMI=" + BMI +
                '}';
    }
}
