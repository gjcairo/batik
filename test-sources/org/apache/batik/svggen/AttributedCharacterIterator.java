/*****************************************************************************
 * Copyright (C) The Apache Software Foundation. All rights reserved.        *
 * ------------------------------------------------------------------------- *
 * This software is published under the terms of the Apache Software License *
 * version 1.1, a copy of which has been included with this distribution in  *
 * the LICENSE file.                                                         *
 *****************************************************************************/

package org.apache.batik.svggen;

import java.awt.*;
import java.awt.geom.*;
import java.awt.font.*;
import java.text.*;

/**
 * This test validates the convertion of Java 2D AffineTransform into SVG
 * Shapes.
 *
 * @author <a href="mailto:cjolif@ilog.fr">Christophe Jolif</a>
 * @author <a href="mailto:vhardy@eng.sun.com">Vincent Hardy</a>
 * @version $Id$
 */
public class AttributedCharacterIterator implements Painter {
    public void paint(Graphics2D g) {
        String fontName = "SunSansCondensed-Demi";
        int fontSize = 30;

        FontRenderContext frc = new FontRenderContext(null, true, true);

        String text = "Attributed Strings are fun !";
        AttributedString styledText = new AttributedString(text);

        //
        // Set font family for the whole string
        //
        Font font = new Font(fontName, Font.PLAIN, fontSize);
        styledText.addAttribute(TextAttribute.FAMILY, font.getFamily());
        styledText.addAttribute(TextAttribute.SIZE, new Float(font.getSize()));
        styledText.addAttribute(TextAttribute.FOREGROUND, Color.black);

        //
        // Set font style attributes for different part of the string
        //

        // "Attributed" is in Bold
        styledText.addAttribute(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD, 0, 10);

        // "String" is italic
        // styledText.addAttribute(TextAttribute.POSTURE, TextAttribute.POSTURE_OBLIQUE, 11, 18);

        // fun is Bold and underlined
        styledText.addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON, 23, 28);
        // styledText.addAttribute(TextAttribute.SWAP_COLORS, TextAttribute.SWAP_COLORS_ON);
        // styledText.addAttribute(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON, 23, 28);

        /*TextLayout aLayout = new TextLayout("A", font, frc);
          Shape aShape = aLayout.getOutline(null);

          ShapeGraphicAttribute aReplacement = new ShapeGraphicAttribute(aShape, GraphicAttribute.ROMAN_BASELINE, true);
          styledText.addAttribute(TextAttribute.CHAR_REPLACEMENT, aReplacement, 0, 1);


          // Create a BufferedImage to decorate the Shape
          {
          TextLayout aLayout = new TextLayout("A", font, frc);
          Shape aShape = aLayout.getOutline(null);
          Rectangle bounds = aShape.getBounds();

          int blurWidth = 6;
          BufferedImage image = new BufferedImage(bounds.width + blurWidth*4, bounds.height + blurWidth*4,
          BufferedImage.TYPE_INT_ARGB);
          Graphics2D g2 = image.createGraphics();
          int w = image.getWidth(), h = image.getHeight();
          g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
          g2.setPaint(Color.black);
          g2.translate(-bounds.x + (w - bounds.width)/2, -bounds.y + (h - bounds.height)/2);
          g2.fill(aShape);
          g2.setStroke(new BasicStroke(blurWidth/2));
          g2.draw(aShape);
          g2.dispose();

          float k[] = new float[blurWidth*blurWidth];
          for(int i=0; i<k.length; i++) k[i] = 1/(float)k.length;
          Kernel kernel = new Kernel(blurWidth, blurWidth, k);
          ConvolveOp blur = new ConvolveOp(kernel);
          image = blur.filter(image, null);
          g2 = image.createGraphics();
          g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
          g2.translate(-bounds.x + (w - bounds.width)/2, -bounds.y + (h - bounds.height)/2);
          g2.setComposite(AlphaComposite.Clear);
          g2.fill(aShape);

          image = image.getSubimage(blurWidth, blurWidth, image.getWidth() - 2*blurWidth, image.getHeight() - 2*blurWidth);

          ImageGraphicAttribute aImageReplacement = new ImageGraphicAttribute(image, GraphicAttribute.ROMAN_BASELINE, blurWidth,
          blurWidth + bounds.height);
          styledText.addAttribute(TextAttribute.CHAR_REPLACEMENT, aImageReplacement, 0, 1);
          }
        */

        //
        // Set text color
        //

        // "Attributed" is in dard red
        styledText.addAttribute(TextAttribute.FOREGROUND, new Color(128, 0, 0), 0, 10);

        // "String" is blue
        styledText.addAttribute(TextAttribute.FOREGROUND, new Color(70, 107, 132), 11, 18);

        // "fun" is yellow on blue background
        styledText.addAttribute(TextAttribute.FOREGROUND, new Color(236, 214, 70), 23, 28);
        styledText.addAttribute(TextAttribute.BACKGROUND, new Color(70, 107, 132), 23, 28);

        java.text.AttributedCharacterIterator iter = styledText.getIterator();
        /*TextLayout layout = new TextLayout(iter, frc);

        Rectangle bounds = layout.getBounds().getBounds();
        bounds.width += 50;
        bounds.height += 50;

        layout.draw(g, 25, layout.getAscent() + 25);*/
        g.drawString(iter, 10, 100);
    }
}
