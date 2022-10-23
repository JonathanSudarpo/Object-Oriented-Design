import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.StringWriter;
import java.util.HashMap;

import controller.ImageManipulatorGUIController;
import model.EvenBetterImageManipulatorModel;
import model.EvenBetterImageManipulatorModelImpl;
import model.Pixel;
import view.ImageManipulatorGUIView;
import view.ImageManipulatorSwingView;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;

/**
 * Tests for the ImageManipulatorGUIController class.
 */
public class ImageManipulatorGUIControllerTest {
  EvenBetterImageManipulatorModel modelNull;
  EvenBetterImageManipulatorModel model1;

  HashMap<String, Pixel[][]> files1;

  ImageManipulatorGUIView viewNull;
  ImageManipulatorGUIView view1;
  StringWriter log;
  MockGUIView mockView;


  ImageManipulatorGUIController controller;
  ImageManipulatorGUIControllerForTest mockController;
  String currImage;


  // Pixels of "test2.txt"
  Pixel one;
  Pixel two;
  Pixel three;
  Pixel four;


  // initializing the previously defined fields.
  @Before
  public void init() {
    this.modelNull = null;
    this.files1 = new HashMap<String, Pixel[][]>();
    this.model1 = new EvenBetterImageManipulatorModelImpl(this.files1);
    this.view1 = new ImageManipulatorSwingView(this.model1);
    this.log = new StringWriter();
    this.mockView = new MockGUIView(this.log);
    this.currImage = "test/testFiles/testTwo2.bmp";
    this.controller = new ImageManipulatorGUIController(this.model1, this.view1);
    this.mockController = new ImageManipulatorGUIControllerForTest(this.model1, this.mockView);
    // Initializing the pixels as variables for "test2.txt"
    this.one = new Pixel(38, 31, 23);
    this.two = new Pixel(78, 29, 58);
    this.three = new Pixel(23, 41, 74);
    this.four = new Pixel(123, 215, 201);

  }

  // testing valid constructor.
  @Test
  public void validConstructor() {
    // no field convenience constructor
    EvenBetterImageManipulatorModel manipulatorModel = new EvenBetterImageManipulatorModelImpl();
    EvenBetterImageManipulatorModel model = manipulatorModel;
    ImageManipulatorSwingView swingView = new ImageManipulatorSwingView(model);
    ImageManipulatorSwingView view = swingView;
    this.controller = new ImageManipulatorGUIController(model, view);
    assertEquals(model, manipulatorModel);
    assertEquals(view, swingView);
  }

  // testing for invalid constructor
  @Test
  public void invalidConstructor() {
    // null model
    try {
      this.controller = new ImageManipulatorGUIController(this.modelNull, this.view1);
      fail("Should have thrown an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Cannot have null fields", e.getMessage());
    }

    // null view
    try {
      this.controller = new ImageManipulatorGUIController(this.model1, this.viewNull);
      fail("Should have thrown an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Cannot have null fields", e.getMessage());
    }

    // both null
    try {
      this.controller = new ImageManipulatorGUIController(this.modelNull, this.viewNull);
      fail("Should have thrown an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Cannot have null fields", e.getMessage());
    }
  }

  /*
   * For these tests, all we need to do is load an image, and make sure that if we take
   * the image from the hashmap of files, they are the correct pixels. Then, we make sure that
   * the current image name is the correct image name. This is enough to show that the controller
   * does what it's supposed to do and that the loadImage function works.
   */
  // testing loading a BMP through GUI controller
  @Test
  public void loadImageBMP() {
    // normal
    this.controller.loadImage(new File("test/testFiles/testTwo2.bmp"));
    Pixel[][] load = this.files1.get("testTwo2.bmp");

    assertEquals(this.one, load[0][0]);
    assertEquals(this.two, load[0][1]);
    assertEquals(this.three, load[1][0]);
    assertEquals(this.four, load[1][1]);
    assertEquals("testTwo2.bmp", this.controller.getCurrentImageName());

    // mock for load
    this.mockController.loadImage(new File("test/testFiles/testTwo2.bmp"));
    assertEquals("Added Features! \n" +
            "Repainted the image.\n" +
            "Rendered a message: Loaded the image as testTwo2.bmp\n", this.log.toString());

    // non-existant
    this.mockController.loadImage(new File("thisfileisfake.bmp"));
    assertEquals("Added Features! \n" +
            "Repainted the image.\n" +
            "Rendered a message: Loaded the image as testTwo2.bmp\n" +
            "Rendered a message: Could not load file\n", this.log.toString());

  }

  // testing loading a PNG through the GUI controller.
  @Test
  public void loadImagePNG() {
    this.controller.loadImage(new File("test/testFiles/testTwo2.png"));
    Pixel[][] load = this.files1.get("testTwo2.png");

    assertEquals(this.one, load[0][0]);
    assertEquals(this.two, load[0][1]);
    assertEquals(this.three, load[1][0]);
    assertEquals(this.four, load[1][1]);
    assertEquals("testTwo2.png", this.controller.getCurrentImageName());

    // mock for load
    this.mockController.loadImage(new File("test/testFiles/testTwo2.png"));
    assertEquals("Added Features! \n" +
            "Repainted the image.\n" +
            "Rendered a message: Loaded the image as testTwo2.png\n", this.log.toString());

    // non-existant
    this.mockController.loadImage(new File("thisfileisfake"));
    assertEquals("Added Features! \n" +
            "Repainted the image.\n" +
            "Rendered a message: Loaded the image as testTwo2.png\n" +
            "Rendered a message: Could not load file\n", this.log.toString());
  }

  // testing loading a PPM through the GUI controller.
  @Test
  public void loadImagePPM() {
    this.controller.loadImage(new File("test/testFiles/testTwo2.ppm"));
    Pixel[][] load = this.files1.get("testTwo2.ppm");

    assertEquals(this.one, load[0][0]);
    assertEquals(this.two, load[0][1]);
    assertEquals(this.three, load[1][0]);
    assertEquals(this.four, load[1][1]);
    assertEquals("testTwo2.ppm", this.controller.getCurrentImageName());

    // mock for load
    this.mockController.loadImage(new File("test/testFiles/testTwo2.ppm"));
    assertEquals("Added Features! \n" +
            "Repainted the image.\n" +
            "Rendered a message: Loaded the image as testTwo2.ppm\n", this.log.toString());

    // non-existant
    this.mockController.loadImage(new File("thisfileisfake.ppm"));
    assertEquals("Added Features! \n" +
            "Repainted the image.\n" +
            "Rendered a message: Loaded the image as testTwo2.ppm\n" +
            "Rendered a message: Could not load file\n", this.log.toString());
  }

  // testing loading a JPEG through the GUI controller.
  @Test
  public void loadImageJPEG() {
    this.controller.loadImage(new File("test/testFiles/test.jpeg"));
    Pixel[][] load = this.files1.get("test.jpeg");

    assertEquals(new Pixel(144, 68, 36), load[0][0]);
    assertEquals(new Pixel(177, 101, 69), load[0][1]);
    assertEquals(new Pixel(248, 172, 140), load[1][0]);
    assertEquals(new Pixel(228, 152, 120), load[1][1]);
    assertEquals("test.jpeg", this.controller.getCurrentImageName());

    // mock for load
    this.mockController.loadImage(new File("test/testFiles/testTwo2.jpeg"));
    assertEquals("Added Features! \n" +
            "Repainted the image.\n" +
            "Rendered a message: Loaded the image as testTwo2.jpeg\n", this.log.toString());

    // non-existant
    this.mockController.loadImage(new File("thisfileisfake"));
    assertEquals("Added Features! \n" +
            "Repainted the image.\n" +
            "Rendered a message: Loaded the image as testTwo2.jpeg\n" +
            "Rendered a message: Could not load file\n", this.log.toString());

  }

  /*
   * For these test, I'm gonna load a file into the controller and check that the
   * current image is the correct image. Then, I'll save the file onto the system
   * and make sure that when we re-load it, the pixels are what they're supposed to be.
   * Since we know that loadImage works from the above test, we are able to use it for these
   * tests.
   * While doing this, I'm also testing saving as a different file format!
   */

  // saving an image of type PNG
  @Test
  public void saveImagePNG() {
    // load a file into the controller (we're testing saving bmp -> png)
    this.controller.loadImage(new File("test/testFiles/testTwo2.bmp"));
    assertEquals("testTwo2.bmp", this.controller.getCurrentImageName());

    // save the file onto the system
    this.controller.saveImage(new File("test/testFiles/testTwo2.png"));

    // re-load the saved image so we can see if it saved properly/actually got saved.
    this.controller.loadImage(new File("test/testFiles/testTwo2.png"));
    assertEquals("testTwo2.png", this.controller.getCurrentImageName());

    // load the pixels onto a temporary variable
    Pixel[][] load = this.files1.get("testTwo2.png");

    // check that the pixels are what they're supposed to do.
    assertEquals(this.one, load[0][0]);
    assertEquals(this.two, load[0][1]);
    assertEquals(this.three, load[1][0]);
    assertEquals(this.four, load[1][1]);

    // mock for save
    this.mockController.loadImage(new File("test/testFiles/testTwo2.png"));
    this.mockController.saveImage(new File("test/testFiles/testTwo2.png"));
    assertEquals("Added Features! \n" +
            "Repainted the image.\n" +
            "Rendered a message: Loaded the image as testTwo2.png\n" +
            "Repainted the image.\n" +
            "Rendered a message: Saved the image as testTwo2.png\n", this.log.toString());
  }

