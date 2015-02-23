package de.abg.jreichert.activeanno.nls

import static org.eclipse.xtend.core.compiler.batch.XtendCompilerTester.*
import org.eclipse.xtext.xbase.compiler.CompilationTestHelper
import org.junit.Test

class NLSTest {

   @Test def void testNLS() {
      val compilerTester = newXtendCompilerTester(NLS)
      val field = compilerTester.getClass.getDeclaredField("compilationTestHelper") => [accessible = true]
      val extension compilationTestHelper = (field.get(compilerTester) as CompilationTestHelper)
      copyToWorkspace("/" + CompilationTestHelper.PROJECT_NAME +
         "/src/messages.properties", '''
         AMBIGUOUS_WILDCARD_IMPORT=The type {0} is ambiguously imported from {1}.
         WRONG_FIELD_ACCESSOR=The accessor {0} is {1}.
         WRONG_STATIC_ACCESSOR=The {0} member {1} cannot be accessed in {2} context.
      ''')
      '''
         @de.abg.jreichert.activeanno.nls.NLS(propertyFileName="messages")
         class MyClass {
         }
      '''.assertCompilesTo('''
         import de.abg.jreichert.activeanno.nls.NLS;
         import java.util.MissingResourceException;
         import java.util.ResourceBundle;
         import org.eclipse.xtext.xbase.lib.Functions.Function0;
         
         @NLS(propertyFileName = "messages")
         @SuppressWarnings("all")
         public class MyClass {
           private final static String BUNDLE_NAME = MyClass.class.getPackage().getName() + ".messages";
           
           private final static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
           
           private final static String INITIALIZER = new Function0<String>() {
                 public String apply() {
                   org.eclipse.osgi.util.NLS.initializeMessages(BUNDLE_NAME, MyClass.class);
                   return "";
                 }
               }.apply();
             ;
           
           private static String getString(final String key) {
             try {
               return RESOURCE_BUNDLE.getString(key);
             } catch (MissingResourceException e) {
               return '!' + key + '!';
             }
           }
           
           public final static String AMBIGUOUS_WILDCARD_IMPORT = "AMBIGUOUS_WILDCARD_IMPORT";
           
           public static String getMessageForAMBIGUOUS_WILDCARD_IMPORT(final Object param0, final Object param1) {
             return org.eclipse.osgi.util.NLS.bind(getString(AMBIGUOUS_WILDCARD_IMPORT), new Object [] { param0, param1 });
           }
           
           public final static String WRONG_FIELD_ACCESSOR = "WRONG_FIELD_ACCESSOR";
           
           public static String getMessageForWRONG_FIELD_ACCESSOR(final Object param0, final Object param1) {
             return org.eclipse.osgi.util.NLS.bind(getString(WRONG_FIELD_ACCESSOR), new Object [] { param0, param1 });
           }
           
           public final static String WRONG_STATIC_ACCESSOR = "WRONG_STATIC_ACCESSOR";
           
           public static String getMessageForWRONG_STATIC_ACCESSOR(final Object param0, final Object param1, final Object param2) {
             return org.eclipse.osgi.util.NLS.bind(getString(WRONG_STATIC_ACCESSOR), new Object [] { param0, param1, param2 });
           }
         }
      ''')
   }
}
