/*
 * capacitive_position_sensor.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:14 by COMSOL 6.3.0.290. */
public class capacitive_position_sensor {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Devices,_Capacitive");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("es", "Electrostatics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stssw", "StationarySourceSweep");
    model.study("std1").feature("stssw").set("solnum", "auto");
    model.study("std1").feature("stssw").set("notsolnum", "auto");
    model.study("std1").feature("stssw").set("outputmap", new String[]{});
    model.study("std1").feature("stssw").set("ngenAUX", "1");
    model.study("std1").feature("stssw").set("goalngenAUX", "1");
    model.study("std1").feature("stssw").set("ngenAUX", "1");
    model.study("std1").feature("stssw").set("goalngenAUX", "1");
    model.study("std1").feature("stssw").setSolveFor("/physics/es", true);

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"11[cm]", "11[cm]", "1"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "1[cm]", 2);
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickz", "5[mm]");
    model.component("comp1").geom("geom1").feature("wp1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"1[cm]", "11[cm]"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("pos", new String[]{"1[cm]", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1").set("fullsize", new int[]{5, 1});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1")
         .set("displ", new String[]{"2[cm]", "0"});
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"60[cm]", "18[cm]", "1"});
    model.component("comp1").geom("geom1").feature("blk2").setIndex("size", "18[cm]", 2);
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new String[]{"-40[cm]", "-4[cm]", "0"});
    model.component("comp1").geom("geom1").feature("blk2").setIndex("pos", "-4[cm]", 2);
    model.component("comp1").geom("geom1").feature("blk2").setIndex("layer", "2[cm]", 0);
    model.component("comp1").geom("geom1").feature("blk2").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("blk2").set("layerright", true);
    model.component("comp1").geom("geom1").feature("blk2").set("layerfront", true);
    model.component("comp1").geom("geom1").feature("blk2").set("layerback", true);
    model.component("comp1").geom("geom1").feature("blk2").set("layertop", true);
    model.component("comp1").geom("geom1").run("blk2");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").coordSystem().create("ie1", "InfiniteElement");
    model.component("comp1").coordSystem("ie1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 15, 16, 17, 18, 20, 21, 22, 23, 24, 25, 26, 27, 28);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").label("Nylon");
    model.component("comp1").material("mat1").set("family", "custom");
    model.component("comp1").material("mat1")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat1").set("diffuse", "custom");
    model.component("comp1").material("mat1")
         .set("customdiffuse", new double[]{0.39215686274509803, 0.39215686274509803, 0.9803921568627451});
    model.component("comp1").material("mat1").set("ambient", "custom");
    model.component("comp1").material("mat1")
         .set("customambient", new double[]{0.39215686274509803, 0.39215686274509803, 0.7843137254901961});
    model.component("comp1").material("mat1").set("noise", true);
    model.component("comp1").material("mat1").set("lighting", "phong");
    model.component("comp1").material("mat1").set("shininess", 500);
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "1700[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"4", "0", "0", "0", "4", "0", "0", "0", "4"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"280e-6[1/K]", "0", "0", "0", "280e-6[1/K]", "0", "0", "0", "280e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "1150[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.26[W/(m*K)]", "0", "0", "0", "0.26[W/(m*K)]", "0", "0", "0", "0.26[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "2[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.4");
    model.component("comp1").material("mat1").selection().set(19);

    model.component("comp1").physics("es").create("ccns1", "ChargeConservationSolid", 3);
    model.component("comp1").physics("es").feature("ccns1").selection().set(19);
    model.component("comp1").physics("es").create("gnd1", "Ground", 2);
    model.component("comp1").physics("es").feature("gnd1").selection().set(69);
    model.component("comp1").physics("es").create("term1", "Terminal", 2);
    model.component("comp1").physics("es").feature("term1").selection().named("geom1_wp1_bnd");
    model.component("comp1").physics("es").feature("term1").runCommand("splitByConnectivity");

    model.component("comp1").mesh("mesh1").autoMeshSize(3);
    model.component("comp1").mesh("mesh1").contribute("geom/detail", false);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u7535\u52bf (es)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("solutionparams", "parent");
    model.result("pg1").feature("mslc1").set("expr", "V/es.VexcTerm");
    model.result("pg1").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg1").feature("mslc1").set("xcoord", "es.CPx");
    model.result("pg1").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg1").feature("mslc1").set("ycoord", "es.CPy");
    model.result("pg1").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg1").feature("mslc1").set("zcoord", "es.CPz");
    model.result("pg1").feature("mslc1").set("colortable", "Dipole");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("data", "parent");
    model.result("pg1").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg1").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg1").feature("strmsl1").set("xcoord", "es.CPx");
    model.result("pg1").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg1").feature("strmsl1").set("ycoord", "es.CPy");
    model.result("pg1").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg1").feature("strmsl1").set("zcoord", "es.CPz");
    model.result("pg1").feature("strmsl1").set("titletype", "none");
    model.result("pg1").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg1").feature("strmsl1").set("udist", 0.02);
    model.result("pg1").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg1").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("inheritcolor", false);
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("data", "parent");
    model.result("pg1").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg1").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg1").feature("strmsl1").feature("col1").set("expr", "V/es.VexcTerm");
    model.result("pg1").feature("strmsl1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg1").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg1").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg1").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u7535\u573a (es)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("solutionparams", "parent");
    model.result("pg2").feature("mslc1").set("expr", "es.normE");
    model.result("pg2").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("mslc1").set("xcoord", "es.CPx");
    model.result("pg2").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("mslc1").set("ycoord", "es.CPy");
    model.result("pg2").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("mslc1").set("zcoord", "es.CPz");
    model.result("pg2").feature("mslc1").set("colortable", "Prism");
    model.result("pg2").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("data", "parent");
    model.result("pg2").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("solutionparams", "parent");
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
    model.result("pg2").feature("strmsl1").feature("col1").set("expr", "es.normE");
    model.result("pg2").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg2").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg2").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg2").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg2").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").set("data", "dset1");
    model.result().evaluationGroup("eg1").label(" (es)");
    model.result().evaluationGroup("eg1").label("\u9006\u9ea6\u514b\u65af\u97e6\u7535\u5bb9 (dset1, es)");
    model.result().evaluationGroup("eg1").create("gmev1", "EvalGlobalMatrix");
    model.result().evaluationGroup("eg1").feature("gmev1")
         .label("\u9006\u9ea6\u514b\u65af\u97e6\u7535\u5bb9 (dset1, es)");
    model.result().evaluationGroup("eg1").feature("gmev1").set("expr", "root.comp1.es.Cinv");
    model.result().evaluationGroup("eg1").feature("gmev1").set("outerdataseries", "none");
    model.result().evaluationGroup("eg1").feature("gmev1").set("dataseries", "sum");

    model.nodeGroup().create("grp2", "Results");
    model.nodeGroup("grp2").label("\u96c6\u603b\u53c2\u6570 (dset1, es)");
    model.nodeGroup("grp2").set("type", "evaluationgroup");
    model.nodeGroup("grp2").add("evaluationgroup", "eg1");

    model.result().evaluationGroup("eg1").run();
    model.result().evaluationGroup().create("eg2", "EvaluationGroup");
    model.result().evaluationGroup("eg2").set("data", "dset1");
    model.result().evaluationGroup("eg2").label(" (es)");
    model.result().evaluationGroup("eg2").label("\u9ea6\u514b\u65af\u97e6\u7535\u5bb9 (dset1, es)");
    model.result().evaluationGroup("eg2").create("gmev1", "EvalGlobalMatrix");
    model.result().evaluationGroup("eg2").feature("gmev1").label("\u9ea6\u514b\u65af\u97e6\u7535\u5bb9 (dset1, es)");
    model.result().evaluationGroup("eg2").feature("gmev1").set("expr", "root.comp1.es.Cinv");
    model.result().evaluationGroup("eg2").feature("gmev1").set("outerdataseries", "none");
    model.result().evaluationGroup("eg2").feature("gmev1").set("dataseries", "sum");
    model.result().evaluationGroup("eg2").feature("gmev1").set("trans", "inverse");

    model.nodeGroup("grp2").add("evaluationgroup", "eg2");

    model.result().evaluationGroup("eg2").run();
    model.result().evaluationGroup().create("eg3", "EvaluationGroup");
    model.result().evaluationGroup("eg3").set("data", "dset1");
    model.result().evaluationGroup("eg3").label(" (es)");
    model.result().evaluationGroup("eg3").label("\u4e92\u7535\u5bb9 (dset1, es)");
    model.result().evaluationGroup("eg3").create("gmev1", "EvalGlobalMatrix");
    model.result().evaluationGroup("eg3").feature("gmev1").label("\u4e92\u7535\u5bb9 (dset1, es)");
    model.result().evaluationGroup("eg3").feature("gmev1").set("expr", "root.comp1.es.Cinv");
    model.result().evaluationGroup("eg3").feature("gmev1").set("outerdataseries", "none");
    model.result().evaluationGroup("eg3").feature("gmev1").set("dataseries", "sum");
    model.result().evaluationGroup("eg3").feature("gmev1").set("trans", "invmaxwellmutual");

    model.nodeGroup("grp2").add("evaluationgroup", "eg3");

    model.result().evaluationGroup("eg3").run();
    model.result("pg1").run();

    model.component("comp1").view("view1").set("showgrid", false);

    model.result("pg1").stepPrevious(0);
    model.result("pg1").run();
    model.result("pg1").stepPrevious(0);
    model.result("pg1").run();
    model.result("pg1").stepPrevious(0);
    model.result("pg1").run();
    model.result("pg1").stepPrevious(0);
    model.result("pg1").run();
    model.result().evaluationGroup("eg1").run();
    model.result().evaluationGroup("eg2").run();
    model.result().evaluationGroup("eg3").run();
    model.result().evaluationGroup("eg2").feature("gmev1").set("unit", "1/pF");
    model.result().evaluationGroup("eg3").feature("gmev1").set("unit", "1/pF");
    model.result().evaluationGroup("eg1").run();
    model.result().evaluationGroup("eg2").run();
    model.result().evaluationGroup("eg3").run();
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "none");
    model.result("pg3").create("tbls1", "TableSurface");
    model.result("pg3").feature("tbls1").set("source", "evaluationgroup");
    model.result("pg3").feature("tbls1").set("evaluationgroup", "eg3");
    model.result("pg3").run();
    model.result("pg3").feature("tbls1").set("dataformat", "cells");
    model.result("pg3").feature("tbls1").set("function", "discrete");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").label("\u4e92\u7535\u5bb9\u77e9\u9635");
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u4e92\u7535\u5bb9\u77e9\u9635 [pF]");
    model.result("pg3").run();

    model.sol("sol1").feature("s1").feature("dDef").active(true);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().evaluationGroup("eg1").run();
    model.result().evaluationGroup("eg2").run();
    model.result().evaluationGroup("eg3").run();

    model.param().set("L", "2[cm]");
    model.param().descr("L", "\u4f4d\u79fb");

    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("blk3", "Block");
    model.component("comp1").geom("geom1").feature("blk3").set("size", new String[]{"25[cm]", "5[cm]", "1"});
    model.component("comp1").geom("geom1").feature("blk3").setIndex("size", "8[cm]", 2);
    model.component("comp1").geom("geom1").feature("blk3").set("pos", new String[]{"-35[cm]+L", "3[cm]", "0"});
    model.component("comp1").geom("geom1").feature("blk3").setIndex("pos", "2[cm]", 2);
    model.component("comp1").geom("geom1").run("blk3");

    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.component("comp1").material("mat2").label("Copper");
    model.component("comp1").material("mat2").set("family", "copper");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"17e-6[1/K]", "0", "0", "0", "17e-6[1/K]", "0", "0", "0", "17e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "8960[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "110[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.35");
    model.component("comp1").material("mat2").propertyGroup("linzRes").label("Linearized resistivity");
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("rho0", "1.72e-8[ohm*m]");
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("alpha", "0.0039[1/K]");
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("Tref", "298[K]");
    model.component("comp1").material("mat2").propertyGroup("linzRes").addInput("temperature");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").material("mat2").selection().set(19);

    model.component("comp1").physics("es").create("term6", "DomainTerminal", 3);
    model.component("comp1").physics("es").feature("term6").selection().set(19);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().evaluationGroup("eg1").run();
    model.result().evaluationGroup("eg2").run();
    model.result().evaluationGroup("eg3").run();

    model.study("std1").feature("stssw").set("specifysources", true);
    model.study("std1").feature("stssw").set("sourcenames", "range(1,1,5)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().evaluationGroup("eg1").run();
    model.result().evaluationGroup("eg2").run();
    model.result().evaluationGroup("eg3").run();

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "L", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "L", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("plistarr", "range(2,2,28)", 0);
    model.study("std1").feature("param").setIndex("punit", "cm", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u7535\u52bf (es) 1");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevel", 5, 0);
    model.result("pg4").setIndex("looplevel", 14, 1);
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").feature().create("mslc1", "Multislice");
    model.result("pg4").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg4").feature("mslc1").set("solutionparams", "parent");
    model.result("pg4").feature("mslc1").set("expr", "V/es.VexcTerm");
    model.result("pg4").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg4").feature("mslc1").set("xcoord", "es.CPx");
    model.result("pg4").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg4").feature("mslc1").set("ycoord", "es.CPy");
    model.result("pg4").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg4").feature("mslc1").set("zcoord", "es.CPz");
    model.result("pg4").feature("mslc1").set("colortable", "Dipole");
    model.result("pg4").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg4").feature("mslc1").set("data", "parent");
    model.result("pg4").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg4").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg4").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg4").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg4").feature("strmsl1").set("xcoord", "es.CPx");
    model.result("pg4").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg4").feature("strmsl1").set("ycoord", "es.CPy");
    model.result("pg4").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg4").feature("strmsl1").set("zcoord", "es.CPz");
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
    model.result("pg4").feature("strmsl1").feature("col1").set("expr", "V/es.VexcTerm");
    model.result("pg4").feature("strmsl1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg4").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg4").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg4").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u7535\u573a (es) 1");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").setIndex("looplevel", 5, 0);
    model.result("pg5").setIndex("looplevel", 14, 1);
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").set("showlegendsmaxmin", true);
    model.result("pg5").feature().create("mslc1", "Multislice");
    model.result("pg5").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg5").feature("mslc1").set("solutionparams", "parent");
    model.result("pg5").feature("mslc1").set("expr", "es.normE");
    model.result("pg5").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg5").feature("mslc1").set("xcoord", "es.CPx");
    model.result("pg5").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg5").feature("mslc1").set("ycoord", "es.CPy");
    model.result("pg5").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg5").feature("mslc1").set("zcoord", "es.CPz");
    model.result("pg5").feature("mslc1").set("colortable", "Prism");
    model.result("pg5").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg5").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg5").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg5").feature("mslc1").set("data", "parent");
    model.result("pg5").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg5").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg5").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg5").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg5").feature("strmsl1").set("xcoord", "es.CPx");
    model.result("pg5").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg5").feature("strmsl1").set("ycoord", "es.CPy");
    model.result("pg5").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg5").feature("strmsl1").set("zcoord", "es.CPz");
    model.result("pg5").feature("strmsl1").set("titletype", "none");
    model.result("pg5").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg5").feature("strmsl1").set("udist", 0.02);
    model.result("pg5").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg5").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg5").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("strmsl1").set("inheritcolor", false);
    model.result("pg5").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg5").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg5").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg5").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg5").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("strmsl1").set("data", "parent");
    model.result("pg5").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg5").feature("strmsl1").feature().create("col1", "Color");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg5").feature("strmsl1").feature("col1").set("expr", "es.normE");
    model.result("pg5").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg5").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg5").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg5").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg5").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg5").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().evaluationGroup().create("eg4", "EvaluationGroup");
    model.result().evaluationGroup("eg4").set("data", "dset2");
    model.result().evaluationGroup("eg4").label(" (es)");
    model.result().evaluationGroup("eg4").label("\u9006\u9ea6\u514b\u65af\u97e6\u7535\u5bb9 (dset2, es)");
    model.result().evaluationGroup("eg4").create("gmev1", "EvalGlobalMatrix");
    model.result().evaluationGroup("eg4").feature("gmev1")
         .label("\u9006\u9ea6\u514b\u65af\u97e6\u7535\u5bb9 (dset2, es)");
    model.result().evaluationGroup("eg4").feature("gmev1").set("expr", "root.comp1.es.Cinv");
    model.result().evaluationGroup("eg4").feature("gmev1").set("outerdataseries", "none");
    model.result().evaluationGroup("eg4").feature("gmev1").set("dataseries", "sum");

    model.nodeGroup().create("grp3", "Results");
    model.nodeGroup("grp3").label("\u96c6\u603b\u53c2\u6570 (dset2, es)");
    model.nodeGroup("grp3").set("type", "evaluationgroup");
    model.nodeGroup("grp3").add("evaluationgroup", "eg4");

    model.result().evaluationGroup("eg4").setIndex("looplevelinput", "last", 1);
    model.result().evaluationGroup("eg4").run();
    model.result().evaluationGroup().create("eg5", "EvaluationGroup");
    model.result().evaluationGroup("eg5").set("data", "dset2");
    model.result().evaluationGroup("eg5").label(" (es)");
    model.result().evaluationGroup("eg5").label("\u9ea6\u514b\u65af\u97e6\u7535\u5bb9 (dset2, es)");
    model.result().evaluationGroup("eg5").create("gmev1", "EvalGlobalMatrix");
    model.result().evaluationGroup("eg5").feature("gmev1").label("\u9ea6\u514b\u65af\u97e6\u7535\u5bb9 (dset2, es)");
    model.result().evaluationGroup("eg5").feature("gmev1").set("expr", "root.comp1.es.Cinv");
    model.result().evaluationGroup("eg5").feature("gmev1").set("outerdataseries", "none");
    model.result().evaluationGroup("eg5").feature("gmev1").set("dataseries", "sum");
    model.result().evaluationGroup("eg5").feature("gmev1").set("trans", "inverse");

    model.nodeGroup("grp3").add("evaluationgroup", "eg5");

    model.result().evaluationGroup("eg5").setIndex("looplevelinput", "last", 1);
    model.result().evaluationGroup("eg5").run();
    model.result().evaluationGroup().create("eg6", "EvaluationGroup");
    model.result().evaluationGroup("eg6").set("data", "dset2");
    model.result().evaluationGroup("eg6").label(" (es)");
    model.result().evaluationGroup("eg6").label("\u4e92\u7535\u5bb9 (dset2, es)");
    model.result().evaluationGroup("eg6").create("gmev1", "EvalGlobalMatrix");
    model.result().evaluationGroup("eg6").feature("gmev1").label("\u4e92\u7535\u5bb9 (dset2, es)");
    model.result().evaluationGroup("eg6").feature("gmev1").set("expr", "root.comp1.es.Cinv");
    model.result().evaluationGroup("eg6").feature("gmev1").set("outerdataseries", "none");
    model.result().evaluationGroup("eg6").feature("gmev1").set("dataseries", "sum");
    model.result().evaluationGroup("eg6").feature("gmev1").set("trans", "invmaxwellmutual");

    model.nodeGroup("grp3").add("evaluationgroup", "eg6");

    model.result().evaluationGroup("eg6").setIndex("looplevelinput", "last", 1);
    model.result().evaluationGroup("eg6").run();
    model.result("pg4").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u7535\u5bb9\u6bd4\u8f83");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").set("titletype", "manual");
    model.result("pg6").set("title", "\u7535\u5bb9\u7684\u76f8\u5bf9\u53d8\u5316");
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").set("data", "dset2");
    model.result("pg6").feature("glob1").setIndex("looplevelinput", "first", 0);
    model.result("pg6").feature("glob1").set("xdatasolnumtype", "level2");
    model.result("pg6").feature("glob1").setIndex("expr", "with(1,es.Cinv11)/withsol('sol3',with(1,es.Cinv11))", 0);
    model.result("pg6").feature("glob1").setIndex("descr", "\u7ec8\u7aef 1", 0);
    model.result("pg6").feature("glob1").setIndex("expr", "with(5,es.Cinv55)/withsol('sol3',with(5,es.Cinv55))", 1);
    model.result("pg6").feature("glob1").setIndex("descr", "\u7ec8\u7aef 5", 1);
    model.result("pg6").feature("glob1")
         .setIndex("expr", "with(5,es.Cinv55)/with(1,es.Cinv11)/withsol('sol3',with(5,es.Cinv55)/with(1,es.Cinv11))", 2);
    model.result("pg6").feature("glob1")
         .setIndex("descr", "\u7ec8\u7aef 1 \u4e0e 5 \u7684\u7535\u5bb9\u6bd4\u7387", 2);
    model.result("pg6").feature("glob1").set("legendmethod", "manual");
    model.result("pg6").feature("glob1").setIndex("legends", "\u7ec8\u7aef 1 \u7684\u53d8\u5316", 0);
    model.result("pg6").feature("glob1").setIndex("legends", "\u7ec8\u7aef 5 \u7684\u53d8\u5316", 1);
    model.result("pg6").feature("glob1")
         .setIndex("legends", "\u7ec8\u7aef 1 \u4e0e 5 \u7684\u76f8\u5bf9\u53d8\u5316", 2);
    model.result("pg6").run();
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 1, 0);
    model.result("pg4").setIndex("looplevel", 1, 1);
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 5, 1);
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 7, 1);
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 13, 1);
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 6, 1);
    model.result("pg4").run();
    model.result("pg4").feature("mslc1").set("multiplanexmethod", "number");
    model.result("pg4").feature("mslc1").set("xnumber", "0");
    model.result("pg4").feature("mslc1").set("multiplanezmethod", "number");
    model.result("pg4").feature("mslc1").set("znumber", "0");
    model.result("pg4").run();
    model.result("pg4").feature("strmsl1").set("multiplanexmethod", "number");
    model.result("pg4").feature("strmsl1").set("xnumber", "0");
    model.result("pg4").feature("strmsl1").set("multiplanezmethod", "number");
    model.result("pg4").feature("strmsl1").set("znumber", "0");
    model.result("pg4").run();
    model.result("pg4").run();

    model.title("\u7535\u5bb9\u5f0f\u4f4d\u7f6e\u4f20\u611f\u5668\u7684\u6709\u9650\u5143\u5efa\u6a21");

    model
         .description("\u672c\u6559\u5b66\u6a21\u578b\u89e3\u91ca\u5982\u4f55\u901a\u8fc7\u7a33\u6001\u6e90\u626b\u63cf \u7814\u7a76\u6765\u63d0\u53d6\u96c6\u603b\u77e9\u9635\u3002\u5176\u4e2d\u4f7f\u7528\u4e94\u7ec8\u7aef\u7cfb\u7edf\u7684\u7535\u5bb9\u77e9\u9635\u6765\u63a8\u65ad\u91d1\u5c5e\u7269\u4f53\u7684\u4f4d\u7f6e\uff0c\u7c7b\u4f3c\u4e8e\u73b0\u5b9e\u4e16\u754c\u4e2d\u7684\u7535\u5bb9\u5f0f\u4f4d\u7f6e\u4f20\u611f\u5668\u3002\n\n\u672c\u4f8b\u8bf4\u660e\u4e86\u9759\u7535 \u63a5\u53e3\u652f\u6301\u7684\u6709\u9650\u5143\u6cd5\u7684\u4f7f\u7528\u3002\u5728\u4f7f\u7528\u6709\u9650\u5143\u6cd5\u65f6\uff0c\u9700\u8981\u5bf9\u5468\u56f4\u7a7a\u6c14\u7684\u4e00\u90e8\u5206\u8fdb\u884c\u4f53\u7f51\u683c\u5212\u5206\u3002\n\n\u8be5\u6a21\u578b\u8fd8\u6bd4\u8f83\u4e86\u4f7f\u7528\u76f4\u63a5\u6c42\u89e3\u5668\u548c\u8fed\u4ee3\u6c42\u89e3\u5668\u65f6\u5bf9\u7814\u7a76\u6027\u80fd\u7684\u5f71\u54cd\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();
    model.sol("sol10").clearSolutionData();
    model.sol("sol11").clearSolutionData();
    model.sol("sol12").clearSolutionData();
    model.sol("sol13").clearSolutionData();
    model.sol("sol14").clearSolutionData();
    model.sol("sol15").clearSolutionData();
    model.sol("sol16").clearSolutionData();

    model.label("capacitive_position_sensor.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
