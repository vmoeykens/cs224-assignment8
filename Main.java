import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

class Main {
    public static void main(String[] args) throws Exception {
        File inputFile = promptFile();
        ArrayList<ArrayList<Integer>> entries = parseFile(inputFile);
        for (ArrayList<Integer> numbers : entries) {
            System.out.println(numbers);
        }
    }

    /**
     * Prompts the user for a file and will loop until a valid filename is inetered
     * @return File object from the filename the user entered
     */
    public static File promptFile() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a filename for input file: ");
        String fileName = in.nextLine();
        File lecturesFile = new File(fileName);
        
        while(!lecturesFile.exists()){
            System.out.print("Invalid file name! Try again: ");
            fileName = in.nextLine();    
            lecturesFile = new File(fileName);
        }
        in.close();
        return lecturesFile;
    }

    /**
     * Parses the input file and outputs an arraylist of all the Lecture objects created from the file
     * @param lecturesFile file containing list of lectures in format (name, startTime, endTime)
     * @return ArrayList of Lecture objects
     * @throws Exception if lecturesFile does not excist (already checked in promptFile)
     */
    public static ArrayList<ArrayList<Integer>> parseFile(File numbersFile) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(numbersFile)); 
        ArrayList<ArrayList<Integer>> out = new ArrayList<ArrayList<Integer>>();
        String line; 
        while ((line = br.readLine()) != null)  {
            ArrayList<Integer> entry = new ArrayList<Integer>();
            String[] numbers = line.split(" ");
            for (String number : numbers){
                entry.add(Integer.parseInt(number)); 
            }
            out.add(entry);
        } 
        br.close();
        return out;
    }
}