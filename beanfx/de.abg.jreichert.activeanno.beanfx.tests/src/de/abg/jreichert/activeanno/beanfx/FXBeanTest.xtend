package de.abg.jreichert.activeanno.beanfx

import org.eclipse.xtend.core.compiler.batch.XtendCompilerTester
import org.junit.Test

class FXBeanTest {

	extension XtendCompilerTester compilerTester = XtendCompilerTester.newXtendCompilerTester(FXBean)

	@Test def void testAnnotation() {
		'''
			@de.abg.jreichert.activeanno.beanfx.FXBean
			class MyBean {
				 String text
			}
		'''.assertCompilesTo(
			'''
				import de.abg.jreichert.activeanno.beanfx.FXBean;
				import javafx.beans.property.SimpleStringProperty;
				import javafx.beans.property.StringProperty;
				
				@FXBean
				@SuppressWarnings("all")
				public class MyBean {
				  private String text;
				  
				  private StringProperty textProperty =  new SimpleStringProperty(this,"text");;
				  
				  public String getText() {
				    return this.textProperty.get();
				  }
				  
				  public void setText(final String text2) {
				    this.textProperty.set(text2);
				  }
				  
				  public StringProperty textProperty() {
				    return this.textProperty;
				  }
				}
			''')
	}
}
