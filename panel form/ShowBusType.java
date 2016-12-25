/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import da.BusResultSetModel;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.PatternSyntaxException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author JianHow
 */
public class ShowBusType extends JPanel{
     private JTable jtable;
    private BusResultSetModel busTypeModel;
    private JScrollPane jsc;
    private TableRowSorter<TableModel> sorter;
    GridBagConstraints gbc = new GridBagConstraints();
        
    private JTextField filterText = new JTextField(30);
    private JLabel filterLabel = new JLabel("Filter:");
    private JButton filterButton = new JButton("Apply Filter");
    public ShowBusType(){
         busTypeModel = new BusResultSetModel();
        jtable = new JTable(busTypeModel);
        jsc = new JScrollPane(jtable);
        jsc.setOpaque(false);
        sorter = new TableRowSorter<TableModel>(busTypeModel);
        jtable.setRowSorter(sorter);
        gbc.fill = GridBagConstraints.CENTER;
        
        
        JPanel p1 = new JPanel(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        p1.add(jsc, gbc);
        jsc.setPreferredSize(new Dimension(700,500));
        
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
        setSize(600,800);
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
