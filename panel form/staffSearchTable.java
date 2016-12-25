/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import javax.swing.*;
import javax.swing.table.*;
import da.StaffResultSetModel;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.PatternSyntaxException;


/**
 *
 * @author Student
 */
public class staffSearchTable extends JPanel{
    private JTable jtable;
    private StaffResultSetModel staffModel;
    private JScrollPane jsc;
    private TableRowSorter<TableModel> sorter;
    GridBagConstraints gbc = new GridBagConstraints();
        
    private JTextField filterText = new JTextField(30);
    private JLabel filterLabel = new JLabel("Filter:");
    private JButton filterButton = new JButton("Apply Filter");
    private JButton updateButton = new JButton("Update");
    
    public staffSearchTable(){
        staffModel = new StaffResultSetModel();
        jtable = new JTable(staffModel);
        jsc = new JScrollPane(jtable);
        jsc.setOpaque(false);
        sorter = new TableRowSorter<TableModel>(staffModel);
        jtable.setRowSorter(sorter);
        gbc.fill = GridBagConstraints.CENTER;
        
        
        JPanel p1 = new JPanel(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        p1.add(jsc, gbc);
        jsc.setPreferredSize(new Dimension(1000,500));
        
        JPanel filterPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
        filterPanel.add(filterLabel);
        filterPanel.add(filterText);
        filterPanel.add(filterButton);
        filterPanel.add(updateButton);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        p1.add(filterPanel, gbc);
        filterButton.addActionListener(new FilterButtonListener());   
        updateButton.addActionListener(new DisplayUpdateForm());
        add(p1);
    }
    private class FilterButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String text = filterText.getText();
            if (text.length() == 0) {
                sorter.setRowFilter(null);
            } else {
                try {
                    sorter.setRowFilter(RowFilter.regexFilter(text));
                } catch (PatternSyntaxException pse) {
                    JOptionPane.showMessageDialog(null, "Bad regex pattern", "Bad regex pattern", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    private class DisplayUpdateForm implements ActionListener {
        public void actionPerformed(ActionEvent e){
            new staffShow();
        }
    }
}