  // saving an image of type BPM
  @Test
  public void saveImageBMP() {
    // load a file into the controller (saving png -> bpm)
    this.controller.loadImage(new File("test/testFiles/testTwo2.png"));
    assertEquals("testTwo2.png", this.controller.getCurrentImageName());

    // save the file onto the system
    this.controller.saveImage(new File("test/testFiles/testTwo2.bmp"));

    // re-load the saved image so we can see if it saved properly/actually got saved.
    this.controller.loadImage(new File("test/testFiles/testTwo2.bmp"));
    assertEquals("testTwo2.bmp", this.controller.getCurrentImageName());

    // load the pixels onto a temporary variable
    Pixel[][] load = this.files1.get("testTwo2.bmp");

    // check that the pixels are what they're supposed to do.
    assertEquals(this.one, load[0][0]);
    assertEquals(this.two, load[0][1]);
    assertEquals(this.three, load[1][0]);
    assertEquals(this.four, load[1][1]);

    // mock for save
    this.mockController.loadImage(new File("test/testFiles/testTwo2.bmp"));
    this.mockController.saveImage(new File("test/testFiles/testTwo2.bmp"));
    assertEquals("Added Features! \n" +
            "Repainted the image.\n" +
            "Rendered a message: Loaded the image as testTwo2.bmp\n" +
            "Repainted the image.\n" +
            "Rendered a message: Saved the image as testTwo2.bmp\n", this.log.toString());

  }

  // saving an image of type PPM
  @Test
  public void saveImagePPM() {
    // load a file into the controller (from png -> ppm)
    this.controller.loadImage(new File("test/testFiles/testTwo2.png"));
    assertEquals("testTwo2.png", this.controller.getCurrentImageName());

    // save the file onto the system
    this.controller.saveImage(new File("test/testFiles/testTwo2.ppm"));

    // re-load the saved image so we can see if it saved properly/actually got saved.
    this.controller.loadImage(new File("test/testFiles/testTwo2.ppm"));
    assertEquals("testTwo2.ppm", this.controller.getCurrentImageName());

    // load the pixels onto a temporary variable
    Pixel[][] load = this.files1.get("testTwo2.ppm");

    // check that the pixels are what they're supposed to do.
    assertEquals(this.one, load[0][0]);
    assertEquals(this.two, load[0][1]);
    assertEquals(this.three, load[1][0]);
    assertEquals(this.four, load[1][1]);

    // mock for save
    this.mockController.loadImage(new File("test/testFiles/testTwo2.ppm"));
    this.mockController.saveImage(new File("test/testFiles/testTwo2.ppm"));
    assertEquals("Added Features! \n" +
            "Repainted the image.\n" +
            "Rendered a message: Loaded the image as testTwo2.ppm\n" +
            "Repainted the image.\n" +
            "Rendered a message: Saved the image as testTwo2.ppm\n", this.log.toString());


  }

  // saving an image of type JPEG
  @Test
  public void saveImageJPEG() {
    // load a file into the controller
    this.controller.loadImage(new File("test/testFiles/test.jpeg"));
    assertEquals("test.jpeg", this.controller.getCurrentImageName());

    // save the file onto the system
    this.controller.saveImage(new File("test/testFiles/testSave.jpeg"));

    // re-load the saved image so we can see if it saved properly/actually got saved.
    this.controller.loadImage(new File("test/testFiles/testSave.jpeg"));
    assertEquals("testSave.jpeg", this.controller.getCurrentImageName());

    // load the pixels onto a temporary variable
    Pixel[][] load = this.files1.get("testSave.jpeg");

    // check that the pixels are what they're supposed to be.

    assertEquals(new Pixel(151, 75, 43), load[0][0]);
    assertEquals(new Pixel(177, 101, 69), load[0][1]);
    assertEquals(new Pixel(236, 160, 128), load[1][0]);
    assertEquals(new Pixel(222, 146, 114), load[1][1]);

    // mock for save
    this.mockController.loadImage(new File("test/testFiles/testTwo2.jpeg"));
    this.mockController.saveImage(new File("test/testFiles/testTwo2.jpeg"));
    assertEquals("Added Features! \n" +
            "Repainted the image.\n" +
            "Rendered a message: Loaded the image as testTwo2.jpeg\n" +
            "Repainted the image.\n" +
            "Rendered a message: Saved the image as testTwo2.jpeg\n", this.log.toString());

  }

  /*
   * For these tests, I'm loading a file onto the controller and applying the vertical flip.
   * Then, from the hashmap of files, I'm loading the vertically flipped file and checking
   * that the pixels have indeed been vertically flipped.
   */

  // tests vertically flipping a BMP
  @Test
  public void verticalFlipBMP() {
    // load a file into the controller
    this.controller.loadImage(new File("test/testFiles/testTwo2.bmp"));
    assertEquals("testTwo2.bmp", this.controller.getCurrentImageName());

    this.controller.verticalFlip();
    assertEquals("testTwo2vert.bmp", this.controller.getCurrentImageName());
    Pixel[][] verticalFlip2 = this.files1.get("testTwo2vert.bmp");

    assertEquals(this.three, verticalFlip2[0][0]);
    assertEquals(this.four, verticalFlip2[0][1]);
    assertEquals(this.one, verticalFlip2[1][0]);
    assertEquals(this.two, verticalFlip2[1][1]);

    // non-existant
    this.mockController.loadImage(new File("thisfileisfake"));
    this.mockController.verticalFlip();
    assertEquals("Added Features! \n" +
                    "Rendered a message: Could not load file\n" +
                    "Rendered a message: Must load an image before you can manipulate it!\n",
            this.log.toString());

    // mock for vertical-flip
    this.mockController.loadImage(new File("test/testFiles/testTwo2.bmp"));
    this.mockController.verticalFlip();
    assertEquals("Added Features! \n" +
                    "Rendered a message: Could not load file\n" +
                    "Rendered a message: Must load an image before you can manipulate it!\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Loaded the image as testTwo2.bmp\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Vertically flipped the image as testTwo2vert.bmp\n",
            this.log.toString());


  }

  // tests vertically flipping a PNG
  @Test
  public void verticalFlipPNG() {
    // load a file into the controller
    this.controller.loadImage(new File("test/testFiles/testTwo2.png"));
    assertEquals("testTwo2.png", this.controller.getCurrentImageName());

    this.controller.verticalFlip();
    assertEquals("testTwo2vert.png", this.controller.getCurrentImageName());
    Pixel[][] verticalFlip2 = this.files1.get("testTwo2vert.png");

    assertEquals(this.three, verticalFlip2[0][0]);
    assertEquals(this.four, verticalFlip2[0][1]);
    assertEquals(this.one, verticalFlip2[1][0]);
    assertEquals(this.two, verticalFlip2[1][1]);

    // mock for vertical-flip
    this.mockController.loadImage(new File("test/testFiles/testTwo2.png"));
    this.mockController.verticalFlip();
    assertEquals("Added Features! \n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Loaded the image as testTwo2.png\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Vertically flipped the image as testTwo2vert.png\n",
            this.log.toString());


  }

  // tests vertically flipping a PPM
  @Test
  public void verticalFlipPPM() {
    // load a file into the controller
    this.controller.loadImage(new File("test/testFiles/testTwo2.ppm"));
    assertEquals("testTwo2.ppm", this.controller.getCurrentImageName());

    this.controller.verticalFlip();
    assertEquals("testTwo2vert.ppm", this.controller.getCurrentImageName());
    Pixel[][] verticalFlip2 = this.files1.get("testTwo2vert.ppm");

    assertEquals(this.three, verticalFlip2[0][0]);
    assertEquals(this.four, verticalFlip2[0][1]);
    assertEquals(this.one, verticalFlip2[1][0]);
    assertEquals(this.two, verticalFlip2[1][1]);

    // mock for vertical-flip
    this.mockController.loadImage(new File("test/testFiles/testTwo2.ppm"));
    this.mockController.verticalFlip();
    assertEquals("Added Features! \n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Loaded the image as testTwo2.ppm\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Vertically flipped the image as testTwo2vert.ppm\n",
            this.log.toString());

  }

  // tests vertically flipping a JPEG
  @Test
  public void verticalFlipJPEG() {
    // load a file into the controller
    this.controller.loadImage(new File("test/testFiles/test.jpeg"));
    assertEquals("test.jpeg", this.controller.getCurrentImageName());

    this.controller.verticalFlip();
    assertEquals("testvert.jpeg", this.controller.getCurrentImageName());
    Pixel[][] verticalFlip2 = this.files1.get("testvert.jpeg");

    assertEquals(new Pixel(248, 172, 140), verticalFlip2[0][0]);
    assertEquals(new Pixel(228, 152, 120), verticalFlip2[0][1]);
    assertEquals(new Pixel(144, 68, 36), verticalFlip2[1][0]);
    assertEquals(new Pixel(177, 101, 69), verticalFlip2[1][1]);

    // mock for vertical-flip
    this.mockController.loadImage(new File("test/testFiles/testTwo2.jpeg"));
    this.mockController.verticalFlip();
    assertEquals("Added Features! \n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Loaded the image as testTwo2.jpeg\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Vertically flipped the image as testTwo2vert.jpeg\n",
            this.log.toString());


  }


  /*
   * For these tests, I'm loading a file into the controller and then
   * applying the horizontal flip. Then, from the hashmap of files,
   * I'm retrieving the horizontally flipped image and making sure that the pixels have
   * been horizontally flipped.
   */

  // tests horizontally flipping a BMP
  @Test
  public void horizontalFlipBMP() {
    // load a file into the controller
    this.controller.loadImage(new File("test/testFiles/testTwo2.bmp"));
    assertEquals("testTwo2.bmp", this.controller.getCurrentImageName());

    this.controller.horizontalFlip();
    assertEquals("testTwo2hor.bmp", this.controller.getCurrentImageName());
    Pixel[][] horizontalFlip = this.files1.get("testTwo2hor.bmp");

    assertEquals(this.two, horizontalFlip[0][0]);
    assertEquals(this.one, horizontalFlip[0][1]);
    assertEquals(this.four, horizontalFlip[1][0]);
    assertEquals(this.three, horizontalFlip[1][1]);

    // non-existant
    this.mockController.loadImage(new File("thisfileisfake"));
    this.mockController.horizontalFlip();
    assertEquals("Added Features! \n" +
                    "Rendered a message: Could not load file\n" +
                    "Rendered a message: Must load an image before you can manipulate it!\n",
            this.log.toString());

    // mock for horizontal-flip
    this.mockController.loadImage(new File("test/testFiles/testTwo2.bmp"));
    this.mockController.horizontalFlip();
    assertEquals("Added Features! \n" +
                    "Rendered a message: Could not load file\n" +
                    "Rendered a message: Must load an image before you can manipulate it!\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Loaded the image as testTwo2.bmp\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Horizontally flipped the image as testTwo2hor.bmp\n",
            this.log.toString());

  }

