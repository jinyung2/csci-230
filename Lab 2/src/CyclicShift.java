public class CyclicShift {
    public static void main(String[] args) {
        int n = 2;
        n = n<<1|n>>>1;
        System.out.println(n);
        String test = "\n\n\n";
        System.out.println(hashCode(test));
    }
    static int hashCode(String s){
        int h = 0;
        for (int i = 0; i < s.length();i++){
            h = (h<<5)|(h>>>27);
            h+=(int)s.charAt(i);
        }
        return h;
    }
}
