import java.io.*;

/**
 * takes in -f for file location and -k for many hex in each line.
 * abra.txt
 *  ABRACADABRA! 🤢
 *  expected output: 0041 0042 0052 0041 0043 0041 0044 0041 0042 0052 0041 0021 0020 d83e dd22
 */
public class HexDump {

    public HexDump(File file, int numberOfDigits){
        if(!file.exists()){
            System.err.println("File does not exist, exiting now.");
        }

        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            int ch;
            int count = 1;

            while((ch = br.read()) != -1){

                System.out.print(String.format("%04x ", ch));

                if(count == numberOfDigits){
                    count = 0;
                    System.out.println();
                }
                count++;
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printHelp(){
        System.out.println("Takes in two arguemtns -f for path to file, and -k for number of hexs per line.");
        System.exit(1);
    }

    public static void main(String[] args){
        File file = new File("C:\\Users\\vjwil\\Desktop\\abra.txt");
        int k = 16;

        for(int i = 0; i < args.length; i++){
            String currentString = args[i];
            switch (currentString){
                case "help", "-h":
                    printHelp();
                    break;
                case "-f":
                    i++;
                    file = new File(args[i]);
                    break;
                case "-k":
                    i++;
                    k = Integer.parseInt(args[i]);
                    break;
                default:
                    System.out.println("unkown token " + currentString);
            }
        }
        new HexDump(file, k);
    }
}
