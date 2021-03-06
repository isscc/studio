/*
 * Copyright (c) 2013-2014, Neuro4j.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.neuro4j.studio.core.diagram.edit.shapes;

import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.swt.graphics.Image;
import org.neuro4j.studio.core.diagram.part.Neuro4jDiagramEditorPlugin;

/**
 * @generated
 */
public class FollowByRelationNodeFigure extends BaseImageFigure {

    public static final Image DEFAULT_IMAGE = Neuro4jDiagramEditorPlugin.imageDescriptorFromPlugin(Neuro4jDiagramEditorPlugin.ID, "icons/images/SwitchNode.png").createImage();
    public static final Image FORK_IMAGE = Neuro4jDiagramEditorPlugin.imageDescriptorFromPlugin(Neuro4jDiagramEditorPlugin.ID, "icons/images/SwitchNodeFork.png").createImage();

    
    private Ellipse input, output; 
    /**
     * @generated
     */
    private WrappingLabel fFigureFollowByRelationNodeRelationNameFigure;

    /**
     * @generated
     */
    public FollowByRelationNodeFigure() {
        super(DEFAULT_IMAGE, 0);
        FlowLayout layoutThis = new FlowLayout();

        this.setLayoutManager(layoutThis);

        createContents();
    }

    /**
     * @generated
     */
    private void createContents() {

        fFigureFollowByRelationNodeRelationNameFigure = new WrappingLabel();

        fFigureFollowByRelationNodeRelationNameFigure.setText("");

        this.add(fFigureFollowByRelationNodeRelationNameFigure);
        
        input = new Ellipse();
        input.setFill(true);
        input.setBackgroundColor(ellipseBGColor);
        input.setPreferredSize(new Dimension(ELLIPSE_SIZE, ELLIPSE_SIZE));
        
        
        this.add(input);
        
        output = new Ellipse();
        output.setFill(true);
        output.setBackgroundColor(ellipseBGColor);
        output.setPreferredSize(new Dimension(ELLIPSE_SIZE, ELLIPSE_SIZE));

    }

    /**
     * @generated
     */
    public WrappingLabel getFigureFollowByRelationNodeRelationNameFigure() {
        return fFigureFollowByRelationNodeRelationNameFigure;
    }
    
    public IFigure getFigureSwitchNodeMainInput() {
        return input;
    }
    
    public IFigure getFigureSwitchNodeMainOutput() {
        return output;
    }

	public void setForkImage() {
		setImage(FORK_IMAGE);    	
		
	}
	public void setDefaultImage() {
		setImage(DEFAULT_IMAGE);    	
		
	}
	

}