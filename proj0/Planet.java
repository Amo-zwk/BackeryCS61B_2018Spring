public class Planet {
    public double xxPos ; //x位置
    public double yyPos ; //y位置
    public double xxVel ; //x方向上的速度
    public double yyVel ; //y方向上的速度
    public double mass ; //质量m
    public String imgFileName ; //与描述行星的图像相对应的文件名称

    //常数G
    public static final double G = 6.67e-11;

    //构造器第一种
    public Planet(double xxPos, double yyPos, double xxVel, double yyVel, double mass, String imgFileName) {
        this.xxPos = xxPos;
        this.yyPos = yyPos;
        this.xxVel = xxVel;
        this.yyVel = yyVel;
        this.mass = mass;
        this.imgFileName = imgFileName;
    }
    //构造器第二种
    public Planet(Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }
    //计算距离
    public double calcDistance(Planet p){
        double dx = p.xxPos - this.xxPos ;
        double dy = p.yyPos - this.yyPos ;
        return Math.sqrt(dx * dx + dy * dy);
    }
    //计算万有引力
    public double calcForceExertedBy(Planet p){
        return (G * p.mass * this.mass) /
                (this.calcDistance(p) * this.calcDistance(p));
    }
    //计算万有引力的分力Fx
    public double calcForceExertedByX(Planet p){
        return (this.calcForceExertedBy(p)) * ((p.xxPos - this.xxPos) / (this.calcDistance(p))) ;
    }
    //计算万有引力的分力Fy
    public double calcForceExertedByY(Planet p){
        return (this.calcForceExertedBy(p)) * ((p.yyPos - this.yyPos) / (this.calcDistance(p))) ;
    }
    //计算net的Fx
    public double calcNetForceExertedByX(Planet[] p){
        double res = 0 ;
        for(Planet number : p){
            if(number != this){
                res += this.calcForceExertedByX(number) ;
            }
        }
        return res ;
    }
    //计算net的Fy
    public double calcNetForceExertedByY(Planet[] p){
        double res = 0 ;
        for(Planet number : p){
            if(number != this){
                res += this.calcForceExertedByY(number) ;
            }
        }
        return res ;
    }
    //计算update的方法
    public void update(double z , double x , double y){
        double xa = x / this.mass ; //计算x加速度
        double ya = y / this.mass ; //计算y加速度
        this.xxVel = this.xxVel + xa * z ; // vx = v0 + ax * t ;
        this.yyVel = this.yyVel + ya * z ; // vy = v0 + ay * t ;
        this.xxPos = this.xxPos + this.xxVel * z ; //新位置x
        this.yyPos = this.yyPos + this.yyVel * z ; //新位置y
    }
    //draw
    public void draw(){
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}
