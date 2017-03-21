/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hyukjink.populationai.model;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author HyukJin
 */
public class Species
{
    public Species(String name, double population, 
            double growthRate, double deathRate)
    {
        setName(name);
        this.population = population;
        this.growthRate = growthRate;
        this.deathRate = deathRate;
    }
    
    private final StringProperty name = new SimpleStringProperty();
    public StringProperty getNameProperty ()
    {
        return name;
    }
    public String getName ()
    {
        return name.get();
    }
    public void setName (String value)
    {
        this.name.set(value);
    }
    
    private double population;
    public double getPopulation()
    {
        if (population < 0)
        {
            population = 0;
        }
        return this.population;
    }
    public void setPopulation(double population)
    {
        this.population = population;
    }
        
    private double growthRate;
    public double getGrowthRate()
    {
        return growthRate;
    }
    
    public void setGrowthRate(double value)
    {
        growthRate = value;
    }

    private double deathRate;
    public double getDeathRate ()
    {
        return deathRate;
    } 
}
