/*
 * piezoelectric_valve_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:00 by COMSOL 6.3.0.290. */
public class piezoelectric_valve_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\MEMS_Module\\Piezoelectric_Devices");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.param().set("t0", "0.05[mm]");
    model.param().descr("t0", "\u538b\u7535\u5c42\u539a\u5ea6");
    model.param().set("ID", "7[mm]");
    model.param().descr("ID", "\u5706\u76d8\u5f0f\u6267\u884c\u5668\u5185\u5f84");
    model.param().set("OD", "50[mm]");
    model.param().descr("OD", "\u5706\u76d8\u5f0f\u6267\u884c\u5668\u5916\u5f84");
    model.param().set("n", "12");
    model.param().descr("n", "\u6267\u884c\u5668\u4e2d\u7684\u5c42\u6570");
    model.param().set("ts", "0.2[mm]");
    model.param().descr("ts", "\u5bc6\u5c01\u4ef6\u539a\u5ea6");
    model.param().set("w0", "0.5[mm]");
    model.param().descr("w0", "\u901a\u5b54\u5c3a\u5bf8");
    model.param().set("w1", "ID/2");
    model.param().descr("w1", "\u5939\u4f4f\u533a\u57df\u5c3a\u5bf8");
    model.param().set("w2", "ID/4");
    model.param().descr("w2", "\u603b\u5939\u4f4f\u5c3a\u5bf8");
    model.param().set("h0", "5*t0*n");
    model.param().descr("h0", "\u5e95\u5ea7\u539a\u5ea6");
    model.param().set("deltaz", "16[um]");
    model.param().descr("deltaz", "0[V] \u65f6\u7684\u63a5\u89e6\u504f\u79fb\u91cf");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"(OD-ID)/2", "t0"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"ID/2", "ts"});
    model.component("comp1").geom("geom1").feature("r1").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("r1").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "0.5*w0", 0);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "3*w0", 1);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new String[]{"1", "n"});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new String[]{"0", "t0"});
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("r1");
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"3*w0", "ts"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"ID/2+0.5*w0", "0"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("fil1").selection("point").set("r2", 1, 2);
    model.component("comp1").geom("geom1").feature("fil1").set("radius", "ts/3");
    model.component("comp1").geom("geom1").run("fil1");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"2*w0", "2*w0+h0"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"ID/2-0.5*w0", "0"});
    model.component("comp1").geom("geom1").feature("r3").setIndex("pos", "-2*w0-deltaz-h0", 1);
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("cha1", "Chamfer");
    model.component("comp1").geom("geom1").feature("cha1").selection("point").set("r3", 4);
    model.component("comp1").geom("geom1").feature("cha1").set("dist", "1.8*w0");
    model.component("comp1").geom("geom1").run("cha1");
    model.component("comp1").geom("geom1").create("fil2", "Fillet");
    model.component("comp1").geom("geom1").feature("fil2").selection("point").set("cha1", 3, 5);
    model.component("comp1").geom("geom1").feature("fil2").set("radius", "0.1*w0");
    model.component("comp1").geom("geom1").run("fil2");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "ID/2+1.2*w0", 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "ID/2+1.6*w0", 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 1, 1);
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input").set("fil2", "pol1");
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("mir1").set("pos", new String[]{"ID/2+2*w0", "0"});
    model.component("comp1").geom("geom1").run("mir1");
    model.component("comp1").geom("geom1").create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("r4").set("size", new String[]{"ID/2+1.5*w0", "1"});
    model.component("comp1").geom("geom1").feature("r4").setIndex("size", "h0", 1);
    model.component("comp1").geom("geom1").feature("r4").set("pos", new String[]{"0", "-2*w0-deltaz-h0"});
    model.component("comp1").geom("geom1").run("r4");
    model.component("comp1").geom("geom1").create("r5", "Rectangle");
    model.component("comp1").geom("geom1").feature("r5").set("size", new String[]{"OD/2+w1-ID/2-2.5*w0", "1"});
    model.component("comp1").geom("geom1").feature("r5").setIndex("size", "h0", 1);
    model.component("comp1").geom("geom1").feature("r5").set("pos", new String[]{"ID/2+2.5*w0", "0"});
    model.component("comp1").geom("geom1").feature("r5").setIndex("pos", "-2*w0-deltaz-h0", 1);
    model.component("comp1").geom("geom1").run("r5");
    model.component("comp1").geom("geom1").create("r6", "Rectangle");
    model.component("comp1").geom("geom1").feature("r6").set("size", new String[]{"w2+w1", "h0+4*w0+n*t0+deltaz"});
    model.component("comp1").geom("geom1").feature("r6").set("pos", new String[]{"OD/2-w2", "-2*w0-deltaz-h0"});
    model.component("comp1").geom("geom1").run("r6");

    model.title(null);

    model.description("");

    model.label("piezoelectric_valve_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
