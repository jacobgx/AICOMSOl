/*
 * frequency_shift_temperature_changes.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:16 by COMSOL 6.3.0.290. */
public class frequency_shift_temperature_changes {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Thermal-Structure_Interaction");

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

    model.param().label("\u51e0\u4f55\u53c2\u6570");
    model.param().set("L0", "10[mm]");
    model.param().descr("L0", "\u53c2\u8003\u957f\u5ea6 (x)");
    model.param().set("a0", "1[mm]");
    model.param().descr("a0", "\u53c2\u8003\u5bbd\u5ea6 (y)");
    model.param().set("b0", "0.5[mm]");
    model.param().descr("b0", "\u53c2\u8003\u9ad8\u5ea6 (z)");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"L0", "a0", "b0"});
    model.component("comp1").geom("geom1").run("blk1");

    model.param().create("par2");
    model.param("par2").label("\u6750\u6599\u5c5e\u6027");
    model.param("par2").set("E0", "100[GPa]");
    model.param("par2").descr("E0", "\u53c2\u8003\u6768\u6c0f\u6a21\u91cf");
    model.param("par2").set("rho0", "1000[kg/m^3]");
    model.param("par2").descr("rho0", "\u53c2\u8003\u8d28\u91cf\u5bc6\u5ea6");
    model.param("par2").set("nu0", "0");
    model.param("par2").descr("nu0", "\u6cca\u677e\u6bd4");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"E0"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"nu0"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"rho0"});

    model.component("comp1").physics("solid").feature("lemm1").create("te1", "ThermalExpansion", 3);
    model.component("comp1").physics("solid").feature("lemm1").feature("te1")
         .set("minput_strainreferencetemperature_src", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").feature("te1")
         .set("minput_temperature_src", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").feature("te1")
         .set("minput_temperature", "293.15[K]+dT");

    model.param("par2").set("alpha_x", "1E-5[1/K]");
    model.param("par2").descr("alpha_x", "CTE\uff0cx \u65b9\u5411");
    model.param("par2").set("alpha_y", "2E-5[1/K]");
    model.param("par2").descr("alpha_y", "CTE\uff0cy \u65b9\u5411");
    model.param("par2").set("alpha_z", "3E-5[1/K]");
    model.param("par2").descr("alpha_z", "CTE\uff0cz \u65b9\u5411");
    model.param("par2").set("dT", "10[K]");
    model.param("par2").descr("dT", "\u6e29\u5ea6\u53d8\u5316");

    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_x", "alpha_y", "alpha_z"});

    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").label("\u56fa\u5b9a\u7ea6\u675f\uff0c\u5de6\u7aef");
    model.component("comp1").physics("solid").feature("fix1").selection().set(1);
    model.component("comp1").physics("solid").feature().duplicate("fix2", "fix1");
    model.component("comp1").physics("solid").feature("fix2").label("\u56fa\u5b9a\u7ea6\u675f\uff0c\u53f3\u7aef");
    model.component("comp1").physics("solid").feature("fix2").selection().set(6);

    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1\uff0c\u5939\u7d27\u6881\uff0c\u57fa\u7840\u89e3");
    model.study("std1").feature("eig").set("neigsactive", true);
    model.study("std1").feature("eig").set("neigs", 10);
    model.study("std1").feature("eig").set("shift", "10000[Hz]");
    model.study("std1").feature("eig").set("useadvanceddisable", true);
    model.study("std1").feature("eig").set("disabledphysics", new String[]{"solid/lemm1/te1"});
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("e1").set("rtol", 1.0E-30);
    model.sol("sol1").feature("e1").set("eigvfunscaleparam", 1);

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
    model.result().evaluationGroup("std1EvgFrq")
         .label("\u7279\u5f81\u9891\u7387 (\u7814\u7a76 1\uff0c\u5939\u7d27\u6881\uff0c\u57fa\u7840\u89e3)");
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
         .label("\u53c2\u4e0e\u56e0\u5b50 (\u7814\u7a76 1\uff0c\u5939\u7d27\u6881\uff0c\u57fa\u7840\u89e3)");
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
    model.result("pg1").label("\u632f\u578b\uff0c\u5939\u7d27\u6881\uff0c\u57fa\u7840\u89e3");
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u5939\u7d27\u6881\uff0c\u57fa\u7840\u89e3");
    model.result("pg1").set("paramindicator", "");
    model.result("pg1").set("edges", false);
    model.result("pg1").set("plotarrayenable", true);
    model.result("pg1").set("arrayshape", "square");
    model.result("pg1").set("relrowpadding", 0.6);
    model.result("pg1").set("relcolumnpadding", 0.4);
    model.result("pg1").feature("surf1").set("arraydim", "2");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").label("\u4e00\u9636\u5f2f\u66f2\uff0cz \u65b9\u5411");
    model.result("pg1").feature().duplicate("surf2", "surf1");
    model.result("pg1").feature("surf2").set("arraydim", "2");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").label("\u4e00\u9636\u5f2f\u66f2\uff0cy \u65b9\u5411");
    model.result("pg1").feature("surf2").set("data", "dset1");
    model.result("pg1").feature("surf2").set("looplevel", new int[]{2});
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("surf3", "surf2");
    model.result("pg1").feature("surf3").set("arraydim", "2");
    model.result("pg1").run();
    model.result("pg1").feature("surf3").set("looplevel", new int[]{6});
    model.result("pg1").feature("surf3").label("\u4e00\u9636\u626d\u8f6c");
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("surf4", "surf3");
    model.result("pg1").feature("surf4").set("arraydim", "2");
    model.result("pg1").run();
    model.result("pg1").feature("surf4").label("\u4e00\u9636\u8f74\u5411");
    model.result("pg1").feature("surf4").set("looplevel", new int[]{9});
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("surf1").feature("def").set("scale", "4E-4");
    model.result("pg1").feature("surf2").set("arraydim", "2");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").set("inheritplot", "surf1");
    model.result("pg1").feature("surf3").set("arraydim", "2");
    model.result("pg1").run();
    model.result("pg1").feature("surf3").set("inheritplot", "surf1");
    model.result("pg1").feature("surf4").set("arraydim", "2");
    model.result("pg1").run();
    model.result("pg1").feature("surf4").set("inheritplot", "surf1");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("arws1", "ArrowSurface");
    model.result("pg1").feature("arws1").set("arraydim", "2");
    model.result("pg1").feature("arws1")
         .label("\u9762\u4e0a\u7bad\u5934\uff0c\u4e00\u9636\u5f2f\u66f2\uff0cz \u65b9\u5411");
    model.result("pg1").feature("arws1").set("arrowcount", 100);
    model.result("pg1").feature("arws1").set("scaleactive", true);
    model.result("pg1").feature("arws1").set("scale", "6E-4");
    model.result("pg1").feature("arws1").set("manualindexing", true);
    model.result("pg1").feature("arws1").set("color", "black");
    model.result("pg1").feature("arws1").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").feature("arws1").feature("def1").set("scaleactive", true);
    model.result("pg1").feature("arws1").feature("def1").set("scale", "4E-4");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("arws2", "ArrowSurface");
    model.result("pg1").feature("arws2").set("arraydim", "2");
    model.result("pg1").feature("arws2")
         .label("\u9762\u4e0a\u7bad\u5934\uff0c\u4e00\u9636\u5f2f\u66f2\uff0cy \u65b9\u5411");
    model.result("pg1").feature("arws2").set("data", "dset1");
    model.result("pg1").feature("arws2").set("looplevel", new int[]{2});
    model.result("pg1").feature("arws2").set("arrowcount", 100);
    model.result("pg1").feature("arws2").set("manualindexing", true);
    model.result("pg1").feature("arws2").set("colindex", 1);
    model.result("pg1").feature("arws2").set("inheritplot", "arws1");
    model.result("pg1").feature("arws2").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").feature("arws2").set("arraydim", "2");
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("arws3", "arws2");
    model.result("pg1").feature("arws3").set("arraydim", "2");
    model.result("pg1").run();
    model.result("pg1").feature("arws3").label("\u9762\u4e0a\u7bad\u5934\uff0c\u4e00\u9636\u626d\u8f6c");
    model.result("pg1").feature("arws3").set("looplevel", new int[]{6});
    model.result("pg1").feature("arws3").set("rowindex", 1);
    model.result("pg1").feature("arws3").set("colindex", 0);
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("arws4", "arws3");
    model.result("pg1").feature("arws4").set("arraydim", "2");
    model.result("pg1").run();
    model.result("pg1").feature("arws4").set("looplevel", new int[]{9});
    model.result("pg1").feature("arws4").label("\u9762\u4e0a\u7bad\u5934\uff0c\u4e00\u9636\u8f74\u5411");
    model.result("pg1").feature("arws4").set("colindex", 1);
    model.result("pg1").feature("surf1").set("arraydim", "2");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").create("tran1", "Transparency");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").set("arraydim", "2");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").create("tran1", "Transparency");
    model.result("pg1").run();
    model.result("pg1").feature("surf3").set("arraydim", "2");
    model.result("pg1").run();
    model.result("pg1").feature("surf3").create("tran1", "Transparency");
    model.result("pg1").run();
    model.result("pg1").feature("surf4").set("arraydim", "2");
    model.result("pg1").run();
    model.result("pg1").feature("surf4").create("tran1", "Transparency");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("arraydim", "2");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("tlan1", "TableAnnotation");
    model.result("pg1").feature("tlan1").set("arraydim", "2");
    model.result("pg1").feature("tlan1").set("source", "localtable");
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "L0/2", 0, 0);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0, 0, 1);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0, 0, 2);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "\u4e00\u9636\u5f2f\u66f2 (z)", 0, 3);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "L0/2+1.4*L0", 1, 0);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0, 1, 1);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0, 1, 2);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "\u4e00\u9636\u5f2f\u66f2 (y)", 1, 3);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "L0/2", 2, 0);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "L0*1.4", 2, 1);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0, 2, 2);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "\u4e00\u9636\u626d\u8f6c", 2, 3);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "L0/2+1.4*L0", 3, 0);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "L0*1.4", 3, 1);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0, 3, 2);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "\u4e00\u9636\u8f74\u5411", 3, 3);
    model.result("pg1").feature("tlan1").set("showpoint", false);
    model.result("pg1").run();

    model.component("comp1").view("view1").set("showgrid", false);
    model.component("comp1").view().create("view2", "geom1");
    model.component("comp1").view("view2").label("\u89c6\u56fe 2\uff1a4 \u6839\u6881");

    model.result("pg1").run();
    model.result("pg1").set("view", "view2");
    model.result().evaluationGroup("std1EvgFrq").run();
    model.result().evaluationGroup("std1mpf1").run();

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
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("eig").set("neigsactive", true);
    model.study("std2").feature("eig").set("neigs", 10);
    model.study("std2").feature("eig").set("shift", "10000[Hz]");
    model.study("std2").feature("eig").set("useadvanceddisable", true);
    model.study("std2").feature("eig").set("disabledphysics", new String[]{"solid/lemm1/te1", "solid/fix2"});
    model.study("std2").label("\u7814\u7a76 2\uff0c\u60ac\u81c2\u6881\uff0c\u57fa\u7840\u89e3");
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("e1").set("rtol", 1.0E-30);
    model.sol("sol2").feature("e1").set("eigvfunscaleparam", 1);

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u632f\u578b\uff0c\u60ac\u81c2\u6881\uff0c\u57fa\u7840\u89e3");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").set("title", "\u60ac\u81c2\u6881\uff0c\u57fa\u7840\u89e3");
    model.result("pg2").feature("surf2").set("arraydim", "2");
    model.result("pg2").run();
    model.result("pg2").feature("surf2").set("data", "dset2");
    model.result("pg2").feature("surf3").set("arraydim", "2");
    model.result("pg2").run();
    model.result("pg2").feature("surf3").set("data", "dset2");
    model.result("pg2").feature("surf3").set("looplevel", new int[]{5});
    model.result("pg2").feature("surf4").set("arraydim", "2");
    model.result("pg2").run();
    model.result("pg2").feature("surf4").set("data", "dset2");
    model.result("pg2").feature("surf4").set("looplevel", new int[]{7});
    model.result("pg2").feature("arws2").set("arraydim", "2");
    model.result("pg2").run();
    model.result("pg2").feature("arws2").set("data", "dset2");
    model.result("pg2").feature("arws3").set("arraydim", "2");
    model.result("pg2").run();
    model.result("pg2").feature("arws3").set("data", "dset2");
    model.result("pg2").feature("arws3").set("looplevel", new int[]{5});
    model.result("pg2").feature("arws4").set("arraydim", "2");
    model.result("pg2").run();
    model.result("pg2").feature("arws4").set("data", "dset2");
    model.result("pg2").feature("arws4").set("looplevel", new int[]{7});
    model.result("pg2").run();

    model.component("comp1").view("view2").set("showgrid", false);

    model.result().evaluationGroup().duplicate("std1EvgFrq1", "std1EvgFrq");
    model.result().evaluationGroup("std1EvgFrq1").set("data", "dset2");
    model.result().evaluationGroup("std1EvgFrq1")
         .label("\u7279\u5f81\u9891\u7387\uff08\u7814\u7a76 2\uff0c\u60ac\u81c2\u6881\uff0c\u57fa\u7840\u89e3\uff09");
    model.result().evaluationGroup("std1EvgFrq1").run();
    model.result().evaluationGroup().duplicate("std1mpf2", "std1mpf1");
    model.result().evaluationGroup("std1mpf2").set("data", "dset2");
    model.result().evaluationGroup("std1mpf2")
         .label("\u53c2\u4e0e\u56e0\u5b50\uff08\u7814\u7a76 2\uff0c\u60ac\u81c2\u6881\uff0c\u57fa\u7840\u89e3\uff09");
    model.result().evaluationGroup("std1mpf2").run();
    model.result("pg1").run();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").add("plotgroup", "pg1");
    model.nodeGroup("grp1").add("evaluationgroup", "std1EvgFrq");
    model.nodeGroup("grp1").add("evaluationgroup", "std1mpf1");
    model.nodeGroup("grp1").label("\u5939\u7d27\u6881");

    model.result("pg2").run();

    model.nodeGroup().create("grp2", "Results");
    model.nodeGroup("grp2").add("plotgroup", "pg2");
    model.nodeGroup("grp2").add("evaluationgroup", "std1EvgFrq1");
    model.nodeGroup("grp2").add("evaluationgroup", "std1mpf2");
    model.nodeGroup("grp2").label("\u60ac\u81c2\u6881");

    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").set("plotgroup", "Default");
    model.study("std3").feature("stat").set("geometricNonlinearity", true);
    model.study("std3").feature("stat").set("outputmap", new String[]{});
    model.study("std3").feature("stat").set("ngenAUX", "1");
    model.study("std3").feature("stat").set("goalngenAUX", "1");
    model.study("std3").feature("stat").set("ngenAUX", "1");
    model.study("std3").feature("stat").set("goalngenAUX", "1");
    model.study("std3").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std3").create("eig", "Eigenfrequency");
    model.study("std3").feature("eig").set("plotgroup", "Default");
    model.study("std3").feature("eig").set("storefact", false);
    model.study("std3").feature("eig").set("geometricNonlinearity", true);
    model.study("std3").feature("eig").set("outputmap", new String[]{});
    model.study("std3").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std3").feature("eig").set("ngenAUX", "1");
    model.study("std3").feature("eig").set("goalngenAUX", "1");
    model.study("std3").feature("eig").set("ngenAUX", "1");
    model.study("std3").feature("eig").set("goalngenAUX", "1");
    model.study("std3").feature("eig").setSolveFor("/physics/solid", true);
    model.study("std3").label("\u7814\u7a76 3\uff0c\u5939\u7d27\u6881\uff0cdT");
    model.study("std3").setGenPlots(false);
    model.study("std3").feature("stat").set("geometricNonlinearity", false);
    model.study("std3").feature("eig").set("neigsactive", true);
    model.study("std3").feature("eig").set("neigs", 10);
    model.study("std3").feature("eig").set("shift", "10000[Hz]");
    model.study("std3").create("param", "Parametric");
    model.study("std3").feature("param").setIndex("pname", "a0", 0);
    model.study("std3").feature("param").setIndex("plistarr", "", 0);
    model.study("std3").feature("param").setIndex("punit", "m", 0);
    model.study("std3").feature("param").setIndex("pname", "a0", 0);
    model.study("std3").feature("param").setIndex("plistarr", "", 0);
    model.study("std3").feature("param").setIndex("punit", "m", 0);
    model.study("std3").feature("param").setIndex("pname", "dT", 0);
    model.study("std3").feature("param").setIndex("plistarr", "range(0,2,10)", 0);
    model.study("std3").showAutoSequences("all");

    model.sol("sol3").feature("e1").set("rtol", 1.0E-30);
    model.sol("sol3").feature("e1").set("eigvfunscale", "average");

    model.study("std3").createAutoSequences("all");

    model.sol().create("sol5");
    model.sol("sol5").study("std3");
    model.sol("sol5").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol5");
    model.batch("p1").run("compute");

    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u968f\u6e29\u5ea6\u53d8\u5316\u7684\u9891\u79fb\uff0c\u5939\u7d27\u6881");
    model.result("pg3").set("data", "dset5");
    model.result("pg3").setIndex("looplevelinput", "first", 0);
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u968f dT \u53d8\u5316\u7684\u9891\u79fb\uff0c\u5939\u7d27\u6881");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").label("\u4e00\u9636\u5f2f\u66f2\u6a21\u5f0f\uff0cz \u65b9\u5411");
    model.result("pg3").feature("glob1").setIndex("expr", "solid.freq/withsol('sol1',freq,setind(lambda,1))", 0);
    model.result("pg3").feature("glob1").set("xdatasolnumtype", "outer");
    model.result("pg3").feature("glob1").set("linewidth", 2);
    model.result("pg3").feature("glob1").set("linemarker", "circle");
    model.result("pg3").feature("glob1").set("legendmethod", "manual");
    model.result("pg3").feature("glob1").setIndex("legends", "\u4e00\u9636\u5f2f\u66f2 (z)", 0);
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("glob2", "glob1");
    model.result("pg3").run();
    model.result("pg3").feature("glob2").label("\u4e00\u9636\u5f2f\u66f2\u6a21\u5f0f\uff0cy \u65b9\u5411");
    model.result("pg3").feature("glob2").set("data", "dset5");
    model.result("pg3").feature("glob2").setIndex("looplevelinput", "manualindices", 0);
    model.result("pg3").feature("glob2").setIndex("looplevelindices", 2, 0);
    model.result("pg3").feature("glob2").setIndex("expr", "solid.freq/withsol('sol1',freq,setind(lambda,2))", 0);
    model.result("pg3").feature("glob2").setIndex("legends", "\u4e00\u9636\u5f2f\u66f2 (y)", 0);
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("glob3", "glob2");
    model.result("pg3").run();
    model.result("pg3").feature("glob3").label("\u4e00\u9636\u626d\u8f6c\u6a21\u5f0f");
    model.result("pg3").feature("glob3").setIndex("looplevelindices", 6, 0);
    model.result("pg3").feature("glob3").setIndex("expr", "solid.freq/withsol('sol1',freq,setind(lambda,6))", 0);
    model.result("pg3").feature("glob3").setIndex("legends", "\u4e00\u9636\u626d\u8f6c", 0);
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("glob4", "glob3");
    model.result("pg3").run();
    model.result("pg3").feature("glob4").label("\u4e00\u9636\u8f74\u5411\u6a21\u5f0f");
    model.result("pg3").feature("glob4").setIndex("looplevelindices", 9, 0);
    model.result("pg3").feature("glob4").setIndex("expr", "solid.freq/withsol('sol1',freq,setind(lambda,9))", 0);
    model.result("pg3").feature("glob4").setIndex("legends", "\u4e00\u9636\u8f74\u5411", 0);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").set("legendpos", "lowerleft");
    model.result("pg3").run();

    model.study().create("std4");
    model.study("std4").create("stat", "Stationary");
    model.study("std4").feature("stat").set("plotgroup", "Default");

    return model;
  }

  public static Model run2(Model model) {
    model.study("std4").feature("stat").set("geometricNonlinearity", true);
    model.study("std4").feature("stat").set("outputmap", new String[]{});
    model.study("std4").feature("stat").set("ngenAUX", "1");
    model.study("std4").feature("stat").set("goalngenAUX", "1");
    model.study("std4").feature("stat").set("ngenAUX", "1");
    model.study("std4").feature("stat").set("goalngenAUX", "1");
    model.study("std4").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std4").create("eig", "Eigenfrequency");
    model.study("std4").feature("eig").set("plotgroup", "Default");
    model.study("std4").feature("eig").set("storefact", false);
    model.study("std4").feature("eig").set("geometricNonlinearity", true);
    model.study("std4").feature("eig").set("outputmap", new String[]{});
    model.study("std4").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std4").feature("eig").set("ngenAUX", "1");
    model.study("std4").feature("eig").set("goalngenAUX", "1");
    model.study("std4").feature("eig").set("ngenAUX", "1");
    model.study("std4").feature("eig").set("goalngenAUX", "1");
    model.study("std4").feature("eig").setSolveFor("/physics/solid", true);
    model.study("std4").label("\u7814\u7a76 4\uff0c\u60ac\u81c2\u6881\uff0cdT");
    model.study("std4").setGenPlots(false);
    model.study("std4").feature("stat").set("geometricNonlinearity", false);
    model.study("std4").feature("stat").set("useadvanceddisable", true);
    model.study("std4").feature("stat").set("disabledphysics", new String[]{"solid/fix2"});
    model.study("std4").feature("eig").set("neigsactive", true);
    model.study("std4").feature("eig").set("neigs", 10);
    model.study("std4").feature("eig").set("shift", "10000[Hz]");
    model.study("std4").feature("eig").set("useadvanceddisable", true);
    model.study("std4").feature("eig").set("disabledphysics", new String[]{"solid/fix2"});
    model.study("std4").create("param", "Parametric");
    model.study("std4").feature("param").setIndex("pname", "a0", 0);
    model.study("std4").feature("param").setIndex("plistarr", "", 0);
    model.study("std4").feature("param").setIndex("punit", "m", 0);
    model.study("std4").feature("param").setIndex("pname", "a0", 0);
    model.study("std4").feature("param").setIndex("plistarr", "", 0);
    model.study("std4").feature("param").setIndex("punit", "m", 0);
    model.study("std4").feature("param").setIndex("pname", "dT", 0);
    model.study("std4").feature("param").setIndex("plistarr", "range(0,2,10)", 0);
    model.study("std4").showAutoSequences("all");

    model.sol("sol12").feature("e1").set("rtol", 1.0E-30);
    model.sol("sol12").feature("e1").set("eigvfunscale", "average");

    model.study("std4").createAutoSequences("all");

    model.sol().create("sol14");
    model.sol("sol14").study("std4");
    model.sol("sol14").label("\u53c2\u6570\u5316\u89e3 2");

    model.batch("p2").feature("so1").set("psol", "sol14");
    model.batch("p2").run("compute");

    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u968f\u6e29\u5ea6\u53d8\u5316\u7684\u9891\u79fb\uff0c\u60ac\u81c2\u6881");
    model.result("pg4").set("data", "dset8");
    model.result("pg4").set("title", "\u968f dT \u53d8\u5316\u7684\u9891\u79fb\uff0c\u60ac\u81c2\u6881");
    model.result("pg4").run();
    model.result("pg4").feature("glob1").setIndex("expr", "solid.freq/withsol('sol2',freq,setind(lambda,1))", 0);
    model.result("pg4").run();
    model.result("pg4").feature("glob2").set("data", "dset8");
    model.result("pg4").feature("glob2").setIndex("expr", "solid.freq/withsol('sol2',freq,setind(lambda,2))", 0);
    model.result("pg4").run();
    model.result("pg4").feature("glob3").set("data", "dset8");
    model.result("pg4").feature("glob3").setIndex("looplevelindices", 5, 0);
    model.result("pg4").feature("glob3").setIndex("expr", "solid.freq/withsol('sol2',freq,setind(lambda,5))", 0);
    model.result("pg4").run();
    model.result("pg4").feature("glob4").set("data", "dset8");
    model.result("pg4").feature("glob4").setIndex("looplevelindices", 7, 0);
    model.result("pg4").feature("glob4").setIndex("expr", "solid.freq/withsol('sol2',freq,setind(lambda,7))", 0);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").set("legendpos", "upperleft");
    model.result("pg4").run();
    model.result("pg3").run();
    model.result().move("pg3", 1);

    model.nodeGroup("grp1").add("plotgroup", "pg3");

    model.result("pg4").run();

    model.nodeGroup("grp2").add("plotgroup", "pg4");

    model.result().evaluationGroup().create("eg1", "EvaluationGroup");

    model.nodeGroup("grp1").add("evaluationgroup", "eg1");

    model.result().evaluationGroup("eg1")
         .label("\u968f\u6e29\u5ea6\u53d8\u5316\u7684\u9891\u79fb\uff0c\u5939\u7d27\u6881");
    model.result().evaluationGroup("eg1").set("data", "dset5");
    model.result().evaluationGroup("eg1").set("concatenation", "vertical");
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").set("data", "dset5");
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("looplevelinput", "first", 0);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("expr", "freq/withsol('sol1',freq,setind(lambda,1))", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("looplevelinput", "last", 1);
    model.result().evaluationGroup("eg1").feature("gev1").label("\u4e00\u9636\u5f2f\u66f2 (z)");
    model.result().evaluationGroup("eg1").feature().duplicate("gev2", "gev1");
    model.result().evaluationGroup("eg1").feature("gev2").label("\u4e00\u9636\u5f2f\u66f2 (y)");
    model.result().evaluationGroup("eg1").feature("gev2").setIndex("looplevelinput", "manualindices", 0);
    model.result().evaluationGroup("eg1").feature("gev2").setIndex("looplevelindices", 2, 0);
    model.result().evaluationGroup("eg1").feature("gev2")
         .setIndex("expr", "freq/withsol('sol1',freq,setind(lambda,2))", 0);
    model.result().evaluationGroup("eg1").feature().duplicate("gev3", "gev2");
    model.result().evaluationGroup("eg1").feature("gev3").label("\u4e00\u9636\u626d\u8f6c");
    model.result().evaluationGroup("eg1").feature("gev3").setIndex("looplevelindices", 6, 0);
    model.result().evaluationGroup("eg1").feature("gev3")
         .setIndex("expr", "freq/withsol('sol1',freq,setind(lambda,6))", 0);
    model.result().evaluationGroup("eg1").feature().duplicate("gev4", "gev3");
    model.result().evaluationGroup("eg1").feature("gev4").setIndex("looplevelindices", 9, 0);
    model.result().evaluationGroup("eg1").feature("gev4")
         .setIndex("expr", "freq/withsol('sol1',freq,setind(lambda,9))", 0);
    model.result().evaluationGroup("eg1").feature("gev4").label("\u4e00\u9636\u8f74\u5411");
    model.result().evaluationGroup("eg1").run();
    model.result().evaluationGroup().copy("eg2", "eg1");

    model.nodeGroup("grp2").add("evaluationgroup", "eg2");

    model.result().evaluationGroup("eg2")
         .label("\u968f\u6e29\u5ea6\u53d8\u5316\u7684\u9891\u79fb\uff0c\u60ac\u81c2\u6881");
    model.result().evaluationGroup("eg2").set("data", "dset8");
    model.result().evaluationGroup("eg2").feature("gev1").set("data", "dset8");
    model.result().evaluationGroup("eg2").feature("gev1")
         .setIndex("expr", "freq/withsol('sol2',freq,setind(lambda,1))", 0);
    model.result().evaluationGroup("eg2").feature("gev2").set("data", "dset8");
    model.result().evaluationGroup("eg2").feature("gev2")
         .setIndex("expr", "freq/withsol('sol2',freq,setind(lambda,2))", 0);
    model.result().evaluationGroup("eg2").feature("gev3").set("data", "dset8");
    model.result().evaluationGroup("eg2").feature("gev3").setIndex("looplevelindices", 5, 0);
    model.result().evaluationGroup("eg2").feature("gev3")
         .setIndex("expr", "freq/withsol('sol2',freq,setind(lambda,5))", 0);
    model.result().evaluationGroup("eg2").feature("gev4").set("data", "dset8");
    model.result().evaluationGroup("eg2").feature("gev4").setIndex("looplevelindices", 7, 0);
    model.result().evaluationGroup("eg2").feature("gev4")
         .setIndex("expr", "freq/withsol('sol2',freq,setind(lambda,7))", 0);
    model.result().evaluationGroup("eg2").run();

    model.study().create("std5");
    model.study("std5").create("stat", "Stationary");
    model.study("std5").feature("stat").set("plotgroup", "Default");
    model.study("std5").feature("stat").set("solnum", "auto");
    model.study("std5").feature("stat").set("notsolnum", "auto");
    model.study("std5").feature("stat").set("outputmap", new String[]{});
    model.study("std5").feature("stat").set("ngenAUX", "1");
    model.study("std5").feature("stat").set("goalngenAUX", "1");
    model.study("std5").feature("stat").set("ngenAUX", "1");
    model.study("std5").feature("stat").set("goalngenAUX", "1");
    model.study("std5").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std5").create("buckling", "LinearBuckling");
    model.study("std5").feature("buckling").set("plotgroup", "Default");
    model.study("std5").feature("buckling").set("neigsactive", true);
    model.study("std5").feature("buckling").set("solnum", "auto");
    model.study("std5").feature("buckling").set("notsolnum", "auto");
    model.study("std5").feature("buckling").set("outputmap", new String[]{});
    model.study("std5").feature("buckling").set("ngenAUX", "1");
    model.study("std5").feature("buckling").set("goalngenAUX", "1");
    model.study("std5").feature("buckling").set("ngenAUX", "1");
    model.study("std5").feature("buckling").set("goalngenAUX", "1");
    model.study("std5").feature("buckling").setSolveFor("/physics/solid", true);
    model.study("std5").label("\u7814\u7a76 5\uff0c\u5c48\u66f2");
    model.study("std5").createAutoSequences("all");

    model.sol("sol21").runAll();

    model.result().dataset("dset9").set("frametype", "spatial");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset9");
    model.result("pg5").setIndex("looplevel", 1, 0);
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").label("\u632f\u578b (solid)");
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
    model.result("pg5").run();
    model.result("pg5").label("\u632f\u578b\uff0c\u5c48\u66f2");
    model.result().move("pg5", 2);

    model.nodeGroup("grp1").add("plotgroup", "pg5");

    model.component("comp1").view().create("view3", "geom1");
    model.component("comp1").view("view3").label("\u89c6\u56fe 3\uff1a\u5c48\u66f2");

    model.result("pg5").run();
    model.result("pg5").set("view", "view3");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("eps_x", "alpha_x*dT");
    model.component("comp1").variable("var1").descr("eps_x", "\u70ed\u5e94\u53d8\uff0cx \u65b9\u5411");
    model.component("comp1").variable("var1").set("eps_y", "alpha_y*dT");
    model.component("comp1").variable("var1").descr("eps_y", "\u70ed\u5e94\u53d8\uff0cy \u65b9\u5411");
    model.component("comp1").variable("var1").set("eps_z", "alpha_z*dT");
    model.component("comp1").variable("var1").descr("eps_z", "\u70ed\u5e94\u53d8\uff0cz \u65b9\u5411");
    model.component("comp1").variable("var1").set("fbz_fb0z", "1-3*eps_x/2+eps_y/2+3*eps_z/2");
    model.component("comp1").variable("var1")
         .descr("fbz_fb0z", "\u9891\u79fb\u4f30\u7b97\u503c\uff0c\u60ac\u81c2\u6881\uff0cz \u65b9\u5411\u5f2f\u66f2");
    model.component("comp1").variable("var1").set("fby_fb0y", "1-3*eps_x/2+3*eps_y/2+eps_z/2");
    model.component("comp1").variable("var1")
         .descr("fby_fb0y", "\u9891\u79fb\u4f30\u7b97\u503c\uff0c\u60ac\u81c2\u6881\uff0cy \u65b9\u5411\u5f2f\u66f2");
    model.component("comp1").variable("var1").set("fa_fa0", "1-eps_x/2+eps_y/2+eps_z/2");
    model.component("comp1").variable("var1")
         .descr("fa_fa0", "\u9891\u79fb\u4f30\u7b97\u503c\uff0c\u60ac\u81c2\u6881\uff0c\u8f74\u5411");
    model.component("comp1").variable("var1").set("ft_ft0", "1-eps_x/2-3*eps_y/10+6*eps_z/5");
    model.component("comp1").variable("var1")
         .descr("ft_ft0", "\u9891\u79fb\u4f30\u7b97\u503c\uff0c\u60ac\u81c2\u6881\uff0c\u626d\u8f6c");

    model.sol("sol12").updateSolution();
    model.sol("sol14").updateSolution();

    model.result().evaluationGroup("eg2").feature("gev1").setIndex("expr", "fbz_fb0z", 1);
    model.result().evaluationGroup("eg2").feature("gev2").setIndex("expr", "fby_fb0y", 1);
    model.result().evaluationGroup("eg2").feature("gev3").setIndex("expr", "ft_ft0", 1);
    model.result().evaluationGroup("eg2").feature("gev4").setIndex("expr", "fa_fa0", 1);
    model.result().evaluationGroup("eg2").run();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("glob5", "glob1");
    model.result("pg4").feature().duplicate("glob6", "glob2");
    model.result("pg4").feature().duplicate("glob7", "glob3");
    model.result("pg4").feature().duplicate("glob8", "glob4");
    model.result("pg4").run();
    model.result("pg4").feature("glob5")
         .label("\u4e00\u9636\u5f2f\u66f2\u6a21\u5f0f\uff0cz \u65b9\u5411\uff08\u4f30\u7b97\u503c\uff09");
    model.result("pg4").feature("glob5").setIndex("expr", "fbz_fb0z", 0);
    model.result("pg4").feature("glob5").set("linestyle", "dashed");
    model.result("pg4").feature("glob5").set("linemarker", "square");
    model.result("pg4").feature("glob5")
         .setIndex("legends", "\u4e00\u9636\u5f2f\u66f2 (z)\uff0c\u4f30\u7b97\u503c", 0);
    model.result("pg4").run();
    model.result("pg4").feature("glob6")
         .label("\u4e00\u9636\u5f2f\u66f2\u6a21\u5f0f\uff0cy \u65b9\u5411\uff08\u4f30\u7b97\u503c\uff09");
    model.result("pg4").feature("glob6").setIndex("expr", "fby_fb0y", 0);
    model.result("pg4").feature("glob6").set("linestyle", "dashed");
    model.result("pg4").feature("glob6").set("linemarker", "square");
    model.result("pg4").feature("glob6")
         .setIndex("legends", "\u4e00\u9636\u5f2f\u66f2 (y)\uff0c\u4f30\u7b97\u503c", 0);
    model.result("pg4").run();
    model.result("pg4").feature("glob7").label("\u4e00\u9636\u626d\u8f6c\u6a21\u5f0f\uff08\u4f30\u7b97\u503c\uff09");
    model.result("pg4").feature("glob7").setIndex("expr", "ft_ft0", 0);
    model.result("pg4").feature("glob7").set("linestyle", "dashed");
    model.result("pg4").feature("glob7").set("linemarker", "square");
    model.result("pg4").feature("glob7").setIndex("legends", "\u4e00\u9636\u626d\u8f6c\uff0c\u4f30\u7b97\u503c", 0);
    model.result("pg4").run();
    model.result("pg4").feature("glob8").label("\u4e00\u9636\u8f74\u5411\u6a21\u5f0f\uff08\u4f30\u7b97\u503c\uff09");
    model.result("pg4").feature("glob8").setIndex("expr", "fa_fa0", 0);
    model.result("pg4").feature("glob8").set("linestyle", "dashed");
    model.result("pg4").feature("glob8").set("linemarker", "square");
    model.result("pg4").feature("glob8").setIndex("legends", "\u4e00\u9636\u8f74\u5411\uff0c\u4f30\u7b97\u503c", 0);
    model.result("pg4").run();
    model.result("pg4").feature("glob5").set("linecolor", "cyclereset");
    model.result().create("pg6", "PlotGroup3D");

    model.nodeGroup("grp2").add("plotgroup", "pg6");

    model.result("pg6").run();
    model.result("pg6").label("\u5939\u94b3\u4e0a\u7684\u5e94\u529b");
    model.result("pg6").set("data", "dset7");
    model.result("pg6").create("vol1", "Volume");
    model.result("pg6").feature("vol1").set("expr", "solid.sx");
    model.result("pg6").run();
    model.result("pg6").feature("vol1").set("unit", "MPa");

    model.component("comp1").physics("solid").feature("fix1").create("tefix1", "ThermalExpansionFix", 2);
    model.component("comp1").physics("solid").feature("fix1").feature("tefix1").set("Inherit", true);

    model.study("std1").feature("eig").set("disabledphysics", new String[]{"solid/lemm1/te1", "solid/fix1/tefix1"});
    model.study("std2").feature("eig")
         .set("disabledphysics", new String[]{"solid/lemm1/te1", "solid/fix2", "solid/fix1/tefix1"});
    model.study("std3").feature("stat").set("useadvanceddisable", true);
    model.study("std3").feature("stat").set("disabledphysics", new String[]{"solid/fix1/tefix1"});
    model.study("std3").feature("eig").set("useadvanceddisable", true);
    model.study("std3").feature("eig").set("disabledphysics", new String[]{"solid/fix1/tefix1"});
    model.study("std4").feature("stat").set("disabledphysics", new String[]{"solid/fix2", "solid/fix1/tefix1"});
    model.study("std4").feature("eig").set("disabledphysics", new String[]{"solid/fix2", "solid/fix1/tefix1"});
    model.study("std5").feature("stat").set("useadvanceddisable", true);
    model.study("std5").feature("stat").set("disabledphysics", new String[]{"solid/fix1/tefix1"});
    model.study("std5").feature("buckling").set("useadvanceddisable", true);
    model.study("std5").feature("buckling").set("disabledphysics", new String[]{"solid/fix1/tefix1"});
    model.study().create("std6");
    model.study("std6").create("stat", "Stationary");
    model.study("std6").feature("stat").set("plotgroup", "Default");
    model.study("std6").feature("stat").set("geometricNonlinearity", true);
    model.study("std6").feature("stat").set("outputmap", new String[]{});
    model.study("std6").feature("stat").set("ngenAUX", "1");
    model.study("std6").feature("stat").set("goalngenAUX", "1");
    model.study("std6").feature("stat").set("ngenAUX", "1");
    model.study("std6").feature("stat").set("goalngenAUX", "1");
    model.study("std6").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std6").create("eig", "Eigenfrequency");
    model.study("std6").feature("eig").set("plotgroup", "Default");
    model.study("std6").feature("eig").set("storefact", false);
    model.study("std6").feature("eig").set("geometricNonlinearity", true);
    model.study("std6").feature("eig").set("outputmap", new String[]{});
    model.study("std6").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std6").feature("eig").set("ngenAUX", "1");
    model.study("std6").feature("eig").set("goalngenAUX", "1");
    model.study("std6").feature("eig").set("ngenAUX", "1");
    model.study("std6").feature("eig").set("goalngenAUX", "1");
    model.study("std6").feature("eig").setSolveFor("/physics/solid", true);
    model.study("std6").label("\u7814\u7a76 6\uff0c\u5e26\u81a8\u80c0\u7ea6\u675f\u7684\u60ac\u81c2\u6881");
    model.study("std6").setGenPlots(false);
    model.study("std6").feature("stat").set("useadvanceddisable", true);
    model.study("std6").feature("stat").set("disabledphysics", new String[]{"solid/fix2"});
    model.study("std6").feature("eig").set("neigsactive", true);
    model.study("std6").feature("eig").set("neigs", 10);
    model.study("std6").feature("eig").set("shift", "10000[Hz]");
    model.study("std6").feature("eig").set("useadvanceddisable", true);
    model.study("std6").feature("eig").set("disabledphysics", new String[]{"solid/fix2"});
    model.study("std6").create("param", "Parametric");
    model.study("std6").feature("param").setIndex("pname", "a0", 0);
    model.study("std6").feature("param").setIndex("plistarr", "", 0);
    model.study("std6").feature("param").setIndex("punit", "m", 0);
    model.study("std6").feature("param").setIndex("pname", "a0", 0);
    model.study("std6").feature("param").setIndex("plistarr", "", 0);
    model.study("std6").feature("param").setIndex("punit", "m", 0);
    model.study("std6").feature("param").setIndex("pname", "dT", 0);
    model.study("std6").feature("param").setIndex("plistarr", "range(0,2,10)", 0);
    model.study("std6").showAutoSequences("all");

    model.sol("sol23").feature("e1").set("rtol", 1.0E-30);
    model.sol("sol23").feature("e1").set("eigvfunscale", "average");

    model.study("std6").createAutoSequences("all");

    model.sol().create("sol25");
    model.sol("sol25").study("std6");
    model.sol("sol25").label("\u53c2\u6570\u5316\u89e3 3");

    model.batch("p3").feature("so1").set("psol", "sol25");
    model.batch("p3").run("compute");

    model.result("pg4").run();
    model.result().duplicate("pg7", "pg4");

    model.nodeGroup("grp2").add("plotgroup", "pg7");

    model.result("pg7").run();
    model.result("pg7")
         .label("\u968f\u6e29\u5ea6\u53d8\u5316\u7684\u9891\u79fb\uff0c\u60ac\u81c2\u6881\uff0c\u81a8\u80c0\u7ea6\u675f");
    model.result("pg7").set("data", "dset13");
    model.result("pg7").run();
    model.result("pg7").feature("glob2").set("data", "dset13");
    model.result("pg7").run();
    model.result("pg7").feature("glob3").set("data", "dset13");
    model.result("pg7").run();
    model.result("pg7").feature("glob4").set("data", "dset13");
    model.result("pg7").run();
    model.result().evaluationGroup("eg2").feature("gev1").setIndex("descr", "\u9891\u79fb", 0);
    model.result().evaluationGroup("eg2").feature("gev1")
         .setIndex("expr", "withsol('sol25',freq,setind(lambda,1))/withsol('sol2',freq,setind(lambda,1))", 1);
    model.result().evaluationGroup("eg2").feature("gev1")
         .setIndex("descr", "\u9891\u79fb\uff0c\u6269\u5c55\u8fb9\u754c", 1);
    model.result().evaluationGroup("eg2").feature("gev1").setIndex("expr", "fbz_fb0z", 2);
    model.result().evaluationGroup("eg2").feature("gev2")
         .setIndex("expr", "withsol('sol25',freq,setind(lambda,2))/withsol('sol2',freq,setind(lambda,2))", 1);
    model.result().evaluationGroup("eg2").feature("gev2").setIndex("expr", "fby_fb0y", 2);
    model.result().evaluationGroup("eg2").feature("gev3")
         .setIndex("expr", "withsol('sol25',freq,setind(lambda,5))/withsol('sol2',freq,setind(lambda,5))", 1);
    model.result().evaluationGroup("eg2").feature("gev3").setIndex("expr", "ft_ft0", 2);
    model.result().evaluationGroup("eg2").feature("gev4")
         .setIndex("expr", "withsol('sol25',freq,setind(lambda,7))/withsol('sol2',freq,setind(lambda,7))", 1);
    model.result().evaluationGroup("eg2").feature("gev4").setIndex("expr", "fa_fa0", 2);
    model.result().evaluationGroup("eg2").run();
    model.result("pg6").run();
    model.result("pg6").feature().duplicate("vol2", "vol1");
    model.result("pg6").run();
    model.result("pg6").feature("vol2").set("data", "dset12");
    model.result("pg6").feature("vol2").set("inheritplot", "vol1");
    model.result("pg6").run();
    model.result("pg6").set("plotarrayenable", true);
    model.result("pg6").set("arrayaxis", "y");
    model.result("pg6").set("relpadding", 5);
    model.result("pg6").run();
    model.result("pg6").set("titletype", "manual");
    model.result("pg6").set("title", "\u5e94\u529b\u5f20\u91cf\uff0cx \u5206\u91cf (MPa)");
    model.result("pg6").run();
    model.result("pg6").feature("vol1").set("arraydim", "1");
    model.result("pg6").run();
    model.result("pg6").feature("vol1").label("\u65e0\u6269\u5c55\u8fb9\u754c");
    model.result("pg6").feature("vol2").set("arraydim", "1");
    model.result("pg6").run();
    model.result("pg6").feature("vol2").label("\u6269\u5c55\u8fb9\u754c");
    model.result("pg6").feature("vol1").set("arraydim", "1");
    model.result("pg6").run();
    model.result("pg6").feature("vol1").create("def1", "Deform");
    model.result("pg6").run();
    model.result("pg6").feature("vol2").set("arraydim", "1");
    model.result("pg6").run();
    model.result("pg6").feature("vol2").create("def1", "Deform");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").create("tlan1", "TableAnnotation");
    model.result("pg6").feature("tlan1").set("arraydim", "1");
    model.result("pg6").feature("tlan1").set("source", "localtable");
    model.result("pg6").feature("tlan1").setIndex("localtablematrix", -0.25, 0, 0);
    model.result("pg6").feature("tlan1").setIndex("localtablematrix", -0.75, 0, 1);
    model.result("pg6").feature("tlan1").setIndex("localtablematrix", 0, 0, 2);
    model.result("pg6").feature("tlan1").setIndex("localtablematrix", "\u65e0\u6269\u5c55\u8fb9\u754c", 0, 3);
    model.result("pg6").feature("tlan1").setIndex("localtablematrix", -0.25, 1, 0);
    model.result("pg6").feature("tlan1").setIndex("localtablematrix", 5.25, 1, 1);
    model.result("pg6").feature("tlan1").setIndex("localtablematrix", 0, 1, 2);
    model.result("pg6").feature("tlan1").setIndex("localtablematrix", "\u6269\u5c55\u8fb9\u754c", 1, 3);
    model.result("pg6").feature("tlan1").set("showpoint", false);
    model.result("pg6").feature("tlan1").set("anchorpoint", "lowerleft");
    model.result("pg6").run();

    model.component("comp1").view().create("view4", "geom1");
    model.component("comp1").view("view4").label("\u89c6\u56fe 4\uff1a2 \u6839\u6881");

    model.result("pg6").run();
    model.result("pg6").set("view", "view4");

    model.param("par2").set("beta", "-1e-4[1/K]*0");
    model.param("par2").descr("beta", "\u6768\u6c0f\u6a21\u91cf\u7684\u76f8\u5bf9\u6e29\u5ea6\u7075\u654f\u5ea6");

    model.component("comp1").material("mat1").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("Enu").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"E0*(1+beta*(T-293.15))"});

    model.component("comp1").physics("solid").feature("lemm1")
         .set("minput_strainreferencetemperature_src", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").set("minput_temperature_src", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").set("minput_temperature", "293.15[K]+dT");

    model.study().create("std7");
    model.study("std7").create("stat", "Stationary");
    model.study("std7").feature("stat").set("plotgroup", "Default");
    model.study("std7").feature("stat").set("geometricNonlinearity", true);
    model.study("std7").feature("stat").set("outputmap", new String[]{});
    model.study("std7").feature("stat").set("ngenAUX", "1");
    model.study("std7").feature("stat").set("goalngenAUX", "1");
    model.study("std7").feature("stat").set("ngenAUX", "1");
    model.study("std7").feature("stat").set("goalngenAUX", "1");
    model.study("std7").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std7").create("eig", "Eigenfrequency");
    model.study("std7").feature("eig").set("plotgroup", "Default");
    model.study("std7").feature("eig").set("storefact", false);
    model.study("std7").feature("eig").set("geometricNonlinearity", true);
    model.study("std7").feature("eig").set("outputmap", new String[]{});
    model.study("std7").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std7").feature("eig").set("ngenAUX", "1");
    model.study("std7").feature("eig").set("goalngenAUX", "1");
    model.study("std7").feature("eig").set("ngenAUX", "1");
    model.study("std7").feature("eig").set("goalngenAUX", "1");
    model.study("std7").feature("eig").setSolveFor("/physics/solid", true);
    model.study("std7")
         .label("\u7814\u7a76 7\uff0c\u6e29\u5ea6\u4f9d\u5b58\u6027\u53c2\u6570\u4e3a E \u7684\u60ac\u81c2\u6881");
    model.study("std7").setGenPlots(false);
    model.study("std7").feature("stat").set("geometricNonlinearity", false);
    model.study("std7").feature("stat").set("useadvanceddisable", true);
    model.study("std7").feature("stat").set("disabledphysics", new String[]{"solid/fix2"});
    model.study("std7").feature("eig").set("neigsactive", true);
    model.study("std7").feature("eig").set("neigs", 10);
    model.study("std7").feature("eig").set("shift", "10000[Hz]");
    model.study("std7").feature("eig").set("useadvanceddisable", true);
    model.study("std7").feature("eig").set("disabledphysics", new String[]{"solid/fix2"});
    model.study("std7").create("param", "Parametric");
    model.study("std7").feature("param").setIndex("pname", "a0", 0);
    model.study("std7").feature("param").setIndex("plistarr", "", 0);
    model.study("std7").feature("param").setIndex("punit", "m", 0);
    model.study("std7").feature("param").setIndex("pname", "a0", 0);
    model.study("std7").feature("param").setIndex("plistarr", "", 0);
    model.study("std7").feature("param").setIndex("punit", "m", 0);
    model.study("std7").feature("param").setIndex("pname", "beta", 0);
    model.study("std7").feature("param").setIndex("plistarr", "-1E-4", 0);
    model.study("std7").feature("param").setIndex("pname", "a0", 1);
    model.study("std7").feature("param").setIndex("plistarr", "", 1);
    model.study("std7").feature("param").setIndex("punit", "m", 1);
    model.study("std7").feature("param").setIndex("pname", "a0", 1);
    model.study("std7").feature("param").setIndex("plistarr", "", 1);
    model.study("std7").feature("param").setIndex("punit", "m", 1);
    model.study("std7").feature("param").setIndex("pname", "dT", 1);
    model.study("std7").feature("param").setIndex("plistarr", "range(0,2,10)", 1);
    model.study("std7").feature("param").set("sweeptype", "filled");
    model.study("std7").showAutoSequences("all");

    model.sol("sol32").feature("e1").set("rtol", 1.0E-30);
    model.sol("sol32").feature("e1").set("eigvfunscale", "average");

    model.study("std7").createAutoSequences("all");

    model.sol().create("sol34");
    model.sol("sol34").study("std7");
    model.sol("sol34").label("\u53c2\u6570\u5316\u89e3 4");

    model.batch("p4").feature("so1").set("psol", "sol34");
    model.batch("p4").run("compute");

    model.result("pg7").run();
    model.result().duplicate("pg8", "pg7");

    model.nodeGroup("grp2").add("plotgroup", "pg8");

    model.result("pg8").run();
    model.result("pg8")
         .label("\u968f\u6e29\u5ea6\u53d8\u5316\u7684\u9891\u79fb\uff0c\u60ac\u81c2\u6881\u3001\u6e29\u5ea6\u4f9d\u5b58\u6027\u53c2\u6570\u4e3a E");
    model.result("pg8").set("data", "dset16");
    model.result("pg8").setIndex("looplevelinput", "first", 2);
    model.result("pg8").run();
    model.result("pg8").feature("glob1").set("data", "dset16");
    model.result("pg8").feature("glob1").setIndex("looplevelinput", "first", 2);
    model.result("pg8").feature("glob1").setIndex("looplevelinput", "first", 0);
    model.result("pg8").feature("glob1").set("xdata", "expr");
    model.result("pg8").feature("glob1").set("xdataexpr", "dT");
    model.result("pg8").run();
    model.result("pg8").feature("glob2").set("data", "dset16");
    model.result("pg8").feature("glob2").setIndex("looplevelinput", "first", 2);
    model.result("pg8").feature("glob2").setIndex("looplevelinput", "manualindices", 0);
    model.result("pg8").feature("glob2").setIndex("looplevelindices", 2, 0);
    model.result("pg8").feature("glob2").set("xdata", "expr");
    model.result("pg8").feature("glob2").set("xdataexpr", "dT");

    return model;
  }

  public static Model run3(Model model) {
    model.result("pg8").run();
    model.result("pg8").feature("glob3").set("data", "dset16");
    model.result("pg8").feature("glob3").setIndex("looplevelinput", "first", 2);
    model.result("pg8").feature("glob3").setIndex("looplevelinput", "manualindices", 0);
    model.result("pg8").feature("glob3").setIndex("looplevelindices", 5, 0);
    model.result("pg8").feature("glob3").set("xdata", "expr");
    model.result("pg8").feature("glob3").set("xdataexpr", "dT");
    model.result("pg8").run();
    model.result("pg8").feature("glob4").set("data", "dset16");
    model.result("pg8").feature("glob4").setIndex("looplevelinput", "first", 2);
    model.result("pg8").feature("glob4").setIndex("looplevelinput", "manualindices", 0);
    model.result("pg8").feature("glob4").setIndex("looplevelindices", 7, 0);
    model.result("pg8").feature("glob4").set("xdata", "expr");
    model.result("pg8").feature("glob4").set("xdataexpr", "dT");
    model.result("pg8").run();
    model.result("pg8").feature("glob5").set("data", "dset16");
    model.result("pg8").feature("glob5").setIndex("looplevelinput", "first", 2);
    model.result("pg8").feature("glob5").setIndex("looplevelinput", "first", 0);
    model.result("pg8").feature("glob5").set("xdata", "expr");
    model.result("pg8").feature("glob5").set("xdataexpr", "dT");
    model.result("pg8").feature("glob5").setIndex("expr", "fbz_fb0z+beta*dT/2", 0);
    model.result("pg8").run();
    model.result("pg8").feature("glob6").set("data", "dset16");
    model.result("pg8").feature("glob6").setIndex("looplevelinput", "first", 2);
    model.result("pg8").feature("glob6").setIndex("looplevelinput", "first", 0);
    model.result("pg8").feature("glob6").setIndex("expr", "fby_fb0y+beta*dT/2", 0);
    model.result("pg8").feature("glob6").set("xdata", "expr");
    model.result("pg8").feature("glob6").set("xdataexpr", "dT");
    model.result("pg8").run();
    model.result("pg8").feature("glob7").set("data", "dset16");
    model.result("pg8").feature("glob7").setIndex("looplevelinput", "first", 2);
    model.result("pg8").feature("glob7").setIndex("looplevelinput", "first", 0);
    model.result("pg8").feature("glob7").setIndex("expr", "ft_ft0+beta*dT/2", 0);
    model.result("pg8").feature("glob7").set("xdata", "expr");
    model.result("pg8").feature("glob7").set("xdataexpr", "dT");
    model.result("pg8").run();
    model.result("pg8").feature("glob8").set("data", "dset16");
    model.result("pg8").feature("glob8").setIndex("looplevelinput", "first", 2);
    model.result("pg8").feature("glob8").setIndex("looplevelinput", "first", 0);
    model.result("pg8").feature("glob8").setIndex("expr", "fa_fa0+beta*dT/2", 0);
    model.result("pg8").feature("glob8").set("xdata", "expr");
    model.result("pg8").feature("glob8").set("xdataexpr", "dT");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").set("legendpos", "lowerleft");
    model.result("pg8").run();
    model.result().evaluationGroup("eg2").feature("gev1")
         .setIndex("expr", "withsol('sol34',freq,setind(lambda,1))/withsol('sol2',freq,setind(lambda,1))", 3);
    model.result().evaluationGroup("eg2").feature("gev1").setIndex("descr", "\u9891\u79fb\uff0cE(T)", 3);
    model.result().evaluationGroup("eg2").feature("gev1").setIndex("expr", "fbz_fb0z+withsol('sol34',beta)*dT/2", 4);
    model.result().evaluationGroup("eg2").feature("gev1")
         .setIndex("descr", "\u9891\u79fb\u4f30\u7b97\u503c\uff0cE(T)", 4);
    model.result().evaluationGroup("eg2").feature("gev2")
         .setIndex("expr", "withsol('sol34',freq,setind(lambda,2))/withsol('sol2',freq,setind(lambda,2))", 3);
    model.result().evaluationGroup("eg2").feature("gev2").setIndex("expr", "fby_fb0y+withsol('sol34',beta)*dT/2", 4);
    model.result().evaluationGroup("eg2").feature("gev3")
         .setIndex("expr", "withsol('sol34',freq,setind(lambda,5))/withsol('sol2',freq,setind(lambda,5))", 3);
    model.result().evaluationGroup("eg2").feature("gev3").setIndex("expr", "ft_ft0+withsol('sol34',beta)*dT/2", 4);
    model.result().evaluationGroup("eg2").feature("gev4")
         .setIndex("expr", "withsol('sol34',freq,setind(lambda,7))/withsol('sol2',freq,setind(lambda,7))", 3);
    model.result().evaluationGroup("eg2").feature("gev4").setIndex("expr", "fa_fa0+withsol('sol34',beta)*dT/2", 4);
    model.result().evaluationGroup("eg2").run();

    model.study().create("std8");
    model.study("std8").create("stat", "Stationary");
    model.study("std8").feature("stat").set("plotgroup", "Default");
    model.study("std8").feature("stat").set("geometricNonlinearity", true);
    model.study("std8").feature("stat").set("outputmap", new String[]{});
    model.study("std8").feature("stat").set("ngenAUX", "1");
    model.study("std8").feature("stat").set("goalngenAUX", "1");
    model.study("std8").feature("stat").set("ngenAUX", "1");
    model.study("std8").feature("stat").set("goalngenAUX", "1");
    model.study("std8").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std8").create("eig", "Eigenfrequency");
    model.study("std8").feature("eig").set("plotgroup", "Default");
    model.study("std8").feature("eig").set("storefact", false);
    model.study("std8").feature("eig").set("geometricNonlinearity", true);
    model.study("std8").feature("eig").set("outputmap", new String[]{});
    model.study("std8").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std8").feature("eig").set("ngenAUX", "1");
    model.study("std8").feature("eig").set("goalngenAUX", "1");
    model.study("std8").feature("eig").set("ngenAUX", "1");
    model.study("std8").feature("eig").set("goalngenAUX", "1");
    model.study("std8").feature("eig").setSolveFor("/physics/solid", true);
    model.study("std8").label("\u7814\u7a76 8\uff0c\u60ac\u81c2\u6881\uff0cdT=30000");
    model.study("std8").setGenPlots(false);
    model.study("std8").feature("stat").set("geometricNonlinearity", false);
    model.study("std8").feature("stat").set("useadvanceddisable", true);
    model.study("std8").feature("stat").set("disabledphysics", new String[]{"solid/fix2"});
    model.study("std8").feature("eig").set("neigsactive", true);
    model.study("std8").feature("eig").set("neigs", 10);
    model.study("std8").feature("eig").set("shift", "10000[Hz]");
    model.study("std8").feature("eig").set("useadvanceddisable", true);
    model.study("std8").feature("eig").set("disabledphysics", new String[]{"solid/fix2"});
    model.study("std8").create("param", "Parametric");
    model.study("std8").feature("param").setIndex("pname", "a0", 0);
    model.study("std8").feature("param").setIndex("plistarr", "", 0);
    model.study("std8").feature("param").setIndex("punit", "m", 0);
    model.study("std8").feature("param").setIndex("pname", "a0", 0);
    model.study("std8").feature("param").setIndex("plistarr", "", 0);
    model.study("std8").feature("param").setIndex("punit", "m", 0);
    model.study("std8").feature("param").setIndex("pname", "dT", 0);
    model.study("std8").feature("param").setIndex("plistarr", 30000, 0);
    model.study("std8").showAutoSequences("all");

    model.sol("sol41").feature("e1").set("rtol", 1.0E-30);
    model.sol("sol41").feature("e1").set("eigvfunscale", "average");

    model.study("std8").createAutoSequences("all");

    model.sol().create("sol43");
    model.sol("sol43").study("std8");
    model.sol("sol43").label("\u53c2\u6570\u5316\u89e3 5");

    model.batch("p5").feature("so1").set("psol", "sol43");
    model.batch("p5").run("compute");

    model.study().create("std9");
    model.study("std9").create("eig", "Eigenfrequency");
    model.study("std9").feature("eig").set("plotgroup", "Default");
    model.study("std9").feature("eig").set("storefact", false);
    model.study("std9").feature("eig").set("solnum", "auto");
    model.study("std9").feature("eig").set("notsolnum", "auto");
    model.study("std9").feature("eig").set("outputmap", new String[]{});
    model.study("std9").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std9").feature("eig").set("ngenAUX", "1");
    model.study("std9").feature("eig").set("goalngenAUX", "1");
    model.study("std9").feature("eig").set("ngenAUX", "1");
    model.study("std9").feature("eig").set("goalngenAUX", "1");
    model.study("std9").feature("eig").setSolveFor("/physics/solid", true);
    model.study("std9").feature("eig").set("neigsactive", true);
    model.study("std9").feature("eig").set("neigs", 10);
    model.study("std9").feature("eig").set("shift", "10000[Hz]");
    model.study("std9").feature("eig").set("useadvanceddisable", true);
    model.study("std9").feature("eig").set("disabledphysics", new String[]{"solid/fix2"});
    model.study("std9")
         .label("\u7814\u7a76 9\uff0c\u60ac\u81c2\u6881\uff0c\u4fee\u6539\u7684\u51e0\u4f55\u5f62\u72b6\u548c\u5bc6\u5ea6");
    model.study("std9").setGenPlots(false);
    model.study("std9").showAutoSequences("all");

    model.sol("sol45").feature("e1").set("rtol", 1.0E-30);
    model.sol("sol45").feature("e1").set("eigvfunscale", "average");

    model.study("std9").create("param", "Parametric");
    model.study("std9").feature("param").setIndex("pname", "a0", 0);
    model.study("std9").feature("param").setIndex("plistarr", "", 0);
    model.study("std9").feature("param").setIndex("punit", "m", 0);
    model.study("std9").feature("param").setIndex("pname", "a0", 0);
    model.study("std9").feature("param").setIndex("plistarr", "", 0);
    model.study("std9").feature("param").setIndex("punit", "m", 0);
    model.study("std9").feature("param").setIndex("pname", "L0", 0);
    model.study("std9").feature("param").setIndex("plistarr", 13, 0);
    model.study("std9").feature("param").setIndex("pname", "a0", 1);
    model.study("std9").feature("param").setIndex("plistarr", "", 1);
    model.study("std9").feature("param").setIndex("punit", "m", 1);
    model.study("std9").feature("param").setIndex("pname", "a0", 1);
    model.study("std9").feature("param").setIndex("plistarr", "", 1);
    model.study("std9").feature("param").setIndex("punit", "m", 1);
    model.study("std9").feature("param").setIndex("punit", "mm", 0);
    model.study("std9").feature("param").setIndex("plistarr", 1.6, 1);
    model.study("std9").feature("param").setIndex("punit", "mm", 1);
    model.study("std9").feature("param").setIndex("pname", "alpha_x", 2);
    model.study("std9").feature("param").setIndex("plistarr", "", 2);
    model.study("std9").feature("param").setIndex("punit", "1/K", 2);
    model.study("std9").feature("param").setIndex("pname", "alpha_x", 2);
    model.study("std9").feature("param").setIndex("plistarr", "", 2);
    model.study("std9").feature("param").setIndex("punit", "1/K", 2);
    model.study("std9").feature("param").setIndex("pname", "b0", 2);
    model.study("std9").feature("param").setIndex("plistarr", 0.95, 2);
    model.study("std9").feature("param").setIndex("punit", "mm", 2);
    model.study("std9").feature("param").setIndex("pname", "alpha_x", 3);
    model.study("std9").feature("param").setIndex("plistarr", "", 3);
    model.study("std9").feature("param").setIndex("punit", "1/K", 3);
    model.study("std9").feature("param").setIndex("pname", "alpha_x", 3);
    model.study("std9").feature("param").setIndex("plistarr", "", 3);
    model.study("std9").feature("param").setIndex("punit", "1/K", 3);
    model.study("std9").feature("param").setIndex("pname", "rho0", 3);
    model.study("std9").feature("param").setIndex("plistarr", "1000/(1.3*1.6*1.9)", 3);
    model.study("std9").createAutoSequences("all");

    model.sol().create("sol46");
    model.sol("sol46").study("std9");
    model.sol("sol46").label("\u53c2\u6570\u5316\u89e3 6");

    model.batch("p6").feature("so1").set("psol", "sol46");
    model.batch("p6").run("compute");

    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").run();
    model.result("pg9").set("data", "dset18");
    model.result("pg9").create("vol1", "Volume");
    model.result("pg9").feature("vol1").create("def1", "Deform");
    model.result("pg9").run();
    model.result("pg9").feature("vol1").feature("def1").set("scaleactive", true);
    model.result("pg9").feature("vol1").feature("def1").set("scale", 1);
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg9").set("titletype", "none");
    model.result("pg9").set("edges", false);
    model.result("pg9").set("plotarrayenable", true);
    model.result("pg9").set("arrayaxis", "y");
    model.result("pg9").set("relpadding", 1.5);
    model.result("pg9").feature("vol1").set("arraydim", "1");
    model.result("pg9").run();
    model.result("pg9").feature().duplicate("vol2", "vol1");
    model.result("pg9").feature("vol2").set("arraydim", "1");
    model.result("pg9").run();
    model.result("pg9").feature("vol2").set("data", "dset20");
    model.result("pg9").run();
    model.result("pg9").feature("vol2").feature().remove("def1");
    model.result("pg9").feature("vol2").set("arraydim", "1");
    model.result("pg9").run();
    model.result("pg9").feature("vol2").set("expr", "1");
    model.result("pg9").feature("vol2").set("coloring", "uniform");
    model.result("pg9").feature("vol2").set("color", "gray");
    model.result("pg9").run();
    model.result("pg9").create("line1", "Line");
    model.result("pg9").feature("line1").set("arraydim", "1");
    model.result("pg9").feature("line1").set("manualindexing", true);
    model.result("pg9").feature("line1").set("expr", "1");
    model.result("pg9").feature("line1").set("coloring", "uniform");
    model.result("pg9").feature("line1").set("color", "black");
    model.result("pg9").run();
    model.result("pg9").run();
    model.result().move("pg9", 7);
    model.result("pg9").label("\u70ed\u81a8\u80c0 vs. \u4fee\u6b63\u51e0\u4f55\u5f62\u72b6");
    model.result("pg9").feature("vol1").set("arraydim", "1");
    model.result("pg9").run();
    model.result("pg9").feature("vol1").label("\u70ed\u81a8\u80c0");
    model.result("pg9").feature("vol2").set("arraydim", "1");
    model.result("pg9").run();
    model.result("pg9").feature("vol2").label("\u4fee\u6b63\u51e0\u4f55\u5f62\u72b6");
    model.result("pg9").feature("line1").set("arraydim", "1");
    model.result("pg9").run();
    model.result("pg9").feature("line1").label("\u70ed\u81a8\u80c0\uff0c\u672a\u53d8\u5f62");
    model.result("pg9").run();
    model.result("pg9").set("view", "view4");
    model.result().evaluationGroup().create("eg3", "EvaluationGroup");

    model.nodeGroup("grp2").add("evaluationgroup", "eg3");

    model.result().evaluationGroup("eg3").label("dT=30000[K] vs. \u4fee\u6b63\u51e0\u4f55\u5f62\u72b6");
    model.result().evaluationGroup("eg3").set("data", "dset19");
    model.result().evaluationGroup("eg3").set("concatenation", "vertical");
    model.result().evaluationGroup("eg3").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg3").feature("gev1").set("data", "dset17");
    model.result().evaluationGroup("eg3").feature("gev1").setIndex("looplevelinput", "first", 0);
    model.result().evaluationGroup("eg3").feature("gev1").label("\u4e00\u9636\u5f2f\u66f2 (z)");
    model.result().evaluationGroup("eg3").feature("gev1")
         .setIndex("expr", "freq/withsol('sol45',freq,setind(lambda,1))", 0);
    model.result().evaluationGroup("eg3").feature().duplicate("gev2", "gev1");
    model.result().evaluationGroup("eg3").feature("gev2").setIndex("looplevelinput", "manualindices", 0);
    model.result().evaluationGroup("eg3").feature("gev2").setIndex("looplevelindices", 2, 0);
    model.result().evaluationGroup("eg3").feature("gev2")
         .setIndex("expr", "freq/withsol('sol45',freq,setind(lambda,2))", 0);
    model.result().evaluationGroup("eg3").feature("gev2").label("\u4e00\u9636\u5f2f\u66f2 (y)");
    model.result().evaluationGroup("eg3").feature().duplicate("gev3", "gev2");
    model.result().evaluationGroup("eg3").feature("gev3").label("\u4e00\u9636\u626d\u8f6c");
    model.result().evaluationGroup("eg3").feature("gev3").setIndex("looplevelindices", 5, 0);
    model.result().evaluationGroup("eg3").feature("gev3")
         .setIndex("expr", "freq/withsol('sol45',freq,setind(lambda,5))", 0);
    model.result().evaluationGroup("eg3").feature().duplicate("gev4", "gev3");
    model.result().evaluationGroup("eg3").feature("gev4").label("\u4e00\u9636\u8f74\u5411");
    model.result().evaluationGroup("eg3").feature("gev4").setIndex("looplevelindices", 7, 0);
    model.result().evaluationGroup("eg3").feature("gev4")
         .setIndex("expr", "freq/withsol('sol45',freq,setind(lambda,7))", 0);
    model.result().evaluationGroup("eg3").run();
    model.result("pg2").run();

    model.title("\u6e29\u5ea6\u53d8\u5316\u5f15\u8d77\u7684\u7279\u5f81\u9891\u7387\u504f\u79fb");

    model
         .description("\u672c\u4f8b\u63a2\u8ba8\u4e86\u6e29\u5ea6\u53d8\u5316\u5f15\u8d77\u7684\u56fa\u6709\u9891\u7387\u7684\u504f\u79fb\uff0c\u5176\u4e2d\u901a\u8fc7\u4e00\u4e2a\u7814\u7a76\u5206\u6790\u4e24\u7aef\u56fa\u5b9a\u7684\u53cc\u5939\u7d27\u6881\uff0c\u53e6\u4e00\u4e2a\u7814\u7a76\u5219\u5206\u6790\u4ec5\u4e00\u7aef\u56fa\u5b9a\u7684\u60ac\u81c2\u6881\u3002\u672c\u4f8b\u5206\u6790\u4e0e\u5e94\u529b\u521a\u5316\u3001\u5c3a\u5bf8\u53d8\u5316\u3001\u7ea6\u675f\u81a8\u80c0\u548c\u6e29\u5ea6\u76f8\u5173\u6750\u6599\u5c5e\u6027\u6709\u5173\u7684\u5404\u79cd\u6548\u5e94\u3002\u7ed3\u679c\u663e\u793a\uff0c\u5939\u7d27\u6881\u4e3b\u8981\u53d7\u5e94\u529b\u8f6f\u5316\u7684\u5f71\u54cd\uff0c\u7ea6\u675f\u81a8\u80c0\u5728\u6b64\u8fc7\u7a0b\u4e2d\u4ea7\u751f\u4e86\u538b\u5e94\u529b\u3002\u53e6\u4e00\u65b9\u9762\uff0c\u60ac\u81c2\u6881\u663e\u793a\u51fa\u4e0e\u70ed\u81a8\u80c0\u5f15\u8d77\u7684\u5fae\u5c0f\u51e0\u4f55\u53d8\u5316\u9ad8\u5ea6\u4e00\u81f4\u7684\u89e3\u6790\u503c\u62df\u5408\u5ea6\u3002\u5bf9\u4e8e\u540e\u4e00\u79cd\u60c5\u51b5\uff0c\u6768\u6c0f\u6a21\u91cf\u7684\u6e29\u5ea6\u4f9d\u5b58\u6027\u88ab\u8bc1\u660e\u662f\u4e00\u4e2a\u91cd\u8981\u56e0\u7d20\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();
    model.sol("sol10").clearSolutionData();
    model.sol("sol11").clearSolutionData();
    model.sol("sol12").clearSolutionData();
    model.sol("sol13").clearSolutionData();
    model.sol("sol14").clearSolutionData();
    model.sol("sol15").clearSolutionData();
    model.sol("sol16").clearSolutionData();
    model.sol("sol17").clearSolutionData();
    model.sol("sol18").clearSolutionData();
    model.sol("sol19").clearSolutionData();
    model.sol("sol20").clearSolutionData();
    model.sol("sol21").clearSolutionData();
    model.sol("sol22").clearSolutionData();
    model.sol("sol23").clearSolutionData();
    model.sol("sol24").clearSolutionData();
    model.sol("sol25").clearSolutionData();
    model.sol("sol26").clearSolutionData();
    model.sol("sol27").clearSolutionData();
    model.sol("sol28").clearSolutionData();
    model.sol("sol29").clearSolutionData();
    model.sol("sol30").clearSolutionData();
    model.sol("sol31").clearSolutionData();
    model.sol("sol32").clearSolutionData();
    model.sol("sol33").clearSolutionData();
    model.sol("sol34").clearSolutionData();
    model.sol("sol35").clearSolutionData();
    model.sol("sol36").clearSolutionData();
    model.sol("sol37").clearSolutionData();
    model.sol("sol38").clearSolutionData();
    model.sol("sol39").clearSolutionData();
    model.sol("sol40").clearSolutionData();
    model.sol("sol41").clearSolutionData();
    model.sol("sol42").clearSolutionData();
    model.sol("sol43").clearSolutionData();
    model.sol("sol44").clearSolutionData();
    model.sol("sol45").clearSolutionData();
    model.sol("sol46").clearSolutionData();
    model.sol("sol47").clearSolutionData();

    model.label("frequency_shift_temperature_changes.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
