import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class BoardPanel {

  private JFrame frame;
  private JRadioButton optionS;
  private JRadioButton optionO;
  private JRadioButton optionSimple;
  private JRadioButton optionGeneral;
  private int turn = 1;
  private int turncount = 1;
  private int cell = 0;
  private int dimension = 0;
  private int p1points = 0;
  private int p2points = 0;
  private int winner;
  private Boolean gameOver = false;
  private Boolean checkSOS = false;
  String[] stringarray = new String[dimension * dimension];
  


  private static Robot robot = null;
  
  public void givenWritingStringToFile_whenUsingPrintWriter_thenCorrect() 
		  throws IOException {
		   
		}


  private void createAndDisplayGui() {
    frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(createTopPanel(), BorderLayout.PAGE_START);
    frame.pack();
    frame.setLocationByPlatform(true);
    frame.setVisible(true);
  }

  private void createBoard(ActionEvent event) {
    Object source = event.getSource();
    if (source instanceof JTextField) {
      JTextField textField = (JTextField) source;
      JCheckBox enablecomputer = new JCheckBox("Enable computer");
      JCheckBox enablecomputer2 = new JCheckBox("Enable computer 2");
      JLabel turnlabel = new JLabel("Player 1's Turn");
      turnlabel.setHorizontalAlignment(JLabel.CENTER);
      String text = textField.getText();
      dimension = Integer.parseInt(text);
      stringarray = new String[dimension * dimension];
      JPanel board = new JPanel(new GridLayout(dimension, dimension));
      for (int row = 0; row < dimension; row++) {
        for (int col = 0; col < dimension; col++) {
          JLabel square = new JLabel("   ");
          square.setHorizontalAlignment(JLabel.CENTER);
          square.setVerticalAlignment(JLabel.CENTER);
          square.setBackground(Color.white);
          square.setOpaque(true);
          square.setBorder(BorderFactory.createLineBorder(Color.black));
          square.setFont(new Font("Verdana", Font.BOLD, dimension * 10));
          board.add(square);
          square.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	PointerInfo a = MouseInfo.getPointerInfo();
            	Point b = a.getLocation();
            	int x = (int) b.getX();
            	int y = (int) b.getY();
            	
            	cell = (dimension*(x-123))/(1785-123)+(dimension*(y-58))/(1019-58)*dimension+1;
            	
              String current = square.getText();
              
              if (current == "   ") {
            	  if (optionS.isSelected())
            	  {
            		  square.setText(" S ");
            		  current = " S ";
            		  stringarray[cell-1] = current;
            		  checkSOS = checkSOS(cell, dimension - 1);
            		  if(checkSOS == true) { 
            			  if(turn == 1) p1points++;
            			  else p2points++;
            			  }
            		  checkSOS = checkSOS(cell, dimension);
            		  if(checkSOS == true) { 
            			  if(turn == 1) p1points++;
            			  else p2points++;
            			  }
            		  checkSOS = checkSOS(cell, dimension + 1);
            		  if(checkSOS == true) { 
            			  if(turn == 1) p1points++;
            			  else p2points++;
            			  }
            		  checkSOS = checkSOS(cell, 1);
            		  if(checkSOS == true) { 
            			  if(turn == 1) p1points++;
            			  else p2points++;
            			  }
            		  checkSOS = checkSOS(cell, -dimension - 1);
            		  if(checkSOS == true) { 
            			  if(turn == 1) p1points++;
            			  else p2points++;
            			  }
            		  checkSOS = checkSOS(cell, -dimension);
            		  if(checkSOS == true) { 
            			  if(turn == 1) p1points++;
            			  else p2points++;
            			  }
            		  checkSOS = checkSOS(cell, -dimension + 1);
            		  if(checkSOS == true) { 
            			  if(turn == 1) p1points++;
            			  else p2points++;
            			  }
            		  checkSOS = checkSOS(cell, - 1);
            		  if(checkSOS == true) { 
            			  if(turn == 1) p1points++;
            			  else p2points++;
            			  }
            		  System.out.println("Player " + turn + " placed an" + current + " at cell " + cell);
            	  }
            	  if (optionO.isSelected())
            	  {
            		  square.setText(" O ");
            		  current = " O ";
            		  stringarray[cell-1] = current;
            		  checkSOS = checkSOS(cell - (dimension - 1), dimension - 1);
            		  if(checkSOS == true) { 
            			  if(turn == 1) p1points++;
            			  else p2points++;
            			  }
            		  checkSOS = checkSOS(cell - (dimension), dimension);
            		  if(checkSOS == true) { 
            			  if(turn == 1) p1points++;
            			  else p2points++;
            			  }
            		  checkSOS = checkSOS(cell - (dimension + 1), dimension + 1);
            		  if(checkSOS == true) { 
            			  if(turn == 1) p1points++;
            			  else p2points++;
            			  }
            		  checkSOS = checkSOS(cell - (1), 1);
            		  if(checkSOS == true) { 
            			  if(turn == 1) p1points++;
            			  else p2points++;
            			  }
            		  System.out.println("Player " + turn + " placed an" + current + " at cell " + cell);
            	  }
                
                if (turn == 1) {
                  turnlabel.setText("Player 2's Turn");
                  square.setBackground(Color.red);
                  turn = 2;
                  turncount++;
                } else {
                  turnlabel.setText("Player 1's Turn");
                  square.setBackground(Color.blue);
                  turn = 1;
                  turncount++;

                }
                if (optionSimple.isSelected() && (p1points + p2points) > 0)
        		{
                	if (p1points > p2points)
                	{
                		gameOver = true;
                		winner = 1;
                		turnlabel.setText("GAME OVER: Player " + winner + " wins");
                		System.out.println("GAME OVER: Player " + winner + " wins");
                	}
                	if (p1points < p2points)
                	{
                		gameOver = true;
                		winner = 2;
                		turnlabel.setText("GAME OVER: Player " + winner + " wins");
                		System.out.println("GAME OVER: Player " + winner + " wins");
                	}
        			
        		}
                if (optionSimple.isSelected() && (turncount == dimension*dimension+1 && (p1points == p2points)))
        		{
                	gameOver = true;
                	turnlabel.setText("GAME OVER: It's a draw!");
            		System.out.println("GAME OVER: It's a draw!");
        			
        		}
                ///general not done yet
                if (optionGeneral.isSelected() && turncount == dimension*dimension+1)
        		{
                	if (p1points > p2points)
                	{
                		gameOver = true;
                		winner = 1;
                		turnlabel.setText("GAME OVER: Player " + winner + " wins");
                		System.out.println("GAME OVER: Player " + winner + " wins");
                	}
                	if (p1points < p2points)
                	{
                		gameOver = true;
                		winner = 2;
                		turnlabel.setText("GAME OVER: Player " + winner + " wins");
                		System.out.println("GAME OVER: Player " + winner + " wins");
                	}
                	if (p1points == p2points)
                	{
                		gameOver = true;
                		turnlabel.setText("GAME OVER: It's a draw!");
                		System.out.println("GAME OVER: It's a draw!");
                	}
        			
        			
        		}
                if (enablecomputer.isSelected() && gameOver == false) {
                  if (turn == 2) {

                    int randomX = ThreadLocalRandom.current().nextInt(150, 1770 + 1);
                    int randomY = ThreadLocalRandom.current().nextInt(60, 1000 + 1);
                    String[] arr = {
                      "S",
                      "O"
                    };
                    Random random = new Random();
                    int select = random.nextInt(arr.length);
                    if (arr[select] == "S") {
                      optionS.setSelected(true);
                    } else {
                      optionO.setSelected(true);
                    }
                    computer(randomX, randomY);
                  }

                } else {
                	
                }
                if (enablecomputer2.isSelected() && gameOver == false) {
                    if (turn == 1) {

                      int randomX = ThreadLocalRandom.current().nextInt(150, 1770 + 1);
                      int randomY = ThreadLocalRandom.current().nextInt(60, 1000 + 1);
                      String[] arr = {
                        "S",
                        "O"
                      };
                      Random random = new Random();
                      int select = random.nextInt(arr.length);
                      if (arr[select] == "S") {
                        optionS.setSelected(true);
                      } else {
                        optionO.setSelected(true);
                      }
                      computer(randomX, randomY);
                    }

                  } else {
                  	
                  }

              } else {
            	  
            	  if (gameOver == true) {
            		  
            	  }
            	  else {
                int randomX = ThreadLocalRandom.current().nextInt(150, 1770 + 1);
                int randomY = ThreadLocalRandom.current().nextInt(60, 1000 + 1);
                computer(randomX, randomY);
            	  }
              	}
                
              
              
            }
          });
        }
      }
      frame.getContentPane().add(board, BorderLayout.CENTER);
      frame.pack();
      frame.add(turnlabel, BorderLayout.SOUTH);
      frame.add(enablecomputer, BorderLayout.WEST);
      frame.add(enablecomputer2, BorderLayout.EAST);
    }
  }

  private JPanel createTopPanel() {

    optionS = new JRadioButton("S");
    optionO = new JRadioButton("O");

    optionSimple = new JRadioButton("Simple");
    optionGeneral = new JRadioButton("General");
    
    
    ButtonGroup group = new ButtonGroup();
    group.add(optionS);
    group.add(optionO);

    ButtonGroup group1 = new ButtonGroup();
    group1.add(optionSimple);
    group1.add(optionGeneral);

    JPanel topPanel = new JPanel();
    JLabel label = new JLabel("Board size:");
    JLabel modeLabel = new JLabel("Game Mode:");
    topPanel.add(label);
    JTextField boardSize = new JTextField(6);
    boardSize.addActionListener(this::createBoard);
    topPanel.add(boardSize);
    topPanel.add(modeLabel);
    topPanel.add(optionSimple, BorderLayout.EAST);
    topPanel.add(optionGeneral, BorderLayout.CENTER);
    topPanel.add(optionS, BorderLayout.NORTH);
    topPanel.add(optionO, BorderLayout.CENTER);

    return topPanel;
  }

  public static void computer(int x, int y) {
    robot.mouseMove(x, y);
    robot.delay(5);
    robot.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
    robot.delay(1);
    robot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
  }
  
  public String findValue(int cell) {
	  return stringarray[cell - 1];
  }
  public Boolean checkSOS(int cell, int direction) {
	    if (cell > dimension * dimension || cell < 1 || cell + 2 * direction > dimension * dimension || cell + 2 * direction < 1 || (cell-1) % dimension + 1 + 2 * ((direction + dimension * 2 + 1) % dimension - 1) < 1 || (cell-1) % dimension + 1 + 2 * ((direction + dimension * 2 + 1) % dimension - 1) > dimension) return false;
	    return findValue(cell) == " S " && findValue(cell + direction) == " O " && findValue(cell + 2*direction) == " S " ;
	}
  

  public static void main(String[] args) throws FileNotFoundException {
    EventQueue.invokeLater(() -> new BoardPanel().createAndDisplayGui());

    try {
      robot = new Robot();
    } catch (AWTException e) {
      e.printStackTrace();
    }

    
    PrintStream fileStream = new PrintStream("../SOS/src/SOS/output.txt");
    System.setOut(fileStream);


    
    
  }
}
