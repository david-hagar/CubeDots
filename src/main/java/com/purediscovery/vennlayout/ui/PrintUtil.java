package com.purediscovery.vennlayout.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.print.*;

/**
 * Created with IntelliJ IDEA.
 * User: home
 * Date: 10/15/14
 * Time: 7:49 PM
 */


public class PrintUtil  implements Printable  {


    RenderPanel renderPanel;

    public PrintUtil(RenderPanel renderPanel) {
        this.renderPanel = renderPanel;
    }

    public void print() {

        try {
            PrinterJob printJob = PrinterJob.getPrinterJob();

            boolean pDialogState = printJob.printDialog();
            Book book = new Book();

            PageFormat pageFormat = printJob.pageDialog(printJob.defaultPage());

            book.append(this, pageFormat);

            printJob.setPageable(book);
            printJob.setJobName("Circles");

            if (pDialogState)
                printJob.print();
        } catch (java.security.AccessControlException ace) {
            String errmsg = "Applet access control exception; to allow "
                    + "access to printer, run policytool and set\n"
                    + "permission for \"queuePrintJob\" in " + "RuntimePermission.";
            JOptionPane.showMessageDialog(renderPanel, errmsg, "Printer Access Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            showException("Error while printing", ex);
        }
    }

    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
            throws PrinterException {
        if (pageIndex >= 1)
            return Printable.NO_SUCH_PAGE;

        Graphics2D g2d = (Graphics2D) graphics;

        double h = pageFormat.getImageableHeight();
        double w = pageFormat.getImageableWidth();
        double x = pageFormat.getImageableX();
        double y = pageFormat.getImageableY();

        if (pageFormat.getOrientation() == PageFormat.LANDSCAPE)
            System.out.println("-pageFormat.LANDSCAPE");
        if (pageFormat.getOrientation() == PageFormat.PORTRAIT)
            System.out.println("-pageFormat.PORTRAIT");

        System.out.println("pf:" + x + "," + y + "," + w + "," + h);

        renderPanel.drawAll(g2d, x, y, w, h);

        return Printable.PAGE_EXISTS;
    }



    private void showException(String errmsg, Throwable ex) {
        JOptionPane.showMessageDialog(renderPanel, errmsg + ":"
                + ex.getMessage(), "Print Error", JOptionPane.ERROR_MESSAGE);


    }
}
