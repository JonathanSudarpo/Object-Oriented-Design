import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.io.File;
import java.util.HashMap;

import controller.ImageManipulatorGUIController;
import model.EvenBetterImageManipulatorModelImpl;
import model.Pixel;
import view.ImageManipulatorGUIView;
import view.ImageManipulatorSwingView;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;

/**
 * Tests for the EvenBetterImageManipulatorModelImpl class.
 */
public class EvenBetterImageManipulatorModelImplTest {

  EvenBetterImageManipulatorModelImpl model;
  EvenBetterImageManipulatorModelImpl modelNull;
  ImageManipulatorGUIController controller;
  ImageManipulatorGUIView view;
  HashMap<String, Pixel[][]> files;

  // Initializing the previously defined variables.
  @Before
  public void init() {
    this.modelNull = null;
    this.model = new EvenBetterImageManipulatorModelImpl();
    this.view = new ImageManipulatorSwingView(this.model);
    this.controller = new ImageManipulatorGUIController(this.model, this.view);
    this.files = new HashMap<String, Pixel[][]>();
  }

  // testing valid construction of an object of the class.
  @Test
  public void validConstructor() {
    this.model = new EvenBetterImageManipulatorModelImpl(this.files);
    HashMap<String, Pixel[][]> files = new HashMap<>();
    assertEquals(this.files, files);

  }

  // Testing invalid construction of an object of the class.
  @Test
  public void invalidConstructor() {
    try {
      this.model = new EvenBetterImageManipulatorModelImpl(null);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Cannot have null fields.", e.getMessage());
    }
  }


  // Tests the getColorAt() method.
  @Test
  public void getColorAt() {
    // jpeg
    this.controller.loadImage(new File("test/testFiles/test.jpeg"));
    assertEquals(new Color(144, 68, 36), this.model.getColorAt(0, 0,
            "test.jpeg"));
    // ppm
    this.controller.loadImage(new File("test/testFiles/testTwo2.ppm"));
    assertEquals(new Color(38, 31, 23), this.model.getColorAt(0, 0,
            "testTwo2.ppm"));
    // png
    this.controller.loadImage(new File("test/testFiles/testTwo2.png"));
    assertEquals(new Color(38, 31, 23), this.model.getColorAt(0, 0,
            "testTwo2.png"));
    // bmp
    this.controller.loadImage(new File("test/testFiles/testTwo2.bmp"));
    assertEquals(new Color(38, 31, 23), this.model.getColorAt(0, 0,
            "testTwo2.bmp"));

  }

  // tests the getBoardWidth() method.
  @Test
  public void getBoardWidth() {
    // jpeg
    this.controller.loadImage(new File("test/testFiles/test.jpeg"));
    assertEquals(3, this.model.getBoardWidth("test.jpeg"));
    // ppm
    this.controller.loadImage(new File("test/testFiles/testTwo2.ppm"));
    assertEquals(2, this.model.getBoardWidth("testTwo2.ppm"));
    // png
    this.controller.loadImage(new File("test/testFiles/testTwo2.png"));
    assertEquals(2, this.model.getBoardWidth("testTwo2.ppm"));
    // bmp
    this.controller.loadImage(new File("test/testFiles/testTwo2.bmp"));
    assertEquals(2, this.model.getBoardWidth("testTwo2.ppm"));
  }

  // tests the getBoardHeight() method.
  @Test
  public void getBoardHeight() {
    // jpeg
    this.controller.loadImage(new File("test/testFiles/drake.jpeg"));
    assertEquals(1126, this.model.getBoardHeight("drake.jpeg"));
    // ppm
    this.controller.loadImage(new File("test/testFiles/testTwo2.ppm"));
    assertEquals(2, this.model.getBoardHeight("testTwo2.ppm"));
    // png
    this.controller.loadImage(new File("test/testFiles/testTwo2.png"));
    assertEquals(2, this.model.getBoardHeight("testTwo2.ppm"));
    // bmp
    this.controller.loadImage(new File("test/testFiles/testTwo2.bmp"));
    assertEquals(2, this.model.getBoardHeight("testTwo2.bmp"));

  }

  // tests getHistogram() method.
  @Test
  public void getHistogram() {
    int[] histogram1;

    // ppm
    int[] histogramTestTwo2blue = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    this.controller.loadImage(new File("test/testFiles/testTwo2.ppm"));
    histogram1 = this.model.getHistogram("blue", "testTwo2.ppm");
    for (int i = 0; i < histogram1.length; i++) {
      assertEquals(histogram1[i], histogramTestTwo2blue[i]);
    }

    // png
    this.controller.loadImage(new File("test/testFiles/testTwo2.png"));
    histogram1 = this.model.getHistogram("blue", "testTwo2.png");
    for (int i = 0; i < histogram1.length; i++) {
      assertEquals(histogram1[i], histogramTestTwo2blue[i]);
    }
    // bmp
    int[] histogramTestTwo2Intensity = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0};
    this.controller.loadImage(new File("test/testFiles/testTwo2.bmp"));
    histogram1 = this.model.getHistogram("intensity", "testTwo2.png");
    for (int i = 0; i < histogram1.length; i++) {
      assertEquals(histogram1[i], histogramTestTwo2Intensity[i]);

    }

    // just testing for the other components

    // green
    int[] histogramTestTwo2Green = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    this.controller.loadImage(new File("test/testFiles/testTwo2.png"));
    histogram1 = this.model.getHistogram("green", "testTwo2.png");
    for (int i = 0; i < histogram1.length; i++) {
      assertEquals(histogram1[i], histogramTestTwo2Green[i]);
    }

    // value
    int[] histogramTestTwo2Value = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    this.controller.loadImage(new File("test/testFiles/testTwo2.png"));
    histogram1 = this.model.getHistogram("value", "testTwo2.png");
    for (int i = 0; i < histogram1.length; i++) {
      assertEquals(histogram1[i], histogramTestTwo2Value[i]);
    }

    // luma
    int[] histogramTestTwo2Luma = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0,
      1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0};
    this.controller.loadImage(new File("test/testFiles/testTwo2.png"));
    histogram1 = this.model.getHistogram("luma", "testTwo2.png");
    for (int i = 0; i < histogram1.length; i++) {
      assertEquals(histogram1[i], histogramTestTwo2Luma[i]);
    }
  }
}

