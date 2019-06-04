public class ReserveAWord {
   public static void main (String [] args) {
   // reverse order of args
      String[] reversed_args = reverse(args);
      for (int i = 0; i < reversed_args.length; i++) {
         System.out.print(reversed_args[i] + " ");
      }
      System.out.println();
   }
   //return new array with elements reversed
   public static String[] reverse(String[] words){
      String[] reversed = new String[words.length];
      for (int i = 0; i < words.length; i++) {
         reversed[i] = words[words.length - i - 1];
      } 
      return reversed;
   }
  
}
   
