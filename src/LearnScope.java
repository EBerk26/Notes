public class LearnScope {
    String pet = "dog";

    public static void main(String[] args) {
        new LearnScope();
    }

    LearnScope(){
        System.out.println("1) "+pet);
        String pet = "iguana";
        System.out.println("2) "+pet);
        butIWantADog();
        TaylorSwiftHousehold();
    }
    void butIWantADog(){
        System.out.println("You have a "+pet);
    }
    void TaylorSwiftHousehold(){
        pet = "cat";
        System.out.println("Taylor Swift has a "+pet);
    }
    void ElvisHousehold(String pet){
        this.pet = "monkey";
        pet = this.pet;
        System.out.println("Elvis has a "+pet);
    }
}