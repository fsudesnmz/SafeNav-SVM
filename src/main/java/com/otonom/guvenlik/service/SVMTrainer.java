package com.otonom.guvenlik.service;

import com.otonom.guvenlik.model.Boundary;
import com.otonom.guvenlik.model.Point;
import java.util.List;

/**
 * Support Vector Machine (SVM) mantığıyla en güvenli sınırı hesaplar.
 */
public class SVMTrainer {
    private final double learningRate;
    private final double lambda; // Regularization parametresi (Marjin genişliği için)
    private final int epochs;

    public SVMTrainer(double learningRate, double lambda, int epochs) {
        this.learningRate = learningRate;
        this.lambda = lambda;
        this.epochs = epochs;
    }

    public Boundary train(List<Point> points) {
        double w1 = 0, w2 = 0, b = 0;

        for (int i = 0; i < epochs; i++) {
            for (Point p : points) {
                // Karar fonksiyonu: label * (w.x + b)
                double decision = p.getLabel() * (w1 * p.getX() + w2 * p.getY() + b);

                if (decision >= 1) {
                    // Sınırın dışında, marjin korunuyor. Sadece ağırlıkları küçült (Max Margin için)
                    w1 -= learningRate * (2 * lambda * w1);
                    w2 -= learningRate * (2 * lambda * w2);
                } else {
                    // Sınır ihlali veya marjin içinde. Katsayıları güncelle.
                    w1 -= learningRate * (2 * lambda * w1 - p.getX() * p.getLabel());
                    w2 -= learningRate * (2 * lambda * w2 - p.getY() * p.getLabel());
                    b -= learningRate * (-p.getLabel());
                }
            }
        }
        return new Boundary(w1, w2, b);
    }
}