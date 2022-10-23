package model;

import java.awt.Color;
import java.util.HashMap;

/**
 * Represents the Image Manipulation Software. Can return information about its state.
 */
public class EvenBetterImageManipulatorModelImpl extends GeneralImageManipulatorModel
        implements EvenBetterImageManipulatorModel {

  /**
   * Constructs an {@code EvenBetterImageManipulatorModel} object. Has a state, as opposed to the
   * BetterImageManipulatorModel implementation.
   */
  public EvenBetterImageManipulatorModelImpl() {
    super();
  }

  /**
   * Constructs an {@code EvenBetterImageManipulatorModel} object with the given HashMap as its
   * files.
   *
   * @param files the hashmap which stores the 2-D Pixel array representation of images.
   * @throws IllegalArgumentException if the files supplied is null.
   */
  public EvenBetterImageManipulatorModelImpl(HashMap<String, Pixel[][]> files)
          throws IllegalArgumentException {
    if (files == null) {
      throw new IllegalArgumentException("Cannot have null fields.");
    }
    this.files = files;

  }

  /**
   * Gets the color of a singular pixel in an image.
   *
   * @param x         the x coordinate of the pixel.
   * @param y         the y coordinate of the pixel.
   * @param imageName the image to grab the color of the pixel from.
   * @return the color of the pixel at the (x, y) coordinate of the image (imageName).
   */
  @Override
  public Color getColorAt(int x, int y, String imageName) {
    Pixel[][] img = this.files.get(imageName);

    return new Color(img[y][x].red, img[y][x].green, img[y][x].blue);

  }

  /**
   * Gets the width of the image.
   *
   * @param imageName the image whose width we measure.
   * @return an integer value of the width of the image.
   */
  @Override
  public int getBoardWidth(String imageName) {
    Pixel[][] img = this.files.get(imageName);

    if (img == null) {
      return 0;
    } else {
      return this.files.get(imageName)[0].length;
    }
  }

  /**
   * Gets the height of the image.
   *
   * @param imageName the image whose height we measure.
   * @return an integer value of the height of the image.
   */
  @Override
  public int getBoardHeight(String imageName) {
    Pixel[][] img = this.files.get(imageName);

    if (img == null) {
      return 0;
    } else {
      return this.files.get(imageName).length;
    }
  }

  /**
   * Gets the histogram values of the image.
   *
   * @param component the component to generate histogram with.
   * @param imageName the image to generate histogram of.
   * @return an array of integers denoting the frequency of each pixel-intensity (0-255).
   */
  @Override
  public int[] getHistogram(String component, String imageName) {
    this.greyscale(component, imageName, imageName
            + component + "h1$t0gr4m");
    Pixel[][] img = this.files.get(imageName + component + "h1$t0gr4m");
    int[] output = new int[256];

    for (int i = 0; i < img.length; i++) {
      for (int j = 0; j < img[0].length; j++) {

        output[img[i][j].red] = output[img[i][j].red] + 1;

      }
    }

    return output;
  }
}
