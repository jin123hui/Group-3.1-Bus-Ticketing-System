/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.toedter.calendar.JDateChooser;
import control.BookingControl;
import control.CustomerControl;
import control.RouteControl;
import control.ScheduleControl;
import control.StaffControl;
import control.TicketControl;
import de.javasoft.plaf.synthetica.SyntheticaBlackStarLookAndFeel;
import domain.Booking;
import domain.Customer;
import domain.Route;
import domain.Schedule;
import domain.Staff;
import domain.Ticket;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JTextFieldDateEditor;
import control.BusControl;
import domain.Bus;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;
import java.util.regex.PatternSyntaxException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SpinnerDateModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import static ui.MainDriver.generateRandomWords;

/**
 *
 * @author CJH
 */

public class MainDriver extends JFrame {
    
    private JPanel topPanel = new JPanel(new BorderLayout());
    private JPanel mainPanel = new JPanel(new BorderLayout());
    private JPanel menuPanel = new JPanel();
    private JPanel infoPanel = new JPanel();
    private JLabel msg = new JLabel("Main Panel");
    private JPanel menuPane = new JPanel();
    private RouteControl rControl = new RouteControl();
    private ScheduleControl sControl = new ScheduleControl();  
    private TicketControl tControl = new TicketControl();
    private BookingControl bControl = new BookingControl();
    private CustomerControl cControl = new CustomerControl();
    private StaffControl stControl = new StaffControl();
    private BusControl busControl = new BusControl();
    private ArrayList<Integer> countList = new ArrayList<>();
    private ArrayList<Schedule> scheduleList = new ArrayList<>();
    private ArrayList<Ticket> ticketList = new ArrayList<>();
    private Customer customer = new Customer();
    private int pointsRequired;
    private Staff staffLogin = new Staff();
    private Customer currentCustomer = new Customer();
    private Booking currentBooking = new Booking();
    private ArrayList<Ticket> currentTicketList = new ArrayList<>();
    private JTextField jtfTotalAmt = new JTextField();
    private JTextField jtfGST = new JTextField(); 
    private JTextField jtfDisc = new JTextField();
    private JTextField jtfNett = new JTextField();
    private JTextField jtfCC = new JTextField();   
    private JTextField jtfCash = new JTextField();
    private JTextField jtfPaid = new JTextField();             
    private JTextField jtfChanges = new JTextField();
    private TableRowSorter<TableModel> sorter;
    private int errorCount = 0;
    private String nameEx;
    private String contactEx;
    private String icEx;        
    private String pointEx;
    private ArrayList<Booking> searchBookingList = new ArrayList<>();
    private Booking editB = new Booking();
    private ArrayList<Ticket> editT = new ArrayList<>();
    private ArrayList<Ticket> editTT = new ArrayList<>();
    private ArrayList<Ticket> ticketLLL = new ArrayList<>();
    private boolean editBooking = false;
    private boolean reprintCounter = false;
    private ArrayList<Schedule> selectedSchedule = new ArrayList<>();
    private JTextField jtfAPUserID = new javax.swing.JTextField();
    private JPasswordField jtfAPPassword = new javax.swing.JPasswordField();
    
    public MainDriver() {
    
        JLabel topLabel = new JLabel("CJH Bus Express Sdn. Bhd.");        
        ImageIcon icon = new ImageIcon(getClass().getResource("/images/top.png"));
        topLabel.setIcon(icon);      
        topLabel.setPreferredSize(new Dimension(1020, 150));       
        topPanel.setPreferredSize(new Dimension(1020, 150));
        mainPanel.setPreferredSize(new Dimension(1020, 650));        
        topPanel.add(topLabel);
        mainPanel.add(loginPanel());    
        add(topPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.SOUTH);            
    }
    
