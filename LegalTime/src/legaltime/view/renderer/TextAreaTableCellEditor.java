/*
 *
 * http://www.roseindia.net/javatutorials/JTable_in_JDK.shtml
 * Dr. Heinz M. Kabutz
 */

package legaltime.view.renderer;

import javax.swing.*;

public class TextAreaTableCellEditor extends DefaultCellEditor {
  public TextAreaTableCellEditor() {
    super(new JTextField());
    final JTextArea textArea = new JTextArea();
    textArea.setWrapStyleWord(true);
    textArea.setLineWrap(true);
    JScrollPane scrollPane = new JScrollPane(textArea);
    scrollPane.setBorder(null);
    editorComponent = scrollPane;

    delegate = new DefaultCellEditor.EditorDelegate() {
      @Override
      public void setValue(Object value) {
        textArea.setText((value != null) ? value.toString() : "");
      }
      @Override
      public Object getCellEditorValue() {
        return textArea.getText();
      }
    };
  }
}