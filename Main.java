import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

class Main {
    public static void main(String[] args) throws Exception {
        File inputFile = promptFile();
        try {
            ArrayList<ArrayList<Integer>> entries = parseFile(inputFile);
            for (ArrayList<Integer> numbers : entries) {
                ListAndCount temp = new ListAndCount(numbers, 0);
                System.out.println(sortAndCount(temp));
            }
        } catch (Exception e) {
            System.out.println(e);
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
     * @param numbersFile file containing list of integers where a new line is a new list of integers
     * @return ArrayList of ArrayList of integers
     * @throws Exception if numbersFile does not exist (already checked in promptFile)
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

    /**
     * 
     * @param L
     * @return
     */
    public static ListAndCount sortAndCount(ListAndCount L) {
        return L;
    }
}

class ListAndCount {
    ArrayList<Integer> list;
    int count;


    public ListAndCount() {
    }

    public ListAndCount(ArrayList<Integer> list, int count) {
        this.list = list;
        this.count = count;
    }

    // Getters and setters
    public ArrayList<Integer> getList() {
        return this.list;
    }

    public void setList(ArrayList<Integer> list) {
        this.list = list;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    // toString 
    @Override
    public String toString() {
        return "Inversion count: " + getCount() + ". " +
            "Sorted array: " + getList() + ".";
    }

}