16
https://raw.githubusercontent.com/Chitturiarunkrishna/Hackerrank30DaysOfCode/master/Day%2021%20-%20Generics.java
class Printer
{
   public static < E > void printArray( E[] inputArray )
   {           
         for ( E element : inputArray ){        
            System.out.println( element );
         }
    }
}
