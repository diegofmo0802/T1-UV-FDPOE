package uv.fdpoe.t1;

import java.util.ArrayList;

public class PeopleManager {
    public ArrayList<People> peoples = new ArrayList();
    public void addPeople() {
        String id = Utilities.askString("insert cc/id", false);
        if (existId(id)) {
            Utilities.dialog("esta id ya fue insertada"); return;
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
    public void delPeople() {
        String id = Utilities.askString("insert cc/id", false);
        if (!existId(id)) {
            Utilities.dialog("esta id no existe"); return;
        }
        People toDelete = getPeople(id);
        if (Utilities.confirm("deseas eliminar la persona con cc/id: " + id + "?")) {
            peoples.remove(toDelete);    
        }
    }
    public void editPeople() {
        String id = Utilities.askString("insert cc/id", false);
        if (!existId(id)) {
            Utilities.dialog("esta id no existe"); return;
        }
        People people = getPeople(id);
        String[] options = {
            "first name",
            "last name",
            "age",
            "gender"
        };
        String selected = Utilities.selectWindow("edit", "property to edit", options);
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
                int age = askAge();
                people.setAge(age);
            }
            default -> { Utilities.dialog("invalid property"); }
        }
    }
    public String asktGender() {
        String[] genders = {
            "male", "female", "other"
        };
        return Utilities.selectWindow("gender", "select your genser", genders);
    }
    public String askFirstName() {
        return Utilities.askString("write you first name", false);
    }
    public String asktLastName() {
        return Utilities.askString("write you last name", false);   
    }
    public int askAge() {
        return Utilities.askInt("write your age");
    }
    public boolean existId(String id) {
        for (People people : peoples) {
            if (people.getId().equals(id)) return true;
        }
        return false;
    }
    public People getPeople(String id) {
        for (People people : peoples) {
            if (people.getId().equals(id)) return people;
        }
        return null;
    }
}
