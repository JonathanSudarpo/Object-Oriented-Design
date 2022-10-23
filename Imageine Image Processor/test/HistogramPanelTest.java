import org.junit.Before;
import org.junit.Test;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

import model.EvenBetterImageManipulatorModel;
import model.EvenBetterImageManipulatorModelImpl;
import model.EvenBetterImageManipulatorViewModel;
import model.ImageManipulatorViewModel;
import view.HistogramPanel;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;

/**
 * Tests for the HistogramPanel class.
 */
public class HistogramPanelTest {

  BufferedImage bi;
  Graphics2D g2;
  EvenBetterImageManipulatorModel model0;
  ImageManipulatorViewModel model;
  HistogramPanel histogramPanel;

  // Initializing the previously defined variables.
  @Before
  public void init() {
    this.bi = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
    this.g2 = bi.createGraphics();
    this.model0 = new EvenBetterImageManipulatorModelImpl();
    this.model = new EvenBetterImageManipulatorViewModel(this.model0);
    this.histogramPanel = new HistogramPanel(this.model);
  }

  // testing the invalid construction of a HistogramPanel
  @Test
  public void invalidConstructor() {
    try {
      this.histogramPanel = new HistogramPanel(null);
      fail("Should have thrown an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Cannot have null model", e.getMessage());
    }
  }

}