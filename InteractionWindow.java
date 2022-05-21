import jason.environment.grid.Location;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Logger;

// Window for executing commands
public class InteractionWindow {
	static Logger logger = Logger.getLogger(InteractionWindow.class.getName());
	
    private AirportEnv enviroment;

    InteractionWindow(AirportEnv env){
        enviroment = env;

        JFrame frame=new JFrame();
        // start buttons
        JButton startButton=new JButton("Start");
        startButton.setBounds(0,0,150, 80);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				enviroment.start();
				logger.info("start button pushed!");
            }
        });
        frame.add(startButton);

        // Setting Frame size. This is the window size
        frame.setSize(340,310);

        frame.setLayout(null);
        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}