  // tests horizontally flipping a PNG
  @Test
  public void horizontalFlipPNG() {
    // load a file into the controller
    this.controller.loadImage(new File("test/testFiles/testTwo2.png"));
    assertEquals("testTwo2.png", this.controller.getCurrentImageName());

    this.controller.horizontalFlip();
    assertEquals("testTwo2hor.png", this.controller.getCurrentImageName());
    Pixel[][] horizontalFlip = this.files1.get("testTwo2hor.png");

    assertEquals(this.two, horizontalFlip[0][0]);
    assertEquals(this.one, horizontalFlip[0][1]);
    assertEquals(this.four, horizontalFlip[1][0]);
    assertEquals(this.three, horizontalFlip[1][1]);

    // mock for vertical-flip
    this.mockController.loadImage(new File("test/testFiles/testTwo2.png"));
    this.mockController.horizontalFlip();
    assertEquals("Added Features! \n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Loaded the image as testTwo2.png\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Horizontally flipped the image as testTwo2hor.png\n",
            this.log.toString());

  }

  // tests horizontally flipping a PPM
  @Test
  public void horizontalFlipPPM() {
    // load a file into the controller
    this.controller.loadImage(new File("test/testFiles/testTwo2.ppm"));
    assertEquals("testTwo2.ppm", this.controller.getCurrentImageName());

    this.controller.horizontalFlip();
    assertEquals("testTwo2hor.ppm", this.controller.getCurrentImageName());
    Pixel[][] horizontalFlip = this.files1.get("testTwo2hor.ppm");

    assertEquals(this.two, horizontalFlip[0][0]);
    assertEquals(this.one, horizontalFlip[0][1]);
    assertEquals(this.four, horizontalFlip[1][0]);
    assertEquals(this.three, horizontalFlip[1][1]);

    // mock for vertical-flip
    this.mockController.loadImage(new File("test/testFiles/testTwo2.ppm"));
    this.mockController.horizontalFlip();
    assertEquals("Added Features! \n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Loaded the image as testTwo2.ppm\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Horizontally flipped the image as testTwo2hor.ppm\n",
            this.log.toString());

  }

  // tests horizontally flipping a JPEG
  @Test
  public void horizontalFlipJPEG() {
    // load a file into the controller
    this.controller.loadImage(new File("test/testFiles/test.jpeg"));
    assertEquals("test.jpeg", this.controller.getCurrentImageName());

    this.controller.horizontalFlip();
    assertEquals("testhor.jpeg", this.controller.getCurrentImageName());
    Pixel[][] horizontalFlip = this.files1.get("testhor.jpeg");

    assertEquals(new Pixel(171, 81, 135), horizontalFlip[0][0]);
    assertEquals(new Pixel(177, 101, 69), horizontalFlip[0][1]);
    assertEquals(new Pixel(162, 72, 126), horizontalFlip[1][0]);
    assertEquals(new Pixel(228, 152, 120), horizontalFlip[1][1]);

    // mock for vertical-flip
    this.mockController.loadImage(new File("test/testFiles/testTwo2.jpeg"));
    this.mockController.horizontalFlip();
    assertEquals("Added Features! \n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Loaded the image as testTwo2.jpeg\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Horizontally flipped the image as testTwo2hor.jpeg\n",
            this.log.toString());

  }

  /*
   * For these tests, I'm loading an image from the controller. Brightening the image
   * by some increment and then checking to make sure that all the pixels have been
   * indeed brightened/dimmed by said amount or clamped to max/min value.
   * In specific, I'm checking brightening above max, below min, and normal.
   * Note: we don't need to check for a null file as the load image doesn't allow that
   * so there wouldn't be a null image to brighten anyways.
   */

  // tests brightening a BMP
  @Test
  public void brightenBMP() {

    // normal brighten
    this.controller.loadImage(new File("test/testFiles/testTwo2.bmp"));

    this.controller.brighten(23);
    assertEquals("testTwo2br.bmp", this.controller.getCurrentImageName());
    Pixel[][] brighten = this.files1.get("testTwo2br.bmp");

    assertEquals(new Pixel(61, 54, 46), brighten[0][0]);
    assertEquals(new Pixel(101, 52, 81), brighten[0][1]);
    assertEquals(new Pixel(46, 64, 97), brighten[1][0]);
    assertEquals(new Pixel(146, 238, 224), brighten[1][1]);

    // brighten above max, we re-load as the current file is the brightened version
    this.controller.loadImage(new File("test/testFiles/testTwo2.bmp"));
    assertEquals("testTwo2.bmp", this.controller.getCurrentImageName());

    this.controller.brighten(100);
    assertEquals("testTwo2br.bmp", this.controller.getCurrentImageName());

    Pixel[][] brighten1 = this.files1.get("testTwo2br.bmp");

    assertEquals(new Pixel(138, 131, 123), brighten1[0][0]);
    assertEquals(new Pixel(178, 129, 158), brighten1[0][1]);
    assertEquals(new Pixel(123, 141, 174), brighten1[1][0]);
    assertEquals(new Pixel(223, 255, 255), brighten1[1][1]);

    // negative brighten below 0
    this.controller.loadImage(new File("test/testFiles/testTwo2.bmp"));
    assertEquals("testTwo2.bmp", this.controller.getCurrentImageName());

    this.controller.brighten(-100);
    assertEquals("testTwo2br.bmp", this.controller.getCurrentImageName());

    Pixel[][] brighten2 = this.files1.get("testTwo2br.bmp");

    assertEquals(new Pixel(0, 0, 0), brighten2[0][0]);
    assertEquals(new Pixel(0, 0, 0), brighten2[0][1]);
    assertEquals(new Pixel(0, 0, 0), brighten2[1][0]);
    assertEquals(new Pixel(23, 115, 101), brighten2[1][1]);


    // non-existent
    this.mockController.loadImage(new File("thisfileisfake"));
    this.mockController.brighten(10);
    assertEquals("Added Features! \n" +
                    "Rendered a message: Could not load file\n" +
                    "Rendered a message: Must load an image before you can manipulate it!\n",
            this.log.toString());

    // mock for brighten
    this.mockController.loadImage(new File("test/testFiles/testTwo2.bmp"));
    this.mockController.brighten(10);
    assertEquals("Added Features! \n" +
                    "Rendered a message: Could not load file\n" +
                    "Rendered a message: Must load an image before you can manipulate it!\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Loaded the image as testTwo2.bmp\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Brightened the image by 10 as testTwo2br.bmp\n",
            this.log.toString());


  }

  // tests brightening a PNG
  @Test
  public void brightenPNG() {
    // normal brighten
    this.controller.loadImage(new File("test/testFiles/testTwo2.png"));

    this.controller.brighten(23);
    assertEquals("testTwo2br.png", this.controller.getCurrentImageName());
    Pixel[][] brighten = this.files1.get("testTwo2br.png");

    assertEquals(new Pixel(61, 54, 46), brighten[0][0]);
    assertEquals(new Pixel(101, 52, 81), brighten[0][1]);
    assertEquals(new Pixel(46, 64, 97), brighten[1][0]);
    assertEquals(new Pixel(146, 238, 224), brighten[1][1]);

    // brighten above max, we re-load as the current file is the brightened version
    this.controller.loadImage(new File("test/testFiles/testTwo2.png"));
    assertEquals("testTwo2.png", this.controller.getCurrentImageName());

    this.controller.brighten(100);
    assertEquals("testTwo2br.png", this.controller.getCurrentImageName());

    Pixel[][] brighten1 = this.files1.get("testTwo2br.png");

    assertEquals(new Pixel(138, 131, 123), brighten1[0][0]);
    assertEquals(new Pixel(178, 129, 158), brighten1[0][1]);
    assertEquals(new Pixel(123, 141, 174), brighten1[1][0]);
    assertEquals(new Pixel(223, 255, 255), brighten1[1][1]);

    // negative brighten below 0
    this.controller.loadImage(new File("test/testFiles/testTwo2.png"));
    assertEquals("testTwo2.png", this.controller.getCurrentImageName());

    this.controller.brighten(-100);
    assertEquals("testTwo2br.png", this.controller.getCurrentImageName());

    Pixel[][] brighten2 = this.files1.get("testTwo2br.png");

    assertEquals(new Pixel(0, 0, 0), brighten2[0][0]);
    assertEquals(new Pixel(0, 0, 0), brighten2[0][1]);
    assertEquals(new Pixel(0, 0, 0), brighten2[1][0]);
    assertEquals(new Pixel(23, 115, 101), brighten2[1][1]);

    // mock for brighten
    this.mockController.loadImage(new File("test/testFiles/testTwo2.png"));
    this.mockController.brighten(10);
    assertEquals("Added Features! \n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Loaded the image as testTwo2.png\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Brightened the image by 10 as testTwo2br.png\n",
            this.log.toString());
  }

