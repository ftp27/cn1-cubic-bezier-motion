package eu.indevgroup.cn1.tests.beziermotion;


import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.NumericSpinner;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;

public class main {

    private Form current;

    public void init(Object context) {
        try {
            Resources theme = Resources.openLayered("/theme");
            UIManager.getInstance().setThemeProps(theme.getTheme(theme.getThemeResourceNames()[0]));
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void start() {
        if(current != null){
            current.show();
            return;
        }
        Form hi = new Form("Custom Motion Test");
        Container controlContainer = new Container(new BorderLayout());
        Container spinnersContainer = new Container (new TableLayout(4, 2));
        
        final BezierVisualisator visualisator = new BezierVisualisator();
        visualisator.setBezier(0.0, 0.0, 0.0, 0.0);
        controlContainer.addComponent(BorderLayout.CENTER, visualisator);
        
        final double minValue = -2.0;
        final double maxValue = 2.0;
        final double div = (maxValue-minValue)/100;
        
        final Label beginXl = new Label("0.0");
        final Label beginYl = new Label("0.0");
        final Label endXl = new Label("0.0");
        final Label endYl = new Label("0.0");
        
        final Slider beginX = new Slider();
        final Slider beginY = new Slider();
        final Slider endX = new Slider();
        final Slider endY = new Slider();
        beginX.setProgress(50); beginY.setProgress(50); endX.setProgress(50); endY.setProgress(50);
        beginX.setEditable(true); beginY.setEditable(true); endX.setEditable(true); endY.setEditable(true);
        spinnersContainer.addComponent(beginX); spinnersContainer.addComponent(beginXl);
        spinnersContainer.addComponent(beginY); spinnersContainer.addComponent(beginYl);
        spinnersContainer.addComponent(endX);   spinnersContainer.addComponent(endXl);
        spinnersContainer.addComponent(endY);   spinnersContainer.addComponent(endYl);
        
        ActionListener onChange;
        onChange = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                visualisator.setBezier(
                        beginX.getProgress()*div+minValue,
                        beginY.getProgress()*div+minValue,
                        endX.getProgress()*div+minValue,
                        endY.getProgress()*div+minValue
                );
                beginXl.setText(new Double(beginX.getProgress()*div+minValue).toString());
                beginYl.setText(new Double(beginY.getProgress()*div+minValue).toString());
                endXl.setText(new Double(endX.getProgress()*div+minValue).toString());
                endYl.setText(new Double(endY.getProgress()*div+minValue).toString());
            }
        };
        beginX.addActionListener(onChange);
        beginY.addActionListener(onChange);
        endX.addActionListener(onChange);
        endY.addActionListener(onChange);
        
        Button bt = new Button("animate");
        bt.addActionListener(onChange);
        
        controlContainer.addComponent(BorderLayout.SOUTH, spinnersContainer);
        hi.setLayout(new BorderLayout());
        
        hi.addComponent(BorderLayout.CENTER, controlContainer);
        hi.addComponent(BorderLayout.SOUTH, bt);

        hi.show();
    }

    public void stop() {
        current = Display.getInstance().getCurrent();
    }
    
    public void destroy() {
    }

}
