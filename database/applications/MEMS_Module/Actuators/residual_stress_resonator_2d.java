/*
 * residual_stress_resonator_2d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:55 by COMSOL 6.3.0.290. */
public class residual_stress_resonator_2d {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\MEMS_Module\\Actuators");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

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
    model.param().set("E1", "155[GPa]", "\u6768\u6c0f\u6a21\u91cf");
    model.param().set("nu1", "0.23", "\u6cca\u677e\u6bd4");
    model.param().set("rho1", "2330[kg/m^3]", "\u5bc6\u5ea6");
    model.param().set("sigma", "50[MPa]", "\u6b8b\u4f59\u5e94\u529b");
    model.param().set("epsilon", "sigma*(1-nu1)/E1", "\u6b8b\u4f59\u5e94\u53d8");
    model.param().set("T1", "605[degC]", "\u6c89\u79ef\u6e29\u5ea6");
    model.param().set("T0", "25[degC]", "\u5ba4\u6e29");
    model.param().set("daT", "epsilon/(T1-T0)", "\u70ed\u81a8\u80c0\u7cfb\u6570");
    model.param().set("thickness", "2.25[um]", "\u539a\u5ea6");
    model.param().set("b", "2[um]", "\u5bbd\u5ea6");
    model.param().set("L_straight", "200[um]", "\u76f4\u60ac\u81c2\u957f\u5ea6");
    model.param().set("Length_plate", "250[um]", "\u8c10\u632f\u5668\u677f\u7684\u957f\u5ea6");
    model.param().set("Width_plate", "120[um]", "\u8c10\u632f\u5668\u677f\u7684\u5bbd\u5ea6");
    model.param().set("m", "Length_plate*Width_plate*thickness*rho1", "\u677f\u8d28\u91cf");
    model.param()
         .set("f0_straight_unstressed", "(1/(2*pi))*sqrt(((4*E1*thickness*b^3)/(m*L_straight^3)))", "\u65e0\u5e94\u529b\u76f4\u60ac\u81c2\u7684\u5171\u632f\u9891\u7387");
    model.param()
         .set("f0_straight_stressed", "(1/(2*pi))*sqrt(((4*E1*thickness*b^3)/(m*L_straight^3))+((24*sigma*thickness*b)/(5*m*L_straight)))", "\u5e94\u529b\u4f5c\u7528\u4e0b\u76f4\u60ac\u81c2\u7684\u5171\u632f\u9891\u7387");
    model.param().set("L1", "170[um]", "\u6298\u53e0\u60ac\u81c2\u7b2c\u4e00\u90e8\u5206\u7684\u957f\u5ea6");
    model.param().set("L2", "10[um]", "\u6298\u53e0\u60ac\u81c2\u7b2c\u4e8c\u90e8\u5206\u7684\u957f\u5ea6");
    model.param().set("L3", "146[um]", "\u6298\u53e0\u60ac\u81c2\u7b2c\u4e09\u90e8\u5206\u7684\u957f\u5ea6");
    model.param()
         .set("L_folded_equiv", "(L1^3+0.25*(L2*thickness^2)+L3^3)^(1/3)", "\u6298\u53e0\u60ac\u81c2\u7684\u7b49\u6548\u957f\u5ea6");
    model.param()
         .set("f0_folded_unstressed", "(1/(2*pi))*sqrt(((4*E1*thickness*b^3)/(m*L_folded_equiv^3)))", "\u65e0\u5e94\u529b\u6298\u53e0\u60ac\u81c2\u7684\u5171\u632f\u9891\u7387");

