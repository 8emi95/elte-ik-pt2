/**
 * Created by Csaba_Hete on 2017.03.21..
 */
package hu.elte.progtech.icafe.view;

import hu.elte.progtech.icafe.backend.controller.ICafeApp;
import hu.elte.progtech.icafe.view.panel.ControlPanel;
import hu.elte.progtech.icafe.view.panel.TablePanel;
import hu.elte.progtech.icafe.view.table.ComputerTable;
import hu.elte.progtech.icafe.view.table.PaymentsTable;
import hu.elte.progtech.icafe.view.table.UserTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {
    public static final int DEFAULT_HEIGHT = 700;

    private static final String FRAME_TITLE = "ICafe";
    static final int DEFAULT_WIDTH = 900;

    private ICafeApp engine;
    private TablePanel tablePanel;

    public MainFrame(ICafeApp engine) {
        this.engine = engine;
        setTitle("");
    }

    /**
     * Metódus, melynek feladata az ablak szerkezetének felépítése, az ablak láthatóvá tétele
     */
    public void initFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        constructFrame();
        setLocation(100, 100);
        pack();
        initApp();
        setVisible(true);
    }

    private void constructFrame() {
        setMinimumSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        ControlPanel cp = new ControlPanel(this);
        tablePanel = new TablePanel(this);
        setContentPane(new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, cp, tablePanel));
    }

    /**
     * Metódus, amely biztosítja, hogy a programnak egyszerre csak egy pélánya futhat
     */
    private void initApp() {
        try {
            engine.init();
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    engine.close();
                    super.windowClosing(e);
                }
            });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Hiba történt az adatbázis-kapcsolat kialakítása közben! \n" +
                            "(Talán fut a program egy másik példánya?)",
                    "Hiba a program inicializálása során",
                    JOptionPane.ERROR_MESSAGE
            );
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
    }

    /**
     * Metódus, melynek segítségével elkérhetjük a táblázatot tartalmazó panelt
     */
    public TablePanel getTablePanel() {
        return tablePanel;
    }

    /**
     * Metódus, melynek segítségével megváltoztatható az ablak címe
     */
    public void setTitle(String title) {
        super.setTitle(FRAME_TITLE + " - " + title);
    }

    /**
     * Metódus, melynek segítségével elérhetjük az alkalmzás "motorját"
     */
    public ICafeApp getEngine() {
        return engine;
    }

    /**
     * Metódus, melynek segítségével a táblázat paneljében az ügyfeleket tartalmazó táblázat jelenik meg
     */
    public void listUsers() {
        setTitle("Ügyfelek");
        tablePanel.setTable(new UserTable());
    }

    /**
     * Metódus, melynek segítségével a táblázat paneljében a számítógépeket tartalmazó táblázat jelenik meg
     */
    public void listComputers() {
        setTitle("Számítógépek");
        tablePanel.setTable(new ComputerTable());
    }

    /**
     * Metódus, melynek segítségével a táblázat paneljében a befizetéseket tartalmazó táblázat jelenik meg
     */
    public void listPayments() {
        setTitle("Befizetések");
        tablePanel.setTable(new PaymentsTable());
    }

    /**
     * Metódus, melynek segítségével hibaüzenetet jeleníthetünk meg
     */
    public static void showError(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Metódus, melynek segítségével hibaüzenetet jeleníthetünk meg egy kivétel szövegével
     */
    public static void showError(Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage(), "HIBA", JOptionPane.ERROR_MESSAGE);
    }
}
