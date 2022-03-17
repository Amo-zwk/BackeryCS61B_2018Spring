package disc04;

public class Cat extends Animal {

    public Cat(String name, int age) {
        super(name, age);
    }

    @Override
    public void greet() {
        if(this.age < 5) {
            System.out.println("MEOW");
        }else {
            System.out.println("MEOW");
        }
    }
}
