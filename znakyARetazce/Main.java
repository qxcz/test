package znakyARetazce;

public class Main {
    public static void main(String[] args) {

        ReadFromKeyboard vstup = new ReadFromKeyboard();
        String textToParse = vstup.readFromKeyboard("Type some text here: ");
        TextParser parser = new TextParser();

        parser.countWords(textToParse);
        parser.countCharOccurence(textToParse,'*');
        parser.countCharOccurence(textToParse,' ');
        parser.countCharOccurence(textToParse,',');
        parser.countCharOccurence(textToParse,'.');
        parser.convertToInitials(textToParse);

    }

}
