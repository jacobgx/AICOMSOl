/*
 * balloon_inflation.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:30 by COMSOL 6.3.0.290. */
public class balloon_inflation {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Nonlinear_Structural_Materials_Module\\Hyperelasticity");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.param().set("Ri", "10[cm]");
    model.param().descr("Ri", "\u5185\u534a\u5f84");
    model.param().set("H", "1[mm]");
    model.param().descr("H", "\u539a\u5ea6");
    model.param().set("mu", "4.225e5[Pa]");
    model.param().descr("mu", "\u526a\u5207\u6a21\u91cf");
    model.param().set("kappa", "1e5*mu");
    model.param().descr("kappa", "\u4f53\u79ef\u6a21\u91cf");
    model.param().set("stretch", "1[1]");
    model.param().descr("stretch", "\u5916\u52a0\u4f38\u957f\u7387");
    model.param().set("C10", "0.4375*mu");
    model.param().descr("C10", "Mooney\u2013Rivlin \u53c2\u6570 C10");
    model.param().set("C01", "0.0625*mu");
    model.param().descr("C01", "Mooney\u2013Rivlin \u53c2\u6570 C01");
    model.param().set("mu1", "6.3e5[Pa]");
    model.param().descr("mu1", "Ogden \u53c2\u6570 mu1");
    model.param().set("mu2", "0.012e5[Pa]");
    model.param().descr("mu2", "Ogden \u53c2\u6570 mu2");
    model.param().set("mu3", "-0.1e5[Pa]");
    model.param().descr("mu3", "Ogden \u53c2\u6570 mu3");
    model.param().set("alpha1", "1.3");
    model.param().descr("alpha1", "Ogden \u53c2\u6570 alpha1");
    model.param().set("alpha2", "5");
    model.param().descr("alpha2", "Ogden \u53c2\u6570 alpha2");
    model.param().set("alpha3", "-2");
    model.param().descr("alpha3", "Ogden \u53c2\u6570 alpha3");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("u_appl", "(stretch-1)*Ri");
    model.component("comp1").variable("var1").descr("u_appl", "\u5916\u52a0\u4f4d\u79fb");

