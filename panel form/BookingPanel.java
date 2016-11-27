/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.event.ItemEvent;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import control.BookingControl;
import control.TicketControl;
import domain.Booking;
import domain.Ticket;
import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author CJH
 */
public class BookingPanel extends javax.swing.JPanel {

    /**
     * Creates new form BookingPanel
     */
    private BookingControl bControl;
    private TicketControl tControl;
    private TableRowSorter<TableModel> sorter1;
    private TableRowSorter<TableModel> sorter2;
    
    public BookingPanel() {
        initComponents();
        bControl = new BookingControl();        
        tControl = new TicketControl();
        DefaultTableModel model1 = (DefaultTableModel) bookingTable.getModel();     
        sorter1 = new TableRowSorter<>(model1);
        bookingTable.setRowSorter(sorter1);
        
        DefaultTableModel model2 = (DefaultTableModel) ticketTable.getModel();     
        sorter2 = new TableRowSorter<>(model2);
        ticketTable.setRowSorter(sorter2);
        
        
        DocumentListener filterRefresh = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = jtfFilter.getText();
                if (text.length() == 0) {
                    sorter1.setRowFilter(null);
                } else {
                    try {
                        sorter1.setRowFilter(RowFilter.regexFilter(text));
                    } catch (PatternSyntaxException pse) {
                        JOptionPane.showMessageDialog(null, "Bad regex pattern", "Bad regex pattern", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = jtfFilter.getText();
                if (text.length() == 0) {
                    sorter1.setRowFilter(null);
                } else {
                    try {
                        sorter1.setRowFilter(RowFilter.regexFilter(text));
                    } catch (PatternSyntaxException pse) {
                        JOptionPane.showMessageDialog(null, "Bad regex pattern", "Bad regex pattern", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }           
        };  
        
        jtfFilter.getDocument().addDocumentListener(filterRefresh);
        
        DocumentListener filterRefresh2 = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = jtfFilter2.getText();
                if (text.length() == 0) {
                    sorter2.setRowFilter(null);
                } else {
                    try {
                        sorter2.setRowFilter(RowFilter.regexFilter(text));
                    } catch (PatternSyntaxException pse) {
                        JOptionPane.showMessageDialog(null, "Bad regex pattern", "Bad regex pattern", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = jtfFilter2.getText();
                if (text.length() == 0) {
                    sorter2.setRowFilter(null);
                } else {
                    try {
                        sorter2.setRowFilter(RowFilter.regexFilter(text));
                    } catch (PatternSyntaxException pse) {
                        JOptionPane.showMessageDialog(null, "Bad regex pattern", "Bad regex pattern", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }           
        };  
        
        jtfFilter2.getDocument().addDocumentListener(filterRefresh2);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbtReprint = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        bookingTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        ticketTable = new javax.swing.JTable();
        jbtBack = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jcbCriteria = new javax.swing.JComboBox();
        jtfSearch = new javax.swing.JTextField();
        jbtSearch = new javax.swing.JButton();
        jlError1 = new javax.swing.JLabel();
        jbtAll = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        Date date = new Date();
        SpinnerDateModel sm =
        new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
        jSpinner1 = new javax.swing.JSpinner(sm);
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        SpinnerDateModel sm2 =
        new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
        jSpinner2 = new javax.swing.JSpinner(sm2);
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jbtCancel = new javax.swing.JButton();
        jtfFilter = new javax.swing.JTextField();
        jbtFilter = new javax.swing.JButton();
        jbtFilter2 = new javax.swing.JButton();
        jtfFilter2 = new javax.swing.JTextField();
        jbtEdit = new javax.swing.JButton();

        setBackground(new java.awt.Color(102, 102, 102));
        setMaximumSize(new java.awt.Dimension(740, 640));
        setMinimumSize(new java.awt.Dimension(740, 640));
        setPreferredSize(new java.awt.Dimension(740, 640));

        jbtReprint.setText("Reprint Ticket");

        bookingTable.setAutoCreateRowSorter(true);
        bookingTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Booking ID", "Staff ID", "Customer ID", "Customer Name", "Destination", "Booking Date", "Booking Time"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        bookingTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bookingTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(bookingTable);

        ticketTable.setAutoCreateRowSorter(true);
        ticketTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ticket ID", "Seat No", "Passenger Name", "Passenger Contact No", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(ticketTable);

        jbtBack.setText("Back");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Booking Details");

        jLabel2.setText("Search");

        jcbCriteria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Booking ID", "Staff ID", "Customer ID", "Customer Name", "Destination", "Time", "Time-Time", "Date", "Date-Date", "Date&Time", "Date&Time-Date&Time" }));
        jcbCriteria.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbCriteriaItemStateChanged(evt);
            }
        });

        jbtSearch.setText("Search");

        jlError1.setText(" ");

        jbtAll.setText("Search All");
        jbtAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAllActionPerformed(evt);
            }
        });

        jDateChooser1.setEnabled(false);

        JSpinner.DateEditor de = new JSpinner.DateEditor(jSpinner1, "HH:mm:ss");
        jSpinner1.setEditor(de);
        jSpinner1.setEnabled(false);

        jLabel3.setText("-");

        jLabel4.setText("-");

        JSpinner.DateEditor de2 = new JSpinner.DateEditor(jSpinner2, "HH:mm:ss");
        jSpinner2.setEditor(de2);
        jSpinner2.setEnabled(false);

        jDateChooser2.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jlError1, javax.swing.GroupLayout.PREFERRED_SIZE, 589, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(250, 250, 250)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(88, 88, 88)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jtfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(18, 18, 18)
                                    .addComponent(jcbCriteria, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jSpinner1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel3))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel4)))
                            .addGap(4, 4, 4)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                                .addComponent(jSpinner2))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jbtSearch)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jbtAll))))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbCriteria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jbtSearch)
                        .addComponent(jbtAll))
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlError1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jbtCancel.setText("Cancel Ticket");

        jtfFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfFilterActionPerformed(evt);
            }
        });

        jbtFilter.setText("Filter");
        jbtFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtFilterActionPerformed(evt);
            }
        });

        jbtFilter2.setText("Filter");
        jbtFilter2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtFilter2ActionPerformed(evt);
            }
        });

        jtfFilter2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfFilter2ActionPerformed(evt);
            }
        });

        jbtEdit.setText("Edit");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbtBack))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jtfFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtFilter)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtEdit))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jtfFilter2, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtFilter2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtReprint)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtFilter)
                    .addComponent(jbtEdit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtFilter2)
                    .addComponent(jtfFilter2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtReprint)
                    .addComponent(jbtCancel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtBack)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jcbCriteriaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbCriteriaItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if(evt.getItem().toString().equals("Time")) {
                jtfSearch.setEnabled(false);
                jSpinner1.setEnabled(true);
                jSpinner2.setEnabled(false);
                jDateChooser1.setEnabled(false);
                jDateChooser2.setEnabled(false);
            } else if(evt.getItem().toString().equals("Time-Time")) {
                jtfSearch.setEnabled(false);
                jSpinner1.setEnabled(true);
                jSpinner2.setEnabled(true);
                jDateChooser1.setEnabled(false);
                jDateChooser2.setEnabled(false);
            } else if(evt.getItem().toString().equals("Date")) {
                jtfSearch.setEnabled(false);
                jSpinner1.setEnabled(false);
                jSpinner2.setEnabled(false);
                jDateChooser1.setEnabled(true);
                jDateChooser2.setEnabled(false);
            } else if(evt.getItem().toString().equals("Date-Date")) {
                jtfSearch.setEnabled(false);
                jSpinner1.setEnabled(false);
                jSpinner2.setEnabled(false);
                jDateChooser1.setEnabled(true);
                jDateChooser2.setEnabled(true);
            } else if(evt.getItem().toString().equals("Date&Time")) {
                jtfSearch.setEnabled(false);
                jSpinner1.setEnabled(true);
                jSpinner2.setEnabled(false);
                jDateChooser1.setEnabled(true);
                jDateChooser2.setEnabled(false);
            } else if(evt.getItem().toString().equals("Date&Time-Date&Time")) {
                jtfSearch.setEnabled(false);
                jSpinner1.setEnabled(true);
                jSpinner2.setEnabled(true);
                jDateChooser1.setEnabled(true);
                jDateChooser2.setEnabled(true);
            } else {
                jtfSearch.setEnabled(true);
                jSpinner1.setEnabled(false);
                jSpinner2.setEnabled(false);
                jDateChooser1.setEnabled(false);
                jDateChooser2.setEnabled(false);
            }
        } 
    }//GEN-LAST:event_jcbCriteriaItemStateChanged

    private void jbtAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAllActionPerformed
        // TODO add your handling code here:
        jtfSearch.setText("");
        jSpinner1.setEnabled(false);
        jSpinner2.setEnabled(false);
        jDateChooser1.setEnabled(false);
        jDateChooser2.setEnabled(false);
        jlError1.setText(" ");
        ArrayList<Booking> bookingList = bControl.selectAllRecord();
        DefaultTableModel model = (DefaultTableModel) bookingTable.getModel();
        model.setRowCount(0);
        for(int i=0; i<bookingList.size(); i++) {
            model.addRow(new Object[]{bookingList.get(i).getBookingID(), bookingList.get(i).getStaff().getStaffID(), bookingList.get(i).getCustomer().getCustID(), bookingList.get(i).getCustomer().getCustName(), tControl.selectRecordByBooking(bookingList.get(i)).get(0).getSchedule().getRoute().getDestination(), bookingList.get(i).getBookingDate(), bookingList.get(i).getBookingTime()});
        }
    }//GEN-LAST:event_jbtAllActionPerformed

    private void bookingTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookingTableMouseClicked
        // TODO add your handling code here:
        JTable tablee =(JTable)evt.getSource();
        DefaultTableModel model = (DefaultTableModel) ticketTable.getModel();
        if(evt.getClickCount() == 1) {
            model.setRowCount(0);
            int row = tablee.getSelectedRow();
            String bookingID = tablee.getModel().getValueAt(tablee.convertRowIndexToModel(row), 0).toString();
            ArrayList<Ticket> ticketList = tControl.selectRecordByBooking(bControl.selectRecord(bookingID));
            if(!ticketList.isEmpty()) {
                for(int i=0; i<ticketList.size(); i++) {
                    model.addRow(new Object[]{ticketList.get(i).getTicketID(), ticketList.get(i).getSeatNo(), ticketList.get(i).getPassengerName(), ticketList.get(i).getPassengerContactNo(), ticketList.get(i).getStatus()});
                }
            }
        }
    }//GEN-LAST:event_bookingTableMouseClicked

    private void jbtFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtFilterActionPerformed
        // TODO add your handling code here:
        String text = jtfFilter.getText();
            if (text.length() == 0) {
                sorter1.setRowFilter(null);
            } else {
                try {
                    sorter1.setRowFilter(RowFilter.regexFilter(text));
                } catch (PatternSyntaxException pse) {
                    JOptionPane.showMessageDialog(null, "Bad regex pattern", "Bad regex pattern", JOptionPane.ERROR_MESSAGE);
                }
            }   
    }//GEN-LAST:event_jbtFilterActionPerformed

    private void jtfFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfFilterActionPerformed
        // TODO add your handling code here:
        String text = jtfFilter.getText();
            if (text.length() == 0) {
                sorter1.setRowFilter(null);
            } else {
                try {
                    sorter1.setRowFilter(RowFilter.regexFilter(text));
                } catch (PatternSyntaxException pse) {
                    JOptionPane.showMessageDialog(null, "Bad regex pattern", "Bad regex pattern", JOptionPane.ERROR_MESSAGE);
                }
            }   
    }//GEN-LAST:event_jtfFilterActionPerformed

    private void jbtFilter2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtFilter2ActionPerformed
        // TODO add your handling code here:
        String text = jtfFilter2.getText();
            if (text.length() == 0) {
                sorter2.setRowFilter(null);
            } else {
                try {
                    sorter2.setRowFilter(RowFilter.regexFilter(text));
                } catch (PatternSyntaxException pse) {
                    JOptionPane.showMessageDialog(null, "Bad regex pattern", "Bad regex pattern", JOptionPane.ERROR_MESSAGE);
                }
            }   
    }//GEN-LAST:event_jbtFilter2ActionPerformed

    private void jtfFilter2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfFilter2ActionPerformed
        // TODO add your handling code here:
        String text = jtfFilter2.getText();
            if (text.length() == 0) {
                sorter2.setRowFilter(null);
            } else {
                try {
                    sorter2.setRowFilter(RowFilter.regexFilter(text));
                } catch (PatternSyntaxException pse) {
                    JOptionPane.showMessageDialog(null, "Bad regex pattern", "Bad regex pattern", JOptionPane.ERROR_MESSAGE);
                }
            }   
    }//GEN-LAST:event_jtfFilter2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable bookingTable;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JButton jbtAll;
    private javax.swing.JButton jbtBack;
    private javax.swing.JButton jbtCancel;
    private javax.swing.JButton jbtEdit;
    private javax.swing.JButton jbtFilter;
    private javax.swing.JButton jbtFilter2;
    private javax.swing.JButton jbtReprint;
    private javax.swing.JButton jbtSearch;
    private javax.swing.JComboBox jcbCriteria;
    private javax.swing.JLabel jlError1;
    private javax.swing.JTextField jtfFilter;
    private javax.swing.JTextField jtfFilter2;
    private javax.swing.JTextField jtfSearch;
    private javax.swing.JTable ticketTable;
    // End of variables declaration//GEN-END:variables
}
