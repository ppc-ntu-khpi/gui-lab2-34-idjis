package matissedemo;

import data.DataSource;
import domain.*;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 * 
 * @author IT-Service
 */
public class GUI extends javax.swing.JFrame {

    public GUI() {
        Gui();
    }

    private void Gui() {

        showButton = new javax.swing.JButton();
        showButton.setText("Show");
        showButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        showButton.addActionListener((java.awt.event.ActionEvent event) -> {
        showButtonActionPerformed(event);});
        reportButton = new javax.swing.JButton();
        reportButton.setText("Report");
        reportButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        reportButton.addActionListener((java.awt.event.ActionEvent event) -> {
        reportButtonActionPerformed(event);});
        aboutButton = new javax.swing.JButton();
        aboutButton.setText("About");
        aboutButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        aboutButton.addActionListener((java.awt.event.ActionEvent event) -> {
        aboutButtonActionPerformed(event);});
        customersList = new javax.swing.JComboBox<>();
        jscrollPane = new javax.swing.JScrollPane();
        customer = new javax.swing.JEditorPane();
        label = new javax.swing.JLabel();
        showButton.setFont(new Font("TimesRoman", Font.BOLD, 12));
        aboutButton.setFont(new Font("TimesRoman", Font.BOLD, 12));
        reportButton.setFont(new Font("TimesRoman", Font.BOLD, 12));
        customersList.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("MyBank Clients");
        setPreferredSize(new Dimension(440, 370));
        setLocation(500, 200);
        setResizable(false);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowOpened(java.awt.event.WindowEvent event) {
                CustomerList(event);}});


        jscrollPane.setViewportView(customer);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(customersList, 0, 320, Short.MAX_VALUE)
                .addComponent(jscrollPane)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(aboutButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(reportButton, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                .addComponent(showButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap()));
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(showButton)
                .addComponent(customersList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addComponent(reportButton).addGap(20,20,20).addComponent(aboutButton).addGap(20, 20, 20))
                .addComponent(jscrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)).addContainerGap(42, Short.MAX_VALUE)));
        pack();
    }

    private void aboutButtonActionPerformed(java.awt.event.ActionEvent event) {
        JOptionPane.showMessageDialog(null, "Hi, I'm just a little program.", "About", JOptionPane.INFORMATION_MESSAGE);
    }

    private void CustomerList(java.awt.event.WindowEvent event) {
        DataSource dataSource = new DataSource("C:\\Users\\IT-Service\\Documents\\NetBeansProjects\\MatisseDemo\\src\\data\\test.dat");
        try {
            dataSource.loadData();
        } catch (IOException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
            for (int i = 0; i < Bank.getNumberOfCustomers(); i++) {
                Customer customers = Bank.getCustomer(i);
                customersList.addItem((new StringBuilder()).append(customers.getFirstName()).append(customers.getLastName()).toString()
                );
            }
    }

    private void showButtonActionPerformed(java.awt.event.ActionEvent event) {
        StringBuilder customerShow = new StringBuilder();
            Customer showCustomer = Bank.getCustomer(customersList.getSelectedIndex());
            customerShow.append(showCustomer.getFirstName()).append(" ").append(showCustomer.getLastName()).append(", customer #").append(customersList.getSelectedIndex())
                    .append("\n-----------------------------------\n").append("Accounts:");                                                  
            for (int j = 0; j < showCustomer.getNumberOfAccounts(); j++) {
                Account account = showCustomer.getAccount(j);
            customerShow.append("\n#").append(j).append(account instanceof CheckingAccount ? " - Checking: $" : " - Savings: $").append(account.getBalance());
            }     
            customer.setText(customerShow.toString());
    }

    private void reportButtonActionPerformed(java.awt.event.ActionEvent event) {
        StringBuilder customerReport = new StringBuilder();
            for(int i=0; i<Bank.getNumberOfCustomers();i++){
            Customer report = Bank.getCustomer(i);
            customerReport.append(report.getFirstName()).append(" ").append(report.getLastName()).append(", customer #")
                    .append(i).append("\n----------------------------------\n").append("Accounts:");                                                  

            for (int j = 0; j < report.getNumberOfAccounts(); j++) {
                Account account = report.getAccount(j);
            customerReport.append("\n#").append(j).append(account instanceof CheckingAccount ? " - Checking: $" : " - Savings: $")
                .append(account.getBalance());
            }   customerReport.append("\n\n");
            }   customer.setText(customerReport.toString());
    }

    public static void main(String args[]) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        try {
            javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        }
        catch (Exception e){
            System.out.println("Metal Look and Feel not found");
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GUI gui = new GUI();
                gui.setVisible(true);
            }
        });
    }
    
    private javax.swing.JButton aboutButton;
    private javax.swing.JEditorPane customer;
    private javax.swing.JComboBox<String> customersList;
    private javax.swing.JScrollPane jscrollPane;
    private javax.swing.JButton reportButton;
    private javax.swing.JButton showButton;
    private javax.swing.JLabel label;
}