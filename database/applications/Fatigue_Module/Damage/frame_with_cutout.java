/*
 * frame_with_cutout.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:01 by COMSOL 6.3.0.290. */
public class frame_with_cutout {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Fatigue_Module\\Damage");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("shell", "Shell", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/shell", true);

    model.param().set("M", "1[N*m]");
    model.param().descr("M", "\u5355\u4f4d\u529b\u77e9");
    model.param().set("pt", "6[mm]");
    model.param().descr("pt", "\u6846\u67b6\u539a\u5ea6");
    model.param().set("ch", "80[mm]");
    model.param().descr("ch", "\u5b54\u9ad8\u5ea6");
    model.param().set("cw", "100[mm]");
    model.param().descr("cw", "\u5b54\u5bbd\u5ea6");
    model.param().set("cr", "10[mm]");
    model.param().descr("cr", "\u5b54\u534a\u5f84");

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new double[]{1.1, 0.154, 0.154});
    model.component("comp1").geom("geom1").feature("blk1").set("base", "center");
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp1").selection("face").set("blk1", 3);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("size", new String[]{"ch", "cw"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").selection("pointinsketch")
         .set("r1", 1, 2, 3, 4);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").set("radius", "cr");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", 0.3, 0);
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", -0.077, 2);
    model.component("comp1").geom("geom1").run("pt1");
    model.component("comp1").geom("geom1").create("pt2", "Point");
    model.component("comp1").geom("geom1").feature("pt2").setIndex("p", 0.3, 0);
    model.component("comp1").geom("geom1").feature("pt2").setIndex("p", 0.077, 1);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").coordSystem().create("sys2", "VectorBase");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").coordSystem("sys2").setIndex("base", "cos(pi/4)", 0, 0);
    model.component("comp1").coordSystem("sys2").setIndex("base", "sin(pi/4)", 0, 1);
    model.component("comp1").coordSystem("sys2").setIndex("base", "-sin(pi/4)", 1, 0);
    model.component("comp1").coordSystem("sys2").setIndex("base", "cos(pi/4)", 1, 1);
    model.component("comp1").coordSystem("sys2").set("orthonormal", true);

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").physics("shell").selection().set(2, 3, 4, 5);
    model.component("comp1").physics("shell").feature("to1").set("d", "pt");
    model.component("comp1").physics("shell").create("emm2", "Elastic", 2);
    model.component("comp1").physics("shell").feature("emm2").selection().set(3);
    model.component("comp1").physics("shell").feature("emm2").feature("shls1").set("coordinateSystem", "sys2");
    model.component("comp1").physics("shell").create("disp1", "Displacement1", 1);
    model.component("comp1").physics("shell").feature("disp1").selection().set(1, 2, 4, 6);
    model.component("comp1").physics("shell").feature("disp1").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("shell").feature("disp1").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("shell").feature("disp1").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("shell").create("srig1", "RigidConnectorShell", 1);
    model.component("comp1").physics("shell").feature("srig1").selection().set(17, 18, 19, 20);
    model.component("comp1").physics("shell").feature("srig1").create("rm1", "RigidBodyMoment", -1);
    model.component("comp1").physics("shell").feature("srig1").feature("rm1").set("Mt", new String[]{"M", "0", "0"});
    model.component("comp1").physics("shell").feature("srig1").feature("rm1").label("\u626d\u77e9 (x)");
    model.component("comp1").physics("shell").feature("srig1").create("rm2", "RigidBodyMoment", -1);
    model.component("comp1").physics("shell").feature("srig1").feature("rm2").set("Mt", new String[]{"0", "M", "0"});
    model.component("comp1").physics("shell").feature("srig1").feature("rm2").label("\u5f2f\u77e9 (y)");
    model.component("comp1").physics("shell").feature("srig1").create("rm3", "RigidBodyMoment", -1);
    model.component("comp1").physics("shell").feature("srig1").feature("rm3").set("Mt", new String[]{"0", "0", "M"});
    model.component("comp1").physics("shell").feature("srig1").feature("rm3").label("\u5f2f\u77e9 (z)");

    model.group().create("lg1", "LoadGroup");

    model.component("comp1").physics("shell").feature("srig1").feature("rm1").set("loadGroup", "lg1");

    model.group().create("lg2", "LoadGroup");

    model.component("comp1").physics("shell").feature("srig1").feature("rm2").set("loadGroup", "lg2");

    model.group().create("lg3", "LoadGroup");

    model.component("comp1").physics("shell").feature("srig1").feature("rm3").set("loadGroup", "lg3");

    model.group("lg1").label("\u8f7d\u8377\u7ec4\uff1aMx");
    model.group("lg1").paramName("lgX");
    model.group("lg2").label("\u8f7d\u8377\u7ec4\uff1aMy");
    model.group("lg2").paramName("lgY");
    model.group("lg3").label("\u8f7d\u8377\u7ec4\uff1aMz");
    model.group("lg3").paramName("lgZ");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").selection().set(2, 3, 4, 5);
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"200e9"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.33"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"7800"});

    model.component("comp1").mesh("mesh1").autoMeshSize(1);
    model.component("comp1").mesh("mesh1").contribute("geom/detail", false);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useloadcase", true);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 0, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 0, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 0, 1);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 0, 1);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 0, 2);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 0, 2);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 0, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 0, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 0, 1);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 0, 1);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 0, 2);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 0, 2);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 2", 1);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 1, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 1, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 1, 1);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 1, 1);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 1, 2);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 1, 2);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 2", 1);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 1, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 1, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 1, 1);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 1, 1);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 1, 2);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 1, 2);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 3", 2);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 2, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 2, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 2, 1);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 2, 1);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 2, 2);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 2, 2);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 3", 2);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 2, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 2, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 2, 1);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 2, 1);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 2, 2);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 2, 2);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 X", 0);
    model.study("std1").feature("stat").setIndex("loadgroup", true, 0, 0);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 y", 1);
    model.study("std1").feature("stat").setIndex("loadgroup", true, 1, 1);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 z", 2);
    model.study("std1").feature("stat").setIndex("loadgroup", true, 2, 2);
    model.study("std1").label("\u7814\u7a76\uff1a\u5e7f\u4e49\u8f7d\u8377");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("dset1shellshl", "Shell");
    model.result().dataset("dset1shellshl").set("data", "dset1");
    model.result().dataset("dset1shellshl").setIndex("topconst", "1", 3, 1);
    model.result().dataset("dset1shellshl").setIndex("bottomconst", "-1", 3, 1);
    model.result().dataset("dset1shellshl").setIndex("orientationexpr", "shell.nlX", 0);
    model.result().dataset("dset1shellshl").setIndex("displacementexpr", "arx", 0);
    model.result().dataset("dset1shellshl").setIndex("orientationexpr", "shell.nlY", 1);
    model.result().dataset("dset1shellshl").setIndex("displacementexpr", "ary", 1);
    model.result().dataset("dset1shellshl").setIndex("orientationexpr", "shell.nlZ", 2);
    model.result().dataset("dset1shellshl").setIndex("displacementexpr", "arz", 2);
    model.result().dataset("dset1shellshl").set("distanceexpr", "shell.z_pos");
    model.result().dataset("dset1shellshl").set("seplevels", false);
    model.result().dataset("dset1shellshl").set("resolution", 2);
    model.result().dataset("dset1shellshl").set("areascalefactor", "shell.ASF");
    model.result().dataset("dset1shellshl").set("linescalefactor", "shell.LSF");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1shellshl");
    model.result("pg1").setIndex("looplevel", 3, 0);
    model.result("pg1").label("\u5e94\u529b (shell)");
    model.result("pg1").set("showlegends", true);
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"shell.misesGp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("descr", "von Mises \u5e94\u529b");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"shell.u", "shell.v", "shell.w"});
    model.result("pg1").run();
    model.result().numerical().create("pev1", "EvalPoint");
    model.result().numerical("pev1").selection().set(13);
    model.result().numerical("pev1").set("expr", new String[]{"shell.el11"});
    model.result().numerical("pev1")
         .set("descr", new String[]{"\u5e94\u53d8\u5f20\u91cf\uff0c\u5c40\u90e8\u5750\u6807\u7cfb\uff0c11 \u5206\u91cf"});
    model.result().numerical("pev1").set("unit", new String[]{"1"});
    model.result().numerical("pev1").setIndex("descr", "e1", 0);
    model.result().numerical("pev1").setIndex("const", 1, 0, 1);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u70b9\u8ba1\u7b97 1");
    model.result().numerical("pev1").set("table", "tbl1");
    model.result().numerical("pev1").setResult();
    model.result().numerical().duplicate("pev2", "pev1");
    model.result().numerical("pev2").set("expr", new String[]{"shell.el22"});
    model.result().numerical("pev2")
         .set("descr", new String[]{"\u5e94\u53d8\u5f20\u91cf\uff0c\u5c40\u90e8\u5750\u6807\u7cfb\uff0c22 \u5206\u91cf"});
    model.result().numerical("pev2").set("unit", new String[]{"1"});
    model.result().numerical("pev2").setIndex("descr", "e2", 0);
    model.result().numerical("pev2").set("table", "tbl1");
    model.result().numerical("pev2").appendResult();
    model.result().numerical().duplicate("pev3", "pev1");
    model.result().numerical("pev3").selection().set(14);
    model.result().numerical("pev3").setIndex("descr", "e3", 0);
    model.result().numerical("pev3").set("table", "tbl1");
    model.result().numerical("pev3").appendResult();

    model.func().create("int1", "Interpolation");
    model.func("int1").set("source", "file");
    model.func("int1").set("filename", "frame_with_cutout_sn_curve.txt");
    model.func("int1").importData();
    model.func().create("int2", "Interpolation");
    model.func("int2").set("source", "file");
    model.func("int2").set("filename", "frame_with_cutout_load.txt");
    model.func("int2").importData();
    model.func("int2").setEntry("columnType", "col2", "value");
    model.func("int2").setEntry("columnType", "col3", "value");
    model.func("int2").setEntry("funcnames", "col2", "fX");
    model.func("int2").setEntry("funcnames", "col3", "fY");
    model.func("int2").setEntry("funcnames", "col4", "fZ");

    model.component("comp1").physics().create("ftg", "Fatigue", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/ftg", false);

    model.component("comp1").physics("ftg").label("\u5916\u90e8\u75b2\u52b3");
    model.component("comp1").physics("ftg").create("cdam1", "CumulativeDamageModel2", 1);
    model.component("comp1").physics("ftg").feature("cdam1").selection().set(9, 10, 11, 12, 13, 14, 15, 16);
    model.component("comp1").physics("ftg").feature("cdam1").set("fatigueInputPhysics", "shell");
    model.component("comp1").physics("ftg").feature("cdam1").set("throughThicknessLocation", "topOfShell");
    model.component("comp1").physics("ftg").feature("cdam1").set("cDamAnalysisType", "cDamTypeGeneralized");
    model.component("comp1").physics("ftg").feature("cdam1").set("cDamDiscretizationRange", 20);
    model.component("comp1").physics("ftg").feature("cdam1").set("m", 10000);
    model.component("comp1").physics("ftg").feature("cdam1").set("lifeCurve", "int1");
    model.component("comp1").physics("ftg").feature("cdam1").set("sf", 1000);
    model.component("comp1").physics("ftg").feature("cdam1").setIndex("cDamGenLoadHistory", 0, 0, 0);
    model.component("comp1").physics("ftg").feature("cdam1").setIndex("cDamGenLoadHistory", 0, 0, 0);
    model.component("comp1").physics("ftg").feature("cdam1").setIndex("cDamGenLoadHistory", 0, 1, 0);
    model.component("comp1").physics("ftg").feature("cdam1").setIndex("cDamGenLoadHistory", 0, 1, 0);
    model.component("comp1").physics("ftg").feature("cdam1").setIndex("cDamGenLoadHistory", 0, 2, 0);
    model.component("comp1").physics("ftg").feature("cdam1").setIndex("cDamGenLoadHistory", 0, 2, 0);
    model.component("comp1").physics("ftg").feature("cdam1").setIndex("cDamGenLoadHistory", "fX", 0, 0);
    model.component("comp1").physics("ftg").feature("cdam1").setIndex("cDamGenLoadHistory", "fY", 1, 0);
    model.component("comp1").physics("ftg").feature("cdam1").setIndex("cDamGenLoadHistory", "fZ", 2, 0);

    model.study().create("std2");
    model.study("std2").create("ftge", "Fatigue");
    model.study("std2").feature("ftge").set("ftplistmethod", "manual");
    model.study("std2").feature("ftge").set("solnum", "auto");
    model.study("std2").feature("ftge").set("usesol", "off");
    model.study("std2").feature("ftge").set("outputmap", new String[]{});
    model.study("std2").feature("ftge").setSolveFor("/physics/shell", false);
    model.study("std2").feature("ftge").setSolveFor("/physics/ftg", true);
    model.study("std2").feature("ftge").set("usesol", true);
    model.study("std2").feature("ftge").set("notsolmethod", "sol");
    model.study("std2").feature("ftge").set("notstudy", "std1");
    model.study("std2").label("\u7814\u7a76\uff1a\u5916\u90e8\u75b2\u52b3");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("expr", "root.comp1.ftg.fus");
    model.result("pg2").feature("lngr1").set("xdata", "arc");
    model.result("pg2").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr1").selection().set(9, 10, 11, 12, 13, 14, 15, 16);
    model.result("pg2").feature("lngr1").selection().inherit(false);
    model.result("pg2").feature("lngr1").selection().embedded(false);
    model.result("pg2").label("\u75b2\u52b3\u5229\u7528\u7387\u56e0\u5b50 (ftg)");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "none");
    model.result("pg3").create("hist1", "MatrixHistogram");
    model.result("pg3").feature("hist1").set("data", "dset2");
    model.result("pg3").feature("hist1").set("expr", "ftg.cdam1.csc");
    model.result("pg3").label("\u5e94\u529b\u5faa\u73af\u5206\u5e03 (ftg)");
    model.result("pg3").feature("hist1").set("xdata", "\u5e73\u5747\u5e94\u529b");
    model.result("pg3").feature("hist1").set("ydata", "\u5e94\u529b\u5e45\u503c");
    model.result("pg3").feature("hist1").create("hght", "HistogramHeight");
    model.result("pg3").feature("hist1").feature("hght")
         .label("\u9ad8\u5ea6\u8868\u8fbe\u5f0f\uff08\u7528\u4e8e\u4e09\u7ef4\u76f4\u65b9\u56fe\uff09");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "none");
    model.result("pg4").create("hist1", "MatrixHistogram");
    model.result("pg4").feature("hist1").set("data", "dset2");
    model.result("pg4").feature("hist1").set("expr", "ftg.cdam1.rus");
    model.result("pg4").label("\u75b2\u52b3\u5229\u7528\u7387\u5206\u5e03 (ftg)");
    model.result("pg4").feature("hist1").set("xdata", "\u5e73\u5747\u5e94\u529b");
    model.result("pg4").feature("hist1").set("ydata", "\u5e94\u529b\u5e45\u503c");
    model.result("pg4").feature("hist1").create("hght", "HistogramHeight");
    model.result("pg4").feature("hist1").feature("hght")
         .label("\u9ad8\u5ea6\u8868\u8fbe\u5f0f\uff08\u7528\u4e8e\u4e09\u7ef4\u76f4\u65b9\u56fe\uff09");
    model.result("pg2").run();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").set("type", "plotgroup");
    model.nodeGroup("grp1").placeAfter("plotgroup", "pg1");
    model.nodeGroup("grp1").add("plotgroup", "pg2");
    model.nodeGroup("grp1").add("plotgroup", "pg3");
    model.nodeGroup("grp1").add("plotgroup", "pg4");
    model.nodeGroup("grp1").label("\u5916\u90e8\u75b2\u52b3");

    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("lngr1").set("xdata", "expr");
    model.result("pg2").feature("lngr1")
         .set("xdataexpr", "atan2(z-0.03,x-0.04)*(dom==15)+atan2(abs(z-0.03),x+0.04)*(dom==11)+atan2(z+0.03,x+0.04)*(dom==10)+atan2(z+0.03,x-0.04)*(dom==14)");
    model.result("pg2").feature("lngr1").set("xdataunit", "\u00b0");
    model.result("pg2").feature("lngr1").set("xdatadescractive", true);
    model.result("pg2").feature("lngr1").set("xdatadescr", "\u5207\u53e3\u89d2\u5ea6");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").label("\u5916\u90e8\u75b2\u52b3\u5229\u7528\u7387\u56e0\u5b50 (ftg)");
    model.result("pg3").run();
    model.result("pg3").label("\u5916\u90e8\u5e94\u529b\u5faa\u73af\u5206\u5e03 (ftg)");
    model.result("pg3").run();
    model.result("pg3").feature("hist1").set("axisunit", "MPa");
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").label("\u5916\u90e8\u75b2\u52b3\u5229\u7528\u7387\u5206\u5e03 (ftg)");
    model.result("pg4").run();
    model.result("pg4").feature("hist1").set("axisunit", "MPa");
    model.result("pg4").run();

    model.component("comp1").physics().create("ftg2", "Fatigue", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/ftg2", false);
    model.study("std2").feature("ftge").setSolveFor("/physics/ftg2", false);

    model.component("comp1").physics("ftg2").label("\u5185\u90e8\u75b2\u52b3");
    model.component("comp1").physics("ftg2").create("cdam1", "CumulativeDamageModel2", 1);
    model.component("comp1").physics("ftg2").feature("cdam1").selection().set(9, 10, 11, 12, 13, 14, 15, 16);
    model.component("comp1").physics("ftg2").feature("cdam1").set("fatigueInputPhysics", "shell");
    model.component("comp1").physics("ftg2").feature("cdam1").set("throughThicknessLocation", "bottomOfShell");
    model.component("comp1").physics("ftg2").feature("cdam1").set("cDamAnalysisType", "cDamTypeGeneralized");
    model.component("comp1").physics("ftg2").feature("cdam1").set("cDamDiscretizationRange", 20);
    model.component("comp1").physics("ftg2").feature("cdam1").set("m", 10000);
    model.component("comp1").physics("ftg2").feature("cdam1").set("lifeCurve", "int1");
    model.component("comp1").physics("ftg2").feature("cdam1").set("sf", 1000);
    model.component("comp1").physics("ftg2").feature("cdam1").setIndex("cDamGenLoadHistory", 0, 0, 0);
    model.component("comp1").physics("ftg2").feature("cdam1").setIndex("cDamGenLoadHistory", 0, 0, 0);
    model.component("comp1").physics("ftg2").feature("cdam1").setIndex("cDamGenLoadHistory", 0, 1, 0);
    model.component("comp1").physics("ftg2").feature("cdam1").setIndex("cDamGenLoadHistory", 0, 1, 0);
    model.component("comp1").physics("ftg2").feature("cdam1").setIndex("cDamGenLoadHistory", 0, 2, 0);
    model.component("comp1").physics("ftg2").feature("cdam1").setIndex("cDamGenLoadHistory", 0, 2, 0);
    model.component("comp1").physics("ftg2").feature("cdam1").setIndex("cDamGenLoadHistory", "fX", 0, 0);
    model.component("comp1").physics("ftg2").feature("cdam1").setIndex("cDamGenLoadHistory", "fY", 1, 0);
    model.component("comp1").physics("ftg2").feature("cdam1").setIndex("cDamGenLoadHistory", "fZ", 2, 0);

    model.study().create("std3");
    model.study("std3").create("ftge", "Fatigue");
    model.study("std3").feature("ftge").set("ftplistmethod", "manual");
    model.study("std3").feature("ftge").set("solnum", "auto");
    model.study("std3").feature("ftge").set("usesol", "off");
    model.study("std3").feature("ftge").set("outputmap", new String[]{});
    model.study("std3").feature("ftge").setSolveFor("/physics/shell", false);
    model.study("std3").feature("ftge").setSolveFor("/physics/ftg", false);
    model.study("std3").feature("ftge").setSolveFor("/physics/ftg2", true);
    model.study("std3").feature("ftge").set("usesol", true);
    model.study("std3").feature("ftge").set("notsolmethod", "sol");
    model.study("std3").feature("ftge").set("notstudy", "std1");
    model.study("std3").label("\u7814\u7a76\uff1a\u5185\u90e8\u75b2\u52b3");
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("expr", "root.comp1.ftg2.fus");
    model.result("pg5").feature("lngr1").set("xdata", "arc");
    model.result("pg5").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg5").feature("lngr1").selection().set(9, 10, 11, 12, 13, 14, 15, 16);
    model.result("pg5").feature("lngr1").selection().inherit(false);
    model.result("pg5").feature("lngr1").selection().embedded(false);
    model.result("pg5").label("\u75b2\u52b3\u5229\u7528\u7387\u56e0\u5b50 (ftg2)");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").set("data", "none");
    model.result("pg6").create("hist1", "MatrixHistogram");
    model.result("pg6").feature("hist1").set("data", "dset3");
    model.result("pg6").feature("hist1").set("expr", "ftg2.cdam1.csc");
    model.result("pg6").label("\u5e94\u529b\u5faa\u73af\u5206\u5e03 (ftg2)");
    model.result("pg6").feature("hist1").set("xdata", "\u5e73\u5747\u5e94\u529b");
    model.result("pg6").feature("hist1").set("ydata", "\u5e94\u529b\u5e45\u503c");
    model.result("pg6").feature("hist1").create("hght", "HistogramHeight");
    model.result("pg6").feature("hist1").feature("hght")
         .label("\u9ad8\u5ea6\u8868\u8fbe\u5f0f\uff08\u7528\u4e8e\u4e09\u7ef4\u76f4\u65b9\u56fe\uff09");
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").set("data", "none");
    model.result("pg7").create("hist1", "MatrixHistogram");
    model.result("pg7").feature("hist1").set("data", "dset3");
    model.result("pg7").feature("hist1").set("expr", "ftg2.cdam1.rus");
    model.result("pg7").label("\u75b2\u52b3\u5229\u7528\u7387\u5206\u5e03 (ftg2)");
    model.result("pg7").feature("hist1").set("xdata", "\u5e73\u5747\u5e94\u529b");
    model.result("pg7").feature("hist1").set("ydata", "\u5e94\u529b\u5e45\u503c");
    model.result("pg7").feature("hist1").create("hght", "HistogramHeight");
    model.result("pg7").feature("hist1").feature("hght")
         .label("\u9ad8\u5ea6\u8868\u8fbe\u5f0f\uff08\u7528\u4e8e\u4e09\u7ef4\u76f4\u65b9\u56fe\uff09");
    model.result("pg5").run();

    model.nodeGroup().create("grp2", "Results");
    model.nodeGroup("grp2").set("type", "plotgroup");
    model.nodeGroup("grp2").placeAfter("plotgroup", "pg1");
    model.nodeGroup().move("grp2", 1);
    model.nodeGroup("grp2").add("plotgroup", "pg5");
    model.nodeGroup("grp2").add("plotgroup", "pg6");
    model.nodeGroup("grp2").add("plotgroup", "pg7");
    model.nodeGroup("grp2").label("\u5185\u90e8\u75b2\u52b3");

    model.result("pg5").run();
    model.result("pg5").label("\u5185\u90e8\u75b2\u52b3\u5229\u7528\u7387\u56e0\u5b50 (ftg2)");
    model.result("pg5").run();
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1")
         .set("xdataexpr", "atan2(z-0.03,x-0.04)*(dom==15)+atan2(abs(z-0.03),x+0.04)*(dom==11)+atan2(z+0.03,x+0.04)*(dom==10)+atan2(z+0.03,x-0.04)*(dom==14)");
    model.result("pg5").feature("lngr1").set("xdataunit", "\u00b0");
    model.result("pg5").feature("lngr1").set("xdatadescractive", true);
    model.result("pg5").feature("lngr1").set("xdatadescr", "\u5207\u53e3\u89d2\u5ea6");
    model.result("pg5").run();
    model.result("pg6").run();
    model.result("pg6").label("\u5185\u90e8\u5e94\u529b\u5faa\u73af\u5206\u5e03 (ftg2)");
    model.result("pg6").run();
    model.result("pg6").feature("hist1").set("axisunit", "MPa");
    model.result("pg6").run();
    model.result("pg7").run();
    model.result("pg7").label("\u5185\u90e8\u75b2\u52b3\u5229\u7528\u7387\u5206\u5e03 (ftg2)");
    model.result("pg7").run();
    model.result("pg7").feature("hist1").set("axisunit", "MPa");
    model.result("pg7").run();
    model.result("pg5").run();

    model.title("\u968f\u673a\u975e\u6bd4\u4f8b\u8f7d\u8377\u7684\u75b2\u52b3\u54cd\u5e94");

    model
         .description("\u5177\u6709\u4e2d\u5fc3\u5207\u53e3\u7684\u8584\u58c1\u6846\u67b6\u53d7\u5230\u968f\u673a\u8f7d\u8377\u4f5c\u7528\u3002\u5c3d\u7ba1\u5e94\u529b\u8fdc\u4f4e\u4e8e\u6750\u6599\u7684\u5c48\u670d\u6c34\u5e73\uff0c\u4f46\u635f\u4f24\u4f1a\u968f\u7740\u8f7d\u8377\u5386\u7a0b\u9010\u6e10\u7d2f\u79ef\u3002\u672c\u4f8b\u4f7f\u7528\u201c\u96e8\u6d41\u8ba1\u6570\u201d\u7b97\u6cd5\u6765\u5b9a\u4e49\u8f7d\u8377\u60c5\u51b5\uff0c\u5e76\u4f7f\u7528 Palmgren-Miner \u7ebf\u6027\u635f\u4f24\u6a21\u578b\u6765\u91cf\u5316\u635f\u4f24\u7a0b\u5ea6\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("frame_with_cutout.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
