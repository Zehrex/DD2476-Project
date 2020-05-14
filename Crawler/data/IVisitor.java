38
https://raw.githubusercontent.com/piyush6348/Design-Patterns/master/Visitor%20Pattern/src/IVisitor.java
public interface IVisitor {
    double accept(CricketBall cricketBall);
    double accept(CricketBat cricketBat);
    double accept(VolleyBall volleyBall);
}