    model.component("comp1").geom("geom1").lengthUnit("cm");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "Ri+H");
    model.component("comp1").geom("geom1").feature("c1").set("angle", 20);
    model.component("comp1").geom("geom1").feature("c1").setIndex("layer", "H", 0);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("c1", 1);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("solid").create("hmm1", "HyperelasticModel", 2);
    model.component("comp1").physics("solid").feature("hmm1").label("Neo-Hookean");
    model.component("comp1").physics("solid").feature("hmm1").selection().all();
    model.component("comp1").physics("solid").feature("hmm1")
         .set("Compressibility_NeoHookean", "NearlyIncompressible");
    model.component("comp1").physics("solid").feature("hmm1").set("kappa", "kappa");
    model.component("comp1").physics("solid").feature("hmm1").set("muLame_mat", "userdef");
    model.component("comp1").physics("solid").feature("hmm1").set("muLame", "mu");
    model.component("comp1").physics("solid").create("hmm2", "HyperelasticModel", 2);
    model.component("comp1").physics("solid").feature("hmm2").label("Mooney\u2013Rivlin");
    model.component("comp1").physics("solid").feature("hmm2").selection().all();
    model.component("comp1").physics("solid").feature("hmm2").set("MaterialModel", "MooneyRivlin");
    model.component("comp1").physics("solid").feature("hmm2").set("C10_mat", "userdef");
    model.component("comp1").physics("solid").feature("hmm2").set("C10", "C10");
    model.component("comp1").physics("solid").feature("hmm2").set("C01_mat", "userdef");
    model.component("comp1").physics("solid").feature("hmm2").set("C01", "C01");
    model.component("comp1").physics("solid").feature("hmm2").set("kappa", "kappa");
    model.component("comp1").physics("solid").create("hmm3", "HyperelasticModel", 2);
    model.component("comp1").physics("solid").feature("hmm3").label("Ogden");
    model.component("comp1").physics("solid").feature("hmm3").selection().all();
    model.component("comp1").physics("solid").feature("hmm3").set("MaterialModel", "Ogden");
    model.component("comp1").physics("solid").feature("hmm3").setIndex("mup", 0, 1, 0);
    model.component("comp1").physics("solid").feature("hmm3").setIndex("alphap", 0, 1, 0);
    model.component("comp1").physics("solid").feature("hmm3").setIndex("alphap", 0, 1, 0);
    model.component("comp1").physics("solid").feature("hmm3").setIndex("mup", 0, 1, 0);
    model.component("comp1").physics("solid").feature("hmm3").setIndex("alphap", 0, 1, 0);
    model.component("comp1").physics("solid").feature("hmm3").setIndex("mup", 0, 2, 0);
    model.component("comp1").physics("solid").feature("hmm3").setIndex("alphap", 0, 2, 0);
    model.component("comp1").physics("solid").feature("hmm3").setIndex("alphap", 0, 2, 0);
    model.component("comp1").physics("solid").feature("hmm3").setIndex("mup", 0, 2, 0);
    model.component("comp1").physics("solid").feature("hmm3").setIndex("alphap", 0, 2, 0);
    model.component("comp1").physics("solid").feature("hmm3").setIndex("mup", "mu1", 0, 0);
    model.component("comp1").physics("solid").feature("hmm3").setIndex("alphap", "alpha1", 0, 0);
    model.component("comp1").physics("solid").feature("hmm3").setIndex("mup", "mu2", 1, 0);
    model.component("comp1").physics("solid").feature("hmm3").setIndex("alphap", "alpha2", 1, 0);
    model.component("comp1").physics("solid").feature("hmm3").setIndex("mup", "mu3", 2, 0);
    model.component("comp1").physics("solid").feature("hmm3").setIndex("alphap", "alpha3", 2, 0);
    model.component("comp1").physics("solid").feature("hmm3").set("kappa", "kappa");
    model.component("comp1").physics("solid").create("hmm4", "HyperelasticModel", 2);
    model.component("comp1").physics("solid").feature("hmm4").label("Varga");
    model.component("comp1").physics("solid").feature("hmm4").selection().all();
    model.component("comp1").physics("solid").feature("hmm4").set("MaterialModel", "Varga");
    model.component("comp1").physics("solid").feature("hmm4").set("c1VA_mat", "userdef");
    model.component("comp1").physics("solid").feature("hmm4").set("c1VA", "2*mu");
    model.component("comp1").physics("solid").feature("hmm4").set("c2VA_mat", "userdef");
    model.component("comp1").physics("solid").feature("hmm4").set("kappa", "kappa");
    model.component("comp1").physics("solid").create("roll1", "Roller", 1);
    model.component("comp1").physics("solid").feature("roll1").selection().set(1, 2);
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 1);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(3);
    model.component("comp1").physics("solid").feature("bndl1").set("forceType", "FollowerPressure");
    model.component("comp1").physics("solid").feature("bndl1").set("pressure", "p_f");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop1").selection().set(3);
    model.component("comp1").cpl("intop1").set("frame", "material");
    model.component("comp1").cpl("intop1").set("axisym", false);

    model.component("comp1").variable("var1").set("ub", "intop1(u)");
    model.component("comp1").variable("var1").descr("ub", "\u5f84\u5411\u4f4d\u79fb\uff0c\u5185\u8fb9\u754c");

    model.component("comp1").physics("solid").create("ge1", "GlobalEquations", -1);
    model.component("comp1").physics("solid").feature("ge1").setIndex("name", "p_f", 0, 0);
    model.component("comp1").physics("solid").feature("ge1").setIndex("equation", "ub-u_appl", 0, 0);
    model.component("comp1").physics("solid").feature("ge1").set("DependentVariableQuantity", "pressure");
    model.component("comp1").physics("solid").feature("ge1").set("SourceTermQuantity", "displacement");

    model.component("comp1").variable("var1")
         .set("p_Ogden", "2*(H/Ri)*(mu1*(stretch^(alpha1-3)-stretch^(-2*alpha1-3))+mu2*(stretch^(alpha2-3)-stretch^(-2*alpha2-3))+mu3*(stretch^(alpha3-3)-stretch^(-2*alpha3-3)))");
    model.component("comp1").variable("var1").descr("p_Ogden", "\u538b\u529b\uff08Ogden\uff0c\u89e3\u6790\uff09");
    model.component("comp1").variable("var1")
         .set("sp1_Ogden", "mu1*(stretch^alpha1-stretch^(-2*alpha1))+mu2*(stretch^alpha2-stretch^(-2*alpha2))+mu3*(stretch^alpha3-stretch^(-2*alpha3))");
    model.component("comp1").variable("var1")
         .descr("sp1_Ogden", "\u73af\u5411\u5e94\u529b\uff08Ogden\uff0c\u89e3\u6790\uff09");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 3);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(3);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 50);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat")
         .set("disabledphysics", new String[]{"solid/hmm2", "solid/hmm3", "solid/hmm4"});
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "Ri", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "Ri", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "stretch", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(1, 0.1, 2) range(2.2, 0.2, 10)", 0);
    model.study("std1").label("Neo-Hookean");
    model.study("std1").setGenPlots(false);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v1").set("scalemethod", "manual");
    model.sol("sol1").feature("s1").feature("dDef").set("linsolver", "pardiso");
    model.sol("sol1").feature("s1").feature("p1").set("porder", "constant");
    model.sol("sol1").feature("s1").feature("fc1").set("dtech", "const");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std2").label("Mooney\u2013Rivlin");
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("stat").set("useadvanceddisable", true);
    model.study("std2").feature("stat")
         .set("disabledphysics", new String[]{"solid/hmm1", "solid/hmm3", "solid/hmm4"});
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "Ri", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "m", 0);
    model.study("std2").feature("stat").setIndex("pname", "Ri", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "m", 0);
    model.study("std2").feature("stat").setIndex("pname", "stretch", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "range(1, 0.1, 5)", 0);
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("v1").set("scalemethod", "manual");
    model.sol("sol2").feature("s1").feature("dDef").set("linsolver", "pardiso");
    model.sol("sol2").feature("s1").feature("p1").set("porder", "constant");
    model.sol("sol2").feature("s1").feature("fc1").set("dtech", "const");

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std3").label("Ogden");
    model.study("std3").setGenPlots(false);
    model.study("std3").feature("stat").set("useadvanceddisable", true);
    model.study("std3").feature("stat")
         .set("disabledphysics", new String[]{"solid/hmm1", "solid/hmm2", "solid/hmm4"});
    model.study("std3").feature("stat").set("useparam", true);
    model.study("std3").feature("stat").setIndex("pname", "Ri", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "m", 0);
    model.study("std3").feature("stat").setIndex("pname", "Ri", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "m", 0);
    model.study("std3").feature("stat").setIndex("pname", "stretch", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "range(1, 0.1, 2) range(2.2, 0.2, 10)", 0);
    model.study("std3").showAutoSequences("all");

    model.sol("sol3").feature("v1").set("scalemethod", "manual");
    model.sol("sol3").feature("s1").feature("dDef").set("linsolver", "pardiso");
    model.sol("sol3").feature("s1").feature("p1").set("porder", "constant");
    model.sol("sol3").feature("s1").feature("fc1").set("dtech", "const");

    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.study().create("std4");
    model.study("std4").create("stat", "Stationary");
    model.study("std4").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std4").setGenPlots(false);
    model.study("std4").feature("stat").set("useadvanceddisable", true);
    model.study("std4").feature("stat")
         .set("disabledphysics", new String[]{"solid/hmm1", "solid/hmm2", "solid/hmm3"});
    model.study("std4").feature("stat").set("useparam", true);
    model.study("std4").feature("stat").setIndex("pname", "Ri", 0);
    model.study("std4").feature("stat").setIndex("plistarr", "", 0);
    model.study("std4").feature("stat").setIndex("punit", "m", 0);
    model.study("std4").feature("stat").setIndex("pname", "Ri", 0);
    model.study("std4").feature("stat").setIndex("plistarr", "", 0);
    model.study("std4").feature("stat").setIndex("punit", "m", 0);
    model.study("std4").feature("stat").setIndex("pname", "stretch", 0);
    model.study("std4").feature("stat").setIndex("plistarr", "range(1, 0.1, 2) range(2.2, 0.2, 7)", 0);
    model.study("std4").label("Varga");
    model.study("std4").showAutoSequences("all");

    model.sol("sol4").feature("v1").set("scalemethod", "manual");
    model.sol("sol4").feature("s1").feature("dDef").set("mumpsalloc", 2.1);
    model.sol("sol4").feature("s1").feature("p1").set("porder", "constant");
    model.sol("sol4").feature("s1").feature("fc1").set("dtech", "const");

    model.study("std4").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"pressure", "\u538b\u529b", "Pa", "Pa"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "kPa", 0, 3);
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 1);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 1, 3);
    model.result().configuration("prfu1").apply();
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 51, 0);
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("resolution", "normal");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("surf1").feature("def").set("scale", "1");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "w"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").set("frametype", "material");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "solid.sp1");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("def").set("scale", 0.05);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u81a8\u80c0\u538b\u529b");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u81a8\u80c0\u538b\u529b (kPa)");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u81a8\u80c0\u538b\u529b vs. \u4f38\u957f\u7387");
    model.result("pg2").create("ptgr1", "PointGraph");
    model.result("pg2").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg2").feature("ptgr1").set("linewidth", "preference");
    model.result("pg2").feature("ptgr1").set("data", "dset1");
    model.result("pg2").feature("ptgr1").selection().set(3);
    model.result("pg2").feature("ptgr1").set("expr", "p_f");
    model.result("pg2").feature("ptgr1").set("xdataexpr", "stretch");
    model.result("pg2").feature("ptgr1").set("xdatadescr", "\u5916\u52a0\u4f38\u957f\u7387");
    model.result("pg2").feature("ptgr1").set("legend", true);
    model.result("pg2").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg2").feature("ptgr1").setIndex("legends", "Neo-Hookean", 0);
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr2").set("data", "dset2");
    model.result("pg2").feature("ptgr2").setIndex("legends", "Mooney\u2013Rivlin", 0);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("ptgr3", "ptgr1");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr3").set("data", "dset3");
    model.result("pg2").feature("ptgr3").setIndex("legends", "Ogden", 0);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("ptgr4", "ptgr1");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr4").set("data", "dset4");
    model.result("pg2").feature("ptgr4").setIndex("legends", "Varga", 0);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("ptgr5", "ptgr1");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr5").set("data", "dset3");
    model.result("pg2").feature("ptgr5").set("expr", "p_Ogden");
    model.result("pg2").feature("ptgr5").set("linestyle", "none");
    model.result("pg2").feature("ptgr5").set("linecolor", "fromtheme");
    model.result("pg2").feature("ptgr5").set("linemarker", "asterisk");
    model.result("pg2").feature("ptgr5").set("markerpos", "interp");
    model.result("pg2").feature("ptgr5").set("markers", 40);
    model.result("pg2").feature("ptgr5").setIndex("legends", "\u89e3\u6790\u503c (Ogden)", 0);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").label("\u7b2c\u4e00\u4e3b\u5e94\u529b");
    model.result("pg3").set("title", "\u7b2c\u4e00\u4e3b\u5e94\u529b vs. \u4f38\u957f\u7387");
    model.result("pg3").set("ylabel", "\u7b2c\u4e00\u4e3b\u5e94\u529b (MPa)");
    model.result("pg3").set("axislimits", true);
    model.result("pg3").set("ymax", 40);
    model.result("pg3").run();
    model.result("pg3").feature("ptgr1").set("expr", "solid.sp1");
    model.result("pg3").run();
    model.result("pg3").feature("ptgr2").set("expr", "solid.sp1");
    model.result("pg3").run();
    model.result("pg3").feature("ptgr3").set("expr", "solid.sp1");
    model.result("pg3").run();
    model.result("pg3").feature("ptgr4").set("expr", "solid.sp1");
    model.result().configuration("prfu1").apply();
    model.result("pg3").run();
    model.result("pg3").feature("ptgr5").set("expr", "sp1_Ogden");
    model.result("pg3").feature("ptgr5").set("unit", "MPa");
    model.result("pg3").run();
    model.result("pg2").run();

    model.title("\u7403\u5f62\u6a61\u80f6\u6c14\u7403\u7684\u81a8\u80c0");

    model
         .description("\u672c\u4f8b\u7814\u7a76\u7403\u5f62\u6a61\u80f6\u6c14\u7403\u7684\u81a8\u80c0\u8fc7\u7a0b\uff0c\u4f7f\u7528\u4e86\u56db\u79cd\u4e0d\u540c\u7684\u8d85\u5f39\u6027\u6750\u6599\u6a21\u578b\uff1aNeo-Hookean\u3001Mooney-Rivlin\u3001Ogden \u548c Varga\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("balloon_inflation.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