  // tests brightening a PPM
  @Test
  public void brightenPPM() {
    // normal brighten
    this.controller.loadImage(new File("test/testFiles/testTwo2.ppm"));

    this.controller.brighten(23);
    assertEquals("testTwo2br.ppm", this.controller.getCurrentImageName());
    Pixel[][] brighten = this.files1.get("testTwo2br.ppm");

    assertEquals(new Pixel(61, 54, 46), brighten[0][0]);
    assertEquals(new Pixel(101, 52, 81), brighten[0][1]);
    assertEquals(new Pixel(46, 64, 97), brighten[1][0]);
    assertEquals(new Pixel(146, 238, 224), brighten[1][1]);

    // brighten above max, we re-load as the current file is the brightened version
    this.controller.loadImage(new File("test/testFiles/testTwo2.ppm"));
    assertEquals("testTwo2.ppm", this.controller.getCurrentImageName());

    this.controller.brighten(100);
    assertEquals("testTwo2br.ppm", this.controller.getCurrentImageName());

    Pixel[][] brighten1 = this.files1.get("testTwo2br.ppm");

    assertEquals(new Pixel(138, 131, 123), brighten1[0][0]);
    assertEquals(new Pixel(178, 129, 158), brighten1[0][1]);
    assertEquals(new Pixel(123, 141, 174), brighten1[1][0]);
    assertEquals(new Pixel(223, 255, 255), brighten1[1][1]);

    // negative brighten below 0
    this.controller.loadImage(new File("test/testFiles/testTwo2.ppm"));
    assertEquals("testTwo2.ppm", this.controller.getCurrentImageName());

    this.controller.brighten(-100);
    assertEquals("testTwo2br.ppm", this.controller.getCurrentImageName());

    Pixel[][] brighten2 = this.files1.get("testTwo2br.ppm");

    assertEquals(new Pixel(0, 0, 0), brighten2[0][0]);
    assertEquals(new Pixel(0, 0, 0), brighten2[0][1]);
    assertEquals(new Pixel(0, 0, 0), brighten2[1][0]);
    assertEquals(new Pixel(23, 115, 101), brighten2[1][1]);

    // mock for brighten
    this.mockController.loadImage(new File("test/testFiles/testTwo2.ppm"));
    this.mockController.brighten(10);
    assertEquals("Added Features! \n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Loaded the image as testTwo2.ppm\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Brightened the image by 10 as testTwo2br.ppm\n",
            this.log.toString());
  }

  @Test
  public void brightenJPEG() {
    // normal brighten
    this.controller.loadImage(new File("test/testFiles/test.jpeg"));
    assertEquals("test.jpeg", this.controller.getCurrentImageName());

    this.controller.brighten(23);
    Pixel[][] brighten = this.files1.get("testbr.jpeg");
    assertEquals("testbr.jpeg", this.controller.getCurrentImageName());

    assertEquals(new Pixel(167, 91, 59), brighten[0][0]);
    assertEquals(new Pixel(200, 124, 92), brighten[0][1]);
    assertEquals(new Pixel(255, 195, 163), brighten[1][0]);
    assertEquals(new Pixel(251, 175, 143), brighten[1][1]);


    // brighten above max
    this.controller.loadImage(new File("test/testFiles/test.jpeg"));
    assertEquals("test.jpeg", this.controller.getCurrentImageName());

    this.controller.brighten(100);
    Pixel[][] brighten1 = this.files1.get("testbr.jpeg");
    assertEquals("testbr.jpeg", this.controller.getCurrentImageName());

    assertEquals(new Pixel(244, 168, 136), brighten1[0][0]);
    assertEquals(new Pixel(255, 201, 169), brighten1[0][1]);
    assertEquals(new Pixel(255, 255, 240), brighten1[1][0]);
    assertEquals(new Pixel(255, 252, 220), brighten1[1][1]);

    // brighten below 0
    this.controller.loadImage(new File("test/testFiles/test.jpeg"));
    assertEquals("test.jpeg", this.controller.getCurrentImageName());

    this.controller.brighten(-100);
    Pixel[][] brighten2 = this.files1.get("testbr.jpeg");

    assertEquals(new Pixel(44, 0, 0), brighten2[0][0]);
    assertEquals(new Pixel(77, 1, 0), brighten2[0][1]);
    assertEquals(new Pixel(148, 72, 40), brighten2[1][0]);
    assertEquals(new Pixel(128, 52, 20), brighten2[1][1]);


    // mock for brighten
    this.mockController.loadImage(new File("test/testFiles/testTwo2.jpeg"));
    this.mockController.brighten(10);
    assertEquals("Added Features! \n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Loaded the image as testTwo2.jpeg\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Brightened the image by 10 as testTwo2br.jpeg\n",
            this.log.toString());
  }

  /*
   * For these tests, I'm loading an image and then applying some greyscale on it.
   * Then, I'll check if the produced image contains the correct pixels.
   */
  // greyscale on a BMP
  @Test
  public void greyscaleBMP() {
    // Tests greyscale with red component
    this.controller.loadImage(new File("test/testFiles/testOne.bmp"));
    assertEquals("testOne.bmp", this.controller.getCurrentImageName());

    this.controller.greyscale("red");
    assertEquals("testOnegrred.bmp", this.controller.getCurrentImageName());

    Pixel[][] redGrey1 = this.files1.get("testOnegrred.bmp");

    assertEquals(new Pixel(255, 255, 255), redGrey1[0][0]);
    assertEquals(new Pixel(255, 255, 255), redGrey1[0][1]);
    assertEquals(new Pixel(255, 255, 255), redGrey1[0][2]);
    assertEquals(new Pixel(255, 255, 255), redGrey1[1][0]);
    assertEquals(new Pixel(0, 0, 0), redGrey1[1][1]);
    assertEquals(new Pixel(125, 125, 125), redGrey1[1][2]);

    // Tests greyscale with green component

    this.controller.loadImage(new File("test/testFiles/testOne.bmp"));
    assertEquals("testOne.bmp", this.controller.getCurrentImageName());

    this.controller.greyscale("green");
    assertEquals("testOnegrgreen.bmp", this.controller.getCurrentImageName());

    Pixel[][] greenGrey1 = this.files1.get("testOnegrgreen.bmp");

    assertEquals(new Pixel(0, 0, 0), greenGrey1[0][0]);
    assertEquals(new Pixel(0, 0, 0), greenGrey1[0][1]);
    assertEquals(new Pixel(125, 125, 125), greenGrey1[0][2]);
    assertEquals(new Pixel(255, 255, 255), greenGrey1[1][0]);
    assertEquals(new Pixel(255, 255, 255), greenGrey1[1][1]);
    assertEquals(new Pixel(0, 0, 0), greenGrey1[1][2]);

    // Tests greyscale with blue component

    this.controller.loadImage(new File("test/testFiles/testOne.bmp"));
    assertEquals("testOne.bmp", this.controller.getCurrentImageName());

    this.controller.greyscale("blue");
    assertEquals("testOnegrblue.bmp", this.controller.getCurrentImageName());

    Pixel[][] blueGrey1 = this.files1.get("testOnegrblue.bmp");

    assertEquals(new Pixel(125, 125, 125), blueGrey1[0][0]);
    assertEquals(new Pixel(255, 255, 255), blueGrey1[0][1]);
    assertEquals(new Pixel(0, 0, 0), blueGrey1[0][2]);
    assertEquals(new Pixel(0, 0, 0), blueGrey1[1][0]);
    assertEquals(new Pixel(0, 0, 0), blueGrey1[1][1]);
    assertEquals(new Pixel(255, 255, 255), blueGrey1[1][2]);

    // Tests greyscale with value component

    this.controller.loadImage(new File("test/testFiles/testOne.bmp"));
    assertEquals("testOne.bmp", this.controller.getCurrentImageName());

    this.controller.greyscale("value");
    assertEquals("testOnegrvalue.bmp", this.controller.getCurrentImageName());
    Pixel[][] valueGrey1 = this.files1.get("testOnegrvalue.bmp");

    assertEquals(new Pixel(255, 255, 255), valueGrey1[0][0]);
    assertEquals(new Pixel(255, 255, 255), valueGrey1[0][1]);
    assertEquals(new Pixel(255, 255, 255), valueGrey1[0][2]);
    assertEquals(new Pixel(255, 255, 255), valueGrey1[1][0]);
    assertEquals(new Pixel(255, 255, 255), valueGrey1[1][1]);
    assertEquals(new Pixel(255, 255, 255), valueGrey1[1][2]);

    // Tests greyscale with intensity component

    this.controller.loadImage(new File("test/testFiles/testOne.bmp"));
    assertEquals("testOne.bmp", this.controller.getCurrentImageName());

    this.controller.greyscale("intensity");
    assertEquals("testOnegrintensity.bmp", this.controller.getCurrentImageName());
    Pixel[][] intensityGrey1 = this.files1.get("testOnegrintensity.bmp");

    assertEquals(new Pixel(380 / 3, 380 / 3, 380 / 3), intensityGrey1[0][0]);
    assertEquals(new Pixel(510 / 3, 510 / 3, 510 / 3), intensityGrey1[0][1]);
    assertEquals(new Pixel(380 / 3, 380 / 3, 380 / 3), intensityGrey1[0][2]);
    assertEquals(new Pixel(510 / 3, 510 / 3, 510 / 3), intensityGrey1[1][0]);
    assertEquals(new Pixel(255 / 3, 255 / 3, 255 / 3), intensityGrey1[1][1]);
    assertEquals(new Pixel(380 / 3, 380 / 3, 380 / 3), intensityGrey1[1][2]);

    // Tests greyscale with luma component


    this.controller.loadImage(new File("test/testFiles/testOne.bmp"));
    assertEquals("testOne.bmp", this.controller.getCurrentImageName());

    this.controller.greyscale("luma");
    assertEquals("testOnegrluma.bmp", this.controller.getCurrentImageName());
    Pixel[][] lumaGrey1 = this.files1.get("testOnegrluma.bmp");

    assertEquals(new Pixel(63, 63, 63), lumaGrey1[0][0]);
    assertEquals(new Pixel(72, 72, 72), lumaGrey1[0][1]);
    assertEquals(new Pixel(143, 143, 143), lumaGrey1[0][2]);
    assertEquals(new Pixel(236, 236, 236), lumaGrey1[1][0]);
    assertEquals(new Pixel(182, 182, 182), lumaGrey1[1][1]);

    assertEquals(new Pixel(44, 44, 44), lumaGrey1[1][2]);
    // non-existent
    this.mockController.loadImage(new File("thisfileisfake"));
    this.mockController.greyscale("red");
    assertEquals("Added Features! \n" +
                    "Rendered a message: Could not load file\n" +
                    "Rendered a message: Must load an image before you can manipulate it!\n",
            this.log.toString());

    // mock for greyscale
    this.mockController.loadImage(new File("test/testFiles/testTwo2.jpeg"));
    this.mockController.greyscale("red");
    this.mockController.greyscale("blue");
    this.mockController.greyscale("green");
    this.mockController.greyscale("luma");
    this.mockController.greyscale("value");
    this.mockController.greyscale("intensity");
    assertEquals("Added Features! \n" +
                    "Rendered a message: Could not load file\n" +
                    "Rendered a message: Must load an image before you can manipulate it!\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Loaded the image as testTwo2.jpeg\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Greyscaled the image as testTwo2grred.jpeg\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Greyscaled the image as testTwo2grredgrblue.jpeg\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Greyscaled the image as " +
                    "testTwo2grredgrbluegrgreen.jpeg\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Greyscaled the image as " +
                    "testTwo2grredgrbluegrgreengrluma.jpeg\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Greyscaled the image as" +
                    " testTwo2grredgrbluegrgreengrlumagrvalue.jpeg\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Greyscaled the image as " +
                    "testTwo2grredgrbluegrgreengrlumagrvaluegrintensity.jpeg\n",
            this.log.toString());

  }

