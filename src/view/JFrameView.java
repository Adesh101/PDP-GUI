package view;


import controller.GUIController;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * A public class for JFrame view.
 */
public class JFrameView extends JFrame implements IView {

  private JLabel display;
  private JButton echoButton, exitButton,toggleButton;
  private JTextField input;

  /**
   * A public constructor for JFrameView.
   * @param caption string
   */
  public JFrameView(String caption) {
    super(caption);

    setSize(500, 300);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // this.setResizable(false);
    // this.setMinimumSize(new Dimension(300,300));


    this.setLayout(new FlowLayout());

    display = new JLabel("To be displayed");
    //label = new JLabel(new ImageIcon("Jellyfish.JPG"));


    this.add(display);

    //the textfield
    input = new JTextField(10);
    this.add(input);

    //echobutton
    echoButton = new JButton("Echo");
    echoButton.setActionCommand("Echo Button");
    this.add(echoButton);

    //toggle color button
    toggleButton = new JButton("Toggle color");
    toggleButton.setActionCommand("Toggle color");
    this.add(toggleButton);

    //exit button
    exitButton = new JButton("Exit");
    exitButton.setActionCommand("Exit Button");
    this.add(exitButton);


    pack();
    setVisible(true);
  }

  @Override
  public void showWelcomeMessage() {

  }

  @Override
  public void showError() {

  }

  @Override
  public void showMenu() {

  }

  @Override
  public void addFeatures(GUIController guiController) {

  }
}
