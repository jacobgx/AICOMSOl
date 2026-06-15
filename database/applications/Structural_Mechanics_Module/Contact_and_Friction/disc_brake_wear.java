/*
 * disc_brake_wear.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:02 by COMSOL 6.3.0.290. */
public class disc_brake_wear {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Contact_and_Friction");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.param().set("v", "60[km/h]");
    model.param().descr("v", "\u8f66\u901f");
    model.param().set("r_wheel", "0.25[m]");
    model.param().descr("r_wheel", "\u8f66\u8f6e\u534a\u5f84");
    model.param().set("omega", "v/r_wheel");
    model.param().descr("omega", "\u8f66\u8f6e\u89d2\u901f\u5ea6");
    model.param().set("wear_accel", "100");
    model.param().descr("wear_accel", "\u78e8\u635f\u52a0\u901f\u5ea6\u56e0\u5b50");

    model.component("comp1").geom("geom1").insertFile("disc_brake_geom_sequence.mph", "geom1");

    model.component("comp1").view("view1").set("showgrid", false);
    model.component("comp1").view("view1").set("showaxisorientation", false);

    model.component("comp1").geom("geom1").feature("fin").set("createpairs", true);
    model.component("comp1").geom("geom1").feature("fin").set("pairtype", "contact");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").pair("ap1").mapping("initial");

