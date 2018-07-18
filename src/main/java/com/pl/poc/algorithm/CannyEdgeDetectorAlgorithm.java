package com.pl.poc.algorithm;

import java.awt.image.BufferedImage;

import static com.pl.poc.algorithm.ImageAlgorithms.*;

/**
 * Created by Rafał on 2017-09-06.
 */
public class CannyEdgeDetectorAlgorithm {
    private double highThreshold;
    private double lowThreshold;
    private int[][] gradientDirection;
    private int[][] gx;
    private int[][] gy;
    private double[][] directionMask;


    public BufferedImage execute(BufferedImage srcImage, double low, double high, int radius) {
        int[][] blurred;
        BufferedImage resultImage = null;
        highThreshold = high;
        lowThreshold = low;

        if (srcImage != null && low > 0 && high > 0) {
            srcImage = GaussianBlurAlgorithms.execute(srcImage, radius);
            blurred = convertToGray(srcImage);
            gx = Sobel.Horizontal(blurred);
            gy = Sobel.Vertical(blurred);

            gradientMagnitudes();
            direction();
            suppression();

            resultImage = convertToRGB(hysteresis());
        }

        return resultImage;
    }

    public int[][] convertToGray(BufferedImage image) {
        int[][] gray = null;
        int height = image.getHeight();
        int width = image.getWidth();

        if (height > 0 && width > 0) {
            gray = new int[height][width];

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    int rgb = image.getRGB(j, i);
                    long rgbGray = Math.round((jred(rgb) + jgreen(rgb) + jblue(rgb)) / 3.0);
                    gray[i][j] = (int)rgbGray;
                }
            }
        }
        return gray;
    }

    public BufferedImage convertToRGB(int[][] gray) {
        BufferedImage resultImage = null;
        int height = gray.length;
        int width = gray[0].length;

        if (height > 0 && width > 0) {
            resultImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    int rgb = gray[i][j];
                    resultImage.setRGB(j, i, jrgb(rgb,rgb,rgb));
                }
            }
        }
        return resultImage;
    }

    private void gradientMagnitudes() {
        int height = gx.length;
        int width = gx[0].length;
        directionMask = new double[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                directionMask[i][j] = Math.sqrt(gx[i][j] * gx[i][j] + gy[i][j] * gy[i][j]);
            }
        }
    }

    private void direction() {
        int height = gx.length;
        int width = gx[0].length;
        double radian = 180 / Math.PI;
        gradientDirection = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                double angle = Math.atan2(gy[i][j], gx[i][j]) * radian;

                if (angle < 0) {
                    angle += 360.;
                }

                if (angle <= 22.5 || (angle >= 157.5 && angle <= 202.5) || angle >= 337.5) {
                    gradientDirection[i][j] = 0;
                } else if ((angle >= 22.5 && angle <= 67.5) || (angle >= 202.5 && angle <= 247.5)) {
                    gradientDirection[i][j] = 45;
                } else if ((angle >= 67.5 && angle <= 112.5) || (angle >= 247.5 && angle <= 292.5)) {
                    gradientDirection[i][j] = 90;
                } else {
                    gradientDirection[i][j] = 135;
                }
            }
        }
    }

    private void suppression() {
        int height = directionMask.length - 1;
        int width = directionMask[0].length - 1;

        for (int i = 1; i < height; i++) {
            for (int j = 1; j < width; j++) {
                double magnitude = directionMask[i][j];

                switch (gradientDirection[i][j]) {
                    case 0 :
                        if (magnitude < directionMask[i][j - 1] && magnitude < directionMask[i][j + 1]) {
                            directionMask[i - 1][j - 1] = 0;
                        }
                        break;
                    case 45 :
                        if (magnitude < directionMask[i - 1][j + 1] && magnitude < directionMask[i + 1][j - 1]) {
                            directionMask[i - 1][j - 1] = 0;
                        }
                        break;
                    case 90 :
                        if (magnitude < directionMask[i - 1][j] && magnitude < directionMask[i + 1][j]) {
                            directionMask[i - 1][j - 1] = 0;
                        }
                        break;
                    case 135 :
                        if (magnitude < directionMask[i - 1][j - 1] && magnitude < directionMask[i + 1][j + 1]) {
                            directionMask[i - 1][j - 1] = 0;
                        }
                        break;
                }
            }
        }
    }

    private int[][] hysteresis() {
        int height = directionMask.length - 1;
        int width = directionMask[0].length - 1;
        int[][] bin = new int[height - 1][width - 1];

        for (int i = 1; i < height; i++) {
            for (int j = 1; j < width; j++) {
                double magnitude = directionMask[i][j];

                if (magnitude >= highThreshold) {
                    bin[i - 1][j - 1] = 255;
                } else if (magnitude < lowThreshold) {
                    bin[i - 1][j - 1] = 0;
                } else {
                    boolean flag = false;

                    for (int nr = -1; nr < 2; nr++) {
                        for (int nc = -1; nc < 2; nc++) {
                            if (directionMask[i + nr][j + nc] >= highThreshold) {
                                flag = true;
                            }
                        }
                    }
                    bin[i - 1][j - 1] = (flag) ? 255 : 0;
                }
            }
        }
        return bin;
    }


    private static class Sobel {
        private static final int[][] horizontalMask = { {-1, 0, 1}, {-2, 0, 2}, {-1, 0, 1} };
        private static final int[][] verticalMask = { {-1, -2, -1}, {0, 0, 0}, {1, 2, 1} };

        public static int[][] Horizontal(int[][] gray) {
            int[][] result = null;
            int height = gray.length;
            int width = gray[0].length;

            if (height > 2 && width > 2) {
                result = new int[height - 2][width - 2];

                for (int i = 1; i < height - 1; i++) {
                    for (int j = 1; j < width - 1; j++) {
                        int sum = 0;

                        for (int kr = -1; kr < 2; kr++) {
                            for (int kc = -1; kc < 2; kc++) {
                                sum += (horizontalMask[kr + 1][kc + 1] * gray[i + kr][j + kc]);
                            }
                        }
                        result[i - 1][j - 1] = sum;
                    }
                }
            }
            return result;
        }

        public static int[][] Vertical(int[][] gray) {
            int[][] result = null;
            int height = gray.length;
            int width = gray[0].length;

            if (height > 2 || width > 2) {
                result = new int[height - 2][width - 2];

                for (int i = 1; i < height - 1; i++) {
                    for (int j = 1; j < width - 1; j++) {
                        int sum = 0;

                        for (int kr = -1; kr < 2; kr++) {
                            for (int kc = -1; kc < 2; kc++) {
                                sum += (verticalMask[kr + 1][kc + 1] * gray[i + kr][j + kc]);
                            }
                        }
                        result[i - 1][j - 1] = sum;
                    }
                }
            }
            return result;
        }
    }

}
