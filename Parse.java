import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Parse {

  //added to keep track of samples whos colums 4 and 5 have a larger length of 1
  private static ArrayList<sampleColumnWrapper> columnSamples = new ArrayList<sampleColumnWrapper>();

  public static void main(String[] args){
      //File[] files = new File("C:\\Users\\dpati\\Documents\\Genomics\\test").listFiles();
      File[] files = new File("/Users/cbseuser/ParsingProject/test").listFiles();
      showFiles(files);
  }

  private static void showFiles(File[] files) {
    for(File file: files){
      if (file.isDirectory()) {
            //System.out.println("Directory: " + file.getName());
            showFiles(file.listFiles()); // Calls same method again.
        } else {
            System.out.println("File: " + file.getName());
            printContents(file);
        }
    }
  }

  public static void printContents(File file){

    try (BufferedReader br = new BufferedReader(new FileReader(file)); PrintWriter out = new PrintWriter(new FileWriter("output/output"+file.getName()));) {
      String readLine = null;
      String currLine = null;
      String prevLine = null;
      ArrayList<Match> matchList = new ArrayList<>();
      ArrayList<String> columnList = new ArrayList<>();
      while((readLine = br.readLine()) != null) {
          if(readLine.charAt(0) != '#'){

            //Checking columns four and five, for current line being read is done here   
            CheckColumnsFourAndFive(readLine);
            //proceed to do matching arithmetic
              // if(prevLine == null){
              //   prevLine = readLine;
              // }
              // else{
              //   boolean matches = compare(prevLine, readLine);
              //   if(matches){
              //     matchList.add(new Match(prevLine, readLine));
              //     out.println(new Match(prevLine, readLine));
              //   }
              //   prevLine = readLine;
              // }
          }
      }
      // out.println(matchList);
      out.println(columnSamples);
    }
    catch(Exception e){
      System.err.println(e);
    }
  }

  public static boolean compare(String str1, String str2){
    return true;
  }

  private static void CheckColumnsFourAndFive(String currentLine) { 

    int columnCounter = 0;
    String sampleName = "";
    String sampleID = "";
    String token4 = "";
    String token5 = "";
    String currentToken = "";

    StringTokenizer st = new StringTokenizer(currentLine);

     while (st.hasMoreTokens()) {
      columnCounter++;
      currentToken = st.nextToken();

      if (columnCounter == 1) {
        sampleName = currentToken;
      }
      else if (columnCounter == 2) {
        sampleID = currentToken;
      }

      else if (columnCounter == 4) {
        token4 = currentToken;
      }

      else if (columnCounter == 5) {
        token5 = currentToken;
      }
  }

  int token4Length = token4.length();
  int token5Length = token5.length();
  
  //make note of the sample's name and ID
  if (token4Length != 1 || token5Length != 1) {
    columnSamples.add(new sampleColumnWrapper(sampleName, sampleID));
    }
  }
}
