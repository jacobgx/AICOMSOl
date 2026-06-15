/*
 * pyroelectric_detector.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:04 by COMSOL 6.3.0.290. */
public class pyroelectric_detector {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\MEMS_Module\\Sensors");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("es", "Electrostatics", "geom1");
    model.component("comp1").physics("es").create("ccnp1", "ChargeConservationPiezo");
    model.component("comp1").physics("es").feature("ccnp1").selection().all();
    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics("solid").create("pzm1", "PiezoelectricMaterialModel");
    model.component("comp1").physics("solid").feature("pzm1").selection().all();
    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");
    model.component("comp1").physics().create("cir", "Circuit", "geom1");

    model.component("comp1").multiphysics().create("pze1", "PiezoelectricEffect", 2);
    model.component("comp1").multiphysics("pze1").set("Solid_physics", "solid");
    model.component("comp1").multiphysics("pze1").set("Electrostatics_physics", "es");
    model.component("comp1").multiphysics().create("te1", "ThermalExpansion", 2);
    model.component("comp1").multiphysics("te1").set("Heat_physics", "ht");
    model.component("comp1").multiphysics("te1").set("Solid_physics", "solid");
    model.component("comp1").multiphysics("te1").selection().all();
    model.component("comp1").multiphysics().create("pye1", "Pyroelectricity", 2);
    model.component("comp1").multiphysics("pye1").set("Electrostatics_physics", "es");
    model.component("comp1").multiphysics("pye1").set("Heat_physics", "ht");
    model.component("comp1").multiphysics("pye1").selection().all();

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/es", true);
    model.study("std1").feature("time").setSolveFor("/physics/solid", true);
    model.study("std1").feature("time").setSolveFor("/physics/ht", true);
    model.study("std1").feature("time").setSolveFor("/physics/cir", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/pze1", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/te1", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/pye1", true);

    model.param().set("r_el", "1.4[mm]");
    model.param().descr("r_el", "\u9876\u90e8\u7535\u6781\u534a\u5f84");
    model.param().set("r_s", "0.25[mm]");
    model.param().descr("r_s", "\u652f\u67b6\u5bbd\u5ea6");
    model.param().set("r_d", "1.5[mm]");
    model.param().descr("r_d", "\u6676\u4f53\u534a\u5f84");
    model.param().set("t_d", "0.025[mm]");
    model.param().descr("t_d", "\u6676\u4f53\u539a\u5ea6");
    model.param().set("t_s", "0.040[mm]");
    model.param().descr("t_s", "\u652f\u67b6\u539a\u5ea6");
    model.param().set("w_b", "2[mm]");
    model.param().descr("w_b", "\u6fc0\u5149\u675f\u5bbd\u5ea6");
    model.param().set("Qmax", "500[W/m^2]");
    model.param().descr("Qmax", "\u6700\u5927\u6fc0\u5149\u529f\u7387\u5bc6\u5ea6");
    model.param().set("pulse", "1[s]");
    model.param().descr("pulse", "\u6fc0\u5149\u8109\u51b2\u6301\u7eed\u65f6\u95f4");
    model.param().set("C_ext", "100[pF]");
    model.param().descr("C_ext", "C1 \u7684\u7535\u5bb9");
    model.param().set("R_ext", "1000[ohm]");
    model.param().descr("R_ext", "R1 \u7684\u7535\u963b");

    model.component("comp1").func().create("rect1", "Rectangle");
    model.component("comp1").func("rect1").set("lower", 0.2);
    model.component("comp1").func("rect1").set("upper", 1);
    model.component("comp1").func().create("an1", "Analytic");
    model.component("comp1").func("an1").set("expr", "exp(-((r^2)/(2*(10000)^2)))");
    model.component("comp1").func("an1").set("args", "r");
    model.component("comp1").func("an1").setIndex("argunit", "um", 0);

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("Flux", "Qmax*an1(r)*rect1(t/pulse)");
    model.component("comp1").variable("var1").descr("Flux", "\u529f\u7387\u5bc6\u5ea6\u5206\u5e03");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"r_d", "t_d"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"r_s", "t_s"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"r_d-r_s", "-t_s"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"r_el", "t_d"});
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").multiphysics("pye1").selection().set(1, 3);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("StrainCharge", "StrainCharge", "Strain-charge form");
    model.component("comp1").material("mat1").propertyGroup()
         .create("StressCharge", "StressCharge", "Stress-charge form");
    model.component("comp1").material("mat1").label("Lithium Niobate");
    model.component("comp1").material("mat1").set("family", "custom");
    model.component("comp1").material("mat1").set("customspecular", new double[]{0.7843137254901961, 1, 1});
    model.component("comp1").material("mat1").set("diffuse", "custom");
    model.component("comp1").material("mat1")
         .set("customdiffuse", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat1").set("ambient", "custom");
    model.component("comp1").material("mat1")
         .set("customambient", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat1").set("noise", true);
    model.component("comp1").material("mat1").set("fresnel", 0.9);
    model.component("comp1").material("mat1").set("roughness", 0.1);
    model.component("comp1").material("mat1").set("diffusewrap", 0);
    model.component("comp1").material("mat1").set("reflectance", 0);
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"43.6", "0", "0", "0", "43.6", "0", "0", "0", "29.16"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "4700[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("StrainCharge").label("Strain-charge form");
    model.component("comp1").material("mat1").propertyGroup("StrainCharge")
         .set("sE", new String[]{"5.78e-012[1/Pa]", "-1.01e-012[1/Pa]", "-1.47e-012[1/Pa]", "-1.02e-012[1/Pa]", "0[1/Pa]", "0[1/Pa]", "-1.01e-012[1/Pa]", "5.78e-012[1/Pa]", "-1.47e-012[1/Pa]", "1.02e-012[1/Pa]", 
         "0[1/Pa]", "0[1/Pa]", "-1.47e-012[1/Pa]", "-1.47e-012[1/Pa]", "5.02e-012[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "-1.02e-012[1/Pa]", "1.02e-012[1/Pa]", 
         "0[1/Pa]", "1.7e-011[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "1.7e-011[1/Pa]", "-2.04e-012[1/Pa]", 
         "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "-2.04e-012[1/Pa]", "1.36e-011[1/Pa]"});
    model.component("comp1").material("mat1").propertyGroup("StrainCharge")
         .set("dET", new String[]{"0[C/N]", "-2.1e-011[C/N]", "-1e-012[C/N]", "0[C/N]", "2.1e-011[C/N]", "-1e-012[C/N]", "0[C/N]", "0[C/N]", "6e-012[C/N]", "0[C/N]", 
         "6.8e-011[C/N]", "0[C/N]", "6.8e-011[C/N]", "0[C/N]", "0[C/N]", "-4.2e-011[C/N]", "0[C/N]", "0[C/N]"});
    model.component("comp1").material("mat1").propertyGroup("StrainCharge")
         .set("epsilonrT", new String[]{"84", "0", "0", "0", "84", "0", "0", "0", "30"});
    model.component("comp1").material("mat1").propertyGroup("StressCharge").label("Stress-charge form");
    model.component("comp1").material("mat1").propertyGroup("StressCharge")
         .set("cE", new String[]{"2.02897e+011[Pa]", "5.29177e+010[Pa]", "7.49098e+010[Pa]", "8.99874e+009[Pa]", "0[Pa]", "0[Pa]", "5.29177e+010[Pa]", "2.02897e+011[Pa]", "7.49098e+010[Pa]", "-8.99874e+009[Pa]", 
         "0[Pa]", "0[Pa]", "7.49098e+010[Pa]", "7.49098e+010[Pa]", "2.43075e+011[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "8.99874e+009[Pa]", "-8.99874e+009[Pa]", 
         "0[Pa]", "5.99034e+010[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "5.99018e+010[Pa]", "8.98526e+009[Pa]", 
         "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "8.98526e+009[Pa]", "7.48772e+010[Pa]"});
    model.component("comp1").material("mat1").propertyGroup("StressCharge")
         .set("eES", new String[]{"0[C/m^2]", "-2.53764[C/m^2]", "0.193644[C/m^2]", "0[C/m^2]", "2.53764[C/m^2]", "0.193644[C/m^2]", "0[C/m^2]", "0[C/m^2]", "1.30863[C/m^2]", "0[C/m^2]", 
         "3.69548[C/m^2]", "0[C/m^2]", "3.69594[C/m^2]", "0[C/m^2]", "0[C/m^2]", "-2.53384[C/m^2]", "0[C/m^2]", "0[C/m^2]"});
    model.component("comp1").material("mat1").propertyGroup("StressCharge")
         .set("epsilonrS", new String[]{"43.6", "0", "0", "0", "43.6", "0", "0", "0", "29.16"});
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalconductivity", new String[]{"4.2"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", new String[]{"628"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"6.5e-6", "6.5e-6", "14.8e-6"});
    model.component("comp1").material("mat1").propertyGroup().create("Pyroelectric", "Pyroelectric", "Pyroelectric");
    model.component("comp1").material("mat1").propertyGroup("Pyroelectric")
         .set("pET", new String[]{"0", "0", "-8.3e-5"});

    model.component("comp1").physics("es").feature("ccnp1").selection().set(1, 3);
    model.component("comp1").physics("es").create("ccns1", "ChargeConservationSolid", 2);
    model.component("comp1").physics("es").feature("ccns1").selection().set(2);
    model.component("comp1").physics("es").create("gnd1", "Ground", 1);
    model.component("comp1").physics("es").feature("gnd1").selection().set(2, 6, 8);
    model.component("comp1").physics("es").create("term1", "Terminal", 1);
    model.component("comp1").physics("es").feature("term1").selection().set(3);
    model.component("comp1").physics("es").feature("term1").set("TerminalType", "Circuit");
    model.component("comp1").physics("solid").feature("pzm1").selection().set(1, 3);
    model.component("comp1").physics("solid").create("fix1", "Fixed", 1);
    model.component("comp1").physics("solid").feature("fix1").selection().set(5);
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 1);
    model.component("comp1").physics("ht").feature("hf1").setIndex("materialType", "solid", 0);
    model.component("comp1").physics("ht").feature("hf1").set("q0_input", "Flux");
    model.component("comp1").physics("ht").feature("hf1").selection().set(3, 9);
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 1);
    model.component("comp1").physics("ht").feature("temp1").selection().set(5);
    model.component("comp1").physics("cir").create("R1", "Resistor", -1);
    model.component("comp1").physics("cir").feature("R1").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").feature("R1").set("R", "R_ext");
    model.component("comp1").physics("cir").create("C1", "Capacitor", -1);
    model.component("comp1").physics("cir").feature("C1").setIndex("Connections", 1, 0, 0);
    model.component("comp1").physics("cir").feature("C1").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").feature("C1").set("C", "C_ext");
    model.component("comp1").physics("cir").create("termI1", "ModelTerminalIV", -1);
    model.component("comp1").physics("cir").feature("termI1").set("Connections", 1);
    model.component("comp1").physics("cir").feature("termI1").set("V_src", "root.comp1.es.V0_1");

    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").label("Ag - Silver");
    model.component("comp1").material("mat2").set("family", "custom");
    model.component("comp1").material("mat2").set("customspecular", new double[]{0.7843137254901961, 1, 1});
    model.component("comp1").material("mat2").set("diffuse", "custom");
    model.component("comp1").material("mat2")
         .set("customdiffuse", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat2").set("ambient", "custom");
    model.component("comp1").material("mat2")
         .set("customambient", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat2").set("noise", true);
    model.component("comp1").material("mat2").set("fresnel", 0.9);
    model.component("comp1").material("mat2").set("roughness", 0.1);
    model.component("comp1").material("mat2").set("diffusewrap", 0);
    model.component("comp1").material("mat2").set("reflectance", 0);
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"61.6e6[S/m]", "0", "0", "0", "61.6e6[S/m]", "0", "0", "0", "61.6e6[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"18.9e-6[1/K]", "0", "0", "0", "18.9e-6[1/K]", "0", "0", "0", "18.9e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "235[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "10500[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"429[W/(m*K)]", "0", "0", "0", "429[W/(m*K)]", "0", "0", "0", "429[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "83e9[Pa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.37");
    model.component("comp1").material("mat2").selection().set(2);
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermittivity", new String[]{"1"});

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 1);
    model.component("comp1").mesh("mesh1").run("ftri1");

    model.study("std1").feature("time").set("tlist", "range(0,0.02,2)");
    model.study("std1").feature("time").label("\u77ac\u6001\uff0c\u5b8c\u6574\u6a21\u578b");
    model.study("std1").feature("time").set("useparam", true);
    model.study("std1").feature("time").setIndex("pname", "r_el", 0);
    model.study("std1").feature("time").setIndex("plistarr", "", 0);
    model.study("std1").feature("time").setIndex("punit", "m", 0);
    model.study("std1").feature("time").setIndex("pname", "r_el", 0);
    model.study("std1").feature("time").setIndex("plistarr", "", 0);
    model.study("std1").feature("time").setIndex("punit", "m", 0);
    model.study("std1").feature("time").setIndex("pname", "R_ext", 0);
    model.study("std1").feature("time").setIndex("plistarr", "0.1 5e6 5e7 1e9", 0);
    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/es", true);
    model.study("std2").feature("time").setSolveFor("/physics/solid", true);
    model.study("std2").feature("time").setSolveFor("/physics/ht", true);
    model.study("std2").feature("time").setSolveFor("/physics/cir", true);
    model.study("std2").feature("time").setSolveFor("/multiphysics/pze1", true);
    model.study("std2").feature("time").setSolveFor("/multiphysics/te1", true);
    model.study("std2").feature("time").setSolveFor("/multiphysics/pye1", true);
    model.study("std2").feature("time").label("\u77ac\u6001 - \u4ec5\u70ed\u91ca\u7535");
    model.study("std2").feature("time").set("tlist", "range(0,0.02,2)");
    model.study("std2").feature("time").setSolveFor("/physics/solid", false);
    model.study("std2").feature("time").setSolveFor("/multiphysics/pze1", false);
    model.study("std2").feature("time").setSolveFor("/multiphysics/te1", false);
    model.study("std2").feature("time").set("useparam", true);
    model.study("std2").feature("time").setIndex("pname", "r_el", 0);
    model.study("std2").feature("time").setIndex("plistarr", "", 0);
    model.study("std2").feature("time").setIndex("punit", "m", 0);
    model.study("std2").feature("time").setIndex("pname", "r_el", 0);
    model.study("std2").feature("time").setIndex("plistarr", "", 0);
    model.study("std2").feature("time").setIndex("punit", "m", 0);
    model.study("std2").feature("time").setIndex("pname", "R_ext", 0);
    model.study("std2").feature("time").setIndex("plistarr", "0.1 5e6 5e7 1e9", 0);
    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("\u6e29\u5ea6\u548c\u7535\u6d41\u5bc6\u5ea6\uff0c\u5b8c\u6574\u6a21\u578b");
    model.result("pg1").set("twoyaxes", true);
    model.result("pg1").create("ptgr1", "PointGraph");
    model.result("pg1").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg1").feature("ptgr1").set("linewidth", "preference");
    model.result("pg1").feature("ptgr1").selection().set(2);
    model.result("pg1").feature("ptgr1").set("expr", "T");
    model.result("pg1").feature("ptgr1").set("descractive", true);
    model.result("pg1").run();
    model.result("pg1").feature("ptgr1").label("\u6e29\u5ea6");
    model.result("pg1").run();
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("markerpos", "datapoints");
    model.result("pg1").feature("glob1").set("linewidth", "preference");
    model.result("pg1").feature("glob1").label("\u7535\u8def\u7535\u6d41");
    model.result("pg1").feature("glob1").set("plotonsecyaxis", true);
    model.result("pg1").feature("glob1").setIndex("expr", "cir.R1.i", 0);
    model.result("pg1").feature("glob1").setIndex("unit", "uA", 0);
    model.result("pg1").feature("glob1").setIndex("descr", "\u901a\u8fc7\u5668\u4ef6 R1 \u7684\u7535\u6d41", 0);
    model.result("pg1").feature("glob1").set("autodescr", false);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u7535\u538b\uff0c\u5b8c\u6574\u6a21\u578b");
    model.result("pg2").set("legendpos", "lowerright");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").set("expr", new String[]{"cir.R1.v"});
    model.result("pg2").feature("glob1")
         .set("descr", new String[]{"\u5668\u4ef6\u201cR1\u201d\u4e0a\u7684\u7535\u538b"});
    model.result("pg2").feature("glob1").set("unit", new String[]{"V"});
    model.result("pg2").feature("glob1").set("autodescr", false);
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u7535\u529f\u7387\uff0c\u5b8c\u6574\u6a21\u578b");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").setIndex("expr", "realdot(cir.R1.i,cir.R1.v)", 0);
    model.result("pg3").feature("glob1").setIndex("unit", "uW", 0);
    model.result("pg3").feature("glob1").setIndex("descr", "\u7535\u529f\u7387", 0);
    model.result("pg3").feature("glob1").set("autodescr", false);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u5b8c\u6574\u6a21\u578b vs. \u4ec5\u70ed\u91ca\u7535");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").label("\u7535\u529f\u7387\uff0c\u5b8c\u6574\u6a21\u578b");
    model.result("pg4").feature("glob1").set("data", "dset1");
    model.result("pg4").feature("glob1").setIndex("looplevelinput", "manual", 1);
    model.result("pg4").feature("glob1").setIndex("looplevel", new int[]{4}, 1);
    model.result("pg4").feature("glob1").setIndex("expr", "realdot(cir.R1.i,cir.R1.v)", 0);
    model.result("pg4").feature("glob1").setIndex("unit", "uW", 0);
    model.result("pg4").feature("glob1").setIndex("descr", "\u7535\u529f\u7387\uff0c\u5b8c\u6574\u6a21\u578b", 0);
    model.result("pg4").feature("glob1").set("autosolution", false);
    model.result("pg4").feature().duplicate("glob2", "glob1");
    model.result("pg4").run();
    model.result("pg4").feature("glob2").label("\u529f\u7387\uff0c\u4ec5\u70ed\u91ca\u7535");
    model.result("pg4").feature("glob2").set("data", "dset2");
    model.result("pg4").feature("glob2").setIndex("descr", "\u529f\u7387\uff0c\u4ec5\u70ed\u91ca\u7535", 0);
    model.result("pg4").run();
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").run();
    model.result("pg5").label("\u6e29\u5ea6\u5206\u5e03");
    model.result("pg5").setIndex("looplevel", 26, 0);
    model.result("pg5").create("vol1", "Volume");
    model.result("pg5").feature("vol1").set("expr", "T");
    model.result("pg5").feature("vol1").set("descractive", true);
    model.result("pg5").feature("vol1").set("colortable", "ThermalDark");
    model.result("pg5").run();

    model.title("\u70ed\u91ca\u7535\u63a2\u6d4b\u5668");

    model
         .description("\u8fd9\u4e2a\u4e8c\u7ef4\u8f74\u5bf9\u79f0\u6a21\u578b\u6f14\u793a\u4e86\u6d4b\u91cf\u6fc0\u5149\u80fd\u91cf\u7684\u4eea\u5668\u4e2d\u7684\u70ed\u91ca\u7535\u63a2\u6d4b\u5668\u7684\u5de5\u4f5c\u539f\u7406\u3002\u8fd9\u79cd\u63a2\u6d4b\u5668\u662f\u5c06\u4e00\u4e2a\u94cc\u9178\u9502 (LiNbO3) \u5706\u76d8\u5939\u5728\u4e24\u4e2a\u7535\u6781\u4e4b\u95f4\uff0c\u5e76\u8fde\u63a5\u5230\u5916\u90e8\u7535\u8def\uff0c\u7136\u540e\u5c06\u5706\u76d8\u5b89\u88c5\u5728\u4e00\u4e2a\u6709\u9650\u70ed\u4f20\u5bfc\u7684\u73af\u5f62\u5bfc\u70ed\u57ab\u4e0a\u3002\u5728\u5706\u76d8\u9876\u9762\u65bd\u52a0\u7684\u8109\u51b2\u80fd\u6d41\u8868\u793a\u5bf9\u7ea2\u5916\u6fc0\u5149\u7684\u5438\u6536\uff0c\u6a21\u578b\u4e2d\u4f7f\u7528\u201c\u538b\u7535\u548c\u70ed\u91ca\u7535\u201d\u591a\u7269\u7406\u573a\u63a5\u53e3\uff0c\u5176\u4e2d\u5305\u542b\u201c\u70ed\u91ca\u7535\u201d\u3001\u201c\u538b\u7535\u201d\u548c\u201c\u70ed\u81a8\u80c0\u201d\u8026\u5408\uff0c\u901a\u8fc7\u77ac\u6001\u7814\u7a76\u8ba1\u7b97\u5706\u76d8\u4e2d\u7684\u6e29\u5ea6\u53d8\u5316\u548c\u70ed\u91ca\u7535\u7535\u6d41\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("pyroelectric_detector.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
