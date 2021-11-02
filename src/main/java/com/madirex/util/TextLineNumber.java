package com.madirex.util;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.beans.*;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.text.*;

public class TextLineNumber extends JPanel
        implements CaretListener, DocumentListener, PropertyChangeListener
{

    public final static float RIGHT = 1.0f;
    private final static Border OUTER = new MatteBorder(0, 0, 0, 1, Color.GRAY);
    private final static int HEIGHT = Integer.MAX_VALUE - 1000000;

    private final JTextComponent COMPONENT;
    private Color currentLineForeground;
    private float digitAlignment;
    private int minimumDisplayDigits;
    private int lastDigits;
    private double lastHeight;
    private int lastLine;
    private HashMap<String, FontMetrics> fonts;
    public TextLineNumber(JTextComponent component)
    {
        this(component, 3);
    }
    public TextLineNumber(JTextComponent component, int minimumDisplayDigits)
    {
        this.COMPONENT = component;

        setFont( component.getFont() );

        setBorderGap( 5 );
        setCurrentLineForeground(Color.GRAY);
        setDigitAlignment( RIGHT );
        setMinimumDisplayDigits( minimumDisplayDigits );

        component.getDocument().addDocumentListener(this);
        component.addCaretListener( this );
        component.addPropertyChangeListener("font", this);
    }


    public void setBorderGap(int borderGap)
    {
        Border inner = new EmptyBorder(0, borderGap, 0, borderGap);
        setBorder( new CompoundBorder(OUTER, inner) );
        lastDigits = 0;
        setPreferredWidth();
    }

    public Color getCurrentLineForeground()
    {
        return currentLineForeground == null ? getForeground() : currentLineForeground;
    }

    public void setCurrentLineForeground(Color currentLineForeground)
    {
        this.currentLineForeground = currentLineForeground;
    }

    public void setDigitAlignment(float digitAlignment)
    {
        this.digitAlignment =
                digitAlignment > 1.0f ? 1.0f : digitAlignment < 0.0f ? -1.0f : digitAlignment;
    }

    public void setMinimumDisplayDigits(int minimumDisplayDigits)
    {
        this.minimumDisplayDigits = minimumDisplayDigits;
        setPreferredWidth();
    }

    private void setPreferredWidth()
    {
        Element root = COMPONENT.getDocument().getDefaultRootElement();
        int lines = root.getElementCount();
        int digits = Math.max(String.valueOf(lines).length(), minimumDisplayDigits);

        if (lastDigits != digits)
        {
            lastDigits = digits;
            FontMetrics fontMetrics = getFontMetrics( getFont() );
            int width = fontMetrics.charWidth( '0' ) * digits;
            Insets insets = getInsets();
            int preferredWidth = insets.left + insets.right + width;

            Dimension d = getPreferredSize();
            d.setSize(preferredWidth, HEIGHT);
            setPreferredSize( d );
            setSize( d );
        }
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        FontMetrics fontMetrics = COMPONENT.getFontMetrics( COMPONENT.getFont() );
        Insets insets = getInsets();
        int availableWidth = getSize().width - insets.left - insets.right;

        Rectangle clip = g.getClipBounds();
        int rowStartOffset = COMPONENT.viewToModel2D(new Point(0, clip.y));
        int endOffset = COMPONENT.viewToModel2D(new Point(0, clip.y + clip.height));

        while (rowStartOffset <= endOffset)
        {
            try
            {
                if (isCurrentLine(rowStartOffset))
                    g.setColor( getCurrentLineForeground() );
                else
                    g.setColor( getForeground() );

                String lineNumber = getTextLineNumber(rowStartOffset);
                int stringWidth = fontMetrics.stringWidth( lineNumber );
                int x = getOffsetX(availableWidth, stringWidth) + insets.left;
                int y = getOffsetY(rowStartOffset, fontMetrics);
                g.drawString(lineNumber, x, y);

                rowStartOffset = Utilities.getRowEnd(COMPONENT, rowStartOffset) + 1;
            }
            catch(Exception e) {break;}
        }
    }

    private boolean isCurrentLine(int rowStartOffset)
    {
        int caretPosition = COMPONENT.getCaretPosition();
        Element root = COMPONENT.getDocument().getDefaultRootElement();

        return root.getElementIndex(rowStartOffset) == root.getElementIndex(caretPosition);
    }

    protected String getTextLineNumber(int rowStartOffset)
    {
        Element root = COMPONENT.getDocument().getDefaultRootElement();
        int index = root.getElementIndex( rowStartOffset );
        Element line = root.getElement( index );

        if (line.getStartOffset() == rowStartOffset)
            return String.valueOf(index + 1);
        else
            return "";
    }

    private int getOffsetX(int availableWidth, int stringWidth)
    {
        return (int)((availableWidth - stringWidth) * digitAlignment);
    }

    private int getOffsetY(int rowStartOffset, FontMetrics fontMetrics)
            throws BadLocationException
    {
        Rectangle2D r = COMPONENT.modelToView2D( rowStartOffset );
        int lineHeight = fontMetrics.getHeight();
        int y = (int) (r.getY() + r.getHeight());
        int descent = 0;

        if (r.getHeight() == lineHeight)
        {
            descent = fontMetrics.getDescent();
        }
        else
        {
            if (fonts == null)
                fonts = new HashMap<>();

            Element root = COMPONENT.getDocument().getDefaultRootElement();
            int index = root.getElementIndex( rowStartOffset );
            Element line = root.getElement( index );

            for (int i = 0; i < line.getElementCount(); i++)
            {
                Element child = line.getElement(i);
                AttributeSet as = child.getAttributes();
                String fontFamily = (String)as.getAttribute(StyleConstants.FontFamily);
                Integer fontSize = (Integer)as.getAttribute(StyleConstants.FontSize);
                String key = fontFamily + fontSize;

                FontMetrics fm = fonts.get( key );

                if (fm == null)
                {
                    Font font = new Font(fontFamily, Font.PLAIN, fontSize);
                    fm = COMPONENT.getFontMetrics( font );
                    fonts.put(key, fm);
                }

                descent = Math.max(descent, fm.getDescent());
            }
        }

        return y - descent;
    }

    @Override
    public void caretUpdate(CaretEvent e)
    {

        int caretPosition = COMPONENT.getCaretPosition();
        Element root = COMPONENT.getDocument().getDefaultRootElement();
        int currentLine = root.getElementIndex( caretPosition );

        if (lastLine != currentLine)
        {
            getParent().repaint();
            lastLine = currentLine;
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e)
    {
        documentChanged();
    }

    @Override
    public void insertUpdate(DocumentEvent e)
    {
        documentChanged();
    }

    @Override
    public void removeUpdate(DocumentEvent e)
    {
        documentChanged();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {documentChanged();}

    private void documentChanged()
    {
        SwingUtilities.invokeLater(() -> {
                int endPos = COMPONENT.getDocument().getLength();
            Rectangle2D recta = null;
            try {
                recta = COMPONENT.modelToView2D(endPos);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }

            if (recta != null && recta.getY() != lastHeight)
                {
                    setPreferredWidth();
                    getParent().repaint();
                    lastHeight = recta.getY();
                }

        });
    }


}