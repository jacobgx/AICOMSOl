/*
 * inductance_matrix_pcb_coils.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:17 by COMSOL 6.3.0.290. */
public class inductance_matrix_pcb_coils {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Devices,_Inductive");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("mfco", "MagneticFieldsCurrentsOnly", "geom1");

    model.study().create("std1");
    model.study("std1").create("init", "SourceInitialization");
    model.study("std1").feature("init").set("solnum", "auto");
    model.study("std1").feature("init").set("notsolnum", "auto");
    model.study("std1").feature("init").set("outputmap", new String[]{});
    model.study("std1").feature("init").set("ngenAUX", "1");
    model.study("std1").feature("init").set("goalngenAUX", "1");
    model.study("std1").feature("init").set("ngenAUX", "1");
    model.study("std1").feature("init").set("goalngenAUX", "1");
    model.study("std1").feature("init").setSolveFor("/physics/mfco", true);
    model.study("std1").create("stssw", "StationarySourceSweep");
    model.study("std1").feature("stssw").set("solnum", "auto");
    model.study("std1").feature("stssw").set("notsolnum", "auto");
    model.study("std1").feature("stssw").set("outputmap", new String[]{});
    model.study("std1").feature("stssw").set("ngenAUX", "1");
    model.study("std1").feature("stssw").set("goalngenAUX", "1");
    model.study("std1").feature("stssw").set("ngenAUX", "1");
    model.study("std1").feature("stssw").set("goalngenAUX", "1");
    model.study("std1").feature("stssw").setSolveFor("/physics/mfco", true);

