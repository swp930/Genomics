import java.io.*;
import java.util.ArrayList;

public class Parse {
  public static void main(String[] args){
      File[] files = new File("C:\\Users\\dpati\\Documents\\Genomics\\test").listFiles();
      showFiles(files);
  }

  public static void showFiles(File[] files) {
    for(File file: files){
      if (file.isDirectory()) {
            showFiles(file.listFiles()); // Calls same method again.
        } else {
            System.out.println("File: " + file.getName());
            printContents(file);
        }
    }
  }

  public static void printContents(File file){
    try(BufferedReader br = new BufferedReader(new FileReader(file)); PrintWriter out = new PrintWriter(new FileWriter("output/output"+file.getName()));) {
      String readLine = null;
      String currLine = null;
      String prevLine = null;
      ArrayList<Match> matchList = new ArrayList<>();
      ArrayList<String> columnList = new ArrayList<>();
      while((readLine = br.readLine()) != null) {
          if(readLine.charAt(0) != '#'){
              if(prevLine == null){
                prevLine = readLine;
              }
              else{
                boolean matches = compare(prevLine, readLine);
                if(matches){
                  matchList.add(new Match("chr1 "+id1, readLine));
                  out.println(new Match(prevLine, readLine));
                }
                prevLine = readLine;
              }
          }
      }
      out.println(matchList);
    }
    catch(Exception e){
      System.err.println(e);
    }
  }

  public static boolean compare(String str1, String str2){
    String[] arr = str1.split("\\t");
    String[] arr2 = str2.split("\\t");
    int num1 = Integer.parseInt(arr[1]), num2 = Integer.parseInt(arr[1]);
    int dist = (int) Math.abs(num1 - num2);
    return dist <= 2 && (str1.contains("missense_variant") || str2.contains("missense_variant"));
  }
}
