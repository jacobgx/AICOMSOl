/*
 * frequency_selective_surface_csrr.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:38 by COMSOL 6.3.0.290. */
public class frequency_selective_surface_csrr {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Wave_Optics_Module\\Gratings_and_Metamaterials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ewfd", "ElectromagneticWavesFrequencyDomain", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").set("ftplistmethod", "manual");
    model.study("std1").feature("freq").set("solnum", "auto");
    model.study("std1").feature("freq").set("notsolnum", "auto");
    model.study("std1").feature("freq").set("outputmap", new String[]{});
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").setSolveFor("/physics/ewfd", true);
    model.study("std1").feature("freq").set("plist", "range(3.8[THz],0.1[THz],5.4[THz])");

    model.param().set("theta", "0[deg]");
    model.param().descr("theta", "\u4ef0\u89d2");

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new int[]{15, 15, 45});
    model.component("comp1").geom("geom1").feature("blk1").set("base", "center");
    model.component("comp1").geom("geom1").run("blk1");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", 5);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2").set("r", 3.5);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("size", new int[]{4, 1});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("pos", new int[]{4, 0});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input").set("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input2").set("c2", "r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("dif1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new int[]{15, 15, 2});
    model.component("comp1").geom("geom1").feature("blk2").set("base", "center");
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new int[]{0, 0, -1});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("ewfd").create("ps1", "PeriodicStructure", 3);
    model.component("comp1").physics("ewfd").feature("ps1").set("alpha1_inc", "theta");
    model.component("comp1").physics("ewfd").feature("ps1").set("LinearPol", "P");
    model.component("comp1").physics("ewfd").feature("ps1").feature("wee1")
         .set("DisplacementFieldModel", "RelativePermittivity");
    model.component("comp1").physics("ewfd").feature("ps1").create("pec2", "PerfectElectricConductor", 2);
    model.component("comp1").physics("ewfd").feature("ps1").feature("pec2").selection().set(9);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat1").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.component("comp1").material("mat1").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").label("Air");
    model.component("comp1").material("mat1").set("family", "air");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").label("Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").label("Piecewise 3");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").label("Analytic 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").label("Analytic 2a");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"200"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("molarmass", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("pressure");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.component("comp1").material("mat1").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp1").material("mat1").propertyGroup("idealGas").label("Ideal gas");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat1").propertyGroup("idealGas").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("idealGas").addInput("pressure");
    model.component("comp1").material("mat1").materialType("nonSolid");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u7535\u4ecb\u8d28");
    model.component("comp1").material("mat2").selection().set(2);
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermittivity", new String[]{"2.1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("electricconductivity", new String[]{"0"});

    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").autoMeshSize(1);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").view("view1").hideEntities().create("hide1");
    model.component("comp1").view("view1").hideEntities("hide1").geom("geom1", 2);
    model.component("comp1").view("view1").hideEntities("hide1").set(7, 8);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u7535\u573a (ewfd)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").set("smooth", "internal");
    model.result("pg1").feature("mslc1").set("data", "parent");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("unit", new String[]{"", "", "", ""});
    model.result("pg2").feature("glob1")
         .set("expr", new String[]{"ewfd.Rorder_0_0", "ewfd.Torder_0_0", "ewfd.RTtotal", "ewfd.Atotal"});
    model.result("pg2").feature("glob1")
         .set("descr", new String[]{"\u53cd\u5c04\u7387\uff0c\u9636\u6570 [0,0]", "\u900f\u5c04\u7387\uff0c\u9636\u6570 [0,0]", "\u603b\u53cd\u5c04\u7387\u548c\u900f\u5c04\u7387", "\u5438\u6536\u7387"});
    model.result("pg2").label("\u53cd\u5c04\u7387\u3001\u900f\u5c04\u7387\u548c\u5438\u6536\u7387 (ewfd)");
    model.result("pg2").feature("glob1").set("titletype", "none");
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u53cd\u5c04\u7387\u3001\u900f\u5c04\u7387\u548c\u5438\u6536\u7387 (1)");
    model.result("pg2").feature("glob1").set("xdataexpr", "freq");
    model.result("pg2").feature("glob1").set("xdataunit", "THz");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "all");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").label("\u6781\u5316\u56fe (ewfd)");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u6781\u5316\u72b6\u6001\uff0cColor\uff1aPhase\uff08Radians\uff09");
    model.result("pg3").setIndex("looplevelinput", "manual", 0);
    model.result("pg3").setIndex("looplevel", "1", 0);
    model.result("pg3").create("plz1", "Polarization");
    model.result("pg3").feature("plz1").set("linestyle", "solid");
    model.result("pg3").feature("plz1").set("linewidth", 2);
    model.result("pg3").feature("plz1").set("display", "0");
    model.result("pg3").feature("plz1").create("col1", "Color");
    model.result("pg3").feature("plz1").feature("col1").set("colortable", "Cyclic");
    model.result("pg3").feature("plz1").feature("col1").set("colorlegend", true);
    model.result("pg3").feature("plz1").set("legend", true);
    model.result("pg3").feature("plz1").set("legendmethod", "manual");
    model.result("pg3").feature("plz1").setIndex("legends", "\u53cd\u5c04", 0);
    model.result("pg3").create("plz2", "Polarization");
    model.result("pg3").feature("plz2").set("linestyle", "dashed");
    model.result("pg3").feature("plz2").set("linewidth", 2);
    model.result("pg3").feature("plz2").set("display", "1");
    model.result("pg3").feature("plz2").create("col1", "Color");
    model.result("pg3").feature("plz2").feature("col1").set("colortable", "Cyclic");
    model.result("pg3").feature("plz2").feature("col1").set("colorlegend", false);
    model.result("pg3").feature("plz2").set("legend", true);
    model.result("pg3").feature("plz2").set("legendmethod", "manual");
    model.result("pg3").feature("plz2").setIndex("legends", "\u4f20\u8f93", 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 9, 0);
    model.result("pg1").run();
    model.result("pg1").feature("mslc1").set("xnumber", "0");
    model.result("pg1").feature("mslc1").set("ynumber", "0");
    model.result("pg1").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg1").feature("mslc1").set("zcoord", -1);
    model.result("pg1").run();
    model.result("pg2").run();

    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").set("plotgroup", "Default");
    model.study("std2").feature("freq").set("ftplistmethod", "manual");
    model.study("std2").feature("freq").set("solnum", "auto");
    model.study("std2").feature("freq").set("notsolnum", "auto");
    model.study("std2").feature("freq").set("outputmap", new String[]{});
    model.study("std2").feature("freq").set("ngenAUX", "1");
    model.study("std2").feature("freq").set("goalngenAUX", "1");
    model.study("std2").feature("freq").set("ngenAUX", "1");
    model.study("std2").feature("freq").set("goalngenAUX", "1");
    model.study("std2").feature("freq").setSolveFor("/physics/ewfd", true);
    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").setIndex("pname", "theta", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "rad", 0);
    model.study("std2").feature("param").setIndex("pname", "theta", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "rad", 0);
    model.study("std2").feature("param").setIndex("plistarr", "range(0[deg],5[deg],85[deg])", 0);
    model.study("std2").feature("freq").set("plist", "4.6[THz]");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u7535\u573a (ewfd) 1");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevel", 18, 0);
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").feature().create("mslc1", "Multislice");
    model.result("pg4").feature("mslc1").set("smooth", "internal");
    model.result("pg4").feature("mslc1").set("data", "parent");
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("unit", new String[]{"", "", "", ""});
    model.result("pg5").feature("glob1")
         .set("expr", new String[]{"ewfd.Rorder_0_0", "ewfd.Torder_0_0", "ewfd.RTtotal", "ewfd.Atotal"});
    model.result("pg5").feature("glob1")
         .set("descr", new String[]{"\u53cd\u5c04\u7387\uff0c\u9636\u6570 [0,0]", "\u900f\u5c04\u7387\uff0c\u9636\u6570 [0,0]", "\u603b\u53cd\u5c04\u7387\u548c\u900f\u5c04\u7387", "\u5438\u6536\u7387"});
    model.result("pg5").label("\u53cd\u5c04\u7387\u3001\u900f\u5c04\u7387\u548c\u5438\u6536\u7387 (ewfd) 1");
    model.result("pg5").feature("glob1").set("titletype", "none");
    model.result("pg5").feature("glob1").set("xdata", "expr");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u53cd\u5c04\u7387\u3001\u900f\u5c04\u7387\u548c\u5438\u6536\u7387 (1)");
    model.result("pg5").feature("glob1").set("xdataexpr", "theta");
    model.result("pg5").feature("glob1").set("xdataunit", "rad");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("xdatasolnumtype", "all");
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").label("\u6781\u5316\u56fe (ewfd) 1");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").set("titletype", "manual");
    model.result("pg6").set("title", "\u6781\u5316\u72b6\u6001\uff0cColor\uff1aPhase\uff08Radians\uff09");
    model.result("pg6").setIndex("looplevelinput", "manual", 0);
    model.result("pg6").setIndex("looplevel", "1", 0);
    model.result("pg6").create("plz1", "Polarization");
    model.result("pg6").feature("plz1").set("linestyle", "solid");
    model.result("pg6").feature("plz1").set("linewidth", 2);
    model.result("pg6").feature("plz1").set("display", "0");
    model.result("pg6").feature("plz1").create("col1", "Color");
    model.result("pg6").feature("plz1").feature("col1").set("colortable", "Cyclic");
    model.result("pg6").feature("plz1").feature("col1").set("colorlegend", true);
    model.result("pg6").feature("plz1").set("legend", true);
    model.result("pg6").feature("plz1").set("legendmethod", "manual");
    model.result("pg6").feature("plz1").setIndex("legends", "\u53cd\u5c04", 0);
    model.result("pg6").create("plz2", "Polarization");
    model.result("pg6").feature("plz2").set("linestyle", "dashed");
    model.result("pg6").feature("plz2").set("linewidth", 2);
    model.result("pg6").feature("plz2").set("display", "1");
    model.result("pg6").feature("plz2").create("col1", "Color");
    model.result("pg6").feature("plz2").feature("col1").set("colortable", "Cyclic");
    model.result("pg6").feature("plz2").feature("col1").set("colorlegend", false);
    model.result("pg6").feature("plz2").set("legend", true);
    model.result("pg6").feature("plz2").set("legendmethod", "manual");
    model.result("pg6").feature("plz2").setIndex("legends", "\u4f20\u8f93", 0);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("mslc1").set("xnumber", "0");
    model.result("pg4").feature("mslc1").set("ynumber", "0");
    model.result("pg4").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg4").feature("mslc1").set("zcoord", -1);
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg5").feature("glob1").set("xdataunit", "\u00b0");
    model.result("pg5").run();

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").label("\u7aef\u53e3 1");
    model.component("comp1").selection("sel1").set(10);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").label("\u7aef\u53e3 2");
    model.component("comp1").selection("sel2").set(3);

    model.study().create("std3");
    model.study("std3").create("frawe", "FrequencyAdaptive");
    model.study("std3").feature("frawe").set("plotgroup", "Default");
    model.study("std3").feature("frawe").set("ftplistmethod", "manual");
    model.study("std3").feature("frawe").set("solnum", "auto");
    model.study("std3").feature("frawe").set("notsolnum", "auto");
    model.study("std3").feature("frawe").set("outputmap", new String[]{});
    model.study("std3").feature("frawe").setSolveFor("/physics/ewfd", true);
    model.study("std3").feature("frawe").set("plist", "range(3.8[THz],0.01[THz],5.4[THz])");
    model.study("std3").feature("frawe").setEntry("outputmap", "ewfd", "selection");
    model.study("std3").feature("frawe").setEntry("outputselectionmap", "ewfd", "sel1;sel2");
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").label("\u7535\u573a (ewfd) 2");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").setIndex("looplevel", 161, 0);
    model.result("pg7").set("frametype", "spatial");
    model.result("pg7").feature().create("mslc1", "Multislice");
    model.result("pg7").feature("mslc1").set("smooth", "internal");
    model.result("pg7").feature("mslc1").set("data", "parent");
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").set("data", "dset3");
    model.result("pg8").create("glob1", "Global");
    model.result("pg8").feature("glob1").set("unit", new String[]{"", "", "", ""});
    model.result("pg8").feature("glob1")
         .set("expr", new String[]{"ewfd.Rorder_0_0", "ewfd.Torder_0_0", "ewfd.RTtotal", "ewfd.Atotal"});
    model.result("pg8").feature("glob1")
         .set("descr", new String[]{"\u53cd\u5c04\u7387\uff0c\u9636\u6570 [0,0]", "\u900f\u5c04\u7387\uff0c\u9636\u6570 [0,0]", "\u603b\u53cd\u5c04\u7387\u548c\u900f\u5c04\u7387", "\u5438\u6536\u7387"});
    model.result("pg8").label("\u53cd\u5c04\u7387\u3001\u900f\u5c04\u7387\u548c\u5438\u6536\u7387 (ewfd) 2");
    model.result("pg8").feature("glob1").set("titletype", "none");
    model.result("pg8").feature("glob1").set("xdata", "expr");
    model.result("pg8").set("ylabelactive", true);
    model.result("pg8").set("ylabel", "\u53cd\u5c04\u7387\u3001\u900f\u5c04\u7387\u548c\u5438\u6536\u7387 (1)");
    model.result("pg8").feature("glob1").set("xdataexpr", "freq");
    model.result("pg8").feature("glob1").set("xdataunit", "THz");
    model.result("pg8").feature("glob1").set("markerpos", "datapoints");
    model.result("pg8").feature("glob1").set("xdatasolnumtype", "all");
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").label("\u6781\u5316\u56fe (ewfd) 2");
    model.result("pg9").set("data", "dset3");
    model.result("pg9").set("titletype", "manual");
    model.result("pg9").set("title", "\u6781\u5316\u72b6\u6001\uff0cColor\uff1aPhase\uff08Radians\uff09");
    model.result("pg9").setIndex("looplevelinput", "manual", 0);
    model.result("pg9").setIndex("looplevel", "1", 0);
    model.result("pg9").create("plz1", "Polarization");
    model.result("pg9").feature("plz1").set("linestyle", "solid");
    model.result("pg9").feature("plz1").set("linewidth", 2);
    model.result("pg9").feature("plz1").set("display", "0");
    model.result("pg9").feature("plz1").create("col1", "Color");
    model.result("pg9").feature("plz1").feature("col1").set("colortable", "Cyclic");
    model.result("pg9").feature("plz1").feature("col1").set("colorlegend", true);
    model.result("pg9").feature("plz1").set("legend", true);
    model.result("pg9").feature("plz1").set("legendmethod", "manual");
    model.result("pg9").feature("plz1").setIndex("legends", "\u53cd\u5c04", 0);
    model.result("pg9").create("plz2", "Polarization");
    model.result("pg9").feature("plz2").set("linestyle", "dashed");
    model.result("pg9").feature("plz2").set("linewidth", 2);
    model.result("pg9").feature("plz2").set("display", "1");
    model.result("pg9").feature("plz2").create("col1", "Color");
    model.result("pg9").feature("plz2").feature("col1").set("colortable", "Cyclic");
    model.result("pg9").feature("plz2").feature("col1").set("colorlegend", false);
    model.result("pg9").feature("plz2").set("legend", true);
    model.result("pg9").feature("plz2").set("legendmethod", "manual");
    model.result("pg9").feature("plz2").setIndex("legends", "\u4f20\u8f93", 0);
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").feature().remove("mslc1");
    model.result("pg7").run();
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").create("sel1", "Selection");
    model.result("pg7").feature("surf1").feature("sel1").selection().set(3, 10);
    model.result("pg7").run();
    model.result("pg8").run();
    model.result("pg8").set("legendpos", "lowerright");
    model.result("pg8").run();
    model.result("pg8").feature("glob1").setIndex("descr", "R \u81ea\u9002\u5e94\u9891\u7387\u626b\u63cf", 0);
    model.result("pg8").feature("glob1").setIndex("descr", "T \u81ea\u9002\u5e94\u9891\u7387\u626b\u63cf", 1);
    model.result("pg8").feature().duplicate("glob2", "glob1");
    model.result("pg8").run();

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg8").feature("glob2").setIndex("descr", "R \u5e38\u89c4\u626b\u63cf", 0);
    model.result("pg8").feature("glob2").setIndex("descr", "T \u5e38\u89c4\u626b\u63cf", 1);
    model.result("pg8").feature("glob2").set("data", "dset1");
    model.result("pg8").feature("glob2").set("linestyle", "dotted");
    model.result("pg8").feature("glob2").set("linemarker", "cycle");
    model.result("pg8").run();
    model.result().dataset().create("arr1", "Array3D");
    model.result().dataset("arr1").set("fullsize", new int[]{16, 16, 1});
    model.result().create("pg10", "PlotGroup3D");
    model.result("pg10").run();
    model.result("pg10").label("\u9635\u5217\u573a\u7ed8\u56fe");
    model.result("pg10").set("data", "arr1");
    model.result("pg10").setIndex("looplevel", 9, 0);
    model.result("pg10").set("titletype", "none");
    model.result("pg10").set("edges", false);
    model.result("pg10").set("showlegends", false);

    model.view("view3").set("showaxisorientation", false);

    model.result("pg10").create("surf1", "Surface");
    model.result("pg10").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg10").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg10").feature("surf1").create("filt1", "Filter");
    model.result("pg10").run();
    model.result("pg10").feature("surf1").feature("filt1").set("expr", "z>=-2.05[\u00b5m] && z<=0");
    model.result("pg10").run();
    model.result("pg10").feature("surf1").create("def1", "Deform");
    model.result("pg10").run();
    model.result("pg10").feature("surf1").feature("def1").set("expr", new String[]{"", "", "ewfd.normE"});
    model.result("pg10").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg10").feature("surf1").feature("def1").set("scale", "0.5E-8");
    model.result("pg10").run();
    model.result().create("pg11", "PlotGroup1D");
    model.result("pg11").run();
    model.result("pg11").label("\u5e26\u56fe\u5f62\u6807\u8bb0\u7684\u901a\u5e26");
    model.result("pg11").set("data", "dset3");
    model.result("pg11").create("glob1", "Global");
    model.result("pg11").feature("glob1").set("markerpos", "datapoints");
    model.result("pg11").feature("glob1").set("linewidth", "preference");
    model.result("pg11").feature("glob1").set("expr", new String[]{"ewfd.Torder_0_0"});
    model.result("pg11").feature("glob1").set("descr", new String[]{"\u900f\u5c04\u7387\uff0c\u9636\u6570 [0,0]"});
    model.result("pg11").feature("glob1").set("unit", new String[]{"1"});
    model.result("pg11").feature("glob1").create("gmrk1", "GraphMarker");
    model.result("pg11").feature("glob1").feature("gmrk1").set("linewidth", "preference");
    model.result("pg11").run();
    model.result("pg11").feature("glob1").feature("gmrk1").set("displaymode", "widthmode");
    model.result("pg11").feature("glob1").feature("gmrk1").set("cutoffvalabs", 0.5);
    model.result("pg11").feature("glob1").feature("gmrk1").set("precision", 3);
    model.result("pg11").feature("glob1").feature("gmrk1").set("includeunit", true);
    model.result("pg11").feature("glob1").feature("gmrk1").set("showframe", true);

    model
         .title("\u9891\u7387\u9009\u62e9\u8868\u9762\uff0c\u5468\u671f\u6027\u4e92\u8865\u5f00\u53e3\u8c10\u632f\u73af");

    model
         .description("\u9891\u7387\u9009\u62e9\u8868\u9762 (FSS) \u662f\u4e00\u79cd\u5177\u6709\u5e26\u901a\u6216\u5e26\u963b\u9891\u7387\u54cd\u5e94\u7684\u5468\u671f\u6027\u7ed3\u6784\u3002\u672c\u4f8b\u8868\u660e\uff0c\u53ea\u6709\u63a5\u8fd1\u4e2d\u5fc3\u9891\u7387\u7684\u4fe1\u53f7\u624d\u80fd\u901a\u8fc7\u5468\u671f\u6027\u4e92\u8865\u5f00\u53e3\u8c10\u632f\u73af\u5c42\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("frequency_selective_surface_csrr.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
