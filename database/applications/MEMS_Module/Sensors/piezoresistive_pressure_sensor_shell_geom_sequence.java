/*
 * piezoresistive_pressure_sensor_shell_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:04 by COMSOL 6.3.0.290. */
public class piezoresistive_pressure_sensor_shell_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\MEMS_Module\\Sensors");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1").set("size", 1200);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1").set("pos", new int[]{0, 478});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1").setIndex("layer", 100, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1").set("layerright", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1").set("layertop", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("sq1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("sq2", "Square");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq2").set("size", 22.6);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq2").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("sq2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("size", new double[]{52.5, 10});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("rot", 45);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").setIndex("layer", 6.25, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("layerright", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("r2", "r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("size", new double[]{62.5, 20});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("rot", -45);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").setIndex("layer", 11.25, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3").set("size", new int[]{210, 140});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3").set("pos", new int[]{0, -15});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4").set("size", new int[]{40, 50});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4").set("pos", new int[]{-105, -35});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r4");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r5", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r5").set("size", new int[]{90, 40});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r5").set("pos", new int[]{-105, 15});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r5");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r6", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r6").set("size", new int[]{90, 40});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r6").set("pos", new int[]{-105, -85});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r6");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r7", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r7").set("size", new int[]{40, 30});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r7").set("pos", new int[]{-55, -45});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r7");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").selection("input")
         .set("r4", "r5", "r6", "r7");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("cmf1", "CompositeFaces");
    model.component("comp1").geom("geom1").feature("cmf1").selection("input")
         .set("fin", 19, 20, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 38);
    model.component("comp1").geom("geom1").run("cmf1");
    model.component("comp1").geom("geom1").create("cmf2", "CompositeFaces");
    model.component("comp1").geom("geom1").feature("cmf2").selection("input")
         .set("cmf1", 11, 14, 15, 16, 17, 18, 25, 27, 32, 33, 34, 35);
    model.component("comp1").geom("geom1").run("cmf2");
    model.component("comp1").geom("geom1").create("ige1", "IgnoreEdges");
    model.component("comp1").geom("geom1").feature("ige1").selection("input").set("cmf2", 24, 94);
    model.component("comp1").geom("geom1").feature("ige1").set("ignorevtx", false);
    model.component("comp1").geom("geom1").run("ige1");
    model.component("comp1").geom("geom1").create("cmf3", "CompositeFaces");
    model.component("comp1").geom("geom1").feature("cmf3").selection("input")
         .set("ige1", 7, 12, 15, 17, 21, 24, 25, 26);
    model.component("comp1").geom("geom1").run("cmf3");
    model.component("comp1").geom("geom1").create("ige2", "IgnoreEdges");
    model.component("comp1").geom("geom1").feature("ige2").selection("input")
         .set("cmf3", 4, 6, 8, 12, 84, 87, 88, 89);
    model.component("comp1").geom("geom1").run("ige2");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u538b\u654f\u7535\u963b\u5668");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("ige2", 9);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("\u8fde\u63a5");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel2").selection("selection")
         .set("ige2", 3, 5, 6, 8, 13, 14, 15, 18);
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").label("\u9694\u819c");
    model.component("comp1").geom("geom1").feature("boxsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel1").set("xmin", -501);
    model.component("comp1").geom("geom1").feature("boxsel1").set("xmax", 501);
    model.component("comp1").geom("geom1").feature("boxsel1").set("ymin", -30);
    model.component("comp1").geom("geom1").feature("boxsel1").set("ymax", 1000);
    model.component("comp1").geom("geom1").feature("boxsel1").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel1");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").label("\u6a21\u578b\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("unisel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"sel2", "boxsel1"});
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("unisel2", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel2").label("\u7535\u6d41");
    model.component("comp1").geom("geom1").feature("unisel2").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("unisel2").set("input", new String[]{"sel1", "sel2"});
    model.component("comp1").geom("geom1").run("unisel2");
    model.component("comp1").geom("geom1").create("adjsel1", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel1").label("\u56fa\u5b9a\u8fb9");
    model.component("comp1").geom("geom1").feature("adjsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("adjsel1").set("input", new String[]{"boxsel1"});
    model.component("comp1").geom("geom1").feature("adjsel1").set("outputdim", 1);
    model.component("comp1").geom("geom1").run("adjsel1");
    model.component("comp1").geom("geom1").create("difsel1", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel1").label("\u56fa\u5b9a\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("difsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("difsel1").set("add", new String[]{"unisel1"});
    model.component("comp1").geom("geom1").feature("difsel1").set("subtract", new String[]{"boxsel1"});

    model.title(null);

    model.description("");

    model.label("piezoresistive_pressure_sensor_shell_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
