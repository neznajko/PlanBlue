////////////////////////////////////////////////////////////////
package com.planblue;
////////////////////////////////////////////////////////////////
import java.awt.image.BufferedImage;
////////////////////////////////////////////////////////////////
class EnemyPlane extends Sprite {
    EnemyPlane( int x, int y, BufferedImage image ){
        super( x, y, image );
        visible = true;
    }
    static EnemyPlane factory( int x, int y, String name ){
        if( name == "boss" ){
            return new Boss( x, y );
        }
        if( name == "enemy_plane1" ){
            return new Enemy_Plane1( x, y );
        }
        if( name == "enemy_plane2" ){
            return new Enemy_Plane2( x, y );
        }
        throw new IllegalArgumentException();
    }
}
////////////////////////////////////////////////////////////////
class Boss extends EnemyPlane {
    Boss( int x, int y ){
        super( x, y, ImageCache.get( "boss" ));
    }
}
////////////////////////////////////////////////////////////////
class Enemy_Plane1 extends EnemyPlane {
    Enemy_Plane1( int x, int y ){
        super( x, y, ImageCache.get( "enemy_plane1" ));
    }
}
////////////////////////////////////////////////////////////////
class Enemy_Plane2 extends EnemyPlane {
    Enemy_Plane2( int x, int y ){
        super( x, y, ImageCache.get( "enemy_plane2" ));
    }
}
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////

