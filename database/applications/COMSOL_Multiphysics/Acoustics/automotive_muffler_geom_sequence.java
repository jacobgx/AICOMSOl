/*
 * automotive_muffler_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:32 by COMSOL 6.3.0.290. */
public class automotive_muffler_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Acoustics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", 0.03);
    model.component("comp1").geom("geom1").feature("cyl1").set("h", 0.75);
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new double[]{-0.1, 0, 0});
    model.component("comp1").geom("geom1").feature("cyl1").set("axistype", "cartesian");
    model.component("comp1").geom("geom1").feature("cyl1").set("ax3", new int[]{1, 0, 0});
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", 0.03);
    model.component("comp1").geom("geom1").feature("cyl2").set("h", 0.75);
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new double[]{0.25, -0.09, 0});
    model.component("comp1").geom("geom1").feature("cyl2").set("axistype", "cartesian");
    model.component("comp1").geom("geom1").feature("cyl2").set("ax3", new int[]{1, 0, 0});
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("e1", "Ellipse");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("e1")
         .set("semiaxes", new double[]{0.15, 0.07});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("e1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", 0.8, 0);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp2").set("quickx", 0.25);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("e1", "Ellipse");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("e1")
         .set("semiaxes", new double[]{0.15, 0.07});
    model.component("comp1").geom("geom1").feature("wp2").geom().run("e1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("r", 0.03);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("pos", new double[]{0.09, 0});
    model.component("comp1").geom("geom1").feature("wp2").geom().run("c1");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").create("wp3", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp3").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp3").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp3").set("quickx", 0.65);
    model.component("comp1").geom("geom1").feature("wp3").geom().create("e1", "Ellipse");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("e1")
         .set("semiaxes", new double[]{0.15, 0.07});
    model.component("comp1").geom("geom1").feature("wp3").geom().run("e1");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("c1").set("r", 0.03);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("c1").set("pos", new double[]{0.09, 0});
    model.component("comp1").geom("geom1").feature("wp3").geom().run("c1");
    model.component("comp1").geom("geom1").run("fin");

    model.title(null);

    model.description("");

    model.label("automotive_muffler_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
