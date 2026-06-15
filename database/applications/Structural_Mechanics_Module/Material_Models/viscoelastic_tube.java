/*
 * viscoelastic_tube.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:13 by COMSOL 6.3.0.290. */
public class viscoelastic_tube {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Material_Models");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");

    model.param().set("G_inst", "27.46[GPa]");
    model.param().descr("G_inst", "\u77ac\u65f6\u526a\u5207\u6a21\u91cf");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", 10);
    model.component("comp1").geom("geom1").feature("c1").set("angle", 90);
    model.component("comp1").geom("geom1").feature("c1").setIndex("layer", 5, 0);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("c1", 1);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("solid").prop("StructuralTransientBehavior")
         .set("StructuralTransientBehavior", "Quasistatic");
    model.component("comp1").physics("solid").feature("lemm1").set("IsotropicOption", "KG");
    model.component("comp1").physics("solid").feature("lemm1").create("vis1", "Viscoelasticity", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("vis1").setIndex("Gvm", "0.04*G_inst", 0, 0);
    model.component("comp1").physics("solid").feature("lemm1").feature("vis1").setIndex("tauvm", 30, 0, 0);
    model.component("comp1").physics("solid").feature("lemm1").feature("vis1").setIndex("Gvm", 0, 1, 0);
    model.component("comp1").physics("solid").feature("lemm1").feature("vis1").setIndex("tauvm", 0, 1, 0);
    model.component("comp1").physics("solid").feature("lemm1").feature("vis1").setIndex("tauvm", 0, 1, 0);
    model.component("comp1").physics("solid").feature("lemm1").feature("vis1").setIndex("Gvm", 0, 1, 0);
    model.component("comp1").physics("solid").feature("lemm1").feature("vis1").setIndex("tauvm", 0, 1, 0);
    model.component("comp1").physics("solid").feature("lemm1").feature("vis1").setIndex("Gvm", "0.08*G_inst", 1, 0);
    model.component("comp1").physics("solid").feature("lemm1").feature("vis1").setIndex("tauvm", 300, 1, 0);
    model.component("comp1").physics("solid").feature("lemm1").feature("vis1").setIndex("Gvm", 0, 2, 0);
    model.component("comp1").physics("solid").feature("lemm1").feature("vis1").setIndex("tauvm", 0, 2, 0);
    model.component("comp1").physics("solid").feature("lemm1").feature("vis1").setIndex("tauvm", 0, 2, 0);
    model.component("comp1").physics("solid").feature("lemm1").feature("vis1").setIndex("Gvm", 0, 2, 0);
    model.component("comp1").physics("solid").feature("lemm1").feature("vis1").setIndex("tauvm", 0, 2, 0);
    model.component("comp1").physics("solid").feature("lemm1").feature("vis1").setIndex("Gvm", "0.09*G_inst", 2, 0);
    model.component("comp1").physics("solid").feature("lemm1").feature("vis1").setIndex("tauvm", 3000, 2, 0);
    model.component("comp1").physics("solid").feature("lemm1").feature("vis1").setIndex("Gvm", 0, 3, 0);
    model.component("comp1").physics("solid").feature("lemm1").feature("vis1").setIndex("tauvm", 0, 3, 0);
    model.component("comp1").physics("solid").feature("lemm1").feature("vis1").setIndex("tauvm", 0, 3, 0);
    model.component("comp1").physics("solid").feature("lemm1").feature("vis1").setIndex("Gvm", 0, 3, 0);
    model.component("comp1").physics("solid").feature("lemm1").feature("vis1").setIndex("tauvm", 0, 3, 0);
    model.component("comp1").physics("solid").feature("lemm1").feature("vis1").setIndex("Gvm", "0.25*G_inst", 3, 0);
    model.component("comp1").physics("solid").feature("lemm1").feature("vis1").setIndex("tauvm", 12000, 3, 0);
    model.component("comp1").physics("solid").feature("lemm1").feature("vis1").set("shiftFunction", "shiftWLF");
    model.component("comp1").physics("solid").feature("lemm1").feature("vis1").set("Twlf", "500[K]");
    model.component("comp1").physics("solid").feature("lemm1").feature("vis1")
         .set("StaticResponse", "Instantaneous");
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 1);
    model.component("comp1").physics("solid").feature("sym1").selection().set(1, 2);
    model.component("comp1").physics("solid").create("disp1", "Displacement1", 1);
    model.component("comp1").physics("solid").feature("disp1").selection().set(3);
    model.component("comp1").physics("solid").feature("disp1").set("coordinateSystem", "sys1");
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("solid").feature("disp1").setIndex("U0", "-1[um]", 1);
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 1);
    model.component("comp1").physics("ht").feature("temp1").selection().set(3, 4);
    model.component("comp1").physics("ht").feature("temp1").set("T0", "(500+6*y/sqrt(x^2+y^2))[K]");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup().create("KG", "KG", "Bulk_modulus_and_shear_modulus");
    model.component("comp1").material("mat1").propertyGroup("KG").set("K", new String[]{"39.88[GPa]"});
    model.component("comp1").material("mat1").propertyGroup("KG").set("G", new String[]{"0.54*G_inst"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"7850[kg/m^3]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.06[N/(s*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("heatcapacity", new String[]{"2100[J/(kg*K)]"});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 12);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(3);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 24);
    model.component("comp1").mesh("mesh1").run();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std1").label("\u7814\u7a76\uff1a\u7a33\u6001");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("resolution", "normal");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u6e29\u5ea6 (ht)");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("solutionparams", "parent");
    model.result("pg2").feature("surf1").set("expr", "T");
    model.result("pg2").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg1").run();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").set("type", "plotgroup");
    model.nodeGroup("grp1").add("plotgroup", "pg1");
    model.nodeGroup("grp1").add("plotgroup", "pg2");
    model.nodeGroup("grp1").label("\u7a33\u6001\u7ed3\u679c");

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/solid", true);
    model.study("std2").feature("time").setSolveFor("/physics/ht", true);
    model.study("std2").label("\u7814\u7a76\uff1a\u77ac\u6001\uff08\u6052\u6e29\uff09");
    model.study("std2").feature("time")
         .set("tlist", "range(0,20,200) range(250,50,1000) range(1100,100,2000) range(2200,200,7200)");
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("v1").set("control", "user");
    model.sol("sol2").feature("v1").set("initmethod", "sol");
    model.sol("sol2").feature("v1").set("initsol", "sol1");
    model.sol("sol2").feature("v1").set("scalemethod", "init");
    model.sol("sol2").feature("t1").set("tstepsbdf", "intermediate");

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 63, 0);
    model.result("pg3").label("\u5e94\u529b (solid) 1");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg3").feature("surf1").set("threshold", "manual");
    model.result("pg3").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg3").feature("surf1").set("colortable", "Rainbow");
    model.result("pg3").feature("surf1").set("colortabletrans", "none");
    model.result("pg3").feature("surf1").set("colorscalemode", "linear");
    model.result("pg3").feature("surf1").set("resolution", "normal");
    model.result("pg3").feature("surf1").set("colortable", "Prism");
    model.result("pg3").feature("surf1").create("def", "Deform");
    model.result("pg3").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg3").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u6e29\u5ea6 (ht) 1");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevel", 63, 0);
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("solutionparams", "parent");
    model.result("pg4").feature("surf1").set("expr", "T");
    model.result("pg4").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg3").run();
    model.result("pg4").run();
    model.result().dataset().create("cpt1", "CutPoint2D");
    model.result().dataset("cpt1").set("pointx", 7.5);
    model.result().dataset("cpt1").set("pointy", 0);
    model.result().dataset("cpt1").set("data", "dset2");
    model.result().dataset().create("cpt2", "CutPoint2D");
    model.result().dataset("cpt2").set("pointx", 0);
    model.result().dataset("cpt2").set("pointy", 7.5);
    model.result().dataset("cpt2").set("data", "dset2");
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").set("data", "dset2");
    model.result("pg5").label("\u5e94\u529b\u677e\u5f1b\uff08\u6052\u6e29\uff09");
    model.result("pg5").set("titletype", "manual");
    model.result("pg5").set("title", "\u5f84\u5411\u5e94\u529b\u677e\u5f1b");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u5f84\u5411\u5e94\u529b (MPa)");
    model.result("pg5").create("ptgr1", "PointGraph");
    model.result("pg5").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg5").feature("ptgr1").set("linewidth", "preference");
    model.result("pg5").feature("ptgr1").set("data", "cpt1");
    model.result("pg5").feature("ptgr1").set("expr", "solid.sGpxx");
    model.result("pg5").feature("ptgr1").set("descr", "\u5e94\u529b\u5f20\u91cf\uff0cxx \u5206\u91cf");
    model.result("pg5").feature("ptgr1").set("linewidth", 2);
    model.result("pg5").feature("ptgr1").set("legend", true);
    model.result("pg5").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg5").feature("ptgr1").setIndex("legends", "x \u8f74\u4e0a", 0);
    model.result("pg5").run();
    model.result("pg5").create("ptgr2", "PointGraph");
    model.result("pg5").feature("ptgr2").set("markerpos", "datapoints");
    model.result("pg5").feature("ptgr2").set("linewidth", "preference");
    model.result("pg5").feature("ptgr2").set("data", "cpt2");
    model.result("pg5").feature("ptgr2").set("expr", "solid.sGpyy");
    model.result("pg5").feature("ptgr2").set("descr", "\u5e94\u529b\u5f20\u91cf\uff0cyy \u5206\u91cf");
    model.result("pg5").feature("ptgr2").set("linewidth", 2);
    model.result("pg5").feature("ptgr2").set("linestyle", "dashed");
    model.result("pg5").feature("ptgr2").set("legend", true);
    model.result("pg5").feature("ptgr2").set("legendmethod", "manual");
    model.result("pg5").feature("ptgr2").setIndex("legends", "y \u8f74\u4e0a", 0);
    model.result("pg5").run();
    model.result("pg5").set("legendpos", "lowerright");
    model.result("pg3").run();

    model.nodeGroup().create("grp2", "Results");
    model.nodeGroup("grp2").set("type", "plotgroup");
    model.nodeGroup().move("grp2", 1);
    model.nodeGroup("grp2").add("plotgroup", "pg3");
    model.nodeGroup("grp2").add("plotgroup", "pg4");
    model.nodeGroup("grp2").add("plotgroup", "pg5");
    model.nodeGroup("grp2").label("\u77ac\u6001\u7ed3\u679c\uff08\u6052\u6e29\uff09");

    model.study().create("std3");
    model.study("std3").create("time", "Transient");
    model.study("std3").feature("time").setSolveFor("/physics/solid", true);
    model.study("std3").feature("time").setSolveFor("/physics/ht", true);
    model.study("std3").feature("time")
         .set("tlist", "range(0,20,200) range(250,50,1000) range(1100,100,2000) range(2200,200,7200)");
    model.study("std3").feature("time").set("useadvanceddisable", true);
    model.study("std3").feature("time").set("disabledphysics", new String[]{"ht/temp1"});
    model.study("std3").label("\u7814\u7a76\uff1a\u77ac\u6001\uff08\u53d8\u6e29\uff09");
    model.study("std3").showAutoSequences("all");

    model.sol("sol3").feature("v1").set("control", "user");
    model.sol("sol3").feature("v1").set("initmethod", "sol");
    model.sol("sol3").feature("v1").set("initsol", "sol1");
    model.sol("sol3").feature("v1").set("scalemethod", "init");
    model.sol("sol3").feature("t1").set("tstepsbdf", "intermediate");

    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").setIndex("looplevel", 63, 0);
    model.result("pg6").label("\u5e94\u529b (solid) 2");
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg6").feature("surf1").set("threshold", "manual");
    model.result("pg6").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg6").feature("surf1").set("colortable", "Rainbow");
    model.result("pg6").feature("surf1").set("colortabletrans", "none");
    model.result("pg6").feature("surf1").set("colorscalemode", "linear");
    model.result("pg6").feature("surf1").set("resolution", "normal");
    model.result("pg6").feature("surf1").set("colortable", "Prism");
    model.result("pg6").feature("surf1").create("def", "Deform");
    model.result("pg6").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg6").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").label("\u6e29\u5ea6 (ht) 2");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").setIndex("looplevel", 63, 0);
    model.result("pg7").feature().create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("showsolutionparams", "on");
    model.result("pg7").feature("surf1").set("solutionparams", "parent");
    model.result("pg7").feature("surf1").set("expr", "T");
    model.result("pg7").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg7").feature("surf1").set("showsolutionparams", "on");
    model.result("pg7").feature("surf1").set("data", "parent");
    model.result("pg6").run();
    model.result().dataset().duplicate("cpt3", "cpt1");
    model.result().dataset("cpt3").set("data", "dset3");
    model.result().dataset().duplicate("cpt4", "cpt2");
    model.result().dataset("cpt4").set("data", "dset3");
    model.result("pg5").run();
    model.result().duplicate("pg8", "pg5");

    model.nodeGroup("grp2").add("plotgroup", "pg8");

    model.result("pg8").run();

    model.nodeGroup("grp2").remove("plotgroup", "pg8", false);

    model.result("pg8").run();
    model.result("pg8").label("\u5e94\u529b\u677e\u5f1b\uff08\u53d8\u6e29\uff09");
    model.result("pg8").run();
    model.result("pg8").feature("ptgr1").set("data", "cpt3");
    model.result("pg8").run();
    model.result("pg8").feature("ptgr2").set("data", "cpt4");
    model.result("pg8").run();
    model.result("pg8").run();

    model.nodeGroup().create("grp3", "Results");
    model.nodeGroup("grp3").set("type", "plotgroup");
    model.nodeGroup().move("grp3", 2);
    model.nodeGroup("grp3").add("plotgroup", "pg8");
    model.nodeGroup("grp3").add("plotgroup", "pg6");
    model.nodeGroup("grp3").add("plotgroup", "pg7");
    model.nodeGroup("grp3").label("\u77ac\u6001\u7ed3\u679c\uff08\u53d8\u6e29\uff09");

    model.result("pg8").run();

    model.title("\u9ecf\u5f39\u6027\u7ba1\u7684\u5e94\u529b\u677e\u5f1b");

    model
         .description("\u672c\u4f8b\u7814\u7a76\u56db\u5206\u652f\u5e7f\u4e49\u9ea6\u514b\u65af\u97e6\u6750\u6599\u4e2d\u9ecf\u5f39\u6027\u5e94\u529b\u677e\u5f1b\u7684\u6e29\u5ea6\u6548\u5e94\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("viscoelastic_tube.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
