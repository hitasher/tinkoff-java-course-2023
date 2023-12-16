package edu.project4.model;

import java.awt.Color;

public class Pixel {
    private int r;
    private int g;
    private int b;
    private int hitCount;
    double normal;

    Pixel(int r, int g, int b, int hitCount, double normal) {
        setRGB(r, g, b);
        this.hitCount = hitCount;
        this.normal = normal;
    }

    public void setRGB(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public void recordHit(Color color) {
        if (hitCount == 0) {
            setRGB(color.getRed(), color.getGreen(), color.getBlue());
        } else {
            setRGB((r + color.getRed()) / 2, (g + color.getGreen()) / 2, (b + color.getBlue()) / 2);
        }
        ++hitCount;
    }

    public void setNormal(double normal) {
        this.normal = normal;
    }

    public int r() {
        return r;
    }

    public int g() {
        return g;
    }

    public int b() {
        return b;
    }

    public int hitCount() {
        return hitCount;
    }

    public double normal() {
        return normal;
    }
}
