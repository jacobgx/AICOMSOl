/*
 * phononic_crystal.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:07 by COMSOL 6.3.0.290. */
public class phononic_crystal {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Elastic_Waves");

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

    model.geom().create("part1", "Part", 2);
    model.geom("part1").create("sq1", "Square");
    model.geom("part1").feature("sq1").setIndex("layer", 0.25, 0);
    model.geom("part1").feature("sq1").set("layerleft", true);
    model.geom("part1").feature("sq1").set("layerright", true);
    model.geom("part1").feature("sq1").set("layertop", true);
    model.geom("part1").run("sq1");
    model.geom("part1").create("del1", "Delete");
    model.geom("part1").feature("del1").selection("input").init(2);
    model.geom("part1").feature("del1").selection("input").set("sq1", 5);
    model.geom("part1").run("del1");
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").runPre("fin");

    model.material().create("mat1", "Common", "");
    model.material("mat1").propertyGroup("def").set("density", "");
    model.material("mat1").propertyGroup("def").set("poissonsratio", "");
    model.material("mat1").propertyGroup("def").set("youngsmodulus", "");
    model.material("mat1").propertyGroup("def").set("density", new String[]{"2700[kg/m^3]"});
    model.material("mat1").propertyGroup("def").set("poissonsratio", new String[]{"0.3"});
    model.material("mat1").propertyGroup("def").set("youngsmodulus", new String[]{"69.9[GPa]"});

    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("matlnk1", "Link");

    model.param().set("P", "1");
    model.param().descr("P", "Sweep parameter");

