/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package legaltime.view;

import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JTable;
import javax.swing.JViewport;

/**
 *
 * @author bmartin
 */
public class SwingUtils {

    /**
     * Scrolls table to selected row
     * @param table
     * @param rowIndex
     * @param vColIndex
     * Reference: http://www.exampledepot.com/egs/javax.swing.table/Vis.html
     */

    public static void scrollTableToVisible(JTable table, int rowIndex, int vColIndex) {
        if (!(table.getParent() instanceof JViewport)) {
            return;
        }
        JViewport viewport = (JViewport)table.getParent();

        // This rectangle is relative to the table where the
        // northwest corner of cell (0,0) is always (0,0).
        Rectangle rect = table.getCellRect(rowIndex, vColIndex, true);

        // The location of the viewport relative to the table
        Point pt = viewport.getViewPosition();

        // Translate the cell location so that it is relative
        // to the view, assuming the northwest corner of the
        // view is (0,0)
        rect.setLocation(rect.x-pt.x, rect.y-pt.y);

        // Scroll the area into view
        viewport.scrollRectToVisible(rect);
    }

}
