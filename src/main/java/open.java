import java.io.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.NoSuchElementException;
import javax.swing.JFileChooser;

class open{
    ObjectInputStream input;

    public open(){
    }

    public File openFile(){
        File sf = null;
        JFileChooser FC = new JFileChooser();
        int returnValue = FC.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            sf = FC.getSelectedFile();
        }
        return sf;
    }

    public String getContents(File f){
        StringBuilder contents = new StringBuilder();
        try{
            BufferedReader input =  new BufferedReader(new FileReader(f));
            try{
                String line = null;
                while(( line = input.readLine()) != null){
                    contents.append(line);
                    contents.append(System.getProperty("line.separator"));
                }
            }
            finally{
                input.close();
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return contents.toString();
    }
}