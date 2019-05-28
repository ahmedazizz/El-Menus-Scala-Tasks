class Factorial() {
    def getFactorial(n: BigInt): BigInt = {  
      if (n <= 1) {
        1
      }  
      else {
        n * getFactorial(n - 1)
      }
   }
}