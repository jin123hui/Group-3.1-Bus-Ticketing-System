/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.toedter.calendar.JDateChooser;
import control.RouteControl;
import control.ScheduleControl;
import domain.Route;

import domain.Schedule;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
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
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
/**
 *
 * @author CJH
 */
public class MainDriver extends JFrame {
    
    private JPanel topPanel = new JPanel(new BorderLayout());
    private JPanel mainPanel = new JPanel(new BorderLayout());
    private JPanel menuPanel = new JPanel();
    private JPanel infoPanel = new JPanel(new GridLayout(5, 1));
    private JLabel msg = new JLabel("Main Panel");
    private JPanel menuPane = new JPanel();
    private RouteControl rControl = new RouteControl();
    private ScheduleControl sControl = new ScheduleControl();  
    
    public MainDriver() {
        
        JLabel topLabel = new JLabel("CJH Bus Express Sdn. Bhd.");
        topLabel.setBorder(new LineBorder(Color.BLACK, 5));
        topLabel.setFont(new Font("Serif", Font.BOLD, 40));
        topLabel.setForeground(Color.WHITE); 
        topLabel.setBackground(Color.gray);
        topLabel.setOpaque(true);
        topLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topLabel.setVerticalAlignment(SwingConstants.CENTER);
        topLabel.setPreferredSize(new Dimension(1000, 150));       
        topPanel.setPreferredSize(new Dimension(1000, 150));
        mainPanel.setPreferredSize(new Dimension(1000, 650));        
        topPanel.add(topLabel);
        mainPanel.add(loginPage());    
        add(topPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.SOUTH);
    }
    
    public JPanel mainPagePanel() {
    
        JPanel mainPagePanel = new JPanel(new BorderLayout());
        mainPagePanel.setPreferredSize(new Dimension(1000, 650));        
        infoPanel.setPreferredSize(new Dimension(250,650));
        infoPanel.setBackground(Color.DARK_GRAY); 
        infoPanel.setBorder(new LineBorder(Color.BLACK, 5));
        menuPanel.setPreferredSize(new Dimension(750,650));      
        menuPanel.setBackground(Color.LIGHT_GRAY);
        GridLayout glOne = new GridLayout(3,2);
        glOne.setHgap(10);
        glOne.setVgap(10);
        menuPane.setLayout(glOne);
        menuPane.setBackground(Color.LIGHT_GRAY);
        
        JButton jbtTicket = new JButton("Ticketing");
        jbtTicket.addActionListener((ActionEvent e) -> {
            menuPanel.removeAll();
            menuPanel.revalidate();
            menuPanel.repaint();
            menuPanel.add(ticketing(), BorderLayout.CENTER);
            menuPanel.setPreferredSize(new Dimension(750,650));  
            infoPanel.setPreferredSize(new Dimension(250,650));
        });
        
        JButton jbtBooking = new JButton("Booking");
        jbtBooking.setPreferredSize(new Dimension(350,190));
        JButton jbtCustomer = new JButton("Customer");
        JButton jbtMember = new JButton("Membership");
        JButton jbtGift = new JButton("Gift Redemption");
        JButton jbtTrack = new JButton("Bus Tracking");
                
        Font font = new Font("Serif", Font.BOLD+Font.ITALIC, 30);
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
                
        JLabel lab1 = new JLabel("Staff ID: ");
        JLabel lab2 = new JLabel("Staff Name: ");
        JPanel info1Panel = new JPanel(new GridLayout(2, 1));
        info1Panel.add(lab1);
        info1Panel.add(lab2);
        JButton editInfo = new JButton("Edit My Infomation");
        JButton lala = new JButton("Admin Panel");
        lala.addActionListener((ActionEvent e) -> {
            menuPanel.removeAll();
            menuPanel.revalidate();
            menuPanel.repaint();
            menuPanel.add(new AdminPanel(), BorderLayout.CENTER);
        });
        JButton lolo = new JButton("Main Panel");
        lolo.addActionListener((ActionEvent e) -> {
            menuPanel.removeAll();
            menuPanel.revalidate();
            menuPanel.repaint();
            menuPanel.add(msg);
            menuPanel.add(menuPane, BorderLayout.CENTER);
            
            menuPanel.setPreferredSize(new Dimension(750, 650));
            infoPanel.setPreferredSize(new Dimension(250, 650));
        });
        JButton jbtLogout = new JButton("Logout");
        jbtLogout.addActionListener((ActionEvent e) -> {
            mainPanel.removeAll();
            mainPanel.revalidate();
            mainPanel.repaint();
            mainPanel.add(loginPage(), BorderLayout.CENTER);
        });
        JButton jbtExit = new JButton("Exit");
        jbtExit.addActionListener((ActionEvent e) -> {
            if((JOptionPane.showConfirmDialog(null, "Quit?", "Warning", JOptionPane.OK_CANCEL_OPTION))!=2)
                System.exit(-1);
        });
        JPanel outPanel = new JPanel();
        outPanel.add(jbtLogout);
        outPanel.add(jbtExit);
        
        infoPanel.add(info1Panel);
        infoPanel.add(editInfo);
        infoPanel.add(lala);
        infoPanel.add(lolo);
        infoPanel.add(outPanel);
        
        msg.setHorizontalAlignment(SwingConstants.LEFT);
        menuPanel.add(msg);
        menuPanel.add(menuPane);               
        
        mainPagePanel.add(infoPanel, BorderLayout.EAST);
        mainPagePanel.add(menuPanel, BorderLayout.WEST);
        mainPagePanel.setPreferredSize(new Dimension(1000, 650));
        return mainPagePanel;
    }
    
