import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.StringWriter;

import model.EvenBetterImageManipulatorModel;
import model.EvenBetterImageManipulatorModelImpl;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the ImageManipulatorSwingView class.
 */
public class ImageManipulatorSwingViewTest {
  MockGUIView mockView;
  StringWriter log;
  ImageManipulatorGUIControllerForTest controller;
  EvenBetterImageManipulatorModel model;

  @Before
  public void init() {
    this.model = new EvenBetterImageManipulatorModelImpl();
    this.log = new StringWriter();
    this.mockView = new MockGUIView(this.log);
    this.controller = new ImageManipulatorGUIControllerForTest(this.model, this.mockView);
  }

  // Testing add Features is not necessary as it uses pre-built code.
  // In specific, this class uses the "addActionListener" method.
  // This method was already pre-build and by extension, rigorously tested.
  // Therefore, we don't need to test it further.

  // Testing loadImage
  @Test
  public void loadImage() {
    // "JFileChooser" and "FileNameExtensionFilter" compose the predominant logic of this method.
    // These are pre-built code that, by extension, have been rigorously tested and need no
    // further testing. However, in this test, I'm double-checking that all the callbacks
    // of this method occurs.
    this.controller.loadImage(new File("test/testFiles/testTwo2.ppm"));
    assertEquals("Added Features! \n" +
            "Repainted the image.\n" +
            "Rendered a message: Loaded the image as testTwo2.ppm\n", this.log.toString());
  }

  // Testing saveImage.
  @Test
  public void saveImage() {
    // "JFileChooser" and "FileNameExtensionFilter" compose the predominant logic of this method.
    // These are pre-built code that, by extension, have been rigorously tested and need no
    // further testing. However, in this test, I'm double-checking that all the callbacks
    // of this method occurs.
    this.controller.loadImage(new File("test/testFiles/testTwo2.ppm"));
    this.controller.saveImage(new File("test/testFiles/testTwo2.png"));
    assertEquals("Added Features! \n" +
            "Repainted the image.\n" +
            "Rendered a message: Loaded the image as testTwo2.ppm\n" +
            "Repainted the image.\n" +
            "Rendered a message: Saved the image as testTwo2.ppm\n", this.log.toString());
  }

  // Testing brighten.
  @Test
  public void brighten() {
    this.controller.loadImage(new File("test/testFiles/testTwo2.bmp"));
    this.controller.brighten(10);
    assertEquals("Added Features! \n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Loaded the image as testTwo2.bmp\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Brightened the image by 10 as testTwo2br.bmp\n",
            this.log.toString());
  }

  // Testing greyscale.
  @Test
  public void greyscale() {
    // "getText()" is the predominant logic of this method.
    // This is a pre-built method that, by extension, have been rigorously tested and need no
    // further testing. However, I'm double-checking that all the callbacks of this method occurs.
    this.controller.loadImage(new File("test/testFiles/testTwo2.bmp"));
    this.controller.greyscale("red");
    this.controller.greyscale("green");
    this.controller.greyscale("blue");
    this.controller.greyscale("luma");
    this.controller.greyscale("value");
    this.controller.greyscale("intensity");
    assertEquals("Added Features! \n" +
            "Repainted the image.\n" +
            "Rendered a message: Loaded the image as testTwo2.bmp\n" +
            "Repainted the image.\n" +
            "Rendered a message: Greyscaled the image as testTwo2grred.bmp\n" +
            "Repainted the image.\n" +
            "Rendered a message: Greyscaled the image as testTwo2grredgrgreen.bmp\n" +
            "Repainted the image.\n" +
            "Rendered a message: Greyscaled the image as testTwo2grredgrgreengrblue.bmp\n" +
            "Repainted the image.\n" +
            "Rendered a message: Greyscaled the image as testTwo2grredgrgreengrbluegrluma.bmp\n" +
            "Repainted the image.\n" +
            "Rendered a message: Greyscaled the image as " +
            "testTwo2grredgrgreengrbluegrlumagrvalue.bmp\n" +
            "Repainted the image.\n" +
            "Rendered a message: Greyscaled the image as " +
            "testTwo2grredgrgreengrbluegrlumagrvaluegrintensity.bmp\n", this.log.toString());

  }

  // Testing renderMessage.
  @Test
  public void renderMessage() {
    // Uses the setText() method as the predominant logic of this method.
    // This is a pre-built method that, by extension, have been rigorously tested and need no
    // further testing. However, I'm double-checking that all the callbacks of this method occurs.
    this.controller.loadImage(new File("test/testFiles/testTwo2.bmp"));
    this.mockView.renderMessage("This is a test!");
    assertEquals("Added Features! \n" +
            "Repainted the image.\n" +
            "Rendered a message: Loaded the image as testTwo2.bmp\n" +
            "Rendered a message: This is a test!\n", this.log.toString());
    this.mockView.renderMessage("Another message!");
    assertEquals("Added Features! \n" +
            "Repainted the image.\n" +
            "Rendered a message: Loaded the image as testTwo2.bmp\n" +
            "Rendered a message: This is a test!\n" +
            "Rendered a message: Another message!\n", this.log.toString());
  }

  // tests the refresh method
  @Test
  public void refresh() {
    // just checking the callback
    this.controller.loadImage(new File("test/testFiles/testTwo2.bmp"));
    this.mockView.refresh();
    assertEquals("Added Features! \n" +
            "Repainted the image.\n" +
            "Rendered a message: Loaded the image as testTwo2.bmp\n" +
            "Repainted the image.\n", this.log.toString());
  }
}