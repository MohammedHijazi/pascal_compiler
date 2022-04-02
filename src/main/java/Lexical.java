import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;


class Lexical extends JFrame implements ActionListener{
    Container cont;
    JButton select , clear;
    JTable table;
    JPanel panel;
    Object[][] obj;
    String[] line;
    String[] keywords = {
             "PROGRAM"
             ,"INTEGER"
             ,"BOOLEAN"
             ,"BEGIN"
             ,"END"
             ,"PROCEDURE"
             ,"IF"
             ,"THEN"
             ,"ELSE"
             ,"FOR"
             ,"TRUE"
             ,"FALSE"
             ,"VAR"
             ,":"
             ,"DO"
             ,"READ"
             ,"WRITE"
             ,"MOD"
             ,"DIV"
             ,"AND"
             ,"OR"
             ,"NOT"
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
             ,"ARRAY"
             ,"OF"
             ,"."
             ,"(" , ")"
             ,"]"
             ,"["
             ,"-"
             ,"/"};
    int number = 0;
    String file;

    public Lexical(){
        super("pascalCompiler");

        cont = getContentPane();
        cont.setLayout(null);


        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBounds(50 , 80 , 410 , 295);

        obj = new Object[100][3];

        select = new JButton("Select Code");
        select.setBounds(120 , 400 , 120 , 40);

        clear = new JButton("Clear Table");
        clear.setBounds(260 , 400 , 120 , 40);


        String[] title ={ "Name" , "Token Type" , "Line Number"};
        table = new JTable(obj , title);

        JTableHeader th = table.getTableHeader();
        th.setBackground(Color.gray);

        JScrollPane scr = new JScrollPane(table);
        panel.add(scr);

        select.addActionListener(this);
        cont.add(select);

        clear.addActionListener(this);
        cont.add(clear);

        cont.add(panel);
        panel.setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500 , 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){

        if(e.getSource() == select){
            openFile op = new openFile();
            file = op.getContents(op.openFile());
            line = file.split("\\n");
            for(int i = 0 ; i < line.length ; i++){
                getToken(line[i] , i + 1);
            }
        }

        else if(e.getSource() == clear){

            for(int i = 0 ; i <= 99 ; i++)
                for(int j = 0 ; j <= 2 ; j++)
                    obj[i][j] = "";
            panel.revalidate();
            panel.repaint();
            number = 0;
        }
    }

    public void getToken(String s , int i){
        String str = "";
        s = s.toUpperCase();
        for(int i1 = 0 ; i1 < s.length(); i1++){
            if(Character.isLetterOrDigit(s.charAt(i1))){
                str += s.charAt(i1);
            }
            else{
                if(Arrays.asList(keywords).contains(str)){
                    obj[number][0] = str;
                    obj[number][1] = "Keyword";
                    obj[number][2] = i;
                    number++;
                    panel.repaint();
                }
                else if(!str.equals("")){
                    try{
                        Integer.parseInt(str);
                        obj[number][0] = str;
                        obj[number][1] = "Number";
                        obj[number][2] = i;
                        number++;
                        panel.repaint();
                    }catch(NumberFormatException ex){
                        obj[number][0] = str;
                        obj[number][1] = "Identifier";
                        obj[number][2] = i;
                        number++;
                        panel.repaint();
                    }
                }

                if(Arrays.asList(keywords).contains("" + s.charAt(i1))){
                    obj[number][0] = s.charAt(i1);
                    obj[number][1] = "Keyword";
                    obj[number][2] = i;
                    number++;
                    panel.repaint();
                }
                str = "";
            }
        }
    }


    public static void main (String[] args) {
        new Lexical();
    }
}