    model.component("comp1").coordSystem().create("sys2", "Cylindrical");
    model.component("comp1").coordSystem("sys1").set("frametype", "material");
    model.component("comp1").coordSystem("sys1").set("mastersystem", "sys2");
    model.component("comp1").coordSystem("sys1").set("mastercoordsystcomp", "2");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u76d8\u5f0f\u5236\u52a8\u5668");
    model.component("comp1").material("mat1").selection().set(1, 2);
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"210[GPa]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.3"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"7850[kg/m^3]"});
    model.component("comp1").material("mat1").set("family", "steel");

    model.component("comp1").view("view1").set("showmaterial", true);
    model.component("comp1").view("view1").set("environmentmap", "envmap_machineshop2");

    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").selection().set(3);
    model.component("comp1").material("mat2").label("\u5239\u8f66\u7247");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", new String[]{"10[GPa]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", new String[]{"0.1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"2000"});
    model.component("comp1").material("mat2").set("color", "black");

    model.component("comp1").physics("solid").prop("StructuralTransientBehavior")
         .set("StructuralTransientBehavior", "Quasistatic");
    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().set(8);
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 2);
    model.component("comp1").physics("solid").feature("sym1").selection().set(3);
    model.component("comp1").physics("solid").create("disp1", "Displacement2", 2);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("solid").feature("disp1").selection().set(16);
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(16);
    model.component("comp1").physics("solid").feature("bndl1").set("forceType", "TotalForce");
    model.component("comp1").physics("solid").feature("bndl1").set("force", new String[]{"0", "0", "-10[kN]"});
    model.component("comp1").physics("solid").feature("dcnt1").set("ContactMethodCtrl", "AugmentedLagrange");
    model.component("comp1").physics("solid").feature("dcnt1").set("tunedFor", "Speed");
    model.component("comp1").physics("solid").feature("dcnt1").create("sv1", "FrictionSlipVelocity", 2);
    model.component("comp1").physics("solid").feature("dcnt1").feature("sv1").set("mu_fric", 0.45);
    model.component("comp1").physics("solid").feature("dcnt1").feature("sv1")
         .set("vslip", new String[]{"omega*sys2.r", "0", "0"});
    model.component("comp1").physics("solid").feature("dcnt1").feature("sv1").set("sliptotStore", true);
    model.component("comp1").physics("solid").feature("dcnt1").create("wear1", "Wear", 2);
    model.component("comp1").physics("solid").feature("dcnt1").feature("wear1").set("k", "1e-14*wear_accel");

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(4, 8);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("se1", "SizeExpression");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("se1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("se1").selection().set(4, 8);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("se1")
         .set("sizeexpr", "if((x/60[mm])^2+((y-115[mm])/40[mm])^2<1, 6[mm], 40[mm])");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("se1").set("hmeshgrad", 1.25);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().set(1, 2);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh1").create("ftri2", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri2").selection().set(16);
    model.component("comp1").mesh("mesh1").feature("ftri2").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("hmax", "3.5[mm]");
    model.component("comp1").mesh("mesh1").create("swe2", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").set("numelem", 3);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").setSolveFor("/frame/material1", false);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v1").feature("comp1_solid_Tn_ap1").set("scaleval", "5e6");

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
    model.result("pg1").feature("vol1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("vol1").feature("def").set("scale", "1");
    model.result("pg1").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").label("\u63a5\u89e6\u529b (solid)");
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
    model.result("pg2").feature("surf1").feature("sel1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18);
    model.result("pg2").feature("surf1").create("tran1", "Transparency");
    model.result("pg2").feature("surf1").feature("tran1").set("transparency", 0.8);
    model.result("pg2").create("arws1", "ArrowSurface");
    model.result("pg2").feature("arws1").set("arrowbase", "head");
    model.result("pg2").feature("arws1")
         .set("expr", new String[]{"solid.dcnt1.Tnx", "solid.dcnt1.Tny", "solid.dcnt1.Tnz"});
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
    model.result("pg2").set("legendpos", "rightdouble");
    model.result("pg2").create("arws2", "ArrowSurface");
    model.result("pg2").feature("arws2").set("arrowbase", "tail");
    model.result("pg2").feature("arws2")
         .set("expr", new String[]{"solid.dcnt1.Ttx", "solid.dcnt1.Tty", "solid.dcnt1.Ttz"});
    model.result("pg2").feature("arws2").set("placement", "gausspoints");
    model.result("pg2").feature("arws2").set("gporder", 4);
    model.result("pg2").feature("arws2").label("\u63a5\u89e6 1, \u6469\u64e6\u529b");
    model.result("pg2").feature("arws2").set("inheritplot", "none");
    model.result("pg2").feature("arws2").set("color", "magenta");
    model.result("pg2").feature("arws2").create("col", "Color");
    model.result("pg2").feature("arws2").feature("col").set("colortable", "Rainbow");
    model.result("pg2").feature("arws2").feature("col").set("colortabletrans", "none");
    model.result("pg2").feature("arws2").feature("col").set("colorscalemode", "linear");
    model.result("pg2").feature("arws2").feature("col").set("colordata", "arrowlength");
    model.result("pg2").feature("arws2").feature("col").set("coloring", "gradient");
    model.result("pg2").feature("arws2").feature("col").set("topcolor", "magenta");
    model.result("pg2").feature("arws2").feature("col").set("bottomcolor", "custom");
    model.result("pg2").feature("arws2").feature("col")
         .set("custombottomcolor", new double[]{0.54902, 0.509804, 0.54902});
    model.result("pg2").feature("arws2").create("def", "Deform");
    model.result("pg2").feature("arws2").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("arws2").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg2").feature("arws2").feature("def").set("scaleactive", true);
    model.result("pg2").feature("arws2").feature("def").set("scale", 1);
    model.result("pg2").feature().move("surf1", 2);
    model.result("pg2").label("\u63a5\u89e6\u529b (solid)");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("arws1").active(false);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("arws2").feature("col").set("coloring", "colortable");
    model.result("pg2").feature("arws2").feature("col").set("colortable", "RainbowLight");

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/solid", true);
    model.study("std2").feature("time").set("tlist", "range(0, 0.1, 5)");
    model.study("std2").feature("time").set("useinitsol", true);
    model.study("std2").feature("time").set("initmethod", "sol");
    model.study("std2").feature("time").set("initstudy", "std1");
    model.study("std2").createAutoSequences("sol");
    model.study("std2").createAutoSequences("jobs");

    model.sol("sol2").runFromTo("st1", "v1");

    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").label("\u5e94\u529b (solid) 1");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg3").feature("vol1").set("threshold", "manual");
    model.result("pg3").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg3").feature("vol1").set("colortable", "Rainbow");
    model.result("pg3").feature("vol1").set("colortabletrans", "none");
    model.result("pg3").feature("vol1").set("colorscalemode", "linear");
    model.result("pg3").feature("vol1").set("resolution", "custom");
    model.result("pg3").feature("vol1").set("refine", 2);
    model.result("pg3").feature("vol1").set("colortable", "Prism");
    model.result("pg3").feature("vol1").create("def", "Deform");
    model.result("pg3").feature("vol1").feature("def").set("scaleactive", true);
    model.result("pg3").feature("vol1").feature("def").set("scale", "1");
    model.result("pg3").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg3").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg3").run();
    model.result().dataset().create("tran1", "Transformation3D");
    model.result().dataset("tran1").set("data", "dset2");
    model.result().dataset("tran1").set("enablerot", true);
    model.result().dataset("tran1").set("rotangle", "-omega*t/200");
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").set("data", "dset2");
    model.result().dataset("surf1").set("param", "xy");
    model.result().dataset("surf1").selection().set(15);
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").label("\u78e8\u635f\u6df1\u5ea6");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").set("titletype", "label");
    model.result("pg4").set("edges", false);
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").create("vol1", "Volume");
    model.result("pg4").feature("vol1").set("expr", "1");
    model.result("pg4").feature("vol1").create("sel1", "Selection");
    model.result("pg4").feature("vol1").feature("sel1").selection().set(1, 2);
    model.result("pg4").run();
    model.result("pg4").feature("vol1").create("mtrl1", "MaterialAppearance");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").create("arws1", "ArrowSurface");
    model.result("pg4").feature("arws1").set("data", "tran1");
    model.result("pg4").feature("arws1").set("expr", new String[]{"y*omega", "-x*omega", "w"});
    model.result("pg4").feature("arws1").setIndex("expr", 0, 2);
    model.result("pg4").feature("arws1").set("arrowcount", 100);
    model.result("pg4").feature("arws1").set("color", "black");
    model.result("pg4").feature("arws1").create("sel1", "Selection");
    model.result("pg4").feature("arws1").feature("sel1").selection().set(4);
    model.result("pg4").run();
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("data", "surf1");
    model.result("pg4").feature("surf1").set("expr", "solid.htot");
    model.result("pg4").feature("surf1").set("unit", "um");
    model.result("pg4").feature("surf1").set("colortable", "WaveLight");
    model.result("pg4").feature("surf1").create("trn1", "Transformation");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").feature("trn1").set("move", new String[]{"0", "0", "2[mm]"});

    model.study("std2").feature("time").set("plot", true);
    model.study("std2").feature("time").set("plotgroup", "pg4");

    model.sol("sol2").feature("v1").feature("comp1_solid_Tn_ap1").set("scaleval", "5e6");
    model.sol("sol2").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol2").feature("t1").set("consistent", false);
    model.sol("sol2").feature("t1").feature("se1").feature("ss2")
         .set("segvar", new String[]{"comp1_u", "comp1_solid_dcnt1_wear1_h_ap1", "comp1_material_disp"});
    model.sol("sol2").feature("t1").feature("se1").feature("ss2").set("subdtech", "const");
    model.sol("sol2").feature("t1").feature("se1").feature("ss2").set("subtermconst", "tol");
    model.sol("sol2").feature("t1").feature("se1").feature().remove("ss1");

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").feature("arws1").set("solutionparams", "parent");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("solutionparams", "parent");
    model.result().configuration().create("ssol1", "SingleSelectSolution");
    model.result().configuration("ssol1").set("solution", "sol2");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").run();
    model.result("pg5").label("\u538b\u529b\u5206\u5e03");
    model.result("pg5").set("solutionparams", "configuration");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "gpeval(4, solid.dcnt1.wear1.Tn)");
    model.result("pg5").feature("surf1").set("unit", "MPa");
    model.result("pg5").run();

    model.view("view3").set("showgrid", false);

    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("\u6ed1\u79fb\u8ddd\u79bb");
    model.result("pg6").run();
    model.result("pg6").feature("surf1").set("expr", "gpeval(4, solid.sliptot)*wear_accel");
    model.result("pg6").feature("surf1").set("unit", "km");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("\u78e8\u635f\u7387");
    model.result("pg7").run();
    model.result("pg7").feature("surf1").set("expr", "gpeval(4, solid.h_tEff)");
    model.result("pg7").feature("surf1").set("unit", "um/s");
    model.result("pg7").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").set("data", "dset2");
    model.result().evaluationGroup("eg1").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("eg1").create("av1", "AvSurface");
    model.result().evaluationGroup("eg1").feature("av1").set("intvolume", true);
    model.result().evaluationGroup("eg1").feature("av1").selection().set(15);
    model.result().evaluationGroup("eg1").feature("av1").setIndex("expr", "solid.htot", 0);
    model.result().evaluationGroup("eg1").feature("av1").setIndex("unit", "um", 0);
    model.result().evaluationGroup("eg1").feature("av1")
         .setIndex("descr", "\u5e73\u5747\u78e8\u635f\u6df1\u5ea6", 0);
    model.result().evaluationGroup("eg1").create("min1", "MinSurface");
    model.result().evaluationGroup("eg1").feature("min1").selection().set(15);
    model.result().evaluationGroup("eg1").feature("min1").setIndex("expr", "solid.htot", 0);
    model.result().evaluationGroup("eg1").feature("min1").setIndex("unit", "um", 0);
    model.result().evaluationGroup("eg1").feature("min1")
         .setIndex("descr", "\u6700\u5c0f\u78e8\u635f\u6df1\u5ea6", 0);
    model.result().evaluationGroup("eg1").create("max1", "MaxSurface");
    model.result().evaluationGroup("eg1").feature("max1").selection().set(15);
    model.result().evaluationGroup("eg1").feature("max1").setIndex("expr", "solid.htot", 0);
    model.result().evaluationGroup("eg1").feature("max1").setIndex("unit", "um", 0);
    model.result().evaluationGroup("eg1").feature("max1")
         .setIndex("descr", "\u6700\u5927\u78e8\u635f\u6df1\u5ea6", 0);
    model.result().evaluationGroup("eg1").run();
    model.result("pg4").run();

    model.component("comp1").view("view1").set("showgrid", true);
    model.component("comp1").view("view1").set("showaxisorientation", true);

    model.title("\u76d8\u5f0f\u5239\u8f66\u7247\u78e8\u635f");

    model
         .description("\u672c\u4f8b\u7814\u7a76\u76d8\u5f0f\u5236\u52a8\u5668\u4e2d\u6469\u64e6\u6750\u6599\u7684\u78e8\u635f\u60c5\u51b5\u3002\u51c6\u9759\u6001\u6469\u64e6\u529b\u662f\u57fa\u4e8e\u7b80\u5355\u7684\u8fd0\u52a8\u5b66\u56e0\u7d20\u6307\u5b9a\u7684\u3002\u5176\u4e2d\u6839\u636e\u8457\u540d\u7684 Archard \u65b9\u7a0b\uff0c\u4e0d\u65ad\u66f4\u65b0\u5239\u8f66\u7247\u7684\u51e0\u4f55\u5f62\u72b6\u4ee5\u8003\u8651\u78e8\u635f\u5f15\u8d77\u7684\u6750\u6599\u53bb\u9664\u3002\u63a5\u89e6\u538b\u529b\u548c\u78e8\u635f\u7387\u90fd\u968f\u7740\u65f6\u95f4\u53d1\u751f\u53d8\u5316\uff0c\u8fd9\u662f\u7531\u66f4\u65b0\u7684\u5239\u8f66\u7247\u51e0\u4f55\u5f62\u72b6\u9020\u6210\u7684\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("disc_brake_wear.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
