/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.regex.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class Lexical {

    static String identiferRegularExperssion = "[_a-zA-Z]+";
    static String digit = "[0-9]+";
    
    static String token;
    static String Filename = "C:\\Users\\moham\\Desktop\\pascal_compiler\\src\\main\\java\\sumProgram.txt";
  
    static List keywords = new ArrayList();

    static String cut[];
    static  String tokenCategory;
    static int line=0;
    static String line1;
    static String x;

    public static void main(String[] args) throws IOException {
        keywords.add("program"); keywords.add("integer"); keywords.add("boolean"); keywords.add("begin");
        keywords.add("End");keywords.add("procedure");keywords.add("if");keywords.add("or");keywords.add("then");
        keywords.add("else");keywords.add("for");keywords.add("true");keywords.add("false");keywords.add("do");
        keywords.add("write");keywords.add("mod");keywords.add("div");keywords.add("read");keywords.add("and");
        keywords.add("not");keywords.add("of");keywords.add("or");keywords.add("array");
        keywords.add(">=");keywords.add("<=");keywords.add("<>");keywords.add("*");
        keywords.add(":=");keywords.add(",");keywords.add(";");keywords.add(":");
        keywords.add("..");keywords.add("<");keywords.add(">");keywords.add("(");
        keywords.add(")");keywords.add("[");keywords.add("]");keywords.add("+");keywords.add("-");
        keywords.add("/");keywords.add(".");keywords.add("=");keywords.add("var");
        splitandreplace();
        scaning();
        
    }
    static void splitandreplace() throws FileNotFoundException, IOException{
       
                
        FileReader read=new FileReader(new File(Filename));
        BufferedReader br=new BufferedReader(read);
        
        FileWriter writex=new FileWriter("C:\\Users\\moham\\Desktop\\pascal_compiler\\src\\main\\java\\result.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(writex);
      
        
             while((line1=br.readLine())!=null){
                 int c= line1.indexOf("//");
                 if (c!=-1){
                   line1=line1.substring(0, c);
                 }
                   cut=line1.split("");
               for(int i=0;i<keywords.size();++i){
                   for (int j = 0; j < cut.length; j++) {
                      if(keywords.get(i).equals(cut[j])){ 
                        if(cut[j].equals(":")){
                           if(cut[j+1].equals("=")){
                            cut[j]=" "+cut[j];
                           }else{
                        cut[j]=" "+cut[j]+" ";
                           }
                           
                        }else{
                        cut[j]=" "+cut[j]+" ";
                           }
                  
                }
               }
              }
                x=String.join("", cut);
                bufferedWriter.write(x+"\n");
                }
                br.close();
                bufferedWriter.close();
    }
      static void scaning() throws FileNotFoundException, IOException{
               FileReader readx=new FileReader("C:\\Users\\moham\\Desktop\\pascal_compiler\\src\\main\\java\\result.txt");
               BufferedReader brx=new BufferedReader(readx);
               
               while((line1=brx.readLine())!=null){
                   ++line;
                   if(line1.isEmpty()){
                   continue;
                   }                
                   cut=line1.split(" ");
                   for(int i=0; i<cut.length;++i){
                  token=cut[i];
                  

                 if((token.equals(":")) && (cut[i+1].equals("="))){
                        check(":=");
                        ++i;
                  }else
                  check(token);
                 
                }
                   
               }
     }   
 static void check(String tokens){

         if (keywords.contains(tokens)) {
                    tokenCategory = "keyword";
                    JOptionPane.showMessageDialog(null, "<In Line: " + line +" There's is: "+ tokens.toUpperCase() + 
                            "  And it's type is: " + tokenCategory + ">");
                } else if (Pattern.matches(identiferRegularExperssion, tokens)) {
                    tokenCategory = "identifire";
                    JOptionPane.showMessageDialog(null, "<In Line: " + line +" There's is: "+ tokens.toUpperCase() +
                            "  And it's type is: " + tokenCategory + ">");
                } else if (Pattern.matches(digit, tokens)) {
                    tokenCategory = "digit";
                    JOptionPane.showMessageDialog(null, "<In Line: " + line +" There's is: "+ 
                            tokens.toUpperCase() + "  And it's type is: " + tokenCategory + ">");
                }else {
                    tokenCategory = "there an error in token !!! in line :" + line;
                }
                System.out.println("<In Line: " + line +" There's is: "+ tokens.toUpperCase() + "  And it's type is: " + tokenCategory + ">");;

     }
     
}

        
        
    
    
    

