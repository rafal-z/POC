package com.pl.poc.algorithm;

import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.List;

/**
 * Created by Rafał on 2017-08-21.
 */
public class MaximumFilterAlgorithms extends NonlinearFilters {
    public BufferedImage execute(BufferedImage srcImage, int[] mat) {
        Command command = new Command() {
            @Override
            public int runCommand(List<Integer> list) {
                return Collections.max(list);
            }
        };

        return super.execute(srcImage, mat, command);
    }
}
