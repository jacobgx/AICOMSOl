/*
 * electric_sensor.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:33 by COMSOL 6.3.0.290. */
public class electric_sensor {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Electromagnetics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("es", "Electrostatics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/es", true);

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickz", 0.1);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("size", new double[]{0.5, 2});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("pos", new double[]{-1, 0.5});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("size", new double[]{1.5, 0.25});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("pos", new double[]{-1.5, 1});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3").set("size", new double[]{1.5, 0.25});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3").set("pos", new double[]{-1.5, 1.75});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").selection("input")
         .set("r1", "r2", "r3");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("uni1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("e1", "Ellipse");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("e1").set("semiaxes", new double[]{0.5, 1});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("e1").set("pos", new double[]{1.5, 1.5});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("e1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("e2", "Ellipse");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("e2").set("semiaxes", new double[]{1, 0.5});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("e2").set("pos", new double[]{1.5, 1.5});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("e2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("co1", "Compose");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("co1").selection("input").set("e1", "e2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("co1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("co1").set("formula", "e1+e2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("co1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", 0.8, 0);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new int[]{5, 3, 1});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new int[]{-2, 0, 0});
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").view("view1").set("transparency", true);

    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("es").create("ccnf1", "ChargeConservationFluid", 3);
    model.component("comp1").physics("es").feature("ccnf1").selection().set(2, 3);
    model.component("comp1").physics("es").create("gnd1", "Ground", 2);
    model.component("comp1").physics("es").feature("gnd1").selection().set(3);
    model.component("comp1").physics("es").create("pot1", "ElectricPotential", 2);
    model.component("comp1").physics("es").feature("pot1").selection().set(4);
    model.component("comp1").physics("es").feature("pot1").set("V0", 1);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").selection().set(2);
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"2"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").selection().set(3);
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermittivity", new String[]{"3"});

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset1").selection().geom("geom1", 2);
    model.result().dataset("dset1").selection().geom("geom1", 2);
    model.result().dataset("dset1").selection().all();
    model.result().dataset("dset1").selection().geom("geom1", 2);
    model.result().dataset("dset1").selection().set(3, 4, 5, 38);

    model.component("comp1").view("view1").set("transparency", false);

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "es.nD");
    model.result("pg1").feature("surf1").set("descr", "\u8868\u9762\u7535\u8377\u5bc6\u5ea6");
    model.result("pg1").feature("surf1").set("unit", "pC/m^2");
    model.result("pg1").feature("surf1").set("colortable", "Cyclic");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").set("posmethod", "magnitude");
    model.result("pg1").feature("str1").set("linetype", "tube");
    model.result("pg1").feature("str1").create("col1", "Color");
    model.result("pg1").run();
    model.result("pg1").feature("str1").feature("col1").set("expr", "es.normE");
    model.result("pg1").feature("str1").feature("col1").set("descr", "\u7535\u573a\u6a21");
    model.result("pg1").feature("str1").feature("col1").set("titletype", "auto");

    model.title("\u7535\u4f20\u611f\u5668");

    model
         .description("\u672c\u4f8b\u4ecb\u7ecd\u5982\u4f55\u5728\u76d2\u5b50\u8fb9\u754c\u4e0a\u65bd\u52a0\u7535\u52bf\u5dee\u6765\u663e\u793a\u76d2\u5185\u7684\u4ecb\u7535\u5e38\u6570\u3002\u76d2\u5185\u4ecb\u8d28\u7684\u4ecb\u7535\u5e38\u6570\u5b58\u5728\u5dee\u5f02\u5bfc\u81f4\u8868\u9762\u7535\u8377\u5bc6\u5ea6\u4e5f\u4e0d\u540c\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("electric_sensor.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
