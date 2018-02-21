import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Diaz, Jericho Hans
 * On 2/21/2018
 */
public class Control implements Initializable {
    private ToggleGroup group;
    @FXML private TextField cost, price, income;
    @FXML private RadioButton rCost, rPrice, rIncome;

    @FXML private void costChange(){
        double cost = Double.parseDouble(this.cost.getText());
        double price = Double.parseDouble(this.price.getText());
        double income = Double.parseDouble(this.income.getText());
        if(rPrice.isSelected()){
            this.income.setText(cost*(price/100)+"");
        }
        else if(rIncome.isSelected())
            this.price.setText((income/cost)*100.0+"");
        else
            this.cost.setText(cost+"");
    }
    @FXML private void priceChange(){
        double cost = Double.parseDouble(this.cost.getText());
        double price = Double.parseDouble(this.price.getText());
        double income = Double.parseDouble(this.income.getText());
        if(rCost.isSelected()){
            this.income.setText(cost*(price/100)+"");
        }
        else if(rIncome.isSelected())
            this.cost.setText((income*100.0)/price+"");
        else
            this.price.setText(price+"");

    }
    @FXML private void incomeChange(){
        double cost = Double.parseDouble(this.cost.getText());
        double price = Double.parseDouble(this.price.getText());
        double income = Double.parseDouble(this.income.getText());
        if(rPrice.isSelected()){
            this.cost.setText((income*100.0)/price+"");
        }
        else if(rCost.isSelected())
            this.price.setText((income/cost)*100.0+"");
        else
            this.income.setText(income+"");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        group = new ToggleGroup();
        rCost.setToggleGroup(group);
        rCost.setSelected(true);
        rPrice.setToggleGroup(group);
        rIncome.setToggleGroup(group);
        cost.setText("0");
        price.setText("0");
        income.setText("0");
    }
}
