//https://howtodoinjava.com/java/io/java-read-file-to-string-examples/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {

    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then read link upto next )
        int currentIndex = 0;

        while(currentIndex < markdown.length()) {
            int openBracket = markdown.indexOf("[", currentIndex);
            if (isEscapeCharacter(markdown, openBracket)){
                openBracket = markdown.indexOf("[", openBracket);
            }
            int closeBracket = markdown.indexOf("]", openBracket);
            System.out.println(closeBracket);
            if (isEscapeCharacter(markdown, closeBracket)){
                closeBracket = markdown.indexOf("]", closeBracket+1);
                System.out.println(closeBracket);
            }
         
            int openParen = markdown.indexOf("(", closeBracket+1);
            System.out.println(openParen);
            if (isEscapeCharacter(markdown, openParen)){
                openParen = markdown.indexOf("(", openParen+1);
                System.out.println(openParen);
            }
            int closeParen = markdown.indexOf(")", openParen);
            if (isEscapeCharacter(markdown, closeParen)){
                closeParen = markdown.indexOf(")", closeParen+1);
            }
            if (openParen + 1 <= closeParen){
                toReturn.add(markdown.substring(openParen + 1, closeParen));
            }
            else{
                break;
            }
            
            currentIndex = closeParen + 1;
        }
        return toReturn;
   
    }

    private static boolean isEscapeCharacter(String md, int index){
        if (index - 1 > -1 && md.charAt(index-1) == '\\'){
            System.out.println("true");
            return true;
        }
        return false;
    }




    public static void main(String[] args) throws IOException {
        Path fileName = Path.of(args[0]);
        String content = Files.readString(fileName);
        ArrayList<String> links = getLinks(content);
	    System.out.println(links);
    }

   
}
