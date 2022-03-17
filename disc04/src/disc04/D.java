package disc04;

public class D {
    public static void main(String[] args) {
        B a0 = (B) new A(); //error
        //ao的编译类型是B,运行类型是A,而B是A的子类,多态的话是不可以子类指向父类的
        //也就是说a0的编译类型是B,而右边是执行类型,你要告诉编译器,new A()的执行类型是B,casting不影响
        //casting并没有任何后续的影响，也没有改变任何类型，它只是用于告诉编译器，new A()的动态类型会是B
        //除非你要告诉编译器,a0的运行类型是B
        a0.m1();
        a0.m2(16);
        A b0 = new B();
        //b0的编译类型是A
        System.out.println(b0.x);
        b0.m1();
        b0.m2();
        //b0.m2(61); //error
        //编译器编译的时候,会先看看编译类型是否有m2这个方法
        //会发现,实际上这个b0并没有这个方法,因为它的编译类型是A
        B b1 = new B();
        b1.m2(61);
        b1.m3();
        A c0 = new C();
        //你父类直接指向子类是可以的,但是子类不可以直接指向父类
        //要强转告诉编译器你的执行类型
        c0.m2();
        //C c1 = (A) new C(); //error
        //告诉编译器你new C()的编译类型是A,但是C的是A的子类,子类是不可以指向父类的
        A a1 = (A) c0;
        C c2 = (C) a1;
        c2.m3();
        c2.m4();
        c2.m5();
        ((C) c0).m3();
        //(C) c0.m3(); //error
        //上面已经说到了A c0 = new C();
        //但是c0的编译类型是A,A里面并没有c3,所以你要告诉编译器它
        //c0的执行类型是C
        b0.update();
        b0.m1();
        //B a0 = new A(); //error
        //同理,子类指向父类要强转
    }
}

