class ReturnTypes {
    /*
    void - doesn't return anything - just runs code
    between public/private and name of method goes return type

    Public vs Private
    public - can be used in a different class
    private methods can be used in that class only

    static
    can be used without created an object

     */
    public static void main(String[] args) {
        new ReturnTypes();
    }
    ReturnTypes(){
        System.out.println(returnPotato());
        System.out.println("I have "+sumOfMyPotatoes(14,32)+" potatoes");
        System.out.println("After we ate our potatoes we have "+remainder(sumOfMyPotatoes(14,32),10)+" potatoes left");
    }

    String returnPotato(){
        return("Potato");
    }

    int sumOfMyPotatoes(int numberYukon, int numberRusset){
        return (numberRusset+numberYukon);
    }
    // modulo - percent symbol in code - 5%3 = 5 mod 3 = 2 11%12 = 11 mod 12 = 11
    int remainder(int potatoes, int people){
        return (potatoes%people);
    }
    boolean coinFlip(){
        return Math.random() > 0.5;
    }
}
