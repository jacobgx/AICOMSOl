/*
 * viscoelastic_damper_eigenmodes.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:05 by COMSOL 6.3.0.290. */
public class viscoelastic_damper_eigenmodes {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Dynamics_and_Vibration");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

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
    model.study("std1").feature("eig").setSolveFor("/physics/solid", true);

    model.component("comp1").common().create("mpf1", "ParticipationFactors");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("fr1", "200[Hz]", "\u53c2\u8003\u9891\u7387 1");
    model.param().set("fr2", "1000[Hz]", "\u53c2\u8003\u9891\u7387 2");
    model.param().set("gsr1", "3.0848E7[Pa]", "\u53c2\u8003\u5b58\u50a8\u6a21\u91cf 1");
    model.param().set("gsr2", "7.8348E7[Pa]", "\u53c2\u8003\u5b58\u50a8\u6a21\u91cf 2");
    model.param().set("glr1", "3.6551E7[Pa]", "\u53c2\u8003\u635f\u8017\u6a21\u91cf 1");
    model.param().set("glr2", "8.4935E7[Pa]", "\u53c2\u8003\u635f\u8017\u6a21\u91cf 2");
    model.param().set("ns", "log(gsr2/gsr1)/log(fr2/fr1)", "\u5b58\u50a8\u6a21\u91cf\u7684\u6307\u6570");
    model.param().set("nl", "log(glr2/glr1)/log(fr2/fr1)", "\u635f\u8017\u6a21\u91cf\u7684\u6307\u6570");

    model.func().create("an1", "Analytic");
    model.func("an1").set("funcname", "gstor");
    model.func("an1").set("expr", "gsr1*(f/fr1)^ns");
    model.func("an1").set("args", "f");
    model.func("an1").set("fununit", "Pa");
    model.func("an1").setIndex("argunit", "Hz", 0);
    model.func("an1").set("complex", true);
    model.func("an1").setIndex("plotargs", "fr1", 0, 1);
    model.func("an1").setIndex("plotargs", "fr2", 0, 2);
    model.func().duplicate("an2", "an1");
    model.func("an2").set("funcname", "gloss");
    model.func("an2").set("expr", "glr1*(f/fr1)^nl");

    model.component("comp1").geom("geom1").insertFile("viscoelastic_damper_geom_sequence.mph", "geom1");

