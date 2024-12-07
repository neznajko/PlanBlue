////////////////////////////////////////////////////////////////
package com.planblue;
////////////////////////////////////////////////////////////////
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
////////////////////////////////////////////////////////////////
import java.awt.Dialog.ModalityType;
import java.awt.Color;
import java.awt.FlowLayout;
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
class Select extends JDialog {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 200;
    private JFrame parent;
    Canvas canvas;
    private void addButton( String name ){
        String path = ImageUtil.getImagePath( name + "_big" );
        var butn = new JButton( new ImageIcon( path ));
        butn.addActionListener( e -> {
            dispose();
            canvas = new Canvas( name ); 
            parent.add( canvas );
            parent.setVisible( true );
            canvas.requestFocus();
        });
        butn.setBackground( Color.BLACK );
        add( butn );
    }
    private Select(){
        setModalityType( ModalityType.APPLICATION_MODAL );
        setSize( WIDTH, HEIGHT );
        getContentPane().setBackground( Color.BLUE );
        setLayout( new FlowLayout());
        addButton( "red_plane" );
        addButton( "blue_plane" );
    }
    private void setParent( JFrame parent ){
        this.parent = parent;
    }
    void showDialog() {
        setVisible( true );
    }
    public static class Builder {
        private Select dialog;
        Builder() {
            dialog = new Select();
        }
        void setTitle( String title ){
            dialog.setTitle( title );
        }
        void setParent( JFrame parent ){
            dialog.setParent( parent );
        }
        Select create() {
            return dialog;
        }
    }
}
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
