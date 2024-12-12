////////////////////////////////////////////////////////////////
package com.planblue;
////////////////////////////////////////////////////////////////
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
/////////////////////////////////////////////////////////////////
import javax.swing.JPanel;
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
class MapCanvas extends JPanel implements Runnable {
    volatile boolean running = true;
    TiledLayer mapLayer;
    TiledLayer mapContext[] = { new MapLayer(), new MapLayer2() };
    int screenY;
    int context_switch = 0;
    MapCanvas() {
        setLayout( null ); //               absolute positioning
        setBackground( Color.BLUE );
        setPreferredSize( new Dimension( PlanBlue.WIDTH,
                                         PlanBlue.HEIGHT ));
        mapLayer = mapContext[ context_switch ];
        screenY = -PlanBlue.HEIGHT;
        new Thread( this ).start();
    }
    protected void paintComponent( Graphics g ){
        super.paintComponent( g );
        drawMap( g );
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
    @Override
    public void run() {
        try {
            while( running ){
                repaint();
                Thread.sleep( 50 );
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
