/*
 * rubber_boot_seal.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:32 by COMSOL 6.3.0.290. */
public class rubber_boot_seal {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Nonlinear_Structural_Materials_Module\\Hyperelasticity");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.param().set("overlap", "1[mm]");
    model.param().descr("overlap", "\u70ed\u538b\u914d\u5408\u91cd\u53e0");
    model.param().set("para1", "0");
    model.param().descr("para1", "\u8fde\u7eed\u6027\u53c2\u6570\uff0c\u6b65\u9aa4 1");
    model.param().set("para2", "0");
    model.param().descr("para2", "\u8fde\u7eed\u6027\u53c2\u6570\uff0c\u6b65\u9aa4 2");

    model.component("comp1").geom("geom1").insertFile("rubber_boot_seal_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "(60)/2-3+overlap");
    model.component("comp1").geom("geom1").run("mcf1");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u63a5\u89e6\u9762\uff0c\u5916\u4fa7");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(63, 66, 69, 75, 79, 83, 87, 91, 95);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u63a5\u89e6\u9762\uff0c\u5185\u4fa7");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set(70, 71, 73, 80, 81, 88, 89, 96, 97);

    model.component("comp1").pair().create("p1", "Contact");
    model.component("comp1").pair("p1").source().set(116, 124);
    model.component("comp1").pair("p1").destination().set(41, 51, 53, 56, 70, 80, 88, 96);
    model.component("comp1").pair().create("p2", "Contact");
    model.component("comp1").pair("p2").source().named("sel1");
    model.component("comp1").pair("p2").destination().named("sel1");
    model.component("comp1").pair("p2").manualDist(true);
    model.component("comp1").pair("p2").searchDist("1");
    model.component("comp1").pair().create("p3", "Contact");
    model.component("comp1").pair("p3").source().named("sel2");
    model.component("comp1").pair("p3").destination().named("sel2");
    model.component("comp1").pair("p3").manualDist(true);
    model.component("comp1").pair("p3").searchDist("1");

