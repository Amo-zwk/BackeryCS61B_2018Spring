package disc04;

/**
 * 输出结果
 * Animal a says: Huh
 * MEOW
 * Dog d says: Woof!
 * MEOW
 * MEOW
 */

public class TestAnimals {

    public static void main(String[] args) {
        Animal a = new Animal("Pluto",10);
        Cat c = new Cat("Garfield",6);
        Dog d = new Dog("Fido", 4);

        a.greet();
        //a的编译类型是Animal,执行类型是Animal
        //所以a.greet其实是运行类型是Animal类型,然后调用方法的时候,先看子类再看父类
        //a的greet也就是会输出Animal a says: Huh
        c.greet() ;
        //c的编译类型是Car,执行类型是Cat
        //所以c.greet输出的是MEOW
        d.greet();
        //d的编译类型是Dog,执行类型是Dog
        //所以d.greet输出的是Dog d says: Woof!
        a = c ;
        //a对象指向c对象
        //也就是说Animal a = new Cat("Garfield",6)
        //告诉编译器我的运行类型是Cat,编译类型还是Animal,调用方法的时候是调用Cat的方法
        //也就是new Cat("Garfield",6)的对象调用.greet
        ((Cat) a).greet();
        //所以输出的还是MEOW
        //a.greet();
        /**
         * 如果36行改成以下40行的代码
         */
        a = new Dog("Spot", 10);
        //a的编译类型是Animal,但是它的运行类型是Dog
        d = (Dog) a;
        //这样的话，是因为编译器可能把d的编译类型看成是Dog,然后a的运行类型是Animal
        //所以子类直接指向Animal是错的
        //你要告诉编译器你a的运行类型是Dog
        //casting并不影响实际的运行类型和编译类型
        //d的编译类型是Dog,运行类型也是Dog
        //所以输出的还是MEOW
    }
}

