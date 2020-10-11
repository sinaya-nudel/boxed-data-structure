package maman15sinaya;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JOptionPane;


/*
This class represent the input class of input from the user 
*/
public class InputOutput 
{
	
	private SizesTree sizes_tree;
	private VolumesTree volumes_tree;
	
	protected static boolean getInputUntil = true;  //This is a flag to get input from the user until to exit from doing it.
    private static Scanner scannerOfFile = new Scanner(System.in);   // Open stream to get input from file.
	
	public InputOutput()
	{
		sizes_tree= new SizesTree();
		volumes_tree= new VolumesTree();
	}
	
	/*
	 Getting source of input from the user 
	 */
	protected void getDataFromUser()
	{
		String cmd= JOptionPane.showInputDialog("Hello,\nplease type 'F', for input data from file.\nPlease type 'K', for input data from keyboard.");
		if (cmd.equals("K"))
		{
			getDataFromKeyboard();// Getting data from keyboard
		}
		else if  (cmd.equals("F"))
		{
			String nameOfFile= JOptionPane.showInputDialog("  Please enter full file name (with path) ");
			getDataFromFile(nameOfFile);//Getting data from file
		}
		else
		{
			JOptionPane.showMessageDialog(null,"Error. No input was entered.","Error",JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}
	
	
	
	/*
	 Getting data from file
	 */
	private void getDataFromFile(String nameOfFile) 
	{
            try 
            {   
            	String input;
                scannerOfFile = new Scanner(new File(nameOfFile)); 
                while(getInputUntil)
        		{
        			if (scannerOfFile.hasNextLine()){
        				 input = scannerOfFile.nextLine();   // Getting the next line from the user
        	            
        	        }
        	        else
        	        {
        	        	getInputUntil=false;//Getting out from the loop
        				continue;
        	        }
        			int start=input.indexOf('(')+1;
        			int stop=input.indexOf(')');
        			String values = input.substring(start, stop);
                    String[] arrayOfInput  = values.split(",");
                    double side = Double.parseDouble(arrayOfInput[0]);
                    double height = Double.parseDouble(arrayOfInput[1]);
                    
        			if(input.contains("INSERTBOX")){
        				INSERTBOX(side,height);
        				JOptionPane.showMessageDialog(null,"the box [side= "+side+",height= "+height+"] has been inserted.\n");

        			}
        			else if(input.contains("CHECKBOX")){
        				boolean isFound = CHECKBOX(side,height);
        				if(isFound)
        					JOptionPane.showMessageDialog(null,"there is a box which matchs the minimum side= "+side+" and the minumum height= "+height+".\n");
        				else
        					JOptionPane.showMessageDialog(null,"no box found for the minimum side= "+side+" and the minumum height= "+height+".\n");
        			}
        			else if(input.contains("REMOVEBOX")){
        				REMOVEBOX(side,height);
        				JOptionPane.showMessageDialog(null,"the box [side= "+side+",height= "+height+"] has been removed.\n");     
        			}
        			else if(input.contains("GETBOX")){
        				Box box = GETBOX(side,height);
                		if(box!=null)
                			JOptionPane.showMessageDialog(null,"for minimum side= "+side+" and minimum height= "+height+ " the matched box with the minimal volume: [side= " +box.getSide()+ ", height= "+ box.getHeight()+"].\n");
                		else
                			JOptionPane.showMessageDialog(null,"no box found for the minimum side= "+side+" and the minumum height= "+height+".\n");
        			}

        		}

                
            } 
            
            catch (FileNotFoundException e) 
            {
                JOptionPane.showMessageDialog(null,"Can't read from file. Please make sure you insert the full path of the file\nThe program will now finish    ","Reading File Erorr",JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        
    }	

	
	
	/*
	 Getting data from keyboard 
	 */
	private void getDataFromKeyboard()
	{
		while (getInputUntil)
		{
			String input= JOptionPane.showInputDialog("Menu:\n1-INSERT BOX\n2-REMOVE BOX\n3-GET BOX\n4-CHECK BOX\n5-EXIT\nPlease enter the operation number.");
			int cmd = Integer.parseInt(input);
			if (cmd==5){
				getInputUntil=false;
				continue;
			}
			input= JOptionPane.showInputDialog("Write down side length and height length seperated by space.");		 
             String[] arrayOfInput  = input.split(" ");                  
            double side = Double.parseDouble(arrayOfInput[0]);
            double height = Double.parseDouble(arrayOfInput [1]);
            switch(cmd) {
            case 1 : INSERTBOX(side,height);
            		JOptionPane.showMessageDialog(null, "the box [side= "+side+",height= "+height+"] has been inserted.");               
            		break;
            case 2 : REMOVEBOX(side,height);
            		JOptionPane.showMessageDialog(null, "the box [side= "+side+",height= "+height+"] has been removed.");     
            		break;
            case 3 : Box box = GETBOX(side,height);
            		if(box!=null)
            			JOptionPane.showMessageDialog(null, "for minimum side= "+side+" and minimum height= "+height+ " the matched box with the minimal volume: [side= " +box.getSide()+ ", height= "+ box.getHeight()+"].");
            		else
            			JOptionPane.showMessageDialog(null, "no box found for the minimum side= "+side+" and the minumum height= "+height+".\n");
    				break;
            case 4 : boolean isFound = CHECKBOX(side,height);
            		 if(isFound)
    					JOptionPane.showMessageDialog(null, "there is a box which matchs the minimum side= "+side+" and the minumum height= "+height+".\n");
            		 else
            			 JOptionPane.showMessageDialog(null, "no box found for the minimum side= "+side+" and the minumum height= "+height+".\n");
			break;
            }
		}
	}
		
	
	private void INSERTBOX(double side, double height){
		sizes_tree.insertValues(height, side);
		volumes_tree.insertValues(height, side);
	}
	private void REMOVEBOX(double side, double height){
		sizes_tree.removeValues(side, height);
		volumes_tree.removeValues(side, height);
	}
	private Box GETBOX(double side, double height){
		Box box = volumes_tree.getValues(side, height);
		return box;
	}	
	private boolean CHECKBOX(double side, double height){
		boolean isFound = sizes_tree.checkValues(side, height);
		return isFound;
	}
	
}
