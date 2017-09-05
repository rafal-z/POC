package com.pl.poc.algorithm;

import java.awt.image.BufferedImage;

import static com.pl.poc.algorithm.ImageAlgorithms.jblue;
import static com.pl.poc.algorithm.ImageAlgorithms.jgreen;
import static com.pl.poc.algorithm.ImageAlgorithms.jred;

/**
 * Created by Rafał on 2017-09-02.
 */
public class BilateralFilterAlgorithm {
    private int matrixSize;
    private float gaussianMatrix[][];
    private float intensityMatrix[];

    public BufferedImage execute(BufferedImage srcImage, float distanceSigma, float intensitySigma) {
        BufferedImage resultImage = new BufferedImage(srcImage.getWidth(), srcImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        matrixSize = (int) Math.floor(6 * distanceSigma) + 1;
        makeGaussianMatrix(distanceSigma);
        makeIntensityMatrix(intensitySigma);

        for (int x = 0; x < srcImage.getWidth(); x++) {
            for (int y = 0; y < srcImage.getHeight(); y++) {
                float sumRed = 0;
                float sumGreen = 0;
                float sumBlue = 0;
                float sumWeight = 0;

                int halfSize = (int) Math.floor(matrixSize / 2);
                int currentPixel = srcImage.getRGB(x, y);

                for (int i = x - halfSize; i < x + halfSize; i++) {
                    for (int j = y - halfSize; j < y + halfSize; j++) {
                        if (i >= 0 && j >= 0 && i < srcImage.getWidth() && j < srcImage.getHeight()) {
                            float weight;
                            int intensity = srcImage.getRGB(i, j);

                            weight = gaussianMatrix[x - i + halfSize][y - j + halfSize] *
                                    intensityMatrix[getDistance(currentPixel, intensity)];

                            sumRed += weight * jred(intensity);
                            sumGreen += weight * jgreen(intensity);
                            sumBlue += weight * jblue(intensity);
                            sumWeight += weight;
                        }
                    }
                }

                resultImage.setRGB(x, y, 0xFF000000 | (((int) (sumRed / sumWeight) & 0xFF) << 16) |
                        (((int) (sumGreen / sumWeight) & 0xFF) << 8) |
                        ((int) (sumBlue / sumWeight) & 0xFF));
            }
        }

        return resultImage;
    }

    private void makeGaussianMatrix(float distanceSigma) {
        gaussianMatrix = new float[matrixSize][matrixSize];
        int halfSize = (int) Math.floor(matrixSize / 2);

        for (int i = -halfSize; i < halfSize + 1; i++) {
            for (int j = -halfSize; j < halfSize + 1; j++) {
                float x = gaussian(i, j, distanceSigma);
                gaussianMatrix[i + halfSize][j + halfSize] = x;
            }
        }
    }

    private void makeIntensityMatrix(float intensitySigma) {
        intensityMatrix = new float[442];

        for (int i = 0; i < intensityMatrix.length; i++) {
            intensityMatrix[i] = (float) Math.exp(-((i) / (2 * intensitySigma * intensitySigma)));
        }
    }

    private int getDistance(int first, int second) {
        int redDistance = (second >> 16 & 0xFF) - jred(first);
        int greenDistance = (second >> 8 & 0xFF) - jgreen(first);
        int blueDifference = (second & 0xFF) - jblue(first);

        return (int) (Math.sqrt(redDistance * redDistance + greenDistance * greenDistance +
                blueDifference * blueDifference));
    }

    private float gaussian(int x, int y, float distanceSigma) {
        return (float) Math.exp(-(x * x + y * y) / (2 * distanceSigma * distanceSigma));
    }
}
