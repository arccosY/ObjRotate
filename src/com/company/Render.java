package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Render {

//    public static void render(BufferedImage img){
//          img.setRGB(500, 300, new Color(255, 0, 200).getRGB());
//        for (int i = 0; i < img.getWidth(); i++) {
//            for (int j = 0; j < img.getHeight(); j++) {
//                img.setRGB(i, j, new Color(i * j % 256, (i + j) % 256, (i * i + j * j) % 256).getRGB() );
//            }
//        }
//    }

    //Стоит начать с этого
    public static void renderLine(BufferedImage img, double x1, double y1, double x2, double y2, Color color) {
        if ((int) x1 == (int) x2 && (int) y1 == (int) y2){
            img.setRGB((int) x1, (int) y2, color.getRGB());
            return;
        }
        double dx = Math.abs(x2 - x1);
        double dy = Math.abs(y2 - y1);
        for (int x = (int) Math.min(x1, x2); x <= (int) Math.max(x1, x2); x++) {
            for (int y = (int) Math.min(y1, y2); y <= (int) Math.max(y1, y2); y++) {
                if (dx > dy) {
                    img.setRGB(x, (int) (((x - x1) * (y2 - y1) / (x2 - x1)) + y1), color.getRGB());
                } else {
                    img.setRGB((int) (((y - y1) * (x2 - x1) / (y2 - y1)) + x1), y, color.getRGB());
                }
            }
        }
    }




//    public static void renderTriangle(BufferedImage img, int x1, int y1, int x2, int y2, int x3, int y3, Color color){
//       Vector AB = new Vector(new double[]{x2, y2}).sum(new Vector(new double[]{x1, y1}).scMult(-1));
//       Vector AC = new Vector(new double[]{x3, y3}).sum(new Vector(new double[]{x1, y1}).scMult(-1));
//        for (int x = Math.min(x1, Math.min(x2, x3)); x <= Math.max(x1, Math.max(x2, x3)); x++) {
//            for (int y = Math.min(y1, Math.min(y2, y3)); y < Math.max(y1, Math.max(y2, y3)); y++) {
//                Vector PA = new Vector(new double[]{x1, y1}).sum(new Vector(new double[]{x, y}).scMult(-1));
//                Vector V = new Vector(new double[]{AB.get(0), AC.get(0), PA.get(0)}).CrossProd(new Vector(new double[]{AB.get(1), AC.get(1), PA.get(1)}));
//                double u = (V.get(0)/V.get(2));
//                double v = (V.get(1)/V.get(2));
//                if (u + v <= 1 && u >= 0 && v >= 0){
//                    img.setRGB(x, y, new Color((int) (u*255), (int) (v*255), (int) ((1-u-v)*255)).getRGB());
//                }
//            }
//        }
//    }
//        renderLine(img, x1, y1, x2, y2, Color.white);
//        renderLine(img, x2, y2, x3, y3, Color.white);
//        renderLine(img, x3, y3, x1, y1, Color.white);
    public static void renderTr(BufferedImage img, double x1, double y1, double z1, double x2, double y2, double z2, double x3, double y3, double z3, double l1, double l2, double l3, Vector sight, int moveX, int moveY, double[][]  zBuffer){
        Vector AB = new Vector(new double[]{x2, y2, z2}).sum(new Vector(new double[]{x1, y1, z1}).scMult(-1));
        Vector AC = new Vector(new double[]{x3, y3, z3}).sum(new Vector(new double[]{x1, y1, z1}).scMult(-1));
        Vector normal = AB.CrossProd(AC).normalize();
        if (normal.scProd(sight) < 0) return;
        x1 += moveX;
        x2 += moveX;
        x3 += moveX;
        y1 += moveY;
        y2 += moveY;
        y3 += moveY;
        int x = (int) Math.min(x1, Math.min(x2, x3));
            x  = Math.max(x, 0);
//            x =  Math.min(x, Main.w-1);

        for (; x <= Math.max(x1, Math.max(x2, x3)); x++) {
                int y = (int) Math.min(y1, Math.min(y2, y3));
                    y = Math.max(y, 0);
//                    y = Math.min(y, Main.h-1);
            for (; y < Math.max(y1, Math.max(y2, y3)); y++) {
                Vector PA = new Vector(new double[]{x1, y1}).sum(new Vector(new double[]{x, y}).scMult(-1));
                Vector V = new Vector(new double[]{AB.get(0), AC.get(0), PA.get(0)}).CrossProd(new Vector(new double[]{AB.get(1), AC.get(1), PA.get(1)}));
                double u = (V.get(0)/V.get(2));
                double v = (V.get(1)/V.get(2));
                if (u + v <= 1 && u >= 0 && v >= 0){
//                    System.out.println(x +" " + y);
                    img.setRGB(x, y, new Color(99, 96, 97).getRGB());
//                      renderLine(img, x1, y1, x2, y2, Color.white);
//                        renderLine(img, x2, y2, x3, y3, Color.white);
//                    renderLine(img, x3, y3, x1, y1, Color.white);
                }
            }
        }

//        System.out.println("(" + x1 + ", " + y1 + "), " + "(" + x2 + ", " + y2 + "), " + "(" + x3 + ", " + y3 + ")");
    }

}