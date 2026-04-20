package com.otonom.guvenlik;

import com.otonom.guvenlik.model.Boundary;
import com.otonom.guvenlik.model.Point;
import com.otonom.guvenlik.service.SVMTrainer;
import com.otonom.guvenlik.ui.Visualizer;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. Veri Seti Oluştur (Engeller)
        List<Point> dataset = new ArrayList<>();
        // Sınıf 1: Engel Grubu A (Kırmızı - Sol Üst)
        dataset.add(new Point(2, 8, 1));
        dataset.add(new Point(3, 9, 1));
        dataset.add(new Point(2, 10, 1));

         // Sınıf 2: Engel Grubu B (Mavi - Sağ Alt)
        dataset.add(new Point(8, 2, -1));
        dataset.add(new Point(9, 3, -1));
        dataset.add(new Point(10, 2, -1));

        // 2. Algoritmayı Yapılandır ve Çalıştır
        SVMTrainer trainer = new SVMTrainer(0.001, 0.01, 50000);
        Boundary bestBoundary = trainer.train(dataset);

        // 3. Konsola Bilgi Yazdır
        System.out.println("Algoritma Tamamlandı.");
        System.out.printf("Denklem: %.2fx + %.2fy + %.2f = 0\n",
                bestBoundary.getW1(), bestBoundary.getW2(), bestBoundary.getB());

        // 4. Görselleştirme
        JFrame frame = new JFrame("Otonom Araç Güvenlik Koridoru");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Visualizer(dataset, bestBoundary));
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}