  // greyscale on a PNG
  @Test
  public void greyscalePNG() {
    // Tests greyscale with red component
    this.controller.loadImage(new File("test/testFiles/testOne.png"));
    assertEquals("testOne.png", this.controller.getCurrentImageName());

    this.controller.greyscale("red");
    assertEquals("testOnegrred.png", this.controller.getCurrentImageName());

    Pixel[][] redGrey1 = this.files1.get("testOnegrred.png");

    assertEquals(new Pixel(255, 255, 255), redGrey1[0][0]);
    assertEquals(new Pixel(255, 255, 255), redGrey1[0][1]);
    assertEquals(new Pixel(255, 255, 255), redGrey1[0][2]);
    assertEquals(new Pixel(255, 255, 255), redGrey1[1][0]);
    assertEquals(new Pixel(0, 0, 0), redGrey1[1][1]);
    assertEquals(new Pixel(125, 125, 125), redGrey1[1][2]);

    // Tests greyscale with green component

    this.controller.loadImage(new File("test/testFiles/testOne.png"));
    assertEquals("testOne.png", this.controller.getCurrentImageName());

    this.controller.greyscale("green");
    assertEquals("testOnegrgreen.png", this.controller.getCurrentImageName());

    Pixel[][] greenGrey1 = this.files1.get("testOnegrgreen.png");

    assertEquals(new Pixel(0, 0, 0), greenGrey1[0][0]);
    assertEquals(new Pixel(0, 0, 0), greenGrey1[0][1]);
    assertEquals(new Pixel(125, 125, 125), greenGrey1[0][2]);
    assertEquals(new Pixel(255, 255, 255), greenGrey1[1][0]);
    assertEquals(new Pixel(255, 255, 255), greenGrey1[1][1]);
    assertEquals(new Pixel(0, 0, 0), greenGrey1[1][2]);

    // Tests greyscale with blue component

    this.controller.loadImage(new File("test/testFiles/testOne.png"));
    assertEquals("testOne.png", this.controller.getCurrentImageName());

    this.controller.greyscale("blue");
    assertEquals("testOnegrblue.png", this.controller.getCurrentImageName());

    Pixel[][] blueGrey1 = this.files1.get("testOnegrblue.png");

    assertEquals(new Pixel(125, 125, 125), blueGrey1[0][0]);
    assertEquals(new Pixel(255, 255, 255), blueGrey1[0][1]);
    assertEquals(new Pixel(0, 0, 0), blueGrey1[0][2]);
    assertEquals(new Pixel(0, 0, 0), blueGrey1[1][0]);
    assertEquals(new Pixel(0, 0, 0), blueGrey1[1][1]);
    assertEquals(new Pixel(255, 255, 255), blueGrey1[1][2]);

    // Tests greyscale with value component

    this.controller.loadImage(new File("test/testFiles/testOne.png"));
    assertEquals("testOne.png", this.controller.getCurrentImageName());

    this.controller.greyscale("value");
    assertEquals("testOnegrvalue.png", this.controller.getCurrentImageName());
    Pixel[][] valueGrey1 = this.files1.get("testOnegrvalue.png");

    assertEquals(new Pixel(255, 255, 255), valueGrey1[0][0]);
    assertEquals(new Pixel(255, 255, 255), valueGrey1[0][1]);
    assertEquals(new Pixel(255, 255, 255), valueGrey1[0][2]);
    assertEquals(new Pixel(255, 255, 255), valueGrey1[1][0]);
    assertEquals(new Pixel(255, 255, 255), valueGrey1[1][1]);
    assertEquals(new Pixel(255, 255, 255), valueGrey1[1][2]);

    // Tests greyscale with intensity component

    this.controller.loadImage(new File("test/testFiles/testOne.png"));
    assertEquals("testOne.png", this.controller.getCurrentImageName());

    this.controller.greyscale("intensity");
    assertEquals("testOnegrintensity.png", this.controller.getCurrentImageName());
    Pixel[][] intensityGrey1 = this.files1.get("testOnegrintensity.png");

    assertEquals(new Pixel(380 / 3, 380 / 3, 380 / 3), intensityGrey1[0][0]);
    assertEquals(new Pixel(510 / 3, 510 / 3, 510 / 3), intensityGrey1[0][1]);
    assertEquals(new Pixel(380 / 3, 380 / 3, 380 / 3), intensityGrey1[0][2]);
    assertEquals(new Pixel(510 / 3, 510 / 3, 510 / 3), intensityGrey1[1][0]);
    assertEquals(new Pixel(255 / 3, 255 / 3, 255 / 3), intensityGrey1[1][1]);
    assertEquals(new Pixel(380 / 3, 380 / 3, 380 / 3), intensityGrey1[1][2]);

    // Tests greyscale with luma component
    this.controller.loadImage(new File("test/testFiles/testOne.png"));
    assertEquals("testOne.png", this.controller.getCurrentImageName());

    this.controller.greyscale("luma");
    assertEquals("testOnegrluma.png", this.controller.getCurrentImageName());
    Pixel[][] lumaGrey1 = this.files1.get("testOnegrluma.png");

    assertEquals(new Pixel(63, 63, 63), lumaGrey1[0][0]);
    assertEquals(new Pixel(72, 72, 72), lumaGrey1[0][1]);
    assertEquals(new Pixel(143, 143, 143), lumaGrey1[0][2]);
    assertEquals(new Pixel(236, 236, 236), lumaGrey1[1][0]);
    assertEquals(new Pixel(182, 182, 182), lumaGrey1[1][1]);
    assertEquals(new Pixel(44, 44, 44), lumaGrey1[1][2]);

    // mock for greyscale
    this.mockController.loadImage(new File("test/testFiles/testTwo2.png"));
    this.mockController.greyscale("red");
    this.mockController.greyscale("blue");
    this.mockController.greyscale("green");
    this.mockController.greyscale("luma");
    this.mockController.greyscale("value");
    this.mockController.greyscale("intensity");
    assertEquals("Added Features! \n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Loaded the image as testTwo2.png\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Greyscaled the image as testTwo2grred.png\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Greyscaled the image as testTwo2grredgrblue.png\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Greyscaled the image as " +
                    "testTwo2grredgrbluegrgreen.png\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Greyscaled the image as " +
                    "testTwo2grredgrbluegrgreengrluma.png\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Greyscaled the image as " +
                    "testTwo2grredgrbluegrgreengrlumagrvalue.png\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Greyscaled the image as " +
                    "testTwo2grredgrbluegrgreengrlumagrvaluegrintensity.png\n",
            this.log.toString());
  }

