package de.abg.jreichert.activeanno.classloader.example.bundle3;

import de.abg.jreichert.activeanno.classloader.example.bundle1.Interface1;

public class Class3 implements Interface1 {

    @Override
    public String transform(String value) {
        return value + " transformed by " + this.getClass().getCanonicalName();
    }
}
