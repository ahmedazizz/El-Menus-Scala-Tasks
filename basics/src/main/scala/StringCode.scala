import scala.collection.mutable.ListBuffer

class StringCode() {
    def runLengthEncode(s: String): String = {  
        var convert: String = "";

        return convert;
    }

    def runLengthDecode(s: String): String = {  
        var convert: String = "";
        

        return convert;
    }

    def encode(str: String): String = {
        var result: String = "";
        def s(x : String) : List[String] = if(x.size == 0) Nil else {
            val (l,r) = x.span(_ == x(0))
            l :: s(r) 
        }

        var stringList = s(str);
        for(x <- stringList) {
            result += x.charAt(0) + "" + x.length;
        }
        return result;
    }

    def decode(str: String): String = {
        var result: String = "";
        var sNumber: String = "";
        var cChar: Char = '.';
        for((c, i) <- str.zip(Stream from 1)) {
            if(c.isDigit) {
                sNumber += c;
                if(i == str.length) {
                    if(sNumber != "") {
                        for( a <- 1 to sNumber.toInt){
                            result += cChar;
                        }
                    }
                }
            } else {
                if(sNumber != "") {
                    for( a <- 1 to sNumber.toInt){
                        result += cChar;
                    }
                }
                cChar = c;
                sNumber = "";
            }
        }
        return result;
    }
}