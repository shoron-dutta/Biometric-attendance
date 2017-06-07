
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Tanjila Mawla Tania
 */
public class Adding extends javax.swing.JFrame {

    /**
     * Creates new form Adding
     */
    String months[]={"none","JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"};
        
    Connection conn = null;
    

    public Adding() throws ClassNotFoundException {
        initComponents();
        
        for(int i=1;i<=31;i++)
            jComboBox1.addItem(i);
        for(int i=1;i<13;i++)
            jComboBox2.addItem(months[i]);
        for(int i=2016;i<=2030;i++)
            jComboBox3.addItem(i);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        takeAttendancePanel = new javax.swing.JPanel();
        takeAttendanceLabel = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox3 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        takeAttendancePanel.setLayout(null);

        takeAttendanceLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/addLabelImage.png"))); // NOI18N
        takeAttendancePanel.add(takeAttendanceLabel);
        takeAttendanceLabel.setBounds(-10, 114, 420, 460);

        takeAttendancePanel.add(jComboBox1);
        jComboBox1.setBounds(414, 70, 28, 20);

        takeAttendancePanel.add(jComboBox3);
        jComboBox3.setBounds(506, 70, 28, 20);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        takeAttendancePanel.add(jButton1);
        jButton1.setBounds(230, 360, 73, 23);

        takeAttendancePanel.add(jComboBox2);
        jComboBox2.setBounds(460, 70, 28, 20);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(takeAttendancePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(takeAttendancePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int date=(int) jComboBox1.getSelectedItem();
        String monthName= (String) jComboBox2.getSelectedItem();
        int year=(int) jComboBox3.getSelectedItem();

        System.out.println(date+"@@"+monthName+"@@"+year);
        String day=null;

        //System.out.println(a+"th "+b+", "+c);
        int monthNum= getMonthIndex(monthName);
        //day=year+"-"+monthNum+"-"+date;
        if(monthNum<10 && date<10)
        day=year+"-0"+monthNum+"-0"+date;
        else if(monthNum<10)
        day=year+"-0"+monthNum+"-"+date;
        else if(date<10)
        day=year+"-"+monthNum+"-0"+date;
        else
        day=year+"-"+monthNum+"-"+date;

        System.out.println(day);
        addToDB(day);
    }//GEN-LAST:event_jButton1ActionPerformed
    
    void addToDB(String day){
        try {
            
            Date dob;
            //dob = (Date)day;
            dob = Date.valueOf(day);
            System.out.println(dob);
            //int batch = Integer.parseInt(batchTextField.getText());
            String sql = "INSERT INTO JADU (DOB) VALUES (?)";

            System.out.println("conn " + Home.conn);

            Home.opendb();
            Home.pst = Home.conn.prepareStatement(sql);
            Home.pst.setString(1,day);
            

            try {
                System.out.println("pst pre error");

                Home.pst.execute();
                Home.closedb();
                //register fingerprint of this student to dataset
                
            } catch (SQLException ex) {
                Logger.getLogger(AddFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    int getMonthIndex(String monthName){
        int index = 0;
        for(int i=1;i<13;i++)
        {
            if(months[i].equals(monthName)){
                index = i;
                
            System.out.println(monthName +"="+ index );
            break;
            }
        }
        return index;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Adding.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Adding.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Adding.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Adding.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Adding().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Adding.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JLabel takeAttendanceLabel;
    private javax.swing.JPanel takeAttendancePanel;
    // End of variables declaration//GEN-END:variables
}
