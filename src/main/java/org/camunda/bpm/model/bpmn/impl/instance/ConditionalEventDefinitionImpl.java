/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.camunda.bpm.model.bpmn.impl.instance;

import org.camunda.bpm.model.bpmn.instance.Condition;
import org.camunda.bpm.model.bpmn.instance.ConditionalEventDefinition;
import org.camunda.bpm.model.bpmn.instance.EventDefinition;
import org.camunda.bpm.model.xml.ModelBuilder;
import org.camunda.bpm.model.xml.impl.instance.ModelTypeInstanceContext;
import org.camunda.bpm.model.xml.type.ModelElementTypeBuilder;
import org.camunda.bpm.model.xml.type.child.ChildElement;
import org.camunda.bpm.model.xml.type.child.SequenceBuilder;

import static org.camunda.bpm.model.bpmn.impl.BpmnModelConstants.BPMN20_NS;
import static org.camunda.bpm.model.bpmn.impl.BpmnModelConstants.BPMN_ELEMENT_CONDITIONAL_EVENT_DEFINITION;
import static org.camunda.bpm.model.xml.type.ModelElementTypeBuilder.ModelTypeInstanceProvider;

/**
 * The BPMN conditionalEventDefinition element
 *
 * @author Sebastian Menski
 */
public class ConditionalEventDefinitionImpl extends EventDefinitionImpl implements ConditionalEventDefinition {

  protected static ChildElement<Condition> conditionChild;

  public static void registerType(ModelBuilder modelBuilder) {
    ModelElementTypeBuilder typeBuilder = modelBuilder.defineType(ConditionalEventDefinition.class, BPMN_ELEMENT_CONDITIONAL_EVENT_DEFINITION)
      .namespaceUri(BPMN20_NS)
      .extendsType(EventDefinition.class)
      .instanceProvider(new ModelTypeInstanceProvider<ConditionalEventDefinition>() {
        public ConditionalEventDefinition newInstance(ModelTypeInstanceContext instanceContext) {
          return new ConditionalEventDefinitionImpl(instanceContext);
        }
      });

    SequenceBuilder sequenceBuilder = typeBuilder.sequence();

    conditionChild = sequenceBuilder.element(Condition.class)
      .required()
      .build();

    typeBuilder.build();
  }

  public ConditionalEventDefinitionImpl(ModelTypeInstanceContext context) {
    super(context);
  }

  public Condition getCondition() {
    return conditionChild.getChild(this);
  }

  public void setCondition(Condition condition) {
    conditionChild.setChild(this, condition);
  }
}
