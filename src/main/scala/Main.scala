object Main extends App {
  var fact = new Factorial();
  println("- Factorial is: " + fact.getFactorial(10));

  var palind = new Palindrome();
  println("- Text is Palindrome? " + palind.isPalindrome("anna"));

  var stringCode = new StringCode();
  var encode = stringCode.encode("aaaaaaaaaabbbaxxxxyyyzyx");
  println("- String encode: " + encode);
  println("- String decode: " + stringCode.decode(encode));

  var composition = new Composition();
  println("- Composition: " + composition.compose(6));

}