    model.component("comp1").geom("geom1").geomRep("cadps");
    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("type", "native");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "inductance_matrix_pcb_coils.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").feature("imp1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("imp1").set("selresultshow", "all");

    model.component("comp1").view("view1").set("transparency", true);

    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection")
         .set("imp1", 6, 16, 26, 36, 2502, 2504, 2506, 2508);
    model.component("comp1").geom("geom1").feature("sel1").label("\u63a5\u5730\u8fb9\u754c");
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("imp1", 1, 11, 21, 31);
    model.component("comp1").geom("geom1").feature("sel2").label("\u8f93\u5165\u7ec8\u7aef");
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel3").selection("selection")
         .set("imp1", 1261, 1266, 1271, 1276);
    model.component("comp1").geom("geom1").feature("sel3").label("\u5185\u90e8\u7ec8\u7aef");
    model.component("comp1").geom("geom1").run("sel3");
    model.component("comp1").geom("geom1").create("sel4", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel4").label("\u8f93\u51fa\u7ec8\u7aef");
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel4").selection("selection")
         .set("imp1", 2501, 2503, 2505, 2507);
    model.component("comp1").geom("geom1").run("sel4");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"sel2", "sel3", "sel4"});
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"5[cm]", "6[cm]", "2[cm]"});
    model.component("comp1").geom("geom1").feature("blk1").set("base", "center");
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickz", "-2[mm]");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").selection("input").set("blk1");
    model.component("comp1").geom("geom1").feature("par1").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").run("par1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp2").set("quickx", "7[mm]");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").create("par2", "Partition");
    model.component("comp1").geom("geom1").feature("par2").selection("input").set("par1");
    model.component("comp1").geom("geom1").feature("par2").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").run("par2");
    model.component("comp1").geom("geom1").create("wp3", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp3").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp3").set("quickplane", "zx");
    model.component("comp1").geom("geom1").feature("wp3").set("quicky", "14.25[mm]");
    model.component("comp1").geom("geom1").run("wp3");
    model.component("comp1").geom("geom1").create("par3", "Partition");
    model.component("comp1").geom("geom1").feature("par3").selection("input").set("par2");
    model.component("comp1").geom("geom1").feature("par3").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").physics("mfco").create("cond1", "Conductor", 3);
    model.component("comp1").physics("mfco").feature("cond1").selection().named("geom1_imp1_dom");
    model.component("comp1").physics("mfco").feature("cond1").create("gnd1", "Ground", 2);
    model.component("comp1").physics("mfco").feature("cond1").feature("term1").selection().named("geom1_unisel1");
    model.component("comp1").physics("mfco").feature("cond1").feature("gnd1").selection().named("geom1_sel1");
    model.component("comp1").physics("mfco").feature("cond1").runCommand("splitByConnectivity");

    model.component("comp1").view("view1").hideEntities().create("hide1");
    model.component("comp1").view("view1").hideEntities("hide1").geom("geom1", 2);
    model.component("comp1").view("view1").hideEntities("hide1").set(4, 5, 7);

    model.component("comp1").mesh("mesh1").autoMeshSize(7);

    model.component("comp1").view("view1").set("transparency", false);

    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").selection().named("geom1_imp1_dom");
    model.component("comp1").material("mat1").propertyGroup("def").set("electricconductivity", new String[]{"6e7"});

    model.study("std1").showAutoSequences("all");

    model.component("comp1").physics("mfco").feature("cond5").feature().remove("gnd1");
    model.component("comp1").physics("mfco").feature("cond6").feature().remove("gnd1");
    model.component("comp1").physics("mfco").feature("cond7").feature().remove("gnd1");
    model.component("comp1").physics("mfco").feature("cond8").feature().remove("gnd1");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u78c1\u901a\u5bc6\u5ea6 (mfco)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("solutionparams", "parent");
    model.result("pg1").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg1").feature("mslc1").set("xcoord", "mfco.CPx");
    model.result("pg1").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg1").feature("mslc1").set("ycoord", "mfco.CPy");
    model.result("pg1").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg1").feature("mslc1").set("zcoord", "mfco.CPz");
    model.result("pg1").feature("mslc1").set("colortable", "Prism");
    model.result("pg1").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("data", "parent");
    model.result("pg1").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg1").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg1").feature("strmsl1").set("xcoord", "mfco.CPx");
    model.result("pg1").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg1").feature("strmsl1").set("ycoord", "mfco.CPy");
    model.result("pg1").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg1").feature("strmsl1").set("zcoord", "mfco.CPz");
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
    model.result("pg1").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg1").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg1").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg1").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg1").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").set("data", "dset1");
    model.result().evaluationGroup("eg1").label(" (mfco)");
    model.result().evaluationGroup("eg1").label("\u7535\u963b\uff08\u76f4\u6d41\uff09 (dset1, mfco)");
    model.result().evaluationGroup("eg1").create("gmev1", "EvalGlobalMatrix");
    model.result().evaluationGroup("eg1").feature("gmev1")
         .label("\u7535\u963b\uff08\u76f4\u6d41\uff09 (dset1, mfco)");
    model.result().evaluationGroup("eg1").feature("gmev1").set("expr", "root.comp1.mfco.Rdc");
    model.result().evaluationGroup("eg1").feature("gmev1").set("outerdataseries", "none");
    model.result().evaluationGroup("eg1").feature("gmev1").set("dataseries", "sum");

    model.nodeGroup().create("grp2", "Results");
    model.nodeGroup("grp2").label("\u96c6\u603b\u53c2\u6570 (dset1, mfco)");
    model.nodeGroup("grp2").set("type", "evaluationgroup");
    model.nodeGroup("grp2").add("evaluationgroup", "eg1");

    model.result().evaluationGroup("eg1").run();
    model.result().evaluationGroup().create("eg2", "EvaluationGroup");
    model.result().evaluationGroup("eg2").set("data", "dset1");
    model.result().evaluationGroup("eg2").label(" (mfco)");
    model.result().evaluationGroup("eg2").label("\u7535\u611f\uff08\u76f4\u6d41\uff09 (dset1, mfco)");
    model.result().evaluationGroup("eg2").create("gmev1", "EvalGlobalMatrix");
    model.result().evaluationGroup("eg2").feature("gmev1")
         .label("\u7535\u611f\uff08\u76f4\u6d41\uff09 (dset1, mfco)");
    model.result().evaluationGroup("eg2").feature("gmev1").set("expr", "root.comp1.mfco.Ldc");
    model.result().evaluationGroup("eg2").feature("gmev1").set("outerdataseries", "none");
    model.result().evaluationGroup("eg2").feature("gmev1").set("dataseries", "sum");

    model.nodeGroup("grp2").add("evaluationgroup", "eg2");

    model.result().evaluationGroup("eg2").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("mslc1").set("xcoord", "7[mm]");
    model.result("pg1").feature("mslc1").set("ycoord", "14.25[mm]");
    model.result("pg1").feature("mslc1").set("zcoord", "-2[mm]");
    model.result("pg1").feature("mslc1").set("rangecoloractive", true);
    model.result("pg1").feature("mslc1").set("rangecolormax", 8.0E-4);
    model.result("pg1").run();
    model.result("pg1").feature("strmsl1").set("xcoord", "7[mm]");
    model.result("pg1").feature("strmsl1").set("ycoord", "14.25[mm]");
    model.result("pg1").feature("strmsl1").set("zcoord", "-2[mm]");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "1");
    model.result("pg1").feature("surf1").create("sel1", "Selection");
    model.result("pg1").feature("surf1").feature("sel1").selection().named("geom1_imp1_bnd");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg1").feature("surf1").feature("mtrl1").set("family", "copper");
    model.result("pg1").run();
    model.result("pg1").label("\u78c1\u901a\u5bc6\u5ea6\u6a21 (DC)");
    model.result("pg1").setIndex("looplevel", 8, 0);
    model.result("pg1").set("titletype", "label");
    model.result("pg1").set("edges", false);
    model.result("pg1").set("showlegendsmaxmin", false);
    model.result("pg1").run();
    model.result().evaluationGroup("eg1").run();
    model.result().evaluationGroup("eg2").run();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "none");
    model.result("pg2").create("tbls1", "TableSurface");
    model.result("pg2").feature("tbls1").set("source", "evaluationgroup");
    model.result("pg2").feature("tbls1").set("evaluationgroup", "eg2");
    model.result("pg2").run();
    model.result("pg2").feature("tbls1").set("dataformat", "cells");
    model.result("pg2").feature("tbls1").set("function", "discrete");
    model.result("pg2").run();
    model.result("pg2").feature("tbls1").set("colortable", "Wave");
    model.result("pg2").feature("tbls1").set("colorscalemode", "linearsymmetric");
    model.result("pg2").feature("tbls1").set("titletype", "none");
    model.result("pg2").run();
    model.result("pg2").label("\u7535\u611f\u77e9\u9635 (DC)");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("\u7ec8\u7aef\u7f16\u53f7");
    model.result("pg3").create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("expr", "mfco.TerminalNumber");
    model.result("pg3").feature("vol1").set("descr", "\u7ec8\u7aef\u6570");
    model.result("pg3").feature("vol1").set("colortable", "Cyclic");
    model.result("pg3").run();

    model.study().create("std2");
    model.study("std2").create("init", "SourceInitialization");
    model.study("std2").feature("init").set("plotgroup", "Default");
    model.study("std2").feature("init").set("solnum", "auto");
    model.study("std2").feature("init").set("notsolnum", "auto");
    model.study("std2").feature("init").set("outputmap", new String[]{});
    model.study("std2").feature("init").set("ngenAUX", "1");
    model.study("std2").feature("init").set("goalngenAUX", "1");
    model.study("std2").feature("init").set("ngenAUX", "1");
    model.study("std2").feature("init").set("goalngenAUX", "1");
    model.study("std2").feature("init").setSolveFor("/physics/mfco", true);
    model.study("std2").create("fdss", "FrequencyDomainSourceSweep");
    model.study("std2").feature("fdss").set("plotgroup", "Default");
    model.study("std2").feature("fdss").set("plist", "1000");
    model.study("std2").feature("fdss").set("probesel", "none");
    model.study("std2").feature("fdss").set("solnum", "auto");
    model.study("std2").feature("fdss").set("notsolnum", "auto");
    model.study("std2").feature("fdss").set("outputmap", new String[]{});
    model.study("std2").feature("fdss").set("ngenAUX", "1");
    model.study("std2").feature("fdss").set("goalngenAUX", "1");
    model.study("std2").feature("fdss").set("ngenAUX", "1");
    model.study("std2").feature("fdss").set("goalngenAUX", "1");
    model.study("std2").feature("fdss").setSolveFor("/physics/mfco", true);
    model.study("std2").feature("fdss").set("plist", "200[kHz]");
    model.study("std2").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u78c1\u901a\u5bc6\u5ea6 (mfco)");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").setIndex("looplevel", 12, 0);
    model.result("pg4").setIndex("looplevel", 1, 1);
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").feature().create("mslc1", "Multislice");
    model.result("pg4").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg4").feature("mslc1").set("solutionparams", "parent");
    model.result("pg4").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg4").feature("mslc1").set("xcoord", "mfco.CPx");
    model.result("pg4").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg4").feature("mslc1").set("ycoord", "mfco.CPy");
    model.result("pg4").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg4").feature("mslc1").set("zcoord", "mfco.CPz");
    model.result("pg4").feature("mslc1").set("colortable", "Prism");
    model.result("pg4").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg4").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg4").feature("mslc1").set("data", "parent");
    model.result("pg4").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg4").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg4").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg4").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg4").feature("strmsl1").set("xcoord", "mfco.CPx");
    model.result("pg4").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg4").feature("strmsl1").set("ycoord", "mfco.CPy");
    model.result("pg4").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg4").feature("strmsl1").set("zcoord", "mfco.CPz");
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
    model.result("pg4").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg4").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg4").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg4").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg4").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().evaluationGroup().create("eg3", "EvaluationGroup");
    model.result().evaluationGroup("eg3").set("data", "dset3");
    model.result().evaluationGroup("eg3").label(" (mfco)");
    model.result().evaluationGroup("eg3").label("\u7535\u963b\uff08\u76f4\u6d41\uff09 (dset3, mfco)");
    model.result().evaluationGroup("eg3").create("gmev1", "EvalGlobalMatrix");
    model.result().evaluationGroup("eg3").feature("gmev1")
         .label("\u7535\u963b\uff08\u76f4\u6d41\uff09 (dset3, mfco)");
    model.result().evaluationGroup("eg3").feature("gmev1").set("expr", "root.comp1.mfco.Rdc");
    model.result().evaluationGroup("eg3").feature("gmev1").set("outerdataseries", "none");
    model.result().evaluationGroup("eg3").feature("gmev1").set("dataseries", "sum");

    model.nodeGroup().create("grp3", "Results");
    model.nodeGroup("grp3").label("\u96c6\u603b\u53c2\u6570 (dset3, mfco)");
    model.nodeGroup("grp3").set("type", "evaluationgroup");
    model.nodeGroup("grp3").add("evaluationgroup", "eg3");

    model.result().evaluationGroup("eg3").setIndex("looplevelinput", "last", 1);
    model.result().evaluationGroup("eg3").run();
    model.result().evaluationGroup().create("eg4", "EvaluationGroup");
    model.result().evaluationGroup("eg4").set("data", "dset3");
    model.result().evaluationGroup("eg4").label(" (mfco)");
    model.result().evaluationGroup("eg4").label("\u7535\u963b\uff08\u4ea4\u6d41\uff09 (dset3, mfco)");
    model.result().evaluationGroup("eg4").create("gmev1", "EvalGlobalMatrix");
    model.result().evaluationGroup("eg4").feature("gmev1")
         .label("\u7535\u963b\uff08\u4ea4\u6d41\uff09 (dset3, mfco)");
    model.result().evaluationGroup("eg4").feature("gmev1").set("expr", "root.comp1.mfco.Rac");
    model.result().evaluationGroup("eg4").feature("gmev1").set("outerdataseries", "none");
    model.result().evaluationGroup("eg4").feature("gmev1").set("dataseries", "sum");

    model.nodeGroup("grp3").add("evaluationgroup", "eg4");

    model.result().evaluationGroup("eg4").setIndex("looplevelinput", "last", 1);
    model.result().evaluationGroup("eg4").run();
    model.result().evaluationGroup().create("eg5", "EvaluationGroup");
    model.result().evaluationGroup("eg5").set("data", "dset3");
    model.result().evaluationGroup("eg5").label(" (mfco)");
    model.result().evaluationGroup("eg5").label("\u7535\u611f\uff08\u4ea4\u6d41\uff09 (dset3, mfco)");
    model.result().evaluationGroup("eg5").create("gmev1", "EvalGlobalMatrix");
    model.result().evaluationGroup("eg5").feature("gmev1")
         .label("\u7535\u611f\uff08\u4ea4\u6d41\uff09 (dset3, mfco)");
    model.result().evaluationGroup("eg5").feature("gmev1").set("expr", "root.comp1.mfco.Lac");
    model.result().evaluationGroup("eg5").feature("gmev1").set("outerdataseries", "none");
    model.result().evaluationGroup("eg5").feature("gmev1").set("dataseries", "sum");

    model.nodeGroup("grp3").add("evaluationgroup", "eg5");

    model.result().evaluationGroup("eg5").setIndex("looplevelinput", "last", 1);
    model.result().evaluationGroup("eg5").run();
    model.result().evaluationGroup().create("eg6", "EvaluationGroup");
    model.result().evaluationGroup("eg6").set("data", "dset3");
    model.result().evaluationGroup("eg6").label(" (mfco)");
    model.result().evaluationGroup("eg6").label("\u963b\u6297 (dset3, mfco)");
    model.result().evaluationGroup("eg6").create("gmev1", "EvalGlobalMatrix");
    model.result().evaluationGroup("eg6").feature("gmev1").label("\u963b\u6297 (dset3, mfco)");
    model.result().evaluationGroup("eg6").feature("gmev1").set("expr", "root.comp1.mfco.Z");
    model.result().evaluationGroup("eg6").feature("gmev1").set("outerdataseries", "none");
    model.result().evaluationGroup("eg6").feature("gmev1").set("dataseries", "sum");

    model.nodeGroup("grp3").add("evaluationgroup", "eg6");

    model.result().evaluationGroup("eg6").setIndex("looplevelinput", "last", 1);
    model.result().evaluationGroup("eg6").run();
    model.result("pg4").run();
    model.result().remove("pg4");
    model.result("pg1").run();
    model.result().duplicate("pg4", "pg1");
    model.result("pg4").run();
    model.result("pg4").label("\u78c1\u901a\u5bc6\u5ea6\u6a21 (AC)");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").setIndex("looplevel", 8, 0);
    model.result("pg4").run();
    model.result().evaluationGroup("eg3").run();
    model.result().evaluationGroup("eg4").run();
    model.result().evaluationGroup("eg5").feature("gmev1").set("outerdataseries", "sum");
    model.result().evaluationGroup("eg5").run();
    model.result().evaluationGroup("eg6").run();
    model.result("pg2").run();
    model.result().duplicate("pg5", "pg2");
    model.result("pg5").run();
    model.result("pg5").label("\u7535\u611f\u77e9\u9635 (AC)");
    model.result("pg5").run();
    model.result("pg5").feature("tbls1").set("evaluationgroup", "eg5");
    model.result("pg5").feature("tbls1").set("dataformat", "cells");
    model.result("pg5").run();
    model.result("pg4").run();

    model.title("PCB \u7ebf\u5708\u7684\u7535\u611f\u77e9\u9635\u8ba1\u7b97");

    model
         .description("\u672c\u6a21\u578b\u6f14\u793a\u5982\u4f55\u7ed3\u5408\u4f7f\u7528\u201c\u78c1\u573a\uff0c\u4ec5\u7535\u6d41\u201d\u63a5\u53e3\u548c\u201c\u5177\u6709\u521d\u59cb\u5316\u7684\u7a33\u6001\u6e90\u626b\u63cf\u201d\u7814\u7a76\u6765\u8ba1\u7b97 12 \u4e2a PCB \u7ebf\u5708\u7684\u7535\u611f\u77e9\u9635\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.result("pg5").feature("tbls1").set("tablechanged", false);
    model.result("pg5").feature("tbls1").set("showparam", false);
    model.result("pg2").feature("tbls1").set("tablechanged", true);
    model.result("pg2").feature("tbls1").set("showparam", false);

    model.label("inductance_matrix_pcb_coils.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
