import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.*;

class Main extends JFrame implements ActionListener{
    Container c;
    JMenuBar mb;
    JMenu about;
    JMenuItem si;
    JButton select , clear;

    JTable t;
    JPanel p1;
    Object r[][];
    String[] line;
    String[] kws = {"program"
             ,"integer"
             ,"boolean"
             ,"begin"
             ,"end"
             ,"procedure"
             ,"if"
             ,"then"
             ,"else"
             ,"for"
             ,"true"
             ,"false"
             ,"var"
             ,":"
             ,"do"
             ,"read"
             ,"write"
             ,"mod"
             ,"div"
             ,"and"
             ,"or"
             ,"not"
             ,">"
             ,"<"
             ,">="
             ,"<="
             ,"="
             ,"+"
             ,"*"
             ,"<>"
             ,":="
             ,";"
             ,","
             ,".."
             ,"array"
             ,"of"
             ,"."
             ,"(" , ")"
             ,"]"
             ,"["
             ,"-"
             ,"/"};
    int n = 0;
    String file;

    public Main(){
        super("pascalCompiler");

        c = getContentPane();
        c.setLayout(null);


        p1 = new JPanel();
        p1.setLayout(new BorderLayout());
        p1.setBounds(50 , 80 , 410 , 295);

        r = new Object[100][4];

        select = new JButton("Select Code");
        select.setBounds(120 , 400 , 120 , 40);

        clear = new JButton("Clear Table");
        clear.setBounds(260 , 400 , 120 , 40);


        String title[]={"Token No." , "Name" , "Token Type" , "Line Number"};
        t = new JTable(r , title);

        JTableHeader th = t.getTableHeader();
        th.setBackground(Color.gray);

        JScrollPane sp = new JScrollPane(t);
        p1.add(sp);

        select.addActionListener(this);
        c.add(select);

        clear.addActionListener(this);
        c.add(clear);

        c.add(p1);
        p1.setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500 , 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){

        if(e.getSource() == select){
            open op = new open();
            file = op.getContents(op.openFile());
            line = file.split("\\n");
            for(int i = 0 ; i < line.length ; i++){
                getToken(line[i] , i + 1);
            }
        }

        else if(e.getSource() == clear){

            for(int i = 0 ; i <= 99 ; i++)
                for(int j = 0 ; j <= 3 ; j++)
                    r[i][j] = "";
            p1.revalidate();
            p1.repaint();
            n = 0;
        }
    }

    public void getToken(String s , int i){
        String s1 = "";
        boolean b = true;
        s = s.toLowerCase();
        for(int i1 = 0 ; i1 < s.length(); i1++){
            if(Character.isLetterOrDigit(s.charAt(i1))){
                s1 += s.charAt(i1);
            }
            else{

                if(Arrays.asList(kws).contains(s1)){

                    r[n][0] = n;
                    r[n][1] = s1;
                    r[n][2] = "Keyword";
                    r[n][3] = i;
                    n++;
                    p1.repaint();
                }
                else if(!s1.equals("")){
                    try{
                        Integer.parseInt(s1);
                        r[n][0] = n;
                        r[n][1] = s1;
                        r[n][2] = "Number";
                        r[n][3] = i;
                        n++;
                        p1.repaint();
                    }catch(NumberFormatException ex){
                        r[n][0] = n;
                        r[n][1] = s1;
                        r[n][2] = "Identifier";
                        r[n][3] = i;
                        n++;
                        p1.repaint();
                    }
                }

                if(Arrays.asList(kws).contains("" + s.charAt(i1))){
                    r[n][0] = n;
                    r[n][1] = s.charAt(i1);
                    r[n][2] = "KeyWord";
                    r[n][3] = i;
                    n++;
                    p1.repaint();
                }
                s1 = "";
            }
        }
    }


    public static void main (String[] args) {
        new Main();
    }
}