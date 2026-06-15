/*
 * demultiplexer_shape_optimization_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:39 by COMSOL 6.3.0.290. */
public class demultiplexer_shape_optimization_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Optimization");

    model.param().set("L", "20[cm]");
    model.param().descr("L", "\u57df\u76f4\u5f84");
    model.param().set("L1", "0.05*L");
    model.param().descr("L1", "\u7aef\u53e3\u5bbd\u5ea6");
    model.param().set("Lperiod", "0.09*L");
    model.param().descr("Lperiod", "\u5b54\u5468\u671f");
    model.param().set("Rhole", "0.5*Lperiod");
    model.param().descr("Rhole", "\u5b54\u534a\u5f84");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "L/2");
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"3*L1", "L1"});
    model.component("comp1").geom("geom1").feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"-L/2-L1*1.45", "0"});
    model.component("comp1").geom("geom1").feature("r1").set("selresult", true);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").named("r1");
    model.component("comp1").geom("geom1").feature("rot1").set("keep", true);
    model.component("comp1").geom("geom1").feature("rot1").set("rot", "120 -120");
    model.component("comp1").geom("geom1").run("rot1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("uni1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("c1", "r1", "rot1");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("c2").set("r", "Rhole");
    model.component("comp1").geom("geom1").feature("c2").set("selresult", true);
    model.component("comp1").geom("geom1").run("c2");
    model.component("comp1").geom("geom1").create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("mov1").selection("input").named("c2");
    model.component("comp1").geom("geom1").feature("mov1").set("keep", true);
    model.component("comp1").geom("geom1").feature("mov1").set("displx", "2*Lperiod*sin(pi/3)");
    model.component("comp1").geom("geom1").feature("mov1").set("disply", "2*Lperiod*cos(pi/3)");
    model.component("comp1").geom("geom1").run("mov1");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").named("c2");
    model.component("comp1").geom("geom1").feature("arr1")
         .set("fullsize", new String[]{"round(0.5*L/Lperiod)", "1"});
    model.component("comp1").geom("geom1").feature("arr1").setIndex("fullsize", "round(0.5*L/Lperiod)", 1);
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new String[]{"4*Lperiod*sin(pi/3)", "0"});
    model.component("comp1").geom("geom1").feature("arr1").setIndex("displ", "2*Lperiod", 1);
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input").named("c2");
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").run("mir1");
    model.component("comp1").geom("geom1").create("mir2", "Mirror");
    model.component("comp1").geom("geom1").feature("mir2").selection("input").named("c2");
    model.component("comp1").geom("geom1").feature("mir2").set("keep", true);
    model.component("comp1").geom("geom1").feature("mir2").set("axis", new int[]{0, 1});
    model.component("comp1").geom("geom1").run("mir2");
    model.component("comp1").geom("geom1").create("disksel1", "DiskSelection");
    model.component("comp1").geom("geom1").feature("disksel1").set("inputent", "selections");
    model.component("comp1").geom("geom1").feature("disksel1").set("input", new String[]{"c2"});
    model.component("comp1").geom("geom1").feature("disksel1").set("r", Double.POSITIVE_INFINITY);
    model.component("comp1").geom("geom1").feature("disksel1").set("rin", "L/2-Rhole");
    model.component("comp1").geom("geom1").run("disksel1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("del1").selection("input").named("disksel1");
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").named("uni1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").named("c2");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("disksel2", "DiskSelection");
    model.component("comp1").geom("geom1").feature("disksel2").label("\u7aef\u53e3");
    model.component("comp1").geom("geom1").feature("disksel2").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("disksel2").set("r", Double.POSITIVE_INFINITY);
    model.component("comp1").geom("geom1").feature("disksel2").set("rin", "L*0.51");
    model.component("comp1").geom("geom1").feature("disksel2").set("condition", "inside");
    model.component("comp1").geom("geom1").feature().duplicate("disksel3", "disksel2");
    model.component("comp1").geom("geom1").feature("disksel3").label("\u5708");
    model.component("comp1").geom("geom1").feature("disksel3").set("rin", 0);
    model.component("comp1").geom("geom1").feature("disksel3").set("r", "L*0.49");
    model.component("comp1").geom("geom1").run("disksel3");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").label("\u7aef\u53e3 1");
    model.component("comp1").geom("geom1").feature("boxsel1").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("boxsel1").set("inputent", "selections");
    model.component("comp1").geom("geom1").feature("boxsel1").set("input", new String[]{"disksel2"});
    model.component("comp1").geom("geom1").feature("boxsel1").set("xmax", 0);
    model.component("comp1").geom("geom1").run("boxsel1");
    model.component("comp1").geom("geom1").create("boxsel2", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel2").label("\u7aef\u53e3 2");
    model.component("comp1").geom("geom1").feature("boxsel2").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("boxsel2").set("inputent", "selections");
    model.component("comp1").geom("geom1").feature("boxsel2").set("input", new String[]{"disksel2"});
    model.component("comp1").geom("geom1").feature("boxsel2").set("ymin", 0);
    model.component("comp1").geom("geom1").feature("boxsel2").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel2");
    model.component("comp1").geom("geom1").create("boxsel3", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel3").label("\u7aef\u53e3 3");
    model.component("comp1").geom("geom1").feature("boxsel3").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("boxsel3").set("inputent", "selections");
    model.component("comp1").geom("geom1").feature("boxsel3").set("input", new String[]{"disksel2"});
    model.component("comp1").geom("geom1").feature("boxsel3").set("ymax", 0);
    model.component("comp1").geom("geom1").feature("boxsel3").set("condition", "inside");
    model.component("comp1").geom("geom1").run("fin");

    model.title(null);

    model.description("");

    model.label("demultiplexer_shape_optimization_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
