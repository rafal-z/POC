package com.pl.poc.algorithm;

import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.List;

/**
 * Created by Rafał on 2017-08-19.
 */
public class MinimumFilterAlgorithms extends NonlinearFilters {
    public BufferedImage execute(BufferedImage srcImage, int[] mat){
        ElementFromList elementFromList = new ElementFromList() {
            @Override
            public int execute(List<Integer> list) {
                return Collections.min(list);
            }
        };

        return super.execute(srcImage, mat, elementFromList);
    }
}
