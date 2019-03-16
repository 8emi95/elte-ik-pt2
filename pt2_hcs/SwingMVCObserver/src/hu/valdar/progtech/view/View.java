package hu.valdar.progtech.view;

import hu.valdar.progtech.model.Model;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame{

    private Model model;
    private MainPanel mainPanel;

    public View(Model model) throws HeadlessException {
        this.model = model;
        this.mainPanel = new MainPanel(model);

        setLayout(new BorderLayout());

        getContentPane().add(mainPanel, BorderLayout.CENTER);
        pack();

        setTitle("Swing MVC - Observer Pattern demo");
        setSize(400,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }
}
