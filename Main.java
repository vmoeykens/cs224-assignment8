/**
 * @author Vincent Moeykens
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

class Main {
    public static void main(String[] args) throws Exception {
        // Get input file
        File inputFile = promptFile();
        try {
            // Get the input lines
            ArrayList<ArrayList<Integer>> entries = parseFile(inputFile);
            for (ArrayList<Integer> numbers : entries) {
                // For each line run the algorithm on the input list and display the output
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
     * Run the sort and count recursive algorithm 
     * @param L List and current count of inversions to be sorted
     * @return Sorted list and number of inversions counted
     */
    public static ListAndCount sortAndCount(ListAndCount L) {
        if (L.size() == 1) {
            return new ListAndCount(L.list, 0);
        }
        int middle = (int) Math.ceil(L.size() / 2);
        ArrayList<Integer> subListA = new ArrayList<Integer>(L.getList().subList(0, middle));
        ArrayList<Integer> subListB = new ArrayList<Integer>(L.getList().subList(middle, L.size()));

        ListAndCount A = sortAndCount(new ListAndCount(subListA, L.getCount()));
        ListAndCount B = sortAndCount(new ListAndCount(subListB, L.getCount()));

        ListAndCount out = mergeAndCount(A.getList(), B.getList());
        return new ListAndCount(out.getList(), A.getCount() + B.getCount() + out.getCount());
    }

    /**
     * Run the merge and count method
     * @param A List of integers on the A side to be merged
     * @param B List of integers on the B side to be merged
     * @return ListAndCount continaing a fully sorted list of integers and the count of inversions
     */
    public static ListAndCount mergeAndCount(ArrayList<Integer> A, ArrayList<Integer> B) {
        int count = 0;
        int aPointer = 0;
        int bPointer = 0;
        ArrayList<Integer> output = new ArrayList<Integer>();
        while (aPointer < A.size() && bPointer < B.size()) {
            if (A.get(aPointer) > B.get(bPointer)) {
                output.add(B.get(bPointer));
                count += A.size() - aPointer;
                bPointer += 1;
            } else {
                output.add(A.get(aPointer));
                aPointer += 1;
            }
        }
        if (aPointer == A.size()) {
            while (bPointer < B.size()) {
                output.add(B.get(bPointer));
                bPointer += 1;
            }
        } else {
            while (aPointer < A.size()) {
                output.add(A.get(aPointer));
                aPointer += 1;
            }
        }
        return new ListAndCount(output, count); // PAGE 224 in TEXTBOOK
    }
}

/**
 * A class to hold a list of integers and the current count of inversions
 */
class ListAndCount {
    ArrayList<Integer> list;
    int count;

    // Constructors
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

    public int size() {
        return this.list.size();
    }

    // toString 
    @Override
    public String toString() {
        String sortedList = "";
        for (Integer item : getList()) {
            sortedList += (" " + item);
        }
        return "Inversion count: " + getCount() + ". " +
            "Sorted array:" + sortedList + ".";
    }

}