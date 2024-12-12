////////////////////////////////////////////////////////////////
package com.planblue;
////////////////////////////////////////////////////////////////
import java.awt.Color;
import java.awt.Graphics;
////////////////////////////////////////////////////////////////
import java.awt.image.BufferedImage;
////////////////////////////////////////////////////////////////
class Score {
    static final int X = PlanBlue.WIDTH - 40;
    static final int Y = PlanBlue.HEIGHT - 20;
    int score;
    Score() {
        score = 0;
    }
    void draw( Graphics g ){
        g.setColor( Color.WHITE );
        g.drawString( String.valueOf( score ), X , Y );
    }
    void inc( int value ){
        score += value;
    }    
}
////////////////////////////////////////////////////////////////
class Life {
    static final int X = 10;
    static final int Y = Score.Y;
    static final BufferedImage IMG =
        ImageCache.get( "blue_plane_small" );
    int nflives;
    Life() {
        nflives = 3;
    }
    void draw( Graphics g ){
        int x = X;
        for( int j = 0; j < nflives; ++j ){
            g.drawImage( IMG, x, Y, null );
            x += 10;
        }
    }
    void dec() {
        --nflives;
    }
    boolean islife() {
        return nflives > 0;
    }
}
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
