/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.BBDD;

import Model.Users.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dam2
 */
public class Query {

    private Connection conn;
    private Statement stmt;

    public Query() {
    }

    ;
    
    public Query(Connection conn) {
        this.conn = conn;
        try {

            conn.getConn().setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //
    //Acceso a Datos
    //
    public ArrayList<User> getUsers() {

        ArrayList list = new ArrayList();
        ResultSet rs;
        try {
            stmt = conn.getConn().createStatement();
            rs = stmt.executeQuery("SELECT * FROM user");
            while (rs.next()) {
                String[] matrix = new String[3];
                matrix[0] = rs.getString("name");
                matrix[1] = rs.getString("win");
                matrix[2] = rs.getString("plays");
                list.add(new User(matrix[0], matrix[1], matrix[2]));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public ArrayList<User> getUsersNeutral(User u) {
        ArrayList list = new ArrayList();
        ResultSet rs;
        try {
            stmt = conn.getConn().createStatement();
            rs = stmt.executeQuery("SELECT * from user WHERE name NOT IN (SELECT Player1 FROM Alliances WHERE player2 = '"+u.getName()+"') AND name NOT IN (SELECT Player1 FROM Enemies WHERE player2 = '"+u.getName()+"') AND name != '"+u.getName()+"'");
            while (rs.next()) {
                String[] matrix = new String[3];
                matrix[0] = rs.getString("name");
                matrix[1] = rs.getString("win");
                matrix[2] = rs.getString("plays");
                list.add(new User(matrix[0], matrix[1], matrix[2]));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public User getUser(String player) {
        ResultSet rs;
        User user;
        String query = "SELECT * FROM user WHERE name='" + player + "'";
        try {
            stmt = conn.getConn().createStatement();
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                user = new User(rs.getString("name"), rs.getString("email"), rs.getString("password"), Integer.valueOf(rs.getString("win")),
                        Integer.valueOf(rs.getString("plays")));
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList getAllies(String player) {
        ArrayList<User> list = new ArrayList();
        ResultSet rs;
        try {
            stmt = conn.getConn().createStatement();
            rs = stmt.executeQuery("SELECT name, email, password, win, plays FROM Alliances e, user u WHERE e.Player1='" + player + "' AND e.Player2 = u.name AND u.name = e.Player2");
            while (rs.next()) {
                list.add(new User(rs.getString("name"), rs.getString("email"), rs.getString("password"), Integer.valueOf(rs.getString("win")),
                        Integer.valueOf(rs.getString("plays"))));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
        }

        Collections.sort(list, (User o1, User o2) -> {
            if (o1.getWinRatio() > o2.getWinRatio()) {
                return -1;
            } else if (o1.getWinRatio() == o2.getWinRatio()) {
                return 0;
            } else {
                return 1;
            }
        });

        return list;
    }

    public ArrayList getEnemies(String player) {
        ArrayList list = new ArrayList();
        ResultSet rs;
        try {
            stmt = conn.getConn().createStatement();
            rs = stmt.executeQuery("SELECT name, email, password, win, plays FROM Enemies e, user u WHERE e.Player1='" + player + "' AND e.Player2 = u.name AND u.name = e.Player2");
            while (rs.next()) {
                list.add(new User(rs.getString("name"), rs.getString("email"), rs.getString("password"), Integer.valueOf(rs.getString("win")),
                        Integer.valueOf(rs.getString("plays"))));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
        }

        Collections.sort(list, (User o1, User o2) -> {
            if (o1.getWinRatio() > o2.getWinRatio()) {
                return -1;
            } else if (o1.getWinRatio() == o2.getWinRatio()) {
                return 0;
            } else {
                return 1;
            }
        });

        return list;
    }

    public ArrayList getMatchInfo(String player) {
        ArrayList list = new ArrayList();
        ResultSet rs;
        try {
            stmt = conn.getConn().createStatement();
            rs = stmt.executeQuery("SELECT * FROM MatchInfo WHERE PlayerName='" + player + "'");
            while (rs.next()) {
                String[] matrix = new String[7];
                matrix[0] = rs.getString("PlayerName");
                matrix[1] = rs.getString("DateHour");
                matrix[2] = rs.getString("Duration");
                matrix[3] = rs.getString("TownHallLife");
                matrix[4] = rs.getString("Defenses");
                matrix[5] = rs.getString("Builders");
                matrix[6] = rs.getString("Warriors");

                list.add(matrix);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public int getTimePlayed(String player) {

        int time = 0;
        ResultSet rs;
        try {
            stmt = conn.getConn().createStatement();
            rs = stmt.executeQuery("SELECT SUM(Duration) AS duration FROM MatchInfo WHERE PlayerName='" + player + "'");

            if (rs.next()) {
                time = rs.getInt("duration");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
        }

        return time;
    }

    public boolean userExist(String player) {
        ResultSet rs;
        String query = "SELECT * FROM user WHERE name='" + player + "'";
        try {
            stmt = conn.getConn().createStatement();
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    //
    //Introducción de Datos
    //
    public boolean setUser(String name, String email, String password) {
        try {
            stmt = conn.getConn().createStatement();
            String insert = "INSERT INTO user VALUES('" + name + "','" + email + "','" + password + "',0,0)";
            stmt.executeUpdate(insert);
            conn.getConn().commit();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    //
    //Modificación/Borrado de Datos
    //
    public boolean setUserName(String name, String actualName) {
        try {
            stmt = conn.getConn().createStatement();
            String update = "UPDATE user SET name='" + name + "' WHERE name='" + actualName + "'";
            stmt.executeUpdate(update);
            conn.getConn().commit();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean setUserAlly(User u, User e) {
        try {
            stmt = conn.getConn().createStatement();
            String insert = "INSERT INTO Alliances VALUES('"+u.getName()+"','"+ e.getName()+"')";
            stmt.executeUpdate(insert);
            conn.getConn().commit();
            insert = "INSERT INTO Alliances VALUES('"+e.getName()+"','"+ u.getName()+"')";
            stmt.executeUpdate(insert);
            conn.getConn().commit();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
    public boolean setUserEnemy(User u, User e) {
        try {
            stmt = conn.getConn().createStatement();
            String insert = "INSERT INTO Enemies VALUES('"+u.getName()+"','"+ e.getName()+"')";
            stmt.executeUpdate(insert);
            conn.getConn().commit();
            insert = "INSERT INTO Enemies VALUES('"+e.getName()+"','"+ u.getName()+"')";
            stmt.executeUpdate(insert);
            conn.getConn().commit();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean deleteUserEnemy(User u, User e) {
        try {
            stmt = conn.getConn().createStatement();
            String delete = "DELETE FROM Enemies WHERE Player1='"+e.getName()+"', AND Player2='"+u.getName()+"'";
            stmt.executeUpdate(delete);
            conn.getConn().commit();
            delete = "DELETE FROM Enemies WHERE Player1='"+u.getName()+"', AND Player2='"+e.getName()+"'";
            stmt.executeUpdate(delete);
            conn.getConn().commit();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean deleteUserAlly(User u, User e) {
        try {
            stmt = conn.getConn().createStatement();
            String delete = "DELETE FROM Alliances WHERE Player1='"+e.getName()+"', AND Player2='"+u.getName()+"'";
            stmt.executeUpdate(delete);
            conn.getConn().commit();
            delete = "DELETE FROM Alliances WHERE Player1='"+u.getName()+"', AND Player2='"+e.getName()+"'";
            stmt.executeUpdate(delete);
            conn.getConn().commit();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean setPassword(String password, String name) {
        try {
            stmt = conn.getConn().createStatement();
            String update = "UPDATE user SET password='" + password + "' WHERE name='" + name + "'";
            stmt.executeUpdate(update);
            conn.getConn().commit();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean setEmail(String email, String name) {
        try {
            stmt = conn.getConn().createStatement();
            String update = "UPDATE user SET email='" + email + "' WHERE name='" + name + "'";
            stmt.executeUpdate(update);
            conn.getConn().commit();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean updateUserMatchWinner(User u, String[] data, List<User> rivals) {
        try {
            stmt = conn.getConn().createStatement();
            String update = "UPDATE user SET win='" + u.getWin() + "', plays='" + u.getPlays() + "' WHERE name='" + u.getName() + "'";
            stmt.executeUpdate(update);
            conn.getConn().commit();

            for (int i = 0; i < rivals.size(); i++) {
                stmt = conn.getConn().createStatement();
                update = "UPDATE user SET win='" + rivals.get(i).getWin() + "', plays='" + rivals.get(i).getPlays() + "' WHERE name='" + rivals.get(i).getName() + "'";
                stmt.executeUpdate(update);
                conn.getConn().commit();
            }

            String insert = "INSERT INTO MatchInfo VALUES('" + u.getName() + "','" + data[0] + "','" + data[1] + "','" + Integer.parseInt(data[2]) + "','" + Integer.parseInt(data[3]) + "','" + Integer.parseInt(data[4]) + "','" + Integer.parseInt(data[5]) + "')";
            stmt.executeUpdate(insert);

            conn.getConn().commit();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean updateUserMatchLoser(User u, String[] data, List<User> rivals) {
        try {
            stmt = conn.getConn().createStatement();
            String update = "UPDATE user SET win='" + u.getWin() + "', plays='" + u.getPlays() + "' WHERE name='" + u.getName() + "'";
            stmt.executeUpdate(update);
            conn.getConn().commit();
            
            for (int i = 0; i < rivals.size(); i++) {
                stmt = conn.getConn().createStatement();
                update = "UPDATE user SET win='" + rivals.get(i).getWin() + "', plays='" + rivals.get(i).getPlays() + "' WHERE name='" + rivals.get(i).getName() + "'";
                stmt.executeUpdate(update);
                conn.getConn().commit();
            }

            String insert = "INSERT INTO MatchInfo VALUES('" + u.getName() + "','" + data[0] + "','" + data[1] + "','" + Integer.parseInt(data[2]) + "','" + Integer.parseInt(data[3]) + "','" + Integer.parseInt(data[4]) + "','" + Integer.parseInt(data[5]) + "')";
            stmt.executeUpdate(insert);

            conn.getConn().commit();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    //
    //Setter de la Conexión
    //
//    public void setConn(Connection conn) {
//        this.conn = conn;
//        try {
//            conn.getConn().setAutoCommit(false);
//        } catch (SQLException ex) {
//            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
