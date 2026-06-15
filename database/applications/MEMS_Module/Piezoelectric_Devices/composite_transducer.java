/*
 * composite_transducer.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:58 by COMSOL 6.3.0.290. */
public class composite_transducer {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\MEMS_Module\\Piezoelectric_Devices");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics("solid").create("pzm1", "PiezoelectricMaterialModel");
    model.component("comp1").physics("solid").feature("pzm1").selection().all();
    model.component("comp1").physics().create("es", "Electrostatics", "geom1");
    model.component("comp1").physics("es").create("ccnp1", "ChargeConservationPiezo");
    model.component("comp1").physics("es").feature("ccnp1").selection().all();

    model.component("comp1").multiphysics().create("pze1", "PiezoelectricEffect", 3);
    model.component("comp1").multiphysics("pze1").set("Solid_physics", "solid");
    model.component("comp1").multiphysics("pze1").set("Electrostatics_physics", "es");

    model.study().create("std1");
    model.study("std1").create("eig", "Eigenfrequency");
    model.study("std1").feature("eig").set("shift", "1[Hz]");
    model.study("std1").feature("eig").set("chkeigregion", true);
    model.study("std1").feature("eig").set("storefact", false);
    model.study("std1").feature("eig").set("linpsolnum", "auto");
    model.study("std1").feature("eig").set("solnum", "auto");
    model.study("std1").feature("eig").set("notsolnum", "auto");
    model.study("std1").feature("eig").set("outputmap", new String[]{});
    model.study("std1").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").setSolveFor("/physics/solid", true);
    model.study("std1").feature("eig").setSolveFor("/physics/es", true);
    model.study("std1").feature("eig").setSolveFor("/multiphysics/pze1", true);

