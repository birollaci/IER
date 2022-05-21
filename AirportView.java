import jason.asSyntax.*;
import jason.environment.Environment;
import jason.environment.grid.GridWorldModel;
import jason.environment.grid.GridWorldView;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.logging.Logger;

class AirportView extends GridWorldView {
	
	public AirportView(AirportModel model) {
        super(model, "Airport", 200);
		setSize(1500, 600);
        defaultFont = new Font("Arial", Font.BOLD, 12); // change default font
        setVisible(true);
        repaint();
    }
	
	@Override
    public void drawAgent(Graphics g, int x, int y, Color c, int id) {
        String label = "airport";
        c = Color.blue;
        //passanger
        if (id == 0) {
            label = "Pass";
			c = Color.red;
        }
        //ticketController
        else if(id ==1){
            label = "tickC";
			c = Color.black;
        }
		//packageController
        else if(id ==2){
            label = "metaD";
			c = Color.gray;
        }
        //packageController
        else if(id ==3){
            label = "packC";
			c = Color.orange;
        }
		else if(id ==4){
            label = "EXIT";
			c = Color.green;
        }
		else if(id ==5){
            label = "POLICE";
			c = Color.blue;
        }
        super.drawAgent(g, x, y, c, -1);
        g.setColor(Color.white);
        super.drawString(g, x, y, defaultFont, label);
    }
}
