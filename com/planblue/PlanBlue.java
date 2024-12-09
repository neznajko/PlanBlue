////////////////////////////////////////////////////////////////
package com.planblue;
////////////////////////////////////////////////////////////////
import java.awt.EventQueue;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
////////////////////////////////////////////////////////////////
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JComponent;
//// / /  / //    ////// //  /          ////////////////////////
/// / /  / //    ////// //  /          /////////////////////////
// / /  / //    ////// //  /          //////////////////////////
class PlanBlue extends JFrame {
    static final int WIDTH = 300;
    static final int HEIGHT = 400;
    static {
        ImageUtil.imagesDirectory = "images";
        ImageUtil.imagesSuffix = "png";
        ImageCache.put( "blue_plane" );
        ImageCache.put( "red_plane" );
        ImageCache.put( "blue_bullet" );
        ImageCache.put( "red_bullet" );
        ImageCache.put( "boss" );
        ImageCache.put( "enemy_plane1" );
        ImageCache.put( "enemy_plane2" );
    }
    ////////////////////////////////////////////////////////////
    Select select;
    PlanBlue( String title ){
        setTitle( title );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setSize( WIDTH, HEIGHT );
        getContentPane().setBackground( new Color( 0x888888 ));
        if( false ){
            var builder = new Select.Builder();
            builder.setTitle( "Select plane" );
            builder.setParent( this );
            select = builder.create();
            //
            var menuBar = new MenuBar( this );
            var popupMenu = new PopupMenu( this );
            menuBar.create();
            popupMenu.create();
            //
        } else {
            var canvas = new EnemyCanvas();
            add( canvas );
            canvas.requestFocus();
        }
        setVisible( true );
    }
    public static void main( String[] args ){
        EventQueue.invokeLater(() -> {
            new PlanBlue( "Plan Blue" );        
        });
    }
}
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
abstract class MenuContainer {
    PlanBlue planblue;
    JComponent menuContainer;
    JMenu gameMenu;
    JMenuItem exitMenu;
    JMenuItem newGame;
    JMenuItem stopGame;
    JMenuItem resumeGame;
    MenuContainer( PlanBlue planblue ){
        this.planblue = planblue;
    }
    abstract void createMenuContainer();
    void create() {
        createMenuContainer();
        addMenus();
        addActionListeners();
        addMouseListeners();
    }
    void addMenus() {
        gameMenu = new JMenu( "Game" );
        exitMenu = new JMenuItem( "Exit" );
        menuContainer.add( gameMenu );
        menuContainer.add( exitMenu );
        newGame = new JMenuItem( "New" );
        stopGame = new JMenuItem( "Stop" );
        resumeGame = new JMenuItem( "Resume" );
        gameMenu.add( newGame );
        gameMenu.add( stopGame );
        gameMenu.add( resumeGame );
    }
    void addActionListeners() {
        newGame.addActionListener( e -> {
            planblue.select.showDialog();
        });
        stopGame.addActionListener(  e -> {
            planblue.select.canvas.paused = true;
        });
        resumeGame.addActionListener(  e -> {
            planblue.select.canvas.paused = false;
        });
        exitMenu.addActionListener( e -> {
            System.exit( 0 );
        });
    }
    void addMouseListeners() {}
}
////////////////////////////////////////////////////////////////
class MenuBar extends MenuContainer {
    MenuBar( PlanBlue planblue ){
        super( planblue );
    }
    void createMenuContainer() {
        menuContainer = new JMenuBar();
        planblue.setJMenuBar(( JMenuBar )menuContainer );
    }
}
////////////////////////////////////////////////////////////////
class PopupMenu extends MenuContainer {
    PopupMenu( PlanBlue planblue ){
        super( planblue );
    }
    void createMenuContainer() {
        menuContainer = new JPopupMenu();
    }
    @Override
    void addMouseListeners() {
        planblue.addMouseListener( new MouseAdapter() {
            @Override
            public void mouseReleased( MouseEvent e ){
                if( e.isPopupTrigger()) {
                    (( JPopupMenu )menuContainer )
                    .show( planblue, e.getX(), e.getY());
                }
            }
            @Override
            public void mousePressed( MouseEvent e ){
                if( e.isPopupTrigger()) {
                    (( JPopupMenu )menuContainer )
                    .show( planblue, e.getX(), e.getY());
                }
            }
        });
    }
}
////////////////////////////////////////////////////////////////
// 2.1.4. A load is secured to a cart by  four stretched threads.
// The  tension  force of the  horizontal   threads is T1 and T2,
// respectively,  and  the vertical threads  are T3 and T4. With
// what acceleration does  the  cart  move  along the horizontal
// plane? //////////////////////////////////////////////////////
////////////////////////// T2 - T1 = ma ////////////////////////
// +------+-----------+ // T4 = T3 + mg, zo ////////////////////
// |      |T4         | ////////////////////////////////////////
// +-----load---------+ // m = ( T4 - T3 )\g ///////////////////
// | T1   |        T2 | ////////////////////////////////////////
// |      |T3         | // a = ( T2 - T1 )|m = /////////////////
// +------+-----------+ //// = g( T2 - T1 )|( T4 - T3 ) ////////
//    O            0    ////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
// 2.1.5. What  force acts in the cross-section of a uniform rod
// of length l at  a  distance x from the end to which a force F
// is applied along the rod? ///////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
// Fri 6  - Decorator                                          V
// Sat 7  - Composite                                          V
// Sun 8  - Chain                                              V
// Mon 9  - Observer
// Tue 10 - Mediator
// Wed 11 - State
// Thu 12 - Finish
