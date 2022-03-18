/** If you project is set up properly, this file should execute. 
* One thing you might consider is to try printing out the sequence of
* operations */
public class StudentArrayDequeLauncher {
    public static void main(String[] args) {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();

        //这个for函数是用来当作获取随机数的案例演示
        //先看看这个for的功能是什么
        //循环10次
        //random.uniform(x, y) 方法将随机生成一个实数，它在 [x,y] 范围内。
        for (int i = 0; i < 10; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.5) {
                sad1.addLast(i);
            } else {
                sad1.addFirst(i);
            }
        }

        //输出
        sad1.printDeque();
    }
} 
