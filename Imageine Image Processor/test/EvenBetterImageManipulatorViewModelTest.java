import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.io.File;

import controller.ImageManipulatorGUIController;
import model.EvenBetterImageManipulatorModel;
import model.EvenBetterImageManipulatorModelImpl;
import model.EvenBetterImageManipulatorViewModel;
import view.ImageManipulatorSwingView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests for the EvenBetterImageManipulatorViewModel class.
 */
public class EvenBetterImageManipulatorViewModelTest {

  EvenBetterImageManipulatorModel model;
  EvenBetterImageManipulatorModel modelNull;
  EvenBetterImageManipulatorViewModel viewModel;
  ImageManipulatorSwingView view;
  ImageManipulatorGUIController controller;

  // initializing the previously defined variables.
  @Before
  public void init() {
    this.model = new EvenBetterImageManipulatorModelImpl();
    this.modelNull = null;
    this.viewModel = new EvenBetterImageManipulatorViewModel(this.model);
    this.view = new ImageManipulatorSwingView(this.viewModel);
    this.controller = new ImageManipulatorGUIController(this.model, this.view);
  }


  // Tests for the invalid construction of an object of this class.
  @Test
  public void invalidConstructor() {
    try {
      this.viewModel = new EvenBetterImageManipulatorViewModel(this.modelNull);
      fail("Should have thrown an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Cannot have null model", e.getMessage());
    }

  }

  // tests for valid construction.
  @Test
  public void validConstructor() {
    EvenBetterImageManipulatorModelImpl model = new EvenBetterImageManipulatorModelImpl();
    this.model = model;
    EvenBetterImageManipulatorViewModel viewModel = new EvenBetterImageManipulatorViewModel(
            this.model);
    this.viewModel = viewModel;

    assertEquals(this.viewModel, viewModel);
  }

  // tests the getColorAt() method.
  @Test
  public void getColorAt() {
    this.controller.loadImage(new File("test/testFiles/drake.jpeg"));
    // out of bounds.
    try {
      this.viewModel.getColorAt(-1, -1, "drake.jpeg");
      fail("Should have thrown an ArrayIndexOutOfBoundsException");
    } catch (ArrayIndexOutOfBoundsException e) {
      assertEquals("Index -1 out of bounds for length 1126", e.getMessage());
    }

    // jpeg
    assertEquals(new Color(16, 39, 55), this.viewModel.getColorAt(0, 0,
            "drake.jpeg"));

    // bmp
    this.controller.loadImage(new File("test/testFiles/testTwo2.bmp"));
    assertEquals(new Color(123, 215, 201), this.viewModel.getColorAt(1, 1,
            "testTwo2.bmp"));
    // png
    this.controller.loadImage(new File("test/testFiles/testTwo2.png"));
    this.controller.loadImage(new File("test/testFiles/testTwo2.png"));
    assertEquals(new Color(123, 215, 201), this.viewModel.getColorAt(1, 1,
            "testTwo2.png"));
    // ppm
    this.controller.loadImage(new File("test/testFiles/testTwo2.ppm"));
    assertEquals(new Color(38, 31, 23), this.viewModel.getColorAt(0, 0,
            "testTwo2.ppm"));
  }

  // tests the getBoardWidth() method.
  @Test
  public void getBoardWidth() {
    // jpeg
    this.controller.loadImage(new File("test/testFiles/drake.jpeg"));
    assertEquals(875, this.viewModel.getBoardWidth("drake.jpeg"));
    // bmp
    this.controller.loadImage(new File("test/testFiles/testTwo2.bmp"));
    assertEquals(2, this.viewModel.getBoardWidth("testTwo2.bmp"));
    // png
    this.controller.loadImage(new File("test/testFiles/testTwo2.png"));
    assertEquals(2, this.viewModel.getBoardWidth("testTwo2.png"));
    // ppm
    this.controller.loadImage(new File("test/testFiles/testTwo2.ppm"));
    assertEquals(2, this.viewModel.getBoardWidth("testTwo2.ppm"));
  }

  // tests the getBoardHeight() method.
  @Test
  public void getBoardHeight() {
    // jpeg
    this.controller.loadImage(new File("test/testFiles/drake.jpeg"));
    assertEquals(1126, this.viewModel.getBoardHeight("drake.jpeg"));
    // bmp
    this.controller.loadImage(new File("test/testFiles/testTwo2.bmp"));
    assertEquals(2, this.viewModel.getBoardHeight("testTwo2.bmp"));
    // png
    this.controller.loadImage(new File("test/testFiles/testTwo2.png"));
    assertEquals(2, this.viewModel.getBoardHeight("testTwo2.png"));
    // ppm
    this.controller.loadImage(new File("test/testFiles/testTwo2.ppm"));
    assertEquals(2, this.viewModel.getBoardHeight("testTwo2.ppm"));
  }

  // tests the getHistogram() method.
  @Test
  public void getHistogram() {
    int[] histogram1;
    // ppm
    int[] histogramTestTwo2blue = new int[]{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    this.controller.loadImage(new File("test/testFiles/testTwo2.ppm"));
    histogram1 = this.viewModel.getHistogram("blue", "testTwo2.ppm");
    for (int i = 0; i < histogram1.length; i++) {
      assertEquals(histogram1[i], histogramTestTwo2blue[i]);
    }

    // png
    this.controller.loadImage(new File("test/testFiles/testTwo2.png"));
    histogram1 = this.viewModel.getHistogram("blue", "testTwo2.png");
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
    histogram1 = this.viewModel.getHistogram("intensity", "testTwo2.png");
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
    histogram1 = this.viewModel.getHistogram("green", "testTwo2.png");
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
    histogram1 = this.viewModel.getHistogram("value", "testTwo2.png");
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
    histogram1 = this.viewModel.getHistogram("luma", "testTwo2.png");
    for (int i = 0; i < histogram1.length; i++) {
      assertEquals(histogram1[i], histogramTestTwo2Luma[i]);
    }
  }
}