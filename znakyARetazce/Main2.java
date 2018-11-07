package znakyARetazce;

public class Main2 {

    public static void main(String[] args) {

        ReadFromKeyboard vstup = new ReadFromKeyboard();
        String textToParse = vstup.readFromKeyboard("Type some text here: ");
        TextParser parser = new TextParser();

        parser.convertToHtml(textToParse);

    }
}
