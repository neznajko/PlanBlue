////////////////////////////////////////////////////////////////
package com.planblue;
////////////////////////////////////////////////////////////////
import java.awt.Graphics;
////////////////////////////////////////////////////////////////
interface Media {
    void action( Graphics g, int x, int y );
}
////////////////////////////////////////////////////////////////
class Bom extends Sprite implements Media {
    Bom() {
        super( 0, 0, ImageCache.get( "bom" ));
    }
    public void action( Graphics g, int x, int y ){
        this.x = x;
        this.y = y;
        visible = true;
        draw( g );
        visible = false;
    }
}
////////////////////////////////////////////////////////////////
class BomMusic implements Runnable, Media {
    static final String BOMWAV = "media/hit.wav";
    public void run() {
        new MakeSound().playSound( BOMWAV );
    }
    public void action( Graphics g, int x, int y ){
        new Thread( this ).start();
    }
}
////////////////////////////////////////////////////////////////
class Mediator {
    Bom bom;
    BomMusic bomMusic;
    Mediator() {
        bom = new Bom();
        bomMusic = new BomMusic();
    }
    void handle( Graphics g, int x, int y ){
        bom.action( g, x, y );
        bomMusic.action( g, x, y );
    }
}
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