    model.component("comp1").physics("solid").create("hmm1", "HyperelasticModel", 3);
    model.component("comp1").physics("solid").feature("hmm1").selection().all();
    model.component("comp1").physics("solid").feature("hmm1").set("MaterialModel", "Yeoh");
    model.component("comp1").physics("solid").feature("hmm1").set("VolumetricEnergyUncoupled", "hartmannNeff");
    model.component("comp1").physics("solid").create("rd1", "RigidDomain", 3);
    model.component("comp1").physics("solid").feature("rd1").selection().set(25, 26);
    model.component("comp1").physics("solid").feature("rd1").create("pdr1", "PrescribedDispRot", -1);
    model.component("comp1").physics("solid").feature("rd1").feature("pdr1")
         .set("CenterOfRotationType", "CentroidOfSelectedEntities");
    model.component("comp1").physics("solid").feature("rd1").feature("pdr1").setIndex("Direction", true, 0);
    model.component("comp1").physics("solid").feature("rd1").feature("pdr1").setIndex("Direction", true, 1);
    model.component("comp1").physics("solid").feature("rd1").feature("pdr1").setIndex("Direction", true, 2);
    model.component("comp1").physics("solid").feature("rd1").feature("pdr1")
         .set("RotationType", "PrescribedRotationGroup");
    model.component("comp1").physics("solid").feature("rd1").feature("pdr1").set("Omega", new int[]{0, 1, 0});
    model.component("comp1").physics("solid").feature("rd1").feature("pdr1").set("phi0", "40[deg]*para2");
    model.component("comp1").physics("solid").feature("rd1").feature("pdr1").feature("crb1").selection()
         .set(117, 122);
    model.component("comp1").physics("solid").feature("dcnt1").set("ContactMethodCtrl", "Nitsche");
    model.component("comp1").physics("solid").feature("dcnt1").set("source_offset", "-overlap*(1-min(para1,1))");
    model.component("comp1").physics("solid").feature("dcnt1").create("fric1", "Friction", 2);
    model.component("comp1").physics("solid").feature("dcnt1").feature("fric1").set("mu_fric", 0.7);
    model.component("comp1").physics("solid").create("cnt1", "SolidContact", 2);
    model.component("comp1").physics("solid").feature("cnt1").set("pairs", new String[]{"p2", "p3"});
    model.component("comp1").physics("solid").feature("cnt1").set("ContactMethodCtrl", "Nitsche");
    model.component("comp1").physics("solid").feature("cnt1").create("fric1", "Friction", 2);
    model.component("comp1").physics("solid").feature("cnt1").feature("fric1").set("mu_fric", 0.8);
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 2);
    model.component("comp1").physics("solid").feature("sym1").selection()
         .set(1, 3, 7, 12, 15, 20, 23, 28, 33, 36, 39, 45, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114);
    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().set(9, 100);
    model.component("comp1").physics("solid").create("roll1", "Roller", 2);
    model.component("comp1").physics("solid").feature("roll1").selection().set(11, 99);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup().create("Lame", "Lame", "Lam\u00e9 parameters");
    model.component("comp1").material("mat1").propertyGroup()
         .create("MooneyRivlin", "MooneyRivlin", "Mooney-Rivlin");
    model.component("comp1").material("mat1").propertyGroup().create("Yeoh", "Yeoh", "Yeoh");
    model.component("comp1").material("mat1").propertyGroup().create("Varga", "Varga", "Varga");
    model.component("comp1").material("mat1").propertyGroup().create("ArrudaBoyce", "ArrudaBoyce", "Arruda-Boyce");
    model.component("comp1").material("mat1").label("Rubber");
    model.component("comp1").material("mat1").set("family", "rubber");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("lossfactor", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalconductivity", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "1100[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"200e-6[1/K]", "0", "0", "0", "200e-6[1/K]", "0", "0", "0", "200e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("lossfactor", "0.06");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.5[W/(m*K)]", "0", "0", "0", "0.5[W/(m*K)]", "0", "0", "0", "0.5[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "1900[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("Lame").label("Lam\u00e9 parameters");
    model.component("comp1").material("mat1").propertyGroup("Lame").set("lambLame", "24.5[MPa]");
    model.component("comp1").material("mat1").propertyGroup("Lame").set("muLame", "0.5[MPa]");
    model.component("comp1").material("mat1").propertyGroup("MooneyRivlin").set("C10", "0.219[MPa]");
    model.component("comp1").material("mat1").propertyGroup("MooneyRivlin").set("C01", "0.031[MPa]");
    model.component("comp1").material("mat1").propertyGroup("MooneyRivlin").set("C11", "0[Pa]");
    model.component("comp1").material("mat1").propertyGroup("MooneyRivlin").set("C20", "0[Pa]");
    model.component("comp1").material("mat1").propertyGroup("MooneyRivlin").set("C02", "0[Pa]");
    model.component("comp1").material("mat1").propertyGroup("MooneyRivlin").set("C21", "0[Pa]");
    model.component("comp1").material("mat1").propertyGroup("MooneyRivlin").set("C12", "0[Pa]");
    model.component("comp1").material("mat1").propertyGroup("MooneyRivlin").set("C30", "0[Pa]");
    model.component("comp1").material("mat1").propertyGroup("MooneyRivlin").set("C03", "0[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Yeoh").set("c1YE", "100[kPa]");
    model.component("comp1").material("mat1").propertyGroup("Yeoh").set("c2YE", "6[kPa]");
    model.component("comp1").material("mat1").propertyGroup("Yeoh").set("c3YE", "0[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Varga").set("c1VA", "1.05[MPa]");
    model.component("comp1").material("mat1").propertyGroup("Varga").set("c2VA", "0.45[MPa]");
    model.component("comp1").material("mat1").propertyGroup("ArrudaBoyce").set("Nseg", "32");
    model.component("comp1").material("mat1").propertyGroup("ArrudaBoyce").set("mu0", "0.5[MPa]");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection()
         .set(1, 3, 7, 12, 15, 20, 23, 28, 33, 36, 39, 45, 126, 128, 132, 135, 138, 141, 142);
    model.component("comp1").mesh("mesh1").feature("map1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmax", 5);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection()
         .set(6, 8, 16, 18, 26, 28, 34, 36, 44, 46, 51, 59, 62, 246);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 6);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(20, 30, 38, 53);
    model.component("comp1").mesh("mesh1").create("map2", "Map");
    model.component("comp1").mesh("mesh1").feature("map2").selection().set(115);
    model.component("comp1").mesh("mesh1").feature("map2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis1").selection().all();
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis1").set("numelem", 1);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").set("smoothcontrol", false);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("elemcount", 16);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("elemratio", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").feature().duplicate("dis2", "dis1");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").selection()
         .set(13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 36, 37, 38, 39, 40);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").set("reverse", true);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis3").selection().set(25, 26);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis3").set("numelem", 25);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("plot", true);
    model.study("std1").feature("stat").set("probefreq", "psteps");
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "overlap", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "overlap", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "para1", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "0 1", 0);
    model.study("std1").create("stat2", "Stationary");
    model.study("std1").feature("stat2").set("plot", true);
    model.study("std1").feature("stat2").set("probefreq", "psteps");
    model.study("std1").feature("stat2").set("useparam", true);
    model.study("std1").feature("stat2").setIndex("pname", "overlap", 0);
    model.study("std1").feature("stat2").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat2").setIndex("punit", "m", 0);
    model.study("std1").feature("stat2").setIndex("pname", "overlap", 0);
    model.study("std1").feature("stat2").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat2").setIndex("punit", "m", 0);
    model.study("std1").feature("stat2").setIndex("pname", "para1", 0);
    model.study("std1").feature("stat2").setIndex("plistarr", 1, 0);
    model.study("std1").feature("stat2").setIndex("pname", "overlap", 1);
    model.study("std1").feature("stat2").setIndex("plistarr", "", 1);
    model.study("std1").feature("stat2").setIndex("punit", "m", 1);
    model.study("std1").feature("stat2").setIndex("pname", "overlap", 1);
    model.study("std1").feature("stat2").setIndex("plistarr", "", 1);
    model.study("std1").feature("stat2").setIndex("punit", "m", 1);
    model.study("std1").feature("stat2").setIndex("pname", "para2", 1);
    model.study("std1").feature("stat2").setIndex("plistarr", "range(0,0.1,1)", 1);
    model.study("std1").feature("stat2").set("sweeptype", "filled");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v1").feature("comp1_solid_hmm1_pw").set("scaleval", "1e5");
    model.sol("sol1").feature("s1").feature("p1").set("paramtuning", true);
    model.sol("sol1").feature("s1").feature("p1").set("pmaxstep", 0.5);
    model.sol("sol1").feature("s1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("s1").feature("fc1").set("maxiter", 8);
    model.sol("sol1").feature("v2").feature("comp1_solid_hmm1_pw").set("scaleval", "1e5");
    model.sol("sol1").feature("s2").feature("p1").set("paramtuning", true);
    model.sol("sol1").feature("s2").feature("p1").set("pinitstep", 0.005);
    model.sol("sol1").feature("s2").feature("p1").set("pmaxstep", 0.025);
    model.sol("sol1").feature("s2").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("s2").feature("fc1").set("maxiter", 8);

    model.study("std1").createAutoSequences("all");

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

    model.sol("sol1").runAll();

    model.result().remove("pg1");

    model.study("std1").feature("stat").set("plotgroup", "Default");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 11, 0);
    model.result("pg1").setIndex("looplevel", 1, 1);
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
    model.result("pg1").run();
    model.result("pg1").feature("vol1").set("unit", "MPa");
    model.result("pg1").run();
    model.result().dataset().create("mir1", "Mirror3D");
    model.result().dataset("mir1").set("quickplane", "zx");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").label("\u4f4d\u79fb");
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("edges", false);
    model.result("pg2").set("plotarrayenable", true);
    model.result("pg2").set("arrayshape", "square");
    model.result("pg2").set("arrayplane", "xz");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("arraydim", "2");
    model.result("pg2").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg2").feature("surf1").set("data", "mir1");
    model.result("pg2").feature("surf1").setIndex("looplevel", 1, 0);
    model.result("pg2").feature("surf1").create("def1", "Deform");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg2").feature("surf1").feature("def1").set("scale", 1);
    model.result("pg2").feature("surf1").set("arraydim", "2");
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("surf2", "surf1");
    model.result("pg2").feature("surf2").set("arraydim", "2");
    model.result("pg2").run();
    model.result("pg2").feature("surf2").setIndex("looplevel", 3, 0);
    model.result("pg2").feature("surf2").set("inheritplot", "surf1");
    model.result("pg2").feature().duplicate("surf3", "surf2");
    model.result("pg2").feature("surf3").set("arraydim", "2");
    model.result("pg2").run();
    model.result("pg2").feature("surf3").setIndex("looplevel", 5, 0);
    model.result("pg2").feature().duplicate("surf4", "surf3");
    model.result("pg2").feature("surf4").set("arraydim", "2");
    model.result("pg2").run();
    model.result("pg2").feature("surf4").setIndex("looplevel", 7, 0);
    model.result("pg2").feature().duplicate("surf5", "surf4");
    model.result("pg2").feature("surf5").set("arraydim", "2");
    model.result("pg2").run();
    model.result("pg2").feature("surf5").setIndex("looplevel", 9, 0);
    model.result("pg2").feature().duplicate("surf6", "surf5");
    model.result("pg2").feature("surf6").set("arraydim", "2");
    model.result("pg2").run();
    model.result("pg2").feature("surf6").setIndex("looplevel", 11, 0);
    model.result("pg2").run();

    model.component("comp1").view("view1").set("showgrid", false);
    model.component("comp1").view("view1").set("showaxisorientation", false);
    model.component("comp1").view("view1").set("showgrid", true);
    model.component("comp1").view("view1").set("showaxisorientation", true);

    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("\u63a5\u89e6\u533a\u57df");
    model.result("pg3").set("titletype", "none");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "if(elemgpmax(8,solid.incontact),1,NaN)");
    model.result("pg3").feature("surf1").set("coloring", "uniform");
    model.result("pg3").feature("surf1").create("def1", "Deform");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg3").feature("surf1").feature("def1").set("scale", 1);
    model.result("pg3").run();
    model.result("pg3").feature("surf1").create("sel1", "Selection");
    model.result("pg3").feature("surf1").feature("sel1").selection().geom("geom1", 3);
    model.result("pg3").feature("surf1").feature("sel1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24);
    model.result("pg3").run();
    model.result("pg3").create("surf2", "Surface");
    model.result("pg3").feature("surf2").set("expr", "1");
    model.result("pg3").feature("surf2").set("coloring", "uniform");
    model.result("pg3").feature("surf2").set("color", "gray");
    model.result("pg3").feature("surf2").create("def1", "Deform");
    model.result("pg3").run();
    model.result("pg3").feature("surf2").feature("def1").set("scaleactive", true);
    model.result("pg3").feature("surf2").feature("def1").set("scale", 1);
    model.result("pg3").run();
    model.result("pg3").feature("surf2").create("tran1", "Transparency");
    model.result("pg3").run();
    model.result("pg3").feature("surf2").feature("tran1").set("transparency", 0.8);
    model.result("pg3").feature("surf2").feature("tran1").set("uniformblending", 0.2);
    model.result("pg3").run();

    model.component("comp1").view("view1").set("showgrid", true);

    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").label("\u7f29\u7565\u56fe");
    model.result("pg4").set("titletype", "none");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg4").feature("surf1").create("def1", "Deform");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg4").feature("surf1").feature("def1").set("scale", 1);
    model.result("pg4").run();
    model.result("pg4").feature("surf1").create("sel1", "Selection");
    model.result("pg4").feature("surf1").feature("sel1").selection().geom("geom1", 3);
    model.result("pg4").feature("surf1").feature("sel1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24);
    model.result("pg4").run();
    model.result("pg4").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").feature("mtrl1").set("useplotcolors", true);
    model.result("pg4").run();
    model.result("pg4").create("surf2", "Surface");
    model.result("pg4").feature("surf2").create("def1", "Deform");
    model.result("pg4").run();
    model.result("pg4").feature("surf2").feature("def1").set("scaleactive", true);
    model.result("pg4").feature("surf2").feature("def1").set("scale", 1);
    model.result("pg4").run();
    model.result("pg4").feature("surf2").create("sel1", "Selection");
    model.result("pg4").feature("surf2").feature("sel1").selection().geom("geom1", 3);
    model.result("pg4").feature("surf2").feature("sel1").selection().set(25, 26);
    model.result("pg4").run();
    model.result("pg4").feature("surf2").create("mtrl1", "MaterialAppearance");
    model.result("pg4").run();
    model.result("pg4").feature("surf2").feature("mtrl1").set("appearance", "custom");
    model.result("pg4").feature("surf2").feature("mtrl1").set("family", "steel");
    model.result("pg4").run();

    model.component("comp1").view("view1").set("showaxisorientation", false);
    model.component("comp1").view("view1").set("showgrid", false);

    model.result("pg4").set("showlegends", false);

    model.component("comp1").view("view1").set("showaxisorientation", true);
    model.component("comp1").view("view1").set("showgrid", true);

    model.result("pg4").set("showlegends", true);
    model.result("pg4").run();
    model.result().remove("pg4");
    model.result("pg1").run();

    model.title("\u6a61\u80f6\u5957\u5bc6\u5c01\u4ef6\u7684\u63a5\u89e6\u5206\u6790");

    model
         .description("\u672c\u4f8b\u7814\u7a76\u8fde\u63a5\u5230\u521a\u6027\u7ba1\u7684\u6a61\u80f6\u5957\u5bc6\u5c01\u4ef6\u7684\u53d8\u5f62\u60c5\u51b5\u3002\u968f\u7740\u7ba1\u7684\u65cb\u8f6c\u548c\u5bc6\u5c01\u4ef6\u7684\u53d8\u5f62\uff0c\u5bc6\u5c01\u6cd5\u5170\u4f1a\u53d1\u751f\u81ea\u76f8\u4ea4\u5e76\u4e0e\u7ba1\u63a5\u89e6\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("rubber_boot_seal.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
