/*
Purpose: Represents data for a person entity.
Authors:
    - Diego Fernando Moreno - 202363205
    - Oscar David Cadavid Ramirez - 202363243
    - Veruzka Guapacha Quiroz - 202378085
Date: 1-09-2024
*/
package uv.fdpoe.t1;

import java.util.ArrayList;

/**
 * The `People` class represents a person with attributes such as first name, last name,
 * age, gender, ID, and a list of friends.
 */
public class People {
    public String firstName;
    public String lastName;
    public int age;
    public String gender;
    public String id;
    public ArrayList<String> Friends;

    /**
     * Constructor to initialize a `People` object with an ID.
     * @param id The ID of the person.
     */
    public People(String id) {
        this.id = id;
        Friends = new ArrayList<>();
    }

    /**
     * Gets the first name of the person.
     * @return The first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the person.
     * @param firstName The first name to set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the person.
     * @return The last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the person.
     * @param lastName The last name to set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the age of the person.
     * @return The age.
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the person.
     * @param age The age to set.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Gets the gender of the person.
     * @return The gender.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the gender of the person.
     * @param gender The gender to set.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Gets the ID of the person.
     * @return The ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Adds a friend to the person's list of friends.
     * @param id The ID of the friend to add.
     */
    public void addFriend(String id) {
        Friends.add(id);
    }

    /**
     * Removes a friend from the person's list of friends.
     * @param id The ID of the friend to remove.
     */
    public void delFriend(String id) {
        Friends.remove(id);
    }

    /**
     * Checks if a given person (by ID) is a friend.
     * @param id The ID of the person to check.
     * @return True if the person is a friend, false otherwise.
     */
    public boolean isFriend(String id) {
        return Friends.contains(id);
    }

    /**
     * Returns a string representation of the `People` object.
     * @return A string representation of the person's data.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("{\n");
        result.append("  id: ").append(id).append(",\n");
        result.append("  firstName: ").append(firstName).append(",\n");
        result.append("  lastName: ").append(lastName).append(",\n");
        result.append("  age: ").append(age).append(",\n");
        result.append("  gender: ").append(gender).append(",\n");
        result.append("  Friends: [\n");
        for (String Friend : Friends) {
            result.append("    ").append(Friend).append(",\n");
        }
        result.append("  ]\n");
        result.append("}");
        return result.toString();
    }
}
