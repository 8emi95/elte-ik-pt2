package hu.valdar.progtech.controller;

import hu.valdar.progtech.model.Model;
import hu.valdar.progtech.view.View;
import hu.valdar.progtech.view.listener.LoginFormEvent;
import hu.valdar.progtech.view.listener.LoginListener;

public class Controller implements LoginListener{

    private View view;
    private Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
    }


    @Override
    public void loginPerformed(LoginFormEvent loginFormEvent) {
        System.out.println("Bejelentkezes tortent: " + loginFormEvent.getName() + " nevvel. Jelszo: " + loginFormEvent.getPassword());
        // TODO: Implementáld mi történjen, az esemény hatására a Modellel és a View-al.
    }
}
