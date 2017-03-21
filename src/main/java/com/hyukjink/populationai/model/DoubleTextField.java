package com.hyukjink.populationai.model;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author HyukJin
 */

public class DoubleTextField extends TextField {

   public DoubleTextField() {
      super();

      addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
         @Override
         public void handle(KeyEvent event) {
            if (!isValid(getText())) {
               event.consume();
            }
         }
      });

      textProperty().addListener(new ChangeListener<String>() {
         @Override
         public void changed(ObservableValue<? extends String> observableValue,
                             String oldValue, String newValue) {
            if(!isValid(newValue)) {
               setText(oldValue);
            }
         }
      });
   }

   private boolean isValid(final String value) {
      if (value.length() == 0 || value.equals("-")) {
         return true;
      }

      try {
         Double.parseDouble(value);
         return true;
      } catch (NumberFormatException ex) {
         return false;
      }
   }

   public double getDouble() {
      try {
          return Double.parseDouble(getText());
      }
      catch (NumberFormatException e) {
         return 0;
      }
   }
}
