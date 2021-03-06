import jason.asSyntax.*;
import jason.environment.Environment;
import jason.environment.grid.GridWorldModel;
import jason.environment.grid.GridWorldView;
import jason.environment.grid.Location;

import java.util.Random;
import java.util.logging.Logger;


class AirportModel extends GridWorldModel {
	AirportEnv enviroment;
	
	//agent locations
    Location passanger = new Location(0, 1);
    Location ticketController = new Location(2, 1);
	Location metalDetector = new Location(6, 1);
    Location packageController = new Location(7, 1);
	Location exitDoor = new Location(14, 1);
	Location police = new Location(7, 3);
	
	public AirportModel(AirportEnv env) {
		// x, y, agensek szama
		super(15, 6, 6);

        enviroment = env;
		
		// initial location of agents
        try {
            setAgPos(0, passanger);
            setAgPos(1, ticketController);
			setAgPos(2, metalDetector);
            setAgPos(3, packageController);
			setAgPos(4, exitDoor);
			setAgPos(5, police);
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}
	
	// passanger moves towards a certain point
    void moveTowards(int x, int y) throws Exception {
        Location loc = getAgPos(0);
		if (loc.x < x)
			loc.x++;
		else if (loc.x > x)
			loc.x--;
		if (loc.y < y)
			loc.y++;
		else if (loc.y > y)
			loc.y--;
		
		setAgPos(0, loc);
		setAgPos(1, getAgPos(1));
		setAgPos(2, getAgPos(2));
		setAgPos(3, getAgPos(3));
		setAgPos(4, getAgPos(4));
		setAgPos(5, getAgPos(5));
		enviroment.informAgsEnvironmentChanged();
    }
}
