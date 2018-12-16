package com.aisino.cloud;

import javassist.*;

import java.io.IOException;

public class TestJavaAssist {

    public static void main(String[] args) {
        try {
            ClassPool pool = ClassPool.getDefault();
            CtClass cc = pool.get("java.lang.String");
            CtField f = new CtField(CtClass.intType, "hiddenValue", cc);
            f.setModifiers(Modifier.PUBLIC);
            cc.addField(f);
            cc.writeFile(".");
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (NotFoundException e){
            e.printStackTrace();
        }
    }

}
