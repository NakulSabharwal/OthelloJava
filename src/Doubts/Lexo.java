package Doubts;

public class Lexo {
	
	
	public static void printAtLevelK(BinaryTreeNode<Integer> root,k)
	{
		if(root == null)
		{
			return;
		}
		
		if(k== 0)
		{
			System.out.println(root.data);
		}
		else
		{
			printAtLevelK(root.left,k-1);
			printAtLevelK(root.right,k-1);
			return;
		}
		
		
	}
	public static int printatdistancek(binaryTreeNode<Integer> root,int element,int k)
	{
		if(root == null)
		{
			return -1;
		}
		
		if(root.data == element)
		{
			printAtLevelK(root,k);
			return 0;
		}
		
		int leftDepth = printatDistacneK(root.left,element,k);
		if(leftDepth >-1)
		{
			System.out.println(root.data);
			return leftDepth + 1;
		} else if(leftDepth +1 >k){
			return leftDepth + 1;
		}
	}
	
	public static int[] finSpan(int[] prices)
	{
		Stack s = new Stack();
		
	}
	
	public static void printL(int numberSoFar,int n)
	{
		if(numberSoFar > n){
			return;
		}
		
		System.out.println(numberSoFar);
		for(int i=0;i<=9;i++)
		{
			printL(numberSoFar*10 + i, n);
		}
	}
	
	public static void printL(int n)
	{
		for(int i=1;i<=9;i++)
		{
			printL(i, n);
		}
	}
	public static void main(String args[]){
		printL(100);
	}

}
