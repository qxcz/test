package znakyARetazce;

import java.util.ArrayList;

public class TextParser {



    public void countCharOccurence( String textToParse, char c)
    {
        char charsToParse[] = textToParse.toCharArray();
        int counter = 0;

        if (c == '*') { counter = textToParse.length();}
        else
         for (int i=0; i < textToParse.length(); i++ )
         {
            if (charsToParse[i] == c) {counter++;}
         }
         System.out.println("Count of \""+ c + "\" is: " + counter);
    }


    public void countWords ( String textToParse )
    {
        char charsToParse[] = textToParse.toCharArray();
        boolean space;
        int counter = 0;
        int capCounter = 0;

        for (int i=0; i < textToParse.length()-1; i++ ){

            if (charsToParse[i] == ' ') {space = true;}
            else space = false;
            if (charsToParse[i+1] != ' ' && space) {counter++; if(Character.isUpperCase(charsToParse[i+1])){capCounter++;}}
        }
        if (textToParse.charAt(0) != ' '){counter++;}
        if (Character.isUpperCase(charsToParse[0])){capCounter++;}

        System.out.println("Word count: " + counter);
        System.out.println("Count of words starts with uppercase character: " + capCounter);

    }


    public void convertToInitials ( String textToParse) {

        char charsToParse[] = textToParse.toCharArray();
        boolean space;
        ArrayList<Character> chlist = new ArrayList<Character>();

        for (int i=0; i < textToParse.length()-1; i++ )
        {
            if (charsToParse[i] == ' ') {space = true;}
            else space = false;
            if (charsToParse[i+1] != ' ' && space && Character.isUpperCase(charsToParse[i+1]))
                {   chlist.add(charsToParse[i+1]);
                    chlist.add('.');
                }
        }

        if (Character.isUpperCase(charsToParse[0]))
        {chlist.add(0,charsToParse[0]);
            chlist.add(1,'.');
        }

        chlist.remove(chlist.size()-1);
        System.out.println("Initials: " + chlist.toString().replaceAll(",","" ).replace("[","").replace("]","").replaceAll(" ","").trim());

    }


    public void convertToHtml (String textToParse) {

        textToParse=textToParse.replaceAll("\\[code]","<code>");
        textToParse=textToParse.replaceAll("\\[/code]","</code>");
        textToParse=textToParse.replaceAll("\\[header]","<h1>");
        textToParse=textToParse.replaceAll("\\[/header]","</h1>");
        textToParse=textToParse.replaceAll("\\[/link]","/a");
        textToParse=textToParse.replaceAll("\\[link:.*]","< a href = \"www.learn2code.sk\">");


        System.out.println(textToParse);

    }



}
