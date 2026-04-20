package com.otonom.guvenlik.model;

/**
 * w1*x + w2*y + b = 0 denklemindeki katsayıları tutar.
 */
public class Boundary {
    private final double w1;
    private final double w2;
    private final double b;

    public Boundary(double w1, double w2, double b) {
        this.w1 = w1;
        this.w2 = w2;
        this.b = b;
    }

    public double getW1() { return w1; }
    public double getW2() { return w2; }
    public double getB() { return b; }

    // X verildiğinde doğru üzerindeki Y'yi hesaplar (Görselleştirme için)
    public double calculateY(double x) {
        return (-b - w1 * x) / w2;
    }
}