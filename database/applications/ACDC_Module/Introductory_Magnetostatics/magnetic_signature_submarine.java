/*
 * magnetic_signature_submarine.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:23 by COMSOL 6.3.0.290. */
public class magnetic_signature_submarine {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Introductory_Magnetostatics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("mfnc", "MagnetostaticsNoCurrents", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/mfnc", true);

    model.param().set("gB", "-5e-5[T]");
    model.param().descr("gB", "\u5730\u78c1\u573a");

    model.component("comp1").geom("geom1").insertFile("magnetic_signature_submarine_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").feature("uni1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("uni1").set("selresultshow", "bnd");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u57df\u6750\u6599");
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u8239\u4f53\u91d1\u5c5e");
    model.component("comp1").material("mat2").selection().geom("geom1", 2);
    model.component("comp1").material("mat2").selection().named("geom1_uni1_bnd");
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermeability", new String[]{"700"});

    model.component("comp1").physics("mfnc").prop("BackgroundField").set("SolveFor", "ReducedField");
    model.component("comp1").physics("mfnc").prop("BackgroundField")
         .set("Hb", new String[]{"0", "0", "gB/mu0_const"});
    model.component("comp1").physics("mfnc").create("exfd1", "ExternalMagneticFluxDensity", 2);
    model.component("comp1").physics("mfnc").feature("exfd1").selection().all();
    model.component("comp1").physics("mfnc").create("zsp1", "ZeroMagneticScalarPotential", 0);
    model.component("comp1").physics("mfnc").feature("zsp1").selection().set(3);
    model.component("comp1").physics("mfnc").create("ms1", "MagneticShielding", 2);
    model.component("comp1").physics("mfnc").feature("ms1").selection().named("geom1_uni1_bnd");
    model.component("comp1").physics("mfnc").feature("ms1").set("ds", 0.05);

    model.component("comp1").view("view1").set("renderwireframe", false);
    model.component("comp1").view("view1").hideEntities().create("hide1");
    model.component("comp1").view("view1").hideEntities("hide1").geom(2);
    model.component("comp1").view("view1").hideEntities("hide1").add(1, 2, 4);

    model.component("comp1").mesh("mesh1").autoMeshSize(4);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").set("edges", false);
    model.result("pg1").create("slc1", "Slice");
    model.result("pg1").feature("slc1").set("expr", "mfnc.normB");
    model.result("pg1").feature("slc1").set("descr", "\u78c1\u901a\u5bc6\u5ea6\u6a21");
    model.result("pg1").feature("slc1").set("quickplane", "xy");
    model.result("pg1").feature("slc1").set("quickzmethod", "coord");
    model.result("pg1").feature("slc1").set("quickz", -15);
    model.result("pg1").feature("slc1").create("filt1", "Filter");
    model.result("pg1").run();
    model.result("pg1").feature("slc1").feature("filt1").set("expr", "abs(x)<(tl*.8)&&abs(y)<(r*5)");
    model.result("pg1").run();
    model.result("pg1").create("iso1", "Isosurface");
    model.result("pg1").feature("iso1").create("filt1", "Filter");
    model.result("pg1").run();
    model.result("pg1").feature("iso1").feature("filt1").set("expr", "x>0");
    model.result("pg1").run();
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "1");
    model.result("pg1").feature("surf1").set("coloring", "uniform");
    model.result("pg1").feature("surf1").set("color", "black");
    model.result("pg1").feature("surf1").create("sel1", "Selection");
    model.result("pg1").feature("surf1").feature("sel1").selection().named("geom1_uni1_bnd");
    model.result("pg1").run();
    model.result("pg1").create("arws1", "ArrowSurface");
    model.result("pg1").feature("arws1").set("expr", new String[]{"mfnc.tBx", "mfnc.tBy", "mfnc.tBz"});
    model.result("pg1").feature("arws1").set("descr", "\u5207\u5411\u78c1\u901a\u5bc6\u5ea6");
    model.result("pg1").feature("arws1").set("color", "white");
    model.result("pg1").run();

    model.title("\u6f5c\u8247\u7684\u78c1\u4fe1\u53f7");

    model
         .description("\u5728\u6c34\u9762\u6216\u6c34\u4e0b\u822a\u884c\u7684\u6f5c\u8247\u4f1a\u5728\u5730\u7403\u78c1\u573a\u4e2d\u4ea7\u751f\u53ef\u68c0\u6d4b\u7684\u5c40\u90e8\u5e72\u6270\uff08\u53ef\u7528\u4e8e\u63a2\u6d4b\uff09\uff0c\u56e0\u6b64\uff0c\u6d77\u519b\u8230\u8247\u8bbe\u8ba1\u7684\u4e00\u4e2a\u91cd\u8981\u6b65\u9aa4\u662f\u9884\u6d4b\u5176\u78c1\u4fe1\u53f7\u3002\u672c\u4f8b\u6f14\u793a\u4e00\u79cd\u5f3a\u5927\u7684\u6280\u672f\uff0c\u53ef\u4ee5\u907f\u514d\u5bf9\u8584\u677f\u8fdb\u884c\u4f53\u7f51\u683c\u5212\u5206\u5e26\u6765\u7684\u56f0\u96be\uff0c\u5e76\u652f\u6301\u7cbe\u786e\u8ba1\u7b97\u8239\u8236\u3001\u6c7d\u8f66\u548c\u5176\u4ed6\u4ea4\u901a\u5de5\u5177\u7684\u78c1\u4fe1\u53f7\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("magnetic_signature_submarine.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
