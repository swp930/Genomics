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
            //System.out.println("Directory: " + file.getName());
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
              //out.println(readLine);
              if(prevLine == null){
                prevLine = readLine;
              }
              else{
                boolean matches = compare(prevLine, readLine);
                if(matches){
                  matchList.add(new Match(prevLine, readLine));
                  out.println(new Match(prevLine, readLine));
                }
                prevLine = readLine;
              }
              /*else {
                currLine = readLine;
                boolean matches = compare(prevLine, currLine);
                if(matches){
                  matchList.add(new Match(prevLine, currLine));
                  out.println(matchList);
                }
              }
              if(((int)Math.random()*10)==0){

              }*/
          }
          out.println(matchList);
      }
    }
    catch(Exception e){
      System.err.println(e);
    }
  }

  public static boolean compare(String str1, String str2){
    return true;
  }
}
