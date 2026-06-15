/*
 * hexagonal_grating.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:38 by COMSOL 6.3.0.290. */
public class hexagonal_grating {

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
    model.study("std1").create("wave", "Wavelength");
    model.study("std1").feature("wave").set("ftplistmethod", "manual");
    model.study("std1").feature("wave").set("solnum", "auto");
    model.study("std1").feature("wave").set("notsolnum", "auto");
    model.study("std1").feature("wave").set("outputmap", new String[]{});
    model.study("std1").feature("wave").set("ngenAUX", "1");
    model.study("std1").feature("wave").set("goalngenAUX", "1");
    model.study("std1").feature("wave").set("ngenAUX", "1");
    model.study("std1").feature("wave").set("goalngenAUX", "1");
    model.study("std1").feature("wave").setSolveFor("/physics/ewfd", true);

    model.param().set("lda0", "1[um]");
    model.param().descr("lda0", "\u6ce2\u957f");
    model.param().set("a", "lda0/2");
    model.param().descr("a", "\u516d\u8fb9\u5f62\u8fb9\u957f");
    model.param().set("h0", "3*lda0");
    model.param().descr("h0", "\u7a7a\u6c14\u9ad8\u5ea6");
    model.param().set("a1", "a/2");
    model.param().descr("a1", "\u7403\u9762\u534a\u5f84");
    model.param().set("theta", "pi/3");
    model.param().descr("theta", "\u4ef0\u89d2");
    model.param().set("phi", "12[deg]");
    model.param().descr("phi", "\u65b9\u4f4d\u89d2");

