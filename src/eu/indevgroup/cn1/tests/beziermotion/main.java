package eu.indevgroup.cn1.tests.beziermotion;


import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.NumericSpinner;
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
        Container spinnersContainer = new Container (new GridLayout(1, 4));
        
        final BezierVisualisator visualisator = new BezierVisualisator();
        visualisator.setBezier(0.0, 0.0, 0.0, 0.0);
        controlContainer.addComponent(BorderLayout.CENTER, visualisator);
        
        final NumericSpinner beginX = new NumericSpinner();
        final NumericSpinner beginY = new NumericSpinner();
        final NumericSpinner endX = new NumericSpinner();
        final NumericSpinner endY = new NumericSpinner();
        beginX.setMax(2.0);   beginY.setMax(2.0);   endX.setMax(2.0);   endY.setMax(2.0);
        beginX.setMin(-2.0);  beginY.setMin(-2.0);  endX.setMin(-2.0);  endY.setMin(-2.0);
        beginX.setValue(0.0); beginY.setValue(0.0); endX.setValue(0.0); endY.setValue(0.0);
        beginX.setStep(0.1); beginY.setStep(0.1); endX.setStep(0.1); endY.setStep(0.1); 
        spinnersContainer.addComponent(beginX); spinnersContainer.addComponent(beginY);
        spinnersContainer.addComponent(endX);   spinnersContainer.addComponent(endY);
        
        ActionListener onChange = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                visualisator.setBezier(
                    beginX.getValue(), 
                    beginY.getValue(), 
                    endX.getValue(), 
                    endY.getValue()
                );
            }
        };
       // beginX.listener(onChange);
        beginY.addPointerPressedListener(onChange);
        endX.addPointerPressedListener(onChange);
        endY.addPointerPressedListener(onChange);
        
        Button bt = new Button("change");
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
