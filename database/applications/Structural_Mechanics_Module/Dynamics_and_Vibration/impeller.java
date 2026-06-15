/*
 * impeller.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:05 by COMSOL 6.3.0.290. */
public class impeller {

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

    model.component("comp1").view("view1").set("showgrid", false);

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "impeller.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").run("fin");

    model.param().set("N", "8");
    model.param().descr("N", "\u6247\u533a\u6570");
    model.param().set("theta", "2*pi/N");
    model.param().descr("theta", "\u5355\u4f4d\u6247\u533a\u89d2\u5ea6");
    model.param().set("mn", "3");
    model.param().descr("mn", "\u65b9\u4f4d\u89d2\u6a21\u6570");
    model.param().set("p0", "1e4[Pa]");
    model.param().descr("p0", "\u8f7d\u8377\u5927\u5c0f");

    model.component("comp1").physics().create("solid2", "SolidMechanics", "geom1");

    model.study("std1").feature("eig").setSolveFor("/physics/solid2", true);

    model.component("comp1").physics("solid2").selection().set(8);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat1").label("Aluminum");
    model.component("comp1").material("mat1").set("family", "aluminum");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23e-6[1/K]", "0", "0", "0", "23e-6[1/K]", "0", "0", "0", "23e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "70[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("l", "-250[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("m", "-330[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("n", "-350[GPa]");

    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().set(54, 55, 67, 68, 85, 87, 109, 113);
    model.component("comp1").physics("solid2").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid2").feature("fix1").selection().set(113);
    model.component("comp1").physics("solid2").create("pc1", "PeriodicCondition", 2);
    model.component("comp1").physics("solid2").feature("pc1").selection().set(112, 134);
    model.component("comp1").physics("solid2").feature("pc1").set("PeriodicType", "CyclicSymmetry");
    model.component("comp1").physics("solid2").feature("pc1").set("mFloquet", "mn");
    model.component("comp1").physics("solid2").feature("pc1").set("manualDestinationSelection", true);
    model.component("comp1").physics("solid2").feature("pc1").selection("destinationDomains").set(112);

    model.component("comp1").mesh("mesh1").autoMeshSize(4);
    model.component("comp1").mesh("mesh1").create("id1", "IdenticalMesh");
    model.component("comp1").mesh("mesh1").feature("id1").selection("group1").set(134);
    model.component("comp1").mesh("mesh1").feature("id1").selection("group2").set(112);
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().set(8);
    model.component("comp1").mesh("mesh1").create("cpd1", "CopyDomain");
    model.component("comp1").mesh("mesh1").feature("cpd1").selection("source").geom(3);
    model.component("comp1").mesh("mesh1").feature("cpd1").selection("destination").geom(3);
    model.component("comp1").mesh("mesh1").feature("cpd1").selection("source").set(8);
    model.component("comp1").mesh("mesh1").feature("cpd1").selection("destination").set(1, 2, 3, 4, 5, 6, 7);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("eig").set("neigsactive", true);
    model.study("std1").feature("eig").set("neigs", 32);
    model.study("std1").feature("eig").set("useadvanceddisable", true);
    model.study("std1").feature("eig").setSolveFor("/physics/solid2", false);
    model.study("std1").feature("eig").set("disabledphysics", new String[]{"solid2"});
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
    model.study("std2").feature("eig").setSolveFor("/physics/solid2", true);
    model.study("std2").feature("eig").set("neigsactive", true);
    model.study("std2").feature("eig").set("neigs", 4);
    model.study("std2").feature("eig").set("useadvanceddisable", true);
    model.study("std2").feature("eig").setSolveFor("/physics/solid", false);
    model.study("std2").feature("eig").set("disabledphysics", new String[]{"solid"});
    model.study("std2").feature("eig").set("useparam", true);
    model.study("std2").feature("eig").setIndex("pname", "N", 0);
    model.study("std2").feature("eig").setIndex("plistarr", "", 0);
    model.study("std2").feature("eig").setIndex("punit", "", 0);
    model.study("std2").feature("eig").setIndex("pname", "N", 0);
    model.study("std2").feature("eig").setIndex("plistarr", "", 0);
    model.study("std2").feature("eig").setIndex("punit", "", 0);
    model.study("std2").feature("eig").setIndex("pname", "mn", 0);
    model.study("std2").feature("eig").setIndex("plistarr", "range(0,1,N/2)", 0);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 4, 0);
    model.result("pg2").setIndex("looplevel", 5, 1);
    model.result("pg2").label("\u632f\u578b (solid2)");
    model.result("pg2").set("showlegends", false);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"solid2.disp"});
    model.result("pg2").feature("surf1").set("threshold", "manual");
    model.result("pg2").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colortabletrans", "none");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg2").feature("surf1").create("def", "Deform");
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"u2", "v2", "w2"});
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
    model.result().dataset().create("sec1", "Sector3D");
    model.result().dataset("sec1").set("data", "dset2");
    model.result().dataset("sec1").set("sectors", 8);
    model.result().dataset("sec1").set("modenumber", "solid2.pc1.mFloquet");
    model.result("pg2").run();
    model.result("pg2").set("data", "sec1");
    model.result("pg2").run();
    model.result().evaluationGroup("std1EvgFrq").run();
    model.result().evaluationGroup("std2EvgFrq").run();

    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl1").selection().all();
    model.component("comp1").physics("solid").feature("bndl1").selection()
         .set(1, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 20, 21, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 69, 70, 71, 72, 73, 74, 75, 77, 78, 79, 80, 81, 82, 83, 84, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 110, 111, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152);
    model.component("comp1").physics("solid").feature("bndl1").set("coordinateSystem", "sys1");
    model.component("comp1").physics("solid").feature("bndl1")
         .set("forceReferenceArea", new String[]{"0", "0", "-p0*exp(-j*mn*atan2(Y,X))"});
    model.component("comp1").physics("solid2").create("bndl1", "BoundaryLoad", 2);
    model.component("comp1").physics("solid2").feature("bndl1").selection()
         .set(114, 115, 119, 123, 125, 130, 131, 133, 136, 137, 142, 143, 144, 145, 146, 150, 151);
    model.component("comp1").physics("solid2").feature("bndl1").set("coordinateSystem", "sys1");
    model.component("comp1").physics("solid2").feature("bndl1")
         .set("forceReferenceArea", new String[]{"0", "0", "-p0*exp(-j*mn*atan2(Y,X))"});

    model.study().create("std3");
    model.study("std3").create("freq", "Frequency");
    model.study("std3").feature("freq").setSolveFor("/physics/solid", true);
    model.study("std3").feature("freq").setSolveFor("/physics/solid2", false);
    model.study("std3").feature("freq").set("plist", 200);
    model.study("std3").feature("freq").setEntry("outputmap", "solid2", "none");
    model.study("std3").setGenPlots(false);
    model.study("std3").showAutoSequences("all");
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("\u4f4d\u79fb (solid)");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("resolution", "finer");
    model.result("pg3").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg3").feature("surf1").create("def1", "Deform");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg3").feature("surf1").feature("def1").set("scale", 25);
    model.result("pg3").run();

    model.study().create("std4");
    model.study("std4").create("freq", "Frequency");
    model.study("std4").feature("freq").setSolveFor("/physics/solid", false);
    model.study("std4").feature("freq").setSolveFor("/physics/solid2", true);
    model.study("std4").feature("freq").set("plist", 200);
    model.study("std4").feature("freq").setEntry("outputmap", "solid", "none");
    model.study("std4").setGenPlots(false);
    model.study("std4").showAutoSequences("all");
    model.study("std4").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result().dataset().create("sec2", "Sector3D");
    model.result().dataset("sec2").set("data", "dset4");
    model.result().dataset("sec2").set("sectors", 8);
    model.result().dataset("sec2").set("modenumber", "solid2.pc1.mFloquet");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").label("\u4f4d\u79fb (solid2)");
    model.result("pg4").set("data", "sec2");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "solid2.disp");
    model.result("pg4").feature("surf1").set("resolution", "finer");
    model.result("pg4").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg4").feature("surf1").create("def1", "Deform");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg4").feature("surf1").feature("def1").set("scale", 25);
    model.result("pg4").feature("surf1").feature("def1").set("expr", new String[]{"u2", "v2", "w2"});
    model.result("pg4").run();

    model.view("view3").set("showgrid", false);

    model.title("\u53f6\u8f6e\u632f\u52a8");

    model
         .description("\u672c\u6559\u7a0b\u65e8\u5728\u6f14\u793a\u52a8\u6001\u5faa\u73af\u5bf9\u79f0\u7684\u4f7f\u7528\uff0c\u540c\u65f6\u5728\u5b8c\u6574\u51e0\u4f55\u7ed3\u6784\u4e0a\u4e3a\u7ed3\u679c\u751f\u6210\u53ef\u89c6\u5316\u6548\u679c\u3002\u5177\u6709\u516b\u4e2a\u76f8\u540c\u53f6\u7247\u7684\u53f6\u8f6e\u7684\u4e09\u7ef4\u6a21\u578b\u53ef\u4ee5\u5206\u4e3a\u516b\u4e2a\u5bf9\u79f0\u6247\u533a\u3002\u6a21\u578b\u8ba1\u7b97\u6574\u4e2a\u53f6\u8f6e\u51e0\u4f55\u7ed3\u6784\u7684\u57fa\u9891\uff0c\u5e76\u5c06\u7ed3\u679c\u4e0e\u5728\u4e24\u4e2a\u6247\u533a\u8fb9\u754c\u4e0a\u4f7f\u7528\u5faa\u73af\u5bf9\u79f0\u6761\u4ef6\u4e3a\u5355\u4e2a\u6247\u533a\u8ba1\u7b97\u7684\u503c\u8fdb\u884c\u6bd4\u8f83\uff0c\u8fd8\u6f14\u793a\u5982\u4f55\u4e3a\u4e00\u4e2a\u5bf9\u79f0\u6247\u533a\u5efa\u7acb\u9891\u7387\u54cd\u5e94\u5206\u6790\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("impeller.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
