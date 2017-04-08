package com.hyukjink.populationai.controller;

import com.hyukjink.populationai.model.DoubleTextField;
import com.hyukjink.populationai.model.IntTextField;
import com.hyukjink.populationai.model.Species;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HomeController implements Initializable 
{
    
    @FXML
    private Label label;
    
    @FXML
    private LineChart<Double, Double> chart;
    private XYChart.Series series1 = new XYChart.Series();
    private XYChart.Series series2 = new XYChart.Series();    
    
    @FXML
    private NumberAxis xAxis = new NumberAxis();
    @FXML
    private NumberAxis yAxis = new NumberAxis();
    
    private ArrayList<Double> preyData = new ArrayList<Double>();
    private ArrayList<Double> predData = new ArrayList<Double>();
    
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
        // Print to output here 
                
        addChartData(prey.getName(), pred.getName(), preyData, predData);
    }
    
    private void addChartData(String preyName, String predName, 
            ArrayList<Double> prey, ArrayList<Double> pred)
    {
        series1 = new XYChart.Series();
        series2 = new XYChart.Series();
        chart.getData().clear();
        for (int i =0; i<prey.size(); i++)
        {
            series1.getData().add(new XYChart.Data(i, prey.get(i)));
        }
        for (int i=0; i<pred.size(); i++)
        {
            series2.getData().add(new XYChart.Data(i, pred.get(i)));
        }
        
        series1.setName(predName);
        series2.setName(preyName);
        chart.getData().add(series1);
        chart.getData().add(series2);
    }
    private void solveDiffEquations (Species prey, Species pred, int years)
    {
        int N = years;
        preyData.clear();
        preyData.add(prey.getPopulation());
        predData.clear();
        predData.add(pred.getPopulation());
        for (int j=0; j<N; j++)
        {
            double growth;
            growth = pred.getGrowthRate() *
                    predData.get(j) * preyData.get(j);
            growth -= pred.getDeathRate()*predData.get(j);
            double nextPop = growth + predData.get(j);
            predData.add(nextPop);

            growth = prey.getGrowthRate() * preyData.get(j);
            growth -= prey.getDeathRate()* preyData.get(j)* predData.get(j);
            nextPop = growth + preyData.get(j);
            preyData.add(nextPop);
        }
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
        
        chart.setTitle("Population Change");
        xAxis.setLabel("Years");
        yAxis.setLabel("Population");
        
    }    
}
