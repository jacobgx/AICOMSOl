/*
 * condensation_electronic_device.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:26 by COMSOL 6.3.0.290. */
public class condensation_electronic_device {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Power_Electronics_and_Electronic_Cooling");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ht", "HeatTransferInSolidsAndFluids", "geom1");
    model.component("comp1").physics("ht").prop("PhysicalModelProperty").set("dz", "1[m]");
    model.component("comp1").physics("ht").prop("ShapeProperty").set("order_temperature", "1");
    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics("spf").prop("AdvancedSettingProperty").set("UsePseudoTime", "1");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "WeaklyCompressible");

    model.component("comp1").multiphysics().create("nitf1", "NonIsothermalFlow", 2);
    model.component("comp1").multiphysics("nitf1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("nitf1").set("Heat_physics", "ht");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/ht", true);
    model.study("std1").feature("time").setSolveFor("/physics/spf", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/nitf1", true);

    model.component("comp1").geom("geom1").insertFile("condensation_electronic_device_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("sel1");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat1").label("Aluminum");
    model.component("comp1").material("mat1").set("family", "aluminum");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23e-6[1/K]", "0", "0", "0", "23e-6[1/K]", "0", "0", "0", "23e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "70[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("l", "-250[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("m", "-330[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("n", "-350[GPa]");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat2").label("Silica glass");
    model.component("comp1").material("mat2").set("family", "custom");
    model.component("comp1").material("mat2").set("diffuse", "custom");
    model.component("comp1").material("mat2").set("ambient", "custom");
    model.component("comp1").material("mat2").set("noise", true);
    model.component("comp1").material("mat2").set("fresnel", 0.99);
    model.component("comp1").material("mat2").set("roughness", 0.02);
    model.component("comp1").material("mat2").set("diffusewrap", 0);
    model.component("comp1").material("mat2").set("reflectance", 0);
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e-14[S/m]", "0", "0", "0", "1e-14[S/m]", "0", "0", "0", "1e-14[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"0.55e-6[1/K]", "0", "0", "0", "0.55e-6[1/K]", "0", "0", "0", "0.55e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "703[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"3.75", "0", "0", "0", "3.75", "0", "0", "0", "3.75"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "2203[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.38[W/(m*K)]", "0", "0", "0", "1.38[W/(m*K)]", "0", "0", "0", "1.38[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "73.1[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.17");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1.45", "0", "0", "0", "1.45", "0", "0", "0", "1.45"});
    model.component("comp1").material("mat1").selection().set(1, 3);
    model.component("comp1").material("mat2").selection().set(4);

    model.component("comp1").common().create("ampr1", "AmbientProperties");
    model.component("comp1").common("ampr1").set("AmbientData", "MeteorologicalData2021");
    model.component("comp1").common("ampr1").setIndex("ashrae2021LocalTime", 0, 0);
    model.component("comp1").common("ampr1").set("ashrae2021Temperature", "Low");

    model.component("comp1").physics("ht").create("ma1", "MoistAirHeatTransferModel", 2);
    model.component("comp1").physics("ht").feature("ma1").selection().set(2);
    model.component("comp1").physics("ht").feature("ma1").set("phi_cond_match_src", "root.comp1.ampr1.phi_amb");
    model.component("comp1").physics("ht").feature("ma1").set("T_cond_match_src", "root.comp1.ampr1.T_amb");
    model.component("comp1").physics("ht").feature("ma1").set("p_cond_match_src", "root.comp1.ampr1.p_amb");
    model.component("comp1").physics("ht").feature("init1").set("Tinit_src", "root.comp1.ampr1.T_amb");
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 1);
    model.component("comp1").physics("ht").feature("hf1").selection().set(1, 2, 5, 7, 21, 23);
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", "10[W/(m^2*K)]");
    model.component("comp1").physics("ht").feature("hf1").set("Text_src", "root.comp1.ampr1.T_amb");
    model.component("comp1").physics("ht").create("hs1", "HeatSource", 2);
    model.component("comp1").physics("ht").feature("hs1").selection().named("geom1_r1_dom");
    model.component("comp1").physics("ht").feature("hs1").set("heatSourceType", "HeatRate");
    model.component("comp1").physics("ht").feature("hs1").set("P0", "1[W]");
    model.component("comp1").physics("ht").create("open1", "OpenBoundary", 1);
    model.component("comp1").physics("ht").feature("open1").selection().set(3, 22);
    model.component("comp1").physics("ht").feature("open1").set("Tustr_src", "root.comp1.ampr1.T_amb");
    model.component("comp1").physics("spf").selection().named("geom1_sel1");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("IncludeGravity", true);
    model.component("comp1").physics("spf").feature("fp1").set("mu_mat", "root.comp1.ht.mu_moist");
    model.component("comp1").physics("spf").feature("init1").set("p_init", "ampr1.p_amb-spf.pref");
    model.component("comp1").physics("spf").create("open1", "OpenBoundary", 1);
    model.component("comp1").physics("spf").feature("open1").selection().set(3, 22);
    model.component("comp1").physics("spf").feature("open1").set("f0", "ampr1.p_amb-spf.pref");

    model.component("comp1").probe().create("dom1", "Domain");
    model.component("comp1").probe("dom1").set("intsurface", true);
    model.component("comp1").probe("dom1").set("intvolume", true);
    model.component("comp1").probe("dom1").selection().set();
    model.component("comp1").probe("dom1").selection().named("geom1_sel1");
    model.component("comp1").probe("dom1").set("type", "maximum");
    model.component("comp1").probe("dom1").set("expr", "ht.phi");
    model.component("comp1").probe("dom1").set("descr", "\u76f8\u5bf9\u6e7f\u5ea6");
    model.component("comp1").probe().create("dom2", "Domain");
    model.component("comp1").probe("dom2").set("intsurface", true);
    model.component("comp1").probe("dom2").set("intvolume", true);
    model.component("comp1").probe("dom2").selection().set();
    model.component("comp1").probe("dom2").selection().named("geom1_sel1");
    model.component("comp1").probe("dom2").set("type", "maximum");
    model.component("comp1").probe("dom2").set("expr", "ht.satInd");
    model.component("comp1").probe("dom2").set("descr", "\u9971\u548c\u6307\u793a\u5668");

    model.study("std1").feature("time").set("tunit", "h");
    model.study("std1").feature("time").set("tlist", "range(0,0.5,24)");
    model.study("std1").feature("time").set("usertol", true);
    model.study("std1").feature("time").set("rtol", "0.0005");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("maxstepconstraintbdf", "const");
    model.sol("sol1").feature("t1").set("maxstepbdf", 0.25);

    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("dom1").genResult("none");
    model.component("comp1").probe("dom2").genResult("none");

    model.sol("sol1").runAll();

    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u6e29\u5ea6 (ht)");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("solutionparams", "parent");
    model.result("pg2").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u901f\u5ea6 (spf)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u8868\u9762");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("expr", "spf.U");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u538b\u529b (spf)");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").feature().create("con1", "Contour");
    model.result("pg4").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg4").feature("con1").set("showsolutionparams", "on");
    model.result("pg4").feature("con1").set("expr", "p");
    model.result("pg4").feature("con1").set("number", 40);
    model.result("pg4").feature("con1").set("levelrounding", false);
    model.result("pg4").feature("con1").set("smooth", "internal");
    model.result("pg4").feature("con1").set("showsolutionparams", "on");
    model.result("pg4").feature("con1").set("data", "parent");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").label("\u6e29\u5ea6\u548c\u6d41\u4f53\u6d41\u52a8 (nitf1)");
    model.result("pg5").set("showlegendsunit", true);
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").label("\u6d41\u4f53\u6e29\u5ea6");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("solutionparams", "parent");
    model.result("pg5").feature("surf1").set("expr", "nitf1.T");
    model.result("pg5").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg5").feature("surf1").set("smooth", "internal");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result("pg5").feature("surf1").feature().create("sel1", "Selection");
    model.result("pg5").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg5").feature("surf1").feature("sel1").selection().set(2);
    model.result("pg5").feature().create("surf2", "Surface");
    model.result("pg5").feature("surf2").label("\u56fa\u4f53\u6e29\u5ea6");
    model.result("pg5").feature("surf2").set("showsolutionparams", "on");
    model.result("pg5").feature("surf2").set("solutionparams", "parent");
    model.result("pg5").feature("surf2").set("expr", "nitf1.T");
    model.result("pg5").feature("surf2").set("smooth", "internal");
    model.result("pg5").feature("surf2").set("showsolutionparams", "on");
    model.result("pg5").feature("surf2").set("data", "parent");
    model.result("pg5").feature("surf2").feature().create("sel1", "Selection");
    model.result("pg5").feature("surf2").feature("sel1").selection().geom("geom1", 2);
    model.result("pg5").feature("surf2").feature("sel1").selection().set(1, 3, 4);
    model.result("pg5").feature("surf2").set("inheritplot", "surf1");
    model.result("pg5").feature().create("arws1", "ArrowSurface");
    model.result("pg5").feature("arws1").label("\u6d41\u4f53\u6d41\u52a8");
    model.result("pg5").feature("arws1").set("showsolutionparams", "on");
    model.result("pg5").feature("arws1").set("solutionparams", "parent");
    model.result("pg5").feature("arws1").set("expr", new String[]{"nitf1.ux", "nitf1.uy"});
    model.result("pg5").feature("arws1").set("xnumber", 30);
    model.result("pg5").feature("arws1").set("ynumber", 30);
    model.result("pg5").feature("arws1").set("arrowtype", "cone");
    model.result("pg5").feature("arws1").set("arrowlength", "logarithmic");
    model.result("pg5").feature("arws1").set("showsolutionparams", "on");
    model.result("pg5").feature("arws1").set("data", "parent");
    model.result("pg5").feature("arws1").feature().create("col1", "Color");
    model.result("pg5").feature("arws1").feature("col1").set("showcolordata", "off");
    model.result("pg5").feature("arws1").feature("col1").set("expr", "spf.U");
    model.result("pg5").feature("arws1").feature().create("filt1", "Filter");
    model.result("pg5").feature("arws1").feature("filt1").set("expr", "spf.U>nitf1.Uave");
    model.result("pg2").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"temperature", "\u6e29\u5ea6", "K", "K"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "\u00b0F", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result("pg2").run();
    model.result("pg3").run();
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").run();
    model.result("pg6").label("\u76f8\u5bf9\u6e7f\u5ea6");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", "ht.phi");
    model.result("pg6").feature("surf1").set("descr", "\u76f8\u5bf9\u6e7f\u5ea6");
    model.result("pg6").feature("surf1").set("colortable", "JupiterAuroraBorealis");
    model.result("pg6").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg6").run();
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").label("\u6700\u5927\u76f8\u5bf9\u6e7f\u5ea6");
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").feature("tblp1").set("legendmethod", "manual");
    model.result("pg1").feature("tblp1").setIndex("legends", "\u6700\u5927\u76f8\u5bf9\u6e7f\u5ea6", 0);
    model.result("pg1").feature("tblp1").setIndex("legends", "\u9971\u548c\u6307\u793a\u5668", 1);
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").set("legendpos", "lowerright");
    model.result("pg1").set("window", "graphics");
    model.result("pg1").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u73af\u5883\u6e29\u5ea6");
    model.result("pg7").set("titletype", "manual");
    model.result("pg7").set("title", "ampr1.T_amb (\u00b0F)");
    model.result("pg7").create("ptgr1", "PointGraph");
    model.result("pg7").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg7").feature("ptgr1").set("linewidth", "preference");
    model.result("pg7").feature("ptgr1").selection().set(10);
    model.result("pg7").feature("ptgr1").set("expr", "ampr1.T_amb");
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").label("\u73af\u5883\u76f8\u5bf9\u6e7f\u5ea6");
    model.result("pg8").set("titletype", "manual");
    model.result("pg8").set("title", "ampr1.phi_amb (1)");
    model.result("pg8").create("ptgr1", "PointGraph");
    model.result("pg8").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg8").feature("ptgr1").set("linewidth", "preference");
    model.result("pg8").feature("ptgr1").selection().set(10);
    model.result("pg8").feature("ptgr1").set("expr", "ampr1.phi_amb");
    model.result("pg8").run();
    model.result("pg2").run();

    model.title("\u7535\u5b50\u8bbe\u5907\u4e2d\u7684\u51b7\u51dd\u68c0\u6d4b");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u7535\u5b50\u76d2\u4e2d\u6e7f\u7a7a\u6c14\u7684\u70ed\u529b\u5b66\u6f14\u5316\uff0c\u76ee\u7684\u5728\u4e8e\u68c0\u6d4b\u5f53\u5916\u90e8\u73af\u5883\u5c5e\u6027\u53d1\u751f\u53d8\u5316\u65f6\u662f\u5426\u4f1a\u53d1\u751f\u51b7\u51dd\u3002\u6a21\u578b\u4e2d\u5bfc\u5165\u4e86\u7a7a\u6c14\u6e29\u5ea6\u3001\u538b\u529b\u548c\u6c34\u84b8\u6c14\u6d53\u5ea6\u7b49\u6d4b\u91cf\u6570\u636e\uff0c\u5176\u4e2d\u5047\u8bbe\u76d2\u5185\u7684\u6c34\u84b8\u6c14\u6d53\u5ea6\u662f\u5747\u5300\u7684\uff0c\u7b49\u4e8e\u5916\u90e8\u6d53\u5ea6\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("condensation_electronic_device.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
