/*
 * temperature_dependent_plasticity_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:36 by COMSOL 6.3.0.290. */
public class temperature_dependent_plasticity_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Nonlinear_Structural_Materials_Module\\Plasticity");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics("solid").prop("StructuralTransientBehavior")
         .set("StructuralTransientBehavior", "Quasistatic");
    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");

    model.component("comp1").multiphysics().create("te1", "ThermalExpansion", 3);
    model.component("comp1").multiphysics("te1").set("Heat_physics", "ht");
    model.component("comp1").multiphysics("te1").set("Solid_physics", "solid");
    model.component("comp1").multiphysics("te1").selection().all();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/te1", true);

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "xz");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("x", "1.3 1.08 1.08 1.12 1.3");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("y", "0.07 0.07 0.14 0.1 0.1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pol1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("size", new double[]{0.3, 0.01});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("pos", new double[]{1, 0.06});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("rev1", "Revolve");
    model.component("comp1").geom("geom1").feature("rev1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("rev1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("rev1").set("angtype", "full");
    model.component("comp1").geom("geom1").feature("rev1").set("axis", new int[]{1, 0});
    model.component("comp1").geom("geom1").feature("rev1").set("angtype", "specang");
    model.component("comp1").geom("geom1").feature("rev1").set("angle2", -90);
    model.component("comp1").geom("geom1").run("rev1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("quickplane", "xz");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", false);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1").set("size", new double[]{0.09, 0.3});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1").set("pos", new double[]{1.01, 0});
    model.component("comp1").geom("geom1").feature("wp2").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r2").set("size", new double[]{0.01, 0.3});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r2").set("pos", new int[]{1, 0});
    model.component("comp1").geom("geom1").feature("wp2").geom().run("r2");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").feature().create("rev2", "Revolve");
    model.component("comp1").geom("geom1").feature("rev2").set("workplane", "wp2");
    model.component("comp1").geom("geom1").feature("rev2").selection("input").set("wp2");
    model.component("comp1").geom("geom1").feature("rev2").set("angtype", "specang");
    model.component("comp1").geom("geom1").feature("rev2").set("angle2", 45);
    model.component("comp1").geom("geom1").run("rev2");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").set("keepsubtract", true);
    model.component("comp1").geom("geom1").feature("dif1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("rev1(2)");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("rev2(2)");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("dif2", "Difference");
    model.component("comp1").geom("geom1").feature("dif2").set("keepsubtract", true);
    model.component("comp1").geom("geom1").feature("dif2").selection("input").set("rev1(1)");
    model.component("comp1").geom("geom1").feature("dif2").selection("input2").set("rev2(1)");
    model.component("comp1").geom("geom1").run("dif2");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", 0.06);
    model.component("comp1").geom("geom1").feature("cyl1").set("h", 0.4);
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new double[]{0.95, 0, 0});
    model.component("comp1").geom("geom1").feature("cyl1").set("axistype", "cartesian");
    model.component("comp1").geom("geom1").feature("cyl1").set("ax3", new int[]{1, 0, 0});
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("dif3", "Difference");
    model.component("comp1").geom("geom1").feature("dif3").selection("input").set("rev2");
    model.component("comp1").geom("geom1").feature("dif3").selection("input2").set("cyl1");
    model.component("comp1").geom("geom1").run("dif3");
    model.component("comp1").geom("geom1").create("dif4", "Difference");
    model.component("comp1").geom("geom1").feature("dif4").selection("input").set("dif3");
    model.component("comp1").geom("geom1").feature("dif4").selection("input2").set("dif1");
    model.component("comp1").geom("geom1").feature("dif4").set("keepsubtract", true);
    model.component("comp1").geom("geom1").run("dif4");
    model.component("comp1").geom("geom1").run("fin");

    model.title(null);

    model.description("");

    model.label("temperature_dependent_plasticity_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
