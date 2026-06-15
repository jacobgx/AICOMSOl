/*
 * microresistor_beam.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:55 by COMSOL 6.3.0.290. */
public class microresistor_beam {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\MEMS_Module\\Actuators");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics("solid").prop("StructuralTransientBehavior")
         .set("StructuralTransientBehavior", "Quasistatic");
    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");
    model.component("comp1").physics().create("ec", "ConductiveMedia", "geom1");

    model.component("comp1").multiphysics().create("te1", "ThermalExpansion", 3);
    model.component("comp1").multiphysics("te1").set("Heat_physics", "ht");
    model.component("comp1").multiphysics("te1").set("Solid_physics", "solid");
    model.component("comp1").multiphysics("te1").selection().all();
    model.component("comp1").multiphysics().create("emh1", "ElectromagneticHeating", 3);
    model.component("comp1").multiphysics("emh1").set("EMHeat_physics", "ec");
    model.component("comp1").multiphysics("emh1").set("Heat_physics", "ht");
    model.component("comp1").multiphysics("emh1").selection().all();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ec", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/te1", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/emh1", true);

    model.param().set("V0", "0.2[V]");
    model.param().descr("V0", "\u5916\u52a0\u7535\u538b");
    model.param().set("T0", "323[K]");
    model.param().descr("T0", "\u6563\u70ed\u5668\u6e29\u5ea6");
    model.param().set("Text", "298[K]");
    model.param().descr("Text", "\u5916\u90e8\u6e29\u5ea6");
    model.param().set("k", "5[W/(m^2*K)]");
    model.param().descr("k", "\u4f20\u70ed\u7cfb\u6570");

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .set("x", "0 5 5 18 18 23 23 23 23 18 18 5 5 0 0 0");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .set("y", "0 1.5 1.5 1.5 1.5 0 0 4 4 2.5 2.5 2.5 2.5 4 4 0");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", 3, 0);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp2").selection("face").set("ext1", 6);
    model.component("comp1").geom("geom1").feature("wp2").set("offset", -1.5);
    model.component("comp1").geom("geom1").feature("wp2").set("reverse", true);
    model.component("comp1").geom("geom1").feature("wp2").set("showintersection", false);
    model.component("comp1").geom("geom1").feature("wp2").set("showcoincident", false);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1")
         .set("x", "-11.5 -6.3 -6.3 -6.3 -6.3 6.3 6.3 6.3 6.3 11.5 11.5 6.5 6.5 -6.5 -6.5 -11.5");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1")
         .set("y", "-1.5 -1.5 -1.5 0.5 0.5 0.5 0.5 -1.5 -1.5 -1.5 -1.5 1.5 1.5 1.5 1.5 -1.5");
    model.component("comp1").geom("geom1").feature("wp2").geom().run("pol1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("fil1").selection("point")
         .set("pol1", 4, 6);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("fil1").set("radius", 0.3);
    model.component("comp1").geom("geom1").feature("wp2").geom().run("fil1");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").feature().create("ext2", "Extrude");
    model.component("comp1").geom("geom1").feature("ext2").set("workplane", "wp2");
    model.component("comp1").geom("geom1").feature("ext2").selection("input").set("wp2");
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", 4, 0);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("int1", "Intersection");
    model.component("comp1").geom("geom1").feature("int1").selection("input").set("ext1", "ext2");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u8fde\u63a5\u5668 1");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(1);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u8fde\u63a5\u5668 2");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set(13);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u8fde\u63a5\u5668");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3").set(1, 13);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").label("Cu - Copper");
    model.component("comp1").material("mat1").set("family", "copper");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"58.1e6[S/m]", "0", "0", "0", "58.1e6[S/m]", "0", "0", "0", "58.1e6[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"16.5e-6[1/K]", "0", "0", "0", "16.5e-6[1/K]", "0", "0", "0", "16.5e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "384[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "8960[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"401[W/(m*K)]", "0", "0", "0", "401[W/(m*K)]", "0", "0", "0", "401[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "120e9[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.34");
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup().create("linzRes", "linzRes", "Linearized_resistivity");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("rho0", new String[]{"1.72e-8[ohm*m]"});
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("alpha", new String[]{"0.0039[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("Tref", new String[]{"293[K]"});

    model.component("comp1").physics("ec").feature("cucn1").set("ConstitutiveRelationJcE", "LinearizedResistivity");
    model.component("comp1").physics("ec").feature("cucn1").set("alpha_mat", "userdef");
    model.component("comp1").physics("ec").create("gnd1", "Ground", 2);
    model.component("comp1").physics("ec").feature("gnd1").selection().named("sel2");
    model.component("comp1").physics("ec").create("pot1", "ElectricPotential", 2);
    model.component("comp1").physics("ec").feature("pot1").set("V0", "V0");
    model.component("comp1").physics("ec").feature("pot1").selection().named("sel1");

    model.common("cminpt").set("modified", new String[][]{{"strainreferencetemperature", "Text"}});

    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T0");
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf1").selection().all();
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", "k");
    model.component("comp1").physics("ht").feature("hf1").set("Text", "Text");
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 2);
    model.component("comp1").physics("ht").feature("temp1").set("T0", "T0");
    model.component("comp1").physics("ht").feature("temp1").selection().named("sel3");
    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().named("sel3");

    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 3);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("vol1").set("threshold", "manual");
    model.result("pg1").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("vol1").set("colortable", "Rainbow");
    model.result("pg1").feature("vol1").set("colortabletrans", "none");
    model.result("pg1").feature("vol1").set("colorscalemode", "linear");
    model.result("pg1").feature("vol1").set("resolution", "custom");
    model.result("pg1").feature("vol1").set("refine", 2);
    model.result("pg1").feature("vol1").set("colortable", "Prism");
    model.result("pg1").feature("vol1").create("def", "Deform");
    model.result("pg1").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u6e29\u5ea6 (ht)");
    model.result("pg2").feature().create("vol1", "Volume");
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("solutionparams", "parent");
    model.result("pg2").feature("vol1").set("expr", "T");
    model.result("pg2").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg2").feature("vol1").set("smooth", "internal");
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("data", "parent");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u7535\u52bf (ec)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").feature().create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("solutionparams", "parent");
    model.result("pg3").feature("vol1").set("expr", "V");
    model.result("pg3").feature("vol1").set("colortable", "Dipole");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("data", "parent");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u7535\u573a (ec)");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").feature().create("mslc1", "Multislice");
    model.result("pg4").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg4").feature("mslc1").set("solutionparams", "parent");
    model.result("pg4").feature("mslc1").set("expr", "ec.normE");
    model.result("pg4").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg4").feature("mslc1").set("xcoord", "ec.CPx");
    model.result("pg4").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg4").feature("mslc1").set("ycoord", "ec.CPy");
    model.result("pg4").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg4").feature("mslc1").set("zcoord", "ec.CPz");
    model.result("pg4").feature("mslc1").set("colortable", "Prism");
    model.result("pg4").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg4").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg4").feature("mslc1").set("data", "parent");
    model.result("pg4").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg4").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg4").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg4").feature("strmsl1").set("expr", new String[]{"ec.Ex", "ec.Ey", "ec.Ez"});
    model.result("pg4").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg4").feature("strmsl1").set("xcoord", "ec.CPx");
    model.result("pg4").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg4").feature("strmsl1").set("ycoord", "ec.CPy");
    model.result("pg4").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg4").feature("strmsl1").set("zcoord", "ec.CPz");
    model.result("pg4").feature("strmsl1").set("titletype", "none");
    model.result("pg4").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg4").feature("strmsl1").set("udist", 0.02);
    model.result("pg4").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg4").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg4").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("strmsl1").set("inheritcolor", false);
    model.result("pg4").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg4").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg4").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg4").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg4").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("strmsl1").set("data", "parent");
    model.result("pg4").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg4").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg4").feature("strmsl1").feature("col1").set("expr", "ec.normE");
    model.result("pg4").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg4").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg4").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg4").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg4").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg1").run();
    model.result("pg1").label("\u4f4d\u79fb - \u7814\u7a76 1");
    model.result("pg1").run();
    model.result("pg1").feature("vol1").set("expr", "solid.disp");
    model.result("pg1").feature("vol1").set("descr", "\u4f4d\u79fb\u5927\u5c0f");
    model.result("pg1").feature("vol1").set("unit", "nm");
    model.result("pg1").run();
    model.result("pg2").run();

    model.component("comp1").physics("ec").feature("cucn1").set("alpha_mat", "from_mat");

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std2").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std2").feature("stat").setSolveFor("/physics/ec", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/te1", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/emh1", true);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").label("\u5e94\u529b (solid)");
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").create("vol1", "Volume");
    model.result("pg5").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg5").feature("vol1").set("threshold", "manual");
    model.result("pg5").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg5").feature("vol1").set("colortable", "Rainbow");
    model.result("pg5").feature("vol1").set("colortabletrans", "none");
    model.result("pg5").feature("vol1").set("colorscalemode", "linear");
    model.result("pg5").feature("vol1").set("resolution", "custom");
    model.result("pg5").feature("vol1").set("refine", 2);
    model.result("pg5").feature("vol1").set("colortable", "Prism");
    model.result("pg5").feature("vol1").create("def", "Deform");
    model.result("pg5").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg5").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").label("\u6e29\u5ea6 (ht) 1");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").feature().create("vol1", "Volume");
    model.result("pg6").feature("vol1").set("showsolutionparams", "on");
    model.result("pg6").feature("vol1").set("solutionparams", "parent");
    model.result("pg6").feature("vol1").set("expr", "T");
    model.result("pg6").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg6").feature("vol1").set("smooth", "internal");
    model.result("pg6").feature("vol1").set("showsolutionparams", "on");
    model.result("pg6").feature("vol1").set("data", "parent");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").label("\u7535\u52bf (ec) 1");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").set("frametype", "spatial");
    model.result("pg7").set("showlegendsmaxmin", true);
    model.result("pg7").feature().create("vol1", "Volume");
    model.result("pg7").feature("vol1").set("showsolutionparams", "on");
    model.result("pg7").feature("vol1").set("solutionparams", "parent");
    model.result("pg7").feature("vol1").set("expr", "V");
    model.result("pg7").feature("vol1").set("colortable", "Dipole");
    model.result("pg7").feature("vol1").set("showsolutionparams", "on");
    model.result("pg7").feature("vol1").set("data", "parent");
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").label("\u7535\u573a (ec) 1");
    model.result("pg8").set("data", "dset2");
    model.result("pg8").set("frametype", "spatial");
    model.result("pg8").set("showlegendsmaxmin", true);
    model.result("pg8").feature().create("mslc1", "Multislice");
    model.result("pg8").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg8").feature("mslc1").set("solutionparams", "parent");
    model.result("pg8").feature("mslc1").set("expr", "ec.normE");
    model.result("pg8").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg8").feature("mslc1").set("xcoord", "ec.CPx");
    model.result("pg8").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg8").feature("mslc1").set("ycoord", "ec.CPy");
    model.result("pg8").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg8").feature("mslc1").set("zcoord", "ec.CPz");
    model.result("pg8").feature("mslc1").set("colortable", "Prism");
    model.result("pg8").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg8").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg8").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg8").feature("mslc1").set("data", "parent");
    model.result("pg8").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg8").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg8").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg8").feature("strmsl1").set("expr", new String[]{"ec.Ex", "ec.Ey", "ec.Ez"});
    model.result("pg8").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg8").feature("strmsl1").set("xcoord", "ec.CPx");
    model.result("pg8").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg8").feature("strmsl1").set("ycoord", "ec.CPy");
    model.result("pg8").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg8").feature("strmsl1").set("zcoord", "ec.CPz");
    model.result("pg8").feature("strmsl1").set("titletype", "none");
    model.result("pg8").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg8").feature("strmsl1").set("udist", 0.02);
    model.result("pg8").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg8").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg8").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg8").feature("strmsl1").set("inheritcolor", false);
    model.result("pg8").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg8").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg8").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg8").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg8").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg8").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg8").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg8").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg8").feature("strmsl1").set("data", "parent");
    model.result("pg8").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg8").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg8").feature("strmsl1").feature("col1").set("expr", "ec.normE");
    model.result("pg8").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg8").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg8").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg8").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg8").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg8").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg5").run();
    model.result("pg6").run();
    model.result("pg5").run();
    model.result("pg5").label("\u4f4d\u79fb - \u7814\u7a76 2");
    model.result("pg5").run();
    model.result("pg5").feature("vol1").set("expr", "solid.disp");
    model.result("pg5").feature("vol1").set("descr", "\u4f4d\u79fb\u5927\u5c0f");
    model.result("pg5").feature("vol1").set("unit", "nm");
    model.result("pg5").run();

    model.title("\u5fae\u7535\u963b\u6881");

    model
         .description("\u672c\u6a21\u578b\u901a\u8fc7\u5728\u6881\u4e2d\u5bfc\u5165\u7535\u6d41\u4f7f\u5176\u5347\u6e29\uff0c\u4ece\u800c\u7814\u7a76\u7531\u6b64\u5f15\u8d77\u7684\u6881\u7684\u8fd0\u52a8\u60c5\u51b5\u3002\u8fd9\u5c31\u662f\u901a\u8fc7\u70ed\u81a8\u80c0\u5f15\u8d77\u4f4d\u79fb\u3002\u672c\u4f8b\u6267\u884c\u70ed\u3001\u7535\u548c\u7ed3\u6784\u7684\u8026\u5408\u5206\u6790\uff0c\u4ece\u800c\u4f30\u8ba1\u4f7f\u6881\u4ea7\u751f\u53d8\u5f62\u6240\u9700\u7684\u7535\u6d41\u548c\u6e29\u5347\u5927\u5c0f\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("microresistor_beam.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
