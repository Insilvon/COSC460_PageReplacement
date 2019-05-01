import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main class which reads the input file and begins the program.
 */
public class setup {
    /**
     * Main method. Asks for a filename and a number of pageFrames, then will run the PageReplacement
     * algorithms FIFO and LRU.
     * @param args - filename to run, number of pageFrames
     */
    public static void main(String[] args) {
        int pageFrames = 0;
        String filename = "";

        if(args.length==2) {
            filename = args[0];
            pageFrames = Integer.parseInt(args[1]);
        } else {
            System.out.println("Please specify your arguments!");
            System.out.println("<filename> <number of page frames>");
            System.exit(0);
        }

        ArrayList<String[]> inputData = new ArrayList<>();

        File in = new File(filename);
        try {
            Scanner read = new Scanner(in);
            while(read.hasNextLine()){
                String line = read.nextLine();
                //Are we handling decimals again?
                String[] values = line.split(" ");
                    inputData.add(values);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int fifoTotal=0;
        int lruTotal=0;
        int matchTotal = 0;
        int total = 0;
        // Loop through all lines of the input file
        for (String[] currentLine : inputData){
            System.out.println("================================================================");
            //Print out the numbers being read
            StringBuilder temp = new StringBuilder();
            for(String item : currentLine) temp.append(item+" ");
            System.out.println(temp.toString());
            //create your algorithms
            FIFO fifo = new FIFO(pageFrames);
            LRU lru =  new LRU(pageFrames);
            //run your paging.
            for(String page:currentLine){
                fifo.insert(Integer.parseInt(page));
                //fifo.printBuffer();
                lru.insert(Integer.parseInt(page));
            }
            //print the results
            int fifoFault = fifo.getPageFaultCount();
            int lruFault = lru.getPageFaultCount();
            System.out.println("Total Page Faults for FIFO: "+fifoFault);
            System.out.println("Total Page Faults for LRU: "+lruFault);
            if (fifoFault>lruFault){
                System.out.println("FIFO performed better by: "+(fifoFault-lruFault)+" faults.");
                fifoTotal++;
            }
            else if (lruFault>fifoFault) {
                System.out.println("LRU performed better by: " + (lruFault - fifoFault) + " faults.");
                lruTotal++;
            }
            else{
                System.out.println("Both algorithms performed the same with: "+fifoFault+" faults.");
                matchTotal++;
            }
            total++;
        }
        //Print total stats
        System.out.println("================================================================");
        System.out.println();
        System.out.println("Overall, FIFO performed best "+fifoTotal+"/"+total+" times.");
        System.out.println("LRU performed best "+lruTotal+"/"+total+" times.");
        System.out.println("And both performed the same "+matchTotal+"/"+total+" times.");
    }
}
