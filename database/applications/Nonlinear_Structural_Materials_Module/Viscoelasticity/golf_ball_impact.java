/*
 * golf_ball_impact.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:38 by COMSOL 6.3.0.290. */
public class golf_ball_impact {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Nonlinear_Structural_Materials_Module\\Viscoelasticity");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/solid", true);

    model.param().set("loft", "34[deg]");
    model.param().descr("loft", "\u6746\u5934\u503e\u89d2");
    model.param().set("attackAngle", "-4.3[deg]");
    model.param().descr("attackAngle", "\u653b\u89d2");
    model.param().set("dynLoft", "28[deg]");
    model.param().descr("dynLoft", "\u52a8\u6001\u6746\u9762\u503e\u89d2");
    model.param().set("vel", "90[mph]");
    model.param().descr("vel", "\u6746\u5934\u901f\u5ea6");
    model.param().set("velx", "cos(attackAngle)*vel");
    model.param().descr("velx", "\u6746\u5934\u901f\u5ea6\uff0cx \u5206\u91cf");
    model.param().set("velz", "sin(attackAngle)*vel");
    model.param().descr("velz", "\u6746\u5934\u901f\u5ea6\uff0cz \u5206\u91cf");

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "golf_ball_impact.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("imp1(1)");
    model.component("comp1").geom("geom1").feature("rot1").set("axistype", "y");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", "loft-dynLoft");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u7403\u6746\u5934");
    model.component("comp1").selection("sel1").set(1, 2);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u7403\u6746\u6746\u8eab");
    model.component("comp1").selection("sel2").set(5, 6, 8, 9, 10, 11, 12, 14);
    model.component("comp1").selection().create("adj1", "Adjacent");
    model.component("comp1").selection("adj1").label("\u7403\u6746\u5934\u548c\u6746\u8eab\uff0c\u8fb9\u754c");
    model.component("comp1").selection("adj1").set("input", new String[]{"sel1", "sel2"});
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u5c0f\u80f6\u5957");
    model.component("comp1").selection("sel3").set(3, 4, 7, 13);
    model.component("comp1").selection().create("adj2", "Adjacent");
    model.component("comp1").selection("adj2").label("\u5c0f\u80f6\u5957\uff0c\u8fb9\u754c");
    model.component("comp1").selection("adj2").set("input", new String[]{"sel3"});
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u7403\u6746");
    model.component("comp1").selection("uni1").set("input", new String[]{"sel1", "sel2", "sel3"});
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("\u9ad8\u5c14\u592b\u7403");
    model.component("comp1").selection("sel4")
         .set(15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38);
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").label("\u5916\u58f3");
    model.component("comp1").selection("sel5").set(15, 16, 17, 18, 27, 28, 33, 38);
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").label("\u8986\u76d6\u5c42");
    model.component("comp1").selection("sel6").set(19, 20, 21, 22, 29, 30, 34, 37);
    model.component("comp1").selection().create("dif1", "Difference");
    model.component("comp1").selection("dif1").label("\u5185\u82af");
    model.component("comp1").selection("dif1").set("add", new String[]{"sel4"});
    model.component("comp1").selection("dif1").set("subtract", new String[]{"sel5", "sel6"});
    model.component("comp1").selection().create("uni2", "Union");
    model.component("comp1").selection("uni2").label("\u7403\u82af");
    model.component("comp1").selection("uni2").set("input", new String[]{"sel6", "dif1"});
    model.component("comp1").selection().create("sel7", "Explicit");
    model.component("comp1").selection("sel7").geom(2);
    model.component("comp1").selection("sel7").label("\u7403\u6746\u9762");
    model.component("comp1").selection("sel7").set(5);
    model.component("comp1").selection().create("sel8", "Explicit");
    model.component("comp1").selection("sel8").label("\u679c\u5cad");
    model.component("comp1").selection("sel8").geom(2);
    model.component("comp1").selection("sel8")
         .set(8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 50, 51, 52, 53, 55, 59, 60, 61, 62, 63, 70, 71, 72, 73, 75, 86, 87, 88, 89, 98, 105, 106, 107, 108, 110);
    model.component("comp1").selection().create("dif2", "Difference");
    model.component("comp1").selection("dif2").label("\u4eae\u94a2");
    model.component("comp1").selection("dif2").set("entitydim", 2);
    model.component("comp1").selection("dif2").set("add", new String[]{"adj1"});
    model.component("comp1").selection("dif2").set("subtract", new String[]{"sel7", "sel8"});

    model.component("comp1").pair().create("p1", "Contact");
    model.component("comp1").pair("p1").source().named("sel7");
    model.component("comp1").pair("p1").destination().set(136, 137, 139, 141);

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().named("sel4");
    model.component("comp1").cpl("intop1").set("frame", "material");
    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().named("sel1");
    model.component("comp1").cpl("aveop1").set("frame", "material");
    model.component("comp1").cpl().create("aveop2", "Average");
    model.component("comp1").cpl("aveop2").set("axisym", true);
    model.component("comp1").cpl("aveop2").selection().named("sel4");
    model.component("comp1").cpl("aveop2").set("frame", "material");

    model.component("comp1").physics("solid").prop("ShapeProperty").set("order_displacement", 1);
    model.component("comp1").physics("solid").create("hmm1", "HyperelasticModel", 3);
    model.component("comp1").physics("solid").feature("hmm1").selection().named("sel5");
    model.component("comp1").physics("solid").feature("hmm1").set("reducedIntegration", true);
    model.component("comp1").physics("solid").create("hmm2", "HyperelasticModel", 3);
    model.component("comp1").physics("solid").feature("hmm2").selection().named("uni2");
    model.component("comp1").physics("solid").feature("hmm2")
         .set("Compressibility_NeoHookean", "NearlyIncompressible");
    model.component("comp1").physics("solid").feature("hmm2").set("VolumetricEnergyUncoupled", "hartmannNeff");
    model.component("comp1").physics("solid").feature("hmm2").set("kappa", "1[GPa]");
    model.component("comp1").physics("solid").feature("hmm2").set("reducedIntegration", true);
    model.component("comp1").physics("solid").feature("hmm2").create("vis1", "Viscoelasticity", 3);
    model.component("comp1").physics("solid").feature("hmm2").feature("vis1").setIndex("betavm", 0, 1, 0);
    model.component("comp1").physics("solid").feature("hmm2").feature("vis1").setIndex("tauvm", 0, 1, 0);
    model.component("comp1").physics("solid").feature("hmm2").feature("vis1").setIndex("tauvm", 0, 1, 0);
    model.component("comp1").physics("solid").feature("hmm2").feature("vis1").setIndex("betavm", 0, 1, 0);
    model.component("comp1").physics("solid").feature("hmm2").feature("vis1").setIndex("tauvm", 0, 1, 0);
    model.component("comp1").physics("solid").feature("hmm2").feature("vis1").setIndex("betavm", 0, 2, 0);
    model.component("comp1").physics("solid").feature("hmm2").feature("vis1").setIndex("tauvm", 0, 2, 0);
    model.component("comp1").physics("solid").feature("hmm2").feature("vis1").setIndex("tauvm", 0, 2, 0);
    model.component("comp1").physics("solid").feature("hmm2").feature("vis1").setIndex("betavm", 0, 2, 0);
    model.component("comp1").physics("solid").feature("hmm2").feature("vis1").setIndex("tauvm", 0, 2, 0);
    model.component("comp1").physics("solid").feature("hmm2").feature("vis1").setIndex("betavm", 0.26, 0, 0);
    model.component("comp1").physics("solid").feature("hmm2").feature("vis1").setIndex("tauvm", "1e-5", 0, 0);
    model.component("comp1").physics("solid").feature("hmm2").feature("vis1").setIndex("betavm", 0.19, 1, 0);
    model.component("comp1").physics("solid").feature("hmm2").feature("vis1").setIndex("tauvm", "1e-4", 1, 0);
    model.component("comp1").physics("solid").feature("hmm2").feature("vis1").setIndex("betavm", 0.22, 2, 0);
    model.component("comp1").physics("solid").feature("hmm2").feature("vis1").setIndex("tauvm", "1e-3", 2, 0);
    model.component("comp1").physics("solid").feature("dcnt1").set("ContactMethodCtrl", "PenaltyDynamic");
    model.component("comp1").physics("solid").feature("dcnt1").set("penaltyCtrlPenalty", "ViscousOnly");
    model.component("comp1").physics("solid").feature("dcnt1").set("taun_penalty", "0.1[ms]");
    model.component("comp1").physics("solid").feature("dcnt1").create("fric1", "Friction", 2);
    model.component("comp1").physics("solid").feature("dcnt1").feature("fric1").set("mu_fric", 0.15);
    model.component("comp1").physics("solid").create("init2", "init", 3);
    model.component("comp1").physics("solid").feature("init2").selection().named("uni1");
    model.component("comp1").physics("solid").feature("init2").set("ut", new String[]{"velx", "0", "velz"});
    model.component("comp1").physics("solid").create("disp1", "Displacement2", 2);
    model.component("comp1").physics("solid").feature("disp1").selection().set(131, 132, 134, 135);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("solid").feature("disp1").setIndex("U0", "velx*t", 0);
    model.component("comp1").physics("solid").feature("disp1").setIndex("U0", "velz*t", 2);
    model.component("comp1").physics("solid").create("avgr1", "AverageRotation", -1);
    model.component("comp1").physics("solid").feature("avgr1").selection("PointSelection")
         .set(158, 161, 164, 166, 169, 172);
    model.component("comp1").physics("solid").feature("avgr1")
         .set("CenterOfRotation", "COR_CentroidOfSelectedEntities");
    model.component("comp1").physics("solid").feature("avgr1").set("EntityLevel", "Point");
    model.component("comp1").physics("solid").feature("avgr1").set("RotationModel", "FiniteRotationSym");
    model.component("comp1").physics("solid").feature("avgr1").feature("crp1").selection().set(165);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u7403\u6746\u5934");
    model.component("comp1").material("mat1").selection().named("uni1");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"200e9"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.3"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"7850"});
    model.component("comp1").material("mat1").set("family", "steel");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u5916\u58f3");
    model.component("comp1").material("mat2").selection().named("sel5");
    model.component("comp1").material("mat2").propertyGroup().create("Lame", "Lame", "Lame_parameters");
    model.component("comp1").material("mat2").propertyGroup("Lame").set("lambLame", new String[]{"1.5e9"});
    model.component("comp1").material("mat2").propertyGroup("Lame").set("muLame", new String[]{"0.2e9"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"1145"});
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").label("\u8986\u76d6\u5c42");
    model.component("comp1").material("mat3").selection().named("sel6");
    model.component("comp1").material("mat3").propertyGroup().create("Lame", "Lame", "Lame_parameters");
    model.component("comp1").material("mat3").propertyGroup("Lame").set("muLame", new String[]{"6e6"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", new String[]{"1145"});
    model.component("comp1").material("mat3").set("color", "blue");
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").label("\u5185\u82af");
    model.component("comp1").material("mat4").selection().named("dif1");
    model.component("comp1").material("mat4").propertyGroup().create("Lame", "Lame", "Lame_parameters");
    model.component("comp1").material("mat4").propertyGroup("Lame").set("muLame", new String[]{"12e6"});
    model.component("comp1").material("mat4").propertyGroup("def").set("density", new String[]{"1145"});
    model.component("comp1").material("mat4").set("color", "magenta");

    model.component("comp1").mesh("mesh1").contribute("geom/detail", false);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", 0.01);
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", 0.001);
    model.component("comp1").mesh("mesh1").feature("size").set("hgrad", 1.4);
    model.component("comp1").mesh("mesh1").feature("size").set("hcurve", 0.2);
    model.component("comp1").mesh("mesh1").feature("size").set("hnarrow", 0.8);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(84, 85, 109, 128, 131, 132, 134, 135);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection()
         .set(168, 169, 203, 240, 244, 245, 254, 258);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 3);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().set(3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);
    model.component("comp1").mesh("mesh1").create("fq1", "FreeQuad");
    model.component("comp1").mesh("mesh1").feature("fq1").selection().set(83);
    model.component("comp1").mesh("mesh1").create("swe2", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe2").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe2").selection().set(2);
    model.component("comp1").mesh("mesh1").feature().move("ftet1", 5);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "2e-3");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("tout", "tsteps");
    model.sol("sol1").feature("t1").set("tstepsstore", 2);
    model.sol("sol1").feature("t1").set("tstepsgenalpha", "manual");
    model.sol("sol1").feature("t1").set("timestepgenalpha", "2.5e-5");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 25);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 0.1);

    model.study("std1").createAutoSequences("sol");
    model.study("std1").createAutoSequences("jobs");

    model.sol("sol1").runFromTo("st1", "v1");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 1, 0);
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
    model.result("pg1").label("\u5e94\u53d8 (solid)");
    model.result("pg1").selection().geom("geom1", 3);
    model.result("pg1").selection().geom("geom1", 3);
    model.result("pg1").selection().set(17, 18, 21, 22, 25, 26, 33, 34, 35, 36, 37, 38);
    model.result("pg1").run();
    model.result("pg1").feature("vol1").set("expr", "solid.ep3");
    model.result("pg1").feature("vol1").set("colortable", "RainbowLight");
    model.result("pg1").feature("vol1").set("colortabletrans", "reverse");
    model.result("pg1").feature("vol1").set("smooth", "everywhere");
    model.result("pg1").feature("vol1").set("threshold", "none");
    model.result("pg1").feature().duplicate("vol2", "vol1");
    model.result("pg1").run();
    model.result("pg1").feature("vol2").set("titletype", "none");
    model.result("pg1").feature("vol2").create("mtrl1", "MaterialAppearance");
    model.result("pg1").run();

    model.study("std1").feature("time").set("plot", true);
    model.study("std1").feature("time").set("plotfreq", "tsteps");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").label("\u6750\u6599\u6e32\u67d3");
    model.result("pg2").set("edges", false);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u9ad8\u5c14\u592b\u7403");
    model.result("pg2").feature("surf1").set("expr", "1");
    model.result("pg2").feature("surf1").set("titletype", "none");
    model.result("pg2").feature("surf1").create("def1", "Deform");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg2").feature("surf1").feature("def1").set("scale", 1);
    model.result("pg2").run();
    model.result("pg2").feature("surf1").create("sel1", "Selection");
    model.result("pg2").feature("surf1").feature("sel1").selection().set(136, 137, 161, 163);
    model.result("pg2").run();
    model.result("pg2").feature("surf1").create("img1", "ImageOverlay");
    model.result("pg2").feature("surf1").feature("img1").set("filename", "golf_ball_impact_logo.png");
    model.result("pg2").feature("surf1").feature("img1").set("mapping", "planar");
    model.result("pg2").feature("surf1").feature("img1").set("planetype", "xz");
    model.result("pg2").feature("surf1").feature("img1").set("width", "0.03");
    model.result("pg2").feature("surf1").feature("img1").set("anchorpos", new String[]{"-0.015", "-0.03", "0"});
    model.result("pg2").feature("surf1").feature("img1").setIndex("anchorpos", -0.002, 2);
    model.result("pg2").feature("surf1").feature("img1").set("extrapolation", "clamp");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").feature("mtrl1").set("material", "mat2");
    model.result("pg2").feature("surf1").feature("mtrl1").set("useplotcolors", true);
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("surf2", "surf1");
    model.result("pg2").run();
    model.result("pg2").feature("surf2").set("inheritplot", "surf1");
    model.result("pg2").run();
    model.result("pg2").feature("surf2").feature("sel1").selection().set(139, 141, 177, 193);
    model.result("pg2").run();
    model.result("pg2").feature("surf2").feature("img1").set("planetype", "zx");
    model.result("pg2").feature("surf2").feature("img1").setIndex("anchorpos", 0.03, 1);
    model.result("pg2").feature("surf2").feature("img1").set("anchorpos", new String[]{"0.015", "0.03", "-0.002"});
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("surf3", "surf1");
    model.result("pg2").run();
    model.result("pg2").feature("surf3").label("\u7403\u6746\u5934");
    model.result("pg2").feature("surf3").set("inheritplot", "surf1");
    model.result("pg2").run();
    model.result("pg2").feature("surf3").feature("sel1").selection().named("dif2");
    model.result("pg2").run();
    model.result("pg2").feature("surf3").feature().remove("img1");
    model.result("pg2").run();
    model.result("pg2").feature("surf3").feature("mtrl1").set("material", "mat1");
    model.result("pg2").feature("surf3").feature("mtrl1").set("useplotcolors", false);
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("surf4", "surf3");
    model.result("pg2").run();
    model.result("pg2").feature("surf4").label("\u7403\u6746\u9762");
    model.result("pg2").run();
    model.result("pg2").feature("surf4").feature("sel1").selection().named("sel7");
    model.result("pg2").run();
    model.result("pg2").feature("surf4").feature("mtrl1").set("appearance", "custom");
    model.result("pg2").feature("surf4").feature("mtrl1").set("family", "steelscratched");
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("surf5", "surf4");
    model.result("pg2").run();
    model.result("pg2").feature("surf5").label("\u679c\u5cad");
    model.result("pg2").run();
    model.result("pg2").feature("surf5").feature("mtrl1").set("family", "plastic");
    model.result("pg2").feature("surf5").feature("mtrl1").set("color", "black");
    model.result("pg2").run();
    model.result("pg2").feature("surf5").feature("sel1").selection().named("sel8");
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("surf6", "surf5");
    model.result("pg2").run();
    model.result("pg2").feature("surf6").label("\u5c0f\u80f6\u5957");
    model.result("pg2").run();
    model.result("pg2").feature("surf6").feature("sel1").selection().named("adj2");

    model.component("comp1").view("view1").set("ssao", true);
    model.component("comp1").view("view1").set("flooreffect", true);
    model.component("comp1").view("view1").set("environmentmap", "envmap_meadow");

    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("plotgroup", "pg2");
    model.result().export("anim1").run();
    model.result().export("anim1").set("framesel", "all");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u80fd\u91cf");
    model.result("pg3").set("titletype", "none");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u80fd\u91cf (J)");
    model.result("pg3").set("legendpos", "middleright");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").setIndex("expr", "intop1(solid.Ws)", 0);
    model.result("pg3").feature("glob1").setIndex("unit", "", 0);
    model.result("pg3").feature("glob1").setIndex("descr", "\u5f39\u6027\u80fd", 0);
    model.result("pg3").feature("glob1").setIndex("expr", "intop1(solid.Wk)", 1);
    model.result("pg3").feature("glob1").setIndex("unit", "", 1);
    model.result("pg3").feature("glob1").setIndex("descr", "\u52a8\u80fd", 1);
    model.result("pg3").feature("glob1").set("xdataparamunit", "ms");
    model.result("pg3").feature("glob1").set("linewidth", 2);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u901f\u5ea6");
    model.result("pg4").set("titletype", "none");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u901f\u5ea6 (m/s)");
    model.result("pg4").set("legendpos", "lowerright");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "aveop1(solid.vel)", 0);
    model.result("pg4").feature("glob1").setIndex("descr", "\u7403\u6746\u5934", 0);
    model.result("pg4").feature("glob1").setIndex("expr", "aveop2(solid.vel)", 1);
    model.result("pg4").feature("glob1").setIndex("descr", "\u9ad8\u5c14\u592b\u7403", 1);
    model.result("pg4").feature("glob1").set("xdataparamunit", "ms");
    model.result("pg4").feature("glob1").set("linewidth", 2);
    model.result("pg4").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("\u9ad8\u5c14\u592b\u6307\u6807");
    model.result().evaluationGroup("eg1").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("eg1").create("pev1", "EvalPoint");
    model.result().evaluationGroup("eg1").feature("pev1").selection().set(165);
    model.result().evaluationGroup("eg1").feature("pev1").setIndex("expr", "atan(z/x)", 0);
    model.result().evaluationGroup("eg1").feature("pev1").setIndex("unit", "deg", 0);
    model.result().evaluationGroup("eg1").feature("pev1").setIndex("descr", "\u8d77\u53d1\u89d2", 0);
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "solid.avgr1.disp_t", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("unit", "mph", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "\u7403\u901f", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "solid.avgr1.disp_t/vel", 1);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "\u51fb\u7403\u6548\u7387", 1);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "solid.avgr1.totrot_t/(2*pi[rad])", 2);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("unit", "rpm", 2);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "\u65cb\u8f6c\u901f\u7387", 2);
    model.result().evaluationGroup("eg1").run();
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{7});
    model.result("pg1").set("view", "new");
    model.result("pg1").run();
    model.result("pg1").set("titletype", "custom");
    model.result("pg1").set("typeintitle", false);
    model.result("pg1").set("unitintitle", false);
    model.result("pg1").set("descriptionintitle", false);
    model.result("pg1").run();

    model.title("\u9ad8\u5c14\u592b\u7403\u7684\u51b2\u51fb\u5206\u6790");

    model
         .description("\u672c\u4f8b\u7814\u7a76\u9ad8\u5c14\u592b\u7403\u6746\u51fb\u6253\u9ad8\u5c14\u592b\u7403\u65f6\u7684\u673a\u68b0\u51b2\u51fb\u3002\u5176\u4e2d\u4f7f\u7528\u9ecf\u6027\u7f5a\u516c\u5f0f\u5bf9\u4e24\u90e8\u5206\u4e4b\u95f4\u7684\u63a5\u89e6\u8fdb\u884c\u5efa\u6a21\uff0c\u4ece\u800c\u4f7f\u8be5\u52a8\u6001\u4e8b\u4ef6\u4fdd\u6301\u7a33\u5b9a\u3002\u4e3a\u4e86\u6b63\u786e\u6a21\u62df\u5927\u53d8\u5f62\uff0c\u6211\u4eec\u4f7f\u7528\u8d85\u5f39\u6027\u6750\u6599\u6a21\u578b\u6765\u5b9a\u4e49\u9ad8\u5c14\u592b\u7403\u3002\u82af\u90e8\u7684\u6750\u6599\u662f\u9ecf\u5f39\u6027\u7684\uff0c\u4f1a\u5bfc\u81f4\u51b2\u51fb\u4ea7\u751f\u7684\u5f39\u6027\u80fd\u53d1\u751f\u8017\u6563\u3002\n\n\u672c\u4f8b\u5c06\u4eff\u771f\u7ed3\u679c\u4e0e\u5178\u578b\u7684\u9ad8\u5c14\u592b\u6307\u6807\uff08\u5982\u7403\u901f\u548c\u65cb\u8f6c\u901f\u5ea6\uff09\u8fdb\u884c\u6bd4\u8f83\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("golf_ball_impact.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
