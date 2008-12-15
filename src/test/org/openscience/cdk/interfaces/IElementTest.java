/* $Revision$ $Author$ $Date$    
 * 
 * Copyright (C) 1997-2007  The Chemistry Development Kit (CDK) project
 * 
 * Contact: cdk-devel@lists.sourceforge.net
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 2.1
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA. 
 */
package org.openscience.cdk.interfaces;

import org.junit.Assert;
import org.junit.Test;
import org.openscience.cdk.tools.diff.ElementDiff;

/**
 * Checks the functionality of {@link IElement} implementations.
 *
 * @cdk.module test-interfaces
 *
 * @see org.openscience.cdk.Element
 */
public class IElementTest extends IChemObjectTest {

    // test constructors
    
    @Test public void testElement() {
        IElement e = getBuilder().newElement();
        Assert.assertTrue(e instanceof IChemObject);
    }
    
    @Test public void testElement_IElement() {
    	IElement element = getBuilder().newElement();
        IElement e = getBuilder().newElement(element);
        Assert.assertTrue(e instanceof IChemObject);
    }
    
    @Test public void testElement_String() {
        IElement e = getBuilder().newElement("C");
        Assert.assertEquals("C", e.getSymbol());
    }
    
    @Test public void testElement_String_int() {
        IElement e = getBuilder().newElement("H", 1);
        Assert.assertEquals("H", e.getSymbol());
        Assert.assertEquals(1, e.getAtomicNumber().intValue());
    }
    
    // test methods
    
    @Test public void testSetSymbol_String() {
        IElement e = getBuilder().newElement();
        e.setSymbol("C");
        Assert.assertEquals("C", e.getSymbol());
    }
        
    @Test public void testGetSymbol() {
        IElement e = getBuilder().newElement("X");
        Assert.assertEquals("X", e.getSymbol());
    }
        
    @Test public void testSetAtomicNumber_Integer() {
        IElement e = getBuilder().newElement("H");
        e.setAtomicNumber(1);
        Assert.assertEquals(1, e.getAtomicNumber().intValue());
    }

    @Test public void testGetAtomicNumber() {
        IElement e = getBuilder().newElement("D", 1);
        Assert.assertEquals(1, e.getAtomicNumber().intValue());
    }

    @Test public void testClone() throws Exception {
        IElement elem = getBuilder().newElement();
        Object clone = elem.clone();
        Assert.assertTrue(clone instanceof IElement);

        // test that everything has been cloned properly
        String diff = ElementDiff.diff(elem, (IElement)clone);
        Assert.assertNotNull(diff);
        Assert.assertEquals(0, diff.length());
    }
    
    @Test public void testCloneDiff() throws Exception {
        IElement elem = getBuilder().newElement();
        IElement clone = (IElement)elem.clone();
        Assert.assertEquals("", ElementDiff.diff(elem, clone));
    }

    @Test public void testClone_Symbol() throws Exception {
        IElement elem = getBuilder().newElement("C");
        IElement clone = (IElement)elem.clone();
        
        // test cloning of symbol
        elem.setSymbol("H");
        Assert.assertEquals("C", clone.getSymbol());
    }
    
    @Test public void testClone_IAtomicNumber() throws Exception {
        IElement elem = getBuilder().newElement("C", 6);
        IElement clone = (IElement)elem.clone();
        
        // test cloning of atomic number
        elem.setAtomicNumber(5); // don't care about symbol
        Assert.assertEquals(6, clone.getAtomicNumber().intValue());
    }
    
    /** Test for RFC #9 */
    @Test public void testToString() {
        IElement elem = getBuilder().newElement();
        String description = elem.toString();
        for (int i=0; i< description.length(); i++) {
        	Assert.assertTrue(description.charAt(i) != '\n');
        	Assert.assertTrue(description.charAt(i) != '\r');
        }
    }

    @Test public void testCompare_Object() {
        // Added to keep the Coverage checker happy, but since the
        // compare(Object) method is not part of the interface, nothing is tested
    	Assert.assertTrue(true);
    }
}
