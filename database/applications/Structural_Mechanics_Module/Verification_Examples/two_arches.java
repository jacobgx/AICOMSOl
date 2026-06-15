/*
 * two_arches.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:27 by COMSOL 6.3.0.290. */
public class two_arches {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("shell", "Shell", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/shell", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Ri_upper", "80[mm]", "\u4e0a\u62f1\u534a\u5f84");
    model.param().set("Ri_lower", "100[mm]", "\u4e0b\u62f1\u534a\u5f84");
    model.param().set("d", "3[mm]", "\u62f1\u539a\u5ea6");
    model.param().set("seg_upper", "80[deg]", "\u4e0a\u62f1\u6bb5");
    model.param().set("seg_lower", "100[deg]", "\u4e0b\u62f1\u6bb5");
    model.param().set("n_elem_upper", "30", "\u7f51\u683c\u5355\u5143\u6570\uff0c\u4e0a\u62f1");
    model.param().set("n_elem_lower", "75", "\u7f51\u683c\u5355\u5143\u6570\uff0c\u4e0b\u62f1");
    model.param().set("F_ref", "-1[N/mm]", "\u53c2\u8003\u8f7d\u8377\u503c");
    model.param().set("max_disp", "60[mm]", "\u6700\u5927\u4f4d\u79fb");
    model.param().set("para", "0", "\u8f7d\u8377\u53c2\u6570");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "xz");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("type", "curve");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", "Ri_upper");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("angle", "seg_upper");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1")
         .set("pos", new String[]{"0", "Ri_upper"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("rot", "-90-seg_upper/2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2").set("type", "curve");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2").set("r", "Ri_lower");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2").set("angle", "seg_lower");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2")
         .set("pos", new String[]{"0", "-Ri_lower"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2").set("rot", "90-seg_lower/2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("del1").selection("input").set("c1", 2, 3);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("del1").selection("input").set("c2", 3, 4);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("del1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pare1", "PartitionEdges");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pare1").selection("edge")
         .set("del1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp1").set("unite", false);
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "d", 0);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u4e0a\u62f1");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init();
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("ext1(2)");
    model.component("comp1").geom("geom1").feature("sel1").set("color", "4");
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").feature().duplicate("sel2", "sel1");
    model.component("comp1").geom("geom1").feature("sel2").label("\u4e0b\u62f1");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("ext1(1)");
    model.component("comp1").geom("geom1").feature("sel2").set("color", "12");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").selection().named("geom1_sel2_bnd");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"40[GPa]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.2"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"1"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").selection().named("geom1_sel1_bnd");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", new String[]{"20[GPa]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", new String[]{"0.3"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"1"});

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("aveop1").selection().set(9);
    model.component("comp1").cpl().duplicate("aveop2", "aveop1");
    model.component("comp1").cpl("aveop2").selection().set(3);
    model.component("comp1").cpl().duplicate("aveop3", "aveop2");
    model.component("comp1").cpl("aveop3").selection().set(7, 11);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("disp_upper", "aveop1(-w)");
    model.component("comp1").variable("var1").descr("disp_upper", "\u4e0a\u62f1\u4f4d\u79fb");
    model.component("comp1").variable("var1").set("disp_lower", "aveop2(-w)");
    model.component("comp1").variable("var1").descr("disp_lower", "\u4e0b\u62f1\u4f4d\u79fb");
    model.component("comp1").variable("var1").set("disp_load", "aveop3(-w)");
    model.component("comp1").variable("var1").descr("disp_load", "\u5e73\u5747\u8f7d\u8377\u70b9\u4f4d\u79fb");

    model.component("comp1").pair().create("p1", "Contact");
    model.component("comp1").pair("p1").source().named("geom1_sel1_bnd");
    model.component("comp1").pair("p1").destination().named("geom1_sel2_bnd");

    model.component("comp1").physics("shell").feature("to1").set("d", "d");
    model.component("comp1").physics("shell").feature("to1").set("OffsetDefinition", "top");
    model.component("comp1").physics("shell").create("sym1", "SymmetrySolid1", 1);
    model.component("comp1").physics("shell").feature("sym1").selection().set(2, 3, 5, 6, 9, 10, 12, 13);
    model.component("comp1").physics("shell").create("disp1", "Displacement1", 1);
    model.component("comp1").physics("shell").feature("disp1").selection().set(8, 14);
    model.component("comp1").physics("shell").feature("disp1").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("shell").feature("disp1").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("shell").create("disp2", "Displacement1", 1);
    model.component("comp1").physics("shell").feature("disp2").selection().set(1, 7);
    model.component("comp1").physics("shell").feature("disp2").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("shell").feature("disp2").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("shell").feature("disp2").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("shell").feature("disp2").set("RotationType", "RotationGroup");
    model.component("comp1").physics("shell").create("el1", "EdgeLoad", 1);
    model.component("comp1").physics("shell").feature("el1").selection().set(8, 14);
    model.component("comp1").physics("shell").feature("el1")
         .set("forceReferenceLength", new String[]{"0", "0", "load*F_ref"});
    model.component("comp1").physics("shell").create("ge1", "GlobalEquations", -1);
    model.component("comp1").physics("shell").feature("ge1").setIndex("name", "load", 0, 0);
    model.component("comp1").physics("shell").feature("ge1").setIndex("equation", "disp_upper-max_disp*para", 0, 0);
    model.component("comp1").physics("shell").feature("ge1")
         .setIndex("description", "\u8f7d\u8377\u56e0\u5b50", 0, 0);
    model.component("comp1").physics("shell").feature("ge1").set("SourceTermQuantity", "displacement");
    model.component("comp1").physics("shell").create("sym2", "SymmetrySolid1", 1);
    model.component("comp1").physics("shell").feature("sym2").selection().set(4, 11);

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().all();
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(2, 5);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", "n_elem_lower");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(9, 12);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", "n_elem_upper");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "Ri_upper", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "Ri_upper", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "para", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,0.02,1)", 0);
    model.study("std1").feature("stat").set("usestol", true);
    model.study("std1").feature("stat").set("stol", "0.0005");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").feature("p1").set("paramtuning", true);
    model.sol("sol1").feature("s1").feature("p1").set("pminstep", "1e-6");
    model.sol("sol1").feature("s1").feature("p1").create("st1", "StopCondition");
    model.sol("sol1").feature("s1").feature("p1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("s1").feature("p1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("s1").feature("p1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("s1").feature("p1").feature("st1")
         .setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("s1").feature("p1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("s1").feature("p1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("s1").feature("p1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("s1").feature("p1").feature("st1")
         .setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("s1").feature("p1").feature("st1").setIndex("stopcondarr", "comp1.load/250", 0);
    model.sol("sol1").feature("s1").feature("p1").feature("st1").set("storestopcondsol", "stepbefore");
    model.sol("sol1").feature("s1").feature("fc1").set("dtech", "const");

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
    model.result("pg1").setIndex("looplevel", 46, 0);
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
    model.result("pg1").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("surf1").feature("def").set("scale", "1");
    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 0, 3);
    model.result().configuration("prfu1").set("applytosamedims", true);
    model.result().configuration("prfu1").apply();
    model.result("pg1").run();
    model.result("pg1").run();

    model.component("comp1").view("view1").set("showgrid", false);

    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 46, 0);
    model.result("pg2").label("\u63a5\u89e6\u529b (shell)");
    model.result("pg2").set("showlegends", true);
    model.result("pg2").set("titletype", "label");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"1"});
    model.result("pg2").feature("surf1").label("\u7070\u8272\u8868\u9762");
    model.result("pg2").feature("surf1").set("coloring", "uniform");
    model.result("pg2").feature("surf1").set("color", "gray");
    model.result("pg2").feature("surf1").create("def", "Deform");
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg2").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg2").feature("surf1").feature("def").set("scale", 1);
    model.result("pg2").feature("surf1").create("sel1", "Selection");
    model.result("pg2").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg2").feature("surf1").feature("sel1").selection().set(1, 2, 3, 4);
    model.result("pg2").feature("surf1").create("tran1", "Transparency");
    model.result("pg2").feature("surf1").feature("tran1").set("transparency", 0.8);
    model.result("pg2").create("arws1", "ArrowSurface");
    model.result("pg2").feature("arws1").set("arrowbase", "head");
    model.result("pg2").feature("arws1")
         .set("expr", new String[]{"shell.dcnt1.Tnx", "shell.dcnt1.Tny", "shell.dcnt1.Tnz"});
    model.result("pg2").feature("arws1").set("placement", "gausspoints");
    model.result("pg2").feature("arws1").set("gporder", 4);
    model.result("pg2").feature("arws1").label("\u63a5\u89e6 1, \u538b\u529b");
    model.result("pg2").feature("arws1").set("inheritplot", "none");
    model.result("pg2").feature("arws1").set("color", "green");
    model.result("pg2").feature("arws1").create("col", "Color");
    model.result("pg2").feature("arws1").feature("col").set("colortable", "Rainbow");
    model.result("pg2").feature("arws1").feature("col").set("colortabletrans", "none");
    model.result("pg2").feature("arws1").feature("col").set("colorscalemode", "linear");
    model.result("pg2").feature("arws1").feature("col").set("colordata", "arrowlength");
    model.result("pg2").feature("arws1").feature("col").set("coloring", "gradient");
    model.result("pg2").feature("arws1").feature("col").set("topcolor", "green");
    model.result("pg2").feature("arws1").feature("col").set("bottomcolor", "custom");
    model.result("pg2").feature("arws1").feature("col")
         .set("custombottomcolor", new double[]{0.509804, 0.54902, 0.509804});
    model.result("pg2").feature("arws1").create("def", "Deform");
    model.result("pg2").feature("arws1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("arws1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg2").feature("arws1").feature("def").set("scaleactive", true);
    model.result("pg2").feature("arws1").feature("def").set("scale", 1);
    model.result("pg2").feature().move("surf1", 1);
    model.result("pg2").label("\u63a5\u89e6\u529b (shell)");
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 31, 0);
    model.result("pg2").run();
    model.result("pg2").feature("arws1").set("scaleactive", true);
    model.result("pg2").feature("arws1").set("scale", "2e-4");
    model.result("pg2").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("plotgroup", "pg2");
    model.result().export("anim1").run();
    model.result().export("anim1").set("framesel", "all");
    model.result().export("anim1").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u8f7d\u8377 vs. \u6320\u5ea6");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").setIndex("expr", "disp_upper", 0);
    model.result("pg3").feature("glob1").setIndex("unit", "mm", 0);
    model.result("pg3").feature("glob1").setIndex("expr", "disp_lower", 1);
    model.result("pg3").feature("glob1").setIndex("unit", "mm", 1);
    model.result("pg3").feature("glob1").setIndex("expr", "disp_load", 2);
    model.result("pg3").feature("glob1").setIndex("unit", "mm", 2);
    model.result("pg3").feature("glob1").set("xdata", "expr");
    model.result("pg3").feature("glob1").set("xdataexpr", "load");
    model.result("pg3").feature("glob1").set("linemarker", "cycle");
    model.result("pg3").feature("glob1").set("markerpos", "interp");
    model.result("pg3").run();
    model.result("pg3").set("switchxy", true);
    model.result("pg3").set("legendpos", "upperleft");
    model.result("pg3").set("xlabelactive", true);
    model.result("pg3").set("xlabel", "\u6320\u5ea6 (mm)");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u53d8\u5f62");
    model.result("pg4").setIndex("looplevelinput", "manualindices", 0);
    model.result("pg4").setIndex("looplevelindices", "range(1,10,41)", 0);
    model.result("pg4").set("titletype", "none");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr1").set("linewidth", "preference");
    model.result("pg4").feature("lngr1").selection().set(2, 5);
    model.result("pg4").feature("lngr1").set("expr", "z");
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").set("xdataexpr", "x");
    model.result("pg4").feature("lngr1").set("linewidth", 2);
    model.result("pg4").feature().duplicate("lngr2", "lngr1");
    model.result("pg4").run();
    model.result("pg4").feature("lngr2").selection().set(9, 12);
    model.result("pg4").feature("lngr2").set("linestyle", "dashed");
    model.result("pg4").feature("lngr2").set("linecolor", "cyclereset");
    model.result("pg4").run();
    model.result("pg4").feature("lngr1").set("legend", true);
    model.result("pg4").feature("lngr1").set("legendprefix", "para = ");
    model.result("pg4").run();
    model.result("pg1").run();

    model.title("\u4e24\u4e2a\u63a5\u89e6\u62f1\u7684\u4e0d\u7a33\u5b9a\u6027");

    model
         .description("\u8fd9\u4e2a\u6982\u5ff5\u793a\u4f8b\u6f14\u793a\u5982\u4f55\u8ba1\u7b97\u63a5\u89e6\u6a21\u578b\u4e2d\u7684\u4e34\u754c\u70b9\u3002\u8be5\u6a21\u578b\u7531\u4e24\u4e2a\u76f8\u4e92\u63a5\u89e6\u7684\u62f1\u7ec4\u6210\uff0c\u901a\u8fc7\u201c\u58f3\u201d\u63a5\u53e3\u8fdb\u884c\u5efa\u6a21\uff0c\u5176\u4e2d\u4f7f\u7528\u7f5a\u51fd\u6570\u6cd5\u6c42\u89e3\u63a5\u89e6\u95ee\u9898\u3002\u4e0a\u62f1\u53d7\u5230\u4f4d\u79fb\u63a7\u5236\u7684\u8fb9\u8f7d\u8377\uff0c\u5bf9\u4e0b\u62f1\u4ea7\u751f\u6324\u538b\u4f5c\u7528\u3002\u5728\u8d1f\u8f7d\u8fc7\u7a0b\u4e2d\uff0c\u6211\u4eec\u89c2\u5bdf\u5230\u4e0b\u62f1\u53d1\u751f\u7a81\u5f39\u8df3\u53d8\u7279\u6027\uff0c\u5176\u4e2d\u591a\u4e2a\u4e34\u754c\u70b9\u4e0e\u4fa7\u79fb\u5931\u7a33\u6709\u5173\u3002\u4e3a\u4e86\u5f97\u5230\u7a33\u5b9a\u7684\u53c2\u8003\u89e3\uff0c\u6211\u4eec\u5bf9\u4e24\u4e2a\u62f1\u4e2d\u8fb9\u7684\u4fa7\u5411\u8fd0\u52a8\u65bd\u52a0\u4e86\u7ea6\u675f\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("two_arches.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
