ActiveAnnotationsExamples
=========================

Some examples to demonstrate the power of Xtend's active annotations

See also [here](http://joergreichert.github.io/ActiveAnnotationsExamples) for a more general description.

## Maven
Download and extract latest [Maven](https://maven.apache.org/download.cgi).

## Setting up Eclipse
Download and start the [Eclipse Installer](https://wiki.eclipse.org/Eclipse_Installer), switch to advanced mode via symbol in right upper corner, choose Eclipse for DSL Developers, Java8, Mars on the first wizard page and then drag and drop this [link](https://raw.githubusercontent.com/joergreichert/ActiveAnnotationsExamples/master/_common/de.abg.jreichert.activeanno.parent/oomph.setup) to the GitHub folder on the second wizard page, let master branch selected. On the nex wizard page state your local Maven install directory you used before and adapt the installation directory of the Eclipse instance to install, the localation of the local Git repository and other settings. The rest should happen automatically so you end up with a fully initialized and compiling workspace.

## Existing Projects

Some projects are already explained in the [general description](http://joergreichert.github.io/ActiveAnnotationsExamples). See the tests in each project for example usage.

### BeanFX

Derive the boiler plate code required for each [JavaFX](docs.oracle.com/javafx/) bean property, see [example](https://github.com/joergreichert/ActiveAnnotationsExamples/blob/master/beanfx/de.abg.jreichert.activeanno.beanfx.tests/src/de/abg/jreichert/activeanno/beanfx/FXBeanTest.xtend).

### JPA

Derive properly annotated getter/setters and a class containing the property names to be used to build type safe JPA queries, see [example](https://github.com/joergreichert/ActiveAnnotationsExamples/blob/master/jpa/de.abg.jreichert.activeanno.jpa.tests/src/de/abg/jreichert/activeanno/jpa/LocationTest.xtend) and a more detailed description, [here](http://joergreichert.github.io/ActiveAnnotationsExamples/#criteria). See also Salvatore's [take](https://github.com/plugback/jpa) on this.

### NLS

Using message bundles from Java via an type safe interface that you now never forget to pass in required parameters for placeholders and messages resp. never rename a message key without notice in Java code. See also a more [detailed description](http://joergreichert.github.io/ActiveAnnotationsExamples/#bundles). See also Stefan's [take](https://github.com/oehme/xtend-contrib#messages) on this.

### Stopwatch

An example for weaving in a cross cutting concern in your code: for annotated methods their execution time at runtime should be measured and logged, see [example](https://github.com/joergreichert/ActiveAnnotationsExamples/blob/master/stopwatch/de.abg.jreichert.activeanno.stopwatch.tests/src/de/abg/jreichert/activeanno/stopwatch/LogExecutionTimeTest.xtend). Note, this is just poor man's profiling and should be done in a more proper way by using a dedicated micro benchmarking framework like [Google's Caliper](https://github.com/google/caliper).

###  Testsuites

[JUnit test suites](https://github.com/junit-team/junit/wiki/Aggregating-tests-in-suites) are used to aggregate test classes to be executed at once. Unfortunately, if you add a new test class in your package, the test suite class won't be updated automatically. With this active annotation this is done at package level. You can even define a ostix pattern to include only those classes whose name is ending with a certain string. See also Stefan's [take](https://github.com/oehme/xtend-junit#autosuite) on this.

### Classloader

Mostly, like in OSGi context, bundles should be loosely coupled, so a bundle should discover possible interaction partners at runtime but should not fail resp. take another router when the other bundle hasn't been provided. So e.g. with `Class.forName("mypackage.MyClassName")` you can try to load a class and only if its available it will be instantiated and assigned to variable whose type is an interface that is well known to your own bundle, but the class identified by the class name should be a runtime dependency of your bundle. Although you can optional bundle dependencies and package import you never get a check, if the class, still exists. 

With this active annotation you get a compile time existence check as the annotation implementation just performs a type look up considering your classpath and will produce an error marker, when a class for that name cannot be found.

See [example class](https://github.com/joergreichert/ActiveAnnotationsExamples/blob/master/classloader/de.abg.jreichert.activeanno.classloader.example.bundle2/src/de/abg/jreichert/activeanno/classloader/example/bundle2/Class2.xtend) in bundle2 that uses interface from bundle1 and loads class from bundle3, where bundle3 is just an optional dependency (as alternative you can also use import package, but that would require some extra configuration effort in Maven, so that Tycho would handle this package import as well).

## Continuous Integration

[![Build Status](https://travis-ci.org/joergreichert/ActiveAnnotationsExamples.svg?branch=master)](https://travis-ci.org/jrlover/ActiveAnnotationsExamples)
