/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.indevgroup.cn1.tests.beziermotion;

import com.codename1.io.Log;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.Graphics;
import com.codename1.ui.Stroke;
import com.codename1.ui.Transform;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.GeneralPath;
import com.codename1.ui.geom.PathIterator;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.geom.Shape;

/**
 *
 * @author Aleksey Cherepanov
 */
public class BezierVisualisator extends Component{
    private double p1,p2,p3,p4;
    private Shape shape;

    @Override
    protected Dimension calcPreferredSize() {
        Display d = Display.getInstance();
        int w = d.getDisplayWidth();
        return new Dimension(w, w);
    }
    
    public void setBezier(double p1, double p2, double p3, double p4){
       this.p1 = p1;
       this.p2 = p2;
       this.p3 = p3;
       this.p4 = p4;
       repaint();
    }
    
    @Override
    public void paint(Graphics g) {
        g.setColor(getStyle().getBgColor());
        g.fillRect(getX(), getY(), getWidth(), getHeight());
        g.setColor(getStyle().getFgColor());
        GeneralPath shape = new GeneralPath();
        int s = Math.min(getWidth(), getHeight());
        int w = s/3;//getWidth()/3;
        int shift = w;
        shape.moveTo(shift, shift);
        shape.curveTo(shift+p1*w, shift+p2*w, shift+p3*w, shift+p4*w, shift+w, shift+w);
        g.drawShape(shape, new Stroke(1, 1, 1, 1));
        g.setColor(0xFF0000);
        g.drawRect(w, w, w, w);
        g.setColor(0x0000FF);
        g.drawRect(getX(), getY(), s, s);
    }
}