    model.component("comp1").physics("solid").create("pc1", "PeriodicCondition", 1);
    model.component("comp1").physics("solid").feature("pc1").selection().set(1, 3, 5, 22, 23, 24);
    model.component("comp1").physics("solid").feature("pc1").set("PeriodicType", "Floquet");
    model.component("comp1").physics("solid").feature("pc1").set("kFloquet", new String[]{"P*pi", "0", "0"});
    model.component("comp1").physics("solid").feature().duplicate("pc2", "pc1");
    model.component("comp1").physics("solid").feature("pc2").selection().set(2, 7, 9, 14, 16, 21);

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().set(1, 2, 3, 4, 5, 6, 7, 8);

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 6);

    model.study("std1").feature("eig").set("useparam", true);
    model.study("std1").feature("eig").setIndex("pname", "P", 0);
    model.study("std1").feature("eig").setIndex("plistarr", "", 0);
    model.study("std1").feature("eig").setIndex("punit", "", 0);
    model.study("std1").feature("eig").setIndex("pname", "P", 0);
    model.study("std1").feature("eig").setIndex("plistarr", "", 0);
    model.study("std1").feature("eig").setIndex("punit", "", 0);
    model.study("std1").feature("eig").setIndex("plistarr", "range(0,0.02,1)", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 6, 0);
    model.result("pg1").setIndex("looplevel", 51, 1);
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
    model.result().dataset().create("arr1", "Array2D");
    model.result().dataset("arr1").set("fullsize", new int[]{20, 20});
    model.result().dataset("arr1").set("floquetper", true);
    model.result().dataset("arr1").set("wavevector", new String[]{"P*pi", "0"});
    model.result("pg1").run();
    model.result("pg1").set("data", "arr1");
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{1, 6});
    model.result("pg1").run();
    model.result("pg1").set("edges", false);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").setIndex("expr", "solid.freq", 0);
    model.result("pg2").feature("glob1").setIndex("unit", "kHz", 0);
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").feature("glob1").set("xdataexpr", "P");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").set("showlegends", false);
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").run();
    model.result("pg2").feature("glob1").set("linestyle", "none");
    model.result("pg2").feature("glob1").set("linemarker", "asterisk");
    model.result("pg2").feature("glob1").set("linewidth", 2);
    model.result("pg2").feature("glob1").create("col1", "Color");
    model.result("pg2").run();
    model.result("pg2").feature("glob1").feature("col1").set("expr", "intop1(real(u)^2)/intop1(real(solid.disp)^2)");
    model.result("pg2").feature("glob1").feature("col1").set("colortable", "GrayScale");
    model.result("pg2").feature("glob1").feature("col1").set("colortabletrans", "reverse");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("glob1").feature("col1").set("colortabletrans", "none");
    model.result("pg3").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").set("transpose", true);
    model.result().evaluationGroup("eg1").set("includeparameters", false);
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").set("data", "dset1");
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("looplevelinput", "manual", 1);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("looplevel", new int[]{2}, 1);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("looplevelinput", "first", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "2*pi*solid.freq/(P*pi/1[m])", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "Wave Speed, S", 0);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("expr", "(2*pi*solid.freq/(P*pi/1[m]))^2*2700[kg/m^2]*0.75", 1);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "Homogenized C66", 1);
    model.result().evaluationGroup("eg1").feature().duplicate("gev2", "gev1");
    model.result().evaluationGroup("eg1").feature("gev2").setIndex("looplevelinput", "manualindices", 0);
    model.result().evaluationGroup("eg1").feature("gev2").setIndex("looplevelindices", 2, 0);
    model.result().evaluationGroup("eg1").feature("gev2").setIndex("descr", "Wave Speed, P", 0);
    model.result().evaluationGroup("eg1").feature("gev2").setIndex("descr", "Homogenized C11", 1);
    model.result().evaluationGroup("eg1").feature("gev2").setIndex("expr", "2700[kg/m^3]*0.75", 2);
    model.result().evaluationGroup("eg1").feature("gev2").setIndex("descr", "Homogenized density", 2);
    model.result().evaluationGroup("eg1").run();

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 2);

    model.component("comp2").mesh().create("mesh2");

    model.component("comp2").geom("geom2").create("pi1", "PartInstance");
    model.component("comp2").geom("geom2").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp2").geom("geom2").feature("pi1").set("part", "part1");
    model.component("comp2").geom("geom2").run("pi1");

    model.component("comp2").physics().create("solid2", "SolidMechanics", "geom2");

    model.study("std1").feature("eig").setSolveFor("/physics/solid2", true);

    model.component("comp2").geom("geom2").run();

    model.component("comp2").variable().create("var1");
    model.component("comp2").variable("var1").set("Omega", "2*pi*freq");
    model.component("comp2").variable("var1").descr("Omega", "Angular frequency");
    model.component("comp2").variable("var1").set("kP", "Omega/solid2.cp");
    model.component("comp2").variable("var1").descr("kP", "P Wave wavenumber");
    model.component("comp2").variable("var1").set("uP", "exp(-1i*kP*x+1i*phase)[m]");
    model.component("comp2").variable("var1").descr("uP", "P Wave: u field");
    model.component("comp2").variable("var1").set("vP", "0[m]");
    model.component("comp2").variable("var1").descr("vP", "P Wave: v field");
    model.component("comp2").variable("var1").set("eps11P", "d(uP, x)");
    model.component("comp2").variable("var1").descr("eps11P", "Incident P wave: strain tensor, 11 component");
    model.component("comp2").variable("var1").set("eps22P", "d(vP, y)");
    model.component("comp2").variable("var1").descr("eps22P", "Incident P wave: strain tensor, 22 component");
    model.component("comp2").variable("var1").set("eps12P", "0.5*(d(uP,y)+d(vP,x))");
    model.component("comp2").variable("var1").descr("eps12P", "Incident P wave: strain tensor, 12 component");
    model.component("comp2").variable("var1")
         .set("s11P", "(solid2.lambLame+2*solid2.muLame)*eps11P+solid2.lambLame*eps22P");
    model.component("comp2").variable("var1").descr("s11P", "Incident P wave: stress tensor, 11 component");
    model.component("comp2").variable("var1")
         .set("s22P", "solid2.lambLame*eps11P+(solid2.lambLame+2*solid2.muLame)*eps22P");
    model.component("comp2").variable("var1").descr("s22P", "Incident P wave: stress tensor, 22 component");
    model.component("comp2").variable("var1").set("s12P", "2*solid2.muLame*eps12P");
    model.component("comp2").variable("var1").descr("s12P", "Incident P wave: stress tensor, 12 component");
    model.component("comp2").variable("var1").set("I1P", "0.5*real(-s11P*conj(1i*Omega*uP)-s12P*conj(1i*Omega*vP))");
    model.component("comp2").variable("var1").descr("I1P", "Incident P wave: mechanical energy flux, 1 component");
    model.component("comp2").variable("var1").set("I2P", "0.5*real(-s12P*conj(1i*Omega*uP)-s22P*conj(1i*Omega*vP))");
    model.component("comp2").variable("var1").descr("I2P", "Incident P wave: mechanical energy flux, 2 component");

    model.component("comp2").physics("solid2").create("pc1", "PeriodicCondition", 1);
    model.component("comp2").physics("solid2").feature("pc1").selection().set(2, 7, 9, 14, 16, 21);
    model.component("comp2").physics("solid2").create("bndl1", "BoundaryLoad", 1);
    model.component("comp2").physics("solid2").feature("bndl1").selection().set(10, 11, 13, 17);
    model.component("comp2").physics("solid2").feature("bndl1")
         .set("forceReferenceArea", new String[]{"-(s11P*solid2.nx+s12P*solid2.ny)", "-(s12P*solid2.nx+s22P*solid2.ny)", "0"});

    model.component("comp2").geom("geom2").create("arr1", "Array");
    model.component("comp2").geom("geom2").feature("arr1").set("fullsize", new int[]{10, 1});
    model.component("comp2").geom("geom2").feature("arr1").set("displ", new int[]{1, 0});
    model.component("comp2").geom("geom2").feature("arr1").selection("input").set("pi1");
    model.component("comp2").geom("geom2").run("arr1");
    model.component("comp2").geom("geom2").create("r1", "Rectangle");
    model.component("comp2").geom("geom2").feature("r1").set("size", new int[]{10, 1});
    model.component("comp2").geom("geom2").feature("r1").set("pos", new int[]{-10, 0});
    model.component("comp2").geom("geom2").feature("r1").setIndex("layer", 1, 0);
    model.component("comp2").geom("geom2").feature("r1").set("layerleft", true);
    model.component("comp2").geom("geom2").feature("r1").set("layerbottom", false);
    model.component("comp2").geom("geom2").run("r1");
    model.component("comp2").geom("geom2").feature().duplicate("r2", "r1");
    model.component("comp2").geom("geom2").feature("r2").set("pos", new int[]{10, 0});
    model.component("comp2").geom("geom2").feature("r2").set("layerleft", false);
    model.component("comp2").geom("geom2").feature("r2").set("layerright", true);
    model.component("comp2").geom("geom2").run("r2");

    model.component("comp2").coordSystem().create("pml1", "PML");

    model.component("comp2").geom("geom2").run();

    model.component("comp2").coordSystem("pml1").selection().set(1, 84);

    model.component("comp2").cpl().create("aveop1", "Average");
    model.component("comp2").cpl("aveop1").set("axisym", true);
    model.component("comp2").cpl("aveop1").selection().geom("geom2", 1);
    model.component("comp2").cpl("aveop1").selection().set(7, 9, 11);

    model.component("comp2").material().create("matlnk2", "Link");

    model.component("comp2").physics("solid2").feature("pc1").selection()
         .set(2, 3, 5, 6, 8, 13, 15, 20, 22, 27, 29, 34, 36, 41, 43, 48, 50, 55, 57, 62, 64, 69, 71, 76, 78, 83, 85, 90, 92, 97, 99, 104, 106, 111, 113, 118, 120, 125, 127, 132, 134, 139, 141, 146, 148, 153, 155, 160, 162, 167, 169, 174, 176, 181, 183, 188, 190, 195, 197, 202, 204, 209, 211, 216, 218, 221, 223, 224);

    model.component("comp2").mesh("mesh2").create("map1", "Map");
    model.component("comp2").mesh("mesh2").feature("map1").selection().geom("geom2", 2);
    model.component("comp2").mesh("mesh2").feature("map1").selection().set(1, 84);
    model.component("comp2").mesh("mesh2").create("ftri1", "FreeTri");
    model.component("comp2").mesh("mesh2").feature("size").set("hauto", 1);
    model.component("comp2").mesh("mesh2").feature("size").set("hmax", 0.1);
    model.component("comp2").mesh("mesh2").run();

    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").setSolveFor("/physics/solid", true);
    model.study("std2").feature("freq").setSolveFor("/physics/solid2", true);
    model.study("std1").feature("eig").set("useadvanceddisable", true);
    model.study("std1").feature("eig").setSolveFor("/physics/solid2", false);
    model.study("std1").feature("eig").set("disabledphysics", new String[]{"solid2"});
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("freq").set("plist", "range(100, 5, 2500 )");
    model.study("std2").feature("freq").set("useadvanceddisable", true);
    model.study("std2").feature("freq").setSolveFor("/physics/solid", false);
    model.study("std2").feature("freq").set("disabledphysics", new String[]{"solid"});
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().create("arr2", "Array2D");
    model.result().dataset("arr2").set("fullsize", new int[]{1, 5});
    model.result().dataset("arr2").set("data", "dset2");
    model.result().dataset("arr2").selection().geom("geom2", 2);
    model.result().dataset("arr2").selection().geom("geom2", 2);
    model.result().dataset("arr2").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84);
    model.result().dataset("arr2").selection().geom("geom2", 2);
    model.result().dataset("arr2").selection()
         .set(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83);
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").run();
    model.result("pg4").set("data", "arr2");
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("paramindicator", "freq = eval(freq) Hz");
    model.result("pg4").set("edges", false);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "d(u2+uP,x)+d(v2+vP,y)");
    model.result("pg4").feature("surf1").set("colortable", "WaveClassic");
    model.result("pg4").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").create("arws1", "ArrowSurface");
    model.result("pg4").feature("arws1").set("expr", new String[]{"u2+uP", "v2+vP"});
    model.result("pg4").feature("arws1").set("arrowlength", "logarithmic");
    model.result("pg4").feature("arws1").set("color", "black");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("surf1").set("expr", "-d(u2+uP,y)+d(v2+vP,x)");
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").set("data", "dset2");
    model.result("pg6").set("titletype", "manual");
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").setIndex("expr", "1-comp2.aveop1(solid2.IX*solid2.nx/I1P)", 0);
    model.result("pg6").feature("glob1").setIndex("descr", "Transmission Coefficient", 0);
    model.result("pg6").feature("glob1").set("xdataparamunit", "kHz");
    model.result("pg6").feature("glob1").set("linewidth", 2);
    model.result("pg6").feature("glob1").set("autosolution", false);
    model.result("pg6").feature("glob1").set("autodescr", false);
    model.result("pg6").feature("glob1").set("autoplotlabel", true);
    model.result("pg6").run();
    model.result("pg5").run();

    model.study().create("std3");
    model.study("std3").create("freq", "Frequency");
    model.study("std3").feature("freq").setSolveFor("/physics/solid", true);
    model.study("std3").feature("freq").setSolveFor("/physics/solid2", true);
    model.study("std3").setGenPlots(false);
    model.study("std3").feature("freq").set("plist", "range(100, 5, 2500)");
    model.study("std3").feature("freq").set("useadvanceddisable", true);
    model.study("std3").feature("freq").setSolveFor("/physics/solid", false);
    model.study("std3").feature("freq").set("disabledphysics", new String[]{"solid"});

    model.component("comp2").variable().create("var2");
    model.component("comp2").variable("var2").set("kS", "Omega/solid2.cs");
    model.component("comp2").variable("var2").descr("kS", "S Wave wavenumber");
    model.component("comp2").variable("var2").set("uS", "0[m]");
    model.component("comp2").variable("var2").descr("uS", "S Wave: u field");
    model.component("comp2").variable("var2").set("vS", "exp(-1i*kS*x+1i*phase)[m]");
    model.component("comp2").variable("var2").descr("vS", "S Wave: v field");
    model.component("comp2").variable("var2").set("eps11S", "d(uS, x)");
    model.component("comp2").variable("var2").descr("eps11S", "Incident S wave: strain tensor, 11 component");
    model.component("comp2").variable("var2").set("eps22S", "d(vS, y)");
    model.component("comp2").variable("var2").descr("eps22S", "Incident S wave: strain tensor, 22 component");
    model.component("comp2").variable("var2").set("eps12S", "0.5*(d(uS,y)+d(vS,x))");
    model.component("comp2").variable("var2").descr("eps12S", "Incident S wave: strain tensor, 12 component");
    model.component("comp2").variable("var2")
         .set("s11S", "(solid2.lambLame+2*solid2.muLame)*eps11S+solid2.lambLame*eps22S");
    model.component("comp2").variable("var2").descr("s11S", "Incident S wave: stress tensor, 11 component");
    model.component("comp2").variable("var2")
         .set("s22S", "solid2.lambLame*eps11S+(solid2.lambLame+2*solid2.muLame)*eps22S");
    model.component("comp2").variable("var2").descr("s22S", "Incident S wave: stress tensor, 22 component");
    model.component("comp2").variable("var2").set("s12S", "2*solid2.muLame*eps12S");
    model.component("comp2").variable("var2").descr("s12S", "Incident S wave: stress tensor, 12 component");
    model.component("comp2").variable("var2").set("I1S", "0.5*real(-s11S*conj(1i*Omega*uS)-s12S*conj(1i*Omega*vS))");
    model.component("comp2").variable("var2").descr("I1S", "Incident S wave: mechanical energy flux, 1 component");
    model.component("comp2").variable("var2").set("I2S", "0.5*real(-s12S*conj(1i*Omega*uS)-s22S*conj(1i*Omega*vS))");
    model.component("comp2").variable("var2").descr("I2S", "Incident S wave: mechanical energy flux, 2 component");

    model.component("comp2").physics("solid2").prop("cref").set("cref", "solid2.cs");
    model.component("comp2").physics("solid2").feature().duplicate("bndl2", "bndl1");
    model.component("comp2").physics("solid2").feature("bndl2")
         .set("forceReferenceArea", new String[]{"-(s11S*solid2.nx+s12S*solid2.ny)", "-(s12S*solid2.nx+s22S*solid2.ny)", "0"});

    model.study("std2").feature("freq").set("disabledphysics", new String[]{"solid", "solid2/bndl2"});
    model.study("std3").feature("freq").set("disabledphysics", new String[]{"solid", "solid2/bndl1"});
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().dataset().duplicate("arr3", "arr2");
    model.result().dataset("arr3").set("data", "dset3");
    model.result("pg4").run();
    model.result().duplicate("pg7", "pg4");
    model.result("pg7").run();
    model.result("pg7").set("data", "arr3");
    model.result("pg7").run();
    model.result("pg7").feature("surf1").set("expr", "d(u2+uS,x)+d(v2+vS,y)");
    model.result("pg7").run();
    model.result("pg7").feature("arws1").set("expr", new String[]{"u2+uS", "v2+vS"});
    model.result("pg7").run();
    model.result("pg5").run();
    model.result().duplicate("pg8", "pg5");
    model.result("pg8").run();
    model.result("pg8").set("data", "arr3");
    model.result("pg8").run();
    model.result("pg8").feature("surf1").set("expr", "-d(u2+uS,y)+d(v2+vS,x)");
    model.result("pg8").run();
    model.result("pg8").feature("arws1").set("expr", new String[]{"u2+uS", "v2+vS"});
    model.result("pg8").run();
    model.result("pg6").run();
    model.result("pg6").feature().duplicate("glob2", "glob1");
    model.result("pg6").run();
    model.result("pg6").feature("glob2").set("data", "dset3");
    model.result("pg6").feature("glob2").setIndex("expr", "1-comp2.aveop1(solid2.IX*solid2.nx/I1S)", 0);
    model.result("pg6").run();
    model.result("pg4").run();

    model.title("\u58f0\u5b50\u6676\u4f53");

    model
         .description("\u672c\u6a21\u578b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u6563\u5c04\u573a\u516c\u5f0f\u8ba1\u7b97 P \u548c S \u5e73\u9762\u5f39\u6027\u6ce2\u5728\u4f20\u64ad\u5230\u6709\u9650\u5c3a\u5bf8\u58f0\u5b50\u6676\u4f53\u65f6\u7684\u900f\u5c04\u7cfb\u6570\u3002\n\n\u5728\u4e0e P \u548c S \u6ce2\u5e26\u9699\u76f8\u5bf9\u5e94\u7684\u9891\u7387\u8303\u56f4\u5185\uff0c\u900f\u5c04\u7cfb\u6570\u8d8b\u8fd1\u4e8e\u96f6\uff0c\u8fd9\u4e0e\u65e8\u5728\u8ba1\u7b97\u8272\u6563\u5173\u7cfb\u7684\u521d\u6b65\u7814\u7a76\u9884\u6d4b\u7684\u4e00\u81f4\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    return model;
  }

  public static Model run2(Model model) {
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("phononic_crystal.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
