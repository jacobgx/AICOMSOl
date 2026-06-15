/*
 * stress_optical.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:15 by COMSOL 6.3.0.290. */
public class stress_optical {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Stress-Optical_Effects");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics().create("ewfd", "ElectromagneticWavesFrequencyDomain", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ewfd", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("nSi", "3.5", "\u6298\u5c04\u7387\uff0c\u7845 (Si)");
    model.param().set("nSiO2", "1.445", "\u6298\u5c04\u7387\uff0c\u4e8c\u6c27\u5316\u7845 (SiO2)");
    model.param().set("deltan", "0.0075", "\u76f8\u5bf9\u6298\u5c04\u7387\u5dee");
    model.param()
         .set("nCore", "nSiO2/sqrt(1-2*deltan)", "\u6298\u5c04\u7387\uff0c\u82af\u5c42\uff08\u63ba\u6742 SiO2\uff09");
    model.param().set("alphaSi", "2.5e-6[1/K]", "\u70ed\u81a8\u80c0\u7cfb\u6570\uff0c\u7845");
    model.param().set("alphaSiO2", "0.35e-6[1/K]", "\u70ed\u81a8\u80c0\u7cfb\u6570\uff0c\u4e8c\u6c27\u5316\u7845");
    model.param().set("ESi", "110[GPa]", "\u6768\u6c0f\u6a21\u91cf\uff0c\u7845");
    model.param().set("ESiO2", "78[GPa]", "\u6768\u6c0f\u6a21\u91cf\uff0c\u4e8c\u6c27\u5316\u7845");
    model.param().set("nuSi", "0.19", "\u6cca\u677e\u6bd4\uff0c\u7845");
    model.param().set("nuSiO2", "0.17", "\u6cca\u677e\u6bd4\uff0c\u4e8c\u6c27\u5316\u7845");
    model.param().set("rhoSi", "2330[kg/m^3]", "\u5bc6\u5ea6\uff0c\u7845");
    model.param().set("rhoSiO2", "2203[kg/m^3]", "\u5bc6\u5ea6\uff0c\u4e8c\u6c27\u5316\u7845");
    model.param().set("B1", "0.65e-12[m^2/N]", "\u7b2c\u4e00\u5e94\u529b\u5149\u5b66\u7cfb\u6570");
    model.param().set("B2", "4.2e-12[m^2/N]", "\u7b2c\u4e8c\u5e94\u529b\u5149\u5b66\u7cfb\u6570");
    model.param().set("T1", "20[degC]", "\u5de5\u4f5c\u6e29\u5ea6");
    model.param().set("T0", "1000[degC]", "\u53c2\u8003\u6e29\u5ea6");
    model.param().set("lambda0_ewfd", "1.55[um]", "\u81ea\u7531\u7a7a\u95f4\u6ce2\u957f");
    model.param().set("d", "2[mm]", "\u9762\u5916\u6269\u5c55\u91cf");
    model.param().rename("d", "para");
    model.param().set("para", "1");
    model.param().descr("para", "1\uff1a\u5149-\u5e94\u529b\u8026\u5408\uff1b0\uff1a\u65e0\u8026\u5408");

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{320, 83});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new int[]{-160, -100});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new int[]{320, 14});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new int[]{-160, -17});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new int[]{320, 16});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new int[]{-160, -3});
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("r4").set("size", new int[]{6, 6});
    model.component("comp1").geom("geom1").feature("r4").set("pos", new int[]{-3, -3});
    model.component("comp1").geom("geom1").run("r4");
    model.component("comp1").geom("geom1").create("r5", "Rectangle");
    model.component("comp1").geom("geom1").feature("r5").set("size", new int[]{20, 20});
    model.component("comp1").geom("geom1").feature("r5").set("pos", new int[]{-10, -10});
    model.component("comp1").geom("geom1").run("r5");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("solid").feature("lemm1").create("te1", "ThermalExpansion", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("te1")
         .set("minput_temperature_src", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").feature("te1").set("minput_temperature", "T1");

    model.common("cminpt").set("modified", new String[][]{{"strainreferencetemperature", "T0"}});

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("N", "nCore");
    model.component("comp1").variable("var1").descr("N", "\u65e0\u5e94\u529b\u6750\u6599\u7684\u6298\u5c04\u7387");
    model.component("comp1").variable("var1").selection().geom("geom1", 2);
    model.component("comp1").variable("var1").selection().set(6);
    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2")
         .set("N", "nSiO2", "\u65e0\u5e94\u529b\u6750\u6599\u7684\u6298\u5c04\u7387");
    model.component("comp1").variable("var2").selection().geom("geom1", 2);
    model.component("comp1").variable("var2").selection().set(4, 5);
    model.component("comp1").variable().create("var3");
    model.component("comp1").variable("var3").set("Nx", "N-para*(B1*solid.sx+B2*(solid.sy+solid.sz))");
    model.component("comp1").variable("var3").descr("Nx", "\u6298\u5c04\u7387\uff0cx \u5206\u91cf");
    model.component("comp1").variable("var3").set("Ny", "N-para*(B1*solid.sy+B2*(solid.sx+solid.sz))");
    model.component("comp1").variable("var3").descr("Ny", "\u6298\u5c04\u7387\uff0cy \u5206\u91cf");
    model.component("comp1").variable("var3").set("Nz", "N-para*(B1*solid.sz+B2*(solid.sx+solid.sy))");
    model.component("comp1").variable("var3").descr("Nz", "\u6298\u5c04\u7387\uff0cz \u5206\u91cf");
    model.component("comp1").variable("var3").selection().geom("geom1", 2);
    model.component("comp1").variable("var3").selection().set(4, 5, 6);

    model.component("comp1").physics("solid").create("rms1", "RigidMotionSuppression", 2);
    model.component("comp1").physics("solid").feature("rms1").selection().set(1);
    model.component("comp1").physics("ewfd").selection().set(4, 5, 6);
    model.component("comp1").physics("ewfd").feature("wee1").set("n_mat", "userdef");
    model.component("comp1").physics("ewfd").feature("wee1")
         .set("n", new String[]{"Nx", "0", "0", "0", "Ny", "0", "0", "0", "Nz"});
    model.component("comp1").physics("ewfd").feature("wee1").set("ki_mat", "userdef");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u7845");
    model.component("comp1").material("mat1").selection().set(1);
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"ESi"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"nuSi"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"rhoSi"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alphaSi"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u4e8c\u6c27\u5316\u7845");
    model.component("comp1").material("mat2").selection().set(2, 3, 4, 5, 6);
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", new String[]{"ESiO2"});
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", new String[]{"nuSiO2"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"rhoSiO2"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alphaSiO2"});

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(4, 5, 6);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", 0.2);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 2);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("mode", "ModeAnalysis");
    model.study("std1").feature("mode").set("transform", "effective_mode_index");
    model.study("std1").feature("mode").set("shift", "1.46");
    model.study("std1").feature("mode").set("neigsactive", true);
    model.study("std1").feature("mode").set("neigs", 4);
    model.study("std1").feature("mode").set("modeFreq", "c_const/lambda0_ewfd");
    model.study("std1").feature("mode").setSolveFor("/physics/solid", false);
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "nSi", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("pname", "nSi", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("pname", "para", 0);
    model.study("std1").feature("param").setIndex("plistarr", "0 1", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").study("std1");
    model.sol("sol3").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol3");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset3");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").setIndex("looplevel", 2, 1);
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
    model.result("pg2").label("\u7535\u573a (ewfd)");
    model.result("pg2").set("data", "dset3");
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").setIndex("looplevel", 2, 1);
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "ewfd.normE");
    model.result("pg2").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg1").set("data", "dset2");
    model.result("pg1").run();

    model.view().create("view2", 2);
    model.view("view2").set("locked", true);
    model.view("view2").axis().set("xmin", -11);
    model.view("view2").axis().set("xmax", 11);
    model.view("view2").axis().set("ymin", -11);
    model.view("view2").axis().set("ymax", 11);

    model.result("pg2").run();
    model.result("pg2").set("view", "view2");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("expr", "ewfd.Poavz");
    model.result("pg2").feature("surf1")
         .set("descr", "\u529f\u7387\u6d41\uff0c\u65f6\u95f4\u5e73\u5747\uff0cz \u5206\u91cf");
    model.result("pg2").run();
    model.result("pg2").set("looplevel", new int[]{1, 1});
    model.result("pg2").run();
    model.result("pg2").set("looplevel", new int[]{2, 1});
    model.result("pg2").run();
    model.result("pg2").set("looplevel", new int[]{3, 1});
    model.result("pg2").run();
    model.result("pg2").set("looplevel", new int[]{4, 1});
    model.result("pg2").run();
    model.result("pg2").set("looplevel", new int[]{1, 2});
    model.result("pg2").run();
    model.result("pg2").set("looplevel", new int[]{2, 2});
    model.result("pg2").run();
    model.result("pg2").set("looplevel", new int[]{3, 2});
    model.result("pg2").run();
    model.result("pg2").set("looplevel", new int[]{4, 2});
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").run();
    model.result("pg3").set("data", "dset2");
    model.result("pg3").set("view", "view2");
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u8868\u9762\uff1aNx-Ny");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "Nx-Ny");
    model.result("pg3").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("data", "dset3");
    model.result().numerical("gev1").set("tablecols", "inner");
    model.result().numerical("gev1").set("expr", new String[]{"ewfd.neff"});
    model.result().numerical("gev1").set("descr", new String[]{"\u6709\u6548\u6a21\u5f0f\u6298\u5c04\u7387"});
    model.result().numerical("gev1").set("unit", new String[]{"1"});
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").create("tblp1", "Table");
    model.result("pg4").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg4").feature("tblp1").set("linewidth", "preference");
    model.result("pg4").feature("tblp1").set("linestyle", "dashed");
    model.result("pg4").feature("tblp1").set("linemarker", "circle");
    model.result("pg4").feature("tblp1").set("markerpos", "interp");
    model.result("pg4").run();
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("title", "\u7279\u5f81\u503c\u504f\u79fb");
    model.result("pg4").set("axislimits", true);
    model.result("pg4").set("xmin", 0);
    model.result("pg4").set("xmax", 1);
    model.result("pg4").set("ymin", 1.443);
    model.result("pg4").set("ymax", 1.453);
    model.result("pg4").set("manualgrid", true);
    model.result("pg4").set("yspacing", "1e-3");
    model.result("pg4").run();
    model.result("pg2").run();

    model.title("\u5149\u5b50\u6ce2\u5bfc\u7684\u5e94\u529b-\u5149\u5b66\u6548\u5e94");

    model
         .description("\u5e94\u529b-\u5149\u5b66\u6548\u5e94\u4f1a\u5728\u5e73\u9762\u5149\u5b50\u6ce2\u5bfc\u4e2d\u5f15\u8d77\u4e0d\u5fc5\u8981\u7684\u53cc\u6298\u5c04\u3002\u672c\u4f8b\u901a\u8fc7\u4f9d\u6b21\u8fdb\u884c\u5e73\u9762\u5e94\u53d8\u5206\u6790\u548c\u5149\u5b66\u6a21\u6001\u5206\u6790\uff0c\u6f14\u793a\u4e86\u57fa\u6a21\u7684\u5206\u79bb\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("stress_optical.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
