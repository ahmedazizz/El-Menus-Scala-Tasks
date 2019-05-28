class Composition() {
    def compose(number: Int): Int = {
        return square(inc(6));
    }

    def inc(number: Int): Int = {
        return number + 1;
    }

    def square(number: Int): Int = {
        return scala.math.pow(number, 2).toInt;
    }
}