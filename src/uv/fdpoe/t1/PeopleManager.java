/*
proposito proporciona una interfaz para gestionar los objetos People
author: Diego Fernando Moreno - 202363205
author: Oscar David Cadavid Ramirez - 202363243
fecha: 1-09-2024
*/
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
            for (People p : peoples) {
                if (p.Friends.contains(toDelete.id)) {
                    p.Friends.remove(toDelete.id);
                }
            }
            peoples.remove(toDelete);    
        }
    }
    public String[] getRegistredIds() {
        ArrayList<String> list = new ArrayList();
        for (People people : peoples) list.add(people.id);
        return list.toArray(String[]::new);
    }
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
    public void addFriend(People subject) {
        ArrayList<String> list = new ArrayList();
        Utilities.log(subject.toString());
        for (People people : peoples) {
            Utilities.log(people.toString());
            if (people != subject && !subject.Friends.contains(people.id)) {
                list.add(people.id);
            }
        }
        String[] options = list.toArray(String[]::new);
        if (options.length >= 1) {
            String selected = Utilities.selectWindow("add friend", "select a people", options);
            subject.addFriend(selected);
            People other = getPeople(selected);
            other.addFriend(subject.id);
        } else {
            Utilities.dialog("no more peoples in system");
        }
    }
    public void delFriend(People subject) {
        ArrayList<String> list = new ArrayList();
        for (String id : subject.Friends) {
            list.add(id);
        }
        String[] options = list.toArray(String[]::new);
        if (options.length >= 1) {
            String selected = Utilities.selectWindow("del friend", "select a people", options);
            subject.delFriend(selected);
        } else {
            Utilities.dialog("no more peoples in system");
        }
    }
    public void listAll() {
        Utilities.log("----------|List of registred peoples|----------");
        for (People people : peoples) {
            Utilities.logConcat(" ", "people:", people.toString());
        }
        Utilities.log("-----------------------------------------------");
    }
    public void listByAge(String option, int reference) {
        ArrayList<People> toShow = new ArrayList();
        for (People people : peoples) {
            switch(option) {
                case ">" -> { if (people.age > reference) toShow.add(people); }
                case ">=" -> { if (people.age >= reference) toShow.add(people); }
                case "<" -> { if (people.age < reference) toShow.add(people); }
                case "<=" -> { if (people.age <= reference) toShow.add(people); }
                default -> { Utilities.dialog("not supported option to age list"); }
            }
        }
        Utilities.log("----------|List of  peoples with age " + option + " " + reference + "|----------");
        for (People people : toShow) {
            Utilities.logConcat(" ", "people:", people.toString());
        }
        Utilities.log("-----------------------------------------------");
    }
        public void listByFriendCount(String option, int reference) {
        ArrayList<People> toShow = new ArrayList();
        for (People people : peoples) {
            switch(option) {
                case ">" -> { if (people.Friends.size() > reference) toShow.add(people); }
                case ">=" -> { if (people.Friends.size() >= reference) toShow.add(people); }
                case "<" -> { if (people.Friends.size() < reference) toShow.add(people); }
                case "<=" -> { if (people.Friends.size() <= reference) toShow.add(people); }
                default -> { Utilities.dialog("not supported option to age list"); }
            }
        }
        Utilities.log("----------|List of  peoples with age " + option + " " + reference + "|----------");
        for (People people : toShow) {
            Utilities.logConcat(" ", "people:", people.toString());
        }
        Utilities.log("-----------------------------------------------");
    }
    public float getFriendsMedia() {
        int sum = 0;
        int total = peoples.size();
        for (People people : peoples) {
            sum += people.Friends.size();
        }
        return sum / total;
    }
    public float getAgeMedia() {
        int sum = 0;
        int total = peoples.size();
        for (People people : peoples) {
            sum += people.age;
        }
        return sum / total;
    }
    public int getGenderCount(String gender) {
        int result = 0;
        for (People p : peoples) {
            if (gender.equals(p.gender)) {
                result ++;
            }
        }
        return result;
    }
}
