package sample;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    public List<String> queryItems;

    @FXML
    private Label currentNoteContent;

    @FXML
    private Button b1;

    @FXML
    private Button b2;

    @FXML
    private Button b3;

    @FXML
    private Button b4;

    @FXML
    private Button b5;

    @FXML
    private Button b6;

    @FXML
    private Button b7;

    @FXML
    private TextArea newNoteContent;

    @FXML
    private TextField newNoteTitle;

    @FXML
    private CheckBox hashing;

    @FXML
    private TextField cesarArg;

    @FXML
    private Button submitNewNote;

    @FXML
    private TextField decrypt;

    @FXML
    ColorPicker colour = new ColorPicker();

    @FXML
    private TextField searchText;

    @FXML
    private Label searchResults;


    private int lastSelected;

    List<Note> notes = new ArrayList<Note>();
    List<Button> buttons = new ArrayList<Button>();

    @Override
            public void initialize(URL location, ResourceBundle resource){
        newNoteContent.setWrapText(true);
        createDefaultNotes();
        refreshNotes();
        showNote1();
    }

    public void showNote1(){
        setCurrentNoteContent(notes.get(0).getContent());
        lastSelected = 0;
    }

    public void showNote2(){
        setCurrentNoteContent(notes.get(1).getContent());
        lastSelected = 1;
    }

    public void showNote3(){
        setCurrentNoteContent(notes.get(2).getContent());
        lastSelected = 2;

    }

    public void showNote4(){
        setCurrentNoteContent(notes.get(3).getContent());
        lastSelected = 3;
    }

    public void showNote5(){
        setCurrentNoteContent(notes.get(4).getContent());
        lastSelected = 4;
    }


    public void createDefaultNotes(){
        Note n1 = new Note("Matematyka","0239902201832820");
        Note n2 = new Note ("Do zrobienia", "Projekt z POJ");
        Note n3 = new Note("Przepis na nalesniki", "Jeszcze tu zajrze");

        notes.add(n1);
        notes.add(n2);
        notes.add(n3);

        buttons.add(b1);
        buttons.add(b2);
        buttons.add(b3);
        buttons.add(b4);
        buttons.add(b5);
        buttons.add(b6);
        buttons.add(b7);
    }

    public void refreshNotes(){
        for(int i=0;i<notes.size();i++){
            buttons.get(i).setText(notes.get(i).getTitle());
        }
    }

    public void setCurrentNoteContent(String s){
        currentNoteContent.setText(s);
    }

    public static StringBuffer encrypt(String text, int s)
    {
        StringBuffer result= new StringBuffer();

        for (int i=0; i<text.length(); i++)
        {
            if (Character.isUpperCase(text.charAt(i)))
            {
                char ch = (char)(((int)text.charAt(i) +
                        s - 65) % 26 + 65);
                result.append(ch);
            }
            else
            {
                char ch = (char)(((int)text.charAt(i) +
                        s - 97) % 26 + 97);
                result.append(ch);
            }
        }
        return result;
    }

    public static StringBuffer decrypt(String text, int s){
        StringBuffer decrypted = encrypt(text, 26 - s);
        return decrypted;
    }

    public void decryptFinal(){
        String coef = decrypt.getText();
        int coefConverted = Integer.parseInt(coef);
        StringBuffer stringDecrypted = decrypt(notes.get(lastSelected).getContent(), coefConverted);
        String stringConverted = stringDecrypted.toString();
        notes.get(lastSelected).setContent(stringConverted);

    }

    public List<String> search(String query){
        List<String> returnedStrings = new ArrayList<String>();
        String regex = "(.*)"+query+"(.*)";
        for(int i=0;i<buttons.size();i++){
            try {
                System.out.print(query.matches(regex));
                if (notes.get(i).getContent().matches(regex) == true) {

                    String item = notes.get(i).getTitle();
                    System.out.println(item);
                    returnedStrings.add(item);
                }
            }
            catch (Exception e){
                break;
            }
        }
        return returnedStrings;
    }

    public void useSearch(){
        queryItems = search(searchText.getText());
        String finalString = "";
        for(int i=0;i<queryItems.size();i++){
            finalString += queryItems.get(i) + "\n";
        }
        searchResults.setText(finalString);
    }

    public void addNote(){
        String title = newNoteTitle.getText();
        String content = newNoteContent.getText();
        if(title.length() > 1 && content.length() > 1) {
            StringBuffer temp;
            int converted = 0;
            if (hashing.isSelected()) {
                String item = cesarArg.getText();
                converted = Integer.parseInt(item);
                temp = encrypt(content, converted);
                content = temp.toString();
            }

            Note n = new Note(title, content);

            if (hashing.isSelected()) {
                n.setDecrypt(26 - converted);
            }

            notes.add(n);
            int size = notes.size() - 1;
            /*
            Color c = Color.web(colour.getValue().toString(), 1.0);
            System.out.println(c);
            String colourToString = c.toString();
            colourToString = colourToString.replace("0x", "#");
            System.out.println(colourToString);
            buttons.get(size).setStyle("-fx-background-color:"+ colourToString + ";");
            */

            System.out.println(newNoteContent.getText());
            System.out.println(newNoteTitle.getText());
            buttons.get(size).setText(newNoteTitle.getText());
            newNoteContent.setText("");
            newNoteTitle.setText("");
        }
    }

}