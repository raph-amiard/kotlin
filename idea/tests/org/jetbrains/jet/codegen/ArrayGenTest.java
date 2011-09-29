package org.jetbrains.jet.codegen;

import jet.arrays.JetGenericArray;
import jet.arrays.JetIntArray;
import jet.typeinfo.TypeInfo;

import java.lang.reflect.Method;

public class ArrayGenTest extends CodegenTestCase {
    public void testKt238 () throws Exception {
        blackBoxFile("regressions/kt238.jet");
    }

    public void testKt326 () throws Exception {
        blackBoxFile("regressions/kt326.jet");
        System.out.println(generateToText());
    }

    public void testCreateMultiInt () throws Exception {
        loadText("fun foo() = Array<Array<Int>> (5)");
        Method foo = generateFunction();
        Object invoke = foo.invoke(null);
        System.out.println(invoke.getClass());
        assertTrue(invoke instanceof JetGenericArray);
        assertTrue(((JetGenericArray)invoke).getTypeInfo() == TypeInfo.INT_ARRAY_TYPE_INFO);
    }

    public void testCreateMultiString () throws Exception {
        loadText("fun foo() = Array<Array<String>> (5)");
        Method foo = generateFunction();
        Object invoke = foo.invoke(null);
        System.out.println(invoke.getClass());
        assertTrue(invoke instanceof JetGenericArray);
    }

    public void testCreateMultiGenerics () throws Exception {
        loadText("class L<T>() { val a = Array<T>(5) } fun foo() = L<Int>.a");
        System.out.println(generateToText());
        Method foo = generateFunction();
        Object invoke = foo.invoke(null);
        System.out.println(invoke.getClass());
        assertTrue(invoke instanceof JetIntArray);
    }

    public void testIntGenerics () throws Exception {
        loadText("class L<T>(var a : T) {} fun foo() = L<Int>(5).a");
        System.out.println(generateToText());
        Method foo = generateFunction();
        Object invoke = foo.invoke(null);
        System.out.println(invoke.getClass());
        assertTrue(invoke instanceof Integer);
    }
}