  @Test
  public void greyscalePPM() {
    // Tests greyscale with red component
    this.controller.loadImage(new File("test/testFiles/testOne.ppm"));
    assertEquals("testOne.ppm", this.controller.getCurrentImageName());

    this.controller.greyscale("red");
    assertEquals("testOnegrred.ppm", this.controller.getCurrentImageName());

    Pixel[][] redGrey1 = this.files1.get("testOnegrred.ppm");

    assertEquals(new Pixel(255, 255, 255), redGrey1[0][0]);
    assertEquals(new Pixel(255, 255, 255), redGrey1[0][1]);
    assertEquals(new Pixel(255, 255, 255), redGrey1[0][2]);
    assertEquals(new Pixel(255, 255, 255), redGrey1[1][0]);
    assertEquals(new Pixel(0, 0, 0), redGrey1[1][1]);
    assertEquals(new Pixel(125, 125, 125), redGrey1[1][2]);

    // Tests greyscale with green component

    this.controller.loadImage(new File("test/testFiles/testOne.ppm"));
    assertEquals("testOne.ppm", this.controller.getCurrentImageName());

    this.controller.greyscale("green");
    assertEquals("testOnegrgreen.ppm", this.controller.getCurrentImageName());

    Pixel[][] greenGrey1 = this.files1.get("testOnegrgreen.ppm");

    assertEquals(new Pixel(0, 0, 0), greenGrey1[0][0]);
    assertEquals(new Pixel(0, 0, 0), greenGrey1[0][1]);
    assertEquals(new Pixel(125, 125, 125), greenGrey1[0][2]);
    assertEquals(new Pixel(255, 255, 255), greenGrey1[1][0]);
    assertEquals(new Pixel(255, 255, 255), greenGrey1[1][1]);
    assertEquals(new Pixel(0, 0, 0), greenGrey1[1][2]);

    // Tests greyscale with blue component

    this.controller.loadImage(new File("test/testFiles/testOne.ppm"));
    assertEquals("testOne.ppm", this.controller.getCurrentImageName());

    this.controller.greyscale("blue");
    assertEquals("testOnegrblue.ppm", this.controller.getCurrentImageName());

    Pixel[][] blueGrey1 = this.files1.get("testOnegrblue.ppm");

    assertEquals(new Pixel(125, 125, 125), blueGrey1[0][0]);
    assertEquals(new Pixel(255, 255, 255), blueGrey1[0][1]);
    assertEquals(new Pixel(0, 0, 0), blueGrey1[0][2]);
    assertEquals(new Pixel(0, 0, 0), blueGrey1[1][0]);
    assertEquals(new Pixel(0, 0, 0), blueGrey1[1][1]);
    assertEquals(new Pixel(255, 255, 255), blueGrey1[1][2]);

    // Tests greyscale with value component

    this.controller.loadImage(new File("test/testFiles/testOne.ppm"));
    assertEquals("testOne.ppm", this.controller.getCurrentImageName());

    this.controller.greyscale("value");
    assertEquals("testOnegrvalue.ppm", this.controller.getCurrentImageName());
    Pixel[][] valueGrey1 = this.files1.get("testOnegrvalue.ppm");

    assertEquals(new Pixel(255, 255, 255), valueGrey1[0][0]);
    assertEquals(new Pixel(255, 255, 255), valueGrey1[0][1]);
    assertEquals(new Pixel(255, 255, 255), valueGrey1[0][2]);
    assertEquals(new Pixel(255, 255, 255), valueGrey1[1][0]);
    assertEquals(new Pixel(255, 255, 255), valueGrey1[1][1]);
    assertEquals(new Pixel(255, 255, 255), valueGrey1[1][2]);

    // Tests greyscale with intensity component

    this.controller.loadImage(new File("test/testFiles/testOne.ppm"));
    assertEquals("testOne.ppm", this.controller.getCurrentImageName());

    this.controller.greyscale("intensity");
    assertEquals("testOnegrintensity.ppm", this.controller.getCurrentImageName());
    Pixel[][] intensityGrey1 = this.files1.get("testOnegrintensity.ppm");

    assertEquals(new Pixel(380 / 3, 380 / 3, 380 / 3), intensityGrey1[0][0]);
    assertEquals(new Pixel(510 / 3, 510 / 3, 510 / 3), intensityGrey1[0][1]);
    assertEquals(new Pixel(380 / 3, 380 / 3, 380 / 3), intensityGrey1[0][2]);
    assertEquals(new Pixel(510 / 3, 510 / 3, 510 / 3), intensityGrey1[1][0]);
    assertEquals(new Pixel(255 / 3, 255 / 3, 255 / 3), intensityGrey1[1][1]);
    assertEquals(new Pixel(380 / 3, 380 / 3, 380 / 3), intensityGrey1[1][2]);

    // Tests greyscale with luma component
    this.controller.loadImage(new File("test/testFiles/testOne.ppm"));
    assertEquals("testOne.ppm", this.controller.getCurrentImageName());

    this.controller.greyscale("luma");
    assertEquals("testOnegrluma.ppm", this.controller.getCurrentImageName());
    Pixel[][] lumaGrey1 = this.files1.get("testOnegrluma.ppm");

    assertEquals(new Pixel(63, 63, 63), lumaGrey1[0][0]);
    assertEquals(new Pixel(72, 72, 72), lumaGrey1[0][1]);
    assertEquals(new Pixel(143, 143, 143), lumaGrey1[0][2]);
    assertEquals(new Pixel(236, 236, 236), lumaGrey1[1][0]);
    assertEquals(new Pixel(182, 182, 182), lumaGrey1[1][1]);
    assertEquals(new Pixel(44, 44, 44), lumaGrey1[1][2]);

    // mock for greyscale
    this.mockController.loadImage(new File("test/testFiles/testTwo2.ppm"));
    this.mockController.greyscale("red");
    this.mockController.greyscale("blue");
    this.mockController.greyscale("green");
    this.mockController.greyscale("luma");
    this.mockController.greyscale("value");
    this.mockController.greyscale("intensity");
    assertEquals("Added Features! \n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Loaded the image as testTwo2.ppm\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Greyscaled the image as testTwo2grred.ppm\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Greyscaled the image as testTwo2grredgrblue.ppm\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Greyscaled the image as " +
                    "testTwo2grredgrbluegrgreen.ppm\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Greyscaled the image as " +
                    "testTwo2grredgrbluegrgreengrluma.ppm\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Greyscaled the image as " +
                    "testTwo2grredgrbluegrgreengrlumagrvalue.ppm\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Greyscaled the image as " +
                    "testTwo2grredgrbluegrgreengrlumagrvaluegrintensity.ppm\n",
            this.log.toString());
  }

  @Test
  public void greyscaleJPEG() {
    // Tests greyscale with red component

    this.controller.loadImage(new File("test/testFiles/test.jpeg"));
    assertEquals("test.jpeg", this.controller.getCurrentImageName());

    this.controller.greyscale("red");
    assertEquals("testgrred.jpeg", this.controller.getCurrentImageName());

    Pixel[][] redGrey1 = this.files1.get("testgrred.jpeg");

    assertEquals(new Pixel(144, 144, 144), redGrey1[0][0]);
    assertEquals(new Pixel(177, 177, 177), redGrey1[0][1]);
    assertEquals(new Pixel(248, 248, 248), redGrey1[1][0]);
    assertEquals(new Pixel(228, 228, 228), redGrey1[1][1]);

    // Tests greyscale with green component

    this.controller.loadImage(new File("test/testFiles/test.jpeg"));
    assertEquals("test.jpeg", this.controller.getCurrentImageName());

    this.controller.greyscale("green");
    assertEquals("testgrgreen.jpeg", this.controller.getCurrentImageName());

    Pixel[][] greenGrey1 = this.files1.get("testgrgreen.jpeg");

    assertEquals(new Pixel(68, 68, 68), greenGrey1[0][0]);
    assertEquals(new Pixel(101, 101, 101), greenGrey1[0][1]);
    assertEquals(new Pixel(172, 172, 172), greenGrey1[1][0]);
    assertEquals(new Pixel(152, 152, 152), greenGrey1[1][1]);

    // Tests greyscale with blue component

    this.controller.loadImage(new File("test/testFiles/test.jpeg"));
    assertEquals("test.jpeg", this.controller.getCurrentImageName());

    this.controller.greyscale("blue");
    assertEquals("testgrblue.jpeg", this.controller.getCurrentImageName());

    Pixel[][] blueGrey1 = this.files1.get("testgrblue.jpeg");

    assertEquals(new Pixel(36, 36, 36), blueGrey1[0][0]);
    assertEquals(new Pixel(69, 69, 69), blueGrey1[0][1]);
    assertEquals(new Pixel(140, 140, 140), blueGrey1[1][0]);
    assertEquals(new Pixel(120, 120, 120), blueGrey1[1][1]);

    // Tests greyscale with value component

    this.controller.loadImage(new File("test/testFiles/test.jpeg"));
    assertEquals("test.jpeg", this.controller.getCurrentImageName());

    this.controller.greyscale("value");
    assertEquals("testgrvalue.jpeg", this.controller.getCurrentImageName());

    Pixel[][] valueGrey1 = this.files1.get("testgrvalue.jpeg");

    assertEquals(new Pixel(144, 144, 144), valueGrey1[0][0]);
    assertEquals(new Pixel(177, 177, 177), valueGrey1[0][1]);
    assertEquals(new Pixel(248, 248, 248), valueGrey1[1][0]);
    assertEquals(new Pixel(228, 228, 228), valueGrey1[1][1]);


    // Tests greyscale with intensity component

    this.controller.loadImage(new File("test/testFiles/test.jpeg"));
    assertEquals("test.jpeg", this.controller.getCurrentImageName());

    this.controller.greyscale("intensity");
    assertEquals("testgrintensity.jpeg", this.controller.getCurrentImageName());

    Pixel[][] intensityGrey1 = this.files1.get("testgrintensity.jpeg");

    assertEquals(new Pixel(82, 82, 82), intensityGrey1[0][0]);
    assertEquals(new Pixel(115, 115, 115), intensityGrey1[0][1]);
    assertEquals(new Pixel(186, 186, 186), intensityGrey1[1][0]);
    assertEquals(new Pixel(166, 166, 166), intensityGrey1[1][1]);

    // Tests greyscale with luma component

    this.controller.loadImage(new File("test/testFiles/test.jpeg"));
    assertEquals("test.jpeg", this.controller.getCurrentImageName());

    this.controller.greyscale("luma");
    assertEquals("testgrluma.jpeg", this.controller.getCurrentImageName());


    Pixel[][] lumaGrey1 = this.files1.get("testgrluma.jpeg");

    assertEquals(new Pixel(81, 81, 81), lumaGrey1[0][0]);
    assertEquals(new Pixel(114, 114, 114), lumaGrey1[0][1]);
    assertEquals(new Pixel(185, 185, 185), lumaGrey1[1][0]);
    assertEquals(new Pixel(165, 165, 165), lumaGrey1[1][1]);

    // mock for greyscale
    this.mockController.loadImage(new File("test/testFiles/testTwo2.jpeg"));
    this.mockController.greyscale("red");
    this.mockController.greyscale("blue");
    this.mockController.greyscale("green");
    this.mockController.greyscale("luma");
    this.mockController.greyscale("value");
    this.mockController.greyscale("intensity");
    assertEquals("Added Features! \n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Loaded the image as testTwo2.jpeg\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Greyscaled the image as testTwo2grred.jpeg\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Greyscaled the image as testTwo2grredgrblue.jpeg\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Greyscaled the image as " +
                    "testTwo2grredgrbluegrgreen.jpeg\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Greyscaled the image as " +
                    "testTwo2grredgrbluegrgreengrluma.jpeg\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Greyscaled the image as " +
                    "testTwo2grredgrbluegrgreengrlumagrvalue.jpeg\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Greyscaled the image as " +
                    "testTwo2grredgrbluegrgreengrlumagrvaluegrintensity.jpeg\n",
            this.log.toString());
  }


  /*
   * For these tests, I'm loading an image and then applying a certain filter to make
   * sure that the output file contains the correct pixels.
   */

