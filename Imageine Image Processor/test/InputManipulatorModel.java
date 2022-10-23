import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;

import model.BetterImageManipulatorModel;

/**
 * An implementation of the Image Manipulator that returns the inputted values.
 */
public class InputManipulatorModel implements BetterImageManipulatorModel {
  private final StringWriter log;

  /**
   * Creates an object of {@code InputManipulatorModel} with the supplied log.
   *
   * @param log the StringWriter that keeps track of what operations have been done.
   */
  public InputManipulatorModel(StringWriter log) {
    this.log = log;
  }

  /**
   * Tells the log that a given filter has been applied to the image and kept at the destName.
   *
   * @param filt     the filter to be used.
   * @param name     the name of the image to apply the filter to.
   * @param destName the new, filtered image's path.
   * @throws IllegalArgumentException if the supplied image can't be found or doesn't exist.
   */
  @Override
  public void filter(double[][] filt, String name, String destName)
          throws IllegalArgumentException {
    this.log.append("Filter: " + filt[0][0] + filt[0][1] + filt[0][2] + " Image name: " + name
            + " Dest name: " + destName);
  }

  /**
   * Tells the log that a given transformation has been applied to the image and kept at destName.
   *
   * @param transform the transformation to be used.
   * @param name      the name of the image to apply the transform to.
   * @param destName  the new, transformed image's path.
   * @throws IllegalArgumentException if the image can't be found or doesn't exist.
   */
  @Override
  public void colorTransformation(double[][] transform, String name, String destName)
          throws IllegalArgumentException {
    this.log.append("Transform: " + transform[0][0] + transform[0][1] + transform[0][2]
            + " Image name: " + name
            + " Dest name: " + destName);
  }

  /**
   * Tells the log that an image at the path has been loaded as destName.
   *
   * @param path     the file path of the file.
   * @param destName the name to refer to the image by.
   * @throws FileNotFoundException if the path supplied doesn't point to a file.
   */
  @Override
  public void loadImage(String path, String destName) throws FileNotFoundException {
    this.log.append("Image path: " + path + " Dest name: " + destName);
  }

  /**
   * Tells the log that the supplied image (name) has been saved to the destPath.
   *
   * @param destPath the location to save the file to.
   * @param name     the file to save.
   * @throws IOException if the file cannot be saved or transmitted.
   */
  @Override
  public void saveImage(String destPath, String name) throws IOException {
    this.log.append("Dest path: " + destPath + " Image name: " + name);
  }

  /**
   * Tells the log that the greyscale operation based on the supplied component has been applied
   * onto the image (name) and kept at the destName.
   *
   * @param component the component to greyscale by.
   * @param name      the name of the image to greyscale.
   * @param destName  the name of the new greyscaled image.
   * @throws IllegalArgumentException if the supplied image/file can't be found or the destName
   *                                  doesn't exist.
   */
  @Override
  public void greyscale(String component, String name, String destName)
          throws IllegalArgumentException {
    this.log.append("Greyscale: " + component + " Image name: " + name
            + " Dest name: " + destName);
  }

  /**
   * Tells the log that the vertical-flip operation has been applied onto the image (name)
   * and saved at the destName.
   *
   * @param name     the name of the image to flip.
   * @param destName the name of the flipped image.
   * @throws IllegalArgumentException if the file or the destName can't be found or doesn't exist.
   */
  @Override
  public void verticalFlip(String name, String destName) throws IllegalArgumentException {
    this.log.append("Vert Flip Image name: " + name + " Dest name: " + destName);
  }

  /**
   * Tells the log that the horizontal-flip operation has been applied onto the image (name)
   * and saved at the destName.
   *
   * @param name     the name of the image to flip.
   * @param destName the name of the flipped image.
   * @throws IllegalArgumentException if the file or the destName can't be found or doesn't exist.
   */
  @Override
  public void horizontalFlip(String name, String destName) throws IllegalArgumentException {
    this.log.append("Hor Flip Image name: " + name + " Dest name: " + destName);
  }

  /**
   * Tells the log that the image (name) has been brightened by the increment and kept at the
   * destName.
   *
   * @param increment the amount to increase the brightness by.
   * @param name      the name of the image to brighten.
   * @param destName  the name of the brightened image.
   * @throws IllegalArgumentException if the file or the destName can't be found or doesn't exist.
   */
  @Override
  public void brighten(int increment, String name, String destName)
          throws IllegalArgumentException {
    this.log.append("Increment: " + increment + " Image name: " + name + " Dest name: " + destName);
  }
}