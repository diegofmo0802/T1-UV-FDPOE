/*
Purpose: Provides an interface to manage `People` objects.
Authors:
    - Diego Fernando Moreno - 202363205
    - Oscar David Cadavid Ramirez - 202363243
Date: 1-09-2024
*/
package uv.fdpoe.t1;

import java.util.ArrayList;

public class PeopleManager {
    /**
     * List of `People` objects representing the database of people.
     */
    public ArrayList<People> peoples = new ArrayList<>();

    /**
     * Adds a new person to the list.
     * Prompts the user for the person's data and checks if the ID already exists.
     */
    public void addPeople() {
        String id = Utilities.askString("insert cc/id", false);
        if (existId(id)) {
            Utilities.dialog("this ID has already been inserted");
            return;
        }
        String firstName = askFirstName();
        String lastName = asktLastName();
        int age = askAge();
        String gender = asktGender();
        People newPeople = new People(id);
        newPeople.setFirstName(firstName);
        newPeople.setLastName(lastName);
        newPeople.setAge(age);
        newPeople.setGender(gender);
        peoples.add(newPeople);
    }

    /**
     * Removes a person from the list.
     * Prompts the user for the ID of the person to remove and checks if it exists.
     * If the person exists and removal is confirmed, the person is removed from the list
     * and the friends lists are updated.
     */
    public void delPeople() {
        String id = Utilities.askString("insert cc/id", false);
        if (!existId(id)) {
            Utilities.dialog("this ID does not exist");
            return;
        }
        People toDelete = getPeople(id);
        if (Utilities.confirm("do you want to delete the person with cc/id: " + id + "?")) {
            for (People p : peoples) {
                if (p.Friends.contains(toDelete.id)) {
                    p.Friends.remove(toDelete.id);
                }
            }
            peoples.remove(toDelete);
        }
    }

    /**
     * Returns a list of IDs of registered people.
     * @return Array of IDs of registered people.
     */
    public String[] getRegistredIds() {
        ArrayList<String> list = new ArrayList<>();
        for (People people : peoples) {
            list.add(people.id);
        }
        return list.toArray(String[]::new);
    }

    /**
     * Allows editing the details of a person.
     * Prompts the user for the ID of the person to edit and displays a menu with editable properties.
     * Updates the selected property according to the option chosen by the user.
     */
    public void editPeople() {
        String[] registrdIds = getRegistredIds();
        String id = Utilities.selectWindow("edit menu", "select people to edit", registrdIds);

        People people = getPeople(id);
        String[] options = {
            "first name",
            "last name",
            "age",
            "gender",
            "add friend",
            "del friend"
        };
        String selected = Utilities.selectWindow("edit menu", "property to edit", options);
        switch (selected) {
            case "first name" -> {
                String firstName = askFirstName();
                people.setFirstName(firstName);
            }
            case "last name" -> {
                String lastName = asktLastName();
                people.setLastName(lastName);
            }
            case "age" -> {
                int age = askAge();
                people.setAge(age);
            }
            case "gender" -> {
                String gender = asktGender();
                people.setGender(gender);
            }
            case "add friend" -> { addFriend(people); }
            case "del friend" -> { delFriend(people); }
            default -> { Utilities.dialog("invalid property"); }
        }
        Utilities.logConcat(" ", people.id, "edited:", people.toString());
    }

    /**
     * Prompts the user to select a gender from a list.
     * @return Selected gender.
     */
    public String asktGender() {
        String[] genders = {
            "male", "female", "other"
        };
        return Utilities.selectWindow("gender", "select your gender", genders);
    }

    /**
     * Prompts the user to enter the first name.
     * @return First name entered by the user.
     */
    public String askFirstName() {
        return Utilities.askString("write your first name", false);
    }

    /**
     * Prompts the user to enter the last name.
     * @return Last name entered by the user.
     */
    public String asktLastName() {
        return Utilities.askString("write your last name", false);
    }

    /**
     * Prompts the user to enter the age.
     * @return Age entered by the user.
     */
    public int askAge() {
        return Utilities.askInt("write your age");
    }

    /**
     * Checks if a person with the given ID exists.
     * @param id ID of the person to check.
     * @return `true` if the ID exists, `false` otherwise.
     */
    public boolean existId(String id) {
        for (People people : peoples) {
            if (people.getId().equals(id)) return true;
        }
        return false;
    }

    /**
     * Searches for a person in the list by their ID.
     * @param id ID of the person to search for.
     * @return `People` object corresponding to the given ID, or `null` if not found.
     */
    public People getPeople(String id) {
        for (People people : peoples) {
            if (people.getId().equals(id)) return people;
        }
        return null;
    }

