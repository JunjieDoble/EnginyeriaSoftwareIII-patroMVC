package mvc;

import interfaces.Observer;
import interfaces.TemperatureControllerInterface;
import interfaces.TemperatureModelInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureView implements ActionListener, Observer {
    /* TODO: Ensure that it implements the right interface, other than ActionListener*/

    private TemperatureModelInterface model;
    private TemperatureControllerInterface controller;
    private JFrame viewFrame;
    private JPanel viewPanel;
    private JLabel tempOutputLabel; //Displays target temperature
    private JFrame controlFrame;
    private JPanel controlPanel;
    private JLabel tempLabel;
    private JLabel tempLabelView;
    private JTextField tempTextField;
    private JButton setTempButton; //Set temperature button (Set)
    private JButton increaseTempButton; //Increase temperature button (+)
    private JButton decreaseTempButton; //Decrease temperature button (-)
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem startMenuItem; //Start Menu Item
    private JMenuItem stopMenuItem; //Stop Menu Item
    private JLabel currentTempLabel;
    private JLabel currentTemp; //Displays current temperature

    @Override
    public void update(String current, String target) {
        currentTemp.setText(current);
        tempOutputLabel.setText(target);
    }

    public TemperatureView(TemperatureControllerInterface controller, TemperatureModelInterface model) {
        /*TODO: Complete this constructor. Remember that the view is an observer of the model.*/
        this.controller = controller;
        this.model = model;
    }

    public void createView() {
        // Create all Swing components here
        viewPanel = new JPanel(new GridLayout(1, 2));
        viewFrame = new JFrame("View");
        viewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewFrame.setPreferredSize(new Dimension(300, 160));
        tempOutputLabel = new JLabel("offline", SwingConstants.CENTER);
        tempLabelView = new JLabel("Target Temp", SwingConstants.CENTER);
        currentTempLabel = new JLabel("Current Temp", SwingConstants.CENTER);
        currentTemp = new JLabel("",SwingConstants.CENTER);

        //Font size
        int fontSizeToUse = currentTemp.getFont().getSize();
        fontSizeToUse *= 3;
        Font newFont = new Font(currentTempLabel.getName(), Font.PLAIN, fontSizeToUse);

        currentTemp.setFont(newFont);
        tempOutputLabel.setFont(newFont);

        //Target Temperature
        JPanel targetTempPanel = new JPanel(new GridLayout(2, 1));
        targetTempPanel.add(tempLabelView);
        targetTempPanel.add(tempOutputLabel);
        viewPanel.add(targetTempPanel);


        //Current Temperature
        JPanel currentTempPanel = new JPanel(new GridLayout(2, 1));
        currentTempPanel.add(currentTempLabel);
        currentTempPanel.add(currentTemp);
        viewPanel.add(currentTempPanel);

        viewFrame.getContentPane().add(viewPanel, BorderLayout.CENTER);
        viewFrame.pack();
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        viewFrame.setLocation((int) (screenWidth/2.5), (int)(screenHeight/2.5));
        viewFrame.setVisible(true);
    }

    public void createControls() {
        // Create all Swing components here
        JFrame.setDefaultLookAndFeelDecorated(true);
        controlFrame = new JFrame("Control");
        controlFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        controlFrame.setSize(new Dimension(100, 80));

        controlPanel = new JPanel(new GridLayout(1, 2));

        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        startMenuItem = new JMenuItem("Start");
        menu.add(startMenuItem);
        startMenuItem.addActionListener((event) -> controller.start());

        stopMenuItem = new JMenuItem("Stop");
        menu.add(stopMenuItem);
        stopMenuItem.addActionListener((event) -> controller.stop());

        JMenuItem exit = new JMenuItem("Quit");
        exit.addActionListener((event) -> System.exit(0));


        menu.add(exit);
        menuBar.add(menu);
        controlFrame.setJMenuBar(menuBar);

        tempTextField = new JTextField(2);
        tempLabel = new JLabel("Enter Temperature:", SwingConstants.RIGHT);
        setTempButton = new JButton("Set");
        setTempButton.setSize(new Dimension(10,40));
        increaseTempButton = new JButton("+");
        decreaseTempButton = new JButton("-");
        setTempButton.addActionListener(this);
        increaseTempButton.addActionListener(this);
        decreaseTempButton.addActionListener(this);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));

        buttonPanel.add(decreaseTempButton);
        buttonPanel.add(increaseTempButton);

        JPanel enterPanel = new JPanel(new GridLayout(1, 2));
        enterPanel.add(tempLabel);
        enterPanel.add(tempTextField);
        JPanel insideControlPanel = new JPanel(new GridLayout(3, 1));
        insideControlPanel.add(enterPanel);
        insideControlPanel.add(setTempButton);
        insideControlPanel.add(buttonPanel);
        controlPanel.add(insideControlPanel);

        tempLabel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        tempOutputLabel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        controlFrame.getRootPane().setDefaultButton(setTempButton);
        controlFrame.getContentPane().add(controlPanel, BorderLayout.CENTER);

        controlFrame.pack();

        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        controlFrame.setLocation((int) (screenWidth/2.2), (int)(screenHeight/2.2));

        controlFrame.setVisible(true);
    }

    /*TODO: Add public methods to enable and disable UI elements
       (https://docs.oracle.com/en/java/javase/22/docs/api/java.desktop/javax/swing/JComponent.html#setEnabled(boolean))*/
    public void enableMonitoringMode() {
        setControlButtonsEnabled(false);
        startMenuItem.setEnabled(false);
        stopMenuItem.setEnabled(true);
        tempOutputLabel.setText("offline");
    }
    public void enableControlMode() {
        setControlButtonsEnabled(true);
        startMenuItem.setEnabled(false);
        stopMenuItem.setEnabled(true);
    }
    private void setControlButtonsEnabled(boolean enabled) {
        setTempButton.setEnabled(enabled);
        increaseTempButton.setEnabled(enabled);
        decreaseTempButton.setEnabled(enabled);
        tempTextField.setEnabled(enabled);
    }
    public void disableAll() {
        setControlButtonsEnabled(false);
        startMenuItem.setEnabled(true);
        stopMenuItem.setEnabled(false);
        tempOutputLabel.setText("offline");
        currentTemp.setText("offline");
    }

    public void actionPerformed(ActionEvent event) {
        /*TODO: Complete this method to ensure that it processes the buttons clicked by the user
        *  https://docs.oracle.com/en/java/javase/22/docs/api/java.base/java/util/EventObject.html#getSource()
        * - increaseTempButton
        * - decreaseTempButton
        * - setTempButton
        * */

        Object source = event.getSource();

        if(source == setTempButton){
            int temp = Integer.parseInt(tempTextField.getText());
            controller.setTemperature(temp);
        }
        else if (source == increaseTempButton) {
            controller.increaseTemperature();
        } else if (source == decreaseTempButton) {
            controller.decreaseTemperature();
        }
    }

}
