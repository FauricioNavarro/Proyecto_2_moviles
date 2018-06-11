package Modelo;

/**
 * Created by fauricio on 08/06/18.
 */

public class Goal {
    private int id;
    private String name;
    private String points;
    private String type;
    private String latitude;
    private String longitude;
    private String challenge_id;

    public Goal(int id, String name, String points, String type) {
        this.id = id;
        this.name = name;
        this.points = points;
        this.type = type;
    }

    public Goal(int id, String name, String points, String type, String latitude, String longitude, String challenge_id) {
        this.id = id;
        this.name = name;
        this.points = points;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
        this.challenge_id = challenge_id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getChallenge_id() {
        return challenge_id;
    }

    public void setChallenge_id(String challenge_id) {
        this.challenge_id = challenge_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
