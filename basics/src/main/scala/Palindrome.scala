class Palindrome() {
    def isPalindrome(s: String): Boolean = {  
        if(s == s.reverse) {
            return true;
        } else {
            return false;
        }
   }
}