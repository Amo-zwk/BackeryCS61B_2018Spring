package disc04;

public class runtime {

    //Animal a = (Dog) new Animal() ; //runtime error
    //为什么会runtime error，因为你告诉编译器new Animal()的动态类型是Dog,但是这样转换完全改变了它的静态类型和动态类型
    //但是实质上的casting是不改变静态类型和动态类型的
    //a的静态类型是Animal
    //a的动态类型是Dog
    //不会编译错误,因为是父类指向子类或者其本身

    /**
     * What class do we check at compile time for a method?
     * Animal: Does it have a makeNoise method that accepts Dogs?
     * Dynamic type is unchanged in any way by casting always, forever.
     */
}
