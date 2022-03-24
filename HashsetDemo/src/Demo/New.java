package Demo;

import java.util.HashSet;

public class New {
    public static void main(String[] args) {
        HashSet<Integer> objects = new HashSet<>();
        objects.add(1);
        objects.add(15);
        objects.add(4);
        objects.add(2);
        objects.add(4);
        objects.add(3);
        for(int item : objects) System.out.println(item);
    }
}
