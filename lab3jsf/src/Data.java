import java.io.Serializable;

public class Data implements Serializable {
    private double x;
    private double y;
    private double r;
    private double res;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Data(){
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public double getRes() {
        return res;
    }

    public void setRes(double res) {
        this.res = res;
    }
}
