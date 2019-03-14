
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.Arrays;

@ManagedBean(name = "calc")
@SessionScoped
public class Calc  {

    @ManagedProperty(value = "#{output}")
    private Output output = new Output();

    public Output getOutput() {
        return output;
    }

    public void setOutput(Output output) {
        this.output = output;
    }
    private Double xslider;
    private Double x;
    private Double y;
    private Double r;
    private Double res;

    private String validation_msg;
    private ArrayList<Double> x_lst= new ArrayList<Double>();
    private ArrayList<Double> y_lst= new ArrayList<Double>();
    private ArrayList<Double> r_lst= new ArrayList<Double>();
    //0 - не попало, 1 - попало
    private ArrayList<Double> res_lst= new ArrayList<Double>();


    public ArrayList<Double> getX_lst() {
        return x_lst;
    }
    public void setX_lst(Double x) {
        this.x_lst.add(x);
    }

    public ArrayList<Double> getY_lst() {
        return y_lst;
    }
    public void setY_lst(Double y) {
        this.y_lst.add(y);
    }

    public ArrayList<Double> getR_lst() {
        return r_lst;
    }
    public void setR_lst(Double r) {
        this.r_lst.add(r);
    }

    public ArrayList<Double> getRes_lst() {
        return res_lst;
    }
    public void setRes_lst(Double res) {
        this.res_lst.add(res);
    }

    public Double getXslider() {
        return xslider;
    }

    public void setXslider(Double xslider) {
        this.xslider = xslider/2;
        this.x = xslider/2;
    }

    public Double getX() {
        return x;
    }
    public void setX(Double x) {
        if(validate_x(x) != null){
            this.x = x;
        }
        else{
            this.validation_msg = "x not in (-3; 3)";
            this.x = null;
        }
    }

    public Double getY() {
        return y;
    }
    public void setY(Double y) {
        if(validate_y(y) != null){
            this.y = y;
        }
        else{
            this.validation_msg = "y not in (-3; 3)";
            this.y = null;
        }
    }

    public Double getR() {
        return r;
    }
    public void setR(Double r) {
        if(validate_r(r) != null){
            if( r != this.r){
                exeNewR(validate_r(r));
                this.r = validate_r(r);
            }
        }
        else{
            this.validation_msg = "r not in {1, 2, 3, 4, 5}";
            this.r = null;
        }
    }

    public Double getRes() {
        return res;
    }
    public void setRes(Double res) {
        this.res = res;
    }
    public void exeNewR(Double r){
        Double x;
        Double y;
        for(int i = 0; i < this.x_lst.size(); i++){
            x = this.x_lst.get(i);
            y = this.y_lst.get(i);
            if(x <= 0 && y <= 0 && x*x + y*y <= r*r || x <= 0 && y >= 0 && y <= x/2 + r/2 || x >= 0 && y >= 0 && y <= r/2 && x <= r){
                this.res_lst.set(i, 1.0);
            }
            else{
                this.res_lst.set(i, 0.0);
            }
        }
    }
    public void exe(){
        if(this.x != null && this.y != null && this.r != null){
            this.validation_msg = "";
            Double x = this.x;
            Double y = this.y;
            Double r = this.r;
            //попадание в область
            if(x <= 0 && y <= 0 && x*x + y*y <= r*r || x <= 0 && y >= 0 && y <= x/2 + r/2 || x >= 0 && y >= 0 && y <= r/2 && x <= r)
                this.res = 1.0;
            else
                this.res = 0.0;

            //добавляем в списки
            setX_lst(x);
            setY_lst(y);

            setR_lst(r);
            setRes_lst(this.res);

            output.addOut(new Results(x, y, r, this.res));

            SessionFactory sessions = HibernateUtil.getSessionFactory();
            Session session = sessions.openSession();


            session.beginTransaction();
            Data newData = new Data();

            newData.setX(x);
            newData.setY(y);
            newData.setR(r);
            newData.setRes(this.res);

            session.save(newData);
            session.getTransaction().commit();
        }
        else{
            this.x = getX_lst().get(getX_lst().size()-1);
            this.y = getY_lst().get(getY_lst().size()-1);
            this.r = getR_lst().get(getR_lst().size()-1);
        }
    }
    private Double validate_x(Double x){
        if( x < 3 && x > -3){
            return x;
        }
        else{
            return null;
        }
    }
    private Double validate_y(Double y){
        if( y < 3 && y > -3){
            return y;
        }
        else{
            return null;
        }
    }
    private Double validate_r(Double r){
        if( r == 1.0 || r == 2.0 || r == 3.0 || r == 4.0 || r == 5.0){
            return r;
        }
        else{
            return null;
        }
    }
    public String getValidation_msg() {
        return validation_msg;
    }

    public void setValidation_msg(String validation_msg) {
        this.validation_msg = validation_msg;
    }
    public void clear(){
        this.x_lst = new ArrayList<Double>();
        this.y_lst = new ArrayList<Double>();
        this.r_lst = new ArrayList<Double>();
        this.res_lst = new ArrayList<Double>();
        this.output.setOut(new ArrayList<Results>());
    }


}
