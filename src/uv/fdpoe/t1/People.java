package uv.fdpoe.t1;

import java.util.ArrayList;

public class People {
    String firstName;
    String lastName;
    int age;
    String gender;
    String id;
    ArrayList<String> Friends;
    public People(String id) {
        this.id = id;
    }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getId() { return id; }
    public void addFriend(String id) {
        Friends.add(id);
    }
    public void delFriend(String id) {
        if (Friends.contains(id)) {
            Friends.remove(id);
        }
    }

    @Override
    public String toString() {
        String result = "{\n";
        result += "  id: " + id + ",\n";
        result += "  firstName: " + firstName + ",\n";
        result += "  lastName: " + lastName + ",\n";
        result += "  age: " + age + ",\n";
        result += "  gender: " + gender + ",\n";
        result += "  Friends: [\n";
        for (String Friend : Friends) {
            result += "    " + Friend + ",\n";
        }
        result += "  ]";
        result += "\n}";
        
        return result;
    }
}
