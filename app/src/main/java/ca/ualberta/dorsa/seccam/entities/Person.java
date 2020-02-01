package ca.ualberta.dorsa.seccam.entities;

/**
 * The type Person.
 * Executed UI tested yet to be unit tested
 * @author Jessica D'Cunha
 * @date 2020-1-31
 * Project: ECE 492 Group 1
 */
public abstract class Person {
    private String name;
    private String phone;

    /**
     * Instantiates a new Person.
     *
     * @param name  the name
     * @param phone the phone
     */
    public Person(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets phone.
     *
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets phone.
     *
     * @param phone the phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
