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
class Canvas extends JPanel implements Runnable {
    volatile boolean running = true;
    volatile boolean paused = false;
    Plane plane;
    Canvas( String plane_name ){
        setLayout( null ); //          absolute positioning
        setBackground( Color.BLUE );
        plane = Plane.factory( 120, 250, plane_name );
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
                case KeyEvent.VK_ENTER:
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
        plane.draw( g );
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