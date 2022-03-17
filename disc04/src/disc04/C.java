package disc04;

public class C extends B{
    public int y = x + 1;
    public void m2() {System.out.println("Cm2-> " + super.x);}
    public void m4() {System.out.println("Cm4-> " + super.x);}
    //不可以用super.super,因为super本身就会追溯到父类以及父类以上
    public void m5() {System.out.println("Cm5-> " + y);}
}
