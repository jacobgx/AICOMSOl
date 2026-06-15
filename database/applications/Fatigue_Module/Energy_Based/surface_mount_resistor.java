/*
 * surface_mount_resistor.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:01 by COMSOL 6.3.0.290. */
public class surface_mount_resistor {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Fatigue_Module\\Energy_Based");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/solid", true);

    model.component("comp1").geom("geom1").insertFile("surface_mount_resistor_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("ige1");

    model.func().create("int1", "Interpolation");
    model.func("int1").set("source", "file");
    model.func("int1").set("filename", "surface_mount_resistor_thermal_load_cycle.txt");
    model.func("int1").importData();
    model.func("int1").set("funcname", "thermLC");
    model.func("int1").setIndex("argunit", "min", 0);
    model.func("int1").setIndex("fununit", "degC", 0);

    model.component("comp1").physics("solid").prop("d").set("d", "1.55[mm]");
    model.component("comp1").physics("solid").prop("StructuralTransientBehavior")
         .set("StructuralTransientBehavior", "Quasistatic");
    model.component("comp1").physics("solid").feature("lemm1").set("CalculateDissipatedEnergy", true);
    model.component("comp1").physics("solid").feature("lemm1").create("te1", "ThermalExpansion", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("te1")
         .set("minput_temperature_src", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").feature("te1")
         .set("minput_temperature", "thermLC(t)");
    model.component("comp1").physics("solid").feature("lemm1").create("cmm1", "Creep2", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("cmm1").selection().named("geom1_csel2_dom");
    model.component("comp1").physics("solid").feature("lemm1").feature("cmm1")
         .set("minput_temperature_src", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").feature("cmm1")
         .set("minput_temperature", "thermLC(t)");
    model.component("comp1").physics("solid").feature("lemm1").feature("cmm1").set("materialModel", "Garofalo");
    model.component("comp1").physics("solid").feature("lemm1").feature("cmm1").set("thermalEffects", "Arrhenius");
    model.component("comp1").physics("solid").feature("lemm1").feature("cmm1").set("Q", 53200);
    model.component("comp1").physics("solid").feature("lemm1").feature("cmm1").set("timeMethod", "forwardEuler");
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 1);
    model.component("comp1").physics("solid").feature("sym1").selection().set(19, 20);
    model.component("comp1").physics("solid").create("roll1", "Roller", 1);
    model.component("comp1").physics("solid").feature("roll1").selection().set(2);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("PCB");
    model.component("comp1").material("mat1").selection().named("geom1_r1_dom");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"22[GPa]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.4"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"0"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"21e-6"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u94dc");
    model.component("comp1").material("mat2").selection().named("geom1_r3_dom");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", new String[]{"141[GPa]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", new String[]{"0.35"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"0"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"16.6e-6"});
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").label("\u710a\u6599");
    model.component("comp1").material("mat3").selection().named("geom1_csel2_dom");
    model.component("comp1").material("mat3").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("E", new String[]{"50[GPa]"});
    model.component("comp1").material("mat3").propertyGroup("Enu").set("nu", new String[]{"0.4"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", new String[]{"0"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"21e-6"});
    model.component("comp1").material("mat3").propertyGroup().create("Garofalo", "Garofalo", "Garofalo");
    model.component("comp1").material("mat3").propertyGroup("Garofalo").set("A_gar", new String[]{"262000"});
    model.component("comp1").material("mat3").propertyGroup("Garofalo").set("sigRef_gar", new String[]{"39.1[MPa]"});
    model.component("comp1").material("mat3").propertyGroup("Garofalo").set("n_gar", new String[]{"6.19"});
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").label("\u954d\u94ec");
    model.component("comp1").material("mat4").selection().named("geom1_csel1_dom");
    model.component("comp1").material("mat4").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat4").propertyGroup("Enu").set("E", new String[]{"170[GPa]"});
    model.component("comp1").material("mat4").propertyGroup("Enu").set("nu", new String[]{"0.31"});
    model.component("comp1").material("mat4").propertyGroup("def").set("density", new String[]{"0"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"13e-6"});
    model.component("comp1").material().create("mat5", "Common");
    model.component("comp1").material("mat5").label("\u6c27\u5316\u94dd");
    model.component("comp1").material("mat5").selection().named("geom1_r2_dom");
    model.component("comp1").material("mat5").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat5").propertyGroup("Enu").set("E", new String[]{"300[GPa]"});
    model.component("comp1").material("mat5").propertyGroup("Enu").set("nu", new String[]{"0.22"});
    model.component("comp1").material("mat5").propertyGroup("def").set("density", new String[]{"0"});
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"8e-6"});

    model.component("comp1").mesh("mesh1").autoMeshSize(3);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("dis1").selection().set(8);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("dis1").set("elemcount", 50);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("dis1").set("elemratio", 0.1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,10,60*60)");
    model.study("std1").feature("time").set("usertol", true);
    model.study("std1").feature("time").set("rtol", "1e-4");
    model.study("std1").label("\u65f6\u7a0b");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 361, 0);
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
    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("surf1").feature("def").set("scale", 20);
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 361, 0);
    model.result("pg2").label("\u7b49\u6548\u8815\u53d8\u5e94\u53d8 (solid)");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"solid.eceGp"});
    model.result("pg2").feature("surf1").set("inheritplot", "none");
    model.result("pg2").feature("surf1").set("resolution", "normal");
    model.result("pg2").feature("surf1").set("colortabletype", "discrete");
    model.result("pg2").feature("surf1").set("bandcount", 11);
    model.result("pg2").feature("surf1").set("colortable", "AuroraAustralisDark");
    model.result("pg2").feature("surf1").set("descractive", true);
    model.result("pg2").feature("surf1").set("descr", "\u7b49\u6548\u8815\u53d8\u5e94\u53d8");
    model.result("pg2").label("\u7b49\u6548\u8815\u53d8\u5e94\u53d8 (solid)");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u8815\u53d8\u5e94\u53d8");
    model.result("pg3").create("ptgr1", "PointGraph");
    model.result("pg3").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg3").feature("ptgr1").set("linewidth", "preference");
    model.result("pg3").feature("ptgr1").selection().named("geom1_pt1_pnt");
    model.result("pg3").feature("ptgr1").set("expr", "solid.eceGp");
    model.result("pg3").feature("ptgr1").set("descr", "\u7b49\u6548\u8815\u53d8\u5e94\u53d8");
    model.result("pg3").feature("ptgr1").set("legend", true);
    model.result("pg3").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg3").feature("ptgr1").setIndex("legends", "\u6709\u6548", 0);
    model.result("pg3").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg3").run();
    model.result("pg3").feature("ptgr2").set("expr", "solid.eclGp12");
    model.result("pg3").feature("ptgr2")
         .set("descr", "\u8815\u53d8\u5e94\u53d8\u5f20\u91cf\uff0c\u5c40\u90e8\u5750\u6807\u7cfb\uff0c12 \u5206\u91cf");
    model.result("pg3").feature("ptgr2").setIndex("legends", "Shear", 0);
    model.result("pg3").run();
    model.result("pg3").set("legendpos", "upperleft");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u526a\u5207\u6ede\u56de");
    model.result("pg4").create("ptgr1", "PointGraph");
    model.result("pg4").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg4").feature("ptgr1").set("linewidth", "preference");
    model.result("pg4").feature("ptgr1").selection().named("geom1_pt1_pnt");
    model.result("pg4").feature("ptgr1").set("expr", "solid.sGpxy");
    model.result("pg4").feature("ptgr1").set("descr", "\u5e94\u529b\u5f20\u91cf\uff0cxy \u5206\u91cf");
    model.result("pg4").feature("ptgr1").set("xdata", "expr");
    model.result("pg4").feature("ptgr1").set("xdataexpr", "solid.eclGp12");
    model.result("pg4").feature("ptgr1")
         .set("xdatadescr", "\u8815\u53d8\u5e94\u53d8\u5f20\u91cf\uff0c\u5c40\u90e8\u5750\u6807\u7cfb\uff0c12 \u5206\u91cf");
    model.result("pg4").run();

    model.component("comp1").physics().create("ftg", "Fatigue", "geom1");

    model.study("std1").feature("time").setSolveFor("/physics/ftg", false);

    model.component("comp1").physics("ftg").label("\u75b2\u52b3\uff0cCoffin-Manson");
    model.component("comp1").physics("ftg").create("elif1", "StrainLifeModel", 2);
    model.component("comp1").physics("ftg").feature("elif1").selection().named("geom1_csel2_dom");
    model.component("comp1").physics("ftg").feature("elif1").set("fatigueInputPhysics", "solid");
    model.component("comp1").physics("ftg").feature("elif1").set("ftgElifCrit", "CoffinManson");
    model.component("comp1").physics("ftg").feature("elif1").set("strainTypeCM", "Creep");
    model.component("comp1").physics().create("ftg2", "Fatigue", "geom1");

    model.study("std1").feature("time").setSolveFor("/physics/ftg2", false);

    model.component("comp1").physics("ftg2").label("\u75b2\u52b3\uff0cMorrow");
    model.component("comp1").physics("ftg2").create("ener1", "EnergyBasedModel", 2);
    model.component("comp1").physics("ftg2").feature("ener1").selection().named("geom1_csel2_dom");
    model.component("comp1").physics("ftg2").feature("ener1").set("fatigueInputPhysics", "solid");
    model.component("comp1").physics("ftg2").feature("ener1").set("ftgEnerType", "Creep");

    model.component("comp1").material("mat3").propertyGroup()
         .create("fatigueEnergyMorrow", "fatigueEnergyMorrow", "Morrow[Fatigue]");
    model.component("comp1").material("mat3").propertyGroup()
         .create("fatigueStrainCoffinManson", "fatigueStrainCoffinManson", "Coffin-Manson[Fatigue]");
    model.component("comp1").material("mat3").propertyGroup("fatigueEnergyMorrow")
         .set("Wf_Morrow", new String[]{"55e6[J/m^3]"});
    model.component("comp1").material("mat3").propertyGroup("fatigueEnergyMorrow")
         .set("m_Morrow", new String[]{"-0.69"});
    model.component("comp1").material("mat3").propertyGroup("fatigueStrainCoffinManson")
         .set("epsilonf_CM", new String[]{"0.218"});
    model.component("comp1").material("mat3").propertyGroup("fatigueStrainCoffinManson")
         .set("c_CM", new String[]{"-0.51"});

    model.study().create("std2");
    model.study("std2").create("ftge", "Fatigue");
    model.study("std2").feature("ftge").set("ftplistmethod", "manual");
    model.study("std2").feature("ftge").set("solnum", "auto");
    model.study("std2").feature("ftge").set("usesol", "off");
    model.study("std2").feature("ftge").set("outputmap", new String[]{});
    model.study("std2").feature("ftge").setSolveFor("/physics/solid", false);
    model.study("std2").feature("ftge").setSolveFor("/physics/ftg", true);
    model.study("std2").feature("ftge").setSolveFor("/physics/ftg2", true);
    model.study("std2").label("\u75b2\u52b3");
    model.study("std2").feature("ftge").set("usesol", true);
    model.study("std2").feature("ftge").set("notsolmethod", "sol");
    model.study("std2").feature("ftge").set("notstudy", "std1");
    model.study("std2").feature("ftge").set("notsolnum", "from_list");
    model.study("std2").feature("ftge")
         .set("notlistsolnum", new int[]{301, 302, 303, 304, 305, 306, 307, 308, 309, 310, 311, 312, 313, 314, 315, 316, 317, 318, 319, 320, 321, 322, 323, 324, 325, 326, 327, 328, 329, 330, 331, 332, 333, 334, 335, 336, 337, 338, 339, 340, 341, 342, 343, 344, 345, 346, 347, 348, 349, 350, 351, 352, 353, 354, 355, 356, 357, 358, 359, 360, 361});
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"ftg.ctf"});
    model.result("pg5").feature("surf1").set("colortable", "Rainbow");
    model.result("pg5").feature("surf1").set("colortabletrans", "none");
    model.result("pg5").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg5").feature("surf1").set("colortablerev", true);
    model.result("pg5").feature("surf1").set("colortable", "Traffic");
    model.result("pg5").label("\u5931\u6548\u5faa\u73af\u6b21\u6570 (ftg)");
    model.result("pg5").feature("surf1").create("mrkr1", "Marker");
    model.result("pg5").feature("surf1").feature("mrkr1").set("precision", 3);
    model.result("pg5").feature("surf1").feature("mrkr1").set("display", "min");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"ftg2.ctf"});
    model.result("pg6").feature("surf1").set("colortable", "Rainbow");
    model.result("pg6").feature("surf1").set("colortabletrans", "none");
    model.result("pg6").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg6").feature("surf1").set("colortablerev", true);
    model.result("pg6").feature("surf1").set("colortable", "Traffic");
    model.result("pg6").label("\u5931\u6548\u5faa\u73af\u6b21\u6570 (ftg2)");
    model.result("pg6").feature("surf1").create("mrkr1", "Marker");
    model.result("pg6").feature("surf1").feature("mrkr1").set("precision", 3);
    model.result("pg6").feature("surf1").feature("mrkr1").set("display", "min");
    model.result("pg5").run();
    model.result("pg5").label("\u5931\u6548\u5faa\u73af\u6b21\u6570\uff0cCoffin-Manson");
    model.result("pg5").run();
    model.result("pg5").feature("surf1").feature("mrkr1").set("anchorpoint", "lowerright");
    model.result("pg6").run();
    model.result("pg6").label("\u5931\u6548\u5faa\u73af\u6b21\u6570\uff0cMorrow");
    model.result("pg6").run();
    model.result("pg6").feature("surf1").feature("mrkr1").set("anchorpoint", "lowerright");
    model.result("pg6").feature("surf1").feature("mrkr1").active(false);
    model.result("pg6").feature("surf1").feature("mrkr1").active(true);
    model.result("pg6").run();

    model.title("\u8868\u9762\u8d34\u7247\u7535\u963b\u7684\u70ed\u75b2\u52b3");

    model
         .description("\u672c\u4f8b\u7814\u7a76\u70ed\u5faa\u73af\u4e0b\u7684\u8868\u9762\u8d34\u7247\u7535\u963b\u3002\u5c06\u7535\u963b\u8fde\u63a5\u5230\u5370\u5237\u7535\u8def\u677f\u7684\u7535\u6781\u710a\u70b9\u662f\u7ec4\u88c5\u4e2d\u6700\u56f0\u96be\u7684\u4e00\u4e2a\u73af\u8282\u3002\u4e3a\u786e\u4fdd\u88c5\u914d\u7ed3\u6784\u7684\u5b8c\u6574\u6027\uff0c\u672c\u4f8b\u6267\u884c\u4e86\u57fa\u4e8e\u8815\u53d8\u5e94\u53d8\u548c\u8017\u6563\u80fd\u7684\u75b2\u52b3\u5206\u6790\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("surface_mount_resistor.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