    model.component("comp1").common().create("mpf1", "ParticipationFactors");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", 27.5);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("angle", 10);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", 5, 0);
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", 5.275, 1);
    model.component("comp1").geom("geom1").feature("ext1").set("displ", new String[][]{{"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext1").set("scale", new String[][]{{"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext1").set("twist", new String[]{"0", "0"});
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", 15.275, 2);
    model.component("comp1").geom("geom1").feature("ext1")
         .set("displ", new String[][]{{"0", "0"}, {"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext1")
         .set("scale", new String[][]{{"1", "1"}, {"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext1").set("twist", new String[]{"0", "0", "0"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("solid").feature("pzm1").selection().set(1);
    model.component("comp1").physics("es").selection().set(1);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("Nepec 6");
    model.component("comp1").material("mat1").selection().set(1);
    model.component("comp1").material("mat1").propertyGroup()
         .create("StressCharge", "StressCharge", "Stress-charge_form");
    model.component("comp1").material("mat1").propertyGroup("StressCharge")
         .set("cE", new String[]{"128[GPa]", "68[GPa]", "128[GPa]", "66[GPa]", "66[GPa]", "110[GPa]", "0", "0", "0", "21[GPa]", 
         "0", "0", "0", "0", "21[GPa]", "0", "0", "0", "0", "0", 
         "21[GPa]"});
    model.component("comp1").material("mat1").propertyGroup("StressCharge")
         .set("eES", new String[]{"0", "0", "-6.1", "0", "0", "-6.1", "0", "0", "15.7", "0", 
         "0", "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp1").material("mat1").propertyGroup("StressCharge").set("epsilonrS", new String[]{"993.53"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"7730"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u7c98\u7ed3\u5c42");
    model.component("comp1").material("mat2").selection().set(2);
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", new String[]{"10[GPa]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", new String[]{"0.38"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"1700"});
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").label("\u94dd");
    model.component("comp1").material("mat3").selection().set(3);
    model.component("comp1").material("mat3").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("E", new String[]{"70.3[GPa]"});
    model.component("comp1").material("mat3").propertyGroup("Enu").set("nu", new String[]{"0.345"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", new String[]{"2690"});

    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 2);
    model.component("comp1").physics("solid").feature("sym1").selection().set(1, 2, 3, 4, 5, 7, 8);
    model.component("comp1").physics("es").create("term1", "Terminal", 2);
    model.component("comp1").physics("es").feature("term1").selection().set(6);
    model.component("comp1").physics("es").feature("term1").set("TerminalType", "Voltage");
    model.component("comp1").physics("es").feature("term1").set("V0", 0.5);
    model.component("comp1").physics("es").create("gnd1", "Ground", 2);
    model.component("comp1").physics("es").feature("gnd1").selection().set(3);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("B", "imag(es.Y11)*36/2");
    model.component("comp1").variable("var1").descr("B", "\u7535\u7eb3");

    model.component("comp1").mesh("mesh1").autoMeshSize(3);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(3);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("dis1").selection().set(2, 3);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("dis1").set("elemcount", 20);
    model.component("comp1").mesh("mesh1").run("ftri1");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
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
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u7535\u52bf (es)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("solutionparams", "parent");
    model.result("pg2").feature("mslc1").set("expr", "V");
    model.result("pg2").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("mslc1").set("xcoord", "es.CPx");
    model.result("pg2").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("mslc1").set("ycoord", "es.CPy");
    model.result("pg2").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("mslc1").set("zcoord", "es.CPz");
    model.result("pg2").feature("mslc1").set("colortable", "Dipole");
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("data", "parent");
    model.result("pg2").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg2").feature("strmsl1").set("expr", new String[]{"es.Ex", "es.Ey", "es.Ez"});
    model.result("pg2").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("strmsl1").set("xcoord", "es.CPx");
    model.result("pg2").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("strmsl1").set("ycoord", "es.CPy");
    model.result("pg2").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("strmsl1").set("zcoord", "es.CPz");
    model.result("pg2").feature("strmsl1").set("titletype", "none");
    model.result("pg2").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg2").feature("strmsl1").set("udist", 0.02);
    model.result("pg2").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg2").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("inheritcolor", false);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("data", "parent");
    model.result("pg2").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg2").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg2").feature("strmsl1").feature("col1").set("expr", "V");
    model.result("pg2").feature("strmsl1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg2").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg2").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg2").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u7535\u573a (es)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").feature().create("mslc1", "Multislice");
    model.result("pg3").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg3").feature("mslc1").set("solutionparams", "parent");
    model.result("pg3").feature("mslc1").set("expr", "es.normE");
    model.result("pg3").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg3").feature("mslc1").set("xcoord", "es.CPx");
    model.result("pg3").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg3").feature("mslc1").set("ycoord", "es.CPy");
    model.result("pg3").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg3").feature("mslc1").set("zcoord", "es.CPz");
    model.result("pg3").feature("mslc1").set("colortable", "Prism");
    model.result("pg3").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg3").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg3").feature("mslc1").set("data", "parent");
    model.result("pg3").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg3").feature("strmsl1").set("expr", new String[]{"es.Ex", "es.Ey", "es.Ez"});
    model.result("pg3").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg3").feature("strmsl1").set("xcoord", "es.CPx");
    model.result("pg3").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg3").feature("strmsl1").set("ycoord", "es.CPy");
    model.result("pg3").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg3").feature("strmsl1").set("zcoord", "es.CPz");
    model.result("pg3").feature("strmsl1").set("titletype", "none");
    model.result("pg3").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg3").feature("strmsl1").set("udist", 0.02);
    model.result("pg3").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg3").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("inheritcolor", false);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("data", "parent");
    model.result("pg3").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg3").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg3").feature("strmsl1").feature("col1").set("expr", "es.normE");
    model.result("pg3").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg3").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg3").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg3").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg3").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
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
    model.result("pg1").feature("surf1").set("descr", "\u4f4d\u79fb\u573a\uff0cZ \u5206\u91cf");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature().remove("mslc1");
    model.result("pg2").feature().remove("strmsl1");
    model.result("pg2").run();
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "V");
    model.result("pg2").feature("surf1").set("descr", "\u7535\u52bf");
    model.result("pg2").run();

    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").setSolveFor("/physics/solid", true);
    model.study("std2").feature("freq").setSolveFor("/physics/es", true);
    model.study("std2").feature("freq").setSolveFor("/multiphysics/pze1", true);
    model.study("std2").feature("freq").set("plist", "range(20[kHz],2[kHz],106[kHz])");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevel", 44, 0);
    model.result("pg4").label("\u5e94\u529b (solid)");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").create("vol1", "Volume");
    model.result("pg4").feature("vol1").set("expr", new String[]{"solid.misesGp_peak"});
    model.result("pg4").feature("vol1").set("threshold", "manual");
    model.result("pg4").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg4").feature("vol1").set("colortable", "Rainbow");
    model.result("pg4").feature("vol1").set("colortabletrans", "none");
    model.result("pg4").feature("vol1").set("colorscalemode", "linear");
    model.result("pg4").feature("vol1").set("resolution", "custom");
    model.result("pg4").feature("vol1").set("refine", 2);
    model.result("pg4").feature("vol1").set("colortable", "Prism");
    model.result("pg4").feature("vol1").create("def", "Deform");
    model.result("pg4").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg4").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u7535\u52bf (es) 1");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").setIndex("looplevel", 44, 0);
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").set("showlegendsmaxmin", true);
    model.result("pg5").feature().create("mslc1", "Multislice");
    model.result("pg5").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg5").feature("mslc1").set("solutionparams", "parent");
    model.result("pg5").feature("mslc1").set("expr", "V");
    model.result("pg5").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg5").feature("mslc1").set("xcoord", "es.CPx");
    model.result("pg5").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg5").feature("mslc1").set("ycoord", "es.CPy");
    model.result("pg5").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg5").feature("mslc1").set("zcoord", "es.CPz");
    model.result("pg5").feature("mslc1").set("colortable", "Dipole");
    model.result("pg5").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg5").feature("mslc1").set("data", "parent");
    model.result("pg5").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg5").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg5").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg5").feature("strmsl1").set("expr", new String[]{"es.Ex", "es.Ey", "es.Ez"});
    model.result("pg5").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg5").feature("strmsl1").set("xcoord", "es.CPx");
    model.result("pg5").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg5").feature("strmsl1").set("ycoord", "es.CPy");
    model.result("pg5").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg5").feature("strmsl1").set("zcoord", "es.CPz");
    model.result("pg5").feature("strmsl1").set("titletype", "none");
    model.result("pg5").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg5").feature("strmsl1").set("udist", 0.02);
    model.result("pg5").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg5").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg5").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("strmsl1").set("inheritcolor", false);
    model.result("pg5").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg5").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg5").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg5").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg5").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("strmsl1").set("data", "parent");
    model.result("pg5").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg5").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg5").feature("strmsl1").feature("col1").set("expr", "V");
    model.result("pg5").feature("strmsl1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg5").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg5").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg5").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").label("\u7535\u573a (es) 1");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").setIndex("looplevel", 44, 0);
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").set("showlegendsmaxmin", true);
    model.result("pg6").feature().create("mslc1", "Multislice");
    model.result("pg6").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg6").feature("mslc1").set("solutionparams", "parent");
    model.result("pg6").feature("mslc1").set("expr", "es.normE");
    model.result("pg6").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg6").feature("mslc1").set("xcoord", "es.CPx");
    model.result("pg6").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg6").feature("mslc1").set("ycoord", "es.CPy");
    model.result("pg6").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg6").feature("mslc1").set("zcoord", "es.CPz");
    model.result("pg6").feature("mslc1").set("colortable", "Prism");
    model.result("pg6").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg6").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg6").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg6").feature("mslc1").set("data", "parent");
    model.result("pg6").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg6").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg6").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg6").feature("strmsl1").set("expr", new String[]{"es.Ex", "es.Ey", "es.Ez"});
    model.result("pg6").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg6").feature("strmsl1").set("xcoord", "es.CPx");
    model.result("pg6").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg6").feature("strmsl1").set("ycoord", "es.CPy");
    model.result("pg6").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg6").feature("strmsl1").set("zcoord", "es.CPz");
    model.result("pg6").feature("strmsl1").set("titletype", "none");
    model.result("pg6").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg6").feature("strmsl1").set("udist", 0.02);
    model.result("pg6").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg6").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg6").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("strmsl1").set("inheritcolor", false);

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg6").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg6").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg6").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg6").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg6").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("strmsl1").set("data", "parent");
    model.result("pg6").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg6").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg6").feature("strmsl1").feature("col1").set("expr", "es.normE");
    model.result("pg6").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg6").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg6").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg6").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg6").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg6").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature().remove("mslc1");
    model.result("pg5").feature().remove("strmsl1");
    model.result("pg5").run();
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "V");
    model.result("pg5").feature("surf1").set("descr", "\u7535\u52bf");
    model.result("pg5").run();
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").run();
    model.result("pg7").label("\u4f4d\u79fb");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").setIndex("looplevel", 44, 0);
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", "w");
    model.result("pg7").feature("surf1").set("descr", "\u4f4d\u79fb\u573a\uff0cZ \u5206\u91cf");
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").label("\u7535\u7eb3");
    model.result("pg8").set("data", "dset2");
    model.result("pg8").create("glob1", "Global");
    model.result("pg8").feature("glob1").set("markerpos", "datapoints");
    model.result("pg8").feature("glob1").set("linewidth", "preference");
    model.result("pg8").feature("glob1").setIndex("expr", "B", 0);
    model.result("pg8").feature("glob1").set("xdataparamunit", "kHz");
    model.result("pg8").run();

    model.title("\u590d\u5408\u538b\u7535\u6362\u80fd\u5668");

    model
         .description("\u672c\u4f8b\u5206\u6790\u4e86\u590d\u5408\u538b\u7535\u8d85\u58f0\u6362\u80fd\u5668\u3002\u4f9d\u6b21\u6267\u884c\u4e86\u7279\u5f81\u9891\u7387\u5206\u6790\u548c\u9891\u7387\u54cd\u5e94\u5206\u6790\uff0c\u4ece\u800c\u57fa\u4e8e\u6fc0\u52b1\u9891\u7387\u6765\u8ba1\u7b97\u8f93\u5165\u5bfc\u7eb3\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("composite_transducer.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
