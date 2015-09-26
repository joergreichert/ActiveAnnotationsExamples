package de.abg.jreichert.activeanno.classloader.example.bundle2

import de.abg.jreichert.activeanno.classloader.example.bundle1.Interface1
import de.abg.jreichert.activeanno.classloader.ClassloaderAware

class Class2 {
   
   @ClassloaderAware(expectedInterface=Interface1)
   private val String classToLoad = "de.abg.jreichert.activeanno.classloader.example.bundle3.Class3"
   
   new() {
      try {
         val impl = Class.forName(classToLoad).newInstance as Interface1
         impl.transform("class2Value")
      } catch(ClassNotFoundException cnfe) {
         cnfe.printStackTrace
      }
   }
}