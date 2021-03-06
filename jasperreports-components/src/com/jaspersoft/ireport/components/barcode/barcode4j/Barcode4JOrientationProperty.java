/*
 * iReport - Visual Designer for JasperReports.
 * Copyright (C) 2002 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of iReport.
 *
 * iReport is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * iReport is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with iReport. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.ireport.components.barcode.barcode4j;

import com.jaspersoft.ireport.designer.sheet.Tag;
import com.jaspersoft.ireport.designer.sheet.properties.EnumProperty;
import com.jaspersoft.ireport.locale.I18n;
import java.util.List;
import net.sf.jasperreports.components.barcode4j.BarcodeComponent;


/**
 *  Class to manage the JRDesignChart.PROPERTY_TITLE_POSITION property
 */
public final class Barcode4JOrientationProperty extends EnumProperty
{

    private final BarcodeComponent component;

    
    public Barcode4JOrientationProperty(BarcodeComponent component)
    {
        super(String.class, component);
        this.component = component;
        setName(BarcodeComponent.PROPERTY_ORIENTATION);
        setDisplayName(I18n.getString("barcode4j.property.orientation.name"));
        setShortDescription(I18n.getString("barcode4j.property.orientation.description"));
    }

    @Override
    public List getTagList()
    {
        List tags = new java.util.ArrayList();
        tags.add(new Tag(""+BarcodeComponent.ORIENTATION_UP, I18n.getString("Global.Property.None")));
        tags.add(new Tag(""+BarcodeComponent.ORIENTATION_LEFT, I18n.getString("Global.Property.Left")));
        tags.add(new Tag(""+BarcodeComponent.ORIENTATION_RIGHT, I18n.getString("Global.Property.Right")));
        tags.add(new Tag(""+BarcodeComponent.ORIENTATION_DOWN, I18n.getString("Global.Property.UpsideDown")));
        return tags;
    }

    
    @Override
    public Object getPropertyValue() {
        return ""+component.getOrientation();
    }

    @Override
    public Object getOwnPropertyValue() {
        return component.getOrientation();
    }

    @Override
    public Object getDefaultValue() {
        return ""+BarcodeComponent.ORIENTATION_UP;
    }

    @Override
    public void setPropertyValue(Object value) {
        if (value == null) component.setOrientation(0);
        else if(value instanceof Integer) component.setOrientation(((Integer) value).intValue());
        else if(value instanceof String) component.setOrientation( Integer.parseInt(""+value));
    }

}
