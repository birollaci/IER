import jason.asSyntax.*;
import jason.environment.Environment;
import jason.environment.grid.GridWorldModel;
import jason.environment.grid.GridWorldView;
import jason.environment.grid.Location;

import java.util.Timer;
import java.util.TimerTask;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;
import java.util.logging.Logger;

public class AirportEnv extends Environment {
	static Logger logger = Logger.getLogger(AirportEnv.class.getName());
	
	// food types
    public static final int KNIFE = 16;
    public static final int CLOTHES = 32;
    public static final int GUN = 64;
	
	public static final int refreshRate = 500; //  every n ms the perceptions are updated
	
	private AirportModel airportModel;
	private AirportView airportView;
	private PackageModel packageModel;
	private PackageView packageView;
	private InteractionWindow interactionWindow;
	private Ticket ticket;
	Literal lStart = null;
	Literal lTicketOk = null;
	
	//Location newLoc = airportModel.getAgPos(0);
	
	@Override
	public void init(String[] args){
		//airport
        airportModel = new AirportModel(this);
        airportView = new AirportView(airportModel);
        airportModel.setView(airportView);
		//package
        packageModel = new PackageModel(this);
        packageView = new PackageView(packageModel);
        packageModel.setView(packageView);
		// interaction window
        interactionWindow = new InteractionWindow(this);
		logger.info("Init OK");
		//start the update timer
        startUpdateTimer(refreshRate);
	}
	
	@Override
    public boolean executeAction(String ag, Structure action) {
        logger.info(ag + " doing: " + action);
		try {
            Thread.sleep(refreshRate);
        } catch (Exception e) {
        }
		try {
			if (action.getFunctor().equals("move_towards")) {
                int x = (int) ((NumberTerm) action.getTerm(0)).solve();
                int y = (int) ((NumberTerm) action.getTerm(1)).solve();
                airportModel.moveTowards(x, y);
			}
			if (action.getFunctor().equals("view_ticket")) {
				ticket = new Ticket(this);
			}
		} catch (Exception e) {
            e.printStackTrace();
        }
		updatePercepts(); // update agents perception for the new world state
		
		informAgsEnvironmentChanged();
		return true;
	}
	
	public Literal position(String pos, int x, int y) {
		Literal lPos = ASSyntax.createLiteral("pos", ASSyntax.createNumber(x), ASSyntax.createNumber(y));
		return lPos;
	}
	public void start() {
		lStart = ASSyntax.createLiteral("start");
	}
	
	public void ticketOk() {
		lTicketOk = ASSyntax.createLiteral("ticket_ok");
	}
	
	void updatePercepts() {
		// remove previous perception
        clearPercepts();   
		Location newLoc = airportModel.getAgPos(0);
		if (newLoc.x == 0 && newLoc.y == 1 && lStart != null) {
			addPercept(lStart);
		}
		if (newLoc.x == 2 && newLoc.y == 1 && lTicketOk != null) {
			addPercept(lTicketOk);
		}
		for(int i = 0; i < 15; i++) {
			for(int j = 0; j < 3; j++){
				if (newLoc.x == i && newLoc.y == j) {
					addPercept(position("pos", i, j));
				}
			}
		}
		
		logger.info("Update percepts");
	}
	
	// Gets the item name from its type
    String getNameFromType(int type) {
        switch (type) {
            case KNIFE:
                return "knife";
            case CLOTHES:
                return "clothes";
            case GUN:
                return "gun";
            default:
                logger.info("Wrong item type: " + String.valueOf(type));
                return null;
        }
    }
	
	/*
     * Timer for updating the percepts every given millisecond
     * */
    private void startUpdateTimer(int period) {
        TimerTask update = new TimerTask() {
            @Override
            public void run() {
                updatePercepts();
            }
        };
        var updateTimer = new Timer();
        updateTimer.schedule(update, 0, period);
    }
}
