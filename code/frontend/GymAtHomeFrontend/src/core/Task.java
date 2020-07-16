/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: joaomarques(Universidade do Minho)
 * License Type: Academic
 */
package core;

import java.util.ArrayList;
import java.util.Collection;

public class Task {

    public String designation;
    public String rest;
    public String duration;
    public String equipment;
    public Collection<Serie> series = new ArrayList<>();

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getRest() {
        return rest;
    }

    public void setRest(String rest) {
        this.rest = rest;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public Collection<Serie> getSeries() {
        return series;
    }

    public void setSeries(Collection<Serie> series) {
        this.series = series;
    }

    @Override
    public String toString() {
        return "Task{" +
                "designation='" + designation + '\'' +
                ", rest='" + rest + '\'' +
                ", duration='" + duration + '\'' +
                ", equipment='" + equipment + '\'' +
                ", series=" + series +
                '}';
    }
}
