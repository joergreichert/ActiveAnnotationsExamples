package annotations

import java.lang.annotation.ElementType
import java.lang.annotation.Target
import org.eclipse.xtend.lib.macro.AbstractClassProcessor
import org.eclipse.xtend.lib.macro.Active
import org.eclipse.xtend.lib.macro.TransformationContext
import org.eclipse.xtend.lib.macro.declaration.MutableClassDeclaration

@Target(ElementType.TYPE)
@Active(FXBeanProcessor)
annotation FXBean {
	
}

class FXBeanProcessor extends AbstractClassProcessor {
	
	override doTransform(MutableClassDeclaration annotatedClass, extension TransformationContext context) {
		
		for(field : annotatedClass.declaredFields) {
			annotatedClass.addMethod("set" + field.simpleName.toFirstUpper) [
				parameters += addParameter(field.simpleName.toFirstLower, string)
				body = ['''this.«field.simpleName»Property.set(«field.simpleName»);''']
			]
		}
	}
}