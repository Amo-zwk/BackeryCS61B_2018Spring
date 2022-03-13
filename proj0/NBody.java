public class NBody {
    //读第二个
    public static double readRadius(String name) {
        In in = new In(name);
        int firstItemInFile = in.readInt();
        double secondItemInFile = in.readDouble();
        return secondItemInFile;
    }

    //读行星
    public static Planet[] readPlanets(String name) {
        In in = new In(name);
        int num = in.readInt();
        Planet[] planets = new Planet[num];
        double secondItemInFile = in.readDouble();
        //读firstItemInFile次
        for (int i = 0; i < num; i++) {
            //每一次读6个数据
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String picture = in.readString();
            planets[i] = new Planet(xP, yP, xV, yV, m, picture);
        }
        return planets;
    }

    //main方法
    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double r = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        //
        StdDraw.setXscale(-r, r);
        StdDraw.setYscale(-r, r);
        StdDraw.enableDoubleBuffering();

        double t = 0;
        int num = planets.length;
        while (t <= T) { //这是题目给的
            double[] xForces = new double[num];
            double[] yForces = new double[num];
            //每个行星的合力存起来
            for (int i = 0; i < num; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            //更新每个行星
            for (int i = 0; i < num; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            //画背景图
            StdDraw.picture(0, 0, "images/starfield.jpg");

            //画所有行星
            for (Planet planet : planets) {
                planet.draw();
            }
            //先show然后暂停10然后t+=dt
            StdDraw.show();
            StdDraw.pause(10);
            t += dt;
        }

        //结束的时候打印
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", r);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
