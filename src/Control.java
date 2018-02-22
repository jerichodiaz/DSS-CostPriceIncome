import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.EventListener;
import java.util.ResourceBundle;

/**
 * Created by Diaz, Jericho Hans
 * On 2/21/2018
 */
public class Control implements Initializable {
    private ToggleGroup group;
    @FXML private TextField cost, price, income;
    @FXML private RadioButton rCost, rPrice, rIncome;
    @FXML private Slider sCost, sPrice, sIncome;

    @FXML private void costChange(){
        if(!cost.getText().isEmpty() && !cost.getText().matches(".*\\D.*")) {
            double cost = Double.parseDouble(this.cost.getText());
            double price = Double.parseDouble(this.price.getText());
            double income = Double.parseDouble(this.income.getText());
            if (rPrice.isSelected()) {
                sIncome.setValue(cost * (price / 100));
            } else if (rIncome.isSelected())
                sPrice.setValue((income / cost) * 100.0);
            else
                sCost.setValue(cost);
        }
        else
            sCost.setValue(0);
    }
    @FXML private void priceChange(){
        if(!price.getText().isEmpty() && !price.getText().matches(".*\\D.*")) {
            double cost = Double.parseDouble(this.cost.getText());
            double price = Double.parseDouble(this.price.getText());
            double income = Double.parseDouble(this.income.getText());
            if (rCost.isSelected()) {
                sIncome.setValue(cost * (price / 100));
            } else if (rIncome.isSelected())
                sCost.setValue((income * 100.0) / price);
            else
                sPrice.setValue(price);
        }
        else
            sPrice.setValue(0);
    }
    @FXML private void incomeChange(){
        if(!income.getText().isEmpty() && !income.getText().matches(".*\\D.*")) {
            double cost = Double.parseDouble(this.cost.getText());
            double price = Double.parseDouble(this.price.getText());
            double income = Double.parseDouble(this.income.getText());
            if (rPrice.isSelected()) {
                sCost.setValue((income * 100.0) / price);
            } else if (rCost.isSelected())
                sPrice.setValue((income / cost) * 100.0);
            else
                sIncome.setValue(income);
        }
        else
            sIncome.setValue(0);
    }
    @FXML private void onDragCost(){
        cost.setText(Math.round(sCost.getValue())+"");
        costChange();
    }
    @FXML private void onDragPrice(){
        price.setText(Math.round(sPrice.getValue())+"");
        priceChange();
    }
    @FXML private void onDragIncome(){
        income.setText(Math.round(sIncome.getValue())+"");
        incomeChange();
    }
    @FXML private void disableButtons(){
        cost.setDisable(rCost.isSelected());
        price.setDisable(rPrice.isSelected());
        income.setDisable(rIncome.isSelected());
        sCost.setDisable(rCost.isSelected());
        sPrice.setDisable(rPrice.isSelected());
        sIncome.setDisable(rIncome.isSelected());
    }
    @FXML private void randomize(){
        int x = (int) (Math.random()*100);
        int y = (int) (Math.random()*100);
        int z = (int) (Math.random()*100);
        sCost.setValue(x);
        sPrice.setValue(y);
        sIncome.setValue(z);
        cost.setText(x+"");
        price.setText(y+"");
        income.setText(z+"");
        rPrice.setSelected(true);
        disableButtons();
        costChange();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        group = new ToggleGroup();
        rCost.setToggleGroup(group);
        rCost.setSelected(true);
        rPrice.setToggleGroup(group);
        rIncome.setToggleGroup(group);
        randomize();

        sCost.valueProperty().addListener((ol, ov, nv) -> {
            while(nv.doubleValue() >= sCost.getMax())
                sCost.setMax(sCost.getMax()+1);
            onDragCost();
        });
        sPrice.valueProperty().addListener((ol, ov, nv) -> {
            onDragPrice();
        });
        sIncome.valueProperty().addListener((ol, ov, nv) -> {
            while(nv.doubleValue()>=sIncome.getMax())
                sIncome.setMax(sIncome.getMax()+1);
            onDragIncome();
        });

    }
}
