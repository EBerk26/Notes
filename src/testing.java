public class testing {
    String urlVersionOfPLContinue(String plcontinue){
        int indexOfFirstPipe = plcontinue.indexOf('|');
        int indexOfSecondPipe = plcontinue.indexOf('|',indexOfFirstPipe+1);
        return plcontinue.substring(0,indexOfFirstPipe)+"%7C"+plcontinue.substring(indexOfFirstPipe+1,indexOfSecondPipe)+"%7C"+plcontinue.substring(indexOfSecondPipe+1);
    }

    public testing() {
        System.out.println(urlVersionOfPLContinue("187586|11|Rough_Draft_Studios"));
    }

    public static void main(String[] args) {
        new testing();
    }
}
