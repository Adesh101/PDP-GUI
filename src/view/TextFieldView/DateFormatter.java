package view.TextFieldView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFormattedTextField.AbstractFormatter;

/**
 * A public class to format date.
 */
public class DateFormatter extends AbstractFormatter {
  private String datePattern = "yyyy-MM-dd";
  private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

  @Override
  public Object stringToValue(String text) throws ParseException {
    return dateFormatter.parseObject(text);
  }

  @Override
  public String valueToString(Object value) throws ParseException {
    if (value != null) {
      Calendar cal = (Calendar) value;
      return dateFormatter.format(cal.getTime());
    }

    return "";
  }
}
