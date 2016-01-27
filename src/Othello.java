import java.awt.Button;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class Othello extends JFrame implements ActionListener{
	static int board[][] = new int[8][8];

	boolean p1 = true;

	private static final long serialVersionUID = 1L;
	static JPanel[] row = new JPanel[9];
	static JButton[][] button = new JButton[8][8];
	static Dimension buttonDimension = new Dimension(45,45);
	static Dimension displayDimension = new Dimension(450,45);
	static JTextArea display = new JTextArea(1,20);
	Font font = new Font("Times new Roman", Font.BOLD, 14);

	public Othello() {

		super("Othello");


		display.setFont(font);
		display.setEditable(false);
		display.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		display.setPreferredSize(displayDimension);
		setDesign();


		setSize(450 , 450+50);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		GridLayout grid = new GridLayout(9,8);
		setLayout(grid);

		//FlowLayout f1 = new FlowLayout(FlowLayout.CENTER);
		FlowLayout f2 = new FlowLayout(FlowLayout.CENTER,1,1);

		for(int i = 0; i < 9; i++) {
			row[i] = new JPanel();
		}

		for(int i = 0; i < 8; i++)
			row[i].setLayout(f2);

		for(int i = 0; i < 8; i++) {
			for(int j=0;j<8;j++){
				button[i][j] = new JButton();
				button[i][j].setText(" ");
				button[i][j].setBackground(Color.YELLOW);
				button[i][j].addActionListener(this);
			}
		}


		button[3][3].setBackground(Color.BLACK);

		//button[3][3].setEnabled(false);
		//button[4][4].setEnabled(false);
		button[4][4].setBackground(Color.BLACK);
		button[4][3].setBackground(Color.WHITE);
		button[3][4].setBackground(Color.WHITE);

		//button[0].setBackground(Color.WHITE);
		//button[].setEnabled(false);

		for(int  i = 0;i<8;i++)
		{
			for(int j=0;j<8;j++)
				button[i][j].setPreferredSize(buttonDimension);
		}

		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				row[i].add(button[i][j]);

			}
		}

		for(int i=0;i<8;i++)
		{
			add(row[i]);
		}

		row[8].add(display);
		display.setText("BLACK TURN");
		add(row[8]);
		setVisible(true);
	}


	public final void setDesign() {
		try {
			UIManager.setLookAndFeel(
					"com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch(Exception e) {   
		}
	}


	@Override
	public void actionPerformed(ActionEvent ae) {
		ArrayList<pair> ans;

		for(int i=0;i<8;i++)
		{
			for(int j=0;j<8;j++){
				if(ae.getSource() == button[i][j])
				{
					button[i][j].setEnabled(false);
					if(p1)
					{
						makeMove(board,1, i, j);

						ans = possibleMoves(board, 2);
						//						if(ans == null)
						//						{
						//							System.out.println("plyaer 2 wins");
						//						}
						//						else
						//						{
						//							for(pair p:ans)
						//							{
						//								button[p.i][p.j].setEnabled(true);
						//								button[p.i][p.j].setBackground(Color.GREEN);
						//							}
						//						}
						//						
						//						do{
						//
						//						}
						//						while(!makeMove(board, 1, i, j));

						display.setText("White TURN                    ");
						p1=false;
					}
					else
					{
						makeMove(board, 2, i, j);
						p1=true;
						ans = possibleMoves(board, 1);
						//do{
						//                            
						//						}
						//						while(!makeMove(board, 2, i, j));
						display.setText("BLACK TURN                    ");
					}

					if(ans == null || ans.size() == 0)
					{
						win();
					}
					printBoard(board);
                    for(pair p :ans)
                    {
                    	button[p.i][p.j].setBackground(Color.GREEN);
                    }

					break;
				}

			}
		}
	}












	public static void win()
	{
		int b=0,w=0;
		for(int i=0;i<8;i++)
		{
			for(int j=0;j<8;j++)
			{
               if(board[i][j] == 1)
               {
            	   b++;
               }
               else if(board[i][j]==2)
               {
            	   w++;
               }
			}
		}
		
		if(b>w)
		{
			display.setText("BLACK WIN                    ");
		}
		else
		{
			display.setText("WHITE WIN                    ");
		}
	}






	public static class pair{
		int i;
		int j;
	}

	public static boolean makeMove(int board[][],int playerNumber,int x,int y)
	{
		int a[] = {1,1,1,0,0,-1,-1,-1};
		int b[] = {1,0,-1,1,-1,1,0,-1};

		if(board[x][y] != 0 )
		{
			return false;
		}
		boolean flag = false;
		for(int k=0;k<a.length;k++)
		{
			int iInc,jInc,i,j;
			iInc = a[k];
			jInc = b[k];

			boolean flag2 = false;
			i = x;
			j = y;

			if(i + iInc<0 || i + iInc >7 ||j + jInc<0 ||j + jInc>7 || board[i + iInc][j + jInc] == playerNumber)
			{
				continue;
			}

			i += iInc;
			j += jInc;

			while(i<8 && i>=0 && j<8 && j>=0 && board[i][j] != 0)
			{
				if(board[i][j] == playerNumber)
				{
					flag2 = true;
					break;
				}
				i += iInc;
				j += jInc;
			}

			if(!flag2)
			{
				continue;
			}

			i = x;
			j = y;
			i += iInc;
			j += jInc;
			while(i<8 && i>=0 &&j<8 && j>=0 && board[i][j] != 0 && board[i][j] != playerNumber)
			{
				board[i][j] = playerNumber;
				i += iInc;
				j += jInc;

			}

			flag = flag || flag2;

		}
		if(flag)
		{
			board[x][y] = playerNumber;
		}
		return flag;
	}


	public static ArrayList<pair> possibleMoves(int board[][],int playerNumber)
	{
		ArrayList<pair> array = new ArrayList<>();
		pair temp = new pair();

		for(int i=0;i<8;i++)
		{
			for(int j=0;j<8;j++)
			{
				if(board[i][j] == playerNumber)
				{
					int a[] = {1,1,1,0,0,-1,-1,-1};
					int b[] = {1,0,-1,1,-1,1,0,-1};

					for(int k=0;k<a.length;k++)
					{
						int I=i,J=j,iInc,jInc;
						iInc = a[k];
						jInc = b[k];


						I = i + iInc;
						J = j + jInc;
						
						if(I<0 || I>7 || J<0 ||J>7 || board[i + iInc][j + jInc] == playerNumber || board[i + iInc][j + jInc]==0)
						{
							continue;
						}



						while(I < 8 && I>=0 && J<8 && J>=0 && board[I][J]!= playerNumber)
						{
							if(board[I][J] == 0)
							{
								pair p = new pair();
								p.i = I;
								p.j = J;
								array.add(p);
								break;
							}
							I = I + iInc;
							J = J + jInc;
						}
					}
				}
			}
		}

		return array;
	}







	public static void printBoard(int board[][])
	{
		for(int i=0;i<8;i++)
		{
			for(int j=0;j<8;j++)
			{
				button[i][j].setEnabled(true);
				if(board[i][j]==1)
				{
					button[i][j].setBackground(Color.BLACK);
					System.out.print(board[i][j]);
				}
				else if(board[i][j]==2)
				{
					button[i][j].setBackground(Color.WHITE);
					System.out.println(board[i][j]);
				}
				else
				{
					button[i][j].setBackground(Color.YELLOW);
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {

		Othello  o = new Othello();
		board[3][3] = board[4][4] =  1;
		board[4][3] = board[3][4] =  2;







	}

}