    public JPanel loginPanel() {        

        JPanel loginPanel = new JPanel();
        JPanel loginPane = new JPanel();
        JLabel topLabel = new JLabel("LOGIN");
        JLabel jlUser = new JLabel("User ID");
        JLabel jlPass = new JLabel("Password");
        JTextField jtfUserID = new JTextField();
        JPasswordField jtfPassword = new JPasswordField();
        JLabel jlError = new JLabel(" ");
        JButton jbtLogin = new JButton("Login");
        JButton jbtForgot = new JButton("Forgot your password?");

        loginPanel.setBackground(new Color(127, 127, 127));
        loginPanel.setBorder(new LineBorder(new Color(51, 51, 51), 7));
        loginPanel.setMaximumSize(new Dimension(1000, 650));
        loginPanel.setMinimumSize(new Dimension(1000, 650));
        loginPanel.setPreferredSize(new Dimension(1000, 650));
      
        loginPane.setBackground(new Color(204, 204, 204));
        loginPane.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 3));

        jtfUserID.requestFocusInWindow();
        topLabel.setBackground(new Color(59, 59, 59));
        topLabel.setFont(new Font("Verdana", 1, 24)); // NOI18N
        topLabel.setForeground(new Color(255, 255, 255));
        topLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topLabel.setOpaque(true);
        jlUser.setFont(new Font("Lucida Fax", 1, 14)); // NOI18N
        jlPass.setFont(new Font("Lucida Fax", 1, 14)); // NOI18N
        jbtLogin.setFont(new Font("Lucida Fax", 1, 14)); // NOI18N              
        jbtForgot.setFont(new Font("Lucida Fax", 1, 14)); // NOI18N
        jbtForgot.setBorderPainted(false);
        jbtForgot.setBackground(new Color(204, 204, 204));
        jlError.setHorizontalAlignment(SwingConstants.CENTER);
        jbtLogin.setMnemonic('L');
        jbtLogin.setToolTipText("Login into CJH BUS EXPRESS TICKETING SYSTEM.");
        jbtForgot.setMnemonic('F');
        jbtForgot.setToolTipText("Reset password.");
        jbtForgot.setForeground(Color.black);

        javax.swing.GroupLayout loginPaneLayout = new javax.swing.GroupLayout(loginPane);
        loginPane.setLayout(loginPaneLayout);
        loginPaneLayout.setHorizontalGroup(
            loginPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(loginPaneLayout.createSequentialGroup()
                .addGroup(loginPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loginPaneLayout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addGroup(loginPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jlError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(loginPaneLayout.createSequentialGroup()
                                .addGroup(loginPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlUser)
                                    .addComponent(jlPass))
                                .addGap(37, 37, 37)
                                .addGroup(loginPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jtfUserID)
                                    .addComponent(jtfPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)))))
                    .addGroup(loginPaneLayout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(jbtForgot, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(71, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPaneLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jbtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(172, 172, 172))
        );
        loginPaneLayout.setVerticalGroup(
            loginPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPaneLayout.createSequentialGroup()
                .addComponent(topLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addGroup(loginPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlUser)
                    .addComponent(jtfUserID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(loginPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlPass)
                    .addComponent(jtfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlError, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtForgot)
                .addGap(0, 41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(loginPanel);
        loginPanel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(286, 286, 286)
                .addComponent(loginPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(261, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(165, 165, 165)
                .addComponent(loginPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(152, Short.MAX_VALUE))
        );    
        
        jbtLogin.addActionListener((ActionEvent e) -> {
            String userID = jtfUserID.getText();
            String password = jtfPassword.getText();
            if(userID.equals("")||password.equals("")) {
                jlError.setText("Please enter user ID and password.");
                jlError.setForeground(Color.red);
            } else {
                Staff staffLogin = stControl.selectRecord(userID);
                if(staffLogin==null) {
                    jlError.setText("Invalid user ID or password.");
                    jlError.setForeground(Color.red);
                } else {
                    if(staffLogin.getStatus()=='D') {
                        jlError.setText("Deactivated staff account.");
                        jlError.setForeground(Color.red);
                    } else if(!password.equals(staffLogin.getPassword())) {
                        jlError.setText("Invalid user ID or password.");
                        jlError.setForeground(Color.red);
                    } else {
                        jlError.setText(" ");
                        this.staffLogin = staffLogin;
                        mainPanel.removeAll();
                        menuPanel.removeAll();
                        infoPanel.removeAll();
                        menuPane.removeAll();
                        mainPanel.revalidate();
                        mainPanel.repaint();
                        mainPanel.add(mainPagePanel(), BorderLayout.CENTER);
                    }
                }
            }
        });
        
        jtfUserID.addActionListener((ActionEvent e) -> {
            if(jtfUserID.getText().equals(""))
                jtfUserID.addActionListener(jbtLogin.getActionListeners()[0]);
            else
                jtfPassword.requestFocusInWindow();
        });
        
        jtfPassword.addActionListener(jbtLogin.getActionListeners()[0]);
        
        DocumentListener errorMsgRefresh = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                jlError.setText(" ");
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                jlError.setText(" ");
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }           
        };           

        jtfUserID.getDocument().addDocumentListener(errorMsgRefresh);
        jtfPassword.getDocument().addDocumentListener(errorMsgRefresh);
        
        jbtForgot.addActionListener((ActionEvent e) -> {
            mainPanel.removeAll();                       
            mainPanel.revalidate();
            mainPanel.repaint();
            mainPanel.add(forgotPassPanel(), BorderLayout.CENTER);
        });
        
        return loginPanel;
    }
    
    public JPanel forgotPassPanel() {
        
        JPanel forgotPassPanel = new JPanel();      
        JPanel jPanel1 = new JPanel();
        JLabel jLabel2 = new JLabel("Staff ID");
        JLabel jLabel3 = new JLabel("Staff Name");
        JLabel jLabel4 = new JLabel("Contact No");
        JLabel jLabel5 = new JLabel("IC No");
        JTextField jtfStaffID = new JTextField();
        JTextField jtfStaffName = new JTextField();
        JTextField jtfContactNo = new JTextField();
        JTextField jtfICNo = new JTextField();
        JLabel jlErrorMsg = new JLabel(" ");
        JButton jbtRecover = new JButton("Recover");
        JLabel jLabel7 = new JLabel("New Password");
        JTextField jtfNewPs = new JTextField();
        JButton jbtReset = new JButton("Reset");
        JLabel jlInfoMsg = new JLabel();
        JLabel jLabel9 = new JLabel("Password Recovery");
        JButton jbtBack = new JButton("Back");

        forgotPassPanel.setBackground(new java.awt.Color(127, 127, 127));
        forgotPassPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51), 7));
        forgotPassPanel.setMaximumSize(new java.awt.Dimension(1000, 650));
        forgotPassPanel.setMinimumSize(new java.awt.Dimension(1000, 650));
        forgotPassPanel.setPreferredSize(new java.awt.Dimension(1000, 650));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));

        jlInfoMsg.setFont(new java.awt.Font("Lucida Fax", 1, 10)); // NOI18N
        jlInfoMsg.setForeground(new java.awt.Color(51, 51, 255));
        jlInfoMsg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlInfoMsg.setText("< Please login with this password to create a new password >");

        jLabel9.setFont(new java.awt.Font("Lucida Fax", 1, 24)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 79, Short.MAX_VALUE)
                .addComponent(jlInfoMsg)
                .addGap(71, 71, 71))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addComponent(jbtRecover)
                        .addGap(18, 18, 18)
                        .addComponent(jbtReset, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jlErrorMsg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(42, 42, 42)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfStaffID)
                                    .addComponent(jtfStaffName)
                                    .addComponent(jtfContactNo)
                                    .addComponent(jtfICNo)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                                .addComponent(jtfNewPs, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jtfStaffID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jtfStaffName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4))
                    .addComponent(jtfContactNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jtfICNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlErrorMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtRecover)
                    .addComponent(jbtReset))
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfNewPs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlInfoMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(forgotPassPanel);
        forgotPassPanel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(245, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(247, 247, 247))
            .addGroup(layout.createSequentialGroup()
                .addGap(461, 461, 461)
                .addComponent(jbtBack)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbtBack)
                .addContainerGap(68, Short.MAX_VALUE))
        );
        
        jLabel7.setEnabled(false);
        jtfNewPs.setEditable(false);
        jtfNewPs.setEnabled(false);
        jlInfoMsg.setEnabled(false);
        
        jbtBack.addActionListener((ActionEvent e) -> {
            mainPanel.removeAll();                       
            mainPanel.revalidate();
            mainPanel.repaint();
            mainPanel.add(loginPanel(), BorderLayout.CENTER);
        });
        
        jbtRecover.addActionListener((ActionEvent e) -> {
            String id = jtfStaffID.getText();
            String name = jtfStaffName.getText();
            String contact = jtfContactNo.getText();
            String icno = jtfICNo.getText();
            int newPsLength = 6;
            if(id.equals("")||name.equals("")||contact.equals("")||icno.equals("")) {
                jlErrorMsg.setText("Please fill in all the information.");
                jlErrorMsg.setForeground(Color.red);
            } else {
                if(stControl.selectRecord(id)==null) {
                    jlErrorMsg.setText("Invalid staff ID in database.");
                    jlErrorMsg.setForeground(Color.red);
                } else {
                    Staff staff = stControl.selectRecord(id);
                    if(!name.equals(staff.getStaffName())||!contact.equals(staff.getContactNo())||!icno.equals(staff.getIcNo())) {
                        jlErrorMsg.setText("Invalid staff information.");
                        jlErrorMsg.setForeground(Color.red);
                    } else {
                        jlErrorMsg.setText(" ");
                        jLabel7.setEnabled(true);       
                        jtfNewPs.setEnabled(true);
                        jlInfoMsg.setEnabled(true); 
                        jtfNewPs.setText("");
                        for(int i=0; i<newPsLength; i++)
                            jtfNewPs.setText(jtfNewPs.getText() + generateRandomWords(newPsLength)[i]);
                        Staff staffWithNewPs = new Staff(staff.getStaffID(), staff.getStaffName(), staff.getAddress(), staff.getContactNo(), staff.getIcNo(), staff.getSalary(), jtfNewPs.getText(), staff.getStatus());
                        stControl.modifyRecord(staffWithNewPs);
                        jbtRecover.setEnabled(false);
                    }
                }
            }
        });
        
        jbtReset.addActionListener((ActionEvent e) -> {
            jtfStaffID.setText("");
            jtfStaffName.setText("");
            jtfContactNo.setText("");
            jtfICNo.setText("");
            jbtRecover.setEnabled(true);
            jlErrorMsg.setText(" ");
            jLabel7.setEnabled(false);
            jtfNewPs.setText("");        
            jtfNewPs.setEnabled(false);
            jlInfoMsg.setEnabled(false);
        });
        
        DocumentListener errorMsgRefresh = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                jlErrorMsg.setText(" ");
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                jlErrorMsg.setText(" ");
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }           
        };    
        
        jtfStaffID.getDocument().addDocumentListener(errorMsgRefresh);
        jtfStaffName.getDocument().addDocumentListener(errorMsgRefresh);
        jtfContactNo.getDocument().addDocumentListener(errorMsgRefresh);
        jtfICNo.getDocument().addDocumentListener(errorMsgRefresh);       

        return forgotPassPanel;
    }
    
    public static String[] generateRandomWords(int numberOfWords) {
        String[] randomStrings = new String[numberOfWords];
        Random random = new Random();
        for(int i = 0; i < numberOfWords; i++)
                randomStrings[i] = Character.toString((char)('a' + random.nextInt(26)));
   
        return randomStrings;
    }
    
    public JPanel sidePanel() {      

        JPanel sidePanel = new JPanel(); 
        JPanel jPanel1 = new JPanel();
        JLabel jlImage = new JLabel(" ");
        JLabel jLabel1 = new JLabel("ID: ");
        JLabel jLabel2 = new JLabel("Name: ");
        JLabel jlID = new JLabel(this.staffLogin.getStaffID());
        JLabel jlName = new JLabel(this.staffLogin.getStaffName());
        JButton jbtEditMyInfo = new JButton("Edit My Information");
        JButton jbtMain = new JButton("Main Panel");
        JButton jbtAdmin = new JButton("Admin Panel");
        JButton jbtReport = new JButton("Report Panel");
        JButton jbtLogout = new JButton("Logout");
        JButton jbtExit = new JButton("Exit");
        
        ImageIcon iconS = new ImageIcon(getClass().getResource("/images/staff.png"));
        ImageIcon iconM = new ImageIcon(getClass().getResource("/images/manager.png"));
        if(staffLogin.getStaffID().startsWith("S")) {
            jlImage.setIcon(iconS);
        } else if(staffLogin.getStaffID().startsWith("M")) {
            jlImage.setIcon(iconM);
        }

        sidePanel.setBackground(new Color(102, 102, 102));
        //sidePanel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        sidePanel.setMaximumSize(new Dimension(250, 650));
        sidePanel.setMinimumSize(new Dimension(250, 650));
        sidePanel.setPreferredSize(new Dimension(250, 650));

        jPanel1.setBackground(new Color(204, 204, 204));
        jPanel1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        //jlImage.setBackground(new Color(0, 51, 255));
        jlImage.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setFont(new Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setFont(new Font("Times New Roman", 1, 14)); // NOI18N
        jbtEditMyInfo.setFont(new Font("Lucida Fax", 1, 14)); // NOI18N    
        jbtMain.setFont(new Font("Goudy Old Style", 1, 18)); // NOI18N       
        jbtAdmin.setFont(new Font("Goudy Old Style", 1, 18)); // NOI18N
        jbtReport.setFont(new Font("Goudy Old Style", 1, 18)); // NOI18N     
        jbtLogout.setFont(new Font("Tahoma", 1, 11)); // NOI18N  
        jbtExit.setFont(new Font("Tahoma", 1, 11)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtEditMyInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlImage, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jlID))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jlName))
                .addGap(18, 18, 18)
                .addComponent(jbtEditMyInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );        

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(sidePanel);
        sidePanel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 16, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jbtLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jbtExit, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jbtReport, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jbtAdmin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                                    .addComponent(jbtMain, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(16, 16, 16)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbtMain, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbtAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbtReport, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtLogout)
                    .addComponent(jbtExit))
                .addGap(21, 21, 21))
        );
        
        jbtEditMyInfo.addActionListener((ActionEvent e) -> {
            this.countList.clear();
            this.ticketList.clear();
            this.editBooking = false;
            this.reprintCounter = false;
            jtfAPPassword.setText("");
            menuPanel.removeAll();
            menuPanel.revalidate();
            menuPanel.repaint();
            menuPanel.add(editMyInfoPanel());
        });
        
        jbtMain.addActionListener((ActionEvent e) -> {
            this.countList.clear();
            this.ticketList.clear();
            this.editBooking = false;
            this.reprintCounter = false;
            jtfAPPassword.setText("");
            menuPanel.removeAll();
            menuPanel.revalidate();
            menuPanel.repaint();
            menuPanel.add(msg);
            menuPanel.add(menuPane, BorderLayout.CENTER);           
            menuPanel.setPreferredSize(new Dimension(770, 650));
            infoPanel.setPreferredSize(new Dimension(250, 650));
        });
        
        jbtAdmin.addActionListener((ActionEvent e) -> {
            this.countList.clear();
            this.ticketList.clear();
            this.editBooking = false;
            this.reprintCounter = false;
            jtfAPPassword.setText("");
            menuPanel.removeAll();
            menuPanel.revalidate();
            menuPanel.repaint();
            menuPanel.add(adminPanelLogin(), BorderLayout.CENTER);
            jtfAPPassword.requestFocusInWindow();
        });
        
        jbtReport.addActionListener((ActionEvent e) -> {
            this.countList.clear();
            this.ticketList.clear();
            this.editBooking = false;
            this.reprintCounter = false;
            jtfAPPassword.setText("");
            menuPanel.removeAll();
            menuPanel.revalidate();
            menuPanel.repaint();
            menuPanel.add(new ReportPanel(), BorderLayout.CENTER);
        });
        
        jbtLogout.addActionListener((ActionEvent e) -> {
            this.countList.clear();
            this.ticketList.clear();
            this.staffLogin = null;
            this.editBooking = false;
            this.reprintCounter = false;
            jtfAPPassword.setText("");
            mainPanel.removeAll();
            mainPanel.revalidate();
            mainPanel.repaint();
            mainPanel.add(loginPanel(), BorderLayout.CENTER);
        });
        
        jbtExit.addActionListener((ActionEvent e) -> {
            if((JOptionPane.showConfirmDialog(null, "Quit?", "Warning", JOptionPane.OK_CANCEL_OPTION))!=2)
                System.exit(-1);
        });
        
        return sidePanel;
    }
    
    public JPanel editMyInfoPanel() {
        
        JPanel editMyInfoPanel = new JPanel();
        JPanel jPanel1 = new javax.swing.JPanel();
        JLabel jLabel2 = new javax.swing.JLabel();
        JLabel jLabel1 = new javax.swing.JLabel();
        JLabel jLabel3 = new javax.swing.JLabel();
        JLabel jLabel4 = new javax.swing.JLabel();
        JLabel jLabel5 = new javax.swing.JLabel();
        JLabel jLabel6 = new javax.swing.JLabel();
        JLabel jLabel7 = new javax.swing.JLabel();
        JTextField jtfID = new javax.swing.JTextField();
        JTextField jtfName = new javax.swing.JTextField();
        JTextField jtfContact = new javax.swing.JTextField();
        JTextField jtfIC = new javax.swing.JTextField();
        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        JTextArea jtaAddr = new javax.swing.JTextArea();
        JTextField jtfSalary = new javax.swing.JTextField();
        JTextField jtfPassword = new javax.swing.JTextField();
        JLabel jLabel8 = new javax.swing.JLabel();
        JTextField jtfNewPass = new javax.swing.JTextField();
        JLabel jlError = new javax.swing.JLabel();
        JButton jbtModify = new javax.swing.JButton();
        JButton jbtSubmit = new javax.swing.JButton();
        JLabel jLabel9 = new javax.swing.JLabel();
        JTextField jtfConfirmPass = new javax.swing.JTextField();

        editMyInfoPanel.setBackground(new java.awt.Color(102, 102, 102));
        editMyInfoPanel.setMaximumSize(new java.awt.Dimension(740, 640));
        editMyInfoPanel.setMinimumSize(new java.awt.Dimension(740, 640));
        editMyInfoPanel.setPreferredSize(new java.awt.Dimension(740, 640));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 5, true), "Edit My Information", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Lucida Fax", 1, 24))); // NOI18N
        jPanel1.setMaximumSize(new java.awt.Dimension(561, 526));
        jPanel1.setMinimumSize(new java.awt.Dimension(561, 526));

        jLabel2.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel2.setText("Staff ID");

        jLabel1.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel1.setText("Staff Name");

        jLabel3.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel3.setText("Contact No");

        jLabel4.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel4.setText("IC No");

        jLabel5.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel5.setText("Password");

        jLabel6.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel6.setText("Address");

        jLabel7.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel7.setText("Salary");

        jtfID.setEditable(false);

        jtfName.setEditable(false);

        jtfContact.setEditable(false);

        jtfIC.setEditable(false);

        jtaAddr.setEditable(false);
        jtaAddr.setColumns(20);
        jtaAddr.setLineWrap(true);
        jtaAddr.setRows(5);
        jScrollPane1.setViewportView(jtaAddr);

        jtfSalary.setEditable(false);

        jtfPassword.setEditable(false);

        jLabel8.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel8.setText("New Password");

        jtfNewPass.setEditable(false);       

        jlError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlError.setText(" ");

        jbtModify.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jbtModify.setText("Modify");

        jbtSubmit.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jbtSubmit.setText("Submit");

        jLabel9.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel9.setText("Confrim Password");

        jtfConfirmPass.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(189, 189, 189)
                .addComponent(jbtModify)
                .addGap(18, 18, 18)
                .addComponent(jbtSubmit)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(jtfConfirmPass, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlError, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                                        .addGap(46, 46, 46))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel8))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jtfID)
                                    .addComponent(jtfName)
                                    .addComponent(jtfContact)
                                    .addComponent(jtfIC)
                                    .addComponent(jScrollPane1)
                                    .addComponent(jtfSalary)
                                    .addComponent(jtfPassword)
                                    .addComponent(jtfNewPass, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(101, 101, 101))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtfID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtfContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtfIC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jtfSalary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jtfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfNewPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfConfirmPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jlError, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtSubmit)
                    .addComponent(jbtModify))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(editMyInfoPanel);
        editMyInfoPanel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(90, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );
        
        if(staffLogin!=null) {
            jtfID.setText(staffLogin.getStaffID());
            jtfName.setText(staffLogin.getStaffName());
            jtfContact.setText(staffLogin.getContactNo());
            jtfIC.setText(staffLogin.getIcNo());
            jtaAddr.setText(staffLogin.getAddress());
            jtfSalary.setText(String.format("%.2f",staffLogin.getSalary()));
            jbtSubmit.setEnabled(false);
            
            jbtModify.addActionListener((ActionEvent e) -> {
                jtfName.setEditable(true);
                jtfContact.setEditable(true);
                jtfIC.setEditable(true);
                jtaAddr.setEditable(true);
                jtfPassword.setEditable(true);
                jtfNewPass.setEditable(true);
                jtfConfirmPass.setEditable(true);
                jbtSubmit.setEnabled(true);
                jbtModify.setEnabled(false);
                jlError.setText(" ");
            });
            
            jbtSubmit.addActionListener((ActionEvent e) -> {
                jlError.setText(" ");
                String name = jtfName.getText();
                String contact = jtfContact.getText();
                String ic = jtfIC.getText();
                String addr = jtaAddr.getText();
                String ps = jtfPassword.getText();
                String nPs = jtfNewPass.getText();
                String cPs = jtfConfirmPass.getText();
                
                int invalid = 0;
                int change = 0;
                if(name.equals(staffLogin.getStaffName())&&contact.equals(staffLogin.getContactNo())&&ic.equals(staffLogin.getIcNo())&&addr.equals(staffLogin.getAddress())&&ps.equals("")&&nPs.equals("")&&cPs.equals("")) {
                    jtfName.setEditable(false);
                    jtfContact.setEditable(false);
                    jtfIC.setEditable(false);
                    jtaAddr.setEditable(false);
                    jtfPassword.setEditable(false);
                    jtfNewPass.setEditable(false);
                    jtfConfirmPass.setEditable(false);
                    jbtModify.setEnabled(true);
                    jbtSubmit.setEnabled(false);
                    jlError.setText(" ");    
                    jtfPassword.setText("");
                    jtfNewPass.setText("");
                    jtfConfirmPass.setText("");
                } else if(name.equals("")||contact.equals("")||ic.equals("")||addr.equals("")) {
                    jlError.setText("Please enter all the required information.");
                    jlError.setForeground(Color.red);
                } else {
                    if(!name.equals(staffLogin.getStaffName())) {
                        if(name.length()>30) {
                            jlError.setText("Name not more than 30 words.");
                            jlError.setForeground(Color.red);
                            invalid++;
                        } else 
                            change++;
                    }
                    if(!contact.equals(staffLogin.getContactNo())) {
                        if(!contact.matches("01[\\d]{1}-[\\d]{7,8}")) {
                            jlError.setText("Invalid contact no format.");
                            jlError.setForeground(Color.red);
                            invalid++;
                        } else 
                            change++;
                    }
                    if(!ic.equals(staffLogin.getIcNo())) {
                        if(!ic.matches("[\\d]{6}-[\\d]{2}-[\\d]{4}")) {
                            jlError.setText("Invalid ic no format.");
                            jlError.setForeground(Color.red);
                            invalid++;
                        } else {                             
                            String date = ic.substring(0, 6);                      
                            int errorCounter = 0;
                            Date valiDate = null;
                            try{
                                SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
                                format.setLenient(false);
                                valiDate = format.parse(date);
                            } catch(Exception ex) {
                                errorCounter++;
                            }
                            if(errorCounter!=0) {
                                jlError.setText("Invalid ic no format.");
                                jlError.setForeground(Color.red);
                                invalid++;
                            } else {
                                if(stControl.selectRecordByIC(ic)!=null) {
                                    jlError.setText("IC exists in database.");
                                    jlError.setForeground(Color.red);
                                    invalid++;                        
                                } else {
                                    change++;
                                }
                            }
                        }
                    }
                    if(!addr.equals(staffLogin.getAddress())) {
                        if(addr.length()>80) {
                            jlError.setText("Length of address not more than 80.");
                            jlError.setForeground(Color.red);
                            invalid++; 
                        } else 
                            change++;
                    }
                    
                    if(change!=0) {
                        if(ps.equals("")) {
                            jlError.setText("Please enter password to confirm changes.");
                            jlError.setForeground(Color.red);
                        } else {
                            if(ps.equals(staffLogin.getPassword())) {
                                if(nPs.equals("")&&cPs.equals("")) {
                                    if(invalid==0) {
                                        jlError.setText("Update successfully.");
                                        jlError.setForeground(Color.blue);
                                        jtfName.setEditable(false);
                                        jtfContact.setEditable(false);
                                        jtfIC.setEditable(false);
                                        jtaAddr.setEditable(false);
                                        jtfPassword.setEditable(false);
                                        jtfNewPass.setEditable(false);
                                        jtfConfirmPass.setEditable(false);
                                        jbtModify.setEnabled(true);
                                        jbtSubmit.setEnabled(false); 
                                        jtfPassword.setText("");
                                        jtfNewPass.setText("");
                                        jtfConfirmPass.setText("");
                                        Staff staff = new Staff(jtfID.getText(), name, addr, contact, ic, staffLogin.getSalary(), staffLogin.getPassword(), staffLogin.getStatus());
                                        //this.sidePanel()
                                        staffLogin = staff;
                                        infoPanel.removeAll();
                                        infoPanel.revalidate();
                                        infoPanel.repaint();
                                        infoPanel.add(sidePanel());
                                        stControl.modifyRecord(staff);                       
                                    }                                    
                                } else {
                                    if(nPs.equals("")||cPs.equals("")) {
                                        jlError.setText("Enter new password and confirm password to change password.");
                                        jlError.setForeground(Color.red);
                                    } else if(nPs.length()<5 || nPs.length()>20) {
                                        jlError.setText("Password must between 5 to 20 words.");
                                        jlError.setForeground(Color.red);
                                    } else if(!nPs.equals(cPs)) {
                                        jlError.setText("New password and confirm password must be the same.");
                                        jlError.setForeground(Color.red);
                                    } else {                                  
                                        if(invalid==0) {
                                            jlError.setText("Update successfully.");
                                            jlError.setForeground(Color.blue);
                                            jtfName.setEditable(false);
                                            jtfContact.setEditable(false);
                                            jtfIC.setEditable(false);
                                            jtaAddr.setEditable(false);
                                            jtfPassword.setEditable(false);
                                            jtfNewPass.setEditable(false);
                                            jtfConfirmPass.setEditable(false);
                                            jbtModify.setEnabled(true);
                                            jbtSubmit.setEnabled(false);       
                                            jtfPassword.setText("");
                                            jtfNewPass.setText("");
                                            jtfConfirmPass.setText("");
                                            Staff staff = new Staff(jtfID.getText(), name, addr, contact, ic, staffLogin.getSalary(), nPs, staffLogin.getStatus());
                                            staffLogin = staff;
                                            infoPanel.removeAll();
                                            infoPanel.revalidate();
                                            infoPanel.repaint();
                                            infoPanel.add(sidePanel());
                                            stControl.modifyRecord(staff);                       
                                        }
                                    }
                                }
                            } else {
                                jlError.setText("Wrong password.");
                                jlError.setForeground(Color.red);
                            }
                        }                                          
                    } else {
                        if(invalid==0) {
                            if(!ps.equals(staffLogin.getPassword())) {
                                jlError.setText("Wrong password.");
                                jlError.setForeground(Color.red);
                            }
                            else if(ps.equals("")) {
                                jlError.setText("Please enter old password to change password.");
                                jlError.setForeground(Color.red);
                            } else {
                                if(nPs.equals("")||cPs.equals("")) {
                                    jlError.setText("Enter new password and confirm password to change password.");
                                    jlError.setForeground(Color.red);
                                } else if(nPs.length()>20) {
                                    jlError.setText("Password not more than 20 words.");
                                    jlError.setForeground(Color.red);
                                } else if(!nPs.equals(cPs)) {
                                    jlError.setText("New password and confirm password must be the same.");
                                    jlError.setForeground(Color.red);
                                } else {                                  
                                    if(invalid==0) {
                                        jlError.setText("Update successfully.");
                                        jlError.setForeground(Color.blue);
                                        jtfName.setEditable(false);
                                        jtfContact.setEditable(false);
                                        jtfIC.setEditable(false);
                                        jtaAddr.setEditable(false);
                                        jtfPassword.setEditable(false);
                                        jtfNewPass.setEditable(false);
                                        jtfConfirmPass.setEditable(false);
                                        jbtModify.setEnabled(true);
                                        jbtSubmit.setEnabled(false);       
                                        jtfPassword.setText("");
                                        jtfNewPass.setText("");
                                        jtfConfirmPass.setText("");
                                        Staff staff = new Staff(jtfID.getText(), name, addr, contact, ic, staffLogin.getSalary(), nPs, staffLogin.getStatus());
                                        staffLogin = staff;
                                        stControl.modifyRecord(staff);                       
                                    }
                                }                           
                            }
                        }
                    }                   
                }               
            });
        }

        
        return editMyInfoPanel;
    }
    
    public JPanel mainPagePanel() {
    
        JPanel mainPagePanel = new JPanel(new BorderLayout());
        mainPagePanel.setPreferredSize(new Dimension(1020, 650)); 
        infoPanel.add(sidePanel());
        infoPanel.setPreferredSize(new Dimension(250, 650));
        infoPanel.setBackground(new Color(102,102,102)); 
        menuPanel.setPreferredSize(new Dimension(770,650));      
        menuPanel.setBackground(Color.LIGHT_GRAY);
        GridLayout glOne = new GridLayout(3,2);
        glOne.setHgap(10);
        glOne.setVgap(10);
        menuPane.setLayout(glOne);
        menuPane.setBackground(Color.LIGHT_GRAY);
        
        JButton jbtTicket = new JButton("Ticketing");
        ImageIcon iconT = new ImageIcon(getClass().getResource("/images/ticketing.png"));
        jbtTicket.setIcon(iconT);
        jbtTicket.setVerticalTextPosition(SwingConstants.BOTTOM);
        jbtTicket.setHorizontalTextPosition(SwingConstants.CENTER);
        jbtTicket.addActionListener((ActionEvent e) -> {
            menuPanel.removeAll();
            menuPanel.revalidate();
            menuPanel.repaint();
            menuPanel.add(ticketing(), BorderLayout.CENTER);
            menuPanel.setPreferredSize(new Dimension(770,650));  
            infoPanel.setPreferredSize(new Dimension(250,650));
        });       
        
        JButton jbtBooking = new JButton("Booking");
        jbtBooking.setPreferredSize(new Dimension(350,190));
        ImageIcon iconB = new ImageIcon(getClass().getResource("/images/booking.png"));
        jbtBooking.setIcon(iconB);
        jbtBooking.setVerticalTextPosition(SwingConstants.BOTTOM);
        jbtBooking.setHorizontalTextPosition(SwingConstants.CENTER);
        jbtBooking.addActionListener((ActionEvent e) -> {
            menuPanel.removeAll();
            menuPanel.revalidate();
            menuPanel.repaint();
            menuPanel.add(bookingPanel(), BorderLayout.CENTER);
            menuPanel.setPreferredSize(new Dimension(770,650));  
            infoPanel.setPreferredSize(new Dimension(250,650));
        });
        
        JButton jbtCustomer = new JButton("Customer");
        ImageIcon iconC = new ImageIcon(getClass().getResource("/images/customer.png"));
        jbtCustomer.setIcon(iconC);
        jbtCustomer.setVerticalTextPosition(SwingConstants.BOTTOM);
        jbtCustomer.setHorizontalTextPosition(SwingConstants.CENTER);
        jbtCustomer.addActionListener((ActionEvent e) -> {
            menuPanel.removeAll();
            menuPanel.revalidate();
            menuPanel.repaint();
            menuPanel.add(customerPanel(), BorderLayout.CENTER);
            menuPanel.setPreferredSize(new Dimension(770,650));  
            infoPanel.setPreferredSize(new Dimension(250,650));
        });  
        
        JButton jbtMember = new JButton("Membership");
        ImageIcon iconM = new ImageIcon(getClass().getResource("/images/membership.png"));
        jbtMember.setIcon(iconM);
        jbtMember.setVerticalTextPosition(SwingConstants.BOTTOM);
        jbtMember.setHorizontalTextPosition(SwingConstants.CENTER);
        jbtMember.addActionListener((ActionEvent e) -> {
            menuPanel.removeAll();
            menuPanel.revalidate();
            menuPanel.repaint();
            menuPanel.add(membershipPanel(), BorderLayout.CENTER);
            menuPanel.setPreferredSize(new Dimension(770,650));  
            infoPanel.setPreferredSize(new Dimension(250,650));
        });
        
        JButton jbtGift = new JButton("Gift Redemption");
        ImageIcon iconG = new ImageIcon(getClass().getResource("/images/gift.png"));
        jbtGift.setIcon(iconG);
        jbtGift.setVerticalTextPosition(SwingConstants.BOTTOM);
        jbtGift.setHorizontalTextPosition(SwingConstants.CENTER);
        jbtGift.addActionListener((ActionEvent e) -> {
            menuPanel.removeAll();
            menuPanel.revalidate();
            menuPanel.repaint();
            menuPanel.add(giftPanel(), BorderLayout.CENTER);
            menuPanel.setPreferredSize(new Dimension(770,650));  
            infoPanel.setPreferredSize(new Dimension(250,650));
        });
        
        JButton jbtTrack = new JButton("Bus Tracking");
        ImageIcon iconTr = new ImageIcon(getClass().getResource("/images/tracking.png"));
        jbtTrack.setIcon(iconTr);
        jbtTrack.setVerticalTextPosition(SwingConstants.BOTTOM);
        jbtTrack.setHorizontalTextPosition(SwingConstants.CENTER);
        jbtTrack.addActionListener((ActionEvent e) -> {
            menuPanel.removeAll();
            menuPanel.revalidate();
            menuPanel.repaint();
            menuPanel.add(busTrackingPanel(), BorderLayout.CENTER);
            menuPanel.setPreferredSize(new Dimension(770,650));  
            infoPanel.setPreferredSize(new Dimension(250,650));
        });
                
        Font font = new Font("Goudy Old Style", 1, 30);
        jbtTicket.setFont(font);
        jbtBooking.setFont(font);
        jbtCustomer.setFont(font);
        jbtMember.setFont(font);
        jbtGift.setFont(font);
        jbtTrack.setFont(font);
        
        menuPane.add(jbtTicket);
        menuPane.add(jbtBooking);
        menuPane.add(jbtCustomer);
        menuPane.add(jbtMember);
        menuPane.add(jbtGift);
        menuPane.add(jbtTrack);
              
        msg.setFont(new Font("Goudy Old Style", 1, 30));
        menuPanel.add(msg);
        menuPanel.add(menuPane);               
        
        mainPagePanel.add(infoPanel, BorderLayout.EAST);
        mainPagePanel.add(menuPanel, BorderLayout.WEST);
        mainPagePanel.setPreferredSize(new Dimension(1020, 650));
        return mainPagePanel;
    }      
    
    public JPanel ticketing() {
            
        JPanel ticketing = new JPanel();
        JPanel firstStep = new JPanel();
        JLabel jLabel1 = new JLabel("Ticketing Information");
        JLabel jLabel2 = new JLabel("From");
        JLabel jLabel3 = new JLabel("To");
        JLabel jLabel4 = new JLabel("Date");
        JPanel jPanel3 = new JPanel();
        JScrollPane jScrollPane1 = new JScrollPane();
        JButton jbtCheck = new JButton("Check");
        JButton jbtReset = new JButton("Reset");
        JButton jbtBack = new JButton("Back");
        JComboBox jcbTo = new JComboBox();
        JDateChooser jdcDate = new JDateChooser();
        JList jlSchedule = new JList();
        JLabel jtfErrorMsg = new JLabel(" ");
        JTextField jtfFrom = new JTextField("CJH Terminal");
        JPanel panel0 = new JPanel();      
                                              
        ticketing.setMaximumSize(new java.awt.Dimension(740, 640));
        ticketing.setMinimumSize(new Dimension(740, 640));
        ticketing.setPreferredSize(new Dimension(740, 640));
        panel0.setMaximumSize(new Dimension(740, 640));
        panel0.setMinimumSize(new Dimension(740, 640));
        panel0.setPreferredSize(new Dimension(740, 640));
        panel0.setBackground(Color.LIGHT_GRAY);
        firstStep.setBackground(new Color(102, 102, 102));
        jLabel1.setFont(new Font("Lucida Fax", 1, 24)); // NOI18N
        jLabel1.setForeground(new Color(255, 255, 255));
        jPanel3.setBackground(new Color(204, 204, 204));
        jLabel2.setFont(new Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel3.setFont(new Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel4.setFont(new Font("Lucida Fax", 1, 12)); // NOI18N
        jtfFrom.setEditable(false);
        jdcDate.setDateFormatString("yyyy-MM-dd");
        jbtCheck.setFont(new Font("Lucida Fax", 1, 12)); // NOI18N       
        jtfErrorMsg.setFont(new Font("Lucida Fax", 1, 12)); // NOI18N
        jtfErrorMsg.setHorizontalAlignment(SwingConstants.CENTER);
        jbtReset.setFont(new Font("Lucida Fax", 1, 12)); // NOI18N
        jlSchedule.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jlSchedule.setToolTipText("");      
        jScrollPane1.setViewportView(jlSchedule);
        jbtBack.setFont(new Font("Lucida Fax", 1, 12)); // NOI18N
        
        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtCheck)
                        .addGap(18, 18, 18)
                        .addComponent(jbtReset)
                        .addGap(47, 47, 47))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel2)
                                        .addGap(68, 68, 68))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfFrom, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jcbTo, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jdcDate, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(66, 66, 66))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jtfErrorMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtfFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jcbTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jdcDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtCheck)
                    .addComponent(jbtReset))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jtfErrorMsg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        
        javax.swing.GroupLayout firstStepLayout = new javax.swing.GroupLayout(firstStep);
        firstStep.setLayout(firstStepLayout);
        firstStepLayout.setHorizontalGroup(
            firstStepLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(firstStepLayout.createSequentialGroup()
                .addContainerGap(222, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(215, 215, 215))
            .addGroup(firstStepLayout.createSequentialGroup()
                .addGap(176, 176, 176)
                .addGroup(firstStepLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(firstStepLayout.createSequentialGroup()
                .addGap(330, 330, 330)
                .addComponent(jbtBack)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        firstStepLayout.setVerticalGroup(
            firstStepLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(firstStepLayout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(23, 23, 23)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtBack)
                .addGap(37, 37, 37))
        );

        javax.swing.GroupLayout panel0Layout = new javax.swing.GroupLayout(panel0);
        panel0.setLayout(panel0Layout);
        panel0Layout.setHorizontalGroup(
            panel0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel0Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(firstStep, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel0Layout.setVerticalGroup(
            panel0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel0Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(firstStep, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(ticketing);
        ticketing.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel0, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        
        ArrayList<Route> routeList = rControl.selectAllRecord();          
        ArrayList<String> routeR = new ArrayList<>();
        for(int i=0; i<routeList.size(); i++) {
            if(routeList.get(i).getStatus()=='A') {
                routeR.add(routeList.get(i).getDestination());
            }
        }
        String[] routeChoice = new String[routeR.size()];
        for(int i=0; i<routeR.size(); i++){
            routeChoice[i] = routeR.get(i);
        }

        DefaultComboBoxModel model = new DefaultComboBoxModel(routeChoice);
        jcbTo.setModel(model);    
        
        JTextFieldDateEditor editor = (JTextFieldDateEditor) jdcDate.getDateEditor();
        editor.setEditable(false);
        jdcDate.setMinSelectableDate(new Date());
       
        DefaultListModel scheduleListM = new DefaultListModel();
        jbtCheck.addActionListener((ActionEvent evt) -> {                                                  
            jlSchedule.setModel(scheduleListM);  
            scheduleListM.removeAllElements();
            if(((JTextField)jdcDate.getDateEditor().getUiComponent()).getText().isEmpty()) {
                jtfErrorMsg.setText("Please select a date.");
                jtfErrorMsg.setForeground(Color.red);
            } else if(!((JTextField)jdcDate.getDateEditor().getUiComponent()).getText().matches("^(19|20)\\d\\d-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$")) {
                jtfErrorMsg.setText("Please select a valid date.");
                jtfErrorMsg.setForeground(Color.red);
            } else {
                jtfErrorMsg.setText(" ");
                Route routeSelected = rControl.selectRecordByDestination(jcbTo.getSelectedItem().toString());
                String dateSelected = ((JTextField)jdcDate.getDateEditor().getUiComponent()).getText();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");               
                ArrayList<Schedule> scheduleDisplayList = sControl.selectRecordByRouteAndDate(routeSelected, dateSelected);
                if(!scheduleDisplayList.isEmpty()) {
                    
                    int counter = 0;
                    ArrayList<Schedule> ssList = new ArrayList<>();
                    for(int i=0; i<scheduleDisplayList.size(); i++) { 
                        String scheduleDate = scheduleDisplayList.get(i).getDepartureDate() + " " + scheduleDisplayList.get(i).getDepartureTime();
                        Date sDate = new Date();
                        try{
                            sDate = format.parse(scheduleDate);
                        } catch(Exception ex) {
                            System.out.println(ex);
                        }
                        sDate = new Date(sDate.getTime()-(30*60000));

                        if(!sDate.before(new Date())) {
                            counter++;
                            ArrayList<Ticket> ticketList = tControl.selectRecordBySchedule(scheduleDisplayList.get(i));
                            int seatCount = 0;
                            for(int j=0; j<ticketList.size(); j++) {
                                for(int k=0; k<scheduleDisplayList.get(i).getBus().getBusType().getCapacity(); k++) {
                                    if(ticketList.get(j).getSeatNo().equals(Integer.toString(k+1))) {
                                        if(ticketList.get(j).getStatus()=='A')
                                            seatCount++;
                                    }
                                }
                            }
                            if(seatCount!=scheduleDisplayList.get(i).getBus().getBusType().getCapacity()) {
                                if(scheduleDisplayList.get(i).getStatus()=='A') {
                                    scheduleListM.addElement(String.format("%02d", counter) + ".) Time(24HR) : " + scheduleDisplayList.get(i).getDepartureTime().substring(0, 5) + " - Seat : (" + seatCount + "/" + scheduleDisplayList.get(i).getBus().getBusType().getCapacity() + ") - RM" + String.format("%.2f", scheduleDisplayList.get(i).getRoute().getPrice().getPrice()));
                                    ssList.add(scheduleDisplayList.get(i));
                                }
                            } else {
                                jtfErrorMsg.setText("No available schedule found.");
                                jtfErrorMsg.setForeground(Color.red);
                            }                                
                        }
                    }
                    if(ssList.isEmpty()) {
                        jtfErrorMsg.setText("No available schedule found.");
                        jtfErrorMsg.setForeground(Color.red);
                    }
                    scheduleList = ssList;
                } else {
                    jtfErrorMsg.setText("No schedule found.");
                    jtfErrorMsg.setForeground(Color.red);
                }
            }
        });   
        
        jbtReset.addActionListener((ActionEvent e) -> {
            jcbTo.setSelectedIndex(0);
            jdcDate.setCalendar(null);
            scheduleListM.removeAllElements();
            jtfErrorMsg.setText(" ");
        });
        
        jlSchedule.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList)evt.getSource();
                int index = list.locationToIndex(evt.getPoint());
                
                if(evt.getClickCount() == 1) {
                    if(jlSchedule.getSelectedValue()!=null) { 
                        Schedule schedule = scheduleList.get(index);                                              
                        if(schedule.getBus().getBusType().getBusType().equals("Type A")) {
                            menuPanel.removeAll();
                            menuPanel.revalidate();
                            menuPanel.repaint();                          
                            menuPanel.add(busA(schedule), BorderLayout.CENTER);                       
                        } else if(schedule.getBus().getBusType().getBusType().equals("Type B")) {
                            menuPanel.removeAll();
                            menuPanel.revalidate();
                            menuPanel.repaint();                           
                            menuPanel.add(busB(schedule), BorderLayout.CENTER);                       
                        } else if(schedule.getBus().getBusType().getBusType().equals("Double Decker")) {
                            menuPanel.removeAll();
                            menuPanel.revalidate();
                            menuPanel.repaint();        
                            menuPanel.add(busDD(schedule), BorderLayout.CENTER);
                        }
                    }
                }
            }
        });     
        
        jbtBack.addActionListener((ActionEvent e) -> {
            if(editBooking==false) {
                menuPanel.removeAll();
                menuPanel.revalidate();
                menuPanel.repaint();
                menuPanel.add(msg);
                menuPanel.add(menuPane, BorderLayout.CENTER);
            } else {
                menuPanel.removeAll();
                menuPanel.revalidate();
                menuPanel.repaint();
                menuPanel.add(editBookingPanel(this.editB, this.editT));               
            }
        });
        
        ticketing.setPreferredSize(new Dimension(740, 640));

        return ticketing;
    }
    
    public JPanel busA(Schedule schedule) {
        
        JPanel busA = new JPanel();
        JToggleButton jToggleButton1 = new JToggleButton("1");
        JToggleButton jToggleButton2 = new JToggleButton("2");
        JToggleButton jToggleButton3 = new JToggleButton("3");
        JToggleButton jToggleButton4 = new JToggleButton("4");
        JToggleButton jToggleButton5 = new JToggleButton("5");
        JToggleButton jToggleButton6 = new JToggleButton("6");
        JToggleButton jToggleButton7 = new JToggleButton("7");
        JToggleButton jToggleButton8 = new JToggleButton("8");
        JToggleButton jToggleButton9 = new JToggleButton("9");
        JToggleButton jToggleButton10 = new JToggleButton("10");
        JToggleButton jToggleButton11 = new JToggleButton("11");
        JToggleButton jToggleButton12 = new JToggleButton("12");
        JToggleButton jToggleButton13 = new JToggleButton("13");
        JToggleButton jToggleButton14 = new JToggleButton("14");
        JToggleButton jToggleButton15 = new JToggleButton("15");
        JToggleButton jToggleButton16 = new JToggleButton("16");
        JToggleButton jToggleButton17 = new JToggleButton("17");
        JToggleButton jToggleButton18 = new JToggleButton("18");
        JToggleButton jToggleButton19 = new JToggleButton("19");
        JToggleButton jToggleButton20 = new JToggleButton("20");
        JToggleButton jToggleButton21 = new JToggleButton("21");
        JToggleButton jToggleButton22 = new JToggleButton("22");
        JToggleButton jToggleButton23 = new JToggleButton("23");
        JToggleButton jToggleButton24 = new JToggleButton("24");
        JToggleButton jToggleButton25 = new JToggleButton("25");
        JToggleButton jToggleButton26 = new JToggleButton("26");
        JToggleButton jToggleButton27 = new JToggleButton("27");
        JToggleButton jToggleButton28 = new JToggleButton("28");
        JToggleButton jToggleButton29 = new JToggleButton("29");
        JToggleButton jToggleButton30 = new JToggleButton("30");
        JToggleButton[] jtb = new JToggleButton[]{
        jToggleButton1, jToggleButton2, jToggleButton3, jToggleButton4, jToggleButton5, jToggleButton6,
        jToggleButton7, jToggleButton8, jToggleButton9, jToggleButton10, jToggleButton11, jToggleButton12,
        jToggleButton13, jToggleButton14, jToggleButton15, jToggleButton16, jToggleButton17, jToggleButton18,
        jToggleButton19, jToggleButton20, jToggleButton21, jToggleButton22, jToggleButton23, jToggleButton24, 
        jToggleButton25, jToggleButton26, jToggleButton27, jToggleButton28, jToggleButton29, jToggleButton30};
        JLabel jLabel1 = new JLabel("Seat Panel");
        JButton jButton1 = new JButton("Driver");
        JLabel jtfErrorMsg = new JLabel(" ");
        JButton jbtSubmit = new JButton("Submit");
        JButton jbtBack = new JButton("Back");
        ImageIcon icon = new ImageIcon(getClass().getResource("/images/seat.png"));
        Font font = new Font("Lucida Fax", 1, 20);

        busA.setBackground(new Color(102, 102, 102));
        busA.setBorder(new LineBorder(new Color(0, 0, 0), 5, true));
        busA.setMaximumSize(new Dimension(320, 628));
        busA.setMinimumSize(new Dimension(320, 628));
        busA.setPreferredSize(new Dimension(320, 628));

        jLabel1.setFont(new Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel1.setForeground(new Color(255, 255, 255));
        jLabel1.setVerticalAlignment(SwingConstants.BOTTOM);
        jButton1.setEnabled(false);
               
        for(int i=0; i<jtb.length; i++) {
            jtb[i].addItemListener(new ItemListener() {
               @Override
               public void itemStateChanged(ItemEvent ev) {
                  if(ev.getStateChange()==ItemEvent.SELECTED){
                      jtfErrorMsg.setText(" ");
                  } 
               }
            });
            jtb[i].setFont(font);
            jtb[i].setIcon(icon);
            jtb[i].setVerticalTextPosition(SwingConstants.CENTER);
            jtb[i].setHorizontalTextPosition(SwingConstants.CENTER);
        }
        
        ArrayList<Ticket> ticketList = tControl.selectRecordBySchedule(schedule);
        for(int i=0; i<jtb.length; i++) {
            jtb[i].setEnabled(true);
        }
        
        for(int i=0; i<ticketList.size(); i++) {
            for(int j=0; j<jtb.length; j++) {
                if(ticketList.get(i).getSeatNo().equals(Integer.toString(j+1))) {
                    if(ticketList.get(i).getStatus()=='A') {
                        jtb[j].setEnabled(false);
                        jtb[j].setBackground(Color.WHITE);
                    }
                }
            }
        }
        
        for(int i=0; i<jtb.length; i++) {
            if(!this.countList.isEmpty()) {
                for(int j=0; j<this.countList.size(); j++) {
                    if(this.countList.get(j)==(i+1)) {
                        jtb[i].setSelected(true);
                    }
                }
            }
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(busA);
        busA.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jtfErrorMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jToggleButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jToggleButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jToggleButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jToggleButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jToggleButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jToggleButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jToggleButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jToggleButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jToggleButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jToggleButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jToggleButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jToggleButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jToggleButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jToggleButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jToggleButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jToggleButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jToggleButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jToggleButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jToggleButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jToggleButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jToggleButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jToggleButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jToggleButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jToggleButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jToggleButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jToggleButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jToggleButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jToggleButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jbtSubmit)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jbtBack, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)))))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jToggleButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jToggleButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jToggleButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfErrorMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtSubmit)
                    .addComponent(jbtBack))
                .addGap(28, 28, 28))
        );  
        
        jbtSubmit.addActionListener((ActionEvent e) -> {
            int count = 0;
            int counter = 0;
            countList.clear();
            for(int i=0; i<jtb.length; i++) {
                if(jtb[i].isSelected()) {
                    countList.add(i+1);
                    counter++;
                } else {
                    count++;
                }
            }
            if(count==jtb.length) {
                jtfErrorMsg.setText("Please select a seat.");
                jtfErrorMsg.setForeground(Color.red);
            } else {
                if(editBooking==false) {
                    menuPanel.removeAll();
                    menuPanel.revalidate();
                    menuPanel.repaint();
                    menuPanel.add(psgForm(countList, schedule), BorderLayout.CENTER);
                } else {
                    if(counter!=this.editTT.size()) {
                        jtfErrorMsg.setText("Please select " + this.editTT.size() + " seat.");
                        jtfErrorMsg.setForeground(Color.red);
                    } else {
                        menuPanel.removeAll();
                        menuPanel.revalidate();
                        menuPanel.repaint();
                        menuPanel.add(psgForm(countList, schedule), BorderLayout.CENTER);
                    }
                }
            }
        });
        
        jbtBack.addActionListener((ActionEvent e) -> {
            this.countList.clear();
            menuPanel.removeAll();
            menuPanel.revalidate();
            menuPanel.repaint();
            menuPanel.add(ticketing(), BorderLayout.CENTER);
        });       
        
        return busA;
    }
    
    public JPanel busB(Schedule schedule) {
        
        JPanel busB = new JPanel();
        JToggleButton jToggleButton1 = new javax.swing.JToggleButton("1");
        JToggleButton jToggleButton2 = new javax.swing.JToggleButton("2");
        JToggleButton jToggleButton3 = new javax.swing.JToggleButton("3");
        JToggleButton jToggleButton4 = new javax.swing.JToggleButton("4");
        JToggleButton jToggleButton5 = new javax.swing.JToggleButton("5");
        JToggleButton jToggleButton6 = new javax.swing.JToggleButton("6");
        JToggleButton jToggleButton7 = new javax.swing.JToggleButton("7");
        JToggleButton jToggleButton8 = new javax.swing.JToggleButton("8");
        JToggleButton jToggleButton9 = new javax.swing.JToggleButton("9");
        JToggleButton jToggleButton10 = new javax.swing.JToggleButton("10");
        JToggleButton jToggleButton11 = new javax.swing.JToggleButton("11");
        JToggleButton jToggleButton12 = new javax.swing.JToggleButton("12");
        JToggleButton jToggleButton13 = new javax.swing.JToggleButton("13");
        JToggleButton jToggleButton14 = new javax.swing.JToggleButton("14");
        JToggleButton jToggleButton15 = new javax.swing.JToggleButton("15");
        JToggleButton jToggleButton16 = new javax.swing.JToggleButton("16");
        JToggleButton jToggleButton17 = new javax.swing.JToggleButton("17");
        JToggleButton jToggleButton18 = new javax.swing.JToggleButton("18");
        JToggleButton jToggleButton19 = new javax.swing.JToggleButton("19");
        JToggleButton jToggleButton20 = new javax.swing.JToggleButton("20");
        JToggleButton jToggleButton21 = new javax.swing.JToggleButton("21");
        JToggleButton jToggleButton22 = new javax.swing.JToggleButton("22");
        JToggleButton jToggleButton23 = new javax.swing.JToggleButton("23");
        JToggleButton jToggleButton24 = new javax.swing.JToggleButton("24");
        JToggleButton jToggleButton25 = new javax.swing.JToggleButton("25");
        JToggleButton jToggleButton26 = new javax.swing.JToggleButton("26");
        JToggleButton jToggleButton27 = new javax.swing.JToggleButton("27");
        JToggleButton jToggleButton28 = new javax.swing.JToggleButton("28");
        JToggleButton jToggleButton29 = new javax.swing.JToggleButton("29");
        JToggleButton jToggleButton30 = new javax.swing.JToggleButton("30");
        JToggleButton jToggleButton31 = new javax.swing.JToggleButton("31");
        JToggleButton jToggleButton32 = new javax.swing.JToggleButton("32");
        JToggleButton jToggleButton33 = new javax.swing.JToggleButton("33");
        JToggleButton jToggleButton34 = new javax.swing.JToggleButton("34");
        JToggleButton jToggleButton35 = new javax.swing.JToggleButton("35");
        JToggleButton jToggleButton36 = new javax.swing.JToggleButton("36");
        JToggleButton jToggleButton37 = new javax.swing.JToggleButton("37");
        JToggleButton jToggleButton38 = new javax.swing.JToggleButton("38");
        JToggleButton jToggleButton39 = new javax.swing.JToggleButton("39");
        JToggleButton jToggleButton40 = new javax.swing.JToggleButton("40");
        JToggleButton[] jtb = new JToggleButton[]{
        jToggleButton1, jToggleButton2, jToggleButton3, jToggleButton4, jToggleButton5, jToggleButton6,
        jToggleButton7, jToggleButton8, jToggleButton9, jToggleButton10, jToggleButton11, jToggleButton12,
        jToggleButton13, jToggleButton14, jToggleButton15, jToggleButton16, jToggleButton17, jToggleButton18,
        jToggleButton19, jToggleButton20, jToggleButton21, jToggleButton22, jToggleButton23, jToggleButton24, 
        jToggleButton25, jToggleButton26, jToggleButton27, jToggleButton28, jToggleButton29, jToggleButton30,
        jToggleButton31, jToggleButton32, jToggleButton33, jToggleButton34, jToggleButton35, jToggleButton36,
        jToggleButton37, jToggleButton38, jToggleButton39, jToggleButton40};
        JLabel jLabel1 = new javax.swing.JLabel("Seat Panel");
        JButton jButton1 = new javax.swing.JButton("Driver");
        JLabel jlErrorMsg = new javax.swing.JLabel(" ");
        JButton jbtSubmit = new javax.swing.JButton("Submit");
        JButton jbtBack = new javax.swing.JButton("Back");

        busB.setBackground(new java.awt.Color(102, 102, 102));
        busB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 5, true));
        busB.setMaximumSize(new java.awt.Dimension(400, 628));
        busB.setMinimumSize(new java.awt.Dimension(400, 628));
        busB.setPreferredSize(new java.awt.Dimension(400, 628));

        jLabel1.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(busB);
        busB.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jlErrorMsg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jToggleButton37, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToggleButton38, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jToggleButton39, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToggleButton40, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jToggleButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToggleButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jToggleButton35, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToggleButton36, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jToggleButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToggleButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jToggleButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToggleButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jToggleButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToggleButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jToggleButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToggleButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jToggleButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToggleButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jToggleButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToggleButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jToggleButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToggleButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jToggleButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToggleButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jToggleButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToggleButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jToggleButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToggleButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jToggleButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToggleButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jToggleButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToggleButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jToggleButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToggleButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jToggleButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToggleButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jToggleButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToggleButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(jbtSubmit)
                        .addGap(18, 18, 18)
                        .addComponent(jbtBack, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton35, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton36, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton37, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton38, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton39, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton40, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlErrorMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtSubmit)
                    .addComponent(jbtBack))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        
        ImageIcon icon = new ImageIcon(getClass().getResource("/images/seat.png"));
        Font font = new Font("Lucida Fax", 1, 20);
        
        for(int i=0; i<jtb.length; i++) {                               
            jtb[i].addItemListener(new ItemListener() {
               @Override
               public void itemStateChanged(ItemEvent ev) {
                  if(ev.getStateChange()==ItemEvent.SELECTED){
                      jlErrorMsg.setText(" ");
                  } 
               }
            });
            jtb[i].setFont(font);
            jtb[i].setIcon(icon);
            jtb[i].setVerticalTextPosition(SwingConstants.CENTER);
            jtb[i].setHorizontalTextPosition(SwingConstants.CENTER);
        }
                    
        ArrayList<Ticket> ticketList = tControl.selectRecordBySchedule(schedule);
        for(int i=0; i<jtb.length; i++) {
            jtb[i].setEnabled(true);
        }
        for(int i=0; i<ticketList.size(); i++) {
            for(int j=0; j<jtb.length; j++) {
                if(ticketList.get(i).getSeatNo().equals(Integer.toString(j+1))) {
                    if(ticketList.get(i).getStatus()=='A') {
                        jtb[j].setEnabled(false);
                        jtb[j].setBackground(Color.WHITE);
                    }
                }
            }
        }
        
        for(int i=0; i<jtb.length; i++) {
            if(!this.countList.isEmpty()) {
                for(int j=0; j<this.countList.size(); j++) {
                    if(this.countList.get(j)==(i+1)) {
                        jtb[i].setSelected(true);
                    }
                }
            }
        }
        
        jbtSubmit.addActionListener((ActionEvent e) -> {
            int count = 0;
            int counter = 0;
            countList.clear();
            for(int i=0; i<jtb.length; i++) {
                if(jtb[i].isSelected()) {
                    countList.add(i+1);
                    counter++;
                } else {
                    count++;
                }
            }
            if(count==jtb.length) {
                jlErrorMsg.setText("Please select a seat.");
                jlErrorMsg.setForeground(Color.red);
            } else {
                if(editBooking==false) {
                    menuPanel.removeAll();
                    menuPanel.revalidate();
                    menuPanel.repaint();
                    menuPanel.add(psgForm(countList, schedule), BorderLayout.CENTER);
                } else {
                    if(counter!=this.editTT.size()) {
                        jlErrorMsg.setText("Please select " + this.editTT.size() + " seat.");
                        jlErrorMsg.setForeground(Color.red);
                    } else {
                        menuPanel.removeAll();
                        menuPanel.revalidate();
                        menuPanel.repaint();
                        menuPanel.add(psgForm(countList, schedule), BorderLayout.CENTER);
                    }
                }
            }
        });
        
        jbtBack.addActionListener((ActionEvent e) -> {
            this.countList.clear();
            menuPanel.removeAll();
            menuPanel.revalidate();
            menuPanel.repaint();
            menuPanel.add(ticketing(), BorderLayout.CENTER);
        });       
        
        return busB;
    }
    
    public JPanel busDD(Schedule schedule) {
        
        JPanel busDD = new JPanel();
        JToggleButton jToggleButton1 = new javax.swing.JToggleButton("1");
        JToggleButton jToggleButton2 = new javax.swing.JToggleButton("2");
        JToggleButton jToggleButton3 = new javax.swing.JToggleButton("3");
        JToggleButton jToggleButton4 = new javax.swing.JToggleButton("4");
        JToggleButton jToggleButton5 = new javax.swing.JToggleButton("5");
        JToggleButton jToggleButton6 = new javax.swing.JToggleButton("6");
        JToggleButton jToggleButton7 = new javax.swing.JToggleButton("7");
        JToggleButton jToggleButton8 = new javax.swing.JToggleButton("8");
        JToggleButton jToggleButton9 = new javax.swing.JToggleButton("9");
        JToggleButton jToggleButton10 = new javax.swing.JToggleButton("10");
        JToggleButton jToggleButton11 = new javax.swing.JToggleButton("11");
        JToggleButton jToggleButton12 = new javax.swing.JToggleButton("12");
        JToggleButton jToggleButton13 = new javax.swing.JToggleButton("13");
        JToggleButton jToggleButton14 = new javax.swing.JToggleButton("14");
        JToggleButton jToggleButton15 = new javax.swing.JToggleButton("15");
        JToggleButton jToggleButton16 = new javax.swing.JToggleButton("16");
        JToggleButton jToggleButton17 = new javax.swing.JToggleButton("17");
        JToggleButton jToggleButton18 = new javax.swing.JToggleButton("18");
        JToggleButton jToggleButton19 = new javax.swing.JToggleButton("19");
        JToggleButton jToggleButton20 = new javax.swing.JToggleButton("20");
        JToggleButton jToggleButton21 = new javax.swing.JToggleButton("21");
        JToggleButton jToggleButton22 = new javax.swing.JToggleButton("22");
        JToggleButton jToggleButton23 = new javax.swing.JToggleButton("23");
        JToggleButton jToggleButton24 = new javax.swing.JToggleButton("24");
        JToggleButton jToggleButton25 = new javax.swing.JToggleButton("25");
        JToggleButton jToggleButton26 = new javax.swing.JToggleButton("26");
        JToggleButton jToggleButton27 = new javax.swing.JToggleButton("27");
        JToggleButton jToggleButton28 = new javax.swing.JToggleButton("28");
        JToggleButton jToggleButton29 = new javax.swing.JToggleButton("29");
        JToggleButton jToggleButton30 = new javax.swing.JToggleButton("30");
        JToggleButton jToggleButton31 = new javax.swing.JToggleButton("31");
        JToggleButton jToggleButton32 = new javax.swing.JToggleButton("32");
        JToggleButton jToggleButton33 = new javax.swing.JToggleButton("33");
        JToggleButton jToggleButton34 = new javax.swing.JToggleButton("34");
        JToggleButton jToggleButton35 = new javax.swing.JToggleButton("35");
        JToggleButton jToggleButton36 = new javax.swing.JToggleButton("36");
        JToggleButton jToggleButton37 = new javax.swing.JToggleButton("37");
        JToggleButton jToggleButton38 = new javax.swing.JToggleButton("38");
        JToggleButton jToggleButton39 = new javax.swing.JToggleButton("39");
        JToggleButton jToggleButton40 = new javax.swing.JToggleButton("40");
        JToggleButton[] jtb = new JToggleButton[]{
        jToggleButton1, jToggleButton2, jToggleButton3, jToggleButton4, jToggleButton5, jToggleButton6,
        jToggleButton7, jToggleButton8, jToggleButton9, jToggleButton10, jToggleButton11, jToggleButton12,
        jToggleButton13, jToggleButton14, jToggleButton15, jToggleButton16, jToggleButton17, jToggleButton18,
        jToggleButton19, jToggleButton20, jToggleButton21, jToggleButton22, jToggleButton23, jToggleButton24, 
        jToggleButton25, jToggleButton26, jToggleButton27, jToggleButton28, jToggleButton29, jToggleButton30,
        jToggleButton31, jToggleButton32, jToggleButton33, jToggleButton34, jToggleButton35, jToggleButton36,
        jToggleButton37, jToggleButton38, jToggleButton39, jToggleButton40};
        JLabel jLabel1 = new javax.swing.JLabel("Seat Panel");
        JButton jButton1 = new javax.swing.JButton("Driver");
        JLabel jlErrorMsg = new javax.swing.JLabel(" ");
        JButton jbtSubmit = new javax.swing.JButton("Submit");
        JButton jbtBack = new javax.swing.JButton("Back");
        ImageIcon icon = new ImageIcon(getClass().getResource("/images/seat.png"));
        Font font = new Font("Lucida Fax", 1, 20);
        JLabel jLabel2 = new javax.swing.JLabel("1st Floor");
        JLabel jLabel3 = new javax.swing.JLabel("2nd Floor");

        busDD.setBackground(new java.awt.Color(102, 102, 102));
        busDD.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 5, true));
        busDD.setMaximumSize(new java.awt.Dimension(400, 628));
        busDD.setMinimumSize(new java.awt.Dimension(400, 628));
        busDD.setPreferredSize(new java.awt.Dimension(400, 628));
  
        jLabel1.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));      
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(busDD);
        busDD.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(jbtSubmit)
                        .addGap(18, 18, 18)
                        .addComponent(jbtBack, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jlErrorMsg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jToggleButton37, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToggleButton38, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jToggleButton39, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToggleButton40, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jToggleButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToggleButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jToggleButton35, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToggleButton36, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jToggleButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToggleButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jToggleButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToggleButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jToggleButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToggleButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jToggleButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToggleButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jToggleButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToggleButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jToggleButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToggleButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jToggleButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToggleButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jToggleButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToggleButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jToggleButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToggleButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jToggleButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToggleButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jToggleButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToggleButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jToggleButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToggleButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jToggleButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToggleButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jToggleButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToggleButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jToggleButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToggleButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(120, 120, 120)
                                .addComponent(jLabel2))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addComponent(jLabel3)))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(2, 2, 2)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton35, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton36, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton37, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton38, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton39, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton40, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlErrorMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtSubmit)
                    .addComponent(jbtBack))
                .addContainerGap())
        );
    
        for(int i=0; i<jtb.length; i++) {                               
            jtb[i].addItemListener(new ItemListener() {
               @Override
               public void itemStateChanged(ItemEvent ev) {
                  if(ev.getStateChange()==ItemEvent.SELECTED){
                      jlErrorMsg.setText(" ");
                  } 
               }
            });
            jtb[i].setFont(font);
            jtb[i].setIcon(icon);
            jtb[i].setVerticalTextPosition(SwingConstants.CENTER);
            jtb[i].setHorizontalTextPosition(SwingConstants.CENTER);
        }
                    
        ArrayList<Ticket> ticketList = tControl.selectRecordBySchedule(schedule);
        for(int i=0; i<jtb.length; i++) {
            jtb[i].setEnabled(true);
        }
        for(int i=0; i<ticketList.size(); i++) {
            for(int j=0; j<jtb.length; j++) {
                if(ticketList.get(i).getSeatNo().equals(Integer.toString(j+1))) {
                    if(ticketList.get(i).getStatus()=='A') {
                        jtb[j].setEnabled(false);
                        jtb[j].setBackground(Color.WHITE);
                    }
                }
            }
        }
        
        for(int i=0; i<jtb.length; i++) {
            if(!this.countList.isEmpty()) {
                for(int j=0; j<this.countList.size(); j++) {
                    if(this.countList.get(j)==(i+1)) {
                        jtb[i].setSelected(true);
                    }
                }
            }
        }
        
        jbtSubmit.addActionListener((ActionEvent e) -> {
            int count = 0;
            int counter = 0;
            countList.clear();
            for(int i=0; i<jtb.length; i++) {
                if(jtb[i].isSelected()) {
                    countList.add(i+1);
                    counter++;
                } else {
                    count++;
                }
            }
            if(count==jtb.length) {
                jlErrorMsg.setText("Please select a seat.");
                jlErrorMsg.setForeground(Color.red);
            } else {
                if(editBooking==false) {
                    menuPanel.removeAll();
                    menuPanel.revalidate();
                    menuPanel.repaint();
                    menuPanel.add(psgForm(countList, schedule), BorderLayout.CENTER);
                } else {
                    if(counter!=this.editTT.size()) {
                        jlErrorMsg.setText("Please select " + this.editTT.size() + " seat.");
                        jlErrorMsg.setForeground(Color.red);
                    } else {
                        menuPanel.removeAll();
                        menuPanel.revalidate();
                        menuPanel.repaint();
                        menuPanel.add(psgForm(countList, schedule), BorderLayout.CENTER);
                    }
                }
            }
        });
        
        jbtBack.addActionListener((ActionEvent e) -> {
            this.countList.clear();
            menuPanel.removeAll();
            menuPanel.revalidate();
            menuPanel.repaint();
            menuPanel.add(ticketing(), BorderLayout.CENTER);
        });       
        
        return busDD;
    }
    
    public JPanel psgForm(ArrayList<Integer> countList, Schedule schedule) {
        
        JPanel payment = new JPanel();
        if(countList.size()>0) {            
            payment.setPreferredSize(new Dimension(740, 640));
            payment.setBackground(Color.LIGHT_GRAY);
            JPanel psgPanel = new JPanel(new GridBagLayout());
            psgPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 3), "Passengers' Information", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Serif", Font.BOLD, 20), Color.RED));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(5,5,5,5);
            JLabel[] seatLabel = new JLabel[countList.size()];
            JLabel[] psgName = new JLabel[countList.size()];
            JTextField[] psgNameField = new JTextField[countList.size()];
            JLabel[] psgContact = new JLabel[countList.size()];
            JTextField[] psgContactField = new JTextField[countList.size()];
            
            int gridy = -1;
            for(int i=0; i<psgName.length; i++) {
                seatLabel[i] = new JLabel((i+1) + ".) Seat No: " + countList.get(i).toString());
                psgName[i] = new JLabel("      Name");
                psgNameField[i] = new JTextField(20);
                psgContact[i] = new JLabel("      Contact No");
                psgContactField[i] = new JTextField(20);
                
                gbc.gridx = 0;
                gbc.gridy = ++gridy;
                psgPanel.add(seatLabel[i], gbc);
                gbc.gridx = 0;
                gbc.gridy = ++gridy;
                psgPanel.add(psgName[i], gbc);
                gbc.gridx = 1;
                gbc.gridy = gridy;
                psgPanel.add(psgNameField[i], gbc);
                gbc.gridx = 0;
                gbc.gridy = ++gridy;
                psgPanel.add(psgContact[i], gbc);
                gbc.gridx = 1;
                gbc.gridy = gridy;
                psgPanel.add(psgContactField[i], gbc);
            }
            
            if(!ticketList.isEmpty()) {
                for(int i=0; i<ticketList.size(); i++) {
                    psgNameField[i].setText(ticketList.get(i).getPassengerName());
                    psgContactField[i].setText(ticketList.get(i).getPassengerContactNo());
                }
            }
            
            JPanel belowPanel = new JPanel(new BorderLayout());
            belowPanel.setPreferredSize(new Dimension(250, 50));
            belowPanel.setBackground(Color.LIGHT_GRAY);
            GridLayout glay = new GridLayout(1, 2);
            JPanel buttonPanel = new JPanel(glay);     
            glay.setHgap(90);
            buttonPanel.setBackground(Color.LIGHT_GRAY);
            JLabel jlErrorMsg = new JLabel(" ");
            jlErrorMsg.setPreferredSize(new Dimension(250, 20));
            JButton jbtBack = new JButton("Back");
            jbtBack.addActionListener((ActionEvent e) -> {  
                this.ticketList.clear();
                menuPanel.removeAll();
                menuPanel.revalidate();
                menuPanel.repaint();
                if(schedule.getBus().getBusType().getBusType().equals("Type A")) {
                    menuPanel.add(busA(schedule), BorderLayout.CENTER);    
                } else if(schedule.getBus().getBusType().getBusType().equals("Type B")) {
                    menuPanel.add(busB(schedule), BorderLayout.CENTER); 
                } else if(schedule.getBus().getBusType().getBusType().equals("Double Decker")) {
                    menuPanel.add(busDD(schedule), BorderLayout.CENTER); 
                }                 
            });
            
            if(editBooking==true) {
                for(int i=0; i<editTT.size(); i++) {
                    psgNameField[i].setText(editTT.get(i).getPassengerName());
                    psgContactField[i].setText(editTT.get(i).getPassengerContactNo());
                }
            }
            
            JButton jbtSubmit = new JButton("Submit");           
            jbtSubmit.addActionListener((ActionEvent e) -> {
                int errorCount = 0;
                ArrayList<Ticket> psgList = new ArrayList<>();
                for(int i=0; i<countList.size(); i++) {
                    if(psgNameField[i].getText().equals("")||psgContactField[i].getText().equals("")) {
                        jlErrorMsg.setText("Please fill in all the information required.");
                        jlErrorMsg.setForeground(Color.red);
                        errorCount++;
                    } 
                }
                if(errorCount==0) {                   
                    for(int i=0; i<countList.size(); i++) {
                        if(psgNameField[i].getText().length()>30) {
                            jlErrorMsg.setText("Name not more than 30 words)");
                            jlErrorMsg.setForeground(Color.red);
                            errorCount++;
                        } else if(!psgContactField[i].getText().matches("01[\\d]{1}-[\\d]{7,8}")) {
                            jlErrorMsg.setText("Invalid contact number format");
                            jlErrorMsg.setForeground(Color.red);
                            errorCount++;
                        }                        
                    }
                    if(errorCount==0) {
                        jlErrorMsg.setText(" ");
                        jlErrorMsg.setForeground(Color.blue);                      
                        String ticketNo;
                        if(tControl.selectLatestRecord()==null) {
                            ticketNo = "TIC000001";
                        } else {
                            Ticket latestTic = tControl.selectLatestRecord();
                            ticketNo = latestTic.getTicketID().substring(0, 3) + String.format("%06d", Integer.parseInt(latestTic.getTicketID().substring(3, 9))+1);
                        }                          
                        
                        for(int i=0; i<countList.size(); i++) {                           
                            psgList.add(new Ticket(ticketNo, schedule, countList.get(i).toString(), psgNameField[i].getText(), psgContactField[i].getText()));
                            ticketNo = ticketNo.substring(0, 3) + String.format("%06d", Integer.parseInt(ticketNo.substring(3, 9))+1);                                                   
                        }
                        this.ticketList = psgList;
                        menuPanel.removeAll();;
                        menuPanel.revalidate();
                        menuPanel.repaint();
                        if(editBooking==false) {
                            menuPanel.add(paymentPanel(psgList), BorderLayout.CENTER);
                        } else {
                            menuPanel.add(updateTicketPanel(), BorderLayout.CENTER);
                        }
                    }
                } 
            });
            
            DocumentListener fillInfo = new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    jlErrorMsg.setText(" ");
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    jlErrorMsg.setText(" ");
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }           
            };
            
            for(int i=0; i<countList.size(); i++) {
                psgNameField[i].getDocument().addDocumentListener(fillInfo);
                psgContactField[i].getDocument().addDocumentListener(fillInfo);
            }
            
            buttonPanel.add(jbtSubmit);
            buttonPanel.add(jbtBack);
            belowPanel.add(jlErrorMsg, BorderLayout.NORTH);
            belowPanel.add(buttonPanel, BorderLayout.SOUTH);
            
            JScrollPane jsPane = new JScrollPane(psgPanel); 
            jsPane.setPreferredSize(new Dimension(730, 550));
            this.getContentPane().add(jsPane);
            payment.add(jsPane, BorderLayout.CENTER);
            payment.add(belowPanel, BorderLayout.SOUTH);          
        }
        
        return payment;
    }
    
    public JPanel paymentPanel(ArrayList<Ticket> psgList) {
  
        JPanel paymentPanel = new JPanel();
        ButtonGroup buttonGroup1 = new ButtonGroup();
        ButtonGroup buttonGroup2 = new ButtonGroup();
        JLabel jLabel1 = new JLabel("Payment Panel");
        JLabel jlBookingID = new JLabel("Booking ID");
        JTextField jtfBookingID = new JTextField();            
        JScrollPane jScrollPane1 = new JScrollPane();
        JTable table = new JTable();
        JLabel jlScheduleID = new JLabel("Schedule ID");
        JTextField jtfScheduleID = new JTextField();
        JLabel jlCustName = new JLabel("Customer Name");
        JTextField jtfCustName = new JTextField();
        JLabel jlCustContact = new JLabel("Customer Contact No");
        JTextField jtfCustContact = new JTextField();
        JLabel jlCustIC = new JLabel("Customer IC No");
        JTextField jtfCustIC = new JTextField();
        JLabel jlError1 = new JLabel(" ");
        JButton jbtSubmit1 = new JButton("Submit");
        JPanel paymentDetailsPanel = new JPanel();
        JLabel jlTotalAmt = new JLabel("Total Amount (RM)");
        JLabel jlGST = new JLabel("GST (6%)");
        JLabel jlDisc = new JLabel("Discount");
        JLabel jlNett = new JLabel("Nett Amount (RM)");
        JButton jbtBack = new JButton("Back");
        JRadioButton jrbMemberDisc = new JRadioButton("Membership Discount (10%)");
        JRadioButton jrbMemberPoint = new JRadioButton("Use Points: ");
        JRadioButton jrbCC = new JRadioButton("Credit Card");
        JRadioButton jrbCash = new JRadioButton("Cash");
        JLabel jlError2 = new JLabel(" ");
        JButton jbtSubmit2 = new JButton("Submit");
        JButton jbtPrintT = new JButton("Tickets");
        JButton jbtPrintR = new JButton("Receipt");
        JLabel jlPaid = new JLabel("Paid Amount (RM)");      
        JLabel jlMemberID = new JLabel("Member ID");
        JTextField jtfMemberID = new JTextField();
        JLabel jlPoint = new JLabel("Points");
        JTextField jtfPoints = new JTextField();
        JButton jbtCancel = new JButton("Cancel");  
        JLabel jlChanges = new JLabel("Changes (RM)");
        JButton jbtReturn = new JButton("Return to Ticketing");
        
        jtfChanges.setEditable(false);
        jtfChanges.setText("");
        jtfPaid.setEditable(false);
        jtfPaid.setText("");
        jtfMemberID.setEditable(false);
        jtfPoints.setEditable(false);
        jtfTotalAmt.setEditable(false);      
        jtfGST.setEditable(false);        
        jtfDisc.setEditable(false);        
        jtfNett.setEditable(false);         
        jtfScheduleID.setEditable(false);
        jrbMemberDisc.setEnabled(false);
        jrbMemberPoint.setEnabled(false);
        jrbCC.setEnabled(false);
        jtfCC.setEnabled(false);
        jtfCC.setText("");
        jtfCC.setEditable(true);
        jrbCash.setEnabled(false);
        jtfCash.setEnabled(false);
        jtfCash.setText("");
        jtfCash.setEditable(true);
        jbtSubmit2.setEnabled(false);
        //jbtCheck.setEnabled(false);
        jbtPrintR.setEnabled(false);
        jbtPrintT.setEnabled(false);
        jlMemberID.setEnabled(false);
        jtfMemberID.setEnabled(false);
        jlPoint.setEnabled(false);
        jtfPoints.setEnabled(false);
        
        paymentPanel.setBackground(Color.LIGHT_GRAY);
        jLabel1.setFont(new Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);      
        jLabel1.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));       
        jtfBookingID.setEditable(false);       
        table.setAutoCreateRowSorter(true);
        table.setModel(new DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ticket No", "Name", "Contact No", "Seat No", "Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setPreferredWidth(50);
            table.getColumnModel().getColumn(3).setPreferredWidth(25);
        }
        
        paymentDetailsPanel.setBackground(new Color(153, 153, 153));
        paymentDetailsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(new LineBorder(new Color(255, 255, 255), 1, true), "Payment Details", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18))); // NOI18N     
        buttonGroup1.add(jrbMemberDisc);
        buttonGroup1.add(jrbMemberPoint);
        buttonGroup2.add(jrbCC);
        buttonGroup2.add(jrbCash);
                                 
        javax.swing.GroupLayout paymentDetailsPanelLayout = new javax.swing.GroupLayout(paymentDetailsPanel);
        paymentDetailsPanel.setLayout(paymentDetailsPanelLayout);
        paymentDetailsPanelLayout.setHorizontalGroup(
            paymentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paymentDetailsPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(paymentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paymentDetailsPanelLayout.createSequentialGroup()
                        .addComponent(jlChanges)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtPrintR)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbtPrintT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtBack)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtReturn)
                        .addGap(18, 18, 18))
                    .addGroup(paymentDetailsPanelLayout.createSequentialGroup()
                        .addGroup(paymentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(paymentDetailsPanelLayout.createSequentialGroup()
                                .addGroup(paymentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlTotalAmt)
                                    .addComponent(jlGST)
                                    .addComponent(jlDisc)
                                    .addComponent(jlNett))
                                .addGap(18, 18, 18)
                                .addGroup(paymentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jtfTotalAmt)
                                    .addComponent(jtfGST)
                                    .addComponent(jtfDisc)
                                    .addComponent(jtfNett, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)))
                            .addGroup(paymentDetailsPanelLayout.createSequentialGroup()
                                .addComponent(jlPaid)
                                .addGap(18, 18, 18)
                                .addGroup(paymentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfPaid)
                                    .addComponent(jtfChanges))))
                        .addGap(18, 18, 18)
                        .addGroup(paymentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(paymentDetailsPanelLayout.createSequentialGroup()
                                .addGroup(paymentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jrbCC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jrbCash, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(paymentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(paymentDetailsPanelLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jtfCash, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paymentDetailsPanelLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jtfCC, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(14, 14, 14)
                                .addComponent(jbtSubmit2))
                            .addComponent(jlError2, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(paymentDetailsPanelLayout.createSequentialGroup()
                                .addGroup(paymentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jrbMemberDisc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(paymentDetailsPanelLayout.createSequentialGroup()
                                        .addComponent(jlMemberID)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jtfMemberID, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(12, 12, 12)
                                .addGroup(paymentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(paymentDetailsPanelLayout.createSequentialGroup()
                                        .addComponent(jlPoint)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jtfPoints))
                                    .addComponent(jrbMemberPoint, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        paymentDetailsPanelLayout.setVerticalGroup(
            paymentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paymentDetailsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paymentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlTotalAmt)
                    .addComponent(jtfTotalAmt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlMemberID)
                    .addComponent(jtfMemberID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlPoint)
                    .addComponent(jtfPoints, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(paymentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paymentDetailsPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(paymentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlGST)
                            .addComponent(jtfGST, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jrbMemberDisc)
                            .addComponent(jrbMemberPoint))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(paymentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlDisc)
                            .addComponent(jtfDisc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jrbCC))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(paymentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlNett)
                            .addComponent(jtfNett, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfCash, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jrbCash)))
                    .addGroup(paymentDetailsPanelLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jbtSubmit2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paymentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlPaid)
                    .addComponent(jtfPaid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlError2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paymentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlChanges)
                    .addComponent(jtfChanges, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtPrintR)
                    .addComponent(jbtPrintT)
                    .addComponent(jbtBack)
                    .addComponent(jbtReturn))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(paymentPanel);
        paymentPanel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(269, 269, 269)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(243, 243, 243)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlScheduleID)
                                .addGap(18, 18, 18)
                                .addComponent(jtfScheduleID, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlBookingID)
                                .addGap(24, 24, 24)
                                .addComponent(jtfBookingID, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jlError1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jlCustName, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                                            .addComponent(jtfCustName, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jlCustIC, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jtfCustIC, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jlCustContact)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jtfCustContact, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(18, 18, 18)
                                    .addComponent(jbtSubmit1)
                                    .addGap(18, 18, 18)
                                    .addComponent(jbtCancel))))
                        .addGap(0, 136, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(paymentDetailsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfBookingID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlBookingID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlScheduleID)
                    .addComponent(jtfScheduleID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlCustName)
                    .addComponent(jtfCustName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlCustContact)
                    .addComponent(jtfCustContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlCustIC)
                    .addComponent(jtfCustIC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtCancel)
                    .addComponent(jbtSubmit1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlError1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(paymentDetailsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        if(bControl.selectLatestRecord()==null) {
            jtfBookingID.setText("BK000001");
        } else {
            Booking booking = bControl.selectLatestRecord();
            jtfBookingID.setText(booking.getBookingID().substring(0, 2) + String.format("%06d", Integer.parseInt(booking.getBookingID().substring(2, 8))+1));
        }
        jtfScheduleID.setText(psgList.get(0).getSchedule().getScheduleID());
        jtfScheduleID.setToolTipText("CJH Terminal to " + psgList.get(0).getSchedule().getRoute().getDestination() + " - Date: " + psgList.get(0).getSchedule().getDepartureDate() + " Time: " + psgList.get(0).getSchedule().getDepartureTime());
        double total = 0;
        for(int i=0; i<psgList.size(); i++) {
            model.addRow(new Object[] {psgList.get(i).getTicketID(), psgList.get(i).getPassengerName(), psgList.get(i).getPassengerContactNo(), psgList.get(i).getSeatNo(), String.format("%.2f", psgList.get(i).getSchedule().getRoute().getPrice().getPrice())});
            total += psgList.get(i).getSchedule().getRoute().getPrice().getPrice();
        }
        jtfTotalAmt.setText(String.format("%.2f", total));
        jtfGST.setText(String.format("%.2f", total*0.06));
        jtfNett.setText(String.format("%.2f", total*1.06));
        jtfDisc.setText("0");      
                             
        jbtSubmit1.addActionListener((ActionEvent evt) -> {                                            
            String custName = jtfCustName.getText();
            String custContact = jtfCustContact.getText();
            String custICNo = jtfCustIC.getText();
            if(custName.equals("")||custContact.equals("")||custICNo.equals("")) {
                jlError1.setText("Please fill in all the required information.");
                jlError1.setForeground(Color.red);
            } else if(custName.length()>30) {
                jlError1.setText("Name not more than 30 words.");
                jlError1.setForeground(Color.red);
            } else if(!custContact.matches("01[\\d]{1}-[\\d]{7,8}")) {
                jlError1.setText("Invalid contact number format.");
                jlError1.setForeground(Color.red);
            } else if(!custICNo.matches("[\\d]{6}-[\\d]{2}-[\\d]{4}")) {
                jlError1.setText("Invalid IC number format.");
                jlError1.setForeground(Color.red);
            } else {
                String date = custICNo.substring(0, 6);                      
                int errorCounter = 0;
                Date valiDate = null;
                try{
                    SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
                    format.setLenient(false);
                    valiDate = format.parse(date);
                } catch(Exception ex) {
                    errorCounter++;
                }
                if(errorCounter!=0) {
                    jlError1.setText("Invalid IC number format.");
                    jlError1.setForeground(Color.red);  
                } else {                               
                    jlError1.setText(" ");
                    Customer customer = cControl.selectRecordByNameContactICNo(custName, custContact, custICNo);               
                    if(customer!=null) {
                        this.customer = customer;
                        if(customer.getStatus()!=' ') {                   
                            jrbMemberDisc.setEnabled(true);
                            jrbMemberDisc.setSelected(true);
                            jlMemberID.setEnabled(true);
                            jtfMemberID.setEnabled(true);
                            jlPoint.setEnabled(true);
                            jtfPoints.setEnabled(true);
                            jrbCC.setEnabled(true);
                            jtfCash.setEnabled(true);
                            jrbCash.setEnabled(true);
                            jrbCash.setSelected(true);
                            jbtSubmit2.setEnabled(true);
                            jtfMemberID.setText(customer.getCustID());
                            jtfPoints.setText(Integer.toString(customer.getPoint()));
                            jtfDisc.setText(String.format("%.2f", (Double.parseDouble(jtfNett.getText())*0.1)));
                            jtfNett.setText(String.format("%.2f", (Double.parseDouble(jtfNett.getText())-Double.parseDouble(jtfDisc.getText()))));
                            int pointRequired = (int)(Double.parseDouble(jtfNett.getText())*100);
                            jrbMemberPoint.setText(jrbMemberPoint.getText() + Integer.toString(pointRequired));
                            if(customer.getPoint()>=pointRequired) {                       
                                jrbMemberPoint.setEnabled(true);  
                                this.pointsRequired = pointRequired;
                            } 
                            jtfCustName.setEditable(false);
                            jtfCustContact.setEditable(false);
                            jtfCustIC.setEditable(false);
                            jbtSubmit1.setEnabled(false);
                        } else {
                            jtfDisc.setText("0"); 
                            jrbCC.setEnabled(true);
                            jtfCash.setEnabled(true);
                            jrbCash.setEnabled(true);
                            jrbCash.setSelected(true);
                            jbtSubmit2.setEnabled(true);
                            jtfCustName.setEditable(false);
                            jtfCustContact.setEditable(false);
                            jtfCustIC.setEditable(false);
                            jbtSubmit1.setEnabled(false);
                        }
                    } else {
                        if(cControl.selectRecordByIC(custICNo)!=null) {
                            jlError1.setText("IC number exists in database.");
                            jlError1.setForeground(Color.red);
                        } else {
                            this.customer = null;
                            jtfDisc.setText("0"); 
                            jrbCC.setEnabled(true);
                            jtfCash.setEnabled(true);
                            jrbCash.setEnabled(true);
                            jrbCash.setSelected(true);
                            jbtSubmit2.setEnabled(true);
                            jtfCustName.setEditable(false);
                            jtfCustContact.setEditable(false);
                            jtfCustIC.setEditable(false);
                            jbtSubmit1.setEnabled(false);
                        }
                    }
                }                
            }
        });    
        
        jbtCancel.addActionListener((ActionEvent e) -> {
            jbtSubmit1.setEnabled(true);
            jtfCustName.setEditable(true);
            jtfCustName.setText("");
            jtfCustContact.setEditable(true);
            jtfCustContact.setText("");
            jtfCustIC.setEditable(true);    
            jtfCustIC.setText("");
            jrbMemberDisc.setEnabled(false);           
            jrbMemberPoint.setEnabled(false);
            jrbMemberPoint.setText("Use Points: ");
            jlMemberID.setEnabled(false);
            jtfMemberID.setEnabled(false);
            jtfMemberID.setText("");
            jlPoint.setEnabled(false);
            jtfPoints.setEnabled(false);
            jtfPoints.setText("");
            jrbCC.setEnabled(false);
            jtfCC.setEnabled(false);
            jtfCC.setText("");
            jtfCash.setEnabled(false);
            jtfCash.setText("");
            jrbCash.setEnabled(false);
            //jbtCheck.setEnabled(false);
            jtfDisc.setText("0");
            jtfNett.setText(String.format("%.2f", Double.parseDouble(jtfTotalAmt.getText())*1.06));
            buttonGroup1.clearSelection();
            buttonGroup2.clearSelection();
            jlError1.setText(" ");
            jlError2.setText(" ");
            jbtSubmit2.setEnabled(false);
        });
        
        jrbMemberPoint.addActionListener((ActionEvent e) -> {
            jtfDisc.setText("0");
            jtfNett.setText(String.format("%.2f", Double.parseDouble(jtfTotalAmt.getText())*1.06));
            jrbCC.setEnabled(false);
            jtfCC.setEnabled(false);
            jrbCash.setEnabled(false);
            jtfCash.setEnabled(false);
            buttonGroup2.clearSelection();
        });
        
        jrbMemberDisc.addActionListener((ActionEvent e) -> {
            jtfDisc.setText(String.format("%.2f", (Double.parseDouble(jtfNett.getText())*0.1)));
            jtfNett.setText(String.format("%.2f", (Double.parseDouble(jtfNett.getText())-Double.parseDouble(jtfDisc.getText()))));
            jrbCC.setEnabled(true);
            jtfCash.setEnabled(true);
            jrbCash.setEnabled(true);
            jrbCash.setSelected(true);
        });
        
        jrbCC.addActionListener((ActionEvent e) -> {
            jtfCC.setEnabled(true);
            jtfCash.setEnabled(false);
            jtfCash.setText("");
        });
        
        jrbCash.addActionListener((ActionEvent e) -> {
            jtfCash.setEnabled(true);
            jtfCC.setEnabled(false);
            jtfCC.setText("");
        });
        Customer custToBe = cControl.selectLatestRecord();
        String custID;
        if(custToBe==null) {
            custID = "C000001";
        } else {
            custID = custToBe.getCustID().substring(0, 1) + String.format("%06d", Integer.parseInt(custToBe.getCustID().substring(1, 7))+1);
        }
        Calendar cal = Calendar.getInstance();
        jbtSubmit2.addActionListener((ActionEvent e) -> {
            if(!jrbCC.isEnabled()&&!jtfCC.isEnabled()&&!jrbCash.isEnabled()&&!jtfCash.isEnabled()) {
                jlError2.setText("Use member points!!! Payment is done.");
                jlError2.setForeground(Color.blue);
                jtfPaid.setText(jtfNett.getText());
                jtfChanges.setText("0");
                jtfPoints.setText(Integer.toString(this.customer.getPoint()-this.pointsRequired));
                jbtCancel.setEnabled(false);
                jbtPrintR.setEnabled(true);
                jbtPrintT.setEnabled(true);
                jrbMemberDisc.setEnabled(false);
                jrbMemberPoint.setEnabled(false);
                jbtBack.setEnabled(false);
                jbtSubmit2.setEnabled(false);
                cControl.decreasePoint(this.customer, Integer.parseInt(jtfPoints.getText()));
                currentCustomer = this.customer;
                Booking newBooking = new Booking(jtfBookingID.getText(), staffLogin, customer, new SimpleDateFormat("HH:mm:ss").format(cal.getTime()), new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));
                bControl.insertRecord(newBooking);
                currentBooking = newBooking;
                for(int i=0; i<psgList.size(); i++) {
                    Ticket ticket = new Ticket(ticketList.get(i).getTicketID(), newBooking, ticketList.get(i).getSchedule(), ticketList.get(i).getSeatNo(), ticketList.get(i).getPassengerName(), ticketList.get(i).getPassengerContactNo(), ticketList.get(i).getStatus());
                    tControl.insertRecord(ticket);
                    currentTicketList.add(ticket);
                }
            } else {
                jlError2.setText(" ");
                if(jrbCC.isSelected()) {
                    String ccEntered = jtfCC.getText();
                    if(ccEntered.equals("")) {
                        jlError2.setText("Please enter a valid credit card number.");
                        jlError2.setForeground(Color.red);
                    } else if(!ccEntered.matches("^4[0-9]{12}(?:[0-9]{3})?$")&&!ccEntered.matches("^5[1-5][0-9]{14}$")) {
                        jlError2.setText("Invalid credit card format. (VISA/MasterCard)");
                        jlError2.setForeground(Color.red);
                    } else {
                        jlError2.setText("Payment is done!!!");
                        jlError2.setForeground(Color.blue);
                        jtfPaid.setText(jtfNett.getText());
                        jtfChanges.setText("0");
                        if(this.customer==null) {                           
                            Customer newCust = new Customer(custID, jtfCustName.getText(), jtfCustContact.getText(), jtfCustIC.getText());
                            cControl.insertRecord(newCust);
                            currentCustomer = newCust;
                            Booking newBooking = new Booking(jtfBookingID.getText(), staffLogin, newCust, new SimpleDateFormat("HH:mm:ss").format(cal.getTime()), new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));
                            bControl.insertRecord(newBooking);
                            currentBooking = newBooking;
                            for(int i=0; i<psgList.size(); i++) {
                                Ticket ticket = new Ticket(ticketList.get(i).getTicketID(), newBooking, ticketList.get(i).getSchedule(), ticketList.get(i).getSeatNo(), ticketList.get(i).getPassengerName(), ticketList.get(i).getPassengerContactNo(), ticketList.get(i).getStatus());
                                tControl.insertRecord(ticket);
                                currentTicketList.add(ticket);
                            }        
                        } else {     
                            currentCustomer = this.customer;
                            Booking newBooking = new Booking(jtfBookingID.getText(), staffLogin, this.customer, new SimpleDateFormat("HH:mm:ss").format(cal.getTime()), new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));
                            bControl.insertRecord(newBooking);
                            currentBooking = newBooking;
                            for(int i=0; i<psgList.size(); i++) {
                                Ticket ticket = new Ticket(ticketList.get(i).getTicketID(), newBooking, ticketList.get(i).getSchedule(), ticketList.get(i).getSeatNo(), ticketList.get(i).getPassengerName(), ticketList.get(i).getPassengerContactNo(), ticketList.get(i).getStatus());
                                tControl.insertRecord(ticket);
                                currentTicketList.add(ticket);
                            }
                            if(this.customer.getStatus()!=' ') {
                                cControl.increasePoint(this.customer, (int)Double.parseDouble(jtfNett.getText()));
                                jtfPoints.setText(cControl.selectRecord(this.customer.getCustID()).getPoint()+"");
                                Customer cust1 = new Customer(this.customer.getCustID(), this.customer.getCustName(), this.customer.getContactNo(), this.customer.getIcNo(), this.customer.getStatus(), cControl.selectRecord(this.customer.getCustID()).getPoint());
                                currentCustomer = cust1;
                            }                            
                        }
                        jbtCancel.setEnabled(false);
                        jbtPrintR.setEnabled(true);
                        jbtPrintT.setEnabled(true);
                        jrbMemberDisc.setEnabled(false);
                        jrbMemberPoint.setEnabled(false);
                        jbtBack.setEnabled(false);
                        jbtSubmit2.setEnabled(false);
                        jrbCash.setEnabled(false);
                        jrbCC.setEnabled(false);
                        jtfCC.setEditable(false);
                    }
                } else if(jrbCash.isSelected()) {
                    String cashEntered = jtfCash.getText();
                    if(cashEntered.equals("")) {
                        jlError2.setText("Please enter a valid cash amount");
                        jlError2.setForeground(Color.red);
                    } else if(!cashEntered.matches("^(\\.\\d{1,2}|\\d{1,5}\\.?\\d{0,2})$")) {
                        jlError2.setText("Invalid cash format. Max(RMXXXXX.XX)");
                        jlError2.setForeground(Color.red);
                    } else if(Double.parseDouble(cashEntered)<Double.parseDouble(jtfNett.getText())) {
                        jlError2.setText("Invalid cash amount.");
                        jlError2.setForeground(Color.red);
                    } else {
                        jlError2.setText("Payment is done!!!");
                        jlError2.setForeground(Color.blue);
                        jtfPaid.setText(String.format("%.2f", Double.parseDouble(cashEntered)));
                        jtfChanges.setText(String.format("%.2f", Double.parseDouble(cashEntered)-Double.parseDouble(jtfNett.getText())));
                        if(this.customer==null) {                           
                            Customer newCust = new Customer(custID, jtfCustName.getText(), jtfCustContact.getText(), jtfCustIC.getText());
                            cControl.insertRecord(newCust);
                            currentCustomer = newCust;
                            Booking newBooking = new Booking(jtfBookingID.getText(), staffLogin, newCust, new SimpleDateFormat("HH:mm:ss").format(cal.getTime()), new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));
                            bControl.insertRecord(newBooking);
                            currentBooking = newBooking;
                            for(int i=0; i<psgList.size(); i++) {
                                Ticket ticket = new Ticket(ticketList.get(i).getTicketID(), newBooking, ticketList.get(i).getSchedule(), ticketList.get(i).getSeatNo(), ticketList.get(i).getPassengerName(), ticketList.get(i).getPassengerContactNo(), ticketList.get(i).getStatus());
                                tControl.insertRecord(ticket);
                                currentTicketList.add(ticket);
                            }        
                        } else {                  
                            currentCustomer = this.customer;
                            Booking newBooking = new Booking(jtfBookingID.getText(), staffLogin, this.customer, new SimpleDateFormat("HH:mm:ss").format(cal.getTime()), new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));
                            bControl.insertRecord(newBooking);
                            currentBooking = newBooking;
                            for(int i=0; i<psgList.size(); i++) {
                                Ticket ticket = new Ticket(ticketList.get(i).getTicketID(), newBooking, ticketList.get(i).getSchedule(), ticketList.get(i).getSeatNo(), ticketList.get(i).getPassengerName(), ticketList.get(i).getPassengerContactNo(), ticketList.get(i).getStatus());
                                tControl.insertRecord(ticket);
                                currentTicketList.add(ticket);
                            }
                            if(this.customer.getStatus()!=' ') {
                                cControl.increasePoint(this.customer, (int)Double.parseDouble(jtfNett.getText()));
                                jtfPoints.setText(cControl.selectRecord(this.customer.getCustID()).getPoint()+"");
                                Customer cust1 = new Customer(this.customer.getCustID(), this.customer.getCustName(), this.customer.getContactNo(), this.customer.getIcNo(), this.customer.getStatus(), cControl.selectRecord(this.customer.getCustID()).getPoint());
                                currentCustomer = cust1;
                            }                            
                        }
                        jbtCancel.setEnabled(false);
                        jbtPrintR.setEnabled(true);
                        jbtPrintT.setEnabled(true);
                        jrbMemberDisc.setEnabled(false);
                        jrbMemberPoint.setEnabled(false);
                        jbtBack.setEnabled(false);
                        jbtSubmit2.setEnabled(false);
                        jrbCC.setEnabled(false);
                        jrbCash.setEnabled(false);
                        jtfCash.setEditable(false);
                    }
                }
            }
        });              
        
        jbtPrintR.addActionListener((ActionEvent e) -> {
            jbtPrintR.setText("RPrinted");
            printReceipt();
        });
        
        jbtPrintT.addActionListener((ActionEvent e) -> {
            jbtPrintT.setText("TPrinted");
            printTicket();
        });
        
        DocumentListener fillInfo = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                jlError1.setText(" ");
                jlError2.setText(" ");
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                jlError1.setText(" ");
                jlError2.setText(" ");
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }           
        };

        jtfCustName.getDocument().addDocumentListener(fillInfo);
        jtfCustContact.getDocument().addDocumentListener(fillInfo);
        jtfCustIC.getDocument().addDocumentListener(fillInfo);
        jtfCash.getDocument().addDocumentListener(fillInfo);
        jtfCC.getDocument().addDocumentListener(fillInfo);
        
        jbtBack.addActionListener((ActionEvent e) -> {
            menuPanel.removeAll();
            menuPanel.revalidate();
            menuPanel.repaint();
            menuPanel.add(psgForm(countList, psgList.get(0).getSchedule()), BorderLayout.CENTER);
        });
        
        jbtReturn.addActionListener((ActionEvent e) -> {
            this.countList.clear();
            this.ticketList.clear();
            this.currentTicketList.clear();
            this.currentCustomer = null;
            this.currentBooking = null;
            menuPanel.removeAll();
            menuPanel.revalidate();
            menuPanel.repaint();
            menuPanel.add(ticketing(), BorderLayout.CENTER);
        });
        
        return paymentPanel;
    }
    
    public void printReceipt() {
        
        File file = new File("src/receipt/" + currentBooking.getBookingID() + ".txt");
        try {
            PrintWriter writer = new PrintWriter(file);
            writer.println("----------------------------------------------------------");
            writer.println("                  CJH BUS EXPRESS SDN. BHD.");
            writer.println("                      *** RECEIPT ***");
            writer.println("                        CJH Terminal,");
            writer.println("                      Taman Bunga Raya,");
            writer.println("                          53300 K.L.");
            writer.println("                                 GST No: 123456789000");
            writer.println("                                 Telephone No: 012-3456789");
            writer.println("----------------------------------------------------------");
            writer.println(" Date       : " + currentBooking.getBookingDate() + " " + currentBooking.getBookingTime());
            writer.println(" Booking ID : " + currentBooking.getBookingID());          
            writer.println("----------------------------------------------------------");
            writer.println(" Staff ID   : " + currentBooking.getStaff().getStaffID());
            writer.println(" Staff Name : " + currentBooking.getStaff().getStaffName());
            writer.println("----------------------------------------------------------");
            writer.println(" QTY   DESTINATION                            PRICE (RM)");
            writer.println("----------------------------------------------------------");   
            if(editBooking==false) {
                writer.println(" " + String.format("%3d", currentTicketList.size()) + "   " + String.format("%-30s", currentTicketList.get(0).getSchedule().getRoute().getDestination()) + String.format("%17.2f", currentTicketList.get(0).getSchedule().getRoute().getPrice().getPrice()));
                writer.println("----------------------------------------------------------");               
                writer.println("                                Total       = " + String.format("%8.2f", Double.parseDouble(jtfTotalAmt.getText())));
                writer.println("                                GST (6%)    = " + String.format("%8.2f", Double.parseDouble(jtfGST.getText())));            
                writer.println("                                Discount    = " + String.format("%8.2f", Double.parseDouble(jtfDisc.getText())));
                writer.println("                                Nett Total  = " + String.format("%8.2f", Double.parseDouble(jtfNett.getText())));
                writer.println("----------------------------------------------------------");
                writer.println("                                Paid Amount = " + String.format("%8.2f", Double.parseDouble(jtfPaid.getText())));
                writer.println("                                Change      = " + String.format("%8.2f", Double.parseDouble(jtfChanges.getText())));
                writer.println("----------------------------------------------------------");
                if(jtfCash.getText().equals("")&&jtfCC.getText().equals("")) {
                    writer.println(" Paid by using member points.");
                } else if (jtfCash.getText().equals("")) {
                    writer.println(" Paid by credit card.");
                    writer.println(" Credit card number : " + jtfCC.getText());
                } else {
                    writer.println(" Paid by cash.");
                }
            }           
            else {
                writer.println(" " + String.format("%3d", editTT.size()) + "   Old-" + String.format("%-26s", editTT.get(0).getSchedule().getRoute().getDestination()) + String.format("%17.2f", editTT.get(0).getSchedule().getRoute().getPrice().getPrice()));
                writer.println(" " + String.format("%3d", currentTicketList.size()) + "   New-" + String.format("%-26s", currentTicketList.get(0).getSchedule().getRoute().getDestination()) + String.format("%17.2f", currentTicketList.get(0).getSchedule().getRoute().getPrice().getPrice()));
                writer.println("----------------------------------------------------------");   
                writer.println("                                Extra Amt   = " + String.format("%8.2f", Double.parseDouble(jtfTotalAmt.getText())));
                writer.println("                                Change Fees = " + String.format("%8.2f", Double.parseDouble(jtfGST.getText())));            
                //writer.println("                                Discount    = " + String.format("%8.2f", Double.parseDouble(jtfDisc.getText())));
                writer.println("                                Nett Amt    = " + String.format("%8.2f", Double.parseDouble(jtfNett.getText())));
                writer.println("----------------------------------------------------------");
                writer.println("                                Paid Amount = " + String.format("%8.2f", Double.parseDouble(jtfPaid.getText())));
                writer.println("                                Change      = " + String.format("%8.2f", Double.parseDouble(jtfChanges.getText())));
                writer.println("----------------------------------------------------------");
                if (jtfCash.getText().equals("")) {
                    writer.println(" Paid by credit card.");
                    writer.println(" Credit card number : " + jtfCC.getText());
                } else {
                    writer.println(" Paid by cash.");
                }
            }
            writer.println("----------------------------------------------------------");           
            if(currentCustomer.getStatus()=='M') {
                writer.println(" Member ID  : " + currentCustomer.getCustID());
                writer.println(" Points     : " + currentCustomer.getPoint());                
            } else {
                writer.println(" Not a member.");
            }
            writer.println("----------------------------------------------------------");
            writer.println("             THANK YOU AND PLEASE COME AGAIN              ");
            writer.println("                www.cjh-bus-express.com                   ");
            writer.println("----------------------------------------------------------");
            writer.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void printTicket() {   
        
        for(int t=0; t<currentTicketList.size(); t++) {
            String myCodeText = currentTicketList.get(t).getTicketID() + " %0A " + currentTicketList.get(t).getBooking().getBookingID() + " - " + currentTicketList.get(t).getSchedule().getRoute().getDestination() + " - " + 
                    currentTicketList.get(t).getPassengerName() + " - " + currentTicketList.get(t).getPassengerContactNo() + " - " + currentTicketList.get(t).getSeatNo();     
            String filePath = "src/ticket/" + currentTicketList.get(t).getTicketID() + ".png";
            int size = 165;
            String fileType = "png";
            File myFile = new File(filePath);
            try {
                Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
                hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
                QRCodeWriter qrCodeWriter = new QRCodeWriter();
                BitMatrix byteMatrix = qrCodeWriter.encode(myCodeText,BarcodeFormat.QR_CODE, size, size, hintMap);
                int width = byteMatrix.getWidth();
                BufferedImage image = new BufferedImage(width, width,
                        BufferedImage.TYPE_INT_RGB);
                image.createGraphics();

                Graphics2D graphics = (Graphics2D) image.getGraphics();
                graphics.setColor(Color.WHITE);
                graphics.fillRect(0, 0, width, width);
                graphics.setColor(Color.BLACK);

                for (int i = 0; i < width; i++) 
                    for (int j = 0; j < width; j++) 
                        if (byteMatrix.get(i, j)) 
                            graphics.fillRect(i, j, 1, 1);

                ImageIO.write(image, fileType, myFile);
            } catch (WriterException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            Document document = new Document();
            try {               
                PdfWriter.getInstance(document,
                        new FileOutputStream("src/ticketpdf/" + currentTicketList.get(t).getTicketID() + "DATE" + new SimpleDateFormat("ddMMyyHHmmss").format(new Date()) + ".pdf"));
                document.open();
                
                Phrase p1 = new Phrase();
                Paragraph para = new Paragraph();
                com.itextpdf.text.Font font = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.COURIER,32,com.itextpdf.text.Font.BOLDITALIC);
                com.itextpdf.text.Font timesRomanfont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN,16,com.itextpdf.text.Font.BOLD);
                Chunk cc = new Chunk("#################################################################", timesRomanfont);
                p1.add(cc);
                p1.add(Chunk.NEWLINE);
                p1.add(Chunk.NEWLINE);
                Chunk c = new Chunk("CJH BUS EXPRESS SDN. BHD.",font);
                p1.add(c);
                p1.add(Chunk.NEWLINE);
                p1.add(Chunk.NEWLINE);
                p1.add(cc);
                para.setAlignment(Element.ALIGN_CENTER);
                para.add(p1);
                document.add(para);
                
                Image image1 = Image.getInstance(filePath);               
                document.add(image1); 
                
                Phrase phrase = new Phrase();
                Paragraph paragraph = new Paragraph();
                
                Chunk c1 = new Chunk("Ticket ID                       : " + currentTicketList.get(t).getTicketID(),timesRomanfont);
                phrase.add(c1);
                phrase.add(Chunk.NEWLINE);
                
                Chunk c2 = new Chunk("Booking ID                    : " + currentTicketList.get(t).getBooking().getBookingID(),timesRomanfont);
                phrase.add(c2);
                phrase.add(Chunk.NEWLINE);
                
                Chunk c3 = new Chunk("Destination                    : " + currentTicketList.get(t).getSchedule().getRoute().getDestination(),timesRomanfont);
                phrase.add(c3);  
                phrase.add(Chunk.NEWLINE);
                
                Chunk cDate = new Chunk("Departure Date             : " + currentTicketList.get(t).getSchedule().getDepartureDate(),timesRomanfont);
                phrase.add(cDate);
                phrase.add(Chunk.NEWLINE);
                
                Chunk cTime = new Chunk("Departure Time            : " + currentTicketList.get(t).getSchedule().getDepartureTime(),timesRomanfont);
                phrase.add(cTime);
                phrase.add(Chunk.NEWLINE);
                
                Chunk c4 = new Chunk("Passenger Name            : " + currentTicketList.get(t).getPassengerName(),timesRomanfont);
                phrase.add(c4);  
                phrase.add(Chunk.NEWLINE);
                
                Chunk c5 = new Chunk("Passenger Contact No   : " + currentTicketList.get(t).getPassengerContactNo(),timesRomanfont);
                phrase.add(c5);  
                phrase.add(Chunk.NEWLINE);
                
                Chunk c6 = new Chunk("Seat No                           : " + currentTicketList.get(t).getSeatNo(),timesRomanfont);
                phrase.add(c6);  
                phrase.add(Chunk.NEWLINE);
                
                if(reprintCounter==true) {
                    Chunk cR = new Chunk("--- REPRINT ---", timesRomanfont);
                    phrase.add(cR);
                    phrase.add(Chunk.NEWLINE);
                }
                
                Chunk c7 = new Chunk("#################################################################", timesRomanfont);
                phrase.add(c7);
                
                paragraph.add(phrase);          
                document.add(paragraph);
             
                document.close();
            } catch(Exception e){
              e.printStackTrace();
            } 
            
//            try {
//                PdfReader reader = new PdfReader("src/ticketpdf/" + currentTicketList.get(t).getTicketID() + "DATE" + new SimpleDateFormat("ddMMyyHHmmss").format(new Date()) + ".pdf");
//                int n = reader.getNumberOfPages();
//                PdfStamper stamp = new PdfStamper(reader, new FileOutputStream("src/ticketpdfWM/" + currentTicketList.get(t).getTicketID() + "WMDATE" + new SimpleDateFormat("ddMMyyHHmmss").format(new Date()) + ".pdf"));
//                int i = 0;
//                PdfContentByte under;
//                Image img = Image.getInstance("src/images/b1.jpg");
//                img.setAbsolutePosition(200, 400);
//                while (i < n) {
//                  i++;
//                  under = stamp.getUnderContent(i);
//                  under.addImage(img);
//                }
//                stamp.close();
//            }
//            catch (Exception de) {
//                de.printStackTrace();
//            }
        }
    }        
    
    public JPanel customerPanel() {
        
        JPanel customerPanel = new JPanel();
        JPanel jPanel1 = new JPanel();
        JLabel jLabel1 = new JLabel("Search");
        JComboBox jcbCriteria = new JComboBox();
        JTextField jtfSearch = new JTextField();
        JButton jbtSearch = new JButton("Search");
        JLabel jlTitle = new JLabel("Customer Details");
        JPanel jPanel2 = new JPanel();
        JLabel jLabel3 = new JLabel("ID");
        JLabel jLabel4 = new JLabel("Name");
        JLabel jLabel5 = new JLabel("Contact No");
        JLabel jLabel6 = new JLabel("IC No");
        JLabel jLabel7 = new JLabel("Status");
        JTextField jtfContact = new JTextField();
        JTextField jtfName = new JTextField();
        JTextField jtfID = new JTextField();
        JTextField jtfIC = new JTextField();
        JTextField jtfStatus = new JTextField();
        JButton jbtModify = new JButton("Modify");
        JButton jbtSubmit = new JButton("Submit");
        JLabel jlError2 = new JLabel(" ");
        JLabel jLabel9 = new JLabel("Read & Update");
        JLabel jlError1 = new JLabel(" ");
        JScrollPane jScrollPane1 = new JScrollPane();
        JTable table = new JTable();
        JTextField jtfFilter = new JTextField();
        JButton jbtFilter = new JButton("Filter");
        JButton jbtSearchAll = new JButton("Search All");
        JButton jbtBack = new JButton("Back");

        customerPanel.setBackground(new java.awt.Color(102, 102, 102));
        customerPanel.setMaximumSize(new java.awt.Dimension(740, 640));
        customerPanel.setMinimumSize(new java.awt.Dimension(740, 640));
        customerPanel.setPreferredSize(new java.awt.Dimension(740, 640));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jLabel1.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jcbCriteria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ID", "Name", "Contact No", "IC No", "Status" }));
        jlTitle.setFont(new java.awt.Font("Lucida Fax", 1, 24)); // NOI18N
        jlTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 4, true));       
        jLabel9.setFont(new java.awt.Font("Lucida Fax", 2, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(64, Short.MAX_VALUE)
                .addComponent(jbtModify)
                .addGap(18, 18, 18)
                .addComponent(jbtSubmit)
                .addGap(63, 63, 63))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlError2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfContact)
                            .addComponent(jtfName)
                            .addComponent(jtfID)
                            .addComponent(jtfIC)
                            .addComponent(jtfStatus))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtfID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jtfContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jtfIC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jtfStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jlError2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtSubmit)
                    .addComponent(jbtModify))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jlTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jcbCriteria, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jtfSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jbtSearch))
                            .addComponent(jlError1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jLabel1))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jlTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbCriteria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlError1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        //table.setAutoCreateRowSorter(true);
        table.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Contact No", "IC No", "Status"
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
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(4).setPreferredWidth(60);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(customerPanel);
        customerPanel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jtfFilter, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtFilter)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtSearchAll))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbtBack)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtFilter)
                    .addComponent(jbtSearchAll))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jbtBack)
                .addContainerGap())
        );                        
        
        jtfID.setEditable(false);
        jtfName.setEditable(false);
        jtfContact.setEditable(false);
        jtfIC.setEditable(false);
        jtfStatus.setEditable(false);
        jbtSubmit.setEnabled(false);
        jbtModify.setEnabled(false);
        DefaultTableModel model = (DefaultTableModel) table.getModel();      
        sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);
        jlError1.setForeground(Color.red);
        jbtSearch.addActionListener((ActionEvent e) -> {
            jlError1.setText(" ");
            jlError2.setText(" ");
            jtfID.setText("");
            jtfName.setText("");
            jtfContact.setText("");
            jtfIC.setText("");
            jtfStatus.setText("");
            jbtModify.setEnabled(false);
            jbtSubmit.setEnabled(false);
            model.setRowCount(0);
            sorter.setRowFilter(null);
            jtfFilter.setText("");
            if(jcbCriteria.getSelectedItem().toString().equals("ID")) {
                String id = jtfSearch.getText();
                if(id.equals("")) {
                    jlError1.setText("Enter customer ID to search.");                    
                } else {
                    Customer customer = cControl.selectRecord(id);
                    if(customer==null) {
                        jlError1.setText("Invalid customer ID in database.");
                    } else {
                        model.addRow(new Object[] {customer.getCustID(), customer.getCustName(), customer.getContactNo(), customer.getIcNo(), customer.getStatus()+""});
                    }
                }
            } else if(jcbCriteria.getSelectedItem().toString().equals("Name")) {
                String name = jtfSearch.getText();
                if(name.equals("")) {
                    jlError1.setText("Enter customer name to search.");
                } else {
                    ArrayList<Customer> customerList = cControl.selectRecordByName(name);
                    if(!customerList.isEmpty()) {
                        for(int i=0; i<customerList.size(); i++) {
                            model.addRow(new Object[] {customerList.get(i).getCustID(), customerList.get(i).getCustName(), customerList.get(i).getContactNo(), customerList.get(i).getIcNo(), customerList.get(i).getStatus()+""});                                                              
                        }
                    } else {
                        jlError1.setText("No record found.");
                    }
                }
                
            } else if(jcbCriteria.getSelectedItem().toString().equals("Contact No")) {
                String contact = jtfSearch.getText();
                if(contact.equals("")) {
                    jlError1.setText("Enter contact no to search.");
                } else {
                    ArrayList<Customer> customerList = cControl.selectRecordByContactNo(contact);
                    if(!customerList.isEmpty()) {
                        for(int i=0; i<customerList.size(); i++) {
                            model.addRow(new Object[] {customerList.get(i).getCustID(), customerList.get(i).getCustName(), customerList.get(i).getContactNo(), customerList.get(i).getIcNo(), customerList.get(i).getStatus()+""});                                                              
                        }
                    } else {
                        jlError1.setText("No record found.");
                    }
                }
                
            } else if(jcbCriteria.getSelectedItem().toString().equals("IC No")) {
                String ic = jtfSearch.getText();
                if(ic.equals("")) {
                    jlError1.setText("Enter customer IC no to search.");                    
                } else {
                    Customer customer = cControl.selectRecordByIC(ic);
                    if(customer==null) {
                        jlError1.setText("Invalid customer IC no in database.");
                    } else {
                        model.addRow(new Object[] {customer.getCustID(), customer.getCustName(), customer.getContactNo(), customer.getIcNo(), customer.getStatus()+""});
                    }
                }
                
            } else if(jcbCriteria.getSelectedItem().toString().equals("Status")) {
                String status = jtfSearch.getText();
                if(status.equals("")) {
                    ArrayList<Customer> customerList = cControl.selectAllRecord();
                    if(!customerList.isEmpty()) {
                        for(int i=0; i<customerList.size(); i++) {
                            if(customerList.get(i).getStatus()==' ') {
                                model.addRow(new Object[] {customerList.get(i).getCustID(), customerList.get(i).getCustName(), customerList.get(i).getContactNo(), customerList.get(i).getIcNo(), customerList.get(i).getStatus()+""});                                   
                            }
                        }
                    } else {
                        jlError1.setText("No record found.");                       
                    }                          
                } else {
                    if(!status.toUpperCase().equals("M")) {
                        jlError1.setText("M for member and blank for non-member");
                    } else {
                        ArrayList<Customer> customerList = cControl.selectAllRecord();
                        if(!customerList.isEmpty()) {
                            for(int i=0; i<customerList.size(); i++) {
                                if(customerList.get(i).getStatus()=='M') {
                                    model.addRow(new Object[] {customerList.get(i).getCustID(), customerList.get(i).getCustName(), customerList.get(i).getContactNo(), customerList.get(i).getIcNo(), customerList.get(i).getStatus()+""});                                   
                                }
                            }
                        } else {
                            jlError1.setText("No record found.");
                        }                             
                    }
                }
            }
        });
        
        jtfSearch.addActionListener(jbtSearch.getActionListeners()[0]);
        
        jbtSearchAll.addActionListener((ActionEvent e) -> {
            model.setRowCount(0);
            sorter.setRowFilter(null);
            jtfFilter.setText("");
            ArrayList<Customer> customerList = cControl.selectAllRecord();
            if(!customerList.isEmpty()) {
                for(int i=0; i<customerList.size(); i++) {
                    model.addRow(new Object[] {customerList.get(i).getCustID(), customerList.get(i).getCustName(), customerList.get(i).getContactNo(), customerList.get(i).getIcNo(), customerList.get(i).getStatus()+""});                   
                }
            }
            jtfSearch.setText("");
            jlError1.setText(" ");
            jlError2.setText(" ");
            jtfID.setText("");
            jtfName.setText("");
            jtfContact.setText("");
            jtfIC.setText("");
            jtfStatus.setText("");
            jbtModify.setEnabled(false);
            jbtSubmit.setEnabled(false);
        });
        
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                jbtModify.setEnabled(true);
                //JTable tablee =(JTable) evt.getSource();
                if(evt.getClickCount() == 1) {
                    int row = table.getSelectedRow();
                    String id = table.getModel().getValueAt(table.convertRowIndexToModel(row), 0).toString();
                    String name = table.getModel().getValueAt(table.convertRowIndexToModel(row), 1).toString();
                    String contact = table.getModel().getValueAt(table.convertRowIndexToModel(row), 2).toString();
                    String ic = table.getModel().getValueAt(table.convertRowIndexToModel(row), 3).toString();
                    char status = table.getModel().getValueAt(table.convertRowIndexToModel(row), 4).toString().charAt(0);
                    jtfID.setText(id);
                    jtfName.setText(name);
                    jtfContact.setText(contact);
                    jtfIC.setText(ic);
                    if(status=='M')
                        jtfStatus.setText("Member");
                    else
                        jtfStatus.setText("Non-Member");
                    jlError2.setText(" ");
                    jtfName.setEditable(false);
                    jtfContact.setEditable(false);
                    jtfIC.setEditable(false);
                    jbtModify.setEnabled(true);
                    jbtSubmit.setEnabled(false);
                }
            }
        });
               
        jbtModify.addActionListener((ActionEvent e) -> {
            nameEx = jtfName.getText();
            contactEx = jtfContact.getText();
            icEx = jtfIC.getText();
            jtfName.setEditable(true);
            jtfContact.setEditable(true);
            jtfIC.setEditable(true);
            jbtModify.setEnabled(false);
            jbtSubmit.setEnabled(true);
            jlError2.setText(" ");
        });
        
        jbtSubmit.addActionListener((ActionEvent e) -> {
            String name = jtfName.getText();
            String contact = jtfContact.getText();
            String ic = jtfIC.getText();
            int invalid = 0;
            if(name.equals(nameEx)&&contact.equals(contactEx)&&ic.equals(icEx)) {
                jtfName.setEditable(false);
                jtfContact.setEditable(false);
                jtfIC.setEditable(false);
                jbtModify.setEnabled(true);
                jbtSubmit.setEnabled(false);
                jlError2.setText(" ");               
            } else if(name.equals("")||contact.equals("")||ic.equals("")) {
                jlError2.setText("Please enter all the required information.");
                jlError2.setForeground(Color.red);
            } else {
                if(!name.equals(nameEx)) {
                    if(name.length()>30) {
                        jlError2.setText("Name not more than 30 words.");
                        jlError2.setForeground(Color.red);
                        invalid++;
                    } 
                }
                if(!contact.equals(contactEx)) {
                    if(!contact.matches("01[\\d]{1}-[\\d]{7,8}")) {
                        jlError2.setText("Invalid contact no format.");
                        jlError2.setForeground(Color.red);
                        invalid++;
                    }
                }
                if(!ic.equals(icEx)) {
                    if(!ic.matches("[\\d]{6}-[\\d]{2}-[\\d]{4}")) {
                        jlError2.setText("Invalid ic no format.");
                        jlError2.setForeground(Color.red);
                        invalid++;
                    } else {
                        String date = ic.substring(0, 6);                      
                        int errorCounter = 0;
                        Date valiDate = null;
                        try{
                            SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
                            format.setLenient(false);
                            valiDate = format.parse(date);
                        } catch(Exception ex) {
                            errorCounter++;
                        }
                        if(errorCounter!=0) {
                            jlError2.setText("Invalid ic no format.");
                            jlError2.setForeground(Color.red);
                            invalid++;
                        } else {
                            if(cControl.selectRecordByIC(ic)!=null) {
                                jlError2.setText("IC exists in database.");
                                jlError2.setForeground(Color.red);
                                invalid++;
                            }
                        }
                    }
                }
                if(invalid==0) {
                    jlError2.setText("Update successfully.");
                    jlError2.setForeground(Color.blue);
                    jtfName.setEditable(false);
                    jtfContact.setEditable(false);
                    jtfIC.setEditable(false);
                    jbtModify.setEnabled(true);
                    jbtSubmit.setEnabled(false);
                    Customer cust = new Customer(jtfID.getText(), name, contact, ic);
                    cControl.modifyRecord(cust);
                    int row = table.getSelectedRow();
                    ((DefaultTableModel)table.getModel()).setValueAt(name, table.convertRowIndexToModel(row), 1);
                    ((DefaultTableModel)table.getModel()).setValueAt(contact, table.convertRowIndexToModel(row), 2);
                    ((DefaultTableModel)table.getModel()).setValueAt(ic, table.convertRowIndexToModel(row), 3);
                }
            }
        });
        
        DocumentListener errorMsgRefresh = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                jlError1.setText(" ");
                jlError2.setText(" ");
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                jlError1.setText(" ");
                jlError2.setText(" ");
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }           
        };    
        
        jtfSearch.getDocument().addDocumentListener(errorMsgRefresh);      
        
        jtfFilter.addActionListener((ActionEvent e) -> {            
            String text = jtfFilter.getText();
            if (text.length() == 0) {
                sorter.setRowFilter(null);
            } else {
                try {
                    sorter.setRowFilter(RowFilter.regexFilter(text));
                } catch (PatternSyntaxException pse) {
                    JOptionPane.showMessageDialog(null, "Bad regex pattern", "Bad regex pattern", JOptionPane.ERROR_MESSAGE);
                }
            }            
        });
        jbtFilter.addActionListener(jtfFilter.getActionListeners()[0]);
        
        DocumentListener filterRefresh = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = jtfFilter.getText();
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

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = jtfFilter.getText();
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

            @Override
            public void changedUpdate(DocumentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }           
        };  
        
        jtfFilter.getDocument().addDocumentListener(filterRefresh);
        
        jbtBack.addActionListener((ActionEvent e) -> {
            menuPanel.removeAll();
            menuPanel.revalidate();
            menuPanel.repaint();
            menuPanel.add(msg);
            menuPanel.add(menuPane, BorderLayout.CENTER);
        });
        
        return customerPanel;
    }
    
    public JPanel bookingPanel() {
        
        JPanel bookingPanel = new JPanel();        
        JButton jbtReprint = new javax.swing.JButton("Reprint Ticket");
        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        JTable bookingTable = new javax.swing.JTable();
        JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
        JTable ticketTable = new javax.swing.JTable();
        JButton jbtBack = new javax.swing.JButton("Back");
        JPanel jPanel1 = new javax.swing.JPanel();
        JLabel jLabel1 = new javax.swing.JLabel("Booking Details");
        JLabel jLabel2 = new javax.swing.JLabel("Search");
        JComboBox jcbCriteria = new javax.swing.JComboBox();
        JTextField jtfSearch = new javax.swing.JTextField();
        JButton jbtSearch = new javax.swing.JButton("Search");
        JLabel jlError1 = new javax.swing.JLabel(" ");
        JButton jbtAll = new javax.swing.JButton("Search All");
        JDateChooser jDateChooser1 = new com.toedter.calendar.JDateChooser();
        Date date = new Date();
        SpinnerDateModel sm =
        new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
        JSpinner jSpinner1 = new javax.swing.JSpinner(sm);
        JLabel jLabel3 = new javax.swing.JLabel("-");
        JLabel jLabel4 = new javax.swing.JLabel("-");
        SpinnerDateModel sm2 =
        new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
        JSpinner jSpinner2 = new javax.swing.JSpinner(sm2);
        JDateChooser jDateChooser2 = new com.toedter.calendar.JDateChooser();
        JButton jbtCancel = new javax.swing.JButton("Cancel Ticket");
        JTextField jtfFilter = new javax.swing.JTextField();
        JButton jbtFilter = new javax.swing.JButton("Filter");
        JButton jbtFilter2 = new javax.swing.JButton("Filter");
        JTextField jtfFilter2 = new javax.swing.JTextField();
        JButton jbtEdit = new javax.swing.JButton("Edit");
        //JButton jbtReactivate = new javax.swing.JButton("Reactivate Ticket");

        bookingPanel.setBackground(new java.awt.Color(102, 102, 102));
        bookingPanel.setMaximumSize(new java.awt.Dimension(740, 640));
        bookingPanel.setMinimumSize(new java.awt.Dimension(740, 640));
        bookingPanel.setPreferredSize(new java.awt.Dimension(740, 640));

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
        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jLabel1.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jcbCriteria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Booking ID", "Staff ID", "Customer ID", "Customer Name", "Destination", "Time", "Time-Time", "Date", "Date-Date", "Date&Time", "Date&Time-Date&Time" }));
        jDateChooser1.setEnabled(false);
        jDateChooser1.setDateFormatString("yyyy-MM-dd");
        //jDateChooser1.setMaxSelectableDate(new Date());
        JSpinner.DateEditor de = new JSpinner.DateEditor(jSpinner1, "HH:mm:ss");
        jSpinner1.setEditor(de);
        jSpinner1.setEnabled(false);
        JSpinner.DateEditor de2 = new JSpinner.DateEditor(jSpinner2, "HH:mm:ss");
        jSpinner2.setEditor(de2);
        jSpinner2.setEnabled(false);
        jDateChooser2.setEnabled(false);
        jDateChooser2.setDateFormatString("yyyy-MM-dd");
        //jDateChooser2.setMaxSelectableDate(new Date());
        jbtReprint.setEnabled(false);
        jbtCancel.setEnabled(false);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(bookingPanel);
        bookingPanel.setLayout(layout);
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
     
        TableRowSorter<TableModel> sorter1;
        TableRowSorter<TableModel> sorter2;
        DefaultTableModel model1 = (DefaultTableModel) bookingTable.getModel();     
        sorter1 = new TableRowSorter<>(model1);
        bookingTable.setRowSorter(sorter1);
        
        DefaultTableModel model2 = (DefaultTableModel) ticketTable.getModel();     
        sorter2 = new TableRowSorter<>(model2);
        ticketTable.setRowSorter(sorter2); 
        
        JTextFieldDateEditor editor1 = (JTextFieldDateEditor) jDateChooser1.getDateEditor();
        editor1.setEditable(false);
        JTextFieldDateEditor editor2 = (JTextFieldDateEditor) jDateChooser2.getDateEditor();
        editor2.setEditable(false);
        
        DocumentListener filterRefresh = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                jbtReprint.setEnabled(false);
                jbtCancel.setEnabled(false);
                model2.setRowCount(0);
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
                jbtReprint.setEnabled(false);
                jbtCancel.setEnabled(false);
                model2.setRowCount(0);
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
                jbtReprint.setEnabled(false);
                jbtCancel.setEnabled(false);
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
                jbtReprint.setEnabled(false);
                jbtCancel.setEnabled(false);
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
                  
        jcbCriteria.addItemListener(new ItemListener() {  
            @Override
            public void itemStateChanged(ItemEvent evt) {
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
                    jtfSearch.setText("");
                    jlError1.setText(" ");
                } 
            }
        });
                   
        jbtSearch.addActionListener((ActionEvent e) -> {
            model2.setRowCount(0);
            model1.setRowCount(0);
            jbtReprint.setEnabled(false);
            jbtCancel.setEnabled(false);
            jlError1.setForeground(Color.red);
            if(jcbCriteria.getSelectedItem().toString().equals("Booking ID")) {
                String id = jtfSearch.getText();
                if(id.equals("")) {
                    jlError1.setText("Please enter booking ID to search.");
                } else {
                    Booking book = bControl.selectRecord(id);
                    if(book!=null) {
                        jlError1.setText(" ");
                        ArrayList<Booking> bookingList = new ArrayList<>();
                        bookingList.add(book);
                        searchBookingList = bookingList;
                        jlError1.setText(bookingList.size() + " records found!");
                        jlError1.setForeground(Color.blue);
                        model1.addRow(new Object[] {book.getBookingID(), book.getStaff().getStaffID(), book.getCustomer().getCustID(), book.getCustomer().getCustName(), 
                            tControl.selectRecordByBooking(book).get(0).getSchedule().getRoute().getDestination(), book.getBookingDate(), book.getBookingTime()});
                    } else {
                        jlError1.setText("Invalid booking ID in database.");
                    }
                }
            } else if(jcbCriteria.getSelectedItem().toString().equals("Staff ID")) {
                String id = jtfSearch.getText();
                if(id.equals("")) {
                    jlError1.setText("Please enter staff ID to search.");
                } else {
                    Staff staff = stControl.selectRecord(id);
                    if(staff!=null) {
                        ArrayList<Booking> book = bControl.selectRecordByStaff(staff);
                        if(!book.isEmpty()) {
                            jlError1.setText(" ");
                            ArrayList<Booking> bookingList = new ArrayList<>();                                          
                            for(int i=0; i<book.size(); i++) {
                                model1.addRow(new Object[] {book.get(i).getBookingID(), book.get(i).getStaff().getStaffID(), book.get(i).getCustomer().getCustID(), book.get(i).getCustomer().getCustName(), 
                                    tControl.selectRecordByBooking(book.get(i)).get(0).getSchedule().getRoute().getDestination(), book.get(i).getBookingDate(), book.get(i).getBookingTime()});                                
                                bookingList.add(book.get(i));
                            }
                            jlError1.setText(book.size() + " records found!");
                            jlError1.setForeground(Color.blue);
                            searchBookingList = bookingList;
                        } else {
                            jlError1.setText("Invalid booking record for this staff ID.");
                        }
                    } else {
                        jlError1.setText("Invalid staff ID in database.");
                    }                   
                }
            } else if(jcbCriteria.getSelectedItem().toString().equals("Customer ID")) {
                String id = jtfSearch.getText();
                if(id.equals("")) {
                    jlError1.setText("Please enter customer ID to search.");
                } else {
                    Customer cust = cControl.selectRecord(id);
                    if(cust!=null) {
                        ArrayList<Booking> book = bControl.selectRecordByCustomer(cust);
                        if(!book.isEmpty()) {
                            jlError1.setText(" ");
                            ArrayList<Booking> bookingList = new ArrayList<>();      
                            for(int i=0; i<book.size(); i++) {
                                model1.addRow(new Object[] {book.get(i).getBookingID(), book.get(i).getStaff().getStaffID(), book.get(i).getCustomer().getCustID(), book.get(i).getCustomer().getCustName(), 
                                    tControl.selectRecordByBooking(book.get(i)).get(0).getSchedule().getRoute().getDestination(), book.get(i).getBookingDate(), book.get(i).getBookingTime()});                                
                                bookingList.add(book.get(i));
                            }
                            jlError1.setText(book.size() + " records found!");
                            jlError1.setForeground(Color.blue);
                            searchBookingList = bookingList;
                        } else {
                            jlError1.setText("Invalid booking record for this customer ID.");
                        }
                    } else {
                        jlError1.setText("Invalid customer ID in database.");
                    }                                                 
                }
            } else if(jcbCriteria.getSelectedItem().toString().equals("Customer Name")) {
                String name = jtfSearch.getText();
                if(name.equals("")) {
                    jlError1.setText("Please enter customer name to search.");
                } else {
                    ArrayList<Customer> cust = cControl.selectRecordByName(name);
                    if(!cust.isEmpty()) {
                        ArrayList<Booking> allBook = new ArrayList<>();
                        int count = 0;
                        for(int i=0; i<cust.size(); i++) {
                            ArrayList<Booking> book = bControl.selectRecordByCustomer(cust.get(i));
                            if(!book.isEmpty()) {
                                for(int j=0; j<book.size(); j++) {
                                    allBook.add(book.get(j));
                                }
                            } else {
                                count++;
                            }
                        }
                        if(count!=cust.size()) {
                            jlError1.setText(" ");
                            ArrayList<Booking> bookingList = new ArrayList<>();      
                            for(int i=0; i<allBook.size(); i++) {
                                model1.addRow(new Object[] {allBook.get(i).getBookingID(), allBook.get(i).getStaff().getStaffID(), allBook.get(i).getCustomer().getCustID(), allBook.get(i).getCustomer().getCustName(), 
                                    tControl.selectRecordByBooking(allBook.get(i)).get(0).getSchedule().getRoute().getDestination(), allBook.get(i).getBookingDate(), allBook.get(i).getBookingTime()});                                
                                bookingList.add(allBook.get(i));
                            }
                            searchBookingList = bookingList;
                        } else {
                            jlError1.setText("Invalid booking record for this customer name.");
                        }
                    } else {
                        jlError1.setText("Invalid customer name in database.");
                    }                                                 
                }
            } else if(jcbCriteria.getSelectedItem().toString().equals("Destination")) {
                String dest = jtfSearch.getText();
                if(dest.equals("")) {
                    jlError1.setText("Please enter destination to search.");
                } else {                  
                    Route route = rControl.selectRecordByDestination(dest);
                    ArrayList<Booking> book1 = new ArrayList<>();
                    if(route!=null) {
                        ArrayList<Schedule> scheduleL = sControl.selectRecordByRoute(route);
                        if(!scheduleL.isEmpty()) {                         
                            for(int i=0; i<scheduleL.size(); i++) {
                                ArrayList<Booking> bookGet = tControl.selectTicketBookingRecord(scheduleL.get(i));
                                if(!bookGet.isEmpty()) {
                                    for(int j=0; j<bookGet.size(); j++) {
                                        book1.add(tControl.selectTicketBookingRecord(scheduleL.get(i)).get(j));
                                    }
                                }
                            }
                        } else {
                            jlError1.setText("No schedule record found for this destination.");
                        }
                    } else {
                        jlError1.setText("Invalid destination in database.");
                    }
                    
                    if(!book1.isEmpty()) {
                        searchBookingList = book1;
                        for(int i=0; i<book1.size(); i++) {
                            model1.addRow(new Object[] {book1.get(i).getBookingID(), book1.get(i).getStaff().getStaffID(), book1.get(i).getCustomer().getCustID(), book1.get(i).getCustomer().getCustName(), 
                            dest, book1.get(i).getBookingDate(), book1.get(i).getBookingTime()});                           
                        }
                        jlError1.setText(book1.size() + " records found!");
                        jlError1.setForeground(Color.blue);
                    } else {
                        jlError1.setText("No booking record found for this destination.");
                    }                        
                }
            } else if(jcbCriteria.getSelectedItem().toString().equals("Time")) {
                String value = jSpinner1.getModel().getValue().toString().substring(11, 19);
                if(value!=null) {
                    ArrayList<Booking> book1 = bControl.selectRecordByTime(value);
                    if(!book1.isEmpty()) {
                        searchBookingList = book1;
                        for(int i=0; i<book1.size(); i++) {
                            model1.addRow(new Object[] {book1.get(i).getBookingID(), book1.get(i).getStaff().getStaffID(), book1.get(i).getCustomer().getCustID(), book1.get(i).getCustomer().getCustName(), 
                            tControl.selectRecordByBooking(book1.get(i)).get(0).getSchedule().getRoute().getDestination(), book1.get(i).getBookingDate(), book1.get(i).getBookingTime()});                           
                        }
                        jlError1.setText(book1.size() + " records found!");
                        jlError1.setForeground(Color.blue);
                    } else {
                        jlError1.setText("No booking record found.");
                    }
                } else {
                    jlError1.setText("Please select time to search.");
                }                
            } else if(jcbCriteria.getSelectedItem().toString().equals("Time-Time")) {
                String value1 = jSpinner1.getModel().getValue().toString().substring(11, 19);
                String value2 = jSpinner2.getModel().getValue().toString().substring(11, 19);
                if(value1!=null&&value2!=null) {
                    ArrayList<Booking> book1 = bControl.selectRecordByTimeTime(value1, value2);
                    if(!book1.isEmpty()) {
                        searchBookingList = book1;
                        for(int i=0; i<book1.size(); i++) {
                            model1.addRow(new Object[] {book1.get(i).getBookingID(), book1.get(i).getStaff().getStaffID(), book1.get(i).getCustomer().getCustID(), book1.get(i).getCustomer().getCustName(), 
                            tControl.selectRecordByBooking(book1.get(i)).get(0).getSchedule().getRoute().getDestination(), book1.get(i).getBookingDate(), book1.get(i).getBookingTime()});                           
                        }
                        jlError1.setText(book1.size() + " records found!");
                        jlError1.setForeground(Color.blue);
                    } else {
                        jlError1.setText("No booking record found.");
                    }
                } else {
                    jlError1.setText("Please select time-time to search.");
                }     
            } else if(jcbCriteria.getSelectedItem().toString().equals("Date")) {
                String dateZ = ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText();
                if(!dateZ.equals("")) {
                    ArrayList<Booking> book1 = bControl.selectRecordByDate(dateZ);
                    if(!book1.isEmpty()) {
                       searchBookingList = book1;
                        for(int i=0; i<book1.size(); i++) {
                            model1.addRow(new Object[] {book1.get(i).getBookingID(), book1.get(i).getStaff().getStaffID(), book1.get(i).getCustomer().getCustID(), book1.get(i).getCustomer().getCustName(), 
                            tControl.selectRecordByBooking(book1.get(i)).get(0).getSchedule().getRoute().getDestination(), book1.get(i).getBookingDate(), book1.get(i).getBookingTime()});                           
                        }
                        jlError1.setText(book1.size() + " records found!");
                        jlError1.setForeground(Color.blue);
                    } else {
                        jlError1.setText("No booking record found.");
                    }
                } else {
                    jlError1.setText("Please select a date to search.");
                }
            } else if(jcbCriteria.getSelectedItem().toString().equals("Date-Date")) {
                String dateZ = ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText();
                String dateY = ((JTextField)jDateChooser2.getDateEditor().getUiComponent()).getText();
                if(!dateZ.equals("")&&!dateY.equals("")) {
                    ArrayList<Booking> book1 = bControl.selectRecordByDateDate(dateZ, dateY);
                    if(!book1.isEmpty()) {
                       searchBookingList = book1;
                        for(int i=0; i<book1.size(); i++) {
                            model1.addRow(new Object[] {book1.get(i).getBookingID(), book1.get(i).getStaff().getStaffID(), book1.get(i).getCustomer().getCustID(), book1.get(i).getCustomer().getCustName(), 
                            tControl.selectRecordByBooking(book1.get(i)).get(0).getSchedule().getRoute().getDestination(), book1.get(i).getBookingDate(), book1.get(i).getBookingTime()});                           
                        }
                        jlError1.setText(book1.size() + " records found!");
                        jlError1.setForeground(Color.blue);
                    } else {
                        jlError1.setText("No booking record found.");
                    }
                } else {
                    jlError1.setText("Please select date-date to search.");
                }
            } else if(jcbCriteria.getSelectedItem().toString().equals("Date&Time")) {
                String dateZ = ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText();
                String value1 = jSpinner1.getModel().getValue().toString().substring(11, 19);
                if(!dateZ.equals("")&&value1!=null) {
                    ArrayList<Booking> book1 = bControl.selectRecordByDateTime(dateZ, value1);
                    if(!book1.isEmpty()) {
                        searchBookingList = book1;
                        for(int i=0; i<book1.size(); i++) {
                            model1.addRow(new Object[] {book1.get(i).getBookingID(), book1.get(i).getStaff().getStaffID(), book1.get(i).getCustomer().getCustID(), book1.get(i).getCustomer().getCustName(), 
                            tControl.selectRecordByBooking(book1.get(i)).get(0).getSchedule().getRoute().getDestination(), book1.get(i).getBookingDate(), book1.get(i).getBookingTime()});                           
                        }
                        jlError1.setText(book1.size() + " records found!");
                        jlError1.setForeground(Color.blue);
                    } else {
                        jlError1.setText("No booking record found.");
                    }
                } else {
                    jlError1.setText("Please select date and time to search.");
                }
            } else if(jcbCriteria.getSelectedItem().toString().equals("Date&Time-Date&Time")) {
                String dateZ = ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText();
                String dateY = ((JTextField)jDateChooser2.getDateEditor().getUiComponent()).getText();
                String value1 = jSpinner1.getModel().getValue().toString().substring(11, 19);
                String value2 = jSpinner2.getModel().getValue().toString().substring(11, 19);
                if(!dateZ.equals("")&&value1!=null&&!dateY.equals("")&&value2!=null) {
                    ArrayList<Booking> book1 = bControl.selectRecordByDateTimeDateTime(dateZ, value1, dateY, value2);
                    if(!book1.isEmpty()) {
                        searchBookingList = book1;
                        for(int i=0; i<book1.size(); i++) {
                            model1.addRow(new Object[] {book1.get(i).getBookingID(), book1.get(i).getStaff().getStaffID(), book1.get(i).getCustomer().getCustID(), book1.get(i).getCustomer().getCustName(), 
                            tControl.selectRecordByBooking(book1.get(i)).get(0).getSchedule().getRoute().getDestination(), book1.get(i).getBookingDate(), book1.get(i).getBookingTime()});                           
                        }
                        jlError1.setText(book1.size() + " records found!");
                        jlError1.setForeground(Color.blue);
                    } else {
                        jlError1.setText("No booking record found.");
                    }
                } else {
                    jlError1.setText("Please select date and time to search.");
                }
            }
        });
        //"Booking ID", "Staff ID", "Customer ID", "Customer Name", "Destination", "Booking Date", "Booking Time"
        //"Booking ID", "Staff ID", "Customer ID", "Customer Name", "Destination", "Time", "Time-Time", "Date", "Date-Date", "Date&Time", "Date&Time-Date&Time"      
        
        jtfSearch.addActionListener(jbtSearch.getActionListeners()[0]);
        
        DocumentListener errorMsgClear = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                jlError1.setText(" ");
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                jlError1.setText(" ");
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }           
        }; 
        jtfSearch.getDocument().addDocumentListener(errorMsgClear);
        
        jbtAll.addActionListener((ActionEvent e) -> { 
            jbtReprint.setEnabled(false);
            jbtCancel.setEnabled(false);
            jtfSearch.setText("");
            jcbCriteria.setSelectedIndex(0);
            jtfSearch.setEnabled(true);
            jSpinner1.setEnabled(false);
            jSpinner2.setEnabled(false);
            jDateChooser1.setEnabled(false);
            jDateChooser2.setEnabled(false);
            model2.setRowCount(0);
            jlError1.setText(" ");
            ArrayList<Booking> bookingList = bControl.selectAllRecord();
            DefaultTableModel model = (DefaultTableModel) bookingTable.getModel();
            model.setRowCount(0);
            if(!bookingList.isEmpty()) {
                searchBookingList = bookingList;
                for(int i=0; i<bookingList.size(); i++) {
                    model.addRow(new Object[]{bookingList.get(i).getBookingID(), bookingList.get(i).getStaff().getStaffID(), bookingList.get(i).getCustomer().getCustID(), bookingList.get(i).getCustomer().getCustName(), tControl.selectRecordByBooking(bookingList.get(i)).get(0).getSchedule().getRoute().getDestination(), bookingList.get(i).getBookingDate(), bookingList.get(i).getBookingTime()});
                }
                jlError1.setText(bookingList.size() + " records found!");
                jlError1.setForeground(Color.blue);
            }
        }); 
        
        ticketTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {   
                JTable tablee = (JTable)evt.getSource();
                if(evt.getClickCount()==1) {
                    if(tablee.getSelectedRow()>=0) {
                        int row = tablee.getSelectedRow();
                        if(tablee.getModel().getValueAt(tablee.convertRowIndexToModel(row), 4).toString().equals("A")) {
                            jbtReprint.setEnabled(true);
                            jbtCancel.setEnabled(true);
                        } else {
                            jbtReprint.setEnabled(false);
                            jbtCancel.setEnabled(false);
                        }
                    } else {
                        jbtReprint.setEnabled(false);
                        jbtCancel.setEnabled(false);
                    }
                }
            }
        });

        bookingTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {                                                      
                JTable tablee =(JTable)evt.getSource();
                DefaultTableModel model = (DefaultTableModel) ticketTable.getModel();
                if(evt.getClickCount() == 1) {
                    model.setRowCount(0);
                    jbtReprint.setEnabled(false);
                    jbtCancel.setEnabled(false);
                    int row = tablee.getSelectedRow();
                    String bookingID = tablee.getModel().getValueAt(tablee.convertRowIndexToModel(row), 0).toString();
                    ArrayList<Ticket> ticketList = tControl.selectRecordByBooking(bControl.selectRecord(bookingID));
                    if(!ticketList.isEmpty()) {
                        for(int i=0; i<ticketList.size(); i++) {
                            model.addRow(new Object[]{ticketList.get(i).getTicketID(), ticketList.get(i).getSeatNo(), ticketList.get(i).getPassengerName(), ticketList.get(i).getPassengerContactNo(), ticketList.get(i).getStatus()+""});
                        }
                    }
                }
            }  
        });                                       

        jbtFilter.addActionListener((ActionEvent e) -> {   
            jbtReprint.setEnabled(false);
            jbtCancel.setEnabled(false);
            model2.setRowCount(0);
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
        });                                         

        jtfFilter.addActionListener((ActionEvent e) -> {  
            jbtReprint.setEnabled(false);
            jbtCancel.setEnabled(false);
            model2.setRowCount(0);
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
        });                                         

        jbtFilter2.addActionListener((ActionEvent e) -> { 
            jbtReprint.setEnabled(false);
            jbtCancel.setEnabled(false);
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
        });                                          


        jtfFilter2.addActionListener((ActionEvent e) -> {
            jbtReprint.setEnabled(false);
            jbtCancel.setEnabled(false);
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
        });   
        
        bookingTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jbtEdit.addActionListener((ActionEvent e) -> {
            if(bookingTable.getSelectedRow()!=-1) {
                Booking book = searchBookingList.get(bookingTable.convertRowIndexToModel(bookingTable.getSelectedRow()));
                ArrayList<Ticket> ticket = tControl.selectRecordByBooking(bControl.selectRecord(book.getBookingID()));
                if(!ticket.isEmpty()) {
                    menuPanel.removeAll();
                    menuPanel.revalidate();
                    menuPanel.repaint();
                    menuPanel.add(editBookingPanel(book, ticket));
                }               
            } else {
                //System.out.println(searchBookingList.get(bookingTable.convertRowIndexToModel(bookingTable.getSelectedRow())).getBookingID());
                
            }
        });
        
        jbtCancel.addActionListener((ActionEvent e) -> {
            int row = ticketTable.getSelectedRow();
            String ticketID = ticketTable.getModel().getValueAt(ticketTable.convertRowIndexToModel(row), 0).toString();
            Ticket ticket = tControl.selectRecord(ticketID);
            if(ticket!=null) {
                tControl.cancelRecordStatus(ticket);
                ticketTable.getModel().setValueAt("C", ticketTable.convertRowIndexToModel(row), 4);
                jbtCancel.setEnabled(false);
                jbtReprint.setEnabled(false);
            }
        });
        
        jbtReprint.addActionListener((ActionEvent evt) -> {           
            int row = ticketTable.getSelectedRow();
            String ticketID = ticketTable.getModel().getValueAt(ticketTable.convertRowIndexToModel(row), 0).toString();
            Ticket ticket = tControl.selectRecord(ticketID);
            if(ticket!=null) {  
                currentTicketList = new ArrayList<>();
                currentTicketList.add(ticket);
                this.reprintCounter = true;
                printTicket();
                jbtReprint.setEnabled(false);              
            }
        });
        
        jbtBack.addActionListener((ActionEvent e) -> {
            this.reprintCounter = false;
            menuPanel.removeAll();
            menuPanel.revalidate();
            menuPanel.repaint();
            menuPanel.add(msg);
            menuPanel.add(menuPane, BorderLayout.CENTER);
        });
        
        return bookingPanel;
    }  
    
    public JPanel editBookingPanel(Booking booking, ArrayList<Ticket> ticketLL) {
        
        JPanel editBookingPanel = new JPanel();       
        JPanel jPanel1 = new javax.swing.JPanel();
        JLabel jLabel1 = new javax.swing.JLabel("Edit Booking Panel");
        JLabel jLabel2 = new javax.swing.JLabel("Booking ID");
        JTextField jtfBookingID = new javax.swing.JTextField();
        JTextField jtfCustID = new javax.swing.JTextField();
        JTextField jtfCustName = new javax.swing.JTextField();
        JLabel jLabel5 = new javax.swing.JLabel("Customer ID");
        JTextField jtfDest = new javax.swing.JTextField();
        JLabel jLabel6 = new javax.swing.JLabel("Customer Name");
        JTextField jtfDepartDate = new javax.swing.JTextField();
        JLabel jLabel7 = new javax.swing.JLabel("Destination");
        JTextField jtfDepartTime = new javax.swing.JTextField();
        JLabel jLabel8 = new javax.swing.JLabel("Departure Date");
        JLabel jLabel3 = new javax.swing.JLabel("Departure Time");
        JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
        JTable ticketTable = new javax.swing.JTable();
        JPanel jPanel2 = new javax.swing.JPanel();
        JLabel jlInfo = new javax.swing.JLabel(" ");
        JButton jbtChange = new javax.swing.JButton("Change Ticket");
        JLabel jlError = new javax.swing.JLabel(" ");
        JButton jbtBack = new javax.swing.JButton("Back");
        this.editB = booking;
        this.editT = ticketLL;

        editBookingPanel.setBackground(new java.awt.Color(102, 102, 102));
        editBookingPanel.setMaximumSize(new java.awt.Dimension(740, 640));
        editBookingPanel.setMinimumSize(new java.awt.Dimension(740, 640));
        editBookingPanel.setPreferredSize(new java.awt.Dimension(740, 640));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jLabel1.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);       
        jtfBookingID.setEditable(false);
        jtfCustID.setEditable(false);
        jtfCustName.setEditable(false);
        jtfDest.setEditable(false);
        jtfDepartDate.setEditable(false);
        jtfDepartTime.setEditable(false);
      
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(217, 217, 217)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel3))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfBookingID, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfCustName, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfCustID, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfDest, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfDepartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfDepartTime, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(255, 255, 255)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtfBookingID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfCustID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfCustName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfDest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfDepartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfDepartTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        ticketTable.setAutoCreateRowSorter(true);
        ticketTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ticket ID", "Seat No", "Passenger Name", "Passenger Contact No", "Status", "Select"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(ticketTable);
        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jlInfo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(167, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jlInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbtChange)
                        .addGap(146, 146, 146))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jlError, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(181, 181, 181))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtChange))
                .addGap(18, 18, 18)
                .addComponent(jlError, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(editBookingPanel);
        editBookingPanel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbtBack)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtBack)
                .addContainerGap())
        );
        
        jtfBookingID.setText(booking.getBookingID());
        jtfCustID.setText(booking.getCustomer().getCustID());
        jtfCustName.setText(booking.getCustomer().getCustName());
        jtfDest.setText(ticketLL.get(0).getSchedule().getRoute().getDestination());
        jtfDepartDate.setText(ticketLL.get(0).getSchedule().getDepartureDate());
        jtfDepartTime.setText(ticketLL.get(0).getSchedule().getDepartureTime());
        
        DefaultTableModel model = (DefaultTableModel) ticketTable.getModel();
        model.setRowCount(0);
        ticketLLL = new ArrayList<>();
        for(int i=0; i<ticketLL.size(); i++) {
            if(ticketLL.get(i).getStatus()=='A') {
                ticketLLL.add(ticketLL.get(i));
            }
        }
        if(!ticketLLL.isEmpty()) {
            for(int i=0; i<ticketLLL.size(); i++) {             
                model.addRow(new Object[]{ticketLLL.get(i).getTicketID(), ticketLLL.get(i).getSeatNo(), ticketLLL.get(i).getPassengerName(), ticketLLL.get(i).getPassengerContactNo(), ticketLLL.get(i).getStatus()+"", false});
            }
        }
        
        if(booking.getCustomer().getStatus()==' ') {
            jlInfo.setText("The customer is not a member. Cannot change ticket.");
            jlInfo.setForeground(Color.red);
            jbtChange.setEnabled(false);
        } else {
            jlInfo.setText("The customer is a member. Can change ticket.");
            jlInfo.setForeground(Color.blue);
            jbtChange.setEnabled(true);
        }
        
        if(ticketLLL.isEmpty()) {
            jlInfo.setText("All tickets have been cancelled.");
            jlInfo.setForeground(Color.red);
            jbtChange.setEnabled(false);
        }
                   
        ArrayList<Ticket> ticketEdit = new ArrayList<>();
        jbtChange.addActionListener((ActionEvent e) -> {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
            Date date = new Date();
            try{
                date = format.parse(jtfDepartDate.getText());
            } catch(Exception ex) {
                System.out.println(ex);
            }
            LocalDateTime ldt = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
            LocalDateTime ldt2 = ldt.minusDays(3);
            Date out = Date.from(ldt2.atZone(ZoneId.systemDefault()).toInstant());
            if(new Date().before(out)) {           
                int count = 0;
                for(int i=0; i<ticketLLL.size(); i++) {
                    if((Boolean)ticketTable.getModel().getValueAt(ticketTable.convertRowIndexToModel(i), 5)) {
                        count++;
                        ticketEdit.add(ticketLLL.get(ticketTable.convertRowIndexToModel(i)));
                    }   
                }
                if(count>0) {
                    jlError.setText(" ");
                    this.editTT = ticketEdit;
                    this.editBooking = true;
                    menuPanel.removeAll();
                    menuPanel.revalidate();
                    menuPanel.repaint();
                    menuPanel.add(ticketing()); 
                } else {
                    jlError.setText("Please select one or more ticket for ticket changing.");
                    jlError.setForeground(Color.red);
                }
            } else {
                jlError.setText("Cannot change tickets. Already past three days after booking.");
                jlError.setForeground(Color.red);
            }
        });
        
        jbtBack.addActionListener((ActionEvent e) -> {
            this.editBooking = false;
            menuPanel.removeAll();
            menuPanel.revalidate();
            menuPanel.repaint();
            menuPanel.add(bookingPanel());           
        });
      
        return editBookingPanel;
    }
    
    public JPanel updateTicketPanel() {
        
        JPanel updateTicketPanel = new JPanel();
        ButtonGroup buttonGroup1 = new javax.swing.ButtonGroup();
        JLabel jLabel1 = new javax.swing.JLabel();
        JPanel jPanel1 = new javax.swing.JPanel();
        JLabel jLabel2 = new javax.swing.JLabel();
        JLabel jLabel3 = new javax.swing.JLabel();
        JTextField jtfBookingID = new javax.swing.JTextField();
        JTextField jtfScheduleID = new javax.swing.JTextField();
        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        JTable newTable = new javax.swing.JTable();
        JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
        JTable oldTable = new javax.swing.JTable();
        JPanel jPanel2 = new javax.swing.JPanel();
        JLabel jlCustContact = new javax.swing.JLabel();
        JTextField jtfCustContact = new javax.swing.JTextField();
        JLabel jlCustIC = new javax.swing.JLabel();
        JTextField jtfCustIC = new javax.swing.JTextField();
        JLabel jlCustName = new javax.swing.JLabel();
        JTextField jtfCustName = new javax.swing.JTextField();
        JLabel jLabel4 = new javax.swing.JLabel();
        JTextField jtfExtra = new javax.swing.JTextField();
        JLabel jLabel5 = new javax.swing.JLabel();
        JTextField jtfFee = new javax.swing.JTextField();
        JRadioButton jrbCC = new javax.swing.JRadioButton();
        JRadioButton jrbCash = new javax.swing.JRadioButton();
        JTextField jtfCC = new javax.swing.JTextField();
        JTextField jtfCash = new javax.swing.JTextField();
        JLabel jLabel6 = new javax.swing.JLabel();
        JTextField jtfNett = new javax.swing.JTextField();
        JLabel jLabel7 = new javax.swing.JLabel();
        JTextField jtfPaid = new javax.swing.JTextField();
        JLabel jLabel8 = new javax.swing.JLabel();
        JTextField jtfChanges = new javax.swing.JTextField();
        JButton jbtSubmit = new javax.swing.JButton();
        JLabel jlError = new javax.swing.JLabel();
        JButton jbtReceipt = new javax.swing.JButton();
        JButton jbtTicket = new javax.swing.JButton();
        JButton jbtBack = new javax.swing.JButton();
        JButton jbtBooking = new javax.swing.JButton();

        updateTicketPanel.setBackground(new java.awt.Color(102, 102, 102));
        updateTicketPanel.setMaximumSize(new java.awt.Dimension(740, 640));
        updateTicketPanel.setMinimumSize(new java.awt.Dimension(740, 640));
        updateTicketPanel.setPreferredSize(new java.awt.Dimension(740, 640));

        jLabel1.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Update Ticket Panel");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel2.setText("New Booking ID");

        jLabel3.setText("New Schedule ID");

        jtfBookingID.setEditable(false);

        jtfScheduleID.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(210, 210, 210)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jtfBookingID, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfScheduleID, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtfBookingID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtfScheduleID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        newTable.setAutoCreateRowSorter(true);
        newTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "New Ticket No", "Name", "Contact No", "Seat No", "Price"
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
        newTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(newTable);

        oldTable.setAutoCreateRowSorter(true);
        oldTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Old Ticket No", "Name", "Contact No", "Seat No", "Price"
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
        oldTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(oldTable);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), "Payment Details", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Fax", 1, 14))); // NOI18N

        jlCustContact.setText("Customer Contact No");

        jtfCustContact.setEditable(false);

        jlCustIC.setText("Customer IC No");

        jtfCustIC.setEditable(false);

        jlCustName.setText("Customer Name");

        jtfCustName.setEditable(false);

        jLabel4.setText("Extra Amount (RM)");

        jtfExtra.setEditable(false);

        jLabel5.setText("Change Fees (RM)");

        jtfFee.setEditable(false);

        buttonGroup1.add(jrbCC);
        jrbCC.setText("Credit Card");

        buttonGroup1.add(jrbCash);
        jrbCash.setSelected(true);
        jrbCash.setText("Cash");

        jLabel6.setText("Nett Amount (RM)");

        jtfNett.setEditable(false);

        jLabel7.setText("Paid Amount (RM)");

        jtfPaid.setEditable(false);

        jLabel8.setText("Changes (RM)");

        jtfChanges.setEditable(false);

        jbtSubmit.setText("Submit");

        jlError.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlError.setText(" ");

        jbtReceipt.setText("Print Receipt");

        jbtTicket.setText("Print Tickets");

        jbtBack.setText("Back");

        jbtBooking.setText("Back to Booking");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 12, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jtfCustName, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jlError, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtSubmit))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jlCustName, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jrbCC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jrbCash, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jlCustIC, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jtfCustIC, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                                            .addComponent(jtfCC)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtfCash, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jlCustContact)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jtfCustContact, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jtfNett, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel7)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jtfPaid, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(87, 87, 87)
                                        .addComponent(jtfChanges, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel4))
                                        .addGap(65, 65, 65)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jtfFee, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jtfExtra, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(49, 49, 49))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtReceipt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtTicket)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtBack)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtBooking)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlCustName)
                    .addComponent(jtfCustName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jtfExtra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlCustContact)
                    .addComponent(jtfCustContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jtfFee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlCustIC)
                    .addComponent(jtfCustIC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jtfNett, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrbCC)
                    .addComponent(jtfCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jtfPaid, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(jtfChanges, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jrbCash)
                        .addComponent(jtfCash, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtSubmit)
                    .addComponent(jbtReceipt)
                    .addComponent(jbtTicket)
                    .addComponent(jbtBack)
                    .addComponent(jlError, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtBooking))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(updateTicketPanel);
        updateTicketPanel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(242, 242, 242)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addComponent(jScrollPane1)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        
        this.jtfCC.setText("");
        this.jtfCash.setText("");
        
        DefaultTableModel modelN = (DefaultTableModel) newTable.getModel();
        DefaultTableModel modelO = (DefaultTableModel) oldTable.getModel();
        if(bControl.selectLatestRecord()==null) {
            jtfBookingID.setText("BK000001");
        } else {
            Booking booking = bControl.selectLatestRecord();
            jtfBookingID.setText(booking.getBookingID().substring(0, 2) + String.format("%06d", Integer.parseInt(booking.getBookingID().substring(2, 8))+1));
        }
        jtfScheduleID.setText(ticketList.get(0).getSchedule().getScheduleID());
        jtfScheduleID.setToolTipText("CJH Terminal to " + editTT.get(0).getSchedule().getRoute().getDestination() + " - Date: " + editTT.get(0).getSchedule().getDepartureDate() + " Time: " + editTT.get(0).getSchedule().getDepartureTime());
        //double total = 0;
        for(int i=0; i<editTT.size(); i++) {
            modelO.addRow(new Object[] {editTT.get(i).getTicketID(), editTT.get(i).getPassengerName(), editTT.get(i).getPassengerContactNo(), editTT.get(i).getSeatNo(), String.format("%.2f", editTT.get(i).getSchedule().getRoute().getPrice().getPrice())});
            //total += editTT.get(i).getSchedule().getRoute().getPrice().getPrice();
        }
        
        for(int i=0; i<ticketList.size(); i++) {
            modelN.addRow(new Object[] {ticketList.get(i).getTicketID(), ticketList.get(i).getPassengerName(), ticketList.get(i).getPassengerContactNo(), ticketList.get(i).getSeatNo(), String.format("%.2f", ticketList.get(i).getSchedule().getRoute().getPrice().getPrice())});
            //total += editTT.get(i).getSchedule().getRoute().getPrice().getPrice();
        }
        
        jtfCustName.setText(editB.getCustomer().getCustName());
        jtfCustContact.setText(editB.getCustomer().getContactNo());
        jtfCustIC.setText(editB.getCustomer().getIcNo());
        jbtReceipt.setEnabled(false);
        jbtTicket.setEnabled(false);
        jbtBooking.setEnabled(false);
        //jtfExtra.setText("$$");
        //jtfFee.setText("$$");
        
        double extra = 0;
        for(int i=0; i<ticketList.size(); i++) {
            double diff = ticketList.get(i).getSchedule().getRoute().getPrice().getPrice()-editTT.get(i).getSchedule().getRoute().getPrice().getPrice();
            if(diff>0) {              
                extra+=diff;
            } 
        }
        jtfExtra.setText(String.format("%.2f", extra));
        double fee = 10.00;
        jtfFee.setText(String.format("%.2f", fee*ticketList.size()));
        double nett = Double.parseDouble(jtfExtra.getText())+Double.parseDouble(jtfFee.getText());
        jtfNett.setText(String.format("%.2f", nett));
        jtfCC.setEnabled(false);
        
        jrbCC.addActionListener((ActionEvent e) -> {
            jtfCC.setEnabled(true);
            jtfCash.setEnabled(false);
            jtfCash.setText("");
        });
        
        jrbCash.addActionListener((ActionEvent e) -> {
            jtfCash.setEnabled(true);
            jtfCC.setEnabled(false);
            jtfCC.setText("");
        });
        
        Calendar cal = Calendar.getInstance();
        jbtSubmit.addActionListener((ActionEvent e) -> {            
            jlError.setText(" ");
            if(jrbCC.isSelected()) {
                String ccEntered = jtfCC.getText();
                if(ccEntered.equals("")) {
                    jlError.setText("Please enter a valid credit card number.");
                    jlError.setForeground(Color.red);
                } else if(!ccEntered.matches("^4[0-9]{12}(?:[0-9]{3})?$")&&!ccEntered.matches("^5[1-5][0-9]{14}$")) {
                    jlError.setText("Invalid credit card format. (VISA/MasterCard)");
                    jlError.setForeground(Color.red);
                } else {
                    jlError.setText("Payment is done!!!");
                    jlError.setForeground(Color.blue);
                    jtfPaid.setText(jtfNett.getText());
                    jtfChanges.setText("0");
                    
                    currentCustomer = editB.getCustomer();
                    Booking newBooking = new Booking(jtfBookingID.getText(), staffLogin, editB.getCustomer(), new SimpleDateFormat("HH:mm:ss").format(cal.getTime()), new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));
                    bControl.insertRecord(newBooking);
                    currentBooking = newBooking;
                    for(int i=0; i<ticketList.size(); i++) {
                        Ticket ticket = new Ticket(ticketList.get(i).getTicketID(), newBooking, ticketList.get(i).getSchedule(), ticketList.get(i).getSeatNo(), ticketList.get(i).getPassengerName(), ticketList.get(i).getPassengerContactNo(), ticketList.get(i).getStatus());
                        tControl.insertRecord(ticket);
                        currentTicketList.add(ticket);
                        tControl.cancelRecordStatus(editTT.get(i));
                    }        
                    jbtReceipt.setEnabled(true);
                    jbtTicket.setEnabled(true);                   
                    jbtBack.setEnabled(false);
                    jbtSubmit.setEnabled(false);
                    jrbCash.setEnabled(false);
                    jrbCC.setEnabled(false);
                    jtfCC.setEditable(false);
                    jbtBooking.setEnabled(true);
                    this.jtfTotalAmt.setText(jtfExtra.getText());
                    this.jtfGST.setText(jtfFee.getText());
                    this.jtfNett.setText(jtfNett.getText());
                    this.jtfPaid.setText(jtfPaid.getText());
                    this.jtfChanges.setText(jtfChanges.getText());
                    this.jtfCC.setText(jtfCC.getText());
                }
            } else if(jrbCash.isSelected()) {
                String cashEntered = jtfCash.getText();
                if(cashEntered.equals("")) {
                    jlError.setText("Please enter a valid cash amount");
                    jlError.setForeground(Color.red);
                } else if(!cashEntered.matches("^(\\.\\d{1,2}|\\d{1,5}\\.?\\d{0,2})$")) {
                    jlError.setText("Invalid cash format. Max(RMXXXXX.XX)");
                    jlError.setForeground(Color.red);
                } else if(Double.parseDouble(cashEntered)<Double.parseDouble(jtfNett.getText())) {
                    jlError.setText("Invalid cash amount.");
                    jlError.setForeground(Color.red);
                } else {
                    jlError.setText("Payment is done!!!");
                    jlError.setForeground(Color.blue);
                    jtfPaid.setText(String.format("%.2f", Double.parseDouble(cashEntered)));
                    jtfChanges.setText(String.format("%.2f", Double.parseDouble(cashEntered)-Double.parseDouble(jtfNett.getText())));
                    
                    currentCustomer = editB.getCustomer();
                    Booking newBooking = new Booking(jtfBookingID.getText(), staffLogin, editB.getCustomer(), new SimpleDateFormat("HH:mm:ss").format(cal.getTime()), new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));
                    bControl.insertRecord(newBooking);
                    currentBooking = newBooking;
                    for(int i=0; i<ticketList.size(); i++) {
                        Ticket ticket = new Ticket(ticketList.get(i).getTicketID(), newBooking, ticketList.get(i).getSchedule(), ticketList.get(i).getSeatNo(), ticketList.get(i).getPassengerName(), ticketList.get(i).getPassengerContactNo(), ticketList.get(i).getStatus());
                        tControl.insertRecord(ticket);
                        currentTicketList.add(ticket);
                        tControl.cancelRecordStatus(editTT.get(i));
                    }        
                    jbtReceipt.setEnabled(true);
                    jbtTicket.setEnabled(true);                   
                    jbtBack.setEnabled(false);
                    jbtSubmit.setEnabled(false);
                    jrbCC.setEnabled(false);
                    jrbCash.setEnabled(false);
                    jtfCash.setEditable(false);
                    jbtBooking.setEnabled(true);
                    this.jtfTotalAmt.setText(jtfExtra.getText());
                    this.jtfGST.setText(jtfFee.getText());
                    this.jtfNett.setText(jtfNett.getText());
                    this.jtfPaid.setText(jtfPaid.getText());
                    this.jtfChanges.setText(jtfChanges.getText());
                    this.jtfCash.setText(jtfCash.getText());
                }
            }
        });
        
        jbtReceipt.addActionListener((ActionEvent e) -> {
            jbtReceipt.setText("RPrinted");
            printReceipt();                   
        });
        
        jbtTicket.addActionListener((ActionEvent e) -> {
            jbtTicket.setText("TPrinted");
            printTicket();
        });
        
        jbtBack.addActionListener((ActionEvent e) -> {
            menuPanel.removeAll();
            menuPanel.revalidate();
            menuPanel.repaint();
            menuPanel.add(psgForm(countList, ticketList.get(0).getSchedule()), BorderLayout.CENTER);
        });     
        
        jbtBooking.addActionListener((ActionEvent e) -> {
            this.countList.clear();
            this.ticketList.clear();
            this.currentTicketList.clear();
            this.currentCustomer = null;
            this.currentBooking = null;
            this.editBooking = false;
            menuPanel.removeAll();
            menuPanel.revalidate();
            menuPanel.repaint();
            menuPanel.add(bookingPanel(), BorderLayout.CENTER);
        });
        
        return updateTicketPanel;
    }
    
    public JPanel membershipPanel() {
        
        JPanel membershipPanel = new JPanel();        
        JPanel jPanel1 = new javax.swing.JPanel();
        JLabel jLabel1 = new javax.swing.JLabel();
        JComboBox jcbCriteria = new javax.swing.JComboBox();
        JTextField jtfSearch = new javax.swing.JTextField();
        JButton jbtSearch = new javax.swing.JButton();
        JLabel jLabel2 = new javax.swing.JLabel();
        JPanel jPanel2 = new javax.swing.JPanel();
        JLabel jLabel3 = new javax.swing.JLabel();
        JLabel jLabel4 = new javax.swing.JLabel();
        JLabel jLabel5 = new javax.swing.JLabel();
        JLabel jLabel6 = new javax.swing.JLabel();
        JLabel jLabel7 = new javax.swing.JLabel();
        JTextField jtfContact = new javax.swing.JTextField();
        JTextField jtfName = new javax.swing.JTextField();
        JTextField jtfID = new javax.swing.JTextField();
        JTextField jtfIC = new javax.swing.JTextField();
        JTextField jtfPoint = new javax.swing.JTextField();
        JButton jbtModify = new javax.swing.JButton();
        JButton jbtSubmit = new javax.swing.JButton();
        JLabel jlError3 = new javax.swing.JLabel();
        JLabel jLabel9 = new javax.swing.JLabel();
        JLabel jlError1 = new javax.swing.JLabel();
        JPanel jPanel3 = new javax.swing.JPanel();
        JLabel jLabel11 = new javax.swing.JLabel();
        JLabel jLabel12 = new javax.swing.JLabel();
        JLabel jLabel13 = new javax.swing.JLabel();
        JTextField jtfCreateName = new javax.swing.JTextField();
        JTextField jtfCreateContact = new javax.swing.JTextField();
        JTextField jtfCreateIC = new javax.swing.JTextField();
        JLabel jlError2 = new javax.swing.JLabel();
        JButton jbtCreate = new javax.swing.JButton();
        JButton jbtReset = new javax.swing.JButton();
        JLabel jLabel15 = new javax.swing.JLabel();
        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        JTable table = new javax.swing.JTable();
        JTextField jtfFilter = new javax.swing.JTextField();
        JButton jbtFilter = new javax.swing.JButton();
        JButton jbtBack = new javax.swing.JButton();
        JButton jbtAll = new javax.swing.JButton();
        JTextField jtfAddPoint = new javax.swing.JTextField();
        JButton jbtAddPoint = new javax.swing.JButton();
        JLabel jlError4 = new javax.swing.JLabel();

        membershipPanel.setBackground(new java.awt.Color(102, 102, 102));
        membershipPanel.setMaximumSize(new java.awt.Dimension(740, 640));
        membershipPanel.setMinimumSize(new java.awt.Dimension(740, 640));
        membershipPanel.setPreferredSize(new java.awt.Dimension(740, 640));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setMaximumSize(new java.awt.Dimension(342, 516));
        jPanel1.setMinimumSize(new java.awt.Dimension(342, 516));

        jLabel1.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel1.setText("Search");

        jcbCriteria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ID", "Name", "Contact No", "IC No", "Point" }));

        jbtSearch.setText("Search");

        jLabel2.setFont(new java.awt.Font("Lucida Fax", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Member Details");

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 4, true));

        jLabel3.setText("ID");

        jLabel4.setText("Name");

        jLabel5.setText("Contact No");

        jLabel6.setText("IC No");

        jLabel7.setText("Point");

        jtfContact.setEditable(false);

        jtfName.setEditable(false);

        jtfID.setEditable(false);

        jtfIC.setEditable(false);

        jtfPoint.setEditable(false);

        jbtModify.setText("Modify");

        jbtSubmit.setText("Submit");
        jbtSubmit.setEnabled(false);

        jlError3.setText(" ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jbtModify)
                .addGap(18, 18, 18)
                .addComponent(jbtSubmit)
                .addContainerGap(69, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlError3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))
                                .addGap(45, 45, 45))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfContact)
                            .addComponent(jtfName)
                            .addComponent(jtfID)
                            .addComponent(jtfIC)
                            .addComponent(jtfPoint))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtfID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfIC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfPoint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlError3, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtSubmit)
                    .addComponent(jbtModify))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel9.setFont(new java.awt.Font("Lucida Fax", 2, 14)); // NOI18N
        jLabel9.setText("Read & Update");

        jlError1.setText(" ");

        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 4, true));

        jLabel11.setText("Name");

        jLabel12.setText("Contact No");

        jLabel13.setText("IC No");

        jlError2.setText(" ");

        jbtCreate.setText("Create");

        jbtReset.setText("Reset");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlError2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(47, 47, 47)
                        .addComponent(jtfCreateName))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfCreateIC)
                            .addComponent(jtfCreateContact))))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jbtCreate)
                .addGap(18, 18, 18)
                .addComponent(jbtReset)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jtfCreateName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfCreateContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(7, 7, 7)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfCreateIC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlError2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtCreate)
                    .addComponent(jbtReset))
                .addContainerGap())
        );

        jLabel15.setFont(new java.awt.Font("Lucida Fax", 2, 14)); // NOI18N
        jLabel15.setText("Create");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jcbCriteria, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlError1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jtfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jbtSearch))))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbCriteria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlError1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Contact No", "IC No", "Point"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
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
        jScrollPane1.setViewportView(table);

        jbtFilter.setText("Filter");

        jbtBack.setText("Back");

        jbtAll.setText("Search All");

        jbtAddPoint.setText("Add Point For All Members");

        jlError4.setText(" ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(membershipPanel);
        membershipPanel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jlError4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtBack))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jtfFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtFilter)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtAll))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jtfAddPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtAddPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtFilter)
                            .addComponent(jbtAll))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfAddPoint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtAddPoint))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbtBack)
                            .addComponent(jlError4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );

        jbtModify.setEnabled(false);
        DefaultTableModel model = (DefaultTableModel) table.getModel();      
        sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);
        jlError1.setForeground(Color.red);
        jbtSearch.addActionListener((ActionEvent e) -> {
            jlError1.setText(" ");
            jlError3.setText(" ");
            jtfID.setText("");
            jtfName.setText("");
            jtfContact.setText("");
            jtfIC.setText("");
            jtfPoint.setText("");
            jbtModify.setEnabled(false);
            jbtSubmit.setEnabled(false);
            model.setRowCount(0);
            sorter.setRowFilter(null);
            jtfFilter.setText("");
            if(jcbCriteria.getSelectedItem().toString().equals("ID")) {
                String id = jtfSearch.getText();
                if(id.equals("")) {
                    jlError1.setText("Enter member ID to search.");                    
                } else {
                    Customer customer = cControl.selectRecord(id);
                    if(customer==null) {
                        jlError1.setText("Invalid member ID in database.");
                    } else {
                        if(customer.getStatus()=='M')
                            model.addRow(new Object[] {customer.getCustID(), customer.getCustName(), customer.getContactNo(), customer.getIcNo(), customer.getPoint()});
                        else {
                            jlError1.setText("Invalid member ID in database.");
                        }
                    }
                }
            } else if(jcbCriteria.getSelectedItem().toString().equals("Name")) {
                String name = jtfSearch.getText();
                if(name.equals("")) {
                    jlError1.setText("Enter member name to search.");
                } else {
                    ArrayList<Customer> customerList = cControl.selectRecordByName(name);
                    if(!customerList.isEmpty()) {
                        int invalid = 0;
                        for(int i=0; i<customerList.size(); i++) {
                            if(customerList.get(i).getStatus()=='M')
                                model.addRow(new Object[] {customerList.get(i).getCustID(), customerList.get(i).getCustName(), customerList.get(i).getContactNo(), customerList.get(i).getIcNo(), customerList.get(i).getPoint()});                                                              
                            else 
                                invalid++;
                        }
                        if(invalid==customerList.size()) {
                            jlError1.setText("No record found.");
                        }
                    } else {
                        jlError1.setText("No record found.");
                    }
                }
                
            } else if(jcbCriteria.getSelectedItem().toString().equals("Contact No")) {
                String contact = jtfSearch.getText();
                if(contact.equals("")) {
                    jlError1.setText("Enter contact no to search.");
                } else {
                    ArrayList<Customer> customerList = cControl.selectRecordByContactNo(contact);
                    if(!customerList.isEmpty()) {
                        int invalid = 0;
                        for(int i=0; i<customerList.size(); i++) {
                            if(customerList.get(i).getStatus()=='M')
                                model.addRow(new Object[] {customerList.get(i).getCustID(), customerList.get(i).getCustName(), customerList.get(i).getContactNo(), customerList.get(i).getIcNo(), customerList.get(i).getPoint()});                                                              
                            else 
                                invalid++;
                        }
                        if(invalid==customerList.size()) {
                            jlError1.setText("No record found.");
                        }
                    } else {
                        jlError1.setText("No record found.");
                    }
                }
                
            } else if(jcbCriteria.getSelectedItem().toString().equals("IC No")) {
                String ic = jtfSearch.getText();
                if(ic.equals("")) {
                    jlError1.setText("Enter member IC no to search.");                    
                } else {
                    Customer customer = cControl.selectRecordByIC(ic);
                    if(customer==null) {
                        jlError1.setText("Invalid member IC no in database.");
                    } else {
                        if(customer.getStatus()=='M')
                            model.addRow(new Object[] {customer.getCustID(), customer.getCustName(), customer.getContactNo(), customer.getIcNo(), customer.getPoint()});
                        else
                            jlError1.setText("Invalid member IC no in database.");
                    }
                }            
            } else if(jcbCriteria.getSelectedItem().toString().equals("Point")) {
                String point = jtfSearch.getText();
                if(point.equals("")) {
                    jlError1.setText("Enter member points to search.");
                } else {
                    if(!point.matches("^[0-9]*$")) {
                        jlError1.setText("Only enter numeric value for points.");
                    } else {
                        ArrayList<Customer> customerList = cControl.selectRecordByPoint(point);
                        if(!customerList.isEmpty()) {
                            int invalid = 0;
                            for(int i=0; i<customerList.size(); i++) {
                                if(customerList.get(i).getStatus()=='M')
                                    model.addRow(new Object[] {customerList.get(i).getCustID(), customerList.get(i).getCustName(), customerList.get(i).getContactNo(), customerList.get(i).getIcNo(), customerList.get(i).getPoint()});                                                              
                                else 
                                    invalid++;
                            }
                            if(invalid==customerList.size()) {
                                jlError1.setText("No record found.");
                            }
                        } else {
                            jlError1.setText("No record found.");
                        }
                    }
                }
            }
        });
        
        jtfSearch.addActionListener(jbtSearch.getActionListeners()[0]);
        
        jbtAll.addActionListener((ActionEvent e) -> {
            model.setRowCount(0);
            sorter.setRowFilter(null);
            jtfFilter.setText("");
            ArrayList<Customer> customerList = cControl.selectAllRecord();
            if(!customerList.isEmpty()) {
                for(int i=0; i<customerList.size(); i++) {
                    if(customerList.get(i).getStatus()=='M')
                        model.addRow(new Object[] {customerList.get(i).getCustID(), customerList.get(i).getCustName(), customerList.get(i).getContactNo(), customerList.get(i).getIcNo(), customerList.get(i).getPoint()});                   
                }
            }
            jtfSearch.setText("");
            jlError1.setText(" ");
            jlError3.setText(" ");
            jtfID.setText("");
            jtfName.setText("");
            jtfContact.setText("");
            jtfIC.setText("");
            jtfPoint.setText("");
            jbtModify.setEnabled(false);
            jbtSubmit.setEnabled(false);
        });
        
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                jbtModify.setEnabled(true);
                //JTable tablee =(JTable) evt.getSource();
                if(evt.getClickCount() == 1) {
                    int row = table.getSelectedRow();
                    String id = table.getModel().getValueAt(table.convertRowIndexToModel(row), 0).toString();
                    String name = table.getModel().getValueAt(table.convertRowIndexToModel(row), 1).toString();
                    String contact = table.getModel().getValueAt(table.convertRowIndexToModel(row), 2).toString();
                    String ic = table.getModel().getValueAt(table.convertRowIndexToModel(row), 3).toString();
                    String point = table.getModel().getValueAt(table.convertRowIndexToModel(row), 4).toString();
                    jtfID.setText(id);
                    jtfName.setText(name);
                    jtfContact.setText(contact);
                    jtfIC.setText(ic);
                    jtfPoint.setText(point);
                    jlError3.setText(" ");
                    jtfName.setEditable(false);
                    jtfContact.setEditable(false);
                    jtfIC.setEditable(false);
                    jtfPoint.setEditable(false);
                    jbtModify.setEnabled(true);
                    jbtSubmit.setEnabled(false);
                }
            }
        });
               
        jbtModify.addActionListener((ActionEvent e) -> {
            nameEx = jtfName.getText();
            contactEx = jtfContact.getText();
            icEx = jtfIC.getText();
            pointEx = jtfPoint.getText();
            jtfName.setEditable(true);
            jtfContact.setEditable(true);
            jtfIC.setEditable(true);
            jtfPoint.setEditable(true);
            jbtModify.setEnabled(false);
            jbtSubmit.setEnabled(true);
            jlError3.setText(" ");
        });
        
        jbtSubmit.addActionListener((ActionEvent e) -> {
            String name = jtfName.getText();
            String contact = jtfContact.getText();
            String ic = jtfIC.getText();
            String point = jtfPoint.getText();
            int invalid = 0;
            if(name.equals(nameEx)&&contact.equals(contactEx)&&ic.equals(icEx)&&point.equals(pointEx)) {
                jtfName.setEditable(false);
                jtfContact.setEditable(false);
                jtfIC.setEditable(false);
                jtfPoint.setEditable(false);
                jbtModify.setEnabled(true);
                jbtSubmit.setEnabled(false);
                jlError3.setText(" ");               
            } else if(name.equals("")||contact.equals("")||ic.equals("")||point.equals("")) {
                jlError3.setText("Please enter all the required information.");
                jlError3.setForeground(Color.red);
            } else {
                if(!name.equals(nameEx)) {
                    if(name.length()>30) {
                        jlError3.setText("Name not more than 30 words.");
                        jlError3.setForeground(Color.red);
                        invalid++;
                    } 
                }
                if(!contact.equals(contactEx)) {
                    if(!contact.matches("01[\\d]{1}-[\\d]{7,8}")) {
                        jlError3.setText("Invalid contact no format.");
                        jlError3.setForeground(Color.red);
                        invalid++;
                    }
                }
                if(!ic.equals(icEx)) {
                    if(!ic.matches("[\\d]{6}-[\\d]{2}-[\\d]{4}")) {
                        jlError3.setText("Invalid IC number format.");
                        jlError3.setForeground(Color.red);
                        invalid++;
                    } else {
                        String date = ic.substring(0, 6);                      
                        int errorCounter = 0;
                        Date valiDate = null;
                        try{
                            SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
                            format.setLenient(false);
                            valiDate = format.parse(date);
                        } catch(Exception ex) {
                            errorCounter++;
                        }
                        if(errorCounter!=0) {
                            jlError3.setText("Invalid IC number format.");
                            jlError3.setForeground(Color.red);
                            invalid++;
                        } else {  
                            if(cControl.selectRecordByIC(ic)!=null) {
                                jlError3.setText("IC exists in database.");
                                jlError3.setForeground(Color.red);
                                invalid++;     
                            }
                        }
                    }
                }
                if(!point.equals(pointEx)) {
                    if(!point.matches("^[0-9]*$")) {
                        jlError3.setText("Invalid point format.");
                        jlError3.setForeground(Color.red);
                        invalid++;
                    }
                }
                
                if(invalid==0) {
                    jlError3.setText("Update successfully.");
                    jlError3.setForeground(Color.blue);
                    jtfName.setEditable(false);
                    jtfContact.setEditable(false);
                    jtfIC.setEditable(false);
                    jtfPoint.setEditable(false);
                    jbtModify.setEnabled(true);
                    jbtSubmit.setEnabled(false);
                    Customer cust = new Customer(jtfID.getText(), name, contact, ic, Integer.parseInt(point));
                    cControl.modifyMemberRecord(cust);
                    int row = table.getSelectedRow();
                    ((DefaultTableModel)table.getModel()).setValueAt(name, table.convertRowIndexToModel(row), 1);
                    ((DefaultTableModel)table.getModel()).setValueAt(contact, table.convertRowIndexToModel(row), 2);
                    ((DefaultTableModel)table.getModel()).setValueAt(ic, table.convertRowIndexToModel(row), 3);
                    ((DefaultTableModel)table.getModel()).setValueAt(point, table.convertRowIndexToModel(row), 4);
                }
            }
        });
        
        DocumentListener errorMsgRefresh = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                jlError1.setText(" ");
                jlError3.setText(" ");
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                jlError1.setText(" ");
                jlError3.setText(" ");
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }           
        };    
        
        jtfSearch.getDocument().addDocumentListener(errorMsgRefresh);      
        
        jtfFilter.addActionListener((ActionEvent e) -> {            
            String text = jtfFilter.getText();
            if (text.length() == 0) {
                sorter.setRowFilter(null);
            } else {
                try {
                    sorter.setRowFilter(RowFilter.regexFilter(text));
                } catch (PatternSyntaxException pse) {
                    JOptionPane.showMessageDialog(null, "Bad regex pattern", "Bad regex pattern", JOptionPane.ERROR_MESSAGE);
                }
            }            
        });
        jbtFilter.addActionListener(jtfFilter.getActionListeners()[0]);
        
        DocumentListener filterRefresh = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = jtfFilter.getText();
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

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = jtfFilter.getText();
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

            @Override
            public void changedUpdate(DocumentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }           
        };  
        
        jtfFilter.getDocument().addDocumentListener(filterRefresh);
        
        jbtCreate.addActionListener((ActionEvent e) -> {
            String name = jtfCreateName.getText();
            String contact = jtfCreateContact.getText();
            String ic = jtfCreateIC.getText();
            if(name.equals("")||contact.equals("")||ic.equals("")) {
                jlError2.setText("Please enter all the required information.");
                jlError2.setForeground(Color.red);
            } else if(name.length()>30) {
                jlError2.setText("Name not more than 30 words.");
                jlError2.setForeground(Color.red);
            } else if(!contact.matches("01[\\d]{1}-[\\d]{7,8}")) {
                jlError2.setText("Invalid contact number format.");
                jlError2.setForeground(Color.red);
            } else if(!ic.matches("[\\d]{6}-[\\d]{2}-[\\d]{4}")) {
                jlError2.setText("Invalid IC number format.");
                jlError2.setForeground(Color.red);
            } else {
                String date = ic.substring(0, 6);                      
                int errorCounter = 0;
                Date valiDate = null;
                try{
                    SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
                    format.setLenient(false);
                    valiDate = format.parse(date);
                } catch(Exception ex) {
                    errorCounter++;
                }
                if(errorCounter!=0) {
                    jlError2.setText("Invalid IC number format.");
                    jlError2.setForeground(Color.red);
                } else {
                    jlError2.setText(" ");
                    Customer customer = cControl.selectRecordByNameContactICNo(name, contact, ic);               
                    if(customer!=null) {                  
                        if(customer.getStatus()==' ') {                                          
                            cControl.customerToMember(customer);
                            jlError2.setText("Membership created.");
                            jlError2.setForeground(Color.blue);
                        } else {
                            jlError2.setText("Already an existing member.");
                            jlError2.setForeground(Color.red);
                        }
                    } else {
                        if(cControl.selectRecordByIC(ic)!=null) {
                            jlError1.setText("IC number exists in database.");
                            jlError1.setForeground(Color.red);
                        } else {
                            Customer custToBe = cControl.selectLatestRecord();
                            String custID;
                            if(custToBe==null) {
                                custID = "C000001";
                            } else {
                                custID = custToBe.getCustID().substring(0, 1) + String.format("%06d", Integer.parseInt(custToBe.getCustID().substring(1, 7))+1);
                            }
                            customer = new Customer(custID, name, contact, ic, 'M', 0);
                            cControl.insertRecord(customer);
                            jlError1.setText("Membership created.");
                            jlError1.setForeground(Color.blue);
                        }
                    }  
                }
            }
        });
        
        jbtReset.addActionListener((ActionEvent e) -> {
            jtfCreateName.setText("");
            jtfCreateContact.setText("");
            jtfCreateIC.setText("");
            jlError2.setText(" ");
        });
        
        jbtAddPoint.addActionListener((ActionEvent e) -> {
            String point = jtfAddPoint.getText();
            if(point.equals("")) {
                jlError4.setText("Please enter points to add.");
                jlError4.setForeground(Color.red);
            } else {
                if(point.matches("^[0-9]*$")) {
                    ArrayList<Customer> custList = cControl.selectAllMemberRecord();
                    if(!custList.isEmpty()) {
                        for(int i=0; i<custList.size(); i++) {
                            cControl.increasePoint(custList.get(i), Integer.parseInt(point));
                        }    
                        jlError4.setText("Points added.");
                        jlError4.setForeground(Color.blue);
                        jtfAddPoint.setText("");
                    } else {
                        jlError4.setText("No member in database");
                        jlError4.setForeground(Color.red);
                    }
                } else {
                    jlError4.setText("Enter only numberic value for points.");
                    jlError4.setForeground(Color.red);
                }
            }
        });
        
        jbtBack.addActionListener((ActionEvent e) -> {
            menuPanel.removeAll();
            menuPanel.revalidate();
            menuPanel.repaint();
            menuPanel.add(msg);
            menuPanel.add(menuPane, BorderLayout.CENTER);
        });
        
        return membershipPanel;
    }
    
    public JPanel giftPanel() {
        
        JPanel giftPanel = new JPanel();
        JLabel jLabel1 = new javax.swing.JLabel();
        JPanel jPanel1 = new javax.swing.JPanel();
        JLabel jLabel2 = new javax.swing.JLabel();
        JLabel jLabel3 = new javax.swing.JLabel();
        JLabel jLabel4 = new javax.swing.JLabel();
        JTextField jtfName = new javax.swing.JTextField();
        JTextField jtfContact = new javax.swing.JTextField();
        JTextField jtfIC = new javax.swing.JTextField();
        JLabel jlError1 = new javax.swing.JLabel();
        JButton jbtCheck = new javax.swing.JButton();
        JButton jbtReset = new javax.swing.JButton();
        JPanel jPanel2 = new javax.swing.JPanel();
        JLabel jLabel6 = new javax.swing.JLabel();
        JTextField jtfPoint = new javax.swing.JTextField();
        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        JList jlGift = new javax.swing.JList();
        JLabel jlError2 = new javax.swing.JLabel();
        JButton jbtSubmit = new javax.swing.JButton();
        JLabel jLabel8 = new javax.swing.JLabel();
        JButton jbtBack = new javax.swing.JButton();
        JLabel jLabel9 = new javax.swing.JLabel();
        JTextField jtfMemberID = new javax.swing.JTextField();

        giftPanel.setBackground(new java.awt.Color(102, 102, 102));
        giftPanel.setMaximumSize(new java.awt.Dimension(740, 640));
        giftPanel.setMinimumSize(new java.awt.Dimension(740, 640));
        giftPanel.setPreferredSize(new java.awt.Dimension(740, 640));

        jLabel1.setFont(new java.awt.Font("Lucida Fax", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Gift Redemption");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel2.setText("Name");

        jLabel3.setText("Contact No");

        jLabel4.setText("IC No");

        jlError1.setText(" ");

        jbtCheck.setText("Check");

        jbtReset.setText("Reset");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jlError1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfContact, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfIC, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfName, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(96, 96, 96))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(172, 172, 172)
                .addComponent(jbtCheck)
                .addGap(18, 18, 18)
                .addComponent(jbtReset)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtfContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtfIC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlError1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtCheck)
                    .addComponent(jbtReset))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setText("Point");

        jtfPoint.setEditable(false);

        jlGift.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Blanket - Point Required: 5000", "Pillow - Point Required: 3000", "Keychain - Point Required: 1000", "CJH Special Mug - Point Required: 7000", "KFC Voucher (RM50) - Point Required: 50000" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jlGift);

        jlError2.setText(" ");

        jbtSubmit.setText("Submit");

        jLabel8.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Gift List");

        jbtBack.setText("Back");

        jLabel9.setText("Member ID");

        jtfMemberID.setEditable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(178, 178, 178)
                        .addComponent(jbtSubmit)
                        .addGap(18, 18, 18)
                        .addComponent(jbtBack))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jlError2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jtfMemberID)
                                    .addComponent(jtfPoint, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))))))
                .addContainerGap(99, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jtfMemberID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jtfPoint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlError2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtSubmit)
                    .addComponent(jbtBack))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(giftPanel);
        giftPanel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(126, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(124, 124, 124))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(231, 231, 231)))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        
        jbtSubmit.setEnabled(false);
        jbtCheck.addActionListener((ActionEvent e) -> {
            String name = jtfName.getText();
            String contact = jtfContact.getText();
            String ic = jtfIC.getText();
            if(name.equals("")||contact.equals("")||ic.equals("")) {
                jlError1.setText("Please fill in all the required information.");
                jlError1.setForeground(Color.red);
            } else {
                Customer cust = cControl.selectRecordByNameContactICNo(name, contact, ic);
                if(cust!=null) {
                    if(cust.getStatus()!='M') {
                        jlError1.setText("Not a member.");
                        jlError1.setForeground(Color.red);
                    } else {
                        jlError1.setText("Membership confirmed.");
                        jlError1.setForeground(Color.blue);
                        jtfMemberID.setText(cust.getCustID());
                        jtfPoint.setText(cust.getPoint()+"");
                        jbtSubmit.setEnabled(true);
                    }
                } else {
                    jlError1.setText("No record found.");
                    jlError1.setForeground(Color.red);
                }
            }
        });
        
        jbtReset.addActionListener((ActionEvent e) -> {
            jtfName.setText("");
            jtfContact.setText("");
            jtfIC.setText("");
            jlError1.setText(" ");
            jtfMemberID.setText("");
            jtfPoint.setText("");
            jlError2.setText(" ");
            jbtSubmit.setEnabled(false);
        });
        
        int[] pointNeeded = new int[] {5000,3000,1000,7000,50000};
        jbtSubmit.addActionListener((ActionEvent e) -> {
            int index = jlGift.getSelectedIndex();
            if(index<0) {
                jlError2.setText("Select one gift to redeem.");
                jlError2.setForeground(Color.red);
            } else {               
                if(pointNeeded[index]-Integer.parseInt(jtfPoint.getText())>0) {
                    jlError2.setText("Insufficient point.");
                    jlError2.setForeground(Color.red);
                } else {
                    cControl.decreasePoint(cControl.selectRecordByNameContactICNo(jtfName.getText(), jtfContact.getText(), jtfIC.getText()), Integer.parseInt(jtfPoint.getText())-pointNeeded[index]);
                    jtfPoint.setText(Integer.toString(Integer.parseInt(jtfPoint.getText())-pointNeeded[index]));
                    jlError2.setText(pointNeeded[index] + " points used. Redeemed successfully.");
                    jlError2.setForeground(Color.blue);
                }
            }
        }); 

        jbtBack.addActionListener((ActionEvent e) -> {
            menuPanel.removeAll();
            menuPanel.revalidate();
            menuPanel.repaint();
            menuPanel.add(msg);
            menuPanel.add(menuPane, BorderLayout.CENTER);
        });
        
        return giftPanel;
    }
    
    public JPanel busTrackingPanel() {
        
        JPanel busTrackingPanel = new JPanel(); 
        JLabel jLabel1 = new javax.swing.JLabel();
        JPanel jPanel1 = new javax.swing.JPanel();
        JPanel jPanel3 = new javax.swing.JPanel();
        JLabel jLabel2 = new javax.swing.JLabel();
        JLabel jLabel3 = new javax.swing.JLabel();
        JComboBox jcbDest = new javax.swing.JComboBox();
        JLabel jLabel4 = new javax.swing.JLabel();
        JComboBox jcbBusID = new javax.swing.JComboBox();
        JLabel jlError = new javax.swing.JLabel();
        JButton jbtCheck = new javax.swing.JButton();
        JPanel jPanel4 = new javax.swing.JPanel();
        JLabel jLabel7 = new javax.swing.JLabel();
        JLabel jLabel8 = new javax.swing.JLabel();
        JLabel jLabel9 = new javax.swing.JLabel();
        JLabel jLabel10 = new javax.swing.JLabel();
        JLabel jLabel11 = new javax.swing.JLabel();
        JLabel jLabel12 = new javax.swing.JLabel();
        JLabel jLabel6 = new javax.swing.JLabel();
        JLabel jLabel13 = new javax.swing.JLabel();
        JTextField jtfBusType = new javax.swing.JTextField();
        JTextField jtfBusPlateNo = new javax.swing.JTextField();
        JTextField jtfDriverID = new javax.swing.JTextField();
        JTextField jtfDriverName = new javax.swing.JTextField();
        JTextField jtfDriverContact = new javax.swing.JTextField();
        JTextField jtfDepartTime = new javax.swing.JTextField();
        JLabel jlTimeRemain = new javax.swing.JLabel();
        JLabel jlDistRemain = new javax.swing.JLabel();
        JPanel busPanel = new javax.swing.JPanel();
        JButton jbtBack = new javax.swing.JButton();

        //busPanel.add(new JLabel("BUS"));
        jLabel1.setText("jLabel1");

        busTrackingPanel.setBackground(new java.awt.Color(102, 102, 102));
        busTrackingPanel.setMaximumSize(new java.awt.Dimension(740, 640));
        busTrackingPanel.setMinimumSize(new java.awt.Dimension(740, 640));
        busTrackingPanel.setPreferredSize(new java.awt.Dimension(740, 640));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));

        jLabel2.setFont(new java.awt.Font("Lucida Fax", 1, 24)); // NOI18N
        jLabel2.setText("Bus Tracking Panel");

        jLabel3.setFont(new java.awt.Font("Lucida Fax", 0, 11)); // NOI18N
        jLabel3.setText("Destination");

        jLabel4.setFont(new java.awt.Font("Lucida Fax", 0, 11)); // NOI18N
        jLabel4.setText("Bus ID");

        jlError.setText(" ");

        jbtCheck.setFont(new java.awt.Font("Lucida Fax", 0, 11)); // NOI18N
        jbtCheck.setText("Check");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jlError, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(38, 38, 38)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jcbDest, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jcbBusID, 0, 155, Short.MAX_VALUE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(131, 131, 131)
                .addComponent(jbtCheck)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jcbDest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jcbBusID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlError, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbtCheck)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        busPanel.setBackground(new java.awt.Color(153, 153, 153));
        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 5, true), "Bus Information", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Lucida Fax", 3, 18))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel7.setText("Bus Type");

        jLabel8.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel8.setText("Bus Plate No");

        jLabel9.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel9.setText("Departure Time");

        jLabel10.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel10.setText("Driver ID");

        jLabel11.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel11.setText("Driver Name");

        jLabel12.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel12.setText("Driver Contact No");

        jLabel6.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel6.setText("Time Remaining");

        jLabel13.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel13.setText("Distance Remaining");

        jtfBusType.setEditable(false);

        jtfBusPlateNo.setEditable(false);

        jtfDriverID.setEditable(false);

        jtfDriverName.setEditable(false);

        jtfDriverContact.setEditable(false);

        jtfDepartTime.setEditable(false);

        jlTimeRemain.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jlTimeRemain.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlTimeRemain.setText("--");

        jlDistRemain.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jlDistRemain.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlDistRemain.setText("--");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel9)
                            .addComponent(jLabel6))
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtfBusType)
                    .addComponent(jtfBusPlateNo)
                    .addComponent(jtfDriverID)
                    .addComponent(jtfDriverName)
                    .addComponent(jtfDriverContact)
                    .addComponent(jtfDepartTime)
                    .addComponent(jlTimeRemain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlDistRemain, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfBusType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jtfBusPlateNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfDriverID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfDriverName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfDriverContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfDepartTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlTimeRemain, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jlDistRemain, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );        

        jbtBack.setText("Back");
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(busTrackingPanel);
        busTrackingPanel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(busPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 284, Short.MAX_VALUE)
                        .addComponent(jbtBack, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(busPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtBack))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
                
        DefaultComboBoxModel emptyModel = new DefaultComboBoxModel(new String[]{" "});
        jcbDest.setModel(emptyModel);
        jcbBusID.setModel(emptyModel);
        ArrayList<Schedule> scheduleList = sControl.selectRecordByDateForRoute(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));        
        int length = scheduleList.size();
        if(length>0) {
            String[] scheduleChoice = new String[length+1];
            scheduleChoice[0] = " ";
            for(int i=1; i<length+1; i++) {
                scheduleChoice[i] = scheduleList.get(i-1).getRoute().getDestination();
            }
            DefaultComboBoxModel model = new DefaultComboBoxModel(scheduleChoice);
            jcbDest.setModel(model);
            
            jcbDest.addActionListener((ActionEvent e) -> {
                if(!jcbDest.getSelectedItem().toString().equals(" ")) {
                    ArrayList<Schedule> sche = sControl.selectRecordByRouteAndDate(scheduleList.get(jcbDest.getSelectedIndex()-1).getRoute(), new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                    //int l = sche.size();
                    //String[] scheduleC = new String[l];
                    ArrayList<String> scheduleC = new ArrayList<>();
                    ArrayList<Schedule> scheC = new ArrayList<>();
                    for(int i=0; i<sche.size(); i++) {
                        if(sche.get(i).getStatus()=='A') {
                            scheduleC.add(sche.get(i).getBus().getBusID() + " - " + sche.get(i).getDepartureTime());
                            scheC.add(sche.get(i));
                        }
                    }
                    String[] scheduleCC = new String[scheduleC.size()];
                    for(int i=0; i<scheduleC.size(); i++){
                        scheduleCC[i] = scheduleC.get(i);
                    }
                    selectedSchedule = scheC;
                    DefaultComboBoxModel model2 = new DefaultComboBoxModel(scheduleCC);
                    jcbBusID.setModel(model2);
                } 
            });
        }
        
        jbtCheck.addActionListener((ActionEvent e) -> {
            jlError.setForeground(Color.red);
            if(jcbDest.getSelectedItem().toString().equals(" ")) {
                jlError.setText("Invalid destination");
            } else {
                if(jcbBusID.getSelectedItem().toString().equals(" ")) {
                    jlError.setText("Select a bus ID.");
                } else {
                    jlError.setText(" ");
                    if(selectedSchedule.get(jcbBusID.getSelectedIndex()).getBus().getBusType().getBusType().equals("Type A")) {
                        busPanel.removeAll();
                        busPanel.revalidate();
                        busPanel.repaint();
                        busPanel.add(new TrackBusA(selectedSchedule.get(jcbBusID.getSelectedIndex())));
                    } else if(selectedSchedule.get(jcbBusID.getSelectedIndex()).getBus().getBusType().getBusType().equals("Type B")) {
                        busPanel.removeAll();
                        busPanel.revalidate();
                        busPanel.repaint();
                        busPanel.add(new TrackBusB(selectedSchedule.get(jcbBusID.getSelectedIndex())));
                    } else if(selectedSchedule.get(jcbBusID.getSelectedIndex()).getBus().getBusType().getBusType().equals("Double Decker")) {
                        busPanel.removeAll();
                        busPanel.revalidate();
                        busPanel.repaint();
                        busPanel.add(new TrackBusD(selectedSchedule.get(jcbBusID.getSelectedIndex())));
                    }
                    String busID = selectedSchedule.get(jcbBusID.getSelectedIndex()).getBus().getBusID();
                    Bus bus = busControl.selectRecord(busID);
                    Schedule scheduleSelected = sControl.selectRecordByBusDateTime(bus, new SimpleDateFormat("yyyy-MM-dd").format(new Date()), selectedSchedule.get(jcbBusID.getSelectedIndex()).getDepartureTime());
                    jtfBusType.setText(scheduleSelected.getBus().getBusType().getBusType());
                    jtfBusPlateNo.setText(scheduleSelected.getBus().getBusPlateNo());
                    jtfDriverID.setText(scheduleSelected.getDriver().getDriverID());
                    jtfDriverName.setText(scheduleSelected.getDriver().getDriverName());
                    jtfDriverContact.setText(scheduleSelected.getDriver().getContactNo());
                    jtfDepartTime.setText(scheduleSelected.getDepartureTime());                   
                    
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
                    Date date = new Date();
                    try{
                        date = format.parse(scheduleSelected.getDepartureDate() + " " + scheduleSelected.getDepartureTime());
                    } catch(Exception ex) {
                        System.out.println(ex);
                    }                   
                    if(date.after(new Date())) {
                        jlTimeRemain.setText("N/A");
                        jlDistRemain.setText("N/A");
                    } else {                      
                        Date departDate = new Date();
                        try{
                            departDate = format.parse(scheduleSelected.getDepartureDate() + " " + scheduleSelected.getDepartureTime());
                        } catch(Exception ex) {
                            System.out.println(ex);
                        }    
                        LocalDateTime ldt = LocalDateTime.ofInstant(departDate.toInstant(), ZoneId.systemDefault());
                        LocalDateTime ldt2 = ldt.plusMinutes(scheduleSelected.getRoute().getTimeRequired());
                        Date out = Date.from(ldt2.atZone(ZoneId.systemDefault()).toInstant());
                        if(out.before(new Date())) {
                            jlTimeRemain.setText("Reached.");
                            jlDistRemain.setText("0 km");
                        } else {
                            long diff = out.getTime() - new Date().getTime();                            
                            long diffMinutes = diff / (60 * 1000) % 60;
                            long diffHours = diff / (60 * 60 * 1000) % 24;
                            long diffSeconds = diff / 1000 % 60;
                            double kmLeft = ((double)scheduleSelected.getRoute().getDistance() / (double)scheduleSelected.getRoute().getTimeRequired()) * (double)((diffHours*60)+diffMinutes+(diffSeconds/60));
                            //long kmLeft = 50;
                            if(diffHours!=0) {
                                jlTimeRemain.setText(diffHours + " hrs " + diffMinutes + " mins");
                                jlDistRemain.setText(kmLeft+" km");
                            } else {
                                if(diffMinutes!=0) {
                                    jlTimeRemain.setText(diffMinutes + " mins");
                                    jlDistRemain.setText(kmLeft+" km");
                                } else {
                                    jlTimeRemain.setText(diffSeconds + " sec");
                                    jlDistRemain.setText(kmLeft+" km");
                                }
                            }			
                        }
                    }                   
                }
            }
            
        });
        
        jbtBack.addActionListener((ActionEvent e) -> {
            menuPanel.removeAll();
            menuPanel.revalidate();
            menuPanel.repaint();
            menuPanel.add(msg);
            menuPanel.add(menuPane, BorderLayout.CENTER);
        });
        
        return busTrackingPanel;
    }
  
    public JPanel adminPanelLogin() {
        
        JPanel adminPanelLogin = new JPanel();
        JPanel jPanel1 = new javax.swing.JPanel();
        JLabel jLabel1 = new javax.swing.JLabel("ADMIN PANEL LOGIN");
        JLabel jLabel2 = new javax.swing.JLabel("Please reenter your password to login (Manager)");
        JLabel jLabel3 = new javax.swing.JLabel("User ID");
        JLabel jLabel4 = new javax.swing.JLabel("Password");                
        JLabel jlErrorMsg = new javax.swing.JLabel(" ");
        JButton jbtLogin = new javax.swing.JButton("Login");

        adminPanelLogin.setBackground(new java.awt.Color(102, 102, 102));
        adminPanelLogin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        adminPanelLogin.setMaximumSize(new java.awt.Dimension(740, 640));
        adminPanelLogin.setMinimumSize(new java.awt.Dimension(740, 640));
        adminPanelLogin.setPreferredSize(new java.awt.Dimension(740, 640));

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        jLabel1.setBackground(new java.awt.Color(59, 59, 59));
        jLabel1.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setOpaque(true);
        jLabel2.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 255));
        jLabel3.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel4.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jbtLogin.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(41, 41, 41)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfAPUserID)
                                    .addComponent(jtfAPPassword)))
                            .addComponent(jlErrorMsg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addComponent(jbtLogin)))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel2)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtfAPUserID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jtfAPPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlErrorMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbtLogin)
                .addGap(0, 34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(adminPanelLogin);
        adminPanelLogin.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(167, 167, 167)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(176, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(160, 160, 160)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(173, Short.MAX_VALUE))
        );
        
        jtfAPUserID.setText(staffLogin.getStaffID());
        jtfAPUserID.setEditable(false);
        jtfAPPassword.requestFocusInWindow();   
        errorCount = 0;
        jbtLogin.addActionListener((ActionEvent e) -> {
            if(staffLogin.getStaffID().startsWith("M")) {
                String ps = jtfAPPassword.getText();            
                if(ps.equals("")) {
                    jlErrorMsg.setText("Please enter password to enter.");
                    jlErrorMsg.setForeground(Color.red);
                } else {
                    if(ps.equals(staffLogin.getPassword())) {
                        menuPanel.removeAll();
                        menuPanel.revalidate();
                        menuPanel.repaint();
                        menuPanel.add(new AdminPanel(), BorderLayout.CENTER);
                        menuPanel.setBackground(Color.LIGHT_GRAY);
                        jtfAPPassword.setText("");
                    } else {
                        jlErrorMsg.setText("Invalid password for this account.");
                        jlErrorMsg.setForeground(Color.red);
                        errorCount++;
                        if(errorCount==3) {
                            jlErrorMsg.setText("Error for three times. Auto logout...");
                            try{
                                Thread.sleep(1000);
                            } catch (Exception ex) {

                            }
                            this.countList.clear();
                            this.ticketList.clear();
                            this.staffLogin = null;
                            mainPanel.removeAll();
                            mainPanel.revalidate();
                            mainPanel.repaint();
                            mainPanel.add(loginPanel(), BorderLayout.CENTER);
                            jtfAPPassword.setText("");
                        }
                    }
                }  
            } else if(staffLogin.getStaffID().startsWith("S")) {
                jlErrorMsg.setText("You are not allowed to enter.");
                jlErrorMsg.setForeground(Color.red);
            }
        });
        
        jtfAPPassword.addActionListener(jbtLogin.getActionListeners()[0]);
        
        DocumentListener errorMsgRefresh = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                jlErrorMsg.setText(" ");
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                jlErrorMsg.setText(" ");
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }           
        };           
        
        jtfAPPassword.getDocument().addDocumentListener(errorMsgRefresh);
        
        return adminPanelLogin;
    }
  
    public static void main(String[] args) {
        
        try 
        {
            UIManager.setLookAndFeel(new SyntheticaBlackStarLookAndFeel());
            Thread.sleep(1500);
        } 
        catch (Exception e) 
        {
          e.printStackTrace();
        }
        
        MainDriver main = new MainDriver();
        main.setTitle("CJH Bus Express Ticketing System");
        main.setSize(1000, 800);
        main.setResizable(false);
        main.setLocationRelativeTo(null);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setVisible(true);
        main.pack();                       
    }
}
