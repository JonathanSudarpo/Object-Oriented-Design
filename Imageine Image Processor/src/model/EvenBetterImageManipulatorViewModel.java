package model;

import java.awt.Color;

/**
 * Represents a view model for the Image Manipulator. Allows users to get a view of the
 * model without exposing the implementation.
 */
public class EvenBetterImageManipulatorViewModel implements ImageManipulatorViewModel {

  private final EvenBetterImageManipulatorModel model;


  /**
   * Constructs an {@code BetterImageManipulatorViewModel} object. Takes in a model to
   * construct with.
   *
   * @param model the model to use in constructing the image.
   * @throws IllegalArgumentException if the model given is null.
   */
  public EvenBetterImageManipulatorViewModel(EvenBetterImageManipulatorModel model)
          throws IllegalArgumentException {

    if (model != null) {
      this.model = model;
    } else {
      throw new IllegalArgumentException("Cannot have null model");
    }
  }

  /**
   * Gets the color of a singular pixel in an image from the model.
   *
   * @param x         the x coordinate of the pixel.
   * @param y         the y coordinate of the pixel.
   * @param imageName the image to grab the color of the pixel from.
   * @return the color of the pixel at the (x,y) coordinate of the image (imageName).
   */
  @Override
  public Color getColorAt(int x, int y, String imageName) {
    return this.model.getColorAt(x, y, imageName);
  }

  /**
   * Gets the width of the image from the model.
   *
   * @param imageName the image whose width we measure.
   * @return an integer value of the width of the image.
   */
  @Override
  public int getBoardWidth(String imageName) {
    return this.model.getBoardWidth(imageName);
  }

  /**
   * Gets the height of the image from the model.
   *
   * @param imageName the image whose height we measure.
   * @return an integer value of the height of the image.
   */
  @Override
  public int getBoardHeight(String imageName) {
    return this.model.getBoardHeight(imageName);
  }

  /**
   * Gets the histogram values of the image from the model.
   *
   * @param component the component to generate histogram with.
   * @param imageName the image to generate histogram of.
   * @return an array of integers denoting the frequency of each pixel-intensity (0-255).
   */
  @Override
  public int[] getHistogram(String component, String imageName) {
    return this.model.getHistogram(component, imageName);
  }

}
