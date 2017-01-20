/*
Name: Gareth Postma
MacID: postmagn
Student Number: 1422248
Description: This code will take any number of matrices, multiply them together 
 			and find the inverse matrix of the result. */

public class HWK2_postmagn {
	//initiate matrix building function. 
	//inputs are the # of row and columns and an array with the matrix values in it
	public static double [][] matrix (int row,int column, double [] numb, int G) {
		   //create matrix calls matrix of size row by column
		   double [][] matrix = new double [row] [column];
		   //use for loop to input values from numb to matrix
		   for(int o = 0; o < row; o++){
			   for (int x = 0; x<column; x++) {
				   //the values for this matrix start at numb index G
				   matrix[o][x] = numb [G];
				   // G increases each time
				   G++;}}
		   //returns the final array called matrix
		   return matrix;}
	//initiate function that will multiply matrices
	public static double [][] mult(double [][] mat1, double [][] mat2, int row1, int column, int column2){
		//create double array to store the product
		double [][] next = new double [row1][column2];
		//use for loop to go through values in mat1 and mat2 and store product in next
		for (int i = 0; i< row1; i++)
			for(int j = 0; j < column2; j++)
				for(int k = 0; k< column; k++)
					next[i][j] += mat1[i][k]*mat2[k][j];
		//return product of the two matrices
		return next;}
	// initiate function to calculate determinant of matrix
	public static double det(double [][] matrix, int row, int col){
		double det1 = 0;
		// the determinate of a single value is that value
		if (row == 1) det1 = matrix[0][0];
		// otherwise calculate the determinate 
		else{double a = 1;
		  int n =1;
		  int m = 0;
		  int i = 0;
		  //loop through the columns so we can get the first value for each column
		  //we are taking the determinant across the top row
		  		for(int j = 0; j< col; j++){
					//create array follow to store smaller matrix
		  			double [][] follow = new double [row-1][col-1];
					// loop through rows and columns so we can store the values in the smaller matrix
		  			for (int f = 0; f< row-1; f++)
						for(int g = 0; g < col-1; g++){
							// n must always be 1 bigger than f because we never want the values in the top row 
							n = f+1;
							// we must skip over the values in the column we are taking the determinant of
							if (m==g) m = g+1;
							else if (g==j) m= g+1;
							else m =g;
							//store the values from the old matrix into the smaller matrix
							follow [f][g] = matrix[n][m];}
		  		// determinant = +-1 * the first row value * the determinant of the smaller matrix
		  		// these part is recursive
				 det1 += a* matrix[i][j]*HWK2_postmagn.det(follow,row-1,col-1);
				 //a is -1 every other time
				 a = a*-1;
		  		}}
		//it returns the determinant
		return det1;}
	//initiate function that removes a row and column for when doing the adjunct
	public static double [][] removeRowColumn(double [][] matrix, int row, int column,int Rrow, int Rcolumn){
		//create smaller array
		double [][] small = new double [row-1][column-1];
		int n =0, m=0;
		//loop through row and column values 
		for(int j = 0; j<row; j++){
			// we must skip over a value when it is in the row we are removing
			if (j == Rrow) continue;	
			for(int k = 0; k<column;k++){
				// skip over a value when it is in the column we are removing
				 if (k==Rcolumn) continue;
				 // store the old values in the new array
				 small[m][n] = matrix[j][k];
				 // n will keep increasing by 1 till it reaches the length of the row, then it will return to 0
				 n = (n+1)%(row-1);
				 // if n returns to 0 so will m
				 if (n==0) m++;}}
		// return the smaller matrix
		return small;}
	//initiate a function for finding the adjunct
	public static double [][] adj (double [][] mat, int row, int col) {
		//create an array for storing the smaller matrices
		double [][] follow = new double [row-1][col-1];
		//create an array for storing the cofactor expansion
		double [][] cofactor = new double [row][col];
		// loop through the rows and columns
		for(int i =0;i<row;i++){
			for(int j=0;j<col;j++){
				int a = 1;
				//find the smaller matrix using the removeRowColumn function
				follow = removeRowColumn(mat, row,col, i,j);
				// find whether the adjunct value is positive or negative
				if ((i+j)%2 != 0) a = -1;
				// find the determinant of the smaller matrix and put it in for the cofactor value
				cofactor[i][j] = a*HWK2_postmagn.det(follow, row-1, col-1);}}
		//create array for the transpose of the cofactor
		double [][] adjunct = new double [row][col];
		//loop through the rows and columns of the transpose
		for (int l = 0;l<row;l++)
			for(int p = 0; p< col;p++){
				//transpose adjunct into transpose
			    adjunct[l][p] = cofactor [p][l];}
		//return the adjunct
		return adjunct;}
// initiate the main function	
	public static void main(String[] args) {
		// store the number of matrices
		int N = Integer.parseInt(args[0]);
		//create a double array to store the sizes of each matrix
		int [][] matSize = new int[2][N];
		// inputs m size values of the matrices into first array of matSize
		int i = 0;
		for (int j = 1; j <= N*2-1; j=j+2) {
			matSize[0][i] = Integer.parseInt(args[j]);
		    i++;}
		// inputs n size values of the matrices into the second array of matSize
		int n =0;
		for (int k = 2; k <=N*2; k = k+2) { 
			matSize[1][n] = Integer.parseInt(args[k]);
			n++;
		}
		int l = 0;
		int sum = 0;
		//loop for each matrix
		while (l < N) {
			// calculate the total number of matrix values
			sum += matSize[0][l]*matSize[1][l];
			//make sure the matrices are all sized properly to multiply
			if (l < N-1 && matSize[0][l+1] != matSize[1][l]) {
				System.out.println("Multiplication error.");
				break;	
			}
			l++;
		}
		int B = N*2+1;
		int P = 0;
		//create an array to store all the matrix values
		double [] vals = new double [sum];
		while (P < sum) {
			// store all the values into vals
			vals [P] = Double.parseDouble(args[B]);
			B++;
			//when all the values are in move to this code
			if (P == sum-1){
				int L = 0;
				int Q = 1;
				//set a variable to the first number of rows in the first matrix
				int prev = matSize[0][0];
				//store the first matrix into nextmatrix using the matrix function
				double [][] nextmatrix = HWK2_postmagn.matrix(matSize[0][0], matSize[1][0], vals, L);
				// set prod = to the first matrix
				double [][] prod = nextmatrix;
				// if there are more than one function we must multiply them together
				// if there is one matrix we can skip this step
				if (N!=1){
				while (Q < N) {
					//set row = row size of the Qth matrix which is stored in matSize
					int row = matSize[0][Q];
					// do the same with the column
					int column = matSize[1][Q];
					//find the number of values in this matrix
					L += matSize[0][Q-1]*matSize[1][Q-1];
					//store the values of this matrix in nextmatrix using the matrix builder
					nextmatrix = HWK2_postmagn.matrix(row, column, vals,L);
					//find product of prod times the new matrix using mult functtion
					prod = HWK2_postmagn.mult(prod,nextmatrix,prev,row,column);
					Q++;
				}}
			 //find the determinant of the final product
			  double determinant = HWK2_postmagn.det(prod, prev, prev);
			  //if the determinant is zero return an error statement
			 if (determinant == 0) {System.out.println("Matrix not invertible");
			  break;}
			 //find the adjunct of the matrix
			 double [][] adjunct = HWK2_postmagn.adj(prod, prev,prev);
			  //create array for the final inverse
			 double [][] final1 = new double[prev][prev];
			  //create string to store the inverse values
			 String s = "";
			 //loop through the inverse
			  for (int o = 0; o < prev; o++){
				  for (int H = 0; H<prev;H++){
				//set the inverse values equal to the adjunct values divided by the determinant
				   final1[o][H] = adjunct[o][H]/determinant;
				   //round off the values in final
				   double roundoff = Math.round(final1[o][H]*100)/100.0;
				   //store these values in string s
				   s = s + " " + String.valueOf(roundoff);
				  }
			  }
			  //print the final answer
			  System.out.println(s);
			}
			P++;}}}
	