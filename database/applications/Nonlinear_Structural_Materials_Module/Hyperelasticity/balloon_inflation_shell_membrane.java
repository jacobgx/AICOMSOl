/*
 * balloon_inflation_shell_membrane.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:30 by COMSOL 6.3.0.290. */
public class balloon_inflation_shell_membrane {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Nonlinear_Structural_Materials_Module\\Hyperelasticity");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("shell", "Shell", "geom1");
    model.component("comp1").physics().create("mbrn", "StructuralMembrane", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/shell", true);
    model.study("std1").feature("stat").setSolveFor("/physics/mbrn", true);

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
    model.param().descr("C10", "Mooney-Rivlin \u53c2\u6570 C10");
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

    model.func().create("int1", "Interpolation");
    model.func("int1")
         .set("table", new String[][]{{"1.1000", "0.82849"}, 
         {"1.2000", "0.69736"}, 
         {"1.3000", "0.59494"}, 
         {"1.4000", "0.51345"}, 
         {"1.5000", "0.44758"}, 
         {"1.6000", "0.39359"}, 
         {"1.7000", "0.34879"}, 
         {"1.8000", "0.31121"}, 
         {"1.9000", "0.27939"}, 
         {"2.0000", "0.25220"}, 
         {"2.2000", "0.20850"}, 
         {"2.4000", "0.17523"}, 
         {"2.6000", "0.14934"}, 
         {"2.8000", "0.12878"}, 
         {"3.0000", "0.11219"}, 
         {"3.2000", "0.098613"}, 
         {"3.4000", "0.087358"}, 
         {"3.6000", "0.077925"}, 
         {"3.8000", "0.069941"}, 
         {"4.0000", "0.063124"}, 
         {"4.2000", "0.057258"}, 
         {"4.4000", "0.052172"}, 
         {"4.6000", "0.047735"}, 
         {"4.8000", "0.043841"}, 
         {"5.0000", "0.040405"}, 
         {"5.2000", "0.037358"}, 
         {"5.4000", "0.034642"}, 
         {"5.6000", "0.032213"}, 
         {"5.8000", "0.030030"}, 
         {"6.0000", "0.028062"}, 
         {"6.2000", "0.026281"}, 
         {"6.4000", "0.024665"}, 
         {"6.6000", "0.023193"}, 
         {"6.8000", "0.021849"}, 
         {"7.0000", "0.020619"}, 
         {"7.2000", "0.019490"}, 
         {"7.4000", "0.018451"}, 
         {"7.6000", "0.017493"}, 
         {"7.8000", "0.016608"}, 
         {"8.0000", "0.015788"}, 
         {"8.2000", "0.015028"}, 
         {"8.4000", "0.014321"}, 
         {"8.6000", "0.013663"}, 
         {"8.8000", "0.013049"}, 
         {"9.0000", "0.012476"}, 
         {"9.2000", "0.011940"}, 
         {"9.4000", "0.011437"}, 
         {"9.6000", "0.010966"}, 
         {"9.8000", "0.010523"}, 
         {"10.000", "0.010107"}});
    model.func("int1").setIndex("argunit", 1, 0);
    model.func("int1").setIndex("fununit", "mm", 0);

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("u_appl", "(stretch-1)*Ri");
    model.component("comp1").variable("var1").descr("u_appl", "\u5916\u52a0\u4f4d\u79fb");

    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("type", "curve");
    model.component("comp1").geom("geom1").feature("c1").set("r", "Ri+H/2");
    model.component("comp1").geom("geom1").feature("c1").set("angle", 20);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("c1", 2, 3);
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup().create("shell", "shell", "Layered_material");
    model.component("comp1").material("mat1").propertyGroup("shell").set("lth", "1e-4[m]");
    model.component("comp1").material("mat1").selection().geom("geom1", 1);
    model.component("comp1").material("mat1").selection().all();
    model.component("comp1").material("mat1").label("\u8d85\u5f39\u6027\u6750\u6599");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"1000"});
    model.component("comp1").material("mat1").propertyGroup("shell").set("lth", new String[]{"H"});

    model.component("comp1").physics("shell").create("lhmm1", "LayeredHyperelasticModel", 1);
    model.component("comp1").physics("shell").feature("lhmm1").label("Neo-Hookean");
    model.component("comp1").physics("shell").feature("lhmm1").selection().all();
    model.component("comp1").physics("shell").feature("lhmm1")
         .set("Compressibility_NeoHookean", "NearlyIncompressible");
    model.component("comp1").physics("shell").feature("lhmm1").set("muLame_mat", "userdef");
    model.component("comp1").physics("shell").feature("lhmm1").set("muLame", "mu");
    model.component("comp1").physics("shell").feature("lhmm1").set("kappa", "kappa");
    model.component("comp1").physics("shell").create("lhmm2", "LayeredHyperelasticModel", 1);
    model.component("comp1").physics("shell").feature("lhmm2").label("Mooney\u2013Rivlin");
    model.component("comp1").physics("shell").feature("lhmm2").selection().all();
    model.component("comp1").physics("shell").feature("lhmm2").set("MaterialModel", "MooneyRivlin");
    model.component("comp1").physics("shell").feature("lhmm2").set("C10_mat", "userdef");
    model.component("comp1").physics("shell").feature("lhmm2").set("C10", "C10");
    model.component("comp1").physics("shell").feature("lhmm2").set("C01_mat", "userdef");
    model.component("comp1").physics("shell").feature("lhmm2").set("C01", "C01");
    model.component("comp1").physics("shell").feature("lhmm2").set("kappa", "kappa");
    model.component("comp1").physics("shell").create("lhmm3", "LayeredHyperelasticModel", 1);
    model.component("comp1").physics("shell").feature("lhmm3").label("Ogden");
    model.component("comp1").physics("shell").feature("lhmm3").selection().all();
    model.component("comp1").physics("shell").feature("lhmm3").set("MaterialModel", "Ogden");
    model.component("comp1").physics("shell").feature("lhmm3").setIndex("mup", 0, 1, 0);
    model.component("comp1").physics("shell").feature("lhmm3").setIndex("alphap", 0, 1, 0);
    model.component("comp1").physics("shell").feature("lhmm3").setIndex("alphap", 0, 1, 0);
    model.component("comp1").physics("shell").feature("lhmm3").setIndex("mup", 0, 1, 0);
    model.component("comp1").physics("shell").feature("lhmm3").setIndex("alphap", 0, 1, 0);
    model.component("comp1").physics("shell").feature("lhmm3").setIndex("mup", 0, 2, 0);
    model.component("comp1").physics("shell").feature("lhmm3").setIndex("alphap", 0, 2, 0);
    model.component("comp1").physics("shell").feature("lhmm3").setIndex("alphap", 0, 2, 0);
    model.component("comp1").physics("shell").feature("lhmm3").setIndex("mup", 0, 2, 0);
    model.component("comp1").physics("shell").feature("lhmm3").setIndex("alphap", 0, 2, 0);
    model.component("comp1").physics("shell").feature("lhmm3").setIndex("mup", "mu1", 0, 0);
    model.component("comp1").physics("shell").feature("lhmm3").setIndex("alphap", "alpha1", 0, 0);
    model.component("comp1").physics("shell").feature("lhmm3").setIndex("mup", "mu2", 1, 0);
    model.component("comp1").physics("shell").feature("lhmm3").setIndex("alphap", "alpha2", 1, 0);
    model.component("comp1").physics("shell").feature("lhmm3").setIndex("mup", "mu3", 2, 0);
    model.component("comp1").physics("shell").feature("lhmm3").setIndex("alphap", "alpha3", 2, 0);
    model.component("comp1").physics("shell").feature("lhmm3").set("kappa", "kappa");
    model.component("comp1").physics("shell").create("lhmm4", "LayeredHyperelasticModel", 1);
    model.component("comp1").physics("shell").feature("lhmm4").label("Varga");
    model.component("comp1").physics("shell").feature("lhmm4").selection().all();
    model.component("comp1").physics("shell").feature("lhmm4").set("MaterialModel", "Varga");
    model.component("comp1").physics("shell").feature("lhmm4").set("c1VA_mat", "userdef");
    model.component("comp1").physics("shell").feature("lhmm4").set("c1VA", "2*mu");
    model.component("comp1").physics("shell").feature("lhmm4").set("c2VA_mat", "userdef");
    model.component("comp1").physics("shell").feature("lhmm4").set("kappa", "kappa");
    model.component("comp1").physics("shell").create("disp1", "Displacement1", 0);
    model.component("comp1").physics("shell").feature("disp1").selection().set(2);
    model.component("comp1").physics("shell").feature("disp1").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("shell").feature("disp1").set("RotationType", "RotationGroup");

    model.component("comp1").coordSystem().create("sys2", "Rotated");
    model.component("comp1").coordSystem("sys2").set("inPlaneAngle", "20[deg]");

    model.component("comp1").physics("shell").create("disp2", "Displacement1", 0);
    model.component("comp1").physics("shell").feature("disp2").selection().set(1);
    model.component("comp1").physics("shell").feature("disp2").set("coordinateSystem", "sys2");
    model.component("comp1").physics("shell").feature("disp2").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("shell").feature("disp2").set("RotationType", "RotationGroup");
    model.component("comp1").physics("shell").create("disp3", "Displacement2", 1);
    model.component("comp1").physics("shell").feature("disp3").selection().all();
    model.component("comp1").physics("shell").feature("disp3").set("coordinateSystem", "sys1");
    model.component("comp1").physics("shell").feature("disp3").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("shell").feature("disp3").setIndex("U0", "-1[mm]", 2);
    model.component("comp1").physics("shell").feature("disp3").set("RotationType", "RotationGroup");
    model.component("comp1").physics("shell").create("fl1", "FaceLoad", 1);
    model.component("comp1").physics("shell").feature("fl1").selection().all();
    model.component("comp1").physics("shell").feature("fl1").set("forceType", "FollowerPressure");
    model.component("comp1").physics("shell").feature("fl1").set("pressure", "pf_s");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop1").selection().set(2);
    model.component("comp1").cpl("intop1").set("frame", "material");
    model.component("comp1").cpl("intop1").set("axisym", false);

    model.component("comp1").variable("var1").set("us", "intop1(u)");
    model.component("comp1").variable("var1").descr("us", "\u5f84\u5411\u4f4d\u79fb\uff0c\u58f3");

    model.component("comp1").physics("shell").create("ge1", "GlobalEquations", -1);
    model.component("comp1").physics("shell").feature("ge1").setIndex("name", "pf_s", 0, 0);
    model.component("comp1").physics("shell").feature("ge1").setIndex("equation", "us-u_appl", 0, 0);
    model.component("comp1").physics("shell").feature("ge1").set("DependentVariableQuantity", "pressure");
    model.component("comp1").physics("shell").feature("ge1").set("SourceTermQuantity", "length");
    model.component("comp1").physics("mbrn").feature("to1").set("d", "H");
    model.component("comp1").physics("mbrn").create("hmm1", "HyperelasticModel", 1);
    model.component("comp1").physics("mbrn").feature("hmm1").label("Neo-Hookean");
    model.component("comp1").physics("mbrn").feature("hmm1").selection().all();
    model.component("comp1").physics("mbrn").feature("hmm1")
         .set("Compressibility_NeoHookean", "NearlyIncompressible");
    model.component("comp1").physics("mbrn").feature("hmm1").set("muLame_mat", "userdef");
    model.component("comp1").physics("mbrn").feature("hmm1").set("muLame", "mu");
    model.component("comp1").physics("mbrn").feature("hmm1").set("kappa", "kappa");
    model.component("comp1").physics("mbrn").create("hmm2", "HyperelasticModel", 1);
    model.component("comp1").physics("mbrn").feature("hmm2").label("Mooney\u2013Rivlin");
    model.component("comp1").physics("mbrn").feature("hmm2").selection().all();
    model.component("comp1").physics("mbrn").feature("hmm2").set("MaterialModel", "MooneyRivlin");
    model.component("comp1").physics("mbrn").feature("hmm2").set("C10_mat", "userdef");
    model.component("comp1").physics("mbrn").feature("hmm2").set("C10", "C10");
    model.component("comp1").physics("mbrn").feature("hmm2").set("C01_mat", "userdef");
    model.component("comp1").physics("mbrn").feature("hmm2").set("C01", "C01");
    model.component("comp1").physics("mbrn").feature("hmm2").set("kappa", "kappa");
    model.component("comp1").physics("mbrn").create("hmm3", "HyperelasticModel", 1);
    model.component("comp1").physics("mbrn").feature("hmm3").label("Ogden");
    model.component("comp1").physics("mbrn").feature("hmm3").selection().all();
    model.component("comp1").physics("mbrn").feature("hmm3").set("MaterialModel", "Ogden");
    model.component("comp1").physics("mbrn").feature("hmm3").setIndex("mup", 0, 1, 0);
    model.component("comp1").physics("mbrn").feature("hmm3").setIndex("alphap", 0, 1, 0);
    model.component("comp1").physics("mbrn").feature("hmm3").setIndex("alphap", 0, 1, 0);
    model.component("comp1").physics("mbrn").feature("hmm3").setIndex("mup", 0, 1, 0);
    model.component("comp1").physics("mbrn").feature("hmm3").setIndex("alphap", 0, 1, 0);
    model.component("comp1").physics("mbrn").feature("hmm3").setIndex("mup", 0, 2, 0);
    model.component("comp1").physics("mbrn").feature("hmm3").setIndex("alphap", 0, 2, 0);
    model.component("comp1").physics("mbrn").feature("hmm3").setIndex("alphap", 0, 2, 0);
    model.component("comp1").physics("mbrn").feature("hmm3").setIndex("mup", 0, 2, 0);
    model.component("comp1").physics("mbrn").feature("hmm3").setIndex("alphap", 0, 2, 0);
    model.component("comp1").physics("mbrn").feature("hmm3").setIndex("mup", "mu1", 0, 0);
    model.component("comp1").physics("mbrn").feature("hmm3").setIndex("alphap", "alpha1", 0, 0);
    model.component("comp1").physics("mbrn").feature("hmm3").setIndex("mup", "mu2", 1, 0);
    model.component("comp1").physics("mbrn").feature("hmm3").setIndex("alphap", "alpha2", 1, 0);
    model.component("comp1").physics("mbrn").feature("hmm3").setIndex("mup", "mu3", 2, 0);
    model.component("comp1").physics("mbrn").feature("hmm3").setIndex("alphap", "alpha3", 2, 0);
    model.component("comp1").physics("mbrn").feature("hmm3").set("kappa", "kappa");
    model.component("comp1").physics("mbrn").create("hmm4", "HyperelasticModel", 1);
    model.component("comp1").physics("mbrn").feature("hmm4").label("Varga");
    model.component("comp1").physics("mbrn").feature("hmm4").selection().all();
    model.component("comp1").physics("mbrn").feature("hmm4").set("MaterialModel", "Varga");
    model.component("comp1").physics("mbrn").feature("hmm4").set("c1VA_mat", "userdef");
    model.component("comp1").physics("mbrn").feature("hmm4").set("c1VA", "2*mu");
    model.component("comp1").physics("mbrn").feature("hmm4").set("c2VA_mat", "userdef");
    model.component("comp1").physics("mbrn").feature("hmm4").set("kappa", "kappa");
    model.component("comp1").physics("mbrn").create("disp1", "Displacement0", 0);
    model.component("comp1").physics("mbrn").feature("disp1").selection().set(2);
    model.component("comp1").physics("mbrn").feature("disp1").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("mbrn").create("disp2", "Displacement0", 0);
    model.component("comp1").physics("mbrn").feature("disp2").selection().set(1);
    model.component("comp1").physics("mbrn").feature("disp2").set("coordinateSystem", "sys2");
    model.component("comp1").physics("mbrn").feature("disp2").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("mbrn").create("disp3", "Displacement1", 1);
    model.component("comp1").physics("mbrn").feature("disp3").selection().all();
    model.component("comp1").physics("mbrn").feature("disp3").set("coordinateSystem", "sys1");
    model.component("comp1").physics("mbrn").feature("disp3").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("mbrn").feature("disp3").setIndex("U0", "-1[mm]", 2);
    model.component("comp1").physics("mbrn").create("fl1", "FaceLoad", 1);
    model.component("comp1").physics("mbrn").feature("fl1").selection().all();
    model.component("comp1").physics("mbrn").feature("fl1").set("forceType", "FollowerPressure");
    model.component("comp1").physics("mbrn").feature("fl1").set("pressure", "pf_m");

    model.component("comp1").variable("var1").set("um", "intop1(u2)");
    model.component("comp1").variable("var1").descr("um", "\u5f84\u5411\u4f4d\u79fb\uff0c\u819c");

    model.component("comp1").physics("mbrn").create("ge1", "GlobalEquations", -1);
    model.component("comp1").physics("mbrn").feature("ge1").setIndex("name", "pf_m", 0, 0);
    model.component("comp1").physics("mbrn").feature("ge1").setIndex("equation", "um-u_appl", 0, 0);
    model.component("comp1").physics("mbrn").feature("ge1").set("DependentVariableQuantity", "pressure");
    model.component("comp1").physics("mbrn").feature("ge1").set("SourceTermQuantity", "length");

    model.component("comp1").variable("var1")
         .set("p_Ogden", "2*(H/Ri)*(mu1*(stretch^(alpha1-3)-stretch^(-2*alpha1-3))+mu2*(stretch^(alpha2-3)-stretch^(-2*alpha2-3))+mu3*(stretch^(alpha3-3)-stretch^(-2*alpha3-3)))");
    model.component("comp1").variable("var1").descr("p_Ogden", "\u538b\u529b\uff08Ogden\uff0c\u89e3\u6790\uff09");
    model.component("comp1").variable("var1")
         .set("sp1_Ogden", "mu1*(stretch^alpha1-stretch^(-2*alpha1))+mu2*(stretch^alpha2-stretch^(-2*alpha2))+mu3*(stretch^alpha3-stretch^(-2*alpha3))");
    model.component("comp1").variable("var1")
         .descr("sp1_Ogden", "\u73af\u5411\u5e94\u529b\uff08Ogden\uff0c\u89e3\u6790\uff09");

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().all();
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("numelem", 50);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76\uff1a\u9884\u62c9\u4f38");
    model.study("std1").setGenPlots(false);
    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disableFrameControl", new String[]{"shell"});
    model.study("std1").feature("stat")
         .set("disabledphysics", new String[]{"shell/fl1", "shell/ge1", "mbrn/fl1", "mbrn/ge1"});
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v1").feature("comp1_ar").set("scaleval", "1e-9");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/shell", true);
    model.study("std2").feature("stat").setSolveFor("/physics/mbrn", true);
    model.study("std2").label("\u7814\u7a76\uff1aNeo-Hookean");
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("stat").set("useadvanceddisable", true);
    model.study("std2").feature("stat").set("disableFrameControl", new String[]{"shell"});
    model.study("std2").feature("stat")
         .set("disabledphysics", new String[]{"shell/lhmm2", "shell/lhmm3", "shell/lhmm4", "shell/disp3", "mbrn/hmm2", "mbrn/hmm3", "mbrn/hmm4", "mbrn/disp3"});
    model.study("std2").feature("stat").set("useinitsol", true);
    model.study("std2").feature("stat").set("initmethod", "sol");
    model.study("std2").feature("stat").set("initstudy", "std1");
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "Ri", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "m", 0);
    model.study("std2").feature("stat").setIndex("pname", "Ri", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "m", 0);
    model.study("std2").feature("stat").setIndex("pname", "stretch", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "range(1.1,0.1,2) range(2.2,0.2,10)", 0);
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("v1").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_ar").set("scaleval", "1e-9");
    model.sol("sol2").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_u").set("scaleval", "1e-3");
    model.sol("sol2").feature("s1").feature("p1").set("porder", "constant");
    model.sol("sol2").feature("s1").feature("fc1").set("dtech", "const");
    model.sol("sol2").feature("s1").feature("fc1").set("stabacc", "aacc");

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").setSolveFor("/physics/shell", true);
    model.study("std3").feature("stat").setSolveFor("/physics/mbrn", true);
    model.study("std3").label("\u7814\u7a76\uff1aMooney\u2013Rivlin");
    model.study("std3").setGenPlots(false);
    model.study("std3").feature("stat").set("useadvanceddisable", true);
    model.study("std3").feature("stat").set("disableFrameControl", new String[]{"shell"});
    model.study("std3").feature("stat")
         .set("disabledphysics", new String[]{"shell/lhmm1", "shell/lhmm3", "shell/lhmm4", "shell/disp3", "mbrn/hmm1", "mbrn/hmm3", "mbrn/hmm4", "mbrn/disp3"});
    model.study("std3").feature("stat").set("useinitsol", true);
    model.study("std3").feature("stat").set("initmethod", "sol");
    model.study("std3").feature("stat").set("initstudy", "std1");
    model.study("std3").feature("stat").set("useparam", true);
    model.study("std3").feature("stat").setIndex("pname", "Ri", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "m", 0);
    model.study("std3").feature("stat").setIndex("pname", "Ri", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "m", 0);
    model.study("std3").feature("stat").setIndex("pname", "stretch", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "range(1.1,0.1,5)", 0);
    model.study("std3").showAutoSequences("all");

    model.sol("sol3").feature("v1").set("scalemethod", "manual");
    model.sol("sol3").feature("v1").feature("comp1_ar").set("scaleval", "1e-9");
    model.sol("sol3").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol3").feature("v1").feature("comp1_u").set("scaleval", "1e-3");
    model.sol("sol3").feature("s1").feature("p1").set("porder", "constant");
    model.sol("sol3").feature("s1").feature("fc1").set("dtech", "const");
    model.sol("sol3").feature("s1").feature("fc1").set("stabacc", "aacc");

    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.study().create("std4");
    model.study("std4").create("stat", "Stationary");
    model.study("std4").feature("stat").setSolveFor("/physics/shell", true);
    model.study("std4").feature("stat").setSolveFor("/physics/mbrn", true);
    model.study("std4").label("\u7814\u7a76\uff1aOgden");
    model.study("std4").setGenPlots(false);
    model.study("std4").feature("stat").set("useadvanceddisable", true);
    model.study("std4").feature("stat").set("disableFrameControl", new String[]{"shell"});
    model.study("std4").feature("stat")
         .set("disabledphysics", new String[]{"shell/lhmm1", "shell/lhmm2", "shell/lhmm4", "shell/disp3", "mbrn/hmm1", "mbrn/hmm2", "mbrn/hmm4", "mbrn/disp3"});
    model.study("std4").feature("stat").set("useinitsol", true);
    model.study("std4").feature("stat").set("initmethod", "sol");
    model.study("std4").feature("stat").set("initstudy", "std1");
    model.study("std4").feature("stat").set("useparam", true);
    model.study("std4").feature("stat").setIndex("pname", "Ri", 0);
    model.study("std4").feature("stat").setIndex("plistarr", "", 0);
    model.study("std4").feature("stat").setIndex("punit", "m", 0);
    model.study("std4").feature("stat").setIndex("pname", "Ri", 0);
    model.study("std4").feature("stat").setIndex("plistarr", "", 0);
    model.study("std4").feature("stat").setIndex("punit", "m", 0);
    model.study("std4").feature("stat").setIndex("pname", "stretch", 0);
    model.study("std4").feature("stat").setIndex("plistarr", "range(1.1,0.1,2) range(2.2,0.2,10)", 0);
    model.study("std4").showAutoSequences("all");

    model.sol("sol4").feature("v1").set("scalemethod", "manual");
    model.sol("sol4").feature("v1").feature("comp1_ar").set("scaleval", "1e-9");
    model.sol("sol4").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol4").feature("v1").feature("comp1_u").set("scaleval", "1e-3");
    model.sol("sol4").feature("s1").feature("p1").set("porder", "constant");
    model.sol("sol4").feature("s1").feature("fc1").set("dtech", "const");
    model.sol("sol4").feature("s1").feature("fc1").set("stabacc", "aacc");

    model.study("std4").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.study().create("std5");
    model.study("std5").create("stat", "Stationary");
    model.study("std5").feature("stat").setSolveFor("/physics/shell", true);
    model.study("std5").feature("stat").setSolveFor("/physics/mbrn", true);
    model.study("std5").label("\u7814\u7a76\uff1aVarga");
    model.study("std5").setGenPlots(false);
    model.study("std5").feature("stat").set("useadvanceddisable", true);
    model.study("std5").feature("stat").set("disableFrameControl", new String[]{"shell"});
    model.study("std5").feature("stat")
         .set("disabledphysics", new String[]{"shell/lhmm1", "shell/lhmm2", "shell/lhmm3", "shell/disp3", "mbrn/hmm1", "mbrn/hmm2", "mbrn/hmm3", "mbrn/disp3"});
    model.study("std5").feature("stat").set("useinitsol", true);
    model.study("std5").feature("stat").set("initmethod", "sol");
    model.study("std5").feature("stat").set("initstudy", "std1");
    model.study("std5").feature("stat").set("useparam", true);
    model.study("std5").feature("stat").setIndex("pname", "Ri", 0);
    model.study("std5").feature("stat").setIndex("plistarr", "", 0);
    model.study("std5").feature("stat").setIndex("punit", "m", 0);
    model.study("std5").feature("stat").setIndex("pname", "Ri", 0);
    model.study("std5").feature("stat").setIndex("plistarr", "", 0);
    model.study("std5").feature("stat").setIndex("punit", "m", 0);
    model.study("std5").feature("stat").setIndex("pname", "stretch", 0);
    model.study("std5").feature("stat").setIndex("plistarr", "range(1.1,0.1,2) range(2.2,0.2,10)", 0);
    model.study("std5").showAutoSequences("all");

    model.sol("sol5").feature("v1").set("scalemethod", "manual");
    model.sol("sol5").feature("v1").feature("comp1_ar").set("scaleval", "1e-9");
    model.sol("sol5").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol5").feature("v1").feature("comp1_u").set("scaleval", "1e-3");
    model.sol("sol5").feature("s1").feature("p1").set("porder", "constant");
    model.sol("sol5").feature("s1").feature("fc1").set("dtech", "const");
    model.sol("sol5").feature("s1").feature("fc1").set("stabacc", "aacc");

    model.study("std5").createAutoSequences("all");

    model.sol("sol5").runAll();

    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 0, 3);
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"pressure", "\u538b\u529b", "Pa", "Pa"}, 1);
    model.result().configuration("prfu1").setIndex("quantityunits", "kPa", 1, 3);
    model.result().configuration("prfu1").apply();
    model.result().dataset().create("lshl1", "LayeredMaterial");
    model.result().dataset("lshl1").label("Neo-Hookean");
    model.result().dataset("lshl1").set("data", "dset2");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").run();
    model.result("pg1").label("\u5e94\u529b\uff08\u58f3\uff09");
    model.result("pg1").set("data", "lshl1");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "shell.sp1");
    model.result("pg1").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg1").feature("surf1").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg1").feature("surf1").feature("def1").set("scale", 0.05);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").run();
    model.result("pg2").label("\u5e94\u529b (mbrn)");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").create("line1", "Line");
    model.result("pg2").feature("line1").set("expr", "mbrn.sp1");
    model.result("pg2").feature("line1").set("linetype", "tube");
    model.result("pg2").feature("line1").set("radiusexpr", "3");
    model.result("pg2").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg2").feature("line1").set("tuberadiusscale", 1.5E-4);
    model.result("pg2").feature("line1").set("colortable", "RainbowLight");
    model.result("pg2").feature("line1").create("def1", "Deform");
    model.result("pg2").run();
    model.result("pg2").feature("line1").feature("def1").set("expr", new String[]{"u2", "w2"});
    model.result("pg2").feature("line1").feature("def1").set("scaleactive", true);

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg2").feature("line1").feature("def1").set("scale", 0.05);
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u81a8\u80c0\u538b\u529b");
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u81a8\u80c0\u538b\u529b vs. \u6307\u5b9a\u4f38\u957f\u7387");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u81a8\u80c0\u538b\u529b (kPa)");
    model.result("pg3").set("axislimits", true);
    model.result("pg3").set("xmin", 0.95);
    model.result("pg3").set("xmax", 11);
    model.result("pg3").set("ymin", 0);
    model.result("pg3").set("ymax", 10);
    model.result("pg3").set("legendlayout", "outside");
    model.result("pg3").create("ptgr1", "PointGraph");
    model.result("pg3").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg3").feature("ptgr1").set("linewidth", "preference");
    model.result("pg3").feature("ptgr1").set("data", "dset2");
    model.result("pg3").feature("ptgr1").selection().set(2);
    model.result("pg3").feature("ptgr1").set("expr", "pf_s");
    model.result("pg3").feature("ptgr1").set("xdata", "expr");
    model.result("pg3").feature("ptgr1").set("xdataexpr", "stretch");
    model.result("pg3").feature("ptgr1").set("xdatadescr", "\u5916\u52a0\u4f38\u957f\u7387");
    model.result("pg3").feature("ptgr1").set("linecolor", "red");
    model.result("pg3").feature("ptgr1").set("legend", true);
    model.result("pg3").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg3").feature("ptgr1").setIndex("legends", "Neo-Hookean\uff0c\u58f3", 0);
    model.result("pg3").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg3").run();
    model.result("pg3").feature("ptgr2").set("expr", "pf_m");
    model.result("pg3").feature("ptgr2").set("linemarker", "circle");
    model.result("pg3").feature("ptgr2").set("markerpos", "interp");
    model.result("pg3").feature("ptgr2").setIndex("legends", "Neo-Hookean\uff0c\u819c", 0);
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("ptgr3", "ptgr1");
    model.result("pg3").run();
    model.result("pg3").feature("ptgr3").set("data", "dset3");
    model.result("pg3").feature("ptgr3").set("linecolor", "green");
    model.result("pg3").feature("ptgr3").setIndex("legends", "Mooney\u2013Rivlin\uff0c\u58f3", 0);
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("ptgr4", "ptgr2");
    model.result("pg3").run();
    model.result("pg3").feature("ptgr4").set("data", "dset3");
    model.result("pg3").feature("ptgr4").set("linecolor", "green");
    model.result("pg3").feature("ptgr4").setIndex("legends", "Mooney\u2013Rivlin\uff0c\u819c", 0);
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("ptgr5", "ptgr1");
    model.result("pg3").run();
    model.result("pg3").feature("ptgr5").set("data", "dset4");
    model.result("pg3").feature("ptgr5").set("linecolor", "blue");
    model.result("pg3").feature("ptgr5").setIndex("legends", "Ogden\uff0c\u58f3", 0);
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("ptgr6", "ptgr2");
    model.result("pg3").run();
    model.result("pg3").feature("ptgr6").set("data", "dset4");
    model.result("pg3").feature("ptgr6").set("linecolor", "blue");
    model.result("pg3").feature("ptgr6").setIndex("legends", "Ogden\uff0c\u819c", 0);
    model.result("pg3").feature().duplicate("ptgr7", "ptgr6");
    model.result("pg3").run();
    model.result("pg3").feature("ptgr7").set("expr", "p_Ogden");
    model.result("pg3").feature("ptgr7").set("linestyle", "none");
    model.result("pg3").feature("ptgr7").set("linemarker", "asterisk");
    model.result("pg3").feature("ptgr7").set("markers", 12);
    model.result("pg3").feature("ptgr7").setIndex("legends", "Ogden\uff0c\u89e3\u6790", 0);
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("ptgr8", "ptgr1");
    model.result("pg3").run();
    model.result("pg3").feature("ptgr8").set("data", "dset5");
    model.result("pg3").feature("ptgr8").set("linecolor", "magenta");
    model.result("pg3").feature("ptgr8").setIndex("legends", "Varga\uff0c\u58f3", 0);
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("ptgr9", "ptgr2");
    model.result("pg3").run();
    model.result("pg3").feature("ptgr9").set("data", "dset5");
    model.result("pg3").feature("ptgr9").set("linecolor", "magenta");
    model.result("pg3").feature("ptgr9").setIndex("legends", "Varga\uff0c\u819c", 0);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u7b2c\u4e00\u4e3b\u5e94\u529b");
    model.result("pg4").set("title", "\u7b2c\u4e00\u4e3b\u5e94\u529b vs. \u6307\u5b9a\u4f38\u957f\u7387");
    model.result("pg4").set("ylabel", "\u7b2c\u4e00\u4e3b\u5e94\u529b (MPa)");
    model.result("pg4").set("ymax", 60);
    model.result("pg4").run();
    model.result("pg4").feature("ptgr1").set("expr", "shell.atxd1(shell.d/2,mean(shell.sp1))");
    model.result("pg4").feature("ptgr1").set("unit", "MPa");
    model.result("pg4").run();
    model.result("pg4").feature("ptgr2").set("expr", "mbrn.sp1");
    model.result("pg4").run();
    model.result("pg4").feature("ptgr3").set("expr", "shell.atxd1(shell.d/2,mean(shell.sp1))");
    model.result("pg4").feature("ptgr3").set("unit", "MPa");
    model.result("pg4").run();
    model.result("pg4").feature("ptgr4").set("expr", "mbrn.sp1");
    model.result("pg4").run();
    model.result("pg4").feature("ptgr5").set("expr", "shell.atxd1(shell.d/2,mean(shell.sp1))");
    model.result("pg4").feature("ptgr5").set("unit", "MPa");
    model.result("pg4").run();
    model.result("pg4").feature("ptgr6").set("expr", "mbrn.sp1");
    model.result("pg4").run();
    model.result("pg4").feature("ptgr7").set("expr", "sp1_Ogden");
    model.result("pg4").feature("ptgr7").set("unit", "MPa");
    model.result("pg4").run();
    model.result("pg4").feature("ptgr8").set("expr", "shell.atxd1(shell.d/2,mean(shell.sp1))");
    model.result("pg4").feature("ptgr8").set("unit", "MPa");
    model.result("pg4").run();
    model.result("pg4").feature("ptgr9").set("expr", "mbrn.sp1");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u53d8\u5f62\u539a\u5ea6");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").set("titletype", "manual");
    model.result("pg5").set("title", "\u53d8\u5f62\u539a\u5ea6\u6bd4\u8f83");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u53d8\u5f62\u539a\u5ea6 (mm)");
    model.result("pg5").create("ptgr1", "PointGraph");
    model.result("pg5").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg5").feature("ptgr1").set("linewidth", "preference");
    model.result("pg5").feature("ptgr1").selection().set(2);
    model.result("pg5").feature("ptgr1").set("expr", "shell.atxd1(shell.d/2,mean(shell.ddef))");
    model.result("pg5").feature("ptgr1").set("unit", "mm");
    model.result("pg5").feature("ptgr1").set("xdata", "expr");
    model.result("pg5").feature("ptgr1").set("xdataexpr", "stretch");
    model.result("pg5").feature("ptgr1").set("xdatadescr", "\u5916\u52a0\u4f38\u957f\u7387");
    model.result("pg5").feature("ptgr1").set("linemarker", "cycle");
    model.result("pg5").feature("ptgr1").set("markerpos", "interp");
    model.result("pg5").feature("ptgr1").set("legend", true);
    model.result("pg5").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg5").feature("ptgr1").setIndex("legends", "\u58f3", 0);
    model.result("pg5").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg5").run();
    model.result("pg5").feature("ptgr2").set("expr", "mbrn.ddef");
    model.result("pg5").feature("ptgr2").set("markers", 10);
    model.result("pg5").feature("ptgr2").setIndex("legends", "\u819c", 0);
    model.result("pg5").run();
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").setIndex("expr", "int1(stretch)", 0);
    model.result("pg5").feature("glob1").setIndex("unit", "mm", 0);
    model.result("pg5").feature("glob1").setIndex("descr", "\u53d8\u5f62\u539a\u5ea6", 0);
    model.result("pg5").feature("glob1").set("xdataexpr", "stretch");
    model.result("pg5").feature("glob1").set("xdatadescr", "\u5916\u52a0\u4f38\u957f\u7387");
    model.result("pg5").feature("glob1").set("linemarker", "diamond");
    model.result("pg5").feature("glob1").set("markerpos", "interp");
    model.result("pg5").feature("glob1").set("markers", 12);
    model.result("pg5").feature("glob1").set("legendmethod", "manual");
    model.result("pg5").feature("glob1").setIndex("legends", "\u56fa\u4f53\u529b\u5b66", 0);
    model.result("pg5").run();
    model.result("pg1").run();

    model.title("\u7403\u5f62\u6a61\u80f6\u6c14\u7403\u7684\u81a8\u80c0 - \u58f3\u548c\u819c\u7248\u672c");

    model
         .description("\u6b64\u7248\u672c\u7684\u6c14\u7403\u81a8\u80c0\u6a21\u578b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u201c\u58f3\u201d\u548c\u201c\u819c\u201d\u63a5\u53e3\u6765\u6a21\u62df\u8584\u7684\u8d85\u5f39\u6027\u7ed3\u6784\u3002");

    model.label("balloon_inflation_shell_membrane.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
