package caribbean.datastorage;

public class Island {
    public String name;
    public int x, y, z;
    public Island(String name, int x, int y, int z) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public long distanceTo(Island other) {
        double xpart = Math.pow(this.x - other.x, 2);
        double ypart = Math.pow(this.y - other.y, 2);
        double zpart = Math.pow(this.z - other.z, 2);
        double sum = xpart + ypart + zpart;
        double distance = Math.sqrt(sum);
        return Math.round(distance);
    }
    public String toString() {
        String description = name + " (" + x + ", " + y + ", " + z + ")";
        return description;
    }
}
