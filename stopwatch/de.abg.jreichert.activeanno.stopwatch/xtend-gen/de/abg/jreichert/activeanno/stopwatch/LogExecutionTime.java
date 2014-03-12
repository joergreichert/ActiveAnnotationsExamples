package de.abg.jreichert.activeanno.stopwatch;

import de.abg.jreichert.activeanno.stopwatch.DurationProcessor;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import org.eclipse.xtend.lib.macro.Active;

@Target(ElementType.METHOD)
@Active(DurationProcessor.class)
public @interface LogExecutionTime {
}
