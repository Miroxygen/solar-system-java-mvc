package controller;
import org.checkerframework.checker.units.qual.mol;

import model.Member;
import model.MemberList;
import view.View;
import view.View.menuChoices;


public class Controller  {
    view.View view = new View();
    model.MemberList memberList = new MemberList();
    
    public void startMenu() {
        Boolean running = true;
        while(running) {
            menuChoices action = view.showMenu();
            switch (action) {
                case AddMember:
                    createMember();
                    break;
                case Quit:
                    running = false;
            }
        }
    }

    public void createMember() {
        model.Member newMember = view.createMember();
        view.showMember(newMember);
    }
}