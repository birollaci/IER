import jason.asSyntax.*;
import jason.environment.Environment;
import jason.environment.grid.GridWorldModel;
import jason.environment.grid.GridWorldView;
import jason.environment.grid.Location;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Logger;

class PackageModel extends GridWorldModel {
	Logger logger = Logger.getLogger(PackageModel.class.getName());
	AirportEnv enviroment;
	
	// foods
    public List<Item> items;
	//parameters
    public static final int rowCount = 3; //  the number of food types as well
    public static final int columnCount = 3;
	int maxItemCount; //computed from the above two
	
	
	public PackageModel(AirportEnv env) {
        super(rowCount, columnCount, 0);
        maxItemCount = rowCount*columnCount;
        enviroment = env;

        // create the initial food objects
        items = new ArrayList<Item>();
		tryToAddItem(AirportEnv.CLOTHES, false, false);

        enviroment.informAgsEnvironmentChanged();
    }
	
	// adds an item if have enough space
    boolean tryToAddItem(int type, Boolean metal, Boolean danger)
    {
        if(items.size()<maxItemCount) {
            var loc = getFreePos();
            int x = loc.x;
            int y = loc.y;
            Item f = new Item(type, enviroment.getNameFromType(type), metal, danger, x, y);
            items.add(f);
            add(type, x, y);
            return true;
        }
        return false;
    }
	
	// Gets a new free location for items
    public Location getFreePos()
    {
        boolean possibble[][] = new boolean[rowCount][columnCount];
        for(Item f : items)
        {
            possibble[f.location.x][f.location.y] = true;
        }
        for(int i =0; i<columnCount; i++)
        {
            for(int j=0; j<rowCount; j++)
            {
                if(possibble[j][i]==false)
                {
                    return new Location(j,i);
                }
            }
        }
        logger.info("No free location could be returned.");
        return null;
    }
	
	//gets an item from its position
    public Item getFromPos(int x, int y)
    {
        Item searched = null;
        for(Item f : items)
        {
            if(f.location.x == x && f.location.y == y)
            {
                searched = f;
                break;
            }
        }
        if(searched!=null)
            return searched;
        else {
            logger.info("No item at position: "+ String.valueOf(x) + " , "+String.valueOf(y));
            return null;
        }
    }
}
