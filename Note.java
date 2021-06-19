package sample;

public class Note {
    private String title;
    private String content;
    private int decrypt;

    public Note(String title, String content){
        this.title = title;
        this.content = content;
    }

    public String getTitle(){
        return this.title;
    }

    public String getContent(){
        return this.content;
    }

    public void setDecrypt(int d){
        this.decrypt = d;
    }

    public void setContent(String s){
        this.content = s;
    }

    public int getDecrypt(){
        return this.decrypt;
    }

    @Override
    public String toString(){
        return String.format(this.title+" "+this.content);
    }

}
