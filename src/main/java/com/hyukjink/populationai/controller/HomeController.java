package com.hyukjink.populationai.controller;

import com.hyukjink.populationai.model.DoubleTextField;
import com.hyukjink.populationai.model.IntTextField;
import com.hyukjink.populationai.model.Species;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HomeController implements Initializable 
{
    
    @FXML
    private Label label;
    
    @FXML
    private TextField preyName;
    @FXML
    private DoubleTextField preyPop;
    @FXML
    private DoubleTextField preyGrowth;
    @FXML
    private DoubleTextField preyDeath;

    @FXML
    private TextField predName;
    @FXML
    private DoubleTextField predPop;
    @FXML
    private DoubleTextField predGrowth;
    @FXML
    private DoubleTextField predDeath;
   
    @FXML
    private IntTextField years;
    
    @FXML
    private void handleButtonAction(ActionEvent event) 
    {
        Species prey = new Species(preyName.getText(), Double.parseDouble(preyPop.getText()), 
                Double.parseDouble(preyGrowth.getText()), Double.parseDouble(preyDeath.getText()));
        Species pred = new Species(predName.getText(), Double.parseDouble(predPop.getText()), 
                Double.parseDouble(predGrowth.getText()), Double.parseDouble(predDeath.getText()));
        
        int yearsRun = years.getInt();
        
        solveDiffEquations(prey, pred, yearsRun);
    }
    
  

    private void solveDiffEquations (Species prey, Species pred, int years)
    {
        int N = years;
        double[] preyData = new double[N+1];
        preyData[0] = prey.getPopulation();
        double[] predData = new double[N+1];
        predData[0] = pred.getPopulation();
        
        //double dt = total_time / N;
        for (int j=0; j<N; j++)
        {
            double growth;
            growth = pred.getGrowthRate() *
                    predData[j] * preyData[j];
            growth -= pred.getDeathRate()*predData[j];
            predData[j+1] = growth + predData[j];

            growth = prey.getGrowthRate() * preyData[j];
            growth -= prey.getDeathRate()* preyData[j]* predData[j];
            preyData[j+1] = growth + preyData[j];
        }
        
        // Print to output here 
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        preyName.setPromptText("Ex. Rabbit");
        preyPop.setPromptText("100");
        preyGrowth.setPromptText("0.02");
        preyDeath.setPromptText("0.0005");
        
        predName.setPromptText("Ex. Wolf");
        predPop.setPromptText("25");
        predGrowth.setPromptText("0.0005");
        predDeath.setPromptText("0.05");
        years.setPromptText("500");

    }    
}
