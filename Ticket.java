import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Logger;

public class Ticket {
	static Logger logger = Logger.getLogger(Ticket.class.getName());
	
    private AirportEnv enviroment;
	
	Ticket(AirportEnv env) {
		enviroment = env;

        JFrame frame=new JFrame();	
		JLabel idLabel=new JLabel("Personal Number:");
		idLabel.setBounds(10,10,150,40);
		frame.add(idLabel);
		JLabel nameLabel=new JLabel("Name:");
		nameLabel.setBounds(10,50,150,40);
		frame.add(nameLabel);
		JLabel destLabel=new JLabel("Destination:");
		destLabel.setBounds(10,100,150,40);
		frame.add(destLabel);
		
		JTextField idField = new JTextField();
		idField.setText("123456");
		idField.setBounds(200,20,150,20);
		frame.add(idField);
		JTextField nameField = new JTextField();
		nameField.setText("Test Elek");
		nameField.setBounds(200,60,150,20);
		frame.add(nameField);
		JTextField destField = new JTextField();
		destField.setText("New York");
		destField.setBounds(200,110,150,20);
		frame.add(destField);
		
		JButton buyButton=new JButton("BUY");
		buyButton.setBounds(150,200,150,80);
        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				enviroment.ticketOk();
				logger.info("Ticket button pushed!");
            }
        });
		frame.add(buyButton);
		
		// Setting Frame size. This is the window size
        frame.setSize(400,400);

        frame.setLayout(null);
        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
