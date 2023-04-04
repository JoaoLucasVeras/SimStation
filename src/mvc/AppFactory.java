package mvc;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.io.*;
import java.beans.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public interface AppFactory {
    public Model makeModel();
    public View makeView(Model model);
    public String[] getEditCommands();
    public Command makeEditCommand(Model model, String type, Object source);
    public String getTitle();
    public String[] getHelp();
    public String about();
}
