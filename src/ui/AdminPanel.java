/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import control.*;
import domain.*;
import function.Function;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import validation.validation;
import control.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author JianHow
 */
public class AdminPanel extends JPanel {
    String  sssID;
     SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Function function = new Function();
    validation validation = new validation();
    StaffControl staffC = new StaffControl();
    DriverControl driverC = new DriverControl();
    TicketControl ticketC=new TicketControl();
    PriceControl priceC = new PriceControl();
    BusControl busC = new BusControl();
    RouteControl routeC = new RouteControl();
    BusTypeControl busTC = new BusTypeControl();
    ScheduleControl scheduleC=new ScheduleControl();
    JTabbedPane mainPane = new JTabbedPane();
    JTabbedPane staffSubPane = new JTabbedPane();
    JTabbedPane driverSubPane = new JTabbedPane();
    JTabbedPane priceSubPane = new JTabbedPane();
    JTabbedPane busSubPane = new JTabbedPane();
     JTabbedPane routeSubPane = new JTabbedPane();
      JTabbedPane scheduleSubPane = new JTabbedPane();
    JPanel staffShowList = new JPanel();
    JPanel staffCreate = new JPanel();
    JFrame staffShowFrame = new JFrame();
    JPanel staffStatusShowList = new JPanel();
    JPanel staffPane = new JPanel();
    JPanel driverPane = new JPanel();
    JPanel schedulePane = new JPanel();
    JPanel busPane = new JPanel();
    JPanel busTypePane = new JPanel();
    JPanel routePanePane = new JPanel();
    JPanel pricePane = new JPanel();
    private String driverDD="" ;
    private String busDD ="";
    Icon create = new ImageIcon(getClass().getResource("/images/create.png"));
    Icon search = new ImageIcon(getClass().getResource("/images/search.png"));
    Icon up = new ImageIcon(getClass().getResource("/images/up.png"));
    Icon deactivate = new ImageIcon(getClass().getResource("/images/deactivate.png"));
    Icon money = new ImageIcon(getClass().getResource("/images/money.png"));
    Icon noMoney = new ImageIcon(getClass().getResource("/images/no-money.jpg"));
    Icon busCreate = new ImageIcon(getClass().getResource("/images/busCreate.png"));
    Icon busDeactivate = new ImageIcon(getClass().getResource("/images/busDeactivate.png"));

    public AdminPanel() {
        mainPane.addTab("   Staff   ", StaffPane());
        mainPane.addTab("   Driver   ", DriverPane());
        mainPane.addTab("   Schedule   ", SchedulePane());
        mainPane.addTab("   Route   ", RoutePane());
        mainPane.addTab("   Bus   ", BusPane());
        mainPane.addTab("   Bus Type   ", ShowBusType());
        mainPane.addTab("   Price   ", PricePane());
        mainPane.setPreferredSize(new Dimension(780, 600));
        mainPane.setBackground(Color.lightGray);
        add(mainPane);
        setVisible(true);
    }

    //Staff CRUD
    public JTabbedPane StaffPane() {

        staffSubPane.addTab("", create, StaffCreate1());
        staffSubPane.addTab("", up, StaffShowList());
        staffSubPane.addTab("", deactivate, StaffStatusShowList());
        staffSubPane.addTab("", search, StaffSearch());
        staffSubPane.setTabPlacement(JTabbedPane.LEFT);
        staffSubPane.setPreferredSize(new Dimension(740, 600));
        staffSubPane.setBackground(Color.lightGray);
        return staffSubPane;
    }
    public JPanel StaffCreate1() {

        Function function = new Function();
        validation validation = new validation();
        StaffControl staffC = new StaffControl();
        JPanel panel = new JPanel();

        JPanel jPanel1 = new javax.swing.JPanel();
        JLabel jLabel1 = new javax.swing.JLabel();
        JComboBox staffPositionList = new javax.swing.JComboBox();
        JLabel jLabel2 = new javax.swing.JLabel();
        JTextField staffIDField = new javax.swing.JTextField();
        JLabel jLabel3 = new javax.swing.JLabel();
        JTextField staffNameField = new javax.swing.JTextField();
        JLabel jLabel4 = new javax.swing.JLabel();
        JTextField contactField = new javax.swing.JTextField();
        JLabel jLabel5 = new javax.swing.JLabel();
        JLabel jLabel6 = new javax.swing.JLabel();
        JTextField ICNoField = new javax.swing.JTextField();
        JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
        JTextArea staffAddressField = new javax.swing.JTextArea();
        JLabel jLabel9 = new javax.swing.JLabel();
        JTextField salaryField = new javax.swing.JTextField();
        JPanel jPanel2 = new javax.swing.JPanel();
        JLabel jLabel7 = new javax.swing.JLabel();
        JLabel jLabel8 = new javax.swing.JLabel();
        JPasswordField passField1 = new javax.swing.JPasswordField();
        JPasswordField passField2 = new javax.swing.JPasswordField();
        JButton create1 = new javax.swing.JButton();
        JButton cancel = new javax.swing.JButton();
        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        JTextArea ErrorField = new javax.swing.JTextArea();

        panel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panel.setMaximumSize(new java.awt.Dimension(612, 439));
        panel.setMinimumSize(new java.awt.Dimension(612, 439));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true), "Staff Info", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Fax", 1, 18))); // NOI18N
        jPanel1.setMaximumSize(new java.awt.Dimension(588, 254));
        jPanel1.setMinimumSize(new java.awt.Dimension(588, 254));

        jLabel1.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel1.setText("Position");

