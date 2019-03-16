/**
 * Created by Csaba_Hete on 2017.03.17..
 */
package hu.elte.progtech.icafe;

import hu.elte.progtech.icafe.backend.controller.ICafeApp;
import hu.elte.progtech.icafe.view.MainFrame;

public class Launcher {
    public static void main(String[] args) {
        ICafeApp app = new ICafeApp();
        MainFrame view = new MainFrame(app);
        javax.swing.SwingUtilities.invokeLater(view::initFrame);
    }
}
