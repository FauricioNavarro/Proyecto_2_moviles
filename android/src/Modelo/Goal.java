package Modelo;

/**
 * Created by fauricio on 08/06/18.
 */

public class Goal {
    private String name;
    private String points;
    private String type;

    public Goal(String name, String points, String type) {
        this.name = name;
        this.points = points;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
