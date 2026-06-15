/*
 * vibrating_membrane.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:27 by COMSOL 6.3.0.290. */
public class vibrating_membrane {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("mbrn", "StructuralMembrane", "geom1");

    model.study().create("std1");
    model.study("std1").create("eig", "Eigenfrequency");
    model.study("std1").feature("eig").set("storefact", false);
    model.study("std1").feature("eig").set("solnum", "auto");
    model.study("std1").feature("eig").set("notsolnum", "auto");
    model.study("std1").feature("eig").set("outputmap", new String[]{});
    model.study("std1").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").setSolveFor("/physics/mbrn", true);

    model.component("comp1").common().create("mpf1", "ParticipationFactors");

    model.param().set("R", "250[mm]");
    model.param().descr("R", "\u534a\u5f84");
    model.param().set("thic", "0.2[mm]");
    model.param().descr("thic", "\u539a\u5ea6");
    model.param().set("T0", "100[MPa]*thic");
    model.param().descr("T0", "\u9884\u7d27\u529b");
    model.param().set("E1", "200[GPa]");
    model.param().descr("E1", "\u6768\u6c0f\u6a21\u91cf");
    model.param().set("rho1", "7850[kg/m^3]");
    model.param().descr("rho1", "\u5bc6\u5ea6");
    model.param().set("nu1", "0.33");
    model.param().descr("nu1", "\u6cca\u677e\u6bd4");
    model.param().set("fct", "sqrt(T0/(thic*rho1))/(2*pi*R)");
    model.param().descr("fct", "\u56fa\u6709\u9891\u7387\u4e2d\u7684\u516c\u56e0\u5b50");
    model.param().set("f10", "2.4048*fct");
    model.param().descr("f10", "\u4e00\u9636\u56fa\u6709\u9891\u7387");
    model.param().set("f11", "3.8317*fct");
    model.param().descr("f11", "\u4e8c\u9636\u548c\u4e09\u9636\u56fa\u6709\u9891\u7387");
    model.param().set("f12", "5.1356*fct");
    model.param().descr("f12", "\u56db\u9636\u548c\u4e94\u9636\u56fa\u6709\u9891\u7387");
    model.param().set("f20", "5.5201*fct");
    model.param().descr("f20", "\u516d\u9636\u56fa\u6709\u9891\u7387");

    model.component("comp1").coordSystem().create("sys2", "Cylindrical");

