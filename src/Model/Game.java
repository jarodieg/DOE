package Model;

import Model.Terrain.Map;
import Model.Users.User;
import View.InfoGame;
import java.util.List;
import java.util.Random;
import no.geosoft.cc.graphics.GScene;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Dani
 */
public class Game {

    private AttAct ares;
    private DieAct hades;
    private MoveAct hermes;
    private BuildAct orfeo;
    private ShowAct apolo;
    private Controller zeus;
    private InfoGame info;
    private View.GUI.Game game;

    public static int BUILDER = 0;
    public static int WARRIOR = 1;
    public static int DEFENSE = 2;

    public static int CENTER = 0;
    public static int UP = 1;
    public static int DOWN = 2;
    public static int LEFT = 3;
    public static int RIGHT = 4;
    public static int UL = 5;
    public static int UR = 6;
    public static int DL = 7;
    public static int DR = 8;

    public Game(User player, List<User> rivals, Map map, GScene scene, InfoGame info, View.GUI.Game game) {
        this.info = info;
        this.game = game;
        zeus = new Controller(player, rivals, map, scene, info);
        hades = new DieAct(zeus);
        hermes = new MoveAct(zeus);
        orfeo = new BuildAct(zeus);
        ares = new AttAct(zeus);
        apolo = new ShowAct(zeus);
        zeus.setInitialScenario();
    }

    private void moveUnits(User player, int posX, int posY) {
        List<Movable> l = zeus.getMovUnits(player);
        for (int i = 0; i < l.size(); i++) {
            zeus.addGO2Move(l.get(i), posX, posY);
        }
    }

    public void moveUnits(int move) {
        int X = zeus.getMap().getNumCasillasX();
        int Y = zeus.getMap().getNumCasillasY();
        switch (move) {
            case 0://CENTER                
                moveUnits(zeus.getPlayer(), X / 2, Y / 2);
                break;
            case 1://UP
                moveUnits(zeus.getPlayer(), X / 2, 0);
                break;
            case 2://DOWN                
                moveUnits(zeus.getPlayer(), X / 2, Y);
                break;
            case 3://LEFT
                moveUnits(zeus.getPlayer(), 0, Y / 2);
                break;
            case 4://RIGHT
                moveUnits(zeus.getPlayer(), X, Y / 2);
                break;
            case 5://UL
                moveUnits(zeus.getPlayer(), 0, 0);
                break;
            case 6://UR
                //moveUnits(zeus.getPlayer(), zeus.getMap().getNumCasillasX()-1,0);
                moveUnits(zeus.getPlayer(), X, 0);
                break;
            case 7://DL
                //moveUnits(zeus.getPlayer(), 0,zeus.getMap().getNumCasillasY()-1);
                moveUnits(zeus.getPlayer(), 0, Y);
                break;
            case 8://DR
                //moveUnits(zeus.getPlayer(), zeus.getMap().getNumCasillasX()-1,zeus.getMap().getNumCasillasY()-1);
                moveUnits(zeus.getPlayer(), X, Y);
                break;
        }
    }

    public void build(int unit) {
        switch (unit) {
            case 0://Builder
                build(zeus.getPlayer(), new Model.Units.Builder(2, 2, zeus.getPlayer()));
                break;
            case 1://Warrior
                build(zeus.getPlayer(), new Model.Units.Warrior(0, 0, zeus.getPlayer()));
                break;
            case 2://Defense
                build(zeus.getPlayer(), new Model.Buildings.Defense(0, 0, zeus.getPlayer()));
                break;
        }
    }

    private void build(User player, GameObj toBuild) {
        GOBuilder builder = zeus.getAppBuilder(player, toBuild);
        if (builder != null) {
            System.out.println("Constructor encontrado");
            List<Integer> pos = zeus.getPosLibre(builder);
            Random r = new Random();
            int iR = r.nextInt(pos.size() / 2);
            int posX = pos.get(iR);
            int posY = pos.get(iR + 1);
            zeus.addGO2Build(builder, posX, posY, toBuild);
        } else {
            System.out.println("No hay constructor");
        }
    }

    public int getXLimit() {
        return zeus.getMap().getNumCasillasX() - 1;
    }

    public int getYLimit() {
        return zeus.getMap().getNumCasillasY() - 1;
    }

    public void loop() {
       
            ares.run();
            hades.run();
            hermes.run();
            orfeo.run();
            apolo.run();
            System.out.flush();
        
        if (zeus.getFinalToken() == 1) {
            game.winner();
        }else if(zeus.getFinalToken() == 0){
            game.loser();
        }
    }
}
