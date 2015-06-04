/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.indevgroup.cn1.tests.beziermotion;

import com.codename1.io.Log;
import com.codename1.ui.Container;
import com.codename1.ui.animations.Motion;
import com.codename1.ui.layouts.Layout;

/**
 *
 * @author Aleksey Cherepanov
 */
public class CustomMotionContainer extends Container{
    private float p1 = 00f;
    private float p2 = 0f;
    private float p3 = 0.75f;
    private float p4 = 1f;

    public CustomMotionContainer(Layout layout) {
        super(layout);
    }

    public CustomMotionContainer() {
        super();
    }
    
    public void setMotion(float p1, float p2, float p3, float p4) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
    }
    
    @Override
    protected Motion createAnimateMotion(int start, int destination, int duration) {
        int dur = duration;//int dur = (int) (duration*(new Random().nextDouble()));
        Log.p("p1 = "+p1+"; p2 = "+p2+"; p3 = "+p3+"; p4 = "+p4+";");
        return Motion.createCubicBezierMotion(start, destination, dur, p1,p2,p3,p4);
    }
}
