package edu.exercise.fraction;

public class Fraction {
    private Integer numerator;
    private Integer denominator;

    public Fraction ( Integer numerator, Integer denominator ) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

   public Fraction add ( Integer numerator, Integer denominator) {
        return new Fraction((this.numerator * denominator + numerator * this.denominator), this.denominator * denominator);
    }

    public String toString() {
        return this.numerator + "/" + this.denominator;
    }
}
