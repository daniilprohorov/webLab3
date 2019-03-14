import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;

@ManagedBean(name = "output")
@SessionScoped
public class Output implements Serializable {
    private ArrayList<Results> out = new ArrayList<Results>();

    public ArrayList<Results> getOut(){
        return this.out;
    }

    public void setOut(ArrayList<Results> out) {
        this.out = out;
    }
    public void addOut(Results r){
        this.out.add(r);
    }

}
