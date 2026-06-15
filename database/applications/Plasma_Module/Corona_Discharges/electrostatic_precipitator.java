/*
 * electrostatic_precipitator.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:02 by COMSOL 6.3.0.290. */
public class electrostatic_precipitator {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Plasma_Module\\Corona_Discharges");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics().create("es", "Electrostatics", "geom1");
    model.component("comp1").physics("es").prop("ShapeProperty").set("order_electricpotential", "3");
    model.component("comp1").physics().create("ct", "ChargeTransport", "geom1");

    model.component("comp1").multiphysics().create("scdc1", "SpaceChargeDensityCoupling", 2);
    model.component("comp1").multiphysics("scdc1").set("SpaceChargeDensitySource_physics", "ct");
    model.component("comp1").multiphysics("scdc1").set("SpaceChargeDensityDestination_physics", "es");
    model.component("comp1").multiphysics("scdc1").selection().all();
    model.component("comp1").multiphysics().create("pc1", "PotentialCoupling", 2);
    model.component("comp1").multiphysics("pc1").set("PotentialSource_physics", "es");
    model.component("comp1").multiphysics("pc1").set("PotentialDestination_physics", "ct");
    model.component("comp1").multiphysics("pc1").selection().all();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std1").feature("stat").setSolveFor("/physics/es", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ct", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/scdc1", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/pc1", true);

    model.param().set("H", "0.1[m]");
    model.param().descr("H", "\u9ad8\u5ea6");
    model.param().set("rin", "0.5[mm]");
    model.param().descr("rin", "\u7535\u6781\u534a\u5f84");
    model.param().set("W", "0.7[m]");
    model.param().descr("W", "\u5bbd\u5ea6");
    model.param().set("sp", "15[cm]");
    model.param().descr("sp", "\u7535\u6781\u5206\u79bb");
    model.param().set("muN", "3e21[1/(V*m*s)]");
    model.param().descr("muN", "\u7ea6\u5316\u79bb\u5b50\u8fc1\u79fb\u7387");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"W", "H"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"0", "-H/2"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "rin");
    model.component("comp1").geom("geom1").feature("c1").set("pos", new String[]{"W/2-sp", "0"});
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("c1");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new int[]{3, 1});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new String[]{"sp", "0"});
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("arr1");
    model.component("comp1").geom("geom1").runPre("fin");
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

    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl1").selection().set(1);
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl1").set("Uavfdf", "1[m/s]");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("spf").feature("out1").selection().set(4);
    model.component("comp1").physics("spf").create("vf1", "VolumeForce", 2);
    model.component("comp1").physics("spf").feature("vf1").selection().set(1);
    model.component("comp1").physics("spf").feature("vf1").set("F", new String[]{"ct.Fehdx", "ct.Fehdy", "0"});
    model.component("comp1").physics("es").create("gnd1", "Ground", 1);
    model.component("comp1").physics("es").feature("gnd1").selection().set(2, 3);
    model.component("comp1").physics("ct").prop("TransportMechanism").set("Convection", true);
    model.component("comp1").physics("ct").feature("tp1").set("muiN", "muN");
    model.component("comp1").physics("ct").feature("tp1").set("u_src", "root.comp1.u");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u7535\u6781");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").set("groupcontang", true);
    model.component("comp1").selection("sel1").add(5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);

    model.component("comp1").multiphysics().create("el1", "Electrode", 1);
    model.component("comp1").multiphysics("el1").selection().named("sel1");
    model.component("comp1").multiphysics("el1").set("V0", "20[kV]");
    model.component("comp1").multiphysics("el1").set("rc", "rin");

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 5);
    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature().move("edg1", 4);
    model.component("comp1").mesh("mesh1").feature().move("edg1", 3);
    model.component("comp1").mesh("mesh1").feature().move("edg1", 2);
    model.component("comp1").mesh("mesh1").feature("edg1").selection().named("sel1");
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("numelem", 50);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7535\u6655\u548c\u5c42\u6d41");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u538b\u529b (spf)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("con1", "Contour");
    model.result("pg2").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("expr", "p");
    model.result("pg2").feature("con1").set("number", 40);
    model.result("pg2").feature("con1").set("levelrounding", false);
    model.result("pg2").feature("con1").set("smooth", "internal");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("data", "parent");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u7535\u52bf (es)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("solutionparams", "parent");
    model.result("pg3").feature("surf1").set("expr", "V");
    model.result("pg3").feature("surf1").set("colortable", "Dipole");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg3").feature().create("str1", "Streamline");
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("solutionparams", "parent");
    model.result("pg3").feature("str1").set("expr", new String[]{"es.Ex", "es.Ey"});
    model.result("pg3").feature("str1").set("titletype", "none");
    model.result("pg3").feature("str1").set("posmethod", "uniform");
    model.result("pg3").feature("str1").set("udist", 0.02);
    model.result("pg3").feature("str1").set("maxlen", 0.4);
    model.result("pg3").feature("str1").set("maxsteps", 5000);
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("inheritcolor", false);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("data", "parent");
    model.result("pg3").feature("str1").selection().geom("geom1", 1);
    model.result("pg3").feature("str1").selection().set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
    model.result("pg3").feature("str1").set("inheritplot", "surf1");
    model.result("pg3").feature("str1").feature().create("col1", "Color");
    model.result("pg3").feature("str1").feature("col1").set("expr", "V");
    model.result("pg3").feature("str1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg3").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg3").feature("str1").feature().create("filt1", "Filter");
    model.result("pg3").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u7535\u573a (es)");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("solutionparams", "parent");
    model.result("pg4").feature("surf1").set("expr", "es.normE");
    model.result("pg4").feature("surf1").set("colortable", "Prism");
    model.result("pg4").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg4").feature().create("str1", "Streamline");
    model.result("pg4").feature("str1").set("showsolutionparams", "on");
    model.result("pg4").feature("str1").set("solutionparams", "parent");
    model.result("pg4").feature("str1").set("expr", new String[]{"es.Ex", "es.Ey"});
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
    model.result("pg4").feature("str1").selection().geom("geom1", 1);
    model.result("pg4").feature("str1").selection().set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
    model.result("pg4").feature("str1").set("inheritplot", "surf1");
    model.result("pg4").feature("str1").feature().create("col1", "Color");
    model.result("pg4").feature("str1").feature("col1").set("expr", "es.normE");
    model.result("pg4").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg4").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg4").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg4").feature("str1").feature().create("filt1", "Filter");
    model.result("pg4").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").label("\u7a7a\u95f4\u7535\u8377\u5bc6\u5ea6 (ct)");
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("expr", "rhoq");
    model.result("pg5").feature("surf1").set("colortable", "Prism");
    model.result("pg5").feature("surf1").set("smooth", "internal");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result("pg1").run();

    model.component("comp1").physics().create("fpt", "FluidParticleTracing", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/fpt", true);

    model.component("comp1").physics("fpt").prop("Formulation")
         .setIndex("Formulation", "NewtonianIgnoreInertialTerms", 0);
    model.component("comp1").physics("fpt").prop("ParticleSizeDistribution")
         .setIndex("ParticleSizeDistribution", "SpecifyParticleDiameter", 0);
    model.component("comp1").physics("fpt").prop("IncludeRarefactionEffects")
         .setIndex("IncludeRarefactionEffects", 1, 0);
    model.component("comp1").physics("fpt").prop("StoreParticleStatusData")
         .setIndex("StoreParticleStatusData", 1, 0);
    model.component("comp1").physics("fpt").create("out1", "Outlet", 1);
    model.component("comp1").physics("fpt").feature("out1").selection().set(4);
    model.component("comp1").physics("fpt").create("pcnt1", "ParticleCounter", 1);
    model.component("comp1").physics("fpt").feature("pcnt1").selection().set(4);
    model.component("comp1").physics("fpt").create("df1", "DragForce", 2);
    model.component("comp1").physics("fpt").feature("df1").selection().set(1);
    model.component("comp1").physics("fpt").feature("df1").set("u_src", "root.comp1.u");
    model.component("comp1").physics("fpt").create("ef1", "ElectricForce", 2);
    model.component("comp1").physics("fpt").feature("ef1").selection().set(1);
    model.component("comp1").physics("fpt").feature("ef1").set("SpecifyForceUsing", "ElectricPotential");
    model.component("comp1").physics("fpt").feature("ef1").set("V_src", "root.comp1.V");
    model.component("comp1").physics("fpt").create("cacc1", "ChargeAccumulation", 2);
    model.component("comp1").physics("fpt").feature("cacc1").selection().set(1);
    model.component("comp1").physics("fpt").feature("cacc1").set("rhoq_src", "root.comp1.rhoq");
    model.component("comp1").physics("fpt").feature("cacc1").set("muiN", "muN");
    model.component("comp1").physics("fpt").feature("cacc1").set("V_src", "root.comp1.V");
    model.component("comp1").physics("fpt").feature("pp1").set("rhop_mat", "userdef");
    model.component("comp1").physics("fpt").feature("pp1").set("ChargeSpecification", "cacc1");
    model.component("comp1").physics("fpt").feature("pp1").set("erp_mat", "userdef");
    model.component("comp1").physics("fpt").feature("pp1").set("erp", 5);
    model.component("comp1").physics("fpt").create("relg1", "ReleaseGrid", -1);
    model.component("comp1").physics("fpt").feature("relg1").setIndex("x0", "range(-0.049,0.002,0.049)", 1);
    model.component("comp1").physics("fpt").feature("relg1").set("DpDistributionFunction", "ListOfValues");
    model.component("comp1").physics("fpt").feature("relg1")
         .setIndex("dpvals", "10^{range(log10(2.0e-8),1/10,log10(2.0e-5))}", 0);

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/spf", true);
    model.study("std2").feature("time").setSolveFor("/physics/es", true);
    model.study("std2").feature("time").setSolveFor("/physics/ct", true);
    model.study("std2").feature("time").setSolveFor("/physics/fpt", true);
    model.study("std2").feature("time").setSolveFor("/multiphysics/scdc1", true);
    model.study("std2").feature("time").setSolveFor("/multiphysics/pc1", true);
    model.study("std2").feature("time").setSolveFor("/multiphysics/el1", true);
    model.study("std2").feature("time").setSolveFor("/physics/spf", false);
    model.study("std2").feature("time").setSolveFor("/physics/es", false);
    model.study("std2").feature("time").setSolveFor("/multiphysics/scdc1", false);
    model.study("std2").feature("time").setSolveFor("/multiphysics/pc1", false);
    model.study("std2").feature("time").set("tlist", "range(0,0.01,1.7)");
    model.study("std2").feature("time").set("usesol", true);
    model.study("std2").feature("time").set("notsolmethod", "sol");
    model.study("std2").feature("time").set("notstudy", "std1");
    model.study("std2").label("\u9897\u7c92\u8ddf\u8e2a");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().create("part1", "Particle");
    model.result().dataset("part1").set("solution", "sol2");
    model.result().dataset("part1").set("posdof", new String[]{"comp1.qx", "comp1.qy"});
    model.result().dataset("part1").set("geom", "geom1");
    model.result().dataset("part1").set("pgeom", "pgeom_fpt");
    model.result().dataset("part1").set("pgeomspec", "fromphysics");
    model.result().dataset("part1").set("physicsinterface", "fpt");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").set("data", "part1");
    model.result("pg6").setIndex("looplevel", 171, 0);
    model.result("pg6").label("\u7c92\u5b50\u8f68\u8ff9 (fpt)");
    model.result("pg6").create("traj1", "ParticleTrajectories");
    model.result("pg6").feature("traj1").set("pointtype", "point");
    model.result("pg6").feature("traj1").set("linetype", "none");
    model.result("pg6").feature("traj1").create("col1", "Color");
    model.result("pg6").feature("traj1").feature("col1").set("expr", "fpt.V");
    model.result("pg6").run();
    model.result("pg6").label("\u9897\u7c92\u8f68\u8ff9 rp = 1e-8 m");
    model.result("pg6").set("titletype", "manual");
    model.result("pg6").set("title", "rp = 0.01 \\mu m");
    model.result("pg6").set("paramindicator", "");
    model.result("pg6").run();
    model.result("pg6").feature("traj1").set("linetype", "line");
    model.result("pg6").feature("traj1").set("interpolation", "uniform");
    model.result("pg6").feature("traj1").set("interpcount", 1000);
    model.result("pg6").run();
    model.result("pg6").feature("traj1").feature("col1").set("expr", "fpt.Z");
    model.result("pg6").run();
    model.result("pg6").feature("traj1").create("filt1", "ParticleTrajectoriesFilter");
    model.result("pg6").run();
    model.result("pg6").feature("traj1").feature("filt1").set("type", "logical");
    model.result("pg6").feature("traj1").feature("filt1").set("logicalexpr", "abs(fpt.rp-10^-8)<1e-20");
    model.result("pg6").run();
    model.result("pg6").run();

    return model;
  }

  public static Model run2(Model model) {
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("\u9897\u7c92\u8f68\u8ff9 rp = 2e-7 m");
    model.result("pg7").set("title", "rp = 0.2 \\mu m");
    model.result("pg7").run();
    model.result("pg7").feature("traj1").feature("filt1").set("logicalexpr", "abs(fpt.rp-10^-6.7)<1e-10");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result().duplicate("pg8", "pg7");
    model.result("pg8").run();
    model.result("pg8").label("\u9897\u7c92\u8f68\u8ff9 rp = 2e-6 m");
    model.result("pg8").set("title", "rp = 2 \\mu m");
    model.result("pg8").run();
    model.result("pg8").feature("traj1").feature("filt1").set("logicalexpr", "abs(fpt.rp-10^-5.7)<1e-10");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result().duplicate("pg9", "pg8");
    model.result("pg9").run();
    model.result("pg9").label("\u9897\u7c92\u8f68\u8ff9 rp = 5e-6 m");
    model.result("pg9").set("title", "rp = 5 \\mu m");
    model.result("pg9").run();
    model.result("pg9").feature("traj1").feature("filt1").set("logicalexpr", "abs(fpt.rp-10^-5.3)<1e-10");
    model.result("pg9").run();
    model.result().dataset().create("pbin1", "ParticleBin");
    model.result().dataset("pbin1").set("expr", "fpt.rp");
    model.result().dataset("pbin1").set("method", "tolerance");
    model.result().dataset("pbin1").set("tolerance", "1e-9");
    model.result().create("pg10", "PlotGroup1D");
    model.result("pg10").run();
    model.result("pg10").label("\u6548\u7387 vs. \u9897\u7c92\u534a\u5f84");
    model.result("pg10").set("data", "pbin1");
    model.result("pg10").setIndex("looplevelinput", "last", 0);
    model.result("pg10").set("titletype", "none");
    model.result("pg10").set("xlabelactive", true);
    model.result("pg10").set("xlabel", "\u9897\u7c92\u534a\u5f84 (\u00b5m)");
    model.result("pg10").set("ylabelactive", true);
    model.result("pg10").set("ylabel", "\u9897\u7c92\u6536\u96c6\u6548\u7387");
    model.result("pg10").set("xlog", true);
    model.result("pg10").create("ptp1", "Particle1D");
    model.result("pg10").feature("ptp1").set("markerpos", "datapoints");
    model.result("pg10").feature("ptp1").set("linewidth", "preference");
    model.result("pg10").feature("ptp1").set("expr", "1-fpt.pcnt1.rL");
    model.result("pg10").feature("ptp1").set("xdata", "expr");
    model.result("pg10").feature("ptp1").set("xdataexpr", "fpt.rp");
    model.result("pg10").feature("ptp1").set("xdataunit", "\u00b5m");
    model.result("pg10").run();
    model.result("pg10").run();
    model.result().duplicate("pg11", "pg10");
    model.result("pg11").run();
    model.result("pg11").label("\u7d2f\u79ef\u7535\u8377\u6570 vs. \u9897\u7c92\u534a\u5f84");
    model.result("pg11").set("ylabel", "\u7d2f\u79ef\u7535\u8377\u6570");
    model.result("pg11").set("ylog", true);
    model.result("pg11").run();
    model.result("pg11").feature("ptp1").set("expr", "fpt.cacc1.Za");
    model.result("pg11").run();
    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg5").run();
    model.result("pg6").run();
    model.result("pg7").run();
    model.result("pg8").run();
    model.result("pg9").run();
    model.result("pg10").run();
    model.result("pg11").run();

    model.title("\u9759\u7535\u9664\u5c18\u5668");

    model
         .description("\u672c\u6a21\u578b\u8ba1\u7b97\u9759\u7535\u9664\u5c18\u5668\u4e2d\u7684\u6d41\u4f53\u6d41\u52a8\u3001\u7535\u8377\u4f20\u8f93\u548c\u7535\u52bf\u5206\u5e03\u3002\u5176\u4e2d\u57fa\u4e8e\u4ea7\u751f\u7684\u573a\uff0c\u5c06\u4e0d\u540c\u76f4\u5f84\u7684\u9897\u7c92\u9001\u5165\u8bbe\u5907\uff0c\u5e76\u8ba1\u7b97\u5176\u4f20\u8f93\u6982\u7387\u3002\u6b63\u5982\u9884\u671f\u7684\u90a3\u6837\uff0c\u5f53\u9897\u7c92\u534a\u5f84\u7ea6\u4e3a 0.2\u00a0\u5fae\u7c73\u65f6\uff0c\u5206\u79bb\u6548\u7387\u51fa\u73b0\u6700\u4f4e\u503c\u3002\n\n\u6b64\u5916\uff0c\u8be5\u6a21\u578b\u8fd8\u9700\u8981\u201c\u7c92\u5b50\u8ffd\u8e2a\u6a21\u5757\u201d\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("electrostatic_precipitator.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
