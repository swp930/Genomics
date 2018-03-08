public class Match{
  public String match1 = null;
  public String match2 = null;
  public Match(String str1, String str2){
    match1 = str1;
    match2 = str2;
  }

  public String toString(){
    return match1 + " " + match2;
  }
}
