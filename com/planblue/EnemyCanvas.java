////////////////////////////////////////////////////////////////
package com.planblue;
////////////////////////////////////////////////////////////////
import java.awt.Color;
import java.awt.Graphics;
////////////////////////////////////////////////////////////////
import javax.swing.JPanel;
////////////////////////////////////////////////////////////////
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
////////////////////////////////////////////////////////////////
import java.io.IOException;
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
class EnemyCanvas extends JPanel implements Runnable {
    volatile boolean running = true;
    volatile boolean paused = false;
    EnemyChain enemyChain = new EnemyChain();
    EnemyCanvas() {
        setLayout( null ); //          absolute positioning
        setBackground( Color.BLUE );
        enemyChain.add( new ChainNode( 2, "enemy_plane1" ));
        enemyChain.add( new ChainNode( 1, "enemy_plane2" ));
        enemyChain.add( new ChainNode( 1, "boss" ));
        // mc adapta
        addKeyListener( new KeyAdapter() {
            public void keyPressed( KeyEvent e ){
                switch( e.getKeyCode()){
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
        enemyChain.move( g );
    }
    @Override
    public void run() {
        try {
            while( running ){
                if( !paused ){
                    repaint();
                    Thread.sleep( 100 );
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
