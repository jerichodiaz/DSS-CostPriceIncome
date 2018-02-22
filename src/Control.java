import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

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
    double dCost, dPrice, dIncome, pCost, pPrice, pIncome;


    @FXML private void savePreviousCost(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER) {
            pCost = Double.parseDouble(cost.getText());
            sCost.setValue(0);
        }
    }
    @FXML private void savePreviousPrice(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER) {
            pPrice = Double.parseDouble(price.getText());
            sPrice.setValue(0);
        }
    }
    @FXML private void savePreviousIncome(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER) {
            pIncome = Double.parseDouble(income.getText());
            sIncome.setValue(0);
        }
    }

    @FXML private void costChange(){
        if(rPrice.isSelected())
            calcIncome();
        else if(rIncome.isSelected())
            calcPrice();
    }
    @FXML private void priceChange(){
        if(rCost.isSelected())
            calcIncome();
        else if(rIncome.isSelected())
            calcCost();
    }
    @FXML private void incomeChange(){
        if(rPrice.isSelected())
            calcCost();
        else if(rCost.isSelected())
            calcPrice();
    }

    private void calcCost(){
        doubleValues();
        cost.setText((dIncome * 100.0) / dPrice+"");
    }
    private void calcPrice(){
        doubleValues();
        price.setText((dIncome / dCost) * 100.0+"");
    }
    private void calcIncome(){
        doubleValues();
        income.setText(dCost * (dPrice / 100)+"");
    }

    private void doubleValues(){
        dCost = Double.parseDouble(this.cost.getText());
        dPrice = Double.parseDouble(this.price.getText());
        dIncome = Double.parseDouble(this.income.getText());
    }


    @FXML private void onDragCost(){

        costChange();
    }
    @FXML private void onDragPrice(){

        priceChange();
    }
    @FXML private void onDragIncome(){

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
            this.cost.setText((pCost+nv.doubleValue())+"");
            costChange();
        });
        sPrice.valueProperty().addListener((ol, ov, nv) -> {
            this.price.setText((pPrice+nv.doubleValue())+"");
            priceChange();

        });
        sIncome.valueProperty().addListener((ol, ov, nv) -> {
            this.income.setText((pIncome+nv.doubleValue())+"");
            incomeChange();

        });

    }
}
