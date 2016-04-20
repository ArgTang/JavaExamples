package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class Controller {
    @FXML
    private Label sliderValue;
    @FXML
    private Label sliderDoubleProp;
    @FXML
    private Label sliderUpdateLabel;
    @FXML
    private Slider slider;

    @FXML
    void initialize() {
        addListeners();
    }

    private void addListeners() {
        //This one displays the doublePropertyValue when
        sliderDoubleProp.visibleProperty().bind(slider.valueChangingProperty());
        sliderDoubleProp.textProperty().bind(slider.valueProperty().asString());

        //This one Displays value of slider when dragging, not only when done dragging as the method below does
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            sliderValue.setText(((int) slider.getValue()) + "");
        });

        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (!slider.isValueChanging()) {
                System.out.println(newValue);
                sliderUpdateLabel.setText("BOOM: " + newValue);
            } else {
                sliderUpdateLabel.setText("");
            }
        });

        // http://stackoverflow.com/questions/18892070/javafx-2-2-hooking-slider-drag-n-drop-events?rq=1
        /*
        slider.valueProperty().addListener((observableValue, previous, now) -> {
            if (!slider.isValueChanging()
                    || now.doubleValue() == slider.getMax()
                    || now.doubleValue() == slider.getMin()) {
                sliderValue.setText(slider.valueProperty().asString().get());
            }
        });
        */
    }
}