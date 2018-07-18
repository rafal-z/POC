# Digital Image Processing

The program was written in Java with the use of Swing. The following algorithms have been implemented: 
* [Gaussian Blur Filter](#gaussian-blur-filter)
* [Unsharp Mask Filter](#unsharp-mask-filter)
* [Canny Edge Detector](#canny-edge-detector)
* Morphological Operations:
     * [Skeletonization](#skeletonization)
     * Contour Detection
* Nonlinear Filters:
    * Minimum Filter
    * Maximum Filter
    * Median Filter
    * Opening Filter
    * Closing Filter
    * [Adaptive Median Filter](#adaptive-median-filter)
    * [Bilateral Filter](#bilateral-filter)
    
### Gaussian Blur Filter

In the following pictures the result of this filter is shown and the radius size is set to 14.

##### Original image
![orginal_image](https://user-images.githubusercontent.com/23417074/42897278-caa29bcc-8abf-11e8-875b-8dc8aa9964d9.jpg)

##### Result image
![gaussian_blur](https://user-images.githubusercontent.com/23417074/42898857-a551767c-8ac4-11e8-889e-84ee1869be65.jpg)

### Unsharp Mask Filter

##### Result image

```
FILTER PARAMETER:
radius = 5
unsharp mask value = 4
```
![unsharp_image](https://user-images.githubusercontent.com/23417074/42899883-a452214c-8ac7-11e8-9298-e4b43c972977.jpg)

### Canny Edge Detector

##### Result image

```
FILTER PARAMETER:
low threshold = 100
hight threshold = 120
radius = 2
```
![canny_edge_detector](https://user-images.githubusercontent.com/23417074/42900751-ed0d5f08-8ac9-11e8-8250-17af63eac3c6.png)

### Skeletonization

##### Original image
![skeletonization_orginal](https://user-images.githubusercontent.com/23417074/42901012-b0e38934-8aca-11e8-9cf6-27c50ab82ae5.jpg)
##### Result image
![skeletonization](https://user-images.githubusercontent.com/23417074/42901085-df101638-8aca-11e8-9bae-397b1ef4c806.jpg)

### Adaptive Median Filter

##### Original image
![adaptive_original](https://user-images.githubusercontent.com/23417074/42901763-cd23a4ba-8acc-11e8-8cee-66dbaebe9ca2.jpg)
##### Result image
```
FILTER PARAMETER:
radius min = 0
radius max = 4
```
![adaptive_filter](https://user-images.githubusercontent.com/23417074/42901836-f9b06e00-8acc-11e8-9ef3-11bdcf6b398e.jpg)

### Bilateral Filter

##### Original image
![bilateral_original](https://user-images.githubusercontent.com/23417074/42902255-51a65786-8ace-11e8-9ba2-46cf37458f52.jpg)

##### Result image
```
FILTER PARAMETER:
distance sigma = 2
intensity sigma = 10
```
![bilateral_filter](https://user-images.githubusercontent.com/23417074/42902295-727addba-8ace-11e8-87c9-6491f9e30f24.jpg)