public class StringParsing {
    public static void main(String[] args) {
        new StringParsing();
    }
    private StringParsing() {
        example1();
        System.out.println();
        String phonebook = "In the ancient phone book you find a number for all four teletubbies. The first number you come across is " +
                "Dipsy's Phone:(476)-892-1130. Dipsy is the green teletubbie. The next phone number you find is for the red one, " +
                "Po, Phone:(241)-557-8943. Next up is Tinky-Winky's Phone:(395)-873-9842. Tinky-Winky has the weirdest name but" +
                "the best color, purple. Finally you find Laa-Laa's Phone:(407)-398-8062. If you need to get a hold of the sun," +
                "you'll have to call her phone:(910)-460-9504. Also, Eli has a phone:(617)-529-4117";
        extractFirstPhoneNumber(phonebook);
        extractSecondPhoneNumber(phonebook);
        System.out.println();
        extractAllPhoneNumbers(phonebook,false);
        System.out.println();
        extractAllPhoneNumbers("My mom (phone: (917)-679-7497) is really awesome. She married my dad (phone: (617)-620-1407), and they made me (phone: (617)-529-4117). I'm here too. I guess.",true);
    }
    private void example1(){
        String word="Unicorn";
        int start = word.indexOf('c');
        System.out.println(start);
        System.out.println(word.substring(start));
        int end = word.indexOf('r')+1; //it doesn't include the end character
        System.out.println(word.substring(start,end));

        System.out.println(word.contains("u")); //false because the U in the word is uppercase
        System.out.println(word.indexOf('u')); //returns negative one because no lowercase u is present
        String lowercaseWord=word.toLowerCase();
        System.out.println(lowercaseWord.indexOf('u'));
    }

    private void extractFirstPhoneNumber(String input){
        System.out.println(input.substring(input.indexOf("Phone:"),input.indexOf("Phone:")+20));
    }
    private void extractSecondPhoneNumber(String input){
        String lowerCaseInput = input.toLowerCase();
        int endOfFirstNumber=lowerCaseInput.indexOf("phone:")+20;
        System.out.println(lowerCaseInput.substring(lowerCaseInput.indexOf("phone:",endOfFirstNumber),lowerCaseInput.indexOf("phone:",endOfFirstNumber)+20));
    }
    private void extractAllPhoneNumbers(String input,boolean spaceAfterColon){
        int extraCharacter = 0;
        if(spaceAfterColon){
             extraCharacter = 1;
        }
        input=input.toLowerCase();
        String unreadPhoneBook = input;
        while(unreadPhoneBook.contains("phone:")){
            System.out.println("P"+unreadPhoneBook.substring(unreadPhoneBook.indexOf("hone:"),unreadPhoneBook.indexOf("hone:")+19+extraCharacter));
            unreadPhoneBook=unreadPhoneBook.substring(unreadPhoneBook.indexOf("phone:")+19+extraCharacter);
        }
    }
}

/*
Methods:
 with a string object at the beginning
 * .indexOf(String s) - find the position of a specific character or substring
 * .indexOf(String s, fromIndex) - find the position of a specific character or substring after a certain point.
 * .substring(beginIndex,int endIndex) - extract a substring based on certain indices
 * .contains(string s) - returns a boolean if it contains a specific sequence
 * .toLowerCase() .totoUpperCase() - transforms the string to all lower case or all caps.
 */
