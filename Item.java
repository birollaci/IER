import jason.environment.grid.Location;

public class Item {
    public int ID;
    public String name;
	public boolean isMetal = false;
	public boolean isDangerous = false;
    public boolean isDetected = false;
    public Location location;
    public Item(int id, String n, Boolean metal, Boolean danger, int x, int y)
    {
        ID = id;
        name = n;
		isMetal = metal;
		isDangerous = danger;
        location = new Location(x,y);
    }
}
