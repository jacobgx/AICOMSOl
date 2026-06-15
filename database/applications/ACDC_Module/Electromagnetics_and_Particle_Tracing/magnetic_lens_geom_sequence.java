/*
 * magnetic_lens_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:23 by COMSOL 6.3.0.290. */
public class magnetic_lens_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Electromagnetics_and_Particle_Tracing");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", 10);
    model.component("comp1").geom("geom1").feature("cyl1").set("h", 2.5);
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", 6);
    model.component("comp1").geom("geom1").feature("cyl2").set("h", 2.5);
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").feature().duplicate("cyl3", "cyl1");
    model.component("comp1").geom("geom1").feature("cyl3").set("pos", new double[]{0, 0, -7.5});
    model.component("comp1").geom("geom1").run("cyl3");
    model.component("comp1").geom("geom1").create("cyl4", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl4").set("r", 2);
    model.component("comp1").geom("geom1").feature("cyl4").set("h", 2.5);
    model.component("comp1").geom("geom1").feature("cyl4").set("pos", new double[]{0, 0, -7.5});
    model.component("comp1").geom("geom1").feature().duplicate("cyl5", "cyl1");
    model.component("comp1").geom("geom1").feature("cyl5").set("pos", new double[]{0, 0, -2.5});
    model.component("comp1").geom("geom1").run("cyl5");
    model.component("comp1").geom("geom1").create("cyl6", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl6").set("r", 3);
    model.component("comp1").geom("geom1").feature("cyl6").set("h", 2.5);
    model.component("comp1").geom("geom1").feature("cyl6").set("pos", new double[]{0, 0, -2.5});
    model.component("comp1").geom("geom1").feature().duplicate("cyl7", "cyl1");
    model.component("comp1").geom("geom1").feature("cyl7").set("pos", new double[]{0, 0, 2.5});
    model.component("comp1").geom("geom1").run("cyl7");
    model.component("comp1").geom("geom1").create("cyl8", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl8").set("r", 3);
    model.component("comp1").geom("geom1").feature("cyl8").set("h", 2.5);
    model.component("comp1").geom("geom1").feature("cyl8").set("pos", new double[]{0, 0, 2.5});
    model.component("comp1").geom("geom1").run("cyl8");
    model.component("comp1").geom("geom1").create("cyl9", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl9").set("r", 20);
    model.component("comp1").geom("geom1").feature("cyl9").set("h", 50);
    model.component("comp1").geom("geom1").feature("cyl9").set("pos", new int[]{0, 0, -15});
    model.component("comp1").geom("geom1").run("cyl9");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("cyl1", "cyl3", "cyl5", "cyl7");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("cyl2", "cyl4", "cyl6", "cyl8");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").feature("wp1").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp1").selection("face").set("dif1", 3);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp2").selection("face").set("dif1", 13);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("type", "curve");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("r", 8);
    model.component("comp1").geom("geom1").feature("wp2").geom().run("c1");

    model.component("comp1").view("view1").set("renderwireframe", false);

    model.title(null);

    model.description("");

    model.label("magnetic_lens_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
