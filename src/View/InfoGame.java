/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

/**
 *
 * @author dam2
 */
public class InfoGame extends javax.swing.JPanel {

    private int LifeCityHall;
    private int warriors, builders, defense;
    

    /**
     * Creates new form InfoGame
     */
    public InfoGame() {
        initComponents();
    }
    

    public void setLifeCityHall(int LifeCityHall) {
        this.LifeCityHall = LifeCityHall;
    }

    public int getLifeCityHall() {
        return LifeCityHall;
    }
    
    public void incrBuilders(int x){
        this.builders += x;
    
    }
    
    public void incrWarriors(int x){
        this.warriors += x;
    
    }
    
    public void incrDefense(int x){
        this.defense += x;
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}