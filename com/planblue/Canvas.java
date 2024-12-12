////////////////////////////////////////////////////////////////
package com.planblue;
////////////////////////////////////////////////////////////////
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
////////////////////////////////////////////////////////////////
import javax.swing.JPanel;
////////////////////////////////////////////////////////////////
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
class Canvas extends JPanel implements Runnable {
    volatile boolean running = true;
    volatile boolean paused = false;
    Plane plane;
    EnemyChain enemyChain;
    Score score;
    Life life;
    Mediator mediator;
    TiledLayer mapLayer;
    TiledLayer mapContext[] = { new MapLayer(), new MapLayer2() };
    int screenY;
    int context_switch = 0;
    Canvas( String plane_name ){
        setLayout( null ); //               absolute positioning
        setBackground( Color.BLUE );
        setPreferredSize( new Dimension( PlanBlue.WIDTH,
                                         PlanBlue.HEIGHT ));
        plane = Plane.factory( 120, 250, plane_name );
        plane.reset();
        enemyChain = new EnemyChain();
        enemyChain.add( new ChainNode( 2, "enemy_plane1" ));
        enemyChain.add( new ChainNode( 1, "enemy_plane2" ));
        enemyChain.add( new ChainNode( 1, "boss" ));
        score = new Score();
        life = new Life();
        mediator = new Mediator();
        mapLayer = mapContext[ context_switch ];
        screenY = -PlanBlue.HEIGHT;
        // mc adapta
        addKeyListener( new KeyAdapter() {
            public void keyPressed( KeyEvent e ){
                switch( e.getKeyCode()){
                case KeyEvent.VK_UP:
                    plane.move( 0, -6 );
                    break;
                case KeyEvent.VK_DOWN:
                    plane.move( 0, +6 );
                    break;
                case KeyEvent.VK_LEFT:
                    plane.move( -6, 0 );
                    break;
                case KeyEvent.VK_RIGHT:
                    plane.move( +6, 0 );
                    break;
                case KeyEvent.VK_SPACE:
                    plane.fire();
                    break;
                case KeyEvent.VK_P:
                    paused = !paused;
                    break;
                default: break;
                }
            }
        });
        setFocusable( true );
        new Thread( this ).start();
    }
    protected void paintComponent( Graphics g ){
        super.paintComponent( g );
        drawMap( g );
        enemyChain.move( g );
        plane.draw( g );
        score.draw( g );
        life.draw( g );
        collideCheck( g );
    }
    void drawMap( Graphics g ){
        mapLayer.setViewPort( 0, screenY );
        mapLayer.draw( g );
        if( screenY >= 0 ){
            screenY = -PlanBlue.HEIGHT;
            context_switch = 1 - context_switch;
            mapLayer = mapContext[ context_switch ];
        } else {
            ++screenY;
        }
    }
    void collideCheck( Graphics g ){
        var bullet = plane.bullet;
        var enemyPlane = ( enemyChain
                           .collidingPlane( bullet ));
        if( enemyPlane != null ){
            enemyPlane.visible = false;
            bullet.visible = false;
            score.inc( 100 );
            mediator.handle( g, bullet.x, bullet.y );
        }
        enemyPlane = ( enemyChain
                       .collidingPlane( plane ));
        if( enemyPlane != null ){
            enemyPlane.visible = false;
            plane.visible = false;
            life.dec();
            mediator.handle( g, plane.x, plane.y );
            if( life.islife()) {
                plane.reset();
            } else {
                repaint();
                running = false;
            }
        }
    }
    @Override
    public void run() {
        try {
            while( running ){
                if( !paused ){
                    repaint();
                    plane.movebullet();
                    Thread.sleep( 50 );
                }
            }
        } catch( InterruptedException e ){
            e.printStackTrace();
        }
    }
}
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
