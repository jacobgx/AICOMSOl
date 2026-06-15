/*
 * decorative_plating_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:57 by COMSOL 6.3.0.290. */
public class decorative_plating_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Electrodeposition_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new double[]{100, 28, 7.25});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new double[]{-70, -10, -1.25});
    model.component("comp1").geom("geom1").feature("blk1").set("selresult", true);
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", 1.25);
    model.component("comp1").geom("geom1").feature("cyl1").set("h", 10);
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new double[]{-48, 8, -1.25});
    model.component("comp1").geom("geom1").feature("cyl1").set("axistype", "y");
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u8981\u51cf\u53bb\u7684\u5bf9\u8c61");
    model.component("comp1").geom("geom1").feature("cyl1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "zx");
    model.component("comp1").geom("geom1").feature("wp1").set("quicky", 8);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("size", new double[]{2.5, 10});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("pos", new double[]{-1.25, -53});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").selection("pointinsketch")
         .set("r1", 2, 3);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").set("radius", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("fil1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("rev1", "Revolve");
    model.component("comp1").geom("geom1").feature("rev1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("rev1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("rev1").set("angtype", "full");
    model.component("comp1").geom("geom1").feature("rev1").set("origfaces", false);
    model.component("comp1").geom("geom1").feature("rev1").set("angtype", "specang");
    model.component("comp1").geom("geom1").feature("rev1").set("angle2", 90);
    model.component("comp1").geom("geom1").feature("rev1").set("axistype", "3d");
    model.component("comp1").geom("geom1").feature("rev1").set("pos3", new double[]{-40, 8, -1.25});
    model.component("comp1").geom("geom1").feature("rev1").set("axis3", new int[]{0, 0, 1});
    model.component("comp1").geom("geom1").feature("rev1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("rev1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set();
    model.component("comp1").geom("geom1").feature("ext1").selection("input").named("csel1");
    model.component("comp1").geom("geom1").feature("ext1").set("extrudefrom", "faces");
    model.component("comp1").geom("geom1").feature("ext1").selection("inputface").named("csel1");
    model.component("comp1").geom("geom1").feature("ext1").selection("inputface").clear("cyl1");
    model.component("comp1").geom("geom1").feature("ext1").selection("inputface").set("rev1", 8);
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", 20, 0);
    model.component("comp1").geom("geom1").feature("ext1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input").named("csel1");
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("mir1").set("pos", new int[]{-20, 0, 0});
    model.component("comp1").geom("geom1").feature("mir1").set("axis", new int[]{1, 0, 0});
    model.component("comp1").geom("geom1").feature("mir1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("mir1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").named("blk1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").named("csel1");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").label("\u9634\u6781");
    model.component("comp1").geom("geom1").feature("boxsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel1").set("xmin", -55);
    model.component("comp1").geom("geom1").feature("boxsel1").set("xmax", 15);
    model.component("comp1").geom("geom1").feature("boxsel1").set("ymin", -8);
    model.component("comp1").geom("geom1").feature("boxsel1").set("ymax", 15);
    model.component("comp1").geom("geom1").feature("boxsel1").set("zmin", 0);
    model.component("comp1").geom("geom1").feature("boxsel1").set("zmax", 4);

    model.title(null);

    model.description("");

    model.label("decorative_plating_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
