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

public class Serie {
    public Serie() {
    }

    private int ID;

    private String description;

    private String repetitions;

    private String rest;

    private void setID(int value) {
        this.ID = value;
    }

    public int getID() {
        return ID;
    }

    public int getORMID() {
        return getID();
    }

    public void setDescription(String value) {
        this.description = value;
    }

    public String getDescription() {
        return description;
    }

    public void setRepetitions(String value) {
        this.repetitions = value;
    }

    public String getRepetitions() {
        return repetitions;
    }

    public void setRest(String value) {
        this.rest = value;
    }

    public String getRest() {
        return rest;
    }

    @Override
    public String toString() {
        return "Serie{" + "ID=" + ID + ", description=" + description + ", repetitions=" + repetitions + ", rest=" + rest + '}';
    }
}