    model.component("comp1").view("view1").set("showgrid", false);

    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("solid").create("lemm2", "LinearElasticModel", 3);
    model.component("comp1").physics("solid").feature("lemm2").set("IsotropicOption", "KG");
    model.component("comp1").physics("solid").feature("lemm2").set("MixedFormulation", "pFormulation");
    model.component("comp1").physics("solid").feature("lemm2").selection().set(2, 5);
    model.component("comp1").physics("solid").feature("lemm2").create("vis1", "Viscoelasticity", 3);
    model.component("comp1").physics("solid").feature("lemm2").feature("vis1").set("MaterialModel", "userDef");
    model.component("comp1").physics("solid").feature("lemm2").feature("vis1").set("Gstor", "gstor(solid.freq)");
    model.component("comp1").physics("solid").feature("lemm2").feature("vis1").set("Gloss", "gloss(solid.freq)");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").label("Steel AISI 4340");
    model.component("comp1").material("mat1").set("family", "steel");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "205[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.28");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u9ecf\u5f39\u6027");
    model.component("comp1").material("mat2").selection().set(2, 5);
    model.component("comp1").material("mat2").propertyGroup().create("KG", "KG", "Bulk_modulus_and_shear_modulus");
    model.component("comp1").material("mat2").propertyGroup("KG").set("K", new String[]{"4e8"});
    model.component("comp1").material("mat2").propertyGroup("KG").set("G", new String[]{"5.86e4"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"1060"});

    model.component("comp1").view("view1").set("showmaterial", true);

    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().named("geom1_sel3");
    model.component("comp1").physics("solid").create("disp1", "Displacement2", 2);
    model.component("comp1").physics("solid").feature("disp1").selection().named("geom1_sel2");
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("solid").create("disp2", "Displacement2", 2);
    model.component("comp1").physics("solid").feature("disp2").selection().named("geom1_sel1");
    model.component("comp1").physics("solid").feature("disp2").setIndex("Direction", "prescribed", 1);

    model.component("comp1").mesh("mesh1").create("fq1", "FreeQuad");
    model.component("comp1").mesh("mesh1").feature("fq1").selection().set(30);
    model.component("comp1").mesh("mesh1").feature("fq1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("hauto", 3);
    model.component("comp1").mesh("mesh1").feature("fq1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis1").selection().set(65);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().set(7);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh1").create("fq2", "FreeQuad");
    model.component("comp1").mesh("mesh1").feature("fq2").selection().set(2, 61);
    model.component("comp1").mesh("mesh1").feature("fq2").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("fq2").feature("size1").set("hauto", 4);
    model.component("comp1").mesh("mesh1").create("swe2", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe2").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe2").selection().set(1, 2, 4);
    model.component("comp1").mesh("mesh1").feature("swe2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh1").create("cpd1", "CopyDomain");
    model.component("comp1").mesh("mesh1").feature("cpd1").selection("source").geom(3);
    model.component("comp1").mesh("mesh1").feature("cpd1").selection("destination").geom(3);
    model.component("comp1").mesh("mesh1").feature("cpd1").selection("source").set(1, 2, 7);
    model.component("comp1").mesh("mesh1").feature("cpd1").selection("destination").set(5, 6, 8);
    model.component("comp1").mesh("mesh1").create("fq3", "FreeQuad");
    model.component("comp1").mesh("mesh1").feature("fq3").selection().set(10);
    model.component("comp1").mesh("mesh1").create("swe3", "Sweep");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").label("\u632f\u578b (solid)");
    model.result("pg1").set("showlegends", false);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"solid.disp"});
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
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").setIndex("expr", "imag(freq)", 0);
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").feature("glob1").set("xdataexpr", "real(freq)");
    model.result("pg2").feature("glob1").set("linestyle", "none");
    model.result("pg2").feature("glob1").set("linewidth", 3);
    model.result("pg2").feature("glob1").set("linemarker", "cycle");
    model.result("pg2").run();

    model.study().create("std2");
    model.study("std2").create("eig", "Eigenfrequency");
    model.study("std2").feature("eig").set("plotgroup", "Default");
    model.study("std2").feature("eig").set("storefact", false);
    model.study("std2").feature("eig").set("solnum", "auto");
    model.study("std2").feature("eig").set("notsolnum", "auto");
    model.study("std2").feature("eig").set("outputmap", new String[]{});
    model.study("std2").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std2").feature("eig").set("ngenAUX", "1");
    model.study("std2").feature("eig").set("goalngenAUX", "1");
    model.study("std2").feature("eig").set("ngenAUX", "1");
    model.study("std2").feature("eig").set("goalngenAUX", "1");
    model.study("std2").feature("eig").setSolveFor("/physics/solid", true);
    model.study("std2").feature("eig").set("eigsolver", "arpacknonlinear");
    model.study("std2").feature("eig").set("eigmethod", "region");
    model.study("std2").feature("eig").set("eigsr", 200);
    model.study("std2").feature("eig").set("eiglr", 1000);
    model.study("std2").feature("eig").set("eigli", 1000);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 1, 0);
    model.result("pg3").label("\u632f\u578b (solid) 1");
    model.result("pg3").set("showlegends", false);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"solid.disp"});
    model.result("pg3").feature("surf1").set("threshold", "manual");
    model.result("pg3").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg3").feature("surf1").set("colortable", "Rainbow");
    model.result("pg3").feature("surf1").set("colortabletrans", "none");
    model.result("pg3").feature("surf1").set("colorscalemode", "linear");
    model.result("pg3").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg3").feature("surf1").create("def", "Deform");
    model.result("pg3").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg3").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
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
    model.result("pg3").run();
    model.result("pg2").run();
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("legendpos", "upperleft");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").run();
    model.result("pg2").feature("glob1").set("legendmethod", "manual");
    model.result("pg2").feature("glob1").setIndex("legends", "Linear solver", 0);
    model.result("pg2").feature().duplicate("glob2", "glob1");
    model.result("pg2").run();
    model.result("pg2").feature("glob2").set("data", "dset2");
    model.result("pg2").feature("glob2").setIndex("legends", "Nonlinear solver", 0);
    model.result("pg2").run();

    model.component("comp1").physics("solid").feature("lemm2").feature().duplicate("vis2", "vis1");
    model.component("comp1").physics("solid").feature("lemm2").feature("vis2").set("LowFrequencyLimit", true);
    model.component("comp1").physics("solid").feature("lemm2").feature("vis2")
         .set("FrequencyRange", "MinimumAndMaximum");
    model.component("comp1").physics("solid").feature("lemm2").feature("vis2").set("fmin", "fr1-10[Hz]");
    model.component("comp1").physics("solid").feature("lemm2").feature("vis2").set("fmax", "fr2+10[Hz]");
    model.component("comp1").physics("solid").feature("lemm2").feature("vis2").runCommand("computeApproximation");

    model.result().table().create("tbl1", "Table");
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl1")
         .addRow(new double[]{189.99999999999997, 2.9945107718183666E7, 3.5581868834054105E7});
    model.result().table("tbl2")
         .addRow(new double[]{189.99999999999997, 2.9945333833063327E7, 3.5581856575726375E7});
    model.result().table("tbl1").addRow(new double[]{206.5532294761069, 3.142940181010154E7, 3.717361944981374E7});
    model.result().table("tbl2").addRow(new double[]{206.5532294761069, 3.1443447502838798E7, 3.717806867843565E7});
    model.result().table("tbl1").addRow(new double[]{224.54861372110145, 3.2987268152018853E7, 3.883657683761189E7});
    model.result().table("tbl2")
         .addRow(new double[]{224.54861372110145, 3.3007665657967284E7, 3.8842647081173524E7});
    model.result().table("tbl1").addRow(new double[]{244.11179651829667, 3.462235351178265E7, 4.057392642381748E7});
    model.result().table("tbl2").addRow(new double[]{244.11179651829667, 3.46409332197206E7, 4.0581533998582155E7});
    model.result().table("tbl1").addRow(new double[]{265.37936802141286, 3.633848541718319E7, 4.2388996134464234E7});
    model.result().table("tbl2").addRow(new double[]{265.37936802141286, 3.634780800247771E7, 4.2400688075754635E7});
    model.result().table("tbl1").addRow(new double[]{288.49981842711117, 3.8139681115711816E7, 4.428526276995614E7});
    model.result().table("tbl2").addRow(new double[]{288.49981842711117, 3.8134311410345554E7, 4.430551399310802E7});
    model.result().table("tbl1").addRow(new double[]{313.63457473363303, 4.003015697842869E7, 4.6266358664944395E7});
    model.result().table("tbl2").addRow(new double[]{313.63457473363303, 4.000755527654663E7, 4.630039870618401E7});
    model.result().table("tbl1").addRow(new double[]{340.9591278241964, 4.201433836995351E7, 4.833607864613321E7});
    model.result().table("tbl2").addRow(new double[]{340.9591278241964, 4.197519814828706E7, 4.838844140767962E7});
    model.result().table("tbl1").addRow(new double[]{370.6642577444448, 4.409687000768386E7, 5.049838730134227E7});
    model.result().table("tbl2").addRow(new double[]{370.6642577444448, 4.404481627998536E7, 5.057144097893149E7});
    model.result().table("tbl1").addRow(new double[]{402.9573657288376, 4.6282626834490344E7, 5.275742657374975E7});
    model.result().table("tbl2").addRow(new double[]{402.9573657288376, 4.622329733463086E7, 5.2850170649512425E7});
    model.result().table("tbl1").addRow(new double[]{438.06392227619017, 4.857672543034072E7, 5.511752369586292E7});
    model.result().table("tbl2").addRow(new double[]{438.06392227619017, 4.851637509192404E7, 5.522492769982158E7});
    model.result().table("tbl1").addRow(new double[]{476.2290413848283, 5.0984535989565685E7, 5.7583199478414E7});
    model.result().table("tbl2").addRow(new double[]{476.2290413848283, 5.09284183069208E7, 5.76963001625154E7});
    model.result().table("tbl1").addRow(new double[]{517.7191919386677, 5.351169489180378E7, 6.015917697005864E7});
    model.result().table("tbl2").addRow(new double[]{517.7191919386677, 5.3462563944156E7, 6.026604815967981E7});
    model.result().table("tbl1").addRow(new double[]{562.8240581931173, 5.6164117896052495E7, 6.2850390504465155E7});
    model.result().table("tbl2").addRow(new double[]{562.8240581931173, 5.6121244162940435E7, 6.293796221906867E7});
    model.result().table("tbl1").addRow(new double[]{611.8585623507195, 5.8948013988711E7, 6.5661995152124055E7});
    model.result().table("tbl2").addRow(new double[]{611.8585623507195, 5.890710131152898E7, 6.571854288108447E7});
    model.result().table("tbl1").addRow(new double[]{665.1650633481528, 6.186989991803111E7, 6.859937659498322E7});
    model.result().table("tbl2").addRow(new double[]{665.1650633481528, 6.182422404250219E7, 6.861735162580305E7});
    model.result().table("tbl1").addRow(new double[]{723.1157472065274, 6.493661544899979E7, 7.166816144282368E7});
    model.result().table("tbl2").addRow(new double[]{723.1157472065274, 6.487958210968904E7, 7.164691437996785E7});
    model.result().table("tbl1").addRow(new double[]{786.1152256344019, 6.815533937436289E7, 7.487422801113713E7});
    model.result().table("tbl2").addRow(new double[]{786.1152256344019, 6.808449920317459E7, 7.482211080181089E7});
    model.result().table("tbl1").addRow(new double[]{854.6033610269694, 7.153360631927004E7, 7.822371758115068E7});
    model.result().table("tbl2").addRow(new double[]{854.6033610269694, 7.14559900873766E7, 7.815904445247233E7});
    model.result().table("tbl1").addRow(new double[]{929.0583375855572, 7.50793243788781E7, 8.17230461635673E7});
    model.result().table("tbl2").addRow(new double[]{929.0583375855572, 7.50178010528945E7, 8.1673447497385E7});
    model.result().table("tbl1").addRow(new double[]{1010, 7.880079363020036E7, 8.537891678855577E7});
    model.result().table("tbl2").addRow(new double[]{1010, 7.880102463117279E7, 8.537871746118689E7});

    return model;
  }

  public static Model run2(Model model) {
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").label("\u8fd1\u4f3c\u56fe");
    model.result("pg4").set("windowtitle", "\u56fe\u5f62");
    model.result("pg4").set("window", "window2");
    model.result("pg4").create("tblp1", "Table");
    model.result("pg4").feature("tblp1").set("linecolor", "cyclereset");
    model.result("pg4").feature("tblp1").set("linestyle", "none");
    model.result("pg4").feature("tblp1").set("linemarker", "point");
    model.result("pg4").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg4").feature("tblp1").set("table", "tbl1");
    model.result("pg4").feature("tblp1").label("\u7528\u6237\u5b9a\u4e49");
    model.result("pg4").create("tblp2", "Table");
    model.result("pg4").feature("tblp2").set("linecolor", "cyclereset");
    model.result("pg4").feature("tblp2").set("legend", true);
    model.result("pg4").feature("tblp2").set("table", "tbl2");
    model.result("pg4").feature("tblp2").label("\u8fd1\u4f3c");
    model.result("pg4").set("xlabel", "\u9891\u7387 (Hz)");
    model.result("pg4").set("legendpos", "middleright");
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("title", "\\[ G_{\\textrm{q}} \\]");
    model.result("pg4").run();
    model.result("pg4").set("window", "window2");
    model.result("pg4").set("windowtitle", "\u8fd1\u4f3c\u56fe");
    model.result("pg4").run();

    model.study().create("std3");
    model.study("std3").create("eig", "Eigenfrequency");
    model.study("std3").feature("eig").set("plotgroup", "Default");
    model.study("std3").feature("eig").set("storefact", false);
    model.study("std3").feature("eig").set("solnum", "auto");
    model.study("std3").feature("eig").set("notsolnum", "auto");
    model.study("std3").feature("eig").set("outputmap", new String[]{});
    model.study("std3").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std3").feature("eig").set("ngenAUX", "1");
    model.study("std3").feature("eig").set("goalngenAUX", "1");
    model.study("std3").feature("eig").set("ngenAUX", "1");
    model.study("std3").feature("eig").set("goalngenAUX", "1");
    model.study("std3").feature("eig").setSolveFor("/physics/solid", true);
    model.study("std3").feature("eig").set("eigwhich", "lr");
    model.study("std3").feature("eig").set("shift", "200[Hz]");
    model.study("std3").feature("eig").set("useadvanceddisable", true);
    model.study("std3").feature("eig").set("disabledphysics", new String[]{"solid/lemm2/vis1"});
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").setIndex("looplevel", 1, 0);
    model.result("pg5").label("\u632f\u578b (solid) 2");
    model.result("pg5").set("showlegends", false);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"solid.disp"});
    model.result("pg5").feature("surf1").set("threshold", "manual");
    model.result("pg5").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg5").feature("surf1").set("colortable", "Rainbow");
    model.result("pg5").feature("surf1").set("colortabletrans", "none");
    model.result("pg5").feature("surf1").set("colorscalemode", "linear");
    model.result("pg5").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg5").feature("surf1").create("def", "Deform");
    model.result("pg5").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg5").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().evaluationGroup().create("std3EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std3EvgFrq").set("data", "dset3");
    model.result().evaluationGroup("std3EvgFrq").label("\u7279\u5f81\u9891\u7387 (\u7814\u7a76 3)");
    model.result().evaluationGroup("std3EvgFrq").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std3EvgFrq").feature("gev1").setIndex("expr", "2*pi*freq", 0);
    model.result().evaluationGroup("std3EvgFrq").feature("gev1").setIndex("unit", "rad/s", 0);
    model.result().evaluationGroup("std3EvgFrq").feature("gev1").setIndex("descr", "\u89d2\u9891\u7387", 0);
    model.result().evaluationGroup("std3EvgFrq").feature("gev1").setIndex("expr", "imag(freq)/abs(freq)", 1);
    model.result().evaluationGroup("std3EvgFrq").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std3EvgFrq").feature("gev1").setIndex("descr", "\u963b\u5c3c\u6bd4", 1);
    model.result().evaluationGroup("std3EvgFrq").feature("gev1").setIndex("expr", "abs(freq)/imag(freq)/2", 2);
    model.result().evaluationGroup("std3EvgFrq").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std3EvgFrq").feature("gev1").setIndex("descr", "\u54c1\u8d28\u56e0\u5b50", 2);
    model.result().evaluationGroup("std3EvgFrq").run();
    model.result().evaluationGroup().create("std3mpf1", "EvaluationGroup");
    model.result().evaluationGroup("std3mpf1").set("data", "dset3");
    model.result().evaluationGroup("std3mpf1").label("\u53c2\u4e0e\u56e0\u5b50 (\u7814\u7a76 3)");
    model.result().evaluationGroup("std3mpf1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std3mpf1").feature("gev1").setIndex("expr", "mpf1.pfLnormX", 0);
    model.result().evaluationGroup("std3mpf1").feature("gev1").setIndex("unit", "1", 0);
    model.result().evaluationGroup("std3mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cX \u5e73\u79fb", 0);
    model.result().evaluationGroup("std3mpf1").feature("gev1").setIndex("expr", "mpf1.pfLnormY", 1);
    model.result().evaluationGroup("std3mpf1").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std3mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cY \u5e73\u79fb", 1);
    model.result().evaluationGroup("std3mpf1").feature("gev1").setIndex("expr", "mpf1.pfLnormZ", 2);
    model.result().evaluationGroup("std3mpf1").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std3mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cZ \u5e73\u79fb", 2);
    model.result().evaluationGroup("std3mpf1").feature("gev1").setIndex("expr", "mpf1.pfRnormX", 3);
    model.result().evaluationGroup("std3mpf1").feature("gev1").setIndex("unit", "1", 3);
    model.result().evaluationGroup("std3mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cX \u65cb\u8f6c", 3);
    model.result().evaluationGroup("std3mpf1").feature("gev1").setIndex("expr", "mpf1.pfRnormY", 4);
    model.result().evaluationGroup("std3mpf1").feature("gev1").setIndex("unit", "1", 4);
    model.result().evaluationGroup("std3mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cY \u65cb\u8f6c", 4);
    model.result().evaluationGroup("std3mpf1").feature("gev1").setIndex("expr", "mpf1.pfRnormZ", 5);
    model.result().evaluationGroup("std3mpf1").feature("gev1").setIndex("unit", "1", 5);
    model.result().evaluationGroup("std3mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cZ \u65cb\u8f6c", 5);
    model.result().evaluationGroup("std3mpf1").feature("gev1").setIndex("expr", "mpf1.mEffLX", 6);
    model.result().evaluationGroup("std3mpf1").feature("gev1").setIndex("unit", "kg", 6);
    model.result().evaluationGroup("std3mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cX \u5e73\u79fb", 6);
    model.result().evaluationGroup("std3mpf1").feature("gev1").setIndex("expr", "mpf1.mEffLY", 7);
    model.result().evaluationGroup("std3mpf1").feature("gev1").setIndex("unit", "kg", 7);
    model.result().evaluationGroup("std3mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cY \u5e73\u79fb", 7);
    model.result().evaluationGroup("std3mpf1").feature("gev1").setIndex("expr", "mpf1.mEffLZ", 8);
    model.result().evaluationGroup("std3mpf1").feature("gev1").setIndex("unit", "kg", 8);
    model.result().evaluationGroup("std3mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cZ \u5e73\u79fb", 8);
    model.result().evaluationGroup("std3mpf1").feature("gev1").setIndex("expr", "mpf1.mEffRX", 9);
    model.result().evaluationGroup("std3mpf1").feature("gev1").setIndex("unit", "kg*m^2", 9);
    model.result().evaluationGroup("std3mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cX \u65cb\u8f6c", 9);
    model.result().evaluationGroup("std3mpf1").feature("gev1").setIndex("expr", "mpf1.mEffRY", 10);
    model.result().evaluationGroup("std3mpf1").feature("gev1").setIndex("unit", "kg*m^2", 10);
    model.result().evaluationGroup("std3mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cY \u65cb\u8f6c", 10);
    model.result().evaluationGroup("std3mpf1").feature("gev1").setIndex("expr", "mpf1.mEffRZ", 11);
    model.result().evaluationGroup("std3mpf1").feature("gev1").setIndex("unit", "kg*m^2", 11);
    model.result().evaluationGroup("std3mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cZ \u65cb\u8f6c", 11);
    model.result().evaluationGroup("std3mpf1").run();
    model.result("pg5").run();
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("glob3", "glob2");
    model.result("pg2").run();
    model.result("pg2").feature("glob3").set("data", "dset3");
    model.result("pg2").feature("glob3").setIndex("legends", "Approximation", 0);
    model.result("pg2").run();

    model.study("std1").feature("eig").set("useadvanceddisable", true);
    model.study("std1").feature("eig").set("disabledphysics", new String[]{"solid/lemm2/vis2"});
    model.study("std2").feature("eig").set("useadvanceddisable", true);
    model.study("std2").feature("eig").set("disabledphysics", new String[]{"solid/lemm2/vis2"});

    model.result("pg5").run();

    model.title("\u9ecf\u5f39\u6027\u7ed3\u6784\u963b\u5c3c\u5668\u7684\u7279\u5f81\u6a21\u6001");

    model
         .description("\u672c\u4f8b\u8ba1\u7b97\u7ed3\u6784\u963b\u5c3c\u5668\u7684\u7279\u5f81\u6a21\u6001\u3002\n\n\u963b\u5c3c\u5668\u4e2d\u7684\u5927\u90e8\u5206\u53d8\u5f62\u7531\u9ecf\u5f39\u6027\u57df\u63a7\u5236\uff0c\u8fd9\u4e9b\u57df\u7684\u521a\u5ea6\u548c\u963b\u5c3c\u5c5e\u6027\u4e0e\u9891\u7387\u7d27\u5bc6\u76f8\u5173\u3002\u8fd9\u4f1a\u5bfc\u81f4\u4e00\u4e2a\u975e\u7ebf\u6027\u7279\u5f81\u9891\u7387\u95ee\u9898\uff0c\u53ef\u4ee5\u901a\u8fc7\u4ee5\u4e0b\u65b9\u5f0f\u8fdb\u884c\u6c42\u89e3\uff1a\u4e00\u662f\u4f7f\u7528\u975e\u7ebf\u6027\u7279\u5f81\u503c\u6c42\u89e3\u5668\uff0c\u4e8c\u662f\u4f7f\u7528\u90e8\u5206\u5206\u5f0f\u62df\u5408\u7b97\u6cd5\u6765\u8fd1\u4f3c\u5904\u7406\u6750\u6599\u6570\u636e\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("viscoelastic_damper_eigenmodes.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