    model.component("comp1").geom("geom1").run();
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", "R");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"E1"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"nu1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"rho1"});

    model.component("comp1").physics("mbrn").feature("to1").set("d", "thic");
    model.component("comp1").physics("mbrn").feature("lemm1").create("iss1", "InitialStressandStrain", 2);
    model.component("comp1").physics("mbrn").feature("lemm1").feature("iss1")
         .set("N0", new String[]{"T0", "0", "0", "T0"});
    model.component("comp1").physics("mbrn").create("disp1", "Displacement1", 1);
    model.component("comp1").physics("mbrn").feature("disp1").selection().set(1, 2, 3, 4);
    model.component("comp1").physics("mbrn").feature("disp1").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("mbrn").create("fix1", "Fixed", 0);
    model.component("comp1").physics("mbrn").feature("fix1").selection().set(1);
    model.component("comp1").physics("mbrn").create("disp2", "Displacement0", 0);
    model.component("comp1").physics("mbrn").feature("disp2").selection().set(2);
    model.component("comp1").physics("mbrn").feature("disp2").setIndex("Direction", "prescribed", 1);

    model.component("comp1").mesh("mesh1").autoMeshSize(6);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("eig").set("geometricNonlinearity", true);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset1").set("frametype", "spatial");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").label("\u632f\u578b (mbrn)");
    model.result("pg1").set("showlegends", false);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"mbrn.disp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().evaluationGroup().create("std1EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std1EvgFrq").set("data", "dset1");
    model.result().evaluationGroup("std1EvgFrq").label("\u7279\u5f81\u9891\u7387 (\u7814\u7a76 1)");
    model.result().evaluationGroup("std1EvgFrq").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "2*pi*freq", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "rad/s", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "\u89d2\u9891\u7387", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "imag(freq)/abs(freq)", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "\u963b\u5c3c\u6bd4", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "abs(freq)/imag(freq)/2", 2);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "\u54c1\u8d28\u56e0\u5b50", 2);
    model.result().evaluationGroup("std1EvgFrq").run();
    model.result().evaluationGroup().create("std1mpf1", "EvaluationGroup");
    model.result().evaluationGroup("std1mpf1").set("data", "dset1");
    model.result().evaluationGroup("std1mpf1").label("\u53c2\u4e0e\u56e0\u5b50 (\u7814\u7a76 1)");
    model.result().evaluationGroup("std1mpf1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.pfLnormX", 0);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "1", 0);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cX \u5e73\u79fb", 0);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.pfLnormY", 1);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cY \u5e73\u79fb", 1);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.pfLnormZ", 2);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cZ \u5e73\u79fb", 2);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.pfRnormX", 3);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "1", 3);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cX \u65cb\u8f6c", 3);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.pfRnormY", 4);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "1", 4);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cY \u65cb\u8f6c", 4);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.pfRnormZ", 5);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "1", 5);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cZ \u65cb\u8f6c", 5);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.mEffLX", 6);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "kg", 6);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cX \u5e73\u79fb", 6);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.mEffLY", 7);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "kg", 7);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cY \u5e73\u79fb", 7);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.mEffLZ", 8);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "kg", 8);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cZ \u5e73\u79fb", 8);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.mEffRX", 9);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "kg*m^2", 9);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cX \u65cb\u8f6c", 9);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.mEffRY", 10);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "kg*m^2", 10);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cY \u65cb\u8f6c", 10);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.mEffRZ", 11);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "kg*m^2", 11);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cZ \u65cb\u8f6c", 11);
    model.result().evaluationGroup("std1mpf1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "w");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{2});
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{3});
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{4});
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{5});
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{6});
    model.result("pg1").run();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").set("plotgroup", "Default");
    model.study("std2").feature("stat").set("geometricNonlinearity", true);
    model.study("std2").feature("stat").set("outputmap", new String[]{});
    model.study("std2").feature("stat").set("ngenAUX", "1");
    model.study("std2").feature("stat").set("goalngenAUX", "1");
    model.study("std2").feature("stat").set("ngenAUX", "1");
    model.study("std2").feature("stat").set("goalngenAUX", "1");
    model.study("std2").feature("stat").setSolveFor("/physics/mbrn", true);
    model.study("std2").create("eig", "Eigenfrequency");
    model.study("std2").feature("eig").set("plotgroup", "Default");
    model.study("std2").feature("eig").set("storefact", false);
    model.study("std2").feature("eig").set("geometricNonlinearity", true);
    model.study("std2").feature("eig").set("outputmap", new String[]{});
    model.study("std2").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std2").feature("eig").set("ngenAUX", "1");
    model.study("std2").feature("eig").set("goalngenAUX", "1");
    model.study("std2").feature("eig").set("ngenAUX", "1");
    model.study("std2").feature("eig").set("goalngenAUX", "1");
    model.study("std2").feature("eig").setSolveFor("/physics/mbrn", true);

    model.component("comp1").physics("mbrn").create("el1", "EdgeLoad", 1);
    model.component("comp1").physics("mbrn").feature("el1").selection().set(1, 2, 3, 4);
    model.component("comp1").physics("mbrn").feature("el1").set("coordinateSystem", "sys2");
    model.component("comp1").physics("mbrn").feature("el1")
         .set("forceReferenceLength", new String[]{"T0", "0", "0"});
    model.component("comp1").physics("mbrn").create("stb1", "MembraneStabilization", 2);

    model.study("std2").feature("stat").set("useadvanceddisable", true);
    model.study("std2").feature("stat").set("disabledphysics", new String[]{"mbrn/lemm1/iss1"});
    model.study("std2").feature("eig").set("useadvanceddisable", true);
    model.study("std2").feature("eig").set("disabledphysics", new String[]{"mbrn/lemm1/iss1"});
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset("dset2").set("frametype", "spatial");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").label("\u632f\u578b (mbrn) 1");
    model.result("pg2").set("showlegends", false);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"mbrn.disp"});
    model.result("pg2").feature("surf1").set("threshold", "manual");
    model.result("pg2").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colortabletrans", "none");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg2").feature("surf1").create("def", "Deform");
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().evaluationGroup().create("std2EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std2EvgFrq").set("data", "dset2");
    model.result().evaluationGroup("std2EvgFrq").label("\u7279\u5f81\u9891\u7387 (\u7814\u7a76 2)");
    model.result().evaluationGroup("std2EvgFrq").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("expr", "2*pi*freq", 0);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("unit", "rad/s", 0);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("descr", "\u89d2\u9891\u7387", 0);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("expr", "imag(freq)/abs(freq)", 1);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("descr", "\u963b\u5c3c\u6bd4", 1);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("expr", "abs(freq)/imag(freq)/2", 2);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("descr", "\u54c1\u8d28\u56e0\u5b50", 2);
    model.result().evaluationGroup("std2EvgFrq").run();
    model.result().evaluationGroup().create("std2mpf1", "EvaluationGroup");
    model.result().evaluationGroup("std2mpf1").set("data", "dset2");
    model.result().evaluationGroup("std2mpf1").label("\u53c2\u4e0e\u56e0\u5b50 (\u7814\u7a76 2)");
    model.result().evaluationGroup("std2mpf1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.pfLnormX", 0);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "1", 0);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cX \u5e73\u79fb", 0);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.pfLnormY", 1);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cY \u5e73\u79fb", 1);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.pfLnormZ", 2);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cZ \u5e73\u79fb", 2);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.pfRnormX", 3);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "1", 3);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cX \u65cb\u8f6c", 3);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.pfRnormY", 4);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "1", 4);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cY \u65cb\u8f6c", 4);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.pfRnormZ", 5);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "1", 5);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cZ \u65cb\u8f6c", 5);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.mEffLX", 6);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "kg", 6);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cX \u5e73\u79fb", 6);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.mEffLY", 7);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "kg", 7);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cY \u5e73\u79fb", 7);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.mEffLZ", 8);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "kg", 8);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cZ \u5e73\u79fb", 8);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.mEffRX", 9);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "kg*m^2", 9);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cX \u65cb\u8f6c", 9);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.mEffRY", 10);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "kg*m^2", 10);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cY \u65cb\u8f6c", 10);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.mEffRZ", 11);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "kg*m^2", 11);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cZ \u65cb\u8f6c", 11);
    model.result().evaluationGroup("std2mpf1").run();
    model.result("pg2").run();

    model.study("std1").feature("eig").set("useadvanceddisable", true);
    model.study("std1").feature("eig").set("disabledphysics", new String[]{"mbrn/el1"});

    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{4});

    model.title("\u632f\u819c");

    model
         .description("\u672c\u4f8b\u7814\u7a76\u8868\u73b0\u51fa\u5e94\u529b\u521a\u5316\u7684\u9884\u5f20\u529b\u819c\u7684\u56fa\u6709\u9891\u7387\u3002\u6a21\u578b\u7ed3\u679c\u4e0e\u89e3\u6790\u89e3\u8fdb\u884c\u4e86\u6bd4\u8f83\u3002\u672c\u4f8b\u63a2\u7d22\u4e86\u91c7\u7528\u4e24\u79cd\u4e0d\u540c\u7684\u6280\u672f\u6765\u4ea7\u751f\u9884\u5e94\u529b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("vibrating_membrane.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
