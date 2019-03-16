package hu.valdar.progtech;

import hu.valdar.progtech.controller.Controller;
import hu.valdar.progtech.model.Model;
import hu.valdar.progtech.view.View;

import javax.swing.*;

public class Boot {

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            Model model = new Model();
            View view = new View(model);

            Controller controller = new Controller(view, model);
            view.getMainPanel().setLoginListener(controller);
        });
    }

}
