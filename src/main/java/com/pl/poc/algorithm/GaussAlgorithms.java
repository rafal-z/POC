package com.pl.poc.algorithm;

import com.pl.poc.model.ImagesModel;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafał on 2017-07-30.
 */
public class GaussAlgorithms {
    private static List<List<Double>> matrixGauss = new ArrayList<List<Double>>();

    public static BufferedImage execute(BufferedImage srcImage, int radius){
        updateMatrixGauss(radius);

        int size = matrixGauss.size();
        double[] matHorizontally = new double[size];
        double[] matVertically = new double[size];

        BufferedImage dstImage = new BufferedImage(srcImage.getWidth(), srcImage.getHeight(), BufferedImage.TYPE_INT_RGB);

        for(int i=0; i<size; i++) {
            matHorizontally[i] = matrixGauss.get(size / 2).get(i);
            matVertically[i] = matrixGauss.get(i).get(size / 2);
        }

        for(int y=0; y<srcImage.getHeight(); y++) {
            for(int x=0; x<srcImage.getWidth(); x++) {
                int rgb = changePixelVertically(y,x,matVertically, srcImage);
                dstImage.setRGB(x,y,rgb);
            }
        }
        srcImage = dstImage;

        for(int y=0; y<srcImage.getHeight(); y++) {
            for(int x=0; x<srcImage.getWidth(); x++) {
                int rgb = changePixelHorizontally(y,x,matHorizontally, srcImage);
                dstImage.setRGB(x,y,rgb);
            }
        }
        return dstImage;
    }

    private static int[] preparatePixelAndSumProduct(int rgb, int[] sum, double[] matrixGauss, int index){
        char[] colors = new char[3];

        colors[0] = (char)ImageAlgorithms.jred(rgb);
        colors[1] = (char)ImageAlgorithms.jgreen(rgb);
        colors[2] = (char)ImageAlgorithms.jblue(rgb);

        for(int k=0; k<3; k++) {
            sum[k] += colors[k] * matrixGauss[index];
        }
        return sum;
    }

    private static int[] normalization(double[] matrixGauss, int[] sum){
        int[] result = new int[3];
        float div=0;
        for(int j=0; j<matrixGauss.length; j++) {
            div += matrixGauss[j];
        }

        for(int k=0; k<3; k++) {
            result[k] = ImageAlgorithms.clamp((int) (sum[k] / div), 0, 255);
        }
        return  result;
    }

    public static int changePixelVertically(int xx, int yy, double[] matrixGauss, BufferedImage srcImage){
        int[] sum = new int[3];
        int size = matrixGauss.length;
        int height = srcImage.getHeight();

        for(int x=xx-size/2, i=0; x<=xx+size/2; x++, i++) {
            if (x >= 0 && x < height) {
                int rgb = srcImage.getRGB(yy,x);
                sum = preparatePixelAndSumProduct(rgb, sum, matrixGauss, i);
            }
        }
        sum = normalization(matrixGauss, sum);

        return ImageAlgorithms.jrgb(sum[0], sum[1], sum[2]);
    }

    public static int changePixelHorizontally(int xx, int yy, double[] matrixGauss, BufferedImage srcImage){
        int[] sum = new int[3];
        int size = matrixGauss.length;
        int width = srcImage.getWidth();

        for(int x=yy-size/2, i=0; x<=yy+size/2; x++, i++) {
            if (x >= 0 && x < width) {
                int rgb = srcImage.getRGB(x,xx);
                sum = preparatePixelAndSumProduct(rgb, sum, matrixGauss, i);
            }
        }
        sum= normalization(matrixGauss, sum);

        return ImageAlgorithms.jrgb(sum[0], sum[1], sum[2]);
    }

    public static void updateMatrixGauss(int radius){
        int MatrixSize = radius*2+1;
        double sum = 0.0;
        matrixGauss.clear();

        for(int i=0; i<MatrixSize; i++){
            List<Double> row = new ArrayList<Double>();
            matrixGauss.add(row);
        }

        int amp = 2147483647/(MatrixSize*255);
        int deviation = (int)Math.ceil(radius*radius / (2*Math.log(amp)));
        for(int row = 0; row<MatrixSize; row++){
            for(int col=0; col<MatrixSize; col++){
                double x = gaussian(row, radius, deviation) * gaussian(col, radius, deviation);
                matrixGauss.get(row).add(x);
                sum += x;
            }
        }

        for(int row = 0; row<MatrixSize; row++) {
            for (int col = 0; col<MatrixSize; col++) {
                double x = matrixGauss.get(row).get(col);
                matrixGauss.get(row).add(x/sum);
            }
        }
    }

    public static double gaussian(double x, double radius, double deviation){
        return Math.exp( -(((x-radius)/deviation)*((x-radius)/deviation))/2.0);
    }
}