    model.component("comp1").geom("geom1").insertFile("residual_stress_resonator_2d_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").physics("solid").prop("Type2D").set("Type2D", "PlaneStress");
    model.component("comp1").physics("solid").prop("d").set("d", "thickness");
    model.component("comp1").physics("solid").feature("lemm1").create("te1", "ThermalExpansion", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("te1")
         .set("minput_temperature_src", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").feature("te1").set("minput_temperature", "T0");

    model.common("cminpt").set("modified", new String[][]{{"strainreferencetemperature", "T1"}});

    model.component("comp1").physics("solid").create("fix1", "Fixed", 1);
    model.component("comp1").physics("solid").feature("fix1").selection().set(5, 9, 15, 19);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"E1"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"nu1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"rho1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"daT"});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(6, 8, 16, 18);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 2);
    model.component("comp1").mesh("mesh1").run();

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 2);

    model.component("comp2").mesh().create("mesh2");

    model.component("comp2").geom("geom2").insertFile("residual_stress_resonator_2d_geom_sequence.mph", "geom2");
    model.component("comp2").geom("geom2").run("fin");

    model.component("comp2").physics().create("solid2", "SolidMechanics", "geom2");

    model.study("std1").feature("eig").setSolveFor("/physics/solid2", true);

    model.component("comp2").physics("solid2").prop("Type2D").set("Type2D", "PlaneStress");
    model.component("comp2").physics("solid2").prop("d").set("d", "thickness");
    model.component("comp2").physics("solid2").feature("lemm1").create("te1", "ThermalExpansion", 2);
    model.component("comp2").physics("solid2").feature("lemm1").feature("te1")
         .set("minput_temperature_src", "userdef");
    model.component("comp2").physics("solid2").feature("lemm1").feature("te1").set("minput_temperature", "T0");
    model.component("comp2").physics("solid2").create("fix1", "Fixed", 1);
    model.component("comp2").physics("solid2").feature("fix1").selection().set(28, 30, 42, 44);

    model.component("comp2").material().create("mat2", "Common");
    model.component("comp2").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp2").material("mat2").propertyGroup("Enu").set("E", new String[]{"E1"});
    model.component("comp2").material("mat2").propertyGroup("Enu").set("nu", new String[]{"nu1"});
    model.component("comp2").material("mat2").propertyGroup("def").set("density", new String[]{"rho1"});
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"daT"});

    model.component("comp2").mesh("mesh2").create("map1", "Map");
    model.component("comp2").mesh("mesh2").feature("map1").create("dis1", "Distribution");
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis1").selection()
         .set(4, 8, 10, 11, 28, 30, 38, 42, 44, 45, 60, 62);
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis1").set("numelem", 2);
    model.component("comp2").mesh("mesh2").feature("size").set("hauto", 2);
    model.component("comp2").mesh("mesh2").run();

    model.study("std1").label("\u7814\u7a76 1 - \u76f4\u60ac\u81c2\uff0c\u65e0\u5e94\u529b");
    model.study("std1").feature("eig").setSolveFor("/physics/solid2", false);
    model.study().create("std2");
    model.study("std2").create("eig", "Eigenfrequency");
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

    model.component("comp2").common().create("mpf2", "ParticipationFactors");

    model.study("std2").feature("eig").setSolveFor("/physics/solid", false);
    model.study("std2").label("\u7814\u7a76 2 - \u6298\u53e0\u60ac\u81c2\uff0c\u65e0\u5e94\u529b");
    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").set("geometricNonlinearity", true);
    model.study("std3").feature("stat").set("outputmap", new String[]{});
    model.study("std3").feature("stat").set("ngenAUX", "1");
    model.study("std3").feature("stat").set("goalngenAUX", "1");
    model.study("std3").feature("stat").set("ngenAUX", "1");
    model.study("std3").feature("stat").set("goalngenAUX", "1");
    model.study("std3").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std3").feature("stat").setSolveFor("/physics/solid2", true);
    model.study("std3").create("eig", "Eigenfrequency");
    model.study("std3").feature("eig").set("storefact", false);
    model.study("std3").feature("eig").set("geometricNonlinearity", true);
    model.study("std3").feature("eig").set("outputmap", new String[]{});
    model.study("std3").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std3").feature("eig").set("ngenAUX", "1");
    model.study("std3").feature("eig").set("goalngenAUX", "1");
    model.study("std3").feature("eig").set("ngenAUX", "1");
    model.study("std3").feature("eig").set("goalngenAUX", "1");
    model.study("std3").feature("eig").setSolveFor("/physics/solid", true);
    model.study("std3").feature("eig").setSolveFor("/physics/solid2", true);
    model.study("std3").feature("stat").setSolveFor("/physics/solid2", false);
    model.study("std3").feature("stat").set("geometricNonlinearity", false);
    model.study("std3").feature("eig").setSolveFor("/physics/solid2", false);
    model.study("std3").label("\u7814\u7a76 3 - \u76f4\u60ac\u81c2\uff0c\u6b8b\u4f59\u5e94\u529b");
    model.study().create("std4");
    model.study("std4").create("stat", "Stationary");
    model.study("std4").feature("stat").set("geometricNonlinearity", true);
    model.study("std4").feature("stat").set("outputmap", new String[]{});
    model.study("std4").feature("stat").set("ngenAUX", "1");
    model.study("std4").feature("stat").set("goalngenAUX", "1");
    model.study("std4").feature("stat").set("ngenAUX", "1");
    model.study("std4").feature("stat").set("goalngenAUX", "1");
    model.study("std4").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std4").feature("stat").setSolveFor("/physics/solid2", true);
    model.study("std4").create("eig", "Eigenfrequency");
    model.study("std4").feature("eig").set("storefact", false);
    model.study("std4").feature("eig").set("geometricNonlinearity", true);
    model.study("std4").feature("eig").set("outputmap", new String[]{});
    model.study("std4").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std4").feature("eig").set("ngenAUX", "1");
    model.study("std4").feature("eig").set("goalngenAUX", "1");
    model.study("std4").feature("eig").set("ngenAUX", "1");
    model.study("std4").feature("eig").set("goalngenAUX", "1");
    model.study("std4").feature("eig").setSolveFor("/physics/solid", true);
    model.study("std4").feature("eig").setSolveFor("/physics/solid2", true);
    model.study("std4").feature("stat").setSolveFor("/physics/solid", false);
    model.study("std4").feature("stat").set("geometricNonlinearity", false);
    model.study("std4").feature("eig").setSolveFor("/physics/solid", false);
    model.study("std4").label("\u7814\u7a76 4 - \u6298\u53e0\u60ac\u81c2\uff0c\u6b8b\u4f59\u5e94\u529b");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
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
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().evaluationGroup().create("std1EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std1EvgFrq").set("data", "dset1");
    model.result().evaluationGroup("std1EvgFrq")
         .label("\u7279\u5f81\u9891\u7387 (\u7814\u7a76 1 - \u76f4\u60ac\u81c2\uff0c\u65e0\u5e94\u529b)");
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
    model.result().evaluationGroup("std1mpf1")
         .label("\u53c2\u4e0e\u56e0\u5b50 (\u7814\u7a76 1 - \u76f4\u60ac\u81c2\uff0c\u65e0\u5e94\u529b)");
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
    model.result("pg1").label("\u76f4\u60ac\u81c2\uff0c\u65e0\u5e94\u529b");
    model.result("pg1").run();

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset4");
    model.result("pg2").setIndex("looplevel", 1, 0);
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
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"u2", "v2"});
    model.result("pg2").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().evaluationGroup().create("std2EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std2EvgFrq").set("data", "dset4");
    model.result().evaluationGroup("std2EvgFrq")
         .label("\u7279\u5f81\u9891\u7387 (\u7814\u7a76 2 - \u6298\u53e0\u60ac\u81c2\uff0c\u65e0\u5e94\u529b)");
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
    model.result().evaluationGroup().create("std2mpf2", "EvaluationGroup");
    model.result().evaluationGroup("std2mpf2").set("data", "dset4");
    model.result().evaluationGroup("std2mpf2")
         .label("\u53c2\u4e0e\u56e0\u5b50 (\u7814\u7a76 2 - \u6298\u53e0\u60ac\u81c2\uff0c\u65e0\u5e94\u529b)");
    model.result().evaluationGroup("std2mpf2").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std2mpf2").feature("gev1").setIndex("expr", "mpf2.pfLnormX", 0);
    model.result().evaluationGroup("std2mpf2").feature("gev1").setIndex("unit", "1", 0);
    model.result().evaluationGroup("std2mpf2").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cX \u5e73\u79fb", 0);
    model.result().evaluationGroup("std2mpf2").feature("gev1").setIndex("expr", "mpf2.pfLnormY", 1);
    model.result().evaluationGroup("std2mpf2").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std2mpf2").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cY \u5e73\u79fb", 1);
    model.result().evaluationGroup("std2mpf2").feature("gev1").setIndex("expr", "mpf2.pfLnormZ", 2);
    model.result().evaluationGroup("std2mpf2").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std2mpf2").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cZ \u5e73\u79fb", 2);
    model.result().evaluationGroup("std2mpf2").feature("gev1").setIndex("expr", "mpf2.pfRnormX", 3);
    model.result().evaluationGroup("std2mpf2").feature("gev1").setIndex("unit", "1", 3);
    model.result().evaluationGroup("std2mpf2").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cX \u65cb\u8f6c", 3);
    model.result().evaluationGroup("std2mpf2").feature("gev1").setIndex("expr", "mpf2.pfRnormY", 4);
    model.result().evaluationGroup("std2mpf2").feature("gev1").setIndex("unit", "1", 4);
    model.result().evaluationGroup("std2mpf2").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cY \u65cb\u8f6c", 4);
    model.result().evaluationGroup("std2mpf2").feature("gev1").setIndex("expr", "mpf2.pfRnormZ", 5);
    model.result().evaluationGroup("std2mpf2").feature("gev1").setIndex("unit", "1", 5);
    model.result().evaluationGroup("std2mpf2").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cZ \u65cb\u8f6c", 5);
    model.result().evaluationGroup("std2mpf2").feature("gev1").setIndex("expr", "mpf2.mEffLX", 6);
    model.result().evaluationGroup("std2mpf2").feature("gev1").setIndex("unit", "kg", 6);
    model.result().evaluationGroup("std2mpf2").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cX \u5e73\u79fb", 6);
    model.result().evaluationGroup("std2mpf2").feature("gev1").setIndex("expr", "mpf2.mEffLY", 7);
    model.result().evaluationGroup("std2mpf2").feature("gev1").setIndex("unit", "kg", 7);
    model.result().evaluationGroup("std2mpf2").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cY \u5e73\u79fb", 7);
    model.result().evaluationGroup("std2mpf2").feature("gev1").setIndex("expr", "mpf2.mEffLZ", 8);
    model.result().evaluationGroup("std2mpf2").feature("gev1").setIndex("unit", "kg", 8);
    model.result().evaluationGroup("std2mpf2").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cZ \u5e73\u79fb", 8);
    model.result().evaluationGroup("std2mpf2").feature("gev1").setIndex("expr", "mpf2.mEffRX", 9);
    model.result().evaluationGroup("std2mpf2").feature("gev1").setIndex("unit", "kg*m^2", 9);
    model.result().evaluationGroup("std2mpf2").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cX \u65cb\u8f6c", 9);
    model.result().evaluationGroup("std2mpf2").feature("gev1").setIndex("expr", "mpf2.mEffRY", 10);
    model.result().evaluationGroup("std2mpf2").feature("gev1").setIndex("unit", "kg*m^2", 10);
    model.result().evaluationGroup("std2mpf2").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cY \u65cb\u8f6c", 10);
    model.result().evaluationGroup("std2mpf2").feature("gev1").setIndex("expr", "mpf2.mEffRZ", 11);
    model.result().evaluationGroup("std2mpf2").feature("gev1").setIndex("unit", "kg*m^2", 11);
    model.result().evaluationGroup("std2mpf2").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cZ \u65cb\u8f6c", 11);
    model.result().evaluationGroup("std2mpf2").run();
    model.result("pg2").run();
    model.result("pg2").label("\u6298\u53e0\u60ac\u81c2\uff0c\u65e0\u5e94\u529b");
    model.result("pg2").run();

    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().dataset("dset5").set("frametype", "spatial");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset5");
    model.result("pg3").setIndex("looplevel", 1, 0);
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").label("\u632f\u578b (solid)");
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
    model.result("pg3").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg3").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().evaluationGroup().create("std3EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std3EvgFrq").set("data", "dset5");
    model.result().evaluationGroup("std3EvgFrq")
         .label("\u7279\u5f81\u9891\u7387 (\u7814\u7a76 3 - \u76f4\u60ac\u81c2\uff0c\u6b8b\u4f59\u5e94\u529b)");
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
    model.result().evaluationGroup("std3mpf1").set("data", "dset5");
    model.result().evaluationGroup("std3mpf1")
         .label("\u53c2\u4e0e\u56e0\u5b50 (\u7814\u7a76 3 - \u76f4\u60ac\u81c2\uff0c\u6b8b\u4f59\u5e94\u529b)");
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

    return model;
  }

  public static Model run2(Model model) {
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
    model.result("pg3").run();
    model.result("pg3").label("\u76f4\u60ac\u81c2\uff0c\u6b8b\u4f59\u5e94\u529b");
    model.result("pg3").run();

    model.study("std4").createAutoSequences("all");

    model.sol("sol5").runAll();

    model.result().dataset("dset10").set("frametype", "spatial");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset10");
    model.result("pg4").setIndex("looplevel", 1, 0);
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").label("\u632f\u578b (solid2)");
    model.result("pg4").set("showlegends", false);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"solid2.disp"});
    model.result("pg4").feature("surf1").set("threshold", "manual");
    model.result("pg4").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg4").feature("surf1").set("colortable", "Rainbow");
    model.result("pg4").feature("surf1").set("colortabletrans", "none");
    model.result("pg4").feature("surf1").set("colorscalemode", "linear");
    model.result("pg4").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg4").feature("surf1").create("def", "Deform");
    model.result("pg4").feature("surf1").feature("def").set("expr", new String[]{"u2", "v2"});
    model.result("pg4").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().evaluationGroup().create("std4EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std4EvgFrq").set("data", "dset10");
    model.result().evaluationGroup("std4EvgFrq")
         .label("\u7279\u5f81\u9891\u7387 (\u7814\u7a76 4 - \u6298\u53e0\u60ac\u81c2\uff0c\u6b8b\u4f59\u5e94\u529b)");
    model.result().evaluationGroup("std4EvgFrq").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std4EvgFrq").feature("gev1").setIndex("expr", "2*pi*freq", 0);
    model.result().evaluationGroup("std4EvgFrq").feature("gev1").setIndex("unit", "rad/s", 0);
    model.result().evaluationGroup("std4EvgFrq").feature("gev1").setIndex("descr", "\u89d2\u9891\u7387", 0);
    model.result().evaluationGroup("std4EvgFrq").feature("gev1").setIndex("expr", "imag(freq)/abs(freq)", 1);
    model.result().evaluationGroup("std4EvgFrq").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std4EvgFrq").feature("gev1").setIndex("descr", "\u963b\u5c3c\u6bd4", 1);
    model.result().evaluationGroup("std4EvgFrq").feature("gev1").setIndex("expr", "abs(freq)/imag(freq)/2", 2);
    model.result().evaluationGroup("std4EvgFrq").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std4EvgFrq").feature("gev1").setIndex("descr", "\u54c1\u8d28\u56e0\u5b50", 2);
    model.result().evaluationGroup("std4EvgFrq").run();
    model.result().evaluationGroup().create("std4mpf2", "EvaluationGroup");
    model.result().evaluationGroup("std4mpf2").set("data", "dset10");
    model.result().evaluationGroup("std4mpf2")
         .label("\u53c2\u4e0e\u56e0\u5b50 (\u7814\u7a76 4 - \u6298\u53e0\u60ac\u81c2\uff0c\u6b8b\u4f59\u5e94\u529b)");
    model.result().evaluationGroup("std4mpf2").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std4mpf2").feature("gev1").setIndex("expr", "mpf2.pfLnormX", 0);
    model.result().evaluationGroup("std4mpf2").feature("gev1").setIndex("unit", "1", 0);
    model.result().evaluationGroup("std4mpf2").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cX \u5e73\u79fb", 0);
    model.result().evaluationGroup("std4mpf2").feature("gev1").setIndex("expr", "mpf2.pfLnormY", 1);
    model.result().evaluationGroup("std4mpf2").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std4mpf2").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cY \u5e73\u79fb", 1);
    model.result().evaluationGroup("std4mpf2").feature("gev1").setIndex("expr", "mpf2.pfLnormZ", 2);
    model.result().evaluationGroup("std4mpf2").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std4mpf2").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cZ \u5e73\u79fb", 2);
    model.result().evaluationGroup("std4mpf2").feature("gev1").setIndex("expr", "mpf2.pfRnormX", 3);
    model.result().evaluationGroup("std4mpf2").feature("gev1").setIndex("unit", "1", 3);
    model.result().evaluationGroup("std4mpf2").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cX \u65cb\u8f6c", 3);
    model.result().evaluationGroup("std4mpf2").feature("gev1").setIndex("expr", "mpf2.pfRnormY", 4);
    model.result().evaluationGroup("std4mpf2").feature("gev1").setIndex("unit", "1", 4);
    model.result().evaluationGroup("std4mpf2").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cY \u65cb\u8f6c", 4);
    model.result().evaluationGroup("std4mpf2").feature("gev1").setIndex("expr", "mpf2.pfRnormZ", 5);
    model.result().evaluationGroup("std4mpf2").feature("gev1").setIndex("unit", "1", 5);
    model.result().evaluationGroup("std4mpf2").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cZ \u65cb\u8f6c", 5);
    model.result().evaluationGroup("std4mpf2").feature("gev1").setIndex("expr", "mpf2.mEffLX", 6);
    model.result().evaluationGroup("std4mpf2").feature("gev1").setIndex("unit", "kg", 6);
    model.result().evaluationGroup("std4mpf2").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cX \u5e73\u79fb", 6);
    model.result().evaluationGroup("std4mpf2").feature("gev1").setIndex("expr", "mpf2.mEffLY", 7);
    model.result().evaluationGroup("std4mpf2").feature("gev1").setIndex("unit", "kg", 7);
    model.result().evaluationGroup("std4mpf2").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cY \u5e73\u79fb", 7);
    model.result().evaluationGroup("std4mpf2").feature("gev1").setIndex("expr", "mpf2.mEffLZ", 8);
    model.result().evaluationGroup("std4mpf2").feature("gev1").setIndex("unit", "kg", 8);
    model.result().evaluationGroup("std4mpf2").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cZ \u5e73\u79fb", 8);
    model.result().evaluationGroup("std4mpf2").feature("gev1").setIndex("expr", "mpf2.mEffRX", 9);
    model.result().evaluationGroup("std4mpf2").feature("gev1").setIndex("unit", "kg*m^2", 9);
    model.result().evaluationGroup("std4mpf2").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cX \u65cb\u8f6c", 9);
    model.result().evaluationGroup("std4mpf2").feature("gev1").setIndex("expr", "mpf2.mEffRY", 10);
    model.result().evaluationGroup("std4mpf2").feature("gev1").setIndex("unit", "kg*m^2", 10);
    model.result().evaluationGroup("std4mpf2").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cY \u65cb\u8f6c", 10);
    model.result().evaluationGroup("std4mpf2").feature("gev1").setIndex("expr", "mpf2.mEffRZ", 11);
    model.result().evaluationGroup("std4mpf2").feature("gev1").setIndex("unit", "kg*m^2", 11);
    model.result().evaluationGroup("std4mpf2").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cZ \u65cb\u8f6c", 11);
    model.result().evaluationGroup("std4mpf2").run();
    model.result("pg4").run();
    model.result("pg4").label("\u6298\u53e0\u60ac\u81c2\uff0c\u6b8b\u4f59\u5e94\u529b");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").run();
    model.result("pg5").label("\u76f4\u60ac\u81c2\u4e2d\u7684\u6b8b\u4f59\u5e94\u529b");
    model.result("pg5").set("data", "dset7");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "solid.mises");
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").run();
    model.result("pg6").label("\u6298\u53e0\u60ac\u81c2\u4e2d\u7684\u6b8b\u4f59\u5e94\u529b");
    model.result("pg6").set("data", "dset12");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", "solid2.mises");
    model.result("pg6").run();

    model.title("\u8584\u819c\u8c10\u632f\u5668\u4e2d\u7684\u6b8b\u4f59\u5e94\u529b - \u4e8c\u7ef4");

    model
         .description("\u8868\u9762\u5fae\u52a0\u5de5\u8584\u819c\u901a\u5e38\u53d7\u5230\u6b8b\u4f59\u5e94\u529b\u7684\u5f71\u54cd\u3002\u672c\u4f8b\u5c06\u70ed\u6b8b\u4f59\u5e94\u529b\u6dfb\u52a0\u5230\u7ed3\u6784\u529b\u5b66\u6a21\u578b\uff0c\u4ece\u800c\u6539\u53d8\u7ed3\u6784\u7684\u5171\u632f\u9891\u7387\u3002\u5176\u4e2d\u91c7\u7528\u4e8c\u7ef4\u6a21\u578b\uff0c\u63cf\u8ff0\u4e00\u79cd\u5177\u6709\u6298\u53e0\u60ac\u81c2\u6881\u5f26\u7684\u8584\u819c\u8c10\u632f\u5668\u3002\u6298\u53e0\u6320\u66f2\u53ef\u4ee5\u51cf\u5c0f\u8f74\u5411\u5e94\u529b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();

    model.label("residual_stress_resonator_2d.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
