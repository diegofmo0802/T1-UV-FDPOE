package uv.fdpoe.t1;

public class PeopleTest {

    public static void main(String[] args) {
        // Crear dos personas
        People alice = new People("1");
        alice.setFirstName("Alice");
        alice.setLastName("Smith");
        alice.setAge(30);
        alice.setGender("Female");

        People bob = new People("2");
        bob.setFirstName("Bob");
        bob.setLastName("Jones");
        bob.setAge(28);
        bob.setGender("Male");

        // Agregar a Bob como amigo de Alice y viceversa
        alice.addFriend(bob.getId());
        bob.addFriend(alice.getId());

        // Verificar si Alice y Bob son amigos
        System.out.println("Are Alice and Bob friends?");
        if (alice.isFriend(bob.getId()) && bob.isFriend(alice.getId())) {
            System.out.println("Yes, they are friends.");
        } else {
            System.out.println("No, they are not friends.");
        }
    }
}
