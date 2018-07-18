package com.pl.poc.algorithm;

import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.List;

/**
 * Created by Rafał on 2017-08-21.
 */
public class MaximumFilterAlgorithms extends NonlinearFilters {
    public BufferedImage execute(BufferedImage srcImage, int[] mat) {
        ElementFromList elementFromList = new ElementFromList() {
            @Override
            public int execute(List<Integer> list) {
                return Collections.max(list);
            }
        };

        //to wyżej można zastąpić lambdą
        //ElementFromList elementFromList = list -> Collections.max(list);

        return super.execute(srcImage, mat, elementFromList);
    }
}
