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

import java.util.Arrays;

public class Workout {
    public Workout() {
    }

    private java.util.Set this_getSet (int key) {
        if (key == ORMConstants.KEY_WORKOUT_TASKS) {
            return ORM_tasks;
        }
        return null;
    }

    org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
        @Override
        public java.util.Set getSet(int key) {
            return this_getSet(key);
        }
    };

    private int ID;

    private String designation;

    private java.util.Date date;

    private boolean done;

    private java.util.Set ORM_tasks = new java.util.HashSet();

    private void setID(int value) {
        this.ID = value;
    }

    public int getID() {
        return ID;
    }

    public int getORMID() {
        return getID();
    }

    public void setDesignation(String value) {
        this.designation = value;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDate(java.util.Date value) {
        this.date = value;
    }

    public java.util.Date getDate() {
        return date;
    }

    public void setDone(boolean value) {
        this.done = value;
    }

    public boolean getDone() {
        return done;
    }

    private void setORM_Tasks(java.util.Set value) {
        this.ORM_tasks = value;
    }

    private java.util.Set getORM_Tasks() {
        return ORM_tasks;
    }

    public final core.TaskSetCollection tasks = new core.TaskSetCollection(this, _ormAdapter, ORMConstants.KEY_WORKOUT_TASKS, ORMConstants.KEY_MUL_ONE_TO_MANY);

    public boolean isDone() {
        return done;
    }

    @Override
    public String toString() {
        return "Workout{" + "ID=" + ID + ", designation=" + designation + ", date=" + date + ", done=" + done + ", tasks=" + Arrays.toString(tasks.toArray()) + '}';
    }	
}
