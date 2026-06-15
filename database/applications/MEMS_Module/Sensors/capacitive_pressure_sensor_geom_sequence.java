/*
 * capacitive_pressure_sensor_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:03 by COMSOL 6.3.0.290. */
public class capacitive_pressure_sensor_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\MEMS_Module\\Sensors");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new double[]{1.2, 1.2, 1.51});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new double[]{0, 0, -1.1});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", 0.7, 0);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", 0.397, 1);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", 0.003, 2);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", 0.01, 3);
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new double[]{0.5, 0.5, 1.51});
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new double[]{0, 0, -1.1});
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("pard1", "PartitionDomains");
    model.component("comp1").geom("geom1").feature("pard1").selection("domain").set("blk1", 1, 2, 3, 4);
    model.component("comp1").geom("geom1").feature("pard1").set("partitionwith", "objects");
    model.component("comp1").geom("geom1").feature("pard1").selection("object").set("blk2");
    model.component("comp1").geom("geom1").run("pard1");
    model.component("comp1").geom("geom1").create("hex1", "Hexahedron");
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", 0.5, 0, 1);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", 0.5, 0, 2);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", 0, 0, 3);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", 0.78322, 0, 5);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", 0.78322, 0, 6);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", 0, 0, 7);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", 0, 1, 1);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", 0.5, 1, 2);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", 0.5, 1, 3);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", 0, 1, 5);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", 0.78322, 1, 6);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", 0.78322, 1, 7);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", 0.01, 2, 0);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", 0.01, 2, 1);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", 0.01, 2, 2);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", 0.01, 2, 3);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", 0.41, 2, 4);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", 0.41, 2, 5);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", 0.41, 2, 6);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", 0.41, 2, 7);
    model.component("comp1").geom("geom1").run("hex1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("pard1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("hex1");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", 3);
    model.component("comp1").geom("geom1").feature("cyl1").set("h", 0.7);
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new double[]{0, 0, -1.1});
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("pard2", "PartitionDomains");
    model.component("comp1").geom("geom1").feature("pard2").selection("domain").set("cyl1", 1);
    model.component("comp1").geom("geom1").feature("pard2").set("partitionwith", "extendedfaces");
    model.component("comp1").geom("geom1").feature("pard2").selection("extendedface").set("dif1", 17, 38);
    model.component("comp1").geom("geom1").run("pard2");
    model.component("comp1").geom("geom1").feature().create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(3);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("blk2", 1);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("pard2", 1, 2, 3);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("YZ \u5bf9\u79f0\u5e73\u9762");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel1").set("groupcontang", true);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection")
         .add("fin", 1, 4, 7, 10, 14, 17, 20, 23, 26, 30);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("XZ \u5bf9\u79f0\u5e73\u9762");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel2").set("groupcontang", true);
    model.component("comp1").geom("geom1").feature("sel2").selection("selection")
         .add("fin", 2, 5, 8, 11, 40, 42, 44, 46, 48, 50);
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").label("\u94a2\u5e95\u5ea7");
    model.component("comp1").geom("geom1").feature("boxsel1").set("zmax", -0.1);
    model.component("comp1").geom("geom1").feature("boxsel1").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel1");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").label("\u8154\u4f53");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("fin", 3);
    model.component("comp1").geom("geom1").run("sel3");
    model.component("comp1").geom("geom1").create("sel4", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel4").label("\u51e0\u4f55\u7ed3\u6784");
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").init();
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").set("fin");
    model.component("comp1").geom("geom1").run("sel4");
    model.component("comp1").geom("geom1").create("difsel1", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel1").label("\u7ebf\u5f39\u6027");
    model.component("comp1").geom("geom1").feature("difsel1").set("add", new String[]{"sel4"});
    model.component("comp1").geom("geom1").feature("difsel1").set("subtract", new String[]{"sel3"});

    model.title(null);

    model.description("");

    model.label("capacitive_pressure_sensor_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
