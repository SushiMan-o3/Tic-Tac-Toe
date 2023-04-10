/*
Name: Sushim Malla
Date: 4/10/2023
Description: A tic tac toe game that uses the java's cordinate system to play the game. It starts at (0, 0) (top left) 
and goes up to (2,2) (bottom right). You have to input the numbers like this 11 (1,1) which is the center.
*/

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

class tictactoe 
{
  public static void main(String[] args) {
    JFrame j = new JFrame("Tic Tac Toe");
    GamePanel pnl = new GamePanel();
      
    j.setSize(600,600);
    j.add(pnl);
    j.setIconImage(new ImageIcon("img_x.png").getImage());
    
    j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    j.setVisible(true);
    

  while(true)
        {
            pnl.animate();
            pnl.move();
            if (pnl.checkWin(1) == true) {
              System.out.println("X won!");
              break;
              
            }
            else if (pnl.checkWin(2) == true) {
              System.out.println("O won!");
              break;
            }
            else {
              ;
            }
      
            try{
              Thread.sleep(100);
            }
            catch(Exception e){
              System.out.println("Exception in thread sleep: "+e);
            }
        }
    }
  
  
}


class GamePanel extends JPanel
{
    BufferedImage draw_grid, draw_x, draw_o;
    BufferedImage[][] subImages;
    final static String img_grid = "grid.jpg";
    final static String img_o = "img_o.png";
    final static String img_x = "img_x.png";
    /*
     0 = blank
     1 = o
     2 = x
     */
    public int myMap[][] = {
      {0,0,0},
      {0,0,0},
      {0,0,0}
    };
    
    /*
     false = x
     true = o
     */
    public boolean turn = false;
    
  
    GamePanel()
    {
        try
        {
            draw_grid = ImageIO.read(new File(img_grid));
            draw_x = ImageIO.read(new File(img_x));
            draw_o = ImageIO.read(new File(img_o));
        }
        catch(Exception e)
        {
            System.out.println("Exception in Loading Image: "+e);
        }
    }

    @Override
    public void paint(Graphics g)
    {
      g.drawImage(draw_grid, -5, -15, null);
      
      for(int y=0; y<3; y++){
        for(int x=0; x<3;x++){
          int num = myMap[x][y];
          
          if (num == 1){
            g.drawImage(draw_x, 200*x, 200*y, null);
          }
          else if (num == 2) {
            g.drawImage(draw_o, 200*x, 200*y, null);
          }
          else {
            ;
          }
        } 
      }
    }
    
    public void move()
    {
      Scanner myObj = new Scanner(System.in);
      
      if (turn == false) 
      {
        System.out.println("Enter move for 0 :"); 
        String movez = myObj.nextLine(); 
        int movez_x = movez.charAt(0) - '0';
        int movez_y =movez.charAt(1) - '0';
        
        if (myMap[movez_x][movez_y] != 0){
          do {
            System.out.println("Enter move for O :"); 
            movez = myObj.nextLine(); 
            movez_x = movez.charAt(0) - '0';
            movez_y = movez.charAt(1) - '0';
        
          }
          while (myMap[movez_x][movez_y] != 0);
        }
        
        turn = true;
        myMap[movez_x][movez_y] = 2;
        
      }
      
      else
      {
        System.out.println("Enter move for X :"); 
        String movez = myObj.nextLine(); 
        int movez_x = movez.charAt(0)- '0';
        int movez_y = movez.charAt(1) - '0';
        
        if (myMap[movez_x][movez_y] != 0){
          do {
            System.out.println("Enter move for X :"); 
            movez = myObj.nextLine(); 
            movez_x = movez.charAt(0) - '0';
            movez_y = movez.charAt(1) - '0';
        
          }
          while (myMap[movez_x][movez_y] != 0);
        }
        turn = false;
        myMap[movez_x][movez_y] = 1;
      }
    }
    
    
    public boolean checkWin(int winner)
    {
      // checking verticly
      if (myMap[0][0] == winner && myMap[1][0] == winner && myMap[2][0] == winner) {return true;}
      if (myMap[0][1] == winner && myMap[1][1] == winner && myMap[2][1] == winner) {return true;}
      if (myMap[0][2] == winner && myMap[1][2] == winner && myMap[2][2] == winner) {return true;}
      
      // checking horozontally
      if (myMap[0][0] == winner && myMap[0][1] == winner && myMap[0][2] == winner) {return true;}
      if (myMap[1][0] == winner && myMap[1][1] == winner && myMap[1][2] == winner) {return true;}
      if (myMap[2][0] == winner && myMap[2][1] == winner && myMap[2][2] == winner) {return true;}
      
      // checking diagonally 
      if (myMap[0][0] == winner && myMap[1][1] == winner && myMap[2][2] == winner) {return true;}
      if (myMap[0][2] == winner && myMap[1][1] == winner && myMap[2][0] == winner) {return true;}
      
      return false;
    }     

    public void animate(){
      repaint();

    }
 
}