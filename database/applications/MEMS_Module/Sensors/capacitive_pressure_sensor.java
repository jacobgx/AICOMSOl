/*
 * capacitive_pressure_sensor.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:02 by COMSOL 6.3.0.290. */
public class capacitive_pressure_sensor {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\MEMS_Module\\Sensors");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics().create("es", "Electrostatics", "geom1");
    model.component("comp1").physics("es").prop("ShapeProperty").set("order_electricpotential", "2m");
    model.component("comp1").physics("es").create("ccns1", "ChargeConservationSolid");
    model.component("comp1").physics("es").feature("ccns1").selection().all();

    model.component("comp1").multiphysics().create("eme1", "ElectromechanicalForces", 3);
    model.component("comp1").multiphysics("eme1").set("Solid_physics", "solid");
    model.component("comp1").multiphysics("eme1").set("Electrostatics_physics", "es");

    model.component("comp1").common().create("free1", "DeformingDomain");
    model.component("comp1").common("free1").set("smoothingType", "hyperelastic");
    model.component("comp1").common("free1").selection().set();
    model.component("comp1").common().create("sym1", "Symmetry");
    model.component("comp1").common("sym1").selection().set();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std1").feature("stat").setSolveFor("/physics/es", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/eme1", true);

    model.component("comp1").geom("geom1").insertFile("capacitive_pressure_sensor_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("difsel1");

    model.param().set("p0", "20[kPa]");
    model.param().descr("p0", "\u538b\u529b");
    model.param().set("T0", "20[degC]");
    model.param().descr("T0", "\u5de5\u4f5c\u6e29\u5ea6");
    model.param().set("Tref", "70[degC]");
    model.param().descr("Tref", "\u82af\u7247\u7c98\u5408\u6e29\u5ea6");

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("aveop1").selection().set(12);
    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop1").selection().set(4);

    model.component("comp1").selection().create("box1", "Box");
    model.component("comp1").selection("box1").set("zmax", "-100[um]");
    model.component("comp1").selection("box1").set("condition", "inside");
    model.component("comp1").selection("box1").label("\u94a2\u5e95\u5ea7");
    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").set(3);
    model.component("comp1").selection("sel1").label("\u8154\u4f53");
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").all();
    model.component("comp1").selection("sel2").label("\u6240\u6709\u57df");
    model.component("comp1").selection().create("dif1", "Difference");
    model.component("comp1").selection("dif1").set("add", new String[]{"sel2"});
    model.component("comp1").selection("dif1").set("subtract", new String[]{"sel1"});
    model.component("comp1").selection("dif1").label("\u5b9e\u4f53");
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").set(3, 4);
    model.component("comp1").selection("sel3").label("\u9759\u7535");

    model.component("comp1").physics("solid").selection().named("dif1");
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 2);
    model.component("comp1").physics("solid").feature("sym1").selection().named("geom1_sel2");
    model.component("comp1").physics("solid").create("sym2", "SymmetrySolid", 2);
    model.component("comp1").physics("solid").feature("sym2").selection().named("geom1_sel1");
    model.component("comp1").physics("solid").create("disp1", "Displacement0", 0);
    model.component("comp1").physics("solid").feature("disp1").selection().set(44);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(13);
    model.component("comp1").physics("solid").feature("bndl1").set("forceType", "FollowerPressure");
    model.component("comp1").physics("solid").feature("bndl1").set("pressure", "p0");

    model.component("comp1").common("free1").selection().named("sel1");
    model.component("comp1").common("sym1").selection().set(7, 8);

    model.component("comp1").physics("es").selection().named("sel3");
    model.component("comp1").physics("es").feature().remove("ccns1");
    model.component("comp1").physics("es").create("term1", "DomainTerminal", 3);
    model.component("comp1").physics("es").feature("term1").selection().set(4);
    model.component("comp1").physics("es").feature("term1").set("TerminalType", "Voltage");
    model.component("comp1").physics("es").create("gnd1", "Ground", 2);
    model.component("comp1").physics("es").feature("gnd1").selection().set(9);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"170[GPa]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.06"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"2330"});
    model.component("comp1").material("mat1").label("\u7845");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").label("Steel AISI 4340");
    model.component("comp1").material("mat2").set("family", "steel");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "205[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.28");
    model.component("comp1").material("mat2").selection().named("box1");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(3, 16, 32);
    model.component("comp1").mesh("mesh1").feature("map1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").selection().set(3);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmax", "50[um]");
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "p0", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "Pa", 0);
    model.study("std1").feature("stat").setIndex("pname", "p0", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "Pa", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,5000,25000)", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 6, 0);
    model.result("pg1").label("\u4f4d\u79fb (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("expr", new String[]{"solid.disp"});
    model.result("pg1").feature("vol1").set("threshold", "manual");
    model.result("pg1").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("vol1").set("colortable", "Rainbow");
    model.result("pg1").feature("vol1").set("colortabletrans", "none");
    model.result("pg1").feature("vol1").set("colorscalemode", "linear");
    model.result("pg1").feature("vol1").set("resolution", "custom");
    model.result("pg1").feature("vol1").set("refine", 2);
    model.result("pg1").feature("vol1").set("colortable", "SpectrumLight");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u7535\u52bf (es)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("solutionparams", "parent");
    model.result("pg2").feature("mslc1").set("expr", "V");
    model.result("pg2").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("mslc1").set("xcoord", "es.CPx");
    model.result("pg2").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("mslc1").set("ycoord", "es.CPy");
    model.result("pg2").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("mslc1").set("zcoord", "es.CPz");
    model.result("pg2").feature("mslc1").set("colortable", "Dipole");
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("data", "parent");
    model.result("pg2").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg2").feature("strmsl1").set("expr", new String[]{"es.Ex", "es.Ey", "es.Ez"});
    model.result("pg2").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("strmsl1").set("xcoord", "es.CPx");
    model.result("pg2").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("strmsl1").set("ycoord", "es.CPy");
    model.result("pg2").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("strmsl1").set("zcoord", "es.CPz");
    model.result("pg2").feature("strmsl1").set("titletype", "none");
    model.result("pg2").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg2").feature("strmsl1").set("udist", 0.02);
    model.result("pg2").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg2").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("inheritcolor", false);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("data", "parent");
    model.result("pg2").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg2").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg2").feature("strmsl1").feature("col1").set("expr", "V");
    model.result("pg2").feature("strmsl1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg2").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg2").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg2").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u7535\u573a (es)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").feature().create("mslc1", "Multislice");
    model.result("pg3").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg3").feature("mslc1").set("solutionparams", "parent");
    model.result("pg3").feature("mslc1").set("expr", "es.normE");
    model.result("pg3").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg3").feature("mslc1").set("xcoord", "es.CPx");
    model.result("pg3").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg3").feature("mslc1").set("ycoord", "es.CPy");
    model.result("pg3").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg3").feature("mslc1").set("zcoord", "es.CPz");
    model.result("pg3").feature("mslc1").set("colortable", "Prism");
    model.result("pg3").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg3").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg3").feature("mslc1").set("data", "parent");
    model.result("pg3").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg3").feature("strmsl1").set("expr", new String[]{"es.Ex", "es.Ey", "es.Ez"});
    model.result("pg3").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg3").feature("strmsl1").set("xcoord", "es.CPx");
    model.result("pg3").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg3").feature("strmsl1").set("ycoord", "es.CPy");
    model.result("pg3").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg3").feature("strmsl1").set("zcoord", "es.CPz");
    model.result("pg3").feature("strmsl1").set("titletype", "none");
    model.result("pg3").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg3").feature("strmsl1").set("udist", 0.02);
    model.result("pg3").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg3").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("inheritcolor", false);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("data", "parent");
    model.result("pg3").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg3").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg3").feature("strmsl1").feature("col1").set("expr", "es.normE");
    model.result("pg3").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg3").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg3").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg3").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg3").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 6, 0);
    model.result("pg4").label("\u52a8\u7f51\u683c");
    model.result("pg4").create("mesh1", "Mesh");
    model.result("pg4").feature("mesh1").set("meshdomain", "volume");
    model.result("pg4").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg4").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg4").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg4").feature("mesh1").feature("sel1").selection().set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    model.result("pg4").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg4").feature("mesh1").set("qualexpr", "comp1.spatial.relVol");
    model.result("pg4").feature("mesh1").set("colorrangeunitinterval", false);
    model.result("pg1").run();
    model.result().dataset("dset1").selection().geom("geom1", 3);
    model.result().dataset("dset1").selection().named("sel3");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").feature().remove("strmsl1");
    model.result("pg2").run();
    model.result("pg2").feature("mslc1").set("multiplanexmethod", "number");
    model.result("pg2").feature("mslc1").set("xnumber", "0");
    model.result("pg2").feature("mslc1").set("multiplaneymethod", "number");
    model.result("pg2").feature("mslc1").set("ynumber", "0");
    model.result("pg2").feature("mslc1").set("zcoord", "-0.0023[mm]");
    model.result("pg2").feature("mslc1").set("rangecoloractive", true);
    model.result("pg2").feature("mslc1").create("sel1", "Selection");
    model.result("pg2").feature("mslc1").feature("sel1").selection().named("sel1");
    model.result("pg2").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").setIndex("expr", "intop1(w)", 0);
    model.result("pg5").feature("glob1").setIndex("unit", "um", 0);
    model.result("pg5").feature("glob1").setIndex("descr", "\u6700\u5927\u4f4d\u79fb", 0);
    model.result("pg5").feature("glob1").setIndex("expr", "aveop1(w)", 1);
    model.result("pg5").feature("glob1").setIndex("unit", "um", 1);
    model.result("pg5").feature("glob1").setIndex("descr", "\u5e73\u5747\u4f4d\u79fb", 1);
    model.result("pg5").run();
    model.result("pg5").set("titletype", "manual");
    model.result("pg5").set("title", "\u9694\u819c\u4f4d\u79fb");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "\u538b\u529b (Pa)");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u4f4d\u79fb (\\mu m)");
    model.result("pg5").set("legendpos", "lowerleft");
    model.result("pg5").label("\u9694\u819c\u4f4d\u79fb vs. \u538b\u529b");
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").set("expr", new String[]{"es.C11"});
    model.result("pg6").feature("glob1").set("descr", new String[]{"\u9ea6\u514b\u65af\u97e6\u7535\u5bb9"});
    model.result("pg6").feature("glob1").set("unit", new String[]{"F"});
    model.result("pg6").feature("glob1").setIndex("unit", "pF", 0);
    model.result("pg6").feature("glob1").setIndex("descr", "\u7535\u5bb9", 0);
    model.result("pg6").feature("glob1").setIndex("expr", "0.738[pF]*(1+8.87e-6[1/Pa]*p0)", 1);
    model.result("pg6").feature("glob1").setIndex("unit", "pF", 1);
    model.result("pg6").feature("glob1").setIndex("descr", "\u7ebf\u6027\u5316\u89e3\u6790\u7535\u5bb9", 1);
    model.result("pg6").run();
    model.result("pg6").set("titletype", "manual");
    model.result("pg6").set("title", "\u6a21\u578b\u7535\u5bb9 vs. \u538b\u529b");
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("xlabel", "\u538b\u529b (Pa)");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "\u7535\u5bb9 (pF)");
    model.result("pg6").set("legendpos", "upperleft");
    model.result("pg6").label("\u6a21\u578b\u7535\u5bb9 vs. \u538b\u529b");
    model.result("pg6").run();

    model.component("comp1").physics("solid").feature("lemm1").create("te1", "ThermalExpansion", 3);
    model.component("comp1").physics("solid").feature("lemm1").feature("te1")
         .set("minput_temperature_src", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").feature("te1").set("minput_temperature", "T0");

    model.common("cminpt").set("modified", new String[][]{{"strainreferencetemperature", "Tref"}});

    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"2.6e-6"});

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std2").feature("stat").setSolveFor("/physics/es", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/eme1", true);
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "p0", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "Pa", 0);
    model.study("std2").feature("stat").setIndex("pname", "p0", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "Pa", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "range(0,5000,25000)", 0);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().create("mir1", "Mirror3D");
    model.result().dataset("mir1").set("data", "dset2");
    model.result("pg1").run();
    model.result().duplicate("pg7", "pg1");
    model.result("pg7").run();
    model.result("pg7").set("data", "mir1");
    model.result("pg7").run();
    model.result("pg5").run();
    model.result("pg5").feature().duplicate("glob2", "glob1");
    model.result("pg5").run();
    model.result("pg5").feature("glob2").set("data", "dset2");
    model.result("pg5").feature("glob2")
         .setIndex("descr", "\u5c01\u88c5\u5e94\u529b\u4f5c\u7528\u4e0b\u7684\u6700\u5927\u4f4d\u79fb", 0);
    model.result("pg5").feature("glob2").setIndex("expr", "", 1);
    model.result("pg5").feature("glob2").setIndex("descr", "", 1);
    model.result("pg5").run();
    model.result("pg6").run();
    model.result("pg6").create("glob2", "Global");
    model.result("pg6").feature("glob2").set("markerpos", "datapoints");
    model.result("pg6").feature("glob2").set("linewidth", "preference");
    model.result("pg6").feature("glob2").set("data", "dset2");
    model.result("pg6").feature("glob2").set("expr", new String[]{"es.C11"});
    model.result("pg6").feature("glob2").set("descr", new String[]{"\u9ea6\u514b\u65af\u97e6\u7535\u5bb9"});
    model.result("pg6").feature("glob2").set("unit", new String[]{"F"});
    model.result("pg6").feature("glob2").setIndex("unit", "pF", 0);
    model.result("pg6").feature("glob2")
         .setIndex("descr", "\u5c01\u88c5\u5e94\u529b\u4f5c\u7528\u4e0b\u7684\u7535\u5bb9", 0);
    model.result("pg6").run();

    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std3").feature("stat").setSolveFor("/physics/es", true);
    model.study("std3").feature("stat").setSolveFor("/multiphysics/eme1", true);
    model.study("std3").feature("stat").set("useparam", true);
    model.study("std3").feature("stat").setIndex("pname", "p0", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "Pa", 0);
    model.study("std3").feature("stat").setIndex("pname", "p0", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "Pa", 0);
    model.study("std3").feature("stat").setIndex("pname", "T0", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "range(290,5,300)", 0);
    model.study("std3").setGenPlots(false);
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").set("data", "dset3");
    model.result("pg8").create("glob1", "Global");
    model.result("pg8").feature("glob1").set("markerpos", "datapoints");
    model.result("pg8").feature("glob1").set("linewidth", "preference");
    model.result("pg8").feature("glob1").set("expr", new String[]{"es.C11"});
    model.result("pg8").feature("glob1").set("descr", new String[]{"\u9ea6\u514b\u65af\u97e6\u7535\u5bb9"});
    model.result("pg8").feature("glob1").set("unit", new String[]{"F"});
    model.result("pg8").feature("glob1").setIndex("unit", "pF", 0);
    model.result("pg8").feature("glob1").setIndex("descr", "\u7535\u5bb9", 0);
    model.result("pg8").run();
    model.result("pg8").set("titletype", "manual");
    model.result("pg8").set("title", "\u6a21\u578b\u7535\u5bb9 vs. \u5de5\u4f5c\u6e29\u5ea6");
    model.result("pg8").set("xlabelactive", true);
    model.result("pg8").set("xlabel", "\u5de5\u4f5c\u6e29\u5ea6 (K)");
    model.result("pg8").label("\u7535\u5bb9 vs. \u5de5\u4f5c\u6e29\u5ea6");
    model.result("pg8").run();
    model.result("pg7").run();

    model.title("\u7535\u5bb9\u5f0f\u538b\u529b\u4f20\u611f\u5668");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u4e00\u79cd\u7535\u5bb9\u5f0f\u538b\u529b\u4f20\u611f\u5668\uff0c\u663e\u793a\u5982\u4f55\u6a21\u62df\u538b\u529b\u4f20\u611f\u5668\u5728\u5916\u52a0\u538b\u529b\u4f5c\u7528\u4e0b\u7684\u54cd\u5e94\uff0c\u4ee5\u53ca\u5982\u4f55\u5206\u6790\u5c01\u88c5\u8bf1\u5bfc\u5e94\u529b\u5bf9\u4f20\u611f\u5668\u6027\u80fd\u7684\u5f71\u54cd\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("capacitive_pressure_sensor.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
