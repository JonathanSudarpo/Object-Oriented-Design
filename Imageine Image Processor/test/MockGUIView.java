import java.io.File;
import java.io.StringWriter;

import javax.swing.JTextField;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.Features;
import view.ImageManipulatorGUIView;

/**
 * An implementation of the GUI View that returns the method being called.
 */
public class MockGUIView implements ImageManipulatorGUIView {
  private JTextField input;
  private final StringWriter log;

  /**
   * Constructs an object of {@code MockGUIView} with the supplied log.
   *
   * @param log the StringWriter that keeps track of all operations being conducted.
   */
  public MockGUIView(StringWriter log) {
    this.log = log;
  }

  /**
   * Signifies to the log that features have been added.
   *
   * @param features the features to be added.
   */
  @Override
  public void addFeatures(Features features) {
    this.log.append("Added Features! " + System.lineSeparator());
    this.input = new JTextField(10);
    this.input.setActionCommand("input");
  }

  /**
   * Tells the log that an image has been loaded.
   *
   * @return the file that has been loaded.
   */
  @Override
  public File loadImage() {
    JFileChooser fileChooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Types",
            "png", "jpg", "jpeg", "gif", "ppm");
    fileChooser.setFileFilter(filter);
    File f = fileChooser.getSelectedFile();
    this.log.append("Loaded the image " + f.getName() + System.lineSeparator());
    return f;
  }

  /**
   * Tells the log that an image has been saved.
   *
   * @return the file that has been saved.
   */
  @Override
  public File saveImage() {
    JFileChooser fileChooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Types",
            "png", "jpg", "jpeg", "gif", "ppm");
    fileChooser.setFileFilter(filter);
    File f = fileChooser.getSelectedFile();
    this.log.append("Saved " + f.getName() + System.lineSeparator());
    return f;
  }

  /**
   * Signifies to the log that the image has been brightened.
   *
   * @return the integer increment that the image has been brightened/dimmed by.
   */
  @Override
  public int brighten() {
    int output = 0;

    String inputText = this.input.getText();

    try {
      output += Integer.parseInt(inputText);
    } catch (NumberFormatException e) {
      this.renderMessage("Must type an integer into the box, positive to brighten, " +
              "negative to darken");
    }
    this.log.append("Brightened by " + output + System.lineSeparator());
    return output;
  }

  /**
   * Signifies to the log that the image has been greyscaled.
   *
   * @return a String representing the user input of the greyscale operation.
   */
  @Override
  public String greyscale() {
    String inputText = this.input.getText();
    this.log.append("Greyscaled " + inputText + System.lineSeparator());
    return inputText;
  }

  /**
   * Tells the log that a message has been rendered, along with what message has been rendered.
   *
   * @param message the message to be rendered to the view, or in this case, appened to the log.
   */
  @Override
  public void renderMessage(String message) {
    this.log.append("Rendered a message: " + message + System.lineSeparator());
  }

  /**
   * Tells the log that the image has been refreshed/repainted.
   */
  @Override
  public void refresh() {
    this.log.append("Repainted the image." + System.lineSeparator());

  }
}
