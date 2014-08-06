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
package org.neuro4j.studio.properties.sources;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor.PropertyValueWrapper;
import org.eclipse.emf.edit.ui.provider.PropertySource;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.neuro4j.workflow.enums.StartNodeTypes;
import org.neuro4j.studio.core.Neuro4jPackage;
import org.neuro4j.studio.core.diagram.edit.parts.LogicNodeEditPart;
import org.neuro4j.studio.core.diagram.edit.parts.StartNodeEditPart;
import org.neuro4j.studio.core.diagram.part.Neuro4jDiagramEditorUtil;
import org.neuro4j.studio.core.impl.StartNodeImpl;
import org.neuro4j.studio.properties.descriptor.StartTypesPropertyDescriptor;

public class StartNodePropertySource extends PropertySource {

    AdapterFactory af;

    public StartNodePropertySource(Object object,
            IItemPropertySource itemPropertySource, AdapterFactory af) {
        super(object, itemPropertySource);
        this.af = af;
    }

    @Override
    protected IPropertyDescriptor createPropertyDescriptor(
            IItemPropertyDescriptor itemPropertyDescriptor) {

        Neuro4jPackage pkg = Neuro4jPackage.eINSTANCE;
        Object feature = itemPropertyDescriptor.getFeature(object);

        if (pkg.getStartNode_Type().equals(feature)) {
            IPropertyDescriptor desc = new StartTypesPropertyDescriptor(
                    "type", "Type", new String[] { });
            return desc;

        }
        return super.createPropertyDescriptor(itemPropertyDescriptor);

    }

    @Override
    public IPropertyDescriptor[] getPropertyDescriptors() {
        return super.getPropertyDescriptors();
    }

    @Override
    public void setPropertyValue(Object propertyId, Object value) {
    	 StartNodeImpl startNode = (StartNodeImpl) getEditableValue();
        if ("name".equals(propertyId)) {
           

            if (!startNode.isValidName((String) value, startNode)) {
                return;
            }
        } else if ("type".equals(propertyId)) {
            Integer index = (Integer) value;
            StartNodeTypes[] names = StartNodeTypes.values();
            value = names[index].name();
            
            updateIcon(startNode, StartNodeTypes.valueOf(value.toString().toUpperCase()));
            
        }

        itemPropertySource.getPropertyDescriptor(object, propertyId)
                .setPropertyValue(object, value);
    }

    @Override
    public Object getPropertyValue(Object propertyId) {

        if ("type".equals(propertyId)) {
            PropertyValueWrapper value = (PropertyValueWrapper) itemPropertySource
                    .getPropertyDescriptor(object, propertyId)
                    .getPropertyValue(object);
            StartNodeTypes op = null;
            if (value != null) {
                op = StartNodeTypes.getByName((String) value
                        .getEditableValue(this));
            }
            if (op == null) {
                op = StartNodeTypes.PUBLIC;
            }
            return new PropertyValueWrapper(af, object, op.value, null);
        }
        return super.getPropertyValue(propertyId);
    }
    
    
    private void updateIcon(EObject element, StartNodeTypes type)
    {

        IEditorPart editorPart = PlatformUI.getWorkbench()
                .getActiveWorkbenchWindow().getActivePage().getActiveEditor();
        if (editorPart instanceof IDiagramWorkbenchPart) {

            DiagramEditPart diagramPart = ((IDiagramWorkbenchPart) editorPart).getDiagramEditPart();
            List<EditPart> editPartCollector = new ArrayList<EditPart>();

            Neuro4jDiagramEditorUtil.findElementsInDiagramByID(diagramPart, element, editPartCollector);

            if (editPartCollector.size() > 0)
            {
                StartNodeEditPart editpart = (StartNodeEditPart) editPartCollector.get(0);

                editpart.setImage(type);

            }

        }
    }

    public Object getEditableValue() {
        return itemPropertySource.getEditableValue(object);
    }

}