  // tests the blur filter on a bmp.
  @Test
  public void filterBlurBMP() {
    this.controller.loadImage(new File("test/testFiles/testTwo2.bmp"));
    assertEquals("testTwo2.bmp", this.controller.getCurrentImageName());
    this.controller.filter("blur");
    assertEquals("testTwo2blur.bmp", this.controller.getCurrentImageName());

    Pixel[][] blurryBMPResult = this.files1.get("testTwo2blur.bmp");

    assertEquals(new Pixel(29, 29, 34), blurryBMPResult[0][0]);
    assertEquals(new Pixel(41, 40, 47), blurryBMPResult[0][1]);
    assertEquals(new Pixel(30, 42, 50), blurryBMPResult[1][0]);
    assertEquals(new Pixel(45, 64, 68), blurryBMPResult[1][1]);

    // non-existent
    this.mockController.loadImage(new File("thisfileisfake"));
    this.mockController.filter("blur");
    assertEquals("Added Features! \n" +
                    "Rendered a message: Could not load file\n" +
                    "Rendered a message: Must load an image before you can manipulate it!\n",
            this.log.toString());
    // mock for filter blur
    this.mockController.loadImage(new File("test/testFiles/testTwo2.bmp"));
    this.mockController.filter("blur");
    assertEquals("Added Features! \n" +
                    "Rendered a message: Could not load file\n" +
                    "Rendered a message: Must load an image before you can manipulate it!\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Loaded the image as testTwo2.bmp\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Applied blur to the image as testTwo2blur.bmp\n",
            this.log.toString());
  }

  // tests the sharpen filter on a bmp
  @Test
  public void filterSharpenBMP() {
    this.controller.loadImage(new File("test/testFiles/testTwo2.bmp"));
    assertEquals("testTwo2.bmp", this.controller.getCurrentImageName());
    this.controller.filter("sharpen");
    assertEquals("testTwo2sharpen.bmp", this.controller.getCurrentImageName());

    Pixel[][] sharpenBMPResult = this.files1.get("testTwo2sharpen.bmp");

    assertEquals(new Pixel(94, 102, 106), sharpenBMPResult[0][0]);
    assertEquals(new Pixel(124, 100, 132), sharpenBMPResult[0][1]);
    assertEquals(new Pixel(82, 109, 144), sharpenBMPResult[1][0]);
    assertEquals(new Pixel(157, 240, 239), sharpenBMPResult[1][1]);

    // non-existent
    this.mockController.loadImage(new File("thisfileisfake"));
    this.mockController.filter("sharpen");
    assertEquals("Added Features! \n" +
                    "Rendered a message: Could not load file\n" +
                    "Rendered a message: Must load an image before you can manipulate it!\n",
            this.log.toString());

    // mock for filter sharpen
    this.mockController.loadImage(new File("test/testFiles/testTwo2.bmp"));
    this.mockController.filter("sharpen");
    assertEquals("Added Features! \n" +
                    "Rendered a message: Could not load file\n" +
                    "Rendered a message: Must load an image before you can manipulate it!\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Loaded the image as testTwo2.bmp\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Applied sharpen to the image as testTwo2sharpen.bmp\n",
            this.log.toString());

  }

  // tests the blur filter on a PNG
  @Test
  public void filterBlurPNG() {
    this.controller.loadImage(new File("test/testFiles/testTwo2.png"));
    assertEquals("testTwo2.png", this.controller.getCurrentImageName());
    this.controller.filter("blur");
    assertEquals("testTwo2blur.png", this.controller.getCurrentImageName());

    Pixel[][] blurryPNGResult = this.files1.get("testTwo2blur.png");

    assertEquals(new Pixel(29, 29, 34), blurryPNGResult[0][0]);
    assertEquals(new Pixel(41, 40, 47), blurryPNGResult[0][1]);
    assertEquals(new Pixel(30, 42, 50), blurryPNGResult[1][0]);
    assertEquals(new Pixel(45, 64, 68), blurryPNGResult[1][1]);

    // mock for filter blur
    this.mockController.loadImage(new File("test/testFiles/testTwo2.png"));
    this.mockController.filter("blur");
    assertEquals("Added Features! \n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Loaded the image as testTwo2.png\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Applied blur to the image as testTwo2blur.png\n",
            this.log.toString());

  }

  // tests the sharpen filter on a PNG
  @Test
  public void filterSharpenPNG() {
    this.controller.loadImage(new File("test/testFiles/testTwo2.png"));
    assertEquals("testTwo2.png", this.controller.getCurrentImageName());
    this.controller.filter("sharpen");
    assertEquals("testTwo2sharpen.png", this.controller.getCurrentImageName());

    Pixel[][] sharpenPNGResult = this.files1.get("testTwo2sharpen.png");

    assertEquals(new Pixel(94, 102, 106), sharpenPNGResult[0][0]);
    assertEquals(new Pixel(124, 100, 132), sharpenPNGResult[0][1]);
    assertEquals(new Pixel(82, 109, 144), sharpenPNGResult[1][0]);
    assertEquals(new Pixel(157, 240, 239), sharpenPNGResult[1][1]);

    // mock for filter sharpen
    this.mockController.loadImage(new File("test/testFiles/testTwo2.png"));
    this.mockController.filter("sharpen");
    assertEquals("Added Features! \n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Loaded the image as testTwo2.png\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Applied sharpen to the image as testTwo2sharpen.png\n",
            this.log.toString());

  }

  // tests the blur filter on a PPM
  @Test
  public void filterBlurPPM() {
    this.controller.loadImage(new File("test/testFiles/testTwo2.ppm"));
    assertEquals("testTwo2.ppm", this.controller.getCurrentImageName());
    this.controller.filter("blur");
    assertEquals("testTwo2blur.ppm", this.controller.getCurrentImageName());

    Pixel[][] blurryPPMResult = this.files1.get("testTwo2blur.ppm");

    assertEquals(new Pixel(29, 29, 34), blurryPPMResult[0][0]);
    assertEquals(new Pixel(41, 40, 47), blurryPPMResult[0][1]);
    assertEquals(new Pixel(30, 42, 50), blurryPPMResult[1][0]);
    assertEquals(new Pixel(45, 64, 68), blurryPPMResult[1][1]);

    // mock for filter blur
    this.mockController.loadImage(new File("test/testFiles/testTwo2.ppm"));
    this.mockController.filter("blur");
    assertEquals("Added Features! \n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Loaded the image as testTwo2.ppm\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Applied blur to the image as testTwo2blur.ppm\n",
            this.log.toString());
  }

  // tests the sharpen filter on a PPM
  @Test
  public void filterSharpenPPM() {
    this.controller.loadImage(new File("test/testFiles/testTwo2.ppm"));
    assertEquals("testTwo2.ppm", this.controller.getCurrentImageName());
    this.controller.filter("sharpen");
    assertEquals("testTwo2sharpen.ppm", this.controller.getCurrentImageName());

    Pixel[][] sharpenPPMResult = this.files1.get("testTwo2sharpen.ppm");

    assertEquals(new Pixel(94, 102, 106), sharpenPPMResult[0][0]);
    assertEquals(new Pixel(124, 100, 132), sharpenPPMResult[0][1]);
    assertEquals(new Pixel(82, 109, 144), sharpenPPMResult[1][0]);
    assertEquals(new Pixel(157, 240, 239), sharpenPPMResult[1][1]);

    // mock for filter sharpen
    this.mockController.loadImage(new File("test/testFiles/testTwo2.ppm"));
    this.mockController.filter("sharpen");
    assertEquals("Added Features! \n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Loaded the image as testTwo2.ppm\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Applied sharpen to the image as testTwo2sharpen.ppm\n",
            this.log.toString());
  }

  // tests the blur filter on a JPEG
  @Test
  public void filterBlurJPEG() {
    this.controller.loadImage(new File("test/testFiles/test.jpeg"));
    assertEquals("test.jpeg", this.controller.getCurrentImageName());
    this.controller.filter("blur");
    assertEquals("testblur.jpeg", this.controller.getCurrentImageName());

    Pixel[][] blurResult = this.files1.get("testblur.jpeg");

    assertEquals(new Pixel(103, 60, 42), blurResult[0][0]);
    assertEquals(new Pixel(137, 78, 70), blurResult[0][1]);
    assertEquals(new Pixel(119, 76, 58), blurResult[1][0]);
    assertEquals(new Pixel(150, 90, 82), blurResult[1][1]);

    // mock for filter blur
    this.mockController.loadImage(new File("test/testFiles/testTwo2.jpeg"));
    this.mockController.filter("blur");
    assertEquals("Added Features! \n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Loaded the image as testTwo2.jpeg\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Applied blur to the image as testTwo2blur.jpeg\n",
            this.log.toString());

  }

  // tests the sharpen filter on a jpeg
  @Test
  public void filterSharpenJPEG() {
    this.controller.loadImage(new File("test/testFiles/test.jpeg"));
    assertEquals("test.jpeg", this.controller.getCurrentImageName());
    this.controller.filter("sharpen");
    assertEquals("testsharpen.jpeg", this.controller.getCurrentImageName());

    Pixel[][] sharpenJPEGResult = this.files1.get("testsharpen.jpeg");

    assertEquals(new Pixel(255, 155, 85), sharpenJPEGResult[0][0]);
    assertEquals(new Pixel(255, 237, 208), sharpenJPEGResult[0][1]);
    assertEquals(new Pixel(255, 233, 163), sharpenJPEGResult[1][0]);
    assertEquals(new Pixel(255, 255, 246), sharpenJPEGResult[1][1]);

    // mock for filter sharpen
    this.mockController.loadImage(new File("test/testFiles/testTwo2.jpeg"));
    this.mockController.filter("sharpen");
    assertEquals("Added Features! \n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Loaded the image as testTwo2.jpeg\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Applied sharpen to the image as testTwo2sharpen.jpeg\n",
            this.log.toString());

  }

  // tests the transform sepia on a bmp
  @Test
  public void transformSepiaBMP() {
    this.controller.loadImage(new File("test/testFiles/testTwo2.bmp"));
    assertEquals("testTwo2.bmp", this.controller.getCurrentImageName());

    this.controller.transform("sepia");
    assertEquals("testTwo2sepia.bmp", this.controller.getCurrentImageName());

    Pixel[][] sepiaBMPResult = this.files1.get("testTwo2sepia.bmp");

    assertEquals(new Pixel(43, 38, 30), sepiaBMPResult[0][0]);
    assertEquals(new Pixel(63, 56, 44), sepiaBMPResult[0][1]);
    assertEquals(new Pixel(54, 48, 38), sepiaBMPResult[1][0]);
    assertEquals(new Pixel(251, 224, 176), sepiaBMPResult[1][1]);

    // non-existent
    this.mockController.loadImage(new File("thisfileisfake"));
    this.mockController.transform("sepia");
    assertEquals("Added Features! \n" +
                    "Rendered a message: Could not load file\n" +
                    "Rendered a message: Must load an image before you can manipulate it!\n",
            this.log.toString());

    // mock for filter sepia
    this.mockController.loadImage(new File("test/testFiles/testTwo2.bmp"));
    this.mockController.transform("sepia");
    assertEquals("Added Features! \n" +
                    "Rendered a message: Could not load file\n" +
                    "Rendered a message: Must load an image before you can manipulate it!\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Loaded the image as testTwo2.bmp\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Applied sepia to the image as testTwo2sepia.bmp\n",
            this.log.toString());


  }