        staffPositionList.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        staffPositionList.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"", "Staff", "Manager"}));

        jLabel2.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel2.setText("Staff ID :");

        staffIDField.setEditable(false);
        staffIDField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel3.setText("Staff Name :");

        staffNameField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        staffNameField.setMaximumSize(new java.awt.Dimension(6, 22));

        jLabel4.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel4.setText("Contact No :");

        contactField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        contactField.setMaximumSize(new java.awt.Dimension(6, 22));

        jLabel5.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel5.setText("Address :");

        jLabel6.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel6.setText("Ic No :");

        ICNoField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        ICNoField.setMaximumSize(new java.awt.Dimension(6, 22));
        staffAddressField.setColumns(20);
        staffAddressField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        staffAddressField.setRows(5);
        staffAddressField.setMaximumSize(new java.awt.Dimension(4, 24));
        jScrollPane2.setViewportView(staffAddressField);

        jLabel9.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel9.setText("Salary :");

        salaryField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(staffNameField, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(staffPositionList, 0, 177, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(staffIDField, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(contactField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ICNoField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(salaryField, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))
                        .addGap(33, 33, 33))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(staffPositionList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(staffIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(staffNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(contactField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(ICNoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(salaryField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true), "Password", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Fax", 1, 18))); // NOI18N
        jPanel2.setMaximumSize(new java.awt.Dimension(343, 146));
        jPanel2.setMinimumSize(new java.awt.Dimension(343, 146));

        jLabel7.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel7.setText("Password :");

        jLabel8.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel8.setText("Confirm Password :");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(74, 74, 74)
                                        .addComponent(passField1, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(passField2)))
                        .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(34, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(passField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(passField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32))
        );

        create1.setFont(new java.awt.Font("Lucida Fax", 0, 14)); // NOI18N
        create1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Users-Add-user-icon_large.png"))); // NOI18N
        create1.setText("Create");

        cancel.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.png"))); // NOI18N
        cancel.setText("Cancel");

        ErrorField.setEditable(false);
        ErrorField.setBackground(new java.awt.Color(240, 240, 240));
        ErrorField.setColumns(20);
        ErrorField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        ErrorField.setForeground(new java.awt.Color(255, 0, 0));
        ErrorField.setRows(5);
        ErrorField.setBorder(null);
        jScrollPane1.setViewportView(ErrorField);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(24, 24, 24)
                                                        .addComponent(create1)
                                                        .addGap(0, 0, Short.MAX_VALUE))
                                                .addComponent(jScrollPane1)))
                                .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(create1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(32, Short.MAX_VALUE))
        );
        staffAddressField.setLineWrap(true);
        staffPositionList.addActionListener((ActionEvent evt) -> {
            if (staffPositionList.getSelectedItem().toString().equals("Manager")) {
                ErrorField.setText("");
                staffIDField.setText(function.setStaffID('M'));
            } else if(staffPositionList.getSelectedItem().toString().equals("Staff")){
                ErrorField.setText("");
                staffIDField.setText(function.setStaffID('S'));
            }else if(staffPositionList.getSelectedItem().toString().equals("")){
                ErrorField.setText("Position cannot be blank.");
            }
        });
        create1.addActionListener((ActionEvent evt) -> {
            String validate1 = validation.validateName(staffNameField.getText());
            String validate2 = validation.validateAddress(staffAddressField.getText());
            String validate3 = validation.validateContactNo(contactField.getText());
            String validate4 = validation.validateICNo(ICNoField.getText());
            String validate5 = validation.validatePassword(passField1.getText().toString(), passField2.getText().toString());
            String validate6=validation.validateSalary(salaryField.getText());
            if (!staffNameField.getText().isEmpty() && !staffAddressField.getText().isEmpty() && !contactField.getText().isEmpty() && !ICNoField.getText().isEmpty() && !salaryField.getText().isEmpty()) {
                if (validate1.isEmpty() && validate2.isEmpty() && validate3.isEmpty() && validate5.isEmpty() && validate4.isEmpty() && validate6.isEmpty()) {
                    Staff newStaff = new Staff(staffIDField.getText(), staffNameField.getText(), staffAddressField.getText(), contactField.getText(), ICNoField.getText(), Double.parseDouble(salaryField.getText()), passField2.getText(), 'A');
                    staffC.addRecord(newStaff);
                    staffSubPane.removeAll();
                    staffSubPane.addTab("", create, StaffCreate1());
                    staffSubPane.addTab("", up, StaffShowList());
                    staffSubPane.addTab("", deactivate, StaffStatusShowList());
                    staffSubPane.addTab("", search, StaffSearch());

                } else {
                    ErrorField.setText(validate1.concat("\n").concat(validate2).concat("\n").concat(validate3).concat("\n").concat(validate4).concat("\n").concat(validate5).concat("\n").concat(validate6));
                }
            } else {
                JOptionPane.showMessageDialog(null, "You may have fill is empty.\nPlease fill those emtpy area", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancel.addActionListener((ActionEvent evt) -> {
            staffNameField.setText("");
            staffAddressField.setText("");
            contactField.setText("");
            ICNoField.setText("");
            passField1.setText("");
            passField2.setText("");
            salaryField.setText((""));
            staffIDField.setText("");
            staffPositionList.setSelectedIndex(0);
            ICNoField.setText("");

        });
        return panel;
    }
    public JPanel StaffShowList() {
        JPanel panel = new JPanel();
        JPanel jPanel1 = new javax.swing.JPanel();
        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        JTable staffList = new javax.swing.JTable();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Staff List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Fax", 1, 24))); // NOI18N

        staffList.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        staffList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Staff ID", "Staff Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        staffList.setMaximumSize(new java.awt.Dimension(225, 0));
        staffList.setMinimumSize(new java.awt.Dimension(225, 0));
        staffList.getTableHeader().setReorderingAllowed(false);

        jScrollPane1.setViewportView(staffList);
        if (staffList.getColumnModel().getColumnCount() > 0) {
            staffList.getColumnModel().getColumn(0).setResizable(false);
            staffList.getColumnModel().getColumn(0).setPreferredWidth(1);
            staffList.getColumnModel().getColumn(1).setResizable(false);
            staffList.getColumnModel().getColumn(2).setResizable(false);
            
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 584, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 11, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        DefaultTableModel model = (DefaultTableModel) staffList.getModel();
        ArrayList<Staff> staffL = staffC.getAllStaff();
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(staffList.getModel());
        staffList.setRowSorter(sorter);
        int i = 1;
        for (Staff s : staffL) {
            model.addRow(new Object[]{Integer.toString(i), s.getStaffID(), s.getStaffName()});
            i++;
        }
        staffList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JTable source = (JTable) evt.getSource();
                if (evt.getClickCount() == 1) {
                    int row = source.getSelectedRow();
                    String id = source.getModel().getValueAt(source.convertRowIndexToModel(row), 1).toString();
                    staffSubPane.remove(1);
                    staffSubPane.insertTab("", up, StaffUpdate(id), "", 1);
                    staffSubPane.setSelectedIndex(1);

                }
            }
        });

        return panel;
    }
    public JPanel StaffUpdate(String id) {
        validation validation = new validation();
        StaffControl staffC = new StaffControl();
        JPanel panel = new JPanel();

        JPanel jPanel1 = new javax.swing.JPanel();
        JLabel jLabel1 = new javax.swing.JLabel();
        JTextField staffPositionList = new javax.swing.JTextField();
        JLabel jLabel2 = new javax.swing.JLabel();
        JTextField staffIDField = new javax.swing.JTextField();
        JLabel jLabel3 = new javax.swing.JLabel();
        JTextField staffNameField = new javax.swing.JTextField();
        JLabel jLabel4 = new javax.swing.JLabel();
        JTextField contactField = new javax.swing.JTextField();
        JLabel jLabel5 = new javax.swing.JLabel();
        JLabel jLabel6 = new javax.swing.JLabel();
        JTextField ICNoField = new javax.swing.JTextField();
        JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
        JTextArea staffAddressField = new javax.swing.JTextArea();
        JLabel jLabel9 = new javax.swing.JLabel();
        JTextField salaryField = new javax.swing.JTextField();
        JPanel jPanel2 = new javax.swing.JPanel();
        JLabel jLabel7 = new javax.swing.JLabel();
        JLabel jLabel8 = new javax.swing.JLabel();
        JPasswordField passField1 = new javax.swing.JPasswordField();
        JPasswordField passField2 = new javax.swing.JPasswordField();
        JButton update = new javax.swing.JButton();
        JButton cancel = new javax.swing.JButton();
        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        JTextArea ErrorField = new javax.swing.JTextArea();

        panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        panel.setMaximumSize(new java.awt.Dimension(612, 439));
        panel.setMinimumSize(new java.awt.Dimension(612, 439));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true), "Staff Info", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Fax", 1, 18))); // NOI18N
        jPanel1.setMaximumSize(new java.awt.Dimension(588, 254));
        jPanel1.setMinimumSize(new java.awt.Dimension(588, 254));

        jLabel1.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel1.setText("Position");

        jLabel2.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel2.setText("Staff ID :");

        staffIDField.setEditable(false);
        staffIDField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel3.setText("Staff Name :");

        staffNameField.setEditable(false);
        staffNameField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        staffNameField.setMaximumSize(new java.awt.Dimension(6, 22));

        jLabel4.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel4.setText("Contact No :");

        contactField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        contactField.setMaximumSize(new java.awt.Dimension(6, 22));

        jLabel5.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel5.setText("Address :");

        jLabel6.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel6.setText("Ic No :");

        ICNoField.setEditable(false);
        ICNoField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        ICNoField.setMaximumSize(new java.awt.Dimension(6, 22));

        staffAddressField.setColumns(20);
        staffAddressField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        staffAddressField.setRows(5);
        staffAddressField.setMaximumSize(new java.awt.Dimension(4, 24));
        jScrollPane2.setViewportView(staffAddressField);

        jLabel9.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel9.setText("Salary :");

        salaryField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N

        staffPositionList.setEditable(false);
        staffPositionList.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(staffNameField, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel4)
                                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(contactField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(ICNoField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(salaryField, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(staffIDField, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(staffPositionList, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(33, 33, 33))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(staffIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(staffPositionList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(staffNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(contactField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(ICNoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(salaryField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(41, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true), "Password", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Fax", 1, 18))); // NOI18N
        jPanel2.setMaximumSize(new java.awt.Dimension(343, 146));
        jPanel2.setMinimumSize(new java.awt.Dimension(343, 146));

        jLabel7.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel7.setText("Password :");

        jLabel8.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel8.setText("Confirm Password :");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(74, 74, 74)
                                        .addComponent(passField1, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(passField2)))
                        .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(34, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(passField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(passField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32))
        );

        update.setFont(new java.awt.Font("Lucida Fax", 0, 14)); // NOI18N
        update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/update.png"))); // NOI18N
        update.setText("Update");

        cancel.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.png"))); // NOI18N
        cancel.setText("Cancel");

        ErrorField.setEditable(false);
        ErrorField.setBackground(new java.awt.Color(240, 240, 240));
        ErrorField.setColumns(20);
        ErrorField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        ErrorField.setForeground(new java.awt.Color(255, 0, 0));
        ErrorField.setRows(5);
        ErrorField.setBorder(null);
        jScrollPane1.setViewportView(ErrorField);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(24, 24, 24)
                                                        .addComponent(update)
                                                        .addGap(0, 0, Short.MAX_VALUE))
                                                .addComponent(jScrollPane1)))
                                .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(15, 15, 15)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(2, Short.MAX_VALUE))
        );
        staffAddressField.setLineWrap(true);
        Staff staff = staffC.selectRecord(id);
        staffIDField.setText(staff.getStaffID());
        if (staff.getStaffID().contains("S")) {
            staffPositionList.setText("Staff");
        } else {
            staffPositionList.setText("Manager");
        }
        staffNameField.setText(staff.getStaffName());
        staffAddressField.setText(staff.getAddress());
        salaryField.setText(String.format("%.2f",staff.getSalary()));
        ICNoField.setText(staff.getIcNo());
        contactField.setText(staff.getContactNo());

        cancel.addActionListener((ActionEvent evt) -> {
            staffSubPane.remove(1);
            staffSubPane.insertTab("", up, StaffShowList(), "", 1);
            staffSubPane.setSelectedIndex(1);
        });

        update.addActionListener((ActionEvent evt) -> {
            if(passField1.getText().isEmpty() || passField2.getText().isEmpty()){
                String validate2 = validation.validateAddress(staffAddressField.getText());
                String validate3 = validation.validateContactNo(contactField.getText());
                String validate4=validation.validateSalary(salaryField.getText());

                if (!staffNameField.getText().isEmpty() && !staffAddressField.getText().isEmpty() && !contactField.getText().isEmpty() && !ICNoField.getText().isEmpty() && !salaryField.getText().isEmpty()) {
                    if (validate4.isEmpty() && validate2.isEmpty() && validate3.isEmpty()) {
                        ErrorField.setText("");
                        Staff newStaff = new Staff(staffIDField.getText(), staffNameField.getText(), staffAddressField.getText(), contactField.getText(), ICNoField.getText(), Double.parseDouble(salaryField.getText()), passField2.getText(), 'A');
                        staffC.updateRecord(newStaff);
                        staffSubPane.remove(1);
                        staffSubPane.insertTab("", search, StaffShowList(), "", 1);
                        staffSubPane.setSelectedIndex(1);
                    } else {
                        ErrorField.setText(validate2.concat("\n").concat(validate3).concat("\n").concat(validate4));
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "You may have fill is empty.\nPlease fill those emtpy area", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }else{
                String validate2 = validation.validateAddress(staffAddressField.getText());
                String validate3 = validation.validateContactNo(contactField.getText());
                String validate4=validation.validateSalary(salaryField.getText());
                String validate5=validation.validatePassword(passField1.getText().toString(),passField2.getText().toString());
                if (!staffNameField.getText().isEmpty() && !staffAddressField.getText().isEmpty() && !contactField.getText().isEmpty() && !ICNoField.getText().isEmpty() && !salaryField.getText().isEmpty() && !passField1.toString().isEmpty() && !passField2.toString().isEmpty()) {
                    if (validate4.isEmpty() && validate2.isEmpty() && validate3.isEmpty() && validate5.isEmpty()) {
                        ErrorField.setText("");
                        Staff newStaff = new Staff(staffIDField.getText(), staffNameField.getText(), staffAddressField.getText(), contactField.getText(), ICNoField.getText(), Double.parseDouble(salaryField.getText()), passField2.getText(), 'A');
                        staffC.updateRecord(newStaff);
                        staffSubPane.remove(1);
                        staffSubPane.insertTab("", up, StaffShowList(), "", 1);
                        staffSubPane.setSelectedIndex(1);
                    } else {
                        ErrorField.setText(validate2.concat("\n").concat(validate3).concat("\n").concat(validate4).concat("\n").concat(validate5));
                    }
            }else {
                    JOptionPane.showMessageDialog(null, "You may have fill is empty.\nPlease fill those emtpy area", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return panel;
    }
    public JPanel StaffStatusShowList() {
        JPanel panel = new JPanel();
        JPanel jPanel1 = new javax.swing.JPanel();
        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        JTable staffList = new javax.swing.JTable();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Staff List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Fax", 1, 24))); // NOI18N

        staffList.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        staffList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Staff ID", "Staff Name", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        staffList.setMaximumSize(new java.awt.Dimension(225, 0));
        staffList.setMinimumSize(new java.awt.Dimension(225, 0));
        staffList.getTableHeader().setReorderingAllowed(false);
  
        jScrollPane1.setViewportView(staffList);
        if (staffList.getColumnModel().getColumnCount() > 0) {
            staffList.getColumnModel().getColumn(0).setResizable(false);
            staffList.getColumnModel().getColumn(0).setPreferredWidth(1);
            staffList.getColumnModel().getColumn(1).setResizable(false);
            staffList.getColumnModel().getColumn(2).setResizable(false);
            staffList.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 584, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 11, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        DefaultTableModel model = (DefaultTableModel) staffList.getModel();
        ArrayList<Staff> staffL = staffC.getAllStaff();
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(staffList.getModel());
        staffList.setRowSorter(sorter);
        int i = 1;
        for (Staff s : staffL) {
            if (s.getStatus() == 'A') {
                model.addRow(new Object[]{Integer.toString(i), s.getStaffID(), s.getStaffName(), "Activate"});

            } else if (s.getStatus() == 'D') {
                model.addRow(new Object[]{Integer.toString(i), s.getStaffID(), s.getStaffName(), "Deactivate"});

            }
            i++;
        }
        //Table on click
        staffList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JTable source = (JTable) evt.getSource();
                if (evt.getClickCount() == 1) {
                    int row = source.getSelectedRow();
                    String id = source.getModel().getValueAt(source.convertRowIndexToModel(row), 1).toString();
                    staffSubPane.remove(2);
                    staffSubPane.insertTab("", deactivate, StaffDeactivate(id), "", 2);
                    staffSubPane.setSelectedIndex(2);
                }
            }
        });

        return panel;
    }
    public JPanel StaffDeactivate(String id) {
        JPanel panel = new JPanel();
        JPanel jPanel1 = new javax.swing.JPanel();
        JLabel jLabel1 = new javax.swing.JLabel();
        JLabel jLabel2 = new javax.swing.JLabel();
        JTextField staffIDField = new javax.swing.JTextField();
        JLabel jLabel3 = new javax.swing.JLabel();
        JTextField staffNameField = new javax.swing.JTextField();
        JLabel jLabel4 = new javax.swing.JLabel();
        JTextField contactField = new javax.swing.JTextField();
        JLabel jLabel5 = new javax.swing.JLabel();
        JLabel jLabel6 = new javax.swing.JLabel();
        JTextField ICNoField = new javax.swing.JTextField();
        JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
        JTextArea staffAddressField = new javax.swing.JTextArea();
        JLabel jLabel9 = new javax.swing.JLabel();
        JTextField salaryField = new javax.swing.JTextField();
        JLabel jLabel10 = new javax.swing.JLabel();
        JTextField statusField = new javax.swing.JTextField();
        JTextField staffPositionList = new javax.swing.JTextField();
        JButton activate = new javax.swing.JButton();
        JButton cancel = new javax.swing.JButton();
        JButton deactivate1 = new javax.swing.JButton();

        panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        panel.setMaximumSize(new java.awt.Dimension(612, 439));
        panel.setMinimumSize(new java.awt.Dimension(612, 439));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true), "Staff Info", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Fax", 1, 18))); // NOI18N
        jPanel1.setMaximumSize(new java.awt.Dimension(588, 254));
        jPanel1.setMinimumSize(new java.awt.Dimension(588, 254));

        jLabel1.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel1.setText("Position");

        jLabel2.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel2.setText("Staff ID :");

        staffIDField.setEditable(false);
        staffIDField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel3.setText("Staff Name :");

        staffNameField.setEditable(false);
        staffNameField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        staffNameField.setMaximumSize(new java.awt.Dimension(6, 22));

        jLabel4.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel4.setText("Contact No :");

        contactField.setEditable(false);
        contactField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        contactField.setMaximumSize(new java.awt.Dimension(6, 22));

        jLabel5.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel5.setText("Address :");

        jLabel6.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel6.setText("Ic No :");

        ICNoField.setEditable(false);
        ICNoField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        ICNoField.setMaximumSize(new java.awt.Dimension(6, 22));

        staffAddressField.setEditable(false);
        staffAddressField.setColumns(20);
        staffAddressField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        staffAddressField.setRows(5);
        staffAddressField.setMaximumSize(new java.awt.Dimension(4, 24));
        jScrollPane2.setViewportView(staffAddressField);

        jLabel9.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel9.setText("Salary :");

        salaryField.setEditable(false);
        salaryField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel10.setText("Status : ");

        statusField.setEditable(false);
        statusField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N

        staffPositionList.setEditable(false);
        staffPositionList.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(staffNameField, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(staffPositionList)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(staffIDField, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(contactField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ICNoField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(salaryField, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                                .addComponent(statusField))
                        .addGap(33, 33, 33))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(staffIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(staffPositionList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(staffNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(contactField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(ICNoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(salaryField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(statusField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        activate.setFont(new java.awt.Font("Lucida Fax", 0, 14)); // NOI18N
        activate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/activate.png"))); // NOI18N
        activate.setText("Activate");

        cancel.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.png"))); // NOI18N
        cancel.setText("Cancel");

        deactivate1.setFont(new java.awt.Font("Lucida Fax", 0, 14)); // NOI18N
        deactivate1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/deactivate1.png"))); // NOI18N
        deactivate1.setText("Deactivate");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                .addGroup(layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(activate)
                        .addGap(41, 41, 41)
                        .addComponent(deactivate1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(activate, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(deactivate1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(137, Short.MAX_VALUE))
        );
        staffAddressField.setLineWrap(true);
        Staff staff = staffC.selectRecord(id);
        staffIDField.setText(staff.getStaffID());
        if (staff.getStaffID().substring(0, 1).equals("S")) {
            staffPositionList.setText("Staff");
        } else {
            staffPositionList.setText("Manager");
        }
        staffNameField.setText(staff.getStaffName());
        staffAddressField.setText(staff.getAddress());
        salaryField.setText(Double.toString(staff.getSalary()));
        ICNoField.setText(staff.getIcNo());
        contactField.setText(staff.getContactNo());
        if (staff.getStatus() == 'A') {
            statusField.setText("Activate");
        } else {
            statusField.setText("Deactivate");
        }

        cancel.addActionListener((ActionEvent evt) -> {
            staffSubPane.remove(2);
            staffSubPane.insertTab("", deactivate, StaffStatusShowList(), "", 2);
            staffSubPane.setSelectedIndex(2);
        });
        activate.addActionListener((ActionEvent evt) -> {
            int result = JOptionPane.showConfirmDialog(this, "Are your sure want to activate " + staffNameField.getText() + "", "Warning", JOptionPane.YES_NO_OPTION);
            if (result == 0) {
                Staff s = new Staff(staffIDField.getText(), 'A');
                staffC.activateOrDeactivateStaff(s);
                
                staffSubPane.remove(2);
                staffSubPane.insertTab("", deactivate, StaffStatusShowList(), "", 2);
                staffSubPane.setSelectedIndex(2);

            } else {
                staffSubPane.remove(2);
                staffSubPane.insertTab("", deactivate, StaffStatusShowList(), "", 2);
                staffSubPane.setSelectedIndex(2);
            }
        });
        deactivate1.addActionListener((ActionEvent evt) -> {
            int result = JOptionPane.showConfirmDialog(this, "Are your sure want to deactivate " + staffNameField.getText() + "", "Warning", JOptionPane.YES_NO_OPTION);
            if (result == 0) {
                Staff s = new Staff(staffIDField.getText(), 'D');
                staffC.activateOrDeactivateStaff(s);
                
                staffSubPane.remove(2);
                staffSubPane.insertTab("", deactivate, StaffStatusShowList(), "", 2);
                staffSubPane.setSelectedIndex(2);

            } else {
                staffSubPane.remove(2);
                staffSubPane.insertTab("", deactivate, StaffStatusShowList(), "", 2);
                staffSubPane.setSelectedIndex(2);
            }
        });

        return panel;
    }
    public JPanel StaffSearch() {
        JPanel panel = new JPanel();
        DefaultTableModel model;
        JPanel jPanel1 = new javax.swing.JPanel();
        JLabel jLabel1 = new javax.swing.JLabel();
        JComboBox criteriaList = new javax.swing.JComboBox();
        JTextField searchField = new javax.swing.JTextField();
        JButton search = new javax.swing.JButton();
        JButton all = new javax.swing.JButton();
        JButton reset = new javax.swing.JButton();
        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        JTable staffTable = new javax.swing.JTable();
        JTextField ErrorField = new javax.swing.JTextField();

        panel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Staff Search", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Fax", 1, 18))); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Criteria", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Fax", 0, 16))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel1.setText("Search By : ");

        criteriaList.setFont(new java.awt.Font("Lucida Fax", 0, 14)); // NOI18N
        criteriaList.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"","Staff ID","Staff Name", "IC No with(-)", "Status(a/d)"}));

        searchField.setEditable(false);
        searchField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N

        search.setFont(new java.awt.Font("Lucida Fax", 0, 14)); // NOI18N
        search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/view.png"))); // NOI18N
        search.setText("Search");

        all.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        all.setText("All");

        reset.setFont(new java.awt.Font("Lucida Fax", 0, 14)); // NOI18N
        reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.png"))); // NOI18N
        reset.setText("Reset");

        ErrorField.setBackground(new java.awt.Color(240, 240, 240));
        ErrorField.setFont(new java.awt.Font("Lucida Fax", 0, 11)); // NOI18N
        ErrorField.setForeground(new java.awt.Color(255, 0, 0));
        ErrorField.setBorder(null);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(111, 111, 111)
                                        .addComponent(search)
                                        .addGap(67, 67, 67)
                                        .addComponent(reset))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(99, 99, 99)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(ErrorField, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(criteriaList, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(all)))))
                        .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(criteriaList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(all))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(ErrorField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(reset, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        staffTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Staff ID", "Staff Name", "IC No", "Contact No", "Status"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jScrollPane1.setViewportView(staffTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(96, Short.MAX_VALUE))
                .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
        );
        model = (DefaultTableModel) staffTable.getModel();
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(staffTable.getModel());
        staffTable.setRowSorter(sorter);

        criteriaList.addActionListener((ActionEvent evt) -> {
            if(criteriaList.getSelectedItem().toString().equals("")){
                ErrorField.setText("Please choose a criteria");
            }else{
                ErrorField.setText("");
                searchField.setEditable(true);
                searchField.requestFocusInWindow();
            }
        });
        all.addActionListener((ActionEvent evt) -> {
            model.setRowCount(0);
            ArrayList<Staff> staffList = staffC.getAllStaff();
            for (Staff s : staffList) {
                model.addRow(new Object[]{s.getStaffID(), s.getStaffName(), s.getIcNo(), s.getContactNo(), s.getStatus()});
            }

        });
        search.addActionListener((ActionEvent evt) -> {
            if (criteriaList.getSelectedItem().toString().equals("")) {
                ErrorField.setText("Please choose a criteria.");
            } else {
                model.setRowCount(0);
                if (criteriaList.getSelectedItem().toString().equals("Staff ID")) {
                    if (searchField.getText().isEmpty()) {
                        ErrorField.setText("Enter Staff ID");
                    } else {
                        Staff staff = staffC.selectRecord(searchField.getText());
                        if (staff != null) {
                            ErrorField.setText("");
                            model.addRow(new Object[]{staff.getStaffID(), staff.getStaffName(), staff.getIcNo(), staff.getContactNo(), staff.getStatus()});
                        } else {
                            ErrorField.setText("Record Not Found !!!");
                        }
                    }
                } else if (criteriaList.getSelectedItem().toString().equals("Staff Name")) {
                    if (searchField.getText().isEmpty()) {
                        ErrorField.setText("Enter Staff Name");
                    } else {
                        ArrayList<Staff> staffList = staffC.searchByName(searchField.getText().toUpperCase());
                        if (staffList != null) {
                            ErrorField.setText("");
                            for (Staff s : staffList) {
                                model.addRow(new Object[]{s.getStaffID(), s.getStaffName(), s.getIcNo(), s.getContactNo(), s.getStatus()});
                            }
                        } else {
                            ErrorField.setText("Record Not Found !!!");
                        }
                    }
                } else if (criteriaList.getSelectedItem().toString().equals("IC No with(-)")) {
                    ErrorField.setText("IC No must include -");
                    if (searchField.getText().isEmpty()) {
                        ErrorField.setText("");
                        ErrorField.setText("Enter IC No");
                    } else {
                        Staff s = staffC.searchByIC(searchField.getText());
                        if (s != null) {
                            ErrorField.setText("");
                            model.addRow(new Object[]{s.getStaffID(), s.getStaffName(), s.getIcNo(), s.getContactNo(), s.getStatus()});

                        } else {
                            ErrorField.setText("Record Not Found !!!");
                        }
                    }
                } else if (criteriaList.getSelectedItem().toString().equals("Status(a/d)")) {
                    model.setRowCount(0);
                    ArrayList<Staff> s = null;
                    if (searchField.getText().isEmpty()) {
                        ErrorField.setText("Enter IC No");
                    } else {
                        if (searchField.getText().toUpperCase().equals("A")) {
                            s = staffC.searchByStatus("A");
                        } else if (searchField.getText().toUpperCase().equals("D")) {
                            s = staffC.searchByStatus("D");
                        }
                        if (s != null) {
                            ErrorField.setText("");
                            for (Staff s1 : s) {
                                if (s1.getStatus() == 'A') {
                                    model.addRow(new Object[]{s1.getStaffID(), s1.getStaffName(), s1.getIcNo(), s1.getContactNo(), "Activate"});

                                } else if (s1.getStatus() == 'D') {
                                    model.addRow(new Object[]{s1.getStaffID(), s1.getStaffName(), s1.getIcNo(), s1.getContactNo(), "Deactive"});

                                }
                            }
                        } else {
                            ErrorField.setText("Record Not Found !!!");
                        }
                    }
                }

            }
        });
        
        reset.addActionListener((ActionEvent evt) -> {
            criteriaList.setSelectedIndex(0);
            ErrorField.setText("");
            model.setRowCount(0);
            searchField.setText("");
            searchField.setEditable(false);
            
        });

        return panel;
    }

    //Driver CRUD
    public JTabbedPane DriverPane() {
        driverSubPane.addTab("", create, DriverCreate());
        driverSubPane.addTab("", up, ShowDriverList());
        driverSubPane.addTab("", deactivate, ShowDriverStatusList());
        driverSubPane.addTab("", search, DriverSearch());
        driverSubPane.setTabPlacement(JTabbedPane.LEFT);
        driverSubPane.setPreferredSize(new Dimension(720, 600));
        
        return driverSubPane;
    }
    public JPanel DriverCreate() {

        JPanel panel = new JPanel();
        JPanel jPanel1 = new javax.swing.JPanel();
        JLabel jLabel2 = new javax.swing.JLabel();
        JTextField driverIDField = new javax.swing.JTextField();
        JLabel jLabel3 = new javax.swing.JLabel();
        JTextField driverNameField = new javax.swing.JTextField();
        JLabel jLabel4 = new javax.swing.JLabel();
        JTextField contactField = new javax.swing.JTextField();
        JLabel jLabel5 = new javax.swing.JLabel();
        JLabel jLabel6 = new javax.swing.JLabel();
        JTextField ICNoField = new javax.swing.JTextField();
        JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
        JTextArea driverAddressField = new javax.swing.JTextArea();
        JLabel jLabel9 = new javax.swing.JLabel();
        JTextField salaryField = new javax.swing.JTextField();
        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        JTextArea ErrorField = new javax.swing.JTextArea();
        JButton create1 = new javax.swing.JButton();
        JButton cancel = new javax.swing.JButton();

        panel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panel.setMaximumSize(new java.awt.Dimension(612, 439));
        panel.setMinimumSize(new java.awt.Dimension(612, 439));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true), "Driver Info", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Fax", 1, 18))); // NOI18N
        jPanel1.setMaximumSize(new java.awt.Dimension(588, 254));
        jPanel1.setMinimumSize(new java.awt.Dimension(588, 254));

        jLabel2.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel2.setText("Driver ID : ");

        driverIDField.setEditable(false);
        driverIDField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel3.setText("Driver Name : ");

        driverNameField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        driverNameField.setMaximumSize(new java.awt.Dimension(6, 22));

        jLabel4.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel4.setText("Contact No :");

        contactField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        contactField.setMaximumSize(new java.awt.Dimension(6, 22));

        jLabel5.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel5.setText("Address :");

        jLabel6.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel6.setText("Ic No :");

        ICNoField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        ICNoField.setMaximumSize(new java.awt.Dimension(6, 22));

        driverAddressField.setColumns(20);
        driverAddressField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        driverAddressField.setRows(5);
        driverAddressField.setMaximumSize(new java.awt.Dimension(4, 24));
        jScrollPane2.setViewportView(driverAddressField);
        driverAddressField.setLineWrap(true);
        jLabel9.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel9.setText("Salary :");

        salaryField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N

        ErrorField.setEditable(false);
        ErrorField.setBackground(new java.awt.Color(240, 240, 240));
        ErrorField.setColumns(20);
        ErrorField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        ErrorField.setForeground(new java.awt.Color(255, 0, 0));
        ErrorField.setRows(5);
        ErrorField.setBorder(null);
        jScrollPane1.setViewportView(ErrorField);

        create1.setFont(new java.awt.Font("Lucida Fax", 0, 14)); // NOI18N
        create1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Users-Add-user-icon_large.png"))); // NOI18N
        create1.setText("Create");

        cancel.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.png"))); // NOI18N
        cancel.setText("Cancel");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(salaryField, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 5, Short.MAX_VALUE)
                                                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                        .addGap(9, 9, 9)
                                                                        .addComponent(driverIDField, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(0, 0, Short.MAX_VALUE)))))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGap(10, 10, 10)
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jLabel4)
                                                                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(contactField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(ICNoField, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                        .addComponent(jLabel3)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                        .addComponent(driverNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGap(27, 27, 27))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGap(18, 18, 18)
                                                        .addComponent(jScrollPane1)
                                                        .addContainerGap())))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(create1)
                                        .addGap(31, 31, 31)
                                        .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(driverIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(driverNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(contactField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(ICNoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(salaryField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGap(42, 42, 42)))
                                        .addGap(1, 1, 1)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(create1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
        );
        driverAddressField.setLineWrap(true);
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(128, Short.MAX_VALUE))
        );
        driverIDField.setText(function.setDriverID('D'));
        cancel.addActionListener((ActionEvent evt) -> {
            driverNameField.setText("");
            driverAddressField.setText("");
            contactField.setText("");
            ICNoField.setText("");
            ICNoField.setText("");
        });
        create1.addActionListener((ActionEvent evt) -> {
            String validate1 = validation.validateName(driverNameField.getText());
            String validate2 = validation.validateAddress(driverAddressField.getText());
            String validate3 = validation.validateContactNo(contactField.getText());
            String validate4 = validation.validateICNo(ICNoField.getText());
            String validate5=validation.validateSalary(salaryField.getText());
            if (!driverNameField.getText().isEmpty() && !driverAddressField.getText().isEmpty() && !contactField.getText().isEmpty() && !ICNoField.getText().isEmpty() && !salaryField.getText().isEmpty()) {
                if (validate1.isEmpty() && validate2.isEmpty() && validate3.isEmpty() && validate4.isEmpty() && validate5.isEmpty()) {
                    Driver newDriver = new Driver(driverIDField.getText(), driverNameField.getText(), driverAddressField.getText(), contactField.getText(), ICNoField.getText(), Double.parseDouble(salaryField.getText()), 'A');
                    driverC.insetRecord(newDriver);
                    ErrorField.setText("");
                    driverSubPane.removeAll();
                    driverSubPane.revalidate();
                    driverSubPane.repaint();
                    driverSubPane.addTab("", create, DriverCreate());
                    driverSubPane.addTab("", up, ShowDriverList());
                    driverSubPane.addTab("", deactivate, ShowDriverStatusList());
                    driverSubPane.addTab("", search, DriverSearch());
                    driverSubPane.setSelectedIndex(0);

                } else {
                    ErrorField.setText(validate1.concat("\n").concat(validate2).concat("\n").concat(validate3).concat("\n").concat(validate4).concat("\n").concat("\n").concat(validate5));
                }
            } else {
                JOptionPane.showMessageDialog(null, "You may have fill is empty.\nPlease fill those emtpy area", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        return panel;
    }
    public JPanel ShowDriverList() {
        JPanel panel = new JPanel();
        JPanel jPanel1 = new javax.swing.JPanel();
        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        JTable driverList = new javax.swing.JTable();
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Driver List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Fax", 1, 24))); // NOI18N

        driverList.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        driverList.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "No", "Driver ID", "Driver Name"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        driverList.setMaximumSize(new java.awt.Dimension(225, 0));
        driverList.setMinimumSize(new java.awt.Dimension(225, 0));
        driverList.getTableHeader().setReorderingAllowed(false);

        jScrollPane1.setViewportView(driverList);
        if (driverList.getColumnModel().getColumnCount() > 0) {
            driverList.getColumnModel().getColumn(0).setResizable(false);
            driverList.getColumnModel().getColumn(0).setPreferredWidth(1);
            driverList.getColumnModel().getColumn(1).setResizable(false);
            driverList.getColumnModel().getColumn(2).setResizable(false);

        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 584, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 11, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(20, Short.MAX_VALUE))
        );
        DefaultTableModel model = (DefaultTableModel) driverList.getModel();
        ArrayList<Driver> driverL = driverC.getAllRecord();
        int i = 1;
        for (Driver s : driverL) {
            model.addRow(new Object[]{Integer.toString(i), s.getDriverID(), s.getDriverName()});
            i++;
        }
        driverList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JTable source = (JTable) evt.getSource();
                if (evt.getClickCount() == 1) {
                    int row = source.getSelectedRow();
                    String id = source.getModel().getValueAt(source.convertRowIndexToModel(row), 1).toString();
                    driverSubPane.remove(1);
                    driverSubPane.insertTab("", up, DriverUpdate(id), "", 1);
                    driverSubPane.setSelectedIndex(1);

                }
            }
        });

        return panel;
    }
    public JPanel DriverUpdate(String id) {
        JPanel panel = new JPanel();
        JPanel jPanel1 = new javax.swing.JPanel();
        JLabel jLabel2 = new javax.swing.JLabel();
        JTextField driverIDField = new javax.swing.JTextField();
        JLabel jLabel3 = new javax.swing.JLabel();
        JTextField driverNameField = new javax.swing.JTextField();
        JLabel jLabel4 = new javax.swing.JLabel();
        JTextField contactField = new javax.swing.JTextField();
        JLabel jLabel5 = new javax.swing.JLabel();
        JLabel jLabel6 = new javax.swing.JLabel();
        JTextField ICNoField = new javax.swing.JTextField();
        JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
        JTextArea driverAddressField = new javax.swing.JTextArea();
        JLabel jLabel9 = new javax.swing.JLabel();
        JTextField salaryField = new javax.swing.JTextField();
        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        JTextArea ErrorField = new javax.swing.JTextArea();
        JButton update = new javax.swing.JButton();
        JButton cancel = new javax.swing.JButton();

        panel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panel.setMaximumSize(new java.awt.Dimension(612, 439));
        panel.setMinimumSize(new java.awt.Dimension(612, 439));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true), "Driver Update", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Fax", 1, 18))); // NOI18N
        jPanel1.setMaximumSize(new java.awt.Dimension(588, 254));
        jPanel1.setMinimumSize(new java.awt.Dimension(588, 254));

        jLabel2.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel2.setText("Driver ID : ");

        driverIDField.setEditable(false);
        driverIDField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel3.setText("Driver Name : ");

        driverNameField.setEditable(false);
        driverNameField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        driverNameField.setMaximumSize(new java.awt.Dimension(6, 22));

        jLabel4.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel4.setText("Contact No :");

        contactField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        contactField.setMaximumSize(new java.awt.Dimension(6, 22));

        jLabel5.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel5.setText("Address :");

        jLabel6.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel6.setText("Ic No :");

        ICNoField.setEditable(false);
        ICNoField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        ICNoField.setMaximumSize(new java.awt.Dimension(6, 22));

        driverAddressField.setColumns(20);
        driverAddressField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        driverAddressField.setRows(5);
        driverAddressField.setMaximumSize(new java.awt.Dimension(4, 24));
        jScrollPane2.setViewportView(driverAddressField);
        driverAddressField.setLineWrap(true);
        jLabel9.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel9.setText("Salary :");

        salaryField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N

        ErrorField.setEditable(false);
        ErrorField.setBackground(new java.awt.Color(240, 240, 240));
        ErrorField.setColumns(20);
        ErrorField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        ErrorField.setForeground(new java.awt.Color(255, 0, 0));
        ErrorField.setRows(5);
        ErrorField.setBorder(null);
        jScrollPane1.setViewportView(ErrorField);

        update.setFont(new java.awt.Font("Lucida Fax", 0, 14)); // NOI18N
        update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/update.png"))); // NOI18N
        update.setText("Update");

        cancel.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.png"))); // NOI18N
        cancel.setText("Cancel");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(salaryField, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 5, Short.MAX_VALUE)
                                                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                        .addGap(9, 9, 9)
                                                                        .addComponent(driverIDField, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(0, 0, Short.MAX_VALUE)))))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGap(10, 10, 10)
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jLabel4)
                                                                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(contactField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(ICNoField, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                        .addComponent(jLabel3)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                        .addComponent(driverNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGap(27, 27, 27))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGap(18, 18, 18)
                                                        .addComponent(jScrollPane1)
                                                        .addContainerGap())))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(update)
                                        .addGap(31, 31, 31)
                                        .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(driverIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(driverNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(contactField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(ICNoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(salaryField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGap(42, 42, 42)))
                                        .addGap(1, 1, 1)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(128, Short.MAX_VALUE))
        );
        Driver driver = driverC.selectRecord(id);
        driverIDField.setText(driver.getDriverID());
        driverNameField.setText(driver.getDriverName());
        driverAddressField.setText(driver.getAddress());
        contactField.setText(driver.getContactNo());
        ICNoField.setText(driver.getIcNo());
        salaryField.setText(Double.toString(driver.getSalary()));
        cancel.addActionListener((ActionEvent evt) -> {
            driverSubPane.remove(1);
            driverSubPane.insertTab("", up, ShowDriverList(), "", 1);
            driverSubPane.setSelectedIndex(1);
        });
        update.addActionListener((ActionEvent evt) -> {
            String validate1 = validation.validateSalary(salaryField.getText());
            String validate2 = validation.validateAddress(driverAddressField.getText());
            String validate3=validation.validateSalary(salaryField.getText());
            String validate5 = validation.validateContactNo(contactField.getText());
            if (!salaryField.getText().isEmpty() && !driverAddressField.getText().isEmpty() && !contactField.getText().isEmpty() ) {
                if (validate1.isEmpty() && validate2.isEmpty() && validate5.isEmpty() && validate3.isEmpty()) {
                    Driver newDriver = new Driver(driverIDField.getText(), driverNameField.getText(), driverAddressField.getText(), contactField.getText(), ICNoField.getText(), Double.parseDouble(salaryField.getText()), 'A');
                    driverC.updateRecord(newDriver);
                    driverSubPane.remove(1);
                    driverSubPane.insertTab("", up, ShowDriverList(), "", 1);
                    driverSubPane.setSelectedIndex(1);

                } else if (!validate1.isEmpty() || !validate2.isEmpty() || !validate5.isEmpty() || !validate3.isEmpty()) {
                    ErrorField.setText(validate1.concat("\n").concat(validate2).concat("\n").concat(validate5).concat("\n").concat(validate3));
                }
            } else {
                JOptionPane.showMessageDialog(null, "You may have fill is empty.\nPlease fill those emtpy area", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }
    public JPanel ShowDriverStatusList() {
        JPanel panel = new JPanel();
        JPanel jPanel1 = new javax.swing.JPanel();
        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        JTable driverList = new javax.swing.JTable();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Driver List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Fax", 1, 24))); // NOI18N

        driverList.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        driverList.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "No", "Driver ID", "Driver Name","Status"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        driverList.setMaximumSize(new java.awt.Dimension(225, 0));
        driverList.setMinimumSize(new java.awt.Dimension(225, 0));
        driverList.getTableHeader().setReorderingAllowed(false);

        jScrollPane1.setViewportView(driverList);
        if (driverList.getColumnModel().getColumnCount() > 0) {
            driverList.getColumnModel().getColumn(0).setResizable(false);
            driverList.getColumnModel().getColumn(0).setPreferredWidth(1);
            driverList.getColumnModel().getColumn(1).setResizable(false);
            driverList.getColumnModel().getColumn(2).setResizable(false);
            driverList.getColumnModel().getColumn(3).setResizable(false);

        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 584, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 11, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(20, Short.MAX_VALUE))
        );
        DefaultTableModel model = (DefaultTableModel) driverList.getModel();
        ArrayList<Schedule> ssList=scheduleC.selectRecordByDate(ft.format(new Date()));
        
        ArrayList<Driver> driverL = driverC.getAllRecord();
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(driverList.getModel());
        driverList.setRowSorter(sorter);
        int i = 1;
        int num=0;
        for (Driver d : driverL) {
            num=0;
            for (Schedule s : ssList) {
                if (d.getDriverID().equals(s.getDriver().getDriverID())) {
                    num++;
                }
            }
            if (num == 0) {
                if (d.getStatus() == 'A') {
                    model.addRow(new Object[]{Integer.toString(i), d.getDriverID(), d.getDriverName(), "Activate"});
                } else {
                    model.addRow(new Object[]{Integer.toString(i), d.getDriverID(), d.getDriverName(), "Deactivate"});
                }
            }
            i++;
        }

        driverList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JTable source = (JTable) evt.getSource();
                if (evt.getClickCount() == 1) {
                    int row = source.getSelectedRow();
                    String id = source.getModel().getValueAt(source.convertRowIndexToModel(row), 1).toString();
                    driverSubPane.remove(2);
                    driverSubPane.insertTab("", deactivate, DriverDeactivate(id), "", 2);
                    driverSubPane.setSelectedIndex(2);

                }
            }
        });

        return panel;
    }
    public JPanel DriverDeactivate(String id) {
        JPanel panel = new JPanel();
        JPanel jPanel1 = new javax.swing.JPanel();
        JLabel jLabel2 = new javax.swing.JLabel();
        JTextField driverIDField = new javax.swing.JTextField();
        JLabel jLabel3 = new javax.swing.JLabel();
        JTextField driverNameField = new javax.swing.JTextField();
        JLabel jLabel4 = new javax.swing.JLabel();
        JTextField contactField = new javax.swing.JTextField();
        JLabel jLabel5 = new javax.swing.JLabel();
        JLabel jLabel6 = new javax.swing.JLabel();
        JTextField ICNoField = new javax.swing.JTextField();
        JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
        JTextArea driverAddressField = new javax.swing.JTextArea();
        JLabel jLabel9 = new javax.swing.JLabel();
        JTextField salaryField = new javax.swing.JTextField();
        JLabel jLabel10 = new javax.swing.JLabel();
        JTextField statusField = new javax.swing.JTextField();
        JButton activate = new javax.swing.JButton();
        JButton cancel = new javax.swing.JButton();
        JButton deactivate1 = new javax.swing.JButton();

       
        panel.setMaximumSize(new java.awt.Dimension(612, 439));
        panel.setMinimumSize(new java.awt.Dimension(612, 439));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true), "Driver Info", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Fax", 1, 18))); // NOI18N
        jPanel1.setMaximumSize(new java.awt.Dimension(588, 254));
        jPanel1.setMinimumSize(new java.awt.Dimension(588, 254));

        jLabel2.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel2.setText("Driver ID :");

        driverIDField.setEditable(false);
        driverIDField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel3.setText("Driver Name :");

        driverNameField.setEditable(false);
        driverNameField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        driverNameField.setMaximumSize(new java.awt.Dimension(6, 22));

        jLabel4.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel4.setText("Contact No :");

        contactField.setEditable(false);
        contactField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        contactField.setMaximumSize(new java.awt.Dimension(6, 22));

        jLabel5.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel5.setText("Address :");

        jLabel6.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel6.setText("Ic No :");

        ICNoField.setEditable(false);
        ICNoField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        ICNoField.setMaximumSize(new java.awt.Dimension(6, 22));

        driverAddressField.setEditable(false);
        driverAddressField.setColumns(20);
        driverAddressField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        driverAddressField.setRows(5);
        driverAddressField.setMaximumSize(new java.awt.Dimension(4, 24));
        jScrollPane2.setViewportView(driverAddressField);

        jLabel9.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel9.setText("Salary :");

        salaryField.setEditable(false);
        salaryField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel10.setText("Status : ");

        statusField.setEditable(false);
        statusField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(salaryField, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(driverIDField, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel4)
                                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(contactField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(statusField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(ICNoField, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(driverNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(33, 33, 33))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(driverIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(driverNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(contactField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(ICNoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(statusField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(salaryField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(45, Short.MAX_VALUE))
        );

        activate.setFont(new java.awt.Font("Lucida Fax", 0, 14)); // NOI18N
        activate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/activate.png"))); // NOI18N
        activate.setText("Activate");

        cancel.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.png"))); // NOI18N
        cancel.setText("Cancel");

        deactivate1.setFont(new java.awt.Font("Lucida Fax", 0, 14)); // NOI18N
        deactivate1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/deactivate1.png"))); // NOI18N
        deactivate1.setText("Deactivate");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                .addGroup(layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(activate)
                        .addGap(41, 41, 41)
                        .addComponent(deactivate1)
                        .addContainerGap(88, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(activate, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(deactivate1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        driverAddressField.setLineWrap(true);
        Driver driver = driverC.selectRecord(id);
        driverIDField.setText(driver.getDriverID());
        driverNameField.setText(driver.getDriverName());
        driverAddressField.setText(driver.getAddress());
        contactField.setText(driver.getContactNo());
        ICNoField.setText(driver.getIcNo());
        salaryField.setText(Double.toString(driver.getSalary()));
        if (driver.getStatus() == 'A') {
            statusField.setText("Activate");
        } else {
            statusField.setText("Deactivate");
        }
        cancel.addActionListener((ActionEvent evt) -> {
            driverSubPane.remove(2);
            driverSubPane.insertTab("", deactivate, ShowDriverStatusList(), "", 2);
            driverSubPane.setSelectedIndex(2);
        });
        activate.addActionListener((ActionEvent evt) -> {
            int result = JOptionPane.showConfirmDialog(this, "Are your sure want to activate " + driver.getDriverName() + "", "Warning", JOptionPane.YES_NO_OPTION);
            if (result == 0) {
                Driver staff = new Driver(driver.getDriverID(), 'A');
                driverC.activateOrDeactivateStaff(staff);
                driverSubPane.remove(2);
                driverSubPane.insertTab("", up, ShowDriverStatusList(), "", 2);
                driverSubPane.setSelectedIndex(2);
            } else {
                driverSubPane.remove(2);
                driverSubPane.insertTab("", deactivate, ShowDriverStatusList(), "", 2);
                driverSubPane.setSelectedIndex(2);
            }
        });
        deactivate1.addActionListener((ActionEvent evt) -> {
            
            int result = JOptionPane.showConfirmDialog(this, "Are your sure want to deactivate " + driver.getDriverName() + "", "Warning", JOptionPane.YES_NO_OPTION);
            if (result == 0) {
                Driver staff = new Driver(driver.getDriverID(), 'D');
                driverC.activateOrDeactivateStaff(staff);
                driverSubPane.remove(2);
                driverSubPane.insertTab("", deactivate, ShowDriverStatusList(), "", 2);
                driverSubPane.setSelectedIndex(2);
            } else {
                driverSubPane.remove(2);
                driverSubPane.insertTab("", deactivate, ShowDriverStatusList(), "", 2);
                driverSubPane.setSelectedIndex(2);
            }
        });
        return panel;
    }
    public JPanel DriverSearch() {
        JPanel panel = new JPanel();
        DefaultTableModel model;
        JPanel jPanel1 = new javax.swing.JPanel();
        JLabel jLabel1 = new javax.swing.JLabel();
        JComboBox criteriaList = new javax.swing.JComboBox();
        JTextField searchField = new javax.swing.JTextField();
        JButton search = new javax.swing.JButton();
        JButton all = new javax.swing.JButton();
        JButton reset = new javax.swing.JButton();
        JTextField ErrorField = new javax.swing.JTextField();
        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        JTable driverTable = new javax.swing.JTable();

        panel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Driver Search", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Fax", 1, 18))); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Criteria", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Fax", 0, 16))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel1.setText("Search By : ");

        criteriaList.setFont(new java.awt.Font("Lucida Fax", 0, 14)); // NOI18N
        criteriaList.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"", "Driver ID", "Driver Name", "IC No with(-)", "Status (a/d)"}));

        searchField.setEditable(true);
        searchField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N

        search.setFont(new java.awt.Font("Lucida Fax", 0, 14)); // NOI18N
        search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/view.png"))); // NOI18N
        search.setText("Search");

        all.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        all.setText("All");

        reset.setFont(new java.awt.Font("Lucida Fax", 0, 14)); // NOI18N
        reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.png"))); // NOI18N
        reset.setText("Reset");

        ErrorField.setBackground(new java.awt.Color(240, 240, 240));
        ErrorField.setFont(new java.awt.Font("Lucida Fax", 0, 11)); // NOI18N
        ErrorField.setForeground(new java.awt.Color(255, 0, 0));
        ErrorField.setBorder(null);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(111, 111, 111)
                                        .addComponent(search)
                                        .addGap(67, 67, 67)
                                        .addComponent(reset))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(99, 99, 99)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(criteriaList, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(all))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(97, 97, 97)
                                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(ErrorField, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)))
                        .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(criteriaList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(all))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(ErrorField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(reset, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        driverTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Driver ID", "Driver Name", "IC No ", "Contact No", "Status "
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jScrollPane1.setViewportView(driverTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(86, Short.MAX_VALUE))
                .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
        );
        model = (DefaultTableModel) driverTable.getModel();
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(driverTable.getModel());
        driverTable.setRowSorter(sorter);
      
        criteriaList.addActionListener((ActionEvent evt) -> {
            if(criteriaList.getSelectedItem().toString().equals("")){
                ErrorField.setText("Please choose a criteria");
            }else{
                ErrorField.setText("");
                searchField.setEditable(true);
            searchField.requestFocusInWindow();
            }
            

        });
        all.addActionListener((ActionEvent evt) -> {
            model.setRowCount(0);
            ArrayList<Driver> driverList = driverC.getAllRecord();
            for (Driver s : driverList) {
                model.addRow(new Object[]{s.getDriverID(), s.getDriverName(), s.getIcNo(), s.getContactNo(), s.getStatus()});
            }

        });
        search.addActionListener((ActionEvent evt) -> {
            if (criteriaList.getSelectedItem().toString().equals("")) {
                ErrorField.setText("Please choose a criteria.");
            } else {
                model.setRowCount(0);
                if (criteriaList.getSelectedItem().toString().equals("Driver ID")) {
                    if (searchField.getText().isEmpty()) {
                        ErrorField.setText("Enter Driver ID");
                    } else {
                        Driver staff = driverC.selectRecord(searchField.getText());
                        if (staff != null) {
                            ErrorField.setText("");
                            model.addRow(new Object[]{staff.getDriverID(), staff.getDriverName(), staff.getIcNo(), staff.getContactNo(), staff.getStatus()});
                        } else {
                            ErrorField.setText("Record Not Found !!!");
                        }
                    }
                } else if (criteriaList.getSelectedItem().toString().equals("Driver Name")) {
                    if (searchField.getText().isEmpty()) {
                        ErrorField.setText("Enter Driver Name");
                    } else {
                        ArrayList<Driver> staffList = driverC.searchByName(searchField.getText().toUpperCase());
                        if (staffList != null) {
                            ErrorField.setText("");
                            for (Driver s : staffList) {
                                model.addRow(new Object[]{s.getDriverID(), s.getDriverName(), s.getIcNo(), s.getContactNo(), s.getStatus()});
                            }
                        } else {
                            ErrorField.setText("Record Not Found !!!");
                        }
                    }
                } else if (criteriaList.getSelectedItem().toString().equals("IC No with(-)")) {
                    if (searchField.getText().isEmpty()) {
                        ErrorField.setText("");
                        ErrorField.setText("Enter IC No");
                    } else {

                        Driver s = driverC.searchByIC(searchField.getText());
                        if (s != null) {
                            ErrorField.setText("");
                            model.addRow(new Object[]{s.getDriverID(), s.getDriverName(), s.getIcNo(), s.getContactNo(), s.getStatus()});

                        } else {
                            ErrorField.setText("Record Not Found !!!");
                        }
                    }
                } else if (criteriaList.getSelectedItem().toString().equals("Status (a/d)")){
                    ArrayList<Driver> s = null;
                    if (searchField.getText().isEmpty()) {
                        ErrorField.setText("Enter a Alphabet");
                    } else {
                        if (searchField.getText().toUpperCase().equals("A")) {
                            s = driverC.searchByStatus("A");
                        } else if (searchField.getText().toUpperCase().equals("D")) {
                            s = driverC.searchByStatus("D");
                        }

                        if (s != null) {
                            ErrorField.setText("");
                            for (Driver s1 : s) {
                                if (s1.getStatus() == 'A') {
                                    model.addRow(new Object[]{s1.getDriverID(), s1.getDriverName(), s1.getIcNo(), s1.getContactNo(), "Activate"});

                                } else if (s1.getStatus() == 'D') {
                                    model.addRow(new Object[]{s1.getDriverID(), s1.getDriverID(), s1.getIcNo(), s1.getContactNo(), "Deactive"});

                                }
                            }
                        } else {
                            ErrorField.setText("Record Not Found !!!");
                        }
                    }
                }

            }
        });
        reset.addActionListener((ActionEvent evt) -> {
            criteriaList.setSelectedIndex(0);
            ErrorField.setText("");
            model.setRowCount(0);
            searchField.setText("");
            searchField.setEditable(false);
        });

        return panel;
    }

    //Bus Type show
    public JPanel ShowBusType() {
        JPanel panel = new JPanel();
        panel.add(new ShowBusType());

        return panel;
    }
    
    //Price CRUD
    public JTabbedPane PricePane(){
        
        priceSubPane.addTab("", money, PriceCreate());
        priceSubPane.addTab("", search, PriceSearch());
        priceSubPane.setTabPlacement(JTabbedPane.LEFT);
        priceSubPane.setPreferredSize(new Dimension(720, 600));
        return priceSubPane;
    }
    public JPanel PriceCreate(){
        JPanel panel=new JPanel();
        JPanel jPanel1 = new javax.swing.JPanel();
        JLabel jLabel1 = new javax.swing.JLabel();
        JTextField priceIDField = new javax.swing.JTextField();
        JLabel jLabel2 = new javax.swing.JLabel();
        JTextField priceField = new javax.swing.JTextField();
        JTextField ErrorField = new javax.swing.JTextField();
        JButton cancel = new javax.swing.JButton();
        JButton create = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Price Create", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Fax", 1, 24))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel1.setText("Price ID :");

        priceIDField.setEditable(false);
        priceIDField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel2.setText("Price : RM");

        priceField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N

        cancel.setFont(new java.awt.Font("Lucida Fax", 0, 14)); // NOI18N
        cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.png"))); // NOI18N
        cancel.setText("Cancel");

        create.setFont(new java.awt.Font("Lucida Fax", 0, 14)); // NOI18N
        create.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/create1.png"))); // NOI18N
        create.setText("Create");

        ErrorField.setEditable(false);
        ErrorField.setFont(new java.awt.Font("Lucida Fax", 0, 14)); // NOI18N
        ErrorField.setForeground(new java.awt.Color(255, 0, 0));
        ErrorField.setBorder(null);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(priceIDField)
                            .addComponent(priceField, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(ErrorField, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(cancel)
                            .addGap(31, 31, 31)
                            .addComponent(create))))
                .addContainerGap(113, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(priceIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(priceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ErrorField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancel)
                    .addComponent(create))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 145, Short.MAX_VALUE))
        );
        priceIDField.setText(function.setPriceID('P'));
        cancel.addActionListener((ActionEvent evt)->{
            priceField.setText("");
        });
        create.addActionListener((ActionEvent evt)->{
                if (!priceField.getText().isEmpty()) {
                    if (priceField.getText().matches("^[0-9]*$")) {
                        priceC.insertRecord(new Price(priceIDField.getText(), Double.parseDouble(priceField.getText())));
                        priceField.setText("");
                        priceIDField.setText(function.setPriceID('P'));
                        priceSubPane.removeAll();
                        priceSubPane.addTab("", money, PriceCreate());
                        priceSubPane.addTab("", search, PriceSearch());
                    } else {
                        ErrorField.setText("Please enter digit only.");
                    }
                }else{
                    ErrorField.setText("");
                    ErrorField.setText("Price field cannot be empty");
                    
                }
        });
        cancel.addActionListener((ActionEvent evt)->{
        ErrorField.setText("");
        priceField.setText("");
    });
        return panel;
    }
    public JPanel PriceSearch(){
        JPanel panel=new JPanel();
        DefaultTableModel model;
        JPanel jPanel1 = new javax.swing.JPanel();
        JLabel jLabel1 = new javax.swing.JLabel();
        JComboBox criteriaList = new javax.swing.JComboBox();
        JTextField range1 = new javax.swing.JTextField();
        JButton search = new javax.swing.JButton();
        JButton all = new javax.swing.JButton();
        JButton reset = new javax.swing.JButton();
        JTextField ErrorField = new javax.swing.JTextField();
        JTextField range2 = new javax.swing.JTextField();
        JLabel jLabel2 = new javax.swing.JLabel();
        JTable priceTable = new javax.swing.JTable();
        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        panel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Price Search", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Fax", 1, 18))); // NOI18N

        
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Criteria", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Fax", 0, 16))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel1.setText("Search By : ");

        criteriaList.setFont(new java.awt.Font("Lucida Fax", 0, 14)); // NOI18N
        criteriaList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "Range" }));

        range1.setEditable(false);
        range1.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N

        search.setFont(new java.awt.Font("Lucida Fax", 0, 14)); // NOI18N
        search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/view.png"))); // NOI18N
        search.setText("Search");

        all.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        all.setText("All");

        reset.setFont(new java.awt.Font("Lucida Fax", 0, 14)); // NOI18N
        reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.png"))); // NOI18N
        reset.setText("Reset");

        ErrorField.setBackground(new java.awt.Color(240, 240, 240));
        ErrorField.setFont(new java.awt.Font("Lucida Fax", 0, 11)); // NOI18N
        ErrorField.setForeground(new java.awt.Color(255, 0, 0));
        ErrorField.setBorder(null);

        range2.setEditable(false);
        range2.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Lucida Fax", 0, 14)); // NOI18N
        jLabel2.setText("to");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(criteriaList, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(all))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(range1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(range2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ErrorField, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(search)
                        .addGap(67, 67, 67)
                        .addComponent(reset)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(criteriaList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(all))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(range1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ErrorField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(range2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reset, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        priceTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Price ID", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        priceTable.setMaximumSize(new java.awt.Dimension(150, 0));
        priceTable.setMinimumSize(new java.awt.Dimension(150, 0));
        jScrollPane1.setViewportView(priceTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );
        model = (DefaultTableModel) priceTable.getModel();
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(priceTable.getModel());
        priceTable.setRowSorter(sorter);
        criteriaList.addActionListener((ActionEvent evt) -> {
                if(criteriaList.getSelectedItem().toString().equals("")){
                    ErrorField.setText("Pleasse a criteria.");
                }else{
                    ErrorField.setText("");
                    range1.setEditable(true);
                    range1.requestFocusInWindow();
                    range2.setEditable(true);
                }
           
        });
        range1.addActionListener((ActionEvent evt) -> {
            range2.setEditable(true);
            range2.requestFocusInWindow();

        });
        all.addActionListener((ActionEvent evt) -> {
            model.setRowCount(0);
            range1.setText("");
            range2.setText("");
            ErrorField.setText("");
            ArrayList<Price> priceList = priceC.getAllRecord();
            for (Price s : priceList) {
                model.addRow(new Object[]{s.getPriceID(),String.format("%.2f",s.getPrice())});
                
            }
        });
        search.addActionListener((ActionEvent evt) -> {
            if (criteriaList.getSelectedItem().toString().equals("")) {
                ErrorField.setText("Please choose a criteria.");
            } else {
                model.setRowCount(0);
                
                if (!range1.getText().isEmpty() && !range2.getText().isEmpty()) {
                    if (range1.getText().matches("^[0-9]*$") && range2.getText().matches("^[0-9]*$")) {
                    if (Integer.parseInt(range1.getText()) < Integer.parseInt(range2.getText())) {
                        
                            ArrayList<Price> priceList = priceC.searchByRange(range1.getText(), range2.getText());
                            if (!priceList.isEmpty()) {
                                ErrorField.setText("");
                                for (Price s : priceList) {
                                    model.addRow(new Object[]{s.getPriceID(), String.format("%.2f", s.getPrice())});
                                }
                            } else {
                                ErrorField.setText("");
                                ErrorField.setText("Record Not Found!!!");
                            }
                        } else {
                         ErrorField.setText("");
                        ErrorField.setText("Num 1 must smaller than num 2");
                            
                        }
                    } else {
                       ErrorField.setText("");
                            ErrorField.setText("Please enter digit only!!!");
                    }

                } else {
                    ErrorField.setText("");
                    ErrorField.setText("Enter the range");
                }

            }

        });
        reset.addActionListener((ActionEvent evt) -> {
            model.setRowCount(0);
            criteriaList.setSelectedItem(0);
            ErrorField.setText("");
            range1.setText("");
            range2.setText("");
            range1.setEditable(false);
            range2.setEditable(false);
            
        });
        return panel;
    }
    
    //Bus CRUD
    public JTabbedPane BusPane(){
        busSubPane.addTab("",busCreate , BusCreate());
        busSubPane.addTab("", busDeactivate, ShowBusList());
        busSubPane.addTab("", search, BusSearchTable());
        busSubPane.setTabPlacement(JTabbedPane.LEFT);
        busSubPane.setPreferredSize(new Dimension(720, 600));
        return busSubPane;
    }
    public JPanel BusCreate(){
        JPanel panel=new JPanel();
        ButtonGroup buttonGroup1 = new javax.swing.ButtonGroup();
        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        JTextPane jTextPane1 = new javax.swing.JTextPane();
        JPanel jPanel1 = new javax.swing.JPanel();
        JLabel jLabel1 = new javax.swing.JLabel();
        JTextField busIDField = new javax.swing.JTextField();
        JLabel jLabel2 = new javax.swing.JLabel();
        JRadioButton typeA = new javax.swing.JRadioButton();
        JRadioButton doubleDecker = new javax.swing.JRadioButton();
        JRadioButton typeB = new javax.swing.JRadioButton();
        JLabel jLabel3 = new javax.swing.JLabel();
        JTextField plateNoField = new javax.swing.JTextField();
        JTextField errorField = new javax.swing.JTextField();
        JButton create = new javax.swing.JButton();
        JButton cancel = new javax.swing.JButton();

         jScrollPane1.setViewportView(jTextPane1);


        panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Bus Create", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Fax", 1, 24))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel1.setText("Bus ID : ");

        busIDField.setEditable(false);
        busIDField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel2.setText("Type :");

        buttonGroup1.add(typeA);
        typeA.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        typeA.setText("Type A");

        buttonGroup1.add(doubleDecker);
        doubleDecker.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        doubleDecker.setText("Double Decker");

        buttonGroup1.add(typeB);
        typeB.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        typeB.setText("Type B");

        jLabel3.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel3.setText("Plate No :");

        plateNoField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N

        errorField.setEditable(false);
        errorField.setBackground(new java.awt.Color(240, 240, 240));
        errorField.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        errorField.setForeground(new java.awt.Color(255, 0, 0));
        errorField.setBorder(null);

        create.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        create.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/create1.png"))); // NOI18N
        create.setText("Create");

        cancel.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.png"))); // NOI18N
        cancel.setText("Cancel");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(101, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(create, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(errorField, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(doubleDecker)
                                .addComponent(typeB)
                                .addComponent(busIDField)
                                .addComponent(typeA)
                                .addComponent(plateNoField, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)))))
                .addGap(141, 141, 141))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(busIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(typeA))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(typeB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(doubleDecker)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(plateNoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errorField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(create, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(63, Short.MAX_VALUE))
        );
        busIDField.setText(function.setBusID('B'));
        typeA.setActionCommand("Type A");
        typeB.setActionCommand("Type B");
        doubleDecker.setActionCommand("Double Decker");
        cancel.addActionListener((ActionEvent evt)->{
            plateNoField.setText("");
            errorField.setText("");
            buttonGroup1.clearSelection();
        });
        create.addActionListener((ActionEvent evt)->{
            plateNoField.setText(plateNoField.getText().toUpperCase());
            String validate=validation.validatePlateNo(plateNoField.getText());
            if(!plateNoField.getText().isEmpty()){
                if(validate.isEmpty()){
                   BusType busT=busTC.selectRecordBasedOnType(buttonGroup1.getSelection().getActionCommand());
                    busC.insertRecord(new Bus(busIDField.getText(),busT,plateNoField.getText().toUpperCase(),'A'));
                    plateNoField.setText("");
                    errorField.setText("");
                    busSubPane.removeAll();
                    busSubPane.addTab("",busCreate,BusCreate());
                    busSubPane.addTab("",busDeactivate, ShowBusList());
                    busSubPane.addTab("",search, BusSearchTable());
                    busSubPane.setSelectedIndex(0);
                }else{
                    errorField.setText(validate);
                }
            }else{
                errorField.setText(validate);
            }
        });

        return panel;
}
    public JPanel ShowBusList(){
        JPanel panel=new JPanel();
        JPanel jPanel1 = new javax.swing.JPanel();
        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        JTable busList = new javax.swing.JTable();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Bus List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Fax", 1, 24))); // NOI18N

        busList.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        busList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Bus ID", "Bus Plate No", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        busList.setMaximumSize(new java.awt.Dimension(225, 0));
        busList.setMinimumSize(new java.awt.Dimension(225, 0));
        busList.getTableHeader().setReorderingAllowed(false);
       
        jScrollPane1.setViewportView(busList);
        if (busList.getColumnModel().getColumnCount() > 0) {
            busList.getColumnModel().getColumn(0).setResizable(false);
            busList.getColumnModel().getColumn(0).setPreferredWidth(1);
            busList.getColumnModel().getColumn(1).setResizable(false);
            busList.getColumnModel().getColumn(2).setResizable(false);
            busList.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 584, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 11, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        
        DefaultTableModel model=(DefaultTableModel) busList.getModel();
        ArrayList<Bus> busL=busC.getAllRecord();
        ArrayList<Schedule> ssList=scheduleC.selectRecordByDate(ft.format(new Date()));
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(busList.getModel());
        busList.setRowSorter(sorter);
        int i=1;
        for(Bus b:busL){
            int num=0;
            for(Schedule s : ssList){
                if(b.getBusID().equals(s.getBus().getBusID())){
                    num++;
                }
            }
            if(num==0){
                
                if (b.getStatus() == 'M') {
                    model.addRow(new Object[]{Integer.toString(i), b.getBusID(), b.getBusPlateNo(), "Maintenance"});
                } else if (b.getStatus() == 'A') {
                    model.addRow(new Object[]{Integer.toString(i), b.getBusID(), b.getBusPlateNo(), "Active"});
                }
            
            }
            
            i++;
        }
        busList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JTable source = (JTable) evt.getSource();
                if (evt.getClickCount() == 1) {
                    int row = source.getSelectedRow();
                    String id = source.getModel().getValueAt(source.convertRowIndexToModel(row), 1).toString();
                    busSubPane.remove(1);
                    busSubPane.insertTab("", busDeactivate, BusDeactivate(id), "", 1);
                    busSubPane.setSelectedIndex(1);

                }
            }
        });
        return panel;
    }
    public JPanel BusDeactivate(String id){
        JPanel panel=new JPanel();
        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        JTextPane jTextPane1 = new javax.swing.JTextPane();
        JPanel jPanel1 = new javax.swing.JPanel();
        JLabel jLabel1 = new javax.swing.JLabel();
        JTextField busIDField = new javax.swing.JTextField();
        JLabel jLabel3 = new javax.swing.JLabel();
       JTextField plateNoField = new javax.swing.JTextField();
        JButton maintaince = new javax.swing.JButton();
        JButton cancel = new javax.swing.JButton();
        JLabel jLabel4 = new javax.swing.JLabel();
        JTextField statusField = new javax.swing.JTextField();
        JButton unused = new javax.swing.JButton();
        JLabel jLabel5 = new javax.swing.JLabel();
        JTextField busTypeField = new javax.swing.JTextField();

        jScrollPane1.setViewportView(jTextPane1);

        panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Bus Create", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Fax", 1, 24))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel1.setText("Bus ID : ");

        busIDField.setEditable(false);
        busIDField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel3.setText("Plate No :");

        plateNoField.setEditable(false);
        plateNoField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N

        maintaince.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        maintaince.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/maintenance.png"))); // NOI18N
        maintaince.setText("Maintenance");

        cancel.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.png"))); // NOI18N
        cancel.setText("Cancel");

        jLabel4.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel4.setText("Status :");

        statusField.setEditable(false);
        statusField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N

        unused.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        unused.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/activate.png"))); // NOI18N
        unused.setText("Activate");

        jLabel5.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel5.setText("Type :");

        busTypeField.setEditable(false);
        busTypeField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(101, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(busIDField)
                    .addComponent(plateNoField, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                    .addComponent(statusField)
                    .addComponent(busTypeField))
                .addGap(141, 141, 141))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(unused)
                .addGap(18, 18, 18)
                .addComponent(maintaince)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(busIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(busTypeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(plateNoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(statusField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(unused)
                    .addComponent(maintaince, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Bus bus=busC.selectRecord(id);
        busIDField.setText(id);
        busTypeField.setText(bus.getBusType().getBusType());
        plateNoField.setText(bus.getBusPlateNo());
        
        if (bus.getStatus() == 'M') {
            statusField.setText("Maintenance");
        } else if (bus.getStatus() == 'A') {
            statusField.setText("Active");
        }
        cancel.addActionListener((ActionEvent evt)->{
            busSubPane.remove(1);
            busSubPane.insertTab("",busDeactivate,ShowBusList(),"",1);
            busSubPane.setSelectedIndex(1);
        });
        unused.addActionListener((ActionEvent evt)->{
            int result = JOptionPane.showConfirmDialog(this, "Are your sure want change status of " + bus.getBusPlateNo() + "", "Warning", JOptionPane.YES_NO_OPTION);
            if (result == 0) {
                busC.updateRecord(bus,'U');
                busSubPane.remove(1);
                busSubPane.insertTab("", busDeactivate, ShowBusList(), "", 1);
                busSubPane.setSelectedIndex(1);
            } else {

                busSubPane.remove(1);
                busSubPane.insertTab("", busDeactivate, ShowBusList(), "", 1);
                busSubPane.setSelectedIndex(1);
            }

        });
        maintaince.addActionListener((ActionEvent evt)->{
            int result = JOptionPane.showConfirmDialog(this, "Are your sure want change status of " + bus.getBusPlateNo() + "", "Warning", JOptionPane.YES_NO_OPTION);
            if (result == 0) {
                busC.updateRecord(bus,'M');
                busSubPane.remove(1);
                busSubPane.insertTab("", busDeactivate, ShowBusList(), "", 1);
                busSubPane.setSelectedIndex(1);
            } else {

                busSubPane.remove(1);
                busSubPane.insertTab("", busDeactivate, ShowBusList(), "", 1);
                busSubPane.setSelectedIndex(1);
            }
        });
        
        return panel;
    }
    public JPanel BusSearchTable(){
        JPanel panel=new JPanel();
        DefaultTableModel model;
        JPanel jPanel1 = new javax.swing.JPanel();
        JLabel jLabel1 = new javax.swing.JLabel();
        JComboBox criteriaList = new javax.swing.JComboBox();
        JTextField searchField = new javax.swing.JTextField();
        JButton search = new javax.swing.JButton();
        JButton all = new javax.swing.JButton();
        JButton reset = new javax.swing.JButton();
        JTextField ErrorField = new javax.swing.JTextField();
        JComboBox typeList = new javax.swing.JComboBox();
        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        JTable busTable = new javax.swing.JTable();

        panel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Bus Search", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Fax", 1, 18))); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Criteria", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Fax", 0, 16))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel1.setText("Search By : ");

        criteriaList.setFont(new java.awt.Font("Lucida Fax", 0, 14)); // NOI18N
        criteriaList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "Bus ID", "Plate No(Case Sensitive)", "Type", "Status (a/m)" }));
       
        searchField.setEditable(false);
        searchField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N

        search.setFont(new java.awt.Font("Lucida Fax", 0, 14)); // NOI18N
        search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/view.png"))); // NOI18N
        search.setText("Search");

        all.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        all.setText("All");
 
        reset.setFont(new java.awt.Font("Lucida Fax", 0, 14)); // NOI18N
        reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.png"))); // NOI18N
        reset.setText("Reset");

        ErrorField.setBackground(new java.awt.Color(240, 240, 240));
        ErrorField.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        ErrorField.setForeground(new java.awt.Color(255, 0, 0));
        ErrorField.setBorder(null);

        typeList.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        typeList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "Type A", "Type B", "Double Decker" }));
        typeList.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(typeList, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(criteriaList, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(all))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ErrorField)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(search)
                                .addGap(67, 67, 67)
                                .addComponent(reset)))))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(criteriaList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(all))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(typeList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ErrorField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reset, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        busTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Bus ID", "Plate No", "Type", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(busTable);
        if (busTable.getColumnModel().getColumnCount() > 0) {
            busTable.getColumnModel().getColumn(0).setResizable(false);
            busTable.getColumnModel().getColumn(1).setResizable(false);
            busTable.getColumnModel().getColumn(2).setResizable(false);
            busTable.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(96, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        model = (DefaultTableModel) busTable.getModel();
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(busTable.getModel());
        busTable.setRowSorter(sorter);
        criteriaList.addActionListener((ActionEvent evt) -> {
            if (criteriaList.getSelectedItem().toString().equals("Type")) {
                searchField.setText("");
                searchField.setEditable(false);
                typeList.setEnabled(true);
                
            } else {
                typeList.setEnabled(false);
                searchField.setEditable(true);

            }

        });

        all.addActionListener((ActionEvent evt) -> {
            model.setRowCount(0);
            ArrayList<Bus> busList = busC.getAllRecord();
            for (Bus bus : busList) {
                if (bus.getStatus() == 'A') {
                    model.addRow(new Object[]{bus.getBusID(), bus.getBusPlateNo(), bus.getBusType().getBusType(), "Active"});
                }else if (bus.getStatus() == 'M') {
                    model.addRow(new Object[]{bus.getBusID(), bus.getBusPlateNo(), bus.getBusType().getBusType(), "Maintenance"});
                }
            }
        });
        criteriaList.addActionListener((ActionEvent evt)->{
            if(criteriaList.getSelectedItem().toString().equals("")){
                ErrorField.setText("Please choose a criteria");
            }else{
                ErrorField.setText("");
            }
        });
        typeList.addActionListener((ActionEvent evt)->{
            if(typeList.getSelectedItem().toString().equals("")){
                ErrorField.setText("Please choose a type of bus");
            }else{
                ErrorField.setText("");
            }
        });
        search.addActionListener((ActionEvent evt) -> {
            if (criteriaList.getSelectedItem().toString().equals("")) {
                ErrorField.setText("Please choose a criteria.");
            } else {
                model.setRowCount(0);
                if (criteriaList.getSelectedItem().toString().equals("Bus ID")) {
                    if (searchField.getText().isEmpty()) {
                        ErrorField.setText("Enter Bus ID");
                    } else {
                        Bus bus = busC.selectRecord(searchField.getText());
                        if (bus != null) {
                            ErrorField.setText("");
                            if (bus.getStatus() == 'A') {
                                model.addRow(new Object[]{bus.getBusID(), bus.getBusPlateNo(), bus.getBusType().getBusType(), "Active"});
                            } else if (bus.getStatus() == 'M') {
                                model.addRow(new Object[]{bus.getBusID(), bus.getBusPlateNo(), bus.getBusType().getBusType(), "Maintenance"});
                            }
                        } else {
                            ErrorField.setText("Record Not Found !!!");
                        }
                    }
                } else if (criteriaList.getSelectedItem().toString().equals("Plate No(Case Sensitive)")) {
                    if (searchField.getText().isEmpty()) {
                        ErrorField.setText("Enter Plate No");
                    } else {
                        ArrayList<Bus> busList = busC.getAllRecord();
                        if (busList != null) {
                            ErrorField.setText("");
                            for (Bus bus : busList) {
                                if (bus.getBusPlateNo().equals(searchField.getText().toUpperCase())) {
                                    if (bus.getStatus() == 'A') {
                                        model.addRow(new Object[]{bus.getBusID(), bus.getBusPlateNo(), bus.getBusType().getBusType(), "Active"});
                                    } else if (bus.getStatus() == 'M') {
                                        model.addRow(new Object[]{bus.getBusID(), bus.getBusPlateNo(), bus.getBusType().getBusType(), "Maintenance"});
                                    }
                                }
                            }
                        } else {
                            ErrorField.setText("Record Not Found !!!");
                        }
                    }
                } else if (criteriaList.getSelectedItem().toString().equals("Type")) {
                    if(typeList.getSelectedItem().toString().equals("")){
                        ErrorField.setText(("Please choose a type of bus"));
                    }else{
                        ArrayList<Bus> busList = busC.getAllRecord();
                        for (Bus bus : busList) {
                            if (bus.getBusType().getBusType().equals(typeList.getSelectedItem().toString())) {
                                if (bus.getStatus() == 'A') {
                                    model.addRow(new Object[]{bus.getBusID(), bus.getBusPlateNo(), bus.getBusType().getBusType(), "Active"});
                                } else if (bus.getStatus() == 'M') {
                                    model.addRow(new Object[]{bus.getBusID(), bus.getBusPlateNo(), bus.getBusType().getBusType(), "Maintenance"});
                                }
                            }
                        }
                    }
                } else if (criteriaList.getSelectedItem().toString().equals("Status (a/m)")) {
                    ArrayList<Bus> busList = busC.getAllRecordByStatus(searchField.getText().toUpperCase());
                    if (searchField.getText().isEmpty()) {
                        ErrorField.setText("Enter alphabet only ");
                    } else {
                        if (!busList.isEmpty()) {
                            ErrorField.setText("");
                            for (Bus bus : busList) {

                                if (bus.getStatus() == 'A') {
                                    model.addRow(new Object[]{bus.getBusID(), bus.getBusPlateNo(), bus.getBusType().getBusType(), "Active"});
                                } else if (bus.getStatus() == 'M') {
                                    model.addRow(new Object[]{bus.getBusID(), bus.getBusPlateNo(), bus.getBusType().getBusType(), "Maintenance"});
                                }
                            }
                        } else {
                            ErrorField.setText("Record Not Found !!!");
                        }

                    }
                }

            }
        });
        reset.addActionListener((ActionEvent evt) -> {
            criteriaList.setSelectedIndex(0);
            ErrorField.setText("");
            model.setRowCount(0);
            searchField.setText("");
            searchField.setEditable(false);
            typeList.setSelectedIndex(0);
            ErrorField.setText("");
            typeList.setEnabled(false);
            ErrorField.setText("");
        });
        
        return panel;
    }
    
    //Route CRUD
    public JTabbedPane RoutePane(){
        routeSubPane.addTab("",create , RouteCreate());
        routeSubPane.addTab("", up, RouteUpdatePrice());
        routeSubPane.addTab("",deactivate, ShowRouteList());
        routeSubPane.addTab("", search, RouteSearch());
        routeSubPane.setTabPlacement(JTabbedPane.LEFT);
        routeSubPane.setPreferredSize(new Dimension(740, 600));
        return routeSubPane;
    }
    public JPanel RouteCreate(){
       JPanel panel =new JPanel();
        JPanel jPanel1 = new javax.swing.JPanel();
        JPanel jPanel2 = new javax.swing.JPanel();
        JLabel jLabel1 = new javax.swing.JLabel();
        JTextField routeIDField = new javax.swing.JTextField();
        JLabel jLabel2 = new javax.swing.JLabel();
        JTextField destinationField = new javax.swing.JTextField();
        JLabel jLabel3 = new javax.swing.JLabel();
        JTextField distanceField = new javax.swing.JTextField();
        JLabel jLabel4 = new javax.swing.JLabel();
        JComboBox priceList = new javax.swing.JComboBox();
        JLabel jLabel5 = new javax.swing.JLabel();
        JTextField timeField = new javax.swing.JTextField();
        JTextField errorField = new javax.swing.JTextField();
        JButton cancel = new javax.swing.JButton();
        JButton create1 = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        panel.setMaximumSize(new java.awt.Dimension(432, 360));
        panel.setMinimumSize(new java.awt.Dimension(432, 360));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Route Create", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Fax", 1, 24))); // NOI18N
        jPanel2.setMaximumSize(new java.awt.Dimension(412, 328));
        jPanel2.setMinimumSize(new java.awt.Dimension(412, 328));

        jLabel1.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel1.setText("Route ID :");

        routeIDField.setEditable(false);
        routeIDField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel2.setText("Destination : ");

        jLabel3.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel3.setText("Distance (km) :");

        distanceField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel4.setText("Price :");

        priceList.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel5.setText("Time Required :");

        timeField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        timeField.setToolTipText("In minutes");

        errorField.setEditable(false);
        errorField.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        errorField.setBorder(null);

        cancel.setFont(new java.awt.Font("Lucida Fax", 1, 13)); // NOI18N
        cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.png"))); // NOI18N
        cancel.setText("Cancel");

        create1.setFont(new java.awt.Font("Lucida Fax", 1, 13)); // NOI18N
        create1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/create1.png"))); // NOI18N
        create1.setText("Create");

        destinationField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cancel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(create1)
                            .addComponent(timeField, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(priceList, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(routeIDField, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                            .addComponent(distanceField, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                            .addComponent(destinationField)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(errorField, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(routeIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(destinationField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(distanceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(priceList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(timeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(errorField, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(create1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(21, 21, 21))
        );

        
        routeIDField.setText(function.setRouteID('R'));
        ArrayList<Price> priceL=priceC.getAllRecord();
        String [] str=new String[priceL.size()];
        int a=0;
        for(Price p:priceL){
 
            str[a]=String.format("%.2f",p.getPrice());
            a++;
        }
        priceList.setModel(new DefaultComboBoxModel(str));
        cancel.addActionListener((ActionEvent evt)->{
            errorField.setText("");
            priceList.setSelectedIndex(0);
            destinationField.setText("");
            timeField.setText("");
            distanceField.setText("");
        });
        create1.addActionListener((ActionEvent evt)->{
            int destinationCount=0;
            int distanceCount=0;
            int timeCount=0;
            if(!distanceField.getText().isEmpty() && !timeField.getText().isEmpty()){
               
               if(!distanceField.getText().matches("^[0-9]*$")){
                   distanceCount++;
               }
               if(!timeField.getText().matches("^[0-9]*$")){
                   timeCount++;
               }
              
               if(!destinationField.getText().matches("^[A-Z a-z]*$")){
                   destinationCount++;
               }
              
               if(distanceCount==0 && timeCount==0 &&destinationCount==0){
                   ArrayList<Price>priceLL=priceC.getAllRecord();
                   ArrayList<Route>routeL=routeC.getAllRecord();
                   Price price=null;
                   for(Price p:priceLL){
                       if(String.format("%.2f",p.getPrice()).equals(priceList.getSelectedItem().toString()))
                           price=new Price(p.getPriceID(),p.getPrice());
                   }
                   int cc=0;
                   for(Route r : routeL){
                       if (r.getDestination().trim().equalsIgnoreCase(destinationField.getText().trim())) {
                           cc++;
                       }else{
                           System.out.println(destinationField.getText());
                       }
                       
                   }
                   if (cc == 0) {
                       routeC.insertRecord(new Route(routeIDField.getText(), price, destinationField.getText().toString(), Integer.parseInt(distanceField.getText()), Integer.parseInt(timeField.getText()), 'A'));
                       routeSubPane.removeAll();
                       routeSubPane.addTab("", create, RouteCreate());
                       routeSubPane.addTab("", up, RouteUpdatePrice());
                       routeSubPane.addTab("", deactivate, ShowRouteList());
                       routeSubPane.addTab("", search, RouteSearch());
                       routeSubPane.setSelectedIndex(0);
                   } else {
                       JOptionPane.showMessageDialog(null, "Unsucces to create a new route because duplicate route in database", "Error", JOptionPane.ERROR_MESSAGE);
                       errorField.setText("");
                       priceList.setSelectedIndex(0);
                       destinationField.setText("");
                       timeField.setText("");
                       distanceField.setText("");
                   }
                   
                   
               }else if(distanceCount==1 || timeCount==1||destinationCount==1){
                   errorField.setText("Error may occur in some field.");
               }
              
            }else{
                errorField.setText("Please fill all empty field.");
            }
        });
        return panel;
    }
    public JPanel RouteUpdatePrice(){
        JPanel panel=new JPanel();
        JPanel jPanel1 = new javax.swing.JPanel();
        JLabel jLabel1 = new javax.swing.JLabel();
        JComboBox jComboBox1 = new javax.swing.JComboBox();
        JComboBox destinationList = new javax.swing.JComboBox();
        JComboBox distanceList=new JComboBox();
        JLabel jLabel3 = new javax.swing.JLabel();
        JLabel jLabel4 = new javax.swing.JLabel();
        JTextField errorField = new javax.swing.JTextField();
        JButton update = new javax.swing.JButton();
        JButton cancel = new javax.swing.JButton();
        JLabel jLabel5 = new javax.swing.JLabel();
        JTextField priceIncreaseField = new javax.swing.JTextField();
        JTable routeTable = new javax.swing.JTable();
        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        JLabel jLabel2 = new javax.swing.JLabel();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Route Update", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Fax", 1, 24))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel1.setText("Update By :");

        jComboBox1.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "Distance", "Destination" }));

        destinationList.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        destinationList.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel3.setText("Distance :");

        jLabel4.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel4.setText("Destination : ");

        errorField.setEditable(false);
        errorField.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        errorField.setForeground(new java.awt.Color(255, 0, 0));
        errorField.setBorder(null);

        update.setFont(new java.awt.Font("Lucida Fax", 1, 13)); // NOI18N
        update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/update.png"))); // NOI18N
        update.setText("Update");

        cancel.setFont(new java.awt.Font("Lucida Fax", 1, 13)); // NOI18N
        cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.png"))); // NOI18N
        cancel.setText("Cancel");

        jLabel5.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel5.setText("Price Increase : ");

        distanceList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "50-100", "101-200", "201-300", "301-400" }));

        jLabel2.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel2.setText("%");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cancel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(update))
                            .addComponent(errorField, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(priceIncreaseField, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                                .addComponent(jLabel2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(32, 32, 32)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(distanceList, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(destinationList, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(distanceList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(destinationList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(priceIncreaseField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errorField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancel)
                    .addComponent(update))
                .addGap(22, 22, 22))
        );

        routeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Route ID", "Destination", "Distance", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });

        jScrollPane1.setViewportView(routeTable);
        if (routeTable.getColumnModel().getColumnCount() > 0) {
            routeTable.getColumnModel().getColumn(0).setResizable(false);
            routeTable.getColumnModel().getColumn(1).setResizable(false);
            routeTable.getColumnModel().getColumn(2).setResizable(false);
            routeTable.getColumnModel().getColumn(3).setResizable(false);
            routeTable.getColumnModel().getColumn(4).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(90, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        distanceList.setEnabled(false);
        jComboBox1.addActionListener((ActionEvent evt)->{
            if(jComboBox1.getSelectedItem().toString().equals("Distance")){
                destinationList.setEnabled(false);
                distanceList.setEnabled(true);
                priceIncreaseField.setEditable(true);
                
            }else if(jComboBox1.getSelectedItem().toString().equals("Destination")){
                distanceList.setEnabled(false);
                destinationList.setEnabled(true);
                priceIncreaseField.setEditable(true);
                destinationList.requestFocusInWindow();
            }
        });
        
        
        
        ArrayList<Route> rrLL=routeC.getAllRecord();
        String [] str=new String[rrLL.size()];
        int a=0;
        for(Route r:rrLL){
            str[a]=r.getDestination();
            a++;
        }
        destinationList.setModel(new DefaultComboBoxModel (str));
        DefaultTableModel model = (DefaultTableModel) routeTable.getModel();
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(routeTable.getModel());
        routeTable.setRowSorter(sorter);
        model.setRowCount(0);
        ArrayList<Route> routeL=routeC.getAllRecord();
       
        jComboBox1.addActionListener((ActionEvent evt)->{
            if(jComboBox1.getSelectedItem().equals("")){
                model.setRowCount(0);
                errorField.setText("Please choose a criteria");
            }else{
                errorField.setText("");
            }
        });
        destinationList.addActionListener((ActionEvent evt) -> {
  
            if(destinationList.getSelectedItem().equals("")){
                 model.setRowCount(0);
                errorField.setText("Destination cannot be empty");
            }else{
                errorField.setText("");
                model.setRowCount(0);
                Route route = routeC.selectRecordByDestination(destinationList.getSelectedItem().toString());
                model.setRowCount(0);
                model.addRow(new Object[]{Integer.toString(1), route.getRouteID(), route.getDestination(), route.getDistance(), String.format("%.2f", route.getPrice().getPrice())});
                routeTable.setModel(model);
            }
            
        });
        distanceList.addActionListener((ActionEvent evt) -> {
            int i=1;
            if(distanceList.getSelectedItem().toString().equals("")){
                 model.setRowCount(0);
                errorField.setText("Distance cannot be empty");
            }else{
                errorField.setText("");
                model.setRowCount(0);
                String[] distance = distanceList.getSelectedItem().toString().split("-");
                ArrayList<Route> routeLL = routeC.getRecordByDistance(distance[0], distance[1]);
                for (Route r : routeLL) {
                    model.addRow(new Object[]{Integer.toString(i), r.getRouteID(), r.getDestination(), r.getDistance(), String.format("%.2f", r.getPrice().getPrice())});
                    i++;
                }
                routeTable.setModel(model);
            }
        });
        update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(jComboBox1.getSelectedItem().toString().trim().equals("")){
                    errorField.setText("Please choose a criteria");
                }else{
                    if (jComboBox1.getSelectedItem().toString().equals("Destination") || jComboBox1.getSelectedItem().toString().equals("Distance")) {
                        Route route = routeC.selectRecordByDestination(destinationList.getSelectedItem().toString());
                        if (!priceIncreaseField.getText().isEmpty()) {
                            int pp = 0;
                            for (int a = 0; a < priceIncreaseField.getText().length(); a++) {
                                if (!Character.isDigit(priceIncreaseField.getText().charAt(a))) {
                                    pp++;
                                }
                            }
                            String newID = null;
                            if (pp == 0) {

                                double newPrice = (route.getPrice().getPrice() * ((Double.parseDouble(priceIncreaseField.getText()) + 100) / 100));
                                ArrayList<Price> ppL = priceC.getAllRecord();
                                for (Price p : ppL) {
                                    newID = null;
                                    if (p.getPrice() != newPrice) {
                                        newID = function.setPriceID('P');
                                        priceC.insertRecord(new Price(newID, newPrice));
                                    }
                                }
                            } else {
                                errorField.setText("Please enter digit only");
                            }
                            ArrayList<Route> routeL = new ArrayList<>();
                            Price price = priceC.selectRecord(newID);
                            routeL.add(new Route(route.getRouteID(), price, route.getDestination(), route.getDistance(), route.getTimeRequired(), 'A'));
                            routeC.updateRecord(routeL, jComboBox1.getSelectedItem().toString(), "", "", destinationList.getSelectedItem().toString());
                        } else {
                            errorField.setText("Please the price increase %.");
                        }
                    }
                }
                priceSubPane.remove(0);
                priceSubPane.insertTab("", money, PriceCreate(), "", 0);
            }
        });
        cancel.addActionListener((ActionEvent evt)->{
            errorField.setText("");
            distanceList.setEnabled(false);
            distanceList.setSelectedIndex(0);
            errorField.setText("");
            destinationList.setSelectedIndex(0);
            errorField.setText("");
            destinationList.setEnabled(false);
            jComboBox1.setSelectedIndex(0);
            errorField.setText("");
            priceIncreaseField.setText("");
        });
        
        return panel;
    }
    public JPanel ShowRouteList(){
        JPanel panel=new JPanel();
        JPanel jPanel1 = new javax.swing.JPanel();
        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        JTable routeList = new javax.swing.JTable();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Route List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Fax", 1, 24))); // NOI18N

        routeList.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        routeList.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "No", "Route ID", "Destination","Status"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        routeList.setMaximumSize(new java.awt.Dimension(225, 0));
        routeList.setMinimumSize(new java.awt.Dimension(225, 0));
        routeList.getTableHeader().setReorderingAllowed(false);

        jScrollPane1.setViewportView(routeList);
        if (routeList.getColumnModel().getColumnCount() > 0) {
            routeList.getColumnModel().getColumn(0).setResizable(false);
            routeList.getColumnModel().getColumn(0).setPreferredWidth(1);
            routeList.getColumnModel().getColumn(1).setResizable(false);
            routeList.getColumnModel().getColumn(2).setResizable(false);
            routeList.getColumnModel().getColumn(3).setResizable(false);

        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 584, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 11, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(20, Short.MAX_VALUE))
        );
        DefaultTableModel model = (DefaultTableModel) routeList.getModel();
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(routeList.getModel());
        routeList.setRowSorter(sorter);
        ArrayList<Route> routeLt = routeC.getAllRecord();
        int c=0;
            for (Route route : routeLt) {
                if(route.getStatus()=='A')
                    model.addRow(new Object[]{Integer.toString(c),route.getRouteID(),route.getDestination(),"Activate"});
                else
                    model.addRow(new Object[]{Integer.toString(c),route.getRouteID(),route.getDestination(),"Deactivate"});
                c++;
            }
            routeList.setModel(model);
        routeList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JTable source = (JTable) evt.getSource();
                if (evt.getClickCount() == 1) {
                    int row = source.getSelectedRow();
                    String id = source.getModel().getValueAt(source.convertRowIndexToModel(row), 1).toString();
                    routeSubPane.remove(2);
                    routeSubPane.insertTab("", deactivate, RouteDeactivate(id), "", 2);
                    routeSubPane.setSelectedIndex(2);

                }
            }
        });
        return panel;
    }
    public JPanel RouteDeactivate(String id){
        JPanel panel=new JPanel();
        JPanel jPanel1 = new javax.swing.JPanel();
        JPanel jPanel2 = new javax.swing.JPanel();
        JLabel jLabel1 = new javax.swing.JLabel();
        JTextField routeIDField = new javax.swing.JTextField();
        JLabel jLabel2 = new javax.swing.JLabel();
        JLabel jLabel5 = new javax.swing.JLabel();
        JTextField statusField = new javax.swing.JTextField();
        JTextField errorField = new javax.swing.JTextField();
        JButton cancel = new javax.swing.JButton();
        JButton deactivate1 = new javax.swing.JButton();
        JTextField destinationField = new javax.swing.JTextField();
        JButton activate = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setMaximumSize(new java.awt.Dimension(432, 360));
        setMinimumSize(new java.awt.Dimension(432, 360));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Route Create", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Fax", 1, 24))); // NOI18N
        jPanel2.setMaximumSize(new java.awt.Dimension(412, 328));
        jPanel2.setMinimumSize(new java.awt.Dimension(412, 328));

        jLabel1.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel1.setText("Route ID :");

        routeIDField.setEditable(false);
        routeIDField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel2.setText("Destination : ");

        jLabel5.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel5.setText("Status :");

        statusField.setEditable(false);
        statusField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        statusField.setToolTipText("In minutes");

        errorField.setEditable(false);
        errorField.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        errorField.setBorder(null);

        cancel.setFont(new java.awt.Font("Lucida Fax", 1, 13)); // NOI18N
        cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.png"))); // NOI18N
        cancel.setText("Cancel");

        deactivate1.setFont(new java.awt.Font("Lucida Fax", 1, 13)); // NOI18N
        deactivate1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/deactivate1.png"))); // NOI18N
        deactivate1.setText("Deactiavte");

        destinationField.setEditable(false);
        destinationField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N

        activate.setFont(new java.awt.Font("Lucida Fax", 1, 13)); // NOI18N
        activate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/activate.png"))); // NOI18N
        activate.setText("Activate");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(statusField, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(3, 3, 3)))
                                .addGap(37, 37, 37)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(routeIDField, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                                    .addComponent(destinationField)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(errorField, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(activate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(deactivate1)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(routeIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(destinationField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(statusField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deactivate1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(activate, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(errorField, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(83, Short.MAX_VALUE))
        );
        routeIDField.setText(id);
        Route route=routeC.selectRecord(id);
        destinationField.setText(route.getDestination());
        if(route.getStatus()=='A')
            statusField.setText("Activate");
        else
            statusField.setText("Deactivate");
        cancel.addActionListener((ActionEvent evt) -> {
            routeSubPane.remove(2);
            routeSubPane.insertTab("", deactivate, ShowRouteList(), "", 2);
            routeSubPane.setSelectedIndex(2);
        });
        activate.addActionListener((ActionEvent evt) -> {
            int result = JOptionPane.showConfirmDialog(this, "Are your sure want to activate " + route.getDestination() + "", "Warning", JOptionPane.YES_NO_OPTION);
            if (result == 0) {
                Route r=new Route(id,'A');
                routeC.activateOrDeactivateRoute(r);
                 routeSubPane.remove(2);
            routeSubPane.insertTab("", deactivate, ShowRouteList(), "", 2);
            routeSubPane.setSelectedIndex(2);
            } else {
                 routeSubPane.remove(2);
            routeSubPane.insertTab("", deactivate, ShowRouteList(), "", 2);
            routeSubPane.setSelectedIndex(2);
            }
        });
        deactivate1.addActionListener((ActionEvent evt) -> {
            int result = JOptionPane.showConfirmDialog(this, "Are your sure want to deactivate " + route.getDestination() + "", "Warning", JOptionPane.YES_NO_OPTION);
            if (result == 0) {
                Route r=new Route(id,'D');
                routeC.activateOrDeactivateRoute(r);
                 routeSubPane.remove(2);
            routeSubPane.insertTab("", deactivate, ShowRouteList(), "", 2);
            routeSubPane.setSelectedIndex(2);
            } else {
                routeSubPane.remove(2);
            routeSubPane.insertTab("", deactivate, ShowRouteList(), "", 2);
            routeSubPane.setSelectedIndex(2);
            }
        });
        return panel;
    }
    public JPanel RouteSearch(){
        JPanel panel=new JPanel();
        JPanel jPanel1 = new javax.swing.JPanel();
         JLabel jLabel1 = new javax.swing.JLabel();
        JComboBox criteriaList = new javax.swing.JComboBox();
       JTextField  range1 = new javax.swing.JTextField();
        JButton search1 = new javax.swing.JButton();
        JButton all = new javax.swing.JButton();
        JButton reset = new javax.swing.JButton();
        JTextField ErrorField = new javax.swing.JTextField();
        JTextField range2 = new javax.swing.JTextField();
        JLabel jLabel2 = new javax.swing.JLabel();
        JLabel jLabel3 = new javax.swing.JLabel();
        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        JTable routeTable = new javax.swing.JTable();

        panel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Route Search", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Fax", 1, 18))); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Criteria", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Fax", 0, 16))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel1.setText("Search By : ");

        criteriaList.setFont(new java.awt.Font("Lucida Fax", 0, 14)); // NOI18N
        criteriaList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "Price Range", "Distance Range" }));
       

        range1.setEditable(false);
        range1.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N

        search1.setFont(new java.awt.Font("Lucida Fax", 0, 14)); // NOI18N
        search1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/view.png"))); // NOI18N
        search1.setText("Search");

        all.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        all.setText("All");

        reset.setFont(new java.awt.Font("Lucida Fax", 0, 14)); // NOI18N
        reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.png"))); // NOI18N
        reset.setText("Reset");

        ErrorField.setBackground(new java.awt.Color(240, 240, 240));
        ErrorField.setFont(new java.awt.Font("Lucida Fax", 0, 12)); // NOI18N
        ErrorField.setForeground(new java.awt.Color(255, 0, 0));
        ErrorField.setBorder(null);

        range2.setEditable(false);
        range2.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Lucida Fax", 0, 14)); // NOI18N
        jLabel2.setText("to");

        jLabel3.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel3.setText("Range :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ErrorField, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(99, 99, 99)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(criteriaList, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(all))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(range1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(range2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(111, 111, 111)
                            .addComponent(search1)
                            .addGap(68, 68, 68)
                            .addComponent(reset))))
                .addContainerGap(124, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(criteriaList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(all))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(range1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(range2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(ErrorField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(search1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reset, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        routeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Route ID", "Destination", "Distance", "Time Required", "Price", "status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        routeTable.setMaximumSize(new java.awt.Dimension(150, 0));
        routeTable.setMinimumSize(new java.awt.Dimension(150, 0));
        jScrollPane1.setViewportView(routeTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        DefaultTableModel model = (DefaultTableModel) routeTable.getModel();
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(routeTable.getModel());
        routeTable.setRowSorter(sorter);
         criteriaList.addActionListener((ActionEvent evt) -> {
            if(criteriaList.getSelectedItem().toString().equals("")){
                model.setRowCount(0);
                ErrorField.setText("Please choose a criteria");
            }else{
                model.setRowCount(0);
                ErrorField.setText("");
                range1.setEditable(true);
            range2.setEditable(true);
            range1.requestFocusInWindow();
            }
        });
       
         all.addActionListener((ActionEvent evt) -> {
             criteriaList.setSelectedIndex(0);
            range1.setText("");
            range2.setText("");
            range1.setEditable(false);
            range2.setEditable(false);
            model.setRowCount(0);
            ArrayList<Route> routeList = routeC.getAllRecord();
            for (Route route : routeList) {
                if(route.getStatus()=='A')
                    model.addRow(new Object[]{route.getRouteID(),route.getDestination(),route.getDistance(),route.getTimeRequired(),String.format("%.2f",route.getPrice().getPrice()),"Activate"});
                else
                    model.addRow(new Object[]{route.getRouteID(),route.getDestination(),route.getDistance(),route.getTimeRequired(),String.format("%.2f",route.getPrice().getPrice()),"Deactivate"});
            }
            routeTable.setModel(model);
        });
        search1.addActionListener((ActionEvent evt) -> {
            if (criteriaList.getSelectedItem().toString().equals("")) {
                ErrorField.setText("Pleaes choose a criteria.");
            } else {
                model.setRowCount(0);
                if (criteriaList.getSelectedItem().toString().equals("Price Range")) {
                    if (!range1.getText().isEmpty() && !range2.getText().isEmpty()) {
                        int count1 = 0;
                        int count2 = 0;
                        for (int i = 0; i < range1.getText().length(); i++) {
                            if (!Character.isDigit(range1.getText().charAt(i))) {
                                count1++;
                            }
                        }
                        for (int i = 0; i < range2.getText().length(); i++) {
                            if (!Character.isDigit(range2.getText().charAt(i))) {
                                count2++;
                            }
                        }
                        if (count1 == 0 && count2 == 0) {
                            ArrayList<Route> routeL = routeC.getAllRecord();
                            for (Route route : routeL) {
                                if (route.getPrice().getPrice() >= Double.parseDouble(range1.getText()) && route.getPrice().getPrice() <= Double.parseDouble(range2.getText())) {
                                    if (route.getStatus() == 'A') {
                                        model.addRow(new Object[]{route.getRouteID(), route.getDestination(), route.getDistance(), route.getTimeRequired(), String.format("%.2f", route.getPrice().getPrice()), "Activate"});
                                    } else {
                                        model.addRow(new Object[]{route.getRouteID(), route.getDestination(), route.getDistance(), route.getTimeRequired(), String.format("%.2f", route.getPrice().getPrice()), "Deactivate"});
                                    }
                                } else {
                                    ErrorField.setText("Record not found.");
                                }

                            }
                            routeTable.setModel(model);

                        } else {
                            ErrorField.setForeground(Color.red);
                            ErrorField.setText("Please enter digit only.");

                        }
                    } else {
                        ErrorField.setForeground(Color.red);
                        ErrorField.setText("Please fill up empty field.");
                    }
                } else if (criteriaList.getSelectedItem().toString().equals("Distance Range")) {
                    if (!range1.getText().isEmpty() && !range2.getText().isEmpty()) {
                        int count1 = 0;
                        int count2 = 0;
                        for (int i = 0; i < range1.getText().length(); i++) {
                            if (!Character.isDigit(range1.getText().charAt(i))) {
                                count1++;
                            }
                        }
                        for (int i = 0; i < range2.getText().length(); i++) {
                            if (!Character.isDigit(range2.getText().charAt(i))) {
                                count2++;
                            }
                        }
                        if (count1 == 0 && count2 == 0) {
                            ArrayList<Route> routeL = routeC.getRecordByDistance(range1.getText(), range2.getText());
                            for (Route route : routeL) {
                                if (route != null) {
                                    if (route.getStatus() == 'A') {
                                        model.addRow(new Object[]{route.getRouteID(), route.getDestination(), route.getDistance(), route.getTimeRequired(), String.format("%.2f", route.getPrice().getPrice()), "Activate"});
                                    } else {
                                        model.addRow(new Object[]{route.getRouteID(), route.getDestination(), route.getDistance(), route.getTimeRequired(), String.format("%.2f", route.getPrice().getPrice()), "Deactivate"});
                                    }
                                } else {
                                    ErrorField.setForeground(Color.red);
                                    ErrorField.setText("Record not found.");
                                }
                            }
                            ErrorField.setText("");
                            routeTable.setModel(model);

                        } else {
                            ErrorField.setForeground(Color.red);
                            ErrorField.setText("Please enter digit only.");

                        }
                    } else {
                        ErrorField.setForeground(Color.red);
                        ErrorField.setText("Please fill up empty field.");
                    }
                }

            }
        });
        reset.addActionListener((ActionEvent evt) -> {
            criteriaList.setSelectedIndex(0);
            model.setRowCount(0);
            range1.setText("");
            range2.setText("");
            range1.setEditable(false);
            range2.setEditable(false);
            ErrorField.setText("");
        });
        
        return panel;
    }
    
    //Schedule CRUD
    public JTabbedPane SchedulePane(){
        scheduleSubPane.addTab("",create,ScheduleCreate());
        scheduleSubPane.addTab("",up,ShowScheduleList());
        scheduleSubPane.addTab("",deactivate,ScheduleDeactivate());
        scheduleSubPane.addTab("",search,ScheduleSearchTable());
        scheduleSubPane.setTabPlacement(JTabbedPane.LEFT);
        scheduleSubPane.setPreferredSize(new Dimension(720, 550));
        return scheduleSubPane;
    }
    public JPanel ScheduleCreate(){
       
        JPanel panel=new JPanel();
        JPanel jPanel1 = new javax.swing.JPanel();
        JLabel jLabel1 = new javax.swing.JLabel();
        JTextField scheduleIDField = new javax.swing.JTextField();
        JLabel jLabel2 = new javax.swing.JLabel();
        JComboBox jComboBox1 = new javax.swing.JComboBox();
        JLabel jLabel3 = new javax.swing.JLabel();
        JDateChooser departureDate = new com.toedter.calendar.JDateChooser();
        JLabel jLabel4 = new javax.swing.JLabel();
        JComboBox jComboBox2 = new javax.swing.JComboBox();
        JPanel jPanel2 = new javax.swing.JPanel();
        JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
        JTable driverList1 = new javax.swing.JTable();
        JPanel jPanel3 = new javax.swing.JPanel();
        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        JTable busList1 = new javax.swing.JTable();
        JButton next = new javax.swing.JButton();
        JButton cancel = new javax.swing.JButton();
        JScrollPane jScrollPane3 = new javax.swing.JScrollPane();
        JTextPane jTextPane1 = new javax.swing.JTextPane();
        
        panel.setMaximumSize(new java.awt.Dimension(671, 472));
        panel.setMinimumSize(new java.awt.Dimension(671, 472));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Schedule Info", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Fax", 1, 18))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel1.setText("Schedule ID :");

        scheduleIDField.setEditable(false);
        scheduleIDField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel2.setText("Destination :");

        jComboBox1.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel3.setText("Departure Date : ");

        departureDate.setDateFormatString("dd/MM/yyyy");
        departureDate.setMinSelectableDate(new Date());

        jLabel4.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel4.setText("Departure Time : ");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "08:00:00", "10:00:00", "12:00:00", "14:00:00", "16:00:00", "18:00:00", "20:00:00", "22:00:00" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3))
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scheduleIDField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(departureDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(scheduleIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(departureDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Driver Info", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Fax", 1, 18))); // NOI18N

        driverList1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Driver ID", "Driver Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(driverList1);
        if (driverList1.getColumnModel().getColumnCount() > 0) {
            driverList1.getColumnModel().getColumn(0).setResizable(false);
            driverList1.getColumnModel().getColumn(1).setResizable(false);
            driverList1.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Bus Info", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Fax", 1, 18))); // NOI18N

        busList1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Bus ID", "Plate No", "Capacity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(busList1);
        if (busList1.getColumnModel().getColumnCount() > 0) {
            busList1.getColumnModel().getColumn(0).setResizable(false);
            busList1.getColumnModel().getColumn(1).setResizable(false);
            busList1.getColumnModel().getColumn(2).setResizable(false);
            busList1.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        next.setFont(new java.awt.Font("Lucida Fax", 1, 13)); // NOI18N
        next.setText("Next");

        cancel.setFont(new java.awt.Font("Lucida Fax", 1, 13)); // NOI18N
        cancel.setText("Cancel");

        jTextPane1.setEditable(false);
        jTextPane1.setBackground(new java.awt.Color(240, 240, 240));
        jScrollPane3.setViewportView(jTextPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(362, 362, 362)
                        .addComponent(cancel)
                        .addGap(51, 51, 51)
                        .addComponent(next, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cancel)
                            .addComponent(next))
                        .addGap(30, 30, 30))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jTextPane1.setFont(new java.awt.Font("Lucida Fax", 1, 13));
                 jTextPane1.setForeground(Color.red);
        departureDate.setMinSelectableDate(new Date());
        scheduleIDField.setText(function.setScheduleID("SC"));
        JTextFieldDateEditor editor = (JTextFieldDateEditor) departureDate.getDateEditor();
        editor.setEditable(false);
        ArrayList<Route> routeL=routeC.getAllRecord();
        String [] str=new String[routeL.size()+1];
        int a=1;
        str[0]="";
        for(Route p:routeL){
            str[a]=p.getDestination();
            a++;
        }
        jComboBox1.setModel(new DefaultComboBoxModel(str));
        
        jComboBox2.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent evt){
                if(evt.getStateChange()==ItemEvent.SELECTED){
                    if(evt.getItem().toString().equals("")){
                         
                    }else{
                        
                        if (!jComboBox2.getSelectedItem().equals("") && !departureDate.toString().equals("") && !jComboBox1.getSelectedItem().equals("")) {
                            ArrayList<Driver> driverList = new ArrayList<Driver>();
                            ArrayList<Bus> busLL = new ArrayList<Bus>();
                            ArrayList<Schedule> scheduleList = scheduleC.getAllRecord();

                            Route route = routeC.selectRecordByDestination(jComboBox1.getSelectedItem().toString());
                            String newDate = ft.format(departureDate.getDate()).concat(" ").concat(jComboBox2.getSelectedItem().toString());
                            if (departureDate.getDate().after(new Date()) || ft.format(departureDate.getDate()).equals(ft.format(new Date()))) {
                                ArrayList<Schedule> shedule = scheduleC.selectRecordByRouteAndDate(route, ft.format(departureDate.getDate()));

                                //filter our available driver
                                for (Schedule s : shedule) {
                                    Date d2 = null;
                                    Date d1 = null;
                                    String date3 = s.getDepartureDate().concat(" ").concat(s.getDepartureTime());
                                    try {
                                        d2 = format.parse(date3);
                                        d1 = format.parse(newDate);
                                    } catch (ParseException ex) {
                                        Logger.getLogger(AdminPanel.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                    if (d1.getTime() < d2.getTime() ) {
                                        if (d2.getTime() - d1.getTime() >= ((route.getTimeRequired() *2) * 60000)) {
                                            driverList.add(s.getDriver());
                                        }
                                    } else if (d1.getTime() > d2.getTime() ) {
                                        if (d1.getTime() - d2.getTime() >= ((route.getTimeRequired() *2) * 60000)) {
                                            driverList.add(s.getDriver());
                                        }

                                    }

                                }
                                Driver[] newDriverList = new Driver[driverList.size()];
                                int i = 0;
                                for (Driver d : driverList) {
                                    Date d2 = null;
                                    Date d1 = null;
                                    for (Schedule s : shedule) {
                                        String date3 = s.getDepartureDate().concat(" ").concat(s.getDepartureTime());
                                        try {
                                            d2 = format.parse(date3);
                                            d1 = format.parse(newDate);
                                        } catch (ParseException ex) {
                                            Logger.getLogger(AdminPanel.class.getName()).log(Level.SEVERE, null, ex);
                                        }

                                        if (d1.getTime() <= d2.getTime() ) {
                                            long aa=((route.getTimeRequired() *2) * 60000);
                                            long time1 = d2.getTime() - d1.getTime();
                                            if ((d2.getTime() - d1.getTime() < ((route.getTimeRequired()*2) * 60000) || d2.getTime() - d1.getTime() == 0) && s.getDriver().getDriverID().equals(d.getDriverID())) {
                                                newDriverList[i] = d;
                                            }
                                        } else if (d1.getTime() >= d2.getTime() ) {
                                            long aa=((route.getTimeRequired() *2) * 60000);
                                            long time1 = d1.getTime() - d2.getTime();
                                            if ((d1.getTime() - d2.getTime() < ((route.getTimeRequired() *2) * 60000) || d1.getTime() - d2.getTime() == 0) && s.getDriver().getDriverID().equals(d.getDriverID())) {
                                                newDriverList[i] = d;
                                            }

                                        }
                                    }
                                    i++;
                                }
                                //Filter out avaible bus
                                for (Schedule s : shedule) {
                                    Date d2 = null;
                                    Date d1 = null;
                                    String date3 = s.getDepartureDate().concat(" ").concat(s.getDepartureTime());
                                    try {
                                        d2 = format.parse(date3);
                                        d1 = format.parse(newDate);
                                    } catch (ParseException ex) {
                                        Logger.getLogger(AdminPanel.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    if (d1.getTime() < d2.getTime() ) {
                                        if (d2.getTime() - d1.getTime() >= ((route.getTimeRequired() *2) * 60000)) {
                                            busLL.add(s.getBus());
                                        }
                                    } else if (d1.getTime() >= d2.getTime() ) {
                                        if (d1.getTime() - d2.getTime() > ((route.getTimeRequired() *2) * 60000)) {
                                            busLL.add(s.getBus());
                                        }
                                    }

                                }
                                i = 0;
                                Bus[] newBusList = new Bus[busLL.size()];
                                for (Bus d : busLL) {
                                    Date d2 = null;
                                    Date d1 = null;
                                    for (Schedule s : shedule) {
                                        String date3 = s.getDepartureDate().concat(" ").concat(s.getDepartureTime());
                                        try {
                                            d2 = format.parse(date3);
                                            d1 = format.parse(newDate);
                                        } catch (ParseException ex) {
                                            Logger.getLogger(AdminPanel.class.getName()).log(Level.SEVERE, null, ex);
                                        }

                                        if (d1.getTime() <= d2.getTime() ) {
                                            if ((d2.getTime() - d1.getTime() < ((route.getTimeRequired() *2) * 60000) || d2.getTime() - d1.getTime() == 0) && s.getBus().getBusID().equals(d.getBusID())) {
                                                newBusList[i] = d;
                                            }
                                        } else if (d1.getTime() >= d2.getTime() ) {
                                            if ((d1.getTime() - d2.getTime() < ((route.getTimeRequired() *2) * 60000) || d1.getTime() - d2.getTime() == 0) && s.getBus().getBusID().equals(d.getBusID())) {
                                                newBusList[i] = d;
                                            }

                                        }
                                    }
                                    i++;
                                }

                                ArrayList<Bus> bL = busC.getAllRecord();
                                ArrayList myList = new ArrayList();
                                Collections.addAll(myList, newBusList);
                                busLL.removeAll(myList);
                                ArrayList myList1 = new ArrayList();
                                Collections.addAll(myList1, newDriverList);
                                driverList.removeAll(myList1);

                                //Add in other available bus from database
                                ArrayList<Bus> busLLL = busC.getAllRecord();
                                ArrayList<Schedule> sss = scheduleC.selectRecordByDate( ft.format(departureDate.getDate()));
                                int count = 0;
                                for (Bus ad : busLLL) {
                                    count = 0;
                                    if (ad.getStatus() != 'M') {
                                        for (Schedule as : sss) {
                                            if (ad.getBusID().equals(as.getBus().getBusID())) {
                                                count++;
                                            }
                                        }
                                        if (count == 0) {
                                            busLL.add(ad);

                                        }
                                    }
                                }

                                //Add driver that is not appeal in any schedule 
                                ArrayList<Driver> driverLL = driverC.getAllRecord();
                                ArrayList<Schedule> ss = scheduleC.selectRecordByDate(ft.format(departureDate.getDate()));
                                count = 0;
                                for (Driver ad : driverLL) {
                                    count = 0;
                                    for (Schedule as : ss) {
                                        if (ad.getDriverID().equals(as.getDriver().getDriverID())) {
                                            count++;
                                        }
                                    }
                                    if (count == 0) {
                                        driverList.add(ad);

                                    }

                                }

                                for (int f = 0; f < driverList.size() - 1; f++) {
                                    for (int j = driverList.size() - 1; j > f; j--) {
                                        if (driverList.get(j).getDriverID().equals(driverList.get(f).getDriverID())) {
                                            driverList.remove(j);
                                        }
                                    }
                                }
                                for (int f = 0; f < busLL.size() - 1; f++) {
                                    for (int j = busLL.size() - 1; j > f; j--) {
                                        if (busLL.get(j).getBusID().equals(busLL.get(f).getBusID())) {
                                            busLL.remove(j);
                                        }
                                    }
                                }
                                DefaultTableModel model1 = (DefaultTableModel) busList1.getModel();
                                DefaultTableModel model = (DefaultTableModel) driverList1.getModel();
                                TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(driverList1.getModel());
                                driverList1.setRowSorter(sorter);
                                model.setRowCount(0);

                                TableRowSorter<TableModel> sorter1 = new TableRowSorter<TableModel>(busList1.getModel());
                                busList1.setRowSorter(sorter1);
                                model1.setRowCount(0);
                                int c = 1;
                                for (Driver d : driverList) {
                                    model.addRow(new Object[]{Integer.toString(c), d.getDriverID(), d.getDriverName()});
                                    c++;
                                }
                                driverList1.setModel(model);
                                c = 1;
                                for (Bus b : busLL) {
                                    model1.addRow(new Object[]{Integer.toString(c), b.getBusID(), b.getBusPlateNo(), b.getBusType().getCapacity()});
                                    c++;
                                }
                                busList1.setModel(model1);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Depature Date and Destination cannot be empty", "Warning", JOptionPane.WARNING_MESSAGE);
                        }
                        
                    }

                }
            }
           
        });

        cancel.addActionListener((ActionEvent evt) -> {
            busDD="";
            driverDD="";
            jTextPane1.setText("");
            DefaultTableModel model1 = (DefaultTableModel) busList1.getModel();
            DefaultTableModel model = (DefaultTableModel) driverList1.getModel();
            departureDate.setCalendar(null);
            jComboBox1.setSelectedIndex(0);
            jComboBox2.setSelectedIndex(0);
            model1.setRowCount(0);
            model.setRowCount(0);

        });

        
        busList1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JTable source = (JTable) evt.getSource();
                if (evt.getClickCount() == 1) {
                    int row = source.getSelectedRow();
                      busDD= source.getModel().getValueAt(source.convertRowIndexToModel(row), 1).toString();
                }
            }
        });
        driverList1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JTable source = (JTable) evt.getSource();
                if (evt.getClickCount() == 1) {
                    int row = source.getSelectedRow();
                      driverDD= source.getModel().getValueAt(source.convertRowIndexToModel(row), 1).toString();
                }
            }
        });
        next.addActionListener((ActionEvent evt)->{
            if((!busDD.isEmpty() && !driverDD.isEmpty() )|| (!busDD.equals("") && !driverDD.equals(""))){
                Schedule sch = new Schedule(scheduleIDField.getText(), driverC.selectRecord(driverDD), routeC.selectRecordByDestination(jComboBox1.getSelectedItem().toString()), busC.selectRecord(busDD), jComboBox2.getSelectedItem().toString(), ft.format(departureDate.getDate()), 'A');
                scheduleC.insertRecord(sch);
                DefaultTableModel model1 = (DefaultTableModel) busList1.getModel();
                DefaultTableModel model = (DefaultTableModel) driverList1.getModel();
                departureDate.setCalendar(null);
                jComboBox1.setSelectedIndex(0);
                jComboBox2.setSelectedIndex(0);
                model1.setRowCount(0);
                model.setRowCount(0);
                scheduleIDField.setText(function.setScheduleID("SC"));
                busSubPane.remove(1);
                busSubPane.revalidate();
                busSubPane.repaint();
                busSubPane.insertTab("",busDeactivate, ShowBusList(),"", 1);
                driverSubPane.remove(2);
                driverSubPane.revalidate();
                driverSubPane.repaint();
                driverSubPane.insertTab("",deactivate,ShowDriverStatusList(),"", 2);
                busDD="";
                driverDD="";
                 jTextPane1.setText("");
            }else if(jComboBox1.getSelectedItem().toString().equals("")){
                jTextPane1.setText("Please the destination");
            }else if(ft.format(departureDate.getDate()).equals("")){
                jTextPane1.setText("Please choose a date");
            }else if(jComboBox2.getSelectedItem().toString().equals("")){
                jTextPane1.setText("Please choose a time ");
            }else if(busDD.isEmpty() || busDD.equals("")){
                jTextPane1.setText("Bus must be select");
                jTextPane1.setFont(new java.awt.Font("Lucida Fax", 1, 13));
                 jTextPane1.setForeground(Color.red);
            }else if(driverDD.isEmpty() || driverDD.equals("")){
                jTextPane1.setText("Driver must be select");
                jTextPane1.setFont(new java.awt.Font("Lucida Fax", 1, 13));
                 jTextPane1.setForeground(Color.red);
            }else if((driverDD.isEmpty() && busDD.isEmpty())  || (busDD.equals("") && driverDD.equals(""))){
                 jTextPane1.setText("Bus and Driver must be select");
                 jTextPane1.setFont(new java.awt.Font("Lucida Fax", 1, 13));
                 jTextPane1.setForeground(Color.red);
            }
            
        });

        return panel;
    }
    public JPanel ShowScheduleList(){
        JPanel panel=new JPanel();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        JPanel jPanel1 = new javax.swing.JPanel();
        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        JTable scheduleList = new javax.swing.JTable();
        JDateChooser jDateChooser1 = new com.toedter.calendar.JDateChooser();
        JLabel jLabel1 = new javax.swing.JLabel();
        JButton search = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Schedule List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Fax", 1, 24))); // NOI18N

        scheduleList.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        scheduleList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Schedule ID", "Driver Name", "Bus Plate No", "Departure Date", "Departure Time", "Destination"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
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
        scheduleList.setMaximumSize(new java.awt.Dimension(225, 0));
        scheduleList.setMinimumSize(new java.awt.Dimension(225, 0));
        scheduleList.getTableHeader().setReorderingAllowed(false);

        jScrollPane1.setViewportView(scheduleList);
        if (scheduleList.getColumnModel().getColumnCount() > 0) {
            scheduleList.getColumnModel().getColumn(0).setResizable(false);
            scheduleList.getColumnModel().getColumn(0).setPreferredWidth(1);
            scheduleList.getColumnModel().getColumn(1).setResizable(false);
            scheduleList.getColumnModel().getColumn(2).setResizable(false);
            scheduleList.getColumnModel().getColumn(3).setResizable(false);
            scheduleList.getColumnModel().getColumn(4).setResizable(false);
            scheduleList.getColumnModel().getColumn(5).setResizable(false);
            scheduleList.getColumnModel().getColumn(6).setResizable(false);
        }

        jLabel1.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel1.setText("Departure Date");

        search.setFont(new java.awt.Font("Lucida Fax", 1, 13)); // NOI18N
        search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/view.png"))); // NOI18N
        search.setText("Search");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(35, 35, 35)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(234, 234, 234))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(241, 241, 241)
                        .addComponent(search))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );
        jDateChooser1.setMinSelectableDate(new Date());
        JTextFieldDateEditor editor = (JTextFieldDateEditor) jDateChooser1.getDateEditor();
        editor.setEditable(false);
        search.addActionListener((ActionEvent evt)->{
            if(jDateChooser1.getDate() != null){
                String dd=ft.format(jDateChooser1.getDate());
                DefaultTableModel model = (DefaultTableModel) scheduleList.getModel();
                ArrayList<Schedule> ssLL = scheduleC.selectRecordByDate(ft.format(jDateChooser1.getDate()));
                if(!ssLL.isEmpty()){
                    int i = 1;
                    for (Schedule ss : ssLL) {
                        model.addRow(new Object[]{Integer.toString(i), ss.getScheduleID(), ss.getDriver().getDriverName(), ss.getBus().getBusPlateNo(), ss.getDepartureDate(), ss.getDepartureTime(),ss.getRoute().getDestination()});
                        i++;
                    }
                    scheduleList.setModel(model);
                }else{
                     JOptionPane.showMessageDialog(null,"No Record Found.","Warning",JOptionPane.ERROR_MESSAGE);
                }
                
            }else{
                JOptionPane.showMessageDialog(null,"Departure date cannot be empty","Warning",JOptionPane.WARNING_MESSAGE);
            }
           
        });
         scheduleList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JTable source = (JTable) evt.getSource();
                if (evt.getClickCount() == 1) {
                    int row = source.getSelectedRow();
                    scheduleSubPane.remove(1);
                    scheduleSubPane.revalidate();
                    scheduleSubPane.repaint();
                    scheduleSubPane.insertTab("", up, ScheduleUpdate(source.getModel().getValueAt(source.convertRowIndexToModel(row), 1).toString()),"",1);
                    scheduleSubPane.setSelectedIndex(1);
                      
                }
            }
        });
        
        
        return panel;
    }
    public JPanel ScheduleUpdate(String id){
        JPanel panel=new JPanel();
        JPanel jPanel1 = new javax.swing.JPanel();
        JLabel jLabel1 = new javax.swing.JLabel();
        JTextField scheduleIDField = new javax.swing.JTextField();
        JLabel jLabel2 = new javax.swing.JLabel();
        JLabel jLabel3 = new javax.swing.JLabel();
        JLabel jLabel4 = new javax.swing.JLabel();
        JTextField departureTime = new javax.swing.JTextField();
        JTextField destinationField = new javax.swing.JTextField();
        JTextField departureDate = new javax.swing.JTextField();
        JPanel jPanel2 = new javax.swing.JPanel();
        JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
        JTable driverList1 = new javax.swing.JTable();
        JPanel jPanel3 = new javax.swing.JPanel();
        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        JTable busList1 = new javax.swing.JTable();
        JButton next = new javax.swing.JButton();
        JButton cancel = new javax.swing.JButton();
        JScrollPane jScrollPane3 = new javax.swing.JScrollPane();
        JTextPane jTextPane1 = new javax.swing.JTextPane();

        panel.setMaximumSize(new java.awt.Dimension(665, 486));
        panel.setMinimumSize(new java.awt.Dimension(665, 486));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Schedule Info", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Fax", 1, 18))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel1.setText("Schedule ID :");

        scheduleIDField.setEditable(false);
        scheduleIDField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel2.setText("Destination :");

        jLabel3.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel3.setText("Departure Date : ");

        jLabel4.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel4.setText("Departure Time : ");

        departureTime.setEditable(false);
        departureTime.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N

        destinationField.setEditable(false);
        destinationField.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N

        departureDate.setEditable(false);
        departureDate.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3))
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scheduleIDField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(departureTime, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(destinationField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(departureDate))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(scheduleIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(destinationField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(departureDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(departureTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Driver Info", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Fax", 1, 18))); // NOI18N

        driverList1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Driver ID", "Driver Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(driverList1);
        if (driverList1.getColumnModel().getColumnCount() > 0) {
            driverList1.getColumnModel().getColumn(0).setResizable(false);
            driverList1.getColumnModel().getColumn(1).setResizable(false);
            driverList1.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Bus Info", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Fax", 1, 18))); // NOI18N

        busList1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Bus ID", "Plate No", "Capacity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(busList1);
        if (busList1.getColumnModel().getColumnCount() > 0) {
            busList1.getColumnModel().getColumn(0).setResizable(false);
            busList1.getColumnModel().getColumn(1).setResizable(false);
            busList1.getColumnModel().getColumn(2).setResizable(false);
            busList1.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
        );

        next.setFont(new java.awt.Font("Lucida Fax", 1, 13)); // NOI18N
        next.setText("Update");

        cancel.setFont(new java.awt.Font("Lucida Fax", 1, 13)); // NOI18N
        cancel.setText("Cancel");

        jTextPane1.setEditable(false);
        jTextPane1.setBackground(new java.awt.Color(240, 240, 240));
        jScrollPane3.setViewportView(jTextPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(cancel)
                        .addGap(55, 55, 55)
                        .addComponent(next))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cancel)
                            .addComponent(next))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(22, Short.MAX_VALUE))))
        );
        Schedule schedulell=scheduleC.selectRecord(id);
        
        scheduleIDField.setText(schedulell.getScheduleID());
        destinationField.setText(schedulell.getRoute().getDestination());
        departureDate.setText(schedulell.getDepartureDate());
        departureTime.setText(schedulell.getDepartureTime());
        
        //get available bus and driver 
        ArrayList<Driver> driverList = new ArrayList<Driver>();
                            ArrayList<Bus> busLL = new ArrayList<Bus>();
                            ArrayList<Schedule> scheduleList = scheduleC.getAllRecord();

                            Route route = routeC.selectRecordByDestination(destinationField.getText());
                            String newDate = departureDate.getText().concat(" ").concat(departureTime.getText());
                            
                                ArrayList<Schedule> shedule = scheduleC.selectRecordByRouteAndDate(route, departureDate.getText());

                                //filter our available driver
                                for (Schedule s : shedule) {
                                    Date d2 = null;
                                    Date d1 = null;
                                    String date3 = s.getDepartureDate().concat(" ").concat(s.getDepartureTime());
                                    try {
                                        d2 = format.parse(date3);
                                        d1 = format.parse(newDate);
                                    } catch (ParseException ex) {
                                        Logger.getLogger(AdminPanel.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                    if (d1.getTime() < d2.getTime() && s.getStatus()=='A') {
                                        if (d2.getTime() - d1.getTime() >= ((route.getTimeRequired() + 60) * 60000)) {
                                            driverList.add(s.getDriver());
                                        }
                                    } else if (d1.getTime() > d2.getTime() && s.getStatus()=='A') {
                                        if (d1.getTime() - d2.getTime() >= ((route.getTimeRequired() + 60) * 60000)) {
                                            driverList.add(s.getDriver());
                                        }

                                    }

                                }
                                Driver[] newDriverList = new Driver[driverList.size()];
                                int i = 0;
                                for (Driver d : driverList) {
                                    Date d2 = null;
                                    Date d1 = null;
                                    for (Schedule s : shedule) {
                                        String date3 = s.getDepartureDate().concat(" ").concat(s.getDepartureTime());
                                        try {
                                            d2 = format.parse(date3);
                                            d1 = format.parse(newDate);
                                        } catch (ParseException ex) {
                                            Logger.getLogger(AdminPanel.class.getName()).log(Level.SEVERE, null, ex);
                                        }

                                        if (d1.getTime() <= d2.getTime() && s.getStatus()=='A') {
                                            long time1 = d2.getTime() - d1.getTime();
                                            if ((d2.getTime() - d1.getTime() < ((route.getTimeRequired() + 60) * 60000) || d2.getTime() - d1.getTime() == 0) && s.getDriver().getDriverID().equals(d.getDriverID())) {
                                                newDriverList[i] = d;
                                            }
                                        } else if (d1.getTime() >= d2.getTime() && s.getStatus()=='A') {
                                            long time1 = d1.getTime() - d2.getTime();
                                            if ((d1.getTime() - d2.getTime() < ((route.getTimeRequired() + 60) * 60000) || d1.getTime() - d2.getTime() == 0) && s.getDriver().getDriverID().equals(d.getDriverID())) {
                                                newDriverList[i] = d;
                                            }

                                        }
                                    }
                                    i++;
                                }
                                //Filter out avaible bus
                                for (Schedule s : shedule) {
                                    Date d2 = null;
                                    Date d1 = null;
                                    String date3 = s.getDepartureDate().concat(" ").concat(s.getDepartureTime());
                                    try {
                                        d2 = format.parse(date3);
                                        d1 = format.parse(newDate);
                                    } catch (ParseException ex) {
                                        Logger.getLogger(AdminPanel.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    if (d1.getTime() < d2.getTime() && s.getStatus()=='A') {
                                        if (d2.getTime() - d1.getTime() >= ((route.getTimeRequired() + 60) * 60000)) {
                                            busLL.add(s.getBus());
                                        }
                                    } else if (d1.getTime() > d2.getTime() && s.getStatus()=='A') {
                                        if (d1.getTime() - d2.getTime() >= ((route.getTimeRequired() + 60) * 60000)) {
                                            busLL.add(s.getBus());
                                        }
                                    }

                                }
                                i = 0;
                                Bus[] newBusList = new Bus[busLL.size()];
                                for (Bus d : busLL) {
                                    Date d2 = null;
                                    Date d1 = null;
                                    for (Schedule s : shedule) {
                                        String date3 = s.getDepartureDate().concat(" ").concat(s.getDepartureTime());
                                        try {
                                            d2 = format.parse(date3);
                                            d1 = format.parse(newDate);
                                        } catch (ParseException ex) {
                                            Logger.getLogger(AdminPanel.class.getName()).log(Level.SEVERE, null, ex);
                                        }

                                        if (d1.getTime() <= d2.getTime() && s.getStatus()=='A') {
                                            if ((d2.getTime() - d1.getTime() < ((route.getTimeRequired() + 60) * 60000) || d2.getTime() - d1.getTime() == 0) && s.getBus().getBusID().equals(d.getBusID())) {
                                                newBusList[i] = d;
                                            }
                                        } else if (d1.getTime() >= d2.getTime() && s.getStatus()=='A') {
                                            if ((d1.getTime() - d2.getTime() < ((route.getTimeRequired() + 60) * 60000) || d1.getTime() - d2.getTime() == 0) && s.getBus().getBusID().equals(d.getBusID())) {
                                                newBusList[i] = d;
                                            }

                                        }
                                    }
                                    i++;
                                }

                                ArrayList<Bus> bL = busC.getAllRecord();
                                ArrayList myList = new ArrayList();
                                Collections.addAll(myList, newBusList);
                                busLL.removeAll(myList);
                                ArrayList myList1 = new ArrayList();
                                Collections.addAll(myList1, newDriverList);
                                driverList.removeAll(myList1);

                                //Add in other available bus from database
                                ArrayList<Bus> busLLL = busC.getAllRecord();
                                ArrayList<Schedule> sss = scheduleC.selectRecordByRouteAndDate(route, departureDate.getText());
                                int count = 0;
                                for (Bus ad : busLLL) {
                                    count = 0;
                                    for (Schedule as : sss) {
                                        if (ad.getBusID().equals(as.getBus().getBusID())) {
                                            count++;
                                        }
                                    }
                                    if (count == 0) {
                                        busLL.add(ad);

                                    }

                                }

                                //Add driver that is not appeal in any schedule 
                                ArrayList<Driver> driverLL = driverC.getAllRecord();
                                ArrayList<Schedule> ss = scheduleC.selectRecordByRouteAndDate(route, departureDate.getText());
                                count = 0;
                                for (Driver ad : driverLL) {
                                    count = 0;
                                    for (Schedule as : ss) {
                                        if (ad.getDriverID().equals(as.getDriver().getDriverID())) {
                                            count++;
                                        }
                                    }
                                    if (count == 0) {
                                        driverList.add(ad);

                                    }

                                }

                                for (int f = 0; f < driverList.size() - 1; f++) {
                                    for (int j = driverList.size() - 1; j > f; j--) {
                                        if (driverList.get(j).getDriverID().equals(driverList.get(f).getDriverID())) {
                                            driverList.remove(j);
                                        }
                                    }
                                }
                                for (int f = 0; f < busLL.size() - 1; f++) {
                                    for (int j = busLL.size() - 1; j > f; j--) {
                                        if (busLL.get(j).getBusID().equals(busLL.get(f).getBusID())) {
                                            busLL.remove(j);
                                        }
                                    }
                                }
                                DefaultTableModel model1 = (DefaultTableModel) busList1.getModel();
                                DefaultTableModel model = (DefaultTableModel) driverList1.getModel();
                                TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(driverList1.getModel());
                                driverList1.setRowSorter(sorter);
                                model.setRowCount(0);

                                TableRowSorter<TableModel> sorter1 = new TableRowSorter<TableModel>(busList1.getModel());
                                busList1.setRowSorter(sorter1);
                                model1.setRowCount(0);
                                int c = 1;
                                for (Driver d : driverList) {
                                    model.addRow(new Object[]{Integer.toString(c), d.getDriverID(), d.getDriverName()});
                                    c++;
                                }
                                driverList1.setModel(model);
                                c = 1;
                                for (Bus b : busLL) {
                                    model1.addRow(new Object[]{Integer.toString(c), b.getBusID(), b.getBusPlateNo(), b.getBusType().getCapacity()});
                                    c++;
                                }
                                busList1.setModel(model1);
        next.addActionListener((ActionEvent evt)->{
             if((!busDD.isEmpty() && !driverDD.isEmpty() )|| (!busDD.equals("") && !driverDD.equals(""))){
                 Schedule ssLLL = new Schedule(scheduleIDField.getText(), driverC.selectRecord(driverDD), routeC.selectRecordByDestination(destinationField.getText()), busC.selectRecord(busDD), departureTime.getText(), departureDate.getText(), 'A');
                 scheduleC.updateRecord(ssLLL, "");
                 scheduleSubPane.remove(1);
                 scheduleSubPane.revalidate();
                 scheduleSubPane.repaint();
                 scheduleSubPane.insertTab("", up, ShowScheduleList(),"",1);
                 scheduleSubPane.setSelectedIndex(1);
                 busDD = "";
                 driverDD = "";
                 jTextPane1.setText("");
             }else if(busDD.isEmpty() || busDD.equals("")){
                jTextPane1.setText("Bus must be select");
                jTextPane1.setFont(new java.awt.Font("Lucida Fax", 1, 13));
                 jTextPane1.setForeground(Color.red);
            }else if(driverDD.isEmpty() || driverDD.equals("")){
                jTextPane1.setText("Driver must be select");
                jTextPane1.setFont(new java.awt.Font("Lucida Fax", 1, 13));
                 jTextPane1.setForeground(Color.red);
            }else if((driverDD.isEmpty() && busDD.isEmpty())  || (busDD.equals("") && driverDD.equals(""))){
                 jTextPane1.setText("Bus and Driver must be select");
                 jTextPane1.setFont(new java.awt.Font("Lucida Fax", 1, 13));
                 jTextPane1.setForeground(Color.red);
            }
        });
        busList1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JTable source = (JTable) evt.getSource();
                if (evt.getClickCount() == 1) {
                    int row = source.getSelectedRow();
                      busDD= source.getModel().getValueAt(source.convertRowIndexToModel(row), 1).toString();
                }
            }
        });
        driverList1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JTable source = (JTable) evt.getSource();
                if (evt.getClickCount() == 1) {
                    int row = source.getSelectedRow();
                      driverDD= source.getModel().getValueAt(source.convertRowIndexToModel(row), 1).toString();
                }
            }
        });
        cancel.addActionListener((ActionEvent evt) -> {
            busDD = "";
            driverDD = "";
            scheduleSubPane.remove(1);
            scheduleSubPane.revalidate();
            scheduleSubPane.repaint();
            scheduleSubPane.insertTab("", up, ShowScheduleList(), "", 1);
            scheduleSubPane.setSelectedIndex(1);
        });
        return panel;
    }
    public JPanel ScheduleDeactivate(){
        JPanel panel=new JPanel();
        JPanel jPanel1 = new javax.swing.JPanel();
        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        JTable scheduleList = new javax.swing.JTable();
        JDateChooser jDateChooser1 = new com.toedter.calendar.JDateChooser();
        JLabel jLabel1 = new javax.swing.JLabel();
        JButton search = new javax.swing.JButton();
        JButton deactivate1 = new javax.swing.JButton();
        JButton cancel = new javax.swing.JButton();
        JButton activate = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Schedule List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Fax", 1, 24))); // NOI18N

        scheduleList.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        scheduleList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Schedule ID", "Driver Name", "Bus Plate No", "Departure Date", "Departure Time", "Destination", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scheduleList.setMaximumSize(new java.awt.Dimension(225, 0));
        scheduleList.setMinimumSize(new java.awt.Dimension(225, 0));
        scheduleList.getTableHeader().setReorderingAllowed(false);

        jScrollPane1.setViewportView(scheduleList);
        if (scheduleList.getColumnModel().getColumnCount() > 0) {
            scheduleList.getColumnModel().getColumn(0).setResizable(false);
            scheduleList.getColumnModel().getColumn(0).setPreferredWidth(1);
            scheduleList.getColumnModel().getColumn(1).setResizable(false);
            scheduleList.getColumnModel().getColumn(2).setResizable(false);
            scheduleList.getColumnModel().getColumn(3).setResizable(false);
            scheduleList.getColumnModel().getColumn(4).setResizable(false);
            scheduleList.getColumnModel().getColumn(5).setResizable(false);
            scheduleList.getColumnModel().getColumn(6).setResizable(false);
            scheduleList.getColumnModel().getColumn(7).setResizable(false);
        }

        

        jLabel1.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel1.setText("Departure Date");

        search.setFont(new java.awt.Font("Lucida Fax", 1, 13)); // NOI18N
        search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/view.png"))); // NOI18N
        search.setText("Search");

        deactivate1.setFont(new java.awt.Font("Lucida Fax", 1, 13)); // NOI18N
        deactivate1.setText("Deactivate");

        cancel.setFont(new java.awt.Font("Lucida Fax", 1, 13)); // NOI18N
        cancel.setText("Cancel");

        activate.setFont(new java.awt.Font("Lucida Fax", 1, 13)); // NOI18N
        activate.setText("Activate");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 643, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(241, 241, 241)
                        .addComponent(search)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(35, 35, 35)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(234, 234, 234))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(activate, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deactivate1)
                .addGap(119, 119, 119))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deactivate1)
                    .addComponent(cancel)
                    .addComponent(activate))
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(55, Short.MAX_VALUE))
        );
        jDateChooser1.setMinSelectableDate(new Date());
        JTextFieldDateEditor editor = (JTextFieldDateEditor) jDateChooser1.getDateEditor();
        editor.setEditable(false);
        search.addActionListener((ActionEvent evt) -> {
            if (jDateChooser1.getDate() != null) {
                String dd = ft.format(jDateChooser1.getDate());
                DefaultTableModel model = (DefaultTableModel) scheduleList.getModel();
                ArrayList<Schedule> ssLL = scheduleC.selectRecordByDate(ft.format(jDateChooser1.getDate()));

                if (!ssLL.isEmpty()) {
                    int i = 1;
                    model.setRowCount(0);
                    for (Schedule ss : ssLL) {
                        ArrayList<Ticket> ttList = ticketC.selectRecordBySchedule(ss);
                        if (ttList.isEmpty()) {
                            if (ss.getStatus() == 'A') {
                                model.addRow(new Object[]{Integer.toString(i), ss.getScheduleID(), ss.getDriver().getDriverName(), ss.getBus().getBusPlateNo(), ss.getDepartureDate(), ss.getDepartureTime(), ss.getRoute().getDestination(), "Activate"});
                            } else if (ss.getStatus() == 'D') {
                                model.addRow(new Object[]{Integer.toString(i), ss.getScheduleID(), ss.getDriver().getDriverName(), ss.getBus().getBusPlateNo(), ss.getDepartureDate(), ss.getDepartureTime(), ss.getRoute().getDestination(), "Deactivate"});
                            }
                            i++;
                        }
                        scheduleList.setModel(model);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "No Record Found.", "Warning", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Departure date cannot be empty", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });
        scheduleList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JTable source = (JTable) evt.getSource();
                if (evt.getClickCount() == 1) {
                    int row = source.getSelectedRow();
                    sssID=(source.getModel().getValueAt(source.convertRowIndexToModel(row), 1).toString());
                }
            }
        });
        deactivate1.addActionListener((ActionEvent evt)->{
            Schedule ss=scheduleC.selectRecord(sssID);
            scheduleC.updateRecord(ss, "D");
                    scheduleSubPane.remove(2);
                    scheduleSubPane.revalidate();
                    scheduleSubPane.repaint();
                    scheduleSubPane.insertTab("",deactivate,ScheduleDeactivate(),"",2);
                    scheduleSubPane.setSelectedIndex(2);
        });
        activate.addActionListener((ActionEvent evt)->{
            Schedule ss=scheduleC.selectRecord(sssID);
            scheduleC.updateRecord(ss, "A");
                    scheduleSubPane.remove(2);
                    scheduleSubPane.revalidate();
                    scheduleSubPane.repaint();
                    scheduleSubPane.insertTab("",deactivate,ScheduleDeactivate(),"",2);
                    scheduleSubPane.setSelectedIndex(2);
        });
        cancel.addActionListener((ActionEvent evt)->{
            DefaultTableModel model = (DefaultTableModel) scheduleList.getModel();
            jDateChooser1.setCalendar(null);
            model.setRowCount(0);
        });
        return panel;
    }
    public JPanel ScheduleSearchTable(){
        JPanel panel=new JPanel();
        JLabel jLabel2 = new javax.swing.JLabel();
        JPanel jPanel1 = new javax.swing.JPanel();
        JLabel jLabel1 = new javax.swing.JLabel();
        JComboBox criteriaList = new javax.swing.JComboBox();
        JButton search1 = new javax.swing.JButton();
        JButton all = new javax.swing.JButton();
        JButton reset = new javax.swing.JButton();
        JLabel jLabel3 = new javax.swing.JLabel();
        JComboBox destinationList = new javax.swing.JComboBox();
        JLabel jLabel4 = new javax.swing.JLabel();
         JComboBox jComboBox1 = new javax.swing.JComboBox();
        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        JTable scheduleTable = new javax.swing.JTable();

        jLabel2.setText("jLabel2");

        setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Staff Search", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Fax", 1, 18))); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Criteria", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Fax", 0, 16))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel1.setText("Search By : ");

        criteriaList.setFont(new java.awt.Font("Lucida Fax", 0, 14)); // NOI18N
        criteriaList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "Destination", "Month" }));


        all.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        all.setText("All");


        reset.setFont(new java.awt.Font("Lucida Fax", 0, 14)); // NOI18N
        reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.png"))); // NOI18N
        reset.setText("Reset");

        jLabel3.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel3.setText("Destination: ");

        destinationList.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel4.setText("Month : ");

        jComboBox1.setFont(new java.awt.Font("Lucida Fax", 0, 13)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "January", "Febuary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));

        search1.setFont(new java.awt.Font("Lucida Fax", 0, 14)); // NOI18N
        search1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/view.png"))); // NOI18N
        search1.setText("Search");
   
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(criteriaList, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(destinationList, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(all))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addComponent(reset)
                        .addGap(18, 18, 18)
                        .addComponent(search1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(criteriaList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(all))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(destinationList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(reset, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        scheduleTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Schedule ID", "Driver Name", "Bus Plate No", "Destination", "Departure Time", "Departure Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(scheduleTable);
        if (scheduleTable.getColumnModel().getColumnCount() > 0) {
            scheduleTable.getColumnModel().getColumn(0).setResizable(false);
            scheduleTable.getColumnModel().getColumn(1).setResizable(false);
            scheduleTable.getColumnModel().getColumn(2).setResizable(false);
            scheduleTable.getColumnModel().getColumn(3).setResizable(false);
            scheduleTable.getColumnModel().getColumn(4).setResizable(false);
            scheduleTable.getColumnModel().getColumn(5).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(215, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 26, Short.MAX_VALUE))
        );
        destinationList.setEnabled(false);
        jComboBox1.setEnabled(false);
        ArrayList<Route> routeL=routeC.getAllRecord();
        String [] str=new String[routeL.size()+1];
        int a=1;
        str[0]="";
        for(Route p:routeL){
            str[a]=p.getDestination();
            a++;
        }
        destinationList.setModel(new DefaultComboBoxModel(str));
        
         criteriaList.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent ie) {
                if(criteriaList.getSelectedItem().toString().trim().equals("")){
                   DefaultTableModel model1 = (DefaultTableModel) scheduleTable.getModel();
                    model1.setRowCount(0);
                }else if(criteriaList.getSelectedItem().toString().trim().equals("Destination")){
                    DefaultTableModel model1 = (DefaultTableModel) scheduleTable.getModel();
                    model1.setRowCount(0);
                    jComboBox1.setEnabled(false);
                    destinationList.setEnabled(true);
 
                }else if(criteriaList.getSelectedItem().toString().trim().equals("Month")){
                    DefaultTableModel model1 = (DefaultTableModel) scheduleTable.getModel();
                    model1.setRowCount(0);
                    destinationList.setEnabled(false);
                    jComboBox1.setEnabled(true);
  
                }
            }
         });
       
         search1.addActionListener((ActionEvent evt)->{
             if(criteriaList.getSelectedItem().toString().equals("")){
                 JOptionPane.showMessageDialog(null,"Please choose a criteria","Warning",JOptionPane.WARNING_MESSAGE);
             }else{
                 if(destinationList.getSelectedItem().toString().equals("")  ){
                     if (criteriaList.getSelectedItem().toString().equals("Destination")) {
                         JOptionPane.showMessageDialog(null, "Destination cannot be empty", "Warning", JOptionPane.WARNING_MESSAGE);
                     }
                 }else{
                     Route r = routeC.selectRecordByDestination(destinationList.getSelectedItem().toString());
                     ArrayList<Schedule> ss = scheduleC.selectRecordByRoute(r);
                     DefaultTableModel model1 = (DefaultTableModel) scheduleTable.getModel();

                     TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(scheduleTable.getModel());
                     scheduleTable.setRowSorter(sorter);
                     model1.setRowCount(0);

                     int c = 1;
                     for (Schedule d : ss) {
                         model1.addRow(new Object[]{Integer.toString(c), d.getScheduleID(), d.getDriver().getDriverName(), d.getBus().getBusPlateNo(), d.getRoute().getDestination(), d.getDepartureDate(), d.getDepartureTime()});
                         c++;
                     }
                     scheduleTable.setModel(model1);
                 }
                 
                 if(jComboBox1.getSelectedItem().toString().equals("")  ){
                     if (criteriaList.getSelectedItem().toString().equals("Month")) {
                         JOptionPane.showMessageDialog(null, "Month cannot be empty", "Warning", JOptionPane.WARNING_MESSAGE);
                     }
                 }else{
                     String month=null;
                     if (jComboBox1.getSelectedItem().toString().equals("Janaury")) {
                         month = "01";
                     } else if (jComboBox1.getSelectedItem().toString().equals("Febuary")) {
                         month = "02";
                     } else if (jComboBox1.getSelectedItem().toString().equals("March")) {
                         month = "03";
                     } else if (jComboBox1.getSelectedItem().toString().equals("April")) {
                         month = "04";
                     } else if (jComboBox1.getSelectedItem().toString().equals("May")) {
                         month = "05";
                     } else if (jComboBox1.getSelectedItem().toString().equals("June")) {
                         month = "06";
                     } else if (jComboBox1.getSelectedItem().toString().equals("July")) {
                         month = "07";
                     } else if (jComboBox1.getSelectedItem().toString().equals("August")) {
                         month = "08";
                     } else if (jComboBox1.getSelectedItem().toString().equals("September")) {
                         month = "09";
                     } else if (jComboBox1.getSelectedItem().toString().equals("October")) {
                         month = "10";
                     } else if (jComboBox1.getSelectedItem().toString().equals("November")) {
                         month = "11";
                     } else if (jComboBox1.getSelectedItem().toString().equals("December")) {
                         month = "12";
                     }
                     ArrayList<Schedule> ss = scheduleC.getAllRecord();
                     DefaultTableModel model1 = (DefaultTableModel) scheduleTable.getModel();
                     TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(scheduleTable.getModel());
                     scheduleTable.setRowSorter(sorter);
                     model1.setRowCount(0);
                     if(ss != null){
                         int c = 1;
                     for (Schedule d : ss) {
                             String aa = d.getDepartureDate().substring(5, 7);
                             if (d.getDepartureDate().substring(5, 7).equals(month)) {
                                 model1.addRow(new Object[]{Integer.toString(c), d.getScheduleID(), d.getDriver().getDriverName(), d.getBus().getBusPlateNo(), d.getRoute().getDestination(), d.getDepartureDate(), d.getDepartureTime()});

                             }
                             c++;
                         }
                         scheduleTable.setModel(model1);
                     }else{
                         JOptionPane.showMessageDialog(null, "Record not found", "Warning", JOptionPane.WARNING_MESSAGE);
                     }
                     
                 }
             }
         });

         reset.addActionListener((ActionEvent evt)->{
              DefaultTableModel model1 = (DefaultTableModel) scheduleTable.getModel();
              model1.setRowCount(0);
              criteriaList.setSelectedIndex(0);
              destinationList.setSelectedIndex(0);
              destinationList.setEnabled(false);
              jComboBox1.setSelectedIndex(0);
              jComboBox1.setEnabled(false);
         });
        all.addActionListener((ActionEvent evt) -> {
            ArrayList<Schedule> ss = scheduleC.getAllRecord();
            DefaultTableModel model1 = (DefaultTableModel) scheduleTable.getModel();
            TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(scheduleTable.getModel());
            scheduleTable.setRowSorter(sorter);
            model1.setRowCount(0);
            int c = 1;
            for (Schedule d : ss) {
                model1.addRow(new Object[]{Integer.toString(c), d.getScheduleID(), d.getDriver().getDriverName(), d.getBus().getBusPlateNo(), d.getRoute().getDestination(), d.getDepartureDate(), d.getDepartureTime()});
                c++;
            }
            scheduleTable.setModel(model1);

        });
        
        return panel;
    }
}
