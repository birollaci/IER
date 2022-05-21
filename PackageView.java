import jason.asSyntax.*;
import jason.environment.Environment;
import jason.environment.grid.GridWorldModel;
import jason.environment.grid.GridWorldView;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.logging.Logger;


class PackageView extends GridWorldView {
	Logger logger = Logger.getLogger(PackageView.class.getName());
	
	public PackageView(PackageModel model) {
        super(model, "Package", 400);
        defaultFont = new Font("Arial", Font.BOLD, 12); // change default font
        setVisible(true);

        repaint();
    }
	
	/** draw application objects */
    @Override
    public void draw(Graphics g, int x, int y, int object) {
        drawItem(g, x, y, ((PackageModel)model).getFromPos(x,y));
    }
	
	public void drawItem(Graphics g, int x, int y, Item f) {
        super.drawObstacle(g, x, y);
        g.setColor(Color.white);
        String label = f.name;
        if(f.isDangerous)
        {
            g.setColor(Color.blue);
        }
		if(f.isMetal)
        {
            g.setColor(Color.gray);
        }
        drawString(g, x, y, defaultFont, label);
    }
}
