package This;
import java.io.Serializable;


public class Coordinates implements Serializable {
    private Integer x; //Поле не может быть null
    private Integer y;//Поле не может быть null

    public void setX(Integer x) {
        this.x = x;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getX() {
        return this.x;
    }

    public Integer getY() {
        return this.y;
    }

    @Override
    public String toString() {
        return "{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
