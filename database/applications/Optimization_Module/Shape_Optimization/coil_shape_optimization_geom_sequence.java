/*
 * coil_shape_optimization_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:42 by COMSOL 6.3.0.290. */
public class coil_shape_optimization_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Optimization_Module\\Shape_Optimization");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.param().set("R1", "45[cm]");
    model.param().descr("R1", "\u57df\u534a\u5f84");
    model.param().set("infR", "10[cm]");
    model.param().descr("infR", "\u65e0\u9650\u57df\u534a\u5f84");
    model.param().set("coilWidth", "1[cm]");
    model.param().descr("coilWidth", "\u7ebf\u5708\u5bbd\u5ea6");
    model.param().set("coilHeight", "2[cm]");
    model.param().descr("coilHeight", "\u7ebf\u5708\u9ad8\u5ea6");
    model.param().set("R2", "6[cm]");
    model.param().descr("R2", "\u7ebf\u5708\u5185\u534a\u5f84");
    model.param().set("nCoil", "3");
    model.param().descr("nCoil", "\u7ebf\u5708\u6570");
    model.param().set("coilSpace", "1.5[cm]");
    model.param().descr("coilSpace", "\u7ebf\u5708\u95f4\u8ddd");
    model.param().set("objHeight", "33[cm]");
    model.param().descr("objHeight", "\u76ee\u6807\u57df\u9ad8\u5ea6");
    model.param().set("objR", "2[cm]");
    model.param().descr("objR", "\u76ee\u6807\u57df\u534a\u5f84");
    model.param().set("objOuter", "6.5[cm]");
    model.param().descr("objOuter", "\u5916\u90e8\u76ee\u6807\u57df");
    model.param().set("objSpace", "1.5[cm]");
    model.param().descr("objSpace", "\u76ee\u6807\u57df\u95f4\u8ddd");
    model.param().set("deformR", "13[cm]");
    model.param().descr("deformR", "\u53d8\u5f62\u57df\u5bbd\u5ea6");
    model.param().set("deformHeight", "40[cm]");
    model.param().descr("deformHeight", "\u53d8\u5f62\u57df\u9ad8\u5ea6");
    model.param().set("tTube", "2[mm]");
    model.param().descr("tTube", "\u7ba1\u539a\u5ea6");
    model.param().set("objInner", "objHeight-2*objSpace-2*objOuter");
    model.param().descr("objInner", "\u5185\u90e8\u76ee\u6807\u57df");

    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "R");
    model.component("comp1").geom("geom1").feature("c1").set("angle", 180);
    model.component("comp1").geom("geom1").feature("c1").set("r", "R1");
    model.component("comp1").geom("geom1").feature("c1").set("rot", -90);
    model.component("comp1").geom("geom1").feature("c1").setIndex("layer", "infR", 0);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"coilWidth", "coilHeight"});
    model.component("comp1").geom("geom1").feature("r1")
         .set("pos", new String[]{"R2", "-coilSpace*(nCoil+0.5)-(nCoil+1)*coilHeight"});
    model.component("comp1").geom("geom1").feature("r1").set("selresult", true);
    model.component("comp1").geom("geom1").feature().duplicate("r2", "r1");
    model.component("comp1").geom("geom1").feature("r2")
         .set("size", new String[]{"coilWidth-2*tTube", "coilHeight"});
    model.component("comp1").geom("geom1").feature("r2").setIndex("size", "coilHeight-2*tTube", 1);
    model.component("comp1").geom("geom1").feature("r2").setIndex("pos", "R2+tTube", 0);
    model.component("comp1").geom("geom1").feature("r2")
         .set("pos", new String[]{"R2+tTube", "-coilSpace*(nCoil+0.5)-(nCoil+1)*coilHeight+tTube"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").named("r1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").named("r2");
    model.component("comp1").geom("geom1").feature("dif1").set("selresult", true);
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("disksel1", "DiskSelection");
    model.component("comp1").geom("geom1").feature("disksel1").set("entitydim", 0);
    model.component("comp1").geom("geom1").feature("disksel1").label("\u5de6\u4e0b\u70b9");
    model.component("comp1").geom("geom1").feature("disksel1").set("r", "coilWidth/100");
    model.component("comp1").geom("geom1").feature("disksel1").set("posx", "R2");
    model.component("comp1").geom("geom1").feature("disksel1")
         .set("posy", "-coilSpace*(nCoil+0.5)-(nCoil+1)*coilHeight");
    model.component("comp1").geom("geom1").run("disksel1");
    model.component("comp1").geom("geom1").create("copy1", "Copy");
    model.component("comp1").geom("geom1").feature("copy1").selection("input").named("dif1");
    model.component("comp1").geom("geom1").feature("copy1").set("disply", "coilSpace+coilHeight");
    model.component("comp1").geom("geom1").feature("copy1").set("selresult", true);
    model.component("comp1").geom("geom1").feature().duplicate("copy2", "copy1");
    model.component("comp1").geom("geom1").feature("copy2").label("\u5185\u7ebf\u5708");
    model.component("comp1").geom("geom1").runPre("copy2");
    model.component("comp1").geom("geom1").feature("copy2").selection("input").named("copy1");
    model.component("comp1").geom("geom1").run("copy2");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").named("copy2");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new String[]{"1", "nCoil-1"});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new String[]{"0", "coilHeight+coilSpace"});
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").label("\u955c\u50cf\u7684\u5bf9\u8c61");
    model.component("comp1").geom("geom1").feature("unisel1").set("entitydim", -1);
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"dif1", "copy1", "copy2"});
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input").named("unisel1");
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("mir1").set("axis", new int[]{0, 1});
    model.component("comp1").geom("geom1").run("mir1");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").label("\u5916\u90e8\u76ee\u6807\u57df");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"objR", "objOuter"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"0", "-objHeight/2"});
    model.component("comp1").geom("geom1").feature("r3").set("selresult", true);
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").label("\u5916\u90e8\u8f74");
    model.component("comp1").geom("geom1").feature("boxsel1").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("boxsel1").set("xmax", "objR*0.1");
    model.component("comp1").geom("geom1").feature("boxsel1").set("ymin", "-objHeight/2-objOuter*0.01");
    model.component("comp1").geom("geom1").feature("boxsel1").set("ymax", "-objHeight/2+objOuter*1.01");
    model.component("comp1").geom("geom1").feature("boxsel1").set("condition", "inside");
    model.component("comp1").geom("geom1").feature().duplicate("mir2", "mir1");
    model.component("comp1").geom("geom1").runPre("mir2");
    model.component("comp1").geom("geom1").feature("mir2").selection("input").named("r3");
    model.component("comp1").geom("geom1").create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("r4").set("size", new String[]{"objR", "objInner"});
    model.component("comp1").geom("geom1").feature("r4").set("base", "center");
    model.component("comp1").geom("geom1").feature("r4").set("pos", new String[]{"objR/2", "0"});
    model.component("comp1").geom("geom1").feature("r4").set("selresult", true);
    model.component("comp1").geom("geom1").run("r4");
    model.component("comp1").geom("geom1").create("r5", "Rectangle");
    model.component("comp1").geom("geom1").feature("r5").set("size", new String[]{"deformR", "deformHeight"});
    model.component("comp1").geom("geom1").feature("r5").set("pos", new String[]{"0", "-deformHeight/2"});
    model.component("comp1").geom("geom1").feature("r5").set("selresult", true);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("difsel1", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel1").label("\u53d8\u5f62\u57df");
    model.component("comp1").geom("geom1").feature("difsel1").set("add", new String[]{"r5"});
    model.component("comp1").geom("geom1").feature("difsel1").set("subtract", new String[]{"r1", "r3", "r4"});
    model.component("comp1").geom("geom1").run("difsel1");
    model.component("comp1").geom("geom1").create("disksel2", "DiskSelection");
    model.component("comp1").geom("geom1").feature("disksel2").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("disksel2").label("\u65e0\u9650\u57df\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("disksel2").set("r", "R1*0.99");
    model.component("comp1").geom("geom1").feature("disksel2").set("rin", "R1*0.98");
    model.component("comp1").geom("geom1").run("disksel2");
    model.component("comp1").geom("geom1").create("adjsel1", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel1").label("\u65e0\u9650\u57df");
    model.component("comp1").geom("geom1").feature("adjsel1").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("adjsel1").set("outputdim", 2);
    model.component("comp1").geom("geom1").feature("adjsel1").set("input", new String[]{"disksel2"});
    model.component("comp1").geom("geom1").run("adjsel1");
    model.component("comp1").geom("geom1").create("boxsel2", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel2").label("\u7ebf\u5708\u8154");
    model.component("comp1").geom("geom1").feature("boxsel2").set("xmin", "R2+tTube*0.5");
    model.component("comp1").geom("geom1").feature("boxsel2").set("xmax", "R2+coilWidth-0.5*tTube");
    model.component("comp1").geom("geom1").feature("boxsel2").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel2");
    model.component("comp1").geom("geom1").create("difsel2", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel2").label("\u5916\u7ebf\u5708");
    model.component("comp1").geom("geom1").feature("difsel2").set("add", new String[]{"dif1"});
    model.component("comp1").geom("geom1").feature("difsel2").set("subtract", new String[]{"copy1"});
    model.component("comp1").geom("geom1").feature().duplicate("difsel3", "difsel2");
    model.component("comp1").geom("geom1").feature("difsel3").label("\u5916\u7ebf\u5708 2");
    model.component("comp1").geom("geom1").runPre("difsel3");
    model.component("comp1").geom("geom1").feature("difsel3").set("subtract", new String[]{"copy2", "difsel2"});
    model.component("comp1").geom("geom1").create("boxsel3", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel3").label("\u5185\u8f74");
    model.component("comp1").geom("geom1").feature("boxsel3").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("boxsel3").set("xmax", "objR*0.1");
    model.component("comp1").geom("geom1").feature("boxsel3").set("ymin", "-objInner/1.99");
    model.component("comp1").geom("geom1").feature("boxsel3").set("ymax", "objInner/1.99");
    model.component("comp1").geom("geom1").feature("boxsel3").set("condition", "inside");
    model.component("comp1").geom("geom1").feature().duplicate("boxsel4", "boxsel3");
    model.component("comp1").geom("geom1").feature("boxsel4").label("\u6574\u4e2a\u8f74");
    model.component("comp1").geom("geom1").feature("boxsel4").set("ymin", "-objHeight/1.99");
    model.component("comp1").geom("geom1").feature("boxsel4").set("ymax", "objHeight/1.99");
    model.component("comp1").geom("geom1").run("boxsel4");

    model.title(null);

    model.description("");

    model.label("coil_shape_optimization_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
