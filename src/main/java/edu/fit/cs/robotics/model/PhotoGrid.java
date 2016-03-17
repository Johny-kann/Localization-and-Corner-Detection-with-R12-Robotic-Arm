package edu.fit.cs.robotics.model;

public class PhotoGrid {

	public char grid[] = new char[9];
	
	/*
	 * C = corner
	 * P = Partial FIlled
	 * F = Filled
	 * E = Empty
	 */
	
	public void setchar(int row,int col,char input)
	{
		grid[3*row+col] = input;
	}
	
	public char getchar(int row,int col)
	{
		return grid[3*row+col]; 
	}
	
	public void print()
	{
		for(int i=0;i<3;i++){
			
			for(int j=0;j<3;j++)
			{
				System.out.print(grid[3*i+j]+" ");
			}
			System.out.println("");
		}
	}
	
}
