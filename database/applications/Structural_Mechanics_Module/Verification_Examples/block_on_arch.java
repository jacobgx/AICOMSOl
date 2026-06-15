/*
 * block_on_arch.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:22 by COMSOL 6.3.0.290. */
public class block_on_arch {

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
    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics("solid").field("displacement").field("u");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/shell", true);
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("R_arch", "1200[mm]", "\u62f1\u534a\u5f84");
    model.param().set("d", "30[mm]", "\u62f1\u539a\u5ea6");
    model.param().set("seg_arch", "40[deg]", "\u62f1\u6bb5");
    model.param().set("R_block", "350[mm]", "\u5757\u4f53\u534a\u5f84");
    model.param().set("height_block", "50[mm]", "\u5757\u4f53\u9ad8\u5ea6");
    model.param().set("seg_block", "40[deg]", "\u5757\u4f53\u6bb5");
    model.param().set("n_elem_arch", "50", "\u7f51\u683c\u5355\u5143\u6570\uff0c\u62f1");
    model.param().set("n_elem_block", "10", "\u7f51\u683c\u5355\u5143\u6570\uff0c\u5757\u4f53");
    model.param().set("F_ref", "-1[N/mm^2]", "\u53c2\u8003\u8f7d\u8377\u503c");
    model.param().set("max_disp", "120[mm]", "\u6700\u5927\u4f4d\u79fb");
    model.param().set("para", "0", "\u8f7d\u8377\u53c2\u6570");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "xz");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("type", "curve");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", "R_arch");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("angle", "seg_arch");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1")
         .set("pos", new String[]{"0", "-R_arch"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("rot", "90-seg_arch/2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("del1").selection("input").set("c1", 2, 3);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("del1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pare1", "PartitionEdges");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pare1").selection("edge").set("del1", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pare1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2").set("r", "R_block");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2").set("angle", "seg_block");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2")
         .set("pos", new String[]{"0", "R_block"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2").set("rot", "-90-seg_block/2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"R_block", "height_block"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("pos", new String[]{"-R_block/2", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("int1", "Intersection");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("int1").selection("input").set("c2", "r1");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", false);
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "d", 0);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u62f1\u5f62\u7ed3\u6784");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init();
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("ext1(1)");
    model.component("comp1").geom("geom1").feature("sel1").set("color", "4");
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").feature().duplicate("sel2", "sel1");
    model.component("comp1").geom("geom1").feature("sel2").label("\u5757\u4f53");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("ext1(2)");
    model.component("comp1").geom("geom1").feature("sel2").set("color", "12");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"10[GPa]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.2"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"1"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").selection().geom("geom1", 2);
    model.component("comp1").material("mat2").selection().named("geom1_sel1_bnd");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", new String[]{"70[GPa]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", new String[]{"0.3"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"1"});

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("aveop1").selection().set(11);
    model.component("comp1").cpl().duplicate("aveop2", "aveop1");
    model.component("comp1").cpl("aveop2").selection().set(3);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("disp_block", "aveop1(-w)");
    model.component("comp1").variable("var1").descr("disp_block", "\u5757\u4f4d\u79fb");
    model.component("comp1").variable("var1").set("disp_arch", "aveop2(-w)");
    model.component("comp1").variable("var1").descr("disp_arch", "\u62f1\u5f62\u7ed3\u6784\u4f4d\u79fb");

    model.component("comp1").pair().create("p1", "Contact");
    model.component("comp1").pair("p1").source().set(4, 8);
    model.component("comp1").pair("p1").destination().named("geom1_sel1_bnd");

    model.component("comp1").physics("shell").selection().named("geom1_sel1_bnd");
    model.component("comp1").physics("shell").feature("to1").set("d", "d");
    model.component("comp1").physics("shell").feature("to1").set("OffsetDefinition", "top");
    model.component("comp1").physics("shell").create("disp1", "Displacement1", 1);
    model.component("comp1").physics("shell").feature("disp1").selection().set(1, 7);
    model.component("comp1").physics("shell").feature("disp1").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("shell").feature("disp1").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("shell").feature("disp1").set("RotationType", "RotationGroup");
    model.component("comp1").physics("shell").create("sym1", "SymmetrySolid1", 1);
    model.component("comp1").physics("shell").feature("sym1").selection().set(2, 3, 5, 6);
    model.component("comp1").physics("shell").feature("dcnt1").set("ContactMethodCtrl", "AugmentedLagrange");
    model.component("comp1").physics("solid").create("disp1", "Displacement1", 1);
    model.component("comp1").physics("solid").feature("disp1").selection().set(13, 19);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 2);
    model.component("comp1").physics("solid").feature("sym1").selection().set(5, 6);
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(7);
    model.component("comp1").physics("solid").feature("bndl1")
         .set("forceReferenceArea", new String[]{"0", "0", "load*F_ref"});
    model.component("comp1").physics("solid").create("ge1", "GlobalEquations", -1);
    model.component("comp1").physics("solid").feature("ge1").setIndex("name", "load", 0, 0);
    model.component("comp1").physics("solid").feature("ge1").setIndex("equation", "disp_block-para*max_disp", 0, 0);
    model.component("comp1").physics("solid").feature("ge1").set("SourceTermQuantity", "displacement");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().named("geom1_sel1_bnd");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(2, 5);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", "n_elem_arch");
    model.component("comp1").mesh("mesh1").create("map2", "Map");
    model.component("comp1").mesh("mesh1").feature("map2").selection().set(5);
    model.component("comp1").mesh("mesh1").feature("map2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis1").selection().set(10, 17);
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis1").set("numelem", "n_elem_block");
    model.component("comp1").mesh("mesh1").feature("map2").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis2").selection().set(9, 20);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "R_arch", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "R_arch", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "para", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,0.05,1)", 0);
    model.study("std1").feature("stat").set("usestol", true);
    model.study("std1").feature("stat").set("stol", "0.0005");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v1").feature("comp1_ODE1").set("scalemethod", "manual");
    model.sol("sol1").feature("s1").feature("se1").feature("ss1")
         .set("segvar", new String[]{"comp1_u", "comp1_ar", "comp1_ODE1"});
    model.sol("sol1").feature("s1").feature("se1").feature().remove("ss2");

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
    model.result("pg1").setIndex("looplevel", 21, 0);
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
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 21, 0);
    model.result("pg2").label("\u5e94\u529b (solid)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").create("vol1", "Volume");
    model.result("pg2").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg2").feature("vol1").set("threshold", "manual");
    model.result("pg2").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("vol1").set("colortable", "Rainbow");
    model.result("pg2").feature("vol1").set("colortabletrans", "none");
    model.result("pg2").feature("vol1").set("colorscalemode", "linear");
    model.result("pg2").feature("vol1").set("resolution", "custom");
    model.result("pg2").feature("vol1").set("refine", 2);
    model.result("pg2").feature("vol1").set("colortable", "Prism");
    model.result("pg2").feature("vol1").create("def", "Deform");
    model.result("pg2").feature("vol1").feature("def").set("scaleactive", true);
    model.result("pg2").feature("vol1").feature("def").set("scale", "1");
    model.result("pg2").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 0, 3);
    model.result().configuration("prfu1").set("applytosamedims", true);
    model.result().configuration("prfu1").apply();
    model.result("pg1").run();
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("data", "dset1");
    model.result("pg1").feature("vol1").set("solutionparams", "parent");
    model.result("pg1").feature("vol1").set("expr", "solid.misesGp");
    model.result("pg1").feature("vol1").set("titletype", "none");
    model.result("pg1").feature("vol1").set("inheritplot", "surf1");
    model.result("pg1").feature("vol1").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").run();

    model.component("comp1").view("view1").set("showgrid", false);

    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 21, 0);
    model.result("pg3").label("\u63a5\u89e6\u529b (shell)");
    model.result("pg3").set("showlegends", true);
    model.result("pg3").set("titletype", "label");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"1"});
    model.result("pg3").feature("surf1").label("\u7070\u8272\u8868\u9762");
    model.result("pg3").feature("surf1").set("coloring", "uniform");
    model.result("pg3").feature("surf1").set("color", "gray");
    model.result("pg3").feature("surf1").create("def", "Deform");
    model.result("pg3").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg3").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg3").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg3").feature("surf1").feature("def").set("scale", 1);
    model.result("pg3").feature("surf1").create("sel1", "Selection");
    model.result("pg3").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg3").feature("surf1").feature("sel1").selection().set(1, 2);
    model.result("pg3").feature("surf1").create("tran1", "Transparency");
    model.result("pg3").feature("surf1").feature("tran1").set("transparency", 0.8);
    model.result("pg3").create("arws1", "ArrowSurface");
    model.result("pg3").feature("arws1").set("arrowbase", "head");
    model.result("pg3").feature("arws1")
         .set("expr", new String[]{"shell.dcnt1.Tnx", "shell.dcnt1.Tny", "shell.dcnt1.Tnz"});
    model.result("pg3").feature("arws1").set("placement", "gausspoints");
    model.result("pg3").feature("arws1").set("gporder", 4);
    model.result("pg3").feature("arws1").label("\u63a5\u89e6 1, \u538b\u529b");
    model.result("pg3").feature("arws1").set("inheritplot", "none");
    model.result("pg3").feature("arws1").set("color", "green");
    model.result("pg3").feature("arws1").create("col", "Color");
    model.result("pg3").feature("arws1").feature("col").set("colortable", "Rainbow");
    model.result("pg3").feature("arws1").feature("col").set("colortabletrans", "none");
    model.result("pg3").feature("arws1").feature("col").set("colorscalemode", "linear");
    model.result("pg3").feature("arws1").feature("col").set("colordata", "arrowlength");
    model.result("pg3").feature("arws1").feature("col").set("coloring", "gradient");
    model.result("pg3").feature("arws1").feature("col").set("topcolor", "green");
    model.result("pg3").feature("arws1").feature("col").set("bottomcolor", "custom");
    model.result("pg3").feature("arws1").feature("col")
         .set("custombottomcolor", new double[]{0.509804, 0.54902, 0.509804});
    model.result("pg3").feature("arws1").create("def", "Deform");
    model.result("pg3").feature("arws1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg3").feature("arws1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg3").feature("arws1").feature("def").set("scaleactive", true);
    model.result("pg3").feature("arws1").feature("def").set("scale", 1);
    model.result("pg3").feature().move("surf1", 1);
    model.result("pg3").label("\u63a5\u89e6\u529b (shell)");
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 9, 0);
    model.result("pg3").run();
    model.result("pg3").feature("arws1").set("scaleactive", true);
    model.result("pg3").feature("arws1").set("scale", "5e-4");
    model.result("pg3").feature("surf1").feature("sel1").selection().named("geom1_sel1_bnd");
    model.result("pg3").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("plotgroup", "pg3");
    model.result().export("anim1").run();
    model.result().export("anim1").set("framesel", "all");
    model.result().export("anim1").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u8f7d\u8377 vs. \u6320\u5ea6");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "disp_block", 0);
    model.result("pg4").feature("glob1").setIndex("unit", "mm", 0);
    model.result("pg4").feature("glob1").setIndex("expr", "disp_arch", 1);
    model.result("pg4").feature("glob1").setIndex("unit", "mm", 1);
    model.result("pg4").feature("glob1").set("xdata", "expr");
    model.result("pg4").feature("glob1").set("xdataexpr", "load");
    model.result("pg4").feature("glob1").set("linemarker", "cycle");
    model.result("pg4").feature("glob1").set("markerpos", "interp");
    model.result("pg4").run();
    model.result("pg4").set("switchxy", true);
    model.result("pg4").set("legendpos", "upperleft");
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "\u6320\u5ea6 (mm)");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u53d8\u5f62");
    model.result("pg5").setIndex("looplevelinput", "manualindices", 0);
    model.result("pg5").setIndex("looplevelindices", "range(1,4,21)", 0);
    model.result("pg5").set("titletype", "none");
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg5").feature("lngr1").set("linewidth", "preference");
    model.result("pg5").feature("lngr1").selection().set(2, 5);
    model.result("pg5").feature("lngr1").set("expr", "z");
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1").set("xdataexpr", "x");
    model.result("pg5").feature("lngr1").set("linewidth", 2);
    model.result("pg5").feature().duplicate("lngr2", "lngr1");
    model.result("pg5").run();
    model.result("pg5").feature("lngr2").selection().set(9, 10, 14, 17, 20);
    model.result("pg5").feature("lngr2").set("linestyle", "dashed");
    model.result("pg5").feature("lngr2").set("linecolor", "cyclereset");
    model.result("pg5").run();
    model.result("pg5").feature("lngr1").set("legend", true);
    model.result("pg5").feature("lngr1").set("legendprefix", "para = ");
    model.result("pg5").run();
    model.result("pg1").run();

    model.title("\u5757\u4f53\u6324\u538b\u62f1");

    model
         .description("\u8fd9\u4e2a\u6982\u5ff5\u6027\u793a\u4f8b\u6f14\u793a\u5982\u4f55\u8ba1\u7b97\u5177\u6709\u63a5\u89e6\u7684\u6a21\u578b\u4e2d\u7684\u4e34\u754c\u70b9\u3002\u8be5\u6a21\u578b\u6a21\u62df\u5757\u4f53\u6324\u538b\u62f1\u7684\u60c5\u51b5\uff0c\u5176\u4e2d\u901a\u8fc7\u201c\u56fa\u4f53\u529b\u5b66\u201d\u63a5\u53e3\u4e3a\u5757\u4f53\u5efa\u6a21\uff0c\u901a\u8fc7\u201c\u58f3\u201d\u63a5\u53e3\u4e3a\u62f1\u5efa\u6a21\uff0c\u4f7f\u7528\u589e\u5e7f\u62c9\u683c\u6717\u65e5\u6cd5\u6c42\u89e3\u63a5\u89e6\u95ee\u9898\u3002\u5757\u4f53\u53d7\u5230\u4f4d\u79fb\u63a7\u5236\u7684\u8fb9\u754c\u8f7d\u8377\uff0c\u9010\u6e10\u5bf9\u62f1\u4ea7\u751f\u6324\u538b\u4f5c\u7528\u3002\u5728\u8d1f\u8f7d\u8fc7\u7a0b\u4e2d\uff0c\u62f1\u53d1\u751f\u7a81\u5f39\u8df3\u53d8\u7279\u6027\uff0c\u4f46\u4ecd\u7136\u53ef\u4ee5\u5f97\u5230\u7a33\u6001\u89e3\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("block_on_arch.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
