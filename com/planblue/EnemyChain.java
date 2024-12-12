////////////////////////////////////////////////////////////////
package com.planblue;
////////////////////////////////////////////////////////////////
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.LinkedList;
////////////////////////////////////////////////////////////////
import java.awt.Graphics;
////////////////////////////////////////////////////////////////
class ChainNode {
    static Random random = new Random();
    static int randInt( int min, int max ){
        return random.nextInt( max + 1 - min ) + min;
    }
    int cap;
    String planeName;
    ChainNode( int cap, String planeName ){
        this.cap = cap;
        this.planeName = planeName;
    }
    List <EnemyPlane> create() {
        List <EnemyPlane> ls = new ArrayList <> ();
        for( int j = 0; j < cap; ++j ){
            var enemy = EnemyPlane.factory( 0, 0, planeName );
            enemy.x = randInt( 0, PlanBlue.WIDTH - enemy.width );
            enemy.y = -enemy.height;
            ls.add( enemy );
        }
        return ls;
    }
}
////////////////////////////////////////////////////////////////
class EnemyChain {
    static final String [] PLANENAMES = {
        "enemy_plane1",
        "enemy_plane2",
        "boss"
    };
    LinkedList <ChainNode> enemyChain;
    List <EnemyPlane> enemyPlanes;
    int timeline;
    int timenext;
    int generateTimeInterval() {
        return ChainNode.randInt( 10, 50 );
    }
    ChainNode generateEnemyPlane() {
        int j = ChainNode.randInt( 0, PLANENAMES.length - 1 );
        return new ChainNode( 1, PLANENAMES[ j ]);
    }
    EnemyChain() {
        enemyChain = new LinkedList <> ();
        enemyPlanes = new ArrayList <> ();
        timeline = 0;
        timenext = generateTimeInterval();
    }
    void add( ChainNode chainNode ){
        enemyChain.add( chainNode );
    }
    void move( Graphics g ){
        if( enemyChain.size() > 0 && timeline == timenext ){
            var chainNode = enemyChain.poll();
            enemyPlanes.addAll( chainNode.create());
            timenext = timeline + generateTimeInterval();
            enemyChain.add( generateEnemyPlane());
        }
        for( EnemyPlane enemyPlane: enemyPlanes ){
            if( enemyPlane.visible ){
                enemyPlane.draw( g );
                enemyPlane.move( 0, 2 );
                if( enemyPlane.outOfCanvas()){
                    enemyPlane.visible = false;
                }
            }
        }
        ++timeline;
    }
    EnemyPlane collidingPlane( Sprite sprite ){
        for( EnemyPlane enemyPlane: enemyPlanes ){
            if( enemyPlane.visible ){
                if( enemyPlane.collideWith( sprite )){
                    return enemyPlane;
                }
            }
        }
        return null;
    }
}
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
