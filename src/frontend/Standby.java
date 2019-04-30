package frontend;
public class Standby {
    String type;
    int id;

    public Standby (){
        type="Ambulance";
        id= 1;
    }
    public Standby(String type, int id){
        this.type = type;
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getType(){
        return type;
    }
    public void setId(int id){
        this.id=id;
    }
    public int getId (){
        return id;
    }
}

