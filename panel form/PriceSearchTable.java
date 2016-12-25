/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import javax.swing.*;
import javax.swing.table.*;
import da.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.PatternSyntaxException;


/**
 *
 * @author Student
 */
public class PriceSearchTable extends JPanel{
    private JTable jtable;
    private PriceResultSetModel priceModel;
    private JScrollPane jsc;
    private TableRowSorter<TableModel> sorter;
    GridBagConstraints gbc = new GridBagConstraints();
        
    private JTextField filterText = new JTextField(30);
    private JLabel filterLabel = new JLabel("Filter:");
    private JButton filterButton = new JButton("Apply Filter");
    
    public PriceSearchTable(){
        priceModel = new PriceResultSetModel();
        jtable = new JTable(priceModel);
        jsc = new JScrollPane(jtable);
        jsc.setOpaque(false);
        sorter = new TableRowSorter<TableModel>(priceModel);
        jtable.setRowSorter(sorter);
        gbc.fill = GridBagConstraints.CENTER;
        
        
        JPanel p1 = new JPanel(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        p1.add(jsc, gbc);
        jsc.setPreferredSize(new Dimension(550,450));
        
        JPanel filterPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
        filterPanel.add(filterLabel);
        filterPanel.add(filterText);
        filterPanel.add(filterButton);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        p1.add(filterPanel, gbc);
        filterButton.addActionListener(new FilterButtonListener());   
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
    
}
