/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.Color;
import javax.swing.JFrame;

/**
 *
 * @author Asus
 */
public class staffShow extends JFrame{
    public staffShow(){
        add(new StaffUpdate());
        this.setSize(700,600);
        this.setBackground(Color.lightGray);
        setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
