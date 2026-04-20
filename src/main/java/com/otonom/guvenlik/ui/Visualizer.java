package com.otonom.guvenlik.ui;

import com.otonom.guvenlik.model.Boundary;
import com.otonom.guvenlik.model.Point;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Visualizer extends JPanel {
    private final List<Point> points;
    private final Boundary boundary;

    public Visualizer(List<Point> points, Boundary boundary) {
        this.points = points;
        this.boundary = boundary;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Daha pürüzsüz çizim

        int scale = 30; // Noktaları biraz daha yaklaştır
        int offsetX = 100; // Soldan boşluk
        int offsetY = 400; // Yukarıdan daha fazla boşluk (noktaları aşağı kaydırır)

        // Eksenleri Çiz (Gri Renk)
        g2.setColor(Color.LIGHT_GRAY);
        g2.drawLine(0, offsetY, 800, offsetY); // X ekseni
        g2.drawLine(offsetX, 0, offsetX, 600); // Y ekseni

        // Noktaları Çiz
        for (Point p : points) {
            g2.setColor(p.getLabel() == 1 ? Color.RED : Color.BLUE);
            int px = (int) (p.getX() * scale) + offsetX;
            int py = offsetY - (int) (p.getY() * scale); // Y eksenini yukarı çevirir
            g2.fillOval(px - 6, py - 6, 12, 12); // Noktaları biraz büyüt
        }

        // Güvenlik Sınırı
        g2.setColor(new Color(34, 139, 34)); // Koyu yeşil
        g2.setStroke(new BasicStroke(3)); // Çizgiyi kalınlaştır

        double xStart = 0;
        double xEnd = 15;
        int x1_scr = (int) (xStart * scale) + offsetX;
        int y1_scr = offsetY - (int) (boundary.calculateY(xStart) * scale);
        int x2_scr = (int) (xEnd * scale) + offsetX;
        int y2_scr = offsetY - (int) (boundary.calculateY(xEnd) * scale);

        g2.drawLine(x1_scr, y1_scr, x2_scr, y2_scr);
    }
}