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
    LinkedList <ChainNode> enemyChain;
    List <EnemyPlane> enemyPlanes;
    volatile int timeline = 45;
    EnemyChain() {
        enemyChain = new LinkedList <> ();
        enemyPlanes = new ArrayList <> ();
    }
    void add( ChainNode chainNode ){
        enemyChain.add( chainNode );
    }
    void move( Graphics g ){
        if( enemyChain.size() > 0 && 
            timeline > 0 && timeline % 15 == 0 ){
            var chainNode = enemyChain.poll();
            enemyPlanes.addAll( chainNode.create());
        }
        for( EnemyPlane enemyPlane: enemyPlanes ){
            if( enemyPlane.visible ){
                enemyPlane.draw( g );
                enemyPlane.move( 0, 5 );
                if( enemyPlane.outOfCanvas()){
                    enemyPlane.visible = false;
                }
            }
        }
        --timeline;
    }
}
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