    model.study("std1").feature("wave").set("plist", "range(0.9[um],0.01[um],1.1[um])");

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "a", 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "a/2", 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "sqrt(3)/2*a", 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "-a/2", 2, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "sqrt(3)/2*a", 2, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "-a", 3, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 3, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "-a/2", 4, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "-sqrt(3)/2*a", 4, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "a/2", 5, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "-sqrt(3)/2*a", 5, 1);
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "h0", 0);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("sph1", "Sphere");
    model.component("comp1").geom("geom1").feature("sph1").set("r", "a1");
    model.component("comp1").geom("geom1").run("sph1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("ext1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("sph1");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").run();

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

    model.component("comp1").physics("ewfd").create("ps1", "PeriodicStructure", 3);
    model.component("comp1").physics("ewfd").feature("ps1").set("addListenerPort", false);
    model.component("comp1").physics("ewfd").feature("ps1").set("alpha1_inc", "theta");
    model.component("comp1").physics("ewfd").feature("ps1").set("alpha2_inc", "phi");
    model.component("comp1").physics("ewfd").feature("ps1")
         .set("DiffractionOrderSpecification", "FromCurrentParameters");
    model.component("comp1").physics("ewfd").feature("ps1").feature("fpc1")
         .set("splitPeriodicConditionSelections", true);
    model.component("comp1").physics("ewfd").feature("ps1").feature("fpc2")
         .set("splitPeriodicConditionSelections", true);
    model.component("comp1").physics("ewfd").feature("ps1").feature("fpc3")
         .set("splitPeriodicConditionSelections", true);
    model.component("comp1").physics("ewfd").feature("ps1").feature("pport1").runCommand("addDiffractionOrders");

    model.component("comp1").mesh("mesh1").run();

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
    model.result("pg2").feature("glob1").set("unit", new String[]{"", "", "", "", "", "", ""});
    model.result("pg2").feature("glob1")
         .set("expr", new String[]{"ewfd.Rorder_0_0", "ewfd.Rorder_n1_n1_ip", "ewfd.Rorder_n1_n1_op", "ewfd.Rorder_0_n1_ip", "ewfd.Rorder_0_n1_op", "ewfd.Rorder_0_0_orth", "ewfd.Rtotal"});
    model.result("pg2").feature("glob1")
         .set("descr", new String[]{"\u53cd\u5c04\u7387\uff0c\u9636\u6570 [0,0]", "\u53cd\u5c04\u7387\uff0c\u9636\u6570 [-1,-1]\uff0c\u9762\u5185", "\u53cd\u5c04\u7387\uff0c\u9636\u6570 [-1,-1]\uff0c\u9762\u5916", "\u53cd\u5c04\u7387\uff0c\u9636\u6570 [0,-1]\uff0c\u9762\u5185", "\u53cd\u5c04\u7387\uff0c\u9636\u6570 [0,-1]\uff0c\u9762\u5916", "\u53cd\u5c04\u7387\uff0c\u9636\u6570 [0,0]\uff0c\u6b63\u4ea4", "\u603b\u53cd\u5c04\u7387"});
    model.result("pg2").label("\u53cd\u5c04\u7387 (ewfd)");
    model.result("pg2").feature("glob1").set("titletype", "none");
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u53cd\u5c04\u7387 (1)");
    model.result("pg2").feature("glob1").set("xdataexpr", "lambda0");
    model.result("pg2").feature("glob1").set("xdataunit", "\u00b5m");
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
    model.result("pg3").feature("plz1").set("display", "2");
    model.result("pg3").feature("plz1").create("col1", "Color");
    model.result("pg3").feature("plz1").feature("col1").set("colortable", "Cyclic");
    model.result("pg3").feature("plz1").feature("col1").set("colorlegend", true);
    model.result("pg3").feature("plz1").set("legend", true);
    model.result("pg3").feature("plz1").set("legendmethod", "manual");
    model.result("pg3").feature("plz1").setIndex("legends", "\u53cd\u5c04", 0);
    model.result("pg3").create("plz2", "Polarization");
    model.result("pg3").feature("plz2").set("linestyle", "solid");
    model.result("pg3").feature("plz2").set("linewidth", 2);
    model.result("pg3").feature("plz2").set("display", "0");
    model.result("pg3").feature("plz2").create("col1", "Color");
    model.result("pg3").feature("plz2").feature("col1").set("colortable", "Cyclic");
    model.result("pg3").feature("plz2").feature("col1").set("colorlegend", false);
    model.result("pg3").create("plz3", "Polarization");
    model.result("pg3").feature("plz3").set("linestyle", "solid");
    model.result("pg3").feature("plz3").set("linewidth", 2);
    model.result("pg3").feature("plz3").set("display", "1");
    model.result("pg3").feature("plz3").create("col1", "Color");
    model.result("pg3").feature("plz3").feature("col1").set("colortable", "Cyclic");
    model.result("pg3").feature("plz3").feature("col1").set("colorlegend", false);
    model.result("pg1").run();
    model.result("pg1").create("arws1", "ArrowSurface");
    model.result("pg1").feature("arws1").set("expr", new String[]{"ewfd.kIncx_1", "ewfd.kIncy_1", "ewfd.kIncz_1"});
    model.result("pg1").feature("arws1").set("descr", "\u5165\u5c04\u6ce2\u77e2");
    model.result("pg1").feature("arws1").set("descractive", true);
    model.result("pg1").feature("arws1").set("descr", "\u5165\u5c04\u6ce2\uff08\u7ea2\u8272\uff09");
    model.result("pg1").feature("arws1").set("arrowcount", 12);
    model.result("pg1").feature("arws1").set("scaleactive", true);
    model.result("pg1").feature("arws1").set("scale", "3e-8");
    model.result("pg1").feature("arws1").create("sel1", "Selection");
    model.result("pg1").feature("arws1").feature("sel1").selection().set(4);
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("arws2", "arws1");
    model.result("pg1").run();
    model.result("pg1").feature("arws2")
         .set("expr", new String[]{"ewfd.kModex_1", "ewfd.kModey_1", "ewfd.kModez_1"});
    model.result("pg1").feature("arws2").set("color", "blue");
    model.result("pg1").feature("arws2").set("descr", "\u53cd\u5c04\u6ce2\uff08\u84dd\u8272\uff09");
    model.result("pg1").feature().duplicate("arws3", "arws2");
    model.result("pg1").run();
    model.result("pg1").feature("arws3")
         .set("expr", new String[]{"ewfd.kModex_3", "ewfd.kModey_3", "ewfd.kModez_3"});
    model.result("pg1").feature("arws3").set("color", "green");
    model.result("pg1").feature("arws3").set("descr", "\u6a21\u5f0f m = -1\uff0cn = -1\uff08\u7eff\u8272\uff09");
    model.result("pg1").feature().duplicate("arws4", "arws3");
    model.result("pg1").run();
    model.result("pg1").feature("arws4")
         .set("expr", new String[]{"ewfd.kModex_5", "ewfd.kModey_5", "ewfd.kModez_5"});
    model.result("pg1").feature("arws4").set("color", "yellow");
    model.result("pg1").feature("arws4").set("descr", "\u6a21\u5f0f m = 0\uff0cn = -1\uff08\u9ec4\u8272\uff09");
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 12, 0);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("ylabel", "\u884d\u5c04\u6548\u7387");
    model.result("pg2").set("legendpos", "upperleft");
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").set("legendpos", "upperleft");
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", new int[]{12}, 0);
    model.result("pg3").run();

    model.view().create("view3", 3);

    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").label("\u504f\u632f\u57fa\u77e2");
    model.result("pg4").set("titletype", "none");
    model.result("pg4").set("view", "view3");
    model.result("pg4").create("arws1", "ArrowSurface");
    model.result("pg4").feature("arws1").label("\u9762\u5916\u57fa\u77e2 (s-\u504f\u632f)");
    model.result("pg4").feature("arws1")
         .set("expr", new String[]{"ewfd.eJROOPx_0_0", "ewfd.eJROOPy_0_0", "ewfd.eJROOPz_0_0"});
    model.result("pg4").feature("arws1")
         .set("descr", "\u53cd\u5c04\u4fa7\u7684 Jones \u57fa\u77e2\uff0c\u9762\u5916\u65b9\u5411\uff0c[0,0] \u9636");
    model.result("pg4").feature("arws1").set("arrowcount", 1);
    model.result("pg4").feature().duplicate("arws2", "arws1");
    model.result("pg4").run();
    model.result("pg4").feature("arws2").label("\u9762\u5185\u57fa\u77e2 (p-\u504f\u632f)");
    model.result("pg4").feature("arws2")
         .set("expr", new String[]{"ewfd.eJRIPx_0_0", "ewfd.eJRIPy_0_0", "ewfd.eJRIPz_0_0"});
    model.result("pg4").feature("arws2")
         .set("descr", "\u53cd\u5c04\u4fa7\u7684 Jones \u57fa\u77e2\uff0c\u9762\u5185\u65b9\u5411\uff0c[0,0] \u9636");
    model.result("pg4").feature("arws2").set("color", "green");
    model.result("pg4").feature("arws2").set("inheritplot", "arws1");
    model.result("pg4").feature("arws2").set("inheritcolor", false);
    model.result("pg4").feature().duplicate("arws3", "arws2");
    model.result("pg4").run();
    model.result("pg4").feature("arws3").label("\u5f52\u4e00\u5316\u7684\u5165\u5c04\u6ce2\u77e2");
    model.result("pg4").feature("arws3").setIndex("expr", "ewfd.kModex_1/ewfd.k", 0);
    model.result("pg4").feature("arws3").setIndex("expr", "ewfd.kModey_1/ewfd.k", 1);
    model.result("pg4").feature("arws3").setIndex("expr", "ewfd.kModez_1/ewfd.k", 2);
    model.result("pg4").feature("arws3").set("color", "blue");
    model.result("pg4").feature("arws3").create("sel1", "Selection");
    model.result("pg4").feature("arws3").feature("sel1").selection().set(4);
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("arws4", "arws3");
    model.result("pg4").run();
    model.result("pg4").feature("arws4").label("\u8fb9\u754c\u6cd5\u5411");
    model.result("pg4").feature("arws4").set("expr", new String[]{"ewfd.nx", "ewfd.ny", "ewfd.nz"});
    model.result("pg4").feature("arws4").set("descr", "\u6cd5\u77e2");
    model.result("pg4").feature("arws4").set("color", "black");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("arws1").setIndex("expr", "ewfd.eJROOPx_n1_n1", 0);
    model.result("pg4").feature("arws1").setIndex("expr", "ewfd.eJROOPy_n1_n1", 1);
    model.result("pg4").feature("arws1").setIndex("expr", "ewfd.eJROOPz_n1_n1", 2);
    model.result("pg4").run();
    model.result("pg4").feature("arws2").setIndex("expr", "ewfd.eJRIPx_n1_n1", 0);
    model.result("pg4").feature("arws2").setIndex("expr", "ewfd.eJRIPy_n1_n1", 1);
    model.result("pg4").feature("arws2").setIndex("expr", "ewfd.eJRIPz_n1_n1", 2);
    model.result("pg4").run();
    model.result("pg4").feature("arws3").setIndex("expr", "ewfd.kModex_3/ewfd.k", 0);
    model.result("pg4").feature("arws3").setIndex("expr", "ewfd.kModey_3/ewfd.k", 1);
    model.result("pg4").feature("arws3").setIndex("expr", "ewfd.kModez_3/ewfd.k", 2);
    model.result("pg4").run();

    model.component("comp1").physics("ewfd").feature("ps1").set("LinearPol", "P");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").stepFirst(0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", new int[]{12}, 0);
    model.result("pg3").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"planeangle", "\u5e73\u9762\u89d2", "rad", "rad"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "\u00b0", 0, 3);
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u884d\u5c04\u89d2 (ewfd)");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").set("descr", new String[]{});
    model.result("pg5").feature("glob1").set("unit", new String[]{});
    model.result("pg5").feature("glob1")
         .set("expr", new String[]{"ewfd.alpha1R_0_0", "ewfd.alpha1R_0_n1", "ewfd.alpha1R_n1_n1"});
    model.result("pg5").feature("glob1").set("xdata", "expr");
    model.result("pg5").feature("glob1").set("xdataexpr", "ewfd.lambda0");
    model.result("pg5").feature("glob1").set("autosolution", false);
    model.result("pg5").feature().duplicate("glob2", "glob1");
    model.result("pg5").run();
    model.result("pg5").feature("glob2").set("descr", new String[]{});
    model.result("pg5").feature("glob2").set("unit", new String[]{});
    model.result("pg5").feature("glob2")
         .set("expr", new String[]{"ewfd.alpha2R_0_0", "ewfd.alpha2R_0_n1", "ewfd.alpha2R_n1_n1"});
    model.result("pg5").feature("glob2").set("linestyle", "none");
    model.result("pg5").feature("glob2").set("linecolor", "cyclereset");
    model.result("pg5").feature("glob2").set("linemarker", "cycle");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").set("titletype", "none");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u884d\u5c04\u89d2\uff08\u5ea6\uff09");
    model.result("pg5").set("legendlayout", "outside");
    model.result("pg5").set("legendposoutside", "bottom");
    model.result("pg5").run();
    model.result("pg1").run();

    model.title("\u516d\u8fb9\u5f62\u5149\u6805");

    model
         .description("\u4e00\u4e2a\u5e73\u9762\u6ce2\u5165\u5c04\u5230\u516d\u8fb9\u5f62\u53cd\u5c04\u5149\u6805\u4e0a\u3002\u5149\u6805\u5355\u5143\u5305\u542b\u4e00\u4e2a\u51f8\u8d77\u7684\u534a\u7403\u3002\u8ba1\u7b97\u4e86\u4e0d\u540c\u6ce2\u957f\u4e0b\u4e0d\u540c\u884d\u5c04\u7ea7\u7684\u6563\u5c04\u7cfb\u6570\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("hexagonal_grating.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
