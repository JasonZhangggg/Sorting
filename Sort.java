/*Jason Zhang
 * Period 5
 * 10/30/2018
 * */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Sort {
	Scanner consoleInput = new Scanner(System.in);
	String input;
	Scanner fileInput;
	int[] inputArray;
	long startTime;

	public Sort() {
		System.out.println("Enter number from input file");
		System.out.println("1: input1.txt 2: input2.txt 3: input3.txt 4: input4.txt");
		//take in input of which file to use, and which type of sorting to use 
		input = consoleInput.nextLine();
		if (input.length() != 1 && input.charAt(0) != '1' && input.charAt(0) != '2' && input.charAt(0) != '3'
				&& input.charAt(0) != '4') {
			System.out.println("Enter 1, 2, 3, or 4");
			while (input.length() != 1 && input.charAt(0) != '1' && input.charAt(0) != '2' && input.charAt(0) != '3'
					&& input.charAt(0) != '4') {
				input = consoleInput.nextLine();
			}
		}
		try {
			//Take in the input from the file
			fileInput = new Scanner(new File("input" + input.charAt(0) + ".txt"));
		} catch (FileNotFoundException ex) {
			//If it crashes stop the program
			ex.printStackTrace();
			System.exit(0);
		}
		String infile = fileInput.nextLine();
		String[] inputStringArray = infile.split(",");
		inputArray = new int[inputStringArray.length];
		for (int i = 0; i < inputStringArray.length; i++) {
			inputArray[i] = Integer.parseInt(inputStringArray[i]);
		}
		System.out.println("Enter number for the sort you want to use");
		System.out.println("1: Bubble 2: Selection 3: Table 4: Quick Sort");
		input = consoleInput.nextLine();
		if (input.length() != 1 && input.charAt(0) != '1' && input.charAt(0) != '2' && input.charAt(0) != '3'&& input.charAt(0) != '4') {
			System.out.println("Enter 1, 2, or 3");
			while (input.length() != 1 && input.charAt(0) != '1' && input.charAt(0) != '2' && input.charAt(0) != '3'&& input.charAt(0) != '4') {
				input = consoleInput.nextLine();
			}
		}
		//run the different sorts depending on the one they want
		startTime = System.currentTimeMillis();
		if (input.equals("1")) {
			inputArray = bubbleSort(inputArray);
		}
		if (input.equals("2")) {
			inputArray = selectionSort(inputArray);
		}
		if (input.equals("3")) {
			inputArray = tableSort(inputArray);
		}
		if(input.equals("4")) {
			quickSort(inputArray, 0, inputArray.length-1);
		}
		//print the time, and the sorted array to the output file
		long totalTime = System.currentTimeMillis() - startTime;
		System.out.println("Total Time:" + totalTime);
		PrintWriter pw;
		try {
			pw = new PrintWriter(new FileWriter(new File("output.txt")));
			String output = "";
			for (int i = 0; i < inputArray.length; i++) {
				output += inputArray[i] + ",";
			}
			output += "\nTotal Time:" + totalTime;
			pw.write(output);
			pw.close();
			System.out.println("Finished printing to file");
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}

	}

	// Tally how often you see each number, print out that number of times
	int[] tableSort(int[] array) {
		int[] tally = new int[1001];
		for (int i = 0; i < array.length; i++) {
			tally[array[i]]++;
			//find how many of each numbers there are
		}
		int count = 0;
		// i keeps track of the actual number
		for (int i = 0; i < tally.length; i++) {
			// j keeps track of how many times we have seen that number
			for (int j = 0; j < tally[i]; j++) {
				array[count] = i;
				count++;
			}
		}
		return array;
	}

	// Find the smallest and move it to the front

	int[] selectionSort(int[] array) {
		for (int j = 0; j < array.length; j++) {
			//get the smallest number
			int smallestNumber = array[j];
			int smallestIndex = j;
			for (int i = j; i < array.length; i++) {
				if (array[i] < smallestNumber) {
					smallestNumber = array[i];
					smallestIndex = i;
					//switch the two numbers
				}
			}
			int temp = array[smallestIndex];
			array[smallestIndex] = array[j];
			array[j] = temp;
		}

		return array;
	}

	// compare each pair of numbers, and move the larger number to the right
	int[] bubbleSort(int[] array) {
		for (int j = 0; j < array.length; j++) {
			for (int i = 0; i < array.length - 1; i++) {
				// if the one on the left is larger, swap
				if (array[i] > array[i + 1]) {
					int temp = array[i];
					array[i] = array[i + 1];
					array[i + 1] = temp;
				}
			}
		}
		return array;
	}
	
	//the psudeo code for this came from:
	//https://www.geeksforgeeks.org/quick-sort/
	void quickSort(int[]array, int low, int high) {
		if(low<high) {
			int pi = partition(array, low, high);
			
			 quickSort(array, low, pi - 1);
		     quickSort(array, pi + 1, high); 
		}
	}
	int partition(int[]array, int low, int high) {
		int pivot = array[high];
		int i = (low - 1);
		for(int j = low; j<=high-1; j++) {
			if(array[j]<=pivot) {
				i++;
				int tempArray = array[i];
				array[i] = array[j];
				array[j] = tempArray;
			}
		}
		int tempArray2 = array[i+1];
		array[i+1] = array[high];
		array[high] = tempArray2;
		return i+1;
	}
	public static void main(String[] args) {
		new Sort();
	}

}