  // tests the transform sepia on a png
  @Test
  public void transformSepiaPNG() {
    this.controller.loadImage(new File("test/testFiles/testTwo2.png"));
    assertEquals("testTwo2.png", this.controller.getCurrentImageName());

    this.controller.transform("sepia");
    assertEquals("testTwo2sepia.png", this.controller.getCurrentImageName());

    Pixel[][] sepiaPNGResult = this.files1.get("testTwo2sepia.png");

    assertEquals(new Pixel(43, 38, 30), sepiaPNGResult[0][0]);
    assertEquals(new Pixel(63, 56, 44), sepiaPNGResult[0][1]);
    assertEquals(new Pixel(54, 48, 38), sepiaPNGResult[1][0]);
    assertEquals(new Pixel(251, 224, 176), sepiaPNGResult[1][1]);

    // mock for filter sepia
    this.mockController.loadImage(new File("test/testFiles/testTwo2.png"));
    this.mockController.transform("sepia");
    assertEquals("Added Features! \n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Loaded the image as testTwo2.png\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Applied sepia to the image as testTwo2sepia.png\n",
            this.log.toString());

  }

  // tests the transform sepia on a ppm
  @Test
  public void transformSepiaPPM() {
    this.controller.loadImage(new File("test/testFiles/testTwo2.ppm"));
    assertEquals("testTwo2.ppm", this.controller.getCurrentImageName());

    this.controller.transform("sepia");
    assertEquals("testTwo2sepia.ppm", this.controller.getCurrentImageName());

    Pixel[][] sepiaPPMResult = this.files1.get("testTwo2sepia.ppm");

    assertEquals(new Pixel(43, 38, 30), sepiaPPMResult[0][0]);
    assertEquals(new Pixel(63, 56, 44), sepiaPPMResult[0][1]);
    assertEquals(new Pixel(54, 48, 38), sepiaPPMResult[1][0]);
    assertEquals(new Pixel(251, 224, 176), sepiaPPMResult[1][1]);

    // mock for filter sepia
    this.mockController.loadImage(new File("test/testFiles/testTwo2.ppm"));
    this.mockController.transform("sepia");
    assertEquals("Added Features! \n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Loaded the image as testTwo2.ppm\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Applied sepia to the image as testTwo2sepia.ppm\n",
            this.log.toString());
  }

  // tests the transform sepia on a jpeg
  @Test
  public void transformSepiaJPEG() {

    this.controller.loadImage(new File("test/testFiles/test.jpeg"));
    assertEquals("test.jpeg", this.controller.getCurrentImageName());

    this.controller.transform("sepia");
    assertEquals("testsepia.jpeg", this.controller.getCurrentImageName());

    Pixel[][] sepiaJPEGResult = this.files1.get("testsepia.jpeg");

    assertEquals(new Pixel(115, 102, 80), sepiaJPEGResult[0][0]);
    assertEquals(new Pixel(160, 142, 112), sepiaJPEGResult[0][1]);
    assertEquals(new Pixel(255, 228, 179), sepiaJPEGResult[1][0]);
    assertEquals(new Pixel(229, 204, 160), sepiaJPEGResult[1][1]);

    // mock for filter sepia
    this.mockController.loadImage(new File("test/testFiles/testTwo2.jpeg"));
    this.mockController.transform("sepia");
    assertEquals("Added Features! \n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Loaded the image as testTwo2.jpeg\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Applied sepia to the image as testTwo2sepia.jpeg\n",
            this.log.toString());

  }

  // tests the transform greyscale on a bmp
  @Test
  public void transformGreyscaleBMP() {
    this.controller.loadImage(new File("test/testFiles/testTwo2.bmp"));
    assertEquals("testTwo2.bmp", this.controller.getCurrentImageName());

    this.controller.transform("greyscale");
    assertEquals("testTwo2greyscale.bmp", this.controller.getCurrentImageName());

    Pixel[][] greyscaleBMPResult = this.files1.get("testTwo2greyscale.bmp");

    assertEquals(new Pixel(31, 31, 31), greyscaleBMPResult[0][0]);
    assertEquals(new Pixel(41, 41, 41), greyscaleBMPResult[0][1]);
    assertEquals(new Pixel(39, 39, 39), greyscaleBMPResult[1][0]);
    assertEquals(new Pixel(194, 194, 194), greyscaleBMPResult[1][1]);

    // non-existent
    this.mockController.loadImage(new File("thisfileisfake"));
    this.mockController.transform("greyscale");
    assertEquals("Added Features! \n" +
                    "Rendered a message: Could not load file\n" +
                    "Rendered a message: Must load an image before you can manipulate it!\n",
            this.log.toString());

    // mock for filter greyscale
    this.mockController.loadImage(new File("test/testFiles/testTwo2.bmp"));
    this.mockController.transform("greyscale");
    assertEquals("Added Features! \n" +
                    "Rendered a message: Could not load file\n" +
                    "Rendered a message: Must load an image before you can manipulate it!\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Loaded the image as testTwo2.bmp\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Applied greyscale to the image as testTwo2greyscale.bmp\n",
            this.log.toString());

  }

  // tests the transform greyscale on a png
  @Test
  public void transformGreyscalePNG() {
    this.controller.loadImage(new File("test/testFiles/testTwo2.png"));
    assertEquals("testTwo2.png", this.controller.getCurrentImageName());

    this.controller.transform("greyscale");
    assertEquals("testTwo2greyscale.png", this.controller.getCurrentImageName());

    Pixel[][] greyscalePNGResult = this.files1.get("testTwo2greyscale.png");

    assertEquals(new Pixel(31, 31, 31), greyscalePNGResult[0][0]);
    assertEquals(new Pixel(41, 41, 41), greyscalePNGResult[0][1]);
    assertEquals(new Pixel(39, 39, 39), greyscalePNGResult[1][0]);
    assertEquals(new Pixel(194, 194, 194), greyscalePNGResult[1][1]);

    // mock for filter greyscale
    this.mockController.loadImage(new File("test/testFiles/testTwo2.png"));
    this.mockController.transform("greyscale");
    assertEquals("Added Features! \n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Loaded the image as testTwo2.png\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Applied greyscale to the image as testTwo2greyscale.png\n",
            this.log.toString());

  }

  // tests the transform greyscale on a ppm
  @Test
  public void transformGreyscalePPM() {
    this.controller.loadImage(new File("test/testFiles/testTwo2.ppm"));
    assertEquals("testTwo2.ppm", this.controller.getCurrentImageName());

    this.controller.transform("greyscale");
    assertEquals("testTwo2greyscale.ppm", this.controller.getCurrentImageName());

    Pixel[][] greyscalePPMResult = this.files1.get("testTwo2greyscale.ppm");

    assertEquals(new Pixel(31, 31, 31), greyscalePPMResult[0][0]);
    assertEquals(new Pixel(41, 41, 41), greyscalePPMResult[0][1]);
    assertEquals(new Pixel(39, 39, 39), greyscalePPMResult[1][0]);
    assertEquals(new Pixel(194, 194, 194), greyscalePPMResult[1][1]);

    // mock for filter greyscale
    this.mockController.loadImage(new File("test/testFiles/testTwo2.ppm"));
    this.mockController.transform("greyscale");
    assertEquals("Added Features! \n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Loaded the image as testTwo2.ppm\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Applied greyscale to the image as testTwo2greyscale.ppm\n",
            this.log.toString());
  }

  // tests the transform greyscale on a jpeg
  @Test
  public void transformGreyscaleJPEG() {
    this.controller.loadImage(new File("test/testFiles/test.jpeg"));
    assertEquals("test.jpeg", this.controller.getCurrentImageName());

    this.controller.transform("greyscale");
    assertEquals("testgreyscale.jpeg", this.controller.getCurrentImageName());

    Pixel[][] greyscaleJPEGResult = this.files1.get("testgreyscale.jpeg");

    assertEquals(new Pixel(81, 81, 81), greyscaleJPEGResult[0][0]);
    assertEquals(new Pixel(114, 114, 114), greyscaleJPEGResult[0][1]);
    assertEquals(new Pixel(185, 185, 185), greyscaleJPEGResult[1][0]);
    assertEquals(new Pixel(165, 165, 165), greyscaleJPEGResult[1][1]);

    // mock for filter greyscale
    this.mockController.loadImage(new File("test/testFiles/testTwo2.jpeg"));
    this.mockController.transform("greyscale");
    assertEquals("Added Features! \n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Loaded the image as testTwo2.jpeg\n" +
                    "Repainted the image.\n" +
                    "Rendered a message: Applied greyscale to the image as" +
                    " testTwo2greyscale.jpeg\n",
            this.log.toString());

  }

  // Tests the getCurrentImageName method
  @Test
  public void getCurrentImageName() {
    // loading in a normal file
    this.controller.loadImage(new File("test/testFiles/testTwo2.ppm"));
    assertEquals("testTwo2.ppm", this.controller.getCurrentImageName());
    // after performing some operation
    this.controller.brighten(10);
    assertEquals("testTwo2br.ppm", this.controller.getCurrentImageName());
    // after performing another operation
    this.controller.horizontalFlip();
    assertEquals("testTwo2brhor.ppm", this.controller.getCurrentImageName());
    // after loading another image onto the controller
    this.controller.loadImage(new File("test/testFiles/test.jpeg"));
    assertEquals("test.jpeg", this.controller.getCurrentImageName());

  }

}