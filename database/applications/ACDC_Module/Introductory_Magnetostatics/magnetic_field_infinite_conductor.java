/*
 * magnetic_field_infinite_conductor.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:23 by COMSOL 6.3.0.290. */
public class magnetic_field_infinite_conductor {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Introductory_Magnetostatics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("mf", "InductionCurrents", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/mf", true);

    model.param().set("ri", "1[cm]");
    model.param().descr("ri", "\u5bfc\u4f53\u7684\u534a\u5f84");
    model.param().set("ro", "10[cm]");
    model.param().descr("ro", "\u8ba1\u7b97\u57df\u7684\u534a\u5f84");
    model.param().set("I0", "1[A]");
    model.param().descr("I0", "\u4f20\u5bfc\u7535\u6d41");
    model.param().set("J0", "I0/(pi*ri^2)");
    model.param().descr("J0", "\u5bfc\u4f53\u4e2d\u7684\u7535\u6d41\u5bc6\u5ea6");

    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "ro");
    model.component("comp1").geom("geom1").feature("c1").setIndex("layer", "ro-ri", 0);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").run("pt1");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").selection("vertex1").set("pt1", 1);
    model.component("comp1").geom("geom1").feature("ls1").selection("vertex2").set("c1", 7);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").physics("mf").create("ecd1", "ExternalCurrentDensity", 2);
    model.component("comp1").physics("mf").feature("ecd1").selection().set(4);
    model.component("comp1").physics("mf").feature("ecd1").set("Je", new String[]{"0", "0", "J0"});

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u78c1\u901a\u5bc6\u5ea6 (mf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("solutionparams", "parent");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature().create("str1", "Streamline");
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("solutionparams", "parent");
    model.result("pg1").feature("str1").set("titletype", "none");
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("udist", 0.03);
    model.result("pg1").feature("str1").set("maxlen", 0.4);
    model.result("pg1").feature("str1").set("maxsteps", 5000);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("inheritcolor", false);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("data", "parent");
    model.result("pg1").feature("str1").selection().geom("geom1", 1);
    model.result("pg1").feature("str1").selection().set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);
    model.result("pg1").feature("str1").set("inheritplot", "surf1");
    model.result("pg1").feature("str1").feature().create("col1", "Color");
    model.result("pg1").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg1").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg1").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg1").feature("str1").feature().create("filt1", "Filter");
    model.result("pg1").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg1").feature().create("con1", "Contour");
    model.result("pg1").feature("con1").set("showsolutionparams", "on");
    model.result("pg1").feature("con1").set("solutionparams", "parent");
    model.result("pg1").feature("con1").set("expr", "mf.Az");
    model.result("pg1").feature("con1").set("titletype", "none");
    model.result("pg1").feature("con1").set("number", 10);
    model.result("pg1").feature("con1").set("levelrounding", false);
    model.result("pg1").feature("con1").set("coloring", "uniform");
    model.result("pg1").feature("con1").set("colorlegend", false);
    model.result("pg1").feature("con1").set("color", "custom");
    model.result("pg1").feature("con1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg1").feature("con1").set("resolution", "fine");
    model.result("pg1").feature("con1").set("inheritcolor", false);
    model.result("pg1").feature("con1").set("showsolutionparams", "on");
    model.result("pg1").feature("con1").set("data", "parent");
    model.result("pg1").feature("con1").set("inheritplot", "surf1");
    model.result("pg1").feature("con1").feature().create("filt1", "Filter");
    model.result("pg1").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg1").run();

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 2);
    model.component("comp2").geom("geom2").axisymmetric(true);

    model.component("comp2").mesh().create("mesh2");

    model.component("comp2").geom("geom2").create("r1", "Rectangle");
    model.component("comp2").geom("geom2").feature("r1").set("size", new String[]{"ro", "2*ro"});
    model.component("comp2").geom("geom2").feature("r1").setIndex("layer", "ri", 0);
    model.component("comp2").geom("geom2").feature("r1").set("layerleft", true);
    model.component("comp2").geom("geom2").feature("r1").set("layerbottom", false);
    model.component("comp2").geom("geom2").run("fin");

    model.component("comp2").physics().create("mef", "ElectricInductionCurrents", "geom2");

    model.study("std1").feature("stat").setSolveFor("/physics/mef", true);

    model.component("comp2").physics("mef").prop("components").set("components", "inplane");
    model.component("comp2").physics("mef").feature("mi1").create("term1", "Terminal", 1);
    model.component("comp2").physics("mef").feature("mi1").feature("term1").selection().set(3);
    model.component("comp2").physics("mef").feature("mi1").feature("term1").set("I0", "I0");
    model.component("comp2").physics("mef").create("alcs1", "ElectromagneticModelSolid", 2);
    model.component("comp2").physics("mef").feature("alcs1").selection().set(1);
    model.component("comp2").physics("mef").create("al1", "AmperesLaw", 2);
    model.component("comp2").physics("mef").feature("al1").selection().set(2);
    model.component("comp2").physics("mef").create("gfa1", "GaugeFixingA", 2);

    model.study("std1").feature("stat").setSolveFor("/physics/mef", false);

    model.component("comp2").material().create("mat1", "Common");
    model.component("comp2").material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp2").material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp2").material("mat1").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp2").material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp2").material("mat1").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp2").material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp2").material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp2").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp2").material("mat1").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.component("comp2").material("mat1").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp2").material("mat1").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp2").material("mat1").label("Air");
    model.component("comp2").material("mat1").set("family", "air");
    model.component("comp2").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp2").material("mat1").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp2").material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp2").material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp2").material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp2").material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp2").material("mat1").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp2").material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp2").material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp2").material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp2").material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp2").material("mat1").propertyGroup("def").func("rho").label("Analytic");
    model.component("comp2").material("mat1").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp2").material("mat1").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp2").material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp2").material("mat1").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp2").material("mat1").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp2").material("mat1").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp2").material("mat1").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp2").material("mat1").propertyGroup("def").func("k").label("Piecewise 3");
    model.component("comp2").material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp2").material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp2").material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp2").material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp2").material("mat1").propertyGroup("def").func("cs").label("Analytic 2");
    model.component("comp2").material("mat1").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp2").material("mat1").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp2").material("mat1").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp2").material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat1").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp2").material("mat1").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp2").material("mat1").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp2").material("mat1").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp2").material("mat1").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp2").material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp2").material("mat1").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp2").material("mat1").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp2").material("mat1").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp2").material("mat1").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp2").material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp2").material("mat1").propertyGroup("def").func("an2").label("Analytic 2a");
    model.component("comp2").material("mat1").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp2").material("mat1").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp2").material("mat1").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp2").material("mat1").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp2").material("mat1").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat1").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"200"});
    model.component("comp2").material("mat1").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp2").material("mat1").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp2").material("mat1").propertyGroup("def").set("molarmass", "");
    model.component("comp2").material("mat1").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp2").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp2").material("mat1").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp2").material("mat1").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp2").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat1").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp2").material("mat1").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp2").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp2").material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp2").material("mat1").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp2").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp2").material("mat1").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp2").material("mat1").propertyGroup("def").addInput("temperature");
    model.component("comp2").material("mat1").propertyGroup("def").addInput("pressure");
    model.component("comp2").material("mat1").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp2").material("mat1").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat1").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.component("comp2").material("mat1").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp2").material("mat1").propertyGroup("idealGas").label("Ideal gas");
    model.component("comp2").material("mat1").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp2").material("mat1").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp2").material("mat1").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp2").material("mat1").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp2").material("mat1").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp2").material("mat1").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp2").material("mat1").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp2").material("mat1").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp2").material("mat1").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.component("comp2").material("mat1").propertyGroup("idealGas").addInput("temperature");
    model.component("comp2").material("mat1").propertyGroup("idealGas").addInput("pressure");
    model.component("comp2").material("mat1").materialType("nonSolid");
    model.component("comp2").material().duplicate("mat2", "mat1");
    model.component("comp2").material("mat2").label("\u5bfc\u4f53");
    model.component("comp2").material("mat2").selection().set(1);
    model.component("comp2").material("mat2").propertyGroup("def").set("electricconductivity", new String[]{"1e6"});

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/mf", true);
    model.study("std2").feature("stat").setSolveFor("/physics/mef", true);
    model.study("std2").feature("stat").setSolveFor("/physics/mf", false);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u78c1\u901a\u5bc6\u5ea6 (mef)");
    model.result("pg2").set("data", "dset3");
    model.result("pg2").set("dataisaxisym", "off");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("solutionparams", "parent");
    model.result("pg2").feature("surf1").set("expr", "mef.normB");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature().create("str1", "Streamline");
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("solutionparams", "parent");
    model.result("pg2").feature("str1").set("titletype", "none");
    model.result("pg2").feature("str1").set("posmethod", "uniform");
    model.result("pg2").feature("str1").set("udist", 0.02);
    model.result("pg2").feature("str1").set("maxlen", 0.4);
    model.result("pg2").feature("str1").set("maxsteps", 5000);
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("inheritcolor", false);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("data", "parent");
    model.result("pg2").feature("str1").selection().geom("geom2", 1);
    model.result("pg2").feature("str1").selection().set(1, 2, 3, 4, 5, 6, 7);
    model.result("pg2").feature("str1").set("inheritplot", "surf1");
    model.result("pg2").feature("str1").feature().create("col1", "Color");
    model.result("pg2").feature("str1").feature("col1").set("expr", "mef.normB");
    model.result("pg2").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg2").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg2").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg2").feature("str1").feature().create("filt1", "Filter");
    model.result("pg2").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "none");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("data", "dset3");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u78c1\u901a\u5bc6\u5ea6\uff0c\u56de\u8f6c\u51e0\u4f55 (mef)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").feature().create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("solutionparams", "parent");
    model.result("pg3").feature("vol1").set("expr", "mef.normB");
    model.result("pg3").feature("vol1").set("colortable", "Prism");
    model.result("pg3").feature("vol1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("vol1").set("colorcalibration", -0.8);
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("data", "parent");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u7535\u573a (mef)");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").set("dataisaxisym", "off");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("solutionparams", "parent");
    model.result("pg4").feature("surf1").set("expr", "mef.normE");
    model.result("pg4").feature("surf1").set("colortable", "Prism");
    model.result("pg4").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg4").feature().create("str1", "Streamline");
    model.result("pg4").feature("str1").set("showsolutionparams", "on");
    model.result("pg4").feature("str1").set("solutionparams", "parent");
    model.result("pg4").feature("str1").set("expr", new String[]{"mef.Er", "mef.Ez"});
    model.result("pg4").feature("str1").set("titletype", "none");
    model.result("pg4").feature("str1").set("posmethod", "uniform");
    model.result("pg4").feature("str1").set("udist", 0.02);
    model.result("pg4").feature("str1").set("maxlen", 0.4);
    model.result("pg4").feature("str1").set("maxsteps", 5000);
    model.result("pg4").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("str1").set("inheritcolor", false);
    model.result("pg4").feature("str1").set("showsolutionparams", "on");
    model.result("pg4").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("str1").set("showsolutionparams", "on");
    model.result("pg4").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("str1").set("showsolutionparams", "on");
    model.result("pg4").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("str1").set("showsolutionparams", "on");
    model.result("pg4").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("str1").set("data", "parent");
    model.result("pg4").feature("str1").selection().geom("geom2", 1);
    model.result("pg4").feature("str1").selection().set(1, 2, 3, 4, 5, 6, 7);
    model.result("pg4").feature("str1").set("inheritplot", "surf1");
    model.result("pg4").feature("str1").feature().create("col1", "Color");
    model.result("pg4").feature("str1").feature("col1").set("expr", "mef.normE");
    model.result("pg4").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg4").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg4").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg4").feature("str1").feature().create("filt1", "Filter");
    model.result("pg4").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg2").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u78c1\u901a\u5bc6\u5ea6\u6bd4\u8f83");
    model.result("pg5").set("titletype", "label");
    model.result("pg5").set("data", "none");
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg5").feature("lngr1").set("linewidth", "preference");
    model.result("pg5").feature("lngr1").set("data", "dset1");
    model.result("pg5").feature("lngr1").selection().set(3, 5);
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1").set("xdataexpr", "x");
    model.result("pg5").feature("lngr1").set("legend", true);
    model.result("pg5").feature("lngr1").set("autosolution", false);
    model.result("pg5").feature("lngr1").set("autodescr", true);
    model.result("pg5").feature("lngr1").set("legendsuffix", "\uff0c\u7ec4\u4ef6 1");
    model.result("pg5").feature("lngr1").set("linemarker", "cycle");
    model.result("pg5").feature("lngr1").set("markerpos", "interp");
    model.result("pg5").run();
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "r \u5750\u6807 (m)");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").create("lngr2", "LineGraph");
    model.result("pg5").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg5").feature("lngr2").set("linewidth", "preference");
    model.result("pg5").feature("lngr2").set("data", "dset3");
    model.result("pg5").feature("lngr2").selection().set(2, 5);
    model.result("pg5").feature("lngr2").set("expr", "mef.normB");
    model.result("pg5").feature("lngr2").set("xdata", "expr");
    model.result("pg5").feature("lngr2").set("xdataexpr", "r");
    model.result("pg5").feature("lngr2").set("legend", true);
    model.result("pg5").feature("lngr2").set("autosolution", false);
    model.result("pg5").feature("lngr2").set("autodescr", true);
    model.result("pg5").feature("lngr2").set("legendsuffix", "\uff0c\u7ec4\u4ef6 2");
    model.result("pg5").feature("lngr2").set("linemarker", "cycle");
    model.result("pg5").feature("lngr2").set("markerpos", "interp");
    model.result("pg5").run();
    model.result("pg5").create("lngr3", "LineGraph");
    model.result("pg5").feature("lngr3").set("markerpos", "datapoints");
    model.result("pg5").feature("lngr3").set("linewidth", "preference");
    model.result("pg5").feature("lngr3").set("data", "dset3");
    model.result("pg5").feature("lngr3").selection().set(2, 5);
    model.result("pg5").feature("lngr3").set("expr", "mu0_const*J0/2*r*(r<=ri)+mu0_const*I0/(2*pi*r)*(r>ri)");
    model.result("pg5").feature("lngr3").set("legend", true);
    model.result("pg5").feature("lngr3").set("autosolution", false);
    model.result("pg5").feature("lngr3").set("autoexpr", true);
    model.result("pg5").feature("lngr3").set("legendmethod", "manual");
    model.result("pg5").feature("lngr3").setIndex("legends", "\u89e3\u6790\u89e3", 0);
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").run();
    model.result("pg6").label("\u7a7a\u6c14\u57df\u8fd4\u56de\u7535\u6d41 (mef)");
    model.result("pg6").create("arws1", "ArrowSurface");
    model.result("pg6").feature("arws1").set("revcoordsys", "cylindrical");
    model.result("pg6").feature("arws1").set("expr", new String[]{"mef.Jsr", "mef.Jsphi", "mef.Jsz"});
    model.result("pg6").feature("arws1").set("descr", "\u8868\u9762\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg6").feature("arws1").set("arrowcount", 1500);
    model.result("pg6").feature("arws1").set("scaleactive", true);
    model.result("pg6").feature("arws1").set("scale", 0.002);
    model.result("pg6").feature("arws1").set("color", "black");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").create("arws2", "ArrowSurface");
    model.result("pg6").feature("arws2").set("revcoordsys", "cylindrical");
    model.result("pg6").feature("arws2").setIndex("expr", "mef.Jr", 0);
    model.result("pg6").feature("arws2").setIndex("expr", "mef.Jphi", 1);

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg6").feature("arws2").setIndex("expr", "mef.Jz", 2);
    model.result("pg6").feature("arws2").set("arrowcount", 300);
    model.result("pg6").feature("arws2").set("scaleactive", true);
    model.result("pg6").feature("arws2").set("scale", "4e-6");
    model.result("pg6").feature("arws2").set("color", "black");
    model.result("pg6").feature("arws2").set("titletype", "none");
    model.result("pg6").feature("arws2").create("sel1", "Selection");
    model.result("pg6").feature("arws2").feature("sel1").selection().set(1);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").create("vol1", "Volume");
    model.result("pg6").feature("vol1").set("titletype", "manual");
    model.result("pg6").feature("vol1").set("title", "\u4f53\uff1a\u7535\u52bf");
    model.result("pg6").feature("vol1").set("colortable", "Prism");
    model.result("pg6").run();
    model.result("pg1").run();
    model.result("pg1").feature("str1").active(false);
    model.result("pg2").run();
    model.result("pg2").feature("str1").set("posmethod", "selection");
    model.result("pg2").run();
    model.result("pg2").feature("str1").active(false);
    model.result("pg3").run();

    model.view("view3").set("showaxisorientation", false);
    model.view("view3").set("showgrid", false);

    model.result("pg3").run();
    model.result("pg3").feature("vol1").set("unit", "uT");
    model.result("pg3").run();
    model.result("pg3").run();

    model.title("\u65e0\u9650\u5bfc\u4f53\u7684\u78c1\u573a");

    model
         .description("\u8fd9\u4e2a\u4ecb\u7ecd\u6027\u6a21\u578b\u521b\u5efa\u4e86\u4e00\u4e2a\u7b80\u5355\u7684\u9759\u78c1\u95ee\u9898\u6a21\u578b\uff0c\u5176\u4e2d\u6709\u4e00\u6839\u65e0\u9650\u957f\u7684\u540c\u8f74\u7535\u7f06\u627f\u8f7d\u7535\u6d41\uff0c\u8fd9\u662f\u6559\u79d1\u4e66\u4e2d\u5e38\u89c1\u7684\u4e00\u4e2a\u6848\u4f8b\u3002\u7531\u4e8e\u8fd9\u4e2a\u95ee\u9898\u6709\u89e3\u6790\u89e3\uff0c\u56e0\u6b64\u53ef\u4ee5\u4f7f\u7528\u8be5\u6a21\u578b\u5c06\u7406\u8bba\u4e0e\u4eff\u771f\u6570\u503c\u7ed3\u679c\u8fdb\u884c\u6bd4\u8f83\u3002\u6a21\u578b\u4e2d\u672a\u5305\u542b\u540c\u8f74\u7535\u7f06\u4ee5\u5916\u7684\u533a\u57df\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("magnetic_field_infinite_conductor.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
