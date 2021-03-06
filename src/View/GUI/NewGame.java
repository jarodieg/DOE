/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.GUI;

import Controller.TableEnemies;
import Model.Users.User;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author dam2
 */
public class NewGame extends javax.swing.JPanel {

    private List<User> enemies;
    private MainFrame mf;
    private TableEnemies te;
    /**
     * Creates new form NewGame
     */
    public NewGame(MainFrame mf, TableEnemies te) {
        initComponents();
        
        enemies = new ArrayList<User>();
        this.mf = mf;
        this.te = te;
        InnerTable.setModel(te);
        
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        table = new javax.swing.JScrollPane();
        InnerTable = new javax.swing.JTable();
        Enviar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        Volver = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        InnerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        InnerTable.setMaximumSize(new java.awt.Dimension(0, 0));
        table.setViewportView(InnerTable);
        InnerTable.setPreferredScrollableViewportSize(InnerTable.getPreferredSize());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        add(table, gridBagConstraints);

        Enviar.setText("Enviar");
        Enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnviarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        add(Enviar, gridBagConstraints);

        jLabel1.setText("Jugadores:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(jLabel1, gridBagConstraints);

        Volver.setText("Volver");
        Volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VolverActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        add(Volver, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void EnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnviarActionPerformed
        // TODO add your handling code here:        
        List<User> enemiesAux = mf.getUser().getEnemies();
        boolean token;
        for (int i = 0; i < enemiesAux.size(); i++) {
            if ((boolean) InnerTable.getValueAt(i+1, 1)) {
                enemies.add(enemiesAux.get(i));
            }
        }
        if (!(enemies == null)) {
            mf.showGame(enemies);
        }else{
            JOptionPane.showMessageDialog(this, "Tienes que elegir al menos un oponente, hasta un máximo de 4.");
        }
    }//GEN-LAST:event_EnviarActionPerformed

    private void VolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VolverActionPerformed
        // TODO add your handling code here:
        mf.showMenu();
    }//GEN-LAST:event_VolverActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Enviar;
    private javax.swing.JTable InnerTable;
    private javax.swing.JButton Volver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane table;
    // End of variables declaration//GEN-END:variables
}