    /**
     * Adds a friend to a person's friend list.
     * Prompts the user to select a friend from the list of available people.
     * @param subject Person to which the friend will be added.
     */
    public void addFriend(People subject) {
        ArrayList<String> list = new ArrayList<>();
        Utilities.log(subject.toString());
        for (People people : peoples) {
            Utilities.log(people.toString());
            if (people != subject && !subject.Friends.contains(people.id)) {
                list.add(people.id);
            }
        }
        String[] options = list.toArray(String[]::new);
        if (options.length >= 1) {
            String selected = Utilities.selectWindow("add friend", "select a person", options);
            subject.addFriend(selected);
            People other = getPeople(selected);
            other.addFriend(subject.id);
        } else {
            Utilities.dialog("no more people in the system");
        }
    }

    /**
     * Removes a friend from a person's friend list.
     * Prompts the user to select a friend from the list of the person's friends.
     * @param subject Person from whom the friend will be removed.
     */
    public void delFriend(People subject) {
        ArrayList<String> list = new ArrayList<>();
        for (String id : subject.Friends) {
            list.add(id);
        }
        String[] options = list.toArray(String[]::new);
        if (options.length >= 1) {
            String selected = Utilities.selectWindow("del friend", "select a person", options);
            subject.delFriend(selected);
        } else {
            Utilities.dialog("no more friends in the system");
        }
    }

    /**
     * Displays a list of all registered people.
     */
    public void listAll() {
        Utilities.log("----------|List of registered people|----------");
        for (People people : peoples) {
            Utilities.logConcat(" ", "people:", people.toString());
        }
        Utilities.log("-----------------------------------------------");
    }

    /**
     * Displays a list of people filtered by age according to a given option.
     * @param option Filter option for age ("<", "<=", ">", ">=").
     * @param reference Reference age for the filter.
     */
    public void listByAge(String option, int reference) {
        ArrayList<People> toShow = new ArrayList<>();
        for (People people : peoples) {
            switch(option) {
                case ">" -> { if (people.age > reference) toShow.add(people); }
                case ">=" -> { if (people.age >= reference) toShow.add(people); }
                case "<" -> { if (people.age < reference) toShow.add(people); }
                case "<=" -> { if (people.age <= reference) toShow.add(people); }
                default -> { Utilities.dialog("not supported option for age list"); }
            }
        }
        Utilities.log("----------|List of people with age " + option + " " + reference + "|----------");
        for (People people : toShow) {
            Utilities.logConcat(" ", "people:", people.toString());
        }
        Utilities.log("-----------------------------------------------");
    }

    /**
     * Displays a list of people filtered by number of friends according to a given option.
     * @param option Filter option for number of friends ("<", "<=", ">", ">=").
     * @param reference Reference number of friends for the filter.
     */
    public void listByFriendCount(String option, int reference) {
        ArrayList<People> toShow = new ArrayList<>();
        for (People people : peoples) {
            switch(option) {
                case ">" -> { if (people.Friends.size() > reference) toShow.add(people); }
                case ">=" -> { if (people.Friends.size() >= reference) toShow.add(people); }
                case "<" -> { if (people.Friends.size() < reference) toShow.add(people); }
                case "<=" -> { if (people.Friends.size() <= reference) toShow.add(people); }
                default -> { Utilities.dialog("not supported option for friend count list"); }
            }
        }
        Utilities.log("----------|List of people with friends " + option + " " + reference + "|----------");
        for (People people : toShow) {
            Utilities.logConcat(" ", "people:", people.toString());
        }
        Utilities.log("-----------------------------------------------");
    }

    /**
     * Calculates and displays the average number of friends for all people in the list.
     */
    public void avgFriendCount() {
        if (peoples.isEmpty()) {
            Utilities.dialog("no people to calculate");
            return;
        }
        int totalFriends = 0;
        for (People people : peoples) {
            totalFriends += people.Friends.size();
        }
        float average = (float) totalFriends / peoples.size();
        Utilities.log("average number of friends in the system: " + average);
    }

    /**
     * Calculates and displays the average age of all people in the list.
     */
    public void avgAge() {
        if (peoples.size() == 0) {
            Utilities.dialog("no people to calculate");
            return;
        }
        int totalAge = 0;
        for (People people : peoples) {
            totalAge += people.age;
        }
        float average = (float) totalAge / peoples.size();
        Utilities.log("average age in the system: " + average);
    }

    /**
     * Displays the total number of people in the list.
     */
    public void countPeople() {
        Utilities.log("total number of people: " + peoples.size());
    }
}