    public JPanel loginPage() {
        
        JPanel loginPage = new JPanel(new GridLayout(3,3));
        loginPage.setBackground(Color.DARK_GRAY);
        loginPage.setPreferredSize(new Dimension(1000, 650));
        JPanel middlePane = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.CENTER;
        middlePane.setBackground(Color.CYAN);
        middlePane.setPreferredSize(new Dimension(500, 216));
        middlePane.setBorder(new LineBorder(Color.black, 2));
        
        JLabel titleLab = new JLabel("LOGIN");
        titleLab.setForeground(Color.white);
        titleLab.setFont(new Font("SansSerif", Font.BOLD, 20));
        titleLab.setHorizontalAlignment(SwingConstants.CENTER);
        titleLab.setVerticalAlignment(SwingConstants.BOTTOM);
        titleLab.setBackground(Color.blue);
        titleLab.setOpaque(true);
       
        JPanel titlePanel = new JPanel(new GridLayout(4,1));
        titlePanel.setBackground(Color.DARK_GRAY);
        JPanel lastPane = new JPanel();
        lastPane.setBackground(Color.BLUE);
        lastPane.setPreferredSize(new Dimension(500, 20));
        lastPane.add(titleLab);
        lastPane.setBorder(new LineBorder(Color.black, 2));
        
        JLabel jlPass = new JLabel("Password");
        jlPass.setHorizontalAlignment(SwingConstants.CENTER);            
        JPasswordField jpfPass = new JPasswordField("", 20); 
        JLabel jlError = new JLabel(" ");
        JButton jbtLogin = new JButton("Login");
        JButton jbtForgotPass = new JButton("Forgot your password?");
        jbtForgotPass.setBorderPainted(false);
        jbtForgotPass.setBackground(Color.cyan);
        
        gbc.insets = new Insets(3,3,3,3);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        middlePane.add(jlPass, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        middlePane.add(jpfPass, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        middlePane.add(jlError, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 4;
        gbc.ipadx = 2;
        gbc.ipady = 2;
        middlePane.add(jbtLogin, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 4;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        middlePane.add(jbtForgotPass, gbc);
        
        titlePanel.add(new JLabel(""));
        titlePanel.add(new JLabel(""));
        titlePanel.add(new JLabel(""));
        titlePanel.add(lastPane);
        
        loginPage.add(new JLabel(""));
        loginPage.add(titlePanel);
        loginPage.add(new JLabel(""));
        loginPage.add(new JLabel(""));
        loginPage.add(middlePane);
        loginPage.add(new JLabel(""));
        loginPage.add(new JLabel(""));
        loginPage.add(new JLabel(""));
        loginPage.add(new JLabel(""));      
        
        jbtLogin.addActionListener((ActionEvent e) -> {
            if(1==1) {
                mainPanel.removeAll();
                menuPanel.removeAll();
                infoPanel.removeAll();
                menuPane.removeAll();
                mainPanel.revalidate();
                mainPanel.repaint();
                mainPanel.add(mainPagePanel(), BorderLayout.CENTER);
            }
        });
             
        return loginPage;
    }
    
    public JPanel ticketing() {
            
        JPanel ticketing = new JPanel();
        JPanel firstStep = new JPanel();
        JLabel jLabel1 = new JLabel();
        JLabel jLabel2 = new JLabel();
        JLabel jLabel3 = new JLabel();
        JLabel jLabel4 = new JLabel();
        JPanel jPanel3 = new JPanel();
        JScrollPane jScrollPane1 = new JScrollPane();
        JButton jbtCheck = new JButton();
        JButton jbtReset = new JButton();
        JButton jbtBack = new JButton();
        JComboBox jcbTo = new JComboBox();
        JDateChooser jdcDate = new JDateChooser();
        JList jlSchedule = new JList();
        JLabel jtfErrorMsg = new JLabel();
        JTextField jtfFrom = new JTextField();
        JPanel panel0 = new JPanel();      
                   
        jtfFrom.setText("CJH Terminal");                                             
        firstStep.setBackground(new java.awt.Color(204, 204, 204));
        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24));
        jLabel1.setText("Ticketing Information");
        jPanel3.setBackground(new java.awt.Color(153, 153, 153));
        jLabel2.setText("From");
        jLabel3.setText("To");
        jLabel4.setText("Date");
        jtfFrom.setEditable(false);
        jdcDate.setDateFormatString("yyyy-MM-dd");
        jbtCheck.setText("Check");       
        jtfErrorMsg.setText(" ");
        jbtReset.setText("Reset");
        
        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jtfErrorMsg, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jbtCheck)
                                .addGap(18, 18, 18)
                                .addComponent(jbtReset)
                                .addGap(32, 32, 32)))
                        .addGap(15, 15, 15))
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

        jlSchedule.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jlSchedule.setToolTipText("");       
        jScrollPane1.setViewportView(jlSchedule);
        jbtBack.setText("Back");

        javax.swing.GroupLayout firstStepLayout = new javax.swing.GroupLayout(firstStep);
        firstStep.setLayout(firstStepLayout);
        firstStepLayout.setHorizontalGroup(
            firstStepLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(firstStepLayout.createSequentialGroup()
                .addContainerGap(106, Short.MAX_VALUE)
                .addGroup(firstStepLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, firstStepLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(firstStepLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, firstStepLayout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(78, 78, 78))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, firstStepLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(143, 143, 143))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, firstStepLayout.createSequentialGroup()
                        .addComponent(jbtBack)
                        .addGap(230, 230, 230))))
        );
        firstStepLayout.setVerticalGroup(
            firstStepLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(firstStepLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(35, 35, 35)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtBack)
                .addGap(54, 54, 54))
        );

        javax.swing.GroupLayout panel0Layout = new javax.swing.GroupLayout(panel0);
        panel0.setLayout(panel0Layout);
        panel0Layout.setHorizontalGroup(
            panel0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel0Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(firstStep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(88, Short.MAX_VALUE))
        );
        panel0Layout.setVerticalGroup(
            panel0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel0Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(firstStep, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(ticketing);
        ticketing.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panel0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        
        ArrayList<Route> routeList = rControl.selectAllRecord();  
        int length = routeList.size();
        String[] routeChoice = new String[length];
        for(int i=0; i<length; i++) {
            routeChoice[i] = routeList.get(i).getDestination();
        }
        DefaultComboBoxModel model = new DefaultComboBoxModel(routeChoice);
        jcbTo.setModel(model);                                      

        jbtCheck.addActionListener((ActionEvent evt) -> {                                         
            DefaultListModel scheduleList = new DefaultListModel();
            jlSchedule.setModel(scheduleList);

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
                ArrayList<Schedule> scheduleDisplayList = sControl.selectRecordByRouteAndDate(routeSelected, dateSelected);
                for(int i=0; i<scheduleDisplayList.size(); i++) {
                    scheduleList.addElement(String.format("%02d", (i+1)) + ".) ID : " + scheduleDisplayList.get(i).getScheduleID() + " - Time(24HR) : " + scheduleDisplayList.get(i).getDepartureTime().substring(0, 5) + " - Bus Type : " + scheduleDisplayList.get(i).getBus().getBusType().getBusType());
                }
            }
        });   
        
        jlSchedule.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent evt) {
                if(evt.getClickCount() == 1) {
                    String selectedSchedule = jlSchedule.getSelectedValue().toString();
                    if(selectedSchedule.endsWith("Type A")) {
                        menuPanel.removeAll();
                        menuPanel.revalidate();
                        menuPanel.repaint();
                        menuPanel.add(busA(selectedSchedule.substring(10, 18)), BorderLayout.CENTER);                                       
                    } else if(selectedSchedule.endsWith("Type B")) {
                        menuPanel.removeAll();
                        menuPanel.revalidate();
                        menuPanel.repaint();
                        menuPanel.add(new BusB(), BorderLayout.CENTER);    
                    } else if(selectedSchedule.endsWith("Double Decker")) {
                        menuPanel.removeAll();
                        menuPanel.revalidate();
                        menuPanel.repaint();
                        menuPanel.add(new BusDD(), BorderLayout.CENTER);    
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
        
        ticketing.setPreferredSize(new Dimension(740, 640));

        return ticketing;
    }
    
    public JPanel busA(String scheduleID) {
        
        JPanel busA = new JPanel();         
        JButton jButton1 = new JButton();
        JLabel jLabel1 = new JLabel();
        JButton jbtBack = new JButton();
        JButton jbtSubmit = new JButton();
        JToggleButton jtb1 = new JToggleButton();
        JToggleButton jtb2 = new JToggleButton();
        JToggleButton jtb3 = new JToggleButton();
        JToggleButton jtb4 = new JToggleButton();
        JToggleButton jtb5 = new JToggleButton();
        JToggleButton jtb6 = new JToggleButton();
        JToggleButton jtb7 = new JToggleButton();
        JToggleButton jtb8 = new JToggleButton();
        JToggleButton jtb9 = new JToggleButton();
        JToggleButton jtb10 = new JToggleButton();
        JToggleButton jtb11 = new JToggleButton();
        JToggleButton jtb12 = new JToggleButton();
        JToggleButton jtb13 = new JToggleButton();
        JToggleButton jtb14 = new JToggleButton();
        JToggleButton jtb15 = new JToggleButton();
        JToggleButton jtb16 = new JToggleButton();
        JToggleButton jtb17 = new JToggleButton();
        JToggleButton jtb18 = new JToggleButton();
        JToggleButton jtb19 = new JToggleButton();
        JToggleButton jtb20 = new JToggleButton();
        JToggleButton jtb21 = new JToggleButton();
        JToggleButton jtb22 = new JToggleButton();
        JToggleButton jtb23 = new JToggleButton();
        JToggleButton jtb24 = new JToggleButton();
        JToggleButton jtb25 = new JToggleButton();
        JToggleButton jtb26 = new JToggleButton();
        JToggleButton jtb27 = new JToggleButton();
        JToggleButton jtb28 = new JToggleButton();
        JToggleButton jtb29 = new JToggleButton();
        JToggleButton jtb30 = new JToggleButton();
        JLabel jtfErrorMsg = new JLabel();

        busA.setBackground(new java.awt.Color(102, 102, 102));
        busA.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 5, true));
        busA.setPreferredSize(new java.awt.Dimension(360, 628));

        jtb1.setBackground(new java.awt.Color(0, 153, 255));
        jtb1.setText("1");
        jtb1.setPreferredSize(new java.awt.Dimension(50, 30));

        jButton1.setText("Driver");
        jButton1.setEnabled(false);

        jtb2.setBackground(new java.awt.Color(0, 153, 255));
        jtb2.setText("2");
        jtb2.setPreferredSize(new java.awt.Dimension(50, 30));

        jtb3.setBackground(new java.awt.Color(0, 153, 255));
        jtb3.setText("3");
        jtb3.setPreferredSize(new java.awt.Dimension(50, 30));

        jtb4.setBackground(new java.awt.Color(0, 153, 255));
        jtb4.setText("4");
        jtb4.setPreferredSize(new java.awt.Dimension(50, 30));

        jtb5.setBackground(new java.awt.Color(0, 153, 255));
        jtb5.setText("5");
        jtb5.setPreferredSize(new java.awt.Dimension(50, 30));

        jtb6.setBackground(new java.awt.Color(0, 153, 255));
        jtb6.setText("6");
        jtb6.setPreferredSize(new java.awt.Dimension(50, 30));

        jtb7.setBackground(new java.awt.Color(0, 153, 255));
        jtb7.setText("7");
        jtb7.setPreferredSize(new java.awt.Dimension(50, 30));

        jtb8.setBackground(new java.awt.Color(0, 153, 255));
        jtb8.setText("8");
        jtb8.setPreferredSize(new java.awt.Dimension(50, 30));

        jtb9.setBackground(new java.awt.Color(0, 153, 255));
        jtb9.setText("9");
        jtb9.setPreferredSize(new java.awt.Dimension(50, 30));

        jtb10.setBackground(new java.awt.Color(0, 153, 255));
        jtb10.setText("10");
        jtb10.setPreferredSize(new java.awt.Dimension(50, 30));

        jtb11.setBackground(new java.awt.Color(0, 153, 255));
        jtb11.setText("11");
        jtb11.setPreferredSize(new java.awt.Dimension(50, 30));

        jtb12.setBackground(new java.awt.Color(0, 153, 255));
        jtb12.setText("12");
        jtb12.setPreferredSize(new java.awt.Dimension(50, 30));

        jtb13.setBackground(new java.awt.Color(0, 153, 255));
        jtb13.setText("13");
        jtb13.setPreferredSize(new java.awt.Dimension(50, 30));

        jtb14.setBackground(new java.awt.Color(0, 153, 255));
        jtb14.setText("14");
        jtb14.setPreferredSize(new java.awt.Dimension(50, 30));

        jtb15.setBackground(new java.awt.Color(0, 153, 255));
        jtb15.setText("15");
        jtb15.setPreferredSize(new java.awt.Dimension(50, 30));

        jtb16.setBackground(new java.awt.Color(0, 153, 255));
        jtb16.setText("16");
        jtb16.setPreferredSize(new java.awt.Dimension(50, 30));

        jtb17.setBackground(new java.awt.Color(0, 153, 255));
        jtb17.setText("17");
        jtb17.setPreferredSize(new java.awt.Dimension(50, 30));        

        jtb18.setBackground(new java.awt.Color(0, 153, 255));
        jtb18.setText("18");
        jtb18.setPreferredSize(new java.awt.Dimension(50, 30));

        jtb19.setBackground(new java.awt.Color(0, 153, 255));
        jtb19.setText("19");
        jtb19.setPreferredSize(new java.awt.Dimension(50, 30));

        jtb20.setBackground(new java.awt.Color(0, 153, 255));
        jtb20.setText("20");
        jtb20.setPreferredSize(new java.awt.Dimension(50, 30));

        jtb21.setBackground(new java.awt.Color(0, 153, 255));
        jtb21.setText("21");
        jtb21.setPreferredSize(new java.awt.Dimension(50, 30));

        jtb22.setBackground(new java.awt.Color(0, 153, 255));
        jtb22.setText("22");
        jtb22.setPreferredSize(new java.awt.Dimension(50, 30));

        jtb23.setBackground(new java.awt.Color(0, 153, 255));
        jtb23.setText("23");
        jtb23.setPreferredSize(new java.awt.Dimension(50, 30));

        jtb24.setBackground(new java.awt.Color(0, 153, 255));
        jtb24.setText("24");
        jtb24.setPreferredSize(new java.awt.Dimension(50, 30));

        jtb25.setBackground(new java.awt.Color(0, 153, 255));
        jtb25.setText("25");
        jtb25.setPreferredSize(new java.awt.Dimension(50, 30));

        jtb26.setBackground(new java.awt.Color(0, 153, 255));
        jtb26.setText("26");
        jtb26.setPreferredSize(new java.awt.Dimension(50, 30));

        jtb27.setBackground(new java.awt.Color(0, 153, 255));
        jtb27.setText("27");
        jtb27.setPreferredSize(new java.awt.Dimension(50, 30));

        jtb28.setBackground(new java.awt.Color(0, 153, 255));
        jtb28.setText("28");
        jtb28.setPreferredSize(new java.awt.Dimension(50, 30));

        jtb29.setBackground(new java.awt.Color(0, 153, 255));
        jtb29.setText("29");
        jtb29.setPreferredSize(new java.awt.Dimension(50, 30));

        jtb30.setBackground(new java.awt.Color(0, 153, 255));
        jtb30.setText("30");
        jtb30.setPreferredSize(new java.awt.Dimension(50, 30));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Seat Panel");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jbtSubmit.setText("Submit");
        jbtBack.setText("Back");
        jtfErrorMsg.setText(scheduleID);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(busA);
        busA.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(56, 56, 56)
                                .addComponent(jButton1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(jbtSubmit)
                                .addGap(31, 31, 31)
                                .addComponent(jbtBack)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(66, 66, 66))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jtfErrorMsg, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jtb1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jtb4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtb5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jtb2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(67, 67, 67)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtb3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtb6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jtb10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jtb7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jtb11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jtb8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(67, 67, 67)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jtb12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jtb9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jtb13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jtb14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jtb28, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jtb25, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jtb22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jtb16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jtb19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jtb20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jtb17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jtb23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jtb26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jtb29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jtb15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jtb18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jtb21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jtb24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jtb27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jtb30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(63, 63, 63))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtb2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtb3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtb1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtb4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtb5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtb6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtb7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtb8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtb9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtb10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtb11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtb12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtb13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtb14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtb15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtb16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtb17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtb18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtb19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtb20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtb21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtb22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtb23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtb24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtb25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtb26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtb27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtb28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtb29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtb30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jtfErrorMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtBack)
                    .addComponent(jbtSubmit))
                .addGap(25, 25, 25))
        );

        jbtBack.addActionListener((ActionEvent e) -> {
            menuPanel.removeAll();
            menuPanel.revalidate();
            menuPanel.repaint();
            menuPanel.add(ticketing(), BorderLayout.CENTER);
        });
        
        Schedule selectedSchedule = sControl.selectRecord(scheduleID);
        
     
        return busA;
    }
  
    public static void main(String[] args) {
        
        MainDriver main = new MainDriver();
        main.setTitle("CJH Bus Express Ticketing System");
        main.setSize(1000, 800);
        main.setResizable(false);
        main.setLocationRelativeTo(null);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setVisible(true);
        //main.pack();
    }
}
