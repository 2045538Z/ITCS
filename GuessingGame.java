package GuessingGame1;

import java.awt.event.*;
import java.awt.GridBagLayout;

import javax.swing.*;
import java.util.List;
import java.util.Random;

public class GuessingGame extends JFrame implements ActionListener {

		private JButton submitButton,startButton;
		private JTextField countDown,result,guessField;
		private HigherLower hl;
		static int  dataInt;
	//	int input;
	//	static int  dataInt;
	//	Double [] data;
	//	private int COUNT_DOWN = 30;
		// We will make 1 rows of 50 data values each
	//	private int M = 50;
	//	private int M = 1;
		public GuessingGame() {
			super("The Higher/Lower Game");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLayout(new GridBagLayout());
			startButton = makeButton("Start Game");
			startButton.setEnabled(true);

			submitButton = makeButton("Submit");
			submitButton.setEnabled(false);


			// This text field will allow user to type any number 		
			guessField = new JTextField(10);
			guessField.setText("0");
			getContentPane().add(guessField);
			
			// This will display the result, such as higher,lower or equal
			result = new JTextField(10);
			result.setText("Result Dispaly");
			result.setEnabled(false);
			getContentPane().add(result);
			
			// This will display the count down timer
			countDown = new JTextField(10);
			countDown.setText("Count Down Timer");
			countDown.setEnabled(false);
			getContentPane().add(countDown);

			pack();
			setVisible(true);
		}

		private JButton makeButton(String caption) {
			JButton b = new JButton(caption);
			b.setActionCommand(caption);
			b.addActionListener(this);
			getContentPane().add(b);
			return b;
		}

		// Manage the clicks - enabling and unenabling buttons as required
		public void actionPerformed(ActionEvent e) {
		
			if(e.getActionCommand() == "Start Game") {
				// This line creates your primefinder object and executes
				(hl = new HigherLower()).execute();
				startButton.setEnabled(false);
				submitButton.setEnabled(true);
			}
			else if(e.getActionCommand() == "Submit") {				 				
				dispaly();
			 }
		}
		
		public void dispaly(){			
			int input=Integer.parseInt(guessField.getText());
			System.out.println("dataInt "+dataInt);
			
	     if(input==dataInt){
	    	 hl.cancel(true);
	    	 result.setText("Right !!!");    	
	    	 JOptionPane.showMessageDialog(null,   
						input+" is the right answer!Congratulation! Game Over!"); 	    		    	 
	    	 System.exit(1); 
	    	 hl = null;
			System.err.println("Right!!!");
			 }
		else if(input > dataInt){
			guessField.setText("");
			result.setText("Higher!!!");
			System.err.println("Higher !!!");
		}
		else if(input < dataInt){
			guessField.setText("");
			result.setText("Lower !!!");
			System.err.println("Lower!!!");
		}
		else{
			JOptionPane.showMessageDialog(null,   
					"Please input one Interger between 0-50!"); 
		}

	}
		private class HigherLower extends SwingWorker<Void,Integer> {
			public Void doInBackground() {
				try{
					// Start with 2 (0,1 are not primes) and none have been found
					Integer countDownTimer = 30;
					// Loop until submit is pressed (this cancels the worker (see actionlistenet))
					while(!isCancelled()) {
						// Check is the current integer is a prime
						if(checkCountDown(countDownTimer)) {						
								countDownTimer--;								
								Thread.sleep(1000);
								publish(countDownTimer);						
						}
					}
				}
				catch(InterruptedException e) {}
				return null;
			}

			
			public void process(List<Integer> visits) {			
				Integer last = visits.get(visits.size()-1);
				countDown.setText(String.format("%d",last));
			}
		
			public Boolean checkCountDown(int interval) {
			//	static int interval;
				if (interval <=0 ){
					 JOptionPane.showMessageDialog(null,   
								"Time Out! Game Over!"); 
			    	 System.exit(1);
			        System.err.println("Stop!!!!");
					return false;
					}
				else {
						--interval;	
						
			    return true;
				}
			}
	}
			public static void main(String[] args) {
				Random rand = new Random();
				 dataInt = rand.nextInt(50) + 1;
				 
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						new GuessingGame();
					}
				});
											
			}
		}
