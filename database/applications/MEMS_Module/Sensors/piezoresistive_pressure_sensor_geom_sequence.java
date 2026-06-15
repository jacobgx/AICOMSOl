/*
 * piezoresistive_pressure_sensor_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:04 by COMSOL 6.3.0.290. */
public class piezoresistive_pressure_sensor_geom_sequence {

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
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4").set("size", new int[]{40, 90});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4").set("pos", new int[]{-105, -35});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r4");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r5", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r5").set("size", new int[]{50, 40});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r5").set("pos", new int[]{-65, 15});
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
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", 20, 0);
    model.component("comp1").geom("geom1").feature("ext1").set("reverse", true);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp2").selection("face").set("ext1", 50);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("cmd1", "CompositeDomains");
    model.component("comp1").geom("geom1").feature("cmd1").selection("input")
         .set("fin", 10, 12, 14, 15, 16, 17, 18, 36, 39, 44, 45, 46, 47, 50);
    model.component("comp1").geom("geom1").run("cmd1");
    model.component("comp1").geom("geom1").create("cmd2", "CompositeDomains");
    model.component("comp1").geom("geom1").feature("cmd2").selection("input")
         .set("cmd1", 7, 12, 16, 18, 32, 36, 37, 38);
    model.component("comp1").geom("geom1").run("cmd2");
    model.component("comp1").geom("geom1").create("cmd3", "CompositeDomains");
    model.component("comp1").geom("geom1").feature("cmd3").selection("input")
         .set("cmd2", 13, 14, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 30);
    model.component("comp1").geom("geom1").run("cmd3");
    model.component("comp1").geom("geom1").create("cmd4", "CompositeDomains");
    model.component("comp1").geom("geom1").feature("cmd4").selection("input").set("cmd3", 1, 2, 3, 4, 6, 23, 24, 25);
    model.component("comp1").geom("geom1").run("cmd4");
    model.component("comp1").geom("geom1").create("parf1", "PartitionFaces");
    model.component("comp1").geom("geom1").feature("parf1").selection("face").set("cmd4", 23, 109);
    model.component("comp1").geom("geom1").feature("parf1").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").run("parf1");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u538b\u654f\u7535\u963b\u5668");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("parf1", 46);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("\u8fde\u63a5");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel2").selection("selection")
         .set("parf1", 14, 26, 39, 73, 77, 81);
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").label("\u9694\u819c\uff08\u4e0b\u8868\u9762\uff09");
    model.component("comp1").geom("geom1").feature("boxsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel1").set("xmin", -501);
    model.component("comp1").geom("geom1").feature("boxsel1").set("xmax", 501);
    model.component("comp1").geom("geom1").feature("boxsel1").set("ymin", -30);
    model.component("comp1").geom("geom1").feature("boxsel1").set("ymax", 1000);
    model.component("comp1").geom("geom1").feature("boxsel1").set("zmax", -1);
    model.component("comp1").geom("geom1").feature("boxsel1").set("condition", "inside");
    model.component("comp1").geom("geom1").feature().duplicate("boxsel2", "boxsel1");
    model.component("comp1").geom("geom1").feature("boxsel2").label("\u9694\u819c\uff08\u4e0a\u8868\u9762\uff09");
    model.component("comp1").geom("geom1").feature("boxsel2").set("zmin", -1);
    model.component("comp1").geom("geom1").feature("boxsel2").set("zmax", "inf");
    model.component("comp1").geom("geom1").run("boxsel2");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").label("\u4e0b\u8868\u9762");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel3").set("groupcontang", true);
    model.component("comp1").geom("geom1").feature("sel3").selection("selection")
         .add("parf1", 3, 8, 13, 17, 21, 25, 32, 38, 45, 51, 56, 61, 72, 76, 80, 84, 97, 103);
    model.component("comp1").geom("geom1").run("sel3");
    model.component("comp1").geom("geom1").create("sel4", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel4").label("\u4e0a\u8868\u9762");
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel4").set("groupcontang", true);
    model.component("comp1").geom("geom1").feature("sel4").selection("selection")
         .add("parf1", 4, 9, 14, 18, 22, 26, 33, 39, 46, 52, 57, 62, 73, 77, 81, 85, 98, 104);
    model.component("comp1").geom("geom1").run("sel4");
    model.component("comp1").geom("geom1").create("difsel1", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel1").label("\u56fa\u5b9a");
    model.component("comp1").geom("geom1").feature("difsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("difsel1").set("add", new String[]{"sel3"});
    model.component("comp1").geom("geom1").feature("difsel1").set("subtract", new String[]{"boxsel1"});
    model.component("comp1").geom("geom1").run("difsel1");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").label("\u7535\u6d41");
    model.component("comp1").geom("geom1").feature("unisel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"sel1", "sel2"});

    model.title(null);

    model.description("");

    model.label("piezoresistive_pressure_sensor_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
