public class Token{
    String name;
    int type;
    int line;
    public Token(String name,int type, int line){
        this.name=name;
        this.type=type;
        this.line=line;
    }
    public String getName(){
        return name;
    }
    public int getType(){
        return type;
    }
    public int getline(){
        return line;
    }
}