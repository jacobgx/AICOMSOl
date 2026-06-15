/*
 * polymerization_multijet_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:23 by COMSOL 6.3.0.290. */
public class polymerization_multijet_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Chemical_Reaction_Engineering_Module\\Reactors_with_Mass_and_Heat_Transfer");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", 0.005);
    model.component("comp1").geom("geom1").feature("cyl1").set("h", 0.06);
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new double[]{0.01318, 0, 0.0205});
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("cyl1");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", -19.2);
    model.component("comp1").geom("geom1").feature("rot1").set("pos", new double[]{0.01318, 0, 0.05});
    model.component("comp1").geom("geom1").feature("rot1").set("axistype", "y");
    model.component("comp1").geom("geom1").run("rot1");
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", 0.005);
    model.component("comp1").geom("geom1").feature("cyl2").set("h", 0.03);
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new double[]{-0.03, 0, 0.036});
    model.component("comp1").geom("geom1").feature("cyl2").set("axistype", "x");
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").selection("inputface").set("cyl2", 4);
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", 0.016, 0);
    model.component("comp1").geom("geom1").feature("ext1").setIndex("scale", ".9", 0, 0);
    model.component("comp1").geom("geom1").feature("ext1").setIndex("scale", ".9", 0, 1);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("ext1", "rot1");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "zx");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").selection("input").set("uni1");
    model.component("comp1").geom("geom1").feature("par1").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").run("par1");
    model.component("comp1").geom("geom1").feature().create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(3);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("par1", 2);
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("r", 0.05);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("angle", 18);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("rot", 90);
    model.component("comp1").geom("geom1").feature("wp2").geom().run("c1");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").feature().create("ext2", "Extrude");
    model.component("comp1").geom("geom1").feature("ext2").set("workplane", "wp2");
    model.component("comp1").geom("geom1").feature("ext2").selection("input").set("wp2");
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", ".1", 0);
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", ".3", 1);
    model.component("comp1").geom("geom1").feature("ext2").set("displ", new String[][]{{"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext2").set("scale", new String[][]{{"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext2").set("twist", new String[]{"0", "0"});
    model.component("comp1").geom("geom1").run("ext2");
    model.component("comp1").geom("geom1").run("fin");

    model.title(null);

    model.description("");

    model.label("polymerization_multijet_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
