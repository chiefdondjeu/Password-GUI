import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//import java.util.Scanner;

public class NewPasswords extends JFrame
	//extanding enables class to use the stuff from the library
	//class name has to be identical to file name
{
		//variables
		private JLabel label1;
		private JLabel label2;

		private JTextField textfield1;
		private JTextField textfield2;

		private JButton button;

		public NewPasswords()	//constructor
		{
			super("Password");
			setLayout(null);
			//(x, y, w, h)

			label1 = new JLabel("Enter Password");
			label1.setBounds(50,20,125,25); 
			add(label1);

			textfield1 = new JTextField(10);
			textfield1.setBounds(180,20,200,25);
			add(textfield1);

			label2 = new JLabel("Confirm Password");
			label2.setBounds(50,55,125,25);
			add(label2);

			textfield2 = new JTextField(10);
			textfield2.setBounds(180,55,200,25);
			add(textfield2);

			button = new JButton("Enter");
			button.setBounds(210,100,75,25);
			add(button);

			//for actions
			TheHandler handler = new TheHandler();
			textfield1.addActionListener(handler);
			textfield2.addActionListener(handler);
			button.addActionListener(handler);

		}

		private class TheHandler implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				String pw1, pw2, msg;
				pw1 = textfield1.getText();
				pw2 = textfield2.getText();

				/*
				when button is pressed first check if textfield is empty
				then if passwd match, then passwd size, then upper case and digit
				*/

				if(event.getSource() == button)
				{
					boolean empty, proceed;
					empty = false;
					proceed = true; //continue 

					//when text boxs are empty
					if(pw1.length() == 0 || pw2.length() == 0)
					{
						empty = true;
						proceed = false;
						msg = "The text field is empty.\nPlease try again.";
						JOptionPane.showMessageDialog(null, msg);
					}

					//when # of char does not match
					if(pw1.length() != pw2.length())
					{
						msg = "Password does not match.\nPlease try again.";
						JOptionPane.showMessageDialog(null, msg);
					}

					//when # of char match
					else if(pw1.length() == pw2.length())
					{
						int pw1_char, pw2_char, capital_count = 0, digit_count = 0;

						//check if each char match
						for(int i = 0; i < pw1.length(); i++)
						{
							pw1_char = pw1.charAt(i);
							pw2_char = pw2.charAt(i);
							//.charAt(i) reads each char, similar to the concept of an array
							
							if(pw1_char != pw2_char)
							{
								proceed = false;
								msg = "Password does not match.\nPlease try again.";
								JOptionPane.showMessageDialog(null, msg);
								break;
							}
						}

						//checks if password is bigger than 8 char
						if(pw1.length() < 8 && empty != true)
						{	
							proceed = false;
							msg = "Password is too short.\nPlease try again.";
							JOptionPane.showMessageDialog(null, msg);
						}

						//checks if the password contains an Upper case and a digit 
						while(proceed == true)
						{
							for(int i = 0; i < pw1.length(); i++)
							{
								pw1_char = pw1.charAt(i);

								//check for upper case
								if(pw1_char >= (int)'A' && pw1_char <= (int)'Z')
									capital_count++;

								//check for digit
								if(pw1_char >= (int)'0' && pw1_char <= (int)'9')
									digit_count++;

								//end loop when both are present
								if(capital_count > 0 && digit_count > 0)
									break;

							}

							//when no upper case are present
							if(capital_count < 1)
							{
								proceed = false;
								msg = "Password has no upper case letter.\nPlease try again";
								JOptionPane.showMessageDialog(null, msg);
								break;
							}

							//when no digits are present
							if(digit_count < 1)
							{
								proceed = false;
								msg = "Password has no digits.\nPlease try again";
								JOptionPane.showMessageDialog(null, msg);
								break;
							}

							//when all specifications are checked
							else
							{
								proceed = false;
								msg = "Success.";
								JOptionPane.showMessageDialog(null, msg);
							}

						}//end while loop

					}//end elseif


				}//button action end

			}
		}//class end

	public static void main(String[] args)	//main
	{
			NewPasswords window = new NewPasswords();
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//enables window to terminate so it doesn't run in the background
			
			window.setSize(475,175);	//window size (l,w)
			window.setVisible(true);
			window.setResizable(false);

	}

}//class end