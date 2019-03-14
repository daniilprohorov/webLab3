public class Results {
    private Double x;
    private Double y;
    private Double r;
    private boolean res;

    public Results(Double x, Double y, Double r, Double res){
        this.x = x;
        this.y = y;
        this.r = r;
        if(res == 1.0){
            this.res = true;
        }
        else{
            this.res = false;
        }
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getR() {
        return r;
    }

    public void setR(Double r) {
        this.r = r;
    }

    public boolean isRes() {
        return res;
    }

    public void setRes(boolean res) {
        this.res = res;
    }
}
