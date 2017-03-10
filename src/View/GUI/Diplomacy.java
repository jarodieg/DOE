/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.GUI;

import Model.Users.User;
import java.util.List;
import javax.swing.table.TableModel;

/**
 *
 * @author Moshitron
 */
public class Diplomacy extends javax.swing.JPanel {

    private List<User> enemies;
    private List<User> allies;
    private List<User> neutrals;
    private int user;
    private MainFrame mf;

    /**
     * Creates new form Diplomacy
     */
    public Diplomacy(MainFrame mf, TableModel enemies, TableModel allies) {
        initComponents();

        this.mf = mf;
        neutrals = mf.getMc().getUsersNeutral();
        this.allies = mf.getMc().getAllies(mf.getUser());
        this.enemies = mf.getMc().getEnemies(mf.getUser());
        this.tableAlly.setModel(allies);
        this.tableEnemy.setModel(enemies);
        modifyNeutrals();

    }

    private void modifyNeutrals() {
        for (int i = 0; i < neutrals.size(); i++) {
            Users.addItem(neutrals.get(i).getName());
        }

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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableEnemy = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        Users = new javax.swing.JComboBox<>();
        Aliado = new javax.swing.JButton();
        Enemigo = new javax.swing.JButton();
        Volver = new javax.swing.JButton();
        Neutral = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableAlly = new javax.swing.JTable();

        setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        tableEnemy.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tableEnemy);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 355;
        gridBagConstraints.ipady = 251;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 100;
        add(jPanel1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        Users.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        Users.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsersActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel2.add(Users, gridBagConstraints);

        Aliado.setText("Aliado");
        Aliado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AliadoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel2.add(Aliado, gridBagConstraints);

        Enemigo.setText("Enemigo");
        Enemigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnemigoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel2.add(Enemigo, gridBagConstraints);

        Volver.setText("Volver");
        Volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VolverActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel2.add(Volver, gridBagConstraints);

        Neutral.setText("Neutral");
        Neutral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NeutralActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(Neutral, gridBagConstraints);

        jLabel1.setText("Usuario:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel2.add(jLabel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 100;
        add(jPanel2, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        tableAlly.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tableAlly);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 433;
        gridBagConstraints.ipady = 338;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jScrollPane2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 100;
        add(jPanel3, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void AliadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AliadoActionPerformed
        // TODO add your handling code here:
        if (user != 0) {
            mf.getUser().addAlly(mf.getMc().getUser(Users.getItemAt(user)));
            mf.getMc().setUserAlly(mf.getUser(), mf.getMc().getUser(Users.getItemAt(user)));
            tableAlly.repaint();
        }
    }//GEN-LAST:event_AliadoActionPerformed

    private void NeutralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NeutralActionPerformed
        // TODO add your handling code here:

        for (int i = 0; i < enemies.size(); i++) {
            if ((boolean) tableEnemy.getValueAt(i+1, 1)) {
                mf.getUser().removeEnemy(enemies.get(i));
                mf.getMc().deleteUserEnemy(mf.getUser(), enemies.get(i));
            }
        }
        
        for (int i = 0; i < allies.size(); i++) {
            if ((boolean) tableAlly.getValueAt(i+1, 1)) {
                mf.getUser().removeEnemy(allies.get(i));
                mf.getMc().deleteUserAlly(mf.getUser(), allies.get(i));
            }
        }
        
        tableAlly.repaint();
        tableEnemy.repaint();
    }//GEN-LAST:event_NeutralActionPerformed

    private void EnemigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnemigoActionPerformed
        // TODO add your handling code here:
        if (user != 0) {
            mf.getUser().addEnemy(mf.getMc().getUser(Users.getItemAt(user)));
            mf.getMc().setUserEnemy(mf.getUser(), mf.getMc().getUser(Users.getItemAt(user)));
            tableEnemy.repaint();
        }
    }//GEN-LAST:event_EnemigoActionPerformed

    private void VolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VolverActionPerformed
        // TODO add your handling code here:
        mf.showMenu();
    }//GEN-LAST:event_VolverActionPerformed

    private void UsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsersActionPerformed
        // TODO add your handling code here:
        user = Users.getSelectedIndex();

    }//GEN-LAST:event_UsersActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Aliado;
    private javax.swing.JButton Enemigo;
    private javax.swing.JButton Neutral;
    private javax.swing.JComboBox<String> Users;
    private javax.swing.JButton Volver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableAlly;
    private javax.swing.JTable tableEnemy;
    // End of variables declaration//GEN-END:variables
}