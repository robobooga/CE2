/*
 * 
 * 	Name: 		Nicholas Tan XunSheng
 * 	Group:		C02
 * 	Matric No:	A0111837J
 * 	
 * 	Assumptions made for program:
 * 	1.	Users enter correct command format
 * 	2.	Users enter correct command arguments format
 *  3.	Users enter correct file name and arguments when initializing program
 * 
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class TextBuddy {
	
	//offsets for getting user parameters from command
	private static final int ADD_COMMAND_OFFSET = 4;
	private static final int DELETE_COMMAND_OFFSET = 7;
	private static final int SEARCH_COMMAND_OFFSET = 7;
	
	private static ArrayList <String> textList;
	
	public static void main(String [] args){
		
		String fileName = args[0];
		
		String readMessage = readFile(fileName);
		
		System.out.println(readMessage);
		
		while(true){
			waitForCommand(fileName);
		}
	}
	
	//
	//	Method for running unit tests
	//
	public static String runTest(String testFileName, String command) {

		String message;
		
		readFile(testFileName);
		
		message = handleInput(command, testFileName);

		return message;
	}

	//
	//	Wait for user's input, then calls command handler
	//
	private static void waitForCommand(String fileName) {
		
		Scanner sc = new Scanner(System.in);
		String userInput = "";
		String output = "";
		
		while(sc.hasNextLine()){
			System.out.print("command: ");
			userInput = sc.nextLine();
			output = handleInput(userInput, fileName);
			System.out.println(output);
		}
		
	}
	
	//
	//	Direct the commands issued by the user to the relevant handling methods
	//
	private static String handleInput(String userInput, String fileName) {
		
		//split up the command and arguments entered by the user
		String [] textElements = userInput.split(" ");
		String command = textElements[0];
		String message = "";
		
		switch(command){
			case "display":
				message = displayList();
				break;
			case "add":
				message = addText(userInput, fileName);
				break;
			case "clear":
				message = clearFile(fileName);
				break;
			case "delete":
				message = deleteLine(userInput, fileName);
				break;
			case "sort":
				message = sortFile(fileName);
				break;
			case "search":
				message = searchWord(userInput);
				break;
			case "exit":
				System.exit(0);
				break;
			default:
				message = "Enter correct command";
					
		}
		
		return message;
	}
	
	//
	// Sort the items within the text file
	//
	private static String searchWord(String keyword) {
		String message = "";
		String searchWord = keyword.substring(SEARCH_COMMAND_OFFSET);
		int currentIndex = 0;
		
		for(String currentItem: textList){
			currentIndex++;
			if(currentItem.contains(searchWord)){
				message += currentIndex + ". " + currentItem + "\n";
			}
		}
	
		return message;
	}
	
	//
	// Sort the items within the text file
	//
	private static String sortFile(String fileName) {
		
		Collections.sort(textList);
		saveFile(fileName);
		String message = "file sorted";
		
		return message;
	}

	//
	// Delete the line in the text file which the user specified
	//
	private static String deleteLine(String userInput, String fileName) {
		
		//extract line number from user input e.g. delete <line number>, -1 for number to index mapping
		int userNumber = Integer.parseInt(userInput.substring(DELETE_COMMAND_OFFSET));
		
		int lineNumber = userNumber - 1;
		
		String lineToBeRemoved = textList.get(lineNumber);
		
		//delete the line from arraylist
		textList.remove(lineNumber);
		
		String message = "deleted from "+fileName+":\""+lineToBeRemoved+"\"";
		
		
		saveFile(fileName);
		return message;
	}
	
	//
	//	Add the line specified by the user into the text file
	//
	private static String addText(String userInput, String fileName) {
		
		String line = userInput.substring(ADD_COMMAND_OFFSET);
		textList.add(line);
		saveFile(fileName);
		String message = "added to "+fileName+": \""+line+"\"";
		
		return message;
	}

	//
	//	Saves the contents of the arraylist into the text file
	//
	private static void saveFile(String fileName) {
		try {
			File file = new File(fileName);
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
		
			//writes every line into the file
			for(String line: textList){
				bw.write(line+"\n");
			}
			bw.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	//
	//	Clears the contents of the arraylist, then saves the file
	//
	private static String clearFile(String fileName) {
		textList.clear();
		saveFile(fileName);
		String message = "all content deleted from "+fileName;
		
		return message;
		
	}
	
	//
	//	Display the contents of the file / arraylist read from file
	//
	private static String displayList() {
		String message="";
		int lineNumber = 1;
		
		for(String line: textList){
			message += lineNumber + ". " + line + "\n";
			lineNumber++;
		}
		
		return message;
	}
	
	//
	//	Copies the contents of the file into an arraylist to be used by the program
	//
	private static String readFile(String nameOfFile) {
		String message = "";
		try{
			FileReader fileToRead = new FileReader(nameOfFile);
			BufferedReader reader = new BufferedReader(fileToRead);
			
			textList = new ArrayList<String>();
			String line = null;

			while((line = reader.readLine())!=null){
				textList.add(line);
			}
			
			reader.close();
			message = "Welcome to TextBuddy, "+ nameOfFile + " is ready for use";
			return message;
		}
		catch(IOException e){
			message = "Welcome to TextBuddy, "+ nameOfFile + " is not ready for use";
			return message;
		}
	}
}

